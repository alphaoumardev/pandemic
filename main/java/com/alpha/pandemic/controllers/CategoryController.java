package com.alpha.pandemic.controllers;

import com.alpha.pandemic.beans.vo.ProductCategoryVo;
import com.alpha.pandemic.models.Category;
import com.alpha.pandemic.services.CategoryService;
import com.alpha.pandemic.structor.annotation.EndPointController;
import com.alpha.pandemic.utils.exception.BindingResultException;
import com.alpha.pandemic.utils.response.FinalResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
@Api(tags = "This is the category module", value = "The category module")
public class CategoryController
{
    private final CategoryService categoryService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('category:list')")
    @ApiOperation(value = "Displaying the category", notes="Displaying the category module")
    @EndPointController(systemMessage = "Displaying the category has failed", operation = "Displaying the category module")
    public FinalResult getCategoryList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "size", defaultValue = "20")Integer size,
                                       ProductCategoryVo categoryVo)
    {
        return categoryService.getCategoryList(page,size, categoryVo);
    }

    @GetMapping("/tree")
    @PreAuthorize("hasAuthority('category:tree')")
    @ApiOperation(value = "Displaying the category stoct list", notes="Displaying the category stoct list module")
    @EndPointController(systemMessage = "Displaying the category stoct list has failed", operation = "Displaying the category stoct list module")
    public FinalResult findTree(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "size", defaultValue = "2000")Integer size)
    {
        return categoryService.getCategoryTree(page,size);
    }

    @GetMapping("/parent")
    @PreAuthorize("hasAuthority('category:parent')")
    @ApiOperation(value = "Displaying the category stoct list", notes="Displaying the category stoct list module")
    @EndPointController(systemMessage = "Displaying the category stoct list has failed", operation = "Displaying the category stoct list module")
    public FinalResult getParentNode()
    {
        List<ProductCategoryVo> parent = categoryService.getParentNode();
        return FinalResult.ok().data(parent);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('category:all')")
    @ApiOperation(value = "Displaying the category stoct list", notes="Displaying the category stoct list module")
    @EndPointController(systemMessage = "Displaying the category stoct list has failed", operation = "Displaying the category stoct list module")
    public FinalResult getAll()
    {
        List<Category> all = categoryService.list();
        return FinalResult.ok().data(all);
    }//This also has to be reviwed

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('category:add')")
    @ApiOperation(value = "Adding a new category", notes="Adding a new category module")
    @EndPointController(systemMessage = "Adding a new category has failed", operation = "Adding a new category module")
    public FinalResult addCategory(@Validated ProductCategoryVo categoryVo,  BindingResult result)
    {
        if (result.hasErrors())
        {
            throw new BindingResultException(result);
        }
        return categoryService.addCategory(categoryVo);
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("hasAuthority('category:details')")
    @ApiOperation(value = "Displaying the category details", notes="Displaying the category details module")
    @EndPointController(systemMessage = "Displaying the category details has failed", operation = "Displaying the category details module")
    public FinalResult getCategoryById(@PathVariable("id") Long id)
    {
        Category details = categoryService.getById(id);
        ProductCategoryVo categoryVo = new ProductCategoryVo();
        BeanUtils.copyProperties(details,categoryVo);
        return FinalResult.ok().data(categoryVo);
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasAuthority('category:add')")
    @ApiOperation(value = "Adding a new category", notes="Adding a new category module")
    @EndPointController(systemMessage = "Adding a new category has failed", operation = "Adding a new category module")
    public FinalResult updateCategory(@PathVariable("id")Long id, @Validated ProductCategoryVo categoryVo,  BindingResult result)
    {
        if (result.hasErrors())
        {
            throw new BindingResultException(result);
        }
        return categoryService.updateCategory(id,categoryVo);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('category:delete')")
    @ApiOperation(value = "Deleting the category details", notes="Deleting the category details module")
    @EndPointController(systemMessage = "Deleting the category details has failed", operation = "Deleting the category details module")
    public FinalResult deleteCategory(@PathVariable("id") Long id)
    {
        return categoryService.deleteCategory(id);
    }
}
