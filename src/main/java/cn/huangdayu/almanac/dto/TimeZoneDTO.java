package cn.huangdayu.almanac.dto;

import cn.huangdayu.almanac.exception.AlmanacException;
import cn.huangdayu.almanac.utils.AreaUtils;
import cn.huangdayu.almanac.utils.ConstantsUtils;
import cn.huangdayu.almanac.utils.DateTimeUtils;
import cn.huangdayu.almanac.utils.JulianCalendarUtils;

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
    private String timeZone;
    /**
     * 省份
     */
    private String province;
    /**
     * 市/县/区
     */
    private String area;
    /**
     * 位置
     */
    private String address;
    /**
     * BUG 2021-01-23 因使用该对象，导致时间无法进入公元前
     */
    private Calendar calendar;
    private Integer julianDay;
    private String position;
    /**
     * 星期
     * TODO 或许可以考虑用上面的week变量
     */
    private String weekName;

    private TimeZoneDTO() {
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

    public TimeZoneDTO(int year, int month, int day, int hourOfDay, int minute, int second,
                       int millisecond) {
        this(DateTimeUtils.intToCalendar(year, month, day, hourOfDay, minute, second, millisecond));
    }

    public TimeZoneDTO(String province, String area, Calendar calendar) {
        this(calendar, province, area);
    }

    public TimeZoneDTO(String address, Calendar calendar) {
        this(calendar, address);
    }

    public TimeZoneDTO(TimeZoneDTO timeZoneDTO, int day, int julianDay) {
        try {
            this.calendar = DateTimeUtils.intToCalendar(timeZoneDTO.getYear(), timeZoneDTO.getMonth(), day, timeZoneDTO.getHour(), timeZoneDTO.getMinute(), timeZoneDTO.getSecond(), timeZoneDTO.getMillisecond());
            this.year = this.calendar.get(Calendar.YEAR);
            this.month = this.calendar.get(Calendar.MONTH) + 1;
            this.day = this.calendar.get(Calendar.DAY_OF_MONTH);
            this.week = this.calendar.get(Calendar.DAY_OF_WEEK);
            this.hour = this.calendar.get(Calendar.HOUR_OF_DAY);
            this.minute = this.calendar.get(Calendar.MINUTE);
            this.second = this.calendar.get(Calendar.SECOND);
            this.millisecond = this.calendar.get(Calendar.MILLISECOND);
            this.address = timeZoneDTO.getAddress();
            this.julianDay = julianDay;
            this.timeZone = timeZoneDTO.getTimeZone();
            this.position = timeZoneDTO.getPosition();
            this.weekName = DateTimeUtils.getWeek(calendar);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AlmanacException("时间与地址构造异常", e);
        }
    }

    public TimeZoneDTO(Calendar calendar, String... names) {
        try {
            this.year = calendar.get(Calendar.YEAR);
            this.month = calendar.get(Calendar.MONTH) + 1;
            this.day = calendar.get(Calendar.DAY_OF_MONTH);
            this.week = calendar.get(Calendar.DAY_OF_WEEK);
            this.hour = calendar.get(Calendar.HOUR_OF_DAY);
            this.minute = calendar.get(Calendar.MINUTE);
            this.second = calendar.get(Calendar.SECOND);
            this.millisecond = calendar.get(Calendar.MILLISECOND);
            this.calendar = calendar;
            if (names != null && names.length > 0) {
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
            } else {
                this.province = "广东";
                this.area = "徐闻";
            }
            this.address = AreaUtils.judgeArea(this.province, this.area)[1];
            this.julianDay = JulianCalendarUtils.getJuLian(this.year, this.month, this.day);
            // +0800
            String format = DateTimeUtils.formatDateByFormat(calendar, "Z");
            String value = format.substring(1, 3);
            int i = 1, j = Integer.parseInt(value);
            if (j > 0) {
                i = Integer.parseInt(value);
            } else {
                i = Integer.parseInt(value.substring(1));
            }
            if (format.contains("-")) {
                this.timeZone = format + " 西" + ConstantsUtils.TIMEZONE[i - 1] + "区";
            } else {
                this.timeZone = format + " 东" + ConstantsUtils.TIMEZONE[i - 1] + "区";
            }
            this.position = (province.replaceAll("省", "") + " " + area.replaceAll("市", "").replaceAll("区", "").replaceAll("县", "").replaceAll("镇", "")
                    .replaceAll("乡", ""));
            this.weekName = DateTimeUtils.getWeek(calendar);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AlmanacException("时间与地址构造异常", e);
        }
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

    public Integer getJulianDay() {
        return julianDay;
    }

    public void setJulianDay(Integer julianDay) {
        this.julianDay = julianDay;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getWeekName() {
        return weekName;
    }

    public void setWeekName(String weekName) {
        this.weekName = weekName;
    }

    public String getDateTime() {
        return year + "-" + add0(month) + "-" + add0(day) + " " + add0(hour) + ":" + add0(minute) + ":" + add0(second);
    }

    private String add0(int value) {
        return value < 10 ? "0" + value : "" + value;
    }

    @Override
    public String toString() {
        return "TimeZoneDTO{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", minute=" + minute +
                ", second=" + second +
                ", millisecond=" + millisecond +
                ", week=" + week +
                ", timeZone='" + timeZone + '\'' +
                ", province='" + province + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", calendar=" + calendar +
                ", julianDay=" + julianDay +
                ", position='" + position + '\'' +
                ", weekName='" + weekName + '\'' +
                '}';
    }
}
