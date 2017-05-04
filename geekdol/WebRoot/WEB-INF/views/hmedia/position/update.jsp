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
    <form id="fm" method="post" novalidate>
         <div class="div_fitem_1">
              <label class="lable_add">编号:</label>
              <input name="positionCode" id="positionCode" class="easyui-textbox" data-options="required:true,validType:['isBlank','length[1,45]','engAndNum']">
         </div>
         
         <div class="div_fitem_1">
              <label class="lable_add">模块:</label>
              <input id="typeId" name="moduleCode" class="easyui-combobox" 
                    data-options="valueField:'value',textField:'lable',url:'common/initSysData/${MODULECODE}'" required="required" editable="false">
         </div>
         
         <div class="div_fitem_1">
              <label class="lable_add">名称:</label>
              <input name="positionName"  class="easyui-textbox" data-options="required:true,validType:['isBlank','length[1,45]']">
         </div>
         
         <div class="div_fitem_1">
              <label class="lable_add">备注:</label>
              <textarea name="remark" style="vertical-align: top;" class="easyui-validatebox" data-options="required:true,validType:['isBlank','length[1,250]']"></textarea>
         </div>
            
        <input name="positionId" id="positionId" type="hidden" value="${positionId}">
    </form>
</body>
<script type="text/javascript">
 
var url= "position/update";
function optSubmit(){
	$.ajax({
   		type: "POST",async:false,
   		url:"position/checkPositionCode",
   		dataType:"json",
   		data:{"positionCode":$('#positionCode').textbox('getValue'), "id":$('#positionId').val()},
   		async:false,
   		success: function(result){
       		if (result.type=='Error'){
       			$.messager.show({
       	            title: 'Warning',
       	            msg: '广告位置编码重复',
       	            timeout:1000,
       	        	showType:'slide'
       	        });
            } else {
            	var typeId = $('#typeId').combobox('getValue');
        	    if(typeId=='0'||typeId==""){
        	        var res = "请选择模块";
        	        $.messager.show({
        	         title: 'Error',
        	         msg: res
        	     });
        	        return false;
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
   		}
   	});
}
var id = $('#positionId').val();
$('#fm').form('load','position/findById/'+id+"?t="+new Date().getTime());
</script>
</html>