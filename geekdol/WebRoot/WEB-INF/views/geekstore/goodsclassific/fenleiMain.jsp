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
    <div style="padding:5px 5px;">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add"  plain="true" onclick="newUser()">新增</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-up"  plain="true" onclick="move()">上移</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-down"  plain="true" onclick="down()">下移</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"  onclick="destroyUser()">删除</a>
    </div>
    
       <div id="dlg" class="easyui-dialog" style="padding:10px 20px;width:600px;height:400px;overflow: hidden;"
            closed="true" buttons="#dlg-buttons" modal="true">
            <iframe id='addUserFormUI' border='0' vspace='0' hspace='0' 
                marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
                style="height:100%;width:100%;left:10px;top:8px;" src="">
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
</body>
<script type="text/javascript">
$('#tt').tree({
	lines:true,
	animate:true,
	dnd:true,
    url: 'goodsclassific/getTreeAll',
    onDrop: function(targetNode, source, point){
        var targetId = $("#tt").tree('getNode', targetNode).id;
    	$.ajax({
            url: 'goodsclassific/changeNode',
            type: 'post',
            dataType: 'json',
            data: {
                id: source.id,
                targetId: targetId,
                point: point
            },
            success:function(data) {  
            	$('#tt').tree('reload'); 
            	}
        });
        
    },
    loadFilter: function(rows){
    	  return convert(rows);      
    },
     onSelect:function(node){
    	$('#frame_work').attr('src',"goodsclassific/list?id="+node.id)
    	},
    onLoadSuccess:function(node,data){  
        $("#tt li:eq(0)").find("div").addClass("tree-node-selected");   //设置第一个节点高亮  
        var n = $("#tt").tree("getSelected");  
        if(n!=null){  
             $("#tt").tree("select",n.target);    //相当于默认点击了一下第一个节点，执行onSelect方法  
        }
        $("#tt li:eq(0)").find("div").removeClass("tree-node-selected");
     },
     onClick: function(node){
         $('#frame_work').attr('src',"goodsclassific/list?id="+node.id);
     } 
});

//上移
function move(){
    //获取选中的树的节点
    var node = $('#tt').tree('getSelected');
     if (node){
    	 var nodeTarget = $('#tt').tree('find',node.id-1);
         $('#tt').tree('expandTo', nodeTarget.target).tree('select', nodeTarget.target);
     }else{
         
     } 
}

//下移
function down(){
    //获取选中的树的节点
    var node = $('#tt').tree('getSelected');
     if (node){
    	 var nodeTarget = $('#tt').tree('find',node.id+1);
         $('#tt').tree('expandTo', nodeTarget.target).tree('select', nodeTarget.target);
     }else{
        
     } 
}

function newUser(){
	//获取选中的树的节点
	var node = $('#tt').tree('getSelected');
     if (node){
    	 $('#addUserFormUI').attr("src","goodsclassific/toAddGoodsclassific?id="+node.id);
     }else{
    	 //没有任何节点的时候   默认parentId为0
    	 $('#addUserFormUI').attr("src","goodsclassific/toAddGoodsclassific?id=0");
     } 
     $('#dlg').dialog('open').dialog('setTitle','新增');
     $('#fm').form('clear');
}

function saveUser(){
    /* 调用共通js中是否为IE的判定方法 */
    if(isIE()) {
        window.frames["addUserFormUI"].optSubmit();
    } else {
        window.frames["addUserFormUI"].contentWindow.optSubmit();
    }
}
//删除选中的节点
function destroyUser(){
    var node = $('#tt').tree('getSelected');
    if (node){
        $.messager.confirm('提示','确定要删除吗?',function(r){
            if (r){
                $.post('goodsclassific/delete',{id:node.id,delFlag:'1'},function(result){
                    if (result.type=="Success"){
                         $.messager.show({    // show error message
                             title: 'Success',
                             msg: result.msg
                         });
                        $('#tt').tree('reload');    // reload the user data
                    } else {
                        $.messager.show({    // show error message
                            title: 'Error',
                            msg: result.msg
                        });
                    }
                },'json');
            }
        });
    }else{
        $.messager.alert('提示信息','请先选择要删除的节点。','info');
    }
}

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
                iconCls:"icon-down"
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
                var child = {id:row.id,text:row.name,iconCls:"icon-goods"};
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
</script>  
</html>