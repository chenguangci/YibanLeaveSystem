var studentId=document.getElementById('dataBox').innerHTML;

var cleanTime = function(str)
{
	var str1 = str.substr(0, str.length - 2);
	return str1;
}

$(document).ready(function() {

			$.ajax({
				///YibanLeaveSystem/Toleave/info/
				url : "../../student/info",
				type : "GET",
				contentType : "application/json",
				dataType : "json",
				success : function(data) {

					var i = 0;
					var tbody = "";
					$.each(data, function(n, value) {
						i++;
						var trs = "";
						trs += "<tr><td data-label='请假记录'>" + i + "</td><td data-label='开始时间'>" + cleanTime(value.beginTime)
								+ "</td><td data-label='结束时间'>" +cleanTime(value.endTime) + "</td><td data-label='请假状态'>"
								+ statusFormatter(value.status)
								+ "</td><</tr>";
						tbody += trs;

					});
					$("#record").append(tbody);

				},

				error : function() {
					layer.msg("加载失败", {
						icon : 2
					});
				}
			});

		});

var statusFormatter = function(value) {

	if (value == 0)

		return "<span class='label label-primary'>待审核</span>";

	else if (value == -1)

		return "<span class='label label-warning'>拒批准</span>";

	else if (value == 1)

		return "<span class='label label-default'>未销假</span>";

	else if (value == 2)

		return "<span class='label label-success'>已销假</span>";

}
