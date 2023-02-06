package com.han.service;

import com.han.domain.ResponseResult;
import com.han.domain.entity.User;

public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
