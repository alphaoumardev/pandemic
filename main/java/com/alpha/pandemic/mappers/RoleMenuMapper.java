package com.alpha.pandemic.mappers;

import com.alpha.pandemic.models.Role;
import com.alpha.pandemic.models.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu>
{
    List<Long> getMenuRoleListById(Role role);
}
