package com.xiaoyuan.controller;

import com.xiaoyuan.model.FirstApi;
import com.xiaoyuan.service.IFirstApiService;
import com.xiaoyuan.tools.MessageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/FirstApi")
public class FirstApiController {

    @Autowired
    IFirstApiService firstApiService;

    public MessageBean select() {
        ArrayList<FirstApi> list = this.firstApiService.select();
        if (list.size() > 0) {
            return new MessageBean(10001, "", list);
        }
        return new MessageBean(10000, "", list);
    }


}
