package com.alpha.pandemic.services;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.alpha.pandemic.beans.vo.DepartmentExportVo;
import com.alpha.pandemic.beans.vo.DepartmentVo;
import com.alpha.pandemic.mappers.DepartmentMapper;
import com.alpha.pandemic.models.Department;
import com.alpha.pandemic.structor.form.DepartmentForm;
import com.alpha.pandemic.structor.tools.ExcelStyleUtil;
import com.alpha.pandemic.structor.tools.ExcelUtil;
import com.alpha.pandemic.utils.exception.BusinessException;
import com.alpha.pandemic.utils.response.ResultCode;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService extends ServiceImpl<DepartmentMapper, Department>
{
    public String getDepartmentNameById(Long departmentId)
    {
        return this.baseMapper.getDepartmentNameById(departmentId);
    }

    public List<String> getDeptNamesByIds(List<Long> deptIds)
    {
        return this.baseMapper.selectDepartmentNamesByIds(deptIds);
    }

    public List<DepartmentVo> findDeparts()
    {
        return this.baseMapper.getDepartcount();
    }

    public Long getDepartByNameId(String name)
    {
        return this.baseMapper.getDepartIdByName(name);
    }
    public Integer getDepartParentById(Long id)
    {
        return this.baseMapper.getDepartParent(id);
    }

    public Page<DepartmentVo> getDepartList(DepartmentForm departmentForm)
    {
        LambdaQueryWrapper<Department> wrapper = executeWrapper(departmentForm);
        Page<Department> page = this.baseMapper.selectPage(new Page<>(departmentForm.getPage(),departmentForm.getSize()),wrapper);
        return departmentPageVo(page);
    }

    public void exportDepartment(HttpServletResponse response, DepartmentForm departmentForm)
    {
        LambdaQueryWrapper<Department> wrapper = executeWrapper(departmentForm);
        List<Department> departs = this.baseMapper.selectList(wrapper);
        List<DepartmentVo> departVo = this.toDepartmentExportVo(departs);
        List<DepartmentExportVo> departExport = new ArrayList<>(departVo.size());
        departVo.forEach(forvo ->
        {
            DepartmentExportVo exportVo = new DepartmentExportVo();
            BeanUtils.copyProperties(forvo, exportVo);
            departExport.add(exportVo);
        });
        try
        {
            ExportParams params = new ExportParams("Department list table", "sheet1", ExcelType.XSSF);
            params.setStyle(ExcelStyleUtil.class);
            ExcelUtil.exportExcel(departExport, DepartmentExportVo.class,"Department list",params,response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new BusinessException(ResultCode.EXPORT_FAIL);
        }
    }

//    This the utils classes


    private Page<DepartmentVo> departmentPageVo(Page<Department> departs)
    {
        Page<DepartmentVo> page = new Page<>();
        BeanUtils.copyProperties(departs, page);
        List<DepartmentVo> list = toDepartmentExportVo(departs.getRecords());
        page.setRecords(list);
        return page;
    }

    private List<DepartmentVo> toDepartmentExportVo( List<Department> departs)
    {
        List<DepartmentVo> departmentvos = new ArrayList<>(departs.size());
        departs.forEach(dep ->
        {
            DepartmentVo depvo = new DepartmentVo();
            Integer count = this.getDepartParentById(dep.getId());
            depvo.setTotal(count);
            BeanUtils.copyProperties(dep, depvo);
            departmentvos.add(depvo);
        });
        return departmentvos;
    }


    private static LambdaQueryWrapper<Department> executeWrapper( DepartmentForm departmentFrom)
    {
        LambdaQueryWrapper<Department> wrapper = new LambdaQueryWrapper<>();
        if (null != departmentFrom.getDepartName())
        {
            wrapper.like(Department::getName, departmentFrom.getDepartName());
        }
        if (null != departmentFrom.getPhone())
        {
            wrapper.like(Department::getPhone, departmentFrom.getPhone());
        }
        return wrapper;
    }

}

