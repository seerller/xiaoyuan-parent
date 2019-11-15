package com.xiaoyuan.tools;


import lombok.Data;

@Data
public class Point{
    /**
     * 经度
     */
    private Double lng;
    /**
     * 纬度
     */
    private Double lat;

    public Point() {};

    public Point(Double lng, Double lat) {this.lng=lng;this.lat = lat;}

    public Point(String lng, String lat) {
        this.lng= Double.valueOf(lng);
        this.lat =  Double.valueOf(lat);
    }

}
