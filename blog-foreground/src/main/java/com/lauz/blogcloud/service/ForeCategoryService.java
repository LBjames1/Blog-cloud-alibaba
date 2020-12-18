package com.lauz.blogcloud.service;

import com.lauz.blogcloud.dto.CategoryDTO;

import java.util.List;

/**
 * @ Author        :  lauz
 * @ CreateDate    :  2020/12/18 9:20
 */

public interface ForeCategoryService {

    List<CategoryDTO> getCateroryDtos();

    Long categoryCount();
}
