package com.lauz.blogcloud.mapper;

import com.lauz.blogcloud.model.BlogRolePermissionRelation;
import com.lauz.blogcloud.model.BlogRolePermissionRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogRolePermissionRelationMapper {
    long countByExample(BlogRolePermissionRelationExample example);

    int deleteByExample(BlogRolePermissionRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlogRolePermissionRelation record);

    int insertSelective(BlogRolePermissionRelation record);

    List<BlogRolePermissionRelation> selectByExample(BlogRolePermissionRelationExample example);

    BlogRolePermissionRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlogRolePermissionRelation record, @Param("example") BlogRolePermissionRelationExample example);

    int updateByExample(@Param("record") BlogRolePermissionRelation record, @Param("example") BlogRolePermissionRelationExample example);

    int updateByPrimaryKeySelective(BlogRolePermissionRelation record);

    int updateByPrimaryKey(BlogRolePermissionRelation record);
}