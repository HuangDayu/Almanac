package almanac.cn.huangdayu.almanac

import almanac.cn.huangdayu.almanac.dto.TimeZoneDTO

/**
 * @author huangdayu at 2024/7/28 create
 */
public class AlmanacApp <: Almanac {

    private let timeZoneDTO: ?TimeZoneDTO

    public init(timeZoneDTO: ?TimeZoneDTO) {
        super()
        this.timeZoneDTO = timeZoneDTO
    }

    override public func initTimeZone(): ?TimeZoneDTO {
        return this.timeZoneDTO
    }
}