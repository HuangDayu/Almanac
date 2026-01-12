package cn.huangdayu.almanac.dto;

import cn.huangdayu.almanac.aggregates.julian.Julian;
import cn.huangdayu.almanac.utils.ConstantsUtils;
import cn.huangdayu.almanac.utils.DateTimeUtils;
import cn.huangdayu.almanac.utils.PropertiesUtils;
import cn.huangdayu.almanac.utils.CoordinatesUtils;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static cn.huangdayu.almanac.utils.CommonUtils.fillZero;
import static cn.huangdayu.almanac.utils.CommonUtils.setStringPointDouble;


/**
 * 时区模型类
 *
 * @author huangdayu
 * @update 2020-03-15
 */
public class TimeZoneDTO {

    /**
     * 是否公元前
     */
    private int era;

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
    /**
     * 时区
     */
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
    /***
     * 纬度
     */
    private double latitudeValue;
    /***
     * 经度
     */
    private double longitudeValue;

    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 港口
     */
    private String portName;
    /**
     * 位置
     */
    private String position;

    /**
     * FIXME 2021-01-23 因使用Calendar对象，导致时间无法进入公元前，改用GregorianCalendar对象，ERA表示公元前标志
     */
    private GregorianCalendar gregorianCalendar;

    /**
     * 公历月内日序数
     */
    private int dayIndexOfMonth;
    /**
     * 公历月天数
     */
    private int daysOfMonth;
    /**
     * 本月的总周数
     */
    private int weeksOfMonth;
    /**
     * 月首的星期
     */
    private int weekFirstOfMonth;
    /**
     * 当前日的星期
     */
    private int weekOfCurrentDay;
    /**
     * 本日所在的周序号
     */
    private int weekIndexOfMonth;


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

    public TimeZoneDTO(String province, String area, long currentTimeMillis) {
        this(province, area, DateTimeUtils.timeInMillisToCalendar(currentTimeMillis));
    }

    public TimeZoneDTO(String province, String area, int year, int month, int day, int hourOfDay, int minute, int second) {
        this(province, area, new GregorianCalendar(year, month - 1, day, hourOfDay, minute, second));
    }

    /**
     * 指定月
     *
     * @param timeZoneDTO
     */
    public TimeZoneDTO(TimeZoneDTO timeZoneDTO) {
        this(timeZoneDTO.getProvince(), timeZoneDTO.getArea(), new GregorianCalendar(timeZoneDTO.getEraYear(), timeZoneDTO.getMonth() - 1, timeZoneDTO.getDay(), timeZoneDTO.getHour(), timeZoneDTO.getMinute(), timeZoneDTO.getSecond()));
    }

    public TimeZoneDTO(String province, String area, GregorianCalendar gregorianCalendar) {
        this.gregorianCalendar = gregorianCalendar;
        this.year = gregorianCalendar.get(Calendar.YEAR);
        this.month = gregorianCalendar.get(Calendar.MONTH) + 1;
        this.day = gregorianCalendar.get(Calendar.DAY_OF_MONTH);
        this.week = gregorianCalendar.get(Calendar.DAY_OF_WEEK);
        this.hour = gregorianCalendar.get(Calendar.HOUR_OF_DAY);
        this.minute = gregorianCalendar.get(Calendar.MINUTE);
        this.second = gregorianCalendar.get(Calendar.SECOND);
        this.era = gregorianCalendar.get(Calendar.ERA);
        this.index = DateTimeUtils.getTimZoneInt(gregorianCalendar);
        this.timeZone = CoordinatesUtils.getTimeZone(gregorianCalendar);
        this.province = province;
        this.area = area;
        this.address = CoordinatesUtils.judgeArea(this.province, this.area)[1];
        String coordinates = CoordinatesUtils.decodeCoordinates(this.address);
        this.latitudeValue = (Double.parseDouble(coordinates.substring(0, 2)) + Double.parseDouble(coordinates.substring(2, 4)) / 60);
        this.longitudeValue = (Double.parseDouble(coordinates.substring(4, 7)) + Double.parseDouble(coordinates.substring(7)) / 60);
        this.portName = CoordinatesUtils.getPortName(PropertiesUtils.getLatitudeProperties(), PropertiesUtils.getLongitudeProperties(), this.latitudeValue, this.longitudeValue);
        this.position = (province.replaceAll("省", "") + " " + area.replaceAll("市", "").replaceAll("区", "").replaceAll("县", "").replaceAll("镇", "").replaceAll("乡", ""));
        this.longitude = setStringPointDouble(this.longitudeValue, true);
        this.latitude = setStringPointDouble(this.latitudeValue, false);
    }

    public TimeZoneDTO nextDay(int day, Julian julian) {
        this.setDay(day + 1);
        TimeZoneDTO timeZoneDTO = new TimeZoneDTO(this);
        // 公历月内日序数
        timeZoneDTO.setDayIndexOfMonth(day);
        // 公历月天数
        timeZoneDTO.setDaysOfMonth(julian.getNumberDayOfMonth());
        // 月首的星期
        timeZoneDTO.setWeekFirstOfMonth(julian.getWeekFirstDayOfMonth());
        // 当前日的星期
        timeZoneDTO.setWeekOfCurrentDay((julian.getWeekFirstDayOfMonth() + day) % 7);
        // 本日所在的周序号
        timeZoneDTO.setWeekIndexOfMonth(((julian.getWeekFirstDayOfMonth() + day) / 7));
        // 本月的总周数
        timeZoneDTO.setWeeksOfMonth(((julian.getWeekFirstDayOfMonth() + julian.getNumberDayOfMonth() - 1) / 7) + 1);
        return timeZoneDTO;
    }

    /**
     * FIXME 2021-02-18 当时间进入公元前时，取反计算会导致值-1,不知何因
     *
     * @return
     */
    public int getEraYear() {
        return getEra() == 0 ? -getYear() + 1 : getYear();
    }

    public String getDateTimeInfo() {
        return getEraYear() + "-" + fillZero(month) + "-" + fillZero(day) + " " + fillZero(hour) + ":" + fillZero(minute) + ":" + fillZero(second);
    }

    public String getDateInfo() {
        return getEraYear() + "-" + fillZero(month) + "-" + fillZero(day);
    }

    public String getInfo() {
        return (era == 0 ? "公元前" : "") + getDateTimeInfo() + " " + ConstantsUtils.WEEK_NAME[week];
    }

    public int getEra() {
        return era;
    }

    public void setEra(int era) {
        this.era = era;
    }

    public double getIndex() {
        return index;
    }

    public void setIndex(double index) {
        this.index = index;
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

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
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

    public double getLatitudeValue() {
        return latitudeValue;
    }

    public void setLatitudeValue(double latitudeValue) {
        this.latitudeValue = latitudeValue;
    }

    public double getLongitudeValue() {
        return longitudeValue;
    }

    public void setLongitudeValue(double longitudeValue) {
        this.longitudeValue = longitudeValue;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public GregorianCalendar getGregorianCalendar() {
        return gregorianCalendar;
    }

    public void setGregorianCalendar(GregorianCalendar gregorianCalendar) {
        this.gregorianCalendar = gregorianCalendar;
    }

    public int getDayIndexOfMonth() {
        return dayIndexOfMonth;
    }

    public void setDayIndexOfMonth(int dayIndexOfMonth) {
        this.dayIndexOfMonth = dayIndexOfMonth;
    }

    public int getDaysOfMonth() {
        return daysOfMonth;
    }

    public void setDaysOfMonth(int daysOfMonth) {
        this.daysOfMonth = daysOfMonth;
    }

    public int getWeeksOfMonth() {
        return weeksOfMonth;
    }

    public void setWeeksOfMonth(int weeksOfMonth) {
        this.weeksOfMonth = weeksOfMonth;
    }

    public int getWeekFirstOfMonth() {
        return weekFirstOfMonth;
    }

    public void setWeekFirstOfMonth(int weekFirstOfMonth) {
        this.weekFirstOfMonth = weekFirstOfMonth;
    }

    public int getWeekOfCurrentDay() {
        return weekOfCurrentDay;
    }

    public void setWeekOfCurrentDay(int weekOfCurrentDay) {
        this.weekOfCurrentDay = weekOfCurrentDay;
    }

    public int getWeekIndexOfMonth() {
        return weekIndexOfMonth;
    }

    public void setWeekIndexOfMonth(int weekIndexOfMonth) {
        this.weekIndexOfMonth = weekIndexOfMonth;
    }
}
