package test_1;

public class GetData {
	public static void main(String[] args) {
		System.out.println(CaculateWeekDay(1995, 8, 12));
		System.out.println(CaculateWeekDay(2017, 10, 17));
	}
	
	/***
	 * 基姆拉尔森计算公式: W= (d+2*m+3*(m+1)/5+y+y/4-y/100+y/400) mod 7
	 * 在公式中d表示日期中的日数，m表示月份数，y表示年数。注意：在公式中有个与其他公式不同的地方：
	 * 把一月和二月看成是上一年的十三月和十四月，例：如果是2004-1-10则换算成：2003-13-10来代入公式计算。
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
			weekstr = "星期一";
			break;
		case 2:
			weekstr = "星期二";
			break;
		case 3:
			weekstr = "星期三";
			break;
		case 4:
			weekstr = "星期四";
			break;
		case 5:
			weekstr = "星期五";
			break;
		case 6:
			weekstr = "星期六";
			break;
		case 0:
			weekstr = "星期日";
			break;
		}
		return weekstr;
	}

	/***
	 * 基姆拉尔森计算公式: W= (d+2*m+3*(m+1)/5+y+y/4-y/100+y/400) mod 7
	 * 在公式中d表示日期中的日数，m表示月份数，y表示年数。注意：在公式中有个与其他公式不同的地方：
	 * 把一月和二月看成是上一年的十三月和十四月，例：如果是2004-1-10则换算成：2003-13-10来代入公式计算。
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
			weekstr = "星期日";
			break;
		case 1:
			weekstr = "星期一";
			break;
		case 2:
			weekstr = "星期二";
			break;
		case 3:
			weekstr = "星期三";
			break;
		case 4:
			weekstr = "星期四";
			break;
		case 5:
			weekstr = "星期五";
			break;
		case 6:
			weekstr = "星期六";
			break;

		}
		return weekstr;
	}

}
