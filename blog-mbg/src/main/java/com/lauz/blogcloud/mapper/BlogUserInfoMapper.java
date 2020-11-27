package com.lauz.blogcloud.mapper;

import com.lauz.blogcloud.model.BlogUserInfo;
import com.lauz.blogcloud.model.BlogUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogUserInfoMapper {
    long countByExample(BlogUserInfoExample example);

    int deleteByExample(BlogUserInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlogUserInfo record);

    int insertSelective(BlogUserInfo record);

    List<BlogUserInfo> selectByExample(BlogUserInfoExample example);

    BlogUserInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlogUserInfo record, @Param("example") BlogUserInfoExample example);

    int updateByExample(@Param("record") BlogUserInfo record, @Param("example") BlogUserInfoExample example);

    int updateByPrimaryKeySelective(BlogUserInfo record);

    int updateByPrimaryKey(BlogUserInfo record);
}