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

import com.xiaoyuan.model.ErpCard;
import com.xiaoyuan.model.ErpDriver;
import com.xiaoyuan.service.IErpCardService;
import com.xiaoyuan.service.IErpDriverService;
import com.xiaoyuan.tools.MessageBean;

@CrossOrigin
@RestController
@RequestMapping("/ErpCard")
public class ErpCardController {
    @Autowired
    private IErpCardService cardService;
    @Autowired
    private IErpDriverService driverService;

    @RequestMapping("/delete")
    public MessageBean delete(@RequestParam("id") Integer id) {
        int delete = cardService.delete(id);
        if (delete > 0) {
            return new MessageBean(10001, "卡片删除成功", null);
        }
        return new MessageBean(10000, "卡片删除失败", null);

    }

    @RequestMapping("/detail")
    public MessageBean detail(@RequestParam("id") Integer id) {
        ErpCard card = cardService.get(id);
        if (card != null) {
            return new MessageBean(10001, "", card);
        }
        return new MessageBean(10000, "没有找到此信息", card);
    }

    @RequestMapping("/stop")
    public MessageBean stop(@RequestParam Integer id, ErpCard card) {
        if (id != null) {
            card.setId(id);
            card.setStatus_(0);
            int result = cardService.update(card);
            if (result > 0) {
                return new MessageBean(10001, "卡片已禁止", null);
            }
        }
        return new MessageBean(10000, "卡片禁止异常", null);
    }

    @RequestMapping("/lists")
    public MessageBean lists(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "50") Integer pageSize
    ) {
        PageHelper.startPage(currentPage, pageSize);
        List<ErpCard> list = cardService.lists();
        if (list != null) {
            int countNums = list.size();//总记录数
            PageBean<ErpCard> pageData = new PageBean<>(currentPage, pageSize, countNums);
            pageData.setItems(list);
            return new MessageBean(10001, "", pageData.getItems());
        }
        return new MessageBean(10000, "数据列表为空", list);
    }

    //20190408 Mon. 15:23
    @RequestMapping("/add")
    public MessageBean add(
            @RequestParam(value = "id", required = false) Integer id,
            ErpCard erpCard,
            @RequestParam(value = "car_sn", required = false) String car_sn) {
        if (id != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            ErpCard card2 = cardService.get(id);

            ErpDriver driver = new ErpDriver();
            if (car_sn != null && car_sn != "") {
                driver.setId(card2.getDriver_id());
                driver.setCar_sn(car_sn);
                driverService.update(driver);
            }
            ErpCard card = new ErpCard();
            card.setId(id);
            if (erpCard.getCard_rfid() != null && erpCard.getCard_rfid() != "") {
                card.setCard_rfid(erpCard.getCard_rfid());
            }
            if (erpCard.getCard_cate() != null && erpCard.getCard_cate() != "") {
                card.setCard_cate(erpCard.getCard_cate());
            }
            if (erpCard.getCard_state() != null && erpCard.getCard_state() != "") {
                card.setCard_state(erpCard.getCard_state());
            }
            if (erpCard.getCard_open_date() != null && erpCard.getCard_open_date() != "") {
//                card.setCard_open_date(sdf.format(erpCard.getCard_open_date()));
                card.setCard_open_date(erpCard.getCard_open_date());

            }
            card.setUpdatetime(sdf.format(new Date()));
            int result = cardService.update(card);
            if (result > 0) {
                return new MessageBean(10001, "卡片修改成功", null);
            }
        }
        return new MessageBean(10000, "id为空", null);
    }
}
