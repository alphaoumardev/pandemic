package com.alpha.pandemic.structor.form;

import lombok.Data;

@Data
public class UserForm
{
    private Integer departmentId;
    private String username;
    private String email;
    private Integer sex;
    private String nikeName;
    private String forbidden;
}
