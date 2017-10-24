package com.yiban.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * 用于发送请求的类
 */
public class SendRequest {

    /**
     * 向服务器发送消息post请求
     * @param param 请求参数
     * @return 返回服务器结果
     */
    protected String sendPost(String url,String param) {
        PrintWriter writer = null;
        BufferedReader bufferedReader = null;
        StringBuilder result = new StringBuilder();
        try {
//            请求的地址
            URL realUrl = new URL(url);
//            打开连接
            URLConnection connection = realUrl.openConnection();
//            设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            发送POST请求必须设置如下两行
            connection.setDoOutput(true);
            connection.setDoInput(true);
//            获取connection对象对应的输出流
            writer = new PrintWriter(connection.getOutputStream());
//            发送请求参数
            writer.print(param);
//            输出流缓冲
            writer.flush();
//            读取服务器响应
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            return "请求出错";
        } finally {
            try {
                if (writer != null)
                    writer.close();
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result.toString();
    }

    /**
     *负责发送get请求
     * @param url 请求地址
     * @param param 请求参数
     * @return 返回一段json
     */
    public String sendGet(String url, String param){
        StringBuilder result = new StringBuilder();
        BufferedReader reader = null;
        String realParam = url+"?"+param;
        try {
            URL realUrl = new URL(realParam);
            URLConnection connection = realUrl.openConnection();
            //            设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            建立实际连接
            connection.connect();
//            读取服务器相应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line=reader.readLine())!=null){
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader!=null)
                    reader.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return result.toString();
    }
}
