package com.alpha.pandemic.models;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_role")
@ApiModel(value = "UserRole", description = "The user role Table")
public class UserRole implements Serializable
{
    private Integer id;
    @ApiModelProperty(value = "User ID")
    private Long userId;

    @ApiModelProperty(value = "Role ID")
    private Long roleId;
}
