package com.alpha.pandemic.services;

import com.alpha.pandemic.beans.vo.HealthVo;
import com.alpha.pandemic.beans.vo.HealthyExportVo;
import com.alpha.pandemic.mappers.HealthMapper;
import com.alpha.pandemic.mappers.UserMapper;
import com.alpha.pandemic.models.Health;
import com.alpha.pandemic.structor.form.DailyCheckForm;
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

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class HealthService extends ServiceImpl<HealthMapper, Health>
{
    private final UserMapper userMapper;

    public Health report(Long id)
    {
        List<Health> list = this.baseMapper.isReport(id);
        if(list.isEmpty()) return null;
        else return list.get(0);
    }

    public int healthReport( HealthVo healthVo)
    {
        Health health = this.report(healthVo.getUserId());
        if(!Objects.isNull(health))
        {
            throw new BusinessException(ResultCode.DOUBLE_REPORT);
        }
        Health newHealth = new Health();
        BeanUtils.copyProperties(healthVo, newHealth);
        return this.baseMapper.insert(newHealth);
    }

    public Page<Health> getHistoryByList(Long id, Integer page, Integer size)
    {
        LambdaQueryWrapper<Health> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Health::getCreateTime, id);
        wrapper.orderByDesc(Health::getCreateTime);
        return this.baseMapper.selectPage(new Page<>(page, size), wrapper);
    }

    public Page<HealthVo> getDailyPoint(Integer page, Integer size, DailyCheckForm dailyCheck)
    {
        Page<Health> pageInfo = this.baseMapper.selectPage(new Page<>(page,size), executeWrapper(dailyCheck));
        return toCheckVoPage(pageInfo);
    }

    public FinalResult deleteHealthCheck(Long id)
    {
        Health health = this.baseMapper.selectById(id);
        if(Objects.isNull(health))
        {
            throw new BusinessException(ResultCode.FAILED, "This report does not exist");
        }
        int remove = this.baseMapper.deleteById(id);
        if(remove>0)
        {
            return FinalResult.ok().message("You have successfully deleted the daily report");
        }
        throw new BusinessException(ResultCode.FAILED);
    }

    public void exportHealthCheck(HttpServletResponse response, DailyCheckForm dailyCheck)
    {
        List<Health> health = this.baseMapper.selectList(executeWrapper(dailyCheck));
        List<HealthyExportVo> exportVos = toHealthExport(toVoList(health));
    }


//    This is an util class

    private Page<HealthVo> toCheckVoPage(Page<Health> pageInfo)
    {
        Page<HealthVo> voPage = new Page<>();
        BeanUtils.copyProperties(pageInfo, voPage);
        voPage.setRecords(toVoList(pageInfo.getRecords()));
        return voPage;
    }


    private List<HealthVo> toVoList( List<Health> healthList)
    {
        List<HealthVo> healthVos = new ArrayList<>(healthList.size());
        healthList.forEach(hel ->
        {
            HealthVo healthVo = new HealthVo();
            BeanUtils.copyProperties(hel,healthVos);
            healthVo.setUsername(userMapper.getUsername(hel.getUserId()));
            healthVos.add(healthVo);
        });
        return healthVos;
    }


    private List<HealthyExportVo> toHealthExport( List<HealthVo> helf)
    {
        List<HealthyExportVo> healthVos = new ArrayList<>(helf.size());
        helf.forEach(dep ->
        {
            HealthyExportVo helVo = new HealthyExportVo();
            BeanUtils.copyProperties(dep, helVo);
            helVo.setSituation(getTagString("Situation",dep.getSituation()));
            helVo.setTouch(getTagString("Touch",dep.getTouch()));
            helVo.setPassby(getTagString("Passby",dep.getPassby()));
            helVo.setReception(getTagString("Reception",dep.getReception()));
            healthVos.add(helVo);
        });
        return healthVos;
    }


    private String getTagString( String type, Integer value)
    {
        if(type.equals("Situation"))
        {
            switch (value)
            {
                case 0:
                    return "Healthy";
                case 1:
                    return "Hill";
                case 2:
                    return "Other";
            }
        }
        else
        {
            switch (value)
            {
                case 0:
                    return "Yeah";
                case 1:
                    return "No";
            }
        }
        return "Type error";
    }

    public LambdaQueryWrapper<Health> executeWrapper( DailyCheckForm dailycheckForm)
    {
        LambdaQueryWrapper<Health> wrapper = Wrappers.lambdaQuery(Health.class);
        if (null != dailycheckForm.getOrigin())
        {
            String address = String.format("%s/%s/%s",dailycheckForm.getProvince(),dailycheckForm.getCity(),dailycheckForm.getOrigin());
            wrapper.eq(Health::getAddress,address);
        }
        else if (null != dailycheckForm.getCity())
        {
            String address = String.format("%s/%s",dailycheckForm.getProvince(),dailycheckForm.getCity());
            wrapper.likeRight(Health::getAddress,address);
        }
        else if (null != dailycheckForm.getProvince())
        {
            String address = String.format("%s/",dailycheckForm.getProvince());
            wrapper.likeRight(Health::getAddress,address);
        }

        if (null != dailycheckForm.getUsername())
        {
            Long id = userMapper.getUserByUsername(dailycheckForm.getUsername());
            if(Objects.isNull(id))
            {
                wrapper.isNull(Health::getUserId);
            }
            else
            wrapper.eq(Health::getUserId,id);
        }
        if (!Objects.isNull(dailycheckForm.getSituation()))
        {
            wrapper.eq(Health::getSituation,dailycheckForm.getSituation());
        }
        if (!Objects.isNull(dailycheckForm.getTouch()))
        {
            wrapper.eq(Health::getTouch,dailycheckForm.getTouch());
        }
        if (!Objects.isNull(dailycheckForm.getPassby()))
        {
            wrapper.eq(Health::getPassby,dailycheckForm.getPassby());
        }
        if (!Objects.isNull(dailycheckForm.getReception()))
        {
            wrapper.eq(Health::getReception,dailycheckForm.getReception());
        }
        if (!Objects.isNull(dailycheckForm.getStartTime())&& !Objects.isNull(dailycheckForm.getEndTime()))
        {
            wrapper.between(Health::getCreateTime,dailycheckForm.getStartTime(),dailycheckForm.getEndTime());
        }
        wrapper.orderByDesc(Health::getCreateTime);
        return wrapper;
    }


}
