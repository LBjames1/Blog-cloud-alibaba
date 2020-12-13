package com.lauz.blogcloud.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @program: Blog-cloud-alibaba
 * @description: 角色资源中间类
 * @author: lauz
 * @create: 2020-12-12 22:46
 **/
@Data
@Getter
@Setter
@NoArgsConstructor
public class RoleResourceDTO {

    private String name;
    private String url;
    private String roleIds;
}
