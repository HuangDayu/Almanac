//package cn.huangdayu.almanac.utils;
//
//import cn.huangdayu.almanac.aggregates.solar_term.SolarTerm;
//import cn.huangdayu.almanac.dto.TimeZoneDTO;
//
//import java.util.*;
//
///**
// * 24节气工具类
// *
// * @author huangdayu
// * @update 2020-03-15
// */
//public class SolarTermUtils {
//
//    /***
//     * 定气测试函数
//     *
//     * @param year
//     * @return
//     */
//    @Deprecated
//    private static String[] get24SolarTerm(int year) {
//        String[] solarTerms = new String[24];
//        double T;
//        int y = CommonUtils.year2Ayear(String.valueOf(year)) - 2001;
//        int n = 24;
//        for (int i = 21, index = 0; i < n; ) {
//            T = AstronomyArithmeticUtils.S_aLon_t((y + (double) i * 15 / 360 + 1) * 2 * Math.PI); // 精确节气时间计算
//            String solarTerm = JulianCalendarUtils.julianDays2str(T * 36525 + CommonUtils.JULIAN_FOR_2000 + (double) 8 / 24 - CommonUtils.dtT(T * 36525))
//                    + " " + AnnalsUtils.JIEQI[(i - 18 >= 0 ? i - 18 : i + 6) % 24]; // 日期转为字串
//            solarTerms[index] = solarTerm.trim();
//            i++;
//            index++;
//            if (i == 24) {
//                i = 0;
//                n = 21;
//                y += 1;
//            }
//        }
//        return solarTerms;
//    }
//
//    /***
//     * 查询指定节气的日期
//     * @param solarTermName
//     * @return
//     */
//    public static String getSolarTermDate(AlmanacDTO almanacDTO, String solarTermName) {
//        String[] allSolarTerm = get24SolarTerm(almanacDTO.getGregorianYear());
//        for (String solarTerm : allSolarTerm) {
//            String sub = solarTerm.substring(solarTerm.length() - 2, solarTerm.length());
//            if (sub.equals(solarTermName)) {
//                return solarTerm.substring(0, solarTerm.length() - 2);
//            }
//        }
//        return null;
//    }
//
//
//    /**
//     * 获取该年份的所有节气
//     *
//     * @param year
//     * @return
//     */
//    public static List<SolarTerm> getSolarTermsByYear(int year) {
//        List<SolarTerm> solarTerms = new ArrayList<>();
//        // 计算天文纪年
//        int chronicle = CommonUtils.year2Ayear(String.valueOf(year)) - 2001, n = 24;
//        // 先申明引用，避免反复申请栈空间
//        double sunLonValue, sunLonTime, julianDays;
//        // 从立春开始计算
//        for (int i = 21; i < n; ) {
//            // 计算太阳视黄经
//            sunLonValue = (chronicle + (double) i * 15 / 360 + 1) * 2 * Math.PI;
//            // 计算精确节气时间
//            sunLonTime = AstronomyArithmeticUtils.S_aLon_t(sunLonValue);
//            // 计算气朔
//            julianDays = sunLonTime * 36525 + CommonUtils.JULIAN_FOR_2000 + (double) 8 / 24 - CommonUtils.dtT(sunLonTime * 36525);
//            SolarTerm solarTerm = new SolarTerm();
//            String name = AnnalsUtils.JIEQI[(i - 18 >= 0 ? i - 18 : i + 6) % 24];
//            solarTerm.setName(name);
//            solarTerm.setDesc(ConstantsUtils.getDesc(name));
//            solarTerm.setDateTime(JulianCalendarUtils.julianDays2str(julianDays));
//            solarTerms.add(solarTerm);
//            i++;
//            if (i == 24) {
//                i = 0;
//                n = 21;
//                chronicle += 1;
//            }
//        }
//        return solarTerms;
//    }
//
//    /**
//     * 获取最近节气
//     *
//     * @param current
//     * @param solarTerms
//     * @param timeZoneDTO
//     * @return
//     */
//    public static SolarTerm getSolarTerm(boolean current, List<SolarTerm> solarTerms, TimeZoneDTO timeZoneDTO) {
//        Map<Integer, String> treeMap = new TreeMap<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;  //倒序.这里说明一下，如果返回负值，则o1先输出，反之则o2
//            }
//        });
//        for (int i = 0; i < solarTerms.size(); i++) {
//            SolarTerm solarTerm = solarTerms.get(i);
//            int cha = solarTerm.getJulianDay() - timeZoneDTO.getJulianDay();
//            if (cha >= 0 && cha < 14) {
//                return current ? solarTerm : solarTerms.get(i + 1);
//            }
//        }
//        List<SolarTerm> list = getSolarTermsByYear(timeZoneDTO.getYear() - 1);
//        list.addAll(solarTerms);
//        list.addAll(getSolarTermsByYear(timeZoneDTO.getYear() + 1));
//        return getSolarTerm(current, list, timeZoneDTO);
//    }
//
//}
