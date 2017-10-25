<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <title>肇院请假系统</title>
    <style>
        * {
            font-family: "Helvetica Neue", serif;
            padding: 0;
            margin: 0;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }

        .header {
            color: #fff;
            padding: 12px 0 12px 0;
            margin: 5px 0 5px 0;
            background-color: #6A5ACD;
        }

        .index-body {
            padding: 0 3px 0 1px;
        }

        .footer {
            padding: 15px;
            border-radius: 5px;
            font-size: 12px;
            background-color: #6A5ACD;
            color: #fff;
            text-align: center;
        }

        form div {
            margin: 13px auto;
        }

        .input-group {
            margin: 0;
        }

        h1, h2, h3, h4 {
            text-align: center;
        }

        .submit-btn {
            margin: 15px auto;
            width: 350px;
            height: 40px;
            background-color: #FFA500;
            border: none;
            border-radius: 6px;
            float: right;
            color: #fff;
        }

        .time input {
            width: 100%;
            height: 32px;
        }
    </style>
</head>
<body class="container-fluid">
<div class="row">
    <div class="index-body col-md-8 col-md-offset-2">
        <div class="header">
            <div class="logo"><h1>肇庆学院</h1></div>
        </div>
        <div class="index-form form container-fluid">
            <h3> 请假登记 </h3>
            <form action="<%=request.getContextPath()%>/toLeave.action" method="post">
                <div class="row">
                    <div class="col-md-3">
                        <div class="input-group"><span class="input-group-addon">学号:</span> <input type="text"
                                                                                                   class="form-control"
                                                                                                   name="id">
                        </div>
                        <div class="input-group"><span class="input-group-addon">姓名:</span> <input type="text"
                                                                                                   class="form-control"
                                                                                                   name="name">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <select class="form-control" name="department">
                            <option>计算机科学与软件学院、大数据学院</option>
                            <option>经济与管理学院</option>
                            <option>旅游与历史文化学院</option>
                            <option>外国语学院</option>
                            <option>文学院</option>
                        </select>
                    </div>
                    <div class="col-md-3">
                        <select class="form-control" name="major">
                            <option>软件工程</option>
                            <option>法学</option>
                            <option>汉语言文学</option>
                        </select>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 time"><p>请假时间</p><input type="date" name="beginTime"></div>
                    <div class="col-md-6 time"><p>结束时间</p><input type="date" name="endTime"></div>
                </div>

                <p>请假原因</p>
                <textarea class="form-control" rows="5" name="reason"></textarea>
                <button class="submit-btn">提交申请</button>
            </form>
        </div>
        <hr/>
        <div class="footer">
            <div class="foot-text">易班学生工作站</div>
            <div class="foot-copyright">Copyright© 2017 ZQU E-Class</div>
        </div>
    </div>
</div>
</body>
</html>