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
                <label class="lable_add">省名称:</label>
                <input id="provinceName" name="provinceName" class="easyui-textbox" data-options="required:true,validType:'isBlank',validType:'hanzi'">
            </div> 
            <div class="div_fitem_1">
                <label class="lable_add">省编码:</label>
                <input name="provinceCode" class="easyui-textbox" data-options="required:true,validType:'isBlank',validType:'engAndNum'">
            </div>
             <div class="div_fitem_1">
                <label class="lable_add">排序序号:</label>
                <input name="ordering" class="easyui-numberbox" data-options="validType:'integer'">
            </div>         
        </form>
</body>
<script type="text/javascript">
var url= "province/insert";

function optSubmit(){
	var provinceName = $('#provinceName').textbox('getValue');
	if (provinceName.length > 20) {
		$.messager.show({
            title: 'Warning',
            msg: '省名字长度超过20',
            timeout:1000,
        	showType:'slide'
        });
		return false;
	}
	$.ajax({
   		type: "POST",async:false,
   		url:"province/checkProvinceName",
   		dataType:"json",
   		data:{"provinceName":provinceName, "id":null},
   		async:false,
   		success: function(result){
       		if (result.type=='Error'){
       			$.messager.show({
       	            title: 'Warning',
       	            msg: '省名字重复',
       	            timeout:1000,
       	        	showType:'slide'
       	        });
            } else {
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
                        	// TODO:加载数据
                             //$('#dlg').dialog('close');        
                            //$('#dg').datagrid('reload'); 
                        }
                    }
                });
            }
   		}
   	});
}


</script>
</html>