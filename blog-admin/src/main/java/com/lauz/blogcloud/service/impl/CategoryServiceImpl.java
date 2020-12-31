package com.lauz.blogcloud.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.lauz.blogcloud.mapper.BlogCategoryMapper;
import com.lauz.blogcloud.model.BlogCategory;
import com.lauz.blogcloud.model.BlogCategoryExample;
import com.lauz.blogcloud.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private BlogCategoryMapper categoryMapper;

    @Override
    public int save(BlogCategory category) {
        return categoryMapper.insert(category);
    }

    @Override
    public int update(BlogCategory category) {
        return categoryMapper.updateByPrimaryKey(category);
    }

    @Override
    public int delete(Integer id) {
        return categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<BlogCategory> getCategoryList(BlogCategory category, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        BlogCategoryExample example = new BlogCategoryExample();
        BlogCategoryExample.Criteria criteria = example.createCriteria();
        if(StrUtil.isNotBlank(category.getCategoryName())){
            criteria.andCategoryNameLike(category.getCategoryName());
        }
        example.setOrderByClause(category.getCreateTime().toString());
        return categoryMapper.selectByExample(example);
    }

    @Override
    public List<BlogCategory> getCategoryList(BlogCategory category) {
        return categoryMapper.selectByExample(null);
    }

    @Override
    public BlogCategory getCategory(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }
}
