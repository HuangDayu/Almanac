package almanac.cn.huangdayu.almanac.aggregates.lunar

import almanac.cn.huangdayu.almanac.aggregates.AbstractAlmanac
import almanac.cn.huangdayu.almanac.aggregates.qishuo.QiShuo
import almanac.cn.huangdayu.almanac.dto.TimeZoneDTO
import almanac.cn.huangdayu.almanac.utils.AnnalsUtils
import almanac.cn.huangdayu.almanac.utils.ConstantsUtils
import j2cjlib.utils.J2CjStringExtension
import std.math.floor

/**
 * 农历，阴历，以 [正月初一] 作为新年的第一天
 *
 * @author huangdayu create at 2021/1/21 10:53
 */
public class Lunar <: BaseAlmanac {

    public init(timeZoneDTO: ?TimeZoneDTO, julianDayForToday: Int32, qiShuo: ?QiShuo) {
        super()
        //------------------------------------农历排月序计算------------------------------------//

        // 农历所在月的序数
        var mk = (julianDayForToday - qiShuo.getOrThrow().getHeShuo().getOrThrow()[0]) / 30i32
        if (mk < 13i32 && qiShuo.getOrThrow().getHeShuo().getOrThrow()[Int64(mk + 1i32)] <= julianDayForToday) {
            mk++
        }

        // 距农历月首的编移量,0对应初一
        this.setMonthOffset(julianDayForToday - qiShuo.getOrThrow().getHeShuo().getOrThrow()[Int64(mk)])
        // 农历日名称
        this.setDay(AnnalsUtils.DAY_NAME[Int64(julianDayForToday - qiShuo.getOrThrow().getHeShuo().getOrThrow()[Int64(mk)])])
        this.setLeapYear(qiShuo.getOrThrow().getLeapMonthIndex() > 0i32)
        // 月名称
        this.setMonth(qiShuo.getOrThrow().getMonthNames().getOrThrow()[Int64(mk)])
        // 月大小
        this.setDaysOfMonth(qiShuo.getOrThrow().getMonthValue().getOrThrow()[Int64(mk)])
        // 闰状况
        this.setLeapMonth((qiShuo.getOrThrow().getLeapMonthIndex() != 0i32 && qiShuo.getOrThrow().getLeapMonthIndex() == mk))
        // 下个月名称,判断除夕时要用到
        this.setNextMonth(if (mk < 13i32) { qiShuo.getOrThrow().getMonthNames().getOrThrow()[Int64(mk + 1i32)] } else { "未知" })
        // 时辰
        let sum = Int32(Float64(timeZoneDTO.getOrThrow().getHour()) + 0.01 * Float64(timeZoneDTO.getOrThrow().getMinute()))
        var index = (sum + 1i32) / 2i32
        if (index >= 12i32) {
            index = 0i32
        }
        var lunarTime: ?String = (AnnalsUtils.DIZHI[Int64(index)] ?? "null") + "时" + (ConstantsUtils.GENG[Int64(index)] ?? "null")
        if ((timeZoneDTO.getOrThrow().getMinute() % 15i32) > 13i32) {
            let k = (timeZoneDTO.getOrThrow().getMinute() + 3i32) / 15i32
            lunarTime = (lunarTime ?? "null") + (ConstantsUtils.KE[Int64(k)] ?? "null")
        }
        this.setTime(lunarTime)
        // 一般第3个月为春节
        var value = qiShuo.getOrThrow().getHeShuo().getOrThrow()[2]
        var l = 0i32
        while (l < 14i32) {
            // 找春节
            if (!"正".equals(qiShuo.getOrThrow().getMonthNames().getOrThrow()[Int64(l)]) || qiShuo.getOrThrow().getLeapMonthIndex() == l && l != 0i32) {
                l++
                continue
            }
            value = qiShuo.getOrThrow().getHeShuo().getOrThrow()[Int64(l)]
            if (julianDayForToday < value) {
                value -= 365i32
                // 无需再找下一个正月
                break
            }
            l++
        }
        // 计算该年春节与1984年平均春节(立春附近)相差天数估计
        value = value + 5810i32
        // 农历纪年(10进制,1984年起算)
        let lunarYears = Int32(floor(Float64(value) / 365.2422 + 0.5))
        value = lunarYears + 12000i32
        // String Lyear3 = this.Gan[D % 10] + this.Zhi[D % 12]; // 干支纪年(正月) ,
        // 黄帝纪年,春节才视为新年
        let kingChronology = lunarYears + 1984i32 + 2698i32
        this.setKingChronology(kingChronology)
        this.setKingChronologyName("开元" + kingChronology.toString() + "年")
        // 干支纪年（春节）
        this.setYear((AnnalsUtils.TIANGAN[Int64(value % 10i32)] ?? "null") + (AnnalsUtils.DIZHI[Int64(value % 12i32)] ?? "null"))
        // 该年对应的生肖
        this.setZodiac(AnnalsUtils.SHENGXIAO[Int64(value % 12i32)])
        // 年号
        this.setYearName(AnnalsUtils.getYearName(timeZoneDTO.getOrThrow().getEraYear()))

        // 干支纪年处理 以立春为界定年首
        value = Int32(Float64(qiShuo.getOrThrow().getZhongQi().getOrThrow()[3] + (if (julianDayForToday < qiShuo.getOrThrow().getZhongQi().getOrThrow()[3]) { -365i32 } else { 0i32 })) + 365.25 * 16.0 - 35.0)
        // 以立春为界定纪年 农历纪年(10进制,1984年起算)
        this.setYearChronology(Int32(floor(Float64(value) / 365.2422 + 0.5)))

        // 纪月处理,1998年12月7(大雪)开始连续进行节气计数,0为甲子
        mk = Int32(floor((Float64(julianDayForToday - qiShuo.getOrThrow().getZhongQi().getOrThrow()[0])) / 30.43685))
        if (mk < 12i32 && julianDayForToday >= qiShuo.getOrThrow().getZhongQi().getOrThrow()[Int64(2i32 * mk + 1i32)]) {
            // 相对大雪的月数计算,lunarMonthIndex的取值范围 0-12
            mk++
        }
        // 相对于1998年12月7(大雪)的月数,900000为正数基数
        value = mk + Int32(floor((Float64(qiShuo.getOrThrow().getZhongQi().getOrThrow()[12] + 390i32)) / 365.2422)) * 12i32 + 900000i32
        this.setMonthChronologySum(value)
        // 农历纪月
        this.setMonthChronology(value % 12i32)
    }


    /**
     * 距农历月首的编移量,0对应初一
     */
    private var monthOffset: Int32 = 0
    /**
     * 干支年
     */
    private var year: ?String = None
    /**
     * 农历月名称
     */
    private var month: ?String = None
    /**
     * 农历日名称
     */
    private var day: ?String = None

    /**
     * 农历时
     */
    private var time: ?String = None
    /**
     * 农历月大小
     */
    private var daysOfMonth: Int32 = 0
    /**
     * 农历闰状况(值为'闰'或空串)
     */
    private var leapMonth: ?Bool = false
    /**
     * 是否闰年
     */
    private var leapYear: ?Bool = false
    /**
     * 下个农历月名称,判断除夕时要用到
     */
    private var nextMonth: ?String = None
    /**
     * 黄帝纪年
     */
    private var kingChronology: Int32 = 0
    /**
     * 农历年,农历纪年(10进制,1984年起算)
     */
    private var yearChronology: Int32 = 0

    /**
     * 农历月总数
     */
    public var monthChronologySum: Int32 = 0

    /**
     * 农历月,农历纪月
     */
    private var monthChronology: Int32 = 0

    /**
     * 生肖
     */
    private var zodiac: ?String = None

    /**
     * 年号纪年
     */
    private var yearName: ?String = None

    /**
     * 皇帝纪年
     */
    private var kingChronologyName: ?String = None


    override public func getInfo(): ?String {
        return (year ?? "null") + (zodiac ?? "null") + "年" + (month ?? "null") + (if (Int32(month.getOrThrow().size) < 2i32) { "月" } else { "" }) + (day ?? "null") + (time ?? "null")
    }

    public func getMonthOffset(): Int32 {
        return monthOffset
    }

    public func setMonthOffset(monthOffset: Int32): Unit {
        this.monthOffset = monthOffset
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

    public func getDaysOfMonth(): Int32 {
        return daysOfMonth
    }

    public func setDaysOfMonth(daysOfMonth: Int32): Unit {
        this.daysOfMonth = daysOfMonth
    }

    public func getLeapMonth(): ?Bool {
        return leapMonth
    }

    public func setLeapMonth(leapMonth: ?Bool): Unit {
        this.leapMonth = leapMonth
    }

    public func getLeapYear(): ?Bool {
        return leapYear
    }

    public func setLeapYear(leapYear: ?Bool): Unit {
        this.leapYear = leapYear
    }

    public func getNextMonth(): ?String {
        return nextMonth
    }

    public func setNextMonth(nextMonth: ?String): Unit {
        this.nextMonth = nextMonth
    }

    public func getKingChronology(): Int32 {
        return kingChronology
    }

    public func setKingChronology(kingChronology: Int32): Unit {
        this.kingChronology = kingChronology
    }

    public func getYearChronology(): Int32 {
        return yearChronology
    }

    public func setYearChronology(yearChronology: Int32): Unit {
        this.yearChronology = yearChronology
    }

    public func getMonthChronologySum(): Int32 {
        return monthChronologySum
    }

    public func setMonthChronologySum(monthChronologySum: Int32): Unit {
        this.monthChronologySum = monthChronologySum
    }

    public func getMonthChronology(): Int32 {
        return monthChronology
    }

    public func setMonthChronology(monthChronology: Int32): Unit {
        this.monthChronology = monthChronology
    }

    public func getZodiac(): ?String {
        return zodiac
    }

    public func setZodiac(zodiac: ?String): Unit {
        this.zodiac = zodiac
    }

    public func getYearName(): ?String {
        return yearName
    }

    public func setYearName(yearName: ?String): Unit {
        this.yearName = yearName
    }

    public func getKingChronologyName(): ?String {
        return kingChronologyName
    }

    public func setKingChronologyName(kingChronologyName: ?String): Unit {
        this.kingChronologyName = kingChronologyName
    }
}