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
            TimeZoneDTO timeZoneForToday = timeZoneDTO.nextDay(i + 1);


            //------------------------------------计算儒略日,北京时12:00------------------------------------//
            int julianDayForToday = julianOfMonth.getFirstJulianDayOfMonth() + i;

            //------------------------------------计算日出月落,经纬度,港口------------------------------------//
            SunriseMoonset sunriseMoonset = new SunriseMoonset(timeZoneForToday);

            //------------------------------------计算回历------------------------------------//
            Islamic islamic = IslamicCalendarUtils.setIslamicCalendar(julianDayForToday);


            //------------------------------------计算西历,伽利略历------------------------------------//
            // 公历月内日序数
            timeZoneForToday.setDayIndexOfMonth(i);
            // 公历月天数
            timeZoneForToday.setDaysOfMonth(julianOfMonth.getNumberDayOfMonth());
            // 月首的星期
            timeZoneForToday.setWeekFirstOfMonth(julianOfMonth.getWeekFirstDayOfMonth());
            // 当前日的星期
            timeZoneForToday.setWeekOfCurrentDay((julianOfMonth.getWeekFirstDayOfMonth() + i) % 7);
            // 本日所在的周序号
            timeZoneForToday.setWeekIndexOfMonth(((julianOfMonth.getWeekFirstDayOfMonth() + i) / 7));
            // 本月的总周数
            timeZoneForToday.setWeeksOfMonth(((julianOfMonth.getWeekFirstDayOfMonth() + julianOfMonth.getNumberDayOfMonth() - 1) / 7) + 1);


            //------------------------------------计算农历（农历纪年以【正月初一】定年首）------------------------------------//
            Lunar lunar = new Lunar(timeZoneForToday, julianDayForToday, qiShuo);


            //------------------------------------计算节气------------------------------------//
            SolarTerm solarTermDTO = new SolarTerm(julianDayForToday, qiShuo, astronomical);

            //------------------------------------计算黄历 (天干地支，干支纪年以【立春】定年首)------------------------------------//

            Era era = new Era(julianDayForToday, lunar, timeZoneForToday);


            //------------------------------------计算星座和儒略日------------------------------------//
            Julian julian = new Julian(julianDayForToday, qiShuo);


            //------------------------------------计算节假日------------------------------------//
            Holiday holiday = new Holiday(timeZoneForToday, lunar, solarTermDTO, era);

            AlmanacDTO almanacDTO = new AlmanacDTO();
            almanacDTO.setEraDTO(era);
            almanacDTO.setHolidayDTO(holiday);
            almanacDTO.setIslamicDTO(islamic);
            almanacDTO.setJulianDTO(julian);
            almanacDTO.setLunarDTO(lunar);
            almanacDTO.setSolarTermDTO(solarTermDTO);
            almanacDTO.setSunMoonDTO(sunriseMoonset);
            almanacDTO.setTimeZoneDTO(timeZoneForToday);
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
