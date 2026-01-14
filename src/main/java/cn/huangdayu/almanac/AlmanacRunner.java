package cn.huangdayu.almanac;

import cn.huangdayu.almanac.dto.TimeZoneDTO;

import java.util.GregorianCalendar;

/**
 * @author huangdayu at 2024/7/21 create
 */
public class AlmanacRunner {
    public static void main(String[] args) {
        AlmanacService almanacService = new AlmanacService(new TimeZoneDTO("广东省", "徐闻县", new GregorianCalendar()));
        almanacService.dayCalendar().getAllInfo().forEach((k, v) -> System.out.println(k + " : " + v));

    }
}
