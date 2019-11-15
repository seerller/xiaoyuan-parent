package com.xiaoyuan.model.search;

import com.xiaoyuan.model.dto.ErpAccountDto;
import com.xiaoyuan.model.dto.ErpCustomerDto;
import com.xiaoyuan.model.dto.ErpGpsAlarmDto;
import com.xiaoyuan.model.dto.ErpPickgoodsDto;
import com.xiaoyuan.model.exception.LogicException;
import com.xiaoyuan.tools.ExcelUtil2;
import com.xiaoyuan.tools.MessageBean;
import com.xiaoyuan.tools.TimeUtils;
import lombok.Data;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


/**
 * 基础条件搜索类
 * 主要作用用来接收前端传递过来的条件，然后进行条件搜索
 * @author zhengweicheng
 * createTime 2019-06-13
 */
@Data
public class BaseSearch {
    /**
     * 当前页码
     */
    private Integer currentPage = 1;
    /**
     * 最大显示多少条数据
     */
    private Integer pageSize    = 10;
    /**
     * 关键字搜索
     */
    private String search;

    /**
     * 多关键字搜索
     */
    private List<String> searchs;

    /**
     * 是查询所有还是只查询用户
     * 该功能仅园区角色用户查询方可进行判断
     * 默认查询单个用户
     */
    private Integer onlyOfAll = 1;

    public boolean isOnly(){
        if(onlyOfAll == 1){
            return true;
        }
        return false;
    }
    /**
     * 导出excel表格时使用
     * 1.账户明细excel表格导出
     * 2.终端客户excel
     * 3.出库单统计
     * 4.入库单统计
     * 5.入库异常统计报表
     * 6.GPS异常处理
     * 7.司机接单统计
     * 8.充值记录统计
     * 默认为账户明细
     */
    private Integer printType = 1;
    public static final List<Integer> printTypeArray = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8));
    public static final List<Integer> printTypeArrayByAccount = new ArrayList<>(Arrays.asList(1,8));
    public static final List<Integer> printTypeArrayByPickGoods = new ArrayList<>(Arrays.asList(3,5,4,7));
    public static final List<Integer> printTypeArrayByCustomer = new ArrayList<>(Arrays.asList(2));
    public static final List<Integer> printTypeArrayByGps = new ArrayList<>(Arrays.asList(6));

    /**
     * 判断是否是订单excel表格导出类型
     * @return
     */
    public void isPickGoodsPrintType(){
        if(printTypeArray.indexOf(this.printType) < 0){
            throw new LogicException("请选择正确的Excel表格导出类型.");
        }
    }
    public final static String[] outOrderHeader = {"序号","公司名称","订单号","物品名称","物料规格","物料编号","车牌号","驾驶员"
            ,"毛重(吨)","皮重(吨)","净重(吨)","挂牌扣款(元)","第一次称重时间","第二次称重时间","终端客户名称","终端客户编号","运输单位","司磅员"};
    public final static String[] inOrderHeader = {"序号","公司名称","订单号","物品名称","物料规格","物料编号","车牌号","驾驶员"
            ,"毛重(吨)","皮重(吨)","净重(吨)","第一次称重时间","第二次称重时间","运输单位","司磅员"};
    public final static String[] gpsArrayHeader = {"序号","车牌号","订单号","客户名称","设备ID号"
            ,"IMEI号","驾驶员","手机号码","送货地址","告警类型","报警时间","处理方式","处理人"};
    public final static String[] driverOrderHeader = {"序号","公司名称","终端客户名称","司机姓名","车牌号"
            ,"订单号","提货重量(吨)","送货地址","提货日期"};
    public final static String[] accountArrayHeader = {"序号","公司名称","公司编号","充值金额","类型"
            ,"充值时间","充值人","备注"};
    public final static String[] customerArrayHeader = {"序号","公司名称","公司编号","销售区域","终端客户","终端客户地址"
            ,"结算价"};

    public void resultExcelByGps(HttpServletResponse response, List<ErpGpsAlarmDto> result){
        List<List<String>> resultArray = new ArrayList<>();
        List<String> header = resultArrayList(gpsArrayHeader);
        String label,fileName;
        if(this.printType ==6){
            label = "GPS告警统计报表";
            fileName = "GPS告警统计报表";
        } else{
            throw new LogicException("请输入有效的excel表格导出类型");
        }
        resultArray.add(header);
        Integer index = 1;
        Iterator<ErpGpsAlarmDto> iterator  = result.iterator();
        while (iterator.hasNext()) {
            List<String> bodyContent = new ArrayList<>();
            ErpGpsAlarmDto val = iterator.next();
            bodyContent.add(String.valueOf(index++));
            bodyContent.add(val.getCarSn());
            bodyContent.add(val.getOrderSn());
            bodyContent.add(val.getCustomerName());
            bodyContent.add(val.getGps_sn());
            bodyContent.add(val.getGps_imei());
            bodyContent.add(val.getDriverName());
            bodyContent.add(val.getDriverPhone());
            bodyContent.add(val.getSend_address());
            bodyContent.add(val.getGpsAlarmCateTxt());
            bodyContent.add(val.getCreate_time());
            bodyContent.add(val.getGps_handling());
            bodyContent.add(val.getHandling_man());
            resultArray.add(bodyContent);
        }
        resultExcelByResponse(response,resultArray,label,fileName,15);
    }

    public void resultExcelByCustomer(HttpServletResponse response, List<ErpCustomerDto> result){
        List<List<String>> resultArray = new ArrayList<>();
        List<String> header = resultArrayList(customerArrayHeader);
        String label,fileName;
        if(this.printType == 2){
            label = "终端客户Excel表格";
            fileName = "终端客户Excel表格";
        } else{
            throw new LogicException("请输入有效的excel表格导出类型");
        }
        resultArray.add(header);
        Integer index = 1;
        Iterator<ErpCustomerDto> iterator  = result.iterator();
        while (iterator.hasNext()) {
            List<String> bodyContent = new ArrayList<>();
            ErpCustomerDto val = iterator.next();
            //序号
            bodyContent.add(String.valueOf(index++));
            bodyContent.add(val.getCustomer_company_name());
            bodyContent.add(val.getCustomer_company_sn());
            bodyContent.add(val.getCustomer_sales_area());
            bodyContent.add(val.getCustomer_name());
            bodyContent.add(val.getTerminal_customer_address());
            bodyContent.add(val.getPrice().toString());
            resultArray.add(bodyContent);
        }
        resultExcelByResponse(response,resultArray,label,fileName,15);
    }


    public void resultExcelByAccount(HttpServletResponse response, List<ErpAccountDto> result){
        List<List<String>> resultArray = new ArrayList<>();
        List<String> header = resultArrayList(accountArrayHeader);
        String label,fileName;
        if(this.printType == 1){
            label = "账户明细Excel表格";
            fileName = "账户明细Excel表格";
        }else if(this.printType == 8){
            label = "充值统计报表";
            fileName = "充值统计报表";
        } else{
            throw new LogicException("请输入有效的excel表格导出类型");
        }
        resultArray.add(header);
        Iterator<ErpAccountDto> iterator  = result.iterator();
        Integer index = 1;
        while (iterator.hasNext()) {
            List<String> bodyContent = new ArrayList<>();
            ErpAccountDto val = iterator.next();
            //序号
            bodyContent.add(String.valueOf(index++));
            bodyContent.add(val.getUser_company_name());
            bodyContent.add(val.getUser_company_sn());
            //充值金额
            bodyContent.add(val.getAccount_price().toString());
            //类型
            bodyContent.add(val.getAccountTypeTxt());
            //充值时间
            bodyContent.add(TimeUtils.resultCurrentDate(val.getCreateTimeTxt()));
            //充值人
            bodyContent.add(val.getAudit_man());
            //备注
            bodyContent.add(val.getAccount_remark());
            resultArray.add(bodyContent);
        }
        resultExcelByResponse(response,resultArray,label,fileName,15);
    }
    public void resultExcelByOrder(HttpServletResponse response, List<ErpPickgoodsDto> result){
        String label,fileName;
        List<List<String>> resultArray = new ArrayList<>();
        List<String> header;
        if(this.printType == 3){
            label = "出库单统计报表";
            fileName = "出库单统计报表";
            header = resultArrayList(outOrderHeader);
        }else if(this.printType == 4){
            label = "入库单统计报表";
            fileName = "入库单统计报表";
            header = resultArrayList(inOrderHeader);
        } else if(this.printType == 5){
            label = "入库异常统计报表";
            fileName = "入库异常统计报表";
            header = resultArrayList(inOrderHeader);
        }else if(this.printType == 7){
            label = "司机接单统计报表";
            fileName = "司机接单统计报表";
            header = resultArrayList(driverOrderHeader);
        } else{
            throw new LogicException("请输入有效的excel表格导出类型");
        }
        resultArray.add(header);
        Iterator<ErpPickgoodsDto> iterator  = result.iterator();
        Integer index = 1;
        while (iterator.hasNext()){
            List<String> bodyContent = new ArrayList<>();
            //序号->暂不需要
            //bodyContent.add(rowIndex.toString());
            ErpPickgoodsDto val = iterator.next();
            //序号
            bodyContent.add(String.valueOf(index++));
            //公司名称
            bodyContent.add(val.getCustomer_company_name());
            //TODO:根据类型对数据进行排序,并添加到resultArray数组内
            if(this.printType == 3||this.printType == 4 ||this.printType == 5){
                //订单号
                bodyContent.add(val.getCar_order_sn());
                //物品名称
                bodyContent.add(val.getGoods_name());
                //物料规格
                bodyContent.add(val.getGoods_spec());
                //物料编号
                bodyContent.add(val.getGoods_sn());
                //车牌号
                bodyContent.add(val.getPickgoods_car_sn());
                //驾驶员
                bodyContent.add(val.getDriver_name());
                //毛重(吨)
                bodyContent.add(val.getGrossweightUnit().toString());
                //皮重(吨)
                bodyContent.add(val.getTareUnit().toString());
                //净重(吨)
                bodyContent.add(val.getNetweightUnit().toString());
                //挂牌扣款(吨)
                if(this.printType == 3)bodyContent.add(String.valueOf(val.getDeduct_Price()));
                //第一次称重时间(吨)
                bodyContent.add(val.getFirstdate());
                //第二次称重时间(吨)
                bodyContent.add(val.getSeconddate());
                if(this.printType == 3){
                    // 终端客户名称
                    bodyContent.add(val.getCustomer_name());
                    // 终端客户编号
                    bodyContent.add(val.getCustomer_sn());
                }
                //运输单位
                bodyContent.add(val.getDriver_name());
                //司磅员
                bodyContent.add(val.getSquadron());
            }else{
                // 终端客户名称
                bodyContent.add(val.getCustomer_name());
                //驾驶员
                bodyContent.add(val.getDriver_name());
                //车牌号
                bodyContent.add(val.getPickgoods_car_sn());
                //订单号
                bodyContent.add(val.getCar_order_sn());
                //提货重量
                bodyContent.add(val.getNetweightUnit().toString());
                //送货地址
                bodyContent.add("["+val.getProvince_txt()+" " + val.getCity_txt() +" " +val.getArea_txt() +"]" +val.getUser_address());
                //送货地址
                bodyContent.add(val.getSendTime());
            }
            resultArray.add(bodyContent);
        }
        resultExcelByResponse(response,resultArray,label,fileName,15);
    }
    /**
     *  返回标题
     * @param header
     * @return
     */
    public List<String> resultArrayList(String[] header){
        return new ArrayList<String>(Arrays.asList(header));
    }
    public MessageBean resultExcelByResponse(HttpServletResponse response,  List<List<String>> print, String label,String fileName,
                                 int columnWidth){
        try {
            ExcelUtil2.exportExcel(response,print,label,fileName,columnWidth);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            throw new LogicException();
        }
    }

}
