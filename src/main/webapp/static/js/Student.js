
$("#Reason").click(function(){  
   var begin =$("#begin1").val();
   var end =$("#end1").val();
   var d1 = new Date(begin.replace(/\-/g, "\/"));  
   var d2 = new Date(end.replace(/\-/g, "\/"));   
   
    if (begin!="" && end !="" && d1 > d2) {

       layer.msg("时间错误，开始时间不能大于截止时间 ",{icon:5});
       
       $("#end1").focus();
       return false;
       
    }
  
	return true;
	
});
/*
 $('#begin1').datetimepicker({
        language:  'zh-CN',//选择语言类型
        weekStart: 1,//设置起始周
        todayBtn: true ,//打开底部今天按钮,false为关闭
        autoclose: true,//选中日期后自动关闭选择器
        todayHighlight: true,//高亮显示当前日期
        startView: 1,//设置为小时视图 ,1 hour 1 day 2 month 3 year 4 decade(十年)
        minView: 1,//设置最小视图为小时视图
        format: 'yyyy-mm-dd hh:mm:ss',//设置时间展现格式
        forceParse: true//是否强制解析时间格式和类型
 }).val(PrevHour);//初始化前端input默认值




 $('#end1').datetimepicker({
        language:  'zh-CN',//选择语言类型
        weekStart: 1,//设置起始周
        todayBtn: true ,//打开底部今天按钮,false为关闭
        autoclose: true,//选中日期后自动关闭选择器
        todayHighlight: true,//高亮显示当前日期
        startView: 1,//设置为小时视图 ,1 hour 1 day 2 month 3 year 4 decade(十年)
        minView: 1,//设置最小视图为小时视图
        format: 'yyyy-mm-dd hh:mm:ss',//设置时间展现格式
        forceParse: true//是否强制解析时间格式和类型
 }).val(Hour);//初始化前端input默认值
*/

/*	
$('.form_datetime').datetimepicker({
				weekStart: 1,
				todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				forceParse: 0,
				language:'zh-CN',
				format: 'yyyy-mm-dd hh:ii:ss',
				pickerPosition: 'bottom-left',
				showMeridian: 1
		});
		
	
$('.form_datetime').datetimepicker({
				weekStart: 1,
				todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				forceParse: 0,
				language:'zh-CN',
				format: 'yyyy-mm-dd hh:ii:ss',
				pickerPosition: 'bottom-left',
				showMeridian: 1
			})*/