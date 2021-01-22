package cn.huangdayu.almanac.dto;

/**
 * @author huangdayu
 * @date 2020/4/3 15:58
 * @project Almanac
 */
public class AddressDTO {
    private String province;
    private String city;
    private String county;
    private String village;
    private String building;
    private String other;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }


    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "ChinaAddressDTO{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", village='" + village + '\'' +
                ", building='" + building + '\'' +
                ", other='" + other + '\'' +
                '}';
    }
}
