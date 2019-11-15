package com.xiaoyuan.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.xiaoyuan.controller.CommonController.BaseController;
import com.xiaoyuan.tools.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyuan.model.ErpAccount;
import com.xiaoyuan.model.ErpUser;
import com.xiaoyuan.service.IErpAccountService;
import com.xiaoyuan.service.IErpPickgoodsService;
import com.xiaoyuan.service.IErpUserService;
import com.xiaoyuan.tools.MessageBean;

/**
 * 到此一游，其他代码暂时没看，这个lists_recharge出错
 *
 * @author YZH
 * @author YZH
 * @version 2019年3月5日 下午7:14:36
 */
@CrossOrigin
@RestController
@RequestMapping("/ErpAccount")
public class ErpAccountController extends BaseController {
    @Autowired
    private IErpAccountService accountService;
    @Autowired
    private IErpUserService userService;
    @Autowired
    private IErpPickgoodsService pickgoodsService;

    /**
     * 充值
     *
     * @param erpAccount
     * @param user_cate
     * @param id            //@param session
     * @param account_price
     * @return
     */
    @RequestMapping("/recharge")
    public MessageBean recharge(
            @RequestParam(value = "id", required = false) Integer id,
            ErpAccount erpAccount,
            @RequestParam(value = "user_cate", required = false) Integer user_cate,
            @RequestParam(value = "account_price", required = false) Double account_price,
            String token) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        ErpAccount account = new ErpAccount();
        ErpUser user = new ErpUser();
        ErpUser user2 = userService.detail(getUserId(token));
        // ErpUser user2 = userService.detail(id);

        user.setUser_cate(user_cate);

        if (account_price > 0) {
            user.setRebate_balance(user2.getRebate_balance() + account_price);
            user.setUpdatetime(sdf.format(new Date()));
            userService.update(user);
        }

        // account.setUser_id(id);
        // account.setSupplier_id(1);
        account.setUser_id((Integer) (getUserId(token)));
        if (erpAccount.getAccount_serial() != null && erpAccount.getAccount_serial() != "") {
            account.setAccount_serial(erpAccount.getAccount_serial());
        }
        if (erpAccount.getAccount_bank_card() != null && erpAccount.getAccount_bank_card() != "") {
            account.setAccount_bank_card(erpAccount.getAccount_bank_card());
        }
        if (erpAccount.getAccount_remark() != null && erpAccount.getAccount_remark() != "") {
            account.setAccount_remark(erpAccount.getAccount_remark());
        }
        account.setAccount_price(account_price);
        account.setAudit(0);
        account.setCreatetime(sdf.format(new Date()));
        int result = accountService.insert(account);
        if (result > 0) {
            return new MessageBean(10001, "充值成功", null);
        }

        return new MessageBean(10000, "充值失败", null);
    }

    /**
     * 充值记录
     *
     * @param request
     *
     * @return
     *
     */
    /**
     * 充值记录
     * <p>
     * 上面的注释传参是 request ？？
     *
     * @param token
     * @return 2019年2月27日上午9:21:14
     * @author : YZH
     * <p>
     * debug 74 客户账户登录，查看充值记录
     * <p>
     * 2019年3月5日下午7:15:14
     * @author : YZH
     */
    @RequestMapping("/lists_recharge")
    public MessageBean lists_recharge(
            String token,
            Integer audit,
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize,
            String date1,
            String date2,
            String user_company_name) {
        // 根据传参token获得user对象
        ErpUser user2 = userService.detail(getUserId(token));// 这里出错了。。kehu01 id
        // 重复？？！！
        if (user2 != null) {// YZH
            // 角色类型为1或者3的时候，会查询数据库，否则直接返回数据列表为空
            if (user2.getRole_id() == 1) {// 客户 条件查询 where user_id=#{id}
                PageHelper.startPage(currentPage, pageSize);
                List<ErpAccount> list = accountService.lists_recharge(getUserId(token), audit, date1, date2, user_company_name);
                if (list != null) {
                    int countNums = list.size();//总记录数
                    PageBean<ErpAccount> pageData = new PageBean<>(currentPage, pageSize, countNums);
                    pageData.setItems(list);
                    return new MessageBean(10001, "", pageData.getItems());
                }
                // return null;
                return new MessageBean(10000, "数据列表为空", list);// YZH
            } else if (user2.getRole_id() == 3) {// 园区 查询所有
                PageHelper.startPage(currentPage, pageSize);
                List<ErpAccount> list = accountService.lists_recharge(null, audit, date1, date2, user_company_name);
                if (list != null) {
                    int countNums = list.size();//总记录数
                    PageBean<ErpAccount> pageData = new PageBean<>(currentPage, pageSize, countNums);
                    pageData.setItems(list);
                    return new MessageBean(10001, "", pageData.getItems());
                }
                // return new MessageBean(10002, "数据列表为空", list);
                return new MessageBean(10000, "数据列表为空", list);// YZH
            }
        }
        return new MessageBean(10002, "token找不到用户", null);
    }

    /**
     * 充值记录统计报表
     *
     * @return
     */
    @RequestMapping("/lists_recharge_statistical")
    public MessageBean lists_recharge_statistical(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize
    ) {

        PageHelper.startPage(currentPage, pageSize);
        //20190413 10:02 查看审核通过的
        List<ErpAccount> list = accountService.lists();
        if (list != null) {
            int countNums = list.size();//总记录数
            PageBean<ErpAccount> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(list);
            return new MessageBean(10001, "", pageData.getItems());
        }
        return new MessageBean(10002, "数据列表为空", list);
    }

    /**
     * 审核充值列表
     *
     * @param token
     * @return
     */
    /**
     * 审核充值列表
     * <p>
     * debug 73
     *
     * @param token
     * @return 2019年3月6日下午3:29:35
     * @author : YZH
     */
    @RequestMapping("/audit_recharge")
    public MessageBean audit_recharge(String token) {
        ErpUser role = userService.detail(getUserId(token));
        if (role.getRole_id() != null) {// 园区
            List<ErpAccount> list = accountService.lists();// SQL
            if (list != null) {
                return new MessageBean(10001, "", list);
            }
            return new MessageBean(10002, "数据列表为空", list);// 查了，查询结果为空
        }
        return new MessageBean(10002, "role_id为空", null);// 没查直接返回空
    }

    /**
     * 审核通过
     *
     * @param id //     * @param account
     * @return
     */
    @RequestMapping("/audit")
    public MessageBean audit(@RequestParam Integer id, String token, String user_name) {

        ErpUser user = userService.balance(getUserId(token));
        ErpAccount erpAccunt = accountService.get(id);
        Integer user_id = erpAccunt.getUser_id();
        ErpUser detail = userService.detail(user_id);
        if (id != null) {
            erpAccunt.setId(id);
            erpAccunt.setAudit(1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            erpAccunt.setAudit_datetime(sdf.format(new Date()));
            erpAccunt.setAudit_man(user_name);
            erpAccunt.setUpdatetime(sdf.format(new Date()));//TODO
            int result = accountService.update(erpAccunt);
            detail.setId(user_id);
            detail.setRebate_balance(detail.getRebate_balance() + erpAccunt.getAccount_price());
            userService.update(detail);
            if (result == 1) {
                ErpUser erpUser = this.userService.detail(erpAccunt.getUser_id());
                if (erpUser == null) {
                    return new MessageBean(10000, "根据充值记录的user_id找不到user", "user_id: " + user_id);
                }
                //返回手机号码
                return new MessageBean(10001, "审核入账通过", erpUser.getUser_phone());
            }
            return new MessageBean(10000, "操作失败", result);
        }
        return new MessageBean(10002, "id为空", null);
    }

    /**
     * 审核bu 通过
     *
     * @param id //     * @param account
     * @return
     */
    @RequestMapping("/unaudit")
    public MessageBean unaudit(@RequestParam Integer id, String token, String user_name) {

        ErpUser user = userService.balance(getUserId(token));
        ErpAccount erpAccunt = accountService.get(id);
        Integer user_id = erpAccunt.getUser_id();
        ErpUser detail = userService.detail(user_id);
        if (id != null) {
            erpAccunt.setId(id);
            erpAccunt.setAudit(-1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            erpAccunt.setAudit_datetime(sdf.format(new Date()));
            erpAccunt.setAudit_man(user_name);
            erpAccunt.setUpdatetime(sdf.format(new Date()));//TODO
            int result = accountService.update(erpAccunt);
            detail.setId(user_id);
            //detail.setRebate_balance(detail.getRebate_balance() + erpAccunt.getAccount_price());
            //	userService.update(detail);
            if (result == 1) {
                ErpUser erpUser = this.userService.detail(erpAccunt.getUser_id());
                if (erpUser == null) {
                    return new MessageBean(10000, "根据充值记录的user_id找不到user", "user_id: " + user_id);
                }
                //返回手机号码
                return new MessageBean(10001, "拒绝成功", erpUser.getUser_phone());
            }
            return new MessageBean(10000, "操作失败", result);
        }
        return new MessageBean(10002, "id为空", null);
    }

    /**
     * 余额
     *
     * @param token
     * @return 2019年3月6日上午9:21:44
     * @author : YZH
     */
    @RequestMapping("/balance")
    public MessageBean balance(String token) {
        ErpUser user = userService.balance(getUserId(token));
        if (user != null) {
            return new MessageBean(10001, "", user.getRebate_balance());
        }
        return null;
    }

    /**
     * 扣款
     * <p>
     * //     * @param session
     *
     * @param price
     * @return
     */
    @RequestMapping("/charge")
    public MessageBean charge(@RequestParam(value = "price", required = false) Double price,
                              @RequestParam(value = "id", required = false) Integer id) {
        Integer user_id = pickgoodsService.get(id).getUser_id();
        ErpUser user = userService.detail(user_id);
        user.setId(user.getId());
        if (price > 0.0) {
            user.setRebate_balance(user.getRebate_balance() - price);
        }
        int result = userService.update(user);
        if (result > 0) {
            return new MessageBean(10001, "扣款成功", null);
        }
        return new MessageBean(10000, "扣款异常", null);
    }

}
