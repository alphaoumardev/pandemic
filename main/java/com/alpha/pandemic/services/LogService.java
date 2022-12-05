package com.alpha.pandemic.services;

import com.alpha.pandemic.mappers.LogMapper;
import com.alpha.pandemic.models.Logs;
import com.alpha.pandemic.structor.form.LogForm;
import com.alpha.pandemic.utils.exception.BusinessException;
import com.alpha.pandemic.utils.response.FinalResult;
import com.alpha.pandemic.utils.response.ResultCode;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

@Service
public class LogService extends ServiceImpl<LogMapper, Logs>
{
    public void saveLog(Logs sysLog)
    {
        this.baseMapper.insert(sysLog);
    }

    public FinalResult getLogs(Integer page, Integer size, LogForm logForm)
    {
        Page<Logs> logsPage = this.baseMapper.selectPage(new Page<>(page,size),executeWrapper(logForm));
        return FinalResult.ok().data(logsPage);
    }

    public FinalResult deleteLogs(Long id)
    {
        Logs log = this.baseMapper.selectById(id);
        if(Objects.isNull(log))
        {
            return FinalResult.error(ResultCode.FAILED);
        }
        int deleteRow = this.baseMapper.deleteById(id);
        if(deleteRow>0)
        {
            return FinalResult.ok();
        }
        else throw new BusinessException("Deleting the system logs has failed");
    }

    public FinalResult deleteLogsBatch( Long[] ids)
    {
        if(ids.length == 0)
        {
            return FinalResult.error(ResultCode.ERROR);
        }
        int res = this.baseMapper.deleteBatchIds(Arrays.asList(ids));
        if(res >0 && res==ids.length)
        {
            return FinalResult.ok();
        }
        else if(res >0 && res<ids.length)
        {
            return FinalResult.error().message("You have deleted the log");
        }
        else return  FinalResult.error().message("Deleting the system logs has failed");

    }

    // This is util class

    private static LambdaQueryWrapper<Logs> executeWrapper( LogForm logForm)
    {
        LambdaQueryWrapper<Logs> wrapper = new LambdaQueryWrapper<>();
        if (null != logForm.getUsername())
        {
            wrapper.like(Logs::getUsername, logForm.getUsername());
        }
        if (logForm.getIp()!=null)
        {
            wrapper.eq(Logs::getIp, logForm.getIp());
        }
        if (logForm.getLocation()!=null)
        {
            wrapper.eq(Logs::getLocation, logForm.getLocation());
        }
        wrapper.orderByDesc(Logs::getCreateTime);
        return wrapper;
    }
}
