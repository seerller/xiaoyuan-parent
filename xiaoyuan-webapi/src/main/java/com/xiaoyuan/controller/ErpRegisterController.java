package com.xiaoyuan.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.xiaoyuan.model.*;
import com.xiaoyuan.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyuan.tools.EncryptUtil;
import com.xiaoyuan.tools.MessageBean;

/**
 * 注册
 * <p>
 * 如何实现的get id
 *
 * @author YZH
 * @author YZH
 * @version 2019年2月28日 下午2:30:07
 */

@RestController
@RequestMapping("/ErpReg")
@CrossOrigin
public class ErpRegisterController {
    @Autowired
    private IErpUserService userService;
    @Autowired
    private IErpTerminalCustomerService customerService;
    @Autowired
    private IErpDriverService driverService;
    @Autowired
    private IErpSupplierService supplierService;
    @Autowired
    private IErpGoodsService goodsService;
    @Autowired
    private IErpCarService erpCarService;
    @Autowired
    private IMessageService messageService;


//    @RequestMapping("get_message")
//    public MessageBean get_message(String phone) {
//        //生产随机数 及发送短信
//        /*
//         * 返回前端，前端校验，简单一点，正常应该是后端检验d、
//         * */
//        String verifyCode = String
//                .valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
//        Message message = new Message();
//        message.set_PhoneNumbers(phone);
//        message.set_TemplateCode("SMS_162456282");
//        // Message.set_TemplateParam("{\"code\":\""+verifyCode+"\"}");
//        message.set_TemplateParam("{\"A\":\"订单号\",\"C\":\"订单号\",}");
//        int is_send = messageService.Send(message);
//        if (is_send == 1) {
//            //成功发送
//            return new MessageBean(10001, "短信发送成功", is_send);
//        } else {
//            //发送失败
//            return new MessageBean(10000, "短信发送失败", is_send);
//        }
//    }

    /**
     * 用户注册
     *
     * @param erpUser
     * @param customer_name   终端客户
     * @param goods_name
     * @param driver_sn
     * @param driver_use_date
     * @param driver_eff_date
     * @param car_cate
     * @param car_sn
     * @param user_price      //     * @param user_cate
     * @param erpUser
     * @param customer_name
     * @param goods_name
     * @param driver_sn
     * @param driver_use_date
     * @param driver_eff_date
     * @param car_cate
     * @param car_sn
     * @param user_price
     * @param role_id
     * @return 2019年3月2日上午9:32:54
     * @author : YZH
     * <p>
     * 终端客户没有？
     * @author : YZH
     */
    @RequestMapping("/reg")
    public MessageBean reg(
            ErpUser erpUser,
            @RequestParam(value = "customer_name", required = false) String customer_name,
            @RequestParam(value = "goods_name", required = false) String goods_name,
            @RequestParam(value = "driver_sn", required = false) String driver_sn,
            @RequestParam(value = "driver_use_date", required = false) String driver_use_date,
            @RequestParam(value = "driver_eff_date", required = false) String driver_eff_date,
            @RequestParam(value = "car_cate", required = false) String car_cate,
            @RequestParam(value = "car_sn", required = false) String car_sn,
            @RequestParam(value = "user_price", required = false) Double user_price,
            // @RequestParam(value = "user_cate", required = false) Integer
            // user_cate,
            @RequestParam(value = "role_id", required = false) Integer role_id,
            String phone,
            String role_name) {


//        String verifyCode = String
//                .valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
//        Message message = new Message();
//        message.set_PhoneNumbers(phone);
//        message.set_TemplateCode("SMS_162456282");
//        // Message.set_TemplateParam("{\"code\":\""+verifyCode+"\"}");
//        message.set_TemplateParam("{\"A\":\"订单号\",\"C\":\"订单号\",}");
//        int is_send = messageService.Send(message);
//        if (is_send == 1) {
//            //成功发送
//            return new MessageBean(10001, "短信发送成功", is_send);
//        } else {
//            //发送失败
////            return new MessageBean(10000, "短信发送失败", is_send);
//            System.out.println("短信发送失败");
//        }

        if (erpUser.getUser_name() == null || erpUser.getPassword_() == null) {
            return new MessageBean(10000, "账号或密码为空", "bb");
        }


        // 西瓜得到用户登录名，这是根据用户名判断用户是否存在，避免用户名重复
        ErpUser user = userService.login(erpUser.getUser_name());// 得到用户登录名
        if (user != null) {
            return new MessageBean(10010, "用户名已存在", null);
        }
        //TODO 20190418 要求去掉手机号查重
//        user = userService.existsPhone(erpUser.getUser_phone(), null);// YZH
        // ???users
        // 这里可以继续用上面的user做判断
//        if (user != null) {
//            return new MessageBean(10000, "手机号已经注册", user);//这里可能会有多个user.
//        }
        // TODO
        // 这里的user.id可以通过先存入数据库再通过账号读出来就有id了，不过先看看数据库是否真的没有？
        int result;
        ErpUser userRegister = new ErpUser();
        userRegister.setArea_txt(erpUser.getArea_txt());
        userRegister.setCity_txt(erpUser.getCity_txt());
        userRegister.setProvince_txt(erpUser.getProvince_txt());
        /*
         * Integer num = userService.increment(); int value=1; if(num ==
         * null){ num=0; } value = num.intValue(); value++;
         */
        // userRegister.setId(Integer.valueOf(value));
        if (erpUser.getUser_name() != null && erpUser.getUser_name() != "") {
            userRegister.setUser_name(erpUser.getUser_name());
        }
        // 1：生成一个随机数
        // 2:用可逆的加密算法加密随机数
        // 3:将随机数和我们的密码 用sha1不可逆算法加密
        // 4：将第三步得到的字符串值用可逆的加密算法加密
        // 5：将第2步和第4步的值拼凑
        // 1
        byte[] random = EncryptUtil.generateSalt(8);
        // 2
        String randomHex = EncryptUtil.encodeHex(random);
        // 3+4
        String sha1Psd = EncryptUtil
                .encodeHex(EncryptUtil.sha1(erpUser.getPassword_().getBytes(), random, 1024));
        // 5
        String encryptPsd = randomHex + sha1Psd;
        if (erpUser.getPassword_() != null && erpUser.getPassword_() != "") {
            userRegister.setPassword_(encryptPsd);
        }
        if (erpUser.getUser_phone() != null && erpUser.getUser_phone() != "") {
            userRegister.setUser_phone(erpUser.getUser_phone());
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        if (erpUser.getRole_id() == (1)) {// 1.客户
            if (erpUser.getUser_company_name() != null && erpUser.getUser_company_name() != "") {
                userRegister.setUser_company_name(erpUser.getUser_company_name());
            }

            if (erpUser.getProvince_id() != null) {
                userRegister.setProvince_id(erpUser.getProvince_id());
            }
            if (erpUser.getCity_id() != null) {
                userRegister.setCity_id(erpUser.getCity_id());
            }
            if (erpUser.getArea_id() != null) {
                userRegister.setArea_id(erpUser.getArea_id());
            }
            if (erpUser.getStreet_id() != null) {
                userRegister.setStreet_id(erpUser.getStreet_id());
            }
            if (erpUser.getUser_address() != null && erpUser.getUser_address() != "") {
                userRegister.setUser_address(erpUser.getUser_address());
            }
            if (erpUser.getSales_area() != null && erpUser.getSales_area() != "") {
                userRegister.setSales_area(erpUser.getSales_area());
            }
            if (erpUser.getUser_description() != null && erpUser.getUser_description() != "") {
                userRegister.setUser_description(erpUser.getUser_description());
            }
            userRegister.setUser_cate(role_id);// TODO
            userRegister.setRole_id(role_id);
            userRegister.setRole_name(role_name);//20190405 清明节快乐喔 10:09
            userRegister.setCreatetime(sdf.format(new Date()));
            userRegister.setStatus_(1);
            userRegister.setRebate_balance(0.0);
            userRegister.setUser_price(0.0);
            userRegister.setAudit(0);
            result = userService.add(userRegister);
            if (customer_name != null) {
                String[] split = customer_name.split(",");
                if (split.length == 0) {// 插入终端客户数据
                    ErpTerminalCustomer customer = new ErpTerminalCustomer();

                    customer.setUser_id(userRegister.getId());
                    customer.setCustomer_name(customer_name);
                    customer.setCreatetime(sdf.format(new Date()));
                    customerService.insert(customer);
                } else {
                    for (int i = 0; i < split.length; i++) {
                        ErpTerminalCustomer customer = new ErpTerminalCustomer();
                        customer.setCustomer_name(split[i]);
                        customer.setUser_id(userRegister.getId());
                        customer.setCreatetime(sdf.format(new Date()));
                        customerService.insert(customer);
                    }
                }
            }
        } else if (erpUser.getRole_id() == (2)) {// 2.供应商
            if (erpUser.getUser_company_name() != null && erpUser.getUser_company_name() != "") {
                userRegister.setUser_company_name(erpUser.getUser_company_name());
            }
            if (erpUser.getProvince_id() != null) {
                userRegister.setProvince_id(erpUser.getProvince_id());
            }
            if (erpUser.getCity_id() != null) {
                userRegister.setCity_id(erpUser.getCity_id());
            }
            if (erpUser.getArea_id() != null) {
                userRegister.setArea_id(erpUser.getArea_id());
            }
            if (erpUser.getStreet_id() != null) {
                userRegister.setStreet_id(erpUser.getStreet_id());
            }
            userRegister.setUser_cate(role_id);// TODO
            userRegister.setRole_id(role_id);
            userRegister.setStatus_(1);
            userRegister.setRole_name(role_name);
            userRegister.setCreatetime(sdf.format(new Date()));
            userRegister.setRebate_balance(0.0);
            // userRegister.setUser_price(user_price);
            userRegister.setAudit(0);
            result = userService.add(userRegister);
            ErpSupplier supplier = new ErpSupplier();
            supplier.setUser_id(userRegister.getId());
            supplier.setSupplier_name(erpUser.getUser_name());
            /*
             * List<String> goodsName = goodsService.goodsName(); if
             * (goodsName != null){ for (String name : goodsName) { if
             * (name.equals(goods_name)){ return new
             * MessageBean(10010,"物料名称已重复",null); } } }
             */
            supplier.setGoods_name(goods_name);
            // supplier.setGoods_price(goods_price);
            supplier.setCreatetime(sdf.format(new Date()));
            supplierService.insert(supplier);

        } else if (erpUser.getRole_id() == (4)) {// 4.司机 TODO 相关表都插入数据了么
            //20190322 司机注册有公司名，在提货申请车辆选择有车辆所属公司
            userRegister.setUser_company_name(erpUser.getUser_company_name());
            //车辆所属公司，是车辆的属性，还是司机的属性，
            userRegister.setUser_cate(role_id);// TODO
            userRegister.setRole_id(role_id);
            userRegister.setCreatetime(sdf.format(new Date()));
            userRegister.setStatus_(1);
            userRegister.setRole_name(role_name);
            userRegister.setRebate_balance(0.0);
            userRegister.setAudit(0);
            //增加 car_id  先根据车牌号，查有木有，有加上，木有不加
            result = userService.add(userRegister);
            // TODO 有参数是因为修改车牌时，需要查本id以外的车牌来去重，为null时不加筛选即查全部
            List<ErpDriver> drivers = driverService.carsn(null);
            ErpDriver driver = new ErpDriver();
            driver.setUser_id(userRegister.getId());// YZH 没有ID吧？就有！
            driver.setDriver_sn(driver_sn);
            if (drivers != null) {
                for (ErpDriver driverObj : drivers) {
                    if (driverObj.getDriver_sn().equals(driver_sn)) {
                        userService.unaudit(userRegister.getId());
                        return new MessageBean(10010, "驾驶证号已经重复", null);
                    }
                }
            }
            sdf = new SimpleDateFormat("HH:mm:ss");
            driver.setDriver_use_date(driver_use_date + " " + sdf.format(new Date()));
            driver.setDriver_eff_date(driver_eff_date + " " + sdf.format(new Date()));
            driver.setCar_cate(car_cate);
            //20190322 车牌号可以重复
//					if (drivers != null) {
//						for (ErpDriver driverObj : drivers) {
//							if (driverObj.getCar_sn().equals(car_sn)) {
//								userService.unaudit(userRegister.getId());
//								return new MessageBean(10010, "车牌号已经重复", null);
//							}
//						}
//					}
            driver.setCar_sn(car_sn);
            sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            driver.setCreatetime(sdf.format(new Date()));
            driver.setStatus_(1);
            driverService.insert(driver);// TODO 注册司机只有这一步持久化操作
        } else {
            // userRegister.setUser_cate(erpUser.getUser_cate());
            userRegister.setUser_cate(role_id);// TODO
            userRegister.setRole_id(erpUser.getRole_id());// YZH ?
            userRegister.setRole_id(role_id);// YZH
            userRegister.setRole_name(role_name);
            userRegister.setCreatetime(sdf.format(new Date()));
            userRegister.setStatus_(1);
            userRegister.setRebate_balance(0.0);
            userRegister.setAudit(0);
            result = userService.add(userRegister);
        }
        if (result == 1) {// 就是搞一个用户表
            return new MessageBean(10001, "添加成功", null);
        }



        /*
         * List<ErpUser> lists = userService.lists_user_cate(); for (ErpUser
         * erpUser2 : lists) {
         * if(erpUser.getUser_name().equals(erpUser2.getUser_name())){ return
         * new MessageBean(10010, "用户名已存在", null); }
         *
         * if(erpUser.getUser_phone().equals(erpUser2.getUser_phone())){ return
         * new MessageBean(10020, "手机号已注册", null); }
         *
         * }
         */
        return new MessageBean(10000, "添加失败", result);

    }

    /**
     * 又改，改为并集赋值
     * <p>
     * 又改回去？。。。改来改去
     *
     * @param erpUser
     * @param customer_name
     * @param goods_name
     * @param driver_sn
     * @param driver_use_date
     * @param driver_eff_date
     * @param car_cate
     * @param car_sn
     * @param user_price
     * @param user_cate
     * @return 2019年2月25日下午4:53:10
     * @author : YZH
     */
    @RequestMapping("/reg2")
    public MessageBean reg2(ErpUser erpUser,
                            @RequestParam(value = "customer_name", required = false) String customer_name,
                            @RequestParam(value = "goods_name", required = false) String goods_name,
                            @RequestParam(value = "driver_sn", required = false) String driver_sn,
                            @RequestParam(value = "driver_use_date", required = false) String driver_use_date,
                            @RequestParam(value = "driver_eff_date", required = false) String driver_eff_date,
                            @RequestParam(value = "car_cate", required = false) String car_cate,
                            @RequestParam(value = "car_sn", required = false) String car_sn,
                            @RequestParam(value = "user_price", required = false) Double user_price,
                            @RequestParam(value = "user_cate", required = false) Integer user_cate) {

        // 判断用户名是否已注册
        if (userService.login(erpUser.getUser_name()) != null) {
            return new MessageBean(10010, "用户名已存在", null);
        }

        // 判断手机号码是否已注册
        if (userService.existsPhone(erpUser.getUser_phone(), null) != null) {
            return new MessageBean(10010, "该手机号已注册", null);
        }
        if (driver_sn != null && driver_sn != "") {
            // TO-DO 有参数是因为修改车牌时，需要查本id以外的车牌来去重，为null时不加筛选即查全部
            List<ErpDriver> drivers = driverService.carsn(null);
            if (drivers != null) {
                // 判断驾驶证号是否已注册
                if (driver_sn != null && driver_sn != "") {
                    for (ErpDriver driverObj : drivers) {
                        if (driverObj.getDriver_sn().equals(driver_sn)) {
                            return new MessageBean(10010, "驾驶证号已注册", null);
                        }
                    }
                }
                // 判断车牌号是否已注册
                if (car_sn != null && car_sn != "") {
                    for (ErpDriver driverObj : drivers) {
                        if (driverObj.getCar_sn().equals(car_sn)) {
                            return new MessageBean(10010, "车牌号已注册", null);
                        }
                    }
                }

            }
        }
        // set 然后 持久化操作，和增加权限的代码修改类似
        ErpUser userRegister = new ErpUser();

        // 注册结果
        int result = 0;
        result = userService.add(userRegister);
        if (result > 0) {
            return new MessageBean(10001, "注册成功", null);
        }

        return new MessageBean(10000, "注册失败", null);

    }
}
