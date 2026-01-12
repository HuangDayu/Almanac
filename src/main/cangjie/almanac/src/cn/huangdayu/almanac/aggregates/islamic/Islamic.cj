package almanac.cn.huangdayu.almanac.aggregates.islamic

import almanac.cn.huangdayu.almanac.aggregates.AbstractAlmanac
import std.math.floor

/**
 * 回历
 *
 * @author huangdayu create at 2021/1/21 10:51
 */
public class Islamic <: BaseAlmanac {

    public init(julianDays: Int32) {
        super()
        // 以下算法使用Excel测试得到,测试时主要关心年临界与月临界
        let z: Int32
        let y: Int32
        let m: Int32
        var d: Int32 = 0
        d = julianDays + 503105i32
        // 10631为一周期(30年)
        z = d / 10631i32
        d -= z * 10631i32
        // 加0.5的作用是保证闰年正确(一周中的闰年是第2,5,7,10,13,16,18,21,24,26,29年)
        y = Int32(floor((Float64(d) + 0.5) / 354.366))
        d -= Int32(floor(Float64(y) * 354.366 + 0.5))
        // 分子加0.11,分母加0.01的作用是第354或355天的的月分保持为12月(m=11)
        m = Int32(floor((Float64(d) + 0.11) / 29.51))
        d -= Int32(floor(Float64(m) * 29.5 + 0.5))
        this.setYear(z * 30i32 + y + 1i32)
        this.setMonth(m + 1i32)
        this.setDay(d + 1i32)
    }

    /**
     * 年(回历)
     */
    private var year: Int32 = 0
    /**
     * 月(回历)
     */
    private var month: Int32 = 0
    /**
     * 日(回历)
     */
    private var day: Int32 = 0

    override public func getInfo(): ?String {
        return year.toString() + "年" + month.toString() + "月" + day.toString() + "日"
    }


    public func getYear(): Int32 {
        return year
    }

    public func setYear(year: Int32): Unit {
        this.year = year
    }

    public func getMonth(): Int32 {
        return month
    }

    public func setMonth(month: Int32): Unit {
        this.month = month
    }

    public func getDay(): Int32 {
        return day
    }

    public func setDay(day: Int32): Unit {
        this.day = day
    }
}