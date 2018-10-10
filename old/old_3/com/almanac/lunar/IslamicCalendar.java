package com.almanac.lunar;

import com.almanac.lunar.LunarDate;

/***
 * 伊斯兰教的历法，又称希吉来历，在我国也叫回回历或回历
 */
public class IslamicCalendar {
	/****
	 * 回历计算方法
	 * @param julianDays
	 * @param date
	 */
	public static void setIslamicCalendar(int julianDays, LunarDate date) {
		// 以下算法使用Excel测试得到,测试时主要关心年临界与月临界
		int z, y, m, d;
		d = julianDays + 503105;
		z = (int) Math.floor(d / 10631); // 10631为一周期(30年)
		d -= z * 10631;
		y = (int) Math.floor((d + 0.5) / 354.366); // 加0.5的作用是保证闰年正确(一周中的闰年是第2,5,7,10,13,16,18,21,24,26,29年)
		d -= (int) Math.floor(y * 354.366 + 0.5);
		m = (int) Math.floor((d + 0.11) / 29.51); // 分子加0.11,分母加0.01的作用是第354或355天的的月分保持为12月(m=11)
		d -= (int) Math.floor(m * 29.5 + 0.5);
		
		date.setIslamic_Year(z * 30 + y + 1);
		date.setIslamic_Month(m + 1);
		date.setIslamic_Day(d + 1);
	}

}
