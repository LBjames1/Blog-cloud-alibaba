package com.lauz.blogcloud.mapper;

import com.lauz.blogcloud.model.BlogLoginLog;
import com.lauz.blogcloud.model.BlogLoginLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogLoginLogMapper {
    long countByExample(BlogLoginLogExample example);

    int deleteByExample(BlogLoginLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlogLoginLog record);

    int insertSelective(BlogLoginLog record);

    List<BlogLoginLog> selectByExample(BlogLoginLogExample example);

    BlogLoginLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlogLoginLog record, @Param("example") BlogLoginLogExample example);

    int updateByExample(@Param("record") BlogLoginLog record, @Param("example") BlogLoginLogExample example);

    int updateByPrimaryKeySelective(BlogLoginLog record);

    int updateByPrimaryKey(BlogLoginLog record);
}