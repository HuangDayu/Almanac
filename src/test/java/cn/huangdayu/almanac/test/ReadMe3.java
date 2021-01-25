package cn.huangdayu.almanac.test;

import cn.huangdayu.almanac.dto.AlmanacDTO;
import cn.huangdayu.almanac.dto.SolarTermDTO;
import cn.huangdayu.almanac.dto.TimeZoneDTO;
import cn.huangdayu.almanac.utils.AlmanacUtils;
import cn.huangdayu.almanac.utils.DateTimeUtils;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReadMe3 {

    public static void main(String[] args) {
        AlmanacDTO[] almanacDTOS = {
//                AlmanacUtils.dayCalendar(new TimeZoneDTO("广东省徐闻县", Calendar.getInstance())),
                AlmanacUtils.dayCalendar(new TimeZoneDTO("广东省徐闻县", "2018-11-01 11:06:48")),
                AlmanacUtils.dayCalendar(new TimeZoneDTO("广东省徐闻县", "1995-08-12 11:10:10")),
                AlmanacUtils.dayCalendar(new TimeZoneDTO("广东省徐闻县", "1-1-1 11:10:10"))
        };
        for (AlmanacDTO almanacDTO : almanacDTOS) {
            TestUtils.pakMap(almanacDTO);
        }
        for (Map.Entry<String, String> entry : TestUtils.MAP.entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());
        }
//        for (AlmanacDTO almanacDTO : almanacDTOS) {
//            for (SolarTermDTO solarTermDTO : almanacDTO.getSolarTermDTO().getNext()) {
//                System.out.println(solarTermDTO);
//            }
//        }
    }



}
