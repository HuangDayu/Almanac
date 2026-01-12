package almanac.cn.huangdayu.almanac.utils

import std.math.MathExtension
import std.math.cos
import std.math.sin


/**
 * 天文算法工具类
 *
 * @author huangdayu
 * @update 2020-03-15
 */
public class AstronomicalUtils {
    public init() { }


    public static func gxc_sunLon(t: Float64): Float64 { // 太阳光行差,t是世纪数
        let v = -0.043126 + 628.301955 * t - 2.732E-6 * t * t // 平近点角
        let e = 0.016708634 - 4.2037E-5 * t - 1.267E-7 * t * t
        return (-20.49552 * (1.0 + e * cos(v))) / CommonUtils.RAD // 黄经光行差
    }

    /***
    * 月球经度光行差,误差0.07"
    * 
    * @param t
    * @return
    */
    public static func gxc_moonLon(t: Float64): Float64 {
        return -3.4E-6
    }

    /**
    * 地球经度计算,返回Date分点黄经,传入世纪数、取项数
    * 
    * @param t
    * @param n
    * @return
    */
    public static func E_Lon(t: Float64, n: Int32): Float64 {
        return CommonUtils.XL0_calc(0i32, 0i32, t, n)
    }

    public static func M_Lon(t: Float64, n: Int32): Float64 {
        return CommonUtils.XL1_calc(0i32, t, n)
    }



    /***
    * 地球速度,t是世纪数,误差小于万分3
    * 
    * @param t
    * @return
    */
    public static func E_v(t: Float64): Float64 {
        let f = 628.307585 * t
        let b = 628.332 + 21.0 * sin(1.527 + f) + 0.44 * sin(1.48 + f * 2.0) + 0.129 * sin(5.82 + f) * t + 5.5E-4 * sin(4.21 + f) * t * t
        return b
    }

    /***
    * 月球速度计算,传入世经数
    * 
    * @param t
    * @return
    */
    public static func M_v(t: Float64): Float64 {
        var v = 8399.71 - 914.0 * sin(0.7848 + 8328.691425 * t + 1.523E-4 * t * t) // 误差小于5%
        v -= 179.0 * sin(2.543 + 15542.7543 * t) // 误差小于0.3%
         + 160.0 * sin(0.1874 + 7214.0629 * t) + 62.0 * sin(3.14 + 16657.3828 * t) + 34.0 * sin(4.827 + 16866.9323 * t) + 22.0 * sin(4.9 + 23871.4457 * t) + 12.0 * sin(2.59 + 14914.4523 * t) + 7.0 * sin(0.23 + 6585.7609 * t) + 5.0 * sin(0.9 + 25195.624 * t) + 5.0 * sin(2.32 - 7700.3895 * t) + 5.0 * sin(3.88 + 8956.9934 * t) + 5.0 * sin(0.49 + 7771.3771 * t)
        return v
    }

    /***
    * 月日视黄经的差值
    * 
    * @param t
    * @param Mn
    * @param Sn
    * @return
    */
    public static func MS_aLon(t: Float64, Mn: Int32, Sn: Int32): Float64 {
        return M_Lon(t, Mn) + gxc_moonLon(t) - (E_Lon(t, Sn) + gxc_sunLon(t) + Float64.PI)
    }

    /***
    * 太阳视黄经
    * 
    * @param t
    * @param n
    * @return
    */
    public static func S_aLon(t: Float64, n: Int32): Float64 {
        return E_Lon(t, n) + CommonUtils.nutationLon2(t) + gxc_sunLon(t) + Float64.PI // 注意，这里的章动计算很耗时
    }

    static var Julian_Shijishu: Float64 = 0.0

    public static func getJulian_Shijishu(): Float64 {
        return Julian_Shijishu
    }

    public static func setJulian_Shijishu(julian_Shijishu: Float64): Unit {
        Julian_Shijishu = julian_Shijishu
    }

    /****
    * 已知月日视黄经差求时间
    * 
    * @param W
    * @return
    */
    public static func MS_aLon_t(W: Float64): Float64 {
        var t: Float64 = 0.0
        var v = 7771.37714500204
        t = (W + 1.08472) / v
        t += (W - MS_aLon(t, 3i32, 3i32)) / v
        setJulian_Shijishu(t)
        v = M_v(t) - E_v(t) // v的精度0.5%，详见原文
        t += (W - MS_aLon(t, 20i32, 10i32)) / v
        t += (W - MS_aLon(t, -1i32, 60i32)) / v
        return t
    }

    /***
    * 已知太阳视黄经反求时间
    * 
    * @param W
    * @return
    */
    public static func S_aLon_t(W: Float64): Float64 {
        var t: Float64 = 0.0
        var v = 628.3319653318
        t = (W - 1.75347 - Float64.PI) / v
        v = E_v(t) // v的精度0.03%，详见原文
        t += (W - S_aLon(t, 10i32)) / v
        v = E_v(t) // 再算一次v有助于提高精度,不算也可以
        t += (W - S_aLon(t, -1i32)) / v
        return t
    }

    /***
    * 已知月日视黄经差求时间,高速低精度,误差不超过600秒(只验算了几千年)
    * 
    * @param W
    * @return
    */
    public static func MS_aLon_t2(W: Float64): Float64 {
        var t: Float64 = 0.0
        var v = 7771.37714500204
        t = (W + 1.08472) / v
        let L: Float64
        let t2 = t * t
        t -= (-3.309E-5 * t2 + 0.10976 * cos(0.784758 + 8328.6914246 * t + 1.52292E-4 * t2) + 0.02224 * cos(0.1874 + 7214.0628654 * t - 2.1848E-4 * t2) - 0.03342 * cos(4.669257 + 628.307585 * t)) / v
        L = M_Lon(t, 20i32) - (4.8950632 + 628.3319653318 * t + 5.297E-6 * t * t + 0.0334166 * cos(4.669257 + 628.307585 * t) + 2.061E-4 * cos(2.67823 + 628.307585 * t) * t + 3.49E-4 * cos(4.6261 + 1256.61517 * t) - 20.5 / CommonUtils.RAD)
        v = 7771.38 - 914.0 * sin(0.7848 + 8328.691425 * t + 1.523E-4 * t * t) - 179.0 * sin(2.543 + 15542.7543 * t) - 160.0 * sin(0.1874 + 7214.0629 * t)
        t += (W - L) / v
        return t
    }

    /***
    * 已知太阳视黄经反求时间,高速低精度,最大误差不超过600秒
    * 
    * @param W
    * @return
    */
    public static func S_aLon_t2(W: Float64): Float64 {
        var t: Float64 = 0.0
        let v = 628.3319653318
        t = (W - 1.75347 - Float64.PI) / v
        t -= (5.297E-6 * t * t + 0.0334166 * cos(4.669257 + 628.307585 * t) + 2.061E-4 * cos(2.67823 + 628.307585 * t) * t) / v
        t += (W - E_Lon(t, 8i32) - Float64.PI + (20.5 + 17.2 * sin(2.1824 - 33.75705 * t)) / CommonUtils.RAD) / v
        return t
    }
}