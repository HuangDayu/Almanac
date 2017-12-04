package com.alamnac.lunar;
/***
 * �ڼ��ռ�����
 *
 */
public class FestivalAndHoliday {

	// ĳ�µĵڼ������ڼ�,���2������һָ�����׿�ʼ˳���ҵ���2��"����һ"
	public static final String[] wFtv = { "0150I���������", "0520.����ĸ�׽�", "0530Iȫ��������", "0630.���׽�", "0730.��ū�۹�����",
			"0932I���ʺ�ƽ��", "0940.�������˽� �����ͯ��", "0950I���纣����", "1011.����ס����", "1013I���ʼ�����Ȼ�ֺ���(������)", "1144I�ж���" };

	// ���ձ�,��initFestival��ʼ��
	public static String[][] sFtv;

	static {
		// ��������,#��ʾ�ż���,I��ʾ��Ҫ���ջ������
		String s = "01#Ԫ��|" // 1��
				+ "02I����ʪ����,10.���������,14I���˽�|" // 2��
				+ "01.���ʺ�����,03.ȫ��������,05.1963-9999ѧ�׷������,08I��Ů��,12Iֲ����,12.1925-9999����ɽ����������,14.���ʾ�����," // 3��
				+ "15I1983-9999������Ȩ����,17.�й���ҽ��,17.���ʺ�����,21.����ɭ����,21.�����������ӹ�����,21.���������,22I����ˮ��,"
				+ "23I����������,24.1982-9999������ν�˲���,25.ȫ����Сѧ����ȫ������,30.����˹̹������|"
				+ "01I1564-9999���˽�,01.ȫ�����������˶���(����),01.˰��������(����),07I����������,22I���������,23.����ͼ��Ͱ�Ȩ��,24.�Ƿ����Ź�������|" // 4��
				+ "01#1889-9999�Ͷ���,04I�����,05.��ȱ����������,08.�����ʮ����,12I���ʻ�ʿ��,15I���ʼ�ͥ��,17.���ʵ�����,18.���ʲ������," // 5��
				+ "20.ȫ��ѧ��Ӫ����,23.����ţ����,31I����������|"
				+ "01I1925-9999���ʶ�ͯ��,05.���绷��������,06.ȫ��������,17.���λ�Į���͸ɺ���,23.���ʰ���ƥ����,25.ȫ��������,26I���ʽ�����|" // 6��
				+ "01I1997-9999��ۻع������,01I1921-9999�й�����,01.���罨����,02.��������������,07I1937-9999����ս��������,11I�����˿���,30.���޸�Ů��|" // 7��
				+ "01I1927-9999������,08.�й����ӽ�(�ְֽ�)|" // 8��
				+ "03I1945-9999����ս��ʤ������,08.1966-9999����ɨä��,08.�������Ź�������,09.ë����������,10I�й���ʦ��,14.������������," // 9��
				+ "16.���ʳ����㱣����,18I�š�һ���±������,20.���ʰ�����,27.����������,28I���ӵ���|"
				+ "01#1949-9999�����,01.����������,01.�������˽�,02#1949-9999����ڼ���,02.���ʺ�ƽ���������ɶ�����,03#1949-9999����ڼ���," // 10��
				+ "04.���綯����,06.���˽�,08.ȫ����Ѫѹ��,08.�����Ӿ���,09.����������,09.���������,10I��������������,10.���羫��������,"
				+ "13.���籣����,13.���ʽ�ʦ��,14.�����׼��,15.����ä�˽�(�����Ƚ�),16.������ʳ��,17.��������ƶ����,22.���紫ͳҽҩ��,24.���Ϲ���,31.�����ڼ���|"
				+ "07.1917-9999ʮ������������������,08.�й�������,09.ȫ��������ȫ����������,10.���������,11.���ʿ�ѧ���ƽ��(����������һ��),12.����ɽ����������,"
				+ "14.����������,17.���ʴ�ѧ����,17.����ѧ����,20.������,21.������,21.�����ʺ���,21.���������,22.������,29.������Ԯ����˹̹���������|" // 11��
				+ "01I1988-9999���簬�̲���,03.����м�����,05.���ʾ��ú���ᷢչ־Ը��Ա��,08.���ʶ�ͯ������,09.����������,10.������Ȩ��," // 12��
				+ "12I�����±������,13I�Ͼ�����ɱ(1937��)������,20.���Żع����,21.����������,24Iƽ��ҹ,25Iʥ����,26.ë�󶫵�������";

		String[] s_Month = s.split("\\|");

		sFtv = new String[s_Month.length][];
		for (int i = 0; i < s_Month.length; i++) {
			sFtv[i] = s_Month[i].split(",");
		}
	}

	/**
	 * ȡĳ�ս���
	 * 
	 * @param date
	 * @param dateTwo
	 */
	public static void getDayName(LunarDate date, LunarDate dateTwo) {
		String impHappyName = dateTwo.getHappyDay_Name() == null ? "" : dateTwo.getHappyDay_Name();
		String impName = dateTwo.getMajorDay_Name() == null ? "" : dateTwo.getMajorDay_Name();
		String allName = dateTwo.getAllDay_Name() == null ? "" : dateTwo.getAllDay_Name();
		/****************
		 * ������������ ���������u ����ĳ�ս�����Ϣ 
		 * r.A ��Ҫϲ����������(�ɽ����������ú�) 
		 * r.B ��Ҫ��������
		 * r.C ������������(����һ��)
		 * r.Fjia �ż�����(���������������ú�)
		 *****************/
		String m0 = (date.getGregorian_Month() < 10 ? "0" : "") + date.getGregorian_Month();
		String d0 = (date.getGregorian_Day() < 10 ? "0" : "") + date.getGregorian_Day();
		String s, s2, type;

		if (date.getGregorian_Week() == 0 || date.getGregorian_Week() == 6)
		 {
			dateTwo.setHoliday_INT(1); // �����ջ��������ż�
		}

		// ���������ڲ���
		for (int i = 0; i < sFtv[date.getGregorian_Month() - 1].length; i++) { // �������ջ������,�������½��ձ�
			s = sFtv[date.getGregorian_Month() - 1][i];
			if (!Common.subString(s, 0, 2).equals(d0)) {
				continue;
			}
			s = Common.subString(s, 2);
			type = Common.subString(s, 0, 1);
			if (Common.subString(s, 5, 6).equals("-")) { // �����޵�
				if (date.getGregorian_Year() < Integer.parseInt(Common.subString(s, 1, 5))
						|| date.getGregorian_Year() > Integer.parseInt(Common.subString(s, 6, 10))) {
					continue;
				}
				s = Common.subString(s, 10);
			} else {
				if (date.getGregorian_Year() < 1850) {
					continue;
				}
				s = Common.subString(s, 1);
			}
			if (type.equals("#")) {
				impHappyName += s + " ";
				dateTwo.setHoliday_INT(1); // �żٵĽ���
			}
			if (type.equals("I"))
			 {
				impName += s + " "; // ��Ҫ
			}
			if (type.equals("."))
			 {
				allName += s + " "; // ����
			}
		}

		// ���ܲ���
		int w = date.getGregorian_WeekIndexForMonth();
		if (date.getGregorian_Week() >= date.getGregorian_WeekFirstForMonth()) {
			w += 1;
		}
		int w2 = w;
		if (date.getGregorian_WeekIndexForMonth() == date.getGregorian_WeeksOfMonth() - 1) {
			w2 = 5;
		}

		String wStr = m0 + w + date.getGregorian_Week(); // d���ڱ��µĵڼ�������ĳ
		String w2Str = m0 + w2 + date.getGregorian_Week();

		for (int i = 0; i < wFtv.length; i++) {
			s = wFtv[i];
			s2 = Common.subString(s, 0, 4);
			if (!s2.equals(wStr) && !s2.equals(w2Str)) {
				continue;
			}
			type = Common.subString(s, 4, 5);
			s = Common.subString(s, 5);
			if (type.equals("#")) {
				impHappyName += s + " ";
				dateTwo.setHoliday_INT(1);
			}
			if (type.equals("I")) {
				impName += s + " ";
			}
			if (type.equals(".")) {
				allName += s + " ";
			}
		}

		dateTwo.setHappyDay_Name(impHappyName);
		dateTwo.setMajorDay_Name(impName);
		dateTwo.setAllDay_Name(allName);
	}

}
