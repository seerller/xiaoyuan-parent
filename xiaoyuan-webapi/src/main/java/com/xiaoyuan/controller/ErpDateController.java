package com.xiaoyuan.controller;


import com.xiaoyuan.tools.MessageBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/ErpDate")
public class ErpDateController {


    //返回 map(一月，ArrayList[date1,date2])
    //二月的date2的月份使用0301就行了，数据库默认时分秒为0，所以不用判断二月份了
    //所有都用当年，加当月的第一天，加下一月的第一天，就行了，
    @RequestMapping("/get_month")
    public MessageBean get_month() {
        Calendar calendar=Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        int nextYear=nowYear+1;
        Map<String, Object>map=new LinkedHashMap<>();
        List<String>list=new ArrayList<>();

        return new MessageBean(10000, "获取月份失败", null);
    }

    @RequestMapping("/get_season")
    public MessageBean get_season() {
        Calendar calendar=Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        int nextYear=nowYear+1;
        return new MessageBean(10000, "获取季度失败", null);
    }
}
