
$(function(){
    obj = {
        search:function(){
            $("#datagrid").datagrid('load',{
                title:$.trim($("input[name='title']").val()),
            });
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
        }
    };

    $("#datagrid").datagrid({
        width:700,
        fit:true,
        url:'/NoticeController/paginationQueryAll',
        title:'通知公告',
        striped:true,
        rownumbers:true,
        fitColumns:true,
        columns:[[
            {
                field:'title',
                title:'标题',
                width:100,
                formatter: function(value,row,index) {///NoticeController/queryNotice/
                    return '<a onclick="obj.dialogNotice('+row.id+');" style="color:blue" href="#">' + row.title + '</a>';
                }
            },
            {
                field:'type',
                title:'公告类型',
                width:100,
            },
            {
                field:'uploador',
                title:'发布者',
                width:100,
            },
            {
                field:'date',
                title:'发布日期',
                width:100,
            },
        ]],
        toolbar:'#tb',
        pagination:true,
        pagePosition:'bottom',
    });
});
function reloaddatagrid() {
    $("#datagrid").datagrid('reload');
    $("#datagrid").datagrid('loaded');
}





