package com.alpha.pandemic.mappers;

import com.alpha.pandemic.models.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole>
{
    List<Long> getRoleIdsByUserId(Long id);
}
