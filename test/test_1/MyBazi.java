package test_1;

/*
* NEWһ��Bazi�� ����ʱ����Եó���������getbazi(); 
*/
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import test_3.*;

/**
 * @author ����ɽ
 * @mail 361186482@qq.com
 */
public class MyBazi {
	public static final String tg[] = { "��", "��", "��", "��", "��", "��", "��", "��", "��", "��" };
	public static final String dz[] = { "��", "��", "��", "î", "��", "��", "��", "δ", "��", "��", "��", "��" };
	final static SolarTerm_1 st = new SolarTerm_1();
	public static DateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
	private Date date = new Date();

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	int year = 0, yue = 0;

	public MyBazi() {
	}

	public MyBazi(Date d0) {
		date = d0;
	}

	/**
	 * ��ȡũ���� ��Ҫ����1900
	 */
	public int getyear() throws Exception {

		Date lichun = st.JQtest(date.getYear() - 3)[21];
		long lctime = lichun.getTime();
		long nowtime = date.getTime();
		if (nowtime >= lctime) {
			year = date.getYear();
		} else {
			year = date.getYear() - 1;
		}
		return year;
	}

	/**
	 * ȡ����
	 */
	public static Date[] getjq(int year) throws Exception {
		Date d0[] = new Date[24];
		for (int i = 0; i < 24; i++) {

			if (i < 3) {
				d0[i] = st.JQtest(year - 1)[i + 21];
			} else {
				d0[i] = st.JQtest(year)[i - 3];
			}
		}
		return d0;
	}

	/**
	 * ��ȡũ���·� 0~11
	 */
	public int getyue() throws Exception {
		int yue = 0;
		int year = getyear() + 1900;
		Date d0[] = getjq(year);
		long nowtime = date.getTime();
		for (int i = 0; i < 24; i++) {
			long jqtime = d0[i].getTime();
			if (nowtime > jqtime) {
				yue = i / 2;
			} else {
				break;
			}
		}
		return yue;
	}

	/**
	 * ���������
	 */
	public int getyeartg() throws Exception {
		int ts = (getyear() + 6) % 10;
		return ts;
	}

	/**
	 * �����֧��
	 */
	public int getyeardz() throws Exception {
		int dz = getyear() % 12;
		return dz;
	}

	/**
	 * ��ȡ���֧
	 */
	public String getyeargz() throws Exception {
		String bz = "";
		bz = tg[getyeartg()] + dz[getyeardz()];
		return bz;
	}

	/**
	 * ���������
	 */
	public int getyuetg() throws Exception {
		int ts = (getyear() * 12 + getyue() + 4) % 10;
		return ts;
	}

	/**
	 * ���µ�֧��
	 */
	public int getyuedz() throws Exception {
		int dz = (getyear() * 12 + getyue() + 2) % 12;
		return dz;
	}

	/**
	 * ��ȡ�°���
	 */
	public String getyuegz() throws Exception {
		String gz = "";
		gz = tg[getyuetg()] + dz[getyuedz()];
		return gz;
	}

	/**
	 * ���ھ���1900����
	 */
	public int tian2(Date date) throws Exception {
		Date date2 = df.parse("1900-1-1 00:00:00");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long between_days = (time1 - time2) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * ���������
	 */
	public int getritg() throws Exception {
		int shi = date.getHours();
		if (shi == 23) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(calendar.DATE, 1);// ��������������һ��.����������,������ǰ�ƶ�
			date = calendar.getTime(); // ���ʱ���������������һ��Ľ��
		}
		int ts = tian2(date) % 10;
		return ts;
	}

	/**
	 * ���յ�֧��
	 */
	public int getridz() throws Exception {
		int shi = date.getHours();
		if (shi == 23) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(calendar.DATE, 1);// ��������������һ��.����������,������ǰ�ƶ�
			date = calendar.getTime(); // ���ʱ���������������һ��Ľ��
		}
		int dz = (tian2(date) + 10) % 12;
		return dz;
	}

	/**
	 * ��ȡ�հ���
	 */
	public String getrigz() throws Exception {
		String gz = "";
		gz = tg[getritg()] + dz[getridz()];
		return gz;
	}

	/**
	 * ��ʱ�������
	 */
	public int getshitg() throws Exception {
		int shi = date.getHours();
		shi = (shi + 1) / 2;
		int ts = (shi + tian2(date) * 12) % 10;
		return ts;
	}

	/**
	 * ��ʱ���֧��
	 */
	public int getshidz() throws Exception {
		int shi = date.getHours();
		shi = (shi + 1) / 2;
		int ds = shi % 12;
		return ds;
	}

	/**
	 * ��ȡʱ�����
	 */
	public String getshigz() throws Exception {
		String gz = "";
		gz = tg[getshitg()] + dz[getshidz()];
		return gz;
	}

	/**
	 * ��ȡ��������
	 */
	public String getbazi() throws Exception {
		String bz = getyeargz() + getyuegz() + getrigz() + getshigz();
		return bz;
	}

	/**
	 * ��ȡ��������
	 */
	public String getMyBaZi() throws Exception {
		String MyBaZi = getyeargz() + "��" + getyuegz() + "��" + getrigz() + "��" + getshigz() + "ʱ";
		return MyBaZi;
	}

	/**
	 * ��ȡ���ִ���
	 */
	public int[] getbazishu() throws Exception {
		int bzs[] = new int[8];
		bzs[0] = getyeartg();
		bzs[1] = getyeardz();
		bzs[2] = getyuetg();
		bzs[3] = getyuedz();
		bzs[4] = getritg();
		bzs[5] = getridz();
		bzs[6] = getshitg();
		bzs[7] = getshidz();
		return bzs;
	}
}