package cn.huangdayu.almanac.aggregates.sunrise_moonset;

import cn.huangdayu.almanac.aggregates.BaseAlmanac;
import cn.huangdayu.almanac.dto.TimeZoneDTO;
import cn.huangdayu.almanac.utils.SunMoonUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * @author huangdayu create at 2021/1/21 11:08
 */
@Getter
@Setter
public class SunriseMoonset extends BaseAlmanac {

    public SunriseMoonset(TimeZoneDTO timeZoneDTO) {
        SunMoonUtils.init(timeZoneDTO, this);
    }
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

    @Override
    public String getInfo() {
        return sunRiseTime + " " + sunSetTime;
    }


}
