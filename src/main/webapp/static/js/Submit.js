$("body").on('click','#btn',function(event){
		$('#test').bootstrapValidator('validate');
		var flag=$("#test").data('bootstrapValidator').isValid();
		if(!flag)
		layer.msg("填写有误，请重新填写");
		else
		{
			$.post('/student/leave',{
				"Mobile":$("#Mobile").val(),
				"Starttime":$("#begin1").val(),
				"Endtime":$("#end1").val(),
				"Number":$("#Number").val(),
				"Reason":$("#Reason").val()
			},function(data,textStatus,xhr){
				if(textStatus=='success')
				{
					$('#test').data('bootstrapValidator').resetForm(true);
				
				}
				else
				layer.msg("提交失败");
			});
		}
	});