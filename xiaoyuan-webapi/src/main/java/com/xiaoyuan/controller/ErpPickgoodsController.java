package com.xiaoyuan.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.xiaoyuan.controller.CommonController.BaseController;
import com.xiaoyuan.model.*;
import com.xiaoyuan.service.IErpRegionSerivce;
import com.xiaoyuan.tools.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyuan.service.IErpCarOrderService;
import com.xiaoyuan.service.IErpPickgoodsService;
import com.xiaoyuan.service.IErpUserService;
import com.xiaoyuan.tools.MessageBean;

/**
 * pick 提，pick goods 提货，仓库提货，所以是自提和园区配送
 *
 * @author YZH
 * @version 2019年2月27日 下午7:34:54
 */
@CrossOrigin
@RestController
@RequestMapping("/ErpPickgoods")
public class ErpPickgoodsController extends BaseController {
    @Autowired
    private IErpPickgoodsService pickgoodsService;
    @Autowired
    private IErpUserService userService;
    @Autowired
    private IErpCarOrderService carorderService;
    @Autowired
    private IErpRegionSerivce erpRegionSerivce;

    /**
     * @param token
     * @return 2019年2月27日上午10:58:19
     * @author : YZH
     */
    @RequestMapping("/lists")
    public MessageBean lists(
            String token,
            Integer status_,
            Integer is_car_order,
            Integer is_end,
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize,
            String date1,
            String date2,
            String customer_name,
            String pickgoods_send_company,
            String pickgoods_car_sn) {


        //20190401 Mon. 11:55  客户和园区才能看提货订单，客户查看自己的，

        ErpUser detail = userService.detail(this.getUserId(token));
        if (detail == null) {
            return new MessageBean(10000, "token找不到用户", detail);
        }
        if (detail.getRole_id() == 4) {//司机看自己
            String driver_phone = detail.getUser_phone();
            PageHelper.startPage(currentPage, pageSize);
            List<ErpPickgoods> list = pickgoodsService.lists(driver_phone, is_car_order, is_end, null, status_, date1, date2, customer_name, pickgoods_send_company, pickgoods_car_sn);//20190321查全部
            if (list != null) {
                int countNums = list.size();//总记录数
                PageBean<ErpPickgoods> pageData = new PageBean<>(currentPage, pageSize, countNums);
                pageData.setItems(list);
                return new MessageBean(10001, "", pageData.getItems());
            }
            return new MessageBean(10000, "司机查看审核列表为空", list);
        }
        //清明节快乐喔，20190405 Fri. 11:31 除了客户其他有这个菜单都能审核？
        if (detail.getRole_id() != 1) {// YZH 3 园区
            PageHelper.startPage(currentPage, pageSize);
            List<ErpPickgoods> list = pickgoodsService.lists(null, is_car_order, is_end, null, status_, date1, date2, customer_name, pickgoods_send_company, pickgoods_car_sn);//20190321查全部
            if (list != null) {
                int countNums = list.size();//总记录数
                PageBean<ErpPickgoods> pageData = new PageBean<>(currentPage, pageSize, countNums);
                pageData.setItems(list);
                return new MessageBean(10001, "", pageData.getItems());
            }
            return new MessageBean(10000, "非客户查看审核列表为空", list);
        }
        if (detail.getRole_id() == (1)) {// YZH 1 客户
            PageHelper.startPage(currentPage, pageSize);
            List<ErpPickgoods> list = pickgoodsService.lists(null, is_car_order, is_end, this.getUserId(token), status_, date1, date2, customer_name, pickgoods_send_company, pickgoods_car_sn);//20190321查全部
            if (list != null) {
                int countNums = list.size();//总记录数
                PageBean<ErpPickgoods> pageData = new PageBean<>(currentPage, pageSize, countNums);
                pageData.setItems(list);
                return new MessageBean(10001, "", pageData.getItems());
            }
            return new MessageBean(10000, "客户查看审核列表为空", list);
        }
        return new MessageBean(10000, "role_id为空", null);
    }

    /**
     * 进货申请的提交按钮
     *
     * @param id
     * @param order_cate
     * @param erpPickgoods
     * @param province_id
     * @param city_id
     * @param area_id
     * @param street_id
     * @param user_address
     * @param customer_name
     * @param goods_name
     * @param goods_sn
     * @param supplier_name
     * @param token
     * @param id
     * @param order_cate
     * @param erpPickgoods
     * @param province_id
     * @param city_id
     * @param area_id
     * @param street_id
     * @param user_address
     * @param customer_name
     * @param goods_name
     * @param goods_sn
     * @param supplier_name
     * @param token
     * @return 2019年3月2日上午11:29:30
     * @author : YZH
     * <p>
     * 这是提货申请的提交按钮，之前前端搞错请求地址了
     * <p>
     * 园区配送司机可以接单，自提接单失败，查数据表返回车单对象空，传id自增，
     * @author : YZH
     */
    @RequestMapping("/add")
    public MessageBean add(
            String driver_phone,
            String token,
            Double pickgoods_price,//前端定的，客户单价
            Double total_price,
            @RequestParam(value = "province_id", required = false) Integer province_id,
            @RequestParam(value = "city_id", required = false) Integer city_id,
            @RequestParam(value = "area_id", required = false) Integer area_id,
            @RequestParam(value = "street_id", required = false) Integer street_id,
            @RequestParam(value = "goods_name", required = false) String goods_name,
            @RequestParam(value = "goods_sn", required = false) String goods_sn,
            String supplier_spec,
            String user_company_name,
            Double pickgoods_num,
            String pickgoods_man,
            String pickgoods_head,
            @RequestParam(value = "user_address", required = false) String user_address,
            @RequestParam(value = "customer_name", required = false) String customer_name,
            String pickgoods_date,
            Integer pickgoods_sendcate,
            String pickgoods_car_sn,
            String pickgoods_send_company,
            String piickgoods_remark,
            @RequestParam(value = "id", required = false) Integer id,
            String order_cate,
            ErpPickgoods erpPickgoods,
            @RequestParam(value = "supplier_name", required = false) String supplier_name) {


        // =====================================================================
        // 在这里判断余额
        // 没有价格？。。
        // 园区是卖水泥的，客户单价买水泥，园区进货，供应商，矿粉，沙子，，
        Integer userId = this.userService.getUserIdByToken(token);
        if (userId == null) {
            return new MessageBean(10000, "根据token找不到user_id", null);
            // 做的事情不止这个
        }
        ErpUser user2 = this.userService.detail(userId);
        if (user2 == null) {
            return new MessageBean(10000, "根据token找不到user对象", null);
            // 做的事情不止这个
        }
        //20190321 判断金额再高五万
        double total_price2 = user2.getUser_price() * erpPickgoods.getPickgoods_num();//提货总价，之后要用
        if ((user2.getRebate_balance() - 50000) < total_price2) {
            return new MessageBean(10000, "余额不足", null);
        }
        // =====================================================================
        ErpPickgoods pickgoods = new ErpPickgoods();
        ErpCarOrder carorder = new ErpCarOrder();
        int result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        order_cate = "销售";
        if ("销售".equals(order_cate)) {//
            //20190410 Wed. 20:38
            //如果id不为空，先做一步删除
            if (id != null) {
                this.pickgoodsService.delete(id);
            }


//            if (id == null) {// id为空表示新增
            //20190326 增加订单
            ErpUser user = userService.detail(this.getUserId(token));
            pickgoods.setUser_id(this.getUserId(token));//客户的订单
            pickgoods.setOrder_cate("销售");//提货订单
            //====
//                pickgoods.setPickgoods_man(pickgoods_man);
//                pickgoods.setPickgoods_head(pickgoods_head);
            pickgoods.setPickgoods_date(pickgoods_date);
            pickgoods.setPickgoods_sendcate(pickgoods_sendcate);
//                pickgoods.setPickgoods_send_company(pickgoods_send_company);
//                pickgoods.setPickgoods_remark(piickgoods_remark);
//                pickgoods.setPickgoods_car_sn(pickgoods_car_sn);
            pickgoods.setIngoods_price(pickgoods_price);
            //====
            pickgoods.setGoods_name(goods_name);
            pickgoods.setGoods_sn(goods_sn);
            pickgoods.setUser_address(erpPickgoods.getUser_address());
            pickgoods.setSupplier_spec(erpPickgoods.getSupplier_spec());
            pickgoods.setPickgoods_num(erpPickgoods.getPickgoods_num());
            pickgoods.setPickgoods_man(erpPickgoods.getPickgoods_man());
            pickgoods.setPickgoods_head(erpPickgoods.getPickgoods_head());
//                pickgoods.setPickgoods_date(sdf.format(new Date()));// 仓哥
            //价格
            pickgoods.setIngoods_totalprice(total_price);
            // 改完这条跑一下
            // sdf = new SimpleDateFormat("HH:mm:ss");
            // pickgoods.setPickgoods_date(erpPickgoods.getPickgoods_date()
            // + " " + sdf.format(new Date()));
            // 1.自提 2.园区配送
            pickgoods.setPickgoods_sendcate(erpPickgoods.getPickgoods_sendcate());
            //
            pickgoods.setCar_order_sn(order_sn(user.getId().toString()));
//                if (erpPickgoods.getPickgoods_sendcate() == 1) {//20190321 司机自提失败
            pickgoods.setPickgoods_car_sn(erpPickgoods.getPickgoods_car_sn());
            pickgoods.setPickgoods_send_company(erpPickgoods.getPickgoods_send_company());
            pickgoods.setPickgoods_out(erpPickgoods.getPickgoods_out());
            pickgoods.setPickgoods_remark(erpPickgoods.getPickgoods_remark());
            carorder.setCar_order_address(erpPickgoods.getUser_address());
            sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            pickgoods.setCreatetime(sdf.format(new Date()));
            pickgoods.setStatus_(0);
            pickgoods.setIngoods_price(user.getUser_price());//单价
            pickgoods.setProvince_id(province_id);//TO-DO 20190328 直接用传参对象insert就好了。。。
            pickgoods.setCity_id(city_id);
            pickgoods.setArea_id(area_id);
            pickgoods.setCustomer_name(customer_name);
            //TODO 20190403 字符串地址 这里可以合成一步走，
            ErpRegion erpRegion = new ErpRegion();
            erpRegion.setId(province_id);
            String province_txt = this.erpRegionSerivce.get_address(erpRegion).getTitle();
            erpRegion.setId(city_id);
            String city_txt = this.erpRegionSerivce.get_address(erpRegion).getTitle();
            erpRegion.setId(area_id);
            String area_txt = this.erpRegionSerivce.get_address(erpRegion).getTitle();
            pickgoods.setProvince_txt(province_txt);
            pickgoods.setCity_txt(city_txt);
            pickgoods.setArea_txt(area_txt);
            pickgoods.setDriver_phone(driver_phone);
            result = pickgoodsService.insert(pickgoods);
//                }
            if (result == 1) {
                //增加车单   20190326
                ErpCarOrder order = new ErpCarOrder();
                order.setCar_order_sn(pickgoods.getCar_order_sn());
                order.setCar_order_num(pickgoods_num);
                order.setCreatetime(sdf.format(new Date()));
                order.setCar_order_address(user_address);
                order.setPickgoods_id(pickgoods.getId());
                int car_order_result = carorderService.insert(order);
                if (car_order_result == 1) {
                    double balance = user.getRebate_balance();
                    user.setRebate_balance(balance - total_price);
                    int user_update_result = this.userService.update(user);
                    if (user_update_result == 1) {
                        //  return new MessageBean(10000, "扣费失败",user_update_result);
                        return new MessageBean(10001, "提货申请成功", erpPickgoods.getCar_order_sn());
                    }
                    return new MessageBean(10000, "扣费失败", user_update_result);
                }
                return new MessageBean(10000, "增加车单失败", car_order_result);
            }
            return new MessageBean(10000, "增加订单失败", result);
//            } else {
//                //修改订单，update操作  20190327 TO-DO
////                pickgoods.setPickgoods_send_price(erpPickgoods.getPickgoods_send_price());
////                pickgoods.setUpdatetime(sdf.format(new Date()));
////                pickgoods.setId(id);
//                //TODO 20190402 重新提交要扣费，重新提交后需要增加车单？直接删除老订单，增加新订单？
//                //TODO 客户提货申请，扣费，增加订单，增加车单，园区拒绝，退款，删除车单，司机拒绝，退款，删除车单，客户重新提交，扣费，增加车单
//                //TODO ===========================
//                ErpUser user = userService.detail(this.getUserId(token));
//                pickgoods.setUser_id(this.getUserId(token));//客户的订单
//                pickgoods.setOrder_cate("销售");//提货订单
//                //====
////                pickgoods.setPickgoods_man(pickgoods_man);
////                pickgoods.setPickgoods_head(pickgoods_head);
//                pickgoods.setPickgoods_date(pickgoods_date);
//                pickgoods.setPickgoods_sendcate(pickgoods_sendcate);
////                pickgoods.setPickgoods_send_company(pickgoods_send_company);
////                pickgoods.setPickgoods_remark(piickgoods_remark);
////                pickgoods.setPickgoods_car_sn(pickgoods_car_sn);
//                pickgoods.setIngoods_price(pickgoods_price);
//                //====
//                pickgoods.setGoods_name(goods_name);
//                pickgoods.setGoods_sn(goods_sn);
//                pickgoods.setUser_address(erpPickgoods.getUser_address());
//                pickgoods.setSupplier_spec(erpPickgoods.getSupplier_spec());
//                pickgoods.setPickgoods_num(erpPickgoods.getPickgoods_num());
//                pickgoods.setPickgoods_man(erpPickgoods.getPickgoods_man());
//                pickgoods.setPickgoods_head(erpPickgoods.getPickgoods_head());
////                pickgoods.setPickgoods_date(sdf.format(new Date()));// 仓哥
//                //价格
//                pickgoods.setIngoods_totalprice(total_price);
//                // 改完这条跑一下
//                // sdf = new SimpleDateFormat("HH:mm:ss");
//                // pickgoods.setPickgoods_date(erpPickgoods.getPickgoods_date()
//                // + " " + sdf.format(new Date()));
//                // 1.自提 2.园区配送
//                pickgoods.setPickgoods_sendcate(erpPickgoods.getPickgoods_sendcate());
//                //
////                pickgoods.setCar_order_sn(order_sn());
////                if (erpPickgoods.getPickgoods_sendcate() == 1) {//20190321 司机自提失败
//                pickgoods.setPickgoods_car_sn(erpPickgoods.getPickgoods_car_sn());
//                pickgoods.setPickgoods_send_company(erpPickgoods.getPickgoods_send_company());
//                pickgoods.setPickgoods_out(erpPickgoods.getPickgoods_out());
//                pickgoods.setPickgoods_remark(erpPickgoods.getPickgoods_remark());
//                carorder.setCar_order_address(erpPickgoods.getUser_address());
//                sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
//                pickgoods.setUpdatetime(sdf.format(new Date()));
//                pickgoods.setStatus_(0);
//                pickgoods.setIngoods_price(user.getUser_price());//单价
//                pickgoods.setProvince_id(province_id);//TO-DO 20190328 直接用传参对象insert就好了。。。
//                pickgoods.setCity_id(city_id);
//                pickgoods.setArea_id(area_id);
//                pickgoods.setCustomer_name(customer_name);
//                pickgoods.setId(id);
//                //TODO 20190403 字符串地址 这里可以合成一步走，
//                ErpRegion erpRegion = new ErpRegion();
//                erpRegion.setId(province_id);
//                String province_txt = this.erpRegionSerivce.get_address(erpRegion).getTitle();
//                erpRegion.setId(city_id);
//                String city_txt = this.erpRegionSerivce.get_address(erpRegion).getTitle();
//                erpRegion.setId(area_id);
//                String area_txt = this.erpRegionSerivce.get_address(erpRegion).getTitle();
//                pickgoods.setProvince_txt(province_txt);
//                pickgoods.setCity_txt(city_txt);
//                pickgoods.setArea_txt(area_txt);
//                //20190408 Mon. 11:07 重新提交，状态恢复
//                pickgoods.setIs_car_order(0);
//                result = pickgoodsService.updateById(pickgoods);
////                }
//                if (result == 1) {
//                    //增加车单   20190326
//                    ErpCarOrder order = new ErpCarOrder();
//                    order.setCar_order_sn(pickgoods.getCar_order_sn());
//                    order.setCar_order_num(pickgoods_num);
//                    order.setCreatetime(sdf.format(new Date()));
//                    order.setCar_order_address(user_address);
//                    order.setPickgoods_id(pickgoods.getId());
//                    int car_order_result = carorderService.insert(order);
//                    if (car_order_result == 1) {
//                        double balance = user.getRebate_balance();
//                        user.setRebate_balance(balance - total_price);
//                        int user_update_result = this.userService.update(user);
//                        if (user_update_result == 1) {
//                            //  return new MessageBean(10000, "扣费失败",user_update_result);
//                            return new MessageBean(10001, "提货申请成功", user_update_result);
//                        }
//                        return new MessageBean(10000, "扣费失败", user_update_result);
//                    }
//                    return new MessageBean(10000, "增加车单失败", car_order_result);
//                }
//                return new MessageBean(10000, "增加订单失败", result);
//                //TODO ===========================
////                erpPickgoods.setStatus_(0);//恢复
////                result = pickgoodsService.update(pickgoods);
////                result = pickgoodsService.update(erpPickgoods);//TO-DO 20190328 unkonwn column user_company_name
////                System.out.println("result: " + result);
////                if (result == 0) {
////                if (result == 1) {
////                    return new MessageBean(10001, "修改订单成功", result);
////                }
////                return new MessageBean(10000, "修改订单失败", result);
//            }
        }
        return new MessageBean(10000, "订单类型不是销售", order_cate);
    }

    /**
     * 根据前端请求的地址新增的方法，根据ID返回supplier对象
     *
     * @param id
     * @return 2019年2月25日下午8:06:02
     * @author : YZH
     */
    @RequestMapping("/getpricebyingoods")
    public MessageBean getpricebyingoods(@RequestParam Integer id) {
        if (id != null) {
            ErpPickgoods pickgoods = pickgoodsService.get(id);

            if (pickgoods != null) {
                return new MessageBean(10001, "", pickgoods);
            }
        }
        return new MessageBean(10000, "没有找到此信息", null);
    }

    /**
     * @param id
     * @return 2019年2月25日下午8:06:02
     * @author : YZH
     */
    @RequestMapping("/detail")
    public MessageBean detail(@RequestParam Integer id) {
        if (id != null) {
            ErpPickgoods pickgoods = pickgoodsService.get(id);
            if (pickgoods != null) {
                return new MessageBean(10001, "", pickgoods);
            }
        }
        return new MessageBean(10000, "没有找到此信息", null);
    }

    @RequestMapping("/delete")
    public MessageBean delete(@RequestParam Integer id) {
        if (id != null) {
            int result = pickgoodsService.delete(id);
            if (result > 0) {
                return new MessageBean(10001, "撤销成功", null);
            }
        }
        return new MessageBean(10000, "撤销异常", null);
    }

    @RequestMapping("/audit")
    public MessageBean audit(
            String token,
            @RequestParam Integer id,
            ErpPickgoods pickgoods) {
        if (id != null) {
            //不直接用了，20190402
//            pickgoods.setStatus_(1);
//            pickgoods.setId(id);
            ErpUser detail = this.userService.detail(this.getUserId(token));
            if (detail == null) {
                return new MessageBean(10000, "根据token找不到user", token);
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
            ErpPickgoods erpPickgoods = new ErpPickgoods();
            erpPickgoods.setId(id);
            erpPickgoods.setStatus_(1);
            erpPickgoods.setAudit_datetime(simpleDateFormat.format(new Date()));
            erpPickgoods.setAudit_man(detail.getUser_name());//暂时放账号 20190413
            int result = pickgoodsService.update(erpPickgoods);
            if (result == 1) {
                //返回手机号
                ErpPickgoods erpPickgoods1 = this.pickgoodsService.get(id);
                ErpUser erpUser = this.userService.detail(erpPickgoods1.getUser_id());
                String driver_phone = erpPickgoods1.getDriver_phone();
                Map<String, String> map = new LinkedHashMap<>();
                map.put("customer_phone", erpUser.getUser_phone());
                map.put("driver_phone", driver_phone);
                map.put("car_order_sn", erpPickgoods1.getCar_order_sn());
                return new MessageBean(10001, "审核通过", map);
            }
            return new MessageBean(10000, "操作失败", result);
        }
        return new MessageBean(10000, "id为空", null);

    }

    //TODO 20190402
    //园区加删单，车单加退款，状态不一样，其他一样，都是退款加删除车单，重新提交和提交都要扣费和加车单，不过重新提交是修改订单
    @RequestMapping("/unaudit")
    public MessageBean unaudit(@RequestParam Integer id, ErpPickgoods pickgoods) {
        if (id != null) {
            ErpPickgoods erpPickgoods = this.pickgoodsService.get(id);
            //20190402 Tue. 14:25 判断状态，避免重复拒绝导致重复退款
            if (erpPickgoods.getStatus_() == null) {
                erpPickgoods.setStatus_(0);
            }
            if (erpPickgoods.getStatus_() == -1) {
                return new MessageBean(10000, "该订单已拒绝！！", erpPickgoods);
            }
            if (erpPickgoods.getStatus_() == 1) {
                return new MessageBean(10000, "该订单已审核通过！！！", erpPickgoods);
            }
            //删除车单
            String car_order_sn = pickgoods.getCar_order_sn();
            ErpCarOrder erpCarOrder = new ErpCarOrder();
            erpCarOrder.setCar_order_sn(car_order_sn);
            erpCarOrder.setPickgoods_id(pickgoods.getId());
            ErpCarOrder carOrder = this.carorderService.getCarOrder(erpCarOrder);
            if (carOrder != null) {
                if (carOrder.getId() != null) {
                    int carOrder_result = this.carorderService.delete(carOrder.getId());
                    //20190405 冲冲冲
                    if (carOrder_result == 0) {
                        return new MessageBean(10000, "删除车单失败", carOrder_result);
                    }
                } else {
                    return new MessageBean(10000, "车单id为空", carOrder);
                }
            } else {
                return new MessageBean(10000, "根据车单号找不到车单对象", erpCarOrder);
            }

            //20190402 不直接用了
//            pickgoods.setStatus_(-1);
//            pickgoods.setId(id);
            ErpPickgoods erpPickgoods1 = new ErpPickgoods();
            erpPickgoods1.setStatus_(-1);
            erpPickgoods1.setId(id);
            erpPickgoods1.setPickgoods_num(null);
            int result = pickgoodsService.update(erpPickgoods1);//选择性修改
            if (result == 1) {
                //前端传pickgoos_id
                //拿到pickgoods对象.total_price，.user_id拿到user对象，balance+total_price退款
                Integer user_id = erpPickgoods.getUser_id();
                ErpUser erpUser = this.userService.detail(user_id);
                Double ingoods_totalprice = erpPickgoods.getIngoods_totalprice();
                Double rebate_balance = erpUser.getRebate_balance();
                rebate_balance = rebate_balance + ingoods_totalprice;
                erpUser.setRebate_balance(rebate_balance);//退款
                int unaudit = this.userService.update(erpUser);
                if (unaudit == 1) {
                    //返回手机号
                    ErpUser erpUser1 = this.userService.detail(erpPickgoods.getUser_id());
                    String car_order_sn1 = erpPickgoods.getCar_order_sn();
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("customer_phone", erpUser1.getUser_phone());
                    map.put("car_order_sn", car_order_sn1);
                    return new MessageBean(10001, "审核拒绝", map);
                }
                return new MessageBean(10000, "退款失败", unaudit);
            }
            return new MessageBean(10000, "更新status_失败", result);
        }
        return new MessageBean(10000, "订单id为空", null);
    }

    /**
     * 提货统计报表
     *
     * @return
     */
    @RequestMapping("/lists_statistice")
    public MessageBean lists_statistice() {
        List<ErpPickgoods> list = pickgoodsService.lists_statistice();
        if (list != null) {
            if (list.size() > 0) {
                return new MessageBean(10001, "", list);
            }
        }
        return new MessageBean(10000, "列表数据为空", list);
    }

    /**
     * 检查价格？找不到供应商确认价格之后的管理者审核，，
     *
     * @param id
     * @param token
     * @return 2019年2月28日下午4:09:06
     * @author : YZH
     */
    @RequestMapping("/checkPrice")
    public MessageBean checkPrice(Integer id, String token) {
        ErpPickgoods erpPickgoods = pickgoodsService.get(id);
        Integer user_id = erpPickgoods.getUser_id();
        ErpUser user = userService.detail(user_id);
        if (user.getUser_price() != null) {
            double totalPrice = (user.getUser_price() * erpPickgoods.getPickgoods_num());
            if (user.getRebate_balance() < totalPrice) {
                return new MessageBean(10001, "余额不足,请充值", null);
            } else {
                return new MessageBean(10000, "", null);
            }
        } else {
            return new MessageBean(10005, "单价不能为空", null);
        }

    }

    /**
     * 销售单
     */
    @RequestMapping("/lists_sell")
    public MessageBean lists_sell(@RequestParam(value = "id", required = false) Integer id) {

        ErpPickgoods erpPickgoods = pickgoodsService.get(id);

        ErpPickgoods list = pickgoodsService.getByCarOrderSn(erpPickgoods.getCar_order_sn(),
                erpPickgoods.getPickgoods_car_sn());
        if (list != null) {
            return new MessageBean(10001, "", list);
        }
        // System.out.println(list);
        return new MessageBean(10000, "数据信息异常", null);
    }

    /*
     * 出货单
     *
     */
    @RequestMapping("/out_goods")
    public MessageBean out_goods(@RequestParam(value = "id", required = false) Integer id) {

        ErpPickgoods erpPickgoods = pickgoodsService.get(id);
        // System.out.println(erpPickgoods.getPickgoods_car_sn());
        // System.out.println(erpPickgoods.getCar_order_sn());
        ErpPickgoods list = pickgoodsService.out_goods(erpPickgoods.getCar_order_sn(),
                erpPickgoods.getPickgoods_car_sn());

        if (list != null) {
            return new MessageBean(10001, "", list);
        }

        return new MessageBean(10000, "无此信息", null);

    }

    /*
     * 出库统计表
     *
     */
    @RequestMapping("/list_out_goods")
    public MessageBean list_out_goods(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize
    ) {
        PageHelper.startPage(currentPage, pageSize);
        List<ErpPickgoods> list = pickgoodsService.list_out_goods();
        if (list != null) {
            int countNums = list.size();//总记录数
            PageBean<ErpPickgoods> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(list);
            return new MessageBean(10001, "", pageData.getItems());
        }

        return new MessageBean(10000, "出库统计报表为空", list);// YZH 加提示字符串

    }

    // 8888888888888888888888888888888888888888888888888888888888888888888

}
