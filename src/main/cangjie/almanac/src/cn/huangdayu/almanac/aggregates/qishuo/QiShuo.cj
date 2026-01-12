package almanac.cn.huangdayu.almanac.aggregates.qishuo

import almanac.cn.huangdayu.almanac.utils.AnnalsUtils
import almanac.cn.huangdayu.almanac.utils.AstronomicalUtils
import almanac.cn.huangdayu.almanac.utils.CommonUtils
import j2cjlib.utils.ValueBox
import j2cjlib.utils.parse
import j2cjlib.utils.rem
import std.math.MathExtension
import std.math.cos
import std.math.floor
import std.math.sin

/***
 * 气朔计算和参数数据表类
 *
 */
public class QiShuo {

    private init() { }

    public init(julianDayForToday: Int32) {
        // 此处有线程安全问题, 暂时直接实例化，qiShuoDO.calcY是为减少计算次数而做的判断。（同一年数据一样）
        if (julianDayForToday < zhongQi[0] || julianDayForToday >= zhongQi[24]) {
            calculateMonth(julianDayForToday)
        }
    }


    // 朔修正表
    private static var sCorrectTable: ?String = ""
    // 气修正表
    private static var qCorrectTable: ?String = ""

    // 朔直线拟合参数
    private static let S_FITTING_PARAMETERS = [1457698.231017, 29.53067166, 1546082.512234, 29.53085106, 1640640.7353, 29.5306, 1642472.151543, 29.53085439, 1683430.5093, 29.53086148, 1752148.041079, 29.53085097, 1807665.420323, 29.53059851, 1883618.1141, 29.5306, 1907360.7047, 29.5306, 1936596.2249, 29.5306, 1939135.6753, 29.5306, 1947168.0]

    // 气直线拟合参数
    private static let Q_FITTING_PARAMETERS = [1640650.479938, 15.218425, 1642476.703182, 15.21874996, 1683430.515601, 15.218750011, 1752157.640664, 15.218749978, 1807675.003759, 15.218620279, 1883627.765182, 15.218612292, 1907369.1281, 15.218449176, 1936603.140413, 15.218425, 1939145.52418, 15.218466998, 1947180.7983, 15.218524844, 1964362.041824, 15.218533526, 1987372.340971, 15.218513908, 1999653.819126, 15.218530782, 2007445.469786, 15.218535181, 2021324.917146, 15.218526248, 2047257.232342, 15.218519654, 2070282.898213, 15.218425, 2073204.87285, 15.218515221, 2080144.500926, 15.218530782, 2086703.688963, 15.218523776, 2110033.182763, 15.218425, 2111190.300888, 15.218425, 2113731.271005, 15.218515671, 2120670.840263, 15.218425, 2123973.309063, 15.218425, 2125068.997336, 15.218477932, 2136026.312633, 15.218472436, 2156099.495538, 15.218425, 2159021.324663, 15.218425, 2162308.575254, 15.218461742, 2178485.706538, 15.218425, 2178759.662849, 15.218445786, 2185334.0208, 15.218425, 2187525.481425, 15.218425, 2188621.191481, 15.218437494, 2322147.76]

    <-- Unsupported initialization blocks: {
        // 619-01-21开始16598个朔日修正表 d0=1947168
        var sCorrectTableValue: ?String = "EqoFscDcrFpmEsF2DfFideFelFpFfFfFiaipqti1ksttikptikqckstekqttgkqttgkqteksttikptikq2fjstgjqttjkqttgkqt"
        sCorrectTableValue += "ekstfkptikq2tijstgjiFkirFsAeACoFsiDaDiADc1AFbBfgdfikijFifegF1FhaikgFag1E2btaieeibggiffdeigFfqDfaiBkF"
        sCorrectTableValue += "1kEaikhkigeidhhdiegcFfakF1ggkidbiaedksaFffckekidhhdhdikcikiakicjF1deedFhFccgicdekgiFbiaikcfi1kbFibef"
        sCorrectTableValue += "gEgFdcFkFeFkdcfkF1kfkcickEiFkDacFiEfbiaejcFfffkhkdgkaiei1ehigikhdFikfckF1dhhdikcfgjikhfjicjicgiehdik"
        sCorrectTableValue += "cikggcifgiejF1jkieFhegikggcikFegiegkfjebhigikggcikdgkaFkijcfkcikfkcifikiggkaeeigefkcdfcfkhkdgkegieid"
        sCorrectTableValue += "hijcFfakhfgeidieidiegikhfkfckfcjbdehdikggikgkfkicjicjF1dbidikFiggcifgiejkiegkigcdiegfggcikdbgfgefjF1"
        sCorrectTableValue += "kfegikggcikdgFkeeijcfkcikfkekcikdgkabhkFikaffcfkhkdgkegbiaekfkiakicjhfgqdq2fkiakgkfkhfkfcjiekgFebicg"
        sCorrectTableValue += "gbedF1jikejbbbiakgbgkacgiejkijjgigfiakggfggcibFifjefjF1kfekdgjcibFeFkijcfkfhkfkeaieigekgbhkfikidfcje"
        sCorrectTableValue += "aibgekgdkiffiffkiakF1jhbakgdki1dj1ikfkicjicjieeFkgdkicggkighdF1jfgkgfgbdkicggfggkidFkiekgijkeigfiski"
        sCorrectTableValue += "ggfaidheigF1jekijcikickiggkidhhdbgcfkFikikhkigeidieFikggikhkffaffijhidhhakgdkhkijF1kiakF1kfheakgdkif"
        sCorrectTableValue += "iggkigicjiejkieedikgdfcggkigieeiejfgkgkigbgikicggkiaideeijkefjeijikhkiggkiaidheigcikaikffikijgkiahi1"
        sCorrectTableValue += "hhdikgjfifaakekighie1hiaikggikhkffakicjhiahaikggikhkijF1kfejfeFhidikggiffiggkigicjiekgieeigikggiffig"
        sCorrectTableValue += "gkidheigkgfjkeigiegikifiggkidhedeijcfkFikikhkiggkidhh1ehigcikaffkhkiggkidhh1hhigikekfiFkFikcidhh1hit"
        sCorrectTableValue += "cikggikhkfkicjicghiediaikggikhkijbjfejfeFhaikggifikiggkigiejkikgkgieeigikggiffiggkigieeigekijcijikgg"
        sCorrectTableValue += "ifikiggkideedeijkefkfckikhkiggkidhh1ehijcikaffkhkiggkidhh1hhigikhkikFikfckcidhh1hiaikgjikhfjicjicgie"
        sCorrectTableValue += "hdikcikggifikigiejfejkieFhegikggifikiggfghigkfjeijkhigikggifikiggkigieeijcijcikfksikifikiggkidehdeij"
        sCorrectTableValue += "cfdckikhkiggkhghh1ehijikifffffkhsFngErD1pAfBoDd1BlEtFqA2AqoEpDqElAEsEeB2BmADlDkqBtC1FnEpDqnEmFsFsAFn"
        sCorrectTableValue += "llBbFmDsDiCtDmAB2BmtCgpEplCpAEiBiEoFqFtEqsDcCnFtADnFlEgdkEgmEtEsCtDmADqFtAFrAtEcCqAE1BoFqC1F1DrFtBmF"
        sCorrectTableValue += "tAC2ACnFaoCgADcADcCcFfoFtDlAFgmFqBq2bpEoAEmkqnEeCtAE1bAEqgDfFfCrgEcBrACfAAABqAAB1AAClEnFeCtCgAADqDoB"
        sCorrectTableValue += "mtAAACbFiAAADsEtBqAB2FsDqpFqEmFsCeDtFlCeDtoEpClEqAAFrAFoCgFmFsFqEnAEcCqFeCtFtEnAEeFtAAEkFnErAABbFkAD"
        sCorrectTableValue += "nAAeCtFeAfBoAEpFtAABtFqAApDcCGJ"

        // 1645-09-23开始7567个节气修正表
        var qCorrectTableValue: ?String = "FrcFs22AFsckF2tsDtFqEtF1posFdFgiFseFtmelpsEfhkF2anmelpFlF1ikrotcnEqEq2FfqmcDsrFor22FgFrcgDscFs22FgEe"
        qCorrectTableValue += "FtE2sfFs22sCoEsaF2tsD1FpeE2eFsssEciFsFnmelpFcFhkF2tcnEqEpFgkrotcnEqrEtFermcDsrE222FgBmcmr22DaEfnaF22"
        qCorrectTableValue += "2sD1FpeForeF2tssEfiFpEoeFssD1iFstEqFppDgFstcnEqEpFg11FscnEqrAoAF2ClAEsDmDtCtBaDlAFbAEpAAAAAD2FgBiBqo"
        qCorrectTableValue += "BbnBaBoAAAAAAAEgDqAdBqAFrBaBoACdAAf1AACgAAAeBbCamDgEifAE2AABa1C1BgFdiAAACoCeE1ADiEifDaAEqAAFe1AcFbcA"
        qCorrectTableValue += "AAAAF1iFaAAACpACmFmAAAAAAAACrDaAAADG0"

        sCorrectTable = unzipCorrectTableValue(sCorrectTableValue) // 定朔修正表解压
        qCorrectTable = unzipCorrectTableValue(qCorrectTableValue) // 定气修正表解压
    } -->

    /***
     * 低精度定朔计算,在2000年至600，误差在2小时以内(仍比古代日历精准很多)
     *
     * @param w
     * @return
     */
    private static func sLowCalculate(w: Float64): Float64 {
        let v = 7771.37714500204
        var t = (w + 1.08472) / v
        t -= (-3.31E-5 * t * t + 0.10976 * cos(0.785 + 8328.6914 * t) + 0.02224 * cos(0.187 + 7214.0629 * t) - 0.03342 * cos(4.669 + 628.3076 * t)) / v + (32.0 * (t + 1.8) * (t + 1.8) - 20.0) / 86400.0 / 36525.0
        return t * 36525.0 + 8.0 / 24.0
    }

    /****
     * 最大误差小于30分钟，平均5分
     *
     * @param w
     * @return
     */
    private static func qLowCalculate(w: Float64): Float64 {
        var t: Float64 = 0.0
        let l: Float64
        let v = 628.3319653318
        t = (w - 4.895062166) / v // 第一次估算,误差2天以内
        t -= (53.0 * t * t + 334116.0 * cos(4.67 + 628.307585 * t) + 2061.0 * cos(2.678 + 628.3076 * t) * t) / v / 10000000.0 // 第二次估算,误差2小时以内

        l = 4.895062166E7 + 6.283319653318E9 * t + 53.0 * t * t // 平黄经
         + 334166.0 * cos(4.669257 + 628.307585 * t) // 地球椭圆轨道级数展开
         + 3489.0 * cos(4.6261 + 1256.61517 * t) // 地球椭圆轨道级数展开
         + 2060.6 * cos(2.67823 + 628.307585 * t) * t // 一次泊松项
         - 994.0 - 834.0 * sin(2.1824 - 33.75705 * t) // 光行差与章动修正

        t -= (l / 10000000.0 - w) / 628.332 + (32.0 * (t + 1.8) * (t + 1.8) - 20.0) / 86400.0 / 36525.0
        return t * 36525.0 + 8.0 / 24.0
    }

    /***
     * 较高精度气
     *
     * @param w
     * @return
     */
    private static func qHighCalculate(w: Float64): Float64 {
        var t = AstronomicalUtils.S_aLon_t2(w) * 36525.0
        t = t - CommonUtils.dtT(t) + 8.0 / 24.0
        let v = (rem((t + 0.5), 1.0)) * 86400.0
        if (v < 1200.0 || v > Float64(86400i32 - 1200i32)) {
            t = AstronomicalUtils.S_aLon_t(w) * 36525.0 - CommonUtils.dtT(t) + 8.0 / 24.0
        }
        return t
    }

    /****
     * 较高精度朔
     *
     * @param w
     * @return
     */
    private static func sHighCalculate(w: Float64): Float64 {
        var t = AstronomicalUtils.MS_aLon_t2(w) * 36525.0
        t = t - CommonUtils.dtT(t) + 8.0 / 24.0
        let v = (rem((t + 0.5), 1.0)) * 86400.0
        if (v < 1800.0 || v > Float64(86400i32 - 1800i32)) {
            t = AstronomicalUtils.MS_aLon_t(w) * 36525.0 - CommonUtils.dtT(t) + 8.0 / 24.0
        }
        return t
    }

    /*****
     * 气朔解压缩
     *
     * @param s
     * @return
     */
    private static func unzipCorrectTableValue(s: ?String): ?String {
        var j2cj_param_generated_s = s
        let o = "0000000000"
        let o2: ?String = o + o
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/J/g", "00")
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/I/g", "000")
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/H/g", "0000")
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/G/g", "00000")
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/t/g", "02")
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/s/g", "002")
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/r/g", "0002")
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/q/g", "00002")
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/p/g", "000002")
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/o/g", "0000002")
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/n/g", "00000002")
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/m/g", "000000002")
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/l/g", "0000000002")
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/k/g", "01")
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/j/g", "0101")
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/i/g", "001")
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/h/g", "001001")
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/g/g", "0001")
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/f/g", "00001")
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/e/g", "000001")
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/d/g", "0000001")
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/c/g", "00000001")
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/b/g", "000000001")
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/a/g", "0000000001")
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/A/g", (o2 ?? "null") + (o2 ?? "null") + (o2 ?? "null"))
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/B/g", (o2 ?? "null") + (o2 ?? "null") + o)
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/C/g", (o2 ?? "null") + (o2 ?? "null"))
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/D/g", (o2 ?? "null") + o)
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/E/g", o2.getOrThrow())
        j2cj_param_generated_s = j2cj_param_generated_s.getOrThrow().replace("/F/g", o)
        return j2cj_param_generated_s
    }

    /*****
     * jd应靠近所要取得的气朔日,qs="气"时，算节气的儒略日
     *
     * @param julianDay
     * @param qs
     * @return
     */
    private static func calculateJulianDay(julianDay: Float64, qs: ?String): Int32 {
        var j2cj_param_generated_julianDay = julianDay
        j2cj_param_generated_julianDay += 2451545.0
        var i: Int32 = 0
        var D = 0.0
        let n: ?String
        var B: ?Array<Float64> = S_FITTING_PARAMETERS
        var pc = 14i32
        if (qs.getOrThrow() == "气") {
            B = Q_FITTING_PARAMETERS
            pc = 7i32
        }
        let f1 = B.getOrThrow()[0] - Float64(pc)
        let f2 = B.getOrThrow()[Int64(Int32(B.getOrThrow().size) - 1i32)] - Float64(pc)
        let f3 = 2436935.0

        if (j2cj_param_generated_julianDay < f1 || j2cj_param_generated_julianDay >= f3) { // 平气朔表中首个之前，使用现代天文算法。1960.1.1以后，使用现代天文算法
            // (这一部分调用了qi_high和so_high,所以需星历表支持)
            if (qs.getOrThrow() == "气") {
                return Int32(floor(qHighCalculate(floor((j2cj_param_generated_julianDay + Float64(pc) - 2451259.0) / 365.2422 * 24.0) * Float64.PI / 12.0) + 0.5)) // 2451259是1999.3.21,太阳视黄经为0,春分.定气计算
            } else {
                return Int32(floor(sHighCalculate(floor((j2cj_param_generated_julianDay + Float64(pc) - 2451551.0) / 29.5306) * Float64.PI * 2.0) + 0.5)) // 2451551是2000.1.7的那个朔日,黄经差为0.定朔计算
            }
        }

        if (j2cj_param_generated_julianDay >= f1 && j2cj_param_generated_julianDay < f2) { // 平气或平朔
            i = 0i32
            while (i < Int32(B.getOrThrow().size)) {
                if (j2cj_param_generated_julianDay + Float64(pc) < B.getOrThrow()[Int64(i + 2i32)]) {
                    break
                }
                i += 2i32
            }
            D = (B.getOrThrow()[Int64(i)] + B.getOrThrow()[Int64(i + 1i32)] * floor((j2cj_param_generated_julianDay + Float64(pc) - B.getOrThrow()[Int64(i)]) / B.getOrThrow()[Int64(i + 1i32)]))
            D = floor(D + 0.5)
            if (Int32(D) == 1683460i32) {
                D += 1.0 // 如果使用太初历计算-103年1月24日的朔日,结果得到的是23日,这里修正为24日(实历)。修正后仍不影响-103的无中置闰。如果使用秦汉历，得到的是24日，本行D不会被执行。
            }
            return Int32(D - 2451545.0)
        }

        if (j2cj_param_generated_julianDay >= f2 && j2cj_param_generated_julianDay < f3) { // 定气或定朔
            if (qs.getOrThrow() == "气") {
                D = floor(qLowCalculate(floor((j2cj_param_generated_julianDay + Float64(pc) - 2451259.0) / 365.2422 * 24.0) * Float64.PI / 12.0) + 0.5) // 2451259是1999.3.21,太阳视黄经为0,春分.定气计算
                let index = Int32(floor((j2cj_param_generated_julianDay - f2) / 365.2422 * 24.0))
                n = CommonUtils.subString(qCorrectTable, index, index + 1i32) // 找定气修正值
            } else {
                D = floor(sLowCalculate(floor((j2cj_param_generated_julianDay + Float64(pc) - 2451551.0) / 29.5306) * Float64.PI * 2.0) + 0.5) // 2451551是2000.1.7的那个朔日,黄经差为0.定朔计算
                let index = Int32(floor((j2cj_param_generated_julianDay - f2) / 29.5306))
                n = CommonUtils.subString(sCorrectTable, index, index + 1i32) // 找定朔修正值
            }
            if (n.getOrThrow() == "1") {
                return Int32(D + 1.0)
            }
            if (n.getOrThrow() == "2") {
                return Int32(D - 1.0)
            }
            return Int32(D)
        }
        return Int32(D)
    }

    /***
     * 闰月位置
     */
    private var leapMonthIndex = 0i32
    /***
     * 各月名称
     */
    private let monthNames = Array<?String>(14) { _ => None }
    /***
     * 各月数字号码
     */
    private let monthIndex = Array<Int32>(14) { _ => 0 }
    /***
     * 中气表,其中.liqiu是节气立秋的儒略日,计算三伏时用到
     */
    private let zhongQi = Array<Int32>(27) { _ => 0 }
    /***
     * 合朔表
     */
    private let heShuo = Array<Int32>(15) { _ => 0 }
    /***
     * 各月大小
     */
    private let monthValue = Array<Int32>(15) { _ => 0 }

    /***
     * 农历排月序计算,可定出农历,有效范围：两个冬至之间(冬至一 <= d < 冬至二)
     *
     * @param julianDay
     */
    private func calculateMonth(julianDay: Int32): Unit {
        let zhongQiValue = zhongQi
        let heShuoValue = heShuo // 中气表,日月合朔表(整日)
        var i: Int32 = 0
        var j: Float64 = 0.0
        var k: Float64 = 0.0

        // 该年的气
        j = floor((Float64(julianDay - 355i32 + 183i32)) / 365.2422) * 365.2422 + 355.0 // 355是2000.12冬至,得到较靠近jd的冬至估计值
        if (calculateJulianDay(j, "气") > julianDay) {
            j -= 365.2422
        }
        i = 0i32
        while (i < 25i32) {
            zhongQiValue[Int64(i)] = calculateJulianDay(j + 15.2184 * Float64(i), "气") // 25个节气时刻(北京时间),从冬至开始到下一个冬至以后
            i++
        }
        zhongQiValue[25] = calculateJulianDay(j - 15.2, "气")
        zhongQiValue[26] = calculateJulianDay(j - 30.4, "气") // 补算二气,确保一年中所有月份的“气”全部被计算在内

        // 今年"首朔"的日月黄经差w
        k = Float64(calculateJulianDay(Float64(zhongQiValue[0]), "朔")) // 求较靠近冬至的朔日
        if (k > Float64(zhongQiValue[0])) {
            k -= 29.53
        }

        // 该年所有朔,包含14个月的始末
        i = 0i32
        while (i < 15i32) {
            heShuoValue[Int64(i)] = calculateJulianDay(k + 29.5306 * Float64(i), "朔")
            i++
        }

        // 月大小
        leapMonthIndex = 0i32
        i = 0i32
        while (i < 14i32) {
            monthValue[Int64(i)] = heShuo[Int64(i + 1i32)] - heShuo[Int64(i)] // 月大小
            monthIndex[Int64(i)] = i // 月序初始化
            i++
        }

        // -721年至-104年的后九月及月建问题,与朔有关，与气无关
        let year = Int32(floor((Float64(zhongQi[0] + 10i32 + 180i32)) / 365.2422) + 2000.0) // 确定年份
        if (year >= -721i32 && year <= -104i32) {
            // FIXME 2021-02-18 原先数组长度为8，但是计算公元前时间时，出现下标溢出，所以设置为10，不知是否有问题
            let objects = Array<?Object>(10) { _ => None }
            var yearIndex: Int32 = 0
            i = 0i32
            while (i < 3i32) {
                yearIndex = year + i - 1i32
                // 颁行历年首, 闰月名称, 月建
                if (yearIndex >= -721i32) {
                    objects[Int64(i)] = ValueBox<Int32>(calculateJulianDay(Float64(1457698i32 - CommonUtils.JULIAN_FOR_2000) + floor(0.342 + (Float64(yearIndex + 721i32)) * 12.368422) * 29.5306, "朔"))
                    objects[Int64(i + 3i32)] = ValueBox<String>("十三")
                    objects[Int64(i + 6i32)] = ValueBox<Int32>(2i32)
                } // 春秋历,ly为-722.12.17
                if (yearIndex >= -479i32) {
                    objects[Int64(i)] = ValueBox<Int32>(calculateJulianDay(Float64(1546083i32 - CommonUtils.JULIAN_FOR_2000) + floor(0.5 + (Float64(yearIndex + 479i32)) * 12.368422) * 29.5306, "朔"))
                    objects[Int64(i + 3i32)] = ValueBox<String>("十三")
                    objects[Int64(i + 6i32)] = ValueBox<Int32>(2i32)
                } // 战国历,ly为-480.12.11
                if (yearIndex >= -220i32) {
                    objects[Int64(i)] = ValueBox<Int32>(calculateJulianDay(Float64(1640641i32 - CommonUtils.JULIAN_FOR_2000) + floor(0.866 + (Float64(yearIndex + 220i32)) * 12.369) * 29.5306, "朔"))
                    objects[Int64(i + 3i32)] = ValueBox<String>("后九")
                    objects[Int64(i + 6i32)] = ValueBox<Int32>(11i32)
                } // 秦汉历,ly为-221.10.31
                i++
            }
            var objectIndex: Int32 = 0
            var monthNumber: Int32 = 0
            i = 0i32
            while (i < 14i32) {
                objectIndex = 2i32
                while (objectIndex >= 0i32) {
                    if (heShuo[Int64(i)] >= parse<Int32>(objects[Int64(objectIndex)].<-- Mapping is present but failed: toString -->())) {
                        break
                    }
                    objectIndex--
                }
                monthNumber = Int32(floor((Float64(heShuo[Int64(i)] - parse<Int32>(objects[Int64(objectIndex)].<-- Mapping is present but failed: toString -->()) + 15i32)) / 29.5306)) // 该月积数
                if (monthNumber < 12i32) {
                    monthNames[Int64(i)] = AnnalsUtils.MONTH_NAME_BIG_CN[Int64((monthNumber + parse<Int32>(objects[Int64(objectIndex + 6i32)].<-- Mapping is present but failed: toString -->())) % 12i32)]
                } else {
                    monthNames[Int64(i)] = objects[Int64(objectIndex + 3i32)].<-- Mapping is present but failed: toString -->()
                }
                i++
            }
            return
        }

        // 无中气置闰法确定闰月,(气朔结合法,数据源需有冬至开始的的气和朔)
        if (heShuoValue[13] <= zhongQiValue[24]) { // 第13月的月末没有超过冬至(不含冬至),说明今年含有13个月
            i = 1i32
            while (heShuoValue[Int64(i + 1i32)] > zhongQiValue[Int64(2i32 * i)] && i < 13i32) {
                // 在13个月中找第1个没有中气的月份
                i++
            }
            leapMonthIndex = i
            while (i < 14i32) {
                monthIndex[Int64(i)] -= 1
                i++
            }
        }

        // 名称转换(月建别名)
        i = 0i32
        while (i < 14i32) {
            let julianDayValue = heShuo[Int64(i)] + CommonUtils.JULIAN_FOR_2000
            let monthIndexValue = monthIndex[Int64(i)] // julianDayValue初一的儒略日,monthIndexValue为月建序号
            var monthName = AnnalsUtils.MONTH_NAME_BIG_CN[Int64(monthIndexValue % 12i32)] // 月建对应的默认月名称：建子十一,建丑十二,建寅为正……
            if (julianDayValue >= 1724360i32 && julianDayValue <= 1729794i32) {
                monthName = AnnalsUtils.MONTH_NAME_BIG_CN[Int64((monthIndexValue + 1i32) % 12i32)] // 8.01.15至 23.12.02 建子为十二,其它顺推
            } else {
                if (julianDayValue >= 1807724i32 && julianDayValue <= 1808699i32) {
                    monthName = AnnalsUtils.MONTH_NAME_BIG_CN[Int64((monthIndexValue + 1i32) % 12i32)] // 237.04.12至239.12.13 建子为十二,其它顺推
                } else {
                    if (julianDayValue >= 1999349i32 && julianDayValue <= 1999467i32) {
                        monthName = AnnalsUtils.MONTH_NAME_BIG_CN[Int64((monthIndexValue + 2i32) % 12i32)] // 761.12.02至762.03.30 建子为正月,其它顺推
                    } else {
                        if (julianDayValue >= 1973067i32 && julianDayValue <= 1977052i32) {
                            if (monthIndexValue % 12i32 == 0i32) {
                                monthName = "正"
                            }
                            if (monthIndexValue == 2i32) {
                                monthName = "一"
                            }
                        }
                    }
                }
            } // 689.12.18至700.11.15 建子为正月,建寅为一月,其它不变

            if (julianDayValue == 1729794i32 || julianDayValue == 1808699i32) {
                monthName = "拾贰" // 239.12.13及23.12.02均为十二月,为避免两个连续十二月，此处改名
            }

            monthNames[Int64(i)] = monthName
            i++
        }
    }

    public static func getsCorrectTable(): ?String {
        return sCorrectTable
    }

    public static func getqCorrectTable(): ?String {
        return qCorrectTable
    }

    public func getLeapMonthIndex(): Int32 {
        return leapMonthIndex
    }

    public func getMonthNames(): ?Array<?String> {
        return monthNames
    }

    public func getMonthIndex(): ?Array<Int32> {
        return monthIndex
    }

    public func getZhongQi(): ?Array<Int32> {
        return zhongQi
    }

    public func getHeShuo(): ?Array<Int32> {
        return heShuo
    }

    public func getMonthValue(): ?Array<Int32> {
        return monthValue
    }
}