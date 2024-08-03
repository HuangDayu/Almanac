package cn.huangdayu.almanac;

import cn.huangdayu.almanac.dto.TimeZoneDTO;

import java.util.GregorianCalendar;

/**
 * @author huangdayu at 2024/7/21 create
 */
public class AlmanacRunner {
    public static void main(String[] args) {
        AlmanacApp almanacApp = new AlmanacApp(new TimeZoneDTO("广东省", "徐闻县", new GregorianCalendar()));

        almanacApp.dayCalendar().toMap().forEach((k, v) -> System.out.println(k + " : " + v));

    }
}
