package com.alpha.pandemic.models;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
@ApiModel(value = "User", description = "The User table")
public class User implements Serializable
{
    @ApiModelProperty(value = "User ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "Username")
    private String username;

    @ApiModelProperty(value = "User Nickname")
    private String nickname;

    @ApiModelProperty(value = "Email")
    private String email;

    @ApiModelProperty(value = "Avatar")
    private String avatar;

    @ApiModelProperty(value = "Phone Number")
    private String phoneNumber;

    @ApiModelProperty(value = "Permission 0:Forbidden 1:Authorized")
    private Integer forbidden;

    @ApiModelProperty(value = "Creation Date")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "Modification Date")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedTime;

    @ApiModelProperty(value = "Gender 0: Male 1:Female 2:Secret")
    private Integer sex;

    @ApiModelProperty(value = "Salt")
    private String salt;

    @ApiModelProperty(value = "0:SysAdmin，1：SysUser")
    private Integer type;

    @ApiModelProperty(value = "Password")
    private String password;

    @ApiModelProperty(value = "Birthday")

    @DateTimeFormat(pattern="YYYY-MM-DD HH:mm:ss")
    private Date birth;

    @ApiModelProperty(value = "Department Id")
    private Long departmentId;

    private String name;//this one is for the list select not in the database just a join
}
