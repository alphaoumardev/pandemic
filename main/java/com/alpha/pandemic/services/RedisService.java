package com.alpha.pandemic.services;

import com.alpha.pandemic.config.RedisCustom;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class RedisService
{
    public final StringRedisTemplate stringRedis;

    public String getLoginVerifyCode(String verifyCodeId)
    {
        return stringRedis.opsForValue().get(RedisCustom.LOGIN_VERIFY_CODE + verifyCodeId);
    }

    public void setLoginVerifyCode(String uuid, String verifyCode, long loginVerifyCode)
    {
        String key= RedisCustom.LOGIN_VERIFY_CODE+uuid;
        if(isExitKey(key))
        {
            removeByKey(key);
        }
        stringRedis.opsForValue().set(key,  verifyCode, loginVerifyCode, TimeUnit.SECONDS);
    }
    public void removeByKey(String key)
    {
        stringRedis.delete(key);
    }
    public boolean isExitKey(String key)
    {
        Boolean bool = stringRedis.hasKey(key);
        return Objects.equals(bool,true);
    }

    public void deleteLoginVerifyCode(String verifyCodeId)
    {
        String key = RedisCustom.LOGIN_VERIFY_CODE + verifyCodeId;
        stringRedis.delete(key);
    }

    public Long getKeyInterval(String loginVerifyCode)
    {
        return stringRedis.getExpire(loginVerifyCode);
    }

}
