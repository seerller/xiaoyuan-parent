package com.xiaoyuan.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.xiaoyuan.controller.CommonController.BaseController;
import com.xiaoyuan.model.*;
import com.xiaoyuan.service.*;
import com.xiaoyuan.tools.EncryptUtil;
import com.xiaoyuan.tools.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyuan.tools.MessageBean;

/**
 * 用户请求
 *
 * @author MyPC
 * @version v1.0
 */
@CrossOrigin
@RestController // json controller+resposeBody
@RequestMapping("/ErpUser")
public class ErpUserController extends BaseController {
    @Autowired
    IErpUserService userService;
    @Autowired
    IErpDriverService driverService;
    @Autowired
    IErpSupplierService supplierService;
    @Autowired
    IErpRebateService rebateService;
    // 搞多两个Service对象，完成审核通过操作的两张表记录写入
    @Autowired
    IUserRoleService userRoleService;
    @Autowired
    IErpCarService erpCarService;

    //TODO 20190404 Thur. 11:04 本来想传参id，根据id修改，但是代码提示有问题
    //验证码 Verification Code ？是放在这里吗还是统一用一个方法接收user_id
    @RequestMapping("/customer_update")
    public MessageBean customer_update(
            String token,
            Integer id,
            String user_contact,
            String user_phone,
            String user_telephone,
            String user_wechat,
            String sales_area,
            String price,
            String user_company_name
    ) {
        //联系人手机号，座机号，微信号，其余不修改，然后手机号校验参考注册
        //判断手机号
//        ErpUser erpUser = new ErpUser();
//        erpUser = this.userService.existsPhone(user_phone);
//        if (erpUser != null) {
//            return new MessageBean(10000, "手机号已经注册", erpUser);
//        }
//        erpUser.setId(id);
//        erpUser.setUser_contact(user_contact);
//        erpUser.setUser_phone(user_phone);
//        erpUser.setUser_telephone(user_telephone);
//        erpUser.setUser_wechat(user_wechat);
//        int result = this.userService.update(erpUser);
//        if (result == 1) {
//            return new MessageBean(10001, "修改客户资料成功", result);
//        }
//        return new MessageBean(10000, "修改客户资料失败", result);
//        if(id==null){
//            return new MessageBean(10000,"id为空",id);
//        }
        //TODO 手机号，不查本id的手机号，
        if (this.userService.existsPhone(user_phone, id) != null) {
            return new MessageBean(10000, "手机号已注册", user_phone);
        }
//        ErpUser erpUser = this.userService.existsPhone(user_phone);
//        //20190405 清明节快乐喔，查找全部用户，where id != 我的id  可以用SQL也可以用Java if(user.id!=id){if(user.phone=phone
//        if (erpUser != null) {
////            return new MessageBean(10000, "手机号已经注册", erpUser);
//        }
        ErpUser erpUser1 = this.userService.detail(id);
        if (erpUser1 == null) {
            return new MessageBean(10000, "根据token找不到用户对象", erpUser1);
        }
        erpUser1.setUser_contact(user_contact);
        erpUser1.setUser_phone(user_phone);
        erpUser1.setUser_telephone(user_telephone);
        erpUser1.setUser_wechat(user_wechat);
        int result = this.userService.update(erpUser1);
        if (result == 1) {
            ErpUser detail = this.userService.detail(id);
            return new MessageBean(10001, "修改客户资料成功", detail);
        }
        return new MessageBean(10000, "修改客户资料失败", result);
    }

    @RequestMapping("/driver_update")
    public MessageBean driver_update(
            String token,
            Integer id,
            String real_name,
            String user_phone,
            String user_wechat,
            String car_owner,
            String car_contact_phone,
            String car_sn,
            String car_cate,
            String car_length,
            String car_weight,
            String car_load

    ) {
        //从车牌号到载重不修改，如果园区需要修改可以考虑在这里修改
        //判断手机号
//        if (id == null) {
//            return new MessageBean(10000, "id为空", id);
//        }
//        ErpUser erpUser = this.userService.detail(this.userService.getUserIdByToken(token));
//        if (erpUser == null) {
//            return new MessageBean(10000, "根据token找不到用户对象", id);
//        }
//        ErpUser erpUser1 = this.userService.existsPhone(user_phone);
//        if (erpUser1 != null) {
////            return new MessageBean(10000, "手机号已经注册", erpUser1);
//        }
        //TODO 手机号，不查本id的手机号，
        if (this.userService.existsPhone(user_phone, id) != null) {
            return new MessageBean(10000, "手机号已注册", user_phone);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ErpUser erpUser = new ErpUser();
        erpUser.setId(id);
        erpUser.setReal_name(real_name);
        erpUser.setUser_wechat(user_wechat);
        ErpCar erpCar = new ErpCar();
        //user_id找driver，再用driver_id找车对象，找不到就修改不了了，一个车牌号的driver_id只对应一个driver，但是其实有多个司机
        //car的driver_id指向的driver的user_id指向的user是第一个注册这个车牌号的人，，，
        erpCar.setCar_owner(car_owner);
        erpCar.setCar_contact_phone(car_contact_phone);
        //根据user_id获得司机对象，再根据司机id获得车
        ErpDriver erpDriver = this.driverService.getDriverByUserId(id);
        if (erpDriver == null) {
            return new MessageBean(10000, "根据用户id找不到司机对象", erpDriver);
        }
        erpCar.setDriver_id(erpDriver.getId());
        //持久化
        int result = this.userService.update(erpUser);
        if (result == 0) {
            return new MessageBean(10000, "用户信息更新失败", erpUser);
        }
        if (result == 1) {
            erpCar.setUpdatetime(sdf.format(new Date()));//
            int result1 = this.erpCarService.updateByDriverId(erpCar);
            if (result1 == 1) {
                ErpUser detail = this.userService.detail(id);
                return new MessageBean(10001, "修改资料成功", detail);
            }
            return new MessageBean(10000, "修改车辆信息失败", result1);
        }
        return new MessageBean(10000, "修改资料失败", null);
    }

    @RequestMapping("/park_update")
    public MessageBean park_update(
            String token,
            ErpUser erpUser2,
            Integer id,
            String real_name,
            String user_phone,
            String user_wechat
    ) {
        //手机号校验，参考注册，
        //判断手机号
//        ErpUser erpUser1 = this.userService.existsPhone(user_phone);
//        if (erpUser1 != null) {
////            return new MessageBean(10000, "手机号已经注册", erpUser1);
//        }
        //TODO 手机号，不查本id的手机号，
        if (this.userService.existsPhone(user_phone, id) != null) {
            return new MessageBean(10000, "手机号已注册", user_phone);
        }
        ErpUser erpUser = new ErpUser();
        erpUser.setId(id);
        erpUser.setReal_name(real_name);
        erpUser.setUser_phone(user_phone);
        erpUser.setUser_wechat(user_wechat);
        //20190408
        int result = this.userService.update(erpUser2);
        if (result == 1) {
            ErpUser detail = this.userService.detail(id);
            return new MessageBean(10001, "修改园区资料成功", detail);
        }
        return new MessageBean(10000, "修改园区资料失败", result);
    }

    //TODO 修改资料应该要写四个接口，按照不同角色修改不同表 20190403
    @RequestMapping("/supplier_update")
    public MessageBean supplier_update(
            String token,
            Integer id,
            String user_contact,
            String user_phone,
            String user_wechat,
            String user_telephone
    ) {
        //判断手机号
//        ErpUser erpUser1 = this.userService.existsPhone(user_phone);
//        if (erpUser1 != null) {
////            return new MessageBean(10000, "手机号已经注册", erpUser1);
//        }
        //TODO 手机号，不查本id的手机号，
        if (this.userService.existsPhone(user_phone, id) != null) {
            return new MessageBean(10000, "手机号已注册", user_phone);
        }
        ErpUser erpUser = new ErpUser();
        erpUser.setId(id);
        erpUser.setUser_contact(user_contact);
        erpUser.setUser_phone(user_phone);
        erpUser.setUser_wechat(user_wechat);
        erpUser.setUser_telephone(user_telephone);
        int result = this.userService.update(erpUser);
        if (result == 1) {
            ErpUser detail = this.userService.detail(id);
            return new MessageBean(10001, "修改供应商资料成功", detail);
        }
        return new MessageBean(10000, "修改供应商资料失败", result);
    }

    //TODO 20190403 使用登录的密码判断，判断旧密码，使用注册的密码加密，保存新密码
    @RequestMapping("/updatePassword")
    public MessageBean updatePassword(
            String token,
            String login_name,
            String password1,
            String password2,
            String password_,
            Integer id) {

        //将传参现用密码解密，与传参明文旧密码比较，
        //如果旧密码正确，则user对象set id和密码，然后update，返回update是否成功

        // 将密文逆转 ，截取 salt盐的明文
        byte[] salt = EncryptUtil.decodeHex(password_.substring(0, 16));
        // 重新拼凑 盐+密码 进行sha1的加密
        byte[] hashPass = EncryptUtil.sha1(password1.getBytes(), salt, 1024);
        String newEcnryptPsd = EncryptUtil.encodeHex(salt) + EncryptUtil.encodeHex(hashPass);
        if (password_.equals(newEcnryptPsd)) {//旧密码加密后，与数据库的旧密码相同
            //加密新密码，并update返回结果
            //使用忘记密码的接口,
            ErpUser userRegister = new ErpUser();
            userRegister.setUser_name(login_name);
            byte[] random = EncryptUtil.generateSalt(8);
            // 2
            String randomHex = EncryptUtil.encodeHex(random);
            // 3+4
            String sha1Psd = EncryptUtil.encodeHex(EncryptUtil.sha1(password2.getBytes(), random, 1024));
            // 5
            String encryptPsd = randomHex + sha1Psd;
            // userRegister.setUser_phone(phone);
            userRegister.setPassword_(encryptPsd);
            int result = userService.forgetPassword(userRegister);
            if (result == 1) {
                return new MessageBean(10001, "修改密码成功", null);
            } else {
                return new MessageBean(10000, "修改密码失败", result);
            }
        }
        return new MessageBean(10000, "旧密码错误", null);


//        //1.将拿到的密码传参加密
//        //2.根据token拿到user对象，判断密码是否相等，
//        //3.调用update方法，返回结果
//
//        // 1：生成一个随机数
//        // 2:用可逆的加密算法加密随机数
//        // 3:将随机数和我们的密码 用sha1不可逆算法加密
//        // 4：将第三步得到的字符串值用可逆的加密算法加密
//        // 5：将第2步和第4步的值拼凑
//        // 1
//        //随机数怎么可能相等？？？看登录是怎么判断密码的，然后拿来这里用。。
//        byte[] random = EncryptUtil.generateSalt(8);
//        // 2
//        String randomHex = EncryptUtil.encodeHex(random);
//        // 3+4
//        String sha1Psd1 = EncryptUtil.encodeHex(EncryptUtil.sha1(password1.getBytes(), random, 1024));
//        String sha1Psd2 = EncryptUtil.encodeHex(EncryptUtil.sha1(password2.getBytes(), random, 1024));
//        // 5
//        String encryptPsd1 = randomHex + sha1Psd1;
//        String encryptPsd2 = randomHex + sha1Psd2;
//        String nowPassword = this.userService.detail(this.getUserId(token)).getPassword_();
//        if (encryptPsd1.equals(nowPassword)) {
//            ErpUser user = new ErpUser();
//            user.setPassword_(encryptPsd2);
//            int result = this.userService.update(user);
//            if (result == 1) {
//                return new MessageBean(10001, "修改密码成功", result);
//            }
//            return new MessageBean(10000, "修改密码失败", result);
//        }
//        return new MessageBean(10000, "旧密码错误", encryptPsd1);
    }

    /**
     * 审核
     *
     * @param id
     * @param token
     * @param role_id
     * @param user_price
     * @return 2019年2月22日下午5:03:35
     * @author : YZH
     * <p>
     * <p>
     * 司机审核失败，，查看SQL语句执行，，
     * <p>
     * 2019年2月28日下午2:50:30
     * @author : YZH
     */
    @RequestMapping("/audit")
    public MessageBean audit(
            @RequestParam(value = "id") Integer id, String token,
            @RequestParam(value = "role_id") Integer role_id,
            @RequestParam(value = "user_price", required = false) Double user_price) {

        ErpUser erpUser = this.userService.detail(id);
        System.out.println(erpUser);
        if (erpUser != null) {
            erpUser.setId(id);
            erpUser.setAudit(1);
            erpUser.setRole_id(role_id);
            //清明节快乐喔 20190405 Fri. 10:55
            erpUser.setUser_cate(role_id);
            erpUser.setUser_price(user_price);

            int update_result = this.userService.update(erpUser);
            System.out.println("update_result: " + update_result);
            if (update_result >= 1) {
                return new MessageBean(10001, "审核成功", null);
            }
        }
        return new MessageBean(10000, "审核失败", null);
    }
    // 这个暂时不写，看有没有已经实现的可以调用
    // @Autowired
    // IErpRoleService erpRoleService;

    /**
     * 用户审核
     *
     * @param tlErpUserRegister
     * @param id
     * @param driver_sn
     * @param driver_use_date
     * @param driver_eff_date
     * @param car_sn
     * @param goods_name
     * @param goods_price
     *            单价
     * @param usertype:
     *            1.客户 2.供应商 3.园区4.司机
     *
     *
     *            2019.2.21 Thur. 杨资宏
     *            这里还是按照四个来搞，这样不行，因为现在远远不止四个，这种硬编码不行，感觉可以根据数据库得到的类型group
     *            by进行循环？或者直接不按照类型，按照最多的数据列赋值
     *
     *            还是一种硬编码，还是通过动态获取赋值吧，，数据库，group by 然后装入遍历赋值，但要考虑赋值器。。
     *
     *            将原来的add方法改为fault_add，自己重新写一个，
     * @return
     */
    // TODO 审核通过按钮，只返回操作失败 debug

    /**
     * 用户审核
     *
     * @param erpUser
     * @param id
     * @param token
     * @param role_id
     * @param driver_sn
     * @param driver_use_date
     * @param driver_eff_date
     * @param car_sn
     * @param goods_name
     * @param goods_price
     * @return
     */
    @RequestMapping("/add")
    public MessageBean add(ErpUser erpUser, @RequestParam(value = "id", required = false) Integer id, String token,
                           @RequestParam(value = "role_id", required = false) Integer role_id,
                           @RequestParam(value = "driver_sn", required = false) String driver_sn,
                           @RequestParam(value = "driver_use_date", required = false) String driver_use_date,
                           @RequestParam(value = "driver_eff_date", required = false) String driver_eff_date,
                           @RequestParam(value = "car_sn", required = false) String car_sn,
                           @RequestParam(value = "goods_name", required = false) String goods_name,
                           @RequestParam(value = "goods_price", required = false) BigDecimal goods_price) {

        // 首先根据传入token拿到ErpUser对象role
        // ErpUser role = userService.detail(this.getUserId(token));

        // 判断ErpUser对象role的role_id是否等于一？
        // if (role.getRole_id() == (1)) {
        if (id != null) {
            ErpUser user = userService.detail(id);// 这是哪个id???
            // 搞多两个列对象分别来自两张表,把得到的值赋给表id
            // TO-DO
            // UserRole userRole = null;
            System.out.println(user);
            if (user != null) {
                user.setId(id);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                user.setRole_id(role_id);
                user.setUpdatetime(sdf.format(new Date()));
                // TO-DO 这个update执行的怎么样，是不是selective
                // 这个update有没有完成足够的事情
                int result = userService.update(user);
                if (result > 0) {
                    // 加多以下一行 同步代码块？
                    user.setAudit(1);
                    return new MessageBean(10001, "修改成功", null);
                }
            }
            // 20190221===========================================================================
        }
        // }
        return new MessageBean(10000, "id为空", null);
    }

    /**
     * @param userRoleId
     * @param userName
     * @param phone
     * @param comName
     * @param token
     * @return 2019年2月21日上午10:07:41
     * @author : YZH
     * <p>
     * ？？
     * <p>
     * 2019年3月7日下午4:12:18
     * @author : YZH
     */
    //TODO 审核？？ s和没s,真的搞晕了。。。。
    @RequestMapping(value = "/lists")
    public MessageBean lists(
            @RequestParam(value = "user_cate", required = false) Integer userRoleId,
            @RequestParam(value = "user_name", required = false) String userName,
            @RequestParam(value = "user_phone", required = false) String phone,
            @RequestParam(value = "company_name", required = false) String comName,
            Integer role_id,
            Integer audit,
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize,
            String token) {
        // ErpUser role = userService.detail(this.getUserId(token));
        ErpUser userRegister = new ErpUser();
        if (userRoleId != null) {
            userRegister.setUser_cate(userRoleId);
        }
        if (userName != null && userName != "") {
            userRegister.setUser_name(userName);
        }
        if (phone != null && phone != "") {
            userRegister.setUser_phone(phone);
        }
        if (comName != null && comName != "") {
            userRegister.setUser_company_name(comName);
        }
//        if (role_id == null) {
//            return new MessageBean(10000, "role_id为空", role_id);
//        }

        userRegister.setAudit(audit);
        userRegister.setRole_id(role_id);
        PageHelper.startPage(currentPage, pageSize);
        List<ErpUser> list = userService.list(userRegister);

        // ======
        // 这里可以这么改么，，，
        // if (role.getRole_id() == 3 || role.getRole_id() == 5) {
        // return new MessageBean(10001, "", list);
        // }
        // return new MessageBean(10000, "列表数据为空", list);

        if (list != null) {
            int countNums = list.size();//总记录数
            PageBean<ErpUser> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(list);
            return new MessageBean(10001, "", pageData.getItems());
        }
        return new MessageBean(10000, "数据列表为空", list);
        // ======

    }

    /**
     * 客户审核没有返回终端客户？
     *
     * @param id
     * @return 2019年3月2日上午9:39:14
     * @author : YZH
     */
    @RequestMapping(value = "/detail")
    public MessageBean detail(@RequestParam(value = "id", required = false) Integer id) {
        // Integer result=id!=null?Integer.valueOf(id):0;
        ErpUser get = userService.detail(id);
        if (get != null) {
            return new MessageBean(10001, "", get);
        }
        return new MessageBean(10000, "没有找到此信息", null);
    }

    /**
     * 获取公司名称
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "/get_company_name")
    public MessageBean getCompanyName(String token) {
        ErpUser user = userService.detail(this.getUserId(token));
        if (user != null) {
            return new MessageBean(10001, "", user.getUser_company_name());
        }
        return new MessageBean(10000, "没有找到此信息", null);
    }

    /**
     * status 0 冻结 1.解冻 冻结用户
     * <p>
     * //     * @param tlErpUserRegister
     *
     * @param id
     * @return
     */

    @RequestMapping("/freeze")
    public MessageBean freeze(ErpUser userRegister, @RequestParam Integer id) {
        if (id != null) {
            userRegister.setId(id);
            userRegister.setStatus_(0);
            int result = userService.update(userRegister);
            if (result > 0) {
                return new MessageBean(10001, "用户已冻结", null);
            }
        }
        return new MessageBean(10000, "用户冻结异常", null);

    }

    /**
     * 解冻用户
     * <p>
     * //     * @param tlErpUserRegister
     *
     * @param id
     * @return
     */

    @RequestMapping("/unfreeze")
    public MessageBean unfreeze(ErpUser userRegister, @RequestParam Integer id) {
        if (id != null) {
            userRegister.setId(id);

            userRegister.setStatus_(1);
            int result = userService.update(userRegister);
            if (result > 0) {
                return new MessageBean(10001, "用户已解冻", null);
            }
        }
        return new MessageBean(10000, "用户解冻异常", null);

    }

    /**
     * 用户审核
     *
     * @param tlErpUserRegister
     * @param id
     * @param driver_sn
     * @param driver_use_date
     * @param driver_eff_date
     * @param car_sn
     * @param goods_name
     * @param goods_price
     *            单价
     * @param usertype:
     *            1.客户 2.供应商 3.园区4.司机
     *
     *
     *            2019.2.21 Thur. 杨资宏
     *            这里还是按照四个来搞，这样不行，因为现在远远不止四个，这种硬编码不行，感觉可以根据数据库得到的类型group
     *            by进行循环？或者直接不按照类型，按照最多的数据列赋值
     *
     *            还是一种硬编码，还是通过动态获取赋值吧，，数据库，group by 然后装入遍历赋值，但要考虑赋值器。。
     * @return
     */
    // TO-DO 审核通过按钮，只返回操作失败 debug

    /**
     * 用户审核
     *
     * @param erpUser
     * @param id
     * @param token
     * @param role_id
     * @param driver_sn
     * @param driver_use_date
     * @param driver_eff_date
     * @param car_sn
     * @param goods_name
     * @param goods_price
     * @return
     */
    @RequestMapping("/fault_add")
    public MessageBean fault_add(ErpUser erpUser, @RequestParam(value = "id", required = false) Integer id,
                                 String token, @RequestParam(value = "role_id", required = false) Integer role_id,
                                 @RequestParam(value = "driver_sn", required = false) String driver_sn,
                                 @RequestParam(value = "driver_use_date", required = false) String driver_use_date,
                                 @RequestParam(value = "driver_eff_date", required = false) String driver_eff_date,
                                 @RequestParam(value = "car_sn", required = false) String car_sn,
                                 @RequestParam(value = "goods_name", required = false) String goods_name,
                                 @RequestParam(value = "goods_price", required = false) BigDecimal goods_price) {
        ErpUser role = userService.detail(this.getUserId(token));
        if (role.getRole_id() == (1)) {
            if (id != null) {
                ErpUser user = userService.detail(id);
                user.setId(id);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                if (user.getUser_cate() == 1) {
                    /*
                     * if (tlErpUserRegister.getProvince_id() != null) {
                     * userRegister.setProvince_id(tlErpUserRegister.
                     * getProvince_id()); } if (tlErpUserRegister.getCity_id()
                     * != null) {
                     * userRegister.setCity_id(tlErpUserRegister.getCity_id());
                     * } if (tlErpUserRegister.getArea_id() != null) {
                     * userRegister.setArea_id(tlErpUserRegister.getArea_id());
                     * } if (tlErpUserRegister.getStreet_id() != null) {
                     * userRegister.setStreet_id(tlErpUserRegister.getStreet_id(
                     * )); } if (tlErpUserRegister.getSales_area() != null &&
                     * tlErpUserRegister.getSales_area() != "") {
                     * userRegister.setSales_area(tlErpUserRegister.
                     * getSales_area()); }
                     */

                    user.setUser_price(erpUser.getUser_price());
                    user.setAudit(1);
                } else if (user.getUser_cate() == (2)) {
                    /*
                     * if (tlErpUserRegister.getUser_name() != null &&
                     * tlErpUserRegister.getUser_name() != "") {
                     * userRegister.setUser_name(tlErpUserRegister.getUser_name(
                     * )); }
                     */
                    user.setUser_cate(user.getUser_cate());
                    if (erpUser.getUser_company_name() != null && erpUser.getUser_company_name() != "") {
                        user.setUser_company_name(erpUser.getUser_company_name());
                    }
                    if (erpUser.getProvince_id() != null) {
                        user.setProvince_id(erpUser.getProvince_id());
                    }
                    if (erpUser.getCity_id() != null) {
                        user.setCity_id(erpUser.getCity_id());
                    }
                    if (erpUser.getArea_id() != null) {
                        user.setArea_id(erpUser.getArea_id());
                    }
                    if (erpUser.getStreet_id() != null) {
                        user.setStreet_id(erpUser.getStreet_id());
                    }
                    ErpSupplier supplier = new ErpSupplier();
                    supplier.setUser_id(id);
                    supplier.setGoods_name(goods_name);
                    supplier.setGoods_price(goods_price);
                    supplier.setUpdatetime(sdf.format(new Date()));
                    supplier.setStatus_(1);
                    supplierService.updateByUserid(supplier);
                    user.setAudit(1);
                } else if (user.getUser_cate() == (3)) {
                    /*
                     * if (tlErpUserRegister.getUser_name() != null &&
                     * tlErpUserRegister.getUser_name() != "") {
                     * userRegister.setUser_name(tlErpUserRegister.getUser_name(
                     * )); }
                     */
                    user.setUser_cate(user.getUser_cate());
                } else if (user.getUser_cate() == (4)) {
                    /*
                     * if (tlErpUserRegister.getUser_name() != null &&
                     * tlErpUserRegister.getUser_name() != "") {
                     * userRegister.setUser_name(tlErpUserRegister.getUser_name(
                     * )); }
                     */
                    /*
                     * user.setUser_cate(4); ErpDriver driver = new ErpDriver();
                     * driver.setUser_id(id); driver.setDriver_sn(driver_sn);
                     * driver.setDriver_use_date(sdf.format(driver_use_date));
                     * driver.setDriver_eff_date(sdf.format(driver_eff_date));
                     * driver.setCar_sn(car_sn);
                     * driver.setUpdatetime(sdf.format(new Date()));
                     * driverService.updateByUserid(driver);
                     */
                    user.setAudit(1);
                }
                user.setRole_id(role_id);
                user.setUpdatetime(sdf.format(new Date()));

                // TO-DO 这个update执行的怎么样，是不是selective
                int result = userService.update(user);
                if (result > 0) {
                    return new MessageBean(10001, "修改成功", null);
                }
                // 20190221===========================================================================
            }
        }
        return new MessageBean(10000, "修改失败", null);
    }

    @RequestMapping("/updateRebate")
    public MessageBean updateRebate(@RequestParam(value = "rebate_rate", required = false) BigDecimal rebate_rate,
                                    @RequestParam(value = "rebate_amount", required = false) BigDecimal rebate_amount,
                                    @RequestParam Integer id) {
        ErpRebate rebate = new ErpRebate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        rebate.setUser_id(id);
        if (rebate_rate != null) {
            rebate.setRebate_rate(rebate_rate);
        }
        if (rebate_amount != null) {
            rebate.setRebate_amount(rebate_amount);
        }
        rebate.setUpdatetime(sdf.format(new Date()));
        int result = rebateService.updateByUserid(rebate);
        if (result > 0) {
            return new MessageBean(10001, "返点修改成功", null);
        }
        return new MessageBean(10000, "返点修改失败", null);
    }

    // 审核

    /**
     * 用户审核界面的通过按钮
     * <p>
     * 这西瓜已经没有在用了，，，
     *
     * @param id
     * @param user
     * @return
     */
    @RequestMapping("/trash_audit")
    public MessageBean trash_audit(@RequestParam Integer id, ErpUser user) {
        if (id != null) {
            user.setId(id);
            user.setAudit(1);
            // TO-DO 这里完成审核的东西
            // user.setUser_role_id(user_role_id);

            int result = userService.update(user);
            if (result > 0) {
                return new MessageBean(10001, "此用户已审核", null);
            }
        }
        return new MessageBean(10000, "用户审核失败", null);
    }

    /**
     * 用户审核界面的拒绝按钮
     * <p>
     * 拒绝按钮只有两个返回值，操作失败和操作成功即审核拒绝通过
     * <p>
     * 审核不通过不是直接调用API赋值就行了么，，，
     * <p>
     * TO-DO:需要和Java仓哥沟通 为什么失败，因为前端传来的user_cate值不止这几个，不能用硬编码，阻碍项目扩展
     * 但是这里如果更改了，那会导致其他地方出现bug， 这里原本的业务逻辑是根据用户类型user_cate到对应的表中根据ID删除记录，
     * 用户注册，我审核，审核拒绝通过，如果用户再注册，两种情况，表中有数据，表中无数据，有数据则会重复，无数据如何实现去掉硬编码删除各表对应记录
     * <p>
     * 这里是审核不通过的业务执行，需要先根据传入的user对象，判断记录在哪个表中，然后根据ID删除
     *
     * @param id
     * @param user
     * @return
     */
    // TO-DO 审核拒绝按钮，只有操作失败，，，，debug
    @RequestMapping("/unaudit")
    public MessageBean unaudit(@RequestParam Integer id, ErpUser user) {
        if (id != null) {// TO-DO 20190220 看看走到了哪一步，，，debug
            int result = userService.unaudit(id);
            // 审核不通过，啥都不干，，，，，直接返回，，，操作执行成功，，，，，但其实啥都不干，，，
            // int result = 0;
            // int result2 = 0;
            // if (user.getUser_cate() == (1)) {// 为啥加小括号，这句判断啥都没干
            // // 用户类别等于1的时候，啥都不干
            // } else if (user.getUser_cate() == (2)) {
            // // 用户类别等于2的时候，，
            // // ErpSupplier supplier = new ErpSupplier();
            // // supplier.setUser_id(id);
            // result2 = supplierService.deleteByUserId(id);
            // } else if (user.getUser_cate() == (4)) {
            // // ErpDriver driver = new ErpDriver();
            // // driver.setUser_id(id);
            // result2 = driverService.deleteByUserId(id);
            // }
            // // user.setId(id);
            // result = userService.delete(id);
            // if (result > 0 && result2 > 0) {
            return new MessageBean(10001, "审核不通过", null);
            // }
        }
        // 没有规范文档吗，我可以随便更改吗，例如这个返回消息的内容
        // return new MessageBean(10000, "用户审核失败", null);
        return new MessageBean(10000, "操作失败", null);
    }
}
