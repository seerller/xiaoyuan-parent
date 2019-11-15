package com.xiaoyuan.tools;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用证件号、手机号等证件格式检验工具类
 * @author zhengweicheng
 */
public class FormatUtil {
    /**
     * 传入手机号检验手机格式是否符合标准
     * 符合标准则返回true
     *
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (StringUtils.isBlank(phone)) {
            return false;
        } else if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            return isMatch;
        }
    }

    public static boolean notIsPhone(String phone){
        return !isPhone(phone);
    }

    /**
     * 检验是否为非0的正整数
     * @param number
     * @return
     */
    public static boolean notIsNumber(String number){
        Pattern p = Pattern.compile("^[1-9]\\d*$");
        Matcher m = p.matcher(number);
        return  !m.matches();
    }

    public static void main(String[] args) {
        String phone = "10918111111";
        System.out.println(isPhone(phone));
    }
}
