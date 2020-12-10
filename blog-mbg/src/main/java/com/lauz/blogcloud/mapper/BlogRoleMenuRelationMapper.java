package com.lauz.blogcloud.mapper;

import com.lauz.blogcloud.model.BlogRoleMenuRelation;
import com.lauz.blogcloud.model.BlogRoleMenuRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogRoleMenuRelationMapper {
    long countByExample(BlogRoleMenuRelationExample example);

    int deleteByExample(BlogRoleMenuRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlogRoleMenuRelation record);

    int insertSelective(BlogRoleMenuRelation record);

    List<BlogRoleMenuRelation> selectByExample(BlogRoleMenuRelationExample example);

    BlogRoleMenuRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlogRoleMenuRelation record, @Param("example") BlogRoleMenuRelationExample example);

    int updateByExample(@Param("record") BlogRoleMenuRelation record, @Param("example") BlogRoleMenuRelationExample example);

    int updateByPrimaryKeySelective(BlogRoleMenuRelation record);

    int updateByPrimaryKey(BlogRoleMenuRelation record);
}