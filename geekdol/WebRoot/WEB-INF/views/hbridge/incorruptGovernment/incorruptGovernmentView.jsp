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
	<script type="text/javascript" src="${contextPath}/static/js/lab2.js"></script>
	
	<!-- ueditor begin -->
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/geekstore_ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/lang/zh-cn/zh-cn.js"></script>
    <!-- ueditor end -->
    
	<style type="text/css">
		.s1{width:120px;}
		#d1{width:510px;height:320px;background-color:#F5DEB3;margin:0 auto;}
		#d2{height:30px;font-size:24px;background-color:blue;color:white;}
		#d3{padding-left:30px;}
	</style>
</head>
<body>
	<div class="ftitle"></div>
    <form id="fm" method="post" novalidate>
        <div class="div_fitem_1">
            <label for="title" class="label_Add">标题:</label>
            ${incorruptGovernment.title }
        </div>
        <div class="div_fitem_1">
            <label for="type" class="label_Add">类别:</label>
            ${incorruptGovernment.typeStr }
        </div>
        <div class="div_fitem_1">
            <label for="messageTime" class="label_Add">信息时间:</label>
            <fmt:formatDate value="${incorruptGovernment.messageTime }" type="both"/>
        </div>
        <c:if test="${INCORRUPTGOVERNMENTSTATUS_AREADY eq incorruptGovernment.publishStatus }">
	        <div class="div_fitem_1">
	            <label for="messageTime" class="label_Add">发布人:</label>
	            ${incorruptGovernment.publishPerson }
	        </div>
	        <div class="div_fitem_1">
	            <label for="messageTime" class="label_Add">发布时间:</label>
	            <fmt:formatDate value="${incorruptGovernment.publishTime }" type="both"/>
	        </div>
        </c:if>
        <div class="div_fitem_1">
            <label for="messageTime" class="label_Add">来源:</label>
            ${incorruptGovernment.contentFrom }
        </div>
        <div class="div_fitem_1">
            <label for="messageTime" class="label_Add">缩略图:</label>
            <img alt="" src="${incorruptGovernment.pic }" style="width: 100px;height: 100px;" onclick="showPic(this)">
        </div>
       	<div class="fitem" style="clear: both;padding-top: 8px;">
       		<label style="display: block; margin-bottom: 8px;">详情:</label>
       		${incorruptGovernment.content }
       	</div>
    </form>
</body>
<script type="text/javascript">
	//点击打开大图
	function showPic(obj) {  
		window.open(obj.src);
	}
</script>
</html>