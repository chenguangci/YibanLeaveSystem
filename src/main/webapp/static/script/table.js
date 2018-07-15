


var yibanId =document.getElementById('dataBox').innerHTML;

$(function () {


  $('#teacherTable').bootstrapTable({
    	method:'GET',
	  //"/YibanLeaveSystem/Toteacher/list"
        url:"/teacher/info",
        contentType:"application/json",
   	    dataType:"json",
        search:false,
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        striped: false,                              //是否显示行间隔色
         icons: {
		  paginationSwitchDown: 'glyphicon-collapse-down icon-chevron-down',
		  paginationSwitchUp: 'glyphicon-collapse-up icon-chevron-up',
		  refresh: 'glyphicon-refresh icon-refresh',
		  toggle: 'glyphicon-list-alt icon-list-alt',
		  columns: 'glyphicon-th icon-th',
		  detailOpen: 'glyphicon-plus icon-plus',
		  detailClose: 'glyphicon-minus icon-minus',
		},
	    pagination:true,
        clickToselect :true,
        sortable:true,
        showColumns:false,
        sortOrder: 'asc', //排序方式
		queryParams: getQueryParams,
	    sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
        pageSize: 10,                       //每页的记录行数（*）
        pageList: 10,        //可供选择的每页的行数（*）
        paginationPreText: "上一页",  
        paginationNextText: "下一页",  
        uniqueId: "studentId",                     //每一行的唯一标识，一般为主键列
        idField: "studentId",
        showRefresh: false,                  //是否显示刷新按钮
        showExport: false, 
        smartDisplay: true,
        searchOnEnterKey: true,
		searchAlign: 'right',
        showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false, 
        minimumCountColumns: 1, //最少允许的列数
        exportDataType: 'all',
        exportTypes:[ 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf'],  //导出文件类型
  
        //可供选择的每页的行数（*）
        onPageChange: function (size, number) {

              },
        onEditableSave: function (field, row, oldValue, $el) {
        	//四个参数field, row, oldValue, $el分别对应着当前列的名称、当前行数据对象、更新前的值、编辑的当前单元格的jQuery对象。  

        },

       columns: [
              {
              	title:'全选',
              	field:'select',
              	checkbox:true,
              	align:"center"
       	
              },
               {
            
                 field:'studentId',
                 name:'studentId',
                 title:'学号',
                 align:"center",             
                
                 edit:false
            
            }, {
                field: 'student.name',
                name: 'name',
                title: '姓名',
                align:"center", 
                edit:false,
              
              
            }, {
                field: 'student.department',
                name: 'department',
                title: '学院',
                align:"center", 
                edit:false,
               
            }, {
                  field: 'student.className',
                  name: 'className',
                  title: '班级',
                  align:"center", 
                  edit:false,
                 
                 
                  
            }, {
                    field: 'phone',
                    name: 'phone',
                    title: '联系方式',
                    align:"center", 
                     edit:false,
                   
                     },
             {
                    field: 'beginTime',
                    name:'beginTime',
                    title:'开始时间',
                    align:"center",
                    formatter:beginTimeFormartter 
                   
      
					
                  
             }, {
                    field: 'endTime',
                    name:'endTime',
                    title:'截止时间',                   
                    align:"center",                   
				    formatter:endTimeFormartter 
                    
                  
             },{  
                            field : 'goodsId',  
                            title : '操作', 
                            events: operateEvents,
                            formatter : function(value,  
                                    row, index) {
   
                          
                            var strHtml = '&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" title="查看或修改" alt="查看或修改" id="editButton" ><i class="icon-edit icon-white"></i></a>';
                            
						         return strHtml;
                            }
     
                           
                            
                  }
           
            
            ],
           
         locale:'zh-CN',//中文支持,
            
           

	});

});

var getQueryParams = function(params){
	var p = {
		limit: params.limit,
		pageNumber: params.offset/params.limit+1,
		yibanId:yibanId
	};
	return p;
}


var cleanTime = function(str)
{
	var str1 = str.substr(0, str.length - 2);
	return str1;
}
 
var beginTimeFormartter=function (value,row,index)
{
   return cleanTime(value);	
};

var endTimeFormartter=function (value,row,index)
{
   return cleanTime(value);	
};

window.operateEvents = {
		
	"click #editButton":function (e, value, row, index)	
	{
		   $("#Iid").val(row.id);
		   $("#studentId").val(row.studentId);
     	   $('#name').val(row.student.name);
     	   $('#department').val(row.student.department);
     	   $('#className').val(row.student.className);
     	   $('#phone').val(row.phone);
     	   $('#leaveNumber').val(row.number);
     	   $('#beginTime').val(cleanTime(row.beginTime));
     	   $('#endTime').val(cleanTime(row.endTime));

		 
     	   var status=$.trim(row.status);
     	 
     	  
     	   if(status==0)//待审核
     	 
     	   document.getElementById("Qing1").checked=true;
     	 
     	   else if(status==-1)//拒绝请假
     	   document.getElementById("Qing2").checked=true;
     	   
     	   else if(status==1)//未销假
     	   document.getElementById("Qing3").checked=true;
     	   
     	   else//已销假
     	   document.getElementById("Qing4").checked=true;

     	 
     	   
     	    $('#reason').val(row.reason);

     	    
     		$('#Edit').modal('toggle'); 
	}

};

$('#submitEdit').click(function(){
	var id=$("#Iid").val();
	var status = $('input:radio[name="Qing"]:checked').val();
	var information=
	{
		"id":id,
		"status":status
	};
	
	$.ajax({
		type:"POST",
        // "/YibanLeaveSystem/Toteacher/handle"
		url:"/teacher/handle",
		data:JSON.stringify(information),
		contentType:"application/json",
		dataType:"json",
		success:function(data)
		{
			if(data.success==true)
				layer.msg(data.msg,{icon:1});
			else
				layer.msg(data.msg,{icon:2});
		}
	});
	
	$('#Edit').modal('toggle'); 
	
}) ;
 
$("#down").click(function(){
	
	var id=$("#Iid").val();
	
	$('#Edit').modal('hide');
    // "/YibanLeaveSystem/Toteacher/download/"+id;
	window.location.href="/teacher/download/"+id;

   

});

$("#printExcel").click(function(){
	var Data= $("#teacherTable").bootstrapTable("getSelections");
	if(Data=="" ||Data == null)
	{
		layer.alert("请选择要打印的数据",{icon:6});
	}
	else
	{
		var List=[];
		for(var i=0;i<Data.length;i++)
		{
					
			  List.push(Data[i].id);
			  console.log(Data[i].id);
		}	
		
		/*window.location.href="/YibanLeaveSystem/Toteacher/downloadExcel?List="+List;*/
        // "/YibanLeaveSystem/Toteacher/downloadExcel/";
		   var url="/teacher/downloadExcel";
		   var form = $("<form></form>").attr("action", url).attr("method", "post");
		   form.append($("<input></input>").attr("type", "hidden").attr("name", "List").attr("value", List));
			form.appendTo('body').submit().remove();
		
	}
})
    //查询按钮事件
    $("#search-btn").click(function(){
        	$("#teacherTable").bootstrapTable('refresh',{url:""});
        });