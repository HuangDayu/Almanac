package almanac.cn.huangdayu.almanac.aggregates.astronomical

import almanac.cn.huangdayu.almanac.aggregates.AbstractAlmanac
import almanac.cn.huangdayu.almanac.utils.AstronomicalUtils
import almanac.cn.huangdayu.almanac.utils.CommonUtils
import std.math.MathExtension
import std.math.floor

/**
 * 天文历
 *
 * @author huangdayu create at 2021/2/17 20:35
 */
public class Astronomical <: BaseAlmanac {

    public init(firstJulianDayOfMonth: Int32) {
        super()
        // 计算世界时与原子时之差
        let jd2 = Float64(firstJulianDayOfMonth) + CommonUtils.dtT(Float64(firstJulianDayOfMonth)) - 8.0 / 24.0

        // 太阳视黄经
        this.solarRetina = AstronomicalUtils.S_aLon(jd2 / 36525.0, 3i32)
        this.solarRetina = Float64(Int32(floor((solarRetina - 0.13) / CommonUtils.PI_2 * 24.0))) * CommonUtils.PI_2 / 24.0

        // 月日视黄经
        this.lunarRetina = AstronomicalUtils.MS_aLon(jd2 / 36525.0, 10i32, 3i32)
        this.lunarRetina = Float64(Int32(floor((this.lunarRetina - 0.78) / Float64.PI * 2.0))) * Float64.PI / 2.0
    }

    /**
     * 太阳视黄经
     */
    private var solarRetina: Float64 = 0.0

    /**
     * 月视黄经
     */
    private var lunarRetina: Float64 = 0.0

    override public func getInfo(): ?String {
        return solarRetina.toString() + " " + lunarRetina.toString()
    }

    public func getSolarRetina(): Float64 {
        return solarRetina
    }

    public func setSolarRetina(solarRetina: Float64): Unit {
        this.solarRetina = solarRetina
    }

    public func getLunarRetina(): Float64 {
        return lunarRetina
    }

    public func setLunarRetina(lunarRetina: Float64): Unit {
        this.lunarRetina = lunarRetina
    }
}