package cn.huangdayu.almanac.dto;

import cn.huangdayu.almanac.aggregates.era.Era;
import cn.huangdayu.almanac.aggregates.holiday.Holiday;
import cn.huangdayu.almanac.aggregates.islamic.Islamic;
import cn.huangdayu.almanac.aggregates.julian.Julian;
import cn.huangdayu.almanac.aggregates.lunar.Lunar;
import cn.huangdayu.almanac.aggregates.moon_phase.MoonPhase;
import cn.huangdayu.almanac.aggregates.solar_term.SolarTerm;
import cn.huangdayu.almanac.aggregates.sunrise_moonset.SunriseMoonset;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 历模型类
 *
 * @author huangdayu
 * @update 2020-03-15
 */
public class AlmanacDTO {

    private Lunar lunar;
    private Era era;
    private Holiday holiday;
    private Islamic islamic;
    private Julian julian;
    private SolarTerm solarTerm;
    private SunriseMoonset sunriseMoonset;
    private TimeZoneDTO timeZoneDTO;
    private MoonPhase moonPhase;

    public AlmanacDTO(Lunar lunar, Era era, Holiday holiday, Islamic islamic, Julian julian, SolarTerm solarTerm, SunriseMoonset sunriseMoonset, TimeZoneDTO timeZoneDTO, MoonPhase moonPhase) {
        this.lunar = lunar;
        this.era = era;
        this.holiday = holiday;
        this.islamic = islamic;
        this.julian = julian;
        this.solarTerm = solarTerm;
        this.sunriseMoonset = sunriseMoonset;
        this.timeZoneDTO = timeZoneDTO;
        this.moonPhase = moonPhase;
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("地点", timeZoneDTO.getCoordinates().getPosition());
        map.put("西历", timeZoneDTO.getInfo());
        map.put("年号", lunar.getYearName());
        map.put("黄帝纪元", lunar.getKingChronologyName());
        map.put("农历", lunar.getInfo());
        map.put("黄历", era.getInfo());
        map.put("节气", solarTerm.getDetails());
        map.put("月相", moonPhase.getDetails());
        map.put("儒略历", julian.getInfo());
        map.put("回历", islamic.getInfo());
        map.put("节假日", holiday.getInfo());
        map.put("经度", timeZoneDTO.getCoordinates().getLongitudeStr());
        map.put("纬度", timeZoneDTO.getCoordinates().getLatitudeStr());
        map.put("时区", timeZoneDTO.getTimeZone());
        map.put("港口", timeZoneDTO.getCoordinates().getPortName());
        map.put("昼长", sunriseMoonset.getDiurnalTime());
        map.put("夜长", sunriseMoonset.getNightTime());
        map.put("天亮", sunriseMoonset.getDawnTime());
        map.put("日出", sunriseMoonset.getSunRiseTime());
        map.put("中天", sunriseMoonset.getMidDayTime());
        map.put("日落", sunriseMoonset.getSunSetTime());
        map.put("天黑", sunriseMoonset.getDarkTime());
        map.put("月出", sunriseMoonset.getMoonRiseTime());
        map.put("月中", sunriseMoonset.getMoonMiddleTime());
        map.put("月落", sunriseMoonset.getMoonSetTime());
        map.put("月期", String.valueOf(lunar.getDaysOfMonth()));
        map.put("闰月", String.valueOf(lunar.getLeapMonth()));
        map.put("闰年", String.valueOf(lunar.getLeapYear()));
        return map;
    }

    public Lunar getLunar() {
        return lunar;
    }

    public void setLunar(Lunar lunar) {
        this.lunar = lunar;
    }

    public Era getEra() {
        return era;
    }

    public void setEra(Era era) {
        this.era = era;
    }

    public Holiday getHoliday() {
        return holiday;
    }

    public void setHoliday(Holiday holiday) {
        this.holiday = holiday;
    }

    public Islamic getIslamic() {
        return islamic;
    }

    public void setIslamic(Islamic islamic) {
        this.islamic = islamic;
    }

    public Julian getJulian() {
        return julian;
    }

    public void setJulian(Julian julian) {
        this.julian = julian;
    }

    public SolarTerm getSolarTerm() {
        return solarTerm;
    }

    public void setSolarTerm(SolarTerm solarTerm) {
        this.solarTerm = solarTerm;
    }

    public SunriseMoonset getSunriseMoonset() {
        return sunriseMoonset;
    }

    public void setSunriseMoonset(SunriseMoonset sunriseMoonset) {
        this.sunriseMoonset = sunriseMoonset;
    }

    public TimeZoneDTO getTimeZoneDTO() {
        return timeZoneDTO;
    }

    public void setTimeZoneDTO(TimeZoneDTO timeZoneDTO) {
        this.timeZoneDTO = timeZoneDTO;
    }

    public MoonPhase getMoonPhase() {
        return moonPhase;
    }

    public void setMoonPhase(MoonPhase moonPhase) {
        this.moonPhase = moonPhase;
    }
}
