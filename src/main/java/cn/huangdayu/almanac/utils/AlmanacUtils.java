package cn.huangdayu.almanac.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.huangdayu.almanac.dto.*;
import cn.huangdayu.almanac.entity.QiShuoDO;

/**
 * 历计算工具类
 *
 * @author huangdayu
 * @update 2020-03-15
 */
public class AlmanacUtils {

    /**
     * 日历
     *
     * @param timeZoneDTO
     * @return
     * @throws cn.huangdayu.almanac.exception.AlmanacException
     */
    public static AlmanacDTO dayCalendar(TimeZoneDTO timeZoneDTO) {
        /** 数组从0开始，所以要减1*/
        int index = timeZoneDTO.getCalendar().get(Calendar.DATE) - 1;
        return monthCalendar(timeZoneDTO)[index];
    }

    /**
     * 月历
     *
     * @param timeZoneDTO
     * @return
     * @throws cn.huangdayu.almanac.exception.AlmanacException
     */
    public static AlmanacDTO[] monthCalendar(TimeZoneDTO timeZoneDTO) {

        int julianDay;

        // 某年气朔的数据信息
        QiShuoDO qiShuoDO = new QiShuoDO();

        // FIXME 2021-01-22 不知为何减去西历两千年的儒略日
        // 这个月1号的儒略日,公历月首,中午
        int julianDaysForMonthFirst = (int) (Math.floor(JulianCalendarUtils.getJulianDayNumber(timeZoneDTO.getYear(), timeZoneDTO.getMonth())) - CommonUtils.JULIAN_FOR_2000);

        // 这个月1号的儒略日和下个月1号的儒略日之差,月天数(公历)
        int julianDaysForMonth = (int) (Math.floor(JulianCalendarUtils.getJulianDayNumber(timeZoneDTO.getYear(), timeZoneDTO.getMonth() + 1)) - CommonUtils.JULIAN_FOR_2000 - julianDaysForMonthFirst);

        // 本月第一天的星期
        int weekFirstForMonthIndex = (julianDaysForMonthFirst + CommonUtils.JULIAN_FOR_2000 + 1 + 7000000) % 7;

        // 所属公历年对应的农历干支纪年
        int chineseEraYear = timeZoneDTO.getYear() - 1984 + 12000;

        // 提取各日信息
        AlmanacDTO[] almanacDTOS = new AlmanacDTO[julianDaysForMonth];

        // 计算世界时与原子时之差
        double jd2 = julianDaysForMonthFirst + CommonUtils.dtT(julianDaysForMonthFirst) - (double) 8 / 24;

        // 计算太阳黄经
        double sunLon = AstronomyArithmeticUtils.S_aLon(jd2 / 36525, 3);
        sunLon = (int) Math.floor((sunLon - 0.13) / CommonUtils.PI_2 * 24) * CommonUtils.PI_2 / 24;

        // 月日视黄经的差值
        double moonLon = AstronomyArithmeticUtils.MS_aLon(jd2 / 36525, 10, 3);
        moonLon = (int) Math.floor((moonLon - 0.78) / Math.PI * 2) * Math.PI / 2;

        // 计算月历
        for (int i = 0, j = 0; i < julianDaysForMonth; i++) {
            // 叠加日期，重构对象
            TimeZoneDTO timeZoneForThisDay = new TimeZoneDTO(timeZoneDTO, i + 1, julianDaysForMonth + i);


            //------------------------------------计算儒略日,北京时12:00------------------------------------//
            JulianDTO julianDTO = new JulianDTO();
            int julianDayForThisDay = julianDaysForMonthFirst + i;
            julianDTO.setDays(julianDayForThisDay + CommonUtils.JULIAN_FOR_2000);

            //------------------------------------计算日出月落,经纬度,港口------------------------------------//
            SunMoonDTO sunMoonDTO = new SunMoonDTO();
            SunMoonUtils.init(timeZoneForThisDay, sunMoonDTO);
            sunMoonDTO.setPortName(PortUtils.getProtName(PropertiesUtils.getLATPORT(), PropertiesUtils.getLOOGPORT()));

            //------------------------------------计算回历------------------------------------//
            IslamicDTO islamicDTO = IslamicCalendarUtils.setIslamicCalendar(julianDayForThisDay);


            //------------------------------------计算西历,伽利略历------------------------------------//
            GregorianDTO gregorianDTO = new GregorianDTO();
            Calendar calendar = timeZoneForThisDay.getCalendar();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int hours = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            // 公历月内日序数
            gregorianDTO.setDayIndexForMonth(i);
            // 公历年
            gregorianDTO.setYear(year);
            // 公历月
            gregorianDTO.setMonth(month);
            // 公历日名称
            gregorianDTO.setDay(day);
            // 公历月天数
            gregorianDTO.setDaysOfMonth(julianDaysForMonth);
            // 月首的星期
            gregorianDTO.setWeekFirstForMonth(weekFirstForMonthIndex);
            // 当前日的星期
            gregorianDTO.setWeek((weekFirstForMonthIndex + i) % 7);
            // 本日所在的周序号
            gregorianDTO.setWeekIndexForMonth((int) Math.floor((weekFirstForMonthIndex + i) / 7));
            // 本月的总周数
            gregorianDTO.setWeeksOfMonth((int) Math.floor((weekFirstForMonthIndex + julianDaysForMonth - 1) / 7) + 1);

            //------------------------------------农历排月序计算------------------------------------//
            // 此处有线程安全问题, 暂时直接实例化，qiShuoDO.calcY是为减少计算次数而做的判断。（同一年数据一样）
            if (julianDayForThisDay < qiShuoDO.ZQ[0] || julianDayForThisDay >= qiShuoDO.ZQ[24]) {
                qiShuoDO.calcY(julianDayForThisDay);
            }

            // 农历所在月的序数
            int lunarMonthIndex = (int) Math.floor((julianDayForThisDay - qiShuoDO.HS[0]) / 30);
            if (lunarMonthIndex < 13 && qiShuoDO.HS[lunarMonthIndex + 1] <= julianDayForThisDay) {
                lunarMonthIndex++;
            }


            //------------------------------------计算农历------------------------------------//
            LunarDTO lunarDTO = new LunarDTO();
            // 距农历月首的编移量,0对应初一
            lunarDTO.setMonthOffset(julianDayForThisDay - qiShuoDO.HS[lunarMonthIndex]);
            // 农历日名称
            lunarDTO.setDay(AnnalsUtils.DAY_NAME[julianDayForThisDay - qiShuoDO.HS[lunarMonthIndex]]);
            lunarDTO.setLeapYear(qiShuoDO.leap > 0);
            // 月的信息
            if (julianDayForThisDay == qiShuoDO.HS[lunarMonthIndex] || julianDayForThisDay == julianDaysForMonthFirst) {
                // 月名称
                lunarDTO.setMonth(qiShuoDO.ym[lunarMonthIndex]);
                // 月大小
                lunarDTO.setDaysOfMonth(qiShuoDO.dx[lunarMonthIndex]);
                // 闰状况
                lunarDTO.setLeapDesc((qiShuoDO.leap != 0 && qiShuoDO.leap == lunarMonthIndex) ? "闰" : "");
                // 下个月名称,判断除夕时要用到
                lunarDTO.setNextMonth(lunarMonthIndex < 13 ? qiShuoDO.ym[lunarMonthIndex + 1] : "未知");
            } else {
                // bug 存在一个数组下标溢出异常 Index -1 out of bounds for length 31
                // 获得前一天的信息
                LunarDTO lastLunarDay = almanacDTOS[i - 1].getLunarDTO();
                lunarDTO.setMonth(lastLunarDay.getMonth());
                lunarDTO.setDaysOfMonth(lastLunarDay.getDaysOfMonth());
                lunarDTO.setLeapDesc(lastLunarDay.getLeapDesc());
                lunarDTO.setNextMonth(lastLunarDay.getNextMonth());
            }
            int sum = (int) (hours + 0.01 * minute);
            int index = (sum + 1) / 2;
            if (index >= 12) {
                index = 0;
            }
            String lunarTime = AnnalsUtils.DIZHI[index] + "时" + ConstantsUtils.GENG[index];
            if ((minute % 15) > 13) {
                int k = (minute + 3) / 15;
                lunarTime = lunarTime + ConstantsUtils.KE[k];
            }
            lunarDTO.setTime(lunarTime);

            double sunLon1 = sunLon;
            SolarTermDTO solarTermDTO = new SolarTermDTO();
            List<SolarTermDTO> solarTermAfterDTOS = new ArrayList<>();
            for (int k = 0; k < 24; k++) {
                double d = AnnalsUtils.qi_accurate(sunLon1);
                sunLon1 += CommonUtils.PI_2 / 24;
                SolarTermDTO solarTerm = new SolarTermDTO();
                solarTerm.setJulianTime(d);
                solarTerm.setDateTime(JulianCalendarUtils.getJulianToDateTime(CommonUtils.JULIAN_FOR_2000 + d));
                solarTerm.setIndex(k);
                solarTerm.setName(AnnalsUtils.JIEQI[k]);
                solarTerm.setJulianDay(qiShuoDO.ZQ[k] + CommonUtils.JULIAN_FOR_2000);
                solarTerm.setDesc(SolarTermUtils.getSolarTermDesc(AnnalsUtils.JIEQI[k]));
                int afterDay = qiShuoDO.ZQ[k] - julianDayForThisDay;
                solarTerm.setAfterDay(afterDay);
                if (afterDay == 0) {
                    solarTermDTO = solarTerm;
                }
                solarTermAfterDTOS.add(solarTerm);
            }
            solarTermDTO.setNext(solarTermAfterDTOS);


            // 节气的取值范围是0-23
            int jieqiIndex = (int) Math.floor((julianDayForThisDay - qiShuoDO.ZQ[0] - 7) / 15.2184);
            if (jieqiIndex < 23 && julianDayForThisDay >= qiShuoDO.ZQ[jieqiIndex + 1]) {
                jieqiIndex++;
            }
            if (julianDayForThisDay == qiShuoDO.ZQ[jieqiIndex]) {
                solarTermDTO.setJulianDay(julianDayForThisDay + CommonUtils.JULIAN_FOR_2000);
                solarTermDTO.setIndex(jieqiIndex);
                solarTermDTO.setName(AnnalsUtils.JIEQI[jieqiIndex]);
            }

            //------------------------------------计算天干地支------------------------------------//

            EraDTO eraDTO = new EraDTO();
            // 干支纪年处理 以立春为界定年首
            julianDay = (int) (qiShuoDO.ZQ[3] + (julianDayForThisDay < qiShuoDO.ZQ[3] ? -365 : 0) + 365.25 * 16 - 35);
            // 以立春为界定纪年
            // 农历纪年(10进制,1984年起算)
            int yearChronology = (int) Math.floor(julianDay / 365.2422 + 0.5);
            lunarDTO.setYearChronology(yearChronology);
            // 以下几行以正月初一定年首
            // 一般第3个月为春节
            julianDay = qiShuoDO.HS[2];
            for (j = 0; j < 14; j++) {
                // 找春节
                if (!"正".equals(qiShuoDO.ym[j]) || qiShuoDO.leap == j && j != 0) {
                    continue;
                }
                julianDay = qiShuoDO.HS[j];
                if (julianDayForThisDay < julianDay) {
                    julianDay -= 365;
                    // 无需再找下一个正月
                    break;
                }
            }
            // 计算该年春节与1984年平均春节(立春附近)相差天数估计
            julianDay = julianDay + 5810;
            // 农历纪年(10进制,1984年起算)
            int lunarYears = (int) Math.floor(julianDay / 365.2422 + 0.5);

            julianDay = yearChronology + 12000;

            // 干支纪年(立春)
            eraDTO.setYear(AnnalsUtils.TIANGAN[julianDay % 10] + AnnalsUtils.DIZHI[julianDay % 12]);
            julianDay = lunarYears + 12000;
            // String Lyear3 = this.Gan[D % 10] + this.Zhi[D % 12]; // 干支纪年(正月) ,
            // 黄帝纪年,春节才视为新年
            int kingChronology = lunarYears + 1984 + 2698;
            lunarDTO.setKingChronology(kingChronology);
            lunarDTO.setKingChronologyName("开元" + kingChronology + "年");

            // 纪月处理,1998年12月7(大雪)开始连续进行节气计数,0为甲子
            lunarMonthIndex = (int) Math.floor((julianDayForThisDay - qiShuoDO.ZQ[0]) / 30.43685);
            if (lunarMonthIndex < 12 && julianDayForThisDay >= qiShuoDO.ZQ[2 * lunarMonthIndex + 1]) {
                // 相对大雪的月数计算,lunarMonthIndex的取值范围 0-12
                lunarMonthIndex++;
            }

            // 相对于1998年12月7(大雪)的月数,900000为正数基数
            julianDay = lunarMonthIndex + (int) Math.floor((qiShuoDO.ZQ[12] + 390) / 365.2422) * 12 + 900000;
            // 农历纪月
            lunarDTO.setMonthChronology(julianDay % 12);
            // TODO 对比chineseEraYear与julianDay的值是否一致
            // 该年对应的生肖
            lunarDTO.setZodiac(AnnalsUtils.SHENGXIAO[yearChronology % 12]);
            // 干支纪年
            lunarDTO.setYearName(AnnalsUtils.getYearName(year));


            eraDTO.setMonth(AnnalsUtils.TIANGAN[julianDay % 10] + AnnalsUtils.DIZHI[julianDay % 12]);

            // 纪日,2000年1月7日起算
            julianDay = julianDayForThisDay - 6 + 9000000;

            eraDTO.setDay(AnnalsUtils.TIANGAN[julianDay % 10] + AnnalsUtils.DIZHI[julianDay % 12]);

            int h = (hours + 1) / 2;
            //天干地支时,大鱼叔叔所写，算法可能有误
            eraDTO.setTime(AnnalsUtils.TIANGAN[(h + julianDay * 12) % 10] + AnnalsUtils.DIZHI[h % 12]);

            // 星座
            lunarMonthIndex = (int) Math.floor((julianDayForThisDay - qiShuoDO.ZQ[0] - 15) / 30.43685);
            if (lunarMonthIndex < 11 && julianDayForThisDay >= qiShuoDO.ZQ[2 * lunarMonthIndex + 2]) {
                // 星座所在月的序数,(如果j=13,ob.d0不会超过第14号中气)
                lunarMonthIndex++;
            }
            julianDTO.setConstellation(AnnalsUtils.XINGZUO[(lunarMonthIndex + 12) % 12] + "座");


            //------------------------------------计算节假日------------------------------------//
            HolidayDTO holidayDTO = new HolidayDTO();
            // 计算公历节日
            FestivalHolidayUtils.getDayName(gregorianDTO, holidayDTO);
            // 计算农历节日
            AnnalsUtils.getHolidayInfo(lunarDTO, holidayDTO, solarTermDTO, eraDTO);

            AlmanacDTO almanacDTO = new AlmanacDTO();
            almanacDTO.setEraDTO(eraDTO);
            almanacDTO.setGregorianDTO(gregorianDTO);
            almanacDTO.setHolidayDTO(holidayDTO);
            almanacDTO.setIslamicDTO(islamicDTO);
            almanacDTO.setJulianDTO(julianDTO);
            almanacDTO.setLunarDTO(lunarDTO);
            almanacDTO.setSolarTermDTO(solarTermDTO);
            almanacDTO.setSunMoonDTO(sunMoonDTO);
            almanacDTO.setTimeZoneDTO(timeZoneForThisDay);
            almanacDTOS[i] = almanacDTO;
        }

        //------------------------------------计算月相------------------------------------//

        do {
            double d = AnnalsUtils.so_accurate(moonLon);
            julianDay = (int) Math.floor(d + 0.5);
            int xn = (int) Math.floor(moonLon / CommonUtils.PI_2 * 4 + 4000000.01) % 4;
            moonLon += CommonUtils.PI_2 / 4;
            if (julianDay >= julianDaysForMonthFirst + julianDaysForMonth) {
                break;
            }
            if (julianDay < julianDaysForMonthFirst) {
                continue;
            }
            SunMoonDTO sunMoonDTO = almanacDTOS[julianDay - julianDaysForMonthFirst].getSunMoonDTO();
            // 取得月相名称
            sunMoonDTO.setMoonPhaseName(AnnalsUtils.YUEXIANG[xn]);
            //月相时刻(儒略日)
            sunMoonDTO.setMoonPhaseTime(CommonUtils.JULIAN_FOR_2000 + d);
            //月相时间串
            sunMoonDTO.setMoonPhaseTimeName(JulianCalendarUtils.getJulianTime(CommonUtils.JULIAN_FOR_2000 + d));
        } while (julianDay + 5 < julianDaysForMonthFirst + julianDaysForMonth);

        return almanacDTOS;
    }

}
