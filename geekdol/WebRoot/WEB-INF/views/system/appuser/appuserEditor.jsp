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
    <form id="fm" method="post" novalidate>
        <div class="div_fitem_1">
            <label class="lable_add" for="nickName">昵称:</label>
            <input id="nickName" name="nickName" class="easyui-textbox">
        </div>
         <div class="div_fitem_1">
            <label class="lable_add" for="moblie">手机号:</label>
            <input id="moblie" name="moblie" class="easyui-textbox" required="true" data-options="validType:'phoneRex'">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="realName">真实姓名:</label>
            <input id="realName" name="realName" class="easyui-textbox" required="true">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="sex">性别:</label>
            <input id="sex" name="sex" value="0" type="radio" class="easyui-validatebox" checked="checked">${SEX_M }
            <input id="sex" name="sex" value="1" type="radio" class="easyui-validatebox">${SEX_W }
        </div>
        <div class="div_fitem_1" style="clear: both;">
            <label class="lable_add" for="birthDay">出生日 :</label>
            <input id="birthDay" name="birthDay" class="easyui-datebox" data-options="required:true">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="certificateType">证件类型:</label>
            <input id="certificateType" name="certificateType" class="easyui-combobox" required="true"
					data-options="valueField:'value',textField:'lable',url:'common/initSysData/${CERTIFICATES }'" style="width: 150px;">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="certificateNumber">证件号码:</label>
            <input id="certificateNumber" name="certificateNumber" class="easyui-textbox" required="true">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="email">邮箱:</label>
            <input id="email" name="email" class="easyui-textbox" data-options="validType:'email'">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="address">地址:</label>
            <input id="address" name="address" class="easyui-textbox">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="staffLevel">用户级别:</label>
            <input id="staffLevel" name="staffLevel" class="easyui-combobox" 
					data-options="valueField:'value',textField:'lable',url:'common/initSysData/${STAFFLEVELTYPE }'" style="width: 150px;">
        </div>
        <input name="id" id="id" type="hidden" value="${id}">
    </form>
</body>
<script type="text/javascript">
$.extend($.fn.validatebox.defaults.rules, {
	  phoneRex: {
	    validator: function(value){
		    var rex=/^1[3-8]{1}\d{9}$/;
		    if(rex.test(value)){
		       return true;
		    }else{
		       return false;
		    }
	    },
	    message: '请输入正确电话或手机格式'
	  }
});
function optSubmit(){
	 var val = $('#staffLevel').combobox('getValue');
		if(val==null||val==""){
			var res = "请选择用户级别";
			$.messager.show({
             title: 'Error',
             msg: res
         });
			return false;
		}
    $('#fm').form('submit',{
        url: "appUser/updateAppUser",
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
var id = $('#id').val();
$('#fm').form('load','appUser/findById/'+id+"?t="+new Date().getTime());
</script>
</html>