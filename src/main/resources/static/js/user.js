$(function(){

	obj = {
		editRow:undefined,
		search:function(){
			$("#datagrid").datagrid('load',{
				name:$.trim($("input[name='name']").val()),
			});
		},
		//添加一行
		add:function(){
			 var content = "<iframe src='addUser.html' id='context' name='context' width='100%' height='99%' frameborder='0' scrolling='no'></iframe>";
		        $('#dd').dialog({
		            content:content,
		            title: '用户添加',
		            width: 600,
		            height: 400,
		            closed: false,
		            cache: false,
		            modal: true,
		            resizable:true,
		            buttons:[{
		                text:'保存',
		                iconCls:'icon-ok',
		                handler:function(){
		                	//点击得到提交表单方法
		                    document.getElementById("context").contentWindow.submitForm();
		                    //关闭窗口
		                    //$('#dd').window('close');
		                    
		                }
		            },{
		                text:'关闭',
		                iconCls:'icon-no',
		                handler:function(){
		                    $('#dd').window('close');
		                }
		            }],
		        });
		},
		edit:function(){
			var rows = $("#datagrid").datagrid('getSelections');
			if(rows.length == 1){
				var jq = top.jQuery;    
				var url = 'updateUser.html?id='+rows[0].id;
				var content = '<iframe id="context" name="context" scrolling="auto" frameborder="0"  src="'+url+'" width="100%" height="99%"></iframe>';     
            	$('#dd').dialog({
		            content:content,
		            title: '修改用户信息',
		            width: 600,
		            height: 400,
		            closed: false,
		            cache: false,
		            modal: true,
		            resizable:true,
		            buttons:[{
		                text:'保存',
		                iconCls:'icon-ok',
		                handler:function(){
		                	//点击得到提交表单方法
		                    document.getElementById("context").contentWindow.submitForm();
		                    //关闭窗口
		                    //$('#dd').window('close');
		                    //刷新datagrid
		                    //reloaddatagrid();
		                    
		                }
		            },{
		                text:'关闭',
		                iconCls:'icon-no',
		                handler:function(){
		                    $('#dd').window('close');
		                }
		            }],
		        });  
			}else{
				$.messager.alert('警告','修改必须选中一行且只能选中一行','warning');
			}
			
		},
		remove:function(){
			var rows = $("#datagrid").datagrid('getSelections');
			if(rows.length>0){
				$.messager.confirm('提示','您确定删除这些记录吗？系统管理员无法进行删除',function(flag){
					if(flag){
						var ids = [];
						for(var i = 0;i<rows.length;i++){
							ids.push(rows[i].id);
						}
						$.ajax({
							type:'post',
							url:'/SysUserController/deleteUser',
							data:{
								ids:ids.join(','),
							},
							beforeSend:function(){
								$("#datagrid").datagrid('loading');
							},
							success:function(data){
								if(data.success){
									$("#datagrid").datagrid('reload');
									$("#datagrid").datagrid('loaded');
									$("#datagrid").datagrid('unselectAll');
									$.messager.show({
										title:'提示',
										msg:data.msg,
									});
								}else{
									$.messager.alert('提示',data.msg,'error',function(){
										$("#datagrid").datagrid('loaded');
									});
								}
							}
						});
					}
				});
			}else{
				$.messager.alert('提示','请选择你要删除的记录','info');
			}
		},
		findAll: function(){
			var rows = $("#datagrid").datagrid('getSelections');
			if(rows.length == 1){
				var jq = top.jQuery;    
				var url = 'pages/loginanduser/detailUser.html?id='+rows[0].id;
				var content = '<iframe id="context" name="context" scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';     
            	parent.$('#maindd').dialog({
		            content:content,
		            title: '查看用户信息',
		            width: $(window).width()-10,
		            height: $(window).height(),
		            closed: false,
		            cache: false,
		            modal: true,
		            resizable:true,
		            buttons:[{
		                text:'关闭',
		                iconCls:'icon-no',
		                handler:function(){
		                	parent.$('#maindd').window('close');
		                }
		            }],
		        });  
			}else{
				$.messager.alert('警告','必须选中一行且只能选中一行','warning');
			}
		}
		
	};
	
	
	$("#datagrid").datagrid({
		width:700,
		fit:true,
		url:'/SysUserController/paginationQuery',
		title:'系统用户',
		striped:true,
		rownumbers:true,
		fitColumns:true,
		columns:[[
			{
				field:'id',
				title:'编号',
				width:100,
				checkbox:true,
			},
			{
				field:'name',
				title:'用户姓名',
				width:100,
			},
			{
				field:'loginname',
				title:'登录名',
				width:100,
			},
			{
				field:'password',
				title:'密码',
				width:100,
			},
			{
				field:'email',
				title:'邮箱',
				width:100,
			},
			{
				field:'jurisdiction',
				title:'权限',
				width:100,
			},
			{
				field:'phone',
				title:'电话号码',
				width:100,
			},
			{
				field:'institute',
				title:'所属学院',
				width:100,
			},
			{
				field:'address',
				title:'地址',
				width:100,
			},
			{
				field:'sex',
				title:'性别',
				width:100,
			},
			{
				field:'birthday',
				title:'出生年月日',
				width:100,
			},
			{
				field:'policy',
				title:'政治面貌',
				width:100,
			},
			{
				field:'position',
				title:'职称',
				width:100,
			},
		]],
		toolbar:'#tb',
		pagination:true,
		pagePosition:'bottom',
		onRowContextMenu:function(e,rowIndex,rowData){
			e.preventDefault();
			$("#menu").menu('show',{
				top:e.pageY,
				left:e.pageX,
			})
		}
	});
})
function reloaddatagrid(){
	$("#datagrid").datagrid('reload');
	$("#datagrid").datagrid('loaded');
}



