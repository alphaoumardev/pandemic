package com.alpha.pandemic.models;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("role")
@ApiModel(value = "Role", description = "The role table")
public class Role implements Serializable
{
    @ApiModelProperty(value = "Role ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "Role code")
    private String roleCode;

    @ApiModelProperty(value = "Role Name")
    private String roleName;

    @ApiModelProperty(value = "Role Description")
    private String remark;

    @ApiModelProperty(value = "Creation Date")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "Modification Date")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifiedTime;

    @ApiModelProperty(value = "Permission,0:Useless，1：Available")
    private Integer forbidden;

}
