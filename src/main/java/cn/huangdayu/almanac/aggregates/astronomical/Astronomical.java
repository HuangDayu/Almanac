package cn.huangdayu.almanac.aggregates.astronomical;

import cn.huangdayu.almanac.aggregates.BaseAlmanac;
import cn.huangdayu.almanac.utils.AstronomyArithmeticUtils;
import cn.huangdayu.almanac.utils.CommonUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * 天文历
 *
 * @author huangdayu create at 2021/2/17 20:35
 */
@Getter
@Setter
public class Astronomical extends BaseAlmanac {

    public Astronomical(int firstJulianDayOfMonth) {
        // 计算世界时与原子时之差
        double jd2 = firstJulianDayOfMonth + CommonUtils.dtT(firstJulianDayOfMonth) - (double) 8 / 24;

        // 太阳视黄经
        this.solarRetina = AstronomyArithmeticUtils.S_aLon(jd2 / 36525, 3);
        this.solarRetina = (int) Math.floor((solarRetina - 0.13) / CommonUtils.PI_2 * 24) * CommonUtils.PI_2 / 24;

        // 月日视黄经
        this.lunarRetina = AstronomyArithmeticUtils.MS_aLon(jd2 / 36525, 10, 3);
        this.lunarRetina = (int) Math.floor((this.lunarRetina - 0.78) / Math.PI * 2) * Math.PI / 2;
    }

    /**
     * 太阳视黄经
     */
    private double solarRetina;

    /**
     * 月视黄经
     */
    private double lunarRetina;

    @Override
    public String getInfo() {
        return solarRetina + " " + lunarRetina;
    }
}
