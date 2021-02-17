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
        int index = timeZoneDTO.getDay() - 1;
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

        List<MoonPhaseDTO> moonPhaseDTOS = new ArrayList<>();

        // FIXME 2021-01-22 不知为何减去西历两千年的儒略日
        // 这个月1号的儒略日,公历月首,中午
        int julianDayOfMonthFirst = (int) (Math.floor(JulianCalendarUtils.getJulianDayNumber(timeZoneDTO.getYear(), timeZoneDTO.getMonth())) - CommonUtils.JULIAN_FOR_2000);

        int nextMonth = timeZoneDTO.getMonth() + 1, nextYear = timeZoneDTO.getYear();
        if (nextMonth > 12) {
            nextMonth = nextMonth - 12;
            nextYear = timeZoneDTO.getYear() + 1;
        }

        // 这个月1号的儒略日和下个月1号的儒略日之差,月天数(公历)
        int julianDayForMonthSum = (int) (Math.floor(JulianCalendarUtils.getJulianDayNumber(nextYear, nextMonth)) - CommonUtils.JULIAN_FOR_2000 - julianDayOfMonthFirst);

        // 本月第一天的星期
        int weekFirstForMonthIndex = (julianDayOfMonthFirst + CommonUtils.JULIAN_FOR_2000 + 1 + 7000000) % 7;

        // 所属公历年对应的农历干支纪年
        int chineseEraYear = timeZoneDTO.getYear() - 1984 + 12000;

        // 提取各日信息
        AlmanacDTO[] almanacDTOS = new AlmanacDTO[julianDayForMonthSum];

        // 计算世界时与原子时之差
        double jd2 = julianDayOfMonthFirst + CommonUtils.dtT(julianDayOfMonthFirst) - (double) 8 / 24;

        // 计算太阳黄经
        double sunLon = AstronomyArithmeticUtils.S_aLon(jd2 / 36525, 3);
        sunLon = (int) Math.floor((sunLon - 0.13) / CommonUtils.PI_2 * 24) * CommonUtils.PI_2 / 24;

        // 月日视黄经的差值
        double moonLon = AstronomyArithmeticUtils.MS_aLon(jd2 / 36525, 10, 3);
        moonLon = (int) Math.floor((moonLon - 0.78) / Math.PI * 2) * Math.PI / 2;

        // 计算月历
        for (int i = 0, j = 0; i < julianDayForMonthSum; i++) {
            // 叠加日期，重构对象
            timeZoneDTO.setDay(i + 1);
            TimeZoneDTO timeZoneOfThisDay = new TimeZoneDTO(timeZoneDTO, julianDayForMonthSum + i);


            //------------------------------------计算儒略日,北京时12:00------------------------------------//
            JulianDTO julianDTO = new JulianDTO();
            int julianDayOfThisDay = julianDayOfMonthFirst + i;
            julianDTO.setDays(julianDayOfThisDay + CommonUtils.JULIAN_FOR_2000);

            //------------------------------------计算日出月落,经纬度,港口------------------------------------//
            SunMoonDTO sunMoonDTO = new SunMoonDTO();
            SunMoonUtils.init(timeZoneOfThisDay, sunMoonDTO);
            sunMoonDTO.setPortName(PortUtils.getProtName(PropertiesUtils.getLATPORT(), PropertiesUtils.getLOOGPORT()));

            //------------------------------------计算回历------------------------------------//
            IslamicDTO islamicDTO = IslamicCalendarUtils.setIslamicCalendar(julianDayOfThisDay);


            //------------------------------------计算西历,伽利略历------------------------------------//
            int year = timeZoneOfThisDay.getYear();
            int month = timeZoneOfThisDay.getMonth();
            int day = timeZoneOfThisDay.getDay();
            int hours = timeZoneOfThisDay.getHour();
            int minute = timeZoneOfThisDay.getMinute();

            //------------------------------------农历排月序计算------------------------------------//
            // 此处有线程安全问题, 暂时直接实例化，qiShuoDO.calcY是为减少计算次数而做的判断。（同一年数据一样）
            if (julianDayOfThisDay < qiShuoDO.ZQ[0] || julianDayOfThisDay >= qiShuoDO.ZQ[24]) {
                qiShuoDO.calcY(julianDayOfThisDay);
            }

            // 农历所在月的序数
            int mk = (int) Math.floor((julianDayOfThisDay - qiShuoDO.HS[0]) / 30);
            if (mk < 13 && qiShuoDO.HS[mk + 1] <= julianDayOfThisDay) {
                mk++;
            }


            //------------------------------------计算农历（农历纪年以【正月初一】定年首）------------------------------------//
            LunarDTO lunarDTO = new LunarDTO();
            // 距农历月首的编移量,0对应初一
            lunarDTO.setMonthOffset(julianDayOfThisDay - qiShuoDO.HS[mk]);
            // 农历日名称
            lunarDTO.setDay(AnnalsUtils.DAY_NAME[julianDayOfThisDay - qiShuoDO.HS[mk]]);
            lunarDTO.setLeapYear(qiShuoDO.leap > 0);
            // 月的信息
            if (julianDayOfThisDay == qiShuoDO.HS[mk] || julianDayOfThisDay == julianDayOfMonthFirst) {
                // 月名称
                lunarDTO.setMonth(qiShuoDO.ym[mk]);
                // 月大小
                lunarDTO.setDaysOfMonth(qiShuoDO.dx[mk]);
                // 闰状况
                lunarDTO.setLeapMonth((qiShuoDO.leap != 0 && qiShuoDO.leap == mk));
                // 下个月名称,判断除夕时要用到
                lunarDTO.setNextMonth(mk < 13 ? qiShuoDO.ym[mk + 1] : "未知");
            } else {
                // 获得前一天的信息
                LunarDTO lastLunarDay = almanacDTOS[i - 1].getLunarDTO();
                lunarDTO.setMonth(lastLunarDay.getMonth());
                lunarDTO.setDaysOfMonth(lastLunarDay.getDaysOfMonth());
                lunarDTO.setLeapMonth(lastLunarDay.getLeapMonth());
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
            // 一般第3个月为春节
            julianDay = qiShuoDO.HS[2];
            for (j = 0; j < 14; j++) {
                // 找春节
                if (!"正".equals(qiShuoDO.ym[j]) || qiShuoDO.leap == j && j != 0) {
                    continue;
                }
                julianDay = qiShuoDO.HS[j];
                if (julianDayOfThisDay < julianDay) {
                    julianDay -= 365;
                    // 无需再找下一个正月
                    break;
                }
            }
            // 计算该年春节与1984年平均春节(立春附近)相差天数估计
            julianDay = julianDay + 5810;
            // 农历纪年(10进制,1984年起算)
            int lunarYears = (int) Math.floor(julianDay / 365.2422 + 0.5);
            julianDay = lunarYears + 12000;
            // String Lyear3 = this.Gan[D % 10] + this.Zhi[D % 12]; // 干支纪年(正月) ,
            // 黄帝纪年,春节才视为新年
            int kingChronology = lunarYears + 1984 + 2698;
            lunarDTO.setKingChronology(kingChronology);
            lunarDTO.setKingChronologyName("开元" + kingChronology + "年");
            // 干支纪年（春节）
            lunarDTO.setYear(AnnalsUtils.TIANGAN[julianDay % 10] + AnnalsUtils.DIZHI[julianDay % 12]);
            // 该年对应的生肖
            lunarDTO.setZodiac(AnnalsUtils.SHENGXIAO[julianDay % 12]);
            // 年号
            lunarDTO.setYearName(AnnalsUtils.getYearName(year));

            //------------------------------------计算节气------------------------------------//

            // 计算当前月份的前一个节气，并从该节气开始结算接下来的24个节气
            int qk = (int) Math.floor((julianDayOfThisDay - qiShuoDO.ZQ[0] - 7) / 15.2184);
            if (qk < 23 && julianDayOfThisDay >= qiShuoDO.ZQ[qk + 1]) {
                // 节气的取值范围是0-23
                qk++;
            }
            double sunLonValue = sunLon, sunLonTime;
            SolarTermDTO solarTermDTO = new SolarTermDTO();
            List<SolarTermDTO> solarTermAfterDTOS = new ArrayList<>();
            int qj = 24, qn;
            for (int qi = qk; qi < qj; ) {
                sunLonTime = AnnalsUtils.qi_accurate(sunLonValue);
                // julianDay = (int) Math.floor(sunLonTime + 0.5);
                qn = (int) Math.floor(sunLonValue / CommonUtils.PI_2 * 24 + 24000006.01) % 24;
                sunLonValue += CommonUtils.PI_2 / 24;
                // BUG 2021-01-23 qiShuoDO.ZQ[qi]的儒略日与sunLonTime的值有出入,与julianDayOfThisDay的值一致
                double afterJulianDay = CommonUtils.JULIAN_FOR_2000 + sunLonTime;
                SolarTermDTO solarTerm = new SolarTermDTO();
                solarTerm.setJulianTime(sunLonTime);
                solarTerm.setDateTime(JulianCalendarUtils.julianDays2str(afterJulianDay));
                solarTerm.setIndex(qn);
                solarTerm.setName(AnnalsUtils.JIEQI[qn]);
                solarTerm.setJulianDay((int) afterJulianDay);
                solarTerm.setDesc(ConstantsUtils.getDesc(AnnalsUtils.JIEQI[qn]));
                int afterDay = (int) afterJulianDay - CommonUtils.JULIAN_FOR_2000 - julianDayOfThisDay;
                solarTerm.setAfterDay(afterDay);
                if (afterDay == 0) {
                    solarTermDTO = solarTerm;
                }
                solarTermAfterDTOS.add(solarTerm);
                qi++;
                if (qi == 24) {
                    qi = 0;
                    qj = qk;
                }
            }
            solarTermDTO.setNext(solarTermAfterDTOS);

            //------------------------------------计算黄历 (天干地支，干支纪年以【立春】定年首)------------------------------------//

            EraDTO eraDTO = new EraDTO();
            // 干支纪年处理 以立春为界定年首
            julianDay = (int) (qiShuoDO.ZQ[3] + (julianDayOfThisDay < qiShuoDO.ZQ[3] ? -365 : 0) + 365.25 * 16 - 35);
            // 以立春为界定纪年
            // 农历纪年(10进制,1984年起算)
            int yearChronology = (int) Math.floor(julianDay / 365.2422 + 0.5);
            lunarDTO.setYearChronology(yearChronology);

            julianDay = yearChronology + 12000;
            // 干支纪年(立春)
            eraDTO.setYear(AnnalsUtils.TIANGAN[julianDay % 10] + AnnalsUtils.DIZHI[julianDay % 12]);

            // 纪月处理,1998年12月7(大雪)开始连续进行节气计数,0为甲子
            mk = (int) Math.floor((julianDayOfThisDay - qiShuoDO.ZQ[0]) / 30.43685);
            if (mk < 12 && julianDayOfThisDay >= qiShuoDO.ZQ[2 * mk + 1]) {
                // 相对大雪的月数计算,lunarMonthIndex的取值范围 0-12
                mk++;
            }

            // 相对于1998年12月7(大雪)的月数,900000为正数基数
            julianDay = mk + (int) Math.floor((qiShuoDO.ZQ[12] + 390) / 365.2422) * 12 + 900000;
            // 农历纪月
            lunarDTO.setMonthChronology(julianDay % 12);


            eraDTO.setMonth(AnnalsUtils.TIANGAN[julianDay % 10] + AnnalsUtils.DIZHI[julianDay % 12]);

            // 纪日,2000年1月7日起算
            julianDay = julianDayOfThisDay - 6 + 9000000;

            eraDTO.setDay(AnnalsUtils.TIANGAN[julianDay % 10] + AnnalsUtils.DIZHI[julianDay % 12]);

            int h = (hours + 1) / 2;
            //天干地支时,大鱼叔叔所写，算法可能有误
            eraDTO.setTime(AnnalsUtils.TIANGAN[(h + julianDay * 12) % 10] + AnnalsUtils.DIZHI[h % 12]);

            // 星座
            mk = (int) Math.floor((julianDayOfThisDay - qiShuoDO.ZQ[0] - 15) / 30.43685);
            if (mk < 11 && julianDayOfThisDay >= qiShuoDO.ZQ[2 * mk + 2]) {
                // 星座所在月的序数,(如果j=13,ob.d0不会超过第14号中气)
                mk++;
            }
            julianDTO.setConstellation(AnnalsUtils.XINGZUO[(mk + 12) % 12] + "座");


            //------------------------------------计算节假日------------------------------------//
            // 计算农历节日
            HolidayDTO holidayDTO = AnnalsUtils.getHolidayInfo(lunarDTO, solarTermDTO, eraDTO);
            // 计算公历节日
            FestivalHolidayUtils.getDayName(timeZoneOfThisDay, holidayDTO);

            AlmanacDTO almanacDTO = new AlmanacDTO();
            almanacDTO.setEraDTO(eraDTO);
            almanacDTO.setHolidayDTO(holidayDTO);
            almanacDTO.setIslamicDTO(islamicDTO);
            almanacDTO.setJulianDTO(julianDTO);
            almanacDTO.setLunarDTO(lunarDTO);
            almanacDTO.setSolarTermDTO(solarTermDTO);
            almanacDTO.setSunMoonDTO(sunMoonDTO);
            almanacDTO.setTimeZoneDTO(timeZoneOfThisDay);
            almanacDTO.setMoonPhaseDTO(new MoonPhaseDTO(moonPhaseDTOS));
            almanacDTOS[i] = almanacDTO;
        }

        //------------------------------------计算月相------------------------------------//
        do {
            double moonLonValue = AnnalsUtils.so_accurate(moonLon);
            julianDay = (int) Math.floor(moonLonValue + 0.5);
            int xn = (int) Math.floor(moonLon / CommonUtils.PI_2 * 4 + 4000000.01) % 4;
            moonLon += CommonUtils.PI_2 / 4;
            if (julianDay >= julianDayOfMonthFirst + julianDayForMonthSum) {
                break;
            }
            if (julianDay < julianDayOfMonthFirst) {
                continue;
            }
            AlmanacDTO almanacDTO = almanacDTOS[julianDay - julianDayOfMonthFirst];
            MoonPhaseDTO moonPhaseDTO = almanacDTO.getMoonPhaseDTO();
            // 取得月相名称
            moonPhaseDTO.setName(AnnalsUtils.YUEXIANG[xn]);
            //月相时刻(儒略日)
            moonPhaseDTO.setJulianTime(CommonUtils.JULIAN_FOR_2000 + moonLonValue);
            //月相时间串
            moonPhaseDTO.setDateTime(JulianCalendarUtils.julianDays2str(CommonUtils.JULIAN_FOR_2000 + moonLonValue));
            moonPhaseDTOS.add(moonPhaseDTO);
            almanacDTO.setMoonPhaseDTO(moonPhaseDTO);
        } while (julianDay + 5 < julianDayOfMonthFirst + julianDayForMonthSum);

        return almanacDTOS;
    }


    /**
     * 年历
     *
     * @param timeZoneDTO
     * @return
     * @throws cn.huangdayu.almanac.exception.AlmanacException
     */
    public static AlmanacDTO[][] yearCalendar(TimeZoneDTO timeZoneDTO) {
        AlmanacDTO[][] almanacDTOS = new AlmanacDTO[12][];
        for (int i = 1; i <= 12; i++) {
            timeZoneDTO.setMonth(i);
            almanacDTOS[i - 1] = monthCalendar(new TimeZoneDTO(timeZoneDTO));
        }
        return almanacDTOS;
    }

}
