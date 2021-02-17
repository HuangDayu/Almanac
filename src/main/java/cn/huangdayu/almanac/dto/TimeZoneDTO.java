package cn.huangdayu.almanac.dto;

import cn.huangdayu.almanac.exception.AlmanacException;
import cn.huangdayu.almanac.utils.AreaUtils;
import cn.huangdayu.almanac.utils.ConstantsUtils;
import cn.huangdayu.almanac.utils.DateTimeUtils;
import cn.huangdayu.almanac.utils.JulianCalendarUtils;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * 时区模型类
 *
 * @author huangdayu
 * @update 2020-03-15
 */
public class TimeZoneDTO {
    /**
     * 时区下标
     */
    private double index;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    /**
     * 星期
     * TODO 或许可以考虑用上面的week变量，如果是周日是一周的第一天，那么久需要减1
     */
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
     *
     */
    private Integer julianDay;
    private String position;

    /**
     * BUG 2021-01-23 因使用Calendar对象，导致时间无法进入公元前，改用GregorianCalendar对象
     */
    private GregorianCalendar gregorianCalendar;


    private TimeZoneDTO() {
    }

    public TimeZoneDTO(String province, String area, String dateTime) {
        this(province, area, DateTimeUtils.toDate(dateTime));
    }

    public TimeZoneDTO(String province, String area, Instant instant) {
        this(province, area, DateTimeUtils.timeInMillisToCalendar(instant.toEpochMilli()));
    }

    public TimeZoneDTO(String province, String area, Calendar calendar) {
        this(province, area, DateTimeUtils.timeInMillisToCalendar(calendar.getTimeInMillis()));
    }

    public TimeZoneDTO(String province, String area, Date date) {
        this(province, area, DateTimeUtils.timeInMillisToCalendar(date.getTime()));
    }

    public TimeZoneDTO(String province, String area, GregorianCalendar gregorianCalendar) {
        this(gregorianCalendar, province, area);
    }

    public TimeZoneDTO(String province, String area, long currentTimeMillis) {
        this(province, area, DateTimeUtils.timeInMillisToCalendar(currentTimeMillis));
    }

    public TimeZoneDTO(String province, String area, int year, int month, int day, int hourOfDay, int minute, int second) {
        this(province, area, new GregorianCalendar(year, month - 1, day, hourOfDay, minute, second));
    }

    public TimeZoneDTO(int year, int month, int day, int hourOfDay, int minute, int second) {
        this(new GregorianCalendar(year, month - 1, day, hourOfDay, minute, second));
    }

    private TimeZoneDTO(GregorianCalendar gregorianCalendar) {
        this.gregorianCalendar = gregorianCalendar;
        this.year = gregorianCalendar.get(Calendar.YEAR);
        this.month = gregorianCalendar.get(Calendar.MONTH) + 1;
        this.day = gregorianCalendar.get(Calendar.DAY_OF_MONTH);
        this.week = gregorianCalendar.get(Calendar.DAY_OF_WEEK) - (gregorianCalendar.getFirstDayOfWeek() == Calendar.SUNDAY ? 1 : 0);
        this.hour = gregorianCalendar.get(Calendar.HOUR_OF_DAY);
        this.minute = gregorianCalendar.get(Calendar.MINUTE);
        this.second = gregorianCalendar.get(Calendar.SECOND);
        this.index = DateTimeUtils.getTimZoneInt(gregorianCalendar);
        String format = DateTimeUtils.formatDateByFormat(gregorianCalendar, "Z");// +0800
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
    }

    /**
     * 指定天
     *
     * @param timeZoneDTO
     * @param julianDay
     */
    public TimeZoneDTO(TimeZoneDTO timeZoneDTO, int julianDay) {
        this(new GregorianCalendar(timeZoneDTO.getYear(), timeZoneDTO.getMonth() - 1, timeZoneDTO.getDay(), timeZoneDTO.getHour(), timeZoneDTO.getMinute(), timeZoneDTO.getSecond()));
        try {
            this.address = timeZoneDTO.getAddress();
            this.julianDay = julianDay;
            this.timeZone = timeZoneDTO.getTimeZone();
            this.position = timeZoneDTO.getPosition();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AlmanacException("时间与地址构造异常", e);
        }
    }

    /**
     * 指定月
     *
     * @param timeZoneDTO
     */
    public TimeZoneDTO(TimeZoneDTO timeZoneDTO) {
        this(new GregorianCalendar(timeZoneDTO.getYear(), timeZoneDTO.getMonth() - 1, timeZoneDTO.getDay(), timeZoneDTO.getHour(), timeZoneDTO.getMinute(), timeZoneDTO.getSecond()));
        try {
            this.address = timeZoneDTO.getAddress();
            this.julianDay = JulianCalendarUtils.getJuLian(this.year, this.month, this.day);
            this.timeZone = timeZoneDTO.getTimeZone();
            this.position = timeZoneDTO.getPosition();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AlmanacException("时间与地址构造异常", e);
        }
    }

    public TimeZoneDTO(GregorianCalendar gregorianCalendar, String... names) {
        this(gregorianCalendar);
        try {
            if (names != null && names.length > 0) {
                if (names.length == 2) {
                    this.province = names[0];
                    this.area = names[1];
                } else if (names.length == 1) {
                    String name = names[0];
                    String[] nameList = name.trim().split("[省市区县镇]");
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
            this.position = (province.replaceAll("省", "") + " " + area.replaceAll("市", "").replaceAll("区", "").replaceAll("县", "").replaceAll("镇", "").replaceAll("乡", ""));
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

    public double getIndex() {
        return index;
    }

    public void setIndex(double index) {
        this.index = index;
    }

    public GregorianCalendar getGregorianCalendar() {
        return gregorianCalendar;
    }

    public void setGregorianCalendar(GregorianCalendar gregorianCalendar) {
        this.gregorianCalendar = gregorianCalendar;
    }

    public String getDateTime() {
        return year + "-" + add0(month) + "-" + add0(day) + " " + add0(hour) + ":" + add0(minute) + ":" + add0(second);
    }

    private String add0(int value) {
        return value < 10 ? "0" + value : "" + value;
    }

    private Calendar toCalendar() {
        return DateTimeUtils.toCalendar(this);
    }

    public String getInfo() {
        return getDateTime() + " " + ConstantsUtils.WEEK_NAME[week];
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
                ", week=" + week +
                ", timeZone='" + timeZone + '\'' +
                ", province='" + province + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", julianDay=" + julianDay +
                ", position='" + position + '\'' +
                '}';
    }
}
