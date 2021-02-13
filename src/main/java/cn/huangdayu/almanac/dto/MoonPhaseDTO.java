package cn.huangdayu.almanac.dto;

import java.util.List;

/**
 * @author huangdayu create at 2021/2/13 8:38
 */
public class MoonPhaseDTO {

    public MoonPhaseDTO(List<MoonPhaseDTO> next) {
        this.next = next;
    }

    private Integer index;
    /**
     * 月相名称
     */
    private String name;
    private Integer julianDay;
    private Integer afterDay;
    /**
     * 月相时间串
     */
    private String dateTime;
    private String desc;
    /**
     * 月相时刻(儒略日)
     */
    private double julianTime;

    private List<MoonPhaseDTO> next;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getJulianDay() {
        return julianDay;
    }

    public void setJulianDay(Integer julianDay) {
        this.julianDay = julianDay;
    }

    public Integer getAfterDay() {
        return afterDay;
    }

    public void setAfterDay(Integer afterDay) {
        this.afterDay = afterDay;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getJulianTime() {
        return julianTime;
    }

    public void setJulianTime(double julianTime) {
        this.julianTime = julianTime;
    }

    public List<MoonPhaseDTO> getNext() {
        return next;
    }

    public void setNext(List<MoonPhaseDTO> next) {
        this.next = next;
    }

    public String getInfo() {
        return name != null ? name + " " + dateTime : "无";
    }

    public String getDetails() {
        return name != null ? name + " " + dateTime + " 至今" + afterDay + "天" : "无";
    }

}
