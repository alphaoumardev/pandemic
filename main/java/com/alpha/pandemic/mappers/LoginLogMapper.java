package com.alpha.pandemic.mappers;

import com.alpha.pandemic.beans.vo.LoginReportVo;
import com.alpha.pandemic.models.LoginLogs;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginLogMapper  extends BaseMapper<LoginLogs>
{
    List<LoginReportVo> loginReport(String username);
}
