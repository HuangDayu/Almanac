package almanac.cn.huangdayu.almanac.aggregates.moon_phase

import almanac.cn.huangdayu.almanac.aggregates.AbstractAlmanac
import almanac.cn.huangdayu.almanac.aggregates.astronomical.Astronomical
import almanac.cn.huangdayu.almanac.aggregates.julian.Julian
import almanac.cn.huangdayu.almanac.utils.AnnalsUtils
import almanac.cn.huangdayu.almanac.utils.CommonUtils
import almanac.cn.huangdayu.almanac.utils.JulianCalendarUtils
import j2cjlib.utils.stringValueOf
import std.collection.ArrayList
import std.math.floor

/**
 * 月相
 *
 * @author huangdayu create at 2021/2/13 8:38
 */
public class MoonPhase <: BaseAlmanac {

    private init() {
        super()
    }

    public init(julianDayForToday: Int32, julianOfMonth: ?Julian, astronomical: ?Astronomical) {
        super()
        let moonPhasesList = ArrayList<?MoonPhase>()
        let julianDays = julianOfMonth.getOrThrow().getFirstJulianDayOfMonth() + julianOfMonth.getOrThrow().getNumberDayOfMonth()
        var moonLon = astronomical.getOrThrow().getLunarRetina()
        var j = 0i32
        while (j < julianOfMonth.getOrThrow().getNumberDayOfMonth()) {
            let moonLonValue = AnnalsUtils.so_accurate(moonLon)
            julianDay = Int32(floor(moonLonValue + 0.5))
            let xn = Int32(floor(moonLon / CommonUtils.PI_2 * 4.0 + 4000000.01)) % 4i32
            moonLon += CommonUtils.PI_2 / 4.0
            if (julianDay.getOrThrow() < julianOfMonth.getOrThrow().getFirstJulianDayOfMonth() || julianDay.getOrThrow() >= julianDays) {
                j++
                continue
            }
            julianDay = CommonUtils.JULIAN_FOR_2000 + julianDay.getOrThrow()
            let afterDays = julianDay.getOrThrow() - CommonUtils.JULIAN_FOR_2000 - julianDayForToday
            let moonPhase = if (afterDays == 0i32) { this } else { MoonPhase() }
            // 取得月相名称
            moonPhase.setName(AnnalsUtils.YUEXIANG[Int64(xn)])
            //月相时刻(儒略日)
            moonPhase.setJulianTime(Float64(CommonUtils.JULIAN_FOR_2000) + moonLonValue)
            //月相时间串
            moonPhase.setDateTime(JulianCalendarUtils.julianDays2str(Float64(CommonUtils.JULIAN_FOR_2000) + moonLonValue))
            moonPhase.setAfterDay(afterDays)
            moonPhasesList.append(moonPhase)
            j++
        }
        this.setNext(moonPhasesList)
    }

    private var index: ?Int32 = None
    /**
     * 月相名称
     */
    private var name: ?String = None
    private var julianDay: ?Int32 = None
    private var afterDay: ?Int32 = None
    /**
     * 月相时间串
     */
    private var dateTime: ?String = None
    private var desc: ?String = None
    /**
     * 月相时刻(儒略日)
     */
    private var julianTime: Float64 = 0.0

    private var next: ?ArrayList<?MoonPhase> = None

    public func getNextOne(): ?MoonPhase {
        for (moonPhase in next.getOrThrow()) {
            if (moonPhase.getOrThrow().getAfterDay().getOrThrow() > 0i32) {
                return moonPhase
            }
        }
        return next.getOrThrow()[0]
    }

    public func getDetails(): ?String {
        return if (name.isSome()) { (getInfo() ?? "null") + (if (afterDay.getOrThrow() != 0i32) { " 至今" + stringValueOf(afterDay) + "天" } else { " 今天" }) } else { getNextOne().getOrThrow().getDetails() }
    }

    override public func getInfo(): ?String {
        return if (name.isSome()) { (name ?? "null") + " " + (dateTime ?? "null") } else { getNextOne().getOrThrow().getInfo() }
    }


    public func getIndex(): ?Int32 {
        return index
    }

    public func setIndex(index: ?Int32): Unit {
        this.index = index
    }

    public func getName(): ?String {
        return name
    }

    public func setName(name: ?String): Unit {
        this.name = name
    }

    public func getJulianDay(): ?Int32 {
        return julianDay
    }

    public func setJulianDay(julianDay: ?Int32): Unit {
        this.julianDay = julianDay
    }

    public func getAfterDay(): ?Int32 {
        return afterDay
    }

    public func setAfterDay(afterDay: ?Int32): Unit {
        this.afterDay = afterDay
    }

    public func getDateTime(): ?String {
        return dateTime
    }

    public func setDateTime(dateTime: ?String): Unit {
        this.dateTime = dateTime
    }

    public func getDesc(): ?String {
        return desc
    }

    public func setDesc(desc: ?String): Unit {
        this.desc = desc
    }

    public func getJulianTime(): Float64 {
        return julianTime
    }

    public func setJulianTime(julianTime: Float64): Unit {
        this.julianTime = julianTime
    }

    public func getNext(): ?ArrayList<?MoonPhase> {
        return next
    }

    public func setNext(next: ?ArrayList<?MoonPhase>): Unit {
        this.next = next
    }
}