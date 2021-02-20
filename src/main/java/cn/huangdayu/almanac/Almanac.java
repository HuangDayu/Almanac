package cn.huangdayu.almanac;

import cn.huangdayu.almanac.dto.AlmanacDTO;
import cn.huangdayu.almanac.dto.TimeZoneDTO;
import cn.huangdayu.almanac.utils.AlmanacUtils;

/**
 * @author huangdayu create at 2021/2/3 10:28
 */
public abstract class Almanac {

    /**
     * 初始化时间和时区
     *
     * @return
     */
    public abstract TimeZoneDTO initTimeZone();


    public AlmanacDTO dayCalendar() {
        return AlmanacUtils.ofDay(initTimeZone());
    }

    public AlmanacDTO[] monthCalendar() {
        return AlmanacUtils.ofMonth(initTimeZone());
    }

    public AlmanacDTO[][] yearCalendar() {
        return AlmanacUtils.ofYear(initTimeZone());
    }

}
