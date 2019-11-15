package com.xiaoyuan.controller;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService

public class WebServiceController {
    @WebMethod
    public String sayTitle(String from) {
        String result = "title is " + from;
        System.out.println(result);
        return result;
    }


    public String sayBody(String Other) {
        String result = "-------------body is-------------- " + Other;
        System.out.println(result);
        return result;
    }

    public String sayAll(String title,String body) {
        String result ="--------title:"+title+ "----------------/r/nbody:--------------------------- " + body;
        System.out.println(result);
        return result;
    }
}