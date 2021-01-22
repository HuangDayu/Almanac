package cn.huangdayu.almanac.dto;

/**
 * @author huangdayu create at 2021/1/21 10:51
 */
public class IslamicDTO {

    /**
     * 年(回历)
     */
    private int year;
    /**
     * 月(回历)
     */
    private int month;
    /**
     * 日(回历)
     */
    private int day;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getInfo() {
        return year + "年" + month + "月" + day + "日";
    }

    @Override
    public String toString() {
        return "IslamicDTO{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}
