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
@TableName("product")
@ApiModel(value = "Product", description = "Product")
public class Product implements Serializable
{
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "Product Id")
    private String pNum;

    @ApiModelProperty(value = "Product Name")
    private String name;

    @ApiModelProperty(value = "Cover")
    private String imageUrl;

    @ApiModelProperty(value = "Model")
    private String model;

    @ApiModelProperty(value = "Unit")
    private String unit;

    @ApiModelProperty(value = "Comments")
    private String remark;

    @ApiModelProperty(value = "Range")
    private Integer sort;

    @ApiModelProperty(value = "Creation Date")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "Modification Date")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifiedTime;

    @ApiModelProperty(value = "Level 1")
    private Long oneCategoryId;

    @ApiModelProperty(value = "Level 2")
    private Long twoCategoryId;

    @ApiModelProperty(value = "Level 3")
    private Long threeCategoryId;

    @ApiModelProperty(value = "Status:1 Normal,0:Returned,2:Loading")
    private Integer status;

}
