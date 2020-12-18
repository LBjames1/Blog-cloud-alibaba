package com.lauz.blogcloud.service;

import com.lauz.blogcloud.dto.TagDTO;

import java.util.List;

/**
 * @ Author        :  lauz
 * @ CreateDate    :  2020/12/18 9:20
 */

public interface ForeTagService {

    List<TagDTO> getTagDtos();

    Long tagCount();
}
