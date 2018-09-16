package test_4;
/***
 * http://bbs.csdn.net/topics/70277519
 * * 这不是基姆拉尔森计算公式: W= (d+2*m+3*(m+1)/5+y+y/4-y/100+y/400) mod 7
	 * 对比许剑伟先生的寿星天文历，和刘国安先生的日梭万年历
	 * 经认证：基姆拉尔森计算公式适合时间不长，大概1000年，时间过大过小是不准确
	 * 这里用CSDN网友推算出来的公式，可用1583年到5582年，年限越往前时，与许剑伟先生的寿星万年历有差误
	 * 源码地址：http://bbs.csdn.net/topics/70277519#new_post
 * @author Administrator
 *
 */
class Week {
	
	public int GetWeek(int y, int m, int d) {
		String week[] = new String[] { "星期日"  ,"星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
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
		String week[] = new String[] { "星期日"  ,"星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

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
	 * 基姆拉尔森计算公式: W= (d+2*m+3*(m+1)/5+y+y/4-y/100+y/400) mod 7
	 * 在公式中d表示日期中的日数，m表示月份数，y表示年数。注意：在公式中有个与其他公式不同的地方：
	 * 把一月和二月看成是上一年的十三月和十四月，例：如果是2004-1-10则换算成：2003-13-10来代入公式计算。
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
		String[] strWay = { "日", "一", "二", "三", "四", "五", "六" };
		
		return "星期"+strWay[week];
	}
	
}
