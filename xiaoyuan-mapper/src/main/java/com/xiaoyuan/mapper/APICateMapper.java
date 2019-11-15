package com.xiaoyuan.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.xiaoyuan.model.APICate;
import com.xiaoyuan.tools.MapperConfig;

public interface APICateMapper extends MapperConfig<APICate> {

    ArrayList<APICate> lists(@Param("condition") String condition);

    ArrayList<APICate> first_api();

}
