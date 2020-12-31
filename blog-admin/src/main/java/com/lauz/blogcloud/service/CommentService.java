package com.lauz.blogcloud.service;

import com.lauz.blogcloud.model.BlogComment;

import java.util.List;

public interface CommentService {

    int delete(Integer id);

    List<BlogComment> getCommentList(BlogComment comment, Integer pageSize, Integer pageNum);
}
