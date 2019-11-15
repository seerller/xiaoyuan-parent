package com.xiaoyuan.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.github.pagehelper.PageHelper;
import com.xiaoyuan.controller.CommonController.BaseController;
import com.xiaoyuan.model.*;
import com.xiaoyuan.tools.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyuan.service.IErpCarOrderService;
import com.xiaoyuan.service.IErpCarService;
import com.xiaoyuan.service.IErpDriverService;
import com.xiaoyuan.service.IErpOrdersService;
import com.xiaoyuan.service.IErpPickgoodsService;
import com.xiaoyuan.service.IErpUserService;
import com.xiaoyuan.tools.MessageBean;

/**
 * 进货流程，司机
 * <p>
 * //20190325 车单为啥放司机类里面？？？司机查看车单，select from driver???
 *
 * @author YZH
 * @version 2019年3月1日 上午9:21:44
 */
@CrossOrigin
@RestController
@RequestMapping("/ErpDriver")
public class ErpDriverController extends BaseController {
    @Autowired
    private IErpCarOrderService carOrderService;
    @Autowired
    private IErpDriverService driverService;
    @Autowired
    private IErpPickgoodsService pickgoodsService;
    @Autowired
    private IErpUserService userService;
    @Autowired
    private IErpCarService carService;
    @Autowired
    private IErpOrdersService ordersService;

    /**
     * 我的订单
     * <p>
     * 操作车单表，应该放在车单控制类，司机查看时，查找where status_=1,园区审核状态列，20190321
     *
     * @param token
     * @param order_cate
     * @return 2019年3月1日上午9:25:19
     * @author : YZH
     */
    @RequestMapping("/lists_order")
    public MessageBean lists_order(
            String token,
            Integer order_cate,
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize) {

        //20190325 token->user.id->driver.id->车单
        //20190325 哪些是我的车单？通过driver_id？但是driver_id为空，

        // 根据前端传参token判断用户角色是否为司机
        ErpUser user = userService.detail(this.getUserId(token));
        if (user == null) {
            return new MessageBean(10000, "token找不到用户对象", user);
        }

        if (user.getRole_id() == (4)) {// 是司机则显示，否则不显示
            // 车单列表
            //第二个传参，根据日期查找订单，第二个传参直接传null...
            PageHelper.startPage(currentPage, pageSize);
            List<ErpCarOrder> list = carOrderService.lists_order(this.getUserId(token), null, null);
            if (list != null) {
                int countNums = list.size();//总记录数
                PageBean<ErpCarOrder> pageData = new PageBean<>(currentPage, pageSize, countNums);
                pageData.setItems(list);
                return new MessageBean(10001, "", pageData.getItems());
            }
            return new MessageBean(10010, "列表数据为空", list);
        }
        if (user.getRole_id() == 3) {// 是司机则显示，否则不显示
            // 车单列表
            //第二个传参，根据日期查找订单，第二个传参直接传null...
            PageHelper.startPage(currentPage, pageSize);
            List<ErpCarOrder> list = carOrderService.lists_order(null, null, null);
            if (list != null) {
                int countNums = list.size();//总记录数
                PageBean<ErpCarOrder> pageData = new PageBean<>(currentPage, pageSize, countNums);
                pageData.setItems(list);
                return new MessageBean(10001, "", pageData.getItems());
            }
            return new MessageBean(10010, "列表数据为空", list);
        }
        return new MessageBean(10000, "不是司机或园区账号", user);
    }

    /**
     * 我的订单
     *
     * @param token
     * @param order_cate
     * @return
     */
    @RequestMapping("/trash_lists_order")
    public MessageBean trash_lists_order(String token, Integer order_cate) {
        ErpUser user = userService.detail(this.getUserId(token));
        if (user.getRole_id() == (4)) {// YZH 司机
            // 提货列表
            /* if(order_cate == 1){ */
            List<ErpCarOrder> lists_order = carOrderService.lists_order(this.getUserId(token), null, null);
            if (lists_order != null) {
                return new MessageBean(10001, "", lists_order);
            }
            /*
             * }else if(order_cate == 2){ List<ErpCarOrder> lists_order =
             * carOrderService.lists_order_ingoods(this.getUserId(token),null);
             * if (lists_order != null) { return new MessageBean(10001, "",
             * lists_order); } }
             */
            return new MessageBean(10010, "列表数据为空", null);
        }
        return new MessageBean(10010, "列表数据为空", null);
    }

    /**
     * 预约时间
     *
     * @param carOrder
     * @param id
     * @return
     */
    @RequestMapping("/add_order_date")
    public MessageBean add_order_date(ErpCarOrder carOrder, @RequestParam(value = "id", required = false) Integer id) {
        SimpleDateFormat sdf = null;
        if (id != null) {
            sdf = new SimpleDateFormat("HH:mm:ss");
            ErpCarOrder erpCarOrder = carOrderService.get(id);
            erpCarOrder.setId(id);
            erpCarOrder.setCar_order_pickdate(carOrder.getCar_order_pickdate() + " " + sdf.format(new Date()));
            erpCarOrder.setCar_order_senddate(carOrder.getCar_order_senddate());
            erpCarOrder.setCar_order_pick_num(carOrder.getCar_order_pick_num());
            erpCarOrder.setCar_order_remark(carOrder.getCar_order_remark());
            erpCarOrder.setCar_order_id(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 15));
            int result = carOrderService.update(erpCarOrder);
            if (result > 0) {
                return new MessageBean(10001, "时间设置成功", null);
            }
        }
        return new MessageBean(10000, "时间操作失败", null);

    }

    /**
     * 设置时间
     *
     * @param carOrder
     * @param id
     * @return
     */
    @RequestMapping("/add_order_set_date")
    public MessageBean add_order_set_date(ErpCarOrder carOrder,
                                          @RequestParam(value = "id", required = false) Integer id) {
        ErpCarOrder order = new ErpCarOrder();
        if (id != null) {
            order.setCar_order_senddate(carOrder.getCar_order_senddate());

            order.setCar_order_pickdate(
                    new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(carOrder.getCar_order_pickdate()));

            order.setCar_order_pick_num(carOrder.getCar_order_pick_num());
            int result = carOrderService.update(carOrder);
            if (result > 0) {
                return new MessageBean(10001, "时间设置成功", null);
            }
        }
        return new MessageBean(10000, "时间操作异常", null);

    }

    /**
     * 转单
     *
     * @param id     //     * @param carOrder
     * @param car_sn
     * @return
     */
    @RequestMapping("/add_order_change")
    public MessageBean add_order_change(@RequestParam(value = "id", required = false) Integer id,
                                        @RequestParam(value = "car_sn", required = false) String car_sn) {
        ErpCarOrder order2 = carOrderService.get(id);
        Integer pickgoods_id = order2.getPickgoods_id();
        ErpPickgoods erpPickgoods = pickgoodsService.get(pickgoods_id);
        if (id != null) {
            /*
             * ErpDriver driver = new ErpDriver();
             * driver.setId(order2.getDriver_id()); driver.setCar_sn(car_sn);
             * driverService.update(driver);
             */
            erpPickgoods.setId(pickgoods_id);
            erpPickgoods.setPickgoods_car_sn(car_sn);
            pickgoodsService.update(erpPickgoods);
            order2.setCar_order_remark(order2.getCar_order_remark());
            order2.setId(id);
            int result = carOrderService.update(order2);
            if (result > 0) {
                return new MessageBean(10001, "转单成功", null);
            }
        }
        return new MessageBean(10000, "转单异常", null);
    }

    //20190325 改用车单编号，作连接外键，在新增车单时，车单没有订单的id，订单也是刚新增，但好像有一个技术可以获得订单id
    @RequestMapping("/add_order2")
    public MessageBean add_order2(
            String token,
            @RequestParam(value = "id", required = false) Integer id) {
        //根据id获得车单对象，根据车单对象的车单编号获得订单对象，
        return null;
    }

    /**
     * 供应商司机5，接单，出错啦，
     * <p>
     * 客户司机320，接单，出错，自提接单都出错？
     * <p>
     * 增加了token传参，
     *
     * @param id
     * @param carOrder
     * @param pickgoods
     * @param car_sn
     * @param goods_name
     * @return 2019年3月1日上午11:02:46
     * @author : YZH
     */
    @RequestMapping("/add_order")
    public MessageBean add_order(
            String token,
            @RequestParam(value = "id", required = false) Integer id,
            ErpCarOrder carOrder,
            ErpPickgoods pickgoods,
            @RequestParam(value = "car_sn", required = false) String car_sn,
            @RequestParam(value = "goods_name", required = false) String goods_name) {

        //TODO 20190326 没有做持久化操作，传参只有token,多传点，少点sql操作
        // 传参没有id，order2为null，第二行空指针异常

        ErpCarOrder order2 = carOrderService.get(id);
        if (order2 != null) {
            Integer pickgoods_id = order2.getPickgoods_id();
            ErpPickgoods erpPickgoods = pickgoodsService.get(pickgoods_id);
            // order表中没有pickgoodsid，但是两表有car_order_sn，或许可以通过这个来找到pickgoods的记录，要不然就是
            // 在前面步骤中对order表的对应记录加上pickgoodsid
            // 查看order表对应的service方法在哪里被调用了，，加上，，，，

//        if (id != null) {
            // 空指针异常
            // 接单，只是返回list，也没用啊，没有持久化操作，，？？
            ErpPickgoods list = pickgoodsService.getByCarOrderSn(erpPickgoods.getCar_order_sn(),
                    erpPickgoods.getPickgoods_car_sn());

            return new MessageBean(10001, "成功", list);
        }
        return new MessageBean(10000, "未成功", null);
    }

    /**
     * 供应商司机5，接单，出错啦，
     *
     * @param id
     * @param carOrder
     * @param pickgoods
     * @param car_sn
     * @param goods_name
     * @return 2019年3月1日上午11:02:46
     * @author : YZH
     */
    @RequestMapping("/trash_add_order")
    public MessageBean trash_add_order(@RequestParam(value = "id", required = false) Integer id, ErpCarOrder carOrder,
                                       ErpPickgoods pickgoods, @RequestParam(value = "car_sn", required = false) String car_sn,
                                       @RequestParam(value = "goods_name", required = false) String goods_name) {

        ErpCarOrder order2 = carOrderService.get(id);
        Integer pickgoods_id = order2.getPickgoods_id();
        ErpPickgoods erpPickgoods = pickgoodsService.get(pickgoods_id);

        if (id != null) {
            ErpPickgoods list = pickgoodsService.getByCarOrderSn(erpPickgoods.getCar_order_sn(),
                    erpPickgoods.getPickgoods_car_sn());

            return new MessageBean(10001, "成功", list);
        }
        return new MessageBean(10000, "未成功", null);
    }

    //TODO 不接单要退款，并且做退款判断，避免重复退款
    @RequestMapping("/no_order")
    public MessageBean no_order(
            @RequestParam(value = "id", required = false) Integer id,
            ErpCarOrder carOrder,
            ErpPickgoods pickgoods,
            @RequestParam(value = "car_sn", required = false) String car_sn,
            @RequestParam(value = "goods_name", required = false) String goods_name,
            String token) {

        ErpDriver driver = driverService.getDriverByUserId(this.getUserId(token));
        ErpCarOrder order2 = carOrderService.get(id);
        Integer pickgoods_id = order2.getPickgoods_id();
        ErpPickgoods erpPickgoods = pickgoodsService.get(pickgoods_id);
        if (erpPickgoods.getStatus_() == null) {
            return new MessageBean(10000, "该订单未审核！", erpPickgoods);
        }
        if (erpPickgoods.getStatus_() == 0) {
            return new MessageBean(10000, "该订单未审核！", erpPickgoods);
        }
        if (erpPickgoods.getStatus_() == -1) {
            return new MessageBean(10000, "该订单已审核拒绝！", erpPickgoods);
        }
        if (erpPickgoods.getIs_car_order() == null) {
            erpPickgoods.setIs_car_order(0);
        }
        if (erpPickgoods.getIs_car_order() == -1) {
            return new MessageBean(10000, "已经不接单！！", erpPickgoods);
        }
        if (id != null) {
            int result2 = carOrderService.delete(id);//删车单？
            erpPickgoods.setId(pickgoods_id);
            erpPickgoods.setPickgoods_car_sn("");
            erpPickgoods.setStatus_(-1);
            erpPickgoods.setIs_car_order(-1);
            int result = pickgoodsService.update(erpPickgoods);
            if (result == 1) {
                Integer user_id = erpPickgoods.getUser_id();
                ErpUser erpUser = this.userService.detail(user_id);
                if (erpUser == null) {
                    return new MessageBean(10000, "根据user_id找不到user", user_id);
                }
                Double ingoods_totalprice = erpPickgoods.getIngoods_totalprice();
                Double rebate_balance = erpUser.getRebate_balance();
                rebate_balance = rebate_balance + ingoods_totalprice;
                erpUser.setRebate_balance(rebate_balance);//退款
                int unaudit = this.userService.update(erpUser);
                if (unaudit == 1) {
//                    return new MessageBean(10001, "审核拒绝", null);
                    //不接单是给客户发短信
                    String user_phone = erpUser.getUser_phone();
                    String car_order_sn = erpPickgoods.getCar_order_sn();
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("customer_phone", user_phone);
                    map.put("car_order_sn", car_order_sn);
                    return new MessageBean(10001, "不接单成功", map);
                }
                return new MessageBean(10000, "退款失败", unaudit);
            }
            return new MessageBean(10000, "修改订单失败", result);
        }
        return new MessageBean(10000, "传参车单id为空", id);
    }

    @RequestMapping("/getDriver")
    public MessageBean getDriver(
            @RequestParam Integer id,
            @RequestParam("code") Integer code) {
        ErpDriver driver;
        if (code == 1) {
            if (id != null) {
                driver = driverService.get(id);
                if (driver != null) {
                    return new MessageBean(10001, "司机", driver);
                }
                return new MessageBean(10000, "根据id找不到driver对象", driver);
            }
            return new MessageBean(10000, "id为空", id);
        } else {
            if (id != null) {
                driver = driverService.get(id);
                if (driver != null) {
                    return new MessageBean(20001, "园区", driver);
                }
                return new MessageBean(10000, "根据id找不到driver对象", driver);
            }
            return new MessageBean(10000, "id为空", id);
        }
    }

    /**
     * 预约记录
     *
     * @param
     * @return
     */
    //20190413 查看司机接单统计
    @RequestMapping("/lists_driver_order")
    public MessageBean lists_driver_order(
            String token,
            Integer order_cate,
            Integer is_car_order,
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize
    ) {
        /* if (order_cate == 1){ */
        PageHelper.startPage(currentPage, pageSize);
//        List<ErpCarOrder> list = carOrderService.lists_order(null, "car_order_pickdate",is_car_order);
        List<ErpCarOrder> list = carOrderService.lists_order(null, null, is_car_order);
        if (list != null) {
            int countNums = list.size();//总记录数
            PageBean<ErpCarOrder> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(list);
            return new MessageBean(10001, "", pageData.getItems());
        }
        /*
         * }else if(order_cate == 2){ List<ErpCarOrder> lists_order =
         * carOrderService.lists_order_ingoods(this.getUserId(token),
         * "car_order_pickdate"); if (lists_order != null) { return new
         * MessageBean(10001, "", lists_order); } }
         */
        return new MessageBean(10000, "列表数据为空", list);
    }

    /**
     * 选择单号
     *
     * @param token
     * @param car_order_sn
     * @return
     */
    @RequestMapping("/choose_sn")
    public MessageBean choose_sn(String token, @RequestParam("car_order_sn") String car_order_sn) {
        List<ErpCarOrder> lists_order = carOrderService.lists(this.getUserId(token), car_order_sn);
        ErpCarOrder order = lists_order.get(0);
        int num = (int) Math.ceil(order.getPickgoods_num() / order.getCar_load());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        ErpOrders erpOrders = new ErpOrders();
        erpOrders.setCar_order_sn(car_order_sn);
        List<ErpOrders> lists = ordersService.orderslist(erpOrders);
        if (lists.size() == 0) {
            for (int i = 1; i <= num; i++) {
                ErpOrders orders = new ErpOrders();
                orders.setCar_order_sn(order.getCar_order_sn());
                orders.setOrder_sn(Long.valueOf(System.currentTimeMillis() + i));
                orders.setStatus(0);
                orders.setCreatetime(sdf.format(new Date()));
                ordersService.insert(orders);
            }
            erpOrders.setCar_order_sn(car_order_sn);
            List<ErpOrders> list = ordersService.orderslist(erpOrders);
            return new MessageBean(10001, "", list);
        } else {
            return new MessageBean(10001, "", lists);
        }
    }

    /**
     * 司机确认
     *
     * @return
     */
    @RequestMapping("/driverConfirm")
    public MessageBean driverConfirm(String token) {
        ErpDriver erpDriver = new ErpDriver();
        erpDriver.setUser_id(this.getUserId(token));
        ErpDriver driver = driverService.driverConfirm(erpDriver);
        if (driver != null) {
            return new MessageBean(10001, null, driver);
        }
        return new MessageBean(10000, null, driver);
    }

    /**
     * 选择
     *
     * @return
     */
    @RequestMapping("/choose")
    public MessageBean choose(@RequestParam("id") Integer id) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        ErpOrders orders = new ErpOrders();
        orders.setId(id);
        orders.setStatus(1);
        orders.setUpdatetime(sdf.format(new Date()));
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, 2);// num为增加的天数，可以改变的
        Date date = ca.getTime();
        orders.setEndtime(sdf.format(date));
        int result = ordersService.update(orders);
        if (result > 0) {
            return new MessageBean(10001, "选择成功", null);
        }
        return new MessageBean(10000, "", null);
    }

    /**
     * 生成排队号
     *
     * @param request
     * @param car_order_sn
     * @param choose_sn
     * @return
     * @throws ParseException
     */
    /**
     * 生成排队号？要不还是按照流程图来debug吧，这里，，不通，，路坑没灯，摸索前进，，
     *
     * @param car_order_sn
     * @return
     * @throws ParseException 2019年3月8日上午11:23:22
     * @author : YZH
     */
    @RequestMapping("/queue")
    public MessageBean queue(@RequestParam("car_order_sn") String car_order_sn) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        ErpCarOrder order = new ErpCarOrder();
        order.setCar_order_sn(car_order_sn);

        if (isToday(sdf.parse(sdf.format(new Date()))) == true) {
            if (carOrderService.queueList() != null) {
                int maxQueue_sn = carOrderService.getMaxQueue_sn();
                order.setQueue_sn(Long.valueOf(maxQueue_sn + 1));
            } else {
                order.setQueue_sn((long) 1);
            }
        } else {
            order.setQueue_sn((long) 1);
        }
        order.setTime(sdf.format(new Date()));
        int result = carOrderService.updateOrder(order);
        return new MessageBean(10001, "排队成功", result);
    }

    /**
     * @param car_order_sn
     * @return
     */
    @RequestMapping("/get_queue")
    public MessageBean get(@RequestParam("car_order_sn") String car_order_sn) {
        ErpCarOrder carOrder = new ErpCarOrder();
        carOrder.setCar_order_sn(car_order_sn);
        ErpCarOrder order = carOrderService.getCarOrder(carOrder);
        return new MessageBean(10001, null, order.getQueue_sn());

    }

    private static boolean isToday(Date inputJudgeDate) {
        boolean flag = false;
        // 获取当前系统时间
        long longDate = System.currentTimeMillis();
        Date nowDate = new Date(longDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(nowDate);
        String subDate = format.substring(0, 10);
        // 定义每天的24h时间范围
        String beginTime = subDate + " 00:00:00";
        String endTime = subDate + " 23:59:59";
        Date paseBeginTime = null;
        Date paseEndTime = null;
        try {
            paseBeginTime = dateFormat.parse(beginTime);
            paseEndTime = dateFormat.parse(endTime);

        } catch (Exception e) {
            // log.error(e.getMessage());
        }
        if (inputJudgeDate.after(paseBeginTime) && inputJudgeDate.before(paseEndTime)) {
            flag = true;
        }
        return flag;
    }

}
