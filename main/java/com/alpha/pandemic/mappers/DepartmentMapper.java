package com.alpha.pandemic.mappers;

import com.alpha.pandemic.beans.vo.DepartmentVo;
import com.alpha.pandemic.models.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DepartmentMapper extends BaseMapper<Department>
{
    List<DepartmentVo> getDepartcount();

    Long getDepartIdByName(String name);

    String getDepartmentNameById(Long departmentId);

    List<String> selectDepartmentNamesByIds(@Param("ids") List<Long> deptIds);

    Integer getDepartParent(Long id);
}
