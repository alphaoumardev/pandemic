package com.alpha.pandemic.services;

import com.alpha.pandemic.beans.vo.SupplierVo;
import com.alpha.pandemic.mappers.SupplierMapper;
import com.alpha.pandemic.models.Supplier;
import com.alpha.pandemic.utils.exception.BusinessException;
import com.alpha.pandemic.utils.response.FinalResult;
import com.alpha.pandemic.utils.response.ResultCode;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SupplierService extends ServiceImpl<SupplierMapper, Supplier>
{
    public List<SupplierVo> getAllSupplier()
    {
        List<Supplier> suppliers = this.baseMapper.selectList(null);
        return supplierVoToList(suppliers);
    }

    public FinalResult getStockList(Integer page, Integer size, SupplierVo supplierVo)
    {
        Page<Supplier> supplierPage = this.baseMapper.selectPage(new Page<>(page, size),executeWrapper(supplierVo));
        return FinalResult.ok().data(supplierPage);
    }

    public FinalResult addSupplier(SupplierVo supplierVo)
    {
        Supplier supplier = new Supplier();
        BeanUtils.copyProperties(supplierVo, supplier);
        int res = this.baseMapper.insert(supplier);
        if(res >0)
        {
            return FinalResult.ok().message("You have successfully added a new supplier");
        }
        else throw new BusinessException(ResultCode.FAILED);
    }

    public FinalResult updateSupplier(Long id, SupplierVo supplierVo)
    {
        Supplier supplier = this.baseMapper.selectById(id);
        if(Objects.isNull(supplier))
        {
            throw new BusinessException(ResultCode.PARAM_ERROR, "This supplier does not exist");
        }
        BeanUtils.copyProperties(supplierVo, supplier);
        int res = this.baseMapper.updateById(supplier);
        if(res >0)
        {
            return FinalResult.ok().message("You have successfully updated the supplier");
        }
        else throw new BusinessException(ResultCode.FAILED);
    }

    public FinalResult deleteSupplier(Long id)
    {
        Supplier supplier = this.baseMapper.selectById(id);
        if(Objects.isNull(supplier))
        {
            throw new BusinessException(ResultCode.PARAM_ERROR, "This supplier does not exist");
        }
        int res = this.baseMapper.deleteById(id);
        if(res >0)
        {
            return FinalResult.ok().message("You have successfully deleted supplier");
        }
        else throw new BusinessException(ResultCode.FAILED);
    }

//    These are the utils classes

    private List<SupplierVo> supplierVoToList( List<Supplier> suppliers)
    {
        List<SupplierVo> supplierVoList = new ArrayList<>(suppliers.size());
        suppliers.forEach(supp ->
        {
            SupplierVo suppVo = new SupplierVo();
            BeanUtils.copyProperties(supp,suppVo);

            supplierVoList.add(suppVo);
        });
        return supplierVoList;
    }


    private static LambdaQueryWrapper<Supplier> executeWrapper( SupplierVo supplierVo)
    {
        LambdaQueryWrapper<Supplier> wrapper = Wrappers.lambdaQuery(Supplier.class);
        if (null != supplierVo.getName())
        {
            wrapper.like(Supplier::getName, supplierVo.getName());
        }
        if (supplierVo.getContact()!=null)
        {
            wrapper.like(Supplier::getContact, supplierVo.getContact());
        }
        if (supplierVo.getAddress()!=null)
        {
            wrapper.like(Supplier::getAddress, supplierVo.getAddress());
        }
        wrapper.orderByDesc(Supplier::getSort);
        return wrapper;
    }
}
