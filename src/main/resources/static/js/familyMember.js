/**
 * 家庭成员
 */
$(function() {

	obj = {
		editRow : undefined,
		search : function() {
			$("#datagrid").datagrid('load', {
				name : $.trim($("input[name='name']").val()),
			});
		},
		// 增加信息
		add : function() {
			var content = "<iframe src='addFamilyMember.html' id='context' name='context' width='100%' height='100%' frameborder='0' scrolling='no'></iframe>";
			$('#dd')
					.dialog(
							{
								content : content,
								title : '增加家庭成员信息',
								width : 600,
								height : 400,
								closed : false,
								cache : false,
								modal : true,
								resizable : true,
								buttons : [
										{
											text : '保存',
											iconCls : 'icon-ok',
											handler : function() {
												// 点击得到提交表单方法
												document
														.getElementById("context").contentWindow
														.submitForm();
												// 关闭窗口
												// $('#dd').window('close');

											}
										}, {
											text : '关闭',
											iconCls : 'icon-no',
											handler : function() {
												$('#dd').window('close');
											}
										} ],
							});
		},
		/**
		 * 修改
		 */
		edit : function() {
			var rows = $("#datagrid").datagrid('getSelections');
			if (rows.length == 1) {
				var jq = top.jQuery;
				var url = 'UpdateFamilyMember.html?id=' + rows[0].id;
				var content = '<iframe id="context" name="context" scrolling="auto" frameborder="0"  src="'
						+ url + '" style="width:100%;height:100%;"></iframe>';
				$('#dd')
						.dialog(
								{
									content : content,
									title : '修改',
									width : 600,
									height : 400,
									closed : false,
									cache : false,
									modal : true,
									resizable : true,
									buttons : [
											{
												text : '保存',
												iconCls : 'icon-ok',
												handler : function() {
													// 点击得到提交表单方法
													document
															.getElementById("context").contentWindow
															.submitForm();

												}
											}, {
												text : '关闭',
												iconCls : 'icon-no',
												handler : function() {
													$('#dd').window('close');
												}
											} ],
								});
			} else {
				$.messager.alert('警告', '修改必须选中一行且只能选中一行', 'warning');
			}

		},
		/**
		 * 删除
		 */
		remove : function() {
			var rows = $("#datagrid").datagrid('getSelections');
			if (rows.length > 0) {
				$.messager.confirm('提示', '您确定删除这些记录吗？', function(flag) {
					if (flag) {
						var ids = [];
						for (var i = 0; i < rows.length; i++) {
							ids.push(rows[i].id);
						}
						$.ajax({
							type : 'post',
							url : '/FamilyMemberController/remove',
							data : {
								ids : ids.join(','),
							},
							beforeSend : function() {
								$("#datagrid").datagrid('loading');
							},
							success : function(data) {
								if (data.success) {
									$("#datagrid").datagrid('reload');
									$("#datagrid").datagrid('loaded');
									$("#datagrid").datagrid('unselectAll');
									$.messager.show({
										title : '提示',
										msg : data.msg,
									});
								} else {
									$.messager.alert('提示', '删除失败', 'error',
											function() {
												$("#datagrid").datagrid(
														'loaded');
											});
								}
							}
						});
					}
				});
			} else {
				$.messager.alert('提示', '请选择你要删除的记录', 'info');
			}
		},

	};

	/**
	 * 加载个人简历
	 */
	$("#datagrid").datagrid({
		width : 700,
		fit : true,
		url : '/FamilyMemberController/paginationQuery',
		title : '个人简历',
		striped : true,
		rownumbers : true,
		fitColumns : true,
		columns : [ [ {
			field : 'id',
			title : '编号',
			width : 40,
			checkbox : true,
		}, {
			field : 'name',
			title : '姓名',
			width : 50,
		}, {
			field : 'relationship',
			title : '与本人关系',
			width : 50,
		}, {
			field : 'policy',
			title : '政治面貌',
			width : 100,
		}, {
			field : 'unit',
			title : '工作单位',
			width : 100,
		}, {
			field : 'position',
			title : '职位',
			width : 100,
		}, ] ],
		toolbar : '#tb',
		pagination : true,
		pagePosition : 'bottom',
		onRowContextMenu : function(e, rowIndex, rowData) {
			e.preventDefault();
			$("#menu").menu('show', {
				top : e.pageY,
				left : e.pageX,
			})
		}
	});
})
function reloaddatagrid() {
	$("#datagrid").datagrid('reload');
	$("#datagrid").datagrid('loaded');
}
