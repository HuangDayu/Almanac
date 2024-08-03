package cn.huangdayu.almanac;

import cn.huangdayu.almanac.dto.AlmanacDTO;
import cn.huangdayu.almanac.dto.TimeZoneDTO;
import cn.huangdayu.almanac.utils.AlmanacUtils;
import com.alibaba.fastjson.JSON;
import org.graalvm.nativeimage.IsolateThread;
import org.graalvm.nativeimage.c.function.CEntryPoint;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.nativeimage.c.type.CTypeConversion;

import java.util.GregorianCalendar;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author huangdayu at 2024/8/1 create
 */
public class AlmanacNative {

    public static void main(String[] args) {
        AlmanacApp almanacApp = new AlmanacApp(new TimeZoneDTO("广东省", "徐闻县", new GregorianCalendar()));
        almanacApp.dayCalendar().toMap().forEach((k, v) -> System.out.println(k + " : " + v));
    }

    @CEntryPoint(name = "printlnCalendar")
    public static void printlnCalendar(IsolateThread isolateThread) {
        AlmanacUtils.ofDay(new TimeZoneDTO("广东省", "徐闻县", new GregorianCalendar()))
                .toMap().forEach((k, v) -> System.out.println(k + " : " + v));
    }

    @CEntryPoint(name = "defaultCalendar")
    public static CCharPointer defaultCalendar(IsolateThread isolateThread, CCharPointer input) {
        return function(isolateThread, input, timeZoneDTO -> AlmanacUtils.ofDay(new TimeZoneDTO("广东省", "徐闻县", new GregorianCalendar())));
    }

    @CEntryPoint(name = "dayCalendar")
    public static CCharPointer dayCalendar(IsolateThread isolateThread, CCharPointer input) {
        return function(isolateThread, input, AlmanacUtils::ofDay);
    }

    @CEntryPoint(name = "monthCalendar")
    public static CCharPointer monthCalendar(IsolateThread isolateThread, CCharPointer input) {
        return function(isolateThread, input, AlmanacUtils::ofMonth);
    }

    @CEntryPoint(name = "yearCalendar")
    public static CCharPointer yearCalendar(IsolateThread isolateThread, CCharPointer input) {
        return function(isolateThread, input, AlmanacUtils::ofYear);
    }

    public static CCharPointer function(IsolateThread thread, CCharPointer input, Function<TimeZoneDTO, Object> function) {
        //Convert C *char to Java String
        final String inputString = CTypeConversion.toJavaString(input);
        //logic goes here
        Object data = function.apply(JSON.parseObject(inputString, TimeZoneDTO.class));

        //Convert Java String to C *char
        try (final CTypeConversion.CCharPointerHolder holder = CTypeConversion.toCString(JSON.toJSONString(data))) {
            final CCharPointer result = holder.get();
            return result;
        }
    }
}
