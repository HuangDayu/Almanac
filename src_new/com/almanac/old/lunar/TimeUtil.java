package com.almanac.old.lunar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
	
	public static String timeStr = "yyyy年MM月dd日  HH时mm分ss秒 时区:Z 星期中的天数:E 年中的周数:w 月份中的周数:W 年中的天数:D 月份中的天数:d 月份中的星期:F ";

	public static Calendar dateToCalendar(Date date) {
		//System.out.println(dateFormat(date,timeStr));
		return timeInMillisToCalendar(date.getTime());
	}

	public static Calendar dateToCalendar2(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public static Calendar timeInMillisToCalendar(long TimeInMillis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(TimeInMillis);
		return calendar;
	}

	public static Calendar timeInMillisToCalendar2(long TimeInMillis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(TimeInMillis);
		return calendar;
	}

	public static Calendar instantToCalendar(Instant instant) {
		return timeInMillisToCalendar(instant.toEpochMilli());
	}

	/***
	 * new Date().toInstant()
	 * 
	 * @param instant
	 *            格式2018-09-07T08:16:03.206Z
	 * @return
	 */
	public static Calendar instantToCalendar(String instant) {
		String[] sqlits = instant.split("T|-|Z|:|\\.");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(sqlits[0]), (Integer.parseInt(sqlits[1])) - 1, Integer.parseInt(sqlits[2]),
				Integer.parseInt(sqlits[3]) + 8, Integer.parseInt(sqlits[4]), Integer.parseInt(sqlits[5]));
		return calendar;
	}

	public static Calendar strToCalendar(String str) {
		return dateToCalendar(getDate(str));
	}

	public static Calendar intToCalendar(int year, int month, int day, int hourOfDay, int minute, int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day, hourOfDay, minute, second);
		return calendar;
	}

	public static Calendar intToCalendar(int year, int month, int day, int hourOfDay, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day, hourOfDay, minute);
		return calendar;
	}

	/***
	 * 老外用0-11，11就相当于12月
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param hourOfDay
	 * @param minute
	 * @param second
	 * @param millisecond
	 * @return
	 */
	public static Calendar intToCalendar(int year, int month, int day, int hourOfDay, int minute, int second,
			int millisecond) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		calendar.set(Calendar.MILLISECOND, millisecond);
		return calendar;
	}

	public static String toUTC(Date date) {
		return date.toInstant().toString();
	}

	/***
	 * 时间格式处理
	 * 
	 * @param date
	 * @param format
	 *            yyyyMMddHHmmssZZZ
	 * @return
	 */
	public static String dateFormat(Date date, String format) {
		String result = null;
		if (date != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 2018-09-07T09:24:05.350Z
	 * 
	 * @param instant
	 * @return
	 */
	public static Date getDate(String str) {
		String format = null;
		if (str.contains(".") || str.contains("T") || str.contains("Z")) {
			str = str.replace("T", " ").replaceAll("Z", "");
			format = "yyyy-MM-dd HH:mm:ss.SS";
		} else if (str.contains("年")) {
			format = "yyyy年MM月dd日 HH:mm:ss";
		} else {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = dateFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/***
	 * 指定Date类时间 3个参数 http://blog.sina.com.cn/s/blog_4550f3ca0101t042.html
	 */
	public static Date getDate(int y, int M, int d, int h, int m) {
		String str = y + "-" + M + "-" + d + " " + h + ":" + m;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		try {
			date = dateFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/***
	 * 指定Date类时间 6个参数
	 * 
	 * @return Date对象
	 */
	public static Date getDate(int y, int M, int d, int h, int m, int s) {
		String str = y + "-" + M + "-" + d + " " + h + ":" + m + ":" + s;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = dateFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}