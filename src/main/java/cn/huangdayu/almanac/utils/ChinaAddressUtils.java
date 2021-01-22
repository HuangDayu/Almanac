package cn.huangdayu.almanac.utils;

import cn.huangdayu.almanac.dto.AddressDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 中国行政区划分工具类
 *
 * @author huangdayu
 * @date 2020/4/3 15:53
 * @project Almanac
 */
public class ChinaAddressUtils {
    /**
     * @param address 详细地址
     * @return
     */
    public static List<AddressDTO> addressResolution(String address) {
        String regex = "(?<province>.*?省|.*?市)?(?<city>.*?市|.*?县|.*?区)?(?<county>.*?市|.*?县|.*?区)(?<village>.*?小区|.*?村)(?<building>.*?号楼|.*?楼|.*?栋|.*?队|.*?组|.*?号|.*?室)?(?<other>.*)";  //不要直接用这个，这个是经过大量精简后的
        List<AddressDTO> addressDTOS = new ArrayList<>();
        Matcher matcher = Pattern.compile(regex).matcher(address);
        while (matcher.find()) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setProvince(matcher.group("province"));
            addressDTO.setCity(matcher.group("city"));
            addressDTO.setCounty(matcher.group("county"));
            addressDTO.setVillage(matcher.group("village"));
            addressDTO.setBuilding(matcher.group("building"));
            addressDTO.setOther(matcher.group("other"));
            addressDTOS.add(addressDTO);
        }
        return addressDTOS;
    }
}
