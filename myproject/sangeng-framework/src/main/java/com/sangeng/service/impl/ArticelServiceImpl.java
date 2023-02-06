package com.sangeng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.domain.entity.Article;
import com.sangeng.mapper.ArticalMapper;
import com.sangeng.service.ArticleService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
public class ArticelServiceImpl extends ServiceImpl<ArticalMapper, Article> implements ArticleService {

}
