var studentId=document.getElementById('dataBox').innerHTML;
console.log(studentId);
$(document).ready(
		function() {

			$.ajax({
				url : "/YibanLeaveSystem/Toleave/info/" + studentId,
				type : "GET",
				contentType : "application/json",
				dataType : "json",
				success : function(data) {

					var i = 0;
					var status;
					var tbody = "";
					$.each(data, function(n, value) {
						i++;
						var trs = "";
						trs += "<tr><td>" + i + "</td><td>" + value.beginTime
								+ "</td><td>" + value.endTime + "</td><td>"
								+ statusFormartter(value.status)
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

var statusFormartter = function(value) {

	if (value == 0)

		return "<span class='label label-primary'>待审核</span>";

	else if (value == -1)

		return "<span class='label label-warning'>拒批准</span>";

	else if (value == 1)

		return "<span class='label label-default'>未销假</span>";

	else if (value == 2)

		return "<span class='label label-success'>已销假</span>";

}
