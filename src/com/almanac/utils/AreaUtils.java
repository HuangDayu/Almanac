package com.almanac.utils;

/**
 * 处理地址工具类
 *
 * @author huangdayu
 * @update 2020-03-15
 */
public class AreaUtils {
	/**
	 * 处理地址的方法
	 * @param prov
	 * @param area
	 * @return
	 */
	public static String[] judgeArea(String prov, String area) throws Exception {
		prov = prov.replaceAll("省", "");// 字符替代
		area = area.replaceAll("市", "").replaceAll("区", "").replaceAll("县", "").replaceAll("镇", "").replaceAll("乡", "");
		String[] strsArea = null;
		String Str_1 = null, Str_2 = null;
		String[] province = { "天津", "TJ", "河北", "HE", "青海", "QH", "西藏", "XZ", "浙江", "ZJ", "重庆", "CQ", "河南", "HA", "福建",
				"FJ", "贵州", "GZ", "广西", "GX", "江西", "JX", "新疆", "XJ", "甘肃", "GS", "湖北", "Hb", "江苏", "JS", "辽宁", "Ln",
				"吉林", "JL", "安徽", "AH", "山西", "sx", "陕西", "SN", "港澳臺", "GAT", "云南", "YN", "宁夏", "NX", "广东", "GD", "上海",
				"SH", "山东", "SD", "四川", "SC", "湖南", "HN", "黑龙", "HL", "北京", "BJ", "内蒙", "NM", "海南", "HI" };
		for (int i = 0; i < province.length; i++) {
			if (prov.contains(province[i])) {
				strsArea = PropertiesUtils.getAdministrativeProperties().getProperty(province[i + 1]).split(" ");// 根据给定正则表达式的匹配拆分此字符串。
				Str_1 = strsArea[0];
				break;
			}
		}
		for (int i = 1; i < strsArea.length; i++) { // && place.equals(strsArea[i].substring(4, 6))
			if (strsArea[i].contains(area) && area.length() > 1) {
				Str_2 = strsArea[i];
				break;
			}
			if (i == strsArea.length - 1) {
				throw new Exception("地址输入错误,地址只能到县级。请确保地址：" + Str_1 + area + " 是正确的。");
			}
		}
		String[] Strs = { Str_1, Str_2 };

		return Strs;
	}
}
