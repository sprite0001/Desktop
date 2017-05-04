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
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/hmedia_ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/lang/zh-cn/zh-cn.js"></script>
    <!-- ueditor end -->
    
</head>
<body>
        <form id="fm" method="post" novalidate enctype="multipart/form-data">
            <div class="div_fitem_1" style="width: 100%">
                <label class="lable_add" style="width: 13%">随心拍人员:</label>
                ${userName}
            </div>
            <div class="div_fitem_1" style="width: 100%">
                <label class="lable_add" style="width: 13%">手机号:</label>
                ${mobile}
            </div>
            <div class="div_fitem_1" style="width: 100%">
                <label class="lable_add" style="width: 13%">发内容时的地址:</label>
                ${currentLocation}
            </div>
            <div class="div_fitem_1" style="width: 100%">
                <label class="lable_add" style="width: 13%">所属小区:</label>
                ${villageName}
            </div>
            <div class="div_fitem_1" style="width: 100%">
                <label class="lable_add" style="width: 13%">类型:</label>
                ${contentTypeName}
            </div>
            <div class="div_fitem_1" style="width: 100%">
                <label class="lable_add" style="width: 13%">状态:</label>
                ${treatmentStatusName}
            </div>
            
            <div id="addFile" class="fitem" style="width:100%;">
		        <c:forEach var="message" items="${attList}">
		        	<div class="div_fitem_1" style="margin-top:16px;">
		        		<label class="lable_add">展示图:</label>
		            	<img name="picture1" src="${message.url}"  width="80px" height="80px" onclick="showPic(this)" style="cursor: pointer;"/>
		            </div>
		        </c:forEach>
	        </div>
            
            <div class="div_fitem_1" style="width:100%;position:relative;">
		        <div style="width:25%;position:absolute;">
		          <label class="lable_add" style="display: initial" >内容:</label>
		        </div>
		        <div id="content" style="margin-left:13%;width:78%;position:absolute;">
		        	${content}
		        </div>
		    </div>
            
             <!-- 审核状态 -->
             <input id="treatmentStatus" name="treatmentStatus"  type="hidden" />
             
             <input name="id" id="currenId" type="hidden" value="${id}">
             
             
        </form>
</body>

<script type="text/javascript">

var id = $('#currenId').val();

//点击打开大图
function showPic(src)  {
	if(src != null){
		 window.open(src.src);
	}
}  

function optSubmit(){
    $('#treatmentStatus').val('02');
    
    $('#fm').form('submit',{
        url: "heartBeat/saveVerify",
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
                setTimeout('window.parent.close()',0);
            }
        }
    });
}
</script>
</html>