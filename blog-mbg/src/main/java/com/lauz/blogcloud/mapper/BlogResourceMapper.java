package com.lauz.blogcloud.mapper;

import com.lauz.blogcloud.model.BlogResource;
import com.lauz.blogcloud.model.BlogResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogResourceMapper {
    long countByExample(BlogResourceExample example);

    int deleteByExample(BlogResourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlogResource record);

    int insertSelective(BlogResource record);

    List<BlogResource> selectByExample(BlogResourceExample example);

    BlogResource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlogResource record, @Param("example") BlogResourceExample example);

    int updateByExample(@Param("record") BlogResource record, @Param("example") BlogResourceExample example);

    int updateByPrimaryKeySelective(BlogResource record);

    int updateByPrimaryKey(BlogResource record);
}