package almanac.cn.huangdayu.almanac.aggregates.solar_term

import almanac.cn.huangdayu.almanac.aggregates.AbstractAlmanac
import almanac.cn.huangdayu.almanac.aggregates.astronomical.Astronomical
import almanac.cn.huangdayu.almanac.aggregates.qishuo.QiShuo
import almanac.cn.huangdayu.almanac.utils.AnnalsUtils
import almanac.cn.huangdayu.almanac.utils.CommonUtils
import almanac.cn.huangdayu.almanac.utils.ConstantsUtils
import almanac.cn.huangdayu.almanac.utils.JulianCalendarUtils
import j2cjlib.utils.stringValueOf
import std.collection.ArrayList
import std.math.floor

/**
 * @author huangdayu create at 2021/1/22 11:37
 */
public class SolarTerm <: BaseAlmanac {

    private init() {
        super()
    }

    public init(julianDayForToday: Int32, qiShuo: ?QiShuo, astronomical: ?Astronomical) {
        super()
        // 计算当前月份的前一个节气，并从该节气开始结算接下来的24个节气
        var qk = Int32(floor((Float64(julianDayForToday - qiShuo.getOrThrow().getZhongQi().getOrThrow()[0] - 7i32)) / 15.2184))
        if (qk < 23i32 && julianDayForToday >= qiShuo.getOrThrow().getZhongQi().getOrThrow()[Int64(qk + 1i32)]) {
            // 节气的取值范围是0-23
            qk++
        }
        var sunLonValue = astronomical.getOrThrow().getSolarRetina()
        var sunLonTime: Float64 = 0.0
        let solarTermAfterDTOS = ArrayList<?SolarTerm>()
        var qj = 24i32
        var qn: Int32 = 0
        var qi = qk
        while (qi < qj) {
            // FIXME 2021-01-17 计算太阳视黄经较耗时
            sunLonTime = AnnalsUtils.qi_accurate(sunLonValue)
            julianDay = Int32(floor(sunLonTime + 0.5))
            qn = Int32(floor(sunLonValue / CommonUtils.PI_2 * 24.0 + 2.400000601E7)) % 24i32
            sunLonValue += CommonUtils.PI_2 / 24.0
            // BUG 2021-01-23 qiShuoDO.ZQ[qi]的儒略日与sunLonTime的值有出入,与julianDayOfThisDay的值一致
            julianDay = CommonUtils.JULIAN_FOR_2000 + julianDay.getOrThrow()
            let afterDays = julianDay.getOrThrow() - CommonUtils.JULIAN_FOR_2000 - julianDayForToday
            let solarTerm = if (afterDays == 0i32) { this } else { SolarTerm() }
            solarTerm.setJulianTime(sunLonTime)
            solarTerm.setDateTime(JulianCalendarUtils.julianDays2str(Float64(CommonUtils.JULIAN_FOR_2000) + sunLonTime))
            solarTerm.setIndex(qn)
            solarTerm.setName(AnnalsUtils.JIEQI[Int64(qn)])
            solarTerm.setJulianDay(julianDay)
            solarTerm.setDesc(ConstantsUtils.getDesc(AnnalsUtils.JIEQI[Int64(qn)]))
            solarTerm.setAfterDay(afterDays)
            solarTermAfterDTOS.append(solarTerm)
            qi++
            if (qi == 24i32) {
                qi = 0i32
                qj = qk
            }
        }
        this.setNext(solarTermAfterDTOS)
    }

    private var index: ?Int32 = None
    private var name: ?String = None
    private var julianDay: ?Int32 = None
    private var afterDay: ?Int32 = None
    private var dateTime: ?String = None
    private var desc: ?String = None
    /**
     * 节气时刻(儒略日)
     */
    private var julianTime: Float64 = 0.0

    private var next: ?ArrayList<?SolarTerm> = None

    public func getNextOne(): ?SolarTerm {
        for (solarTerm in next.getOrThrow()) {
            if (solarTerm.getOrThrow().getAfterDay().getOrThrow() > 0i32) {
                // 列表中，第一个大于0的则是下一个节气
                return solarTerm
            }
        }
        return next.getOrThrow()[0]
    }

    public func getByName(name: ?String): ?SolarTerm {
        for (solarTerm in next.getOrThrow()) {
            if (solarTerm.getOrThrow().getName().<-- Missing mapping for java.lang.String member: equalsIgnoreCase -->(name)) {
                return solarTerm
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

    public func getNext(): ?ArrayList<?SolarTerm> {
        return next
    }

    public func setNext(next: ?ArrayList<?SolarTerm>): Unit {
        this.next = next
    }
}