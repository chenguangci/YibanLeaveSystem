$(document).ready(function() {
    /**
     * 下面是进行插件初始化
     * 你只需传入相应的键值对
     * */
//     var width2=($(window).width())/2;
//     var width1=$('.container').width();
//     var height1=$('.container').height();
////     alert(width1+" "+height1);
//     var width=width2-width1;
//     $(".container").animate({
//  
//     	
//    
//     },1000);
//     	 $("#index").animate({left:'250px'}).fadeOut("fast");
       $(".container").fadeIn(5000);
       	
    	$("#test").bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {/*输入框不同状态，显示图片的样式*/
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {/*验证*/
               Name: {/*键名username和input name值对应*/
                    message: '输入错误',
                    validators: {
                        notEmpty: {/*非空提示*/
                            message: '姓名不能为空'
                        },
                        stringLength: {/*长度提示*/
                            min: 2,
                            max: 5,
                           message:'必须至少两个字符'
                        },
                        regexp:
					{
						regexp:/^[\u4e00-\u9fa5]+$/,
						message:'必须是中文'
						}

                    }
                },
                Mobile:{
					validators:{
						notEmpty:{
							message:'联系方式不能为空'
						},
						regexp:
						{
							regexp:/^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/,
							message:'输入格式有误'
						}
					}
					
				},
               	Reason:
				{
					validators:{
						notEmpty:{
							message:'请假原因不能为空'
						},
						stringLength:{
							min:5,
							max:50,
							message:'请假原因不能少于5个字或者不能多于50个字'
						}
					}
				}
				,Number:
				{
					validators:{
						notEmpty:
						{
							message:'请假节数不能为空'
						},
						regexp:
						{
							regexp:   /^[1-9]\d*$/,
							message:'必须是整数，却不得为0'
						}
					}
				},
               	File:
				{
					validators:{
						notEmpty:{
							message:'请假证明不能为空'
						}
					}
				}

              
            }
        });
            // 修复bootstrap validator重复向服务端提交bug
//      $form.on('success.form.bv', function(e) {
//          // Prevent form submission
//          e.preventDefault();
//      });


  

});

$("#Number").click(function(){
	var reg = /^\s*|\s*$/g;

    
    var t1 = document.getElementById("begin1").value.replace(reg, "");
  
    var t2 = document.getElementById("end1").value.replace(reg, "");
    console.log(t1+" "+t2);
    reg = /^(\d+)\-(\d+)\-(\d+)\s+(\d+)\:(\d+)\:(\d*)$/;
    if (!reg.test(t1) || !reg.test(t2)) {
         throw new Error("Date Format is Error !");
         return;
    }
    var d1 = new Date(t1.replace(reg, "$1"), parseInt(t1.replace(reg, "$2")) - 1, t1.replace(reg, "$3"));
    d1.setHours(t1.replace(reg, "$4"), t1.replace(reg, "$5"), t1.replace(reg, "$6"));
    var d2 = new Date(t2.replace(reg, "$1"), parseInt(t2.replace(reg, "$2")) - 1, t2.replace(reg, "$3"));
    d2.setHours(t2.replace(reg, "$4"), t2.replace(reg, "$5"), t2.replace(reg, "$6"));
    if (d1 > d2) {
//     alert("时间错误，截止时间不能在开始时间之前");
       layer.msg("时间错误，截止时间不能在开始时间之前",{icon:5});
       var t3=document.getElementById("end");
       t3.onfocus();
       return false;
    }
  
	
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
			})