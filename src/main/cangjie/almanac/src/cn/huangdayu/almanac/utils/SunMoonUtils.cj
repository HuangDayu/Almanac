package almanac.cn.huangdayu.almanac.utils

import almanac.cn.huangdayu.almanac.aggregates.sunrise_moonset.SunriseMoonset
import almanac.cn.huangdayu.almanac.dto.TimeZoneDTO
import UNSUPPORTED.java.lang.Math
import std.math.MathExtension
import std.math.abs
import std.math.acos
import std.math.asin
import std.math.atan
import std.math.ceil
import std.math.cos
import std.math.floor
import std.math.sin
import std.math.sqrt

/**
 * 日出月落工具类
 *
 * @author huangdayu
 * @update 2020-03-15
 */
public class SunMoonUtils {
    public init() { }

    private static var RAD = 180.0 * 3600.0 / Float64.PI

    private static var midDayTime: Float64 = 0.0

    private static var dawnTime: Float64 = 0.0

    private static var moonRise: Float64 = 0.0

    private static var moonSet: Float64 = 0.0


    public static func `init`(timeZoneDTO: ?TimeZoneDTO, sunriseMoonset: ?SunriseMoonset): Unit {
        sunTime(timeZoneDTO, sunriseMoonset)
        moonTime(timeZoneDTO, sunriseMoonset)
    }




    /**
     * 算出日出日落方法
     *
     * @param timeZoneDTO
     * @param sunriseMoonset
     */
    private static func sunTime(timeZoneDTO: ?TimeZoneDTO, sunriseMoonset: ?SunriseMoonset): Unit {

        let wd: ?Float64 = timeZoneDTO.getOrThrow().getLatitudeValue() / 180.0 * Float64.PI
        let jd: ?Float64 = -timeZoneDTO.getOrThrow().getLongitudeValue() / 180.0 * Float64.PI

        var richu = Float64(getJuLian_old(timeZoneDTO.getOrThrow().getYear(), timeZoneDTO.getOrThrow().getMonth(), timeZoneDTO.getOrThrow().getDay(), timeZoneDTO.getOrThrow().getHour(), timeZoneDTO.getOrThrow().getMinute(), timeZoneDTO.getOrThrow().getSecond()) - JulianCalendarUtils.getJuLianByYear(timeZoneDTO.getOrThrow().getYear())) // 2451545
        // 2451544.5

        var i = 0i32
        while (i < 10i32) {
            richu = sunRiseTime(richu, jd.getOrThrow(), wd.getOrThrow(), timeZoneDTO.getOrThrow().getIndex() / 24.0).getOrThrow() // 逐步逼近法算10次
            i++
        }

        // 日出
        sunriseMoonset.getOrThrow().setSunRiseTime(doubleToStr(richu))
        // 日落
        sunriseMoonset.getOrThrow().setSunSetTime(doubleToStr(midDayTime + midDayTime - richu))
        // 中天
        sunriseMoonset.getOrThrow().setMidDayTime(doubleToStr(midDayTime))
        // 天亮
        sunriseMoonset.getOrThrow().setDawnTime(doubleToStr(dawnTime))
        // 天黑
        sunriseMoonset.getOrThrow().setDarkTime(doubleToStr(midDayTime + midDayTime - dawnTime))
        // 昼长 = 日落-日出
        sunriseMoonset.getOrThrow().setDiurnalTime(doubleToStr((midDayTime - richu) * 2.0 - 0.5))
        // 夜长
        sunriseMoonset.getOrThrow().setNightTime(doubleToStr(24.0 - ((midDayTime - richu) * 2.0 - 0.5)))
    }


    /**
     * 太阳升起时间
     *
     * @param date 儒略日平午
     * @param lo   地理经度
     * @param la   地理纬度
     * @param tz   时区
     * @return 太阳升起时间
     */
    public static func sunRiseTime(date: Float64, lo: Float64, la: Float64, tz: Float64): ?Float64 {
        var j2cj_param_generated_date = date
        // System.out.println(lo+"--"+la);
        j2cj_param_generated_date = j2cj_param_generated_date - tz
        // 太阳黄经以及它的正余弦值
        let t = j2cj_param_generated_date / 36525.0
        let j = sunHJ(t).getOrThrow()
        // 太阳黄经以及它的正余弦值
        let sinJ = sin(j)
        let cosJ = cos(j)
        // 其中2*PI*(0.7790572732640 + 1.00273781191135448*jd)恒星时（子午圈位置）
        let gst = 2.0 * Float64.PI * (0.779057273264 + 1.00273781191135 * j2cj_param_generated_date) + (0.014506 + 4612.15739966 * t + 1.39667721 * t * t) / RAD
        let E = (84381.406 - 46.836769 * t) / RAD // 黄赤交角
        let a = atan(sinJ * cos(E) / cosJ) // '太阳赤经
        let D = asin(sin(E) * sinJ) // 太阳赤纬
        let cosH0 = (sin(Float64(-50i32 * 60i32) / RAD) - sin(la) * sin(D)) / (cos(la) * cos(D)) // 日出的时角计算，地平线下50分
        let cosH1 = (sin(Float64(-6i32 * 3600i32) / RAD) - sin(la) * sin(D)) / (cos(la) * cos(D)) // 天亮的时角计算，地平线下6度，若为航海请改为地平线下12度
        // 严格应当区分极昼极夜，本程序不算
        if (cosH0 >= 1.0 || cosH0 <= -1.0) {
            return -0.5 // 极昼
        }
        let H0 = -acos(cosH0) // 升点时角（日出）若去掉负号 就是降点时角，也可以利用中天和升点计算
        let H1 = -acos(cosH1)
        let H = gst - lo - a // 太阳时角
        midDayTime = j2cj_param_generated_date - degree(H).getOrThrow() / Float64.PI / 2.0 + tz // 中天时间
        dawnTime = j2cj_param_generated_date - degree(H - H1).getOrThrow() / Float64.PI / 2.0 + tz // 天亮时间
        return j2cj_param_generated_date - degree(H - H0).getOrThrow() / Float64.PI / 2.0 + tz // 日出时间，函数返回值
    }

    /**
     * 保证角度∈(-π,π)
     *
     * @param ag
     * @return ag
     */
    public static func degree(ag: Float64): ?Float64 {
        var j2cj_param_generated_ag = ag
        j2cj_param_generated_ag = mod(j2cj_param_generated_ag, 2.0 * Float64.PI).getOrThrow()
        return if (j2cj_param_generated_ag <= -Float64.PI) { j2cj_param_generated_ag + 2.0 * Float64.PI } else { if (j2cj_param_generated_ag > Float64.PI) { j2cj_param_generated_ag - 2.0 * Float64.PI } else { j2cj_param_generated_ag } }
    }

    public static func mod(num1: Float64, num2: Float64): ?Float64 {
        var j2cj_param_generated_num2 = num2
        j2cj_param_generated_num2 = abs(j2cj_param_generated_num2)
        // 只是取决于Num1的符号
        return if (num1 >= 0.0) { num1 - Float64(Int32(num1 / j2cj_param_generated_num2)) * j2cj_param_generated_num2 } else { Float64(Int32(abs(num1) / j2cj_param_generated_num2)) * j2cj_param_generated_num2 - abs(num1) }
    }

    /**
     * @param t 儒略世纪数
     * @return 太阳黄经
     */
    public static func sunHJ(t: Float64): ?Float64 {
        var j2cj_param_generated_t = t
        j2cj_param_generated_t = j2cj_param_generated_t + (32.0 * (j2cj_param_generated_t + 1.8) * (j2cj_param_generated_t + 1.8) - 20.0) / 86400.0 / 36525.0
        // 儒略世纪年数,力学时
        let j = 4.895062166E7 + 6.283319653318E9 * j2cj_param_generated_t + 53.0 * j2cj_param_generated_t * j2cj_param_generated_t - 994.0 + 334166.0 * cos(4.669257 + 628.307585 * j2cj_param_generated_t) + 3489.0 * cos(4.6261 + 1256.61517 * j2cj_param_generated_t) + 2060.6 * cos(2.67823 + 628.307585 * j2cj_param_generated_t) * j2cj_param_generated_t
        return j / 10000000.0
    }

    /**
     * 计算出儒略日 方法1 多出1
     *
     * @param y 年
     * @param M 月
     * @param d 日
     * @param h 小时
     * @param m 分
     * @param s 秒
     * @return int
     */
    public static func getJuLian_old(y: Int32, M: Int32, d: Int32, h: Int32, m: Int32, s: Int32): Int32 {
        var j2cj_param_generated_M = M
        var j2cj_param_generated_y = y
        var time = 0.0
        if (j2cj_param_generated_M <= 2i32) {
            j2cj_param_generated_M += 12i32
            j2cj_param_generated_y -= 1i32
        }
        if (j2cj_param_generated_y * 372i32 + j2cj_param_generated_M * 31i32 + d >= 588829i32) {
            time = Float64(j2cj_param_generated_y / 100i32)
            time = 2.0 - time + Float64(Int32(time / 4.0))
        }
        time += Float64(Int32(<-- No type mapping: java.lang.Math -->.<-- Missing mapping for java.lang.Math member: round -->(365.25 * (Float64(j2cj_param_generated_y + 4716i32)) + 0.01)) + Int32(30.60001 * (Float64(j2cj_param_generated_M + 1i32))) + d + (h * 3600i32 + m * 60i32 + s) / (24i32 * 3600i32)) - 1524.5
        return Int32(<-- No type mapping: java.lang.Math -->.<-- Missing mapping for java.lang.Math member: round -->(time))
    }

    /***
     * 公历转儒略日 方法2 少了0.5
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    private static func getJuLian_new(year: Int32, month: Int32, day: Float64): Float64 {
        var j2cj_param_generated_month = month
        var j2cj_param_generated_year = year
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
        return (floor(365.25 * (Float64(j2cj_param_generated_year + 4716i32))) + floor(30.6001 * (Float64(j2cj_param_generated_month + 1i32))) + day + Float64(n) - 1524.5) + 0.5
    }

    /***
     * 格式化成时间
     *
     * @param time
     * @return
     */
    public static func doubleToStr(time: Float64): ?String {
        var t = time + 0.5
        t = (t - Float64(Int32(t))) * 24.0
        let h = Int32(t)
        t = (t - Float64(h)) * 60.0
        let m = Int32(t)
        t = (t - Float64(m)) * 60.0
        let s = Int32(t)
        return (if (h < 10i32) { "0" + h.toString() } else { h.toString() }) + ":" + (if (m < 10i32) { "0" + m.toString() } else { m.toString() }) + ":" + (if (s < 10i32) { "0" + s.toString() } else { s.toString() })
    }

    /***
     * 格式化时间
     *
     * @param sum
     * @return
     */
    public static func timeToStr(sum: Float64): ?String {
        if (sum < 0.0) {
            return "--:--:--"
        }
        let h = Int32(sum)
        let m = Int32((sum - Float64(h)) * 60.0)
        let s = Int32((((sum - Float64(h)) * 60.0) - Float64(m)) * 60.0)
        return (if (h < 10i32) { "0" + h.toString() } else { h.toString() }) + ":" + (if (m < 10i32) { "0" + m.toString() } else { m.toString() }) + ":" + (if (s < 10i32) { "0" + s.toString() } else { s.toString() })
    }


    private static func moonTime(timeZoneDTO: ?TimeZoneDTO, sunriseMoonset: ?SunriseMoonset): Unit {
        let dbLon = CommonUtils.getTwoPointDouble(timeZoneDTO.getOrThrow().getLongitudeValue())
        let dbLat = CommonUtils.getTwoPointDouble(timeZoneDTO.getOrThrow().getLatitudeValue())
        let mjdd = mjd(timeZoneDTO.getOrThrow().getDay(), timeZoneDTO.getOrThrow().getMonth(), timeZoneDTO.getOrThrow().getYear(), 0i32)
        find_moonrise_set(mjdd, timeZoneDTO.getOrThrow().getIndex(), dbLon, dbLat, 0i32, 0i32, sunriseMoonset)
    }

    private static func find_moonrise_set(mjd: Float64, tz: Float64, glong: Float64, glat: Float64, dls: Int32, ST: Int32, sunriseMoonset: ?SunriseMoonset): Unit {
        let sglat: Float64
        let date: Float64
        var ym: Float64 = 0.0
        var yz: Float64 = 0.0
        var utrise = 0.0
        var utset = 0.0
        let sinho: Float64
        let cglat: Float64
        var xe: Float64 = 0.0
        var ye: Float64 = 0.0
        var yp: Float64 = 0.0
        var nz: Float64 = 0.0
        var hour: Float64 = 0.0
        var z1: Float64 = 0.0
        var z2: Float64 = 0.0
        let rads = 0.0174532925
        var rise: ?Bool = None
        var sett: ?Bool = None
        var above: ?Bool = None
        var quadout: ?Array<Float64> = Array<Float64>(5) { _ => 0.0 }

        sinho = sin(rads * 8.0 / 60.0) // moonrise taken as centre of moon at +8 arcmin
        sglat = sin(rads * glat)
        cglat = cos(rads * glat)
        date = mjd - tz / 24.0
        rise = false
        sett = false
        above = false
        hour = 1.0
        ym = sin_alt(1i32, date, hour - 1.0, glong, cglat, sglat) - sinho
        if (ym > 0.0) {
            above = true
        }
        while (hour < 25.0 && (sett.getOrThrow() == false || rise.getOrThrow() == false)) {
            yz = sin_alt(1i32, date, hour, glong, cglat, sglat) - sinho
            yp = sin_alt(1i32, date, hour + 1.0, glong, cglat, sglat) - sinho
            quadout = quad(ym, yz, yp)
            nz = quadout.getOrThrow()[0]
            z1 = quadout.getOrThrow()[1]
            z2 = quadout.getOrThrow()[2]
            xe = quadout.getOrThrow()[3]
            ye = quadout.getOrThrow()[4]
            // case when one event is found in the interval
            if (nz == 1.0) {
                if (ym < 0.0) {
                    utrise = hour + z1
                    rise = true
                } else {
                    utset = hour + z1
                    sett = true
                }
            } // end of nz = 1 case

            // case where two events are found in this interval
            // (rare but whole reason we are not using simple iteration)
            if (nz == 2.0) {
                if (ye < 0.0) {
                    utrise = hour + z2
                    utset = hour + z1
                } else {
                    utrise = hour + z1
                    utset = hour + z2
                }
            }

            // set up the next search interval
            ym = yp
            hour += 2.0
        } // end of while loop

        if (rise.getOrThrow() == true || sett.getOrThrow() == true) {
            if (rise.getOrThrow() == true) {
                if (ST == 0i32) {
                    moonRise = utrise + Float64(dls)
                } else {
                    moonRise = lmst(mjd + (utrise - tz) / 24.0, glong)
                }
            } else {
                moonRise = -1.0
            }
            if (sett.getOrThrow() == true) {
                if (ST == 0i32) {
                    moonSet = utset + Float64(dls)
                } else {
                    moonSet = lmst(mjd + (utset - tz) / 24.0, glong)
                }
            } else {
                moonSet = -1.0
            }
        } else {
            moonSet = -2.0
        }
        /***
         * 月出时间
         *
         * @return
         */
        sunriseMoonset.getOrThrow().setMoonRiseTime(timeToStr(moonRise))
        /***
         * 月落时间
         *
         * @return
         */
        sunriseMoonset.getOrThrow().setMoonSetTime(timeToStr(moonSet))
        /***
         * 月中时间
         *
         * @return
         */
        sunriseMoonset.getOrThrow().setMoonMiddleTime(timeToStr((moonSet + moonRise) / 2.0))
    }

    private static func lmst(mjd: Float64, glong: Float64): Float64 {
        var lst: Float64 = 0.0
        let t: Float64
        let d: Float64
        d = mjd - 51544.5
        t = d / 36525.0
        lst = range(280.46061837 + 360.98564736629 * d + 3.87933E-4 * t * t - t * t * t / 38710000.0)
        lst = lst / 15.0 + glong / 15.0
        if (lst >= 24.0) {
            lst -= 24.0
        }
        return (lst)
    }

    private static func range(x: Float64): Float64 {

        var a: Float64 = 0.0
        let b: Float64
        b = x / 360.0
        a = 360.0 * (b - ipart(b))
        if (a < 0.0) {
            a = a + 360.0
        }
        return a
    }

    private static func ipart(x: Float64): Float64 {

        let a: Float64
        if (x > 0.0) {
            a = floor(x)
        } else {
            a = ceil(x)
        }
        return a
    }

    private static func quad(ym: Float64, yz: Float64, yp: Float64): ?Array<Float64> {

        let a: Float64
        let b: Float64
        let c: Float64
        let dis: Float64
        let dx: Float64
        let xe: Float64
        let ye: Float64
        var z1 = 0.0
        var z2 = 0.0
        var nz: Float64 = 0.0
        let quadout: Array<Float64> = [0.0, 0.0, 0.0, 0.0, 0.0]

        nz = 0.0
        a = 0.5 * (ym + yp) - yz
        b = 0.5 * (yp - ym)
        c = yz
        xe = -b / (2.0 * a)
        ye = (a * xe + b) * xe + c
        dis = b * b - 4.0 * a * c
        if (dis > 0.0) {
            dx = 0.5 * sqrt(dis) / abs(a)
            z1 = xe - dx
            z2 = xe + dx
            if (abs(z1) <= 1.0) {
                nz += 1.0
            }
            if (abs(z2) <= 1.0) {
                nz += 1.0
            }
            if (z1 < -1.0) {
                z1 = z2
            }
        }
        quadout[0] = nz
        quadout[1] = z1
        quadout[2] = z2
        quadout[3] = xe
        quadout[4] = ye
        return quadout
    }

    private static func sin_alt(iobj: Int32, mjd0: Float64, hour: Float64, glong: Float64, cglat: Float64, sglat: Float64): Float64 {


        let mjd: Float64
        let t: Float64
        let ra: Float64
        let dec: Float64
        let tau: Float64
        let salt: Float64
        let rads = 0.0174532925
        var objpos: ?Array<Float64> = [0.0, 0.0]
        mjd = mjd0 + hour / 24.0
        t = (mjd - 51544.5) / 36525.0
        if (iobj == 1i32) {
            objpos = minimoon(t)
        }
        // else objpos = minisun(t);
        ra = objpos.getOrThrow()[0]
        dec = objpos.getOrThrow()[1]
        // hour angle of object
        tau = 15.0 * (lmst(mjd, glong) - ra)
        // sin(alt) of object using the conversion formulas
        salt = sglat * sin(rads * dec) + cglat * cos(rads * dec) * cos(rads * tau)
        return salt
    }

    private static func minimoon(t: Float64): ?Array<Float64> {

        let p2 = 6.283185307
        let arc = 206264.8062
        let coseps = 0.91748
        let sineps = 0.39778
        let L0: Float64
        let L: Float64
        let LS: Float64
        let F: Float64
        let D: Float64
        let H: Float64
        let S: Float64
        var N: Float64 = 0.0
        var DL: Float64 = 0.0
        let CB: Float64
        let L_moon: Float64
        let B_moon: Float64
        let V: Float64
        let W: Float64
        let X: Float64
        let Y: Float64
        let Z: Float64
        let RHO: Float64
        let dec: Float64
        var ra: Float64 = 0.0
        let mooneq: Array<Float64> = [0.0, 0.0]

        L0 = frac(0.606433 + 1336.855225 * t) // mean longitude of moon
        L = p2 * frac(0.374897 + 1325.55241 * t) // mean anomaly of Moon
        LS = p2 * frac(0.993133 + 99.997361 * t) // mean anomaly of Sun
        D = p2 * frac(0.827361 + 1236.853086 * t) // difference in longitude of moon and sun
        F = p2 * frac(0.259086 + 1342.227825 * t) // mean argument of latitude

        // corrections to mean longitude in arcsec
        DL = 22640.0 * sin(L)
        DL += -4586.0 * sin(L - 2.0 * D)
        DL += <-- Unary numeric promotion is not supported: +2370 --> * sin(2.0 * D)
        DL += <-- Unary numeric promotion is not supported: +769 --> * sin(2.0 * L)
        DL += -668.0 * sin(LS)
        DL += -412.0 * sin(2.0 * F)
        DL += -212.0 * sin(2.0 * L - 2.0 * D)
        DL += -206.0 * sin(L + LS - 2.0 * D)
        DL += <-- Unary numeric promotion is not supported: +192 --> * sin(L + 2.0 * D)
        DL += -165.0 * sin(LS - 2.0 * D)
        DL += -125.0 * sin(D)
        DL += -110.0 * sin(L + LS)
        DL += <-- Unary numeric promotion is not supported: +148 --> * sin(L - LS)
        DL += -55.0 * sin(2.0 * F - 2.0 * D)

        // simplified form of the latitude terms
        S = F + (DL + 412.0 * sin(2.0 * F) + 541.0 * sin(LS)) / arc
        H = F - 2.0 * D
        N = -526.0 * sin(H)
        N += <-- Unary numeric promotion is not supported: +44 --> * sin(L + H)
        N += -31.0 * sin(-L + H)
        N += -23.0 * sin(LS + H)
        N += <-- Unary numeric promotion is not supported: +11 --> * sin(-LS + H)
        N += -25.0 * sin(-2.0 * L + F)
        N += <-- Unary numeric promotion is not supported: +21 --> * sin(-L + F)

        // ecliptic long and lat of Moon in rads
        L_moon = p2 * frac(L0 + DL / 1296000.0)
        B_moon = (18520.0 * sin(S) + N) / arc

        // equatorial coord conversion - note fixed obliquity
        CB = cos(B_moon)
        X = CB * cos(L_moon)
        V = CB * sin(L_moon)
        W = sin(B_moon)
        Y = coseps * V - sineps * W
        Z = sineps * V + coseps * W
        RHO = sqrt(1.0 - Z * Z)
        dec = (360.0 / p2) * atan(Z / RHO)
        ra = (48.0 / p2) * atan(Y / (X + RHO))
        if (ra < 0.0) {
            ra += 24.0
        }
        mooneq[1] = dec
        mooneq[0] = ra
        return mooneq
    }

    private static func frac(x: Float64): Float64 {
        var j2cj_param_generated_x = x

        j2cj_param_generated_x -= Float64(Int32(j2cj_param_generated_x))
        return (if (j2cj_param_generated_x < 0.0) { j2cj_param_generated_x + 1.0 } else { j2cj_param_generated_x })
    }

    private static func mjd(day: Int32, month: Int32, year: Int32, hour: Int32): Float64 {
        var j2cj_param_generated_month = month
        var j2cj_param_generated_year = year

        var a: Float64 = 0.0
        let b: Float64
        if (j2cj_param_generated_month <= 2i32) {
            j2cj_param_generated_month = j2cj_param_generated_month + 12i32
            j2cj_param_generated_year = j2cj_param_generated_year - 1i32
        }
        a = 10000.0 * Float64(j2cj_param_generated_year) + 100.0 * Float64(j2cj_param_generated_month) + Float64(day)
        if (a <= 1.58210041E7) {
            b = -2.0 * floor(Float64((j2cj_param_generated_year + 4716i32) / 4i32)) - 1179.0
        } else {
            b = floor(Float64(j2cj_param_generated_year / 400i32)) - floor(Float64(j2cj_param_generated_year / 100i32)) + floor(Float64(j2cj_param_generated_year / 4i32))
        }
        a = 365.0 * Float64(j2cj_param_generated_year) - 679004.0
        return (a + b + floor(30.6001 * (Float64(j2cj_param_generated_month + 1i32))) + Float64(day) + Float64(hour) / 24.0)
    }
}