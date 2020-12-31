package com.lauz.blogcloud.service.impl;

import com.github.pagehelper.PageHelper;
import com.lauz.blogcloud.mapper.BlogCommentMapper;
import com.lauz.blogcloud.model.BlogComment;
import com.lauz.blogcloud.model.BlogCommentExample;
import com.lauz.blogcloud.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private BlogCommentMapper commentMapper;

    @Override
    public int delete(Integer id) {
        return commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<BlogComment> getCommentList(BlogComment comment, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        BlogCommentExample example = new BlogCommentExample();
        BlogCommentExample.Criteria criteria = example.createCriteria();
        if(comment.getArticleId()!=null){
            criteria.andArticleIdEqualTo(comment.getArticleId());
        }
        return commentMapper.selectByExample(example);
    }
}
