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
            <label class="lable_add">类型:</label>
            <input name="type" class="easyui-textbox"  data-options="required:true,validType:['isBlank','cnennum','length[1,90]']">
        </div>
        
        <div class="div_fitem_1">
            <label class="lable_add">名称:</label>
            <input name="lable" class="easyui-textbox"  data-options="required:true,validType:['isBlank','length[1,90]']">
        </div>
      
        <div class="div_fitem_1">
            <label class="lable_add">字典值:</label>
            <input  name="value" class="easyui-textbox" style="width: 150px;" data-options="required:true,validType:['isBlank','cnennum','length[1,90]']">
        </div>
         <div style="clear: both; padding-top: 8px;">
            <label style="width: 68px; text-align: left; display: inline-block;">描述:</label>
            <textarea name="description" class="easyui-validatebox" style="height: 100px; width: 416px;vertical-align: top;" data-options="validType:['isBlank','cnennum','length[1,90]']"></textarea>
        </div>
    </form>
</body>
<script type="text/javascript">
var url= "sysdata/saveSysdata";

function optSubmit(){
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
                setTimeout('window.parent.close()',2000);
            } else {
            	$.messager.show({
                    title: 'Success',
                    msg: result.msg,
                    timeout:1000,
                	showType:'slide'
                });
            	setTimeout('window.parent.close()',2000);
            	// TODO:加载数据
                /* $('#dlg').dialog('close');        
                $('#dg').datagrid('reload');   */  
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
        onHidePanel: function(){
            $("#county").combobox("setValue",'');
            $("#community").combobox("setValue",'');
            $("#community").combobox('loadData', {});
            var city = $('#city').combobox('getValue');     
            if(city!=''){
            $.ajax({
                type: "POST",
                url: "common/getCountryByCity/"+city,
                cache: false,
                dataType : "json",
                success: function(data){
                $("#county").combobox("loadData",data);
                                       }
                                   });  
                           }
                     }
       }); 
    //区下拉菜单
    $('#county').combobox({ 
        editable:false, //不可编辑状态
        cache: false,
        panelHeight: 'auto',//自动高度适合
        valueField:'countyId',   
        textField:'countyName',
        onHidePanel: function(){
            $("#community").combobox("setValue",'');
            var county = $('#county').combobox('getValue');     
            if(county!=''){
            $.ajax({
                type: "POST",
                url: "common/getCommunityByCounty/"+county,
                cache: false,
                dataType : "json",
                success: function(data){
                $("#community").combobox("loadData",data);
                                       }
                                   });  
                           }
                     }
     });  
  //社区下拉菜单
    $('#community').combobox({ 

        editable:false, //不可编辑状态
        cache: false,
        panelHeight: 'auto',//自动高度适合
        valueField:'communityId',   
        textField:'communityName',
       });  
  
});



</script>
</html>