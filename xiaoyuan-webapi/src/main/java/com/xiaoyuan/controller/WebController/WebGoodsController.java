package com.xiaoyuan.controller.WebController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.xiaoyuan.controller.CommonController.BaseController;
import com.xiaoyuan.model.exception.LogicException;
import com.xiaoyuan.model.search.ErpGoodsSearch;
import com.xiaoyuan.service.IErpUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoyuan.model.ErpGoods;
import com.xiaoyuan.service.IErpGoodsService;
import com.xiaoyuan.tools.MessageBean;

/**
 * ？？？
 *
 * @author YZH
 * @version 2019年3月8日 下午4:34:39
 */
@CrossOrigin
@RestController
@RequestMapping("/ErpGoods")
public class WebGoodsController extends BaseController {
    @Autowired
    private IErpGoodsService goodsService;
    @Autowired
    private IErpUserService userService;
    /**
     * @return 2019年3月7日下午2:08:26
     * @author : YZH
     */
    @RequestMapping("/lists")
    public MessageBean lists( ErpGoodsSearch search) {
        Map<String,Object> map = new HashMap<>();
        map.put("data",goodsService.selectBySearchOfPage(search));
        map.put("countNum",goodsService.countBySearch(search));
        return resultSuccess(map);
    }

    @RequestMapping("/detail")
    public MessageBean detail(Integer id) {
        ErpGoods goods = goodsService.get(id);
        if (goods != null) {
            return new MessageBean(10001, "", goods);
        }
        return new MessageBean(10000, "没有找到此信息", goods);
    }

    @RequestMapping("/add")
    public MessageBean add(ErpGoods erpGoods) {
        Integer user_id = resultUserId();
        ErpGoods goods = new ErpGoods();
        goods.setUser_id(user_id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ErpGoodsSearch search = new ErpGoodsSearch();
        search.setCateId(0);
        search.setGoodsId(erpGoods.getId());
        String oldGoodsSn = null;
        if (erpGoods.getId() != null) {
            erpGoods.setUpdatetime(sdf.format(new Date()));
            ErpGoods old = goodsService.selectFirstBySearch(search);
            if(null == old){
                throw new LogicException("请选择有效的物料");
            }
            oldGoodsSn = old.getGoods_sn();
        }else{
            if(erpGoods.getGoods_cate() == 1&&goodsService.findPickGoods()!=null){
                throw new LogicException("只允许存在一个销货商品");
            }else if(erpGoods.getGoods_cate() == 1){
                erpGoods.setGoods_type(true);
            }
            erpGoods.setStatus_(1);
            erpGoods.setCreatetime(sdf.format(new Date()));
        }
        //检验物料编号唯一性
        goodsService.goodsSnUnique(oldGoodsSn,erpGoods.getGoods_sn());
        //数据检验与数据准备完毕后开始修改数据库数据
        if(erpGoods.getId()!=null&&goodsService.update(erpGoods)<=0){
            throw new LogicException("修改物料失败.");
        }else if(erpGoods.getId()==null&&goodsService.insertService(erpGoods)<=0){
            throw new LogicException("新增物料失败.");
        }
        return resultSuccess();
    }

    @RequestMapping("/delete")
    public MessageBean delete(Integer id) {
        int delete = goodsService.delete(id);
        if (delete > 0) {
            return new MessageBean(10001, "删除成功", null);
        }
        return new MessageBean(10000, "删除失败", null);
    }
}
