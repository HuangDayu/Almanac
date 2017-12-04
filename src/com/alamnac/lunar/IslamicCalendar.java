package com.alamnac.lunar;
/***
 * ��˹���̵��������ֳ�ϣ�����������ҹ�Ҳ�лػ��������
 */
public class IslamicCalendar {
	/****
	 * �������㷽��
	 * @param julianDays
	 * @param date
	 */
	public static void setIslamicCalendar(int julianDays, LunarDate date) {
		// �����㷨ʹ��Excel���Եõ�,����ʱ��Ҫ�������ٽ������ٽ�
		int z, y, m, d;
		d = julianDays + 503105;
		z = (int) Math.floor(d / 10631); // 10631Ϊһ����(30��)
		d -= z * 10631;
		y = (int) Math.floor((d + 0.5) / 354.366); // ��0.5�������Ǳ�֤������ȷ(һ���е������ǵ�2,5,7,10,13,16,18,21,24,26,29��)
		d -= (int) Math.floor(y * 354.366 + 0.5);
		m = (int) Math.floor((d + 0.11) / 29.51); // ���Ӽ�0.11,��ĸ��0.01�������ǵ�354��355��ĵ��·ֱ���Ϊ12��(m=11)
		d -= (int) Math.floor(m * 29.5 + 0.5);
		
		date.setIslamic_Year(z * 30 + y + 1);
		date.setIslamic_Month(m + 1);
		date.setIslamic_Day(d + 1);
	}
}
