package com.alpha.pandemic.beans.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StockExportVo
{

    @Excel(name = "ID", width = 25)
    private Long id;

    @Excel(name = "入库单号", width = 25)
    private String inNum;

    @Excel(name = "物资类型", width = 25)
    private String type;

    @Excel(name = "物资数量", width = 25)
    private Integer productNumber;

    @Excel(name = "物资提供方联系方式", width = 25)
    private String phone;

    @Excel(name = "物资状态", width = 25)
    private String status;

    @Excel(name = "操作员", width = 25)
    private String operator;

    @Excel(name = "物资提供方", width = 25)
    private String supplierName;

    @Excel(name = "入库时间", width = 25)
    private LocalDateTime createTime;
}
