package com.alamnac.main;
import com.alamnac.lunar.CalendarTime;
import com.alamnac.lunar.DataMapList;


/***
 * 
 * @author Administrator
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		
		// CalendarTime time = new CalendarTime(1995,5, 14, 11, 22, 33, 44);
		// CalendarTime time = new CalendarTime(-101, 1, 1, 11, 22, 33, 44);
		// CalendarTime time = new CalendarTime(558, 1, 1, 11, 22, 33, 44);
		// CalendarTime time = new CalendarTime(1583, 1, 1, 11, 22, 33, 44);
		// CalendarTime time = new CalendarTime(5582, 1, 1, 11, 22, 33, 44);
		
		//CalendarTime time = new CalendarTime(1, 12, 3, 11, 22, 33, 44);
		
		CalendarTime time = new CalendarTime();
		
		DataMapList timeMapList = new DataMapList();
		
		/**
		 * bug:时间不能进入公元前
		 */
		
		String[] key_DayTimeType = { "日期", "时间", "星期", "地点", "经度", "纬度", "时区", "昼长", "夜长", "天亮", "日出", "中天", "日落", "天黑",
				"月出", "月中", "月落", "港口", "儒略日", "干支年", "干支月", "干支日", "干支时", "黄帝纪年", "生肖", "农历年", "农历月", "农历日", "月天数",
				"大月否", "闰月否", "闰年否", "回历年", "回历月", "回历日", "星座", "节气","天干","地支","八字","伊斯兰历","年号","节假日","月相","农历" };

		timeMapList.addDataMapList("广东", "广州", time);

		timeMapList.printDataMapList(key_DayTimeType);

	}

}
