package com.alpha.pandemic.services;

import com.alpha.pandemic.mappers.RoleMapper;
import com.alpha.pandemic.mappers.RoleMenuMapper;
import com.alpha.pandemic.models.Role;
import com.alpha.pandemic.models.RoleMenu;
import com.alpha.pandemic.utils.exception.BusinessException;
import com.alpha.pandemic.utils.response.ResultCode;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class RoleMenuService extends ServiceImpl<RoleMenuMapper, RoleMenu>
{
    private final RoleMapper roleMapper;

    public List<Long> getRoleMenuById(Long id)
    {
        Role role = roleMapper.selectById(id);
        if(Objects.isNull(role))
        {
            throw new BusinessException(ResultCode.ROLE_NOT_EXIST);
        }
        return this.baseMapper.getMenuRoleListById(role);
    }
}
