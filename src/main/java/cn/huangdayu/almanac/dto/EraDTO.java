package cn.huangdayu.almanac.dto;

/**
 * 黄历，天干地支，以 [立春]  作为新年的第一天
 *
 * @author huangdayu create at 2021/1/21 11:00
 */
public class EraDTO {

    /**
     * 干支年
     */
    private String year;
    /**
     * 干支月
     */
    private String month;
    /**
     * 干支日
     */
    private String day;
    /**
     * 干支时
     */
    private String time;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInfo() {
        return year + "年" + month + "月" + day + "日" + time + "时";
    }

    @Override
    public String toString() {
        return "EraDTO{" +
                "year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
