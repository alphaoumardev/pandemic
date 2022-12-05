package com.alpha.pandemic.beans.vo;

import lombok.Data;


@Data
public class StockItemVo
{
    private Long id;

    private String pNum;

    private String name;

    private String model;

    private String unit;

    private String imageUrl;

    private int count;
}
