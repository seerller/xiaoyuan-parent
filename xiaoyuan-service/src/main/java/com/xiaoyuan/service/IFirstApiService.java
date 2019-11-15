package com.xiaoyuan.service;

import com.xiaoyuan.model.FirstApi;

import java.util.ArrayList;

public interface IFirstApiService {
    /**
     * 一级菜单
     *
     * @return
     */
    ArrayList<FirstApi> select();
}
