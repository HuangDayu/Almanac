package cn.huangdayu.almanac.test;

import cn.huangdayu.almanac.Almanac;
import cn.huangdayu.almanac.dto.AlmanacDTO;
import cn.huangdayu.almanac.dto.TimeZoneDTO;

import java.util.GregorianCalendar;import java.util.HashMap;import java.util.LinkedHashMap;import java.util.Map;

/**
 * @author huangdayu at 2024/7/21 create
 */
public class AlmanacRunner {
    public static void main(String[] args) {
        // 构造历法对象
        Almanac almanac = new Almanac(new TimeZoneDTO("广东省", "徐闻县", new GregorianCalendar()));
        // 获取当天的历法信息
        almanac.dayAlmanac().getAllInfo().forEach((k, v) -> System.out.println(k + " : " + v));
        // 获取当月的历法信息
        for (AlmanacDTO almanacDTO : almanac.monthAlmanac()) {
            almanacDTO.getAllInfo().forEach((k, v) -> System.out.println(k + " : " + v));
        }
        // 获取当年的历法信息
        for (AlmanacDTO[] almanacDTOS : almanac.yearAlmanac()) {
            for (AlmanacDTO almanacDTO : almanacDTOS) {
                almanacDTO.getAllInfo().forEach((k, v) -> System.out.println(k + " : " + v));
            }
        }
    }
}
