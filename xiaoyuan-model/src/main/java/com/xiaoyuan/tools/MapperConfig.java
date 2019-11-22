package com.xiaoyuan.tools;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import static com.sun.javafx.tk.Toolkit.tk;

/**
 * 乱码恢复
 * 
 * http://www.mytju.com/classcode/tools/messycoderecover.asp
 * 
 * @author YZH
 * @version 2019年2月25日 上午9:13:42
 *
 * @param <T>
 */
public interface MapperConfig<T> extends Mapper<T>, MySqlMapper<T> {

}
