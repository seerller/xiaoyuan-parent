package com.xiaoyuan.service;

import com.xiaoyuan.mapper.FirstApiMapper;
import com.xiaoyuan.model.FirstApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FirstApiService implements IFirstApiService {

    @Autowired
    FirstApiMapper firstApiMapper;

    @Override
    public ArrayList<FirstApi> select() {
        return this.firstApiMapper.select();
    }
}
