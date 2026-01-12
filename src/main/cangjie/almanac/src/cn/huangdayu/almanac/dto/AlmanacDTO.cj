package almanac.cn.huangdayu.almanac.dto

import almanac.cn.huangdayu.almanac.aggregates.era.Era
import almanac.cn.huangdayu.almanac.aggregates.holiday.Holiday
import almanac.cn.huangdayu.almanac.aggregates.islamic.Islamic
import almanac.cn.huangdayu.almanac.aggregates.julian.Julian
import almanac.cn.huangdayu.almanac.aggregates.lunar.Lunar
import almanac.cn.huangdayu.almanac.aggregates.moon_phase.MoonPhase
import almanac.cn.huangdayu.almanac.aggregates.solar_term.SolarTerm
import almanac.cn.huangdayu.almanac.aggregates.sunrise_moonset.SunriseMoonset
import j2cjlib.utils.ValueBox
import UNSUPPORTED.java.util.LinkedHashMap
import std.collection.Map

/**
 * 历模型类
 *
 * @author huangdayu
 * @update 2020-03-15
 */
public class AlmanacDTO {

    private var lunar: ?Lunar = None
    private var era: ?Era = None
    private var holiday: ?Holiday = None
    private var islamic: ?Islamic = None
    private var julian: ?Julian = None
    private var solarTerm: ?SolarTerm = None
    private var sunriseMoonset: ?SunriseMoonset = None
    private var timeZoneDTO: ?TimeZoneDTO = None
    private var moonPhase: ?MoonPhase = None

    public init(lunar: ?Lunar, era: ?Era, holiday: ?Holiday, islamic: ?Islamic, julian: ?Julian, solarTerm: ?SolarTerm, sunriseMoonset: ?SunriseMoonset, timeZoneDTO: ?TimeZoneDTO, moonPhase: ?MoonPhase) {
        this.lunar = lunar
        this.era = era
        this.holiday = holiday
        this.islamic = islamic
        this.julian = julian
        this.solarTerm = solarTerm
        this.sunriseMoonset = sunriseMoonset
        this.timeZoneDTO = timeZoneDTO
        this.moonPhase = moonPhase
    }

    public func toMap(): ?Map<?String, ?String> {
        let map: Map<?String, ?String> = <-- Missing mapping for java.util.LinkedHashMap constructor: LinkedHashMap -->()
        map.put("地点", timeZoneDTO.getOrThrow().getPosition())
        map.put("西历", timeZoneDTO.getOrThrow().getInfo())
        map.put("年号", lunar.getOrThrow().getYearName())
        map.put("黄帝纪元", lunar.getOrThrow().getKingChronologyName())
        map.put("农历", lunar.getOrThrow().getInfo())
        map.put("黄历", era.getOrThrow().getInfo())
        map.put("节气", solarTerm.getOrThrow().getDetails())
        map.put("月相", moonPhase.getOrThrow().getDetails())
        map.put("儒略历", julian.getOrThrow().getInfo())
        map.put("回历", islamic.getOrThrow().getInfo())
        map.put("节假日", holiday.getOrThrow().getInfo())
        map.put("经度", timeZoneDTO.getOrThrow().getLongitude())
        map.put("纬度", timeZoneDTO.getOrThrow().getLatitude())
        map.put("时区", timeZoneDTO.getOrThrow().getTimeZone())
        map.put("港口", timeZoneDTO.getOrThrow().getPortName())
        map.put("昼长", sunriseMoonset.getOrThrow().getDiurnalTime())
        map.put("夜长", sunriseMoonset.getOrThrow().getNightTime())
        map.put("天亮", sunriseMoonset.getOrThrow().getDawnTime())
        map.put("日出", sunriseMoonset.getOrThrow().getSunRiseTime())
        map.put("中天", sunriseMoonset.getOrThrow().getMidDayTime())
        map.put("日落", sunriseMoonset.getOrThrow().getSunSetTime())
        map.put("天黑", sunriseMoonset.getOrThrow().getDarkTime())
        map.put("月出", sunriseMoonset.getOrThrow().getMoonRiseTime())
        map.put("月中", sunriseMoonset.getOrThrow().getMoonMiddleTime())
        map.put("月落", sunriseMoonset.getOrThrow().getMoonSetTime())
        map.put("月期", lunar.getOrThrow().getDaysOfMonth().toString())
        map.put("闰月", String.<-- Missing mapping for java.lang.String member: valueOf -->(ValueBox<Bool>(lunar.getOrThrow().getLeapMonth())))
        map.put("闰年", String.<-- Missing mapping for java.lang.String member: valueOf -->(ValueBox<Bool>(lunar.getOrThrow().getLeapYear())))
        return map
    }

    public func getLunar(): ?Lunar {
        return lunar
    }

    public func setLunar(lunar: ?Lunar): Unit {
        this.lunar = lunar
    }

    public func getEra(): ?Era {
        return era
    }

    public func setEra(era: ?Era): Unit {
        this.era = era
    }

    public func getHoliday(): ?Holiday {
        return holiday
    }

    public func setHoliday(holiday: ?Holiday): Unit {
        this.holiday = holiday
    }

    public func getIslamic(): ?Islamic {
        return islamic
    }

    public func setIslamic(islamic: ?Islamic): Unit {
        this.islamic = islamic
    }

    public func getJulian(): ?Julian {
        return julian
    }

    public func setJulian(julian: ?Julian): Unit {
        this.julian = julian
    }

    public func getSolarTerm(): ?SolarTerm {
        return solarTerm
    }

    public func setSolarTerm(solarTerm: ?SolarTerm): Unit {
        this.solarTerm = solarTerm
    }

    public func getSunriseMoonset(): ?SunriseMoonset {
        return sunriseMoonset
    }

    public func setSunriseMoonset(sunriseMoonset: ?SunriseMoonset): Unit {
        this.sunriseMoonset = sunriseMoonset
    }

    public func getTimeZoneDTO(): ?TimeZoneDTO {
        return timeZoneDTO
    }

    public func setTimeZoneDTO(timeZoneDTO: ?TimeZoneDTO): Unit {
        this.timeZoneDTO = timeZoneDTO
    }

    public func getMoonPhase(): ?MoonPhase {
        return moonPhase
    }

    public func setMoonPhase(moonPhase: ?MoonPhase): Unit {
        this.moonPhase = moonPhase
    }
}