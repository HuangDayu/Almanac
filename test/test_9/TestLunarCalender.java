package test_9;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import test_9.LunarConstant.XL;
import test_8.LunarConstant.*;

public class TestLunarCalender {
	/***
	 * ָ��Date��ʱ��  3������
	 * http://blog.sina.com.cn/s/blog_4550f3ca0101t042.html
	 */
	public static Date getCalendarToDate(int y, int M, int d) {
		String str = y + "-" + M + "-" + d;
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH");
		Date myDate1 = null;
		try {
			myDate1 = dateFormat1.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return myDate1;
	}
	
	/***
	 * ָ��Date��ʱ��  6������
	 * @param y ��
	 * @param M ��
	 * @param d ��
	 * @param h ʱ
	 * @param m ��
	 * @param s ��
	 * @return Date����
	 */
	public static Date getCalendarToDate(int y, int M, int d,int h,int m,int s) {
		String str = y + "-" + M + "-" + d+" "+h+":"+m+":"+s;
		//System.out.println(str);
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date myDate1 = null;
		try {
			myDate1 = dateFormat1.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return myDate1;
	}

	public static void main(String[] args) {
		LunarConstant lc = new LunarConstant();//���ⲿ��ľ�̬������ʵ�����ڲ������
		LunarConstant.LunarCalendar calendar = lc.new LunarCalendar(getCalendarToDate(1995, 8,12, 11, 22, 33));
		//LunarCalendar calendar = new LunarCalendar();
		System.out.println("ũ����Ф=" + calendar.getAnimalString());
		System.out.println("��ȡũ�������ַ���=" + calendar.getDateString());
		System.out.println("ũ����=" + calendar.getDay());
		System.out.println("ũ�����ַ���=" + calendar.getDayString());
		System.out.println("��ȡ��֧ũ�������ַ���=" + calendar.getGanZhiDateString());
		System.out.println("ĳũ�����ж�����=" + calendar.getMaxDayInMonth());
		System.out.println("ũ����=" + calendar.getMonth());
		System.out.println("ũ�����ַ���=" + calendar.getMonthString());
		System.out.println("ũ����=" + calendar.getYear());
		System.out.println("ũ�����ַ���=" + calendar.getYearString());
		System.out.println("�Ƿ����=" + calendar.isBigMonth());
		System.out.println("�Ƿ�����=" + calendar.isLeap());
		System.out.println("�Ƿ�����=" + calendar.isLeapYear());
		
		
		System.out.println("��ȡĳһ���ũ������=" + calendar.getDayLunarDate().getClass().getName());
		System.out.println("��ȡĳ�µ�ũ������=" + calendar.getMonthLunarDates().getClass().getName());
		
		System.out.println("����ת�ٶȣ�"+XL.E_v(XL.getJulian_Shijishu()));
		System.out.println("�����ٶȣ�"+XL.M_v(XL.getJulian_Shijishu()));
		System.out.println("���򾭶ȼ��㣺"+XL.E_Lon(XL.getJulian_Shijishu(), 5));
		
		System.out.println("̫���ӻƾ���"+XL.S_aLon(XL.getJulian_Shijishu(), 5));
		
		System.out.println("�����꣺"+calendar.lunarDate.gethYear());
		System.out.println("�����£�"+calendar.lunarDate.gethMonth());
		System.out.println("�����գ�"+calendar.lunarDate.gethDay());
		System.out.println("�¶����죺"+calendar.lunarDate.getDaysOfMonth());
		System.out.println("���ڣ�"+calendar.lunarDate.getWeek());
		
		System.out.println("�����ƣ�"+calendar.lunarDate.getLunarMonthName());
		System.out.println("�����ƣ�"+calendar.lunarDate.getLunarDayName());
		System.out.println("ũ���´�С��"+calendar.lunarDate.getDaysofLunarMonth());
		System.out.println("�Ƿ����£�"+calendar.lunarDate.getLunarLunarLeap());
		
		System.out.println("��һ��ũ���£�"+calendar.lunarDate.getNextLunarMonthName());
		
		System.out.println("������"+calendar.lunarDate.getLunarSolarTerm());
		
		System.out.println("����һ��������������"+calendar.lunarDate.getDaysToDZ());
		
		System.out.println("������"+calendar.lunarDate.getConstellation());
		
		System.out.println("���ࣺ"+calendar.lunarDate.getMoonPhaseName());
		
		System.out.println("�������ƣ�"+calendar.lunarDate.getSolarTermName());
		
		System.out.println("�Ƶۼ��꣺"+calendar.lunarDate.getKingYear());
		
		
		
		System.out.println("�ϸ߾���˷��"+calendar.ssq.so_high(131334));
		
		System.out.println("�ż����ӣ�"+calendar.lunarDate.getImpName());
		System.out.println("�ż����ӣ�"+calendar.lunarDate.getImpHappyName());
		System.out.println("���գ�"+calendar.lunarDate.getAllName());
		System.out.println("�ż����ӣ�"+calendar.lunarDate.getHoliday());
		
		/***
		 * ��ȡĳ��Ķ�ʮ�Ľ�����Ϣ
		 */
       String[] strs = calendar.getAllSolarTerm();

		
		for (String str : strs) {
			System.out.println(str);
		}	
		
		System.out.println(calendar.getNowSolarTerm());
		
	}

}
