package com.lauz.blogcloud.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.lauz.blogcloud.common.constant.AuthConstant;
import com.lauz.blogcloud.common.domain.UserDto;
import com.lauz.blogcloud.dao.UserRoleRelationDao;
import com.lauz.blogcloud.mapper.BlogUserMapper;
import com.lauz.blogcloud.model.BlogRole;
import com.lauz.blogcloud.model.BlogUser;
import com.lauz.blogcloud.model.BlogUserExample;
import com.lauz.blogcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BlogUserMapper userMapper;
    @Autowired
    private UserRoleRelationDao userRoleRelationDao;

    @Override
    public BlogUser getUserByUserName(String username) {
        BlogUserExample example = new BlogUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<BlogUser> adminList = userMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }

    @Override
    public UserDto loadUserByUsername(String username) {
        BlogUser user = getUserByUserName(username);
        if (user != null) {
            List<BlogRole> roleList = userRoleRelationDao.getRoleList(user.getId());
            UserDto userDTO = new UserDto();
            BeanUtil.copyProperties(user,userDTO);
            if(CollUtil.isNotEmpty(roleList)){
                List<String> roleStrList = roleList.stream().map(item -> AuthConstant.AUTHORITY_PREFIX+item.getId()).collect(Collectors.toList());
                userDTO.setRoles(roleStrList);
            }
            return userDTO;
        }
        return null;
    }
}
