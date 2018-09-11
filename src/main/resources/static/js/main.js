$(function(){
	$("#nav").tree({
		url:"/treeController/tree",
		lines:true,
		onClick: function(node){
			if(node.url){
				if($("#tabs").tabs("exists",node.text)){
					$("#tabs").tabs("select",node.text);
				}else{
					$("#tabs").tabs('add',{
						title:node.text,
						iconCls:node.iconCls,
						closable:true,
						content:'<iframe width="100%" height="99%" id="iframe" name="iframe" frameborder="0" scrolling="auto" src="'+node.url+'"></iframe>'
					});
				}
			}
		}
	});
	
	$('#tabs').tabs({
		fit:true,
		border:false,
	});
})