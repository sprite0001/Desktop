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
                <label class="lable_add">编号:</label>
                <input name="code"   class="easyui-textbox"  required="true">
            </div>
            
            <div class="div_fitem_1">
                <label class="lable_add">名称:</label>
                <input name="name"  class="easyui-textbox" required="true">
            </div>
            
             <div class="div_fitem_1">
                <label class="lable_add">备注:</label>
                <input name="remarks"  class="easyui-textbox"  >
            </div>
            
            <!-- <div class="fitem">
                <label>操做人:</label>
            </div> -->
            <input value="${user.userName}" type="hidden">
             
            <input name="id" id="currenId" type="hidden" value="${id}">
        </form>
</body>
<script type="text/javascript">
var url= "goodsclassific/update";
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
	var id = $('#currenId').val();
	$('#fm').form('load','goodsclassific/findById/'+id);
	
	// 省份 下拉框选择控件，下拉框的内容是动态查询数据库信息 
 	$('#provinceId').combobox({ 
	    editable:false, //不可编辑状态
	    cache: false,
	    panelHeight: 'auto',//自动高度适合
	    valueField:'provinceId',   
	    textField:'provinceName',
			onHidePanel: function(){
		    $("#cityId").combobox("setValue",'');
		    $("#countyId").combobox("setValue",'');
		    //$("#cregicounty").val('');
			var province = $('#provinceId').combobox('getValue');		
			if(province!=''){
			$.ajax({
				type: "POST",
				url: "common/getCityByProvice/"+province,
				cache: false,
				dataType : "json",
				success: function(data){
				$("#cityId").combobox("loadData",data);
		                               }
	                               }); 	
                           }
                     } 
    }); 
	//市下拉菜单
	$('#cityId').combobox({ 
	    editable:false, //不可编辑状态
	    cache: false,
	    panelHeight: 'auto',//自动高度适合
	    valueField:'cityId',   
	    textField:'cityName',
	    onHidePanel: function(){
		    $("#countyId").combobox("setValue",'');
			var city = $('#cityId').combobox('getValue');		
			if(city!=''){
			$.ajax({
				type: "POST",
				url: "common/getCountryByCity/"+city,
				cache: false,
				dataType : "json",
				success: function(data){
				$("#countyId").combobox("loadData",data);
		                               }
	                               }); 	
                           }
                     }
	});
	   //区下拉菜单
    $('#countyId').combobox({ 
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
    $('#communityId').combobox({ 

        editable:false, //不可编辑状态
        cache: false,
        panelHeight: 'auto',//自动高度适合
        valueField:'communityId',   
        textField:'communityName',
       }); 
})
</script>
</html>