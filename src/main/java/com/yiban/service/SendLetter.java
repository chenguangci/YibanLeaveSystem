package com.yiban.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class SendLetter {

    private static final String CONTENT = "尊敬的老师，您有一学生申请请假，详情请登陆易班请假系统查看";

    private String accessToken = "0d53c624068d0f79a497c09976ca79f33d4a55f1";

    /**
     * 调用sendPost()发送请求
     * @return 返回json
     */
    public String sendLetter(){
        String param="access_token="+accessToken+"&to_yb_uid="+getTeacherId()+"&content="+CONTENT+"&template=user";
        String str = sendPost(param);
        System.out.println(str);
        return str;
    }

    /**
     * 此方法只负责发送消息请求，后期如需扩展再单独封装
     * 向服务器发送消息请求
     * @param param 请求参数
     * @return 返回服务器结果
     */
    private String sendPost(String param) {
        PrintWriter writer = null;
        BufferedReader bufferedReader = null;
        StringBuilder result = new StringBuilder();
        try {
//            请求的地址
            URL realUrl = new URL("https://openapi.yiban.cn/msg/letter");
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
     * 获取指定发送老师的id
     */
    private String getTeacherId(){
        FormHandle formHandle = new FormHandle();
        return formHandle.getTeacherId();
    }

    /**
     * 需定期获取access_token
     * @return
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * 测试成功
     */
    public static void main(String[] args){
        SendLetter sendLetter = new SendLetter();
        sendLetter.sendLetter();
    }
}
