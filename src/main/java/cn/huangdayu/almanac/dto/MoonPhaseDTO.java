package cn.huangdayu.almanac.dto;

import java.util.List;

/**
 * @author huangdayu create at 2021/2/13 8:38
 */
public class MoonPhaseDTO {

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

    private List<MoonPhaseDTO> moonPhaseDTOS;

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

    public List<MoonPhaseDTO> getMoonPhaseDTOS() {
        return moonPhaseDTOS;
    }

    public void setMoonPhaseDTOS(List<MoonPhaseDTO> moonPhaseDTOS) {
        this.moonPhaseDTOS = moonPhaseDTOS;
    }

    public String getInfo() {
        return name != null ? name + " " + dateTime : "无";
    }

}
