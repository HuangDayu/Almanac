package cn.huangdayu.almanac.dto;

import cn.huangdayu.almanac.utils.DateTimeUtils;
import cn.huangdayu.almanac.utils.PortUtils;
import cn.huangdayu.almanac.utils.SolarTermUtils;

import java.util.List;

/**
 * 历模型类
 *
 * @author huangdayu
 * @update 2020-03-15
 */
public class AlmanacDTO {

    private LunarDTO lunarDTO;
    private EraDTO eraDTO;
    private GregorianDTO gregorianDTO;
    private HolidayDTO holidayDTO;
    private IslamicDTO islamicDTO;
    private JulianDTO julianDTO;
    private SolarTermDTO solarTermDTO;
    private SunMoonDTO sunMoonDTO;
    private TimeZoneDTO timeZoneDTO;

    public LunarDTO getLunarDTO() {
        return lunarDTO;
    }

    public void setLunarDTO(LunarDTO lunarDTO) {
        this.lunarDTO = lunarDTO;
    }

    public EraDTO getEraDTO() {
        return eraDTO;
    }

    public void setEraDTO(EraDTO eraDTO) {
        this.eraDTO = eraDTO;
    }

    public GregorianDTO getGregorianDTO() {
        return gregorianDTO;
    }

    public void setGregorianDTO(GregorianDTO gregorianDTO) {
        this.gregorianDTO = gregorianDTO;
    }

    public HolidayDTO getHolidayDTO() {
        return holidayDTO;
    }

    public void setHolidayDTO(HolidayDTO holidayDTO) {
        this.holidayDTO = holidayDTO;
    }

    public IslamicDTO getIslamicDTO() {
        return islamicDTO;
    }

    public void setIslamicDTO(IslamicDTO islamicDTO) {
        this.islamicDTO = islamicDTO;
    }

    public JulianDTO getJulianDTO() {
        return julianDTO;
    }

    public void setJulianDTO(JulianDTO julianDTO) {
        this.julianDTO = julianDTO;
    }

    public SolarTermDTO getSolarTermDTO() {
        return solarTermDTO;
    }

    public void setSolarTermDTO(SolarTermDTO solarTermDTO) {
        this.solarTermDTO = solarTermDTO;
    }

    public SunMoonDTO getSunMoonDTO() {
        return sunMoonDTO;
    }

    public void setSunMoonDTO(SunMoonDTO sunMoonDTO) {
        this.sunMoonDTO = sunMoonDTO;
    }

    public TimeZoneDTO getTimeZoneDTO() {
        return timeZoneDTO;
    }

    public void setTimeZoneDTO(TimeZoneDTO timeZoneDTO) {
        this.timeZoneDTO = timeZoneDTO;
    }
}
