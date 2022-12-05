package com.alpha.pandemic.structor.tools;

import com.alpha.pandemic.structor.security.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Slf4j
@Component
public class JwtUtil
{
    @Value("${token.expireTime}")
    private Long expireTime;

    @Value("${token.secret}")
    private String secret;

    public String generateToken(String accountName)
    {
        Date nowDate = new Date();
        Date expireDate = new Date(System.currentTimeMillis() + expireTime * 1000L);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(accountName)
                .setIssuedAt(nowDate)   //设置生成 token 的时间
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims getClaimByToken(String token)
    {
        try
        {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }
        catch (Exception e)
        {
            log.error("[getClaimByToken]:token is incorrect! {}", e.getMessage());
            return null;
        }
    }

    public Date getExpiration(String token)
    {
        return getClaimByToken(token).getExpiration();
    }

    public boolean isExpired(String token)
    {
        try
        {
            final Date expiration = getExpiration(token);
            return expiration.before(new Date());
        }
        catch (Exception e)
        {
            log.error("[JwtUtils --> isExpired]: {}", e.getMessage());
            return true;
        }
    }

    public boolean isJwtStr(String token)
    {
        return StringUtils.countOccurrencesOf(token, ".") == 2;
    }

    public String getAccountName(String token)
    {
        return getClaimByToken(token).getSubject();
    }

    public boolean checkIsCurrentLoginUser(String token,  LoginUser loginUser)
    {
        return loginUser.getUsername().equals(getAccountName(token));
    }
}
