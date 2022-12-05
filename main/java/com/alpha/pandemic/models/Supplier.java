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
@TableName("supplier")
@ApiModel(value = "Supplier", description = "Supplier")
public class Supplier implements Serializable
{
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "Supplier Name")
    private String name;

    @ApiModelProperty(value = "Address")
    private String address;

    @ApiModelProperty(value = "Email")
    private String email;

    @ApiModelProperty(value = "Phone Number")
    private String phone;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifiedTime;

    @ApiModelProperty(value = "Range")
    private Integer sort;

    @ApiModelProperty(value = "Contact")
    private String contact;

}
