package com.lauz.blogcloud.mapper;

import com.lauz.blogcloud.model.BlogMessage;
import com.lauz.blogcloud.model.BlogMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogMessageMapper {
    long countByExample(BlogMessageExample example);

    int deleteByExample(BlogMessageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlogMessage record);

    int insertSelective(BlogMessage record);

    List<BlogMessage> selectByExample(BlogMessageExample example);

    BlogMessage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlogMessage record, @Param("example") BlogMessageExample example);

    int updateByExample(@Param("record") BlogMessage record, @Param("example") BlogMessageExample example);

    int updateByPrimaryKeySelective(BlogMessage record);

    int updateByPrimaryKey(BlogMessage record);
}