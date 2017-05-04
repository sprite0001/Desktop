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
    <script type="text/javascript" src="${basePath}static/js/validator.js"></script>
</head>
<body>
	<div class="ftitle"></div>
        <form id="fm" method="post" novalidate style="height: 100px; width: 100%;">
            <div class="div_fitem_1">
              <label class="div_lable_1">级别编码:</label>
              <input id="staffCode" name="staffCode" value="" class="easyui-textbox" data-options="required:true,validType:['isBlank','length[1,50]']">
            </div>
            <input type="hidden" name="token" id="token" value="${token }" />
            <div class="div_fitem_1">
              <label class="div_lable_1">级别名称:</label>
              <input id="staffName" name="staffName" value="" class="easyui-textbox" data-options="required:true,validType:['isBlank','length[1,100]']">
            </div>
            <div class="div_fitem_1">
              <label class="div_lable_1">级别等级:</label>
              <input id="staffLevel" name="staffLevel" value="" class="easyui-textbox" data-options="required:true,validType:['isBlank','length[1,2]']">
            </div>
            <div class="div_fitem_1">
              <label class="div_lable_1">备注:</label>
              <input id="remark" name="remark" value="" class="easyui-textbox" data-options="validType:['length[1,255]']">
            </div>
        </form>
</body>
<script type="text/javascript">
var url= "staffLevel/saveAdd";

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
            }
        }
    });
}
 
</script>
</html>