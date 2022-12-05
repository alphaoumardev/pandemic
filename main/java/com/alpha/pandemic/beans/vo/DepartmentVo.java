package com.alpha.pandemic.beans.vo;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class DepartmentVo
{
    private Long id;
    private String name;
    private Integer total;
    private String phone;
    private String address;
    private Integer deptCount;
    private String deptName;
    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;
}
