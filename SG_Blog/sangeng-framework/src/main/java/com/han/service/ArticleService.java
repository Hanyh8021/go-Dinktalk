package com.han.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.han.domain.ResponseResult;
import com.han.domain.entity.Article;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();



    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleListDetil(Long id);
}
