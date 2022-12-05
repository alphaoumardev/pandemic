package com.alpha.pandemic.beans.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class ConsumerVo
{
    private Long id;

    @NotBlank(message = "物资发放地点不能为空")
    private String name;

    @NotBlank(message = "省市县不能为空")
    private String address;

    private LocalDateTime createTime;

    private LocalDateTime modifiedTime;

    @NotBlank(message = "联系人电话不能为空")
    private String phone;

    @NotNull(message = "排序号不能为空")
    private  Integer sort;

    @NotBlank(message = "联系人姓名不能为空")
    private String contact;
}
