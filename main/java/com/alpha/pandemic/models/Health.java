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
@TableName("health")
@ApiModel(value = "Health", description = "Health")
public class Health implements Serializable
{
    @ApiModelProperty(value = "Key id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "Address")
    private String address;

    @ApiModelProperty(value = "User Id")
    private Long userId;

    private Integer situation;

    private Integer touch;

    private Integer passby;

    private Integer reception;

    @ApiModelProperty(value = "Creation Date")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
