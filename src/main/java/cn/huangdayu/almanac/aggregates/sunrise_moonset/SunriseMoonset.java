package cn.huangdayu.almanac.aggregates.sunrise_moonset;

import cn.huangdayu.almanac.aggregates.AbstractAlmanac;
import cn.huangdayu.almanac.dto.InfoDTO;
import cn.huangdayu.almanac.dto.TimeZoneDTO;
import cn.huangdayu.almanac.utils.SunMoonUtils;

import java.util.LinkedList;

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
    public InfoDTO getBaseInfo() {
        return new InfoDTO("日出日落", "sunriseMoonset", sunRiseTime + " " + sunSetTime);
    }

    @Override
    public LinkedList<InfoDTO> getAllInfo() {
        LinkedList<InfoDTO> list = new LinkedList<>();
        list.add(new InfoDTO("天亮", "dawn", dawnTime));
        list.add(new InfoDTO("日出", "sunRise", sunRiseTime));
        list.add(new InfoDTO("日中", "midDay", midDayTime));
        list.add(new InfoDTO("日落", "sunSet", sunSetTime));
        list.add(new InfoDTO("天黑", "dark", darkTime));
        list.add(new InfoDTO("月出", "moonRise", moonRiseTime));
        list.add(new InfoDTO("月中", "moonMiddle", moonMiddleTime));
        list.add(new InfoDTO("月落", "moonSet", moonSetTime));
        list.add(new InfoDTO("昼长", "diurnalTime", diurnalTime));
        list.add(new InfoDTO("夜长", "nightTime", nightTime));
        return list;
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
