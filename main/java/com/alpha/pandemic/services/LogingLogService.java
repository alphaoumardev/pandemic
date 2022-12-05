package com.alpha.pandemic.services;

import com.alpha.pandemic.beans.vo.LoginReportVo;
import com.alpha.pandemic.mappers.LoginLogMapper;
import com.alpha.pandemic.models.LoginLogs;
import com.alpha.pandemic.structor.form.LoginLogsForm;
import com.alpha.pandemic.structor.tools.AddressUtil;
import com.alpha.pandemic.structor.tools.IpUtil;
import com.alpha.pandemic.utils.exception.BusinessException;
import com.alpha.pandemic.utils.response.FinalResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogingLogService extends ServiceImpl<LoginLogMapper, LoginLogs>
{
    public void saveLoginLog(String name, HttpServletRequest request)
    {
        this.baseMapper.insert(createLoginLogs(name,request));
    }

    public Page<LoginLogs> getLoginLogsList(Integer page, Integer size, LoginLogsForm loginLogsForm)
    {
        return this.baseMapper.selectPage(new Page<>(page, size),executeWrapper(loginLogsForm));
    }

    public FinalResult deleteLogiLogsBatch( String ids)
    {
        if(ids.isEmpty())
        {
            throw new BusinessException("Deleting the bach has failed");
        }
        String[] arr = ids.split(",");
        if(arr.length <= 0)
        {
            throw new BusinessException("The logs cannot be empty");
        }
        List<Long> idValue = Arrays.stream(arr).map(Long::parseLong).collect(Collectors.toList());
        int count = this.baseMapper.deleteBatchIds(idValue);
        if(idValue.size()== count)
        {
            return FinalResult.ok().message("You have successfully deleted the logs");
        }
        else if(count>0 && count<idValue.size())
        {
            throw new BusinessException("Deleting the logs has failed");
        }
        else throw new BusinessException("It's failed to delete the login logs");
    }

    public List<LoginReportVo> loginReportsList(String username)
    {
        return this.baseMapper.loginReport(username);
    }

//    This is a util class

    private LoginLogs createLoginLogs(String name,  HttpServletRequest request)
    {
        UserAgent agent = UserAgent.parseUserAgentString(request.getHeader("User_Agent"));
        Browser browser = agent.getBrowser();
        OperatingSystem os = agent.getOperatingSystem();

        LoginLogs loginLogs = new LoginLogs();

        loginLogs.setUsername(name);
        loginLogs.setUserSystem(os.getName());
        loginLogs.setUserBrowser(browser.getName());
        loginLogs.setLoginTime(LocalDateTime.now());
        loginLogs.setIp(IpUtil.getIpAddr(request));
        loginLogs.setLocation(AddressUtil.getCityInfo(IpUtil.getIpAddr(request)));

        return loginLogs;
    }


    private static LambdaQueryWrapper<LoginLogs> executeWrapper( LoginLogsForm loginLogsForm)
    {
        LambdaQueryWrapper<LoginLogs> wrapper = new LambdaQueryWrapper<>();
        if (null != loginLogsForm.getUsername())
        {
            wrapper.like(LoginLogs::getUsername, loginLogsForm.getUsername());
        }
        if (loginLogsForm.getIp()!=null)
        {
            wrapper.like(LoginLogs::getIp, loginLogsForm.getIp());
        }
        if (loginLogsForm.getLocation()!=null)
        {
            wrapper.eq(LoginLogs::getLocation, loginLogsForm.getLocation());
        }
        wrapper.orderByDesc(LoginLogs::getLoginTime);
        return wrapper;
    }



}
