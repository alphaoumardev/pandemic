package com.alpha.pandemic.controllers;

import com.alpha.pandemic.beans.vo.StockDetailVo;
import com.alpha.pandemic.beans.vo.StockVo;
import com.alpha.pandemic.services.StockService;
import com.alpha.pandemic.structor.annotation.EndPointController;
import com.alpha.pandemic.structor.form.StockForm;
import com.alpha.pandemic.utils.response.FinalResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@AllArgsConstructor
@RequestMapping("/stock")
@Api(tags = "The stock of the products", value = "The stock module")
public class StockController
{
    private final StockService stockService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('stock:list')")
    @ApiOperation(value = "Displaying the stock", notes="Displaying the stock module")
    @EndPointController(systemMessage = "Displaying the stock has failed", operation = "Displaying the stock module")
    public FinalResult getStockList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "size", defaultValue = "20")Integer size,
                                    StockForm stockForm)
    {
        Page<StockVo> pageInfo = stockService.getStockList(page,size, stockForm);
        return FinalResult.ok().data(pageInfo);
    }

    @GetMapping("/export")
    @PreAuthorize("hasAuthority('stock:export')")
    @ApiOperation(value = "Displaying the export stock", notes="Displaying the export stock module")
    @EndPointController(systemMessage = "Displaying the export stock has failed", operation = "Displaying the export stock module")
    public void exportStockList(HttpServletResponse response, StockForm stockForm)
    {
       stockService.exportStockList(response, stockForm);
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("hasAuthority('stock:details')")
    @ApiOperation(value = "Displaying the stock details", notes="Displaying the stock details module")
    @EndPointController(systemMessage = "Displaying the stock details has failed", operation = "Displaying the stock details module")
    public FinalResult getStockDetails(@PathVariable("id") Long id,
                                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "size", defaultValue = "20")Integer size )
    {
        StockDetailVo details = stockService.getStockDetails(id,page,size);
        return FinalResult.ok().data(details);
    }

    @PutMapping("/remove/{id}")
    @PreAuthorize("hasAuthority('stock:remove')")
    @ApiOperation(value = "Removing the stock details", notes="Removing the stock details module")
    @EndPointController(systemMessage = "Removing the stock details has failed", operation = "Removing the stock details module")
    public FinalResult removeStockDetails(@PathVariable("id") Long id)
    {
        return stockService.removeStock(id);
    }

    @PutMapping("/backup/{id}")
    @PreAuthorize("hasAuthority('stock:backup')")
    @ApiOperation(value = "Backing up the stock details", notes="Backing up the stock details module")
    @EndPointController(systemMessage = "Backing up the stock details has failed", operation = "Backing up the stock details module")
    public FinalResult backUpStock(@PathVariable("id") Long id)
    {
        return stockService.backUpStock(id);
    }

    @PutMapping("/push/{id}")
    @PreAuthorize("hasAuthority('stock:push')")
    @ApiOperation(value = "Pushing up the stock details", notes="Pushing up the stock details module")
    @EndPointController(systemMessage = "Pushing up the stock details has failed", operation = "Pushing up the stock details module")
    public FinalResult pushUpStock(@PathVariable("id") Long id)
    {
        return stockService.pushUpStock(id);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('stock:delete')")
    @ApiOperation(value = "Deleting the stock details", notes="Deleting the stock details module")
    @EndPointController(systemMessage = "Deleting the stock details has failed", operation = "Deleting the stock details module")
    public FinalResult deleteStock(@PathVariable("id") Long id)
    {
        return stockService.deleteStock(id);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('stock:add')")
    @ApiOperation(value = "Adding a new stock", notes="Adding a new stock module")
    @EndPointController(systemMessage = "Adding a new stock has failed", operation = "Adding a new stock module")
    public FinalResult addStock(@Validated StockVo stockVo)
    {
        return stockService.addStock(stockVo);
    }

}
