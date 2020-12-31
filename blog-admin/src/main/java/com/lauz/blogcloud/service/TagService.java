package com.lauz.blogcloud.service;

import com.lauz.blogcloud.model.BlogTag;

import java.util.List;

public interface TagService {

    int save(BlogTag tag);

    int update(BlogTag tag);

    int delete(Integer id);

    List<BlogTag> getTagList(BlogTag tag, Integer pageSize, Integer pageNum);

    List<BlogTag> getTagList();

    BlogTag getTag(Integer id);
}
