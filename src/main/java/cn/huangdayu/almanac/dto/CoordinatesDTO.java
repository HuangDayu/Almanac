package cn.huangdayu.almanac.dto;

import cn.huangdayu.almanac.utils.CoordinatesUtils;
import cn.huangdayu.almanac.utils.PropertiesUtils;

import java.util.Map;

import static cn.huangdayu.almanac.utils.CommonUtils.setStringPointDouble;

/**
 * 坐标信息
 *
 * @author huangdayu
 */
public class CoordinatesDTO {

    public CoordinatesDTO(String province, String area) {
        this(province, area, CoordinatesUtils.decodeCoordinatesByArea(province, area));
    }

    public CoordinatesDTO(String province, String area, double[] coordinates) {
        this(province, area, coordinates[0], coordinates[1]);
    }

    public CoordinatesDTO(double longitude, double latitude) {
        this.longitudeValue = longitude;
        this.latitudeValue = latitude;
        this.longitudeStr = setStringPointDouble(this.longitudeValue, true);
        this.latitudeStr = setStringPointDouble(this.latitudeValue, false);
        String value = encode(latitudeStr) + encode(longitudeStr);
        String code = CoordinatesUtils.encodeCoordinates(Integer.parseInt(value));
        for (Map.Entry<Object, Object> entry : PropertiesUtils.getAdministrativeProperties().entrySet()) {
            String[] areas = entry.getValue().toString().split(" ");
            for (int i = 1; i < areas.length; i++) {
                if (areas[i].contains(code)) {
                    this.position = areas[0] + " " + areas[i].replaceAll(code, "");
                    this.province = areas[0];
                    this.area = areas[i].replaceAll(code, "");
                    this.portName = CoordinatesUtils.getPortName(this.latitudeValue, this.longitudeValue);
                }
            }
        }
    }

    public CoordinatesDTO(String province, String area, double longitude, double latitude) {
        this.longitudeValue = longitude;
        this.latitudeValue = latitude;
        this.longitudeStr = setStringPointDouble(this.longitudeValue, true);
        this.latitudeStr = setStringPointDouble(this.latitudeValue, false);
        this.portName = CoordinatesUtils.getPortName(this.latitudeValue, this.longitudeValue);
        this.position = province.replaceAll("省", "") + " " + area.replaceAll("[市区县镇乡]", "");
        this.province = province;
        this.area = area;
    }

    private static String encode(String spd) {
        spd = spd.split(" ")[1];
        String[] split = spd.split("°");
        return split[0] + split[1].split("'")[0];
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
