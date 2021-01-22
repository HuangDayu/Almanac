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
    private String otherDay;
    /**
     * 放假日子(可用于日期数字置红)
     */
    private int flag;

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

    public String getOtherDay() {
        return otherDay;
    }

    public void setOtherDay(String otherDay) {
        this.otherDay = otherDay;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "HolidayDTO{" +
                "happyDay='" + happyDay + '\'' +
                ", majorDay='" + majorDay + '\'' +
                ", otherDay='" + otherDay + '\'' +
                ", flag=" + flag +
                '}';
    }
}
