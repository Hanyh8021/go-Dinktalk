package com.han.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.han.domain.ResponseResult;
import com.han.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2022-12-07 15:48:25
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}
