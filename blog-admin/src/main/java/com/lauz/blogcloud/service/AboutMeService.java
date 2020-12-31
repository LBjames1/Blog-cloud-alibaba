package com.lauz.blogcloud.service;

import com.lauz.blogcloud.model.BlogAboutInfo;

public interface AboutMeService {

    int add(BlogAboutInfo aboutInfo);

    int update(BlogAboutInfo aboutInfo);

    int delete(Integer id);

    String getAboutInfo();
}
