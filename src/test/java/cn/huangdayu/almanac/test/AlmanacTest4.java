package cn.huangdayu.almanac.test;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import cn.huangdayu.almanac.dto.AlmanacDTO;
import cn.huangdayu.almanac.dto.TimeZoneDTO;
import cn.huangdayu.almanac.utils.AlmanacUtils;
import cn.huangdayu.almanac.utils.DateTimeUtils;

public class AlmanacTest4 {

    public static void main(String[] args) {

        TimeZoneDTO timeZoneDTO1 = new TimeZoneDTO("广东省徐闻县", "2021-12-12 11:10:10");

//        TimeZoneDTO timeZoneDTO1 = new TimeZoneDTO("广东省徐闻县", Calendar.getInstance());

//        TimeZoneDTO timeZoneDTO2 = new TimeZoneDTO("广东省徐闻县", "1995-08-12 11:10:10");

//        TimeZoneDTO timeZoneDTO3 = new TimeZoneDTO("广东省徐闻县", "1-1-1 11:10:10");

//        TimeZoneDTO timeZoneDTO3 = new TimeZoneDTO("广东省徐闻县", "2021-02-01 11:10:10");

        AlmanacDTO[] almanacDTOS = AlmanacUtils.monthCalendar(timeZoneDTO1);
        for (AlmanacDTO almanacDTO : almanacDTOS) {
            TestUtils.pakMap(almanacDTO);
        }
        for (Map.Entry<String, String> entry : TestUtils.MAP.entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());
        }
    }

}
