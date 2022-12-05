package com.alpha.pandemic.controllers;

import com.alpha.pandemic.structor.annotation.EndPointController;
import com.alpha.pandemic.utils.response.FinalResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/common")
@AllArgsConstructor
@Api(value = "The common api", tags = "The common api")
public class CommonController
{
    private final Environment environment;

    @GetMapping("/druid")
    @PreAuthorize("hasAuthority('common:druid')")
    @ApiOperation(value = "Getting the druid login module ", notes = "Getting the druid login module")
    @EndPointController(systemMessage = "Getting the druid login module has failed", operation = "Getting the druid login module")
    public FinalResult getDruiLoginConfig()
    {
        Map<String, String> config = new HashMap<>(2);
        config.put("username", environment.getProperty("druid.username"));
        config.put("password", environment.getProperty("druid.password"));
        return FinalResult.ok().data(config);
    }

    @GetMapping("/sysinfo")
    @PreAuthorize("hasAuthority('common:sysinfo')")
    @ApiOperation(value = "Getting the druid system info ", notes = "Getting the druid system info")
    @EndPointController(systemMessage = "Getting the druid system info has failed", operation = "Getting the druid system info")
    public FinalResult getDruidSysInfo()
    {
        Map<String, String> config = new HashMap<>(4);
        config.put("author", environment.getProperty("system.author"));
        config.put("phone", environment.getProperty("system.phone"));
        config.put("qq", environment.getProperty("system.qq"));
        config.put("wechat", environment.getProperty("system.wechat"));
        return FinalResult.ok().data(config);
    }
}
