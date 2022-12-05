package com.alpha.pandemic.controllers;

import com.alpha.pandemic.beans.vo.ConsumerVo;
import com.alpha.pandemic.models.Consumer;
import com.alpha.pandemic.services.ConsumerService;
import com.alpha.pandemic.structor.annotation.EndPointController;
import com.alpha.pandemic.utils.exception.BindingResultException;
import com.alpha.pandemic.utils.response.FinalResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/consumer")
@Api(value = "The consumer api", tags = "The consumer api")
public class ConsumerController
{
    private final ConsumerService consumerService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('consumer:list')")
    @ApiOperation(value = "Display the consumer list by name ", notes = "Getting consumer by name")
    @EndPointController(systemMessage = "Display the consumer by name list has failed", operation = "Display the consumer list")
    public FinalResult getConsumerList(Integer page, Integer size, ConsumerVo consumerVo)
    {
        return consumerService.findConsumer(page, size, consumerVo);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('consumer:add')")
    @ApiOperation(value = "To add a new consumer", notes = "Adding a new consumer")
    @EndPointController(systemMessage = "Adding a new consumer has failed", operation = "Adding the new consumer")
    public FinalResult addConsumer(@Validated ConsumerVo consumerVo,  BindingResult result)
    {
        if(result.hasErrors())
        {
            throw new BindingResultException(result);
        }
        return consumerService.addNewConsumer(consumerVo);
    }

    @GetMapping("/getconsumer/{id}")
    @PreAuthorize("hasAuthority('consumer:getbyid')")
    @ApiOperation(value = "Get the consumer by id", notes = "Getting the consumer by id")
    @EndPointController(systemMessage = "Getting the consumer by id has failed", operation = "Getting the consumer by id")
    public FinalResult getConsumerById(@PathVariable("id")Long id)
    {
        Consumer consumer = consumerService.getById(id);
        return FinalResult.ok().data(consumer);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('consumer:update')")
    @ApiOperation(value = "Get the consumer by id", notes = "Getting the consumer by id")
    @EndPointController(systemMessage = "Getting the consumer by id has failed", operation = "Getting the consumer by id")
    public FinalResult updateConsumerById(@PathVariable("id")Long id, @Validated ConsumerVo consumerVo,  BindingResult result)
    {
        if(result.hasErrors())
        {
            throw new BindingResultException(result);
        }
        return consumerService.updateConsumer(id, consumerVo);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('consumer:delete')")
    @ApiOperation(value = "To Delete the consumer by id", notes = "Deleting the consumer by id")
    @EndPointController(systemMessage = "Deleting the consumer by id has failed", operation = "Deleting the consumer by id")
    public FinalResult deleteConsumerById(@PathVariable("id")Long id)
    {
        return consumerService.deleteConsumer(id);
    }

    @GetMapping("/findall")
    @PreAuthorize("hasAuthority('consumer:query')")
    @ApiOperation(value = "To display all the consumers", notes = "Displying all consumers")
    @EndPointController(systemMessage = "Displying all consumers has failed", operation = "Displying all consumers")
    public FinalResult findAllConsumers()
    {
        List<ConsumerVo> list = consumerService.findAllConsumers();
        return FinalResult.ok().data(list);
    }
}
