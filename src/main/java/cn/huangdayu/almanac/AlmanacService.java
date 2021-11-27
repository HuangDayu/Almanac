package cn.huangdayu.almanac;

import cn.huangdayu.almanac.dto.AlmanacDTO;
import cn.huangdayu.almanac.dto.TimeZoneDTO;
import cn.huangdayu.almanac.utils.AlmanacUtils;

/**
 * @author huangdayu create at 2021/2/3 10:28
 */
public class AlmanacService extends Almanac {

    private TimeZoneDTO timeZoneDTO;

    private AlmanacService() {
    }

    public AlmanacService(TimeZoneDTO timeZoneDTO) {
        this.timeZoneDTO = timeZoneDTO;
    }

    @Override
    public TimeZoneDTO initTimeZone() {
        return timeZoneDTO;
    }

}
