/**
 * 活动信息
 */
$(function(){

	obj = {
		editRow:undefined,
		search:function(){
			$("#datagrid").datagrid('load',{
				name:$.trim($("input[name='name']").val()),
			});
		},
		
        /**
         * 添加活动报名信息
         */
		add:function(){
			 var content = "<iframe src='addsignup.html' id='context' name='context' width='100%' height='100%' frameborder='0' scrolling='no'></iframe>";
		        $('#dd').dialog({
		            content:content,
		            title: '活动报名',
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
		
		/**
		 * 修改活动
		 */
		edit:function(){
			var rows = $("#datagrid").datagrid('getSelections');
			if(rows.length == 1){
				var jq = top.jQuery;    
				var url = 'updatesignup.html?id='+rows[0].id+'&name='+rows[0].name;
				var content = '<iframe id="context" name="context" scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';     
            	$('#dd').dialog({
		            content:content,
		            title: '修改',
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
		/**
		 * 删除活动
		 */
		remove:function(){
			var rows = $("#datagrid").datagrid('getSelections');
			if(rows.length>0){
				$.messager.confirm('提示','您确定删除这些记录吗？',function(flag){
					if(flag){
						var ids = [];
						for(var i = 0;i<rows.length;i++){
							ids.push(rows[i].id);
						}
						$.ajax({
							type:'post',
							url:'/signUpController/remove',
							data:{
								ids:ids.join(','),
							},
							beforeSend:function(){
								$("#datagrid").datagrid('loading');
							},
							success:function(data){
								if(data.success){
									$("#datagridatt").datagrid('reload');
									$("#datagrid").datagrid('reload');
									$("#datagrid").datagrid('loaded');
									$("#datagrid").datagrid('unselectAll');
									$.messager.show({
										title:'提示',
										msg:data.msg,
									});
								}else{
									$.messager.alert('提示','删除失败','error',function(){
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
		uploadAttactment : function(){
        	var rows = $("#datagrid").datagrid('getSelections');
			if(rows.length == 1){
				var jq = top.jQuery;    
				var url = 'uploadAttachmentActivity.html?id='+rows[0].id;
				var content = '<iframe id="context" name="context" scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';     
           	    $('#dd').dialog({
		            content:content,
		            title: '上传文件',
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
				$.messager.alert('警告','必须选中一行且只能选中一行','warning');
			}
        },
		
	};
	
	
	/**
	 * 加载活动信息
	 */
	$("#datagrid").datagrid({
		width:1000,
		fit:true,
		url:'/signUpController/paginationQuery',
		title:'活动信息',
		striped:true,
		rownumbers:true,
		fitColumns:true,
		columns:[[
			{
				field:'id',
				title:'编号',
				width:40,
				checkbox:true,
			},
			{
				field:'name',
				title:'活动名称',
				width:100,
			},
			{
				field:'signupdate',
				title:'报名时间',
				width:200,
			},
			{
				field:'username',
				title:'报名人',
				width:50,
			},
			{
				field:'checkactivity',
				title:'是否通过',
				width:50,
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
		},
		 onClickRow:function(index,row){
	        	$("#datagridatt").datagrid({
					width:700,
					fit:true,
					url:'/AttachmentUploadController/paginationQueryActivity',
					title:'附件',
					striped:true,
					rownumbers:true,
					fitColumns:true,
					resizable : true,
					queryParams: {
						id: row.id,
					},
					columns:[[
						{
							field:'id',
							title:'编号',
							width:40,
							checkbox:true,
						},
						{
							field:'originallyname',
							title:'原本名字',
							width:50,
						},
						{
							field:'servername',
							title:'服务器名',
							width:50,
						},
					]],
					toolbar:'#tb2',
					pagination:true,
					pagePosition:'bottom',
				});
	        }
	});
})
function reloaddatagrid() {
    $("#datagrid").datagrid('reload');
    $("#datagrid").datagrid('loaded');
}

function reloaddatagridAtt() {
    $("#datagridatt").datagrid('reload');
    $("#datagridatt").datagrid('loaded');
}

function removeAtt(){
	var rows = $("#datagridatt").datagrid('getSelections');
	if(rows.length>0){
		$.messager.confirm('提示','您确定删除这些记录吗？',function(flag){
			if(flag){
				var ids = [];
				for(var i = 0;i<rows.length;i++){
					ids.push(rows[i].id);
				}
				$.ajax({
					type:'post',
					url:'/AttachmentUploadController/removeActivity',
					data:{
						ids:ids.join(','),
					},
					beforeSend:function(){
						$("#datagridatt").datagrid('loading');
					},
					success:function(data){
						if(data.success){
							$("#datagridatt").datagrid('reload');
							$("#datagridatt").datagrid('loaded');
							$("#datagridatt").datagrid('unselectAll');
							$.messager.show({
								title:'提示',
								msg:data.msg,
							});
						}else{
							$.messager.alert('提示','删除失败','error',function(){
								$("#datagridatt").datagrid('loaded');
							});
						}
					}
				});
			}
		});
	}else{
		$.messager.alert('提示','请选择你要删除的记录','info');
	}
}
