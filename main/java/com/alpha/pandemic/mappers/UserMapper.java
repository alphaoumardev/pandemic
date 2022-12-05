package com.alpha.pandemic.mappers;

import com.alpha.pandemic.models.Menu;
import com.alpha.pandemic.models.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User>
{


    User getByUsername(@Param("username") String username);

    List<String> getAuthListByUsername(String username, boolean isAdmin);

    List<Menu> getMenusByUsername(int type, String username);

    int updateUserStatusById(Long id, Integer integer);

    int resetPassword(String encode, Long id);

    String getAvatarByUsername(String username);

    String getUsername(Long userId);

    Long getUserByUsername(String username);
}
