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
		 * bug:ʱ�䲻�ܽ��빫Ԫǰ
		 */
		
		String[] key_DayTimeType = { "����", "ʱ��", "����", "�ص�", "����", "γ��", "ʱ��", "�糤", "ҹ��", "����", "�ճ�", "����", "����", "���",
				"�³�", "����", "����", "�ۿ�", "������", "��֧��", "��֧��", "��֧��", "��֧ʱ", "�Ƶۼ���", "��Ф", "ũ����", "ũ����", "ũ����", "������",
				"���·�", "���·�", "�����", "������", "������", "������", "����", "����","���","��֧","����","��˹����","���","�ڼ���","����","ũ��" };

		timeMapList.addDataMapList("�㶫", "����", time);

		timeMapList.printDataMapList(key_DayTimeType);

	}

}
