package almanac.cn.huangdayu.almanac

import almanac.cn.huangdayu.almanac.dto.TimeZoneDTO
import UNSUPPORTED.java.util.GregorianCalendar
import UNSUPPORTED.java.util.function.BiConsumer

class AlmanacRunner_1 <: <-- No type mapping: java.util.function.BiConsumer<String, String> --> {
    init() { }

    override public func accept(k: ?String, v: ?String): Unit {
        println((k ?? "null") + " : " + (v ?? "null"))
    }
}

/**
 * @author huangdayu at 2024/7/21 create
 */
public class AlmanacRunner {
    public init() { }
    public static func javaMain(args: ?Array<?String>): Unit {
        let almanacApp = AlmanacApp(TimeZoneDTO("广东省", "徐闻县", <-- Missing mapping for java.util.GregorianCalendar constructor: GregorianCalendar -->()))

        almanacApp.dayCalendar().getOrThrow().toMap().<-- Missing mapping for java.util.Map member: forEach -->(AlmanacRunner_1() as <-- No type mapping: java.util.function.BiConsumer<Any, Any> -->)
    }
}