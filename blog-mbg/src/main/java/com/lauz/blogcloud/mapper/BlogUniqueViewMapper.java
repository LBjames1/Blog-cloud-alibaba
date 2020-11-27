package com.lauz.blogcloud.mapper;

import com.lauz.blogcloud.model.BlogUniqueView;
import com.lauz.blogcloud.model.BlogUniqueViewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogUniqueViewMapper {
    long countByExample(BlogUniqueViewExample example);

    int deleteByExample(BlogUniqueViewExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlogUniqueView record);

    int insertSelective(BlogUniqueView record);

    List<BlogUniqueView> selectByExample(BlogUniqueViewExample example);

    BlogUniqueView selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlogUniqueView record, @Param("example") BlogUniqueViewExample example);

    int updateByExample(@Param("record") BlogUniqueView record, @Param("example") BlogUniqueViewExample example);

    int updateByPrimaryKeySelective(BlogUniqueView record);

    int updateByPrimaryKey(BlogUniqueView record);
}