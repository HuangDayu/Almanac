package almanac.cn.huangdayu.almanac

import almanac.cn.huangdayu.almanac.dto.TimeZoneDTO

/**
 * @author huangdayu create at 2021/2/3 10:28
 */
public class AlmanacService <: Almanac {

    private var timeZoneDTO: ?TimeZoneDTO = None

    private init() {
        super()
    }

    public init(timeZoneDTO: ?TimeZoneDTO) {
        super()
        this.timeZoneDTO = timeZoneDTO
    }

    override public func initTimeZone(): ?TimeZoneDTO {
        return timeZoneDTO
    }
}