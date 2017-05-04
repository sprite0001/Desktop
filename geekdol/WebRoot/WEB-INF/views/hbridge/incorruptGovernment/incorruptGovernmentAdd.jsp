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
    <form id="fm" method="post" novalidate enctype="multipart/form-data">
        <div class="fitem">
            <label for="title">标题:&nbsp;&nbsp;&nbsp;</label>
            <input id="title" name="title" class="easyui-textbox" data-options="required:true,validType:['isBlank','length[1,25]']" style="width: 540px;">
        </div>
        <div class="fitem">
            <label for="type" class="label_Add">缩略图:</label>
            <input style="width: 537px;" type="text" class="easyui-filebox" id="file1" name="file" data-options="onChange:validateFileType" required="required">
        </div>
        <div class="div_fitem_1">
            <label for="type" class="label_Add">类别:&nbsp;&nbsp;&nbsp;</label>
            <input id="type" name="type" class="easyui-combobox" 
					data-options="valueField:'value',textField:'lable',url:'common/initSysData/${INCORRUPTGOVERNMENTTYPE }'" editable="false" style="width: 150px;">
        </div>
        <div class="div_fitem_1">
            <label for="messageTime" class="label_Add">信息时间:</label>
            <input id="messageTime" name="messageTime" class="easyui-datetimebox" style="width: 160px;" required="required" editable="false">
        </div>
        <div class="div_fitem_1">
            <label for="contentFrom" class="label_Add">来源:&nbsp;&nbsp;&nbsp;</label>
            <input id="contentFrom" name="contentFrom" class="easyui-textbox" style="width: 150px;" data-options="required:true,validType:['isBlank','length[1,50]']">
        </div>
       	<div class="fitem" style="clear: both;padding-top: 8px;">
       		<label style="display: block; margin-bottom: 8px;">详情:</label>
       		<script id="editor" name="content" type="text/plain" style="width:700px;height:500px;"></script>
       	</div>
    </form>
</body>
<script type="text/javascript">
	$('#file1').filebox({
	    buttonText: '选择缩略图',
	    buttonAlign: 'right',
	    accept: 'image/*'
	});
	function validateFileType(){
		var fileName = $("#file1").filebox('getValue');
		var fileType = fileName.substr(fileName.lastIndexOf(".")+1);
		if(fileType != null && fileType !="png" && fileType !="jpg"
			&& fileType !="jpeg" && fileType !="bmp" && fileType !="gif"){
			$.messager.show({
	            title: 'Warning',
	            msg: "请选择正确图片格式！",
	            timeout:1000,
	            showType:'slide'
	        });
			$("#file1").filebox('setValue','');
		}
	}
	function optSubmit(){
		var type = $('#type').combobox('getValue');
		if (type == null || type == '') {
			$.messager.show({
	            title: 'Warning',
	            msg: "请选择类型！",
	            timeout:1000,
	            showType:'slide'
	        });
	        return;
		}
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
	        url: "incorruptGovernment/saveIncorruptGovernment",
	        onSubmit: function(){
	            return $(this).form('validate');
	        },
	        success: function(result){
	            var result = eval('('+result+')');
	            if ("Error"==result.type){
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
	            	setTimeout('window.parent.close()',2000);
	            }
	        }
	    });
	}
	$(function(){
		var ue = UE.getEditor('editor');
	});
</script>
</html>