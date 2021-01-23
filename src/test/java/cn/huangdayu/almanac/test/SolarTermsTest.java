package cn.huangdayu.almanac.test;

import cn.huangdayu.almanac.dto.SolarTermDTO;
import cn.huangdayu.almanac.utils.SolarTermUtils;

/**
 * @author huangdayu create at 2021/1/23 14:36
 */
public class SolarTermsTest {
    public static void main(String[] args) {
        for (SolarTermDTO solarTermDTO : SolarTermUtils.getSolarTermsByYear(2018)) {
            System.out.println(solarTermDTO);
        }
    }
}
