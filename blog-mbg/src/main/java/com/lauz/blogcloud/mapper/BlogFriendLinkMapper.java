package com.lauz.blogcloud.mapper;

import com.lauz.blogcloud.model.BlogFriendLink;
import com.lauz.blogcloud.model.BlogFriendLinkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogFriendLinkMapper {
    long countByExample(BlogFriendLinkExample example);

    int deleteByExample(BlogFriendLinkExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlogFriendLink record);

    int insertSelective(BlogFriendLink record);

    List<BlogFriendLink> selectByExample(BlogFriendLinkExample example);

    BlogFriendLink selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlogFriendLink record, @Param("example") BlogFriendLinkExample example);

    int updateByExample(@Param("record") BlogFriendLink record, @Param("example") BlogFriendLinkExample example);

    int updateByPrimaryKeySelective(BlogFriendLink record);

    int updateByPrimaryKey(BlogFriendLink record);
}