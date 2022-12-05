package com.alpha.pandemic.models;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author qiukangming
 * @since 2020-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("productstock")
@ApiModel(value = "ProductStock", description = "ProductStock")
public class ProductStock implements Serializable
{
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String pNum;

    @ApiModelProperty(value = "Stock")
    private Long stock;

}
