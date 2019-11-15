package com.xiaoyuan.controller;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoyuan.service.*;

/**
 * 这个是干嘛用的呢，，
 *
 * @author YZH
 * @version 2019年3月8日 下午4:24:14
 */
@WebService(serviceName = "IBaseService", // 与接口中指定的name一致
        targetNamespace = "http://webservice.leftso.com/", // 与接口中的命名空间一致,一般是接口的包名倒
        endpointInterface = "com.xiaoyuan.controller.IBaseService"// 接口地址
)
@Service
public class BaseService implements IBaseService {
    @Autowired
    IErpDriverService driverService;
    @Autowired
    IErpPickgoodsService pickgoodsService;
    @Autowired
    IErpEquipmentService equipmentService;
    @Autowired
    IErpPoundService poundService;
    @Autowired
    IErpCarOrderService carOrderService;
    @Autowired
    IErpCarService carService;
    @Autowired
    IErpOrdersService ordersService;
    @Autowired
    IErpDeviceLogService deviceLogService;
    @Autowired
    IErpLoggerService loggerService;

    @Override
    public String EDCStdcall(String parameters) {
        return null;
    }
    /**
     * 无用函数，保留记录
     */
    /*// TODO YZH 20190308 干嘛用的呢，，
    public String EDCStdcall(String parameters) {
        String[] split = null;
        String[] str;
        String I_CMDType;
        String I_CMDList;
        str = parameters.split(";");
        I_CMDType = str[0].substring(10);
        I_CMDList = str[1].substring(10, str[1].length());
        if (I_CMDList != null && I_CMDList != "") {
            split = I_CMDList.split(",");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuffer buffer = null;
        switch (I_CMDType) {
            case "400":
                buffer = new StringBuffer();
                ErpDriver driver = driverService.getCarsnByRfid(I_CMDList);
                if (driver != null) {
                    buffer.append("1;" + driver.getCar_sn());
                    return buffer.toString();
                } else {
                    return "0;f";
                }
            case "401":
                ErpDriver driverQuenue = driverService.getQueueByRfid(I_CMDList);

                buffer = new StringBuffer("1;");
                if (driverQuenue != null) {
                    buffer.append(driverQuenue.getCar_sn());
                    buffer.append(";");
                    buffer.append(driverQuenue.getCar_order_sn());
                    return buffer.toString();
                } else {
                    return "0;f";
                }
            case "320":
                ErpPickgoods pickgoods = new ErpPickgoods();
                pickgoods.setPickgoods_car_sn(split[0]);
                pickgoods.setUser_id(Integer.valueOf(split[1]));
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                int i = 0;
                // pickgoodsService.maxOrderSn(o_id);
                String sn = "SO" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "-"
                        + (int) (Math.random() * (999 - 100) * 1000);
                pickgoods.setOrder_cate("销售");
                pickgoods.setCar_order_sn(sn);
                pickgoods.setCreatetime(sdf.format(new Date()));
                int result = pickgoodsService.insert(pickgoods);
                ErpCar car = new ErpCar();
                car.setCar_owner(split[0]);
                car.setCar_contact_phone(split[3]);
                // car.setCar_weight(Double.parseDouble(split[4]));
                ErpCar carByMsg = carService.getCarByMsg(car);

                ErpCarOrder order = new ErpCarOrder();
                order.setCar_order_num(Double.parseDouble(split[4]));
                order.setCar_order_sn(pickgoods.getCar_order_sn());
                order.setPickgoods_id(pickgoods.getId());
                if (order.getCar_order_num() > carByMsg.getCar_load()) {
                    return "0;提货重量不能大于最大载重";
                } else {
                    carOrderService.insert(order);
                    if (carByMsg != null) {
                        buffer = new StringBuffer();
                        buffer.append("1;" + sn);
                        buffer.append(";" + carByMsg.getCar_load());
                        return buffer.toString();
                    } else {
                        return "0;f";
                    }
                }
            case "160": {
                ErpDriver erpDriver = new ErpDriver();
                erpDriver.setCar_sn(split[0]);
                ErpDriver driver1 = driverService.driverChoosesn(erpDriver);
                ErpPound pound = new ErpPound();
                pound.setPickgoods_id(driver1.getO_id().toString());
                ErpEquipment equipment = new ErpEquipment();
                equipment.setEquipment_sn(split[2]);
                ErpEquipment erpEquipment = equipmentService.getErpEquipment(equipment);
                Integer id = erpEquipment.getId();
                ErpPound pound2 = poundService.getPoundMessageByPickgoodsid(pound);
                if (pound2 != null) {
                    pound = new ErpPound();
                    pound.setId(pound2.getId());
                    pound.setGrossweight(Double.parseDouble(split[3]));
                    pound.setNetweight(Double.parseDouble(split[3]) - pound2.getTare());
                    pound.setSeconddate(sdf.format(new Date()));
                    pound.setUpdatetime(sdf.format(new Date()));
                    int rs = poundService.updatePound(pound);
                    if (rs > 0) {
                        return "1;毛重称量成功";
                    } else {
                        return "0;f";
                    }
                } else {
                    pound.setCar_sn(split[0]);
                    pound.setCard_rfid(split[1]);
                    pound.setEquipment_id(id);
                    pound.setTare(Double.parseDouble(split[3]));
                    pound.setSquadron("高安智能过磅系统");
                    pound.setFirstdate(sdf.format(new Date()));
                    pound.setCreatetime(sdf.format(new Date()));
                    int rs = poundService.insertPound(pound);
                    if (rs > 0) {
                        return "1;皮重称量成功";
                    } else {
                        return "0;f";
                    }
                }
            }
            case "110": {
                ErpPickgoods pick = new ErpPickgoods();
                pick.setGoods_name(split[0]);
                pick.setGoods_sn(split[1]);
                ErpPickgoods pickgoods1 = pickgoodsService.getPound(pick);
                buffer = new StringBuffer("1;");
                if (pickgoods1 != null) {
                    buffer.append(pickgoods1.getCar_order_sn());
                    buffer.append(";");
                    buffer.append(pickgoods1.getPickgoods_car_sn());
                    buffer.append(";");
                    buffer.append(pickgoods1.getQueue_sn());
                    buffer.append(";");
                    buffer.append(pickgoods1.getPickgoods_num());
                    buffer.append(";");
                    buffer.append(pickgoods1.getNetweight());
                    buffer.append(";");
                    buffer.append(pickgoods1.getCar_load());
                    buffer.append(";");
                    buffer.append(pickgoods1.getGoods_sn());
                    return buffer.toString();
                } else {
                    return "0;f";
                }
            }
			//根据车牌号,查询该车牌号当前唯一可提货订单信息
			case "111": {
				*//*ErpPickgoods pick = new ErpPickgoods();
				pick.setGoods_name(split[0]);
				pick.setGoods_sn(split[1]);
				ErpPickgoods pickgoods1 = pickgoodsService.getPound_queue(pick);
				buffer = new StringBuffer("1;");
				if (pickgoods1 != null) {
					buffer.append(pickgoods1.getCar_order_sn());
					buffer.append(";");
					buffer.append(pickgoods1.getPickgoods_car_sn());
					buffer.append(";");
					buffer.append(pickgoods1.getQueue_sn());
					buffer.append(";");
					buffer.append(pickgoods1.getPickgoods_num());
					buffer.append(";");
					buffer.append(pickgoods1.getNetweight());
					buffer.append(";");
					buffer.append(pickgoods1.getCar_load());
					buffer.append(";");
					buffer.append(pickgoods1.getGoods_sn());
					return buffer.toString();
				} else {
					return "0;f";
				}*//*
			}
            case "300":
                List<ErpCarOrder> queueList = carOrderService.queueList();
                buffer = new StringBuffer();
                if (queueList != null) {
                    buffer.append("1;");
                    for (ErpCarOrder erpCarOrder : queueList) {
                        buffer.append(erpCarOrder.getQueue_sn());
                        buffer.append(";");
                        buffer.append(erpCarOrder.getCar_sn());
                        buffer.append("|");
                    }
                    return buffer.toString();
                } else {
                    return "0;f";
                }
            case "340":
                buffer = new StringBuffer();
                List<ErpEquipment> lists = equipmentService.lists();
                List<ErpEquipment> list = ExcelUtil.getDeviceList("D:\\poi\\data.xlsx");
                buffer.append("1;");
                if (lists == null && list.size() > 0) {
                    for (ErpEquipment erpEquipment : list) {
                        equipmentService.insert(erpEquipment);
                        buffer.append(erpEquipment.getEquipment_ip());
                        buffer.append(";");
                        buffer.append(erpEquipment.getEquipment_addr());
                        buffer.append(";");
                        buffer.append(erpEquipment.getEquipment_cate());
                        buffer.append(";");
                        buffer.append(erpEquipment.getEquipment_sn());
                        buffer.append(";");
                        buffer.append(erpEquipment.getEquipment_state());
                        buffer.append("|");
                    }
                    return buffer.toString();
                } else if (lists != null && list.size() > 0) {
                    for (ErpEquipment erpEquipment : list) {
                        buffer.append(erpEquipment.getEquipment_ip());
                        buffer.append(";");
                        buffer.append(erpEquipment.getEquipment_addr());
                        buffer.append(";");
                        buffer.append(erpEquipment.getEquipment_cate());
                        buffer.append(";");
                        buffer.append(erpEquipment.getEquipment_sn());
                        buffer.append(";");
                        buffer.append(erpEquipment.getEquipment_state());
                        buffer.append("|");
                    }
                    return buffer.toString();
                } else {
                    return "0;f";
                }
            case "410":
                ErpPound pound2 = new ErpPound();
                pound2.setCar_sn(split[0]);
                pound2.setCard_rfid(split[1]);
                ErpPound pound = poundService.getPound(pound2);
                buffer = new StringBuffer();
                if (pound != null) {
                    buffer.append("1;" + pound.getCreatetime());
                    buffer.append(";" + pound.getOrder_sn());
                    buffer.append(";" + pound.getCar_sn());
                    buffer.append(";" + pound.getUser_name());
                    buffer.append(";" + pound.getGoods_name());
                    buffer.append(";" + pound.getSupplier_spec());
                    buffer.append(";" + pound.getUser_company_name());
                    buffer.append(";" + pound.getFirstdate());
                    buffer.append(";" + pound.getSeconddate());
                    buffer.append(";" + pound.getGrossweight());
                    buffer.append(";" + pound.getNetweight());
                    buffer.append(";" + pound.getTare());
                    buffer.append(";" + pound.getSquadron());
                    buffer.append(";" + pound.getRemark());
                    buffer.append(";销售");
                    buffer.append(";" + pound.getGoods_sn());

                    *//** 司机确认后需改回 **//*
                    ErpOrders orders = new ErpOrders();
                    orders.setId(pound.getO_id());
                    orders.setStatus(2);
                    ordersService.update(orders);
                    return buffer.toString();
                } else {
                    return "0;f";
                }
            case "1000": {
                I_CMDType = str[0].substring(11);
                I_CMDList = str[1].substring(11, str[1].length());
                ErpDeviceLog deviceLog = new ErpDeviceLog();
                deviceLog.setRunMode(split[0]);
                deviceLog.setCameraNum(Double.parseDouble(split[1]));
                deviceLog.setCameraIP(split[2]);
                deviceLog.setDeviceID(split[3]);
                deviceLog.setDeviceIP(split[4]);
                deviceLog.setDeviceName(split[5]);
                deviceLog.setDeviceValue(split[6]);
                deviceLog.setSeqNo(split[7]);
                deviceLog.setMSG(split[8]);
                deviceLog.setCreatetime(sdf.format(new Date()));
                int result1 = deviceLogService.insert(deviceLog);
                if (result1 > 0) {
                    return "1;success";
                } else {
                    return "0;f";
                }
            }
            case "330":
                List<ErpOrders> whitelist = ordersService.whitelist();
                if (whitelist != null) {
                    buffer = new StringBuffer("1;");
                    for (ErpOrders erpOrders : whitelist) {
                        buffer.append(erpOrders.getCar_sn());
                        String[] starttime = erpOrders.getUpdatetime().split(" ");
                        String[] endtime = erpOrders.getEndtime().split(" ");
                        buffer.append(";W");
                        buffer.append(";" + starttime[0].replaceAll("-", "/"));
                        buffer.append(";" + endtime[0].replaceAll("-", "/"));
                        buffer.append(";" + starttime[1].substring(0, starttime[1].indexOf(".")));
                        buffer.append(";" + endtime[1].substring(0, endtime[1].indexOf(".")));
                        buffer.append("|");
                    }
                    return buffer.toString();
                } else {
                    return "0;f";
                }
            case "440": {
                ErpLogger logger = new ErpLogger();
                logger.setCar_sn(split[0]);
                logger.setCard_rfid(split[1]);
                logger.setDevice_id(split[2]);
                logger.setWeight(Double.parseDouble(split[3]));
                logger.setCreatetime(sdf.format(new Date()));
                int result1 = loggerService.insert(logger);
                if (result1 > 0) {
                    return "1;success";
                } else {
                    return "0;f";
                }
            }
            case "350": {
                List<ErpEquipment> list1 = equipmentService.getMsgByIP(split[0]);
                if (list1.size() > 0) {
                    buffer = new StringBuffer("1;");
                    for (ErpEquipment erpEquipment : list1) {
                        buffer.append(erpEquipment.getEquipment_ip());
                        buffer.append(";" + erpEquipment.getEquipment_addr());
                        buffer.append(";" + erpEquipment.getEquipment_cate());
                        buffer.append(";" + erpEquipment.getEquipment_sn());
                        buffer.append(";" + erpEquipment.getEquipment_state());
                        buffer.append("|");
                    }
                    return buffer.toString();
                } else {
                    return "0;f";
                }
            }

            case "100":
                List<ErpEquipment> list1 = equipmentService.getMsg();
                if (list1.size() > 0) {
                    buffer = new StringBuffer("1;");
                    for (ErpEquipment erpEquipment : list1) {
                        buffer.append(erpEquipment.getEquipment_ip());
                        buffer.append(";" + erpEquipment.getEquipment_position());
                        buffer.append("|");
                    }
                    return buffer.toString();
                } else {
                    return "0;f";
                }
            default:
        }
        return I_CMDType;
    }
*/
}
