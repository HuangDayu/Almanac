package cn.huangdayu.almanac.test

import cn.huangdayu.almanac.AlmanacApp
import cn.huangdayu.almanac.dto.TimeZoneDTO

/**
 * @author huangdayu at 2024/7/19 create
 */
object AlmanacMain {
    @JvmStatic
    fun main(args: Array<String>) {
        val almanacApp = AlmanacApp(TimeZoneDTO("广东省", "徐闻县", "1995-08-12 11:10:10"))
        almanacApp.dayCalendar().toMap().forEach { (key: String, value: String) -> println("$key $value") }
    }
}
