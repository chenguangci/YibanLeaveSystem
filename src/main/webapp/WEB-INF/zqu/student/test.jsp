<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${result.success}<br>
${result.data.studentId}<br>
${result.data.name}<br>
${result.data.department}<br>
${result.data.className}<br>
${result.data.yibanId}
<form action="#" method="post">
    <input type="submit" value="我的请假信息">
</form>
</body>
</html>
