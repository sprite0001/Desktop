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
            <label class="lable_add">区编码:</label>
            <input name="countyCode" class="easyui-textbox" data-options="required:true,validType:'isBlank',validType:'engAndNum'">
        </div>
         <div class="div_fitem_1">
            <label class="lable_add">区名称:</label>
            <input name="countyName" class="easyui-textbox" data-options="required:true,validType:'isBlank',validType:'hanzi'">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add">所属省:</label>
            <input id="provinceId" name="provinceId" class="easyui-combobox" 
                data-options="valueField:'provinceId',textField:'provinceName',url:'common/initProvinceQY',required:true,validType:'isBlank'">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add">所属市:</label>
            <input id="cityId" name="cityId" class="easyui-combobox" 
            data-options="valueField:'cityId',textField:'cityName',url:'common/getCityByProviceQY/${provinceId}',required:true,validType:'isBlank'">
        </div>
        
        <div class="div_fitem_1"> 
            <label class="lable_add">排序:</label>
            <input id="ordering" name="ordering" value="" class="easyui-numberbox" data-options="validType:'integer'" >
        </div>
        <input name="countyId" id="countyId" type="hidden" value="${countyId}">
    </form>
</body>
<script type="text/javascript">
var url= "county/update";
function optSubmit(){
	   var provinceId = $('#provinceId').combobox('getValue');
	    if(provinceId=='0'||provinceId==""){
	        var res = "请选择所属省";
	        $.messager.show({
	         title: 'Error',
	         msg: res
	     });
	        return false;
	    }
	    var cityId = $('#cityId').combobox('getValue');
	    if(cityId=='0'||cityId==""){
	        var res = "请选择所属市";
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
var id = $('#countyId').val();
$('#fm').form('load','county/findById/'+id+"?t="+new Date().getTime());

$(function(){
    // 省份 下拉框选择控件，下拉框的内容是动态查询数据库信息 
    $('#provinceId').combobox({ 
        url:'common/initProvinceQY',
        editable:false, //不可编辑状态
        cache: false,
        panelHeight: '180',//自动高度适合
        valueField:'provinceId',   
        textField:'provinceName',
        onHidePanel: function(){
            $("#cityId").combobox("setValue",'');
           // $("#countyId").combobox("setValue",'');
            var province = $('#provinceId').combobox('getValue');       
            if(province!=''){
                $.ajax({
                    type: "POST",
                    url: "common/getCityByProviceQY/"+province,
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
        panelHeight: '180',//自动高度适合
        valueField:'cityId',   
        textField:'cityName',
    });
}) 
</script>
</html>