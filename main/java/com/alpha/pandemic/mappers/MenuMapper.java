package com.alpha.pandemic.mappers;

import com.alpha.pandemic.models.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu>
{
    Integer checkHasChild(Long id);

    void updateChildren(List<Long> ids, Integer newStatus);
}
