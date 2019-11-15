package com.xiaoyuan.controller;

import javax.xml.ws.Endpoint;

/**
 * Created by Lin_Yang on 2014/12/16.
 */
public class Publish {
    public static void main(String args[]){
        Object implementor = new WebServiceController();
        String address = "http://localhost:8989/sayTitle";     //发布到的地址
        Endpoint.publish(address, implementor);
        System.out.println("发布成功");
    }
}