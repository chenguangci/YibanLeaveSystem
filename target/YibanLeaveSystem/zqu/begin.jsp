<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
//    String appKey  = "6e5022b516e51935";
//    String appSecret = "7eafe47e5c585ef1ad6b3e3bd3aff408";
//    String callbackUrl = "http://localhost:8080/zqu/begin.jsp";
//    Authorize authorize = new Authorize(appKey,appSecret);
//    String code = request.getParameter("code");
//    if (code==null||code.isEmpty()){
//        String url = authorize.forwardurl(callbackUrl,"test",Authorize.DISPLAY_TAG_T.WEB);
//        System.out.println(url);
//        request.getRequestDispatcher(url).forward(request,response);
//    } else {
//        String test = authorize.querytoken(code,callbackUrl);
//        System.out.println(test);
//        session.setAttribute("test",test);
//    }
%>
    <form action="leave/leave.jsp" method="post" >
        <input type="submit" value="我要请假" /><br/>
    </form>
    <form action="action/SickLeave.jsp" method="post">
        <input type="submit" value="我要销假"/>
    </form>
</body>
</html>
