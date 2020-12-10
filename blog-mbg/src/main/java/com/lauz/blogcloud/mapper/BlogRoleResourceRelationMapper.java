package com.lauz.blogcloud.mapper;

import com.lauz.blogcloud.model.BlogRoleResourceRelation;
import com.lauz.blogcloud.model.BlogRoleResourceRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogRoleResourceRelationMapper {
    long countByExample(BlogRoleResourceRelationExample example);

    int deleteByExample(BlogRoleResourceRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlogRoleResourceRelation record);

    int insertSelective(BlogRoleResourceRelation record);

    List<BlogRoleResourceRelation> selectByExample(BlogRoleResourceRelationExample example);

    BlogRoleResourceRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlogRoleResourceRelation record, @Param("example") BlogRoleResourceRelationExample example);

    int updateByExample(@Param("record") BlogRoleResourceRelation record, @Param("example") BlogRoleResourceRelationExample example);

    int updateByPrimaryKeySelective(BlogRoleResourceRelation record);

    int updateByPrimaryKey(BlogRoleResourceRelation record);
}