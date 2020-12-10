package com.lauz.blogcloud.mapper;

import com.lauz.blogcloud.model.BlogPermission;
import com.lauz.blogcloud.model.BlogPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogPermissionMapper {
    long countByExample(BlogPermissionExample example);

    int deleteByExample(BlogPermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlogPermission record);

    int insertSelective(BlogPermission record);

    List<BlogPermission> selectByExample(BlogPermissionExample example);

    BlogPermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlogPermission record, @Param("example") BlogPermissionExample example);

    int updateByExample(@Param("record") BlogPermission record, @Param("example") BlogPermissionExample example);

    int updateByPrimaryKeySelective(BlogPermission record);

    int updateByPrimaryKey(BlogPermission record);
}