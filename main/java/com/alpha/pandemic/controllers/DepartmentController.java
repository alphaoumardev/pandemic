package com.alpha.pandemic.controllers;

import com.alpha.pandemic.beans.vo.DepartmentVo;
import com.alpha.pandemic.models.Department;
import com.alpha.pandemic.services.DepartmentService;
import com.alpha.pandemic.structor.annotation.EndPointController;
import com.alpha.pandemic.structor.form.DepartmentForm;
import com.alpha.pandemic.utils.exception.BindingResultException;
import com.alpha.pandemic.utils.exception.BusinessException;
import com.alpha.pandemic.utils.response.FinalResult;
import com.alpha.pandemic.utils.response.ResultCode;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/department")
public class DepartmentController
{
    private final DepartmentService departService;

//    @ApiOperation("finding the department")
//    @GetMapping("/find")
//    public FinalResult findDepart()
//    {
//        List<Department> depart =departService.findAndCountDepart();
//        if(depart.size()==0)
//        {
//            throw new BusinessException(ResultCode.DEPARTMENT_NOT_EXIST.getCode(),ResultCode.DEPARTMENT_NOT_EXIST.getMessage());
//        }
//        return FinalResult.ok().datamap("depart", depart);
//    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('departement:list')")
    @ApiOperation(value = "Display the departments list ", notes = "Getting departments")
    @EndPointController(systemMessage = "Display the departments list has failed", operation = "Deleting the batch logs")
    public FinalResult getDepartsList()
    {
        List<DepartmentVo> departVos = departService.findDeparts();
        if(!departVos.isEmpty())
        {
            return FinalResult.ok().data(departVos);
        }
        else throw new BusinessException(ResultCode.DEPARTMENT_NOT_EXIST);
    }

    @GetMapping("/{name}")
    @PreAuthorize("hasAuthority('departement:query')")
    @ApiOperation(value = "Display the departments list by name ", notes = "Getting departments by name")
    @EndPointController(systemMessage = "Display the departments by name list has failed", operation = "Deleting the batch logs")
    public FinalResult getDepartsByName(@PathVariable("name") String name)
    {
        Long departId = departService.getDepartByNameId(name);
        if(departId !=null)
        {
            return FinalResult.ok().data(departId);
        }
        else throw new BusinessException(ResultCode.DEPARTMENT_NOT_EXIST);
    }

    @PostMapping("/departlist")
    @PreAuthorize("hasAuthority('departement:querylist')")
    @ApiOperation(value = "Display the departments list by name ", notes = "Getting departments by name")
    @EndPointController(systemMessage = "Display the departments by name list has failed", operation = "Deleting the batch logs")
    public FinalResult getList(@Validated DepartmentForm departmentForm,  BindingResult result)
    {
        if(result.hasErrors())
        {
            throw new BindingResultException(result);
        }
        Page<DepartmentVo> page = departService.getDepartList(departmentForm);
        return FinalResult.ok().data(page);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('departement:add')")
    @ApiOperation(value = "To add a new department ", notes = "To add a new department")
    @EndPointController(systemMessage = "Adding a new department has failed", operation = "To add a new department")
    public FinalResult addDepart(@Validated DepartmentVo departmentVo,  BindingResult result)
    {
        if(result.hasErrors())
        {
            throw new BindingResultException(result);
        }
        Department department = new Department();
        BeanUtils.copyProperties(departmentVo, department);
        boolean add = departService.save(department);
        if(add)
        {
            return FinalResult.ok().message("You have successfully add a new department");
        }
        else throw new BusinessException(ResultCode.ADD_DEPT.getCode(), "Adding department has failed");
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('departement:update')")
    @ApiOperation(value = "To update the department ", notes = "To update department")
    @EndPointController(systemMessage = "Updating department has failed", operation = "To delete department")
    public FinalResult updateDepart(@Validated DepartmentVo departmentVo,  BindingResult result)
    {
        if(result.hasErrors())
        {
            throw new BindingResultException(result);
        }
        Department department = new Department();
        BeanUtils.copyProperties(departmentVo, department);
        boolean add = departService.updateById(department);
        if(add)
        {
            return FinalResult.ok().message("You have successfully updated department");
        }
        else throw new BusinessException(ResultCode.ADD_DEPT.getCode(), "Update department has failed");
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('departement:delete')")
    @ApiOperation(value = "To delete the department ", notes = "To delete the department")
    @EndPointController(systemMessage = "Deleting department has failed", operation = "To delete department")
    public FinalResult deleteDepart(@PathVariable("id") Long id)
    {
        Integer count = departService.getDepartParentById(id);
        if(count>0)
        {
            throw new BusinessException(ResultCode.DEL_DEPT_NOT_EMPTY);
        }
        boolean removed = departService.removeById(id);
        if(removed)
        {
            return FinalResult.ok().message("You have successfully deleted the department");
        }
        else throw new BusinessException(ResultCode.FAILED.getCode(), "Deleting department has failed");
    }

    @PostMapping("/export")
    @PreAuthorize("hasAuthority('departement:export')")
    @ApiOperation(value = "To export the department ", notes = "To export department")
    @EndPointController(systemMessage = "Updating department has failed", operation = "To delete department")
    public void exportDepartment(HttpServletResponse response, @Validated DepartmentForm departmentForm,  BindingResult result)
    {
        if(result.hasErrors())
        {
            throw new BindingResultException(result);
        }
       departService.exportDepartment(response, departmentForm);
    }
}
