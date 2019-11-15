package com.xiaoyuan.mapper;

import java.util.List;
//import org.junit.runners.Parameterized.Parameter;
import com.xiaoyuan.model.ErpRole;
//import com.yuanqu.model.UserRole;
import com.xiaoyuan.tools.MapperConfig;

/**
 * 
 * 这个表不用的话，这个service就废了
 * 
 * @author YZH
 * @version 2019年2月25日 下午4:30:36
 *
 */
public interface ErpRoleMapper extends MapperConfig<ErpRole> {
	int insert(ErpRole role);

	ErpRole getRole(String role);

	List<ErpRole> lists();

	int getId(String role_name);
}
