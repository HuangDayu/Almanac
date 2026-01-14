package cn.huangdayu.almanac.dto;

/**
 * @author huangdayu
 */
public class InfoDTO {

    private String cnName;

    private String enName;

    private String value;

    public InfoDTO() {
    }

    public InfoDTO(String cnName, String enName, String value) {
        this.cnName = cnName;
        this.enName = enName;
        this.value = value;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
