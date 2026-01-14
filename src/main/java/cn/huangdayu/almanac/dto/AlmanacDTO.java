package cn.huangdayu.almanac.dto;

import cn.huangdayu.almanac.aggregates.AbstractAlmanac;
import cn.huangdayu.almanac.aggregates.era.Era;
import cn.huangdayu.almanac.aggregates.holiday.Holiday;
import cn.huangdayu.almanac.aggregates.islamic.Islamic;
import cn.huangdayu.almanac.aggregates.julian.Julian;
import cn.huangdayu.almanac.aggregates.lunar.Lunar;
import cn.huangdayu.almanac.aggregates.moon_phase.MoonPhase;
import cn.huangdayu.almanac.aggregates.solar_term.SolarTerm;
import cn.huangdayu.almanac.aggregates.sunrise_moonset.SunriseMoonset;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 历模型类
 *
 * @author huangdayu
 * @update 2020-03-15
 */
public class AlmanacDTO {

    private final Lunar lunar;
    private final Era era;
    private final Holiday holiday;
    private final Islamic islamic;
    private final Julian julian;
    private final SolarTerm solarTerm;
    private final SunriseMoonset sunriseMoonset;
    private final TimeZoneDTO timeZoneDTO;
    private final MoonPhase moonPhase;
    private final LinkedList<AbstractAlmanac> allAlmanac = new LinkedList<>();

    public AlmanacDTO(TimeZoneDTO timeZoneDTO, Lunar lunar, Era era, Holiday holiday, Islamic islamic, Julian julian, SolarTerm solarTerm, SunriseMoonset sunriseMoonset, MoonPhase moonPhase) {
        this.lunar = lunar;
        this.era = era;
        this.holiday = holiday;
        this.islamic = islamic;
        this.julian = julian;
        this.solarTerm = solarTerm;
        this.sunriseMoonset = sunriseMoonset;
        this.timeZoneDTO = timeZoneDTO;
        this.moonPhase = moonPhase;
        allAlmanac.addAll(List.of(lunar, era, solarTerm, holiday, islamic, julian, sunriseMoonset, moonPhase));
    }

    public LinkedHashMap<String, String> getAllInfo() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        allAlmanac.forEach(allAlmanac -> {
            map.put(allAlmanac.getBaseInfo().getCnName(), allAlmanac.getBaseInfo().getValue());
            allAlmanac.getAllInfo().forEach(infoDTO -> map.put(infoDTO.getCnName(), infoDTO.getValue()));
        });
        return map;
    }

    public Lunar getLunar() {
        return lunar;
    }

    public Era getEra() {
        return era;
    }

    public Holiday getHoliday() {
        return holiday;
    }

    public Islamic getIslamic() {
        return islamic;
    }

    public Julian getJulian() {
        return julian;
    }

    public SolarTerm getSolarTerm() {
        return solarTerm;
    }

    public SunriseMoonset getSunriseMoonset() {
        return sunriseMoonset;
    }

    public TimeZoneDTO getTimeZoneDTO() {
        return timeZoneDTO;
    }

    public MoonPhase getMoonPhase() {
        return moonPhase;
    }

    public LinkedList<AbstractAlmanac> getAllAlmanac() {
        return allAlmanac;
    }
}
