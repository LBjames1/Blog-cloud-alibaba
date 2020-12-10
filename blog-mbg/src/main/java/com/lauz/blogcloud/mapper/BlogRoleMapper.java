package com.lauz.blogcloud.mapper;

import com.lauz.blogcloud.model.BlogRole;
import com.lauz.blogcloud.model.BlogRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogRoleMapper {
    long countByExample(BlogRoleExample example);

    int deleteByExample(BlogRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlogRole record);

    int insertSelective(BlogRole record);

    List<BlogRole> selectByExample(BlogRoleExample example);

    BlogRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlogRole record, @Param("example") BlogRoleExample example);

    int updateByExample(@Param("record") BlogRole record, @Param("example") BlogRoleExample example);

    int updateByPrimaryKeySelective(BlogRole record);

    int updateByPrimaryKey(BlogRole record);
}