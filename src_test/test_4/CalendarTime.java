package test_4;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/***
 * ʱ��ʱ����
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
	 * ��ȡϵͳʱ��Ĺ��췽��
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
	 * ָ��ʱ��Ĺ��췽��
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
	 * ��ķ����ɭ���㹫ʽ: W= (d+2*m+3*(m+1)/5+y+y/4-y/100+y/400) mod 7
	 * �ڹ�ʽ��d��ʾ�����е�������m��ʾ�·�����y��ʾ������ע�⣺�ڹ�ʽ���и���������ʽ��ͬ�ĵط���
	 * ��һ�ºͶ��¿�������һ���ʮ���º�ʮ���£����������2004-1-10����ɣ�2003-13-10�����빫ʽ���㡣
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
				weekstr = "����һ";
				break;
			case 2:
				weekstr = "���ڶ�";
				break;
			case 3:
				weekstr = "������";
				break;
			case 4:
				weekstr = "������";
				break;
			case 5:
				weekstr = "������";
				break;
			case 6:
				weekstr = "������";
				break;
			case 0:
				weekstr = "������";
				break;
			}
		}
		
		return weekstr;
	}

	/***
	 * ����������ϸ�ַ���
	 * 
	 * @return
	 */
	public static String timeDataString() {
		String[] strWay = { "��", "һ", "��", "��", "��", "��", "��" };
		// getTimZoneInt();timeZone.getDisplayName()+": " +
		return year + "��" + month + "��" + day + "�� " + hour + "ʱ" + minute + "��" + second + "��" + millisecond + "�� "
				+ CaculateWeekDay(year,month,day);
	}

	/**
	 * ��ʱ��ı��ʽ�����������
	 * 
	 * @return "1995-08-12 11:22:33"
	 */
	public String getTimeDataStringForBazi() {
		return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
	}
	
	/***
	 * ��ȡʱ��
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

		String[] timeZoneChese = { "һ", "��", "��", "��", "��", "��", "��", "��", "��", "ʮ", "ʮһ", "ʮ��" };

		if (strDateFormat.contains("-")) {
			strTimeZone = timeZone.getID() + " " + strDateFormat + " ��" + timeZoneChese[timeZoneInt - 1] + "��";
		} else {
			strTimeZone = timeZone.getID() + " " + strDateFormat + " ��" + timeZoneChese[timeZoneInt - 1] + "��";
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
	 * ʱ���ʽ����
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
	 * ָ��Date��ʱ�� 3 ������
	 * http://blog.sina.com.cn/s/blog_4550f3ca0101t042.html
	 * @param y ��
	 * @param M ��
	 * @param d ��
	 * @return Date����
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
	 * ָ��Date��ʱ��  6������
	 * @param y ��
	 * @param M ��
	 * @param d ��
	 * @param h ʱ
	 * @param m ��
	 * @param s ��
	 * @return Date����
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
	 * Calendar ת�� Date
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
	 * Date ת�� Calendar
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
