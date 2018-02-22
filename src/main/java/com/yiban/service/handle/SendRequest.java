package com.yiban.service.handle;

import com.yiban.exception.SendError;
import com.yiban.exception.SystemRunTimeError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;

/**
 * 用于发送请求的类
 */
public class SendRequest {

    private Logger logger = LoggerFactory.getLogger(SendRequest.class);

    public enum TYPE {
        POST,
        GET
    }

    /**
     * 向服务器发送消息post请求
     *
     * @param param 请求参数
     * @return 返回服务器结果
     */
    private String sendPost(String url, String param) {
        StringBuilder result = new StringBuilder();
        BufferedReader reader = null;
        try {
            URL realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            //设置属性
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            //输入输出流设为true
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept", "text/plain, text/html");
            connection.setRequestProperty("Connection", "keep-alive");
            //以输出流的形式提交给服务器
            OutputStream out = connection.getOutputStream();
            out.write(param.getBytes("UTF-8"));
            //如果连接成功
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            throw new SendError("服务器请求发生异常");
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (Exception e) {
                logger.error("输入流关闭错误");
                throw new SystemRunTimeError("系统未知异常");
            }
        }
        return result.toString();
    }

    /**
     * 负责发送get请求
     *
     * @param url   请求地址
     * @param param 请求参数
     * @return 返回一段json
     */
    private String sendGet(String url, String param) {
        StringBuilder result = new StringBuilder();
        BufferedReader reader = null;
        String realParam = url + "?" + param;
        try {
            URL realUrl = new URL(realParam);
            URLConnection connection = realUrl.openConnection();
            //设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            建立实际连接
            connection.connect();
//            读取服务器响应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            throw new SendError("服务器请求发生异常");
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                logger.error("输入流关闭错误");
                throw new SystemRunTimeError("系统未知异常");
            }
        }
        return result.toString();
    }


    public String sendRequest(String url, String param, SendRequest.TYPE type) throws IllegalArgumentException, SystemRunTimeError {
        switch (type) {
            case POST:
                return sendPost(url, param);
            case GET:
                return sendGet(url, param);
            default:
                throw new IllegalArgumentException();
        }
    }
}
