package com.alpha.pandemic.controllers;

import com.alpha.pandemic.beans.vo.SupplierVo;
import com.alpha.pandemic.models.Supplier;
import com.alpha.pandemic.services.SupplierService;
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
@RequestMapping("/supplier")
@AllArgsConstructor
@Api(tags = "The supplier of the products", value = "The supplier module")
public class SupplierController
{
    private final SupplierService supplierService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('supplier:all')")
    @ApiOperation(value = "Displaying the supplier", notes="Displaying the supplier module")
    @EndPointController(systemMessage = "Displaying the supplier has failed", operation = "Displaying the supplier module")
    public FinalResult getAllSupplier()
    {
        List<SupplierVo> pageInfo = supplierService.getAllSupplier();
        return FinalResult.ok().data(pageInfo);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('supplier:list')")
    @ApiOperation(value = "Displaying the supplier list", notes="Displaying the supplier list module")
    @EndPointController(systemMessage = "Displaying the supplier list has failed", operation = "Displaying the supplier list module")
    public FinalResult getSupplierList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "size", defaultValue = "20")Integer size,
                                       SupplierVo supplierVo)
    {
        return supplierService.getStockList(page,size, supplierVo);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('supplier:add')")
    @ApiOperation(value = "Adding a new supplier", notes="Adding a new supplier module")
    @EndPointController(systemMessage = "Adding a new supplier has failed", operation = "Adding a new supplier module")
    public FinalResult addSupplier(@Validated SupplierVo supplierVo, BindingResult result)
    {
        if(result.hasErrors())
        {
            throw new BindingResultException(result);
        }
        return supplierService.addSupplier(supplierVo);
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasAuthority('supplier:find')")
    @ApiOperation(value = "Finding the supplier details by id", notes="Finding the supplier details by id module")
    @EndPointController(systemMessage = "Finding the supplier details by id has failed", operation = "Finding the supplier details by id module")
    public FinalResult findSupplierById(@PathVariable("id") Long id)
    {
        Supplier supplierbyId = supplierService.getById(id);
        return FinalResult.ok().data(supplierbyId);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('supplier:update')")
    @ApiOperation(value = "Updating the supplier details", notes="Updating the supplier details module")
    @EndPointController(systemMessage = "Updating the supplier details has failed", operation = "Updating the supplier details module")
    public FinalResult updateSupplier(Long id, SupplierVo supplierVo,  BindingResult result)
    {
        if (result.hasErrors())
        {
            throw new BindingResultException(result);
        }
        return supplierService.updateSupplier(id, supplierVo);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('supplier:delete')")
    @ApiOperation(value = "Deleting the supplier details", notes="Deleting the supplier details module")
    @EndPointController(systemMessage = "Deleting the supplier details has failed", operation = "Deleting the supplier details module")
    public FinalResult deleteSupplier(@PathVariable("id") Long id)
    {
        return supplierService.deleteSupplier(id);
    }
}
