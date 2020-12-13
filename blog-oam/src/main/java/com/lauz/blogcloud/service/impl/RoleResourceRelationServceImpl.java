package com.lauz.blogcloud.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import com.lauz.blogcloud.common.constant.AuthConstant;
import com.lauz.blogcloud.common.service.RedisService;
import com.lauz.blogcloud.dao.RoleResourceRelationDao;
import com.lauz.blogcloud.dto.RoleResourceDTO;
import com.lauz.blogcloud.mapper.BlogResourceMapper;
import com.lauz.blogcloud.mapper.BlogRoleMapper;
import com.lauz.blogcloud.mapper.BlogRoleResourceRelationMapper;
import com.lauz.blogcloud.model.*;
import com.lauz.blogcloud.service.RoleResourceRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: Blog-cloud-alibaba
 * @description: 初始化角色资源map
 * @author: lauz
 * @create: 2020-12-12 14:15
 **/
@Service
public class RoleResourceRelationServceImpl implements RoleResourceRelationService {

    /*@Autowired
    private BlogRoleMapper roleMapper;
    @Autowired
    private BlogResourceMapper resourceMapper;
    @Autowired
    private BlogRoleResourceRelationMapper relationMapper;*/
    @Autowired
    private RedisService redisService;
    @Autowired
    private RoleResourceRelationDao relationDao;

    @Override
    public Map<String, List<String>> initRoleResourceMap() {
        Map<String,List<String>> resourceRoleMap = new TreeMap<>();
       /* List<BlogRole> roleList = roleMapper.selectByExample(new BlogRoleExample());
        List<BlogResource> resourceList = resourceMapper.selectByExample(new BlogResourceExample());
        List<BlogRoleResourceRelation> relationList = relationMapper.selectByExample(new BlogRoleResourceRelationExample());
        for (BlogResource resource : resourceList) {
            Set<Integer> roleIds = relationList.stream().filter(item -> item.getResourceId().equals(resource.getId())).map(BlogRoleResourceRelation::getRoleId).collect(Collectors.toSet());
            List<String> roleNames = roleList.stream().filter(item -> roleIds.contains(item.getId())).map(item -> AuthConstant.AUTHORITY_PREFIX+item.getId()).collect(Collectors.toList());
            resourceRoleMap.put(resource.getUrl(),roleNames);
        }*/
        List<RoleResourceDTO> relationList = relationDao.getRoleResourceRelation();
        relationList.forEach(roleResourceDTO -> {
            List<String> ids = Convert.convert(List.class,roleResourceDTO.getRoleIds().split(","));
            List<String> roles = ids.stream()
                    .map(roleId -> AuthConstant.AUTHORITY_PREFIX+roleId)
                    .collect(Collectors.toList());
            if(CollectionUtil.isNotEmpty(roles)){
                resourceRoleMap.put(roleResourceDTO.getUrl(),roles);
            }
        });
        redisService.del(AuthConstant.RESOURCE_ROLES_MAP_KEY);
        redisService.hSetAll(AuthConstant.RESOURCE_ROLES_MAP_KEY,resourceRoleMap);
        return resourceRoleMap;
    }
}
