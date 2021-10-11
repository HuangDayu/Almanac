package cn.huangdayu.almanac.aggregates.solar_term;

import java.util.List;

/**
 * @author huangdayu create at 2021/1/22 11:37
 */
public class SolarTerm {

    private Integer index;
    private String name;
    private Integer julianDay;
    private Integer afterDay;
    private String dateTime;
    private String desc;
    /**
     * 节气时刻(儒略日)
     */
    private double julianTime;

    private List<SolarTerm> next;

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

    public List<SolarTerm> getNext() {
        return next;
    }

    public void setNext(List<SolarTerm> next) {
        this.next = next;
    }

    public double getJulianTime() {
        return julianTime;
    }

    public void setJulianTime(double julianTime) {
        this.julianTime = julianTime;
    }

    public SolarTerm getNextOne() {
        for (SolarTerm solarTerm : next) {
            if (solarTerm.getAfterDay() > 0) {
                // 列表中，第一个大于0的则是下一个节气
                return solarTerm;
            }
        }
        return null;
    }

    public SolarTerm getByName(String name) {
        for (SolarTerm solarTerm : next) {
            if (solarTerm.getName().equalsIgnoreCase(name)) {
                return solarTerm;
            }
        }
        return null;
    }

    public String getDetails() {
        return name + " " + dateTime + (afterDay != 0 ? " 至今" + afterDay + "天" : " 今天");
    }

    public String getInfo() {
        return name != null ? getDetails() : getNextOne().getDetails();
    }


    @Override
    public String toString() {
        return "SolarTermDTO{" +
                "index=" + index +
                ", name='" + name + '\'' +
                ", julianDay=" + julianDay +
                ", afterDay=" + afterDay +
                ", date='" + dateTime + '\'' +
                ", julianTime=" + julianTime +
                '}';
    }
}
