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

	String[] key_DayTimeType = { "�ճ�", "����", "����", "����", "���", "�糤", "ҹ��", "�³�", "����", "����", "����", "γ��","�ص�",  "ʱ��",
			"����","ʱ��","����","�ۿ�", "������", "��֧��", "��֧��", "��֧��", "��֧ʱ", "�Ƶۼ���", "��Ф", "ũ����", "ũ����", "ũ����", "������", "���·�", "���·�",
			"�����", "������", "������", "������", "����","����","���","��֧","����","��˹����","���","�ڼ���","����","ũ��"};

	/***
	 * 
	 * �����ݼ���Map���ͼ�����
	 * 
	 * @param area
	 *            ��ַ
	 * @param returnArea[1] 
	 * @param timeZone
	 *            ʱ��
	 * @param y
	 *            ��
	 * @param m
	 *            ��
	 * @param d
	 *            ��
	 * @param h
	 *            ʱ
	 * @param mm
	 *            ��
	 * @param s
	 *            ��
	 * @param ptme
	 *            Ptme�����
	 * @param jw
	 *            ����
	 * @param wd
	 *            γ��
	 * @param sun
	 *            DayTime�����
	 * @param moon
	 *            Moon�����
	 * @throws Exception
	 */
	public  void addDataMapList(String province, String area, CalendarTime time){
		
		LunarCalendar calendar = new LunarCalendar(CalendarTime.setCalendarToDate(time));
		LunarDate lunarDate = new LunarDate(calendar.getLunarDateClassObj(),calendar);
		
		//LunarConstant lc = new LunarConstant();// ���ⲿ��ľ�̬������ʵ�����ڲ������
		//LunarConstant.LunarCalendar lunarCalendar = lc.new LunarCalendar(time);
		

		
		String[] returnArea = judgeArea(province,area);

		/***
		 * ��ʼ������,ʱ�����Ϣ
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
				lunarDate.getLunar_king_Years_Obj() + "��",
				lunarDate.getLunarDate_Animal_Year()+"��", 
				lunarDate.getLunarYearString_Obj(), 
				lunarDate.getLunarMonthString_Obj()+"��",
				lunarDate.getLunarDayString_Obj(),
				lunarDate.getLunarDaySumInMonth_Obj() + "��",
				booleanToChinese(lunarDate.isBigLunarMonthBool_Obj()),
				booleanToChinese(lunarDate.isLeapMonthBool_Obj()),
				booleanToChinese(lunarDate.isLeapYearBool_Obj()),
				lunarDate.getIslamic_Year_Obj() + "��",
				lunarDate.getIslamic_Month_Obj() + "��", 
				lunarDate.getIslamic_Day_Obj() + "��",
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
	 * ��true����falseת������
	 * 
	 * @param b
	 * @return
	 */
	public String booleanToChinese(boolean b) {
		if (!b) {
			return "��";
		} else {
			return "��";
		}
	}
	
	/***
	 * ��null����""ת����
	 * 
	 * @param b
	 * @return
	 */
	public String nullToChinese(String str) {
		if (str == "") {
			return "��";
		} else if (str == null) {
			return "��";
		}else {
			return str;
		}
	}
	/***
	 * �����ַ�ķ���
	 * @param strsArea
	 * @param area
	 * @return
	 */
	public String[] judgeArea(String prov,String area) {
		String[] strsArea = null;
		String Str_1= null,Str_2= null;
		String[] province= {"���","TJ","�ӱ�","HE","�ຣ","QH","����","XZ","�㽭","ZJ","����","CQ",
				"����","HA","����","FJ","����","GZ","����","GX","����","JX","�½�","XJ","����","GS",
				"����","Hb","����","JS","����","Ln","����","JL","����","AH","ɽ��","sx","����","SN",
				"�۰��_","GAT","����","YN","����","NX","�㶫","GD","�Ϻ�","SH","ɽ��","SD","�Ĵ�","SC",
				"����","HN","����","HL","����","BJ","����","NM","����","HI"};
		for (int i = 0; i < province.length; i++) {
			if(prov.contains(province[i])) {
				strsArea = SunAndMoon.propt.getProperty(province[i+1]).split(" ");// ���ݸ���������ʽ��ƥ���ִ��ַ�����
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
				System.out.println("��ַ��λ����,�ĵ�ַ" + area + "����" + Str_1 + "!");
				System.exit(0);
			}
		}
		String[] Strs= {Str_1,Str_2};
		
		return Strs;
	}

	/***
	 * ��װHashMap��put����
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
	 * ʱ��24Сʱ��
	 * 
	 * @param str
	 * @return
	 */
	public String setVolueSplit(String str) {
		String newStr = "";
		if (str.contains(":") && !str.contains("��") && !str.contains("-")&& !str.contains("��")) {
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
	 *            ������ʽ���ɵĵ�ַ�����ļ�����
	 * @param place
	 *            �Զ����ַ
	 * @param dml
	 *            DataMapList
	 * @param key
	 *            HashMap��key
	 * @param time
	 *            Time��Ķ���
	 * @param sun
	 *            Sun��Ķ���
	 * @param moon
	 *            Moon��Ķ���
	 * @throws Exception
	 */
	public void printDataMapList(String[] key_DayTimeType) {

		for (int j = 0; j < key_DayTimeType.length; j++) {
			String str = key_DayTimeType[j] + " : " + this.time_HashMapList.get(key_DayTimeType[j]);
			System.out.println(str);
		}
	}

}
