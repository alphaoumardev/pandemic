package com.alpha.pandemic.services;

import com.alpha.pandemic.beans.vo.ProductStockVo;
import com.alpha.pandemic.beans.vo.ProductVo;
import com.alpha.pandemic.mappers.ProductMapper;
import com.alpha.pandemic.models.Product;
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

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductService extends ServiceImpl<ProductMapper, Product>
{
    private final ProductMapper productMapper;

    public FinalResult getProductList(Integer page, Integer size, ProductVo productVo, String category,  Boolean filter)
    {
        if (filter){productVo.setStatus(0);}
        buildCategorySearh(category, productVo);
        Page<Product> pageInfo = this.baseMapper.selectPage(new Page<>(page, size), executeWrapper(productVo));
        return FinalResult.ok().data(pageInfo);
    }

    public FinalResult getProductStockList(Integer page, Integer size, ProductVo productVo, String category)
    {
        buildCategorySearh(category, productVo);
        page = (page -1)*size;
        List<ProductStockVo> list = productMapper.findProductstock(page, size, productVo);
        Page<ProductStockVo> pageInfo = new Page<>();
        pageInfo.setRecords(list);
        pageInfo.setCurrent(page);
        pageInfo.setSize(size);
        long total = productMapper.findProductStockCount(productVo);
        pageInfo.setTotal(total);
        pageInfo.setPages((long) Math.ceil((double)(total/size)));
        return FinalResult.ok().data(pageInfo);
    }

    public FinalResult getAllProducts(ProductVo productVo, String category)
    {
        buildCategorySearh(category, productVo);
        List<ProductStockVo> list = productMapper.getAllProducts(productVo);
        return FinalResult.ok().data(list);
    }

    public FinalResult addProduct( ProductVo productVo)
    {
        if (productVo.getCategoryKeys().length != 3)
        {
            throw new BusinessException(ResultCode.PARAM_ERROR,"The product has three levels");
        }

        Product product = getProduct(productVo);
        product.setStatus(2);
        product.setPNum(UUID.randomUUID().toString().replace("-",""));
        int res = this.baseMapper.insert(product);
        if (res >0)
        {
            return FinalResult.ok().message("You have added a new product");
        }
        throw new BusinessException(ResultCode.FAILED);
    }

    public FinalResult updateProduct( ProductVo productVo)
    {
        if (productVo.getCategoryKeys().length != 3)
        {
            throw new BusinessException(ResultCode.PARAM_ERROR,"The product has three levels");
        }
        Product product = getProduct(productVo);
        product.setStatus(2);
        product.setPNum(UUID.randomUUID().toString().replace("-",""));
        int res = this.baseMapper.updateById(product);
        if (res >0)
        {
            return FinalResult.ok().message("You have updated product");
        }
        throw new BusinessException(ResultCode.FAILED);
    }

    public FinalResult deleteProduct(Long id)
    {
        Product pro = this.baseMapper.selectById(id);
        if(Objects.isNull(pro))
        {
            throw new BusinessException(ResultCode.PARAM_ERROR,"This product does not exist");
        }
        else if(pro.getStatus() != 1 && pro.getStatus() !=2)
        {
            throw new BusinessException(ResultCode.PARAM_ERROR,"This product status has an error");
        }
        int res = this.baseMapper.deleteById(id);
        if(res> 0)
        {
            return FinalResult.ok().message("You have deleted the product");
        }
        else throw new BusinessException(ResultCode.FAILED);
    }

    public FinalResult removeProduct(Long id)
    {
        Product pro = this.baseMapper.selectById(id);
        if(Objects.isNull(pro))
        {
            throw new BusinessException(ResultCode.PARAM_ERROR, "This product does not exist");
        }
        if(pro.getStatus()!=0)
        {
            throw new BusinessException(ResultCode.FAILED, "The product status is not correct");
        }
        pro.setStatus(1);
        int res = this.baseMapper.updateById(pro);
        if(res>0)
        {
            return FinalResult.ok().message("You have updated the product status");
        }
        throw new BusinessException(ResultCode.FAILED,"You got an error during updating the product status");
    }

    public FinalResult pushUpProduct(Long id)
    {
        Product pro = this.baseMapper.selectById(id);
        if(Objects.isNull(pro))
        {
            throw new BusinessException(ResultCode.PARAM_ERROR, "This product does not exist");
        }
        else if(pro.getStatus() != 2)
        {
            throw new BusinessException(ResultCode.PARAM_ERROR,"The product status is not correct");
        }
        pro.setStatus(0);
        int res = this.baseMapper.updateById(pro);
        if(res>0)
        {
            return FinalResult.ok().message("You have pushed up the product");
        }
        throw new BusinessException(ResultCode.PARAM_ERROR,"You got an error during updating the product status");
    }

    public FinalResult backUpProduct(Long id)
    {
        Product pro = this.baseMapper.selectById(id);
        if(Objects.isNull(pro))
        {
            throw new BusinessException(ResultCode.PARAM_ERROR, "This product does not exist");
        }
        else if(pro.getStatus() != 1)
        {
            throw new BusinessException(ResultCode.PARAM_ERROR,"The product status is not correct");
        }

        pro.setStatus(0);
        int res = this.baseMapper.updateById(pro);
        if(res>0)
        {
            return FinalResult.ok().message("You have updated the product status");
        }
        throw new BusinessException(ResultCode.FAILED,"You got an error during updating the product status");
    }

    //   These are the utils classes

    private static LambdaQueryWrapper<Product> executeWrapper( ProductVo productVo)
    {
        LambdaQueryWrapper<Product> wrapper = Wrappers.lambdaQuery();
        if (!Objects.isNull(productVo.getStatus()))
        {
            wrapper.eq(Product::getStatus,productVo.getStatus());
        }
        boolean flag = false;
        if (!Objects.isNull(productVo.getThreeCategoryId()))
        {
            wrapper.eq(Product::getOneCategoryId, productVo.getOneCategoryId());
            wrapper.eq(Product::getTwoCategoryId, productVo.getTwoCategoryId());
            wrapper.eq(Product::getThreeCategoryId, productVo.getThreeCategoryId());
            flag =true;
        }
        else if (!Objects.isNull(productVo.getTwoCategoryId()))
        {
            wrapper.eq(Product::getOneCategoryId, productVo.getOneCategoryId());
            wrapper.eq(Product::getTwoCategoryId, productVo.getTwoCategoryId());
            flag =true;
        }
        else if (!Objects.isNull(productVo.getOneCategoryId()))
        {
            wrapper.eq(Product::getOneCategoryId, productVo.getOneCategoryId());
            flag =true;
        }
        if(flag)return wrapper;
        wrapper.orderByAsc(Product::getSort);
        if (productVo.getName()!=null)
        {
            wrapper.like(Product::getName, productVo.getName());
        }
        if (productVo.getPNum()!=null)
        {
            wrapper.eq(Product::getPNum, productVo.getPNum());
        }
        return wrapper;
    }


    private Product getProduct(ProductVo productVo)
    {
        Product product = new Product();
        BeanUtils.copyProperties(productVo, product);
//        ("The cate. key can't be empty")
        Long[] categoryKey = productVo.getCategoryKeys();
        product.setOneCategoryId(categoryKey[0]);
        product.setTwoCategoryId(categoryKey[1]);
        product.setThreeCategoryId(categoryKey[3]);
        return product;
    }

    private void buildCategorySearh(String category, ProductVo productVo)
    {
        if(category !=null)
        {
            String[] split = category.split(",");
            switch (split.length)
            {
                case 1 -> productVo.setOneCategoryId(Long.parseLong(split[0]));
                case 2 -> {
                    productVo.setOneCategoryId(Long.parseLong(split[0]));
                    productVo.setOneCategoryId(Long.parseLong(split[1]));
                }
                case 3 -> {
                    productVo.setOneCategoryId(Long.parseLong(split[0]));
                    productVo.setOneCategoryId(Long.parseLong(split[1]));
                    productVo.setOneCategoryId(Long.parseLong(split[2]));
                }
            }
        }
    }
}
