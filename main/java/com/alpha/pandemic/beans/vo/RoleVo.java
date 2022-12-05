package com.alpha.pandemic.beans.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alpha.pandemic.structor.validation.EnumValue;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoleVo
{
    @Excel(name = "角色ID", width = 25)
    private Long id;
    @Excel(name = "角色标识码", width = 25)
    private String roleCode;
    @Excel(name = "角色名称", width = 25)
    private String roleName;
    @Excel(name = "角色描述信息", width = 25)
    private String remark;
    @Excel(name = "创建角色信息时间", width = 25)
    private LocalDateTime createTime;
    @Excel(name = "修改角色信息时间", width = 25)
    private LocalDateTime modifiedTime;

    private Long[] rids;
    @Excel(name = "角色状态", width = 25)
    @EnumValue(intValues = {0, 1})
    private String forbidden;
}
