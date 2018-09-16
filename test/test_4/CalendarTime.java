package test_4;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/***
 * 时间时区类
 * 
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
	CalendarTime() {
		year = calendar.get(Calendar.YEAR);
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
	CalendarTime(int year, int month, int day, int hourOfDay, int minute, int second, int millisecond) {
		this.calendar.set(Calendar.YEAR, year);
		this.calendar.set(Calendar.MONTH, month);
		this.calendar.set(Calendar.DAY_OF_MONTH, day);
		this.calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
		this.calendar.set(Calendar.MINUTE, minute);
		this.calendar.set(Calendar.SECOND, second);
		this.calendar.set(Calendar.MILLISECOND, millisecond);

	//	calendar.set(Calendar.DAY_OF_WEEK, CaculateWeekDay(year,month,day));

		this.year = calendar.get(Calendar.YEAR);
		this.month = calendar.get(Calendar.MONTH);
		this.day = calendar.get(Calendar.DAY_OF_MONTH);
		this.way = calendar.get(Calendar.DAY_OF_WEEK);
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
		this.millisecond = calendar.get(Calendar.MILLISECOND);
	}

	/***
	 * 基姆拉尔森计算公式: W= (d+2*m+3*(m+1)/5+y+y/4-y/100+y/400) mod 7
	 * 在公式中d表示日期中的日数，m表示月份数，y表示年数。注意：在公式中有个与其他公式不同的地方：
	 * 把一月和二月看成是上一年的十三月和十四月，例：如果是2004-1-10则换算成：2003-13-10来代入公式计算。
	 * 
	 * @param y
	 * @param m
	 * @param d
	 * @return
	 */
	static String CaculateWeekDay(int ys, int ms, int ds) {
		String weekstr = null;
		if (ms == 1 || ms == 2) {
			ms += 12;
			ys--;
		}else {
			int week = (ds + 2 * ms + 3 * (ms + 1) / 5 + ys + ys / 4 - ys / 100 + ys / 400 + 1) % 7;
			switch (week) {
			case 1:
				weekstr = "星期一";
				break;
			case 2:
				weekstr = "星期二";
				break;
			case 3:
				weekstr = "星期三";
				break;
			case 4:
				weekstr = "星期四";
				break;
			case 5:
				weekstr = "星期五";
				break;
			case 6:
				weekstr = "星期六";
				break;
			case 0:
				weekstr = "星期日";
				break;
			}
		}
		
		return weekstr;
	}

	/***
	 * 返回日期详细字符串
	 * 
	 * @return
	 */
	public static String timeDataString() {
		String[] strWay = { "日", "一", "二", "三", "四", "五", "六" };
		// getTimZoneInt();timeZone.getDisplayName()+": " +
		return year + "年" + month + "月" + day + "日 " + hour + "时" + minute + "分" + second + "秒" + millisecond + "毫 "
				+ CaculateWeekDay(year,month,day);
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
			strTimeZone = timeZone.getID() + " " + strDateFormat + " 西" + timeZoneChese[timeZoneInt - 1] + "区";
		} else {
			strTimeZone = timeZone.getID() + " " + strDateFormat + " 东" + timeZoneChese[timeZoneInt - 1] + "区";
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
		System.out.println(str);
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
		System.out.println(str);
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
		int hour_Of_Day = calendar.get(Calendar.HOUR_OF_DAY);
		return calendar;
	}

}
