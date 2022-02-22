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

    public Lunar getLunarDTO() {
        return lunar;
    }

    public void setLunarDTO(Lunar lunar) {
        this.lunar = lunar;
    }

    public Era getEraDTO() {
        return era;
    }

    public void setEraDTO(Era era) {
        this.era = era;
    }

    public Holiday getHolidayDTO() {
        return holiday;
    }

    public void setHolidayDTO(Holiday holiday) {
        this.holiday = holiday;
    }

    public Islamic getIslamicDTO() {
        return islamic;
    }

    public void setIslamicDTO(Islamic islamic) {
        this.islamic = islamic;
    }

    public Julian getJulianDTO() {
        return julian;
    }

    public void setJulianDTO(Julian julian) {
        this.julian = julian;
    }

    public SolarTerm getSolarTermDTO() {
        return solarTerm;
    }

    public void setSolarTermDTO(SolarTerm solarTerm) {
        this.solarTerm = solarTerm;
    }

    public SunriseMoonset getSunMoonDTO() {
        return sunriseMoonset;
    }

    public void setSunMoonDTO(SunriseMoonset sunriseMoonset) {
        this.sunriseMoonset = sunriseMoonset;
    }

    public TimeZoneDTO getTimeZoneDTO() {
        return timeZoneDTO;
    }

    public void setTimeZoneDTO(TimeZoneDTO timeZoneDTO) {
        this.timeZoneDTO = timeZoneDTO;
    }

    public MoonPhase getMoonPhaseDTO() {
        return moonPhase;
    }

    public void setMoonPhaseDTO(MoonPhase moonPhase) {
        this.moonPhase = moonPhase;
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("地点", getTimeZoneDTO().getPosition());
        map.put("西历", getTimeZoneDTO().getInfo());
        map.put("年号", getLunarDTO().getYearName());
        map.put("农历", getLunarDTO().getInfo());
        map.put("黄历", getEraDTO().getInfo());
        map.put("节气", getSolarTermDTO().getInfo());
        map.put("黄帝纪元", getLunarDTO().getKingChronologyName());
        map.put("儒略历", getJulianDTO().getInfo());
        map.put("回历", getIslamicDTO().getInfo());
        map.put("节假日", getHolidayDTO().getInfo());
        map.put("经度", getSunMoonDTO().getLongitude());
        map.put("纬度", getSunMoonDTO().getLatitude());
        map.put("时区", getTimeZoneDTO().getTimeZone());
        map.put("港口", getSunMoonDTO().getPortName());
        map.put("昼长", getSunMoonDTO().getDiurnalTime());
        map.put("夜长", getSunMoonDTO().getNightTime());
        map.put("天亮", getSunMoonDTO().getDawnTime());
        map.put("日出", getSunMoonDTO().getSunRiseTime());
        map.put("中天", getSunMoonDTO().getMidDayTime());
        map.put("日落", getSunMoonDTO().getSunSetTime());
        map.put("天黑", getSunMoonDTO().getDarkTime());
        map.put("月出", getSunMoonDTO().getMoonRiseTime());
        map.put("月中", getSunMoonDTO().getMoonMiddleTime());
        map.put("月落", getSunMoonDTO().getMoonSetTime());
        map.put("月相", getMoonPhaseDTO().getInfo());
        map.put("月期", String.valueOf(getLunarDTO().getDaysOfMonth()));
        map.put("闰月", String.valueOf(getLunarDTO().getLeapMonth()));
        map.put("闰年", String.valueOf(getLunarDTO().getLeapYear()));
        return map;
    }
}
