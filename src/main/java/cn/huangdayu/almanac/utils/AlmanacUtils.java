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
    private AlmanacUtils() {
    }

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

        Julian julianOfMonth = new Julian(timeZoneDTO.getEraYear(), timeZoneDTO.getMonth());

        // 提取各日信息
        AlmanacDTO[] almanacDTOS = new AlmanacDTO[julianOfMonth.getNumberDayOfMonth()];

        Astronomical astronomical = new Astronomical(julianOfMonth.getFirstJulianDayOfMonth());

        // dayIndex >= 0 ? 计算日历 : 计算月历
        boolean forDay = dayIndex >= 0;

        for (int i = forDay ? dayIndex : 0; forDay ? i <= dayIndex : i < julianOfMonth.getNumberDayOfMonth(); i++) {
            // 叠加日期
            TimeZoneDTO timeZoneForToday = timeZoneDTO.nextDay(i, julianOfMonth);
            // 计算儒略日,北京时12:00
            int julianDayForToday = julianOfMonth.getFirstJulianDayOfMonth() + i;
            // 计算日出月落,经纬度,港口
            SunriseMoonset sunriseMoonset = new SunriseMoonset(timeZoneForToday);
            // 计算回历
            Islamic islamic = new Islamic(julianDayForToday);
            // 计算农历（农历纪年以【正月初一】定年首）
            Lunar lunar = new Lunar(timeZoneForToday, julianDayForToday, qiShuo);
            // 计算节气
            SolarTerm solarTermDTO = new SolarTerm(julianDayForToday, qiShuo, astronomical);
            // 计算黄历 (天干地支，干支纪年以【立春】定年首)
            Era era = new Era(julianDayForToday, lunar, timeZoneForToday);
            // 计算星座和儒略日
            Julian julian = new Julian(julianDayForToday, qiShuo);
            // 计算节假日
            Holiday holiday = new Holiday(timeZoneForToday, lunar, solarTermDTO, era);
            // 计算月相
            MoonPhase moonPhase = new MoonPhase(julianDayForToday, julianOfMonth, astronomical);

            almanacDTOS[i] = AlmanacDTO.builder()
                    .era(era)
                    .holiday(holiday)
                    .islamic(islamic)
                    .julian(julian)
                    .lunar(lunar)
                    .solarTerm(solarTermDTO)
                    .sunriseMoonset(sunriseMoonset)
                    .timeZoneDTO(timeZoneForToday)
                    .moonPhase(moonPhase)
                    .build();
        }
        return almanacDTOS;
    }

}
