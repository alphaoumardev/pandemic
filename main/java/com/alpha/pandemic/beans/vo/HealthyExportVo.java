package com.alpha.pandemic.beans.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HealthyExportVo
{

    @Excel(name = "ID", width = 25)
    private Long id;

    @Excel(name = " 打卡地点", width = 25)
    private String address;

    @Excel(name = "打卡用户名", width = 25)
    private String username;

    @Excel(name = "健康状态", width = 25)
    private String situation;

    @Excel(name = "是否接触过疑似人", width = 25)
    private String touch;

    @Excel(name = "是否接经过湖北地区(包括转车)", width = 25)
    private String passby;

    @Excel(name = "是否接待过湖北客户", width = 25)
    private String reception;

    @Excel(name = "打卡时间", width = 25)
    private LocalDateTime createTime;
}
