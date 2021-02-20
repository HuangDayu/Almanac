package cn.huangdayu.almanac.dto;

/**
 * 天文历
 *
 * @author huangdayu create at 2021/2/17 20:35
 */
public class AstronomyDTO {

    // 太阳黄经
    private double sunAccurate;

    // 月球黄经
    private double moonAccurate;

    // 月球速度
    private double moonSpeed;

    // 地球速度
    private double earthSpeed;


    public double getSunAccurate() {
        return sunAccurate;
    }

    public void setSunAccurate(double sunAccurate) {
        this.sunAccurate = sunAccurate;
    }

    public double getMoonAccurate() {
        return moonAccurate;
    }

    public void setMoonAccurate(double moonAccurate) {
        this.moonAccurate = moonAccurate;
    }

    public double getMoonSpeed() {
        return moonSpeed;
    }

    public void setMoonSpeed(double moonSpeed) {
        this.moonSpeed = moonSpeed;
    }

    public double getEarthSpeed() {
        return earthSpeed;
    }

    public void setEarthSpeed(double earthSpeed) {
        this.earthSpeed = earthSpeed;
    }
}
