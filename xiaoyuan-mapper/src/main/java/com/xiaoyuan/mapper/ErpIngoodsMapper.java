package com.xiaoyuan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyuan.model.ErpIngoods;
import com.xiaoyuan.tools.MapperConfig;

public interface ErpIngoodsMapper extends MapperConfig<ErpIngoods> {
	int insert(ErpIngoods ingoods);
	
	List<ErpIngoods> lists(@Param("ingoods_car_sn")String ingoods_car_sn);
	
	List<ErpIngoods> lists_statistical();
	
	ErpIngoods get(Integer id);
	
	ErpIngoods  out_lists(@Param("ingoods_car_sn")String ingoods_car_sn );
	
	ErpIngoods  in_warehouse(@Param("ingoods_sn")String ingoods_sn ,@Param("ingoods_car_sn")String ingoods_car_sn );
	
	ErpIngoods  purchase(@Param("ingoods_sn")String ingoods_sn ,@Param("ingoods_car_sn")String ingoods_car_sn );
	
	List<ErpIngoods> lists_price(@Param("supplier_id") Integer supplier_id);
	
	List<ErpIngoods> lists_audit();
	
	ErpIngoods getPriceByMessage(ErpIngoods ingoods);
}