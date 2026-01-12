package almanac.cn.huangdayu.almanac.aggregates.julian

import almanac.cn.huangdayu.almanac.aggregates.AbstractAlmanac
import almanac.cn.huangdayu.almanac.aggregates.qishuo.QiShuo
import almanac.cn.huangdayu.almanac.utils.AnnalsUtils
import almanac.cn.huangdayu.almanac.utils.CommonUtils
import almanac.cn.huangdayu.almanac.utils.JulianCalendarUtils
import j2cjlib.utils.stringValueOf
import std.math.floor

/**
 * 儒略日
 *
 * @author huangdayu create at 2021/1/21 11:25
 */
public class Julian <: BaseAlmanac {

    public init(julianDayForToday: Int32, qiShuo: ?QiShuo) {
        super()
        this.days = julianDayForToday + CommonUtils.JULIAN_FOR_2000
        // 星座
        var mk = Int32(floor((Float64(julianDayForToday - qiShuo.getOrThrow().getZhongQi().getOrThrow()[0] - 15i32)) / 30.43685))
        if (mk < 11i32 && julianDayForToday >= qiShuo.getOrThrow().getZhongQi().getOrThrow()[Int64(2i32 * mk + 2i32)]) {
            // 星座所在月的序数,(如果j=13,ob.d0不会超过第14号中气)
            mk++
        }
        this.setConstellation((AnnalsUtils.XINGZUO[Int64((mk + 12i32) % 12i32)] ?? "null") + "座")
    }

    public init(year: Int32, month: Int32) {
        super()
        // FIXME 2021-01-22 不知为何减去西历两千年的儒略日
        // 这个月1号的儒略日,公历月首,中午
        this.firstJulianDayOfMonth = Int32(floor(JulianCalendarUtils.getJulianDayNumber(year, month)) - Float64(CommonUtils.JULIAN_FOR_2000))
        var nextMonth = month + 1i32
        var nextYear = year
        if (nextMonth > 12i32) {
            nextMonth = nextMonth - 12i32
            nextYear = year + 1i32
        }
        // 这个月1号的儒略日和下个月1号的儒略日之差,月天数(公历)
        this.numberDayOfMonth = Int32(floor(JulianCalendarUtils.getJulianDayNumber(nextYear, nextMonth)) - Float64(CommonUtils.JULIAN_FOR_2000) - Float64(firstJulianDayOfMonth))
        // 本月第一天的星期
        this.weekFirstDayOfMonth = (firstJulianDayOfMonth + CommonUtils.JULIAN_FOR_2000 + 1i32 + 7000000i32) % 7i32
    }

    /**
     * 月的第一天的儒略日
     */
    private var firstJulianDayOfMonth: Int32 = 0

    /**
     * 月份的天数
     */
    private var numberDayOfMonth: Int32 = 0

    /**
     * 本月第一天的星期
     */
    private var weekFirstDayOfMonth: Int32 = 0

    private var days: ?Int32 = None

    /**
     * 星座
     */
    private var constellation: ?String = None

    override public func getInfo(): ?String {
        return stringValueOf(getDays()) + " " + (getConstellation() ?? "null")
    }

    public func getFirstJulianDayOfMonth(): Int32 {
        return firstJulianDayOfMonth
    }

    public func setFirstJulianDayOfMonth(firstJulianDayOfMonth: Int32): Unit {
        this.firstJulianDayOfMonth = firstJulianDayOfMonth
    }

    public func getNumberDayOfMonth(): Int32 {
        return numberDayOfMonth
    }

    public func setNumberDayOfMonth(numberDayOfMonth: Int32): Unit {
        this.numberDayOfMonth = numberDayOfMonth
    }

    public func getWeekFirstDayOfMonth(): Int32 {
        return weekFirstDayOfMonth
    }

    public func setWeekFirstDayOfMonth(weekFirstDayOfMonth: Int32): Unit {
        this.weekFirstDayOfMonth = weekFirstDayOfMonth
    }

    public func getDays(): ?Int32 {
        return days
    }

    public func setDays(days: ?Int32): Unit {
        this.days = days
    }

    public func getConstellation(): ?String {
        return constellation
    }

    public func setConstellation(constellation: ?String): Unit {
        this.constellation = constellation
    }
}