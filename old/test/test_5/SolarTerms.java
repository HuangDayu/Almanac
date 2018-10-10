package test_5;
/***
 * 24节气枚举�?
 * @author Administrator
 *
 */
public enum SolarTerms {

    XIAO_HAN(20, "小寒", 1, 5),
    DA_HAN(21, "大寒", 1, 22),
    LI_CHUN(22, "立春", 2, 5),
    YU_SHUI(23, "雨水", 2, 22),
    JING_ZHE(24, "惊蛰", 3, 5),
    CHUN_FEN(1, "春分", 3, 22),
    QING_MING(2, "清明", 4, 5),
    GU_YU(3, "谷雨", 4, 22),
    LI_XIA(4, "立夏", 5, 5),
    XIAO_MAN(5, "小满", 5, 22),
    MANG_ZHONG(6, "芒种", 6, 5),
    XIA_ZHI(7, "夏至", 6, 22),
    XIAO_SHU(8, "小暑", 7, 5),
    DA_SHU(9, "大暑", 7, 22),
    LI_QIU(10, "立秋", 8, 5),
    CHU_SHU(11, "处暑", 8, 22),
    BAI_LU(12, "白露", 9, 5),
    QIU_FEN(13, "秋分", 9, 22),
    HAN_LU(14, "寒露", 10, 5),
    SHUANG_JIANG(15, "霜降", 10, 22),
    LI_DONG(16, "立冬", 11, 5),
    XIAO_XUE(17, "小雪", 11, 22),
    DA_XUE(18, "大雪", 12, 5),
    DONG_ZHI(19, "冬至", 12, 22);

    private int    order;
    private String name;
    private int    month;
    private int    estimateDate;

    SolarTerms(int order, String name, int month, int estimateDate) {
        this.order = order;
        this.name = name;
        this.month = month;
        this.estimateDate = estimateDate;
    }

    public int getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }

    public int getMonth() {
        return month;
    }

    public int getEstimateDate() {
        return estimateDate;
    }

}
