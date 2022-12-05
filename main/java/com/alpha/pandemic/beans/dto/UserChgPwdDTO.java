package com.alpha.pandemic.beans.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author qiukangming
 * @version 1.0
 * @date 2021/01/17 13:40
 * @description 用户修改密码
 */

@Data
@Deprecated
public class UserChgPwdDTO {

    @NotNull(message = "用户信息错误[ ID不能为空 ]")
    private Long id;

    @NotBlank(message = "旧密码不能为空")
    private String oldPwd;

    @NotBlank(message = "新密码不能为空")
    private String newPwd;
}
