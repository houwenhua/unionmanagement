
$(function(){

	$("#datagridatt").datagrid({
		width:700,
		fit:true,
		url:'/AttachmentUploadController/paginationQuery',
		title:'附件',
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
		pagination:true,
		pagePosition:'bottom',
	});
})
function reloaddatagrid(){
	$("#datagrid").datagrid('reload');
	$("#datagrid").datagrid('loaded');
}
