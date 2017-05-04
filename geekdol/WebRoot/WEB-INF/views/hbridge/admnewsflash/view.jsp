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
        <form id="fm" method="post" novalidate style="height: 100px; width: 100%;">
        <div style="width: 100%; display: inline-block">
        	<div class="div_fitem_1">
              <label class="lable_add">标题:</label>
              ${title }
            </div>
            <div class="div_fitem_1">
              <label class="lable_add" for="newsflashTimeStr">快报时间:</label> 
              <input class="easyui-datetimebox" type="text" name="newsflashTimeStr" id="newsflashTimeStr" disabled = "true" />
            </div>
        </div>
            <div class="div_fitem_1">
              <label class="lable_add">来源:</label>
              ${source }
              <input id="verifyStatus" name="verifyStatus" value="" type="hidden" />
              <input id="id" name="id" value="${id}" type="hidden" />
            </div>
            <div class="div_fitem_1" style="width:100%;position:relative;">
	            <div style="width:25%;position:absolute">
	              <label class="lable_add" style="display: initial" >内容:</label>
	            </div>
	            <div class="content" style="margin-left:13%;width:84%;height:auto;">
                    ${content } 
               </div>
	        </div>
            <div class="div_fitem_1" style="width:100%;">
              <label class="lable_add" style="width: 13%">审核意见:</label>
              <input id="verifySug" name="verifySug" class="easyui-textbox" data-options="multiline:true" value="" style="width:84%;height:100px"  disabled = "true">
            </div>
        </form>
</body>
<script type="text/javascript">
var url= "admNewsflash/saveVerify";
$('#fm').form('load','admNewsflash/findById/${id}?t='+new Date().getTime());
function optSubmit(status){
    $("#verifyStatus").val(status);
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