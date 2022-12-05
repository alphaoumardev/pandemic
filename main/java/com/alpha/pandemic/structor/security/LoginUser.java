package com.alpha.pandemic.structor.security;

import com.alpha.pandemic.models.Menu;
import com.alpha.pandemic.models.User;
import com.alpha.pandemic.utils.constant.Constant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class LoginUser extends User implements UserDetails
{
    private List<String> authorities;
    private List<Menu> menus;
    private String token;
    private Long loginTime;
    private Long expireTime;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return authorities.parallelStream().filter(auth -> !auth.isEmpty())
                .map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked()
    {
        return Constant.ACTIVE.equals(getForbidden());
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled()
    {
        return Constant.ACTIVE.equals(getForbidden());
    }
}
