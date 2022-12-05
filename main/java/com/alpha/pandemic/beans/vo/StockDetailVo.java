package com.alpha.pandemic.beans.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class StockDetailVo
{
    private String inNum;

    private Integer status;

    private Integer type;

    private String operator;

    private SupplierVo supplierVo;

    /** 总数**/
    private long total;

    private List<StockItemVo> itemVos = new ArrayList<>();
}
