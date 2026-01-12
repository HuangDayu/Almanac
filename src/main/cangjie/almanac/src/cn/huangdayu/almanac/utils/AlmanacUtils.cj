package almanac.cn.huangdayu.almanac.utils

import almanac.cn.huangdayu.almanac.aggregates.astronomical.Astronomical
import almanac.cn.huangdayu.almanac.aggregates.era.Era
import almanac.cn.huangdayu.almanac.aggregates.holiday.Holiday
import almanac.cn.huangdayu.almanac.aggregates.islamic.Islamic
import almanac.cn.huangdayu.almanac.aggregates.julian.Julian
import almanac.cn.huangdayu.almanac.aggregates.lunar.Lunar
import almanac.cn.huangdayu.almanac.aggregates.moon_phase.MoonPhase
import almanac.cn.huangdayu.almanac.aggregates.qishuo.QiShuo
import almanac.cn.huangdayu.almanac.aggregates.solar_term.SolarTerm
import almanac.cn.huangdayu.almanac.aggregates.sunrise_moonset.SunriseMoonset
import almanac.cn.huangdayu.almanac.dto.AlmanacDTO
import almanac.cn.huangdayu.almanac.dto.TimeZoneDTO

/**
 * 历计算工具类
 *
 * @author huangdayu create at 2020-03-15
 */
public class AlmanacUtils {
    private init() { }

    /**
     * 日历
     *
     * @param timeZoneDTO 时区数据结构体
     * @return 历数据对象
     */
    public static func ofDay(timeZoneDTO: ?TimeZoneDTO): ?AlmanacDTO {
        // 数组从0开始，所以要减1
        let index = timeZoneDTO.getOrThrow().getDay() - 1i32
        return handler(timeZoneDTO, index).getOrThrow()[Int64(index)]
    }

    /**
     * 月历
     *
     * @param timeZoneDTO 时区数据结构体
     * @return 历数据对象
     */
    public static func ofMonth(timeZoneDTO: ?TimeZoneDTO): ?Array<?AlmanacDTO> {
        return handler(timeZoneDTO, -1i32)
    }

    /**
     * 年历
     *
     * @param timeZoneDTO 时区数据结构体
     * @return 历数据对象
     */
    public static func ofYear(timeZoneDTO: ?TimeZoneDTO): ?Array<Array<?AlmanacDTO>> {
        let almanacDTOS = Array<Array<?AlmanacDTO>>(12) { _ => Array<?AlmanacDTO>(0) { _ => None } }
        var i = 1i32
        while (i <= 12i32) {
            timeZoneDTO.getOrThrow().setMonth(i)
            almanacDTOS[Int64(i - 1i32)] = ofMonth(TimeZoneDTO(timeZoneDTO)).getOrThrow()
            i++
        }
        return almanacDTOS
    }

    /**
     * 计算历
     *
     * @param timeZoneDTO 时区数据结构体
     * @return 历数据对象
     */
    private static func handler(timeZoneDTO: ?TimeZoneDTO, dayIndex: Int32): ?Array<?AlmanacDTO> {

        let julianOfMonth = Julian(timeZoneDTO.getOrThrow().getEraYear(), timeZoneDTO.getOrThrow().getMonth())

        // 提取各日信息
        let almanacDTOS = Array<?AlmanacDTO>(Int64(julianOfMonth.getNumberDayOfMonth())) { _ => None }

        let astronomical = Astronomical(julianOfMonth.getFirstJulianDayOfMonth())

        // dayIndex >= 0 ? 计算日历 : 计算月历
        let forDay = dayIndex >= 0i32

        var i = if (forDay) { dayIndex } else { 0i32 }
        while (if (forDay) { i <= dayIndex } else { i < julianOfMonth.getNumberDayOfMonth() }) {
            // 叠加日期
            let timeZoneForToday = timeZoneDTO.getOrThrow().nextDay(i, julianOfMonth)
            // 计算儒略日,北京时12:00
            let julianDayForToday = julianOfMonth.getFirstJulianDayOfMonth() + i
            // 计算气朔的数据信息
            let qiShuo = QiShuo(julianDayForToday)
            // 计算日出月落,经纬度,港口
            let sunriseMoonset = SunriseMoonset(timeZoneForToday)
            // 计算回历
            let islamic = Islamic(julianDayForToday)
            // 计算农历（农历纪年以【正月初一】定年首）
            let lunar = Lunar(timeZoneForToday, julianDayForToday, qiShuo)
            // 计算节气
            let solarTermDTO = SolarTerm(julianDayForToday, qiShuo, astronomical)
            // 计算黄历 (天干地支，干支纪年以【立春】定年首)
            let era = Era(julianDayForToday, lunar, timeZoneForToday)
            // 计算星座和儒略日
            let julian = Julian(julianDayForToday, qiShuo)
            // 计算节假日
            let holiday = Holiday(timeZoneForToday, lunar, solarTermDTO, era)
            // 计算月相
            let moonPhase = MoonPhase(julianDayForToday, julianOfMonth, astronomical)

            almanacDTOS[Int64(i)] = AlmanacDTO(lunar, era, holiday, islamic, julian, solarTermDTO, sunriseMoonset, timeZoneForToday, moonPhase)
            i++
        }
        return almanacDTOS
    }
}