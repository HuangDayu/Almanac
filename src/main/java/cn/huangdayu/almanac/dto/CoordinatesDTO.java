package cn.huangdayu.almanac.dto;

import cn.huangdayu.almanac.utils.CoordinatesUtils;
import cn.huangdayu.almanac.utils.PropertiesUtils;

import static cn.huangdayu.almanac.utils.CommonUtils.setStringPointDouble;

/**
 * 坐标信息
 * @author huangdayu
 */
public class CoordinatesDTO {

    public CoordinatesDTO(String province,String area) {
        this.province = province;
        this.area = area;
        double[]  coordinates = CoordinatesUtils.decodeCoordinatesByArea(this.province, this.area);
        this.latitudeValue = coordinates[0];
        this.longitudeValue = coordinates[1];
        this.portName = CoordinatesUtils.getPortName(PropertiesUtils.getLatitudeProperties(), PropertiesUtils.getLongitudeProperties(), this.latitudeValue, this.longitudeValue);
        this.position = (province.replaceAll("省", "") + " " + area.replaceAll("市", "").replaceAll("区", "").replaceAll("县", "").replaceAll("镇", "").replaceAll("乡", ""));
        this.longitudeStr = setStringPointDouble(this.longitudeValue, true);
        this.latitudeStr = setStringPointDouble(this.latitudeValue, false);
    }

    /**
     * 省份
     */
    private String province;
    /**
     * 市/县/区
     */
    private String area;

    /***
     * 纬度
     */
    private double latitudeValue;
    /***
     * 经度
     */
    private double longitudeValue;

    /**
     * 经度
     */
    private String longitudeStr;
    /**
     * 纬度
     */
    private String latitudeStr;
    /**
     * 港口
     */
    private String portName;
    /**
     * 位置
     */
    private String position;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public double getLatitudeValue() {
        return latitudeValue;
    }

    public void setLatitudeValue(double latitudeValue) {
        this.latitudeValue = latitudeValue;
    }

    public double getLongitudeValue() {
        return longitudeValue;
    }

    public void setLongitudeValue(double longitudeValue) {
        this.longitudeValue = longitudeValue;
    }

    public String getLongitudeStr() {
        return longitudeStr;
    }

    public void setLongitudeStr(String longitudeStr) {
        this.longitudeStr = longitudeStr;
    }

    public String getLatitudeStr() {
        return latitudeStr;
    }

    public void setLatitudeStr(String latitudeStr) {
        this.latitudeStr = latitudeStr;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
