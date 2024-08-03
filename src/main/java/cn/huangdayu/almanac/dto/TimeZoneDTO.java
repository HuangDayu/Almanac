package cn.huangdayu.almanac.dto;

import cn.huangdayu.almanac.aggregates.julian.Julian;
import cn.huangdayu.almanac.exception.AlmanacException;
import cn.huangdayu.almanac.utils.AreaUtils;
import cn.huangdayu.almanac.utils.ConstantsUtils;
import cn.huangdayu.almanac.utils.DateTimeUtils;
import lombok.Data;

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
@Data
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
        this.week = gregorianCalendar.get(Calendar.DAY_OF_WEEK);
        this.hour = gregorianCalendar.get(Calendar.HOUR_OF_DAY);
        this.minute = gregorianCalendar.get(Calendar.MINUTE);
        this.second = gregorianCalendar.get(Calendar.SECOND);
        this.era = gregorianCalendar.get(Calendar.ERA);
        this.index = DateTimeUtils.getTimZoneInt(gregorianCalendar);
        String format = DateTimeUtils.formatDateByFormat(gregorianCalendar, "Z");// +0800
        if (format != null && format.length() > 0) {
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
    }

    /**
     * 指定月
     *
     * @param timeZoneDTO
     */
    public TimeZoneDTO(TimeZoneDTO timeZoneDTO) {
        this(new GregorianCalendar(timeZoneDTO.getEraYear(), timeZoneDTO.getMonth() - 1, timeZoneDTO.getDay(), timeZoneDTO.getHour(), timeZoneDTO.getMinute(), timeZoneDTO.getSecond()));
        try {
            this.address = timeZoneDTO.getAddress();
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
            this.position = (province.replaceAll("省", "") + " " + area.replaceAll("市", "").replaceAll("区", "").replaceAll("县", "").replaceAll("镇", "").replaceAll("乡", ""));
        } catch (Exception e) {
            e.printStackTrace();
            throw new AlmanacException("时间与地址构造异常", e);
        }
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
        return getEraYear() + "-" + add0(month) + "-" + add0(day) + " " + add0(hour) + ":" + add0(minute) + ":" + add0(second);
    }

    public String getDateInfo() {
        return getEraYear() + "-" + add0(month) + "-" + add0(day);
    }

    private String add0(int value) {
        return value < 10 ? "0" + value : "" + value;
    }

    public String getInfo() {
        return (era == 0 ? "公元前" : "") + getDateTimeInfo() + " " + ConstantsUtils.WEEK_NAME[week];
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
                ", position='" + position + '\'' +
                '}';
    }
}
