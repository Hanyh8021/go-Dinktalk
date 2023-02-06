package com.han.filter;

import com.alibaba.fastjson.JSON;
import com.han.domain.ResponseResult;
import com.han.domain.entity.LoginUser;
import com.han.enums.AppHttpCodeEnum;
import com.han.utils.JwtUtil;
import com.han.utils.RedisCache;
import com.han.utils.WebUtils;
import io.jsonwebtoken.Claims;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //获取请求头中的token
        String token = httpServletRequest.getHeader("token");
        if(!StringUtils.hasText(token)){
            //说明该接口不需要登录，直接放行
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        //获取UserID
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            //token超时或者前端发送的token非法
            e.printStackTrace();
            //响应告诉前端需要重新登录
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(httpServletResponse, JSON.toJSONString(result));
            return;
        }
        String userId = claims.getSubject();
        //从Redis中获取用户信息
        LoginUser loginUser = redisCache.getCacheObject("bloglogin"+userId);

        //如果获取不到
        if(Objects.isNull(loginUser)){
            //说明登录过期，提示重新登录
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(httpServletResponse, JSON.toJSONString(result));
            return;
        }
        //存入SencuarityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //放行
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
