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
        <form id="fm" method="post" novalidate enctype="multipart/form-data">
          
           <div style="clear: both; padding-top: 8px;">
            <label style="width: 68px; text-align: left; display: inline-block;">标题:</label>
            ${netizenAcumenVo.title}
           </div>
           <div style="clear: both; padding-top: 8px;">
            <label style="width: 68px; text-align: left; display: inline-block;">类别:</label>
           ${netizenAcumenVo.type}
           </div>
                 <div style="clear: both; padding-top: 8px;">
            <label style="width: 68px; text-align: left; display: inline-block;">发布人地址:</label>
            ${netizenAcumenVo.address}
           </div>
           
            <div style="clear: both; padding-top: 8px;">
            <label style="width: 68px; text-align: left; display: inline-block;">来源:</label>
            ${netizenAcumenVo.source}
           </div>
           <div style="clear: both; padding-top: 8px;">
            <label style="width: 68px; text-align: left; display: inline-block;">发布时间:</label>
            ${netizenAcumenVo.insYmdhmsStr}
           </div>
           
            <div style="clear: both; padding-top: 8px;">
            <label style="width: 68px; text-align: left; display: inline-block;">发布人电话:</label>
            ${netizenAcumenVo.phone}
           </div>
             
             <div style="clear: both; padding-top: 8px;">
            <label style="width: 68px; text-align: left; display: inline-block;">发布人IP:</label>
            ${netizenAcumenVo.insIp}
           </div>
             
            
            <div class="div_fitem_1" style="width:100%;position:relative;">
              <div style="width:25%;position:absolute;">
               <label class="lable_add" style="display: initial" >内容:</label>
              </div>
              <div id="content" style="margin-left:13%;width:78%;">
               ${netizenAcumenVo.reason}
             </div>
            </div>
           
           <div id="addFile" class="fitem" style="width:100%;">
		        <c:forEach var="attach" items="${attchList}" varStatus="status">
		        	<div class="div_fitem_1" style="margin-top:16px;">
		        		<label class="lable_add" for="propagandafile">图片:</label>
		            	<img src="${attach.url }"  width="80px" height="80px" onclick="showPic(this)" style="cursor: pointer;"/>
		            </div>
		        </c:forEach>
	        </div>
             <input name="id" id="id" type="hidden" value="${id}">
        </form>
</body>
<script type="text/javascript">
function conversion(value){
    var time = new Date(value);
    return time.format("yyyy-MM-dd");
};
//点击打开大图
function showPic(obj) {  
	window.open(obj.src);
}
</script>
</html>