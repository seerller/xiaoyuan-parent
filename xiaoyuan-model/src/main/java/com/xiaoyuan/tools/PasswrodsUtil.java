package com.xiaoyuan.tools;

import java.util.Random;

/**
 * 用户密码工具类
 */
public class PasswrodsUtil {


    /**
     * 传入密码进行加密返回加密后的新密码
     * @param password
     * @return
     */
    public static String resultNewPassword(String password){
        //获取随机数作用盐
        byte[] random = EncryptUtil.generateSalt(8);
        return resultNewPassword(random,password);
    }

    /**
     * 传入密码进行加密返回加密后的新密码
     * @param random
     * @param password
     * @return
     */
    public static String resultNewPassword(byte[] random,String password){
        //将随机数类型转换为字符串类型
        String randomHex = EncryptUtil.encodeHex(random);
        // 将盐与密码混合进行加密
        String sha1Psd = EncryptUtil.encodeHex(EncryptUtil.sha1(password.getBytes(), random, 1024));
        // 将盐与加密后的密码进行拼接返回
        return randomHex.concat(sha1Psd);
    }

    /**
     * 检验两密码是否是相同密码
     * @param oldPassword   加密后的密码
     * @param password      未加密的密码
     * @return
     */
    public static boolean oldOrNewIsEquals(String oldPassword,String password){
        return oldPassword.equals(resultNewPassword(EncryptUtil.decodeHex(oldPassword.substring(0, 16)),password));
    }

    /**
     * 拼接返回char数组
     * @return
     */
    public static String charArray(Integer integer){
        integer = null == integer ? 8 : integer;
        int i = 1234567890;
        String s ="qwertyuiopasdfghjklzxcvbnm";
        String S=s.toUpperCase();
        String word=s+S+i;
        char[] c = word.toCharArray();
        Random rd = new Random();
        String code="";
        for (int k = 0; k <= integer; k++) {
            int index = rd.nextInt(c.length);//随机获取数组长度作为索引
            code+=c[index];//循环添加到字符串后面
        }

        return code;
    }

    /**
     * 随机返回8位密码
     * @return
     */
    public static String charArray(){
        return charArray(null);
    }

}
