package com.han.controller;

import com.han.domain.ResponseResult;
import com.han.domain.entity.User;
import com.han.enums.AppHttpCodeEnum;
import com.han.exception.SystemException;
import com.han.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService blogLoginService;
    //post请求在请求体中是需要注释的
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            //提示必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
            //后期要自定义异常情况，转换成我们需要的事情(已经处理)
        }
        return blogLoginService.login(user);
    }


    @PostMapping("/logout")
    public ResponseResult logout(){
        return blogLoginService.logout();
    }
}
