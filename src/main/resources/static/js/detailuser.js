function getQueryString(name) { 
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
    var r = window.location.search.substr(1).match(reg); 
    if (r != null) return unescape(r[2]); 
    return null; 
} 

var id = getQueryString("id");

/**
 * 加载个人简历
 */
$("#datagridresume").datagrid({
	width : 700,
	fit : true,
	url : '/ResumeController/paginationQueryResume',
	title : '个人简历',
	striped : true,
	rownumbers : true,//加载编号
	fitColumns : true,
	queryParams : {
		id:id,
	},
	columns : [ [ {
		field : 'id',
		title : '编号',
		width : 40,
		checkbox : true,
	}, {
		field : 'startdate',
		title : '开始时间',
		width : 50,
	}, {
		field : 'enddate',
		title : '结束时间',
		width : 50,
	}, {
		field : 'description',
		title : '简历描述',
		width : 100,
	}, ] ],
	toolbar : '#tb',
	pagination : true,
	pagePosition : 'bottom',
});

/**
 * 加载家庭情况
 */
$("#datagridfamily").datagrid({
	width : 700,
	fit : true,
	url : '/FamilyMemberController/paginationQueryFamily',
	title : '家庭情况',
	striped : true,
	rownumbers : true,
	fitColumns : true,
	queryParams : {
         id:id,
	},//传递额外参数到后台
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
	toolbar : '#tb',//加载工具栏
	pagination : true,//增加分页
	pagePosition : 'bottom',//分页位置
});