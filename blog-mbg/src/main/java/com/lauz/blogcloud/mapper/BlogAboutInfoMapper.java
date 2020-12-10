package com.lauz.blogcloud.mapper;

import com.lauz.blogcloud.model.BlogAboutInfo;
import com.lauz.blogcloud.model.BlogAboutInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogAboutInfoMapper {
    long countByExample(BlogAboutInfoExample example);

    int deleteByExample(BlogAboutInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlogAboutInfo record);

    int insertSelective(BlogAboutInfo record);

    List<BlogAboutInfo> selectByExampleWithBLOBs(BlogAboutInfoExample example);

    List<BlogAboutInfo> selectByExample(BlogAboutInfoExample example);

    BlogAboutInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlogAboutInfo record, @Param("example") BlogAboutInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") BlogAboutInfo record, @Param("example") BlogAboutInfoExample example);

    int updateByExample(@Param("record") BlogAboutInfo record, @Param("example") BlogAboutInfoExample example);

    int updateByPrimaryKeySelective(BlogAboutInfo record);

    int updateByPrimaryKeyWithBLOBs(BlogAboutInfo record);
}