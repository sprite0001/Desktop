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
        	<div class="fitem">
                <label>发送店铺:${storeName }</label>
            </div>
	        <div class="fitem">
                <label>内容:</label>
                <textarea name="content" class="easyui-validatebox" required="required" style="height:300px;width: 500px"></textarea>
            </div>
			<input name="storeId" id="storeId" type="hidden" value="${storeId}">        
        </form>
        <div id="view_dlg" class="easyui-dialog" style="padding:10px 20px;width: 650px;height: 400px;"
           closed="true" modal="true">
		  	<iframe id='viewFormUI' border='0' vspace='0' hspace='0' 
		    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
		    	style="height:100%;width:100%;left:10px;top:8px">
		    </iframe>
	    </div>
</body>
<script type="text/javascript">
function viewStore(cooperationStoreId){
	$('#viewFormUI').attr("src","cooperationStore/viewCooperationStore/"+cooperationStoreId);
    $('#view_dlg').dialog('open').dialog('setTitle','合作店预览');
}
var url= "cooperationStore/pushMessage";
function optSubmit(){
    $('#fm').form('submit',{
        url: url,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if (result.type=="Error"){
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
            	setTimeout('window.parent.close()',100);
            }
        }
    });
}
</script>
</html>