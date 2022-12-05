package com.alpha.pandemic.controllers;

import com.alpha.pandemic.beans.vo.LoginReportVo;
import com.alpha.pandemic.models.LoginLogs;
import com.alpha.pandemic.services.LogingLogService;
import com.alpha.pandemic.structor.annotation.EndPointController;
import com.alpha.pandemic.structor.form.LoginLogsForm;
import com.alpha.pandemic.utils.exception.BusinessException;
import com.alpha.pandemic.utils.response.FinalResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/loginlog")
@Api(value = "The login logs" , tags="The login log api")
public class LoginLogController
{
    private final LogingLogService logingLogService;

    @PostMapping("/list")
    @PreAuthorize("hasAuthority('loginlog:list')")
    @ApiOperation(value = "The login logs list", notes = "To login logs list")
    public FinalResult getLoginLogsList(@Validated LoginLogsForm loginLogsForm)
    {
        Page<LoginLogs> loginLogsPage = logingLogService.getLoginLogsList(loginLogsForm.getPage(),loginLogsForm.getSize(),loginLogsForm);
        return FinalResult.ok().data(loginLogsPage);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('loginlog:delete')")
    @ApiOperation(value = "The delete logs ", notes = "To delete the login logs")
    @EndPointController(systemMessage = "deleting the logs has failed", operation = "Deleting the logs")
    public FinalResult deleteLoginLogs(@PathVariable("id") Long id)
    {
        boolean delete = logingLogService.removeById(id);
        if(delete)
        {
            return FinalResult.ok().message("You have successfully the logs");
        }
        else throw new BusinessException("Deleting the logs has failed");
    }

    @DeleteMapping("/batch/{ids}")
    @PreAuthorize("hasAuthority('loginlog:batchdelete')")
    @ApiOperation(value = "The delete batch logs ", notes = "To delete the batch login logs")
    @EndPointController(systemMessage = "deleting the batch logs has failed", operation = "Deleting the batch logs")
    public FinalResult deleteLoginLogsBatch(@PathVariable("ids") String ids)
    {
       return  logingLogService.deleteLogiLogsBatch(ids);
    }

    @GetMapping("/loginreport/{username}")
    @PreAuthorize("hasAuthority('loginlog:batchdelete')")
    @ApiOperation(value = "The delete batch logs ", notes = "To delete the batch login logs")
    @EndPointController(systemMessage = "deleting the batch logs has failed", operation = "Deleting the batch logs")
    public FinalResult loginReport(@PathVariable("username") String username)
    {
        List<LoginReportVo> loginReport = logingLogService.loginReportsList(username);
        List<LoginReportVo> loginReportAll = logingLogService.loginReportsList(null);
        Map<String , Object> maps = new HashMap<>(2);
        maps.put("mine",loginReport);
        maps.put("all", loginReportAll);
        return FinalResult.ok().data(maps);
    }
}
