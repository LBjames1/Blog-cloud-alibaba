package com.lauz.blogcloud.mapper;

import com.lauz.blogcloud.model.BlogWebView;
import com.lauz.blogcloud.model.BlogWebViewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogWebViewMapper {
    long countByExample(BlogWebViewExample example);

    int deleteByExample(BlogWebViewExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlogWebView record);

    int insertSelective(BlogWebView record);

    List<BlogWebView> selectByExample(BlogWebViewExample example);

    BlogWebView selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlogWebView record, @Param("example") BlogWebViewExample example);

    int updateByExample(@Param("record") BlogWebView record, @Param("example") BlogWebViewExample example);

    int updateByPrimaryKeySelective(BlogWebView record);

    int updateByPrimaryKey(BlogWebView record);
}