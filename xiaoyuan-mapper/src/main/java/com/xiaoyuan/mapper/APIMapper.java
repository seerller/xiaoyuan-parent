package com.xiaoyuan.mapper;

import com.xiaoyuan.model.API;
import com.xiaoyuan.tools.MapperConfig;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface APIMapper extends MapperConfig<API> {

    ArrayList<API> listsbycategroy(@Param("c") String c, @Param("listsorder") Integer listsorder,
                                   @Param("condition") String condition);

    ArrayList<API> second_api();

    ArrayList<API> third_api();
}
