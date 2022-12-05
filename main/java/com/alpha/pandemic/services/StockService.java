package com.alpha.pandemic.services;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alpha.pandemic.beans.vo.*;
import com.alpha.pandemic.mappers.*;
import com.alpha.pandemic.models.*;
import com.alpha.pandemic.structor.form.StockForm;
import com.alpha.pandemic.structor.security.LoginUser;
import com.alpha.pandemic.structor.tools.ExcelStyleUtil;
import com.alpha.pandemic.structor.tools.ExcelUtil;
import com.alpha.pandemic.structor.tools.UserUtil;
import com.alpha.pandemic.utils.constant.Constant;
import com.alpha.pandemic.utils.exception.BusinessException;
import com.alpha.pandemic.utils.response.FinalResult;
import com.alpha.pandemic.utils.response.ResultCode;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StockService extends ServiceImpl<StockMapper, Stock>
{
    private final SupplierMapper supplierMapper;
    private final ProductMapper productMapper;
    private final StoctInfoMapper stockInfoMapper;
    private final ProductStockMapper productStockMapper;

    public Page<StockVo> getStockList(Integer page, Integer size, StockForm stockForm)
    {
        Page<Stock> pageInfo = this.baseMapper.selectPage(new Page<>(page, size), executeWrapper(stockForm));
        return toStockVoPage(pageInfo);
    }

    public void exportStockList(HttpServletResponse response, StockForm stockForm)
    {
        LambdaQueryWrapper<Stock> wrapper = executeWrapper(stockForm);
        List<Stock> stock = this.baseMapper.selectList(wrapper);
        List<StockExportVo> exportVos = toStockExportList(stock);
        try
        {
            ExportParams params = new ExportParams("Stock list table", "sheet2", ExcelType.XSSF);
            params.setStyle(ExcelStyleUtil.class);
            ExcelUtil.exportExcel(exportVos, RoleVo.class,"Stock list",params,response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new BusinessException(ResultCode.EXPORT_FAIL);
        }
    }

    public StockDetailVo getStockDetails(Long id, Integer page, Integer size)
    {
        Stock in = this.baseMapper.selectById(id);
        if(Objects.isNull(in))
        {
            throw new BusinessException("This stock does not exist");
        }
        StockDetailVo detailVo = new StockDetailVo();
        BeanUtils.copyProperties(in, detailVo);
        Supplier supplier = supplierMapper.selectById(in.getSupplierId());
        if(Objects.isNull(supplier))
        {
            throw new BusinessException("This supplier does not exist");
        }
        SupplierVo supplierVo = new SupplierVo();
        BeanUtils.copyProperties(supplier, supplierVo);
        detailVo.setSupplierVo(supplierVo);

        String inNum = in.getInNum();
        Page<StockInfo> stockInfoPage = stockInfoMapper.selectPage(new Page<>(page, size), Wrappers.lambdaQuery(StockInfo.class).eq(StockInfo::getInNum, inNum));
        detailVo.setTotal(stockInfoPage.getTotal());
        if(!stockInfoPage.getRecords().isEmpty())
        {
            for(StockInfo records : stockInfoPage.getRecords())
            {
                String pNum = records.getPNum();
                List<Product> products = productMapper.selectList(Wrappers.lambdaQuery(Product.class).eq(Product::getPNum, pNum));

                if(!products.isEmpty())
                {
                    Product product = products.get(0);
                    StockItemVo stockItemVo = new StockItemVo();
                    BeanUtils.copyProperties(product, stockItemVo);
                    stockItemVo.setCount(records.getProductNumber());
                    detailVo.getItemVos().add(stockItemVo);
                }
                else throw new BusinessException(ResultCode.PARAM_ERROR);
            }
        }
        else throw new BusinessException(ResultCode.PARAM_ERROR);
        return detailVo;
    }

    public FinalResult removeStock(Long id)
    {
        Stock in = this.baseMapper.selectById(id);
        if(Objects.isNull(in))
        {
            throw new BusinessException(ResultCode.PARAM_ERROR, "This stock does not exist");
        }
        Integer status = in.getStatus();
        if(Constant.ACTIVE.equals(status))
        {
            throw new BusinessException(ResultCode.FAILED, "The stock status is not correct");
        }
        in.setStatus(1);
        boolean res = this.saveOrUpdate(in);
        if(res)
        {
            return FinalResult.ok().message("You have updated the stock status");
        }
        throw new BusinessException(ResultCode.FAILED,"You got an error during updating the stock status");
    }

    public FinalResult backUpStock(Long id)
    {
        Stock in = this.baseMapper.selectById(id);
        if(Objects.isNull(in))
        {
            throw new BusinessException(ResultCode.PARAM_ERROR, "This stock does not exist");
        }
        else if(in.getStatus() != 1)
        {
            throw new BusinessException(ResultCode.PARAM_ERROR,"The stock status is not correct");
        }

        in.setStatus(0);
        boolean res = this.saveOrUpdate(in);
        if(res)
        {
            return FinalResult.ok().message("You have updated the stock status");
        }
        throw new BusinessException(ResultCode.FAILED,"You got an error during updating the stock status");
    }

    public FinalResult pushUpStock(Long id)
    {
        Stock in = this.baseMapper.selectById(id);
        if(Objects.isNull(in))
        {
            throw new BusinessException(ResultCode.PARAM_ERROR, "This stock does not exist");
        }
        else if(in.getStatus() != 2)
        {
            throw new BusinessException(ResultCode.PARAM_ERROR,"The stock status is not correct");
        }

        Supplier supplier = supplierMapper.selectById(in.getSupplierId());
        if(Objects.isNull(supplier))
        {
            throw new BusinessException(ResultCode.PARAM_ERROR,"You got an error during updating the stock status");
        }
        List<StockInfo> stockInfos = stockInfoMapper.selectList(Wrappers.lambdaQuery(StockInfo.class).eq(StockInfo::getInNum, in.getInNum()));
        if(!stockInfos.isEmpty())
        {
            for(StockInfo info :stockInfos)
            {
                List<Product> products = productMapper.selectList(Wrappers.lambdaQuery(Product.class).eq(Product::getPNum, info.getInNum()));
                if(products.isEmpty())
                {
                    throw new BusinessException(ResultCode.PARAM_ERROR, "This stock does not exist");
                }
                Product product = products.get(0);
                List<ProductStock> productStocks = productStockMapper.selectList(Wrappers.lambdaQuery(ProductStock.class).eq(ProductStock::getPNum, product.getPNum()));

                if(productStocks.isEmpty())
                {
                    ProductStock productStock = new ProductStock();
                    productStock.setPNum(product.getPNum());
                    productStock.setStock((long) info.getProductNumber());
                    productStockMapper.insert(productStock);
                }
                else
                {
                    ProductStock productStock = productStocks.get(0);
                    productStock.setStock(productStock.getStock()+info.getProductNumber());
                    productStockMapper.updateById(productStock);

                }
                in.setStatus(0);
                this.baseMapper.updateById(in);
            }
            return FinalResult.ok();
        }
        else throw new BusinessException(ResultCode.FAILED, "The stock can't be empty");
    }

    public FinalResult deleteStock(Long id)
    {
        Stock in = this.baseMapper.selectById(id);
        if(Objects.isNull(in))
        {
            throw new BusinessException(ResultCode.PARAM_ERROR,"This stock does not exist");
        }
        else if(in.getStatus() != 1 && in.getStatus() !=2)
        {
            throw new BusinessException(ResultCode.PARAM_ERROR,"This stock status has an error");
        }
        int res = this.baseMapper.deleteById(id);
        stockInfoMapper.delete(Wrappers.lambdaQuery(StockInfo.class).eq(StockInfo::getInNum, in.getInNum()));
        if(res> 0)
        {
            return FinalResult.ok().message("You have deleted the stock");
        }
        else throw new BusinessException(ResultCode.FAILED);
    }

    @Transactional(rollbackFor = BusinessException.class)
    public FinalResult addStock( StockVo stockVo)
    {
        if(Objects.isNull(stockVo.getSupplierId()))
        {
            SupplierVo supplier = new SupplierVo();
            BeanUtils.copyProperties(stockVo, supplier);
            if(supplier.getName().isEmpty())
            {
                throw new BusinessException(ResultCode.PARAM_ERROR,"The name cannot be empty");
            }
            if(supplier.getEmail().isEmpty())
            {
                throw new BusinessException(ResultCode.PARAM_ERROR,"The email cannot be empty");
            }
            if(supplier.getContact().isEmpty())
            {
                throw new BusinessException(ResultCode.PARAM_ERROR,"The contact cannot be empty");
            }
            if(supplier.getAddress().isEmpty())
            {
                throw new BusinessException(ResultCode.PARAM_ERROR,"The address cannot be empty");
            }
            if(supplier.getPhone().isEmpty())
            {
                throw new BusinessException(ResultCode.PARAM_ERROR,"The phone cannot be empty");
            }
            if(Objects.isNull(supplier.getSort()))
            {
                throw new BusinessException(ResultCode.PARAM_ERROR,"The sort cannot be empty");
            }
            Supplier sup = new Supplier();
            BeanUtils.copyProperties(supplier, sup);
            int res = supplierMapper.insert(sup);
            if(res <= 0)
            {
                throw new BusinessException(ResultCode.FAILED,"Adding a new stock  has failed please try it again");
            }
            stockVo.setSupplierId(sup.getId());
        }

        String stockNum = UUID.randomUUID().toString().replace("-","");
        int itemNum =0;
        List<Object> pro = stockVo.getProducts();
        if(pro.isEmpty())
        {
            throw new BusinessException(ResultCode.PRODUCT_IN_STOCK_EMPTY);
        }
        for(Object pr : pro)
        {
            JSONObject jsonObject = JSONUtil.parseObj(pr);
            Integer proNum = jsonObject.getInt("ProductNumber");
            Integer proId = jsonObject.getInt("ProductId");

            Product prod = productMapper.selectById(proId);
            if(Objects.isNull(prod))
            {
                throw new BusinessException(ResultCode.PRODUCT_NOT_FOUND);
            }
            else if(prod.getStatus()==1)
            {
                throw new BusinessException(ResultCode.PRODUCT_IS_REMOVE,prod.getName()+ "This stock is removed");
            }
            else if(prod.getStatus()==2)
            {
                throw new BusinessException(ResultCode.PRODUCT_WAIT_PASS,prod.getName()+ "This stock is loading");
            }
            else if(prod.getStatus()<=0)
            {
                throw new BusinessException(ResultCode.PRODUCT_IN_STOCK_NUMBER_ERROR,prod.getName()+ "This stock is missing");
            }
            else itemNum+=proNum;
            StockInfo stockInfo = new StockInfo();
            stockInfo.setProductNumber(proNum);
            stockInfo.setPNum(prod.getPNum());
            stockInfo.setInNum(stockNum);
            stockInfoMapper.insert(stockInfo);
        }
        Stock stock = new Stock();
        BeanUtils.copyProperties(stockVo, stock);
        stock.setProductNumber(itemNum);
        LoginUser loginUser = UserUtil.getCurrentLoginUser();
        assert loginUser != null;
        stock.setOperator(loginUser.getUsername());
        stock.setInNum(stockNum);
        stock.setStatus(2);
        int res = this.baseMapper.insert(stock);
        if(res>0)
        {
            return FinalResult.ok();
        }
        throw new BusinessException(ResultCode.FAILED);
    }

    //   These are the utils classes

    private Page<StockVo> toStockVoPage( Page<Stock> sto)
    {
        Page<StockVo> stockPage = new Page<>();
        BeanUtils.copyProperties(sto, stockPage);
        List<StockVo> voList =toStockList(sto.getRecords());
        stockPage.setRecords(voList);
        return stockPage;
    }


    private List<StockExportVo> toStockExportList( List<Stock> stock)
    {
        List<StockExportVo> stockVoList = new ArrayList<>(stock.size());
        stock.forEach(sto ->
        {
            StockExportVo stockExportVo = new StockExportVo();
            BeanUtils.copyProperties(sto,stockExportVo);
            Supplier supplier = supplierMapper.selectById(sto.getSupplierId());
            if(supplier != null)
            {
                stockExportVo.setSupplierName(supplier.getName());
                stockExportVo.setPhone(supplier.getPhone());
            }
            stockExportVo.setType(getTagString("type", sto.getType()));
            stockExportVo.setStatus(getTagString("status", sto.getStatus()));

            stockVoList.add(stockExportVo);
        });
        return stockVoList;
    }

//    @Contract(pure = true)
    private String getTagString( String type, Integer value)
    {
        if(type.equals("type"))
        {
            switch (value)
            {
                case 0:
                    return "Lost";
                case 1:
                    return "Out";
                case 2:
                    return "selled";
                case 3:
                    return "Returned";
                case 4:
                    return "Unknown";
            }
        }
        else if(type.equals("status"))
        {
            switch (value)
            {
                case 0:
                    return "Returned";
                case 1:
                    return "In stock";
                case 2:
                    return "Loading";
                case 3:
                    return "Unknow";
            }
        }
        return "[Type error]";
    }

    public List<StockVo> toStockList( List<Stock> stocks)
    {
        List<StockVo> stockVoList = new ArrayList<>(stocks.size());
        stocks.forEach(stock ->
        {
            StockVo stockVo = new StockVo();
            BeanUtils.copyProperties(stock,stockVo);
            Supplier supplier = supplierMapper.selectById(stock.getSupplierId());
            if(supplier != null)
            {
                stockVo.setSupplierName(supplier.getName());
                stockVo.setPhone(supplier.getPhone());
            }
            stockVoList.add(stockVo);
        });
        return stockVoList;
    }

    private static LambdaQueryWrapper<Stock> executeWrapper( StockForm stockForm)
    {
        LambdaQueryWrapper<Stock> wrapper = new LambdaQueryWrapper<>();
        if (null != stockForm.getType())
        {
            wrapper.eq(Stock::getType, stockForm.getType());
        }
        if (stockForm.getInNum()!=null)
        {
            wrapper.eq(Stock::getInNum, stockForm.getInNum());
        }
        if (stockForm.getStatus()!=null)
        {
            wrapper.eq(Stock::getStatus, stockForm.getStatus());
        }
        if (!Objects.isNull(stockForm.getStartTime())&& !Objects.isNull(stockForm.getEndTime()))
        {
            wrapper.between(Stock::getCreateTime,stockForm.getStartTime(),stockForm.getEndTime());
        }
        wrapper.orderByDesc(Stock::getCreateTime);
        return wrapper;
    }
}
