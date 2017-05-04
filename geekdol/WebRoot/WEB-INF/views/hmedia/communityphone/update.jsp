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
                <label class="lable_add">小区名称:</label> 
                <input id="villageId" name="villageId" value="" class="easyui-combobox" data-options="required:true,validType:'isBlank'">
            </div>
            
            
             <div class="div_fitem_1">
              <label class="lable_add">电话名称:</label>
              <input id="name" name="name" value="" class="easyui-textbox" data-options="required:true,validType:'isBlank'">
            </div>
            
            <div class="div_fitem_1">
              <label class="lable_add">电话号码:</label>
              <input id="phoneNumber" name="phoneNumber"  class="easyui-textbox" data-options="required:true,validType:'isBlank',validType:'phoneRex'">
            </div>
            
             <div class="div_fitem_1">
              <label class="lable_add">类别:</label>
              <select id="phoneType" name="phoneType" value="0" class="easyui-combobox" required="true" editable="false" style="width: 150px;">
                    <option value="0">${COMMUNTIYPHONE}</option>
                    <option value="1">${FUWUPHONE}</option>
              </select>
            </div>
             
            <div class="div_fitem_1">
            <label class="lable_add">排序:</label>
            <input id="ordering" name="ordering" value="" class="easyui-numberbox" data-options="validType:'integer'">
            </div>
        <input name="id" id="id" type="hidden" value="${id}">
    </form>
</body>
<script type="text/javascript">
 
var url= "communityphone/update";

$.extend($.fn.validatebox.defaults.rules, {
    phoneRex: {
      validator: function(value){
          var rex=/^1[3-8]{1}\d{9}$/;
          var rex2=/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
          var rex3=/^[0-9]{8}$/;
          if(rex.test(value)||rex2.test(value)||rex3.test(value)){
             return true;
          }else{
             return false;
          }
      },
      message: '请输入正确电话或手机格式'
    }
});

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
            }
        }
    });
}

$(function(){
    // 小区 下拉框选择控件，下拉框的内容是动态查询数据库信息 
    $('#villageId').combobox({ 
        url:'communityphone/initCommunity',
        editable:false, //不可编辑状态
        cache: false,
        panelHeight: 'auto',//自动高度适合
        valueField:'areaId',   
        textField:'villageName',
    });
  })

var id = $('#id').val();
$('#fm').form('load','communityphone/findById/'+id+"?t="+new Date().getTime());
</script>
</html>