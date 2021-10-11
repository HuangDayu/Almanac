package cn.huangdayu.almanac.test;

import cn.huangdayu.almanac.aggregates.solar_term.SolarTerm;
import cn.huangdayu.almanac.utils.SolarTermUtils;

/**
 * @author huangdayu create at 2021/1/23 14:36
 */
public class SolarTermsTest {
    public static void main(String[] args) {
        for (SolarTerm solarTerm : SolarTermUtils.getSolarTermsByYear(1995)) {
            System.out.println(solarTerm);
        }
    }
}
