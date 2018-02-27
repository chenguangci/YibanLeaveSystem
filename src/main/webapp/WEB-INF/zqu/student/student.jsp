<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath}" var="path"/>
<html>
<head>
    <link rel="stylesheet" href="${path}/static/source/student/bootstrapvalidator-master/vendor/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="${path}/static/source/student/bootstrapvalidator-master/dist/css/bootstrapValidator.css"/>
    <link rel="stylesheet" href="${path}/static/source/student/datatime/bootstrap-datetimepicker.min.css"/>
    <link rel="stylesheet" href="${path}/static/source/student/font-awesome/css/font-awesome.css"/>
    <script type="text/javascript"
            src="${path}/static/source/student/bootstrapvalidator-master/vendor/jquery/jquery-1.10.2.min.js"></script>
    <script type="text/javascript"
            src="${path}/static/source/student/bootstrapvalidator-master/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="${path}/static/source/student/bootstrapvalidator-master/dist/js/bootstrapValidator.js"></script>
    <script type="text/javascript" src="${path}/static/source/student/datatime/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="${path}/static/source/student/datatime/bootstrap-datetimepicker.zh-CN.js"></script>
    <link rel="stylesheet" href="${path}/static/css/demo.css"/>
    <link rel="stylesheet" href="${path}/static/css/gsdk-bootstrap-wizard.css"/>
    <link rel="stylesheet" href="${path}/static/css/style.css"/>
    <script src="${path}/static/js/Submit.js"></script>


    <!--End Framework-->
    <!-- <script src="js/jquery.ffform.js" type="text/javascript"></script>-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <title>肇院请假系统</title>


</head>
<body>

<div class="image-container set-full-height" style="background-image: url('${path}/static/images/bg1.png')">
    <section>
        <div class="container">

            <div class="row">

                <div class="index-body animated" id="index">
                    <div class="wizard-container">
                        <div class="card wizard-card" data-color="orange" id="wizardProfile">
                            <form id="test" class="form-signin required-validate" action="" method="post"
                                  onsubmit="return validateCallback(this)">
                                <div class="wizard-header">
                                    <h3>
                                        肇院 <b>请假系统</b><br>
                                        <small></small>
                                    </h3>
                                </div>
                                <div class="form-group row">
                                    <h4>
                                        学号：${result.data.studentId}&nbsp;&nbsp;&nbsp;&nbsp;姓名：${result.data.name}<br>
                                        院系：${result.data.department}&nbsp;&nbsp;&nbsp;&nbsp;班级：${result.data.className}<br>
                                    </h4>
                                    <%--<div class="col-md-6">--%>
                                        <%--<div class="input-group"><span class="input-group-addon"><i class="fa fa-user">姓名</i></span>--%>
                                            <%--<input name="Name" type="text" class="form-control " id="Name"--%>
                                                   <%--placeholder="请输入你的姓名">--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <br>
                                    <div class="col-md-6 ">
                                        <div class="input-group"><span class="input-group-addon"><i class="fa fa-phone">联系方式</i></span>
                                            <input name="phone" type="text" class="form-control" id="Mobile"
                                                   placeholder="请输入手机号码" tip="请输入手机号码"></div>

                                    </div>
                                </div>

                                <!--<div class="form-group row">
                                    <div class="col-md-6 "><div class="input-group"><span class="input-group-addon">学院</span><select class="form-control" id="Collage" name="Collage"><option >请选择</option></select></div></div>
                              <div class="col-md-6 "><div class="input-group"><span class="input-group-addon">班级</span><select class="form-control" id="Class" name="Class"><option>请选择</option></select></div></div>

                                </div>-->

                                <div class="form-group row">
                                    <div class="col-md-4"><span class="input-group-addon"><i
                                            class="fa fa-calendar">开始时间</i></span>
                                        <div class="input-group date form_datetime" data-link-field="begin">
                                            <input class="form-control" id="begin1" type="text"
                                                   value="2017-10-16 15:00:00">
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                            <input type="hidden" id="begin" value="2017-10-17 15:00:00"
                                                   name="set_order_time"/>
                                        </div>
                                    </div>

                                    <div class="col-md-4 "><span class="input-group-addon"><i class="fa fa-calendar">截止时间</i></span>
                                        <div class="input-group date form_datetime" data-link-field="end">
                                            <input class="form-control" id="end1" type="text"
                                                   value="2017-10-17 15:00:00">
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                        </div>
                                    </div>
                                    <div class="col-md-4"><span class="input-group-addon"><i
                                            class="fa fa-clock-o">请假节数</i></span><input type="text" name="Number"
                                                                                        id="Number"
                                                                                        class="form-control"/></div>
                                    <!-- <div class="col-md-2">共<input type="text" name="number">节</div> -->
                                </div>
                                <div class="form-group row">
                                    <label for="reason"><i class="fa fa-edit">请假理由</i></label>
                                    <textarea class="form-control" row="8" name="Reason" id="Reason"></textarea></div>
                                <div class="form-group row ">
                                    &nbsp;&nbsp;&nbsp;&nbsp;<label for="Inputfile"><i
                                        class="fa fa-upload">上传请假证明</i></label>
                                    &nbsp;&nbsp;&nbsp;&nbsp;<input type="file" id="File" name="File">

                                </div>
                                <div class="row">
                                    <div class="col-md-3 col-md-offset-9">
                                        <button type="reset" class="btn btn-default" title="重置"><i class="fa fa-reply">重置</i>
                                        </button>&nbsp;&nbsp;&nbsp;&nbsp;
                                        <button type="submit" class="btn btn-info" id="btn" title="提交申请"><i
                                                class="fa fa-check">提交申请</i></button>
                                    </div>
                                </div>


                            </form>

                        </div>
                    </div>
                </div>
            </div>

            <div class="footer" style="margin-bottom: 0px;">
                <div class="foot-text">易班学生工作站</div>
                <div class="foot-copyright">Copyright© 2017 ZQU E-Class</div>
            </div>
        </div>
    </section>
</div>

<script src="${path}/static/js/Bind.js"></script>
<script src="${path}/static/js/Student.js"></script>
<script type="text/javascript" src="${path}/static/source/layer/layer.js"></script>
<script src="${path}/static/source/layer/extend/layer.ext.js"></script>


</body>
</html>
     