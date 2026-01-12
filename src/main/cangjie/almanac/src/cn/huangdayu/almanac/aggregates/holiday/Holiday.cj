package almanac.cn.huangdayu.almanac.aggregates.holiday

import almanac.cn.huangdayu.almanac.aggregates.AbstractAlmanac
import almanac.cn.huangdayu.almanac.aggregates.era.Era
import almanac.cn.huangdayu.almanac.aggregates.lunar.Lunar
import almanac.cn.huangdayu.almanac.aggregates.solar_term.SolarTerm
import almanac.cn.huangdayu.almanac.dto.TimeZoneDTO
import almanac.cn.huangdayu.almanac.utils.AnnalsUtils
import almanac.cn.huangdayu.almanac.utils.FestivalHolidayUtils

/**
 * @author huangdayu create at 2021/1/21 11:11
 */
public class Holiday <: BaseAlmanac {

    public init(timeZoneDTO: ?TimeZoneDTO, lunar: ?Lunar, solarTerm: ?SolarTerm, era: ?Era) {
        super()
        // 计算农历节日
        this.lunarHolidays = AnnalsUtils.getCalendarHolidays(lunar, solarTerm)
        // 计算节气伏梅
        this.solarTermHolidays = AnnalsUtils.getSolarTermHolidays(solarTerm, era)
        // 计算公历节日
        this.calendarHolidays = FestivalHolidayUtils.getCalendarHolidays(timeZoneDTO)
    }

    public init(lunarHolidays: ?String, flag: Int32) {
        super()
        this.lunarHolidays = lunarHolidays
        this.flag = flag
    }

    /**
     * 西历节日
     */
    private var calendarHolidays: ?String = None
    /**
     * 农历节日
     */
    private var lunarHolidays: ?String = None
    /**
     * 节气
     */
    private var solarTermHolidays: ?String = None
    /**
     * 放假日子(可用于日期数字置红)
     */
    private var flag: Int32 = 0

    override public func getInfo(): ?String {
        return (calendarHolidays ?? "null") + " " + (lunarHolidays ?? "null") + " " + (solarTermHolidays ?? "null")
    }

    public func getCalendarHolidays(): ?String {
        return calendarHolidays
    }

    public func setCalendarHolidays(calendarHolidays: ?String): Unit {
        this.calendarHolidays = calendarHolidays
    }

    public func getLunarHolidays(): ?String {
        return lunarHolidays
    }

    public func setLunarHolidays(lunarHolidays: ?String): Unit {
        this.lunarHolidays = lunarHolidays
    }

    public func getSolarTermHolidays(): ?String {
        return solarTermHolidays
    }

    public func setSolarTermHolidays(solarTermHolidays: ?String): Unit {
        this.solarTermHolidays = solarTermHolidays
    }

    public func getFlag(): Int32 {
        return flag
    }

    public func setFlag(flag: Int32): Unit {
        this.flag = flag
    }
}