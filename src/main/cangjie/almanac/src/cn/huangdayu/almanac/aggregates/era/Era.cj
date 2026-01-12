package almanac.cn.huangdayu.almanac.aggregates.era

import almanac.cn.huangdayu.almanac.aggregates.AbstractAlmanac
import almanac.cn.huangdayu.almanac.aggregates.lunar.Lunar
import almanac.cn.huangdayu.almanac.dto.TimeZoneDTO
import almanac.cn.huangdayu.almanac.utils.AnnalsUtils

/**
 * 黄历，天干地支，以 [立春]  作为新年的第一天
 *
 * @author huangdayu create at 2021/1/21 11:00
 */
public class Era <: BaseAlmanac {

    public init(julianDayForToday: Int32, lunar: ?Lunar, timeZoneDTO: ?TimeZoneDTO) {
        super()
        var value = lunar.getOrThrow().getYearChronology() + 12000i32
        // 干支纪年(立春)
        this.setYear((AnnalsUtils.TIANGAN[Int64(value % 10i32)] ?? "null") + (AnnalsUtils.DIZHI[Int64(value % 12i32)] ?? "null"))

        value = lunar.getOrThrow().getMonthChronologySum()
        this.setMonth((AnnalsUtils.TIANGAN[Int64(value % 10i32)] ?? "null") + (AnnalsUtils.DIZHI[Int64(value % 12i32)] ?? "null"))

        // 纪日,2000年1月7日起算
        value = julianDayForToday - 6i32 + 9000000i32

        this.setDay((AnnalsUtils.TIANGAN[Int64(value % 10i32)] ?? "null") + (AnnalsUtils.DIZHI[Int64(value % 12i32)] ?? "null"))

        let hour = (timeZoneDTO.getOrThrow().getHour() + 1i32) / 2i32
        //天干地支时
        this.setTime((AnnalsUtils.TIANGAN[Int64((hour + value * 12i32) % 10i32)] ?? "null") + (AnnalsUtils.DIZHI[Int64(hour % 12i32)] ?? "null"))
    }

    /**
     * 干支年
     */
    private var year: ?String = None
    /**
     * 干支月
     */
    private var month: ?String = None
    /**
     * 干支日
     */
    private var day: ?String = None
    /**
     * 干支时
     */
    private var time: ?String = None

    override public func getInfo(): ?String {
        return (year ?? "null") + "年" + (month ?? "null") + "月" + (day ?? "null") + "日" + (time ?? "null") + "时"
    }

    public func getYear(): ?String {
        return year
    }

    public func setYear(year: ?String): Unit {
        this.year = year
    }

    public func getMonth(): ?String {
        return month
    }

    public func setMonth(month: ?String): Unit {
        this.month = month
    }

    public func getDay(): ?String {
        return day
    }

    public func setDay(day: ?String): Unit {
        this.day = day
    }

    public func getTime(): ?String {
        return time
    }

    public func setTime(time: ?String): Unit {
        this.time = time
    }
}