package com.xiaoyuan.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.xiaoyuan.model.*;
import com.xiaoyuan.tools.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyuan.service.IErpCarService;
import com.xiaoyuan.service.IErpCardService;
import com.xiaoyuan.service.IErpDriverService;
import com.xiaoyuan.service.IErpGpsService;
import com.xiaoyuan.service.IErpUserService;
import com.xiaoyuan.tools.MessageBean;

/**
 * @author YZH
 * @author YZH
 * @version 2019年3月8日 下午3:20:59
 */
@CrossOrigin
@RestController
@RequestMapping("/ErpCar")
public class ErpCarController {
    /**
     * 车辆表操作
     */
    @Autowired
    private IErpCarService carService;
    /**
     * 用户表操作
     */
    @Autowired
    private IErpUserService userService;
    /**
     * 司机表操作
     */
    @Autowired
    private IErpDriverService driverService;
    /**
     * 门卡表操作
     */
    @Autowired
    private IErpCardService cardService;
    /**
     * GPS表操作
     */
    @Autowired
    private IErpGpsService gpsService;

    /**
     * 增加和修改信息，判断增加还是修改，并且修改时判断车牌号方法与增加不同，
     *
     * @param erpCar
     * @param id
     * @param user_name
     * @param user_phone
     * @param car_sn
     * @param driver_sn
     * @param driver_use_date
     * @param driver_eff_date
     * @param gps_imei
     * @param card_rfid
     * @param car_cate
     * @param gps_sn
     * @param code
     * @return 2019年3月4日下午4:44:13
     * @author : YZH
     * <p>
     * 园区账号车辆管理，保存的时候报错Expected one result (or null) to be returned by
     * selectOne(), but found:
     * <p>
     * 2019年3月5日上午10:03:33
     * @author : YZH
     * <p>
     * 园区账号，车辆管理，列表，车辆修改，提交失败
     * <p>
     * 2019年3月5日下午5:00:56
     * @author : YZH
     * <p>
     * 问题有点多，首先需要知道业务，否则，，，各表关系和业务
     * <p>
     * 2019年3月5日下午5:19:34
     * @author : YZH
     * <p>
     * 增加车辆，需要用到GPS类，即需要对GPS表进行数据插入，持久化
     * <p>
     * 2019年3月8日下午3:21:17
     * @author : YZH
     */
    @RequestMapping("/add")
    public MessageBean add(
            ErpCar erpCar,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "user_name", required = false) String user_name,
            @RequestParam(value = "user_phone", required = false) String user_phone,
            @RequestParam(value = "car_sn", required = false) String car_sn,
            @RequestParam(value = "driver_sn", required = false) String driver_sn,
            @RequestParam(value = "driver_use_date", required = false) String driver_use_date,
            @RequestParam(value = "driver_eff_date", required = false) String driver_eff_date,
            @RequestParam(value = "gps_imei", required = false) String gps_imei,
            @RequestParam(value = "card_rfid", required = false) String card_rfid,
            @RequestParam(value = "car_cate", required = false) String car_cate,
            @RequestParam(value = "gps_sn", required = false) String gps_sn,
            Integer code,
            ErpUser erpUser2,
            ErpDriver erpDriver2,
            String user_wechar) {

        //20190410 Wed. 19:44
        erpUser2.setId(erpDriver2.getUser_id());
        this.userService.update(erpUser2);

        // 五张表，CRUD
        ErpUser user = new ErpUser();
        ErpDriver driver = new ErpDriver();
        ErpCar car = new ErpCar();
        ErpGPS gps = new ErpGPS();
        ErpCard card = new ErpCard();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        // YZH 判断车牌号是否已注册
        // 20190304 修改的车牌查重实现为传id,当id不为null加where id !=
        // #{id}，但是这么实现要改接口，，可变参数，，，
        // TO-DO 20190304 YZH debug 84 加一个id参数，当id非空时查车牌号不为此id,
        //TODO 20190408 Mon. 14:11 允许重复
//        List<ErpDriver> drivers = driverService.carsn(id);
//        if (drivers != null) {
//            for (ErpDriver driverObj : drivers) {
//                if (driverObj.getCar_sn().equals(car_sn)) {
//                    return new MessageBean(10000, "车牌号已存在", null);
//                }
//            }
//        }
        if (code == 1) {// TODO YZH code?
            user.setUser_name(user_name);
            user.setUser_cate(4);
            user.setUser_phone(user_phone);
            user.setCreatetime(sdf.format(new Date()));
            userService.add(user);
            driver.setUser_id(id);
            driver.setCar_sn(car_sn);
            driver.setDriver_sn(driver_sn);
            driver.setDriver_use_date(driver_use_date);
            driver.setDriver_eff_date(driver_eff_date);
            driver.setCar_cate(car_cate);
            driver.setCreatetime(sdf.format(new Date()));
            int result = driverService.updateByUserid(driver);
            if (result > 0) {
                return new MessageBean(10001, "车辆登记成功", null);
            }
        } else {// TODO 这部分是code=2执行的代码，code是干啥的前端也不知道  好像是新增和修改吧
            ErpDriver driver2 = driverService.getDriverByUserId(id);
            if (driver2 == null) {
                return new MessageBean(10000, "根据id找不到driver", id);
            }
            ErpCar car2 = carService.getIdByDriverId(driver2.getId());
            int result = 0;
            car.setDriver_id(driver2.getId());
            car.setCar_owner(erpCar.getCar_owner());
            car.setCar_contact_phone(erpCar.getCar_contact_phone());
            car.setCar_weight(erpCar.getCar_weight());
            car.setCar_length(erpCar.getCar_length());
            car.setCar_load(erpCar.getCar_load());
            if (car2 != null) {
                car.setUpdatetime(sdf.format(new Date()));
                result = carService.updateByDriverId(car);
            } else {
                result = carService.insert(car);//TODO 增加车辆信息？
            }
            user.setId(id);
            user.setUser_name(user_name);
            user.setUser_phone(user_phone);
            user.setUpdatetime(sdf.format(new Date()));
            user.setUser_wechat(user_wechar);
            userService.update(user);
            driver.setUser_id(id);
            driver.setCar_sn(car_sn);
            driver.setUpdatetime(sdf.format(new Date()));
            driver.setDriver_sn(erpDriver2.getDriver_sn());
            driverService.updateByUserid(driver);
            gps.setGps_sn(gps_sn);
            gps.setGps_imei(gps_imei);
            card.setCard_rfid(card_rfid);
            if (car2 != null) {
                gps.setCar_id(car2.getId());
                gps.setDriver_id(driver2.getId());
                card.setDriver_id(car2.getDriver_id());
                ErpGPS gps2 = gpsService.getGpsByCarid(car2.getId());
                // 这里报错，找到多个
                // 可以查看一下SQL。。
                // 目测涉及多表删除，影响关系没处理好
                // 返回了多条记录，，
                // 修改的时候实际执行了新增，导致数据很多，重复，没有唯一性
                // 增加和修改用的同一个方法，把修改部分debug一下，应该可以解决吧。。
                ErpCard card2 = cardService.getCardByCarid(driver2.getId());// TODO
                if (gps2 != null) {
                    gpsService.updateByCarId(gps);
                } else {
                    gpsService.insert(gps);
                }
                if (card2 != null) {
                    cardService.updateByDriverId(card);
                } else {
                    cardService.insert(card);
                }
            } else {
                gps.setCar_id(car.getId());
                gps.setDriver_id(driver2.getId());
                card.setDriver_id(car.getDriver_id());
                gpsService.insert(gps);
                cardService.insert(card);
            }
            if (result > 0) {
                return new MessageBean(10001, "车辆修改成功", null);
            }
        }
        return new MessageBean(10000, "车辆操作失败", null);
    }

    @RequestMapping("/trash_add")
    public MessageBean trash_add(ErpCar erpCar, @RequestParam(value = "id", required = false) Integer id,
                                 @RequestParam(value = "user_name", required = false) String user_name,
                                 @RequestParam(value = "user_phone", required = false) String user_phone,
                                 @RequestParam(value = "car_sn", required = false) String car_sn,
                                 @RequestParam(value = "driver_sn", required = false) String driver_sn,
                                 @RequestParam(value = "driver_use_date", required = false) String driver_use_date,
                                 @RequestParam(value = "driver_eff_date", required = false) String driver_eff_date,
                                 @RequestParam(value = "gps_imei", required = false) String gps_imei,
                                 @RequestParam(value = "card_rfid", required = false) String card_rfid,
                                 @RequestParam(value = "car_cate", required = false) String car_cate,
                                 @RequestParam(value = "gps_sn", required = false) String gps_sn, Integer code) {

        // 可以选择不在这里new，线程安全的懒汉
        ErpUser user = new ErpUser();
        ErpDriver driver = new ErpDriver();
        ErpCar car = new ErpCar();
        ErpGPS gps = new ErpGPS();
        ErpCard card = new ErpCard();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        // YZH 判断车牌号是否已注册
        // 20190304 修改的车牌查重实现为传id,当id不为null加where id !=
        // #{id}，但是这么实现要改接口，，可变参数，，，

        // TO-DO 20190304 YZH debug 84 加一个id参数，当id非空时查车牌号不为此id,
        List<ErpDriver> drivers = driverService.carsn(id);
        if (drivers != null) {
            for (ErpDriver driverObj : drivers) {
                if (driverObj.getCar_sn().equals(car_sn)) {
                    return new MessageBean(10000, "车牌号已存在", null);
                }
            }
        }

        if (code == 1) {// TO-DO YZH code?
            /*
             * user.setUser_name(user_name); user.setUser_cate(4);
             * user.setUser_phone(user_phone); user.setCreatetime(sdf.format(new
             * Date())); userService.add(user);
             */
            driver.setUser_id(id);
            /*
             * driver.setCar_sn(car_sn); driver.setDriver_sn(driver_sn);
             */
            driver.setDriver_use_date(driver_use_date);
            /*
             * driver.setDriver_eff_date(driver_eff_date);
             * driver.setCar_cate(car_cate); driver.setCreatetime(sdf.format(new
             * Date()));
             */
            int result = driverService.updateByUserid(driver);

            if (result > 0) {
                return new MessageBean(10001, "车辆登记成功", null);
            }
        } else {
            ErpDriver driver2 = driverService.getDriverByUserId(id);
            ErpCar car2 = carService.getIdByDriverId(driver2.getId());

            int result = 0;
            car.setDriver_id(driver2.getId());
            car.setCar_owner(erpCar.getCar_owner());
            car.setCar_contact_phone(erpCar.getCar_contact_phone());
            car.setCar_weight(erpCar.getCar_weight());
            car.setCar_length(erpCar.getCar_length());
            car.setCar_load(erpCar.getCar_load());
            if (car2 != null) {
                car.setUpdatetime(sdf.format(new Date()));
                result = carService.updateByDriverId(car);
            } else {
                result = carService.insert(car);
            }
            user.setId(id);
            user.setUser_name(user_name);
            user.setUser_phone(user_phone);
            user.setUpdatetime(sdf.format(new Date()));
            userService.update(user);
            driver.setUser_id(id);
            driver.setCar_sn(car_sn);
            driver.setUpdatetime(sdf.format(new Date()));
            driverService.updateByUserid(driver);

            gps.setGps_sn(gps_sn);
            gps.setGps_imei(gps_imei);

            card.setCard_rfid(card_rfid);
            if (car2 != null) {
                gps.setCar_id(car2.getId());
                gps.setDriver_id(driver2.getId());
                card.setDriver_id(car2.getDriver_id());
                ErpGPS gps2 = gpsService.getGpsByCarid(car2.getId());

                // 这里报错，找到多个
                // 可以查看一下SQL。。
                // 目测涉及多表删除，影响关系没处理好
                // 返回了多条记录，，
                // 修改的时候实际执行了新增，导致数据很多，重复，没有唯一性
                // 增加和修改用的同一个方法，把修改部分debug一下，应该可以解决吧。。
                ErpCard card2 = cardService.getCardByCarid(driver2.getId());// TO-DO
                if (gps2 != null) {
                    gpsService.updateByCarId(gps);
                } else {
                    gpsService.insert(gps);
                }
                if (card2 != null) {
                    cardService.updateByDriverId(card);
                } else {
                    cardService.insert(card);
                }
            } else {
                gps.setCar_id(car.getId());
                gps.setDriver_id(driver2.getId());
                card.setDriver_id(car.getDriver_id());
                gpsService.insert(gps);
                cardService.insert(card);
            }

            if (result > 0) {
                return new MessageBean(10001, "车辆修改成功", null);
            }

        }
        return new MessageBean(10000, "车辆操作失败", null);
    }

    /**
     * 车辆列表
     * <p>
     * 供应商预约送货（录入车牌号）没有显示供应商司机
     *
     * @return 2019年2月28日下午12:01:48
     * @author : YZH
     * <p>
     * 园区登录，车辆管理，列表，查看SQL查询语句，CRUD目测相关SQL语句都没做好，，
     * <p>
     * 简单的lists_car可能不够，至少需要传参筛选if test SQL
     * <p>
     * 2019年3月5日下午3:32:59
     * @author : YZH
     * <p>
     * 又来了，就是上面说的问题，只查客户车辆，加传参，不行就换控制类方法，
     * <p>
     * role_id,1234,客户供应商园区司机
     * <p>
     * car_cate,123,园区供应商客户
     * <p>
     * <p>
     * 园区登录，进货自提和提货预约，查园区车
     * <p>
     * 供应商登录，园区进货预约，查供应商车
     * <p>
     * 客户登录，提货自提，查客户车
     * <p>
     * 园区登录，车辆管理，查看全部车，用控制类的另一个方法，SQl传参null
     * <p>
     * 2019年3月7日上午10:40:45
     * @author : YZH
     */
    //TODO 20190330 前端传参car_cate 1,2
    @RequestMapping("/lists")
    public MessageBean lists(
            String token,
            Integer car_cate,
            String search,
            Integer audit,
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize) {

        // YZH 客户提货自提，选择车牌号，只看客户车辆
        ErpUser user = this.userService.detail(this.userService.getUserIdByToken(token));
        //20190330 非空判断 没有根据角色了，可以去掉，token验证可以用一下吧？
//        if (user == null) {
//            return new MessageBean(10000, "token查不到用户对象", user);
//        }
//        if (user.getRole_id() == null) {
//            return new MessageBean(10000, "用户对象role_id为空", user);
//        }
        //之前是根据角色返回列表，现在使用前端传参查
        PageHelper.startPage(currentPage, pageSize);
        List<ErpDriver> list = this.driverService.lists_car(car_cate, search, audit);
        if (list != null) {
            int countNums = list.size();//总记录数
            PageBean<ErpDriver> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(list);
            return new MessageBean(10001, "", pageData.getItems());
        }
        return new MessageBean(10000, "", list);

//        if (user.getRole_id() == 1) {// &&(!user.getUser_name().equals("admin"))
//            List<ErpDriver> lists_car = driverService.lists_car(3);
//            if (lists_car != null) {// YZH Service实现类返回情况有空值，，
//                return new MessageBean(10001, "", lists_car);
//            }
//            return new MessageBean(10000, "客户车辆列表为空", lists_car);
//        }
//
//        if (user.getRole_id() == 2) {// 供应商给园区进货预约安排车辆
//            List<ErpDriver> lists_car = driverService.lists_car(2);
//            if (lists_car != null) {// YZH Service实现类返回情况有空值，，
//                return new MessageBean(10001, "", lists_car);
//            }
//            return new MessageBean(10000, "供应商车辆列表为空", lists_car);
//        }
//        // YZH 园区进货自提和客户提货预约，只查看园区司机
//        if (user.getRole_id() == 3) {// 园区
//            List<ErpDriver> lists_car = driverService.lists_car(1);
//            if (lists_car != null) {// YZH Service实现类返回情况有空值，，
//                return new MessageBean(10001, "", lists_car);
//            }
//            return new MessageBean(10000, "园区车辆列表为空", lists_car);
//        }
//
//        List<ErpDriver> lists_car = driverService.lists_car(null);// YZH
//        // 加了null传参
//        if (lists_car != null) {// YZH Service实现类返回情况有空值，，
//            return new MessageBean(10001, "", lists_car);
//        }
//        return new MessageBean(10000, "数据列表为空", lists_car);
    }

    /**
     * 查全部车辆，，原来的查全部车辆用作查分类车辆
     *
     * @param token
     * @return 2019年3月7日上午11:40:09
     * @author : YZH
     */
    @RequestMapping("/lists_all")
    public MessageBean lists_all(
            String token,
            Integer car_cate,
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize,
            Integer audit,
            String search) {

        PageHelper.startPage(currentPage, pageSize);
        List<ErpDriver> lists_car = driverService.lists_car(car_cate, search, audit);// YZH
        // 加了null传参
        if (lists_car != null) {// YZH Service实现类返回情况有空值，，
            int countNums = lists_car.size();//总记录数
            PageBean<ErpDriver> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(lists_car);
            return new MessageBean(10001, "", pageData.getItems());
        }
        return new MessageBean(10000, "数据列表为空", lists_car);
    }

    @RequestMapping("/delete")
    public MessageBean delete(@RequestParam(value = "id", required = false) Integer id) {
        int delete = carService.delete(id);
        if (delete > 0) {
            return new MessageBean(10001, "车辆删除成功", null);
        }

        return new MessageBean(10000, "车辆删除失败", null);
    }
}
