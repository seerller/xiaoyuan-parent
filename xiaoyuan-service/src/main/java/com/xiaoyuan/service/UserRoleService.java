package com.xiaoyuan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyuan.mapper.ErpRoleMapper;
import com.xiaoyuan.mapper.UserRoleMapper;
import com.xiaoyuan.model.ErpRole;
import com.xiaoyuan.model.UserRole;

@Service
public class UserRoleService implements IUserRoleService {
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Autowired
	private ErpRoleMapper erpRoleMapper;
	@Override
	public int add(UserRole userRole) {
		// TODO Auto-generated method stub
//		return userRoleMapper.insert(userRole);
		return this.userRoleMapper.add(userRole);
	}

	@Override
	public UserRole role_auth_ids(UserRole role) {
		// TODO Auto-generated method stub
		return userRoleMapper.role_auth_ids(role);
	}

	@Override
	public int update(UserRole userRole) {
		// TODO Auto-generated method stub
		return userRoleMapper.update(userRole);
	}

	

	@Override
	public UserRole role(Integer id) {
		// TODO Auto-generated method stub
		return userRoleMapper.role(id);
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return userRoleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public UserRole role_auth_id(Integer seller_id) {
		// TODO Auto-generated method stub
		return userRoleMapper.role_auth_id(seller_id);
	}

	@Override
	public List<UserRole>  userroles() {
		// TODO Auto-generated method stub
		
		//YZH 这里修改一下查询的表
		return this.userRoleMapper.userroles();
//		return erpRoleMapper.lists();
	}

	@Override
	public int addRole(ErpRole role) {
		// TODO Auto-generated method stub
		return erpRoleMapper.insert(role);
	}

	@Override
	public ErpRole getRole(String role) {
		// TODO Auto-generated method stub
		return erpRoleMapper.getRole(role);
	}

	@Override
	public int getId(String role_name) {
			int result =erpRoleMapper.getId(role_name);
			if(result>0){
				return  result; 
			}else{
				return  0;//出现问题了59
			}
	
	}

}
