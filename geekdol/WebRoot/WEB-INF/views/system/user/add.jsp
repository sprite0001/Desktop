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
	<div class="ftitle"></div>
        <form id="fm" method="post" novalidate style="height: 400px; width: 100%;">
            <div class="div_fitem_1">
                <label class="div_lable_1">登录账号:</label>
                <input name="userName" id="userName" class="easyui-textbox" data-options="required:true,validType:['engAndNum','isBlank','length[1,100]']">
            </div>
             <div class="div_fitem_1">
                <label class="div_lable_1">密码:</label>
                <input type="password" name="password" id="password" class="easyui-textbox" data-options="required:true,validType:['isBlank','length[1,100]']">
            </div>
            <div class="div_fitem_1">
                <label class="div_lable_1">用户真实姓名:</label>
                <input name="realName" id="realName" class="easyui-textbox" data-options="validType:['hanzi','length[1,100]']">
            </div>
            <div class="div_fitem_1">
                <label class="div_lable_1">邮箱:</label>
                <input name="email" id="email" class="easyui-textbox" data-options="validType:['email','length[1,255]']">
            </div>
            <div class="div_fitem_1">
                <label class="div_lable_1">用户绑定手机号:</label>
                <input name="moblie" id="moblie" class="easyui-textbox" data-options="validType:['phoneRe','length[1,255]']" required="true">
            </div>
            <div class="div_fitem_1">
                <label class="div_lable_1">用户类型:</label>
                <input id="userType" name="userType" class="easyui-combobox" 
					data-options="valueField:'value',editable:false,textField:'lable',url:'sysdata/selectByType/USERTYPE'" style="width: 150px;height:auto;">
            </div> 
            <div class="div_fitem_1">
                <label class="div_lable_1">选择角色:</label>
                <input id="roleId" name="roleId" class="easyui-combobox" 
                    data-options="required:true,valueField:'roleId',editable:false,textField:'name',url:'role/findAllNoPage'" style="width: 150px;height:auto;">
            </div> 
        </form>
</body>
<script type="text/javascript">
var url= "user/insert";
$.extend($.fn.validatebox.defaults.rules, {
	  phoneRe: {
	    validator: function(value){
		    var rex=/^1[3-8]{1}\d{9}$/;
		    if(rex.test(value)){
		       return true;
		    }else{
		       return false;
		    }
	    },
	    message: '请输入正确格式的手机号'
	  }
});
function optSubmit(){
	var password = $('#password').textbox('getValue');
	if(password == null || password.length < 6){
        $.messager.alert('提示信息','密码的长度不能小于6位','Error');
        return;
	}
	var roleId = $('#roleId').combobox('getValue');
	if(roleId == null || roleId == ''){
		$.messager.show({
            title: 'Warning',
            msg: '请选择用户角色',
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
                $.messager.alert('提示信息',result.msg,'Error');
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