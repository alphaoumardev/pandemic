package com.alpha.pandemic.services;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alpha.pandemic.beans.vo.ConsumerVo;
import com.alpha.pandemic.beans.vo.OutStockDetailVo;
import com.alpha.pandemic.beans.vo.OutStockItemVo;
import com.alpha.pandemic.beans.vo.OutStockVo;
import com.alpha.pandemic.mappers.*;
import com.alpha.pandemic.models.*;
import com.alpha.pandemic.structor.security.LoginUser;
import com.alpha.pandemic.structor.tools.UserUtil;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OutStockService extends ServiceImpl<OutStockMapper, OutStock>
{
    private final ConsumerMapper consumerMapper;
    private final ProductMapper productMapper;
    private final OutStockInfoMapper outStockInfoMapper;
    private final ProductStockMapper productStockMapper;

    @Transactional(rollbackFor = BusinessException.class)
    public FinalResult addOutStock( OutStockVo outstockVo)
    {
        if (Objects.isNull(outstockVo.getConsumerId()))
        {
            ConsumerVo consumervo = new ConsumerVo();
            BeanUtils.copyProperties(outstockVo, consumervo);
            if (consumervo.getName().isEmpty()) {
                throw new BusinessException(ResultCode.PARAM_ERROR, "The name cannot be empty");
            }
            if (consumervo.getContact().isEmpty()) {
                throw new BusinessException(ResultCode.PARAM_ERROR, "The contact cannot be empty");
            }
            if (consumervo.getAddress().isEmpty()) {
                throw new BusinessException(ResultCode.PARAM_ERROR, "The address cannot be empty");
            }
            if (consumervo.getPhone().isEmpty()) {
                throw new BusinessException(ResultCode.PARAM_ERROR, "The phone cannot be empty");
            }
            if (Objects.isNull(consumervo.getSort())) {
                throw new BusinessException(ResultCode.PARAM_ERROR, "The sort cannot be empty");
            }
            Consumer con = new Consumer();
            BeanUtils.copyProperties(consumervo, con);
            int res = consumerMapper.insert(con);
            if (res > 0) {
                outstockVo.setConsumerId(con.getId());
            } else
                throw new BusinessException(ResultCode.FAILED, "Adding a new outstock  has failed please try it again");


        }
        String outStockNum = UUID.randomUUID().toString().replace("-","");
        int itemNum =0;
        List<Object> pro = outstockVo.getProducts();
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
            if(proNum<=0)
            {
                throw new BusinessException(ResultCode.FAILED);
            }
            ProductStock productStock = productStockMapper.selectOne(Wrappers.lambdaQuery(ProductStock.class).eq(ProductStock::getPNum, prod.getPNum()));
            if(Objects.isNull(productStock))
            {
                throw new BusinessException(ResultCode.PRODUCT_IN_STOCK_NUMBER_ERROR,prod.getName()+ "This outstock is missing");
            }
            if(proNum > productStock.getStock())
            {
                throw new BusinessException(ResultCode.PRODUCT_IN_STOCK_NUMBER_ERROR,prod.getName()+ "This outstock is missing"+productStock.getStock());
            }
            else itemNum+=proNum;
            OutStockInfo outStockInfo = new OutStockInfo();
            outStockInfo.setProductNumber(proNum);
            outStockInfo.setPNum(prod.getPNum());
            outStockInfo.setOutNum(outStockNum);
            int res = outStockInfoMapper.insert(outStockInfo);
            if(res<=0)
            {
                throw new BusinessException(ResultCode.FAILED, prod.getName()+" insert has failed");
            }
        }
        OutStock out = new OutStock();
        BeanUtils.copyProperties(outstockVo, out);
        out.setProductNumber(itemNum);
        LoginUser loginUser = UserUtil.getCurrentLoginUser();
        assert loginUser != null;
        out.setOperator(loginUser.getUsername());
        out.setOutNum(outStockNum);
        out.setStatus(2);
        int res = this.baseMapper.insert(out);
        if(res>0)
        {
            return FinalResult.ok();
        }
        throw new BusinessException(ResultCode.FAILED);
    }

    public FinalResult getOutStockList(Integer page, Integer size, OutStockVo outStockVo)
    {
        Page<OutStock> outStockPage = this.baseMapper.selectPage(new Page<>(page, size), executeWrapper(outStockVo));
        return FinalResult.ok().data(outStockToPage(outStockPage));
    }

    public FinalResult removeOutStock(Long id)
    {
        OutStock out = this.baseMapper.selectById(id);
        if(Objects.isNull(out))
        {
            throw new BusinessException(ResultCode.PARAM_ERROR, "This outstock does not exist");
        }
        if(out.getStatus() != 0)
        {
            throw new BusinessException(ResultCode.FAILED, "The outstock status is not correct");
        }
        out.setStatus(1);
        int res = this.baseMapper.updateById(out);
        if(res>0)
        {
            return FinalResult.ok().message("You have updated the outstock status");
        }
        throw new BusinessException(ResultCode.FAILED,"You got an error during updating the outstock status");
    }

    public OutStockDetailVo getOutStockDetails(Long id, Integer page, Integer size)
    {
        OutStockDetailVo outDetailVo = new OutStockDetailVo();
        OutStock out = this.baseMapper.selectById(id);
        if(Objects.isNull(out))
        {
            throw new BusinessException("This outstock does not exist");
        }
        BeanUtils.copyProperties(out, outDetailVo);
        Consumer consumers = consumerMapper.selectById(out.getConsumerId());
        if(Objects.isNull(consumers))
        {
            throw new BusinessException("This consumer does not exist");
        }
        ConsumerVo conVo = new ConsumerVo();
        BeanUtils.copyProperties(consumers, conVo);
        outDetailVo.setConsumerVo(conVo);

        String outNum = out.getOutNum();
        Page<OutStockInfo> stockInfoPage = outStockInfoMapper.selectPage(new Page<>(page, size), Wrappers.lambdaQuery(OutStockInfo.class).eq(OutStockInfo::getOutNum, outNum));
        outDetailVo.setTotal(stockInfoPage.getTotal());
        if(!stockInfoPage.getRecords().isEmpty())
        {
            for(OutStockInfo records : stockInfoPage.getRecords())
            {
                String pNum = records.getPNum();
                List<Product> products = productMapper.selectList(Wrappers.lambdaQuery(Product.class).eq(Product::getPNum, pNum));

                if(!products.isEmpty())
                {
                    Product product = products.get(0);
                   OutStockItemVo outstockItemVo = new OutStockItemVo();
                    BeanUtils.copyProperties(product, outstockItemVo);
                    outstockItemVo.setCount(records.getProductNumber());
                    outDetailVo.getItemVos().add(outstockItemVo);
                }
                else throw new BusinessException(ResultCode.PARAM_ERROR);
            }
        }
        else throw new BusinessException(ResultCode.PARAM_ERROR);
        return outDetailVo;
    }

    @Transactional(rollbackFor = BusinessException.class)
    public FinalResult deleteOutStock(Long id)
    {
        OutStock out = this.baseMapper.selectById(id);
        if(Objects.isNull(out))
        {
            throw new BusinessException(ResultCode.PARAM_ERROR,"This outstock does not exist");
        }
        else if(out.getStatus() != 1 && out.getStatus() !=2)
        {
            throw new BusinessException(ResultCode.PARAM_ERROR,"This outstock status has an error");
        }
        int res = this.baseMapper.deleteById(id);
        if(res> 0)
        {
            int del = outStockInfoMapper.delete(Wrappers.lambdaQuery(OutStockInfo.class).eq(OutStockInfo::getOutNum, out.getOutNum()));
            if(del>0)
            {
                return FinalResult.ok();
            }
            return FinalResult.ok().message("You have deleted the outstock");
        }
        else throw new BusinessException(ResultCode.FAILED);
    }

    public FinalResult pushUpOutStock(Long id)
    {
        OutStock out = this.baseMapper.selectById(id);
        if(Objects.isNull(out))
        {
            throw new BusinessException(ResultCode.PARAM_ERROR, "This outstock does not exist");
        }
        else if(out.getStatus() != 2)
        {
            throw new BusinessException(ResultCode.PARAM_ERROR,"The outstock status is not correct");
        }

        Consumer consumers = consumerMapper.selectById(out.getConsumerId());
        if(Objects.isNull(consumers))
        {
            throw new BusinessException(ResultCode.PARAM_ERROR,"You got an error during updating the outstock status");
        }
        List<OutStockInfo> stockInfos = outStockInfoMapper.selectList(Wrappers.lambdaQuery(OutStockInfo.class).eq(OutStockInfo::getOutNum, out.getOutNum()));
        if(!stockInfos.isEmpty())
        {
            for(OutStockInfo info :stockInfos)
            {
                Integer productNumber = info.getProductNumber();
                List<Product> products = productMapper.selectList(Wrappers.lambdaQuery(Product.class).eq(Product::getPNum, info.getPNum()));
                if(products.isEmpty())
                {
                    throw new BusinessException(ResultCode.PARAM_ERROR, "This outstock does not exist");
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
                    productStock.setStock(productStock.getStock()-productNumber);
                    productStockMapper.updateById(productStock);
                }
                out.setStatus(0);
                this.baseMapper.updateById(out);
            }
            return FinalResult.ok();
        }
        else throw new BusinessException(ResultCode.FAILED, "The outstock can't be empty");
    }

    public FinalResult backUpOutStock(Long id)
    {
        OutStock out = this.baseMapper.selectById(id);
        if(Objects.isNull(out))
        {
            throw new BusinessException(ResultCode.PARAM_ERROR, "This outstock does not exist");
        }
        else if(out.getStatus() != 1)
        {
            throw new BusinessException(ResultCode.PARAM_ERROR,"The outstock status is not correct");
        }

        out.setStatus(0);
        int res = this.baseMapper.updateById(out);
        if(res>0)
        {
            return FinalResult.ok().message("You have updated the outstock status");
        }
        throw new BusinessException(ResultCode.FAILED,"You got an error during updating the outstock status");
    }


    //   These are the utils classes

    private Page<OutStockVo> outStockToPage( Page<OutStock> outs)
    {
        Page<OutStockVo> outstockPage = new Page<>();
        BeanUtils.copyProperties(outs, outstockPage);
        outstockPage.setRecords(outList(outs.getRecords()));
        return outstockPage;
    }


    private List<OutStockVo> outList( List<OutStock> records)
    {
        List<OutStockVo> stockVoList = new ArrayList<>(records.size());
        records.forEach(outstock ->
        {
            OutStockVo outstockVo = new OutStockVo();
            BeanUtils.copyProperties(outstock,outstockVo);
            Consumer consumervo = consumerMapper.selectById(outstock.getConsumerId());
            if(!Objects.isNull(consumervo))
            {
                outstockVo.setName(consumervo.getAddress()+" [ "+ consumervo.getName()+" ]");
                outstockVo.setPhone(consumervo.getPhone());
            }
            stockVoList.add(outstockVo);
        });
        return stockVoList;
    }


    private static LambdaQueryWrapper<OutStock> executeWrapper( OutStockVo outVo)
    {
        LambdaQueryWrapper<OutStock> wrapper = Wrappers.lambdaQuery(OutStock.class);
        if (outVo.getOutNum()!=null)
        {
            wrapper.like(OutStock::getOutNum, outVo.getOutNum());
        }
        if (!Objects.isNull(outVo.getStatus()))
        {
            wrapper.eq(OutStock::getStatus, outVo.getStatus());
        }
        if (!Objects.isNull(outVo.getType()))
        {
            wrapper.eq(OutStock::getType, outVo.getType());
        }
        wrapper.orderByDesc(OutStock::getCreateTime);
        return wrapper;
    }
}
