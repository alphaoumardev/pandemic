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
@TableName("outstock")
@ApiModel(value = "OutStock", description = "OutStock")
public class OutStock implements Serializable
{
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "OutStock Number")
    private String outNum;

    @ApiModelProperty(value = "Type:0:Out,1:Not yet")
    private Integer type;

    @ApiModelProperty(value = "Oprator")
    private String operator;

    @ApiModelProperty(value = "Out Date")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "Total")
    private Integer productNumber;

    @ApiModelProperty(value = "Consumer Id")
    private Long consumerId;

    @ApiModelProperty(value = "Comments")
    private String remark;

    @ApiModelProperty(value = "Status:0:Normal,1:Out,2:Not yet")
    private Integer status;

    @ApiModelProperty(value = "Not urgent:1:Normal,2:Normal,3:Urgent4:very Urgent")
    private Integer priority;

}
