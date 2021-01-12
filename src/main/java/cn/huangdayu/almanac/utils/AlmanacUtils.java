package cn.huangdayu.almanac.utils;

import java.util.Calendar;

import cn.huangdayu.almanac.entity.QiShuoDO;
import cn.huangdayu.almanac.dto.AlmanacDTO;
import cn.huangdayu.almanac.dto.TimeZoneDTO;

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
     */
    public static AlmanacDTO[] monthCalendar(TimeZoneDTO timeZoneDTO) {
        /** 初始化经纬度信息 */
        PortUtils.init(PropertiesUtils.getLatportProperties(), PropertiesUtils.getLoogportProperties());

        int chineseEraYear, julianDaysForMonthFirst, julianDaysForMonth, julianDay;

        /** 某年气朔的数据信息 */
        QiShuoDO qiShuoDO = new QiShuoDO();

        /**
         * 这个月1号的儒略日
         */
        julianDaysForMonthFirst = (int) (Math.floor(JulianCalendarUtils.getJulianDayNumber(timeZoneDTO.getYear(), timeZoneDTO.getMonth())) - CommonUtils.JULIAN_FOR_2000); // 公历月首,中午
        /**
         * 这个月1号的儒略日和下个月1号的儒略日只差
         */
        julianDaysForMonth = (int) (Math.floor(JulianCalendarUtils.getJulianDayNumber(timeZoneDTO.getYear(), timeZoneDTO.getMonth() + 1)) - CommonUtils.JULIAN_FOR_2000 - julianDaysForMonthFirst); // 本月天数(公历)

        /** 本月第一天的星期 */
        int weekFirstForMonthIndex = (julianDaysForMonthFirst + CommonUtils.JULIAN_FOR_2000 + 1 + 7000000) % 7;

        // 所属公历年对应的农历干支纪年
        chineseEraYear = timeZoneDTO.getYear() - 1984 + 12000;

        // 提取各日信息
        AlmanacDTO[] almanacDTOS = new AlmanacDTO[julianDaysForMonth];

        // 计算月历
        for (int i = 0, j = 0; i < julianDaysForMonth; i++) {
            AlmanacDTO almanacDTO1 = new AlmanacDTO();
            timeZoneDTO.setDay(i + 1);
            Calendar calendar = timeZoneDTO.toCalendar();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int hours = calendar.get(Calendar.HOUR_OF_DAY);
            /** 计算日出月落时间 */
            SunMoonUtils.init(timeZoneDTO, almanacDTO1);
            almanacDTO1.setJulianDays(julianDaysForMonthFirst + i); // 儒略日,北京时12:00
            almanacDTO1.setGregorianDayIndexForMonth(i); // 公历月内日序数
            almanacDTO1.setGregorianYear(year); // 公历年
            almanacDTO1.setGregorianMonth(month); // 公历月
            almanacDTO1.setGregorianDaysOfMonth(julianDaysForMonth); // 公历月天数
            almanacDTO1.setGregorianWeekFirstForMonth(weekFirstForMonthIndex); // 月首的星期
            almanacDTO1.setGregorianWeek((weekFirstForMonthIndex + i) % 7); // 当前日的星期
            almanacDTO1.setGregorianWeekIndexForMonth((int) Math.floor((weekFirstForMonthIndex + i) / 7)); // 本日所在的周序号
            almanacDTO1.setGregorianWeeksOfMonth(
                    (int) Math.floor((weekFirstForMonthIndex + julianDaysForMonth - 1) / 7) + 1); // 本月的总周数
            double julianDays = almanacDTO1.getJulianDays() + CommonUtils.JULIAN_FOR_2000;
            TimeZoneDTO timeZoneDTO1 = JulianCalendarUtils.julianDaysToGregorianCalendar(julianDays);
            almanacDTO1.setGregorianDay(timeZoneDTO1.getDay()); // 公历日名称

            // 农历月历
            // 此处有线程安全问题, 暂时直接实例化，qiShuoDO.calcY是为减少计算次数而做的判断。（同一年数据一样）
            if (almanacDTO1.getJulianDays() < qiShuoDO.ZQ[0] || almanacDTO1.getJulianDays() >= qiShuoDO.ZQ[24]) {
                qiShuoDO.calcY(almanacDTO1.getJulianDays());
            }

            int mk = (int) Math.floor((almanacDTO1.getJulianDays() - qiShuoDO.HS[0]) / 30);
            if (mk < 13 && qiShuoDO.HS[mk + 1] <= almanacDTO1.getJulianDays()) {
                mk++; // 农历所在月的序数
            }

            almanacDTO1.setLunarMonthOffset(almanacDTO1.getJulianDays() - qiShuoDO.HS[mk]); // 距农历月首的编移量,0对应初一
            almanacDTO1.setLunarDayName(AnnalsUtils.DAY_NAME[almanacDTO1.getLunarMonthOffset()]); // 农历日名称
            almanacDTO1.setLunarLastDZ(almanacDTO1.getJulianDays() - qiShuoDO.ZQ[0]); // 距冬至的天数
            almanacDTO1.setLunarLastXZ(almanacDTO1.getJulianDays() - qiShuoDO.ZQ[12]); // 距夏至的天数
            almanacDTO1.setLunarLastLQ(almanacDTO1.getJulianDays() - qiShuoDO.ZQ[15]); // 距立秋的天数
            almanacDTO1.setLunarLastMZ(almanacDTO1.getJulianDays() - qiShuoDO.ZQ[11]); // 距芒种的天数
            almanacDTO1.setLunarLastXS(almanacDTO1.getJulianDays() - qiShuoDO.ZQ[13]); // 距小暑的天数
            if (almanacDTO1.getJulianDays() == qiShuoDO.HS[mk]
                    || almanacDTO1.getJulianDays() == julianDaysForMonthFirst) { // 月的信息
                almanacDTO1.setLunarMonthName(qiShuoDO.ym[mk]); // 月名称
                almanacDTO1.setLunarDaysOfMonth(qiShuoDO.dx[mk]); // 月大小
                almanacDTO1.setLunarLeapStr((qiShuoDO.leap != 0 && qiShuoDO.leap == mk) ? "闰" : ""); // 闰状况
                almanacDTO1.setLunarNextLunarMonthName(mk < 13 ? qiShuoDO.ym[mk + 1] : "未知"); // 下个月名称,判断除夕时要用到
            } else {
                // bug 存在一个数组下标溢出异常 Index -1 out of bounds for length 31
                AlmanacDTO lunarDate2 = almanacDTOS[i - 1];
                almanacDTO1.setLunarMonthName(lunarDate2.getLunarMonthName());
                almanacDTO1.setLunarDaysOfMonth(lunarDate2.getLunarDaysOfMonth());
                almanacDTO1.setLunarLeapStr(lunarDate2.getLunarLeapStr());
                almanacDTO1.setLunarNextLunarMonthName(lunarDate2.getLunarNextLunarMonthName());
            }
            int qk = (int) Math.floor((almanacDTO1.getJulianDays() - qiShuoDO.ZQ[0] - 7) / 15.2184);
            if (qk < 23 && almanacDTO1.getJulianDays() >= qiShuoDO.ZQ[qk + 1]) {
                qk++; // 节气的取值范围是0-23
            }
            if (almanacDTO1.getJulianDays() == qiShuoDO.ZQ[qk]) {
                almanacDTO1.setLunarSolarTerm(AnnalsUtils.JIEQI[qk]);
            } else {
                almanacDTO1.setLunarSolarTerm("");
            }

            // 干支纪年处理 以立春为界定年首
            julianDay = (int) (qiShuoDO.ZQ[3] + (almanacDTO1.getJulianDays() < qiShuoDO.ZQ[3] ? -365 : 0) + 365.25 * 16
                    - 35); // 以立春为界定纪年
            almanacDTO1.setLunarPeriodOfYears((int) Math.floor(julianDay / 365.2422 + 0.5)); // 农历纪年(10进制,1984年起算)
            // 以下几行以正月初一定年首
            julianDay = qiShuoDO.HS[2]; // 一般第3个月为春节
            for (j = 0; j < 14; j++) { // 找春节
                if (!qiShuoDO.ym[j].equals("正") || qiShuoDO.leap == j && j != 0) {
                    continue;
                }
                julianDay = qiShuoDO.HS[j];
                if (almanacDTO1.getJulianDays() < julianDay) {
                    julianDay -= 365;
                    break;
                } // 无需再找下一个正月
            }
            julianDay = julianDay + 5810; // 计算该年春节与1984年平均春节(立春附近)相差天数估计
            int Lyear0 = (int) Math.floor(julianDay / 365.2422 + 0.5); // 农历纪年(10进制,1984年起算)

            julianDay = almanacDTO1.getLunarPeriodOfYears() + 12000;

            almanacDTO1.setChineseEraYear(AnnalsUtils.TIANGAN[julianDay % 10] + AnnalsUtils.DIZHI[julianDay % 12]); // 干支纪年(立春)
            julianDay = Lyear0 + 12000;
            // String Lyear3 = this.Gan[D % 10] + this.Zhi[D % 12]; // 干支纪年(正月) ,
            // 与Lyear2区别在于春节才视为新年
            almanacDTO1.setKingChronology(Lyear0 + 1984 + 2698); // 黄帝纪年

            // 纪月处理,1998年12月7(大雪)开始连续进行节气计数,0为甲子
            mk = (int) Math.floor((almanacDTO1.getJulianDays() - qiShuoDO.ZQ[0]) / 30.43685);
            if (mk < 12 && almanacDTO1.getJulianDays() >= qiShuoDO.ZQ[2 * mk + 1]) {
                mk++; // 相对大雪的月数计算,mk的取值范围0-12
            }

            julianDay = mk + (int) Math.floor((qiShuoDO.ZQ[12] + 390) / 365.2422) * 12 + 900000; // 相对于1998年12月7(大雪)的月数,900000为正数基数
            almanacDTO1.setLunarPeriodOfMonths(julianDay % 12);

            almanacDTO1.setChineseEraMonth(AnnalsUtils.TIANGAN[julianDay % 10] + AnnalsUtils.DIZHI[julianDay % 12]);

            // 纪日,2000年1月7日起算
            julianDay = almanacDTO1.getJulianDays() - 6 + 9000000;

            almanacDTO1.setChineseEraDay(AnnalsUtils.TIANGAN[julianDay % 10] + AnnalsUtils.DIZHI[julianDay % 12]);

            int shi = (hours + 1) / 2;
            //天干地支时,大鱼叔叔所写，算法可能有误
            almanacDTO1.setChineseEraTime(AnnalsUtils.TIANGAN[(shi + julianDay * 12) % 10] + AnnalsUtils.DIZHI[shi % 12]);

            // 星座
            mk = (int) Math.floor((almanacDTO1.getJulianDays() - qiShuoDO.ZQ[0] - 15) / 30.43685);
            if (mk < 11 && almanacDTO1.getJulianDays() >= qiShuoDO.ZQ[2 * mk + 2]) {
                mk++; // 星座所在月的序数,(如果j=13,ob.d0不会超过第14号中气)
            }
            almanacDTO1.setConstellation(AnnalsUtils.XINGZUO[(mk + 12) % 12] + "座");
            // 回历
            IslamicCalendarUtils.setIslamicCalendar(almanacDTO1.getJulianDays(), almanacDTO1);
            // 节日
            // 计算公历节日
            FestivalHolidayUtils.getDayName(almanacDTO1, almanacDTO1); // 公历
            // 计算农历节日
            AnnalsUtils.getDayName(almanacDTO1, almanacDTO1); // 农历

            almanacDTO1.setLunarDateChinaEraYear(AnnalsUtils.TIANGAN[chineseEraYear % 10] + AnnalsUtils.DIZHI[chineseEraYear % 12]); // 干支纪年
            almanacDTO1.setZodiac(AnnalsUtils.SHENGXIAO[chineseEraYear % 12]); // 该年对应的生肖
            almanacDTO1.setLunarDateChinaEraYearNumber(AnnalsUtils.getNH(year));// 干支纪年

            almanacDTO1.setLeapYear(qiShuoDO.leap > 0);

            /*********************************额外添加的数据*************************************/

            almanacDTO1.setProtName(PortUtils.getProtName());
            almanacDTO1.setJulianDay(JulianCalendarUtils.getJuLian(calendar));

            String Hstr = null, Mstr = null;
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            double sum = hour + 0.01 * minute;
            if (sum < 1 || sum >= 23) {
                Hstr = AnnalsUtils.DIZHI[0] + "时" + "[三更]";
            }
            if (sum >= 1 && sum < 3) {
                Hstr = AnnalsUtils.DIZHI[1] + "时" + "[四更]";
            }
            if (sum >= 3 && sum < 5) {
                Hstr = AnnalsUtils.DIZHI[2] + "时" + "[五更]";
            }
            if (sum >= 5 && sum < 7) {
                Hstr = AnnalsUtils.DIZHI[3] + "时";
            }
            if (sum >= 7 && sum < 9) {
                Hstr = AnnalsUtils.DIZHI[4] + "时";
            }
            if (sum >= 9 && sum < 11) {
                Hstr = AnnalsUtils.DIZHI[5] + "时";
            }
            if (sum >= 11 && sum < 13) {
                Hstr = AnnalsUtils.DIZHI[6] + "时";
            }
            if (sum >= 13 && sum < 15) {
                Hstr = AnnalsUtils.DIZHI[7] + "时";
            }
            if (sum >= 15 && sum < 17) {
                Hstr = AnnalsUtils.DIZHI[8] + "时";
            }
            if (sum >= 17 && sum < 19) {
                Hstr = AnnalsUtils.DIZHI[9] + "时";
            }
            if (sum >= 19 && sum < 21) {
                Hstr = AnnalsUtils.DIZHI[10] + "时" + "[一更]";
            }
            if (sum >= 21 && sum < 23) {
                Hstr = AnnalsUtils.DIZHI[11] + "时" + "[二更]";
            }
            if (minute >= 14 && minute <= 15) {
                Mstr = "一刻";
            } else if (minute >= 28 && minute <= 30) {
                Mstr = "二刻";
            } else if (minute >= 42 && minute <= 45) {
                Mstr = "三刻";
            } else if (minute >= 48 && minute <= 60) {
                Mstr = "四刻";
            } else {
                Mstr = "";
            }
            almanacDTO1.setLunarTime(Hstr + Mstr);

            String timeZoneStr;
            String strDateFormat = DateTimeUtils.formatDateByFormat(calendar, "Z");// +0800
            String str2 = strDateFormat.substring(1, 3);
            int timeZoneInt = 0;
            int j1 = Integer.valueOf(str2.substring(0));
            if (j1 > 0) {
                timeZoneInt = Integer.valueOf(str2);
            } else {
                timeZoneInt = Integer.valueOf(str2.substring(1));
            }
            String[] timeZoneChese = {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"};

            if (strDateFormat.contains("-")) {
                timeZoneStr = strDateFormat + " 西" + timeZoneChese[timeZoneInt - 1] + "区";
            } else {
                timeZoneStr = strDateFormat + " 东" + timeZoneChese[timeZoneInt - 1] + "区";
            }
            almanacDTO1.setTimeZone(timeZoneStr);


            // return areas[0] + " " + areas[1].substring(4, areas[1].length());
            String prov = timeZoneDTO.getProvince().replaceAll("省", "");// 字符替代
            String area = timeZoneDTO.getArea().replaceAll("市", "").replaceAll("区", "").replaceAll("县", "").replaceAll("镇", "")
                    .replaceAll("乡", "");
            almanacDTO1.setPosition(prov + " " + area);

            almanacDTO1.setWeek(DateTimeUtils.getWeek(calendar));

            /***
             * G 年代标志符   y 年   M 月   d 日   h 时 在上午或下午 (1~12)   H 时 在一天中 (0~23)   m 分   s 秒  
             * S 毫秒   E 星期   D 一年中的第几天   F 一月中第几个星期几   w 一年中第几个星期   W 一月中第几个星期   a 上午 / 下午
             * 标记符   k 时 在一天中 (1~24)   K 时 在上午或下午 (0~11)   z 时区
             */
            String timeStr = "G yyyy年MM月dd日 HH时mm分ss秒SSS毫秒 Eak时 Z时区 yyyy年第w周第D天 M月第W周第d天";

            almanacDTO1.setWesternCalendarCN(DateTimeUtils.dateFormat(calendar, timeStr));
            almanacDTO1.setWesternCalendar(DateTimeUtils.dateFormat(calendar, "yyyy-MM-dd HH:mm:ss.SS"));
            almanacDTO1.setTime(DateTimeUtils.dateFormat(calendar, "HH时mm分ss秒SS毫秒"));
            almanacDTO1.setTimeFormer(DateTimeUtils.dateFormat(calendar, "HH:mm:ss.SS"));
            almanacDTO1.setDate(DateTimeUtils.dateFormat(calendar, "yyyy年MM月dd日"));
            almanacDTO1.setDateFormer(DateTimeUtils.dateFormat(calendar, "yyyy-MM-dd"));


            almanacDTOS[i] = almanacDTO1;
        }

        /** 月日视黄经的差值 */
        double w;
        // 以下是月相与节气的处理
        double d;
        int xn;
        double jd2 = julianDaysForMonthFirst + CommonUtils.dtT(julianDaysForMonthFirst) - (double) 8 / 24;
        // 月相查找
        w = AstronomyArithmeticUtils.MS_aLon(jd2 / 36525, 10, 3);
        w = (int) Math.floor((w - 0.78) / Math.PI * 2) * Math.PI / 2;
        do {
            d = AnnalsUtils.so_accurate(w);
            julianDay = (int) Math.floor(d + 0.5);
            xn = (int) Math.floor(w / CommonUtils.PI_2 * 4 + 4000000.01) % 4;
            w += CommonUtils.PI_2 / 4;
            if (julianDay >= julianDaysForMonthFirst + julianDaysForMonth) {
                break;
            }
            if (julianDay < julianDaysForMonthFirst) {
                continue;
            }
            AlmanacDTO almanacDTO2 = almanacDTOS[julianDay - julianDaysForMonthFirst];
            almanacDTO2.setMoonPhaseName(AnnalsUtils.YUEXIANG[xn]); // 取得月相名称
            almanacDTO2.setMoonPhaseTime(d);//月相时刻(儒略日)
            almanacDTO2.setMoonPhaseTimeStr(JulianCalendarUtils.moonPhaseTimeStr(d));//月相时间串
        } while (julianDay + 5 < julianDaysForMonthFirst + julianDaysForMonth);

        // 节气查找
        w = AstronomyArithmeticUtils.S_aLon(jd2 / 36525, 3);
        w = (int) Math.floor((w - 0.13) / CommonUtils.PI_2 * 24) * CommonUtils.PI_2 / 24;
        do {
            d = AnnalsUtils.qi_accurate(w);
            julianDay = (int) Math.floor(d + 0.5);
            xn = (int) Math.floor(w / CommonUtils.PI_2 * 24 + 24000006.01) % 24;
            w += CommonUtils.PI_2 / 24;
            if (julianDay >= julianDaysForMonthFirst + julianDaysForMonth) {
                break;
            }
            if (julianDay < julianDaysForMonthFirst) {
                continue;
            }
            AlmanacDTO almanacDTO3 = almanacDTOS[julianDay - julianDaysForMonthFirst];
            almanacDTO3.setLunarSolarTermName(AnnalsUtils.JIEQI[xn]); // 取得节气名称
            almanacDTO3.setLunarSolarTermTime(d);
            almanacDTO3.setLunarSolarTermTimeStr(JulianCalendarUtils.moonPhaseTimeStr(d));
        } while (julianDay + 12 < julianDaysForMonthFirst + julianDaysForMonth);

        return almanacDTOS;
    }

}
