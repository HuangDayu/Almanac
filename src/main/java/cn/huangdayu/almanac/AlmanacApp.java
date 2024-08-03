package cn.huangdayu.almanac;

import cn.huangdayu.almanac.dto.TimeZoneDTO;

/**
 * @author huangdayu at 2024/7/28 create
 */
public class AlmanacApp extends Almanac {

    private final TimeZoneDTO timeZoneDTO;

    public AlmanacApp(TimeZoneDTO timeZoneDTO) {
        this.timeZoneDTO = timeZoneDTO;
    }

    @Override
    public TimeZoneDTO initTimeZone() {
        return this.timeZoneDTO;
    }
}
