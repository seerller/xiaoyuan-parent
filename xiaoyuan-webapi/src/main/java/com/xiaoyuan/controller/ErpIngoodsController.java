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

import com.xiaoyuan.model.ErpCarOrder;
import com.xiaoyuan.model.ErpPickgoods;
import com.xiaoyuan.model.ErpPrice;
import com.xiaoyuan.model.ErpUser;
import com.xiaoyuan.service.IErpCarOrderService;
import com.xiaoyuan.service.IErpPickgoodsService;
import com.xiaoyuan.service.IErpPriceService;
import com.xiaoyuan.service.IErpUserService;
import com.xiaoyuan.tools.MessageBean;

/**
 * In 进，InGoods 进货
 *
 * @author YZH
 * @author YZH
 * @version 2019年3月8日 下午3:45:05
 */
@CrossOrigin
@RestController
@RequestMapping("/ErpIngoods")
public class ErpIngoodsController extends BaseController {

    @Autowired
    private IErpPickgoodsService pickgoodsService;
    @Autowired
    private IErpUserService userService;
    @Autowired
    private IErpCarOrderService carorderService;
    @Autowired
    private IErpPriceService priceService;

    /**
     * 请求参数要改回去，之前接口弄错，请求参数因此也改了，现在接口改回来，请求参数也要改回去了
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
     * @param car_sn
     * @param token
     * @return 2019年2月28日上午10:18:22
     * @author : YZH
     */
    @RequestMapping("/add")
    public MessageBean add(@RequestParam(value = "id", required = false) Integer id, String order_cate,
                           ErpPickgoods erpPickgoods, @RequestParam(value = "province_id", required = false) Integer province_id,
                           @RequestParam(value = "city_id", required = false) Integer city_id,
                           @RequestParam(value = "area_id", required = false) Integer area_id,
                           @RequestParam(value = "street_id", required = false) Integer street_id,
                           @RequestParam(value = "user_address", required = false) String user_address,
                           @RequestParam(value = "customer_name", required = false) String customer_name,
                           @RequestParam(value = "goods_name", required = false) String goods_name,
                           @RequestParam(value = "goods_sn", required = false) String goods_sn,
                           @RequestParam(value = "supplier_name", required = false) String supplier_name,
                           @RequestParam(value = "car_sn", required = false) String car_sn, String token) {

        int result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        order_cate = "采购";
        if ("采购".equals(order_cate)) {
            if (id != null) {
                ErpPickgoods ingoods = new ErpPickgoods();
                ingoods.setOrder_cate("采购");
                ingoods.setSupplier_id(id);
                ingoods.setCar_order_sn(order_sn());
                ingoods.setGoods_name(erpPickgoods.getGoods_name());
                ingoods.setGoods_sn(erpPickgoods.getGoods_sn());
                ingoods.setIngoods_price(erpPickgoods.getIngoods_price());
                ingoods.setGoods_spec(erpPickgoods.getGoods_spec());
                ingoods.setPickgoods_num(erpPickgoods.getPickgoods_num());
                ingoods.setIngoods_totalprice(erpPickgoods.getIngoods_totalprice());
                ingoods.setPickgoods_man(erpPickgoods.getPickgoods_man());
                if (erpPickgoods.getPickgoods_sendcate() == 1) {
                    ingoods.setPickgoods_car_sn(erpPickgoods.getPickgoods_car_sn());
                    ingoods.setPickgoods_send_company(erpPickgoods.getPickgoods_send_company());
                    ingoods.setPickgoods_remark(erpPickgoods.getPickgoods_remark());
                    ErpCarOrder order = new ErpCarOrder();
                    order.setCar_order_sn(ingoods.getCar_order_sn());
                    order.setPickgoods_id(erpPickgoods.getId());// TODO 有用？
                    carorderService.insert(order);
                }
                // 1.自取 2.预约送货
                ingoods.setPickgoods_sendcate(erpPickgoods.getPickgoods_sendcate());
                ingoods.setCreatetime(sdf.format(new Date()));
                ingoods.setAudit(0);
                ingoods.setStatus_(0);
                ErpPrice price = new ErpPrice();
                price.setSupplier_id(id);
                price.setGoods_name(erpPickgoods.getGoods_name());
                ErpPrice priceByMessage = priceService.selectByPriceMsg(price);
                if (priceByMessage != null) {
                    if (priceByMessage.getGoods_price() != null) {
                        ingoods.setStatus_(1);
                    }
                }
                result = pickgoodsService.insert(ingoods);
                if (result > 0) {
                    return new MessageBean(10001, "进货申请成功", null);
                }
            }
        }

        return new MessageBean(10000, "申请失败", null);
    }

    private String order_sn() {
        int random = (int) (Math.random() * 999999999 + 100000000);
        return String.valueOf(random) + ((int) (Math.random() * 10));
    }

    /**
     * part_audit 园区审核？
     * <p>
     * 预约审核
     *
     * @param id
     * @param ingoods
     * @return 2019年2月27日下午7:44:24
     * @author : YZH
     */
    @RequestMapping("/park_audit")
    public MessageBean audit2(@RequestParam Integer id, ErpPickgoods ingoods) {
        if (id != null) {
            ingoods.setAudit(2);
            ingoods.setId(id);
            int result = pickgoodsService.update(ingoods);
            if (result > 0) {
                return new MessageBean(10001, "预约审核成功", null);
            }
        }
        return new MessageBean(10000, "预约审核异常", null);
    }

    /**
     * 判断价格
     *
     * @return
     */
    @RequestMapping("/getpricebyingoods")
    public MessageBean getPriceByIngoods(@RequestParam("id") Integer supplier_id,
                                         @RequestParam("goods_name") String goods_name) {
        ErpPrice price = new ErpPrice();
        price.setSupplier_id(supplier_id);
        price.setGoods_name(goods_name);
        ErpPrice priceByMessage = priceService.selectByPriceMsg(price);
        if (priceByMessage != null) {
            if (priceByMessage.getGoods_price() != null) {
                return new MessageBean(10001, "已存在价格", priceByMessage.getGoods_price());
            } else {
                return new MessageBean(20001, "不存在价格", null);
            }
        }
        return new MessageBean(10000, "没有匹配的数据", null);
    }

    /**
     * 审核进货？
     *
     * @param id
     * @param ingoods
     * @return 2019年2月27日下午7:44:59
     * @author : YZH
     */
    @RequestMapping("/auditingoods")
    public MessageBean auditingoods(@RequestParam Integer id, ErpPickgoods ingoods) {
        if (id != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            ingoods.setAudit(1);
            ingoods.setAudittime(sdf.format(new Date()));
            ingoods.setId(id);
            int result = pickgoodsService.update(ingoods);
            if (result > 0) {
                return new MessageBean(10001, "审核成功", null);
            }
        }
        return new MessageBean(10000, "审核异常", null);
    }

    /**
     * KEYI
     * @param id
     * @param ingoods
     * @return 2019年2月27日下午7:45:39
     * @author : YZH
     */
    @RequestMapping("/unauditingoods")
    public MessageBean unauditingoods(@RequestParam Integer id, ErpPickgoods ingoods) {
        if (id != null) {
            ingoods.setAudit(0);
            ingoods.setId(id);
            int result = pickgoodsService.update(ingoods);
            if (result > 0) {
                return new MessageBean(10001, "审核拒绝", null);
            }
        }
        return new MessageBean(10000, "审核拒绝异常", null);

    }

    /**
     * 预约审核列表
     *
     * @return
     */
    @RequestMapping("/lists_audit")
    public MessageBean lists_audit() {
        List<ErpPickgoods> lists = pickgoodsService.lists_audit();
        if (lists != null) {
            return new MessageBean(10001, "", lists);
        }
        return new MessageBean(10000, "列表数据为空", lists);
    }

    /**
     * 进货单列表
     *
     * @param token
     * @return
     */
    /**
     * 园区审核供应商的价格确认，供应商录入车牌,
     * <p>
     * 园区账号登录没有看到数据，但是SQL语句有查到结果，debug一下，是不是没进去if
     *
     * @param token
     * @return 2019年3月1日下午4:10:31
     * @author : YZH
     */
    @RequestMapping("/lists_ingoods")
    public MessageBean lists_ingoods(
            String token,
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize) {
        ErpUser user = userService.detail(this.getUserId(token));
        List<ErpPickgoods> lists = null;
        System.out.println("登录用户的角色: " + user.getRole_id());
        if (user.getRole_id() == 3) {// 园区
            PageHelper.startPage(currentPage, pageSize);
            //TODO 20190404 Thur. 14:25 接单后复制此列？ 后加了是否接单列
            lists = pickgoodsService.lists_ingoods("not null");// 这里debug后看到lists的值是null
            int countNums = lists.size();//总记录数
            PageBean<ErpPickgoods> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(lists);
            return new MessageBean(10001, user.getRole_id().toString(), pageData.getItems());
        } else if (user.getRole_id() == 2) {// 供应商
            PageHelper.startPage(currentPage, pageSize);
            lists = pickgoodsService.lists_ingoods(null);
            int countNums = lists.size();//总记录数
            PageBean<ErpPickgoods> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(lists);
            return new MessageBean(20001, user.getRole_id().toString(), pageData.getItems());
        }
        return new MessageBean(10000, "登录角色不是供应商或园区", lists);
    }

    /**
     * 进货统计报表
     *
     * @return
     */
    @RequestMapping("/lists_statistical")
    public MessageBean lists_statistical(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize
    ) {
        PageHelper.startPage(currentPage, pageSize);
        List<ErpPickgoods> lists = pickgoodsService.lists_statistical();
        if (lists != null) {
            int countNums = lists.size();//总记录数
            PageBean<ErpPickgoods> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(lists);
            return new MessageBean(10001, "", lists);
        }
        return new MessageBean(10000, "列表数据为空", lists);
    }

    /**
     * 入库单
     */
    @RequestMapping("/in_warehouse")
    public MessageBean in_warehouse(
            @RequestParam(value = "id", required = false) Integer id) {
        ErpPickgoods ingoods = pickgoodsService.get(id);
        ErpPickgoods list = pickgoodsService.in_warehouse(ingoods.getCar_order_sn(), ingoods.getPickgoods_car_sn());
        if (list != null) {
            return new MessageBean(10001, "", list);
        }
        return new MessageBean(10000, "列表数据为空", list);

    }

    /**
     * 采购单
     *
     * @param id
     * @return
     */
    @RequestMapping("/purchase")
    public MessageBean purchase(@RequestParam(value = "id", required = false) Integer id) {
        ErpPickgoods ingoods = pickgoodsService.get(id);
        ErpPickgoods list = pickgoodsService.purchase(ingoods.getCar_order_sn(), ingoods.getPickgoods_car_sn());
        if (list != null) {
            return new MessageBean(10001, "", list);
        }
        return new MessageBean(10000, "列表数据为空", list);

    }

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
    public MessageBean audit(@RequestParam Integer id, ErpPickgoods pickgoods) {
        if (id != null) {
            pickgoods.setStatus_(1);
            pickgoods.setId(id);
            int result = pickgoodsService.update(pickgoods);
            if (result > 0) {
                return new MessageBean(10001, "审核通过", null);
            }
        }
        return new MessageBean(10000, "审核异常", null);

    }

    @RequestMapping("/unaudit")
    public MessageBean unaudit(@RequestParam Integer id, ErpPickgoods pickgoods) {
        if (id != null) {
            pickgoods.setStatus_(0);
            pickgoods.setId(id);
            int result = pickgoodsService.update(pickgoods);
            if (result > 0) {
                return new MessageBean(10001, "审核拒绝", null);
            }
        }
        return new MessageBean(10000, "审核异常", null);

    }

}
