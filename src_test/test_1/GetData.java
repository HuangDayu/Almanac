package test_1;

public class GetData {
	public static void main(String[] args) {
		System.out.println(CaculateWeekDay(1995, 8, 12));
		System.out.println(CaculateWeekDay(2017, 10, 17));
	}
	
	/***
	 * ��ķ����ɭ���㹫ʽ: W= (d+2*m+3*(m+1)/5+y+y/4-y/100+y/400) mod 7
	 * �ڹ�ʽ��d��ʾ�����е�������m��ʾ�·�����y��ʾ������ע�⣺�ڹ�ʽ���и���������ʽ��ͬ�ĵط���
	 * ��һ�ºͶ��¿�������һ���ʮ���º�ʮ���£����������2004-1-10����ɣ�2003-13-10�����빫ʽ���㡣
	 * @param y
	 * @param m
	 * @param d
	 * @return
	 */
	static String CaculateWeekDay(int y, int m, int d) {
		if (m == 1 || m == 2) {
			m += 12;
			y--;
		}
		int week = (d + 2 * m + 3 * (m + 1) / 5 + y + y / 4 - y / 100 + y / 400 + 1) % 7;
		String weekstr = "";
		switch (week) {
		case 1:
			weekstr = "����һ";
			break;
		case 2:
			weekstr = "���ڶ�";
			break;
		case 3:
			weekstr = "������";
			break;
		case 4:
			weekstr = "������";
			break;
		case 5:
			weekstr = "������";
			break;
		case 6:
			weekstr = "������";
			break;
		case 0:
			weekstr = "������";
			break;
		}
		return weekstr;
	}

	/***
	 * ��ķ����ɭ���㹫ʽ: W= (d+2*m+3*(m+1)/5+y+y/4-y/100+y/400) mod 7
	 * �ڹ�ʽ��d��ʾ�����е�������m��ʾ�·�����y��ʾ������ע�⣺�ڹ�ʽ���и���������ʽ��ͬ�ĵط���
	 * ��һ�ºͶ��¿�������һ���ʮ���º�ʮ���£����������2004-1-10����ɣ�2003-13-10�����빫ʽ���㡣
	 * 
	 * @param y
	 * @param m
	 * @param d
	 * @return
	 */
	public static String weekstr(int y, int m, int d) { // y=2014 m=14 d=9
		if (m == 1) {
			m = 13;
			y--;
		}
		if (m == 2) {
			m = 14;
			y--;
		}
		int week = (d + 2 * m + 3 * (m + 1) / 5 + y + y / 4 - y / 100 + y / 400) % 7;
		String weekstr = "";
		switch (week) {
		case 0:
			weekstr = "������";
			break;
		case 1:
			weekstr = "����һ";
			break;
		case 2:
			weekstr = "���ڶ�";
			break;
		case 3:
			weekstr = "������";
			break;
		case 4:
			weekstr = "������";
			break;
		case 5:
			weekstr = "������";
			break;
		case 6:
			weekstr = "������";
			break;

		}
		return weekstr;
	}

}
