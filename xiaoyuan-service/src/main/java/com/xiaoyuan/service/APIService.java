package com.xiaoyuan.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyuan.mapper.APICateMapper;
import com.xiaoyuan.mapper.APIMapper;
import com.xiaoyuan.model.API;
import com.xiaoyuan.model.APICate;


@Service
public class APIService implements IAPIService {
    @Autowired
    private APIMapper apiMapper;

    @Autowired
    private APICateMapper apiCateMapper;

    @Override
    public ArrayList<APICate> lists(String condition) {
        // TODO Auto-generated method stub
        return apiCateMapper.lists(condition);
    }

    @Override
    public ArrayList<API> listsbycategroy(String c, Integer listsorder, String condition) {
        // TODO Auto-generated method stub
        return apiMapper.listsbycategroy(c, listsorder, condition);
    }

    @Override
    public ArrayList<APICate> first_api() {
        return this.apiCateMapper.first_api();
    }

    @Override
    public ArrayList<API> second_api() {
        return this.apiMapper.second_api();
    }

    @Override
    public ArrayList<API> third_api() {
        return this.apiMapper.third_api();
    }

}
