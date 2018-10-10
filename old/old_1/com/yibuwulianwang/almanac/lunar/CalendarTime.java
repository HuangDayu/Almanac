package com.yibuwulianwang.almanac.lunar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import com.yibuwulianwang.almanac.lunar.CalendarTime;

/***
 * 时间格式转换和处理类
 * 时间时区类
 * 获取系统默认时区：TimeZone.getDefault()
 * @author Administrator
 *
 */
public class CalendarTime {
	private static int millisecond;
	private static int year;
	private static int month;
	private static int day;
	private static int hour;
	private static int minute;
	private static int second;
	private static int way;

	static Date date = new Date();

	private static String strTimeZone;

	static int timeZoneInt;

	static String strDateFormat;

	private static Calendar calendar = Calendar.getInstance();

	static TimeZone timeZone = calendar.getTimeZone();

	public static String getStrTimeZone() {
		return strTimeZone;
	}

	public static void setStrTimeZone(String strTimeZone) {
		CalendarTime.strTimeZone = strTimeZone;
	}

	public static int getMillisecond() {
		return millisecond;
	}

	public static int getYear() {
		return year;
	}

	public static int getMonth() {
		return month;
	}

	public static int getDay() {
		return day;
	}

	public static int getHour() {
		return hour;
	}

	public static int getMinute() {
		return minute;
	}

	public static int getSecond() {
		return second;
	}

	public static int getWay() {
		return way;
	}

	public static Calendar getC() {
		return calendar;
	}

	/***
	 * 获取系统时间的构造方法
	 */
	public CalendarTime() {
		year = calendar.get(Calendar.YEAR);
		/*******************老外用0-11****************************/
		month = calendar.get(Calendar.MONTH) + 1;
		day = calendar.get(Calendar.DAY_OF_MONTH);
		way = calendar.get(Calendar.DAY_OF_WEEK);
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		minute = calendar.get(Calendar.MINUTE);
		second = calendar.get(Calendar.SECOND);
		millisecond = calendar.get(Calendar.MILLISECOND);
	}

	/***
	 * 指定时间的构造方法
	 * 
	 * @param year
	 * @param month
	 * @param date
	 * @param hourOfDay
	 * @param minute
	 * @param second
	 * @param millisecond
	 */
	public CalendarTime(int year, int month, int day, int hourOfDay, int minute, int second, int millisecond) {
		calendar.set(Calendar.YEAR, year+1);
		/*******************老外用0-11，11就相当于12月****************************/
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		calendar.set(Calendar.MILLISECOND, millisecond);

		this.year = calendar.get(Calendar.YEAR)-1;
		this.month = calendar.get(Calendar.MONTH)+1;
		this.day = calendar.get(Calendar.DAY_OF_MONTH);
		this.way = calendar.get(Calendar.DAY_OF_WEEK);
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
		this.millisecond = calendar.get(Calendar.MILLISECOND);
	}
	public CalendarTime(int year, int month, int day, int hourOfDay, int minute, int second) {
		calendar.set(Calendar.YEAR, year+1);
		/*******************老外用0-11，11就相当于12月****************************/
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		calendar.set(Calendar.MILLISECOND, 0);

		this.year = calendar.get(Calendar.YEAR)-1;
		this.month = calendar.get(Calendar.MONTH)+1;
		this.day = calendar.get(Calendar.DAY_OF_MONTH);
		this.way = calendar.get(Calendar.DAY_OF_WEEK);
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
		this.millisecond = calendar.get(Calendar.MILLISECOND);
	}
	public CalendarTime(int year, int month, int day) {
		calendar.set(Calendar.YEAR, year+1);
		/*******************老外用0-11，11就相当于12月****************************/
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		this.year = calendar.get(Calendar.YEAR)-1;
		this.month = calendar.get(Calendar.MONTH)+1;
		this.day = calendar.get(Calendar.DAY_OF_MONTH);
		this.way = calendar.get(Calendar.DAY_OF_WEEK);
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
		this.millisecond = calendar.get(Calendar.MILLISECOND);
	}
	
	public CalendarTime(int year, int month) {
		calendar.set(Calendar.YEAR, year+1);
		/*******************老外用0-11，11就相当于12月****************************/
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		this.year = calendar.get(Calendar.YEAR)-1;
		this.month = calendar.get(Calendar.MONTH)+1;
		this.day = calendar.get(Calendar.DAY_OF_MONTH);
		this.way = calendar.get(Calendar.DAY_OF_WEEK);
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
		this.millisecond = calendar.get(Calendar.MILLISECOND);
	}
	public CalendarTime(int year) {
		calendar.set(Calendar.YEAR, year+1);
		/*******************老外用0-11，11就相当于12月****************************/
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		this.year = calendar.get(Calendar.YEAR)-1;
		this.month = calendar.get(Calendar.MONTH)+1;
		this.day = calendar.get(Calendar.DAY_OF_MONTH);
		this.way = calendar.get(Calendar.DAY_OF_WEEK);
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
		this.millisecond = calendar.get(Calendar.MILLISECOND);
	}
	
	/***
	 * 这不是基姆拉尔森计算公式: W= (d+2*m+3*(m+1)/5+y+y/4-y/100+y/400) mod 7
	 * 对比许剑伟先生的寿星天文历，和刘国安先生的日梭万年历
	 * 经认证：基姆拉尔森计算公式适合时间不长，大概1000年，时间过大过小是不准确
	 * 这里用CSDN网友推算出来的公式，可用1583年到5582年，年限越往前时，与许剑伟先生的寿星万年历有差误
	 * 源码地址：http://bbs.csdn.net/topics/70277519#new_post
	 * @param y
	 * @param m
	 * @param d
	 * @return
	 */
	public static String GetWeek() {
		int y = year;
		int m = month; 
		int d = day;
		//String week[] = new String[] { "星期日"  ,"星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		String week[] = new String[] { "日"  ,"一", "二", "三", "四", "五", "六"};
		if (m < 3) {
			m += 12;
			--y;
		}
		int w = (d + 1 + 2 * m + 3 * (m + 1) / 5 + y + (y >> 2) - y / 100 + y / 400) % 7;
		
		return week[w];
	}
	
	/***
	 * 返回日期
	 * 
	 * @return
	 */
	public static String getDataString() {
		return year + "年" + month + "月" + day + "日 ";
	}
	/***
	 * 返回时间
	 * 
	 * @return
	 */
	public static String getTimeString() {
		return hour + "时" + minute + "分" + second + "秒" + millisecond + "毫秒";
	}
	/***
	 * 返回星期
	 * 
	 * @return
	 */
	public static String getWeekString() {
		return "周"+GetWeek();
	}
	
	
	

	/***
	 * 返回日期时间详细字符串
	 * 
	 * @return
	 */
	public static String timeDataString() {
		// getTimZoneInt();timeZone.getDisplayName()+": " +
		return year + "年" + month + "月" + day + "日 " + hour + "时" + minute + "分" + second + "秒" + millisecond + "毫秒"
				+ GetWeek();
	}

	/**
	 * 把时间改变格式，传入八字类
	 * 
	 * @return "1995-08-12 11:22:33"
	 */
	public String getTimeDataStringForBazi() {
		return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
	}
	
	/***
	 * 获取时区
	 * @return
	 */
	public static int getTimZoneInt() {

		String dateTime = formatDateByFormat(date, "yyyyMMddHHmmssZZZ");// 20171014201011+0800

		strDateFormat = formatDateByFormat(date, "Z");// +0800

		String str2 = strDateFormat.substring(1, 3);

		int j = Integer.valueOf(str2.substring(0));
		if (j > 0) {
			timeZoneInt = Integer.valueOf(str2);
		} else {
			timeZoneInt = Integer.valueOf(str2.substring(1));
		}

		String[] timeZoneChese = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二" };

		if (strDateFormat.contains("-")) {
			//strTimeZone = timeZone.getID() + " " + strDateFormat + " 西" + timeZoneChese[timeZoneInt - 1] + "区";
			strTimeZone =   strDateFormat + " 西" + timeZoneChese[timeZoneInt - 1] + "区";
		} else {
			//strTimeZone = timeZone.getID() + " " + strDateFormat + " 东" + timeZoneChese[timeZoneInt - 1] + "区";
			strTimeZone = strDateFormat + " 东" + timeZoneChese[timeZoneInt - 1] + "区";
		}
		return timeZoneInt;
	}

	public static String getTimeShort(double d) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date currentTime = new Date((long) d);
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/***
	 * 时间格式处理
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDateByFormat(Date date, String format) {
		String result = "";
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

	/***
	 * 指定Date类时间 3 个参数
	 * http://blog.sina.com.cn/s/blog_4550f3ca0101t042.html
	 * @param y 年
	 * @param M 月
	 * @param d 日
	 * @return Date对象
	 */
	public static Date getCalendarToDate(int y, int M, int d) {
		String str = y + "-" + M + "-" + d;
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate1 = null;
		try {
			myDate1 = dateFormat1.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return myDate1;
	}
	/***
	 * 指定Date类时间  6个参数
	 * @param y 年
	 * @param M 月
	 * @param d 日
	 * @param h 时
	 * @param m 分
	 * @param s 秒
	 * @return Date对象
	 */
	public static Date getCalendarToDate(int y, int M, int d,int h,int m,int s) {
		String str = y + "-" + M + "-" + d+" "+h+":"+m+":"+s;
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date myDate1 = null;
		try {
			myDate1 = dateFormat1.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return myDate1;
	}
	
	/***
	 * Calendar 转成 Date
	 * @param c
	 * @return
	 */
	public static Date setCalendarToDate(CalendarTime c) {
		String str = c.getYear() + "-" + c.getMonth() + "-" + c.getDay()+" "+c.getHour()+":"+c.getMinute()+":"+c.getSecond();
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date myDate1 = null;
		try {
			myDate1 = dateFormat1.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return myDate1;
	}
	/***
	 * Date 转成 Calendar
	 * @param d
	 * @return
	 */
	public static Calendar setDateToCalendar(Date d) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(d.getTime());
		return calendar;
	}
	/***
	 * Date 转成 Calendar
	 * @param d
	 * @return
	 */
	public static Calendar setDateToCalendar_2(Date date) {
		Calendar calendar = Calendar.getInstance();//创建实例
		calendar.setTime(date);
		return calendar;
	}
	/***
	 *  将Date格式化成中文字符串
	 */
	public static String setDateToCNStr(Date date) {
		String timeStr = "yyyy年MM月dd日  HH时mm分ss秒 时区:Z 星期中的天数:E 年中的周数:w 月份中的周数:W 年中的天数:D 月份中的天数:d 月份中的星期:F ";
		SimpleDateFormat sdf = new SimpleDateFormat(timeStr);
		System.out.println(sdf.format(date));
		return sdf.format(date);
	}

}
