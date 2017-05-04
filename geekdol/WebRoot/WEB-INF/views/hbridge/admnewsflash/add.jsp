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
        <form id="fm" method="post">
            <div class="div_fitem_1">
              <label class="lable_add">标题:</label>
              <input id="title" name="title" value="" class="easyui-textbox"  data-options="required:true,validType:['isBlank','length[1,100]']">
            </div>
            <input type="hidden" name="token" id="token" value="${token }" />
            <div class="div_fitem_1">
              <label class="lable_add" for="newsflashTimeStr" style="width: 25%;text-align: right;display: inline-block;">快报时间:</label> 
              <input class="easyui-datetimebox" type="text" name="newsflashTimeStr" id="newsflashTimeStr" required="true" editable="false"/>
            </div>
            <div class="div_fitem_1">
              <label class="lable_add">来源:</label>
              <input id="source" name="source" value="" class="easyui-textbox" data-options="validType:['length[1,200]']">
              <input id="verifyStatus" name="verifyStatus" value="" type="hidden" />
            </div>
            <div class="div_fitem_1" style="width:100%;position:relative;">
	            <div style="width:25%;position:absolute">
	              <label class="lable_add" style="display: initial" >内容:</label>
	            </div>
	            <script id="editor" name="content" type="text/plain" style="margin-left:13%;width:84%;height:230px;"></script>
	        </div>
            
        </form>
</body>
<script type="text/javascript">
var url= "admNewsflash/saveAdd";
function optSubmit(status){
	$("#verifyStatus").val(status);
    var content = UE.getEditor('editor').getContent();
    var length = UE.getEditor('editor').getContentLength(true);
    if(length > 10000){
        $.messager.show({
            title: 'Info',
            msg: "内容字数大于10000，无法保存！",
            timeout:1000,
            showType:'slide'
        });
        return;
    }
    if(length <= 0){
        $.messager.show({
            title: 'Info',
            msg: "内容不能为空或全为空格！",
            timeout:1000,
            showType:'slide'
        });
        return;
    }
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

$(function(){
    var ue = UE.getEditor('editor',{
        autoHeightEnabled: false,
        initialFrameWidth : 700,
        initialFrameHeight: 250
    });
    var ueditorBody ='';
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
</html>