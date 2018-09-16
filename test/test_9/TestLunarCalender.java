package test_9;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import test_9.LunarConstant.XL;
import test_8.LunarConstant.*;

public class TestLunarCalender {
	/***
	 * 指定Date类时间  3个参数
	 * http://blog.sina.com.cn/s/blog_4550f3ca0101t042.html
	 */
	public static Date getCalendarToDate(int y, int M, int d) {
		String str = y + "-" + M + "-" + d;
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH");
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
		//System.out.println(str);
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date myDate1 = null;
		try {
			myDate1 = dateFormat1.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return myDate1;
	}

	public static void main(String[] args) {
		LunarConstant lc = new LunarConstant();//从外部类的静态方法中实例化内部类对象。
		LunarConstant.LunarCalendar calendar = lc.new LunarCalendar(getCalendarToDate(1995, 8,12, 11, 22, 33));
		//LunarCalendar calendar = new LunarCalendar();
		System.out.println("农历生肖=" + calendar.getAnimalString());
		System.out.println("获取农历日期字符串=" + calendar.getDateString());
		System.out.println("农历日=" + calendar.getDay());
		System.out.println("农历日字符串=" + calendar.getDayString());
		System.out.println("获取干支农历日期字符串=" + calendar.getGanZhiDateString());
		System.out.println("某农历月有多少天=" + calendar.getMaxDayInMonth());
		System.out.println("农历月=" + calendar.getMonth());
		System.out.println("农历月字符串=" + calendar.getMonthString());
		System.out.println("农历年=" + calendar.getYear());
		System.out.println("农历年字符串=" + calendar.getYearString());
		System.out.println("是否大月=" + calendar.isBigMonth());
		System.out.println("是否闰月=" + calendar.isLeap());
		System.out.println("是否闰年=" + calendar.isLeapYear());
		
		
		System.out.println("获取某一天的农历对象=" + calendar.getDayLunarDate().getClass().getName());
		System.out.println("获取某月的农历对象=" + calendar.getMonthLunarDates().getClass().getName());
		
		System.out.println("地球公转速度："+XL.E_v(XL.getJulian_Shijishu()));
		System.out.println("月球速度："+XL.M_v(XL.getJulian_Shijishu()));
		System.out.println("地球经度计算："+XL.E_Lon(XL.getJulian_Shijishu(), 5));
		
		System.out.println("太阳视黄经："+XL.S_aLon(XL.getJulian_Shijishu(), 5));
		
		System.out.println("回历年："+calendar.lunarDate.gethYear());
		System.out.println("回历月："+calendar.lunarDate.gethMonth());
		System.out.println("回历日："+calendar.lunarDate.gethDay());
		System.out.println("月多少天："+calendar.lunarDate.getDaysOfMonth());
		System.out.println("星期："+calendar.lunarDate.getWeek());
		
		System.out.println("月名称："+calendar.lunarDate.getLunarMonthName());
		System.out.println("日名称："+calendar.lunarDate.getLunarDayName());
		System.out.println("农历月大小："+calendar.lunarDate.getDaysofLunarMonth());
		System.out.println("是否闰月："+calendar.lunarDate.getLunarLunarLeap());
		
		System.out.println("下一个农历月："+calendar.lunarDate.getNextLunarMonthName());
		
		System.out.println("节气："+calendar.lunarDate.getLunarSolarTerm());
		
		System.out.println("距上一个冬至的天数："+calendar.lunarDate.getDaysToDZ());
		
		System.out.println("星座："+calendar.lunarDate.getConstellation());
		
		System.out.println("月相："+calendar.lunarDate.getMoonPhaseName());
		
		System.out.println("节气名称："+calendar.lunarDate.getSolarTermName());
		
		System.out.println("黄帝纪年："+calendar.lunarDate.getKingYear());
		
		
		
		System.out.println("较高精度朔："+calendar.ssq.so_high(131334));
		
		System.out.println("放假日子："+calendar.lunarDate.getImpName());
		System.out.println("放假日子："+calendar.lunarDate.getImpHappyName());
		System.out.println("假日："+calendar.lunarDate.getAllName());
		System.out.println("放假日子："+calendar.lunarDate.getHoliday());
		
		/***
		 * 获取某年的二十四节气信息
		 */
       String[] strs = calendar.getAllSolarTerm();

		
		for (String str : strs) {
			System.out.println(str);
		}	
		
		System.out.println(calendar.getNowSolarTerm());
		
	}

}
