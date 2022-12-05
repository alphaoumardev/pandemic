package com.alpha.pandemic.beans.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OutStockDetailVo
{
    private String outNum;

    private Integer status;

    private Integer type;

    private String operator;

    private ConsumerVo consumerVo;

    /**
     * 总数
     **/
    private long total;

    private List<OutStockItemVo> itemVos = new ArrayList<>();
}
