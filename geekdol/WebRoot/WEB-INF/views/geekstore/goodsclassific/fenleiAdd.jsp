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
</head>
<body>
	<div class="ftitle"></div>
        <form id="fm" method="post" novalidate>
            <div class="div_fitem_1">
                <label class="lable_add">编号:</label>
                <input name="code" class="easyui-textbox" required="true">
            </div>
            
            <div class="div_fitem_1">
                <label class="lable_add">名称:</label>
                <input name="name" class="easyui-textbox" required="true">
            </div>
          
            <div class="div_fitem_1">
                <label class="lable_add">备注:</label>
                <input  name="remarks" class="easyui-textbox" style="width: 100px; required="true"">
            </div>
            <!-- <div class="fitem">
                <label>操作人:</label>
            </div> -->
            <input value="${user.userName}" style="width: 100px;" type="hidden">
            
           <input type="hidden" name="parentId"  value="${parentId}">
            
        </form>
</body>
<script type="text/javascript">
var url= "goodsclassific/saveGoodsClassific";

function optSubmit(){
    $('#fm').form('submit',{
        url: url,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if ("Error"==result.type){
                $.messager.show({
                    title: 'Error',
                    msg: result.msg
                });
                
            } else {
            	$.messager.show({
                    title: 'Success',
                    msg: result.msg,
                    timeout:1000,
                	showType:'slide'
                });
            	setTimeout('window.parent.close()',2000);
                //两种情况  1:从fenleiMain.jsp跳转过来的 则掉用这个方法(parent.$('#tt').tree('reload');)
                //2:从fenleiList.jsp跳转过来的则掉用这个方法(parent.parent.$('#tt').tree('reload');)
                parent.$('#tt').tree('reload');
            	parent.parent.$('#tt').tree('reload'); 
            }
        }
    });
}
</script>
</html>