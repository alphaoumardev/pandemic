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
@TableName("menu")
@ApiModel(value = "Menu", description = "Menu list")
public class Menu implements Serializable
{
    @ApiModelProperty(value = "Menu ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "Parent ID")
    private Long parentId;

    @ApiModelProperty(value = "Menu Name")
    private String menuName;

    @ApiModelProperty(value = "URL")
    private String url;

    @ApiModelProperty(value = "permission")
    private String perms;

    @ApiModelProperty(value = "icon")
    private String icon;

    @ApiModelProperty(value = "Type 0:Menu 1:Button")
    private String type;

    @ApiModelProperty(value = "Range")
    private Long orderNum;

    @ApiModelProperty(value = "Creation Date")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "Modification Date")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifiedTime;

    @ApiModelProperty(value = "0：Unavailable，1：available")
    private Integer available;

    @ApiModelProperty(value = "0:Unopnable，1：Opnable")
    private Integer open;

}
