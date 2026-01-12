package almanac.cn.huangdayu.almanac.aggregates.sunrise_moonset

import almanac.cn.huangdayu.almanac.aggregates.AbstractAlmanac
import almanac.cn.huangdayu.almanac.dto.TimeZoneDTO
import almanac.cn.huangdayu.almanac.utils.SunMoonUtils

/**
 * @author huangdayu create at 2021/1/21 11:08
 */
public class SunriseMoonset <: BaseAlmanac {

    public init(timeZoneDTO: ?TimeZoneDTO) {
        super()
        SunMoonUtils.`init`(timeZoneDTO, this)
    }
    /**
     * 日出
     */
    private var sunRiseTime: ?String = None
    /**
     * 日落
     */
    private var sunSetTime: ?String = None
    /**
     * 中天
     */
    private var midDayTime: ?String = None
    /**
     * 天亮
     */
    private var dawnTime: ?String = None
    /**
     * 天黑
     */
    private var darkTime: ?String = None
    /**
     * 昼长
     */
    private var diurnalTime: ?String = None
    /**
     * 夜长
     */
    private var nightTime: ?String = None
    /**
     * 月出
     */
    private var moonRiseTime: ?String = None
    /**
     * 月落
     */
    private var moonSetTime: ?String = None
    /**
     * 月中
     */
    private var moonMiddleTime: ?String = None

    override public func getInfo(): ?String {
        return (sunRiseTime ?? "null") + " " + (sunSetTime ?? "null")
    }

    public func getSunRiseTime(): ?String {
        return sunRiseTime
    }

    public func setSunRiseTime(sunRiseTime: ?String): Unit {
        this.sunRiseTime = sunRiseTime
    }

    public func getSunSetTime(): ?String {
        return sunSetTime
    }

    public func setSunSetTime(sunSetTime: ?String): Unit {
        this.sunSetTime = sunSetTime
    }

    public func getMidDayTime(): ?String {
        return midDayTime
    }

    public func setMidDayTime(midDayTime: ?String): Unit {
        this.midDayTime = midDayTime
    }

    public func getDawnTime(): ?String {
        return dawnTime
    }

    public func setDawnTime(dawnTime: ?String): Unit {
        this.dawnTime = dawnTime
    }

    public func getDarkTime(): ?String {
        return darkTime
    }

    public func setDarkTime(darkTime: ?String): Unit {
        this.darkTime = darkTime
    }

    public func getDiurnalTime(): ?String {
        return diurnalTime
    }

    public func setDiurnalTime(diurnalTime: ?String): Unit {
        this.diurnalTime = diurnalTime
    }

    public func getNightTime(): ?String {
        return nightTime
    }

    public func setNightTime(nightTime: ?String): Unit {
        this.nightTime = nightTime
    }

    public func getMoonRiseTime(): ?String {
        return moonRiseTime
    }

    public func setMoonRiseTime(moonRiseTime: ?String): Unit {
        this.moonRiseTime = moonRiseTime
    }

    public func getMoonSetTime(): ?String {
        return moonSetTime
    }

    public func setMoonSetTime(moonSetTime: ?String): Unit {
        this.moonSetTime = moonSetTime
    }

    public func getMoonMiddleTime(): ?String {
        return moonMiddleTime
    }

    public func setMoonMiddleTime(moonMiddleTime: ?String): Unit {
        this.moonMiddleTime = moonMiddleTime
    }
}