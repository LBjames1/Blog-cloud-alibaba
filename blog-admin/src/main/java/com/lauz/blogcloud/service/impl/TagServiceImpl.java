package com.lauz.blogcloud.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.lauz.blogcloud.mapper.BlogTagMapper;
import com.lauz.blogcloud.model.BlogTag;
import com.lauz.blogcloud.model.BlogTagExample;
import com.lauz.blogcloud.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private BlogTagMapper tagMapper;

    @Override
    public int save(BlogTag tag) {
        return tagMapper.insert(tag);
    }

    @Override
    public int update(BlogTag tag) {
        return tagMapper.updateByPrimaryKey(tag);
    }

    @Override
    public int delete(Integer id) {
        return tagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<BlogTag> getTagList(BlogTag tag, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        BlogTagExample example = new BlogTagExample();
        BlogTagExample.Criteria criteria = example.createCriteria();
        if(StrUtil.isNotBlank(tag.getTagName())){
            criteria.andTagNameLike(tag.getTagName());
        }
        example.setOrderByClause(tag.getCreateTime().toString());
        return tagMapper.selectByExample(example);
    }

    @Override
    public List<BlogTag> getTagList() {
        return tagMapper.selectByExample(null);
    }

    @Override
    public BlogTag getTag(Integer id) {
        return tagMapper.selectByPrimaryKey(id);
    }
}
