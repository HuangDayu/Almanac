package test_4;
/***
 * http://bbs.csdn.net/topics/70277519
 * * �ⲻ�ǻ�ķ����ɭ���㹫ʽ: W= (d+2*m+3*(m+1)/5+y+y/4-y/100+y/400) mod 7
	 * �Ա���ΰ������������������������������������������
	 * ����֤����ķ����ɭ���㹫ʽ�ʺ�ʱ�䲻�������1000�꣬ʱ������С�ǲ�׼ȷ
	 * ������CSDN������������Ĺ�ʽ������1583�굽5582�꣬����Խ��ǰʱ������ΰ�����������������в���
	 * Դ���ַ��http://bbs.csdn.net/topics/70277519#new_post
 * @author Administrator
 *
 */
class Week {
	
	public int GetWeek(int y, int m, int d) {
		String week[] = new String[] { "������"  ,"����һ", "���ڶ�", "������", "������", "������", "������"};
		if (m < 3) {
			m += 12;
			--y;
		}
		int w = (d + 1 + 2 * m + 3 * (m + 1) / 5 + y + (y >> 2) - y / 100 + y / 400) % 7;
		return w;
	}
}

public class TestWeek {
	public static void main(String[] args) {
		int y = 2005;
		int m = 1;
		int d = 1;

		Week t = new Week();
		String week[] = new String[] { "������"  ,"����һ", "���ڶ�", "������", "������", "������", "������"};

//		for (y = 400; y <= 667; y += 3) {
//			for (m = 1; m <= 12; ++m) {
//				String str = y + "-" + m + "-" + d + "\t" + week[t.GetWeek(y, m, d)];
//				System.out.println(str);
//			}
//		}
		String str =  week[t.GetWeek(5582, 1, 1)];
		System.out.print(str);
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
	static String CaculateWeekDay(int year,int month,int day) {
		int ys = year;
		int ms = month; 
		int ds = day;
		int week = 0;
		System.out.println("CalendarTime"+ys+","+ms+","+ds);
		String weekstr = null;
		if (ms == 1 || ms == 2) {
			ms += 12;
			ys--;
		}else {
			//return (int)((d+2*m+3*(m+1)/5+y+y/4-y/100+y/400)+1)%7;
			//W= (d+2*m+3*(m+1)/5+y+y/4-y/100+y/400) mod 7
			
			week = (int)(ds + 2 * ms + 3 * (ms + 1) / 5 + ys + ys / 4 - ys / 100 + ys / 400 + 1) % 7;
			
		}
		System.out.println("CalendarTime"+ys+","+ms+","+ds);
		String[] strWay = { "��", "һ", "��", "��", "��", "��", "��" };
		
		return "����"+strWay[week];
	}
	
}
