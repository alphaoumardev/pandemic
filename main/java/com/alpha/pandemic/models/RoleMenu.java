package com.alpha.pandemic.models;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("role_menu")
@ApiModel(value = "RoleMenu", description = "The role menu table")
public class RoleMenu implements Serializable
{
    private Integer id;
    @ApiModelProperty(value = "Role Id")
    private Long roleId;

    @ApiModelProperty(value = "Role Menu ID")
    private Long menuId;

    @ApiModelProperty(value = "Menu Type")
    private Long menuType;

}
