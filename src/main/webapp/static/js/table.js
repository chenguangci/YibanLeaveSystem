		
$(function () {
/*   $.ajax({
   	type:"get",
   	url:"/Leave system/data/teacherList.json",
    contentType:"application/json",
   	dataType:"json",
   	success:function(data)
   	{
   		
   		
   	},
   	error:function()
   	{
   		layer.msg("加载失败",{icon:2});
   	}
   	
   });*/
    

    $('#teacherTable').bootstrapTable({
    	method:'GET',
        url:"/teacher/info",
        contentType:"application/json",
   	    dataType:"json",
        search:true,
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
        /*sortOrder: 'asc', //排序方式*/
		queryParams: getQueryParams,
	    sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 20, 50, 100],        //可供选择的每页的行数（*）
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
                 sortable:true,
                 edit:false
            
            }, {
                field: 'student.name',
                name: 'name',
                title: '姓名',
                align:"center", 
                edit:false,
                sortable: true
              
            }, {
                field: 'student.department',
                name: 'department',
                title: '学院',
                align:"center", 
                edit:false,
                sortable: true,
            }, {
                  field: 'student.className',
                  name: 'className',
                  title: '班级',
                  align:"center", 
                  edit:false,
                  sortable:true
                 
                  
            }, {
                    field: 'phone',
                    name: 'phone',
                    title: '联系方式',
                    align:"center", 
                     edit:false,
                    sortable: true
                     },
             {
                    field: 'beginTime',
                    name:'beginTime',
                    title:'开始时间',
                    align:"center",                     
                    sortable: true,
      
					
                  
             }, {
                    field: 'endTime',
                    name:'endTime',
                    title:'截止时间',
                    
                    align:"center", 
                    sortable: true,
				    edit:false,
                    
                  
             },{  
                            field : 'goodsId',  
                            title : '操作', 
                           // events: operateEvents,
                            formatter : function(value,  
                                    row, index) {
   
                          
                            var strHtml = '&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" title="查看或修改" alt="查看或修改" ><i class="icon-edit icon-white"></i></a>';
                            
						         return strHtml;
                            }
     
                           
                            
                        }
           
            
            ],
            
        onClickRow:function(row ,$element)
        {
       $('#studentId').val(row.studentId);
   	   $('#name').val(row.student.name);
   	   $('#department').val(row.student.department);
   	   $('#className').val(row.student.className);
   	   $('#phone').val(row.phone);
   	   $('#beginTime').val(row.beginTime);
   	   $('#endTime').val(row.endTime);
   	   $('#number').val(row.number);
   	   var status=$.trim(row.status);
   	 
   	  
   	   if(status==0)//待审核
   	 
   	   document.getElementById("Qing1").checked=true;
   	 
   	   else if(status==-1)//拒绝请假
   	   document.getElementById("Qing2").checked=true;
   	   
   	   else if(status==1)//未销假
   	   document.getElementById("Qing3").checked=true;
   	   
   	   else//已销假
   	   document.getElementById("Qing4").checked=true;

   	   $('#filePath').val(row.filePath);
   	    $('#reason').val(row.reason);
   		$('#Edit').modal('toggle'); 
   		
        },
         locale:'zh-CN',//中文支持,
            
           

	});

});

var getQueryParams = function(params){
	var p = {
		limit: params.limit,
		pageNumber: params.offset/params.limit+1,
		order: params.order,
		sort: params.sort,
		search: params.search
	};
	return p;
}


function Delete(studentId)
{


}
 
    //查询按钮事件
    $("#search-btn").click(function(){
        	$("#teacherTable").bootstrapTable('refresh',{url:""});
        });
/* 
$('#submit').click(function()
{
	var ID=$("#ID").val();
	var Qing=$("input[name='Qing']:checked").val();
	var Xiao=$("input[name='Xiao']:checked").val();
	
	$.ajax({
		type:"post",
		url:"",
		async:false,
		data:{
			"ID":ID,
			"Qing":Qing,
			"Xiao":Xiao
		},
		dataType:"json",
		error:function(data){
			layer.alert("提交失败",{
				 icon:2,
				 skin: 'layer-ext-moon'
				 			
				});
		},
		success:function(data)
		{
			layer.alert("更改成功",{
				 icon:1,
				 skin: 'layer-ext-moon'
				 			
				});
		}
	});
});

$("#delete").click(function(){
	var data=$("#teacherTable").bootstrapTable('getSelections');
	if(data=="")
	layer.alert("请选择要删除数据",{icon:6});
	else
	{
		var array=[];
		for(var i=0;i<data.length;i++)
		array.push(data[i].ID);
		layer.confirm('确认删除？删除后将不能恢复',{
		 btn: ['确认','取消'] //按钮
	},function(index)
	{
		layer.msg('已成功删除',{icon:1});
		$.ajax({
		type:"post",
		url:"",
		dataType:'json',
		data:{
			"IDs":array
		},
		//data:"IDs="+array,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success:function(result)
		{
			layer.msg('已成功删除',{icon:1});
			$("#teacherTable").bootstrapTable('refresh', {url: ''});
		},
		error:function()
		{
			layer.msg("删除失败",{icon:2});
		}
		

	    });
//      $.post(" ",{ID:array},function(data){
//      	
//      })

		
	},function(index){
		layer.msg('已取消删除');
	
	});
		
		
	}
	
	
});

$("#print").click(function(){

	var data=$("#teacherTable").bootstrapTable('getSelections');
	if(data=="")
	layer.alert("请选择要打印的数据",{icon:6});
	else
	{
		var array=[];
		for(var i=0;i<data.length;i++)
		array.push(data[i].ID);
		layer.confirm('确认要打印',{
		 btn: ['确认','取消'] //按钮
	},function(index)
	{
		layer.load(1, {
            shade: [0.1,'#fff'] //0.1透明度的白色背景
        });
		$.ajax({
		type:"post",
		url:"",
		dataType:'json',
		data:{
			"IDs":array
		},
		//data:"IDs="+array,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success:function(result)
		{
			layer.msg('已完成打印',{icon:1});
			
		},
		error:function()
		{
			layer.msg("打印失败",{icon:2});
		}
		

	    });
//      $.post(" ",{ID:array},function(data){
//      	
//      })

		
	},function(index){
		layer.msg('已取消打印');
	
	});
		
		
	}
	
	
	
})*/
