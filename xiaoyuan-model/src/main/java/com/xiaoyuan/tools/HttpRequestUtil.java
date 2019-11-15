package com.xiaoyuan.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoyuan.model.exception.LogicException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * 模拟http请求工具
 */
@Slf4j
public class HttpRequestUtil {

    /**
     * get请求
     * @return
     */
    public static String doGet(String url) {
        try {
            HttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());
                return strResult;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 发送POST请求
     *
     * @param url
     *            目的地址
     * @param parameters
     *            请求参数，Map类型。
     * @return 远程响应结果
     */
    public static String sendPost(String url, Map<String, String> parameters) {
        String result = "";// 返回的结果
        BufferedReader in = null;// 读取响应输入流
        PrintWriter out = null;
        StringBuffer sb = new StringBuffer();// 处理请求参数
        String params = "";// 编码之后的参数
        try {
            // 编码请求参数
            if (parameters.size() == 1) {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(parameters.get(name),
                                    "UTF-8"));
                }
                params = sb.toString();
            } else {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(parameters.get(name),
                                    "UTF-8")).append("&");
                }
                String temp_params = sb.toString();
                params = temp_params.substring(0, temp_params.length() - 1);
            }
            // 创建URL对象
            java.net.URL connURL = new java.net.URL(url);
            // 打开URL连接
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
                    .openConnection();
            // 设置通用属性
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // 设置POST方式
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            // 获取HttpURLConnection对象对应的输出流
            out = new PrintWriter(httpConn.getOutputStream());
            // 发送请求参数
            out.write(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应，设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn
                    .getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
    public static JSONObject doPost(String url, JSONObject json){
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        try {
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = httpclient.execute(post);
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSON.parseObject(result);
            }
        } catch (Exception e) {
            log.error("请求第三方gps定位平台时发生异常。");
            e.printStackTrace();
            throw new LogicException();
        }
        return response;
    }

    /**
     * GPS第三方请求地址
     */
    private static final String BASE_URL = "http://basic.yholink.com/API.aspx";
    /**
     * 默认设备类型 5011
     */
    private static final String REC_ID = "5011";


    private static final List<String> REC_CODE_ARR = new ArrayList<String>(Arrays.asList("8001","8002"));
    /**
     * 获取当前位置的请求接口名称
     */
    public static final String REC_CODE_NOW = "8001";
    /**
     * 获取历史轨迹的请求接口名称
     */
    public static final String REC_CODE_HISTORY = "8002";
    /**
     * 请求gps数据函数
     * @param parameters
     * @return
     */
    public static JSONObject resultGpsVal(JSONObject parameters){
        parameters.put("rec_id", REC_ID);
        parameters.put("sign", "jamAdmin");
        return doPost(BASE_URL, parameters);
    }

    /**
     * 请求当前位置函数
     * @param rec_sysNo     设备主键
     * @return              返回JSON对象
     */
    public static JSONObject resultGpsVal(String rec_sysNo){
        return resultGpsVal( REC_CODE_NOW, rec_sysNo,null,null);
    }

    /**
     * 请求车辆历史轨迹函数
     * @param code              接口id
     * @param rec_sysNo         设备主键
     * @param rec_beginTime     开始时间(可为空)
     * @param rec_endTime       结束时间(可为空)
     * @return                  返回JSON对象
     */
    public static JSONObject resultGpsVal(String code,String rec_sysNo,String rec_beginTime,String rec_endTime){
        JSONObject parameters = new JSONObject();
        if(REC_CODE_ARR.indexOf(code) < 0){
            log.info("请求参数rec_code为空或不在接口范围内。");
            throw new LogicException();
        }else if(StringUtils.isBlank(rec_sysNo)){
            log.info("请求参数rec_sysNo为空。");
            throw new LogicException();
        }
        parameters.put("rec_code", code);
        parameters.put("rec_sysNo",rec_sysNo);
        if(!StringUtils.isBlank(rec_beginTime) &&! StringUtils.isBlank(rec_beginTime)){
            parameters.put("rec_beginTime",rec_beginTime);
            parameters.put("rec_endTime",rec_endTime.concat(" 23:59:59"));
        }
        return resultGpsVal(parameters);
    }



    /**
     * 主函数，测试请求
     *
     * @param args
     */
    public static void main(String[] args) {
        long time = 60 * 60 * 24 * 3 ;
        time *= 1000;
        //Map<String, String> parameters = new HashMap<String, String>();
        JSONObject parameters = new JSONObject();
        String iccid = "27021121249";
        parameters.put("rec_code", "8001");
        parameters.put("rec_sysNo",iccid);
        parameters.put("rec_sysNo",iccid);
        String startTime = TimeUtils.resultCurrentDate(new Date(System.currentTimeMillis() - time ));
        JSONObject result = resultGpsVal(parameters);
        System.out.println(result);
        System.out.println(result.getString("res_num"));
        System.out.println(result.getDoubleValue("res_lng"));
    }
}
