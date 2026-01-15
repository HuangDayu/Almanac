package cn.huangdayu.almanac;

import cn.huangdayu.almanac.aggregates.astronomical.Astronomical;
import cn.huangdayu.almanac.aggregates.era.Era;
import cn.huangdayu.almanac.aggregates.holiday.Holiday;
import cn.huangdayu.almanac.aggregates.islamic.Islamic;
import cn.huangdayu.almanac.aggregates.julian.Julian;
import cn.huangdayu.almanac.aggregates.lunar.Lunar;
import cn.huangdayu.almanac.aggregates.moon_phase.MoonPhase;
import cn.huangdayu.almanac.aggregates.qishuo.QiShuo;
import cn.huangdayu.almanac.aggregates.solar_term.SolarTerm;
import cn.huangdayu.almanac.aggregates.sunrise_moonset.SunriseMoonset;
import cn.huangdayu.almanac.dto.AlmanacDTO;
import cn.huangdayu.almanac.dto.TimeZoneDTO;

/**
 * @author huangdayu create at 2021/2/3 10:28
 */
public class Almanac {

    /**
     * 初始化时间和时区
     *
     * @return
     */
    private final TimeZoneDTO timeZoneDTO;

    public Almanac(TimeZoneDTO timeZoneDTO) {
        this.timeZoneDTO = timeZoneDTO;
    }

    public AlmanacDTO dayAlmanac() {
        // 数组从0开始，所以要减1
        int index = timeZoneDTO.getDay() - 1;
        return handler(timeZoneDTO, index)[index];
    }

    public AlmanacDTO[] monthAlmanac() {
        return handler(timeZoneDTO, 0);
    }

    public AlmanacDTO[][] yearAlmanac() {
        AlmanacDTO[][] almanacDTOS = new AlmanacDTO[12][];
        for (int i = 1; i <= 12; i++) {
            timeZoneDTO.setMonth(i);
            almanacDTOS[i - 1] = handler(new TimeZoneDTO(timeZoneDTO), 0);
        }
        return almanacDTOS;
    }


    /**
     * 计算历
     *
     * @param timeZoneDTO 时区数据结构体
     * @param dayIndex    指定从哪一天开始
     * @return 历数据对象
     */
    private AlmanacDTO[] handler(TimeZoneDTO timeZoneDTO, int dayIndex) {
        Julian julianOfMonth = new Julian(timeZoneDTO.getEraYear(), timeZoneDTO.getMonth());
        AlmanacDTO[] almanacDTOS = new AlmanacDTO[julianOfMonth.getNumberDayOfMonth()];
        Astronomical astronomical = new Astronomical(julianOfMonth.getFirstJulianDayOfMonth());
        // 如果指定了只算哪一天就算到哪一天为止
        int j = dayIndex > 0 ? dayIndex + 1 : julianOfMonth.getNumberDayOfMonth();
        for (int i = dayIndex; i < j; i++) {
            // 叠加日期
            TimeZoneDTO timeZoneForToday = timeZoneDTO.nextDay(i, julianOfMonth);
            // 计算儒略日,北京时12:00
            int julianDayForToday = julianOfMonth.getFirstJulianDayOfMonth() + i;
            // 计算气朔的数据信息
            QiShuo qiShuo = new QiShuo(julianDayForToday);
            // 计算日出月落,经纬度,港口
            SunriseMoonset sunriseMoonset = new SunriseMoonset(timeZoneForToday);
            // 计算回历
            Islamic islamic = new Islamic(julianDayForToday);
            // 计算农历（农历纪年以【正月初一】定年首）
            Lunar lunar = new Lunar(julianDayForToday, qiShuo, timeZoneForToday);
            // 计算节气
            SolarTerm solarTermDTO = new SolarTerm(julianDayForToday, qiShuo, astronomical);
            // 计算黄历 (天干地支，干支纪年以【立春】定年首)
            Era era = new Era(julianDayForToday, qiShuo, timeZoneForToday);
            // 计算星座和儒略日
            Julian julian = new Julian(julianDayForToday, qiShuo);
            // 计算节假日
            Holiday holiday = new Holiday(timeZoneForToday, lunar, solarTermDTO, era);
            // 计算月相
            MoonPhase moonPhase = new MoonPhase(julianDayForToday, julianOfMonth, astronomical);

            almanacDTOS[i] = new AlmanacDTO(timeZoneForToday, lunar, era, holiday, islamic, julian, solarTermDTO, sunriseMoonset, moonPhase);
        }
        return almanacDTOS;
    }
}
