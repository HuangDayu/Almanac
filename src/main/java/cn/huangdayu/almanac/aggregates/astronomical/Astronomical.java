package cn.huangdayu.almanac.aggregates.astronomical;

import cn.huangdayu.almanac.utils.AstronomyArithmeticUtils;
import cn.huangdayu.almanac.utils.CommonUtils;
import lombok.Data;

@Data
public class Astronomical {

    public Astronomical(int firstJulianDayOfMonth) {
        // 计算世界时与原子时之差
        double jd2 = firstJulianDayOfMonth + CommonUtils.dtT(firstJulianDayOfMonth) - (double) 8 / 24;

        // 太阳视黄经
        this.sunSolarRetina = AstronomyArithmeticUtils.S_aLon(jd2 / 36525, 3);
        this.sunSolarRetina = (int) Math.floor((sunSolarRetina - 0.13) / CommonUtils.PI_2 * 24) * CommonUtils.PI_2 / 24;

        // 月日视黄经
        this.moonSolarRetina = AstronomyArithmeticUtils.MS_aLon(jd2 / 36525, 10, 3);
        this.moonSolarRetina = (int) Math.floor((this.moonSolarRetina - 0.78) / Math.PI * 2) * Math.PI / 2;
    }

    /**
     * 太阳视黄经
     */
    private double sunSolarRetina;

    /**
     * 月日视黄经
     */
    private double moonSolarRetina;

}
