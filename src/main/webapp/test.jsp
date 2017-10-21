<%--
  Created by IntelliJ IDEA.
  User: BeiYi
  Date: 2017/10/21
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
 <form method="post" action="https://openapi.yiban.cn/oauth/reset_token">
     <input type="text" name="client_id" >
     <input type="text" name="client_secret">
     <input type="text" name="dev_uid" value="10849451">
     <input type="submit" value="提交">
 </form>
</body>
</html>
