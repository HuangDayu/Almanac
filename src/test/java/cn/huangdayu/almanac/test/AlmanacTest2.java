package cn.huangdayu.almanac.test;

import cn.huangdayu.almanac.dto.AlmanacDTO;
import cn.huangdayu.almanac.dto.TimeZoneDTO;
import cn.huangdayu.almanac.utils.AlmanacUtils;

import java.util.Map;

/**
 * @author huangdayu create at 2021/1/25 10:08
 */
public class AlmanacTest2 {
    public static void main(String[] args) {
        TimeZoneDTO timeZoneDTO2 = new TimeZoneDTO("广东省","徐闻县", "2021-01-29 11:13:29");
        AlmanacDTO almanacDTO = AlmanacUtils.ofYear(timeZoneDTO2)[7][11];
        TestUtils testUtils = new TestUtils();
        testUtils.pakMap(almanacDTO);
        for (Map.Entry<String, String> entry : testUtils.getMap().entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());
        }
//        for (SolarTermDTO solarTermDTO : almanacDTO.getSolarTermDTO().getNext()) {
//            System.out.println(solarTermDTO);
//        }
    }
}
