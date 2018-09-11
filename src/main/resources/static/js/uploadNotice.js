/**
 * 公告编辑
 */


$(function(){
	
    obj = {
        search:function(){
            $("#datagrid").datagrid('load',{
                title:$.trim($("input[name='title']").val()),
            });
        },
        
        /**
         * 编辑公告
         */
        add:function(){
			 var content = "<iframe src='addNotice.html' id='context' name='context' width='100%' height='100%' frameborder='0' scrolling='auto'></iframe>";
		        $('#dd').dialog({
		            content:content,
		            title: '增加记录',
		            width: $(window).width()-200,
	                height: $(window).height()-100,
		            closed: false,
		            cache: false,
		            modal: true,
		            resizable:true,
		            maximizable:true,
		            buttons:[{
		                text:'保存',
		                iconCls:'icon-ok',
		                handler:function(){
		                	//点击得到提交表单方法
		                    document.getElementById("context").contentWindow.addNotice();
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
         * 修改已发布公告
         */
		edit:function(){
			var rows = $("#datagrid").datagrid('getSelections');
			if(rows.length == 1){
				var jq = top.jQuery;    
				var url = 'updateNotice.html?id='+rows[0].id;
				var content = '<iframe id="context" name="context" scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';     
           	    $('#dd').dialog({
		            content:content,
		            title: '修改记录',
		            width: $(window).width()-200,
	                height: $(window).height()-100,
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
         * 删除所发布公告
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
							url:'/NoticeController/remove',
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
        dialogNotice:function(noticeid) {
            var content = "<iframe src='/pages/notice/noticeDetail.html?noticeid="+noticeid+"' id='context' name='context' width='100%' height='100%' frameborder='0' scrolling='auto'></iframe>";
            $('#dd').dialog({
                content:content,
                title: "通知公告",
                width: $(window).width()-200,
                height: $(window).height()-100,
                closed: false,
                cache: false,
                modal: true,
                resizable:true,
                buttons:[{
                    text:'关闭',
                    iconCls:'icon-no',
                    handler:function(){
                        $('#dd').window('close');
                    }
                }],
            });
        },
        uploadAttactment : function(){
        	var rows = $("#datagrid").datagrid('getSelections');
			if(rows.length == 1){
				var jq = top.jQuery;    
				var url = 'uploadAttachment.html?id='+rows[0].id;
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
    

    $("#datagrid").datagrid({
        width:700,
        fit:true,
        url:'/NoticeController/paginationQuery',
        title:'通知公告',
        striped:true,
        rownumbers:true,
        fitColumns:true,
        resizable:true,
        columns:[[
        	{
				field:'id',
				title:'编号',
				width:40,
				checkbox:true,
			},
            {
                field:'title',
                title:'标题',
                width:200,
            },
            {
                field:'type',
                title:'公告类型',
                width:100,
            },
            {
                field:'uploador',
                title:'发布者',
                width:50,
            },
            {
                field:'date',
                title:'发布日期',
                width:150,
            },
        ]],
        toolbar:'#tb',
        pagination:true,
        pagePosition:'bottom',
        onClickRow:function(index,row){
        	$("#datagridatt").datagrid({
				width:700,
				fit:true,
				url:'/AttachmentUploadController/paginationQuery',
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
						width:100,
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
});
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
					url:'/AttachmentUploadController/remove',
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





