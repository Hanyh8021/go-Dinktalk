package com.han.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.han.domain.ResponseResult;
import com.han.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2022-11-29 14:39:01
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}
