package com.xiaoyuan.controller.WebController;

import com.xiaoyuan.controller.CommonController.BaseController;
import com.xiaoyuan.model.dto.ErpAccountDto;
import com.xiaoyuan.model.dto.ErpCustomerDto;
import com.xiaoyuan.model.dto.ErpGpsAlarmDto;
import com.xiaoyuan.model.dto.ErpPickgoodsDto;
import com.xiaoyuan.model.search.ErpAccountSearch;
import com.xiaoyuan.model.search.ErpGpsAlarmSearch;
import com.xiaoyuan.model.search.ErpPickGoodsSearch;
import com.xiaoyuan.model.search.ErpTerminalCustomerSearch;
import com.xiaoyuan.service.ErpAccountService;
import com.xiaoyuan.service.ErpGpsAlarmService;
import com.xiaoyuan.service.ErpPickgoodsService;
import com.xiaoyuan.service.ErpTerminalCustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * WEB端后台数据提交
 */
@RestController
@RequestMapping("/webExcel")
@Slf4j
@Transactional
public class WebExcelController extends BaseController{

    @Autowired
    private ErpPickgoodsService pickgoodsService;

    @Autowired
    private ErpAccountService accountService;

    @Autowired
    private ErpGpsAlarmService gpsAlarmService;
    @Autowired
    private ErpTerminalCustomerService customerService;

    /**
     *  返回订单订单excel表格供客户端下载
     * @param bean
     * @return
     */
    @RequestMapping(value ="/pickExcelPrint" , produces = "application/json;charset=utf-8")
    public void pickExcelPrint(ErpPickGoodsSearch bean, HttpServletResponse response) {
        bean.isPickGoodsPrintType();
        List<ErpPickgoodsDto> list = pickgoodsService.selectOrderBySearch(bean);
        bean.resultExcelByOrder(response,list);
    }
    /**
     *  返回充值相关excel表格供客户端下载
     * @param bean
     * @param response
     * @return
     */
    @RequestMapping("/accountExcelPrint")
    public void accountExcelPrint(ErpAccountSearch bean, HttpServletResponse response) {
        bean.isPickGoodsPrintType();
        List<ErpAccountDto> list = accountService.selectBySearch(bean);
        bean.resultExcelByAccount(response,list);
    }

    /**
     *  返回订单订单excel表格供客户端下载
     * @param bean
     * @return
     */
    @RequestMapping(value ="/customerExcelPrint" , produces = "application/json;charset=utf-8")
    public void customerExcelPrint(ErpTerminalCustomerSearch bean, HttpServletResponse response) {
        bean.isPickGoodsPrintType();
        List<ErpCustomerDto> list = customerService.selectBySearch(bean);
        bean.resultExcelByCustomer(response,list);
    }

    /**
     * 返回订单订单excel表格供客户端下载
     * @param bean
     * @return
     */
    @RequestMapping(value ="/gpsExcelPrint" , produces = "application/json;charset=utf-8")
    public void gpsExcelPrint(ErpGpsAlarmSearch bean, HttpServletResponse response) {
        bean.isPickGoodsPrintType();
        List<ErpGpsAlarmDto> list = gpsAlarmService.selectBySearch(bean);
        bean.resultExcelByGps(response,list);
    }

}
