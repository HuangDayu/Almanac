package cn.huangdayu.almanac.test;

import cn.huangdayu.almanac.dto.AlmanacDTO;
import cn.huangdayu.almanac.dto.TimeZoneDTO;
import cn.huangdayu.almanac.utils.AlmanacUtils;

import java.util.Map;

public class AlmanacTest5 {

    public static void main(String[] args) {
        AlmanacDTO[] almanacDTOS = {
                // TODO 农历算法错误，不应该以立春作为农历的第一天，而是正月
                AlmanacUtils.ofDay(new TimeZoneDTO("广东省","徐闻县", "2021-02-02 11:14:48")),
                AlmanacUtils.ofDay(new TimeZoneDTO("广东省","徐闻县", "2021-02-03 23:30:48")),
                AlmanacUtils.ofDay(new TimeZoneDTO("广东省","徐闻县", "2021-02-04 01:45:48")),
                AlmanacUtils.ofDay(new TimeZoneDTO("广东省","徐闻县", "2021-02-12 03:59:48")),


//                AlmanacUtils.ofDay(new TimeZoneDTO("广东省","徐闻县", Calendar.getInstance())),
//                AlmanacUtils.ofDay(new TimeZoneDTO("广东省","徐闻县", "2018-11-01 11:06:48")),
//                AlmanacUtils.ofDay(new TimeZoneDTO("广东省","徐闻县", "1995-08-12 11:10:10")),
//                AlmanacUtils.ofDay(new TimeZoneDTO("广东省","徐闻县", "1-1-1 11:10:10"))
        };
        Pack pack = new Pack();
        for (AlmanacDTO almanacDTO : almanacDTOS) {
            pack.toMap(almanacDTO);
//            System.out.println(almanacDTO.getLunarDTO().toString());
        }
        for (Map.Entry<String, String> entry : pack.getMap().entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());
        }
//        for (AlmanacDTO almanacDTO : almanacDTOS) {
//            for (SolarTermDTO solarTermDTO : almanacDTO.getSolarTermDTO().getNext()) {
//                System.out.println(solarTermDTO);
//            }
//        }
    }



}
