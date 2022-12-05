package com.alpha.pandemic.controllers;

import com.alpha.pandemic.beans.vo.ProductVo;
import com.alpha.pandemic.models.Product;
import com.alpha.pandemic.services.ProductService;
import com.alpha.pandemic.structor.annotation.EndPointController;
import com.alpha.pandemic.utils.exception.BindingResultException;
import com.alpha.pandemic.utils.response.FinalResult;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController
{
    private final ProductService productService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('product:list')")
    @ApiOperation(value = "Displaying the product", notes="Displaying the product module")
    @EndPointController(systemMessage = "Displaying the product has failed", operation = "Displaying the product module")
    public FinalResult getProductList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "size", defaultValue = "20")Integer size,
                                    @RequestParam(value = "category", defaultValue = "20")String category,
                                    @RequestParam(value = "filter", defaultValue = "false")Boolean filter,
                                    ProductVo productVo)
    {
        return productService.getProductList(page,size, productVo,category, filter);
    }

    @GetMapping("/findlist")
    @PreAuthorize("hasAuthority('product:findlist')")
    @ApiOperation(value = "Displaying the product stoct list", notes="Displaying the product stoct list module")
    @EndPointController(systemMessage = "Displaying the product stoct list has failed", operation = "Displaying the product stoct list module")
    public FinalResult findProducts(  @RequestParam(value = "page", defaultValue = "1") Integer page,
                                      @RequestParam(value = "size", defaultValue = "20")Integer size,
                                      @RequestParam(value = "category", required = false)String category,
                                      ProductVo productVo)
    {
        return productService.getProductStockList(page,size, productVo,category);
    }

    @GetMapping("/findAll")
    @PreAuthorize("hasAuthority('product:findlist')")
    @ApiOperation(value = "Displaying the product stoct list", notes="Displaying the product stoct list module")
    @EndPointController(systemMessage = "Displaying the product stoct list has failed", operation = "Displaying the product stoct list module")
    public FinalResult findAllProducts(String category, ProductVo productVo)
    {
        return productService.getAllProducts(productVo,category);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('product:add')")
    @ApiOperation(value = "Adding a new product", notes="Adding a new product module")
    @EndPointController(systemMessage = "Adding a new product has failed", operation = "Adding a new product module")
    public FinalResult addStock(@Validated ProductVo productVo,  BindingResult result)
    {
        if (result.hasErrors())
        {
            throw new BindingResultException(result);
        }
        return productService.addProduct(productVo);
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("hasAuthority('product:details')")
    @ApiOperation(value = "Displaying the product details", notes="Displaying the product details module")
    @EndPointController(systemMessage = "Displaying the product details has failed", operation = "Displaying the product details module")
    public FinalResult getStockDetails(@PathVariable("id") Long id)
    {
        Product details = productService.getById(id);
        ProductVo productVo = new ProductVo();
        BeanUtils.copyProperties(details,productVo);
        return FinalResult.ok().data(productVo);
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasAuthority('product:add')")
    @ApiOperation(value = "Adding a new product", notes="Adding a new product module")
    @EndPointController(systemMessage = "Adding a new product has failed", operation = "Adding a new product module")
    public FinalResult updateProduct(@PathVariable("id")Long id, @Validated ProductVo productVo,  BindingResult result)
    {
        if (result.hasErrors())
        {
            throw new BindingResultException(result);
        }
        return productService.updateProduct(productVo);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('product:delete')")
    @ApiOperation(value = "Deleting the product details", notes="Deleting the product details module")
    @EndPointController(systemMessage = "Deleting the product details has failed", operation = "Deleting the product details module")
    public FinalResult deleteStock(@PathVariable("id") Long id)
    {
        return productService.deleteProduct(id);
    }

    @PutMapping("/remove/{id}")
    @PreAuthorize("hasAuthority('product:remove')")
    @ApiOperation(value = "Removing the product details", notes="Removing the product details module")
    @EndPointController(systemMessage = "Removing the product details has failed", operation = "Removing the product details module")
    public FinalResult removeProduct(@PathVariable("id") Long id)
    {
        return productService.removeProduct(id);
    }

    @PutMapping("/push/{id}")
    @PreAuthorize("hasAuthority('product:push')")
    @ApiOperation(value = "Pushing up the product details", notes="Pushing up the product details module")
    @EndPointController(systemMessage = "Pushing up the product details has failed", operation = "Pushing up the product details module")
    public FinalResult pushUpStock(@PathVariable("id") Long id)
    {
        return productService.pushUpProduct(id);
    }

    @PutMapping("/backup/{id}")
    @PreAuthorize("hasAuthority('product:backup')")
    @ApiOperation(value = "Backing up the product details", notes="Backing up the product details module")
    @EndPointController(systemMessage = "Backing up the product details has failed", operation = "Backing up the product details module")
    public FinalResult backUpStock(@PathVariable("id") Long id)
    {
        return productService.backUpProduct(id);
    }
}
