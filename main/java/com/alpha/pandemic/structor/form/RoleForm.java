package com.alpha.pandemic.structor.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class RoleForm
{
    @NotNull(message = "The page can't be empty")
    @Min(value=1 , message = "The page can't be lower than 1")
    private Integer page =1;

    @NotNull
    private Integer size =10;
    private String roleName;

}
