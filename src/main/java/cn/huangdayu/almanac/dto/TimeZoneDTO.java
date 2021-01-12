package cn.huangdayu.almanac.dto;

import cn.huangdayu.almanac.utils.AreaUtils;
import cn.huangdayu.almanac.utils.DateTimeUtils;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;


/**
 * 时区模型类
 *
 * @author huangdayu
 * @update 2020-03-15
 */
public class TimeZoneDTO {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private int millisecond;
    private int week;
    private int timeZone;
    private String province;// 省份
    private String area;// 市/县/区
    private String address;// 位置结果
    private Calendar calendar;

    public TimeZoneDTO() {
    }

    public TimeZoneDTO(String province, String area, Instant instant) {
        this(province, area, DateTimeUtils.instantToCalendar(instant));
    }

    public TimeZoneDTO(String province, String area, String str) {
        this(province, area, DateTimeUtils.strToCalendar(str));
    }

    public TimeZoneDTO(String province, String area, String date, String time) {
        this(province, area, DateTimeUtils.strToCalendar(date + " " + time));
    }

    public TimeZoneDTO(String province, String area, Date date) {
        this(province, area, DateTimeUtils.dateToCalendar(date));
    }

    public TimeZoneDTO(String province, String area, long currentTimeMillis) {
        this(province, area, DateTimeUtils.timeInMillisToCalendar(currentTimeMillis));
    }

    public TimeZoneDTO(String province, String area, int year, int month, int day, int hourOfDay, int minute, int second) {
        this(province, area, DateTimeUtils.intToCalendar(year, month, day, hourOfDay, minute, second));
    }

    public TimeZoneDTO(String province, String area, int year, int month, int day, int hourOfDay, int minute, int second,
                       int millisecond) {
        this(province, area, DateTimeUtils.intToCalendar(year, month, day, hourOfDay, minute, second, millisecond));
    }

    public TimeZoneDTO(String address, Instant instant) {
        this(address, DateTimeUtils.instantToCalendar(instant));
    }


    public TimeZoneDTO(String address, String str) {
        this(address, DateTimeUtils.strToCalendar(str));
    }


    public TimeZoneDTO(String address, Date date) {
        this(address, DateTimeUtils.dateToCalendar(date));
    }

    public TimeZoneDTO(String address, long currentTimeMillis) {
        this(address, DateTimeUtils.timeInMillisToCalendar(currentTimeMillis));
    }

    public TimeZoneDTO(String address, int year, int month, int day, int hourOfDay, int minute, int second) {
        this(address, DateTimeUtils.intToCalendar(year, month, day, hourOfDay, minute, second));
    }

    public TimeZoneDTO(String address, int year, int month, int day, int hourOfDay, int minute, int second,
                       int millisecond) {
        this(address, DateTimeUtils.intToCalendar(year, month, day, hourOfDay, minute, second, millisecond));
    }

    public TimeZoneDTO(String... str) {
        this(str[0], str[1], DateTimeUtils.strToCalendar(str[2] + " " + str[3]));
    }

    public TimeZoneDTO(String[]... strs) {
        this(strs[0][0], strs[0][1], DateTimeUtils.strToCalendar(strs[0][2] + " " + strs[0][3]));
    }

    public TimeZoneDTO(String province, String area, Calendar calendar) {
        this(calendar, province, area);
    }

    public TimeZoneDTO(String address, Calendar calendar) {
        this(calendar, address);
    }

    public TimeZoneDTO(Calendar calendar, String... names) {
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH) + 1;
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
        this.week = calendar.get(Calendar.DAY_OF_WEEK);
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);
        this.millisecond = calendar.get(Calendar.MILLISECOND);
        this.calendar = calendar;
        if (names.length == 2) {
            this.province = names[0];
            this.area = names[1];
        } else if (names.length == 1) {
            String name = names[0];
            String[] nameList = name.trim().split("省|市|区|县|镇");
            if (nameList.length < 2) {
                this.province = "广东";
                this.area = "徐闻";
            } else if (nameList.length == 2) {
                this.province = nameList[0];
                this.area = nameList[1];
            } else {
                this.province = nameList[0];
                this.area = nameList[2];
            }
        }
        this.address = AreaUtils.judgeArea(this.province, this.area)[1];
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

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getMillisecond() {
        return millisecond;
    }

    public void setMillisecond(int millisecond) {
        this.millisecond = millisecond;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(int timeZone) {
        this.timeZone = timeZone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Calendar toCalendar() {
        return DateTimeUtils.intToCalendar(this.year, this.month, this.day, this.hour, this.minute, this.second, this.millisecond);
    }

}
