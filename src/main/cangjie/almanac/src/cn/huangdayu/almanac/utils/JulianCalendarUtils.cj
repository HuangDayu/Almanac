package almanac.cn.huangdayu.almanac.utils

import almanac.cn.huangdayu.almanac.dto.TimeZoneDTO
import j2cjlib.utils.parse
import j2cjlib.utils.rem
import UNSUPPORTED.java.util.Calendar
import std.math.floor


/**
 * 儒略历工具类
 * 儒略历（Julian calendar）是由罗马共和国独裁官儒略·恺撒（即盖乌斯·尤里乌斯·凯撒）采纳数学家兼天文学家索西琴尼的计算后，
 * 于公元前45年1月1日起执行的取代旧罗马历法的一种历法。儒略历中，一年被划分为12个月，大小月交替；四年一闰，平年365日，
 * 闰年366日为在当年二月底增加一闰日，年平均长度为365.25日。由于实际使用过程中累积的误差随着时间越来越大，
 * 1582年教皇格里高利十三世颁布、推行了以儒略历为基础改善而来的格里历，即沿用至今的公历。
 *
 * @author huangdayu
 * @update 2020-03-15
 */
public class JulianCalendarUtils {
    public init() { }
    // private final String[] weeks = { "日", "一", "二", "三", "四", "五", "六", "七" };

    /***
     * 输入年，月，日算出儒略日 http://www.ilovematlab.cn/thread-19002-1-1.html
     *
     * @param year
     *            年
     * @param month
     *            月
     * @param day
     *            日
     * @return 儒略日
     */
    public static func getJuLian(year: Int32, month: Int32, day: Int32): Int32 {
        return day - 32075i32 + 1461i32 * (year + 4800i32 + (month - 14i32) / 12i32) / 4i32 + 367i32 * (month - 2i32 - (month - 14i32) / 12i32 * 12i32) / 12i32 - 3i32 * ((year + 4900i32 + (month - 14i32) / 12i32) / 100i32) / 4i32
    }

    public static func getJuLian(calendar: ?<-- No type mapping: java.util.Calendar -->): Int32 {
        let y = calendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: YEAR -->)
        let m = calendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: MONTH -->) + 1i32
        let d = calendar.<-- Missing mapping for java.util.Calendar member: get -->(<-- No type mapping: java.util.Calendar -->.<-- Missing mapping for java.util.Calendar member: DAY_OF_MONTH -->)
        return getJuLian(y, m, d)
    }

    /***
     * 取某世纪年的1月1日的儒略日数，如1900,1800,2000,2100
     *
     * @param year
     *            公元1百年
     * @return 1757583
     */
    public static func getJuLianByYear(year: Int32): Int32 {
        let quZhen = year / 100i32
        let quYu = year % 100i32
        var Str: ?String = None

        if (0i32 == quYu && quZhen > 0i32 && year != 0i32) { // 整除100时取整减1 ：如果是1000年，则算出900年的儒略日
            Str = (quZhen - 1i32).toString() + "00"
        } else {
            Str = quZhen.toString() + "00"
        }

        if (year <= 0i32) {
            Str = ((-year - 1i32) / 100i32 - 1i32).toString() + "00"
        }

        // System.out.println(Str);
        return getJuLian(parse<Int32>(Str.getOrThrow()), 1i32, 1i32)
    }

    /***
     * 输入2017 返回1900
     *
     * @param year
     * @return
     */
    public static func getYear_INT(year: Int32): Int32 {

        let quZhen = year / 100i32
        let quYu = year % 100i32
        var Str: ?String = None

        if (quZhen > 0i32 && year != 0i32) { // 整除100时取整减1 ：如果是1000年，则算出900年的儒略日
            Str = (quZhen - 1i32).toString() + "00"
        } else {
            Str = quZhen.toString() + "00"
        }

        if (year <= 0i32) {
            Str = ((-year - 1i32) / 100i32 - 1i32).toString() + "00"
        }

        return parse<Int32>(Str.getOrThrow())
    }

    /****
     * 公历转儒略日
     *
     * @return
     */
    public static func getJulianDayNumber(year: Int32, month: Int32): Float64 {
        return getJulianDayNumber(year, month, 1i32, 12i32, 0i32, 0.1)
    }

    /****
     * 公历转儒略日
     *
     * @return
     */
    public static func getJulianDayNumber(year: Int32, month: Int32, day: Int32, hour: Int32, minute: Int32, second: Float64): Float64 {
        return getJulianDayNumber(year, month, Float64(day) + ((second / 60.0 + Float64(minute)) / 60.0 + Float64(hour)) / 24.0)
    }

    /***
     * 公历转儒略日
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static func getJulianDayNumber(year: Int32, month: Int32, day: Float64): Float64 {
        var j2cj_param_generated_month = month
        var j2cj_param_generated_year = year
        if (j2cj_param_generated_month > 12i32) {
            j2cj_param_generated_month = j2cj_param_generated_month % 12i32
            j2cj_param_generated_year = j2cj_param_generated_year + j2cj_param_generated_month / 12i32
        }
        var n = 0i32
        var G = 0i32
        if (Float64(j2cj_param_generated_year * 372i32 + j2cj_param_generated_month * 31i32) + floor(day) >= 588829.0) {
            G = 1i32 // 判断是否为格里高利历日1582*372+10*31+15
        }
        if (j2cj_param_generated_month <= 2i32) {
            j2cj_param_generated_month += 12i32
            j2cj_param_generated_year--
        }
        if (G != 0i32) {
            n = Int32(floor(Float64(j2cj_param_generated_year / 100i32)))
            n = 2i32 - n + Int32(floor(Float64(n / 4i32))) // 加百年闰
        }
        return floor(365.25 * (Float64(j2cj_param_generated_year + 4716i32))) + floor(30.6001 * (Float64(j2cj_param_generated_month + 1i32))) + day + Float64(n) - 1524.5
    }

    /****
     * 儒略日数转公历
     *
     * @param julianDays
     * @return
     */
    public static func julianDaysToTimeZone(julianDays: Float64): ?String {
        var year: Int32 = 0
        var month: Int32 = 0
        let day: Int32
        let hour: Int32
        let minute: Int32
        let second: Float64
        var D = Int32(floor(julianDays + 0.5))
        let c: Int32
        var F = julianDays + 0.5 - Float64(D) // 取得日数的整数部份A及小数部分F

        if (D >= 2299161i32) {
            c = Int32(floor((Float64(D) - 1867216.25) / 36524.25))
            D += Int32(Float64(1i32 + c) - floor(Float64(c / 4i32)))
        }
        D += 1524i32
        year = Int32(floor((Float64(D) - 122.1) / 365.25)) // 年数
        D -= Int32(floor(365.25 * Float64(year)))
        month = Int32(floor(Float64(D) / 30.601)) // 月数
        D -= Int32(floor(30.601 * Float64(month)))
        day = D // 日数
        if (month > 13i32) {
            month -= 13i32
            year -= 4715i32
        } else {
            month -= 1i32
            year -= 4716i32
        }
        // 日的小数转为时分秒
        F *= 24.0
        hour = Int32(floor(F))
        F -= Float64(hour)
        F *= 60.0
        minute = Int32(floor(F))
        F -= Float64(minute)
        F *= 60.0
        second = F

        return year.toString() + "-" + (CommonUtils.fillZero(month) ?? "null") + "-" + (CommonUtils.fillZero(day) ?? "null") + " " + (CommonUtils.fillZero(hour) ?? "null") + ":" + (CommonUtils.fillZero(minute) ?? "null") + ":" + (CommonUtils.fillZero(Int32(second)) ?? "null")
    }

    /***
     * 日期转为串
     *
     * @param timeZoneDTO
     * @return
     */
    @<-- Unsupported annotation: Deprecated -->
    public static func julianDateByTimeZone(@<-- Unsupported annotation: Deprecated --> timeZoneDTO: ?TimeZoneDTO): ?String {
        var y: ?String = "     " + timeZoneDTO.getOrThrow().getYear().toString()
        var m: ?String = "0" + timeZoneDTO.getOrThrow().getMonth().toString()
        var d: ?String = "0" + timeZoneDTO.getOrThrow().getDay().toString()
        var h = timeZoneDTO.getOrThrow().getHour()
        var mm = timeZoneDTO.getOrThrow().getMinute()
        var s = Int32(floor(Float64(timeZoneDTO.getOrThrow().getSecond()) + 0.5))
        if (s >= 60i32) {
            s -= 60i32
            mm++
        }
        if (mm >= 60i32) {
            mm -= 60i32
            h++
        }
        var hStr: ?String = None
        var mStr: ?String = None
        var sStr: ?String = None
        hStr = "0" + h.toString()
        mStr = "0" + mm.toString()
        sStr = "0" + s.toString()
        y = CommonUtils.subString(y, Int32(y.getOrThrow().size) - 5i32)
        m = CommonUtils.subString(m, Int32(m.getOrThrow().size) - 2i32)
        d = CommonUtils.subString(d, Int32(d.getOrThrow().size) - 2i32)
        hStr = CommonUtils.subString(hStr, Int32(hStr.getOrThrow().size) - 2i32)
        mStr = CommonUtils.subString(mStr, Int32(mStr.getOrThrow().size) - 2i32)
        sStr = CommonUtils.subString(sStr, Int32(sStr.getOrThrow().size) - 2i32)
        return (y ?? "null") + "-" + (m ?? "null") + "-" + (d ?? "null") + " " + (hStr ?? "null") + ":" + (mStr ?? "null") + ":" + (sStr ?? "null")
    }

    /****
     * JD转为串
     *
     * @param jd
     * @return
     */
    public static func julianDays2str(jd: Float64): ?String {
        return julianDaysToTimeZone(jd)
    }

    /***
     * 提取jd中的时间(去除日期)
     *
     * @param jd
     * @return
     */
    public static func getJulianTime(jd: Float64): ?String {
        var j2cj_param_generated_jd = jd

        let h: Int32
        let m: Int32
        var s: Int32 = 0
        j2cj_param_generated_jd += 0.5
        j2cj_param_generated_jd = (j2cj_param_generated_jd - floor(j2cj_param_generated_jd))
        s = Int32(floor(j2cj_param_generated_jd * 86400.0 + 0.5))
        h = Int32(floor(Float64(s / 3600i32)))
        s -= h * 3600i32
        m = Int32(floor(Float64(s / 60i32)))
        s -= m * 60i32
        let hStr: ?String
        let mStr: ?String
        let sStr: ?String
        hStr = "0" + h.toString()
        mStr = "0" + m.toString()
        sStr = "0" + s.toString()
        return (CommonUtils.subString(hStr, Int32(hStr.getOrThrow().size) - 2i32, Int32(hStr.getOrThrow().size)) ?? "null") + ":" + (CommonUtils.subString(mStr, Int32(mStr.getOrThrow().size) - 2i32, Int32(mStr.getOrThrow().size)) ?? "null") + ":" + (CommonUtils.subString(sStr, Int32(sStr.getOrThrow().size) - 2i32, Int32(sStr.getOrThrow().size)) ?? "null")
    }

    //    public static String getJulianToDateTime(double julianDay) {
    //        return DateTimeUtils.dateFormat(julianDaysToTimeZone(julianDay).toCalendar(), "yyyy-MM-dd HH:mm:ss.SS");
    //    }

    /****
     * 星期计算
     *
     * @param jd
     * @return
     */
    protected func getWeek(jd: Float64): Int32 {
        return Int32(floor(jd + 1.5 + 7000000.0)) % 7i32
    }

    /****
     * 求y年m月的第n个星期w的儒略日数
     *
     * @param year
     * @param month
     * @param n
     * @param week
     * @return
     */
    public static func julianDaysByWeek(year: Int32, month: Int32, n: Int32, week: Int32): Int32 {
        var j2cj_param_generated_month = month
        var j2cj_param_generated_year = year
        // =================可能有错

        // 求y年m月的第n个星期w的儒略日数
        let jd = getJulianDayNumber(j2cj_param_generated_year, j2cj_param_generated_month, 1.5) // 月首儒略日
        let w0 = rem((jd + 1.0 + 7000000.0), 7.0) // 月首的星期
        var r = Int32(jd - w0 + Float64(7i32 * n) + Float64(week))
        // jd-w0+7*n是和n个星期0,起算下本月第一行的星期日(可能落在上一月)。加w后为第n个星期w
        if (Float64(week) >= w0) {
            r -= 7i32 // 第1个星期w可能落在上个月,造成多算1周,所以考虑减1周
        }
        if (n == 5i32) {
            j2cj_param_generated_month++
            if (j2cj_param_generated_month > 12i32) {
                j2cj_param_generated_month = 1i32
                j2cj_param_generated_year++
            } // 下个月
            if (Float64(r) >= getJulianDayNumber(j2cj_param_generated_year, j2cj_param_generated_month, 1.5)) {
                r -= 7i32 // r跑到下个月则减1周
            }
        }
        return r
    }
}