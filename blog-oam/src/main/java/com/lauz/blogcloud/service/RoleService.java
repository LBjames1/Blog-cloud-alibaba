package com.lauz.blogcloud.service;

import com.lauz.blogcloud.model.BlogRole;

import java.util.List;

public interface RoleService {

    List<BlogRole> getRoleList(Integer userId);
}
