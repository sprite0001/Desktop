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
              <label class="lable_add">电话名称:</label>
              <input name="name"  class="easyui-textbox" data-options="required:true,validType:'isBlank'">
         </div>
         
         <div class="div_fitem_1">
              <label class="lable_add">电话:</label>
              <input name="phoneNumber" class="easyui-textbox" data-options="validType:'phoneRex',required:'true',validType:'isBlank'">
         </div>
         
         <div class="div_fitem_1">
              <label class="lable_add">类别:</label>
              <select id="phoneType" name="phoneType"  class="easyui-combobox" required="true" editable="false" style="width: 150px;">
                    <option value="0">${FW}</option>
                    <option value="1">${CG}</option>
              </select>
         </div>
         
         <div class="div_fitem_1">
              <label class="lable_add">排序:</label>
              <input name="ordering"  class="easyui-numberbox" data-options="validType:'integer'">
         </div>
            
        <input name="id" id="id" type="hidden" value="${id}">
    </form>
</body>
<script type="text/javascript">
 
var url= "commonphone/update";
$.extend($.fn.validatebox.defaults.rules, {
    phoneRex: {
      validator: function(value){
          var rex=/^1[3-8]{1}\d{9}$/;
          var rex2=/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
          var rex3=/^[0-9]{3}$/;
          var rex4=/^[0-9]{8}$/;
          if(rex.test(value)||rex2.test(value)||rex3.test(value)||rex4.test(value)){
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
var id = $('#id').val();
$('#fm').form('load','commonphone/findById/'+id+"?t="+new Date().getTime());
</script>
</html>