package com.alpha.pandemic.models;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("login_logs")
@ApiModel(value = "LoginLog", description = "Login logs")
public class LoginLogs implements Serializable
{
    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "Username")
    private String username;

    @ApiModelProperty(value = "Login Time")
    private LocalDateTime loginTime;

    @ApiModelProperty(value = "Login locatio")
    private String location;

    @ApiModelProperty(value = "IP Address")
    private String ip;

    @ApiModelProperty(value = "Operator System")
    private String userSystem;

    @ApiModelProperty(value = "Used Broswer")
    private String userBrowser;

}
