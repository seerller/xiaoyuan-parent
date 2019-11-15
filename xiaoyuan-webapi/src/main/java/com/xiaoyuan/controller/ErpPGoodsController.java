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
import com.xiaoyuan.service.IErpGoodsService;
import com.xiaoyuan.tools.MessageBean;

/**
 * 提货物料管理
 *
 * @author YZH
 * @version 2019年3月7日 上午10:07:15
 */
@CrossOrigin
@RestController
@RequestMapping("/ErpPGoods")
public class ErpPGoodsController {
    @Autowired
    private IErpGoodsService goodsService;

    /**
     * 、 提货物料列表
     *
     * @return 2019年3月7日上午10:07:25
     * @author : YZH
     */
    @RequestMapping("/lists")
    public MessageBean lists(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize,
            Integer goods_cate,
            Integer status_) {
        PageHelper.startPage(currentPage, pageSize);
        List<ErpGoods> lists = goodsService.lists(goods_cate, status_);
        if (lists != null) {
            int countNums = lists.size();//总记录数
            PageBean<ErpGoods> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(lists);
            return new MessageBean(10001, "", pageData.getItems());
        }
        return new MessageBean(10000, "数据列表为空", lists);
    }

    @RequestMapping("/detail")
    public MessageBean detail(Integer id) {
        ErpGoods goods = goodsService.get(id);
        if (goods != null) {
            return new MessageBean(10001, "", goods);
        }
        return new MessageBean(10000, "没有找到此信息", goods);
    }

    //表status_默认为null，这里又没有set=0，则拿出来的时候需要判断null转0？ -1,0,1,审拒，待审，审通，
    //20190329 Fri. 20:50
    //有传id就修改，没有就是新增
    @RequestMapping("/add")
    public MessageBean add(
            @RequestParam(value = "id", required = false) Integer id,
            ErpGoods erpGoods) {

        //为什么要重新new一个对象，直接用传参不行吗，
        ErpGoods goods = new ErpGoods();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//操作时间
        goods.setGoods_name(erpGoods.getGoods_name());//物料名称
        goods.setGoods_sn(erpGoods.getGoods_sn());//物料编号
        goods.setGoods_spec(erpGoods.getGoods_spec());//规格型号
        //差一个单位 20190329 Fri. 21:06
        goods.setGoods_cate(erpGoods.getGoods_cate());//物料分类
        goods.setGoods_man(erpGoods.getGoods_man());//申请人
        if (id == null) {
            goods.setCreatetime(sdf.format(new Date()));//录入时间
            //增加也有修改时间，即创建时间，然后列表按照修改时间排序
            goods.setUpdatetime(sdf.format(new Date()));
            int add = goodsService.add(goods);
            if (add == 1) {
                return new MessageBean(10001, "物品新增成功", add);
            } else {
                return new MessageBean(10000, "物料新增失败", add);
            }
        } else {
            goods.setId(id);
            goods.setUpdatetime(sdf.format(new Date()));//修改时间
            int add = goodsService.update(goods);
            if (add == 1) {
                return new MessageBean(10001, "物品修改成功", add);
            } else {
                return new MessageBean(10000, "物品修改失败", add);
            }
        }
//        return new MessageBean(10000, "物品操作失败", erpGoods);
    }

    @RequestMapping("/delete")
    public MessageBean delete(Integer id) {
        int delete = goodsService.delete(id);
        if (delete > 0) {
            return new MessageBean(10001, "删除成功", null);
        }
        return new MessageBean(10000, "删除失败", null);
    }

    @RequestMapping("/get_goodsmsg")
    public MessageBean getGoods(String goods_name) {
        ErpGoods goodsMsg = goodsService.searchGoodsMessageByGoodsName(goods_name);
        if (goodsMsg != null) {
            return new MessageBean(10001, "", goodsMsg);
        }
        return new MessageBean(10000, "", goodsMsg);

    }

}
