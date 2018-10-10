package com.almanac.lunar;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class DataBean {
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

	public DataBean(String province, String area, Instant instant) {
		this(province, area, TimeUtil.instantToCalendar(instant));
	};

	public DataBean(String province, String area, String str) {
		this(province, area, TimeUtil.strToCalendar(str));
	};

	public DataBean(String province, String area, Date date) {
		this(province, area, TimeUtil.dateToCalendar(date));
	}

	public DataBean(String province, String area, long currentTimeMillis) {
		this(province, area, TimeUtil.timeInMillisToCalendar(currentTimeMillis));
	}

	public DataBean(String province, String area, int year, int month, int day, int hourOfDay, int minute, int second) {
		this(province, area, TimeUtil.intToCalendar(year, month, day, hourOfDay, minute, second));
	}

	public DataBean(String province, String area, int year, int month, int day, int hourOfDay, int minute, int second,
			int millisecond) {
		this(province, area, TimeUtil.intToCalendar(year, month, day, hourOfDay, minute, second, millisecond));
	}

	public DataBean(String address, Instant instant) {
		this(address, TimeUtil.instantToCalendar(instant));
	};

	public DataBean(String address, String str) {
		this(address, TimeUtil.strToCalendar(str));
	};

	public DataBean(String address, Date date) {
		this(address, TimeUtil.dateToCalendar(date));
	}

	public DataBean(String address, long currentTimeMillis) {
		this(address, TimeUtil.timeInMillisToCalendar(currentTimeMillis));
	}

	public DataBean(String address, int year, int month, int day, int hourOfDay, int minute, int second) {
		this(address, TimeUtil.intToCalendar(year, month, day, hourOfDay, minute, second));
	}

	public DataBean(String address, int year, int month, int day, int hourOfDay, int minute, int second,
			int millisecond) {
		this(address, TimeUtil.intToCalendar(year, month, day, hourOfDay, minute, second, millisecond));
	}

	public DataBean(String province, String area, Calendar calendar) {
		this.year = calendar.get(Calendar.YEAR);
		this.month = calendar.get(Calendar.MONTH) + 1;
		this.day = calendar.get(Calendar.DAY_OF_MONTH);
		this.week = calendar.get(Calendar.DAY_OF_WEEK);
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
		this.millisecond = calendar.get(Calendar.MILLISECOND);
		this.province = province;
		this.area = area;
		this.address = AreaUtil.judgeArea(this.province, this.area)[1];
		this.calendar = calendar;
	}

	public DataBean(String address, Calendar calendar) {
		this.province = address.split("省")[0];
		this.area = null;
		if (address.contains("县") && !address.contains("市") && !address.contains("区")) {
			this.area = address.split("省|县")[1];
		} else if (address.contains("区") && !address.contains("市") && !address.contains("县")) {
			this.area = address.split("省|区")[1];
		} else if (address.contains("市") && !address.contains("区") && !address.contains("县")) {
			this.area = address.split("省|市")[1];
		} else if (address.contains("市") && address.contains("县")) {
			this.area = address.split("省|市|县")[2];
		} else if (address.contains("市") && address.contains("区")) {
			this.area = address.split("省|市|区")[2];
		}
		this.year = calendar.get(Calendar.YEAR);
		this.month = calendar.get(Calendar.MONTH) + 1;
		this.day = calendar.get(Calendar.DAY_OF_MONTH);
		this.week = calendar.get(Calendar.DAY_OF_WEEK);
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
		this.millisecond = calendar.get(Calendar.MILLISECOND);
		this.address = AreaUtil.judgeArea(this.province, this.area)[1];
		this.calendar = calendar;
	}

	public DataBean(Calendar calendar) {
		this.year = calendar.get(Calendar.YEAR);
		this.month = calendar.get(Calendar.MONTH) + 1;
		this.day = calendar.get(Calendar.DAY_OF_MONTH);
		this.week = calendar.get(Calendar.DAY_OF_WEEK);
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
		this.millisecond = calendar.get(Calendar.MILLISECOND);
		this.calendar = calendar;
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
	
}
