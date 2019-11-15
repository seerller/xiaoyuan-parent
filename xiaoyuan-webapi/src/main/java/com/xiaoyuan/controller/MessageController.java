package com.xiaoyuan.controller;


import com.xiaoyuan.model.Message;
import com.xiaoyuan.service.IMessageService;
import com.xiaoyuan.tools.MessageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author yzh
 */
@RestController
@RequestMapping("/Message")
@CrossOrigin
public class MessageController {

    @Autowired
    IMessageService messageService;


    //可以使用一个方法，然后根据传参进行赋值
    //可以写成properties文件

    @RequestMapping("sendMessage")
    public MessageBean sendMessage(
            String phone,
            String type,
            String car_order_sn,
            String warehouse_sn,
            String pickgoods_num) {

        //生产随机数 及发送短信
        /*
         * 返回前端，前端校验，简单一点，正常应该是后端检验d、
         * */
        //生成短信验证码
        String verifyCode = String
                .valueOf(new Random().nextInt(899999) + 100000);


        Message message = new Message();
        message.set_PhoneNumbers(phone);

        if (type.equals(message.getRegister())) {
            message.set_TemplateCode("SMS_162456282");
            message.set_TemplateParam("{\"code\":" + verifyCode + "}");
        }
        if (type.equals(message.getRegisterAudit())) {
            message.set_TemplateCode("SMS_162635028");
        }
        if (type.equals(message.getRegisterUnAudit())) {
            message.set_TemplateCode("SMS_162635031");
        }
        if (type.equals(message.getPickgoodsAudit())) {
            message.set_TemplateCode("SMS_162635032");
            message.set_TemplateParam("{\"code\":" + car_order_sn + "}");
        }
        if (type.equals(message.getPickgoodsUnAudit())) {
            message.set_TemplateCode("SMS_162630041");
            message.set_TemplateParam("{\"code\":" + car_order_sn + "}");
        }
        if (type.equals(message.getIsCarOrder())) {
            message.set_TemplateCode("SMS_162630042");
            message.set_TemplateParam("{\"code\":" + car_order_sn + "}");
        }
        if (type.equals(message.getNoCarOrder())) {
            message.set_TemplateCode("SMS_162635038");
            message.set_TemplateParam("{\"code\":" + car_order_sn + "}");
        }
        if (type.equals(message.getOutWareHouse())) {
            message.set_TemplateCode("SMS_162630043");
            message.set_TemplateParam("{\"A\":" + car_order_sn + ",\"B\":" + warehouse_sn + ",\"C\":" + pickgoods_num + "}");
        }
        if (type.equals(message.getRechargeAudit())) {
            message.set_TemplateCode("SMS_162635040");
        }
        if (type.equals(message.getRechargeUnAudit())) {
            message.set_TemplateCode("SMS_162635042");
        }
        if (type.equals(message.getIngoodsAudit())) {
            message.set_TemplateCode("SMS_162630046");
        }
        if (type.equals(message.getIngoodsUnAudit())) {
            message.set_TemplateCode("SMS_162635048");
        }
        if (type.equals(message.getGoodsAudit())) {
            message.set_TemplateCode("SMS_162635050");
        }
        if (type.equals(message.getGoodsUnAudit())) {
            message.set_TemplateCode("SMS_162635052");
        }
        if (message.getTemplateCode() == null) {
            return new MessageBean(10000, "短信类型传参不匹配", type);
        }

        int is_send = messageService.Send(message);

        if (is_send == 1) {
            //成功发送
            return new MessageBean(10001, "短信发送成功", verifyCode);
        } else {
            //发送失败
            return new MessageBean(10000, "短信发送失败", verifyCode);
        }
    }


//    @RequestMapping("register_audit")
//    public MessageBean register_audit(String phone) {
//        //生产随机数 及发送短信
//        /*
//         * 返回前端，前端校验，简单一点，正常应该是后端检验d、
//         * */
//        String verifyCode = String
//                .valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
//        Message message = new Message();
//        message.set_PhoneNumbers(phone);
//        message.set_TemplateCode("SMS_162635028");
//        // Message.set_TemplateParam("{\"code\":\""+verifyCode+"\"}");
//        message.set_TemplateParam("{\"A\":\"订单号\",\"C\":\"订单号\",}");
//        int is_send = messageService.Send(message);
//        if (is_send == 1) {
//            //成功发送
//            return new MessageBean(10001, "短信发送成功", verifyCode);
//        } else {
//            //发送失败
//            return new MessageBean(10000, "短信发送失败", verifyCode);
//        }
//    }
//
//    @RequestMapping("register_unaudit")
//    public MessageBean register_unaudit(String phone) {
//        //生产随机数 及发送短信
//        /*
//         * 返回前端，前端校验，简单一点，正常应该是后端检验d、
//         * */
//        String verifyCode = String
//                .valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
//        Message message = new Message();
//        message.set_PhoneNumbers(phone);
//        message.set_TemplateCode("SMS_162635031");
//        // Message.set_TemplateParam("{\"code\":\""+verifyCode+"\"}");
//        message.set_TemplateParam("{\"A\":\"订单号\",\"C\":\"订单号\",}");
//        int is_send = messageService.Send(message);
//        if (is_send == 1) {
//            //成功发送
//            return new MessageBean(10001, "短信发送成功", verifyCode);
//        } else {
//            //发送失败
//            return new MessageBean(10000, "短信发送失败", verifyCode);
//        }
//    }
//
//    @RequestMapping("pickgoods_audit")
//    public MessageBean pickgoods_audit(String phone) {
//        //生产随机数 及发送短信
//        /*
//         * 返回前端，前端校验，简单一点，正常应该是后端检验d、
//         * */
//        String verifyCode = String
//                .valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
//        Message message = new Message();
//        message.set_PhoneNumbers(phone);
//        message.set_TemplateCode("SMS_162635032");
//        // Message.set_TemplateParam("{\"code\":\""+verifyCode+"\"}");
//        message.set_TemplateParam("{\"A\":\"订单号\",\"C\":\"订单号\",}");
//        int is_send = messageService.Send(message);
//        if (is_send == 1) {
//            //成功发送
//            return new MessageBean(10001, "短信发送成功", verifyCode);
//        } else {
//            //发送失败
//            return new MessageBean(10000, "短信发送失败", verifyCode);
//        }
//    }
//
//    @RequestMapping("pickgoods_unaudit")
//    public MessageBean pickgoods_unaudit(String phone) {
//        //生产随机数 及发送短信
//        /*
//         * 返回前端，前端校验，简单一点，正常应该是后端检验d、
//         * */
//        String verifyCode = String
//                .valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
//        Message message = new Message();
//        message.set_PhoneNumbers(phone);
//        message.set_TemplateCode("SMS_162630041");
//        // Message.set_TemplateParam("{\"code\":\""+verifyCode+"\"}");
//        message.set_TemplateParam("{\"A\":\"订单号\",\"C\":\"订单号\",}");
//        int is_send = messageService.Send(message);
//        if (is_send == 1) {
//            //成功发送
//            return new MessageBean(10001, "短信发送成功", verifyCode);
//        } else {
//            //发送失败
//            return new MessageBean(10000, "短信发送失败", verifyCode);
//        }
//    }
//
//    @RequestMapping("is_car_order")
//    public MessageBean is_car_order(String phone) {
//        //生产随机数 及发送短信
//        /*
//         * 返回前端，前端校验，简单一点，正常应该是后端检验d、
//         * */
//        String verifyCode = String
//                .valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
//        Message message = new Message();
//        message.set_PhoneNumbers(phone);
//        message.set_TemplateCode("SMS_162630042");
//        // Message.set_TemplateParam("{\"code\":\""+verifyCode+"\"}");
//        message.set_TemplateParam("{\"A\":\"订单号\",\"C\":\"订单号\",}");
//        int is_send = messageService.Send(message);
//        if (is_send == 1) {
//            //成功发送
//            return new MessageBean(10001, "短信发送成功", verifyCode);
//        } else {
//            //发送失败
//            return new MessageBean(10000, "短信发送失败", verifyCode);
//        }
//    }
//
//    @RequestMapping("no_car_order")
//    public MessageBean no_car_order(String phone) {
//        //生产随机数 及发送短信
//        /*
//         * 返回前端，前端校验，简单一点，正常应该是后端检验d、
//         * */
//        String verifyCode = String
//                .valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
//        Message message = new Message();
//        message.set_PhoneNumbers(phone);
//        message.set_TemplateCode("SMS_162635038");
//        // Message.set_TemplateParam("{\"code\":\""+verifyCode+"\"}");
//        message.set_TemplateParam("{\"A\":\"订单号\",\"C\":\"订单号\",}");
//        int is_send = messageService.Send(message);
//        if (is_send == 1) {
//            //成功发送
//            return new MessageBean(10001, "短信发送成功", verifyCode);
//        } else {
//            //发送失败
//            return new MessageBean(10000, "短信发送失败", verifyCode);
//        }
//    }
//
//    @RequestMapping("out_warehouse")
//    public MessageBean out_warehouse(String phone) {
//        //生产随机数 及发送短信
//        /*
//         * 返回前端，前端校验，简单一点，正常应该是后端检验d、
//         * */
//        String verifyCode = String
//                .valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
//        Message message = new Message();
//        message.set_PhoneNumbers(phone);
//        message.set_TemplateCode("SMS_162630043");
//        // Message.set_TemplateParam("{\"code\":\""+verifyCode+"\"}");
//        message.set_TemplateParam("{\"A\":\"订单号\",\"C\":\"订单号\",}");
//        int is_send = messageService.Send(message);
//        if (is_send == 1) {
//            //成功发送
//            return new MessageBean(10001, "短信发送成功", verifyCode);
//        } else {
//            //发送失败
//            return new MessageBean(10000, "短信发送失败", verifyCode);
//        }
//    }
//
//    @RequestMapping("recharge_audit")
//    public MessageBean recharge_audit(String phone) {
//        //生产随机数 及发送短信
//        /*
//         * 返回前端，前端校验，简单一点，正常应该是后端检验d、
//         * */
//        String verifyCode = String
//                .valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
//        Message message = new Message();
//        message.set_PhoneNumbers(phone);
//        message.set_TemplateCode("SMS_162635040");
//        // Message.set_TemplateParam("{\"code\":\""+verifyCode+"\"}");
//        message.set_TemplateParam("{\"A\":\"订单号\",\"C\":\"订单号\",}");
//        int is_send = messageService.Send(message);
//        if (is_send == 1) {
//            //成功发送
//            return new MessageBean(10001, "短信发送成功", verifyCode);
//        } else {
//            //发送失败
//            return new MessageBean(10000, "短信发送失败", verifyCode);
//        }
//    }
//
//    @RequestMapping("recharge_unaudit")
//    public MessageBean recharge_unaudit(String phone) {
//        //生产随机数 及发送短信
//        /*
//         * 返回前端，前端校验，简单一点，正常应该是后端检验d、
//         * */
//        String verifyCode = String
//                .valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
//        Message message = new Message();
//        message.set_PhoneNumbers(phone);
//        message.set_TemplateCode("SMS_162635042");
//        // Message.set_TemplateParam("{\"code\":\""+verifyCode+"\"}");
//        message.set_TemplateParam("{\"A\":\"订单号\",\"C\":\"订单号\",}");
//        int is_send = messageService.Send(message);
//        if (is_send == 1) {
//            //成功发送
//            return new MessageBean(10001, "短信发送成功", verifyCode);
//        } else {
//            //发送失败
//            return new MessageBean(10000, "短信发送失败", verifyCode);
//        }
//    }
//
//    @RequestMapping("ingoods_audit")
//    public MessageBean ingoods_audit(String phone) {
//        //生产随机数 及发送短信
//        /*
//         * 返回前端，前端校验，简单一点，正常应该是后端检验d、
//         * */
//        String verifyCode = String
//                .valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
//        Message message = new Message();
//        message.set_PhoneNumbers(phone);
//        message.set_TemplateCode("SMS_162630046");
//        // Message.set_TemplateParam("{\"code\":\""+verifyCode+"\"}");
//        message.set_TemplateParam("{\"A\":\"订单号\",\"C\":\"订单号\",}");
//        int is_send = messageService.Send(message);
//        if (is_send == 1) {
//            //成功发送
//            return new MessageBean(10001, "短信发送成功", verifyCode);
//        } else {
//            //发送失败
//            return new MessageBean(10000, "短信发送失败", verifyCode);
//        }
//    }
//
//    @RequestMapping("ingoods_unaudit")
//    public MessageBean ingoods_unaudit(String phone) {
//        //生产随机数 及发送短信
//        /*
//         * 返回前端，前端校验，简单一点，正常应该是后端检验d、
//         * */
//        String verifyCode = String
//                .valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
//        Message message = new Message();
//        message.set_PhoneNumbers(phone);
//        message.set_TemplateCode("SMS_162635048");
//        // Message.set_TemplateParam("{\"code\":\""+verifyCode+"\"}");
//        message.set_TemplateParam("{\"A\":\"订单号\",\"C\":\"订单号\",}");
//        int is_send = messageService.Send(message);
//        if (is_send == 1) {
//            //成功发送
//            return new MessageBean(10001, "短信发送成功", verifyCode);
//        } else {
//            //发送失败
//            return new MessageBean(10000, "短信发送失败", verifyCode);
//        }
//    }
//
//    @RequestMapping("goods_audit")
//    public MessageBean goods_audit(String phone) {
//        //生产随机数 及发送短信
//        /*
//         * 返回前端，前端校验，简单一点，正常应该是后端检验d、
//         * */
//        String verifyCode = String
//                .valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
//        Message message = new Message();
//        message.set_PhoneNumbers(phone);
//        message.set_TemplateCode("SMS_162635050");
//        // Message.set_TemplateParam("{\"code\":\""+verifyCode+"\"}");
//        message.set_TemplateParam("{\"A\":\"订单号\",\"C\":\"订单号\",}");
//        int is_send = messageService.Send(message);
//        if (is_send == 1) {
//            //成功发送
//            return new MessageBean(10001, "短信发送成功", verifyCode);
//        } else {
//            //发送失败
//            return new MessageBean(10000, "短信发送失败", verifyCode);
//        }
//    }
//
//    @RequestMapping("goods_unaudit")
//    public MessageBean goods_unaudit(String phone) {
//        //生产随机数 及发送短信
//        /*
//         * 返回前端，前端校验，简单一点，正常应该是后端检验d、
//         * */
//        String verifyCode = String
//                .valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
//        Message message = new Message();
//        message.set_PhoneNumbers(phone);
//        message.set_TemplateCode("SMS_162635052");
//        // Message.set_TemplateParam("{\"code\":\""+verifyCode+"\"}");
//        message.set_TemplateParam("{\"A\":\"订单号\",\"C\":\"订单号\",}");
//        int is_send = messageService.Send(message);
//        if (is_send == 1) {
//            //成功发送
//            return new MessageBean(10001, "短信发送成功", verifyCode);
//        } else {
//            //发送失败
//            return new MessageBean(10000, "短信发送失败", verifyCode);
//        }
//    }
}
