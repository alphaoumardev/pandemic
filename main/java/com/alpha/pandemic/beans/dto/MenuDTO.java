package com.alpha.pandemic.beans.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Deprecated
@ApiModel(value = "菜单数据传输", description = "菜单数据传输，用于编辑菜单，添加菜单")
public class MenuDTO {
    private Long id;

    @NotNull(message = "父级ID不能为空")
    private Long parentId;

    @NotBlank(message = "菜单名称不能为空")
    private String menuName;

    private String url;

    private String icon;

    @NotBlank(message = "菜单类型不为空")
    private String type;

    @NotNull(message = "排序数不能为空")
    private Long orderNum;

    private Date createTime;

    private Date modifiedTime;

    @NotNull(message = "菜单状态不能为空")
    private Boolean disabled;

    private Integer open;

    private String perms;

    private List<Long> ids;
}
