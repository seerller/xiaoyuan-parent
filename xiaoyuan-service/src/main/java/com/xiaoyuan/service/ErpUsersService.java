package com.xiaoyuan.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyuan.mapper.ErpUserMapper;
import com.xiaoyuan.model.ErpUser;



@Service
public class ErpUsersService implements IErpUsersService {
	@Autowired
	ErpUserMapper userMapper;

	@Override
	public int delete(Integer id) {
		int delete = userMapper.deleteByPrimaryKey(id);
		if(delete>0){
			return delete;
		}
		return 0;
	}

	@Override
	public int update(ErpUser user) {
		int update =userMapper.updateByPrimaryKeySelective(user);
		if(update>0){
			return update;
		}
		return 0;
	}

	@Override
	public int insert(ErpUser user) {
		int insert = userMapper.insert(user);
		if(insert>0){
			return insert;
		}
		return 0;
	}


	@Override
	public ErpUser get(Integer userId) {
		ErpUser state=userMapper.get(userId);
		if (state != null){
			return state;
		}
		return null;
	}

	@Override
	public List<ErpUser> lists(ErpUser userRegister) {
		List<ErpUser> list = userMapper.lists(userRegister);
		if(list.size()>0){
			return list;
		}
		return null;
	}
}
