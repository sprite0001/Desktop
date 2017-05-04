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
        <form id="fm" method="post" novalidate style="height: 100px; width: 100%;">
            <div class="div_fitem_1">
                <label class="div_lable_1">登录账号:</label>
                <input name="userName" id="userName" value="${user.userName }" class="easyui-textbox" data-options="required:true,validType:['engAndNum','isBlank','length[1,100]']">
            </div>
            <div class="div_fitem_1">
                <label class="div_lable_1">用户真实姓名:</label>
                <input name="realName" id="realName" value="${user.realName }" class="easyui-textbox" data-options="validType:['hanzi','length[1,100]']">
            </div>
            <div class="div_fitem_1">
                <label class="div_lable_1">邮箱:</label>
                <input name="email" id="email" value="${user.email }" class="easyui-textbox" data-options="validType:['email','length[1,255]']">
            </div>
            <div class="div_fitem_1">
                <label class="div_lable_1">用户绑定手机号:</label>
                <input name="moblie" id="moblie" value="${user.moblie }" class="easyui-textbox" data-options="validType:['phoneRe','length[1,255]']" required="true">
            </div>
            <div class="div_fitem_1">
                <label class="div_lable_1">用户类型:</label>
                <input id="userType" name="userType" class="easyui-combobox" editable="false" style="width: 150px;height:auto;">
            </div>
            <div class="div_fitem_1">
                <label class="div_lable_1">选择角色:</label>
                <input id="roleId" name="roleId" class="easyui-combobox" required="true" editable="false" style="width: 150px;height:auto;">
            </div> 
			<input name="id" id="id" type="hidden" value="${userId}">  
        </form>
</body>
<script type="text/javascript">
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
        url: "user/update",
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if ("Error"==result.type){
                $.messager.alert('提示信息',result.msg,'Error');
                return;
            } else {
            	$.messager.show({
                    title: 'Success',
                    msg: result.msg,
                    timeout:1000,
                	showType:'slide'
                });
            }
            setTimeout('window.parent.close()',2000);
        }
    });
}
$("#userType").combobox({
    url: 'sysdata/selectByType/USERTYPE', //获取所有私有域
    valueField: "value",
    textField: "lable",
    panelHeight: "auto",
    editable: false, //不允许手动输入
    onLoadSuccess: function () {
    	//数据加载完毕事件
        var data = $('#userType').combobox('getData');
        if (data.length > 0) {
            $("#userType").combobox('select', '${user.userType }');
        }
    }
});
$("#roleId").combobox({
    url: 'role/findAllNoPage', //获取所有私有域
    valueField: "roleId",
    textField: "name",
    editable: false, //不允许手动输入
    onLoadSuccess: function () {
        //数据加载完毕事件
        var data = $('#roleId').combobox('getData');
        if (data.length > 0) {
            $("#roleId").combobox('select', '${user.roleId }');
        }
    }
});

</script>
</html>