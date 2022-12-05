package com.alpha.pandemic.beans.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class OutStockVo
{
    private Long id;
    private String outNum;
    private Integer type;
    private String operator;
    private LocalDateTime createTime;
    private Integer productNumber;
    private Integer priority;
    private String remark;
    private Integer status;
    private Long consumerId;
    private String name;
    private String address;
    private String phone;
    private String contact;
    private Integer sort;
    //发放的物资列表
    private List<Object> products = new ArrayList<>();
}
