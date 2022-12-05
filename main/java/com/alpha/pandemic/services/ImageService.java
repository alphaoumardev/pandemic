package com.alpha.pandemic.services;

import com.alpha.pandemic.mappers.ImageMapper;
import com.alpha.pandemic.models.Image;
import com.alpha.pandemic.structor.form.ImageForm;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ImageService extends ServiceImpl<ImageMapper, Image>
{

    public Page<Image> listPage(Integer page, Integer size,  ImageForm imageForm)
    {
        LambdaQueryWrapper<Image> wrapper = new LambdaQueryWrapper<>();
        if(imageForm.getMediaType() != null)
        {
            wrapper.eq(Image::getMediaType, imageForm.getMediaType());
        }
        if(imageForm.getPath() != null)
        {
            wrapper.eq(Image::getPath, imageForm.getPath());
        }
        if(imageForm.getSuffix() != null)
        {
            wrapper.eq(Image::getSuffix, imageForm.getSuffix());
        }
        wrapper.orderByDesc(Image::getCreateTime);
        return this.baseMapper.selectPage(new Page<>(page, size),wrapper);
    }
}
