package com.alpha.pandemic.beans.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class SupplierVo
{
    private Long id;
    @NotBlank(message = "供应商名称不能为空")
    private String name;
    @NotBlank(message = "地址不能为空")
    private String address;
    @NotBlank(message = "邮箱不能为空")
    private String email;
    @NotBlank(message = "电话不能为空")
    private String phone;
    @NotNull(message = "排序号不能为空")
    private Integer sort;
    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;
    private String contact;
}
