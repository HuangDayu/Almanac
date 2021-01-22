package cn.huangdayu.almanac.dto;

/**
 * @author huangdayu create at 2021/1/21 11:08
 */
public class SunMoonDTO {

    /**
     * 港口
     */
    private String portName;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 日出
     */
    private String sunRiseTime;
    /**
     * 日落
     */
    private String sunSetTime;
    /**
     * 中天
     */
    private String midDayTime;
    /**
     * 天亮
     */
    private String dawnTime;
    /**
     * 天黑
     */
    private String darkTime;
    /**
     * 昼长
     */
    private String diurnalTime;
    /**
     * 夜长
     */
    private String nightTime;
    /**
     * 月出
     */
    private String moonRiseTime;
    /**
     * 月落
     */
    private String moonSetTime;
    /**
     * 月中
     */
    private String moonMiddleTime;

    /**
     * 月相名称
     */
    private String moonPhaseName;
    /**
     * 月相时刻(儒略日)
     */
    private double moonPhaseTime;
    /**
     * 月相时间串
     */
    private String moonPhaseTimeName;

    public String getMoonPhaseName() {
        return moonPhaseName;
    }

    public void setMoonPhaseName(String moonPhaseName) {
        this.moonPhaseName = moonPhaseName;
    }

    public double getMoonPhaseTime() {
        return moonPhaseTime;
    }

    public void setMoonPhaseTime(double moonPhaseTime) {
        this.moonPhaseTime = moonPhaseTime;
    }

    public String getMoonPhaseTimeName() {
        return moonPhaseTimeName;
    }

    public void setMoonPhaseTimeName(String moonPhaseTimeName) {
        this.moonPhaseTimeName = moonPhaseTimeName;
    }

    public String getSunRiseTime() {
        return sunRiseTime;
    }

    public void setSunRiseTime(String sunRiseTime) {
        this.sunRiseTime = sunRiseTime;
    }

    public String getSunSetTime() {
        return sunSetTime;
    }

    public void setSunSetTime(String sunSetTime) {
        this.sunSetTime = sunSetTime;
    }

    public String getMidDayTime() {
        return midDayTime;
    }

    public void setMidDayTime(String midDayTime) {
        this.midDayTime = midDayTime;
    }

    public String getDawnTime() {
        return dawnTime;
    }

    public void setDawnTime(String dawnTime) {
        this.dawnTime = dawnTime;
    }

    public String getDarkTime() {
        return darkTime;
    }

    public void setDarkTime(String darkTime) {
        this.darkTime = darkTime;
    }

    public String getDiurnalTime() {
        return diurnalTime;
    }

    public void setDiurnalTime(String diurnalTime) {
        this.diurnalTime = diurnalTime;
    }

    public String getNightTime() {
        return nightTime;
    }

    public void setNightTime(String nightTime) {
        this.nightTime = nightTime;
    }

    public String getMoonRiseTime() {
        return moonRiseTime;
    }

    public void setMoonRiseTime(String moonRiseTime) {
        this.moonRiseTime = moonRiseTime;
    }

    public String getMoonSetTime() {
        return moonSetTime;
    }

    public void setMoonSetTime(String moonSetTime) {
        this.moonSetTime = moonSetTime;
    }

    public String getMoonMiddleTime() {
        return moonMiddleTime;
    }

    public void setMoonMiddleTime(String moonMiddleTime) {
        this.moonMiddleTime = moonMiddleTime;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    @Override
    public String toString() {
        return "SunMoonDTO{" +
                "portName='" + portName + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", sunRiseTime='" + sunRiseTime + '\'' +
                ", sunSetTime='" + sunSetTime + '\'' +
                ", midDayTime='" + midDayTime + '\'' +
                ", dawnTime='" + dawnTime + '\'' +
                ", darkTime='" + darkTime + '\'' +
                ", diurnalTime='" + diurnalTime + '\'' +
                ", nightTime='" + nightTime + '\'' +
                ", moonRiseTime='" + moonRiseTime + '\'' +
                ", moonSetTime='" + moonSetTime + '\'' +
                ", moonMiddleTime='" + moonMiddleTime + '\'' +
                ", moonPhaseName='" + moonPhaseName + '\'' +
                ", moonPhaseTime=" + moonPhaseTime +
                ", moonPhaseTimeName='" + moonPhaseTimeName + '\'' +
                '}';
    }
}
