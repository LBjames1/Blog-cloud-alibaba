package com.lauz.blogcloud.mapper;

import com.lauz.blogcloud.model.BlogUserAuth;
import com.lauz.blogcloud.model.BlogUserAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogUserAuthMapper {
    long countByExample(BlogUserAuthExample example);

    int deleteByExample(BlogUserAuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlogUserAuth record);

    int insertSelective(BlogUserAuth record);

    List<BlogUserAuth> selectByExample(BlogUserAuthExample example);

    BlogUserAuth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlogUserAuth record, @Param("example") BlogUserAuthExample example);

    int updateByExample(@Param("record") BlogUserAuth record, @Param("example") BlogUserAuthExample example);

    int updateByPrimaryKeySelective(BlogUserAuth record);

    int updateByPrimaryKey(BlogUserAuth record);
}