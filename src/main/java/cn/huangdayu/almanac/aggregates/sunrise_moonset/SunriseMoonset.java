package cn.huangdayu.almanac.aggregates.sunrise_moonset;

import cn.huangdayu.almanac.aggregates.AbstractAlmanac;
import cn.huangdayu.almanac.dto.TimeZoneDTO;
import cn.huangdayu.almanac.utils.SunMoonUtils;

/**
 * @author huangdayu create at 2021/1/21 11:08
 */
public class SunriseMoonset extends AbstractAlmanac {

    public SunriseMoonset(TimeZoneDTO timeZoneDTO) {
        SunMoonUtils.init(timeZoneDTO, this);
    }
    /**
     * 日出
     */
    private String sunRiseTime;
    /**
     * 日落
     */
    private String sunSetTime;
    /**
     * 中天
     */
    private String midDayTime;
    /**
     * 天亮
     */
    private String dawnTime;
    /**
     * 天黑
     */
    private String darkTime;
    /**
     * 昼长
     */
    private String diurnalTime;
    /**
     * 夜长
     */
    private String nightTime;
    /**
     * 月出
     */
    private String moonRiseTime;
    /**
     * 月落
     */
    private String moonSetTime;
    /**
     * 月中
     */
    private String moonMiddleTime;

    @Override
    public String getInfo() {
        return sunRiseTime + " " + sunSetTime;
    }

    public String getSunRiseTime() {
        return sunRiseTime;
    }

    public void setSunRiseTime(String sunRiseTime) {
        this.sunRiseTime = sunRiseTime;
    }

    public String getSunSetTime() {
        return sunSetTime;
    }

    public void setSunSetTime(String sunSetTime) {
        this.sunSetTime = sunSetTime;
    }

    public String getMidDayTime() {
        return midDayTime;
    }

    public void setMidDayTime(String midDayTime) {
        this.midDayTime = midDayTime;
    }

    public String getDawnTime() {
        return dawnTime;
    }

    public void setDawnTime(String dawnTime) {
        this.dawnTime = dawnTime;
    }

    public String getDarkTime() {
        return darkTime;
    }

    public void setDarkTime(String darkTime) {
        this.darkTime = darkTime;
    }

    public String getDiurnalTime() {
        return diurnalTime;
    }

    public void setDiurnalTime(String diurnalTime) {
        this.diurnalTime = diurnalTime;
    }

    public String getNightTime() {
        return nightTime;
    }

    public void setNightTime(String nightTime) {
        this.nightTime = nightTime;
    }

    public String getMoonRiseTime() {
        return moonRiseTime;
    }

    public void setMoonRiseTime(String moonRiseTime) {
        this.moonRiseTime = moonRiseTime;
    }

    public String getMoonSetTime() {
        return moonSetTime;
    }

    public void setMoonSetTime(String moonSetTime) {
        this.moonSetTime = moonSetTime;
    }

    public String getMoonMiddleTime() {
        return moonMiddleTime;
    }

    public void setMoonMiddleTime(String moonMiddleTime) {
        this.moonMiddleTime = moonMiddleTime;
    }
}
