package cn.huangdayu.almanac.dto;

import cn.huangdayu.almanac.utils.DateTimeUtils;
import cn.huangdayu.almanac.utils.PortUtils;
import cn.huangdayu.almanac.utils.SolarTermUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, String> toMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("日期", DateTimeUtils.dateFormat(getTimeZoneDTO().getCalendar(), "yyyy-MM-dd HH:mm:ss.SS"));
        map.put("星期", getTimeZoneDTO().getWeekName());
        map.put("地点", getTimeZoneDTO().getPosition());
        map.put("年号", getLunarDTO().getYearName());
        map.put("农历", getLunarDTO().getInfo());
        map.put("黄历", getEraDTO().getInfo());
        map.put("回历", getIslamicDTO().getInfo());
        map.put("儒略日", String.valueOf(getJulianDTO().getDays()));
        map.put("黄帝纪年", getLunarDTO().getKingChronologyName());
        map.put("生肖", getLunarDTO().getZodiac());
        map.put("节日", getHolidayDTO().getMajorDay());
        map.put("假日", getHolidayDTO().getHappyDay());
        map.put("其他节日", getHolidayDTO().getOtherDay());
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
        map.put("月相", getSunMoonDTO().getMoonPhaseInfo());
        map.put("月天数", String.valueOf(getLunarDTO().getDaysOfMonth()));
        map.put("闰月否", getLunarDTO().getLeapDesc());
        map.put("闰年否", String.valueOf(getLunarDTO().getLeapYear()));
        map.put("星座", getJulianDTO().getConstellation());
        map.put("当下节气", getSolarTermDTO().getName() + " " + getSolarTermDTO().getDateTime());
        map.put("最近节气", getSolarTermDTO().getNextOne().getName() + " " + getSolarTermDTO().getNextOne().getDateTime());
        map.put("春分", getSolarTermDTO().getByName("春分").getDateTime());
        map.put("夏至", getSolarTermDTO().getByName("夏至").getDateTime());
        map.put("秋分", getSolarTermDTO().getByName("秋分").getDateTime());
        map.put("冬至", getSolarTermDTO().getByName("冬至").getDateTime());
        return map;
    }
}
