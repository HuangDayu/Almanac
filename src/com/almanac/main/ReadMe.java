package com.almanac.main;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.almanac.lunar.Almanac;
import com.almanac.lunar.AlmanacImpl;
import com.almanac.lunar.TimeBean;

public class ReadMe {

	public static void main(String[] args) {
		
		TimeBean timeBean1 = new TimeBean("广东省徐闻县",Calendar.getInstance());

		TimeBean timeBean2 = new TimeBean("广东省徐闻县","1995-08-12 11:10:10");

		TimeBean timeBean3 = new TimeBean("广东省徐闻县","1-1-1 11:10:10");

		pakMap(new AlmanacImpl(timeBean1),new AlmanacImpl(timeBean2),new AlmanacImpl(timeBean3)).forEach((K, V) -> {
			System.out.println("| `" + K + "`" + V + " |");
		});
	}

	public static Map<String, String> pakMap(Almanac almanac1,Almanac almanac2,Almanac almanac3) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("日期", " | "+almanac1.getDate()+" | "+almanac2.getDate()+" | "+almanac3.getDate());
		map.put("时间", " | "+almanac1.getTime()+" | "+almanac2.getTime()+" | "+almanac3.getTime());
		map.put("星期", " | "+almanac1.getWeek()+" | "+almanac2.getWeek()+" | "+almanac3.getWeek());
		map.put("年号", " | "+almanac1.getYearNumber()+" | "+almanac2.getYearNumber()+" | "+almanac3.getYearNumber());
		map.put("农历", " | "+almanac1.getLunar()+" | "+almanac2.getLunar()+" | "+almanac3.getLunar());
		map.put("时辰", " | "+almanac1.getLunarTime()+" | "+almanac2.getLunarTime()+" | "+almanac3.getLunarTime());
		map.put("黄历", " | "+almanac1.getHuangLi()+" | "+almanac2.getHuangLi()+" | "+almanac3.getHuangLi());
		map.put("天干", " | "+almanac1.getTianGan()+" | "+almanac2.getTianGan()+" | "+almanac3.getTianGan());
		map.put("地支", " | "+almanac1.getDiZhi()+" | "+almanac2.getDiZhi()+" | "+almanac3.getDiZhi());
		map.put("八字", " | "+almanac1.getBaZi()+" | "+almanac2.getBaZi()+" | "+almanac3.getBaZi());
		map.put("回历", " | "+almanac1.getIslamic()+" | "+almanac2.getIslamic()+" | "+almanac3.getIslamic());
		map.put("儒略日", " | "+almanac1.getJulianDay()+" | "+almanac2.getJulianDay()+" | "+almanac3.getJulianDay());
		map.put("黄帝纪年", " | "+almanac1.getChronology()+" | "+almanac2.getChronology()+" | "+almanac3.getChronology());
		map.put("生肖", " | "+almanac1.getZodiac()+" | "+almanac2.getZodiac()+" | "+almanac3.getZodiac());
		map.put("地点", " | "+almanac1.getPosition()+" | "+almanac2.getPosition()+" | "+almanac3.getPosition());
		map.put("节假日", " | "+almanac1.getHolidayVacations()+" | "+almanac2.getHolidayVacations()+" | "+almanac3.getHolidayVacations());
		map.put("经度", " | "+almanac1.getLongitude()+" | "+almanac2.getLongitude()+" | "+almanac3.getLongitude());
		map.put("纬度", " | "+almanac1.getLatitude()+" | "+almanac2.getLatitude()+" | "+almanac3.getLatitude());
		map.put("时区", " | "+almanac1.getTimeZone()+" | "+almanac2.getTimeZone()+" | "+almanac3.getTimeZone());
		map.put("港口", " | "+almanac1.getPortName()+" | "+almanac2.getPortName()+" | "+almanac3.getPortName());
		map.put("昼长", " | "+almanac1.getDiurnalTime()+" | "+almanac2.getDiurnalTime()+" | "+almanac3.getDiurnalTime());
		map.put("夜长", " | "+almanac1.getNightTime()+" | "+almanac2.getNightTime()+" | "+almanac3.getNightTime());
		map.put("天亮", " | "+almanac1.getDawnTime()+" | "+almanac2.getDawnTime()+" | "+almanac3.getDawnTime());
		map.put("日出", " | "+almanac1.getSunriseTime()+" | "+almanac2.getSunriseTime()+" | "+almanac3.getSunriseTime());
		map.put("中天", " | "+almanac1.getMidDayTime()+" | "+almanac2.getMidDayTime()+" | "+almanac3.getMidDayTime());
		map.put("日落", " | "+almanac1.getSunsetTime()+" | "+almanac2.getSunsetTime()+" | "+almanac3.getSunsetTime());
		map.put("天黑", " | "+almanac1.getDarkTime()+" | "+almanac2.getDarkTime()+" | "+almanac3.getDarkTime());
		map.put("月出", " | "+almanac1.getMoonOutTime()+" | "+almanac2.getMoonOutTime()+" | "+almanac3.getMoonOutTime());
		map.put("月中", " | "+almanac1.getMidMoonTime()+" | "+almanac2.getMidMoonTime()+" | "+almanac3.getMidMoonTime());
		map.put("月落", " | "+almanac1.getMoonDownTime()+" | "+almanac2.getMoonDownTime()+" | "+almanac3.getMoonDownTime());
		map.put("月相", " | "+almanac1.getMoonPhase()+" | "+almanac2.getMoonPhase()+" | "+almanac3.getMoonPhase());
		map.put("月天数", " | "+almanac1.getLunarDays()+" | "+almanac2.getLunarDays()+" | "+almanac3.getLunarDays());
		map.put("大月否", " | "+almanac1.isLunarBigMonth()+" | "+almanac2.isLunarBigMonth()+" | "+almanac3.isLunarBigMonth());
		map.put("闰月否", " | "+almanac1.isLeapMonth()+" | "+almanac2.isLeapMonth()+" | "+almanac3.isLeapMonth());
		map.put("闰年否", " | "+almanac1.isLeapYear()+" | "+almanac2.isLeapYear()+" | "+almanac3.isLeapYear());
		map.put("星座", " | "+almanac1.getConstellation()+" | "+almanac2.getConstellation()+" | "+almanac3.getConstellation());
		map.put("下一节气", " | "+almanac1.getNextSolarTerm()+" | "+almanac2.getNextSolarTerm()+" | "+almanac3.getNextSolarTerm());
		map.put("春分", " | "+almanac1.getSolarTerm("春分")+" | "+almanac2.getSolarTerm("春分")+" | "+almanac3.getSolarTerm("春分"));
		map.put("夏至", " | "+almanac1.getSolarTerm("夏至")+" | "+almanac2.getSolarTerm("夏至")+" | "+almanac3.getSolarTerm("夏至"));
		map.put("秋分", " | "+almanac1.getSolarTerm("秋分")+" | "+almanac2.getSolarTerm("秋分")+" | "+almanac3.getSolarTerm("秋分"));
		map.put("冬至", " | "+almanac1.getSolarTerm("冬至")+" | "+almanac2.getSolarTerm("冬至")+" | "+almanac3.getSolarTerm("冬至"));
		return map;
	}

}
