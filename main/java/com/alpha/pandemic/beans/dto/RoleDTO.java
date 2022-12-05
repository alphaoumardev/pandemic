package com.alpha.pandemic.beans.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@Deprecated
public class RoleDTO
{
    private Long id;

    @NotBlank(message = "权限标识不能为空")
    private String roleCode;

    @NotBlank(message = "权限名称不能为空")
    private String roleName;

    private String remark;

    @NotNull(message = "角色状态不能为空")
//    @EnumValue(intValues = {0, 1})
    private Integer forbidden;
}
