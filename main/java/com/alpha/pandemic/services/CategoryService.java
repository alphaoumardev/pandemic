package com.alpha.pandemic.services;

import com.alpha.pandemic.beans.vo.ProductCategoryVo;
import com.alpha.pandemic.mappers.CategoryMapper;
import com.alpha.pandemic.mappers.ProductMapper;
import com.alpha.pandemic.models.Category;
import com.alpha.pandemic.models.Product;
import com.alpha.pandemic.structor.tools.PageDataUtil;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CategoryService extends ServiceImpl<CategoryMapper, Category>
{
    private final ProductMapper productMapper;

    public FinalResult getCategoryList(Integer page, Integer size, ProductCategoryVo categoryVo)
    {
        Page<Category> pageInfo = this.baseMapper.selectPage(new Page<>(page, size), executeWrapper(categoryVo));
        return FinalResult.ok().data(pageInfo);
    }

    public FinalResult getCategoryTree(Integer page, Integer size)
    {
        List<Category> listAll = this.baseMapper.selectList(null);
        List<ProductCategoryVo> treeNode = nodeList(listAll);
        List<ProductCategoryVo> categoryNode = buildCategory(treeNode, false);
        Page<ProductCategoryVo> pageInfo = new Page<>();
        pageInfo.setCurrent(page);
        pageInfo.setSize(size);
        long total = categoryNode.size();
        pageInfo.setTotal(total);
        pageInfo.setPages((long) Math.ceil((double)(total/size)));
        pageInfo.setRecords(PageDataUtil.getPageData(page, size,categoryNode));
        return FinalResult.ok().data(pageInfo);
    }

    public List<ProductCategoryVo> getParentNode()
    {
        List<Category> listAll = this.baseMapper.selectList(null);
        List<ProductCategoryVo> treeNode = nodeList(listAll);
        return buildCategory(treeNode, true);
    }

    public FinalResult addCategory(ProductCategoryVo categoryVo)
    {
        Category category = new Category();
        BeanUtils.copyProperties(categoryVo, category);
        int res = this.baseMapper.insert(category);
        if (res >0)
        {
            return FinalResult.ok().message("You have added a new category");
        }
        throw new BusinessException(ResultCode.FAILED);
    }

    public FinalResult updateCategory(Long id, ProductCategoryVo categoryVo)
    {
        Category category = this.baseMapper.selectById(id);
        if(!Objects.isNull(category))
        {
            throw new BusinessException(ResultCode.PARAM_ERROR, "This category does not exist");
        }
        assert false;
        BeanUtils.copyProperties(categoryVo, category);
        int res = this.baseMapper.updateById(category);
        if(res>0)
        {
            return FinalResult.ok().message("You have updated the category");
        }
        throw new BusinessException(ResultCode.FAILED);
    }

    public FinalResult deleteCategory(Long id)
    {
        Category pro = this.baseMapper.selectById(id);
        if(Objects.isNull(pro))
        {
            throw new BusinessException(ResultCode.PARAM_ERROR,"This product does not exist");
        }

        int count = Math.toIntExact(this.baseMapper.selectCount(Wrappers.lambdaQuery(Category.class).eq(Category::getPid, id)));
        if (count>0)
        {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        LambdaQueryWrapper<Product> wrapper = Wrappers.lambdaQuery(Product.class);
        wrapper.eq(Product::getOneCategoryId, id)
               .eq(Product::getTwoCategoryId, id)
               .eq(Product::getThreeCategoryId, id);
        int c = Math.toIntExact(productMapper.selectCount(wrapper));
        if (c>0)
        {
            throw new BusinessException(ResultCode.PARAM_ERROR);
        }
        int res = this.baseMapper.deleteById(id);
        if(res>0)
        {
            return FinalResult.ok();
        }
        throw new BusinessException(ResultCode.FAILED);
    }


    //   These are the utils classes

    private List<ProductCategoryVo> buildCategory( List<ProductCategoryVo> treeNode, boolean parent)
    {
        List<ProductCategoryVo> root =new ArrayList<>();
        for (ProductCategoryVo  pro: treeNode)
        {
            if(pro.getId()==0)
            {
                pro.setLev(1);
                root.add(pro);
            }
        }
        root.sort(ProductCategoryVo.order());
        for(ProductCategoryVo vo : root)
        {
            List<ProductCategoryVo> child;
            if (parent)
            {
                child= getParent(vo, treeNode);
            }
            else child = getChild(vo, treeNode);
            vo.setChildren(child);
        }
        return root;
    }


    private List<ProductCategoryVo> getChild(ProductCategoryVo vo,  List<ProductCategoryVo> treeNode)
    {
        List<ProductCategoryVo> root =new ArrayList<>();
        for (ProductCategoryVo  vos: treeNode)
        {
            if(vos.getPid().equals(vo.getId()))
            {
                vos.setLev(vo.getLev()+1);
                root.add(vos);
            }
        }
        return root;
    }


    private List<ProductCategoryVo> getParent(ProductCategoryVo vo,  List<ProductCategoryVo> treeNode)
    {
        List<ProductCategoryVo> root =new ArrayList<>();
        for (ProductCategoryVo  vos: treeNode)
        {
            if(vos.getPid().equals(vo.getId()))
            {
                vos.setLev(2);
                root.add(vos);
            }
        }
        return root;
    }


    private List<ProductCategoryVo> nodeList( List<Category> listAll)
    {
        List<ProductCategoryVo> list = new ArrayList<>();
        if (!listAll.isEmpty())
        {
            for (Category category : listAll)
            {
                ProductCategoryVo pro =new ProductCategoryVo();
                BeanUtils.copyProperties(category, pro);
                list.add(pro);
            }
        }
        return list;
    }


    private static LambdaQueryWrapper<Category> executeWrapper( ProductCategoryVo categoryVo)
    {
        LambdaQueryWrapper<Category> wrapper = Wrappers.lambdaQuery(Category.class);
        if (!Objects.isNull(categoryVo.getName()))
        {
            wrapper.like(Category::getName,categoryVo.getName());
        }
        if (!Objects.isNull(categoryVo.getRemark()))
        {
            wrapper.like(Category::getRemark,categoryVo.getRemark());
        }
        if (!Objects.isNull(categoryVo.getSort()))
        {
            wrapper.eq(Category::getSort,categoryVo.getSort());
        }
        wrapper.orderByAsc(Category::getSort);
        return wrapper;
    }
}
