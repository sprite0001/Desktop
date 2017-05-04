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
</head>
<body>
    <form id="fm" method="post" novalidate enctype="multipart/form-data">
    	<div class="div_fitem_1">
            <label for="name">店名:</label>
            ${aroundStoreVo.name}
        </div>
        <div class="div_fitem_1">
            <label for="villageId">所属小区:</label>
            ${aroundStoreVo.villageName}
        </div>
        <div class="div_fitem_1">
            <label for="legalPerson">法人:</label>
            ${aroundStoreVo.legalPerson }
        </div>
        <div class="div_fitem_1">
            <label for="personCard">法人身份证:</label>
            ${aroundStoreVo.personCard }
        </div>
        <div class="div_fitem_1">
            <label for="organizationCode">组织代码证:</label>
          	${aroundStoreVo.organizationCode }
        </div>
        <div class="div_fitem_1">
            <label for="businessLicensefile">营业执照:</label>
           	<img src="${aroundStoreVo.licenseImage}"  width="80px" height="80px" onclick="showPic(this)" style="cursor: pointer;"/>
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