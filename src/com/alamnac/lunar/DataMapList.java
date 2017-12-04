package com.alamnac.lunar;

import java.util.HashMap;
import java.util.Map;

/***
 * 
 * @author Administrator
 *
 */
public class DataMapList {
	
	Map<String, String> time_HashMapList = new HashMap<String, String>();

	String[] key_DayTimeType = { "日出", "日落", "中天", "天亮", "天黑", "昼长", "夜长", "月出", "月中", "月落", "经度", "纬度","地点",  "时区",
			"日期","时间","星期","港口", "儒略日", "干支年", "干支月", "干支日", "干支时", "黄帝纪年", "生肖", "农历年", "农历月", "农历日", "月天数", "大月否", "闰月否",
			"闰年否", "回历年", "回历月", "回历日", "星座","节气","天干","地支","八字","伊斯兰历","年号","节假日","月相","农历"};

	/***
	 * 
	 * 将数据加入Map泛型集合中
	 * 
	 * @param area
	 *            地址
	 * @param returnArea[1] 
	 * @param timeZone
	 *            时区
	 * @param y
	 *            年
	 * @param m
	 *            月
	 * @param d
	 *            日
	 * @param h
	 *            时
	 * @param mm
	 *            分
	 * @param s
	 *            秒
	 * @param ptme
	 *            Ptme类对象
	 * @param jw
	 *            经度
	 * @param wd
	 *            纬度
	 * @param sun
	 *            DayTime类对象
	 * @param moon
	 *            Moon类对象
	 * @throws Exception
	 */
	public  void addDataMapList(String province, String area, CalendarTime time){
		
		LunarCalendar calendar = new LunarCalendar(CalendarTime.setCalendarToDate(time));
		LunarDate lunarDate = new LunarDate(calendar.getLunarDateClassObj(),calendar);
		
		//LunarConstant lc = new LunarConstant();// 从外部类的静态方法中实例化内部类对象。
		//LunarConstant.LunarCalendar lunarCalendar = lc.new LunarCalendar(time);
		

		
		String[] returnArea = judgeArea(province,area);

		/***
		 * 初始化坐标,时间等信息
		 */
		SunAndMoon sumAndMoon = new SunAndMoon(returnArea[1],time);
		
		String[] map_Volue = {
				this.setVolueSplit(GetSetData.getSunRise()),
				this.setVolueSplit(GetSetData.getSunSet()),
				this.setVolueSplit(GetSetData.getMidTime()),
				this.setVolueSplit(GetSetData.getDawnTime()),
				this.setVolueSplit(GetSetData.getNightTime()),
				this.setVolueSplit(GetSetData.getDayTime()),
				this.setVolueSplit(GetSetData.getEveningTime()),
				this.setVolueSplit(GetSetData.getMoonRise()),
				this.setVolueSplit(GetSetData.getMoonMiddleTime()),
				this.setVolueSplit(GetSetData.getMoonSet()),
				this.setVolueSplit(GetSetData.getLongitude()),
				this.setVolueSplit(GetSetData.getLatitude()),

				returnArea[0]+ " " + returnArea[1].substring(4, returnArea[1].length()),
				
				CalendarTime.getStrTimeZone(),
				CalendarTime.getDataString(),
				CalendarTime.getTimeString(),
				CalendarTime.getWeekString(),
				
				GetPort.getProtName(sumAndMoon),
				
				String.valueOf(JulianCalendar.getJuLian(time)), 
				lunarDate.getChineseEra_Year_Obj(),
				lunarDate.getChineseEra_Month_Obj(),
				lunarDate.getChineseEra_Day_Obj(),
				lunarDate.getChineseEra_Time_Obj(),
				lunarDate.getLunar_king_Years_Obj() + "年",
				lunarDate.getLunarDate_Animal_Year()+"年", 
				lunarDate.getLunarYearString_Obj(), 
				lunarDate.getLunarMonthString_Obj()+"月",
				lunarDate.getLunarDayString_Obj(),
				lunarDate.getLunarDaySumInMonth_Obj() + "天",
				booleanToChinese(lunarDate.isBigLunarMonthBool_Obj()),
				booleanToChinese(lunarDate.isLeapMonthBool_Obj()),
				booleanToChinese(lunarDate.isLeapYearBool_Obj()),
				lunarDate.getIslamic_Year_Obj() + "年",
				lunarDate.getIslamic_Month_Obj() + "月", 
				lunarDate.getIslamic_Day_Obj() + "日",
				lunarDate.getConstellation_Obj(), 
				lunarDate.getNowOrNext24SolarTerm(),
				lunarDate.getChinaEra_TianGan_String(),
				lunarDate.getChinaEra_DiZhi_String(),
				lunarDate.getChinaGanZhiDateString_Obj(),
				lunarDate.getIslamicDate_String(),
				lunarDate.getLunarDate_China_Era_Year_Number(),
				nullToChinese(lunarDate.getAllDay_Name_Obj()),
				nullToChinese(lunarDate.getMoon_PhaseName_Obj()),
				lunarDate.getChinaLuanrDate_String()
				};
		for (int i = 0; i < map_Volue.length; i++) {
			this.myHashMapPut(key_DayTimeType[i], map_Volue[i]);
		}

	}

	/***
	 * 将true或者false转成中文
	 * 
	 * @param b
	 * @return
	 */
	public String booleanToChinese(boolean b) {
		if (!b) {
			return "否";
		} else {
			return "是";
		}
	}
	
	/***
	 * 将null或者""转成无
	 * 
	 * @param b
	 * @return
	 */
	public String nullToChinese(String str) {
		if (str == "") {
			return "无";
		} else if (str == null) {
			return "无";
		}else {
			return str;
		}
	}
	/***
	 * 处理地址的方法
	 * @param strsArea
	 * @param area
	 * @return
	 */
	public String[] judgeArea(String prov,String area) {
		String[] strsArea = null;
		String Str_1= null,Str_2= null;
		String[] province= {"天津","TJ","河北","HE","青海","QH","西藏","XZ","浙江","ZJ","重庆","CQ",
				"河南","HA","福建","FJ","贵州","GZ","广西","GX","江西","JX","新疆","XJ","甘肃","GS",
				"湖北","Hb","江苏","JS","辽宁","Ln","吉林","JL","安徽","AH","山西","sx","陕西","SN",
				"港澳臺","GAT","云南","YN","宁夏","NX","广东","GD","上海","SH","山东","SD","四川","SC",
				"湖南","HN","黑龙","HL","北京","BJ","内蒙","NM","海南","HI"};
		for (int i = 0; i < province.length; i++) {
			if(prov.contains(province[i])) {
				strsArea = SunAndMoon.propt.getProperty(province[i+1]).split(" ");// 根据给定正则表达式的匹配拆分此字符串。
				Str_1=strsArea[0];
				break;
			}
		}
		for (int i = 1; i < strsArea.length; i++) { // && place.equals(strsArea[i].substring(4, 6))
			if (strsArea[i].contains(area) && area.length() > 1) {
				Str_2=strsArea[i];
				break;
			}
			if(i==strsArea.length-1) {
				System.out.println("地址定位错误,改地址" + area + "不在" + Str_1 + "!");
				System.exit(0);
			}
		}
		String[] Strs= {Str_1,Str_2};
		
		return Strs;
	}

	/***
	 * 包装HashMap的put方法
	 * 
	 * @param key
	 * @param volue
	 */
	public void myHashMapPut(String key, String volue) {
		if (volue == "" || key == "") {
			return;
		} else {
			time_HashMapList.put(key, volue);
		}
	}

	/***
	 * 时间24小时化
	 * 
	 * @param str
	 * @return
	 */
	public String setVolueSplit(String str) {
		String newStr = "";
		if (str.contains(":") && !str.contains("年") && !str.contains("-")&& !str.contains("月")) {
			String[] strsArea = str.split(":");
			for (int i = 0; i < strsArea.length; i++) {
				if (Integer.valueOf(strsArea[i]) < 10) {
					strsArea[i] = "0" + strsArea[i];
				}
			}
			newStr = strsArea[0] + ":" + strsArea[1] + ":" + strsArea[2];
		} else {
			newStr = str;
		}
		return newStr;
	}

	/***
	 * 
	 * @param strsArea
	 *            正则表达式生成的地址配置文件数组
	 * @param place
	 *            自定义地址
	 * @param dml
	 *            DataMapList
	 * @param key
	 *            HashMap的key
	 * @param time
	 *            Time类的对象
	 * @param sun
	 *            Sun类的对象
	 * @param moon
	 *            Moon类的对象
	 * @throws Exception
	 */
	public void printDataMapList(String[] key_DayTimeType) {

		for (int j = 0; j < key_DayTimeType.length; j++) {
			String str = key_DayTimeType[j] + " : " + this.time_HashMapList.get(key_DayTimeType[j]);
			System.out.println(str);
		}
	}

}
