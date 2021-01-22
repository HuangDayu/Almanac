package cn.huangdayu.almanac.dto;

/**
 * @author huangdayu create at 2021/1/21 11:11
 */
public class HolidayDTO {

    /**
     * 重要喜庆日子名称(可将日子名称置红)
     */
    private String happyDay;
    /**
     * 重要日子名称
     */
    private String majorDay;
    /**
     * 各种日子名称(连成一大串)
     */
    private String allDay;
    /**
     * 放假日子(可用于日期数字置红)
     */
    private int holidayDay;

    public String getHappyDay() {
        return happyDay;
    }

    public void setHappyDay(String happyDay) {
        this.happyDay = happyDay;
    }

    public String getMajorDay() {
        return majorDay;
    }

    public void setMajorDay(String majorDay) {
        this.majorDay = majorDay;
    }

    public String getAllDay() {
        return allDay;
    }

    public void setAllDay(String allDay) {
        this.allDay = allDay;
    }

    public int getHolidayDay() {
        return holidayDay;
    }

    public void setHolidayDay(int holidayDay) {
        this.holidayDay = holidayDay;
    }

    @Override
    public String toString() {
        return "HolidayDTO{" +
                "happyDay='" + happyDay + '\'' +
                ", majorDay='" + majorDay + '\'' +
                ", allDay='" + allDay + '\'' +
                ", holidayDay=" + holidayDay +
                '}';
    }
}
