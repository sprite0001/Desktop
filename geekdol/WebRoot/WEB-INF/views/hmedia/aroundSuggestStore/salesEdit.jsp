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
</head>
<body>
    <form id="fm" method="post" novalidate enctype="multipart/form-data">
    	<input type="hidden" id="id" name="id" value="${id}" />
    	<input type="hidden" id="aroundSuggestStoreId" name="aroundSuggestStoreId" value="${aroundSuggestStorevo.id}" />
    	<input type="hidden" id="attachCount" name="attachCount" value="${attachCount}" />
    	<div class="div_fitem_1">
            <label class="lable_add">店名:</label> 
			${aroundSuggestStorevo.name}
		</div>
        <div class="div_fitem_1">
			<label class="lable_add">所属小区:</label> 
			${aroundSuggestStorevo.villageName}
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="startTime">开始时间:</label>
            <input id="startTimeStr" name="startTimeStr" class="easyui-datetimebox" formatter="conversion" required="true" style="width: 150px;" editable="false">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="endTime">结束时间:</label>
            <input id="endTimeStr" name="endTimeStr" class="easyui-datetimebox" formatter="conversion" required="true" style="width: 150px;" editable="false">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="applicationTime">申请时间:</label>
            <input id="applicationTimeStr" name="applicationTimeStr" class="easyui-datetimebox" formatter="conversion" required="true" style="width: 150px;" editable="false">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="promotionInfo">促销信息:</label>
            <input id="promotionInfo" name="promotionInfo" class="easyui-textbox" data-options="required:true,validType:'isBlank'" style="width: 150px;">
        </div>
        <div class="div_fitem_1" style="width:100%;position:relative;margin-top:12px;">
        	<div style="width:25%;position:absolute">
            	<label class="lable_add" style="display: initial" for="promotionDetails">促销详情:</label>
            </div>
            <textarea id="promotionDetails" name="promotionDetails" class="easyui-validatebox" data-options="required:true,validType:'isBlank'" style="margin-left:13%;width:70%;height:200px;"></textarea>
        </div>
        <div id="addFile" class="fitem" style="width:100%;">
	        <c:forEach var="attach" items="${attchList}" varStatus="status">
	        	<div class="div_fitem_1" style="margin-top:16px;">
	        		<label class="lable_add" for="propagandafile">宣传图片:</label>
	            	<input id="propagandafile" name="propagandafile" value="${attach.url }" type="file" style="width: 150px;">
	            	<img src="${attach.url }"  width="80px" height="80px" onclick="showPic(this)" style="cursor: pointer;"/>
	            </div>
	        </c:forEach>
        </div>
    </form>
</body>
<script type="text/javascript">
$(document).ready(function(){
	var attachCount = parseInt($('#attachCount').val());
	if (attachCount < 3) {
		var least = 3 - attachCount;
		for (var i=0; i<least; i++) {
			$('#addFile').append("<div class='div_fitem_1' style='margin-top:16px;'><label class='lable_add' for='propagandafile'>宣传图片:</label><input id='propagandafile' name='propagandafile' type='file' style='width: 150px;'></div>");
		}
	}
})
function saveSubmit(){
    /* 调用共通js中是否为IE的判定方法 */
   if(window.frames["addAroundStorePromotionFormUI"].contentWindow==undefined){
        window.frames["addAroundStorePromotionFormUI"].optSubmit();
   }else{
        window.frames["addAroundStorePromotionFormUI"].contentWindow.optSubmit();
   }
}

function conversion(value){
	var time = new Date(value);
	return time.format("yyyy-MM-dd hh:mm:ss");
}
$('#fm').form({onLoadSuccess:loadsucc});
$('#fm').form('load','aroundSuggestStore/findByPromotionId/${id}?t='+new Date().getTime());
//回调函数
function loadsucc(data) {  
	$("#thumbnail").attr("src",data.thumbnail);
	$("#businessLicense").attr("src",data.businessLicense);
}  
//点击打开大图
function showPic(obj) {  
	window.open(obj.src);
}
function optSubmit(val){
	var suggestStart = $('#startTimeStr').datetimebox('getValue');
	var suggestEnd = $('#endTimeStr').datetimebox('getValue');
	var sugstart = new Date(suggestStart);
	var sugend = new Date(suggestEnd);
	if(sugstart >= sugend){
        $.messager.show({
            title: 'Info',
            msg: "结束时间应大于开始时间！",
            timeout:1000,
            showType:'slide'
        });
        return;
	}
    $('#fm').form('submit',{
        url: "aroundSuggestStore/updateAroundSuggestStorePromotion",
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