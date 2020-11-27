package com.lauz.blogcloud.mapper;

import com.lauz.blogcloud.model.BlogBlogInfo;
import com.lauz.blogcloud.model.BlogBlogInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogBlogInfoMapper {
    long countByExample(BlogBlogInfoExample example);

    int deleteByExample(BlogBlogInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlogBlogInfo record);

    int insertSelective(BlogBlogInfo record);

    List<BlogBlogInfo> selectByExampleWithBLOBs(BlogBlogInfoExample example);

    List<BlogBlogInfo> selectByExample(BlogBlogInfoExample example);

    BlogBlogInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlogBlogInfo record, @Param("example") BlogBlogInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") BlogBlogInfo record, @Param("example") BlogBlogInfoExample example);

    int updateByExample(@Param("record") BlogBlogInfo record, @Param("example") BlogBlogInfoExample example);

    int updateByPrimaryKeySelective(BlogBlogInfo record);

    int updateByPrimaryKeyWithBLOBs(BlogBlogInfo record);
}