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
@TableName("stock")
@ApiModel(value = "stock", description = "stock")
public class Stock implements Serializable
{
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "stock number")
    private String inNum;

    @ApiModelProperty(value = "Type：1：lost，2：sortage，3：ready, 4:returns")
    private Integer type;

    @ApiModelProperty(value = "Operator")
    private String operator;

    @ApiModelProperty(value = "Stocking Date")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "Modification Date")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modified;

    @ApiModelProperty(value = "Total Resources")
    private Integer productNumber;

    @ApiModelProperty(value = "Supplier")
    private Long supplierId;

    @ApiModelProperty(value = "Description")
    private String remark;

    @ApiModelProperty(value = "0:Normal,1:Trashed,2:charge")
    private Integer status;

}
