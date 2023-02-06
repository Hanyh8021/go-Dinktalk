package com.han.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.han.domain.ResponseResult;
import com.han.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2022-12-25 16:24:48
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);


    ResponseResult addComment(Comment comment);
}
