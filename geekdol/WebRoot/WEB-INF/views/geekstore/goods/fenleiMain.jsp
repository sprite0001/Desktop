<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
 <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>${title}</title>
    
    <link rel="stylesheet" type="text/css" href="${basePath}static/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${basePath}static/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${basePath}static/easyui/themes/color.css">
    <link rel="stylesheet" type="text/css" href="${basePath}static/easyui/demo/demo.css">
    <script type="text/javascript" src="${basePath}static/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${basePath}static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${basePath}static/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${basePath}static/js/common.js"></script> 
    <script type="text/javascript" src="${basePath}static/easyui/plugins/jquery.edatagrid.js"></script> 
</head>
<body class="easyui-layout">

    <div data-options="region:'west',width:'260px',title:'商品分类'">
       <div id="dlg" class="easyui-dialog" style="padding:10px 20px;width:600px;height:400px;"
            closed="true" buttons="#dlg-buttons" modal="true">
            <iframe id='addUserFormUI' border='0' vspace='0' hspace='0' 
                marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
                style="height:100%;width:100%;left:10px;top:8px" src="">
            </iframe>
        </div>
         <div id="dlg-buttons">
            <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
        </div>
    <ul id="tt" class="easyui-tree"  style="height:94%"></ul>  
    </div>

    <div id="div_work" data-options="region:'center',title:'下属子分类'">  
      <iframe id="frame_work" name="frame_work" src="" scrolling="auto" frameborder="0" style="width:100%;height:99%;"></iframe>   
    </div>
    <input type="hidden" id="goodsId" value="${goodsId }"> 
</body>
<script type="text/javascript">
$('#tt').tree({
	lines:true,
	animate:true,
	dnd:true,
    url: 'goods/getTreeAll',
    loadFilter: function(rows){
    	  return convert(rows);      
    },
     onSelect:function(node){
    	$('#frame_work').attr('src',"goods/list?id="+node.id)
    	},
    onLoadSuccess:function(node,data){  
        $("#tt li:eq(0)").find("div").addClass("tree-node-selected");   //设置第一个节点高亮  
        var n = $("#tt").tree("getSelected");  
        if(n!=null){  
             $("#tt").tree("select",n.target);    //相当于默认点击了一下第一个节点，执行onSelect方法  
        }  
     },
     onClick: function(node){
         $('#frame_work').attr('src',"goods/list?id="+node.id);
     } 
});

/* 转换的实现 */
function convert(rows){
    function exists(rows, parentId){
        for(var i=0; i<rows.length; i++){
            if (rows[i].id == parentId) return true;
        }
        return false;
    }
    
    var nodes = [];
    // get the top level nodes
    for(var i=0; i<rows.length; i++){
        var row = rows[i];
        if (!exists(rows, row.parentId)){
            nodes.push({
                id:row.id,
                text:row.name,
                iconCls:"icon-add"
            });
        }
    }
    
    var toDo = [];
    for(var i=0; i<nodes.length; i++){
        toDo.push(nodes[i]);
    }
    while(toDo.length){
        var node = toDo.shift();    // the parent node
        // get the children nodes
        for(var i=0; i<rows.length; i++){
            var row = rows[i];
            if (row.parentId == node.id){
                var child = {id:row.id,text:row.name,iconCls:"icon-save"};
                if (node.children){
                    node.children.push(child);
                } else {
                    node.children = [child];
                }
                toDo.push(child);
            }
        }
    }
    return nodes;
}
function optSubmit(){
	var row = document.getElementById('frame_work').contentWindow.getSelect();
	var array = new Array();
	var data = {
			id:$("#goodsId").val(),
			classificId:row.id,
			classificName:row.name
	}
	array.push(data);
   	if(row){
       	window.parent.closeClassific(array);
   	}else{
       	$.messager.show({
            title: 'Warming',
            msg: "请确认选择商品分类 ",
            timeout:1000,
        	showType:'slide'
        });
   	}
}
</script>  
</html>