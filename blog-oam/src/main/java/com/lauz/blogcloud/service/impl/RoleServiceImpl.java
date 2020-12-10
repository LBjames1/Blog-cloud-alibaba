package com.lauz.blogcloud.service.impl;

import com.lauz.blogcloud.dao.UserRoleRelationDao;
import com.lauz.blogcloud.mapper.BlogRoleMapper;
import com.lauz.blogcloud.model.BlogRole;
import com.lauz.blogcloud.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private UserRoleRelationDao userRoleRelationDao;
    @Autowired
    private BlogRoleMapper roleMapper;

    @Override
    public List<BlogRole> getRoleList(Integer userId) {
        return userRoleRelationDao.getRoleList(userId);
    }
}
