package com.almanac.main;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.almanac.lunar.AlmanacData;
import com.almanac.lunar.AlmanacDataImpl;
import com.almanac.lunar.DataBean;

public class ReadMe {

	public static void main(String[] args) {
		// DataBean dataBean = new DataBean(1995,8, 12, 11, 22, 33, 44);
		// DataBean dataBean = new DataBean(-101, 1, 1, 11, 22, 33, 44);
		// DataBean dataBean = new DataBean(558, 1, 1, 11, 22, 33, 44);
		// DataBean dataBean = new DataBean(1583, 1, 1, 11, 22, 33, 44);
		// DataBean dataBean = new DataBean(5582, 1, 1, 11, 22, 33, 44);
		// DataBean dataBean = new DataBean(1, 1, 1, 11, 22, 33, 44);

		// DataBean dataBean = new DataBean(2018, 9, 7, 17, 36, 33,1001);

		// DataBean dataBean = new DataBean("广东省徐闻县",Calendar.getInstance());

		// DataBean dataBean = new DataBean(new Date());

		// DataBean dataBean = new DataBean(System.currentTimeMillis());

		//DataBean dataBean = new DataBean("广东省徐闻县",new Date().toInstant());

		// DataBean dataBean = new DataBean("2018-09-07T18:40:54.063Z");

		// DataBean dataBean = new DataBean("2018-09-07 09:24:54.666");

		// DataBean dataBean = new DataBean(2018, 9, 7, 17, 36, 33,1001);

		// DataBean dataBean = new DataBean(2018, 9, 7, 17, 36, 33);

		// DataBean dataBean = new DataBean();

		// DataBean dataBean = new DataBean("2018年09月07日 18:24:54");

		//DataBean dataBean = new DataBean("广东省徐闻县","2018-09-20 18:24:54");
		
		//DataBean dataBean = new DataBean("广东省徐闻县","2018-09-20 18:24");
		
		//DataBean dataBean = new DataBean("广东省广州市","2018年09月10日 14:12");
		
		//DataBean dataBean = new DataBean("广东省徐闻县","2018-09-20 18:24:54.46");

		// DataBean dataBean = new DataBean("广东省", "徐闻","2018-09-07 18:24:54");

		//DataBean dataBean = new DataBean("广东省广州市白云区", 1995, 8, 12, 11, 22, 33, 44);

		DataBean dataBean1 = new DataBean("广东省徐闻县",Calendar.getInstance());

		DataBean dataBean2 = new DataBean("广东省徐闻县","1995-08-12 11:10:10");

		DataBean dataBean3 = new DataBean("广东省徐闻县","1-1-1 11:10:10");

		pakMap(new AlmanacDataImpl(dataBean1),new AlmanacDataImpl(dataBean2),new AlmanacDataImpl(dataBean3)).forEach((K, V) -> {
			System.out.println("| "+K  + V + " |");
		});
	}

	public static Map<String, String> pakMap(AlmanacData almanacData,AlmanacData almanacData2,AlmanacData almanacData3) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("日期", " | "+almanacData.getDate()+" | "+almanacData2.getDate()+" | "+almanacData3.getDate());
		map.put("时间", " | "+almanacData.getTime()+" | "+almanacData2.getTime()+" | "+almanacData3.getTime());
		map.put("星期", " | "+almanacData.getWeek()+" | "+almanacData2.getWeek()+" | "+almanacData3.getWeek());
		map.put("年号", " | "+almanacData.getYearNumber()+" | "+almanacData2.getYearNumber()+" | "+almanacData3.getYearNumber());
		map.put("农历", " | "+almanacData.getLunar()+" | "+almanacData2.getLunar()+" | "+almanacData3.getLunar());
		map.put("时辰", " | "+almanacData.getLunarTime()+" | "+almanacData2.getLunarTime()+" | "+almanacData3.getLunarTime());
		map.put("黄历", " | "+almanacData.getHuangLi()+" | "+almanacData2.getHuangLi()+" | "+almanacData3.getHuangLi());
		map.put("天干", " | "+almanacData.getTianGan()+" | "+almanacData2.getTianGan()+" | "+almanacData3.getTianGan());
		map.put("地支", " | "+almanacData.getDiZhi()+" | "+almanacData2.getDiZhi()+" | "+almanacData3.getDiZhi());
		map.put("八字", " | "+almanacData.getBaZi()+" | "+almanacData2.getBaZi()+" | "+almanacData3.getBaZi());
		map.put("回历", " | "+almanacData.getIslamic()+" | "+almanacData2.getIslamic()+" | "+almanacData3.getIslamic());
		map.put("儒略日", " | "+almanacData.getJulianDay()+" | "+almanacData2.getJulianDay()+" | "+almanacData3.getJulianDay());
		map.put("黄帝纪年", " | "+almanacData.getChronology()+" | "+almanacData2.getChronology()+" | "+almanacData3.getChronology());
		map.put("生肖", " | "+almanacData.getZodiac()+" | "+almanacData2.getZodiac()+" | "+almanacData3.getZodiac());
		map.put("地点", " | "+almanacData.getPosition()+" | "+almanacData2.getPosition()+" | "+almanacData3.getPosition());
		map.put("节假日", " | "+almanacData.getHolidayVacations()+" | "+almanacData2.getHolidayVacations()+" | "+almanacData3.getHolidayVacations());
		map.put("经度", " | "+almanacData.getLongitude()+" | "+almanacData2.getLongitude()+" | "+almanacData3.getLongitude());
		map.put("纬度", " | "+almanacData.getLatitude()+" | "+almanacData2.getLatitude()+" | "+almanacData3.getLatitude());
		map.put("时区", " | "+almanacData.getTimeZone()+" | "+almanacData2.getTimeZone()+" | "+almanacData3.getTimeZone());
		map.put("港口", " | "+almanacData.getPortName()+" | "+almanacData2.getPortName()+" | "+almanacData3.getPortName());
		map.put("昼长", " | "+almanacData.getDiurnalTime()+" | "+almanacData2.getDiurnalTime()+" | "+almanacData3.getDiurnalTime());
		map.put("夜长", " | "+almanacData.getNightTime()+" | "+almanacData2.getNightTime()+" | "+almanacData3.getNightTime());
		map.put("天亮", " | "+almanacData.getDawnTime()+" | "+almanacData2.getDawnTime()+" | "+almanacData3.getDawnTime());
		map.put("日出", " | "+almanacData.getSunriseTime()+" | "+almanacData2.getSunriseTime()+" | "+almanacData3.getSunriseTime());
		map.put("中天", " | "+almanacData.getMidDayTime()+" | "+almanacData2.getMidDayTime()+" | "+almanacData3.getMidDayTime());
		map.put("日落", " | "+almanacData.getSunsetTime()+" | "+almanacData2.getSunsetTime()+" | "+almanacData3.getSunsetTime());
		map.put("天黑", " | "+almanacData.getDarkTime()+" | "+almanacData2.getDarkTime()+" | "+almanacData3.getDarkTime());
		map.put("月出", " | "+almanacData.getMoonOutTime()+" | "+almanacData2.getMoonOutTime()+" | "+almanacData3.getMoonOutTime());
		map.put("月中", " | "+almanacData.getMidMoonTime()+" | "+almanacData2.getMidMoonTime()+" | "+almanacData3.getMidMoonTime());
		map.put("月落", " | "+almanacData.getMoonDownTime()+" | "+almanacData2.getMoonDownTime()+" | "+almanacData3.getMoonDownTime());
		map.put("月相", " | "+almanacData.getMoonPhase()+" | "+almanacData2.getMoonPhase()+" | "+almanacData3.getMoonPhase());
		map.put("月天数", " | "+almanacData.getLunarDays()+" | "+almanacData2.getLunarDays()+" | "+almanacData3.getLunarDays());
		map.put("大月否", " | "+almanacData.isLunarBigMonth()+" | "+almanacData2.isLunarBigMonth()+" | "+almanacData3.isLunarBigMonth());
		map.put("闰月否", " | "+almanacData.isLeapMonth()+" | "+almanacData2.isLeapMonth()+" | "+almanacData3.isLeapMonth());
		map.put("闰年否", " | "+almanacData.isLeapYear()+" | "+almanacData2.isLeapYear()+" | "+almanacData3.isLeapYear());
		map.put("星座", " | "+almanacData.getConstellation()+" | "+almanacData2.getConstellation()+" | "+almanacData3.getConstellation());
		map.put("下一节气", " | "+almanacData.getNextSolarTerm()+" | "+almanacData2.getNextSolarTerm()+" | "+almanacData3.getNextSolarTerm());
		map.put("春分", " | "+almanacData.getSolarTerm("春分")+" | "+almanacData2.getSolarTerm("春分")+" | "+almanacData3.getSolarTerm("春分"));
		map.put("夏至", " | "+almanacData.getSolarTerm("夏至")+" | "+almanacData2.getSolarTerm("夏至")+" | "+almanacData3.getSolarTerm("夏至"));
		map.put("秋分", " | "+almanacData.getSolarTerm("秋分")+" | "+almanacData2.getSolarTerm("秋分")+" | "+almanacData3.getSolarTerm("秋分"));
		map.put("冬至", " | "+almanacData.getSolarTerm("冬至")+" | "+almanacData2.getSolarTerm("冬至")+" | "+almanacData3.getSolarTerm("冬至"));
		return map;
	}

}
