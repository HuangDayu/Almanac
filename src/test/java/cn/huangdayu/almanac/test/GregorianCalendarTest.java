package cn.huangdayu.almanac.test;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author huangdayu create at 2021/2/17 10:32
 */
public class GregorianCalendarTest {
    public static void main(String args[]) {
        GregorianCalendar calendar = new GregorianCalendar(10, 1, 1);
        for (int i = 0; i < 20; i++) {
            calendar.add(Calendar.YEAR, -1);
            System.out.println(calendar.get(Calendar.ERA) + " " + calendar.get(Calendar.YEAR));
        }
    }
}
