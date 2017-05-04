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
	<!-- ueditor begin -->
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/hbridge_ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/lang/zh-cn/zh-cn.js"></script>
    <!-- ueditor end -->
</head>
<body>
    <form id="fm" method="post" novalidate enctype="multipart/form-data">
    	<input id="id" name="id" type="hidden" value="${id}">
    	<div class="div_fitem_1">
       		<label class="lable_add" for="name">标题:</label>
        	<input id="title" name="title" class="easyui-textbox" data-options="required:true,validType:'length[1,50]'" style="width: 150px;">
        </div>
        <div class="div_fitem_1">
           	<label class="lable_add" for="newsTime">网安报时间:</label>
           	<input id="newsTimeStr" name="newsTimeStr" class="easyui-datetimebox" required="true" style="width: 150px;" editable="false">
        </div>
        <!-- <div class="div_fitem_1">
            <label class="lable_add" for="adress">摘要:</label>
            <input id="summary" name="summary" class="easyui-textbox" data-options="required:true,validType:'length[1,100]'" style="width: 150px;">
        </div> -->
        <div class="div_fitem_1" style="width: 100%">
         	<label class="lable_add" style="width: 13%" for="storeType">信息来源:</label> 
			<input id="source" name="source" class="easyui-textbox" data-options="required:true,validType:'length[1,100]'" style="width: 150px;">
        </div>
        <div class="div_fitem_1" style="width: 100%">
            <label class="lable_add" style="width: 13%" for="picturefile">缩略图:</label>
            <input id="picturefile" name="file" class="easyui-filebox" type="text">
            <img id="picture" src=""  width="80px" height="80px" onclick="showPic(this)" style="cursor: pointer;"/>
            <br><font style="color: #FF0000; margin-left: 13%;">请注意: 图片请按照宽195px,高130px进行上传,否则会变形失真</font>
        </div>
        <div class="div_fitem_1" style="width:100%;position:relative;">
            <div style="width:25%;position:absolute">
              <label class="lable_add" style="display: initial" >内容:</label>
            </div>
            <script id="editor" name="content" type="text/plain" style="margin-left:13%;width:84%;height:170px;">${content}</script>
        </div>
        <!-- 令牌防止重复提交  -->
        <input type="hidden" name="token" id="token" value="${token}" />
    </form>
    <script type="text/javascript">
    $(function(){
        var ue = UE.getEditor('editor');
    })
    $('#picturefile').filebox({
	    buttonText: '选择缩略图',
	    buttonAlign: 'right',
	    accept: 'image/*'
	});
    var id = $('#id').val();
    $('#fm').form({onLoadSuccess:loadsucc});
    $('#fm').form('load','netizenSecurity/findById/'+id+"?t="+new Date().getTime());
  	//回调函数
    function loadsucc(data) {  
    	$("#picture").attr("src",data.picture);
    }  
    //点击打开大图
    function showPic(obj) {  
    	window.open(obj.src);
    }
    function optSubmit(val){
    	var editor = UE.getEditor('editor');
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
            url: "netizenSecurity/updateNetizenSecurity/"+val,
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
</body>
</html>