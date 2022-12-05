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
@TableName("image")
@ApiModel(value = "Image", description = "Image")
public class Image implements Serializable
{
    @ApiModelProperty(value = "Primary Key")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "Cover Name")
    private String name;

    @ApiModelProperty(value = "Image Directory")
    private String path;

    @ApiModelProperty(value = "Image size")
    private Long size;

    @ApiModelProperty(value = "Imge Type")
    private String mediaType;

    @ApiModelProperty(value = "Image suffix")
    private String suffix;

    @ApiModelProperty(value = "Image Height")
    private Integer height;

    @ApiModelProperty(value = "Width")
    private Integer width;

    @ApiModelProperty(value = "Create Date")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}
