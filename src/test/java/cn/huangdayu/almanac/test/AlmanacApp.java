package cn.huangdayu.almanac.test;

import cn.huangdayu.almanac.Almanac;
import cn.huangdayu.almanac.dto.AlmanacDTO;
import cn.huangdayu.almanac.dto.TimeZoneDTO;

import java.util.Map;

/**
 * @author huangdayu create at 2021/1/25 10:08
 */
public class AlmanacApp extends Almanac {

    public static void main(String[] args) {
        Almanac almanac = new AlmanacApp();
        AlmanacDTO almanacDTO = almanac.yearCalendar()[7][11];
        TestUtils.pakMap(almanacDTO);
        for (Map.Entry<String, String> entry : TestUtils.MAP.entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());
        }
//        for (SolarTermDTO solarTermDTO : almanacDTO.getSolarTermDTO().getNext()) {
//            System.out.println(solarTermDTO);
//        }
    }

    @Override
    public TimeZoneDTO initTimeZone() {
        return new TimeZoneDTO("广东省徐闻县", "2021-01-29 11:13:29");
    }
}
