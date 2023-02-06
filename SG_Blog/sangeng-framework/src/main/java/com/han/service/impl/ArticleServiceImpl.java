package com.han.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.han.constants.SystemConstants;
import com.han.domain.ResponseResult;
import com.han.domain.VO.ArticleDetilVo;
import com.han.domain.VO.ArticleListVo;
import com.han.domain.VO.HotArticleVo;
import com.han.domain.VO.PageVo;
import com.han.domain.entity.Category;
import com.han.mapper.ArticleMapper;
import com.han.service.ArticleService;
import com.han.domain.entity.Article;
import com.han.service.CategoryService;
import com.han.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Override
    public ResponseResult hotArticleList(){
        //查询热门文章的需求实现
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //必须是正常的文章而不是被删除过的文章；第二按照浏览量进行排序，这里可以使用page方法实现
        //最多只查询10条
        //想到与使用Article中的字段与0比较
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        queryWrapper.orderByDesc(Article::getViewCount);
        Page<Article> page = new Page<>(1,10);
        page(page,queryWrapper);

        List<Article> articles = page.getRecords();
//这一部分可以将要使用Bean拷贝的封装工具类
        //使用到了bean拷贝,后面会封装成VO
//        List<HotArticleVo> articleVos = new ArrayList<>();
//        for (Article article: articles) {
//            HotArticleVo vo = new HotArticleVo();
//            BeanUtils.copyProperties(article,vo);
//            articleVos.add(vo);
//        }
        List<HotArticleVo> vs = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
        return ResponseResult.okResult(vs);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件：在首页和分类页面都要查询文章列表
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //第一个参数表示判断是否为空，同时
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId)&&categoryId >0,Article::getCategoryId,categoryId);
        //判断状态是否是正常发布的
        lambdaQueryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        //对isTop进行降序排序
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);
        //如果有categoryID 就要查询时要传入的时间相同    状态是正式发布的    对istop进行降序排序
        //分页查询
        Page<Article> page = new Page<>(pageNum,pageSize);//这里pageName 实际上是pageNum
        page(page,lambdaQueryWrapper);

        //查询CategoryName
        List<Article> articles = page.getRecords();
        //用Id去查询Name进行设置
//        articles.stream()
//                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
//                .collect(Collectors.toList());
        //articleId去查询articleName进行设置
        for (Article article: articles) {
            Category byId = categoryService.getById(article.getCategoryId());
            article.setCategoryName(byId.getName());
        }

        //封装查询结果为成Vo
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(),ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        //在这里会有一个错误出现，错误详情是在进行postman测试的时候
        //会出现500的错误提示，并爆出空指针异常的错误，产生的主要原因是在进行测试的时候没有对MyBatiesPlus进行配置
        //最终导致在进行分页的时候没有成功，进行了分页的操作但是实际上没有实现分页的功能。因此需要让MD支持分页配置。
        return ResponseResult.okResult(pageVo);

    }

    @Override
    public ResponseResult getArticleListDetil(Long id) {
        //根据id查询文章
        Article article = getById(id);
        //转化为VO
        ArticleDetilVo articleDetilVo = BeanCopyUtils.copyBean(article, ArticleDetilVo.class);
        //根据分类id查询分类名称
        Long categoryID = articleDetilVo.getCategoryID();
        Category category = categoryService.getById(categoryID);
        if(category!=null){
            articleDetilVo.setCategoryName(category.getName());
        }
        //封装响应返回
        return ResponseResult.okResult(articleDetilVo);
    }
}
