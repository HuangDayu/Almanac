package com.almanac.main;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import com.almanac.lunar.Almanac;
import com.almanac.lunar.AlmanacImpl;
import com.almanac.lunar.TimeBean;

public class Main {
	String[] key_DayTimeType = { "日期", "时间", "星期", "地点", "经度", "纬度", "时区", "昼长", "夜长", "天亮", "日出", "中天", "日落", "天黑",
			"月出", "月中", "月落", "港口", "儒略日", "干支年", "干支月", "干支日", "干支时", "黄帝纪年", "生肖", "农历年", "农历月", "农历日", "月天数", "大月否",
			"闰月否", "闰年否", "回历年", "回历月", "回历日", "星座", "节气", "天干", "地支", "八字", "黄历", "伊斯兰历", "年号", "节假日", "月相", "农历",
			"立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至", "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降",
			"立冬", "小雪", "大雪", "冬至", "小寒", "大寒" };

	public static void main(String[] args) {
		// TimeBean timeBean = new TimeBean(1995,8, 12, 11, 22, 33, 44);
		// TimeBean timeBean = new TimeBean(-101, 1, 1, 11, 22, 33, 44);
		// TimeBean timeBean = new TimeBean(558, 1, 1, 11, 22, 33, 44);
		// TimeBean timeBean = new TimeBean(1583, 1, 1, 11, 22, 33, 44);
		// TimeBean timeBean = new TimeBean(5582, 1, 1, 11, 22, 33, 44);
		// TimeBean timeBean = new TimeBean(1, 1, 1, 11, 22, 33, 44);

		// TimeBean timeBean = new TimeBean(2018, 9, 7, 17, 36, 33,1001);

		// TimeBean timeBean = new TimeBean("广东省徐闻县",Calendar.getInstance());

		// TimeBean timeBean = new TimeBean(new Date());

		// TimeBean timeBean = new TimeBean(System.currentTimeMillis());

		 //TimeBean timeBean = new TimeBean("广东省徐闻县",new Date().toInstant());

		// TimeBean timeBean = new TimeBean("广东省徐闻县","2018-09-07T18:40:54.063Z");

		// TimeBean timeBean = new TimeBean("广东省徐闻县","2018-09-07 09:24:54.666");

		// TimeBean timeBean = new TimeBean("广东省徐闻县",2018, 9, 7, 17, 36, 33,1001);

		 //TimeBean timeBean = new TimeBean("广东省徐闻县",1,1,1, 17, 36, 33);
		
		//TimeBean timeBean = new TimeBean("广东","徐闻","2018-09-07","09:24:54.666");

		// TimeBean timeBean = new TimeBean();

		// TimeBean timeBean = new TimeBean("2018年09月07日 18:24:54");

		 //TimeBean timeBean = new TimeBean("广东省徐闻县","2018-09-20 18:24:54");
		
		//TimeBean timeBean = new TimeBean("广东省徐闻县","2018-09-20 18:24");
		
		//TimeBean timeBean = new TimeBean("广东省广州市","2018年09月10日 14:12");
		
		//TimeBean timeBean = new TimeBean("广东省徐闻县","2018-09-20 18:24:54.46");

		 //TimeBean timeBean = new TimeBean("广东省", "徐闻县","2018-09-07 18:24:54");
		 
		 //TimeBean timeBean = new TimeBean("广东省徐闻县", Calendar.getInstance());
		 
		 TimeBean timeBean = new TimeBean("广东 徐闻", Calendar.getInstance());

		//TimeBean timeBean = new TimeBean("广东省广州市白云区", 1995, 8, 12, 11, 22, 33, 44);

		pakMap(new AlmanacImpl(timeBean)).forEach((K, V) -> {
			System.out.println(K + ":" + V);
		});
	}

	public static Map<String, String> pakMap(Almanac almanacData) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("日期", almanacData.getDate());
		map.put("时间", almanacData.getTime());
		map.put("星期", almanacData.getWeek());
		map.put("年号", almanacData.getYearNumber());
		map.put("农历", almanacData.getLunar());
		map.put("时辰", almanacData.getLunarTime());
		map.put("黄历", almanacData.getHuangLi());
		map.put("天干", almanacData.getTianGan());
		map.put("地支", almanacData.getDiZhi());
		map.put("八字", almanacData.getBaZi());
		map.put("回历", almanacData.getIslamic());
		map.put("儒略日", almanacData.getJulianDay());
		map.put("黄帝纪年", almanacData.getChronology());
		map.put("生肖", almanacData.getZodiac());
		map.put("地点", almanacData.getPosition());
		map.put("节假日", almanacData.getHolidayVacations());
		map.put("经度", almanacData.getLongitude());
		map.put("纬度", almanacData.getLatitude());
		map.put("时区", almanacData.getTimeZone());
		map.put("港口", almanacData.getPortName());
		map.put("昼长", almanacData.getDiurnalTime());
		map.put("夜长", almanacData.getNightTime());
		map.put("天亮", almanacData.getDawnTime());
		map.put("日出", almanacData.getSunriseTime());
		map.put("中天", almanacData.getMidDayTime());
		map.put("日落", almanacData.getSunsetTime());
		map.put("天黑", almanacData.getDarkTime());
		map.put("月出", almanacData.getMoonOutTime());
		map.put("月中", almanacData.getMidMoonTime());
		map.put("月落", almanacData.getMoonDownTime());
		map.put("月相", almanacData.getMoonPhase());
		map.put("月天数", almanacData.getLunarDays());
		map.put("大月否", almanacData.isLunarBigMonth());
		map.put("闰月否", almanacData.isLeapMonth());
		map.put("闰年否", almanacData.isLeapYear());
		map.put("星座", almanacData.getConstellation());
		map.put("节气", almanacData.getNextSolarTerm());
		map.put("指定节气", almanacData.getSolarTerm("秋分"));
		map.put("24节气", almanacData.getAllSolarTerm()[23]);
		return map;
	}

}
