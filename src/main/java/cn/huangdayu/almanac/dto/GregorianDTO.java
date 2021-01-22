package cn.huangdayu.almanac.dto;

/**
 * @author huangdayu create at 2021/1/21 10:49
 */
public class GregorianDTO {
    /**
     * 所在公历月内日序数
     */
    private int dayIndexForMonth;
    /**
     * 所在公历年,同
     */
    private int year;
    /**
     * 所在公历月,同
     */
    private int month;
    /**
     * 日名称(公历)
     */
    private int day;
    /**
     * 所在公历月的总天数
     */
    private int daysOfMonth;
    /**
     * 所在月的月首的星期,同
     */
    private int weekFirstForMonth;
    /**
     * 星期
     */
    private int week;
    /**
     * 在本月中的周序号
     */
    private int weekIndexForMonth;
    /**
     * 本月的总周数
     */
    private int weeksOfMonth;

    public int getDayIndexForMonth() {
        return dayIndexForMonth;
    }

    public void setDayIndexForMonth(int dayIndexForMonth) {
        this.dayIndexForMonth = dayIndexForMonth;
    }

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

    public int getDaysOfMonth() {
        return daysOfMonth;
    }

    public void setDaysOfMonth(int daysOfMonth) {
        this.daysOfMonth = daysOfMonth;
    }

    public int getWeekFirstForMonth() {
        return weekFirstForMonth;
    }

    public void setWeekFirstForMonth(int weekFirstForMonth) {
        this.weekFirstForMonth = weekFirstForMonth;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeekIndexForMonth() {
        return weekIndexForMonth;
    }

    public void setWeekIndexForMonth(int weekIndexForMonth) {
        this.weekIndexForMonth = weekIndexForMonth;
    }

    public int getWeeksOfMonth() {
        return weeksOfMonth;
    }

    public void setWeeksOfMonth(int weeksOfMonth) {
        this.weeksOfMonth = weeksOfMonth;
    }

    @Override
    public String toString() {
        return "GregorianDTO{" +
                "dayIndexForMonth=" + dayIndexForMonth +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", daysOfMonth=" + daysOfMonth +
                ", weekFirstForMonth=" + weekFirstForMonth +
                ", week=" + week +
                ", weekIndexForMonth=" + weekIndexForMonth +
                ", weeksOfMonth=" + weeksOfMonth +
                '}';
    }
}
