package com.alpha.pandemic.beans.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


@Data
public class UserVo
{
    private Long id;
    private String avatar;
    private String username;
    private String nickname;
    private String sex;
    private String deptName;
    private LocalDate birth;
    private String email;
    private String phoneNumber;
    private String roles;
    private Boolean forbidden;
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
}
