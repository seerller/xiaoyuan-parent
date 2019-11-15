package com.xiaoyuan.tools;

public class BaiduLocationUtils {

        public static Double getDistance(Point a, Point b) {
            if (a!=null && b!=null) {
                return XO(a, b);
            }else {
                return 0d;
            }
        }

        private static Double XO(Point a, Point b) {
            a.setLng(OD(a.getLng(), -180d, 180d));
            a.setLat(SD(a.getLat(), -74d, 74d));
            b.setLng(OD(b.getLng(), -180d, 180d));
            b.setLat(SD(b.getLat(), -74d, 74d));
            return Te(Uk(a.getLng()), Uk(b.getLng()), Uk(a.getLat()), Uk(b.getLat()));
        }

        private static Double OD(Double a, Double b, Double c) {
            for (; a > c;) a -= c - b;
            for (; a < b;) a += c - b;
            return a;
        }

        private static Double SD(Double a, Double b, Double c) {
            return a;
        }

        private static Double Te(Double a, Double b, Double c, Double d) {
            return 6370996.81 * Math.acos(Math.sin(c) * Math.sin(d) + Math.cos(c) * Math.cos(d) * Math.cos(b - a));
        }

        private static Double Uk(Double a) {
            return Math.PI * a / 180;
        }

        public static void main(String[] args) {
            System.out.println(getDistance(new Point(116.404, 39.915),new Point(116.399814, 39.908209)));
        }
}
