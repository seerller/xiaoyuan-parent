package com.xiaoyuan.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.xiaoyuan.controller.CommonController.BaseController;
import com.xiaoyuan.model.ErpUser;
import com.xiaoyuan.tools.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyuan.model.ErpTerminalCustomer;
import com.xiaoyuan.service.IErpTerminalCustomerService;
import com.xiaoyuan.service.IErpUserService;
import com.xiaoyuan.tools.MessageBean;

/**
 * 终端客户，
 *
 * @author YZH
 * @author YZH
 * @version 2019年3月6日 下午4:01:06
 */
@CrossOrigin
@RestController
@RequestMapping("/ErpTerminal")
public class ErpTerminalCustomerController extends BaseController {
    @Autowired
    private IErpTerminalCustomerService terminalCustomerService;
    @Autowired
    private IErpUserService userService;


    //20100403 Wed 09:55 审核
    @RequestMapping("/audit")
    public MessageBean audit(Integer id, ErpTerminalCustomer erpTerminalCustomer) {
        if (id != null) {
            ErpTerminalCustomer erpTerminalCustomer1 = new ErpTerminalCustomer();
            erpTerminalCustomer1.setStatus_(1);
            erpTerminalCustomer1.setId(id);
            //用的是自动生成的 updateByIdSelective
            int result = this.terminalCustomerService.update(erpTerminalCustomer1);
            if (result == 1) {
                return new MessageBean(10001, "终端客户审核通过", result);
            }
            return new MessageBean(10000, "status_更新失败", result);
        }
        return new MessageBean(10000, "传参id为空", id);
    }

    @RequestMapping("/un_audit")
    public MessageBean un_audit(Integer id, ErpTerminalCustomer erpTerminalCustomer) {
        if (id != null) {
            ErpTerminalCustomer erpTerminalCustomer1 = new ErpTerminalCustomer();
            erpTerminalCustomer1.setStatus_(-1);
            erpTerminalCustomer1.setId(id);
            int result = this.terminalCustomerService.update(erpTerminalCustomer1);
            if (result == 1) {
                return new MessageBean(10001, "终端客户审核不通过", result);
            }
            return new MessageBean(10000, "status_更新失败", result);
        }
        return new MessageBean(10000, "传参id为空", id);
    }

    /**
     * debug 71 拟搞一个控制类方法，select from t where user_id = #{id}
     * <p>
     * 返回该用户的所有终端用户，前端需要传参id，好像可参考现成的lists方法及实现。。
     * <p>
     * 在查看SQL语句时找到了customers这个方法
     * <p>
     * //	 * @param token
     *
     * @return 2019年3月6日下午4:23:01
     * @author : YZH
     */
    @RequestMapping("/selectByUserId")
    public MessageBean selectByUserId(@RequestParam Integer id) {
        // 根据ID查lists...进去看看，，
        // List<ErpTerminalCustomer> lists = terminalCustomerService.lists(id);
        // if (lists != null) {
        // // 搞好。。。
        // StringBuffer stringBuffer = new StringBuffer();
        // for (ErpTerminalCustomer e : lists) {
        // stringBuffer.append(e.getCustomer_name() + ",");
        // }
        //
        // return new MessageBean(10001, "", stringBuffer);
        // }
        List<String> lists = terminalCustomerService.customer_names(id);
        if (lists != null) {
            return new MessageBean(10001, "", lists);
        }
        return new MessageBean(10000, "列表数据为空", lists);
    }

    @RequestMapping("/detail")
    public MessageBean detail(@RequestParam Integer id) {
        if (id != null) {
            ErpTerminalCustomer erpTerminalCustomer = terminalCustomerService.get(id);
            if (erpTerminalCustomer != null) {
                return new MessageBean(10001, "", erpTerminalCustomer);
            }
        }
        return new MessageBean(10000, "没有找到此信息", null);
    }

    @RequestMapping("/delete")
    public MessageBean delete(@RequestParam Integer id) {
        if (id != null) {
            int result = terminalCustomerService.delete(id);
            if (result > 0) {
                return new MessageBean(10001, "终端客户删除成功", null);
            }
        }
        return new MessageBean(10000, "终端删除失败", null);
    }

    /**
     * YZH debug 88 客户查看终端客户列表
     *
     * @param token
     * @return 2019年3月4日下午4:02:59
     * @author : YZH
     */
    @RequestMapping("/lists")
    public MessageBean lists(
            String customer_company_name,
            String token,
            Integer status_,
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize) {
        // 根据ID查lists...进去看看，，
//        List<ErpTerminalCustomer> lists = terminalCustomerService.lists(this.getUserId(token));
        List<ErpTerminalCustomer> lists = null;
        Integer id = this.userService.getUserIdByToken(token);
        ErpUser erpUser = this.userService.detail(id);
        if (erpUser.getRole_id() != 1) {//园区
            PageHelper.startPage(currentPage, pageSize);
            lists = terminalCustomerService.lists(null, status_,customer_company_name);
        } else {
            PageHelper.startPage(currentPage, pageSize);
            lists = terminalCustomerService.lists(id, status_,customer_company_name);
        }
        if (lists != null) {
            int countNums = lists.size();//总记录数
            PageBean<ErpTerminalCustomer> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(lists);
            return new MessageBean(10001, "", pageData.getItems());
        }
        return new MessageBean(10000, "列表数据为空", lists);
    }

    /**
     * 在哪里请求这个地址呢？
     *
     * @param erpTerminalCustomer
     * @param id
     * @param token
     * @return 2019年3月4日下午4:18:42
     * @author : YZH
     */
    @RequestMapping("/add")
    public MessageBean add(
            ErpTerminalCustomer erpTerminalCustomer,
            @RequestParam(value = "id", required = false) Integer id,
            String token) {


        ErpTerminalCustomer terminalCustomer = new ErpTerminalCustomer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        if (id != null) {
            terminalCustomer.setId(id);
            // TODO YZH 20190304 debug 88 这个公司名称在客户查看终端列表的时候SQL筛选条件为非空
            terminalCustomer.setCustomer_company_name(erpTerminalCustomer.getCustomer_company_name());
            terminalCustomer.setCustomer_company_sn(erpTerminalCustomer.getCustomer_company_sn());
            terminalCustomer.setCustomer_name(erpTerminalCustomer.getCustomer_name());
            terminalCustomer.setCustomer_sales_area(erpTerminalCustomer.getCustomer_sales_area());
            terminalCustomer.setCustomer_description(erpTerminalCustomer.getCustomer_description());
            terminalCustomer.setUpdatetime(sdf.format(new Date()));
            int result = terminalCustomerService.update(terminalCustomer);
            if (result == 1) {
                return new MessageBean(10001, "修改成功", null);
            }
            return new MessageBean(10000, "修改失败", null);
        } else {
            terminalCustomer.setUser_id(this.getUserId(token));
            terminalCustomer.setCustomer_company_name(erpTerminalCustomer.getCustomer_company_name());
            terminalCustomer.setCustomer_company_sn(erpTerminalCustomer.getCustomer_company_sn());
            terminalCustomer.setCustomer_sales_area(erpTerminalCustomer.getCustomer_sales_area());
            terminalCustomer.setCustomer_name(erpTerminalCustomer.getCustomer_name());
            terminalCustomer.setCustomer_description(erpTerminalCustomer.getCustomer_description());
            terminalCustomer.setTerminal_customer_address(erpTerminalCustomer.getTerminal_customer_address());
            terminalCustomer.setCreatetime(sdf.format(new Date()));
            int result = terminalCustomerService.insert(terminalCustomer);
            if (result == 1) {
                return new MessageBean(10001, "新增成功", null);
            }
            return new MessageBean(10000, "新增失败", null);
        }

    }

    /**
     * SQL根据user_id获取终端，
     * <p>
     * 这个方法是获取自己的终端客户，但是我要根据user_id查看终端客户，
     * <p>
     * 那还是用自己写的方法吧
     *
     * @param token
     * @return 2019年3月6日下午4:47:20
     * @author : YZH
     */
    @RequestMapping("/customers")
    public MessageBean customers(String token) {
        List<String> lists = terminalCustomerService.customer_names(this.getUserId(token));
        if (lists != null) {
            return new MessageBean(10001, "", lists);
        }
        return new MessageBean(10000, "列表数据为空", lists);
    }

}
