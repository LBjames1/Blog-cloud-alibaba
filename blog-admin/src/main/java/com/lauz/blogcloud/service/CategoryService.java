package com.lauz.blogcloud.service;

import com.lauz.blogcloud.model.BlogCategory;

import java.util.List;

public interface CategoryService {

    int save(BlogCategory category);

    int update(BlogCategory category);

    int delete(Integer id);

    List<BlogCategory> getCategoryList(BlogCategory category, Integer pageSize, Integer pageNum);

    List<BlogCategory> getCategoryList(BlogCategory category);

    BlogCategory getCategory(Integer id);
}
