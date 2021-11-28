package cn.huangdayu.almanac.aggregates.qishuo;

import cn.huangdayu.almanac.utils.AstronomyArithmeticUtils;
import cn.huangdayu.almanac.utils.AnnalsUtils;
import cn.huangdayu.almanac.utils.CommonUtils;
import lombok.Getter;

/***
 * 气朔计算和参数数据表类
 *
 */
@Getter
public class QiShuo {

    private QiShuo() {
    }

    public QiShuo(int julianDayForToday) {
        // 此处有线程安全问题, 暂时直接实例化，qiShuoDO.calcY是为减少计算次数而做的判断。（同一年数据一样）
        if (julianDayForToday < zhongQi[0] || julianDayForToday >= zhongQi[24]) {
            calculateMonth(julianDayForToday);
        }
    }


    // 朔修正表
    private static String sCorrectTable = "";
    // 气修正表
    private static String qCorrectTable = "";

    // 朔直线拟合参数
    private static final double[] S_FITTING_PARAMETERS = {1457698.231017, 29.53067166, 1546082.512234, 29.53085106, 1640640.735300,
            29.53060000, 1642472.151543, 29.53085439, 1683430.509300, 29.53086148, 1752148.041079, 29.53085097,
            1807665.420323, 29.53059851, 1883618.114100, 29.53060000, 1907360.704700, 29.53060000, 1936596.224900,
            29.53060000, 1939135.675300, 29.53060000, 1947168.00};

    // 气直线拟合参数
    private static final double[] Q_FITTING_PARAMETERS = {1640650.479938, 15.21842500, 1642476.703182, 15.21874996, 1683430.515601,
            15.218750011, 1752157.640664, 15.218749978, 1807675.003759, 15.218620279, 1883627.765182, 15.218612292,
            1907369.128100, 15.218449176, 1936603.140413, 15.218425000, 1939145.524180, 15.218466998,
            1947180.798300, 15.218524844, 1964362.041824, 15.218533526, 1987372.340971, 15.218513908,
            1999653.819126, 15.218530782, 2007445.469786, 15.218535181, 2021324.917146, 15.218526248,
            2047257.232342, 15.218519654, 2070282.898213, 15.218425000, 2073204.872850, 15.218515221,
            2080144.500926, 15.218530782, 2086703.688963, 15.218523776, 2110033.182763, 15.218425000,
            2111190.300888, 15.218425000, 2113731.271005, 15.218515671, 2120670.840263, 15.218425000,
            2123973.309063, 15.218425000, 2125068.997336, 15.218477932, 2136026.312633, 15.218472436,
            2156099.495538, 15.218425000, 2159021.324663, 15.218425000, 2162308.575254, 15.218461742,
            2178485.706538, 15.218425000, 2178759.662849, 15.218445786, 2185334.020800, 15.218425000,
            2187525.481425, 15.218425000, 2188621.191481, 15.218437494, 2322147.76};

    static {
        // 619-01-21开始16598个朔日修正表 d0=1947168
        String sCorrectTableValue = "EqoFscDcrFpmEsF2DfFideFelFpFfFfFiaipqti1ksttikptikqckstekqttgkqttgkqteksttikptikq2fjstgjqttjkqttgkqt";
        sCorrectTableValue += "ekstfkptikq2tijstgjiFkirFsAeACoFsiDaDiADc1AFbBfgdfikijFifegF1FhaikgFag1E2btaieeibggiffdeigFfqDfaiBkF";
        sCorrectTableValue += "1kEaikhkigeidhhdiegcFfakF1ggkidbiaedksaFffckekidhhdhdikcikiakicjF1deedFhFccgicdekgiFbiaikcfi1kbFibef";
        sCorrectTableValue += "gEgFdcFkFeFkdcfkF1kfkcickEiFkDacFiEfbiaejcFfffkhkdgkaiei1ehigikhdFikfckF1dhhdikcfgjikhfjicjicgiehdik";
        sCorrectTableValue += "cikggcifgiejF1jkieFhegikggcikFegiegkfjebhigikggcikdgkaFkijcfkcikfkcifikiggkaeeigefkcdfcfkhkdgkegieid";
        sCorrectTableValue += "hijcFfakhfgeidieidiegikhfkfckfcjbdehdikggikgkfkicjicjF1dbidikFiggcifgiejkiegkigcdiegfggcikdbgfgefjF1";
        sCorrectTableValue += "kfegikggcikdgFkeeijcfkcikfkekcikdgkabhkFikaffcfkhkdgkegbiaekfkiakicjhfgqdq2fkiakgkfkhfkfcjiekgFebicg";
        sCorrectTableValue += "gbedF1jikejbbbiakgbgkacgiejkijjgigfiakggfggcibFifjefjF1kfekdgjcibFeFkijcfkfhkfkeaieigekgbhkfikidfcje";
        sCorrectTableValue += "aibgekgdkiffiffkiakF1jhbakgdki1dj1ikfkicjicjieeFkgdkicggkighdF1jfgkgfgbdkicggfggkidFkiekgijkeigfiski";
        sCorrectTableValue += "ggfaidheigF1jekijcikickiggkidhhdbgcfkFikikhkigeidieFikggikhkffaffijhidhhakgdkhkijF1kiakF1kfheakgdkif";
        sCorrectTableValue += "iggkigicjiejkieedikgdfcggkigieeiejfgkgkigbgikicggkiaideeijkefjeijikhkiggkiaidheigcikaikffikijgkiahi1";
        sCorrectTableValue += "hhdikgjfifaakekighie1hiaikggikhkffakicjhiahaikggikhkijF1kfejfeFhidikggiffiggkigicjiekgieeigikggiffig";
        sCorrectTableValue += "gkidheigkgfjkeigiegikifiggkidhedeijcfkFikikhkiggkidhh1ehigcikaffkhkiggkidhh1hhigikekfiFkFikcidhh1hit";
        sCorrectTableValue += "cikggikhkfkicjicghiediaikggikhkijbjfejfeFhaikggifikiggkigiejkikgkgieeigikggiffiggkigieeigekijcijikgg";
        sCorrectTableValue += "ifikiggkideedeijkefkfckikhkiggkidhh1ehijcikaffkhkiggkidhh1hhigikhkikFikfckcidhh1hiaikgjikhfjicjicgie";
        sCorrectTableValue += "hdikcikggifikigiejfejkieFhegikggifikiggfghigkfjeijkhigikggifikiggkigieeijcijcikfksikifikiggkidehdeij";
        sCorrectTableValue += "cfdckikhkiggkhghh1ehijikifffffkhsFngErD1pAfBoDd1BlEtFqA2AqoEpDqElAEsEeB2BmADlDkqBtC1FnEpDqnEmFsFsAFn";
        sCorrectTableValue += "llBbFmDsDiCtDmAB2BmtCgpEplCpAEiBiEoFqFtEqsDcCnFtADnFlEgdkEgmEtEsCtDmADqFtAFrAtEcCqAE1BoFqC1F1DrFtBmF";
        sCorrectTableValue += "tAC2ACnFaoCgADcADcCcFfoFtDlAFgmFqBq2bpEoAEmkqnEeCtAE1bAEqgDfFfCrgEcBrACfAAABqAAB1AAClEnFeCtCgAADqDoB";
        sCorrectTableValue += "mtAAACbFiAAADsEtBqAB2FsDqpFqEmFsCeDtFlCeDtoEpClEqAAFrAFoCgFmFsFqEnAEcCqFeCtFtEnAEeFtAAEkFnErAABbFkAD";
        sCorrectTableValue += "nAAeCtFeAfBoAEpFtAABtFqAApDcCGJ";

        // 1645-09-23开始7567个节气修正表
        String qCorrectTableValue = "FrcFs22AFsckF2tsDtFqEtF1posFdFgiFseFtmelpsEfhkF2anmelpFlF1ikrotcnEqEq2FfqmcDsrFor22FgFrcgDscFs22FgEe";
        qCorrectTableValue += "FtE2sfFs22sCoEsaF2tsD1FpeE2eFsssEciFsFnmelpFcFhkF2tcnEqEpFgkrotcnEqrEtFermcDsrE222FgBmcmr22DaEfnaF22";
        qCorrectTableValue += "2sD1FpeForeF2tssEfiFpEoeFssD1iFstEqFppDgFstcnEqEpFg11FscnEqrAoAF2ClAEsDmDtCtBaDlAFbAEpAAAAAD2FgBiBqo";
        qCorrectTableValue += "BbnBaBoAAAAAAAEgDqAdBqAFrBaBoACdAAf1AACgAAAeBbCamDgEifAE2AABa1C1BgFdiAAACoCeE1ADiEifDaAEqAAFe1AcFbcA";
        qCorrectTableValue += "AAAAF1iFaAAACpACmFmAAAAAAAACrDaAAADG0";

        sCorrectTable = unzipCorrectTableValue(sCorrectTableValue); // 定朔修正表解压
        qCorrectTable = unzipCorrectTableValue(qCorrectTableValue); // 定气修正表解压
    }

    /***
     * 低精度定朔计算,在2000年至600，误差在2小时以内(仍比古代日历精准很多)
     *
     * @param w
     * @return
     */
    private static double sLowCalculate(double w) {
        double v = 7771.37714500204;
        double t = (w + 1.08472) / v;
        t -= (-0.0000331 * t * t + 0.10976 * Math.cos(0.785 + 8328.6914 * t)
                + 0.02224 * Math.cos(0.187 + 7214.0629 * t) - 0.03342 * Math.cos(4.669 + 628.3076 * t)) / v
                + (32 * (t + 1.8) * (t + 1.8) - 20) / 86400 / 36525;
        return t * 36525 + (double) 8 / 24;
    }

    /****
     * 最大误差小于30分钟，平均5分
     *
     * @param w
     * @return
     */
    private static double qLowCalculate(double w) {
        double t, l, v = 628.3319653318;
        t = (w - 4.895062166) / v; // 第一次估算,误差2天以内
        t -= (53 * t * t + 334116 * Math.cos(4.67 + 628.307585 * t) + 2061 * Math.cos(2.678 + 628.3076 * t) * t) / v
                / 10000000; // 第二次估算,误差2小时以内

        l = 48950621.66 + 6283319653.318 * t + 53 * t * t // 平黄经
                + 334166 * Math.cos(4.669257 + 628.307585 * t) // 地球椭圆轨道级数展开
                + 3489 * Math.cos(4.6261 + 1256.61517 * t) // 地球椭圆轨道级数展开
                + 2060.6 * Math.cos(2.67823 + 628.307585 * t) * t // 一次泊松项
                - 994 - 834 * Math.sin(2.1824 - 33.75705 * t); // 光行差与章动修正

        t -= (l / 10000000 - w) / 628.332 + (32 * (t + 1.8) * (t + 1.8) - 20) / 86400 / 36525;
        return t * 36525 + (double) 8 / 24;
    }

    /***
     * 较高精度气
     *
     * @param w
     * @return
     */
    private static double qHighCalculate(double w) {
        double t = AstronomyArithmeticUtils.S_aLon_t2(w) * 36525;
        t = t - CommonUtils.dtT(t) + (double) 8 / 24;
        double v = ((t + 0.5) % 1) * 86400;
        if (v < 1200 || v > 86400 - 1200) {
            t = AstronomyArithmeticUtils.S_aLon_t(w) * 36525 - CommonUtils.dtT(t) + (double) 8 / 24;
        }
        return t;
    }

    /****
     * 较高精度朔
     *
     * @param w
     * @return
     */
    private static double sHighCalculate(double w) {
        double t = AstronomyArithmeticUtils.MS_aLon_t2(w) * 36525;
        t = t - CommonUtils.dtT(t) + (double) 8 / 24;
        double v = ((t + 0.5) % 1) * 86400;
        if (v < 1800 || v > 86400 - 1800) {
            t = AstronomyArithmeticUtils.MS_aLon_t(w) * 36525 - CommonUtils.dtT(t) + (double) 8 / 24;
        }
        return t;
    }

    /*****
     * 气朔解压缩
     *
     * @param s
     * @return
     */
    private static String unzipCorrectTableValue(String s) {
        String o = "0000000000", o2 = o + o;
        s = s.replace("/J/g", "00");
        s = s.replace("/I/g", "000");
        s = s.replace("/H/g", "0000");
        s = s.replace("/G/g", "00000");
        s = s.replace("/t/g", "02");
        s = s.replace("/s/g", "002");
        s = s.replace("/r/g", "0002");
        s = s.replace("/q/g", "00002");
        s = s.replace("/p/g", "000002");
        s = s.replace("/o/g", "0000002");
        s = s.replace("/n/g", "00000002");
        s = s.replace("/m/g", "000000002");
        s = s.replace("/l/g", "0000000002");
        s = s.replace("/k/g", "01");
        s = s.replace("/j/g", "0101");
        s = s.replace("/i/g", "001");
        s = s.replace("/h/g", "001001");
        s = s.replace("/g/g", "0001");
        s = s.replace("/f/g", "00001");
        s = s.replace("/e/g", "000001");
        s = s.replace("/d/g", "0000001");
        s = s.replace("/c/g", "00000001");
        s = s.replace("/b/g", "000000001");
        s = s.replace("/a/g", "0000000001");
        s = s.replace("/A/g", o2 + o2 + o2);
        s = s.replace("/B/g", o2 + o2 + o);
        s = s.replace("/C/g", o2 + o2);
        s = s.replace("/D/g", o2 + o);
        s = s.replace("/E/g", o2);
        s = s.replace("/F/g", o);
        return s;
    }

    /*****
     * jd应靠近所要取得的气朔日,qs="气"时，算节气的儒略日
     *
     * @param julianDay
     * @param qs
     * @return
     */
    private static int calculateJulianDay(double julianDay, String qs) {
        julianDay += 2451545;
        int i;
        double D = 0;
        String n;
        double[] B = S_FITTING_PARAMETERS;
        int pc = 14;
        if (qs.equals("气")) {
            B = Q_FITTING_PARAMETERS;
            pc = 7;
        }
        double f1 = B[0] - pc, f2 = B[B.length - 1] - pc, f3 = 2436935;

        if (julianDay < f1 || julianDay >= f3) { // 平气朔表中首个之前，使用现代天文算法。1960.1.1以后，使用现代天文算法
            // (这一部分调用了qi_high和so_high,所以需星历表支持)
            if (qs.equals("气")) {
                return (int) Math
                        .floor(qHighCalculate(Math.floor((julianDay + pc - 2451259) / 365.2422 * 24) * Math.PI / 12) + 0.5); // 2451259是1999.3.21,太阳视黄经为0,春分.定气计算
            } else {
                return (int) Math.floor(sHighCalculate(Math.floor((julianDay + pc - 2451551) / 29.5306) * Math.PI * 2) + 0.5); // 2451551是2000.1.7的那个朔日,黄经差为0.定朔计算
            }
        }

        if (julianDay >= f1 && julianDay < f2) { // 平气或平朔
            for (i = 0; i < B.length; i += 2) {
                if (julianDay + pc < B[i + 2]) {
                    break;
                }
            }
            D = (B[i] + B[i + 1] * Math.floor((julianDay + pc - B[i]) / B[i + 1]));
            D = Math.floor(D + 0.5);
            if ((int) D == 1683460) {
                D++; // 如果使用太初历计算-103年1月24日的朔日,结果得到的是23日,这里修正为24日(实历)。修正后仍不影响-103的无中置闰。如果使用秦汉历，得到的是24日，本行D不会被执行。
            }
            return (int) (D - 2451545);
        }

        if (julianDay >= f2 && julianDay < f3) { // 定气或定朔
            if (qs.equals("气")) {
                D = Math.floor(qLowCalculate(Math.floor((julianDay + pc - 2451259) / 365.2422 * 24) * Math.PI / 12) + 0.5); // 2451259是1999.3.21,太阳视黄经为0,春分.定气计算
                int index = (int) Math.floor((julianDay - f2) / 365.2422 * 24);
                n = CommonUtils.subString(qCorrectTable, index, index + 1); // 找定气修正值
            } else {
                D = Math.floor(sLowCalculate(Math.floor((julianDay + pc - 2451551) / 29.5306) * Math.PI * 2) + 0.5); // 2451551是2000.1.7的那个朔日,黄经差为0.定朔计算
                int index = (int) Math.floor((julianDay - f2) / 29.5306);
                n = CommonUtils.subString(sCorrectTable, index, index + 1); // 找定朔修正值
            }
            if (n.equals("1")) {
                return (int) (D + 1);
            }
            if (n.equals("2")) {
                return (int) (D - 1);
            }
            return (int) D;
        }
        return (int) D;
    }

    /***
     * 闰月位置
     */
    public int leapMonthIndex = 0;
    /***
     * 各月名称
     */
    public String[] monthNames = new String[14];
    /***
     * 各月数字号码
     */
    private int[] monthIndex = new int[14];
    /***
     * 中气表,其中.liqiu是节气立秋的儒略日,计算三伏时用到
     */
    public int[] zhongQi = new int[27];
    /***
     * 合朔表
     */
    public int[] heShuo = new int[15];
    /***
     * 各月大小
     */
    public int[] monthValue = new int[15];

    /***
     * 农历排月序计算,可定出农历,有效范围：两个冬至之间(冬至一 <= d < 冬至二)
     *
     * @param julianDay
     */
    private void calculateMonth(int julianDay) {
        int[] zhongQiValue = zhongQi, heShuoValue = heShuo; // 中气表,日月合朔表(整日)
        int i;
        double j, k;

        // 该年的气
        j = Math.floor((julianDay - 355 + 183) / 365.2422) * 365.2422 + 355; // 355是2000.12冬至,得到较靠近jd的冬至估计值
        if (calculateJulianDay(j, "气") > julianDay) {
            j -= 365.2422;
        }
        for (i = 0; i < 25; i++) {
            zhongQiValue[i] = calculateJulianDay(j + 15.2184 * i, "气"); // 25个节气时刻(北京时间),从冬至开始到下一个冬至以后
        }
        zhongQiValue[25] = calculateJulianDay(j - 15.2, "气");
        zhongQiValue[26] = calculateJulianDay(j - 30.4, "气"); // 补算二气,确保一年中所有月份的“气”全部被计算在内

        // 今年"首朔"的日月黄经差w
        k = calculateJulianDay(zhongQiValue[0], "朔"); // 求较靠近冬至的朔日
        if (k > zhongQiValue[0]) {
            k -= 29.53;
        }

        // 该年所有朔,包含14个月的始末
        for (i = 0; i < 15; i++) {
            heShuoValue[i] = calculateJulianDay(k + 29.5306 * i, "朔");
        }

        // 月大小
        leapMonthIndex = 0;
        for (i = 0; i < 14; i++) {
            monthValue[i] = heShuo[i + 1] - heShuo[i]; // 月大小
            monthIndex[i] = i; // 月序初始化
        }

        // -721年至-104年的后九月及月建问题,与朔有关，与气无关
        int year = (int) (Math.floor((zhongQi[0] + 10 + 180) / 365.2422) + 2000); // 确定年份
        if (year >= -721 && year <= -104) {
            // FIXME 2021-02-18 原先数组长度为8，但是计算公元前时间时，出现下标溢出，所以设置为10，不知是否有问题
            Object[] objects = new Object[10];
            int yearIndex;
            for (i = 0; i < 3; i++) {
                yearIndex = year + i - 1;
                // 颁行历年首, 闰月名称, 月建
                if (yearIndex >= -721) {
                    objects[i] = calculateJulianDay(1457698 - CommonUtils.JULIAN_FOR_2000 + Math.floor(0.342 + (yearIndex + 721) * 12.368422) * 29.5306, "朔");
                    objects[i + 3] = "十三";
                    objects[i + 6] = 2;
                } // 春秋历,ly为-722.12.17
                if (yearIndex >= -479) {
                    objects[i] = calculateJulianDay(1546083 - CommonUtils.JULIAN_FOR_2000 + Math.floor(0.500 + (yearIndex + 479) * 12.368422) * 29.5306, "朔");
                    objects[i + 3] = "十三";
                    objects[i + 6] = 2;
                } // 战国历,ly为-480.12.11
                if (yearIndex >= -220) {
                    objects[i] = calculateJulianDay(1640641 - CommonUtils.JULIAN_FOR_2000 + Math.floor(0.866 + (yearIndex + 220) * 12.369000) * 29.5306, "朔");
                    objects[i + 3] = "后九";
                    objects[i + 6] = 11;
                } // 秦汉历,ly为-221.10.31
            }
            int objectIndex, monthNumber;
            for (i = 0; i < 14; i++) {
                for (objectIndex = 2; objectIndex >= 0; objectIndex--) {
                    if (heShuo[i] >= Integer.parseInt(objects[objectIndex].toString())) {
                        break;
                    }
                }
                monthNumber = (int) Math.floor((heShuo[i] - Integer.parseInt(objects[objectIndex].toString()) + 15) / 29.5306); // 该月积数
                if (monthNumber < 12) {
                    monthNames[i] = AnnalsUtils.MONTH_NAME_BIG_CN[(monthNumber + Integer.parseInt(objects[objectIndex + 6].toString())) % 12];
                } else {
                    monthNames[i] = objects[objectIndex + 3].toString();
                }
            }
            return;
        }

        // 无中气置闰法确定闰月,(气朔结合法,数据源需有冬至开始的的气和朔)
        if (heShuoValue[13] <= zhongQiValue[24]) { // 第13月的月末没有超过冬至(不含冬至),说明今年含有13个月
            for (i = 1; heShuoValue[i + 1] > zhongQiValue[2 * i] && i < 13; i++) {
                ; // 在13个月中找第1个没有中气的月份
            }
            leapMonthIndex = i;
            for (; i < 14; i++) {
                monthIndex[i]--;
            }
        }

        // 名称转换(月建别名)
        for (i = 0; i < 14; i++) {
            int julianDayValue = heShuo[i] + CommonUtils.JULIAN_FOR_2000, monthIndexValue = monthIndex[i]; // julianDayValue初一的儒略日,monthIndexValue为月建序号
            String monthName = AnnalsUtils.MONTH_NAME_BIG_CN[monthIndexValue % 12]; // 月建对应的默认月名称：建子十一,建丑十二,建寅为正……
            if (julianDayValue >= 1724360 && julianDayValue <= 1729794) {
                monthName = AnnalsUtils.MONTH_NAME_BIG_CN[(monthIndexValue + 1) % 12]; // 8.01.15至 23.12.02 建子为十二,其它顺推
            } else if (julianDayValue >= 1807724 && julianDayValue <= 1808699) {
                monthName = AnnalsUtils.MONTH_NAME_BIG_CN[(monthIndexValue + 1) % 12]; // 237.04.12至239.12.13 建子为十二,其它顺推
            } else if (julianDayValue >= 1999349 && julianDayValue <= 1999467) {
                monthName = AnnalsUtils.MONTH_NAME_BIG_CN[(monthIndexValue + 2) % 12]; // 761.12.02至762.03.30 建子为正月,其它顺推
            } else if (julianDayValue >= 1973067 && julianDayValue <= 1977052) {
                if (monthIndexValue % 12 == 0) {
                    monthName = "正";
                }
                if (monthIndexValue == 2) {
                    monthName = "一";
                }
            } // 689.12.18至700.11.15 建子为正月,建寅为一月,其它不变

            if (julianDayValue == 1729794 || julianDayValue == 1808699) {
                monthName = "拾贰"; // 239.12.13及23.12.02均为十二月,为避免两个连续十二月，此处改名
            }

            monthNames[i] = monthName;
        }
    }

}
