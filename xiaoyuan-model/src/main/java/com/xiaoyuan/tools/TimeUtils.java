package com.xiaoyuan.tools;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * @author  zhengweicheng
 */
public class TimeUtils {

    public final static String default_format = "yyyy-MM-dd HH:mm:ss";

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    public static boolean isEffectiveDate(Date startTime, Date endTime){
        return isEffectiveDate(new Date(),startTime,endTime);
    }



    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     * @param nowTime 当前时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     * @author jqlin
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 判断当前时间是否在[start, end]区间，注意时间格式要一致
     * @return
     * @author jqlin
     */
    public static boolean isEffectiveDate( String start, String end)  {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int startTime = (int)(sdf.parse(start.concat(" 00:00:00")).getTime()/1000) ;
            int endTime = (int)(sdf.parse(end.concat(" 23:59:59")).getTime()/1000) ;
            int now = (int) (System.currentTimeMillis()/1000);
            if(startTime<=now&&now<=endTime){
                return true;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        String start    = "2019-07-04";
        String end      = "2019-07-04";
        System.out.println( isEffectiveDate(start,end));

        BigDecimal a  = new BigDecimal(50.00);
        BigDecimal b  = new BigDecimal(50.00);
        BigDecimal c = new BigDecimal(100.00);
        System.out.println(a.add(b));
        System.out.println(a.add(b).compareTo(c));

    }


    /**
     * 返回当前时间的年月日时分秒
     * @return
     */
    public static String resultCurrentDate(Date date){
        return resultCurrentDate("yyyy-MM-dd HH:mm:ss",date);
    }
    /**
     * 返回当前时间的年月日时分秒
     * @return
     */
    public static String resultCurrentDate(String format,Date date){
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 将string类型的时间转化为DATE的时间
     * @param format
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parseCurrentString(String format,String date) throws ParseException {
        return new SimpleDateFormat(format).parse(date);
    }

    /**
     * 将string类型的时间转化为DATE的时间
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parseCurrentString(String date) throws ParseException {
        if(StringUtils.isBlank(date)){
            return null;
        }
        return new SimpleDateFormat(default_format).parse(date);
    }

    /**
     * 返回当前时间的年月日时分秒
     * @return
     */
    public static String resultCurrentDate(){
        return resultCurrentDate(new Date());
    }

    /**
     * 获取指定日期凌晨时间戳
     * @return
     */
    public static long resultCurrentNextDayDate(Integer date){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.DATE, date);
        return c.getTimeInMillis();
    }

}
