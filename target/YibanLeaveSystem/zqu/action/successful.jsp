<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
请假成功,信息如下：<br>
姓名：<%=request.getParameter("name")%><br>
学院：<%=request.getParameter("department")%><br>
专业：<%=request.getParameter("major")%><br>
请假时间：<%=request.getParameter("beginTime")%><br>
结束时间：<%=request.getParameter("endTime")%><br>
请假原因：<%=request.getParameter("reason")%>
</body>
</html>
