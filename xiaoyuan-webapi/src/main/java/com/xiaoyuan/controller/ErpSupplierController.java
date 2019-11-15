package com.xiaoyuan.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.xiaoyuan.tools.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyuan.model.ErpGoods;
import com.xiaoyuan.model.ErpSupplier;
import com.xiaoyuan.model.ErpUser;
import com.xiaoyuan.service.IErpGoodsService;
import com.xiaoyuan.service.IErpSupplierService;
import com.xiaoyuan.service.IErpUserService;
import com.xiaoyuan.tools.MessageBean;

@CrossOrigin
@RestController
@RequestMapping("/ErpSupplier")
public class ErpSupplierController {
    @Autowired
    private IErpSupplierService supplierService;
    @Autowired
    private IErpGoodsService goodsService;
    @Autowired
    private IErpUserService userService;

    /**
     *
     * 园区修改供应商信息，
     *
     * 增加s表中的编号和u表中的角色类别，条件是传入的id（user.id），
     *
     * @param id
     * @param erpSany_address
     * @param company_name
     * @param user_wechat
     * @param user_telephone
     * @param user_phone
     * @param user_contact
     * @param company_snupplier
     * @param comp
     * @return
     *
     *        2019年3月2日下午2:56:59
     * @author : YZH
     */
    /**
     * 如果需要修改类别的话，还需要注意当不是供应商时，涉及表的信息处理，状态赋值或者删除行记录
     *
     * @param id
     * @param erpSupplier
     * @param role_id
     * @param company_address
     * @param company_name
     * @param user_wechat
     * @param user_telephone
     * @param user_phone
     * @param user_contact
     * @param supplier_sn
     * @param company_sn
     * @return 2019年3月4日上午9:11:16
     * @author : YZH
     */
    @RequestMapping("/add")
    public MessageBean add(@RequestParam(value = "id", required = false) Integer id, ErpSupplier erpSupplier,
                           @RequestParam(value = "role_id", required = false) Integer role_id,
                           @RequestParam(value = "company_address", required = false) String company_address,
                           @RequestParam(value = "company_name", required = false) String company_name,
                           @RequestParam(value = "user_wechat", required = false) String user_wechat,
                           @RequestParam(value = "user_telephone", required = false) String user_telephone,
                           @RequestParam(value = "user_phone", required = false) String user_phone,
                           @RequestParam(value = "user_contact", required = false) String user_contact,
                           @RequestParam(value = "supplier_sn", required = false) String supplier_sn,
                           @RequestParam(value = "company_sn", required = false) String company_sn) {

        int result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        ErpUser user = new ErpUser();

        // 1.传参等数据填充
        user.setUser_company_name(company_name);
        user.setUser_company_sn(company_sn);
        user.setUser_contact(user_contact);// TODO 检查SQL有没有写这个列
        user.setUser_phone(user_phone);
        user.setUser_telephone(user_telephone);
        user.setUser_wechat(user_wechat);
        user.setCompany_address(company_address);
        // YZH 在这里修改，，，
        // user.setUser_cate(2);
        // user.setRole_id(2);
        user.setUser_cate(role_id);
        user.setRole_id(role_id);

        ErpSupplier supplier = new ErpSupplier();// TODO 按表顺序填充，，规范真的好

        // 201910304 debug91 加了之后数据表中还是没有，前端也传了，需要查看
        supplier.setUser_id(user.getId());
        supplier.setSupplier_name(erpSupplier.getSupplier_name());
        supplier.setSupplier_sn(supplier_sn);// YZH 增加这个字段的修改 debug_doc_91
        // 这个联系人也没有持久化
        supplier.setSupplier_contact(erpSupplier.getSupplier_contact());
        supplier.setSupplier_telephone(erpSupplier.getSupplier_telephone());
        supplier.setSupplier_wechat(erpSupplier.getSupplier_wechat());
        supplier.setGoods_sn(erpSupplier.getGoods_sn());
        supplier.setGoods_name(erpSupplier.getGoods_name());
        supplier.setGoods_price(erpSupplier.getGoods_price());
        supplier.setGoods_unit(erpSupplier.getGoods_unit());
        // updatetime和createtime在下面判断赋值
        supplier.setStatus_(1);
        supplier.setSupplier_cate(erpSupplier.getSupplier_cate());
        supplier.setSupplier_audit(erpSupplier.getSupplier_audit());
        // model缺少supplier_spec属性
        supplier.setState(erpSupplier.getState());
        supplier.setGoods_cate(erpSupplier.getGoods_cate());
        // model缺少supplier_phone属性
        supplier.setCompany_name(company_name);
        supplier.setCompany_address(company_address);

        // 2.持久化（并返回结果）
        // 查看SQL
        if (id == null) {// 新增
            user.setCreatetime(sdf.format(new Date()));
            userService.add(user);
            supplier.setCreatetime(sdf.format(new Date()));
            result = supplierService.insert(supplier);// TO-DO YZH
            // 这样增加的供应商？没有user表的信息？
            if (result > 0) {
                return new MessageBean(10001, "供应商新增成功", null);
            }
        } else {// 修改
            user.setId(id);
            user.setUpdatetime(sdf.format(new Date()));
            userService.update(user);// TO-DO 查看SQL
            supplier.setUser_id(id);// mapper根据这个user_id修改的，，有点烂哦，，不是唯一键，改为非空全字段
            supplier.setUpdatetime(sdf.format(new Date()));

            // 20190304 TO-DO 查看SQL
            result = supplierService.updateByUserid(supplier);// 20190304
            // debug91修改信息不全，检查
            System.out.println("修改结果: " + result);
            if (result > 0) {
                return new MessageBean(10001, "供应商修改成功", null);
            }
        }
        return new MessageBean(10000, "供应商操作异常", null);
    }

    @RequestMapping("/trash_add")
    public MessageBean trash_add(@RequestParam(value = "id", required = false) Integer id, ErpSupplier erpSupplier,
                                 @RequestParam(value = "role_id", required = false) Integer role_id,
                                 @RequestParam(value = "company_address", required = false) String company_address,
                                 @RequestParam(value = "company_name", required = false) String company_name,
                                 @RequestParam(value = "user_wechat", required = false) String user_wechat,
                                 @RequestParam(value = "user_telephone", required = false) String user_telephone,
                                 @RequestParam(value = "user_phone", required = false) String user_phone,
                                 @RequestParam(value = "user_contact", required = false) String user_contact,
                                 @RequestParam(value = "supplier_sn", required = false) String supplier_sn,
                                 @RequestParam(value = "company_sn", required = false) String company_sn) {

        int result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        ErpUser user = new ErpUser();
        user.setUser_company_name(company_name);
        user.setUser_company_sn(company_sn);
        user.setUser_contact(user_contact);
        user.setUser_phone(user_phone);
        user.setUser_telephone(user_telephone);
        user.setUser_wechat(user_wechat);
        user.setCompany_address(company_address);
        // YZH 在这里修改，，，
        // user.setUser_cate(2);
        // user.setRole_id(2);
        user.setUser_cate(role_id);
        user.setRole_id(role_id);
        if (id == null) {
            user.setCreatetime(sdf.format(new Date()));
            userService.add(user);
        }
        ErpSupplier supplier = new ErpSupplier();
        supplier.setSupplier_sn(supplier_sn);// YZH 增加这个字段的修改 debug_doc_91
        supplier.setUser_id(user.getId());
        supplier.setSupplier_contact(erpSupplier.getSupplier_contact());
        supplier.setCompany_name(company_name);
        supplier.setSupplier_cate(erpSupplier.getSupplier_cate());
        supplier.setCompany_address(company_address);
        supplier.setGoods_sn(erpSupplier.getGoods_sn());
        supplier.setGoods_name(erpSupplier.getGoods_name());
        supplier.setGoods_price(erpSupplier.getGoods_price());
        supplier.setGoods_unit(erpSupplier.getGoods_unit());
        supplier.setStatus_(1);

        if (id == null) {
            supplier.setCreatetime(sdf.format(new Date()));
            result = supplierService.insert(supplier);// TO-DO YZH
            // 这样增加的供应商？没有user表的信息？
            if (result > 0) {
                return new MessageBean(10001, "供应商新增成功", null);
            }
        } else {
            user.setId(id);
            userService.update(user);
            supplier.setUser_id(id);
            user.setUpdatetime(sdf.format(new Date()));
            supplier.setUpdatetime(sdf.format(new Date()));
            result = supplierService.updateByUserid(supplier);
            if (result > 0) {
                return new MessageBean(10001, "供应商修改成功", null);
            }
        }
        return new MessageBean(10000, "供应商操作异常", null);
    }

    /**
     * 园区登录修改供应商信息，供应商管理，，返回供应商的所有信息
     *
     * @param goodsName
     * @param goodsName
     * @param goodsName
     * @return 2019年3月4日上午9:07:31
     * @author : YZH
     * <p>
     * 这个方法有两个用途，一个是园区填写进货申请的时候，需要根据物料名称去查找供应商，条件查询
     * <p>
     * 另外一个是园区对供应商信息进行管理，这个是无条件查询，，
     * <p>
     * 执行有无条件查询，根据传参是否有条件来判断选择决定
     * @author : YZH
     * <p>
     * 加入权限后，首先应该根据token获得id，再根据id获得user对象，然后根据user对象的role_id判断
     * <p>
     * 如果是园区就只上以上注释的判断，如果是供应商就只查该id的供应商的信息，否则返回空
     * @author : YZH
     * <p>
     * 数量啥的还有一些字段，可能是SQL没有连表查询，，这样的话修改可以再加一张表的全字段，
     * <p>
     * 2019年3月4日下午3:45:07
     * @author : YZH
     * <p>
     * <p>
     * <p>
     * 2019年3月4日下午3:49:46
     * @author : YZH
     */
    @RequestMapping("/lists")
    public MessageBean lists(
            @RequestParam(value = "goods_name", required = false) String goodsName,
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize,
            String search) {
        List<ErpSupplier> list = null;
        if (goodsName != null && goodsName != "") {
            // 根据goodsName查找供应商列表
            PageHelper.startPage(currentPage, pageSize);
            list = supplierService.list(goodsName, null);
        } else {

            // YZH debug_doc_91
            // 查到的数据缺少supplier_sn和goods_sn这两个，需要加上，连接查询，，，SQL语句，好像不用连接查询，直接where因为查的就是s表。。
            // 数据库表中的上述句中两个值皆为null，不是没查，是查了为null。。。
            // 还有其他的所有信息，都要返回
            PageHelper.startPage(currentPage, pageSize);
            list = supplierService.list(null, search);
        }
        if (list != null) {
            int countNums = list.size();//总记录数
            PageBean<ErpSupplier> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(list);
            return new MessageBean(10001, "", pageData.getItems());
        } else {
            return new MessageBean(10000, "列表数据为空", list);
        }
    }

    /**
     * TO-DO 填写进货申请会跳转到供应商列表
     *
     * @param goodsName
     * @return
     *
     *        2019年2月25日上午8:58:26
     * @author : YZH
     */
    /**
     * 园区账号，供应商管理，修改信息，要求有role_id查看查询语句，返回值，
     * <p>
     * 将这个改为listsbygoodsName
     *
     * @param goodsName
     * @return 2019年3月2日下午4:14:06
     * @author : YZH
     */
    @RequestMapping("/trash_lists")
    public MessageBean trash_lists(@RequestParam(value = "goods_name", required = false) String goodsName) {
        List<ErpSupplier> list = null;
        if (goodsName != null && goodsName != "") {
            // 根据goodsName查找供应商列表
            list = supplierService.list(goodsName, null);
        } else {
            list = supplierService.list(null, null);
        }
        if (list != null) {
            return new MessageBean(10001, "", list);
        }
        return new MessageBean(10000, "列表数据为空", list);
    }

    /**
     * 根据ID查找供应商
     * <p>
     * 可以获取到good_price属性
     *
     * @param id
     * @return 2019年2月25日下午8:12:36
     * @author : YZH
     */
    @RequestMapping("/detail")
    public MessageBean detail(@RequestParam Integer id) {
        ErpSupplier supplier = supplierService.get(id);
        if (supplier != null) {
            return new MessageBean(10001, "", supplier);
        }
        return new MessageBean(10000, "没有找到此信息", supplier);
    }

    @RequestMapping("/delete")
    public MessageBean delete(@RequestParam Integer id) {
        int result = supplierService.delete(id);
        if (result > 0) {
            return new MessageBean(10001, "供应商删除成功", null);
        }
        return new MessageBean(10000, "供应商删除失败", null);
    }

    @RequestMapping("/stop")
    public MessageBean stop(@RequestParam Integer id, ErpSupplier supplier) {
        if (id != null) {
            supplier.setStatus_(0);
            supplier.setId(id);
            int result = supplierService.update(supplier);
            if (result > 0) {
                return new MessageBean(10001, "供应商禁止成功", null);
            }
        }
        return new MessageBean(10000, "供应商禁止异常", null);
    }

    // 获取物料名称列表

    /**
     * 获取物料名称列表
     * <p>
     * KEYI
     *
     * @param goods_cate
     * @param goods_cate
     * @return 2019年3月4日上午9:11:44
     * @author : YZH
     * <p>
     * <p>
     * postman能查到信息，前端查不到？
     * @author : YZH
     */
    @RequestMapping("/goods_name")
    public MessageBean goods_name(Integer goods_cate) {
        List<ErpGoods> goodsName = goodsService.goodsName(goods_cate);
        if (goodsName != null) {
            return new MessageBean(10001, "", goodsName);
        }
        return new MessageBean(10000, "列表数据为空", goodsName);
    }

}
