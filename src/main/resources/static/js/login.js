$(function() {
	$("#login").dialog({
		width : 300,
		height : 230,
		title : '登录',
		iconCls : 'icon-lock',
		modal : true,
		buttons : '#btn',
		closable : false,
		draggable : false,
	});
	// 管理员验证
	$("#loginname").validatebox({
		required : true,
		missingMessage : '请输入账号',
		invalidmessage : '账号不能为空',
	});
	$("#pwd").validatebox({
		required : true,
		validType : 'length[6,30]',
		missingMessage : '请输入密码',
		invalidmessage : '密码不能为空',
	});

	// 点击登录
	$("#btn a").click(function() {
		if (!$("#loginname").validatebox('isValid')) {
			$("#loginname").focus();
		} else if (!$("#pwd").validatebox('isValid')) {
			$("#pwd").focus();
		} else {
			$.ajax({
				url : '/SysUserController/login',
				type : 'POST',  //post方法安全性更高，相get方法比较而言
				data : {
					loginname : $("#loginname").val(),
					pwd : $("#pwd").val(),
					jurisdiction : $("#jurisdiction").val(),
				},//data 额外传输的数据

				success : function(data) {
					if (data == 1) {
						window.location.href = "main.html";
					} else {
						$.messager.alert("提示消息", "用户名或者密码错误或者权限错误！！！", "info");
					}
				},
			})
		}
	});

})