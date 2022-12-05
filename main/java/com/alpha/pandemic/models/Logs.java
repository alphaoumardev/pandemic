package com.alpha.pandemic.models;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("logs")
@ApiModel(value = "Logs", description = "Daily logs")
public class Logs implements Serializable
{
    @ApiModelProperty(value = "logs ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "Operator")
    private String username;

    @ApiModelProperty(value = "Content")
    private String operation;

    @ApiModelProperty(value = "Duration")
    private BigDecimal time;

    @ApiModelProperty(value = "Opration Method")
    private String method;

    @ApiModelProperty(value = "Result")
    private String result;

    @ApiModelProperty(value = "Parameters")
    private String params;

    @ApiModelProperty(value = "Operator Ip Address")
    private String ip;

    @ApiModelProperty(value = "Creation Date")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "Opration's location")
    private String location;

}
