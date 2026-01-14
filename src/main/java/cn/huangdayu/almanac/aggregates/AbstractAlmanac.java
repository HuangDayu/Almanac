package cn.huangdayu.almanac.aggregates;

import cn.huangdayu.almanac.dto.InfoDTO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractAlmanac {

    public abstract InfoDTO getBaseInfo();

    public LinkedList<InfoDTO> getAllInfo() {
        return new LinkedList<>();
    }

}
