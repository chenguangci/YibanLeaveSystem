<%@ page import="com.yiban.bean.LeaveContent" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: BeiYi
  Date: 2017/10/26
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%--<script type="text/javascript">--%>
        <%--$(function() {--%>
            <%--$('#btn').click(function () {--%>
                <%--$.ajax({--%>
                    <%--url: "http://localhost:8080/MyLeave.action",--%>
                    <%--type: 'POST',--%>
                    <%--dataType: 'json',--%>
                    <%--timeout: 20000,--%>
                    <%--error: function () {--%>
                        <%--alert('Failed to communicate to the server. Try again!')--%>
                    <%--}, success: function (ljdata) {--%>
                        <%--var jsonData = eval(ljdata).data; //数组--%>
                        <%--var jsonCode = eval(ljdata).code; //字符串--%>
                        <%--var contnetstr = '';--%>
                        <%--$.each(jsonData, function (index, item) {--%>
                            <%--var name = jsonData[index].name;--%>
                            <%--var content = jsonData[index].content;--%>
                            <%--contnetstr = contnetstr + name + '----' + content + '\r\n' + '\r\n';--%>
                        <%--});--%>
                        <%--$('#label').text(contnetstr)--%>
                    <%--}--%>
                <%--});--%>
            <%--});--%>
        <%--})--%>
    <%--</script>--%>
</head>
<body>
<%
    Map<String,String> map = (Map<String, String>) request.getAttribute("info");
    out.println(map.get("yb_realname"));
    out.println(map.get("yb_studentid"));
    out.println(map.get("yb_collegename"));
    out.println(map.get("yb_classname"));
%>
</body>
</html>
