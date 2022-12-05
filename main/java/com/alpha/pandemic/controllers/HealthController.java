package com.alpha.pandemic.controllers;

import cn.hutool.json.JSONUtil;
import com.alpha.pandemic.beans.vo.HealthVo;
import com.alpha.pandemic.models.Health;
import com.alpha.pandemic.services.HealthService;
import com.alpha.pandemic.structor.annotation.EndPointController;
import com.alpha.pandemic.structor.form.DailyCheckForm;
import com.alpha.pandemic.structor.security.LoginUser;
import com.alpha.pandemic.structor.tools.HttpUtil;
import com.alpha.pandemic.structor.tools.UserUtil;
import com.alpha.pandemic.utils.exception.BusinessException;
import com.alpha.pandemic.utils.response.FinalResult;
import com.alpha.pandemic.utils.response.ResultCode;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/health")
@Api(value = "The pandemic", tags = "The covid pandemic")
public class HealthController
{
    @Value("$covid.DataUrl")
    private String covidDataUrl;//Note that this var. can't be local

    private final HealthService healthService;
    @Autowired
    public HealthController(HealthService healthService) {this.healthService = healthService;}

    @GetMapping("/covidata")
    @PreAuthorize("hasAuthority('health:data')")
    @ApiOperation(value = "Display the pendemic data ", notes = "Display the pendemic data")
    @EndPointController(systemMessage = "Display the pendemic data has failed", operation = "Display the pendemic data")
    public FinalResult getCovidData()
    {
        try
        {
            String data = HttpUtil.httpGet(covidDataUrl);
            return FinalResult.ok().data(JSONUtil.parseObj(data));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new BusinessException(ResultCode.API_GET_ERROR);
        }
    }

    @GetMapping("/report")
    @PreAuthorize("hasAuthority('health:report')")
    @ApiOperation(value = "Display the pendemic report ", notes = "Display the pendemic report")
    @EndPointController(systemMessage = "Display the pendemic report has failed", operation = "Display the pendemic report")
    public FinalResult covidReport()
    {
        LoginUser loginUser = UserUtil.getCurrentLoginUser();
        assert loginUser != null;
        Health health = healthService.report(loginUser.getId());
        return FinalResult.ok().data(health);
    }

    @GetMapping("/covidreport")
    @PreAuthorize("hasAuthority('health:covidreport')")
    @ApiOperation(value = "Display the pendemic report ", notes = "Display the pendemic report")
    @EndPointController(systemMessage = "Display the pendemic(covid)2 report has failed", operation = "Display the pendemic report")
    public FinalResult healthReport( @Validated HealthVo healthVo)
    {
        LoginUser loginUser = UserUtil.getCurrentLoginUser();
        assert loginUser != null;
        healthVo.setUserId(loginUser.getId());
        int res = healthService.healthReport(healthVo);
        if(res>0)
        {
            return FinalResult.ok();
        }
        else throw new BusinessException(ResultCode.REPORT_ERROR);
    }

    @GetMapping("/history")
    @PreAuthorize("hasAuthority('health:history')")
    @ApiOperation(value = "Display the pendemic report history ", notes = "Display the pendemic report history")
    @EndPointController(systemMessage = "Display the pendemic report history has failed", operation = "Display the pendemic report history")
    public FinalResult covidHistory(@RequestParam(value = "page", defaultValue = "1")Integer page,
                                    @RequestParam(value = "size", defaultValue = "10")Integer size)
    {
        LoginUser loginUser = UserUtil.getCurrentLoginUser();
        assert loginUser != null;
        Page<Health> list = healthService.getHistoryByList(loginUser.getId(),page, size);
        return FinalResult.ok().data(list);
    }

    @GetMapping("/dailycheck")
    @PreAuthorize("hasAuthority('health:dailycheck')")
    @ApiOperation(value = "Display the pendemic report daily ckeck ", notes = "Display the pendemic report daily check")
    @EndPointController(systemMessage = "Display the pendemic report daily check has failed", operation = "Display the pendemic report daily check")
    public FinalResult getCheckPoint(@RequestParam(value = "page", defaultValue = "1")Integer page,
                                     @RequestParam(value = "size", defaultValue = "20")Integer size,
                                     DailyCheckForm dailyCheck)
    {
        Page<HealthVo> list = healthService.getDailyPoint(page, size, dailyCheck);
        return FinalResult.ok().data(list);
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('health:delete')")
    @ApiOperation(value = "Delete the pendemic report ", notes = "Delete the pendemic report")
    @EndPointController(systemMessage = "Delete the pendemic(covid)2 report has failed", operation = "Delete the pendemic report")
    public FinalResult deleteHealthport(@PathVariable("id") Long id)
    {
        return healthService.deleteHealthCheck(id);
    }

    @GetMapping("/export")
    @PreAuthorize("hasAuthority('health:export')")
    @ApiOperation(value = "Export the pendemic report ", notes = "Export the pendemic report")
    @EndPointController(systemMessage = "Export the pendemic(covid)2 report has failed", operation = "Export the pendemic report")
    public void exportHealthport( HttpServletResponse response,DailyCheckForm daily)
    {
        healthService.exportHealthCheck(response, daily);
    }
}
