package cn.huangdayu.almanac.utils;

import java.util.ArrayList;
import java.util.List;

import cn.huangdayu.almanac.aggregates.astronomical.Astronomical;
import cn.huangdayu.almanac.aggregates.era.Era;
import cn.huangdayu.almanac.aggregates.holiday.Holiday;
import cn.huangdayu.almanac.aggregates.islamic.Islamic;
import cn.huangdayu.almanac.aggregates.julian.Julian;
import cn.huangdayu.almanac.aggregates.lunar.Lunar;
import cn.huangdayu.almanac.aggregates.moon_phase.MoonPhase;
import cn.huangdayu.almanac.aggregates.solar_term.SolarTerm;
import cn.huangdayu.almanac.aggregates.sunrise_moonset.SunriseMoonset;
import cn.huangdayu.almanac.dto.*;
import cn.huangdayu.almanac.aggregates.qishuo.QiShuo;

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
    public static AlmanacDTO ofDay(TimeZoneDTO timeZoneDTO) {
        /** 数组从0开始，所以要减1*/
        int index = timeZoneDTO.getDay() - 1;
        return handler(timeZoneDTO, index)[index];
    }

    public static AlmanacDTO[] ofMonth(TimeZoneDTO timeZoneDTO) {
        return handler(timeZoneDTO, -1);
    }

    /**
     * 年历
     *
     * @param timeZoneDTO
     * @return
     * @throws cn.huangdayu.almanac.exception.AlmanacException
     */
    public static AlmanacDTO[][] ofYear(TimeZoneDTO timeZoneDTO) {
        AlmanacDTO[][] almanacDTOS = new AlmanacDTO[12][];
        for (int i = 1; i <= 12; i++) {
            timeZoneDTO.setMonth(i);
            almanacDTOS[i - 1] = ofMonth(new TimeZoneDTO(timeZoneDTO));
        }
        return almanacDTOS;
    }

    /**
     * 月历
     *
     * @param timeZoneDTO
     * @return
     * @throws cn.huangdayu.almanac.exception.AlmanacException
     */
    private static AlmanacDTO[] handler(TimeZoneDTO timeZoneDTO, int dayIndex) {

        int julianDay;

        // 某年气朔的数据信息
        QiShuo qiShuo = new QiShuo();

        List<MoonPhase> moonPhases = new ArrayList<>();

        Julian julianOfMonth = new Julian(timeZoneDTO.getEraYear(), timeZoneDTO.getMonth());

        // 所属公历年对应的农历干支纪年
        int chineseEraYear = timeZoneDTO.getYear() - 1984 + 12000;

        // 提取各日信息
        AlmanacDTO[] almanacDTOS = new AlmanacDTO[julianOfMonth.getNumberDayOfMonth()];

        Astronomical astronomical = new Astronomical(julianOfMonth.getFirstJulianDayOfMonth());

        // dayIndex >= 0 ? 计算日历 : 计算月历
        boolean day = dayIndex >= 0;

        for (int i = day ? dayIndex : 0, j = 0; day ? i <= dayIndex : i < julianOfMonth.getNumberDayOfMonth(); i++) {

            //------------------------------------叠加日期，重构对象------------------------------------//
            timeZoneDTO.setDay(i + 1);
            TimeZoneDTO timeZoneOfThisDay = new TimeZoneDTO(timeZoneDTO, julianOfMonth.getNumberDayOfMonth() + i);


            //------------------------------------计算儒略日,北京时12:00------------------------------------//
            Julian julian = new Julian();
            int julianDayOfThisDay = julianOfMonth.getFirstJulianDayOfMonth() + i;
            julian.setDays(julianDayOfThisDay + CommonUtils.JULIAN_FOR_2000);

            //------------------------------------计算日出月落,经纬度,港口------------------------------------//
            SunriseMoonset sunriseMoonset = new SunriseMoonset();
            SunMoonUtils.init(timeZoneOfThisDay, sunriseMoonset);
            sunriseMoonset.setPortName(PortUtils.getProtName(PropertiesUtils.getLATPORT(), PropertiesUtils.getLOOGPORT()));

            //------------------------------------计算回历------------------------------------//
            Islamic islamic = IslamicCalendarUtils.setIslamicCalendar(julianDayOfThisDay);


            //------------------------------------计算西历,伽利略历------------------------------------//
            int hours = timeZoneOfThisDay.getHour();
            int minute = timeZoneOfThisDay.getMinute();
            // 公历月内日序数
            timeZoneOfThisDay.setDayIndexOfMonth(i);
            // 公历月天数
            timeZoneOfThisDay.setDaysOfMonth(julianOfMonth.getNumberDayOfMonth());
            // 月首的星期
            timeZoneOfThisDay.setWeekFirstOfMonth(julianOfMonth.getWeekFirstDayOfMonth());
            // 当前日的星期
            timeZoneOfThisDay.setWeekOfCurrentDay((julianOfMonth.getWeekFirstDayOfMonth() + i) % 7);
            // 本日所在的周序号
            timeZoneOfThisDay.setWeekIndexOfMonth((int) Math.floor((julianOfMonth.getWeekFirstDayOfMonth() + i) / 7));
            // 本月的总周数
            timeZoneOfThisDay.setWeeksOfMonth((int) Math.floor((julianOfMonth.getWeekFirstDayOfMonth() + julianOfMonth.getNumberDayOfMonth() - 1) / 7) + 1);


            //------------------------------------农历排月序计算------------------------------------//
            // 此处有线程安全问题, 暂时直接实例化，qiShuoDO.calcY是为减少计算次数而做的判断。（同一年数据一样）
            if (julianDayOfThisDay < qiShuo.zhongQi[0] || julianDayOfThisDay >= qiShuo.zhongQi[24]) {
                qiShuo.calculateMonth(julianDayOfThisDay);
            }

            // 农历所在月的序数
            int mk = (int) Math.floor((julianDayOfThisDay - qiShuo.heShuo[0]) / 30);
            if (mk < 13 && qiShuo.heShuo[mk + 1] <= julianDayOfThisDay) {
                mk++;
            }


            //------------------------------------计算农历（农历纪年以【正月初一】定年首）------------------------------------//
            Lunar lunar = new Lunar();
            // 距农历月首的编移量,0对应初一
            lunar.setMonthOffset(julianDayOfThisDay - qiShuo.heShuo[mk]);
            // 农历日名称
            lunar.setDay(AnnalsUtils.DAY_NAME[julianDayOfThisDay - qiShuo.heShuo[mk]]);
            lunar.setLeapYear(qiShuo.leapMonthIndex > 0);
            // 月名称
            lunar.setMonth(qiShuo.monthNames[mk]);
            // 月大小
            lunar.setDaysOfMonth(qiShuo.monthValue[mk]);
            // 闰状况
            lunar.setLeapMonth((qiShuo.leapMonthIndex != 0 && qiShuo.leapMonthIndex == mk));
            // 下个月名称,判断除夕时要用到
            lunar.setNextMonth(mk < 13 ? qiShuo.monthNames[mk + 1] : "未知");
            // 时辰
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
            lunar.setTime(lunarTime);
            // 一般第3个月为春节
            julianDay = qiShuo.heShuo[2];
            for (j = 0; j < 14; j++) {
                // 找春节
                if (!"正".equals(qiShuo.monthNames[j]) || qiShuo.leapMonthIndex == j && j != 0) {
                    continue;
                }
                julianDay = qiShuo.heShuo[j];
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
            lunar.setKingChronology(kingChronology);
            lunar.setKingChronologyName("开元" + kingChronology + "年");
            // 干支纪年（春节）
            lunar.setYear(AnnalsUtils.TIANGAN[julianDay % 10] + AnnalsUtils.DIZHI[julianDay % 12]);
            // 该年对应的生肖
            lunar.setZodiac(AnnalsUtils.SHENGXIAO[julianDay % 12]);
            // 年号
            lunar.setYearName(AnnalsUtils.getYearName(timeZoneOfThisDay.getEraYear()));

            //------------------------------------计算节气------------------------------------//

            // 计算当前月份的前一个节气，并从该节气开始结算接下来的24个节气
            int qk = (int) Math.floor((julianDayOfThisDay - qiShuo.zhongQi[0] - 7) / 15.2184);
            if (qk < 23 && julianDayOfThisDay >= qiShuo.zhongQi[qk + 1]) {
                // 节气的取值范围是0-23
                qk++;
            }
            double sunLonValue = astronomical.getSunSolarRetina(), sunLonTime;
            SolarTerm solarTermDTO = new SolarTerm();
            List<SolarTerm> solarTermAfterDTOS = new ArrayList<>();
            int qj = 24, qn;
            for (int qi = qk; qi < qj; ) {
                // FIXME 2021-01-17 计算太阳视黄经较耗时
                sunLonTime = AnnalsUtils.qi_accurate(sunLonValue);
                julianDay = (int) Math.floor(sunLonTime + 0.5);
                qn = (int) Math.floor(sunLonValue / CommonUtils.PI_2 * 24 + 24000006.01) % 24;
                sunLonValue += CommonUtils.PI_2 / 24;
                // BUG 2021-01-23 qiShuoDO.ZQ[qi]的儒略日与sunLonTime的值有出入,与julianDayOfThisDay的值一致
                julianDay = CommonUtils.JULIAN_FOR_2000 + julianDay;
                SolarTerm solarTerm = new SolarTerm();
                solarTerm.setJulianTime(sunLonTime);
                solarTerm.setDateTime(JulianCalendarUtils.julianDays2str(CommonUtils.JULIAN_FOR_2000 + sunLonTime));
                solarTerm.setIndex(qn);
                solarTerm.setName(AnnalsUtils.JIEQI[qn]);
                solarTerm.setJulianDay(julianDay);
                solarTerm.setDesc(ConstantsUtils.getDesc(AnnalsUtils.JIEQI[qn]));
                int afterDay = julianDay - CommonUtils.JULIAN_FOR_2000 - julianDayOfThisDay;
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

            Era era = new Era();
            // 干支纪年处理 以立春为界定年首
            julianDay = (int) (qiShuo.zhongQi[3] + (julianDayOfThisDay < qiShuo.zhongQi[3] ? -365 : 0) + 365.25 * 16 - 35);
            // 以立春为界定纪年
            // 农历纪年(10进制,1984年起算)
            int yearChronology = (int) Math.floor(julianDay / 365.2422 + 0.5);
            lunar.setYearChronology(yearChronology);

            julianDay = yearChronology + 12000;
            // 干支纪年(立春)
            era.setYear(AnnalsUtils.TIANGAN[julianDay % 10] + AnnalsUtils.DIZHI[julianDay % 12]);

            // 纪月处理,1998年12月7(大雪)开始连续进行节气计数,0为甲子
            mk = (int) Math.floor((julianDayOfThisDay - qiShuo.zhongQi[0]) / 30.43685);
            if (mk < 12 && julianDayOfThisDay >= qiShuo.zhongQi[2 * mk + 1]) {
                // 相对大雪的月数计算,lunarMonthIndex的取值范围 0-12
                mk++;
            }

            // 相对于1998年12月7(大雪)的月数,900000为正数基数
            julianDay = mk + (int) Math.floor((qiShuo.zhongQi[12] + 390) / 365.2422) * 12 + 900000;
            // 农历纪月
            lunar.setMonthChronology(julianDay % 12);


            era.setMonth(AnnalsUtils.TIANGAN[julianDay % 10] + AnnalsUtils.DIZHI[julianDay % 12]);

            // 纪日,2000年1月7日起算
            julianDay = julianDayOfThisDay - 6 + 9000000;

            era.setDay(AnnalsUtils.TIANGAN[julianDay % 10] + AnnalsUtils.DIZHI[julianDay % 12]);

            int h = (hours + 1) / 2;
            //天干地支时,大鱼叔叔所写，算法可能有误
            era.setTime(AnnalsUtils.TIANGAN[(h + julianDay * 12) % 10] + AnnalsUtils.DIZHI[h % 12]);

            // 星座
            mk = (int) Math.floor((julianDayOfThisDay - qiShuo.zhongQi[0] - 15) / 30.43685);
            if (mk < 11 && julianDayOfThisDay >= qiShuo.zhongQi[2 * mk + 2]) {
                // 星座所在月的序数,(如果j=13,ob.d0不会超过第14号中气)
                mk++;
            }
            julian.setConstellation(AnnalsUtils.XINGZUO[(mk + 12) % 12] + "座");


            //------------------------------------计算节假日------------------------------------//
            // 计算农历节日
            Holiday holiday = AnnalsUtils.getHolidayInfo(lunar, solarTermDTO, era);
            // 计算公历节日
            FestivalHolidayUtils.getDayName(timeZoneOfThisDay, holiday);

            AlmanacDTO almanacDTO = new AlmanacDTO();
            almanacDTO.setEraDTO(era);
            almanacDTO.setHolidayDTO(holiday);
            almanacDTO.setIslamicDTO(islamic);
            almanacDTO.setJulianDTO(julian);
            almanacDTO.setLunarDTO(lunar);
            almanacDTO.setSolarTermDTO(solarTermDTO);
            almanacDTO.setSunMoonDTO(sunriseMoonset);
            almanacDTO.setTimeZoneDTO(timeZoneOfThisDay);
            almanacDTO.setMoonPhaseDTO(new MoonPhase(moonPhases));
            almanacDTOS[i] = almanacDTO;
        }

        //------------------------------------计算月相------------------------------------//
        double moonLon = astronomical.getMoonSolarRetina();
        do {
            // FIXME 2021-01-17 计算月日视黄经较耗时
            double moonLonValue = AnnalsUtils.so_accurate(moonLon);
            julianDay = (int) Math.floor(moonLonValue + 0.5);
            int xn = (int) Math.floor(moonLon / CommonUtils.PI_2 * 4 + 4000000.01) % 4;
            moonLon += CommonUtils.PI_2 / 4;
            if (julianDay >= julianOfMonth.getFirstJulianDayOfMonth() + julianOfMonth.getNumberDayOfMonth()) {
                break;
            }
            if (julianDay < julianOfMonth.getFirstJulianDayOfMonth()) {
                continue;
            }
            AlmanacDTO almanacDTO = almanacDTOS[julianDay - julianOfMonth.getFirstJulianDayOfMonth()];
            if (almanacDTO == null) {
                continue;
            }
            MoonPhase moonPhase = almanacDTO.getMoonPhaseDTO();
            // 取得月相名称
            moonPhase.setName(AnnalsUtils.YUEXIANG[xn]);
            //月相时刻(儒略日)
            moonPhase.setJulianTime(CommonUtils.JULIAN_FOR_2000 + moonLonValue);
            //月相时间串
            moonPhase.setDateTime(JulianCalendarUtils.julianDays2str(CommonUtils.JULIAN_FOR_2000 + moonLonValue));
            moonPhases.add(moonPhase);
            almanacDTO.setMoonPhaseDTO(moonPhase);
        } while (julianDay + 5 < julianOfMonth.getFirstJulianDayOfMonth() + julianOfMonth.getNumberDayOfMonth());

        return almanacDTOS;
    }

}
