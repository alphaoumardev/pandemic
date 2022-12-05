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
@TableName("department")
@ApiModel(value = "Department", description = "Department")
public class Department implements Serializable
{

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "Department Name")
    private String name;

    @ApiModelProperty(value = "Department Phone Number")
    private String phone;

    @ApiModelProperty(value = "Address")
    private String address;

    @ApiModelProperty(value = "Creation")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "Modification Date")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifiedTime;
    private Integer deptCount;

}
