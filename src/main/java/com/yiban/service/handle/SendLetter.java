package com.yiban.service.handle;

import com.yiban.dto.YibanURL;
import com.yiban.entity.Token;
import com.yiban.exception.ReSetTokenException;
import com.yiban.exception.SendException;
import com.yiban.exception.RequestInfoException;
import com.yiban.exception.SystemRunTimeException;
import com.yiban.mapper.TokenMapper;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 发送消息到指定用户
 */
@Service
public class SendLetter {

    private static final String appKey = "6e5022b516e51935";
    private static final String appSecret = "7eafe47e5c585ef1ad6b3e3bd3aff408";
    private static final String myId = "10849451";
    //TODO 消息需要动态生成，再修改
    //TODO 试试能不能通过硬编码给消息添加链接
    private static final String CONTENT = "尊敬的老师，您有一学生申请请假，详情请登陆易班请假系统查看";

    private SendRequest request = new SendRequest();
    private Token accessToken = null;

    private Logger logger = LoggerFactory.getLogger(SendLetter.class);

    @Autowired
    private TokenMapper tokenMapper;


    /**
     * 负责发送通知的方法
     *
     * @param userId 指定的用户的易班ID
     */
    public void send(String userId) throws SendException, RequestInfoException, ReSetTokenException, SystemRunTimeException {
        //获取token
        getAccessToken();
        //判断日期是否过时，如果过期则重置token
        if (overdue(accessToken.getAddTime())) {
            resetToken();
        }
        sendLetter(userId);
    }


    /**
     * 调用sendPost()发送请求
     * @param userId 制定用户的id
     * @throws SendException
     * @throws RequestInfoException
     * @throws SystemRunTimeException
     */
    private void sendLetter(String userId) throws SendException, RequestInfoException, SystemRunTimeException {
        //拼接参数
        String param = "access_token=" + accessToken + "&to_yb_uid=" + userId + "&content=" + CONTENT + "&template=user";
        //发送信息
        String str = request.sendRequest(YibanURL.SendLetter, param, SendRequest.TYPE.POST);
        logger.debug("送信时返回的json：{}", str);
        //判断请求是否成功
        if (!str.contains("success")) {
            throw new RequestInfoException("发送信息失败");
        }
    }

    /**
     * 重置授权,返回新的access_token
     * 将新的access_token存入数据库
     */
    private void resetToken() throws SendException, ReSetTokenException, SystemRunTimeException {
        //拼接参数
        String param = "client_id=" + appKey + "&client_secret=" + appSecret + "&dev_uid=" + myId;
        //发送请求
        String json = request.sendRequest(YibanURL.ResetToken, param, SendRequest.TYPE.POST);
        logger.info("重置授权到返回的json：{}", json);
        //解析json
        JSONObject jsonObject = JSONObject.fromObject(json);
        Iterator iterator = jsonObject.keys();
        Map<String, String> map = new HashMap<String, String>();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            map.put(key, jsonObject.getString(key));
        }
        //如果重置授权成功
        if (map.get("status").equals("200")) {
            //插入新的access_token
            Token token = new Token();
            token.setTokenType(0);
            token.setToken(map.get("access_token"));
            token.setAddTime(new Date());
            int insert = tokenMapper.updateToken(token);
        /*
         * insert代表插入的结果，为0的时候说明插入失败，但不影响业务流程，
         * 这里以错误日志提示，如有出现插入失败，后期再做维修
         */
            if (insert == 0) {
                logger.error("重置的token更新失败");
            }
        } else if (map.get("status").equals("500")) {
            logger.error("成功返回，但token重置失败，失败原因：{}", json);
            throw new ReSetTokenException("token重置失败");
        } else {
            logger.error("重置token请求时发生错误，失败原因：{}", json);
            throw new ReSetTokenException("请求重置token发生错误");
        }

    }

    /**
     * 需定期获取access_token
     * 从数据库中取回最新的一条access_token，类型为0
     * 理论上方法中的if判断不会发生，有也只是发生在第一次
     */
    private void getAccessToken()  throws SendException, ReSetTokenException, SystemRunTimeException {
        this.accessToken = tokenMapper.selectToken(0);
        if (accessToken == null) {
            logger.error("获取的json为空");
            resetToken();
            this.accessToken = tokenMapper.selectToken(0);
        }
    }

    /**
     * 判断数据库中的token是否过期，期限为15天
     *
     * @param date1 原token的保存日期
     * @return 过期返回true，否则返回false
     */
    private boolean overdue(Date date1) {
        Date date2 = new Date();
        int days = (int) (date2.getTime() - date1.getTime()) / (1000 * 3600 * 24);
        return days >= 15;
    }
}
