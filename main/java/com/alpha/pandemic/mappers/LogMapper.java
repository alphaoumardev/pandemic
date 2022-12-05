package com.alpha.pandemic.mappers;

import com.alpha.pandemic.models.Logs;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper extends BaseMapper<Logs>{}

