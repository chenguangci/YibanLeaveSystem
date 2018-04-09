$(document).ready(function() {

	$(".container").fadeIn(5000);

	$("#testForm").bootstrapValidator({
		message: '该值无效',
		feedbackIcons: { /*输入框不同状态，显示图片的样式*/
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: { /*验证*/
			
			Mobile: {
				validators: {
					notEmpty: {
						message: '联系方式不能为空'
					},
					regexp: {
						regexp: /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/,
						message: '输入格式有误'
					}
				}

			},
			/*begin1:{
				validators: {
					notEmpty: {
						message: '请假开始时间不能为空'
					},
					callback: {
                        message: '开始日期不能大于截止日期',
                        callback:function(value, validator,$field,options){
                            var begin = $("#begin1").val();
                            return parseInt(value)<=parseInt(begin);
                        }
                    }
				}
				
			},
			end1:{
				validators: {
					notEmpty: {
						message: '请假截止时间不能为空'
					},
					callback: {
                        message: '开始日期不能大于截止日期',
                        callback:function(value, validator,$field,options){
                        	var begin = $("#begin1").val();
                            if(parseInt(value)<parseInt(begin))
                        
                            return false;
                        }
				}
			}
			},*/
			Reason: {
				validators: {
					notEmpty: {
						message: '请假原因不能为空'
					},
					stringLength: {
						min: 5,
						max: 50,
						message: '请假原因不能少于5个字或者不能多于50个字'
					}
				}
			},
			Number: {
				validators: {
					notEmpty: {
						message: '请假节数不能为空'
					},
					regexp: {
						regexp: /^[1-9]\d*$/,
						message: '必须是整数，却不得为0'
					}
				}
			},
			File: {
				validators: {
					notEmpty: {
						message: '请假证明不能为空'
					}
				}
			}

		}
	});

});

/*var bootstrapValidator = $("#testForm").data('bootstrapValidator');
	console.log(bootstrapValidator);
      var flag=true;*/
/*    var flag=$("#testForm").data('bootstrapValidator').isValid();
 */
var flag = true;
var vm = new Vue({
	el: "#testForm",
	data: {
		information: {
		phone: "",
		beginTime:"",
		endTime: "",
		number: "",
		reason: ""
		}
	},
	created: function() {

	},
	/*mounted: function() {
		//获取名字和学号等信息
		$.ajax({
			type: "POST",
			url: "",
			dataType: "json",
			success: function(data) {
				this.studentId = data.studentId;
				this.name = data.name;
				this.department = data.department;
				this.className = data.className;
			},
			error: function() {
				layer.alert("加载失败", {
					icon: 2

				});
			}

		})
	},*/
	methods: {
		submitSignin: function() {
			vm.information.studentId = this.studentId;
			console.log(JSON.stringify(vm.information) + " " + vm.information.number);
			if(flag) {
				$.ajaxFileUpload({
					type: "POST",
					url: "/student/leave",
					//secureuri: false, //是否需要安全协议，一般设置为false
					//fileElementId: ['file'],
					contentType: "application/json",
					data: JSON.stringify(vm.information),
					async: false,
					cache: false,
					success: function(result) {
						if(result.success)
							layer.alert(result.success, {
								icon: 1

							});
						else {
							layer.alert("请假失败,请重新检查你的请假信息", {
								icon: 2
							});
						}

					}

				})
			}
		}
	}
})