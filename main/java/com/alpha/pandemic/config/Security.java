package com.alpha.pandemic.config;

import com.alpha.pandemic.services.RedisService;
import com.alpha.pandemic.structor.filter.JwtAuthoraztionFilter;
import com.alpha.pandemic.structor.filter.SecurityLoginFilter;
import com.alpha.pandemic.structor.handler.*;
import com.alpha.pandemic.structor.security.UserDetailsLogin;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Security extends WebSecurityConfigurerAdapter
{
    private final UserDetailsLogin userDetails;
    private final LoginFailure authFailureHandler;
    private final LoginSuccess authSuccessHandler;
    private final RedisService redisService;
    private final DeniedAccess accessDeniedHandler;
    private final EntryPoint authEntryPoint;
    private final JwtAuthoraztionFilter jwtAuthoraztionFilter;
    private final Logout logout;
    private final LogoutSuccess logoutSuccess;


    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityLoginFilter securityLoginFilter() throws Exception
    {
        SecurityLoginFilter securityLoginFilter = new SecurityLoginFilter(redisService);
        securityLoginFilter.setAuthenticationFailureHandler(authFailureHandler);
        securityLoginFilter.setAuthenticationSuccessHandler(authSuccessHandler);
        securityLoginFilter.setAuthenticationManager(authenticationManager());
        return securityLoginFilter;
    }

    @Override
    public void configure( AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure( org.springframework.security.config.annotation.web.builders.WebSecurity webSecurity)
    {
        webSecurity.ignoring().antMatchers(HttpMethod.GET,
                "/shop.jpg",
                "/**/*.png",
                "/**/*.ttf",
                "/*.html",
                "/**/*.css",
                "/**/*.js");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable();
//        // 第一步：解决跨域问题，允许OPTIONS请求，很重要，跨域都会发送该请求
        http.authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
//
//        // 第二步：让Security永远不会创建HttpSession，它不会使用HttpSession来获取SecurityContext
        http.csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and().headers().cacheControl();
//
//        // 第八步：关闭 iframe 的限制，才可以嵌套显示 druid，接口文档页面
        http.headers().frameOptions().disable();

        http.addFilterAt(jwtAuthoraztionFilter, BasicAuthenticationFilter.class);

        http.exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);

        http.addFilterAt(securityLoginFilter(), UsernamePasswordAuthenticationFilter.class);

        http.formLogin().loginProcessingUrl("/user/login");
        http.logout().addLogoutHandler(logout).
                logoutSuccessHandler(logoutSuccess).permitAll();
    }
}
