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
	<style type="text/css">
		.fitem{
	    	margin-bottom: 7px;
		}
	</style>
</head>
<body>
	<div class="ftitle"></div>
        <form id="fm" method="post" novalidate style="height: 100px; width: 100%;" enctype="multipart/form-data">
        	<div class="fitem">
                <label>店名:${storeName}</label>
            </div>  
            <div class="fitem">
	            <label for="content">公告:</label>
	            <textarea rows="10" cols="60" id="content" name="content" class="easyui-validatebox" required="required"></textarea>
        	</div>
        	<div class="fitem">
	            <label for="file1">展示图1&nbsp;&nbsp;:</label>
	                <input type="text" class="easyui-filebox" required="required" id="file1" name="file" style="width: 355px;">
        	</div>
        	<div class="fitem">
	            <label for="file2">展示图2&nbsp;&nbsp;:</label>
	                <input type="text" class="easyui-filebox" required="required" id="file2" name="file" style="width: 355px;">
        	</div>
        	<div class="fitem">
	            <label for="file3">展示图3&nbsp;&nbsp;:</label>
	                <input type="text" class="easyui-filebox" required="required" id="file3" name="file" style="width: 355px;">
        	</div>
        	<div class="fitem">
	            <label for="file4">展示图4&nbsp;&nbsp;:</label>
	                <input type="text" class="easyui-filebox" id="file4" name="file" style="width: 355px;">
        	</div>
        	<div class="fitem">
	            <label for="file5">展示图5&nbsp;&nbsp;:</label>
	                <input type="text" class="easyui-filebox" id="file5" name="file" style="width: 355px;">
        	</div>
        	<div class="fitem">
	            <label for="file6">展示图6&nbsp;&nbsp;:</label>
	                <input type="text" class="easyui-filebox" id="file6" name="file" style="width: 355px;">
        	</div>
        	<div class="fitem">
	            <label for="file7">展示图7&nbsp;&nbsp;:</label>
	                <input type="text" class="easyui-filebox" id="file7" name="file" style="width: 355px;">
        	</div>
        	<div class="fitem">
	            <label for="file8">展示图8&nbsp;&nbsp;:</label>
	                <input type="text" class="easyui-filebox" id="file8" name="file" style="width: 355px;">
        	</div>
        	<div class="fitem">
	            <label for="file9">展示图9&nbsp;&nbsp;:</label>
	                <input type="text" class="easyui-filebox" id="file9" name="file" style="width: 355px;">
        	</div>
        	<div class="fitem">
	            <label for="file10">展示图10:</label>
	                <input type="text" class="easyui-filebox" id="file10" name="file" style="width: 355px;">
        	</div>
			<input name="storeId" id="storeId" type="hidden" value="${storeId}">        
        </form>
</body>
<script type="text/javascript">
$(function(){
	for(var i=1;i<=10;i++){
		$('#file'+i).filebox({
		    buttonText: '选择展示图',
		    buttonAlign: 'right',
		    accept: 'image/*'
		});
	}
});

var url= "cooperationStore/saveStoreNotice";
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
                    msg: result.Msg
                });
                
            } else {
            	$.messager.show({
                    title: 'Success',
                    msg: result.Msg,
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