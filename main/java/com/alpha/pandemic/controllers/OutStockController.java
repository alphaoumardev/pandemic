package com.alpha.pandemic.controllers;

import com.alpha.pandemic.beans.vo.OutStockDetailVo;
import com.alpha.pandemic.beans.vo.OutStockVo;
import com.alpha.pandemic.services.OutStockService;
import com.alpha.pandemic.structor.annotation.EndPointController;
import com.alpha.pandemic.utils.exception.BindingResultException;
import com.alpha.pandemic.utils.response.FinalResult;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/outstock")
@AllArgsConstructor
public class OutStockController
{
    private final OutStockService outStockService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('outstock:add')")
    @ApiOperation(value = "Adding a new outstock", notes="Adding a new outstock module")
    @EndPointController(systemMessage = "Adding a new outstock has failed", operation = "Adding a new outstock module")
    public FinalResult addOutStock(@Validated OutStockVo outstockVo,  BindingResult result)
    {
        if (result.hasErrors())
        {
            throw new BindingResultException(result);
        }
        return outStockService.addOutStock(outstockVo);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('outstock:list')")
    @ApiOperation(value = "Displaying the outstock", notes="Displaying the outstock module")
    @EndPointController(systemMessage = "Displaying the outstock has failed", operation = "Displaying the outstock module")
    public FinalResult getOutStockList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "size", defaultValue = "20")Integer size,
                                    OutStockVo outStockVo)
    {
        return outStockService.getOutStockList(page, size, outStockVo);
    }

    @PutMapping("/remove/{id}")
    @PreAuthorize("hasAuthority('outstock:remove')")
    @ApiOperation(value = "Removing the outstock details", notes="Removing the outstock details module")
    @EndPointController(systemMessage = "Removing the outstock details has failed", operation = "Removing the outstock details module")
    public FinalResult removeStockDetails(@PathVariable("id") Long id)
    {
        return  outStockService.removeOutStock(id);
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("hasAuthority('outstock:details')")
    @ApiOperation(value = "Displaying the outstock details", notes="Displaying the outstock details module")
    @EndPointController(systemMessage = "Displaying the outstock details has failed", operation = "Displaying the outstock details module")
    public FinalResult getOutStockDetails(@PathVariable("id") Long id,
                                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "size", defaultValue = "20")Integer size )
    {
        OutStockDetailVo details = outStockService.getOutStockDetails(id,page,size);
        return FinalResult.ok().data(details);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('outstock:delete')")
    @ApiOperation(value = "Deleting the outstock details", notes="Deleting the outstock details module")
    @EndPointController(systemMessage = "Deleting the outstock details has failed", operation = "Deleting the outstock details module")
    public FinalResult deleteOutStock(@PathVariable("id") Long id)
    {
        return outStockService.deleteOutStock(id);
    }

    @PutMapping("/push/{id}")
    @PreAuthorize("hasAuthority('outstock:push')")
    @ApiOperation(value = "Pushing up the outstock details", notes="Pushing up the outstock details module")
    @EndPointController(systemMessage = "Pushing up the outstock details has failed", operation = "Pushing up the outstock details module")
    public FinalResult pushUpOutStock(@PathVariable("id") Long id)
    {
        return outStockService.pushUpOutStock(id);
    }

    @PutMapping("/backup/{id}")
    @PreAuthorize("hasAuthority('outstock:backup')")
    @ApiOperation(value = "Backing up the outstock details", notes="Backing up the outstock details module")
    @EndPointController(systemMessage = "Backing up the outstock details has failed", operation = "Backing up the outstock details module")
    public FinalResult backUpOutStock(@PathVariable("id") Long id)
    {
        return outStockService.backUpOutStock(id);
    }
}
