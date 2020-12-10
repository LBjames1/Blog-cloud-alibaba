package com.lauz.blogcloud.mapper;

import com.lauz.blogcloud.model.BlogUserRoleRelation;
import com.lauz.blogcloud.model.BlogUserRoleRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogUserRoleRelationMapper {
    long countByExample(BlogUserRoleRelationExample example);

    int deleteByExample(BlogUserRoleRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlogUserRoleRelation record);

    int insertSelective(BlogUserRoleRelation record);

    List<BlogUserRoleRelation> selectByExample(BlogUserRoleRelationExample example);

    BlogUserRoleRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlogUserRoleRelation record, @Param("example") BlogUserRoleRelationExample example);

    int updateByExample(@Param("record") BlogUserRoleRelation record, @Param("example") BlogUserRoleRelationExample example);

    int updateByPrimaryKeySelective(BlogUserRoleRelation record);

    int updateByPrimaryKey(BlogUserRoleRelation record);
}