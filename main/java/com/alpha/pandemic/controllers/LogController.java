package com.alpha.pandemic.controllers;

import com.alpha.pandemic.services.LogService;
import com.alpha.pandemic.structor.annotation.EndPointController;
import com.alpha.pandemic.structor.form.LogForm;
import com.alpha.pandemic.utils.response.FinalResult;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/log")
public class LogController
{
    private final LogService logService;
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('log:list')")
    @ApiOperation(value = "The logs list", notes = "To logs list")
    public FinalResult getLogListPage(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                      @RequestParam(value = "size",defaultValue = "9") Integer size,
                                      LogForm logForm)
    {
        return logService.getLogs(page, size, logForm);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('log:delete')")
    @ApiOperation(value = "The delete logs ", notes = "To delete the logs")
    @EndPointController(systemMessage = "deleting the logs has failed", operation = "Deleting the logs")
    public FinalResult deleteLogs(@PathVariable("id") Long id)
    {
        return logService.deleteLogs(id);
    }

    @DeleteMapping("/batch")
    @PreAuthorize("hasAuthority('log:batchdelete')")
    @ApiOperation(value = "The delete batch logs ", notes = "To delete the batch logs")
    @EndPointController(systemMessage = "deleting the batch logs has failed", operation = "Deleting the batch logs")
    public FinalResult deleteLogsBatch(Long[] ids)
    {
        return  logService.deleteLogsBatch(ids);
    }
}
