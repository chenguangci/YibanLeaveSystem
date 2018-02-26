<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath}" var="path"/>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>肇庆学院请假系统教师版</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <!-- basic styles -->
    <link rel="shortcut icon" href="${path}/static/images/favicon.ico">
    <link href="${path}/static/source/teacher/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${path}/static/source/teacher/assets/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${path}/static/js/bootstrap_above/bootstrap-table-develop/dist/bootstrap-table.css"/>
    <link href="${path}/static/js/bootstrap_above/x-editable-develop/dist/bootstrap-editable/css/bootstrap-editable.css"
          rel="stylesheet">
    <!--[if IE 7]>
    <link rel="stylesheet" href="/static/source/teacher/assets/css/font-awesome-ie7.min.css"/>
    <![endif]-->

    <!-- page specific plugin styles -->

    <!--<link rel="stylesheet" href="assets/css/jquery-ui-1.10.3.full.min.css" />
    <link rel="stylesheet" href="assets/css/ui.jqgrid.css" />-->

    <!-- fonts -->

    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300"/>

    <!-- ace styles -->

    <link rel="stylesheet" href="${path}/static/source/teacher/assets/css/ace.min.css"/>
    <link rel="stylesheet" href="${path}/static/source/teacher/assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="${path}/static/source/teacher/assets/css/ace-skins.min.css"/>

    <!--[if lte IE 8]>
    <link rel="stylesheet" href="/static/source/teacher/assets/css/ace-ie.min.css"/>
    <![endif]-->

    <!-- inline styles related to this page -->

    <!-- ace settings handler -->

    <script src="${path}/static/source/teacher/assets/js/ace-extra.min.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

    <!--[if lt IE 9]>
    <script src="/static/source/teacher/assets/js/html5shiv.js"></script>
    <script src="/static/source/teacher/assets/js/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="navbar navbar-default" id="navbar">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                <small>
                    <i class="icon-external-link"></i>
                    肇庆学院请假系统
                </small>
            </a><!-- /.brand -->
        </div><!-- /.navbar-header -->


    </div><!-- /.container -->
</div>

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span>
        </a>

        <div class="sidebar" id="sidebar">
            <script type="text/javascript">
                try {
                    ace.settings.check('sidebar', 'fixed')
                } catch (e) {
                }
            </script>

            <div class="sidebar-shortcuts" id="sidebar-shortcuts">


                <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                    <span class="btn btn-success"></span>

                    <span class="btn btn-info"></span>

                    <span class="btn btn-warning"></span>

                    <span class="btn btn-danger"></span>
                </div>
            </div><!-- #sidebar-shortcuts -->

            <ul class="nav nav-list">
                <li>
                    <a href="teacher.jsp">
                        <i class="icon-home"></i>
                        <span class="menu-text">首页</span>
                    </a>
                </li>


                <li class="active open">
                    <a href="#" class="dropdown-toggle">
                        <i class="icon-list"></i>
                        <span class="menu-text">请假系统 </span>

                        <b class="arrow icon-angle-down"></b>
                    </a>

                    <ul class="submenu">

                        <li class="active">
                            <a href="jqgrid.jsp">
                                <i class="icon-file"></i>
                                请假情况
                            </a>
                        </li>

                        <li>
                            <a href="system.jsp">
                                <i class="icon-wrench"></i>
                                系统设置
                            </a>
                        </li>


                    </ul>
                </li>


            </ul><!-- /.nav-list -->

            <div class="sidebar-collapse" id="sidebar-collapse">
                <i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
                   data-icon2="icon-double-angle-right"></i>
            </div>

            <script type="text/javascript">
                try {
                    ace.settings.check('sidebar', 'collapsed')
                } catch (e) {
                }
            </script>
        </div>

        <div class="main-content">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try {
                        ace.settings.check('breadcrumbs', 'fixed')
                    } catch (e) {
                    }
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="icon-home home-icon"></i>
                        <a href="teacher.jsp">首页</a>
                    </li>

                    <li>
                        <a href="#">请假系统</a>
                    </li>
                    <li class="active">请假情况</li>
                </ul><!-- .breadcrumb -->

                <div class="nav-search" id="nav-search">
                    <form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="请输入关键字" class="nav-search-input"
                                           id="nav-search-input" autocomplete="off"/>
									<button id="search_btn" title="搜索"><i
                                            class="icon-search nav-search-icon"></i></button>
								</span>
                    </form>
                </div><!-- #nav-search -->
            </div>

            <div class="page-content">
                <div class="page-header">
                    <h1>
                        请假系统
                        <small>
                            <i class="icon-double-angle-right"></i>
                            请假情况
                        </small>
                    </h1>
                </div><!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12 col-md-12 col-lg-12 ">
                        <div class="" style="margin: 20px;">
                            <a href="#" id="delete" class="btn btn-sm btn-primary icon-trash icon-blue">删除</a>
                            &nbsp;&nbsp;&nbsp;
                            <a href="#" id="print" class="btn btn-sm btn-success icon-print icon-blue">打印</a>
                        </div>
                        <table class="table-responsive" id="teacherTable">
                        </table>

                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.page-content -->
        </div><!-- /.main-content -->

        <!--
            模态框
        -->
        <div class="modal fade" id="Edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">修改信息</h4>
                    </div>
                    <form class="form-horizontal">
                        <div class="modal-body">
                            <div class="form-group row">
                                <div class="col-md-6 col-sm-6">
                                    <div class="input-group"><span class="input-group-addon"><i
                                            class="icon-bookmark"></i>学号</span>
                                        <input type="text" class="form-control" name="ID" id="ID" disabled/>
                                    </div>
                                </div>
                                <div class="col-md-6 col-sm-6">
                                    <div class="input-group"><span class="input-group-addon"><i class="icon-user"></i>姓名</span>
                                        <input type="text" class="form-control" name="Name" id="Name" disabled/>
                                    </div>
                                </div>

                            </div>

                            <div class="form-group row">
                                <div class="col-md-6 col-sm-6">
                                    <div class="input-group"><span class="input-group-addon"><i
                                            class="icon-building"></i>学院</span>
                                        <input type="text" class="form-control" name="Collage" id="Collage" disabled/>
                                    </div>
                                </div>
                                <div class="col-md-6 col-sm-6">
                                    <div class="input-group"><span class="input-group-addon"><i
                                            class="icon-reorder"></i>班级</span>
                                        <input type="text" class="form-control" name="Class" id="Class" disabled/>
                                    </div>
                                </div>


                            </div>

                            <div class="form-group row">
                                <div class="col-md-6 col-sm-6">
                                    <div class="input-group"><span class="input-group-addon"><i
                                            class="icon-calendar"></i>起始日期</span>
                                        <input type="text" class="form-control" name="Starttime" id="Starttime"
                                               disabled/>
                                    </div>
                                </div>
                                <div class="col-md-6 col-sm-6">
                                    <div class="input-group"><span class="input-group-addon"><i
                                            class="icon-calendar"></i>截止日期</span>
                                        <input type="text" class="form-control" name="Endtime" id="Endtime" disabled/>
                                    </div>
                                </div>

                            </div>

                            <div class="form-group row">
                                <div class="col-md-6 col-sm-6">
                                    <div class="input-group"><span class="input-group-addon"><i class="icon-phone"></i>联系方式</span>
                                        <input type="text" class="form-control" name="Mobile" id="Mobile" disabled/>
                                    </div>
                                </div>

                                <div class="col-md-6 col-sm-6">
                                    <div class="input-group"><span class="input-group-addon"><i class="icon-time"></i>请假节数</span>
                                        <input type="text" class="form-control" name="Number" id="Number" disabled/>
                                    </div>
                                </div>

                            </div>

                            <div class="form-group row">
                                <div class="col-sm-6 col-md-6">
                                    <div class="control-group">
                                        <label class="control-label bolder blue">请假状态</label>

                                        <div class="radio">
                                            <label>
                                                <input type="radio" class="ace" name="Qing" id="Qing1" value="待审核"/>
                                                <span class="lbl">待审核</span>
                                            </label>
                                        </div>

                                        <div class="radio">
                                            <label>
                                                <input type="radio" class="ace" name="Qing" id="Qing2" class="ace"
                                                       value="拒绝请假"/>
                                                <span class="lbl">拒绝请假</span>
                                            </label>
                                        </div>

                                        <div class="radio">
                                            <label>
                                                <input type="radio" class="ace" name="Qing" id="Qing3" class="ace"
                                                       value="同意请假"/>
                                                <span class="lbl">同意请假</span>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-6">
                                    <div class="control-group">
                                        <label class="control-label bolder blue">销假状态</label>

                                        <div class="radio">
                                            <label>
                                                <input type="radio" class="ace" name="Xiao" id="Xiao1" value="待销假"/>
                                                <span class="lbl">待销假</span>
                                            </label>
                                        </div>

                                        <div class="radio">
                                            <label>
                                                <input type="radio" class="ace" name="Xiao" id="Xiao2" value="已销假"/>
                                                <span class="lbl">已销假</span>
                                            </label>
                                        </div>


                                    </div>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="Reason" class="form-control-label">请假理由</label>

                                <textarea class="form-control" rows="3" name="Reason" id="Reason"
                                          disabled=""></textarea>
                            </div>

                            <br>
                            <div class="form-group row">
                                <label for="File" class="form-control-label">请假证明</label>
                                <br>
                                <a href="#" title="请单击下载"><i class="icon-download"></i>请假证明文件</a>
                            </div>
                        </div>

                        <br><br>
                        <div class="modal-footer">

                            <button type="button" class="btn btn-default" data-dismiss="modal"><i
                                    class="icon-remove"></i>关闭
                            </button>
                            <button type="button" class="btn btn-primary" id="submit"><i class="icon-ok"></i> 提交
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="ace-settings-container" id="ace-settings-container">
            <div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
                <i class="icon-cog bigger-150"></i>
            </div>

            <div class="ace-settings-box" id="ace-settings-box">
                <div>
                    <div class="pull-left">
                        <select id="skin-colorpicker" class="hide">
                            <option data-skin="default" value="#438EB9">#438EB9</option>
                            <option data-skin="skin-1" value="#222A2D">#222A2D</option>
                            <option data-skin="skin-2" value="#C6487E">#C6487E</option>
                            <option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
                        </select>
                    </div>
                    <span>&nbsp;选择皮肤 </span>
                </div>


            </div>
        </div><!-- /#ace-settings-container -->
    </div><!-- /.main-container-inner -->

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->


<!-- <![endif]-->

<!--[if IE]>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<![endif]-->

<!--[if !IE]> -->

<script type="text/javascript">
    window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>" + "<" + "/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>" + "<" + "/script>");
</script>
<![endif]-->

<script type="text/javascript">
    if ("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");


</script>


<!--
    描述：js导入，注意顺序，jquery必须引入
    bootstrap-table-zh-CN.js为表格汉化的文件
    FileSaver.min.js等是关联引用文件
-->
<script src="${path}/static/js/bootstrap_above/jquery-1.9.1/jquery.min.js"></script>
<script src="${path}/static/source/teacher/assets/js/bootstrap.min.js"></script>
<script type="text/javascript"
        src="${path}/static/js/bootstrap_above/bootstrap-table-develop/dist/bootstrap-table.js"></script>
<script type="text/javascript"
        src="${path}/static/js/bootstrap_above/bootstrap-table-develop/dist/locale/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript"
        src="${path}/static/js/bootstrap_above/bootstrap-table-develop/dist/extensions/export/bootstrap-table-export.js"></script>

<script src="${path}/static/js/bootstrap_above/tableExport/libs/pdfmake/pdfmake.min.js"></script>
<script src="${path}/static/js/bootstrap_above/tableExport/libs/pdfmake/vfs_fonts.js"></script>
<script type="text/javascript"
        src="${path}/static/js/bootstrap_above/tableExport/libs/FileSaver/FileSaver.min.js"></script>
<script type="text/javascript" src="${path}/static/js/bootstrap_above/tableExport/libs/jsPDF/jspdf.min.js"></script>
<script type="text/javascript"
        src="${path}/static/js/bootstrap_above/tableExport/libs/jsPDF-AutoTable/jspdf.plugin.autotable.js"></script>
<script type="text/javascript" src="${path}/static/js/bootstrap_above/tableExport/tableExport.js"></script>
<script type="text/javascript" src="${path}/static/source/layer/layer.js"></script>
<script src="${path}/static/source/layer/extend/layer.ext.js"></script>


<!--	<script src="js/bootstrap_above/x-editable-develop/dist/bootstrap-editable/js/bootstrap-editable.js"></script>-->
<script src="${path}/static/js/bootstrap_above/bootstrap-table-develop/dist/extensions/editable/bootstrap-table-editable.js"></script>


<script src="${path}/static/js/table.js"></script>

<script src="${path}/static/source/teacher/assets/js/typeahead-bs2.min.js"></script>

<!-- page specific plugin scripts -->

<script src="${path}/static/source/teacher/assets/js/date-time/bootstrap-datepicker.min.js"></script>
<script src="${path}/static/source/teacher/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
<script src="${path}/static/source/teacher/assets/js/jqGrid/i18n/grid.locale-en.js"></script>

<!-- ace scripts -->

<script src="${path}/static/source/teacher/assets/js/ace-elements.min.js"></script>
<script src="${path}/static/source/teacher/assets/js/ace.min.js"></script>


<!-- inline scripts related to this page -->


</body>
</html>
