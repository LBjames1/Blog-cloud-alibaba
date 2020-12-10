package com.lauz.blogcloud.dao;

import com.lauz.blogcloud.model.BlogRole;

import java.util.List;

public interface UserRoleRelationDao {

    List<BlogRole> getRoleList(Integer userId);
}
