package com.xiaoyuan.controller.SubController;

import com.xiaoyuan.controller.CommonController.BaseController;
import com.xiaoyuan.model.ErpDriver;
import com.xiaoyuan.model.dto.ErpPickgoodsDto;
import com.xiaoyuan.model.search.ErpPickGoodsSearch;
import com.xiaoyuan.service.IErpDriverService;
import com.xiaoyuan.service.IErpPickgoodsService;
import com.xiaoyuan.tools.MessageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 司机角色请求接口
 */
@RestController
@RequestMapping("/sub/car")
@Slf4j
public class SubCarController extends BaseController {
    @Autowired
    private IErpPickgoodsService pickgoodsService;
    @Autowired
    private IErpDriverService driverService;
    /**
     * 公众号接口：确认排队
     * @return
     */
    @RequestMapping("/confirmQueue")
    public MessageBean confirm_queue(Integer pickgoodsId) {
        //判断用户是否是司机角色
        if(!resultByUserCate(4)){
            return resultFailed("该功能只对司机角色开放");
            //判断参数是否存在
        }else if (pickgoodsId == null) {
            return new MessageBean(10000, "订单id为空",null);
        }
        ErpDriver driver = driverService.getDriverByUserId(resultUserId());
        if(null == driver){
            return resultFailed("请配合后台进行配置车辆信息.");
        }

        ErpPickGoodsSearch search = new ErpPickGoodsSearch();
        search.setUserId(resultUserId());
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        search.setNowTime(time);
        Integer count =  pickgoodsService.countIsConfirmQueueNum(resultUserId());
        //从数据库从取出该提货单。
        ErpPickgoodsDto pick = pickgoodsService.selectFirstOrderBySearch(new ErpPickGoodsSearch().setId(pickgoodsId));
        ;
        if(!driver.getId().equals(pick.getDriver_id())){
            return resultFailed("该订单不属于您的订单");
        }else if(pick.getConfirm_queue_device() !=0||pick.getIs_confirm_queue() != 0){
            return resultFailed("该订单已完成排队入厂,请勿重复操作.");
        }else if(count > 0){
            return resultFailed("该还有未出厂的销货单,无法排队.");
        }else if(pickgoodsService.selectIsConfirmQueueByTime(search) > 0 ){
            return resultFailed("已存在订单正在排队中，请勿重复操作.");
        }else if(pick.isQueueConfirm()){
            return resultFailed("该订单已完成排队进场操作.");
        }else if(pick.getOrder_status() == -1){
            return resultFailed("该订单已取消");
        }
        pick.setConfirm_queue_datetime(time);
        int result = this.pickgoodsService.updateById(pick);
        if (result == 1) {
            return resultSuccess("排队成功");
        }
        return resultFailed("确认排队失败");
    }

}
