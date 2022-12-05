package com.alpha.pandemic.beans.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "用户登录信息传输对象", description = "登录")
public class LoginVo
{
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名", notes = "用户名")
    private String username;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", notes = "密码")
    private String password;

    @ApiModelProperty(value = "回调地址", notes = "返回地址")
    private String backUrl;

    @NotBlank(message = "验证码ID不能为空")
    @ApiModelProperty(value = "验证码唯一标识", notes = "验证码标识")
    private String verifyCodeId;

    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码", notes = "验证码")
    private String verifyCode;
}
