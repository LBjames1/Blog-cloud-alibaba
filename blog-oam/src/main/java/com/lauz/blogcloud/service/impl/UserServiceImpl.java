package com.lauz.blogcloud.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.lauz.blogcloud.common.api.ResultCode;
import com.lauz.blogcloud.common.constant.AuthConstant;
import com.lauz.blogcloud.common.domain.UserDto;
import com.lauz.blogcloud.common.exception.Asserts;
import com.lauz.blogcloud.dao.UserRoleRelationDao;
import com.lauz.blogcloud.mapper.BlogUserMapper;
import com.lauz.blogcloud.model.BlogRole;
import com.lauz.blogcloud.model.BlogUser;
import com.lauz.blogcloud.model.BlogUserExample;
import com.lauz.blogcloud.service.UserCacheService;
import com.lauz.blogcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BlogUserMapper userMapper;
    @Autowired
    private UserRoleRelationDao userRoleRelationDao;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserCacheService userCacheService;

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

    @Override
    public BlogUser getCurrentuser() {
        String userStr = request.getHeader(AuthConstant.USER_TOKEN_HEADER);
        if(StrUtil.isEmpty(userStr)){
            Asserts.fail(ResultCode.UNAUTHORIZED);
        }
        UserDto userDto = JSONUtil.toBean(userStr, UserDto.class);
        BlogUser user = userCacheService.getUserCache(userDto.getId().intValue());
        if(user!=null){
            return user;
        }else{
            user = userMapper.selectByPrimaryKey(userDto.getId().intValue());
            userCacheService.setUserCache(user);
            return user;
        }
    }
}
