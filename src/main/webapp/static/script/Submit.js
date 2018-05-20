var studentId = $("#studentId").val();
var beginTime;
var endTime;

//判断文件是否过大
// function isTooBig() {
//     if (document.getElementById("file").files[0].size / 1024 / 1024 > 5) {
//         alert(fileSize);
//         alert("请上传小于5MB的文件！");
//         return false;
//     }
//     return true;
// }


function getTime() {
    var begin = {
        elem: '#begin', //选择ID为begin的input
        format: 'YYYY-MM-DD hh:mm:ss', //自动生成的时间格式
        min: laydate.now(0, "YYYY-MM-DD hh:mm:ss"), //设定最小日期为当前日期
        max: '2099-06-16 23:59:59', //最大日期
        isclear: true, // 是否显示清空
        istime: true, //必须填入时间
        istoday: true,  //是否是当天
        calendar: true,
        theme: 'molv',
        begin: laydate.now(0, "YYYY-MM-DD hh:mm:ss"),  //设置开始时间为当前时间
        choose: function (datas) {
            beginTime = datas;
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.begin = datas //将结束日的初始值设定为开始日
        }
    };

    var end = {
        elem: '#end',
        format: 'YYYY-MM-DD hh:mm:ss',
        min: laydate.now(),
        max: '2099-06-16 23:59:59',
        isclear: true, // 是否显示清空
        istime: true, //必须填入时间
        istoday: true,  //是否是当天
        calendar: true,
        theme: 'molv',
        begin: laydate.now(0, "YYYY-MM-DD hh:mm:ss"),
        choose: function (datas) {
            endTime = datas;
            begin.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };

    laydate(begin);
    laydate(end);


}


/**
 * @return {boolean}
 */
function JudgeTime(begin, end) {


    //截取字符串，得到日期部分,用split把字符串分隔成数组
    var begin1 = begin.substr(0, 10).split("-");
    var end1 = end.substr(0, 10).split("-");

    //将拆分的数组重新组合，并实例成化新的日期对象
    var date1 = new Date(begin1[1] + -+begin1[2] + -+begin1[0]);
    var date2 = new Date(end1[1] + -+end1[2] + -+end1[0]);

    //得到两个日期之间的差值m，以分钟为单位
    //Math.abs(date2-date1)计算出以毫秒为单位的差值
    //Math.abs(date2-date1)/1000得到以秒为单位的差值
    //Math.abs(date2-date1)/1000/60得到以分钟为单位的差值
    var m = parseInt(Math.abs((date1 - date2)) / 1000 / 60);

    //小时数和分钟数相加得到总的分钟数
    //time1.substr(11,2)截取字符串得到时间的小时数
    //parseInt(time1.substr(11,2))*60把小时数转化成为分钟
    var min1 = parseInt(begin.substr(11, 2)) * 60 + parseInt(begin.substr(14, 2) + parseInt(begin.substr(16, 2)) / 60);
    var min2 = parseInt(end.substr(11, 2)) * 60 + parseInt(end.substr(14, 2) + parseInt(end.substr(16, 2)) / 60);

    //两个分钟数相减得到时间部分的差值，以分钟为单位
    var n = min2 - min1;

    //将日期和时间两个部分计算出来的差值相加，即得到两个时间相减后的分钟数
    var minutes = m + n;
    //检测时间
    if (minutes > 4320) {
        layer.msg("请假的时间不能超过三天", {icon: 2});
        return false;
    } else if ($.trim(begin) == "" || $.trim(end) == "" || begin == null || end == null) {
        layer.msg("开始或截止时间不能为空", {icon: 2});

        return false;
    } else
        return true;


}

function viewRecord() {


    window.location.href = "/student/record/" + studentId;
    // /YibanLeaveSystem/Toleave/index1
}

$(function () {

    $(".container").fadeIn(5000);

    getTime();

    $("#testForm").bootstrapValidator({
        message: '该值无效',
        feedbackIcons: {
            /* 输入框不同状态，显示图片的样式 */
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            /* 验证 */

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
            }

        }


    });

});


/*
 * $("#btn").click(function(){ var formData =new FormData(); var file
 * =document.getElementById("file").files[0]; // 获取文件对象 //
 * formData.append("information",vm.information);
 * formData.append("phone",vm.information.phone);
 * formData.append("beginTime",vm.information.beginTime);
 * formData.append("endTime",vm.information.endTime);
 * formData.append("number",vm.information.number);
 * formData.append("reason",vm.information.reason);
 * formData.append("file",file); console.log(formData); $.ajax({
 * url:"/YibanLeaveSystem/Toleave/leave1", type:"POST", data:formData,
 * async:false, cache:false, contentType:false,// 告诉jQuery不要去设置Content-Type请求头
 * processData:false, success: function(result) { if(result.success == true)
 * layer.alert(result.success, { icon: 1
 * 
 * }); else { layer.alert("请假失败,请重新检查你的请假信息", { icon: 2
 * 
 * }); } } }) });
 */


var vm = new Vue({
    el: "#testForm",
    data: {

        information: {
            studentId: "",
            phone: "",
            beginTime: "",
            endTime: "",
            number: "",
            reason: ""
        }

    },

    created: function () {

    },
    mounted: function () {
        // 获取名字和学号等信息

    },
    methods: {
        submitSignIn: function () {

            if (document.getElementById("file").files[0].size / 1024 / 1024 > 5) {
                alert("请上传小于5MB的文件！");
                return ;
            }
            $("#testForm").bootstrapValidator('validate');
            if ($("#testForm").data('bootstrapValidator').isValid() && JudgeTime(beginTime, endTime)) {
                vm.information.studentId = studentId;
                vm.information.beginTime = beginTime;
                vm.information.endTime = endTime;
                console.log(vm.information.beginTime);
                var information = {
                    "studentId": vm.information.studentId,
                    "phone": vm.information.phone,
                    "beginTime": vm.information.beginTime,
                    "endTime": vm.information.endTime,
                    "number": vm.information.number,
                    "reason": vm.information.reason
                };


                $.ajaxFileUpload({
                    url: "/student/leave",
                    type: "POST",
                    secureuri: false, // 是否需要安全协议，一般设置为false
                    fileElementId: ['file'],
                    data: information,
                    dataType: "text",

                    success: function (result) {
                        result = JSON.parse(result);
                        //result.success == true
                        if (result.success)
                            layer.alert(result.string, {
                                icon: 1

                            });
                        else {
                            layer.alert(result.msg, {
                                icon: 2

                            });
                        }

                    }

                });


            }
        }
    }
})