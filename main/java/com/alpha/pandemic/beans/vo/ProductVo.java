package com.alpha.pandemic.beans.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class ProductVo
{
    private Long id;
    private String pNum;
    @NotBlank(message = "商品名称不能为空")
    private String name;
    @NotBlank(message = "规格型号不能为空")
    private String model;
    @NotBlank(message = "计算单位不能为空")
    private String unit;
    @NotBlank(message = "备注不能为空")
    private String remark;
    private Integer sort;
    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;
    private String imageUrl;
    @NotNull(message = "分类不能为空")
    private Long[] categoryKeys;
    private Long oneCategoryId;
    private Long twoCategoryId;
    private Long threeCategoryId;
    private Integer status; // 是否已经进入回收站:1:逻辑删除,0:正常数据,2:添加待审核
}
