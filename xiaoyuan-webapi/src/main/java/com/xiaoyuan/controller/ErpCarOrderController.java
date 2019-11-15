package com.xiaoyuan.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.xiaoyuan.controller.CommonController.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyuan.model.ErpCarOrder;
import com.xiaoyuan.model.ErpPickgoods;
import com.xiaoyuan.model.ErpUser;
import com.xiaoyuan.service.IErpCarOrderService;
import com.xiaoyuan.service.IErpDriverService;
import com.xiaoyuan.service.IErpPickgoodsService;
import com.xiaoyuan.service.IErpUserService;
import com.xiaoyuan.tools.MessageBean;

/**
 * carorder ordercar .... 20190322
 */
@CrossOrigin
@RestController
@RequestMapping("/ErpOrderCar")
public class ErpCarOrderController extends BaseController {
    @Autowired
    private IErpCarOrderService carOrderService;
    @Autowired
    private IErpDriverService driverService;
    @Autowired
    private IErpPickgoodsService pickgoodsService;
    @Autowired
    private IErpUserService userService;


    //司机，接单，不接单，确定排队，三个按钮，三个方法，
    //pickgoods is_car_order=null , 接单=1，不接单=-1，去掉车牌号，确定排队则增加确定排队时间和确定排队状态=1
    @RequestMapping("/is_car_order")
    public MessageBean is_car_order(ErpCarOrder erpCarOrder) {
        Integer pickgoods_id = erpCarOrder.getPickgoods_id();
        if (pickgoods_id != null) {
            ErpPickgoods erpPickgoods = this.pickgoodsService.get(pickgoods_id);
            erpPickgoods.setIs_car_order(1);//接单
            int result = this.pickgoodsService.updateById(erpPickgoods);
            if (result == 1) {
                return new MessageBean(10001, "接单成功", result);
            }
            return new MessageBean(10000, "接单失败", result);
        }
        return new MessageBean(10000, "订单id为空", pickgoods_id);
    }

    @RequestMapping("/confirm_queue")
    public MessageBean confirm_queue(ErpCarOrder erpCarOrder) {
        //确认排队，
        if (erpCarOrder.getPickgoods_id() != null) {
            ErpPickgoods erpPickgoods = this.pickgoodsService.get(erpCarOrder.getPickgoods_id());
            if (erpPickgoods.getIs_car_order() == null) {
                erpPickgoods.setIs_car_order(0);
            }
            if (erpPickgoods.getIs_car_order() == 1) {
                erpPickgoods.setIs_confirm_queue(1);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(new Date());
                erpPickgoods.setConfirm_queue_datetime(date);//TODO
                int result = this.pickgoodsService.updateById(erpPickgoods);
                if (result == 1) {
                    return new MessageBean(10001, "确认排队成功", result);
                }
                return new MessageBean(10000, "确认排队失败", result);
            }
            return new MessageBean(10000, "还未接单", erpPickgoods);
        }
        return new MessageBean(10000, "订单id为空", erpCarOrder);
    }

//    @RequestMapping("/un_car_order")
//    public MessageBean un_car_order(ErpCarOrder erpCarOrder) {
//
//        Integer pickgoods_id = erpCarOrder.getPickgoods_id();
//        if (pickgoods_id != null) {
//
//        }
//        return new MessageBean(10000, "订单id为空", pickgoods_id);
//    }


    /**
     * 车辆预约列表
     *
     * @return
     */
    /*
     * @RequestMapping("/lists") public MessageBean lists(HttpServletRequest
     * request){ //Object id =
     * request.getSession().getServletContext().getAttribute("userId");
     * List<ErpCarOrder> lists = carOrderService.lists(null); if (lists !=
     * null){ return new MessageBean(10001,"",lists); } return new
     * MessageBean(10000,"数据列表为空",lists); }
     */
    @RequestMapping("/add")
    public MessageBean add(
            @RequestParam(value = "id", required = false) Integer id,
            ErpCarOrder erpCarOrder,
            @RequestParam(value = "car_order_pick_num", required = false) Integer car_order_pick_num) {
        if (id != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            ErpCarOrder carOrder = carOrderService.get(id);
            carOrder.setCar_order_pickdate(sdf.format(erpCarOrder.getCar_order_pickdate()));

            if (car_order_pick_num <= 0) {
                return new MessageBean(10020, "请输入大于零的数字", null);
            }
            if (car_order_pick_num > carOrder.getCar_order_remain_num()
                    || car_order_pick_num > carOrder.getCar_order_num()) {
                return new MessageBean(10010, "库存不足", null);
            }
            carOrder.setId(id);
            carOrder.setCar_order_pick_num(erpCarOrder.getCar_order_pick_num());
            carOrder.setCar_order_remain_num(carOrder.getCar_order_remain_num() - carOrder.getCar_order_pick_num());
            carOrder.setUpdatetime(sdf.format(new Date()));
            int result = carOrderService.update(carOrder);
            if (result > 0) {
                return new MessageBean(10001, "车辆预约修改成功", null);
            }
        }
        return new MessageBean(10000, "车辆预约修改失败", null);

    }

    @RequestMapping("/detail")
    public MessageBean detail(@RequestParam Integer id) {
        if (id != null) {
            ErpCarOrder carOrder = carOrderService.get(id);
            if (carOrder != null) {
                return new MessageBean(10001, "", carOrder);
            }
        }
        return new MessageBean(10000, "没有找到此纪录", null);
    }

    //20190326
    //订单加状态，拒绝接单，
    @RequestMapping("/delete")
    public MessageBean delete(@RequestParam Integer id) {

        if (id != null) {
            //删除时把pickgoods对象的对应接单列置为-1
            ErpCarOrder erpCarOrder = this.carOrderService.get(id);
            ErpPickgoods erpPickgoods = this.pickgoodsService.get(erpCarOrder.getPickgoods_id());
            erpPickgoods.setIs_car_order(-1);
            erpPickgoods.setPickgoods_car_sn("");//车牌号断掉
            //20190327 司机不接单后，审核status_=0
            //20190405 status_=-1
            erpPickgoods.setStatus_(-1);
            int update_result = this.pickgoodsService.updateById(erpPickgoods);
            if (update_result == 1) {
                int result = carOrderService.delete(id);
                if (result == 1) {
                    return new MessageBean(10001, "删除车单成功", result);
                }
                return new MessageBean(10000, "删除车单失败", result);
            }
            return new MessageBean(10000, "修改订单失败", update_result);
        }
        return new MessageBean(10001, "车单id为空", id);
    }

    //20190326 TODO 园区配送安排车辆，去掉配送后，可用于客户重新指派，用园区配送安排车辆的页面就行了吧~是否status_=1不知道

    /**
     * 销货
     *
     * @param id               提货id
     * @param pickgoods_car_sn
     * @return
     */
    //TODO 20190405 重新指派没了，都用重新提交，司机和园区拒绝效果一样，司机多一个不接单的状态值，
    @RequestMapping("/add_sales_car")
    public MessageBean add_sales_car(
            @RequestParam Integer id,
            ErpCarOrder erpCarOrder,
            String token,
            @RequestParam(value = "pickgoods_car_sn", required = false) String pickgoods_car_sn) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        if (id != null) {
            ErpCarOrder carOrder = new ErpCarOrder();
            // carOrder.setDriver_id(driver.getId());

            ErpPickgoods erpPickgoods = pickgoodsService.get(id);
            erpPickgoods.setId(id);
            erpPickgoods.setPickgoods_car_sn(pickgoods_car_sn);
            carOrder.setPickgoods_id(id);
            carOrder.setCreatetime(sdf.format(new Date()));
            carOrder.setCar_order_address(erpPickgoods.getUser_address());
            carOrder.setCar_order_price(erpCarOrder.getCar_order_price());
            carOrder.setCar_order_money(erpCarOrder.getCar_order_money());
            carOrder.setCar_order_sn(erpPickgoods.getCar_order_sn());
            erpPickgoods.setStatus_(-1);//重新指派，按照流程图是审核拒绝？ 20190327
            erpPickgoods.setIs_car_order(0);//恢复
            pickgoodsService.update(erpPickgoods);//更新订单的车牌号，根据订单id，还有更新园区审核status_=1
            int result = carOrderService.insert(carOrder);
            if (result > 0) {
                return new MessageBean(10001, "提交成功", null);
            }
        }
        return new MessageBean(10000, "提交失败", null);
    }

    //???
    @RequestMapping("/totalPrice")
    private Double totalPrice(@RequestParam Integer id) {
        ErpPickgoods erpPickgoods = pickgoodsService.get(id);
        Integer user_id = erpPickgoods.getUser_id();
        ErpUser user = userService.detail(user_id);
        return user.getUser_price() * erpPickgoods.getPickgoods_num();
    }

    /**
     * 进货
     *
     * @param id
     * @param erpCarOrder
     * @param car_sn
     * @return
     */
    @RequestMapping("/add_in_car")
    public MessageBean add_in_car(@RequestParam Integer id, ErpCarOrder erpCarOrder, String token,
                                  @RequestParam(value = "car_sn", required = false) String car_sn) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        if (id != null) {
            ErpPickgoods erpIngoods = pickgoodsService.get(id);
            ErpPickgoods ingoods = new ErpPickgoods();
            ingoods.setId(id);
            ingoods.setPickgoods_car_sn(car_sn);
            ingoods.setUpdatetime(sdf.format(new Date()));
            pickgoodsService.update(ingoods);
            ErpCarOrder carOrder = new ErpCarOrder();
            carOrder.setCar_order_sn(erpIngoods.getCar_order_sn());
            carOrder.setCar_order_money(erpCarOrder.getCar_order_money());
            carOrder.setCreatetime(sdf.format(new Date()));
            int result = carOrderService.insert(carOrder);
            if (result > 0) {
                return new MessageBean(10001, "提交成功", null);
            }
        }
        return new MessageBean(10000, "提交失败", null);
    }

    @RequestMapping("/updateAddress")
    public MessageBean updateAddress(Integer id, @RequestParam("car_order_address") String car_order_address) {
        ErpCarOrder erpCarOrder = carOrderService.get(id);
        erpCarOrder.setCar_order_address(car_order_address);
        erpCarOrder.setId(id);
        int result = carOrderService.update(erpCarOrder);
        if (result > 0) {
            return new MessageBean(10001, "地址修改成功", null);
        }
        return new MessageBean(10000, "地址修改失败", null);
    }
}
