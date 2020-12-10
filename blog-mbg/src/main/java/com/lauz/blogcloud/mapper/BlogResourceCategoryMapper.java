package com.lauz.blogcloud.mapper;

import com.lauz.blogcloud.model.BlogResourceCategory;
import com.lauz.blogcloud.model.BlogResourceCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogResourceCategoryMapper {
    long countByExample(BlogResourceCategoryExample example);

    int deleteByExample(BlogResourceCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlogResourceCategory record);

    int insertSelective(BlogResourceCategory record);

    List<BlogResourceCategory> selectByExample(BlogResourceCategoryExample example);

    BlogResourceCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlogResourceCategory record, @Param("example") BlogResourceCategoryExample example);

    int updateByExample(@Param("record") BlogResourceCategory record, @Param("example") BlogResourceCategoryExample example);

    int updateByPrimaryKeySelective(BlogResourceCategory record);

    int updateByPrimaryKey(BlogResourceCategory record);
}