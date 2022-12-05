package com.alpha.pandemic.beans.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author qiukangming
 * @version 1.0
 * @description 用户传输对象
 * @since 2020/09/28 09:01
 */

@Data
@Deprecated
public class UserDTO {
    @ApiModelProperty(value = "用户头像", notes = "头像")
    private String avatar;

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名", notes = "用户名")
    private String username;

    @NotNull(message = "请选择部门")
    @ApiModelProperty(value = "部门", notes = "部门编号")
    private Long departmentId;

    @NotBlank(message = "昵称不能为空")
    @ApiModelProperty(value = "昵称", notes = "昵称")
    private String nickname;

    @NotNull(message = "性别不能为空")
    @ApiModelProperty(value = "性别", notes = "性别")
    private Integer sex;

    @ApiModelProperty(value = "密码", notes = "密码")
    private String password;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @ApiModelProperty(value = "邮箱", notes = "邮箱")
    private String email;

    @NotBlank(message = "电话不能为空")
    @ApiModelProperty(value = "电话", notes = "电话")
    private String phoneNumber;

    @NotNull(message = "生日不能为空")
    @ApiModelProperty(value = "生日", notes = "生日")
    private LocalDate birth;
}
