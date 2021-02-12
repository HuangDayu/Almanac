package cn.huangdayu.almanac.dto;

/**
 * @author huangdayu create at 2021/1/21 11:25
 */
public class JulianDTO {

    private Integer days;

    /**
     * 星座
     */
    private String constellation;

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getInfo() {
        return getDays() + " " + getConstellation();
    }

    @Override
    public String toString() {
        return "JulianDTO{" +
                "days=" + days +
                ", constellation='" + constellation + '\'' +
                '}';
    }
}
