<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String content = (String)request.getAttribute("content");
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
	<!-- ueditor begin -->
	<script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/hbridge_ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/lang/zh-cn/zh-cn.js"></script>
    <!-- ueditor end -->
</head>
<body>
	<div>
	    <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
	</div>
	<form id="ueditorForm" method="POST">
		<div id="btns">
	        <button onclick="getContent()">提交</button>
		</div>
		<input type="hidden" id="content" name="content"/>
	</form>
	<script type="text/javascript">
		$(function(){
		    var ue = UE.getEditor('editor');
	        var ueditorBody ='<%=content%>';
	        //判断ueditor 编辑器是否创建成功
	        if(ueditorBody != null && ueditorBody != undefined && ueditorBody != ""){
	        	ue.addListener("ready", function () {
	    	        // editor准备好之后才可以使用
	    	        ue.setContent(ueditorBody);
	        	});
	        }
	    });
	    
	    function getContent() {
	        var arr = [];
	        arr.push(UE.getEditor('editor').getContent());
	        $("#content").val(arr.join("\n"));
	        $("#ueditorForm").attr("action","${contextPath}/toueditor");
	        $("#ueditorForm").submit();
	    }    
	</script>
</body>
</html>