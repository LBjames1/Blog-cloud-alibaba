package com.lauz.blogcloud.mapper;

import com.lauz.blogcloud.model.BlogUserPermissionRelation;
import com.lauz.blogcloud.model.BlogUserPermissionRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogUserPermissionRelationMapper {
    long countByExample(BlogUserPermissionRelationExample example);

    int deleteByExample(BlogUserPermissionRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlogUserPermissionRelation record);

    int insertSelective(BlogUserPermissionRelation record);

    List<BlogUserPermissionRelation> selectByExample(BlogUserPermissionRelationExample example);

    BlogUserPermissionRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlogUserPermissionRelation record, @Param("example") BlogUserPermissionRelationExample example);

    int updateByExample(@Param("record") BlogUserPermissionRelation record, @Param("example") BlogUserPermissionRelationExample example);

    int updateByPrimaryKeySelective(BlogUserPermissionRelation record);

    int updateByPrimaryKey(BlogUserPermissionRelation record);
}