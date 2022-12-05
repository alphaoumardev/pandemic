package com.alpha.pandemic.beans.vo;

import lombok.Data;

@Data
public class ProductStockVo
{
    private Long id;
    private String name;
    private String pNum;
    private String model;
    private String unit;
    private String remark;
    private Long stock;
    private String imageUrl;
}
