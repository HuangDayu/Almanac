package com.yibuwulianwang.almanac.main;
import com.yibuwulianwang.almanac.lunar.CalendarTime;
import com.yibuwulianwang.almanac.lunar.DataMapList;


/***
 * 
 * @author Administrator
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		
		 //CalendarTime time = new CalendarTime(1995,8, 12, 11, 22, 33, 44);
		// CalendarTime time = new CalendarTime(-101, 1, 1, 11, 22, 33, 44);
		// CalendarTime time = new CalendarTime(558, 1, 1, 11, 22, 33, 44);
		// CalendarTime time = new CalendarTime(1583, 1, 1, 11, 22, 33, 44);
		// CalendarTime time = new CalendarTime(5582, 1, 1, 11, 22, 33, 44);
		// CalendarTime time = new CalendarTime(1, 12, 3, 11, 22, 33, 44);
		//CalendarTime time = new CalendarTime(2017,2);
		
		CalendarTime time = new CalendarTime();
		
		DataMapList dataMapList = new DataMapList();
		
		/**
		 * bug:时间不能进入公元前
		 */
		
		String[] key_DayTimeType = { "日期", "时间", "星期", "地点", "经度", "纬度", "时区", "昼长", "夜长", "天亮", "日出", "中天", "日落", "天黑",
				"月出", "月中", "月落", "港口", "儒略日", "干支年", "干支月", "干支日", "干支时", "黄帝纪年", "生肖", "农历年", "农历月", "农历日", "月天数",
				"大月否", "闰月否", "闰年否", "回历年", "回历月", "回历日", "星座", "节气","天干","地支","八字","黄历","伊斯兰历","年号","节假日","月相","农历",
				"立春","雨水","惊蛰","春分","清明","谷雨","立夏","小满","芒种","夏至","小暑","大暑","立秋","处暑","白露","秋分","寒露","霜降","立冬","小雪","大雪","冬至","小寒","大寒"};

		dataMapList.addDataMapList("广东省", "深圳", time);

		dataMapList.printDataMapList(key_DayTimeType);
		
		System.out.println(dataMapList.returnDataVolue("立春"));
	}

}
