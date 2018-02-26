		
$(function () {
   $.ajax({
   	type:"post",
   	url:"",
   	async:false,
   	dataType:'json',
   	success:function(data)
   	{
   		layer.load(0, {shade: false});
   		$("#teacherTable").bootstrapTable('load',data);
   	},
   	error:function()
   	{
   		layer.msg("加载失败",{icon:2});
   	}
   	
   });
    

    $('#teacherTable').bootstrapTable({
    	method:'POST',
    	contentType: "application/x-www-form-urlencoded",
        dataType:'json',
        search:false,
        cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        striped: false,                              //是否显示行间隔色
        sidePagination: "client", //分页方式：client客户端分页，server服务端分页（*）
        clickToselect :true,
        showColumns:false,
        pagination:true,
        minimumCountColumns:2,
        pageNumber:1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 20, 50, 100],        //可供选择的每页的行数（*）
        paginationPreText: "上一页",  
        paginationNextText: "下一页",  
        uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
        showRefresh: false,                  //是否显示刷新按钮
        showExport: false, 
        showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false, 
        exportDataType: 'all',
        exportTypes:[ 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf'],  //导出文件类型
         queryParamsType:'limit', 
        //查询条件 
        queryParams: function (params) {    //请求服务数据时所传参数
        //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                    var temp = {   
                        rows: params.limit,                         //页面大小
                        page: (params.offset / params.limit) + 1,   //页码
                        ID:$.trim($("#ID").val()),
                        Name:$.trim($("#Name").val()),
                        Class:$.trim($("#Class").val()),
                    };
                    return temp;
       
        },
        //可供选择的每页的行数（*）
        onPageChange: function (size, number) {

                },
        onEditableSave: function (field, row, oldValue, $el) {
        	//四个参数field, row, oldValue, $el分别对应着当前列的名称、当前行数据对象、更新前的值、编辑的当前单元格的jQuery对象。  
//          $.ajax({
//          	type:"post",
//          	url:"",
//          	data:row,
//              success: function (data, status) {
//                  if (status == "success") {
//                 alert("修改成功");
//              
//                  }
//              },
//              error: function () {
//              	 alert("加载失败");
//                 
//              },
//              complete: function () {
//
//              }
//          });
        },
//      onEditableHidden: function(field, row, $el, reason) { // 当编辑状态被隐藏时触发
//          if(reason === 'save') {
//              var $td = $el.closest('tr').children();
//          //    $td.eq(-1).html((row.price*row.number).toFixed(2));
//          //    $el.closest('tr').next().find('.editable').editable('show'); //编辑状态向下一行移动
//          } else if(reason === 'nochange') {
//              $el.closest('tr').next().find('.editable').editable('show');
//          }
//      },

       columns: [
              {
              	title:'全选',
              	field:'select',
              	checkbox:true,
              	align:"center"
       	
              },
               {
            
                 field:'ID',
                 name:'ID',
                 title:'学号',
                 align:"center",             
                 sortable:true,
                 edit:false
            
            }, {
                field: 'Name',
                name: 'Name',
                title: '姓名',
                align:"center", 
                edit:false,
                sortable: true
           
               
               
                 
            }, {
                field: 'Collage',
                name: 'Collage',
                title: '学院',
                align:"center", 
                edit:false,
                sortable: true,
            }, {
                  field: 'Class',
                  name: 'Class',
                  title: '班级',
                  align:"center", 
                  edit:false,
                  sortable:true
                 
                  
            }, {
                    field: 'Mobile',
                    name: 'Mobile',
                    title: '联系方式',
                    align:"center", 
                     edit:false,
                    sortable: true
                     },
          {
                    field: 'Starttime',
                    name:'Starttime',
                    title:'开始时间',
                    align:"center", 
                     
                    sortable: true,
      
					
                  
             }, {
                    field: 'Endtime',
                    name:'Starttime',
                    title:'开始时间',
                    
                    align:"center", 
                    sortable: true,
                  //  visible:false,
				    edit:false,
                    
                  
             },{
                
                    field: 'Number',
                    name: 'Number',
                    title: '请假节数',
                    sortable: true,
                    edit:false,
                    align:"center"
                   //  ,visible:false
           }, {
	        field: 'Qing',
	        name:'Qing',
	        title: '请假状态',
            sortable:true,
            align:"center"
            //,visible:false
		       
     
	      },{
                	
                    field: 'Xiao',        
                    name: 'Xiao',                    
                    title: '销假状态',
                    sortable:true,
                    align:"center"
                   //  ,visible:false
             
                    
              },{
                	field: 'Reason',
                    name: 'Reason',
                    title: '请假原因' ,
              //      visible:false
                    
              },{
                
                    field: 'File',
                    name: 'File',
                    title: '查看请假证明',                    
                    align:"center",
               //     visible:false
                  
                },{  
                            field : 'goodsId',  
                            title : '操作', 
                           // events: operateEvents,
                            formatter : function(value,  
                                    row, index) {
   
                          
                            var strHtml = '&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" title="查看或修改" alt="查看或修改" onclick="Edit('+index+')"><i class="icon-edit icon-white"></i></a>';
                            
						         return strHtml;
                            }
     
                           
                            
                        }
           
            
            ],
            
        onClickRow:function(row ,$element)
        {
       $('#ID').val(row.ID);
   	   $('#Name').val(row.Name);
   	   $('#Collage').val(row.Collage);
   	   $('#Class').val(row.Class);
   	   $('#Mobile').val(row.Mobile);
   	   $('#Starttime').val(row.Starttime);
   	   $('#Endtime').val(row.Endtime);
   	   $('#Number').val(row.Number);
   	   var Qing=$.trim(row.Qing);
   	   var Xiao=$.trim(row.Xiao);
   	  
   	   if(Qing=="待审核")
   	 
   	   document.getElementById("Qing1").checked=true;
   	 
   	   else if(Qing=="拒绝请假")
   	   document.getElementById("Qing2").checked=true;
   	   
   	   else if(Qing=="同意请假")
   	   document.getElementById("Qing3").checked=true;
   	   
   	   if(Xiao=="未销假")
   	   document.getElementById("Xiao1").checked=true;
   	   
   	   else
   	   document.getElementById("Xiao2").checked=true;
   
   	   $('#File').val(row.File);
   	    $('#Reason').val(row.Reason);
   		$('#Edit').modal('toggle'); 
            },
            locale:'zh-CN',//中文支持,
            data:[{
           	"ID":"001",
           	"Name":"小明",
           	"Collage":"计算机科学与软件学院、大数据学院",
           	"Class":"15软一",
           	"Mobile":"17876253414",
           	"Starttime":"2017-12-04 13:35:51",
           	"Endtime":"2017-12-23 13:35:51",
           	"Number":"4",
           	"Qing":"待审核",
           	"Xiao":"未销假",
           	"Reason":"hgjk方法和钢结构i",
           	"File":"jij"
           },
           {
           	"ID":"002",
           	"Name":"小红",
           	"Collage":"计算机科学与软件学院、大数据学院",
           	"Class":"软件工程",
           	"Mobile":"17876253414",
           	"Starttime":"2017-12-04 13:35:51",
           	"Endtime":"2017-12-23 13:35:51",
           	"Number":"4",
          	"Qing":"拒绝请假",
           	"Xiao":"已销假",
           	"Reason":"病假",
           	"File":"NULL"
           },{
           	"ID":"003",
           	"Name":"小明",
           	"Collage":"音乐学院",
           	"Class":"软件工程",
           	"Mobile":"17876253414",
           	"Starttime":"2017-12-04 13:35:51",
           	"Endtime":"2017-12-23 13:35:51",
           	"Number":"4",
           	"Qing":"待审核",
           	"Xiao":"未销假",
           	"Reason":"病假",
           	"File":"NULL"
           },{
           	"ID":"004",
           	"Name":"小明",
           	"Collage":"体育学院",
           	"Class":"软件工程",
           	"Mobile":"17876253414",
           	"Starttime":"2017-12-04 13:35:51",
           	"Endtime":"2017-12-23 13:35:51",
           	"Number":"4",
            "Qing":"待审核",
           	"Xiao":"未销假",
           	"Reason":"病假",
           	"File":"NULL"
           },{
           	"ID":"005",
           	"Name":"小明",
           	"Collage":"计算机科学与软件学院、大数据学院",
           	"Class":"软件工程",
           	"Mobile":"17876253414",
           	"Starttime":"2017-12-04 13:35:51",
           	"Endtime":"2017-12-23 13:35:51",
           	"Number":"4",
           	"Qing":"待审核",
           	"Xiao":"未销假",
           	"Reason":"病假",
           	"File":"NULL"
           }
           ,{
           	"ID":"005",
           	"Name":"小明",
           	"Collage":"计算机科学与软件学院、大数据学院",
           	"Class":"软件工程",
           	"Mobile":"17876253414",
           	"Starttime":"2017-12-04 13:35:51",
           	"Endtime":"2017-12-23 13:35:51",
           	"Number":"4",
           	"Qing":"待审核",
           	"Xiao":"未销假",
            "Reason":"病假",
           	"File":"NULL"
           }
           ,{
           	"ID":"005",
           	"Name":"小明",
           	"Collage":"计算机科学与软件学院、大数据学院",
           	"Class":"软件工程",
           	"Mobile":"17876253414",
           	"Starttime":"2017-12-04 13:35:51",
           	"Endtime":"2017-12-23 13:35:51",
           	"Number":"4",
           	"Qing":"待审核",
           	"Xiao":"未销假",
           	"Reason":"病假",
           	"File":"NULL"
           }
           ,{
           	"ID":"006",
           	"Name":"小明",
           	"Collage":"计算机科学与软件学院、大数据学院",
           	"Class":"软件工程",
           	"Mobile":"17876253414",
           	"Starttime":"2017-12-04 13:35:51",
           	"Endtime":"2017-12-23 13:35:51",
           	"Number":"4",
           	"Qing":"待审核",
           	"Xiao":"未销假",
           	"Reason":"病假",
           	"File":"NULL"
           }
           ,{
           	"ID":"007",
           	"Name":"小明",
           	"Collage":"计算机科学与软件学院、大数据学院",
           	"Class":"软件工程",
           	"Mobile":"17876253414",
           	"Starttime":"2017-12-04 13:35:51",
           	"Endtime":"2017-12-23 13:35:51",
           	"Number":"4",
           	"Qing":"待审核",
           	"Xiao":"未销假",
           	"Reason":"病假",
           	"File":"NULL"
           }
           ,{
           	"ID":"008",
           	"Name":"小明",
           	"Collage":"计算机科学与软件学院、大数据学院",
           	"Class":"软件工程",
           	"Mobile":"17876253414",
           	"Starttime":"2017-12-04 13:35:51",
           	"Endtime":"2017-12-23 13:35:51",
           	"Number":"4",
           	"Qing":"待审核",
           	"Xiao":"未销假",
           	"Reason":"病假",
           	"File":"NULL"
           }
           ,{
           	"ID":"009",
           	"Name":"小明",
           	"Collage":"计算机科学与软件学院、大数据学院",
           	"Class":"软件工程",
           	"Mobile":"17876253414",
           	"Starttime":"2017-12-04 13:35:51",
           	"Endtime":"2017-12-23 13:35:51",
           	"Number":"4",
           	"Qing":"待审核",
           	"Xiao":"未销假",
           	"Reason":"病假",
           	"File":"NULL"
           }
           ,{
           	"ID":"010",
           	"Name":"小明",
           	"Collage":"计算机科学与软件学院、大数据学院",
           	"Class":"软件工程",
           	"Mobile":"17876253414",
           	"Starttime":"2017-12-04 13:35:51",
           	"Endtime":"2017-12-23 13:35:51",
           	"Number":"4",
           	"Qing":"待审核",
           	"Xiao":"未销假",
           	"Reason":"病假",
           	"File":"NULL"
           }
           ,{
           	"ID":"011",
           	"Name":"小明",
           	"Collage":"计算机科学与软件学院、大数据学院",
           	"Class":"软件工程",
           	"Mobile":"17876253414",
           	"Starttime":"2017-12-04 13:35:51",
           	"Endtime":"2017-12-23 13:35:51",
           	"Number":"4",
           	"Qing":"待审核",
           	"Xiao":"未销假",
           	"Reason":"病假",
           	"File":"NULL"
           }
           ,{
           	"ID":"012",
           	"Name":"小明",
           	"Collage":"计算机科学与软件学院、大数据学院",
           	"Class":"软件工程",
           	"Mobile":"17876253414",
           	"Starttime":"2017-12-04 13:35:51",
           	"Endtime":"2017-12-23 13:35:51",
           	"Number":"4",
           	"Qing":"待审核",
           	"Xiao":"未销假",
           	"Reason":"病假",
           	"File":"NULL"
           }
           
           ]

	});
	 $('#teacherTable').bootstrapTable('hideColumn', 'Mobile');
	 $('#teacherTable').bootstrapTable('hideColumn', 'Starttime');
	 $('#teacherTable').bootstrapTable('hideColumn', 'Endtime');
	 $('#teacherTable').bootstrapTable('hideColumn', 'Number');
	 $('#teacherTable').bootstrapTable('hideColumn', 'Qing');
	 $('#teacherTable').bootstrapTable('hideColumn', 'Xiao');
	 $('#teacherTable').bootstrapTable('hideColumn', 'Reason');
	 $('#teacherTable').bootstrapTable('hideColumn', 'File');
});

function Delete(ID)
{


}
 
    //查询按钮事件
        $("#search-btn").click(function(){
        	$("#teacherTable").bootstrapTable('refresh',{url:""});
        });
 
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
	
	
	
})
