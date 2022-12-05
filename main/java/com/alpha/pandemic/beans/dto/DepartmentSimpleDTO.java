package com.alpha.pandemic.beans.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author qiukangming
 * @version 1.0
 * @description 部门信息传输对象, 包含部门名字和人数
 * @since 2020/09/25 11:35
 */

@Data
@Deprecated
@ApiModel(value = "部门名字和人数", description = "部门信息传输对象, 包含部门名字和人数")
public class DepartmentSimpleDTO {
    @ApiModelProperty(value = "部门id")
    private Long id;

    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "部门所有的人数")
    private Integer deptCount;
}
