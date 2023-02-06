package com.han.controller;


import com.han.domain.ResponseResult;
import com.han.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList() {

        ResponseResult result = articleService.hotArticleList();
        return result;
    }
  /*  @GetMapping("/list")
    public List<Article> test(){
        return articleService.list();
    }
    */
    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum, Integer pageSize,Long categoryId){
       return articleService.articleList(pageNum,pageSize,categoryId);
    }
    @GetMapping("/{id}")
    public ResponseResult getArticleListDetil(@PathVariable("id") Long id){
        return articleService.getArticleListDetil(id);
    }
}
