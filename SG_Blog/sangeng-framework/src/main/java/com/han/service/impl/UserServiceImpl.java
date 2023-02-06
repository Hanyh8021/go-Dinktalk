package com.han.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.han.domain.entity.User;
import com.han.mapper.UserMapper;
import com.han.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2022-12-28 16:47:45
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
