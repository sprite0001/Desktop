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
                <label class="lable_add">标题:</label>
                <input name="title" class="easyui-textbox"  data-options="required:true,validType:'length[1,32]'">
            </div>
          
            <div class="div_fitem_1">
                <label class="lable_add">省:</label>
                <input id="province" name="provinceId" class="easyui-combobox" style="width:150px;" required="required">
            </div>
            
             <div class="div_fitem_1">
                <label class="lable_add">市:</label>
                <input id="city" name="cityId" class="easyui-combobox" style="width: 150px;" required="required">
            </div>

            <div class="div_fitem_2">
	            <label class="lable_add" style="width: 19%;">接受者:</label>
	            <input name="receiverType" value="01" type="radio" class="easyui-validatebox" checked="checked">${dianzhu}
	            <input name="receiverType" value="02" type="radio" class="easyui-validatebox">${wanggezhang}
	            <input name="receiverType" value="03" type="radio" class="easyui-validatebox">${dianzhu_wanggezhang}
            </div>
            
            <div style="clear: both; padding-top: 8px;">
                <label style="width: 68px; text-align: left; display: inline-block;">内容:</label>
                <textarea name="content" class="easyui-validatebox" required="required" style="height: 100px; width: 416px;vertical-align: top;"></textarea>
            </div>
            
        </form>
</body>
<script type="text/javascript">
function optSubmit(){
	
	   var provinceId = $('#province').combobox('getValue');
	    if(provinceId=='0'||provinceId==""){
	        var res = "请选择所属省";
	        $.messager.show({
	         title: 'Error',
	         msg: res
	     });
	        return false;
	    }
	    var cityId = $('#city').combobox('getValue');
	    if(cityId=='0'||cityId==""){
	        var res = "请选择所属市";
	        $.messager.show({
	         title: 'Error',
	         msg: res
	     });
	        return false;
	    }
    $('#fm').form('submit',{
        url: "message/saveMessage",
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

//发送信息
function sendMessage(){
    $('#fm').form('submit',{
        url: "message/sendMessage",
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
    
    // 省份 下拉框选择控件，下拉框的内容是动态查询数据库信息 
    $('#province').combobox({ 
                url:'common/initProvince',
                editable:false, //不可编辑状态
                cache: false,
                panelHeight: 'auto',//自动高度适合
                valueField:'provinceId',   
                textField:'provinceName',
                
                onHidePanel: function(){

            $("#city").combobox("setValue",'');
            $("#county").combobox("setValue",'');
            $("#county").combobox('loadData', {});
            $("#community").combobox("setValue",'');
            $("#community").combobox('loadData', {});
            var province = $('#province').combobox('getValue');     
            if(province!=''){
            $.ajax({
                type: "POST",
                url: "common/getCityByProvice/"+province,
                cache: false,
                dataType : "json",
                success: function(data){
                $("#city").combobox("loadData",data);
                                       }
                                   });  
                           }
                     } 
                 }); 
    //市下拉菜单
    $('#city').combobox({ 
        editable:false, //不可编辑状态
        cache: false,
        panelHeight: 'auto',//自动高度适合
        valueField:'cityId',   
        textField:'cityName',
       }); 
});

</script>
</html>