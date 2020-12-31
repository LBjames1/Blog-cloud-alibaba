package com.lauz.blogcloud.service.impl;

import com.lauz.blogcloud.mapper.BlogAboutInfoMapper;
import com.lauz.blogcloud.model.BlogAboutInfo;
import com.lauz.blogcloud.service.AboutMeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AboutMeServiceImpl implements AboutMeService {

    @Autowired
    private BlogAboutInfoMapper aboutInfoMapper;

    @Override
    public int add(BlogAboutInfo aboutInfo) {
        return aboutInfoMapper.insert(aboutInfo);
    }

    @Override
    public int update(BlogAboutInfo aboutInfo) {
        return aboutInfoMapper.updateByPrimaryKeySelective(aboutInfo);
    }

    @Override
    public int delete(Integer id) {
        return aboutInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public String getAboutInfo() {
        BlogAboutInfo aboutInfo = aboutInfoMapper.selectByExample(null).get(0);
        if(aboutInfo!=null){
            return aboutInfo.getAboutContent();
        }else{
            return null;
        }
    }
}
