package cn.huangdayu.almanac.aggregates.julian;

import cn.huangdayu.almanac.utils.CommonUtils;
import cn.huangdayu.almanac.utils.JulianCalendarUtils;
import lombok.Data;

/**
 * 儒略日
 *
 * @author huangdayu create at 2021/1/21 11:25
 */
@Data
public class Julian {

    public Julian() {
    }

    public Julian(int year, int month) {
        // FIXME 2021-01-22 不知为何减去西历两千年的儒略日
        // 这个月1号的儒略日,公历月首,中午
        this.firstJulianDayOfMonth = (int) (Math.floor(JulianCalendarUtils.getJulianDayNumber(year, month)) - CommonUtils.JULIAN_FOR_2000);
        int nextMonth = month + 1;
        int nextYear = year;
        if (nextMonth > 12) {
            nextMonth = nextMonth - 12;
            nextYear = year + 1;
        }
        // 这个月1号的儒略日和下个月1号的儒略日之差,月天数(公历)
        this.numberDayOfMonth = (int) (Math.floor(JulianCalendarUtils.getJulianDayNumber(nextYear, nextMonth)) - CommonUtils.JULIAN_FOR_2000 - firstJulianDayOfMonth);
        // 本月第一天的星期
        this.weekFirstDayOfMonth = (firstJulianDayOfMonth + CommonUtils.JULIAN_FOR_2000 + 1 + 7000000) % 7;
    }

    /**
     * 月的第一天的儒略日
     */
    private int firstJulianDayOfMonth;

    /**
     * 月份的天数
     */
    private int numberDayOfMonth;

    /**
     * 本月第一天的星期
     */
    private int weekFirstDayOfMonth;

    private Integer days;

    /**
     * 星座
     */
    private String constellation;

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
