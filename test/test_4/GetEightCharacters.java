package test_4;

/*
* NEW一个Bazi类 传入时间可以得出生辰八字getbazi(); 
*/
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author 刘座山
 * @mail 361186482@qq.com
 */
public class GetEightCharacters {
	/***
	 * 天干
	 */
	public static final String tianGan[] = { "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸" };
	/***
	 * 地支
	 */
	public static final String diZhi[] = { "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥" };
	final static SolarTerm solarTerm = new SolarTerm();
	public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
	private Date date;

	int year = 0, yue = 0;

	public GetEightCharacters(CalendarTime calendar) {
		try {
			date = dateFormat.parse(calendar.getTimeDataStringForBazi());
		} catch (ParseException e) {
			System.out.println("八字类中的日期解释出异常");
			e.printStackTrace();
		}
	}

	/**
	 * 获取农历年 需要另加1900
	 */
	public int getyear() throws Exception {

		Date lichun = solarTerm.JQtest(date.getYear() - 3)[21];
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
	 * 取节气
	 */
	public static Date[] getjq(int year) throws Exception {
		Date d0[] = new Date[24];
		for (int i = 0; i < 24; i++) {

			if (i < 3) {
				d0[i] = solarTerm.JQtest(year - 1)[i + 21];
			} else {
				d0[i] = solarTerm.JQtest(year)[i - 3];
			}
		}
		return d0;
	}

	/**
	 * 获取农历月份 0~11
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
	 * 求年天干数
	 */
	public int getyeartg() throws Exception {
		int ts = (getyear() + 6) % 10;
		return ts;
	}

	/**
	 * 求年地支数
	 */
	public int getyeardz() throws Exception {
		int dz = getyear() % 12;
		return dz;
	}

	/**
	 * 获取年干支
	 */
	public String getYear_Ganzhi() throws Exception {
		String bz = "";
		bz = tianGan[getyeartg()] + diZhi[getyeardz()];
		return bz;
	}

	/**
	 * 求月天干数
	 */
	public int getyuetg() throws Exception {
		int ts = (getyear() * 12 + getyue() + 4) % 10;
		return ts;
	}

	/**
	 * 求月地支数
	 */
	public int getyuedz() throws Exception {
		int dz = (getyear() * 12 + getyue() + 2) % 12;
		return dz;
	}

	/**
	 * 获取月八字
	 */
	public String getMonth_Ganzhi() throws Exception {
		String gz = "";
		gz = tianGan[getyuetg()] + diZhi[getyuedz()];
		return gz;
	}

	/**
	 * 日期距离1900天数
	 */
	public int tian2(Date date) throws Exception {
		Date date2 = dateFormat.parse("1900-1-1 00:00:00");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long between_days = (time1 - time2) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 求日天干数
	 */
	public int getritg() throws Exception {
		int shi = date.getHours();
		if (shi == 23) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
			date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		}
		int ts = tian2(date) % 10;
		return ts;
	}

	/**
	 * 求日地支数
	 */
	public int getridz() throws Exception {
		int shi = date.getHours();
		if (shi == 23) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
			date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		}
		int dz = (tian2(date) + 10) % 12;
		return dz;
	}

	/**
	 * 获取日八字
	 */
	public String getDay_Ganzhi() throws Exception {
		String gz = "";
		gz = tianGan[getritg()] + diZhi[getridz()];
		return gz;
	}

	/**
	 * 求时间天干数
	 */
	public int getshitg() throws Exception {
		int shi = date.getHours();
		shi = (shi + 1) / 2;
		int ts = (shi + tian2(date) * 12) % 10;
		return ts;
	}

	/**
	 * 求时间地支数
	 */
	public int getshidz() throws Exception {
		int shi = date.getHours();
		shi = (shi + 1) / 2;
		int ds = shi % 12;
		return ds;
	}

	/**
	 * 获取时间八字
	 */
	public String getHours_Ganzhi() throws Exception {
		String gz = "";
		gz = tianGan[getshitg()] + diZhi[getshidz()];
		return gz;
	}

	/**
	 * 获取完整八字
	 */
	public String getbazi() throws Exception {
		String bz = getYear_Ganzhi() + getMonth_Ganzhi() + getDay_Ganzhi() + getHours_Ganzhi();
		return bz;
	}

	/**
	 * 获取完整八字
	 */
	public String getMyBaZi() throws Exception {
		String MyBaZi = getYear_Ganzhi() + "年" + getMonth_Ganzhi() + "月" + getDay_Ganzhi() + "日" + getHours_Ganzhi()
				+ "时";
		return MyBaZi;
	}

	/**
	 * 获取八字代数
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
