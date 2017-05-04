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
    <script type="text/javascript" src="${basePath}static/js/validator.js"></script>
    <!-- ueditor begin -->
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/hbridge_ueditor.config.js"></script>
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
        <form id="fm" method="post" novalidate style="height: 100px; width: 100%;">
            <div class="div_fitem_1">
              <label class="lable_add">标题:</label>
              <input id="title" name="title" value="" class="easyui-textbox" data-options="validType:['isBlank','length[1,100]'],disabled:true">
            </div>
            <div class="div_fitem_1">
              <label class="lable_add" for="newsflashTimeStr" style="width: 25%;text-align: right;display: inline-block;">快报时间:</label> 
              <input class="easyui-datetimebox" type="text" name="newsflashTimeStr" id="newsflashTimeStr" required="true" editable="false"  disabled = "true"/>
            </div>
            <div class="div_fitem_1">
              <label class="lable_add">来源:</label>
              <input id="source" name="source" value="" class="easyui-textbox" data-options="validType:['length[1,200]']" disabled = "true">
              <input id="verifyStatus" name="verifyStatus" value="" type="hidden" />
              <input id="id" name="id" value="${id}" type="hidden" />
            </div>
            <input type="hidden" name="token" id="token" value="${token }" />
            <div class="div_fitem_1" style="width:100%;position:relative;">
	            <div style="width:25%;position:absolute">
	              <label class="lable_add" style="display: initial" >内容:</label>
	            </div>
	            <div class="content" style="margin-left:13%;width:84%;height:230px;">
                    ${content } 
               </div>
	        </div>
        </form>
</body>
<script type="text/javascript">
$('#fm').form('load','admNewsflash/findById/${id}?t='+new Date().getTime());
</script>
</html>