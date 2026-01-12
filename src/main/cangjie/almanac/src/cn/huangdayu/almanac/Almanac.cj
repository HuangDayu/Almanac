package almanac.cn.huangdayu.almanac

import almanac.cn.huangdayu.almanac.dto.AlmanacDTO
import almanac.cn.huangdayu.almanac.dto.TimeZoneDTO
import almanac.cn.huangdayu.almanac.utils.AlmanacUtils

/**
 * @author huangdayu create at 2021/2/3 10:28
 */
public abstract class Almanac {
    public init() { }

    /**
     * 初始化时间和时区
     *
     * @return
     */
    public open func initTimeZone(): ?TimeZoneDTO


    public func dayCalendar(): ?AlmanacDTO {
        return AlmanacUtils.ofDay(initTimeZone())
    }

    public func monthCalendar(): ?Array<?AlmanacDTO> {
        return AlmanacUtils.ofMonth(initTimeZone())
    }

    public func yearCalendar(): ?Array<Array<?AlmanacDTO>> {
        return AlmanacUtils.ofYear(initTimeZone())
    }
}