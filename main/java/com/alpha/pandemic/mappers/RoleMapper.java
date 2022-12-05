package com.alpha.pandemic.mappers;

import com.alpha.pandemic.models.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role>
{

    List<String> queryRoleNamesByUserId(Long id);

    int updateRoleStatus(Long id, Integer status);
}
