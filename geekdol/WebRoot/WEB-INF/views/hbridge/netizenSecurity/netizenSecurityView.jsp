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
	div>p {
		margin: 0
	}
	</style>
</head>
<body>
	<div class="div_fitem_1" style="width: 100%">
   		<label class="lable_add" style="width: 13%" for="name">标题:</label>
    	${netizenSecurityVo.title}
    </div>
    <div class="div_fitem_1" style="width: 100%">
       	<label class="lable_add" style="width: 13%" for="newsTime">网安报时间:</label>
       	${netizenSecurityVo.newsTimeStr}
    </div>
    <%-- <div class="div_fitem_1">
        <label class="lable_add" for="adress">摘要:</label>
        ${netizenSecurityVo.summary}
     </div> --%>
     <div class="div_fitem_1" style="width: 100%">
      	<label class="lable_add" style="width: 13%" for="storeType">信息来源:</label> 
      	${netizenSecurityVo.source}
    </div>
    <div class="div_fitem_1">
        <label class="lable_add" for="picturefile">缩略图:</label>
        <c:if test="${netizenSecurityVo.picture == null || netizenSecurityVo.picture == ''}">
        	无
        </c:if>
        <c:if test="${netizenSecurityVo.picture != null && netizenSecurityVo.picture != ''}">
        	<img id="picture" src="${netizenSecurityVo.picture}"  width="80px" height="80px" onclick="showPic(this)" style="cursor: pointer;"/>
        </c:if>
    </div>
    <div class="div_fitem_1" style="width:100%;position:relative;">
        <div style="width:25%;position:absolute;">
          <label class="lable_add" style="display: initial" >内容:</label>
        </div>
        <div id="content" style="margin-left:13%;width:78%;position:absolute;">
        	${netizenSecurityVo.content}
        </div>
    </div>
    <script type="text/javascript">
  	//点击打开大图
    function showPic(obj) {  
    	window.open(obj.src);
    }
    </script>
</body>
</html>