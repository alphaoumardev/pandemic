package com.alpha.pandemic.services;

import com.alpha.pandemic.beans.vo.ConsumerVo;
import com.alpha.pandemic.mappers.ConsumerMapper;
import com.alpha.pandemic.models.Consumer;
import com.alpha.pandemic.utils.exception.BusinessException;
import com.alpha.pandemic.utils.response.FinalResult;
import com.alpha.pandemic.utils.response.ResultCode;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ConsumerService extends ServiceImpl<ConsumerMapper, Consumer>
{
    public FinalResult findConsumer(Integer page, Integer size, ConsumerVo consumerVo)
    {
        Page<Consumer> pageInfo = this.baseMapper.selectPage(new Page<>(page, size), executeWrapper(consumerVo));
        return FinalResult.ok().data(consumerVoPage(pageInfo));
    }

    public FinalResult addNewConsumer(ConsumerVo consumerVo)
    {
        Consumer consumer = new Consumer();
        BeanUtils.copyProperties(consumerVo, consumer);
        int res = this.baseMapper.insert(consumer);
        if(res> 0)
        {
            return FinalResult.ok().message("You have successfully added a new consumer(member of the family)");
        }
        throw new BusinessException(ResultCode.FAILED);
    }

    public FinalResult updateConsumer(Long id, ConsumerVo consumerVo)
    {
        Consumer con = this.baseMapper.selectById(id);
        if(Objects.isNull(con))
        {
            throw new BusinessException(ResultCode.PARAM_ERROR,"The consumer does not exist!  ");
        }
        BeanUtils.copyProperties(consumerVo, con);
        int res = this.baseMapper.updateById(con);
        if(res> 0)
        {
            return FinalResult.ok().message("You have successfully updated the consumer(member of the family)");
        }
        throw new BusinessException(ResultCode.FAILED);
    }

    public FinalResult deleteConsumer(Long id)
    {
        Consumer con = this.baseMapper.selectById(id);
        if(Objects.isNull(con))
        {
            throw new BusinessException(ResultCode.PARAM_ERROR,"The consumer does not exist!  ");
        }
        int res = this.baseMapper.deleteById(con);
        if(res> 0)
        {
            return FinalResult.ok().message("You have successfully deleted the consumer");
        }
        throw new BusinessException(ResultCode.FAILED);
    }

    public List<ConsumerVo> findAllConsumers()
    {
        List<Consumer> all = this.baseMapper.selectList(null);
        return toConsumerList(all);
    }

    //    This the utils classes

    private Page<ConsumerVo> consumerVoPage( Page<Consumer> consumer)
    {
        Page<ConsumerVo> pages = new Page<>();
        List<Consumer> list =consumer.getRecords();
        BeanUtils.copyProperties(consumer, pages);
        pages.setRecords(toConsumerList(list));
        return pages;
    }

    private List<ConsumerVo> toConsumerList( List<Consumer> consumer)
    {
        List<ConsumerVo> consumerVos = new ArrayList<>(consumer.size());
        consumer.forEach(con ->
        {
            ConsumerVo conVo = new ConsumerVo();
            BeanUtils.copyProperties(con, conVo);
            consumerVos.add(conVo);
        });
        return consumerVos;
    }

    private static LambdaQueryWrapper<Consumer> executeWrapper( ConsumerVo consumerVo)
    {
        LambdaQueryWrapper<Consumer> wrapper = new LambdaQueryWrapper<>();
        if (null != consumerVo.getName())
        {
            wrapper.like(Consumer::getName, consumerVo.getName());
        }
        if (null != consumerVo.getAddress())
        {
            wrapper.like(Consumer::getAddress, consumerVo.getAddress());
        }
        if (null != consumerVo.getContact())
        {
            wrapper.like(Consumer::getContact, consumerVo.getContact());
        }
        wrapper.orderByDesc(Consumer::getPhone);
        return wrapper;
    }
}
