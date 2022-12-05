package com.alpha.pandemic.services;

import com.alpha.pandemic.mappers.UserRoleMapper;
import com.alpha.pandemic.models.UserRole;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRole>
{
    public List<Long> getRoleIdsByUserId(Long id)
    {
        return this.baseMapper.getRoleIdsByUserId(id);
    }

}
