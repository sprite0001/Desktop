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
    <!-- ueditor begin -->
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/hbridge_ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/lang/zh-cn/zh-cn.js"></script>
    <!-- ueditor end -->
</head>
<body>
    <div class="ftitle"></div>
        <form id="fm" method="post" novalidate style="height: 100px; width: 100%;">
            <div class="div_fitem_1">
              <label class="div_lable_1">内参标题:</label>
              <input id="title" name="title" value="" class="easyui-textbox" data-options="required:true,validType:['isBlank','length[1,100]']">
            </div>
            <input type="hidden" name="token" id="token" value="${token }" />
            <input type="hidden" name="publishStatus" id="publishStatus" value="" />
            <input type="hidden" name="id" id="id" value="" />
            <div class="div_fitem_1">
              <label class="div_lable_1">摘要:</label>
              <input id="summary" name="summary" value="" class="easyui-textbox" data-options="required:true,validType:['isBlank','length[1,200]']">
            </div>
            <div class="div_fitem_1">
              <label class="div_lable_1">查看人员最低级别:</label>
              <input id="staffLevel" name="staffLevel" value="" class="easyui-combobox" 
              data-options="editable:false,valueField:'staffCode',textField:'staffName',url:'staffLevel/selectAll'" required="true">
            </div>
            <div class="div_fitem_1">
              <label class="div_lable_1">来源:</label>
              <input id="source" name="source" value="" class="easyui-textbox" data-options="required:true,validType:['isBlank','length[1,200]']">
            </div>
            <div class="div_fitem_1">
              <label class="div_lable_1">分类 :</label>
              <input id="type" name="type" value="" class="easyui-combobox" 
              data-options="editable:false,valueField:'value',textField:'lable',url:'sysdata/selectByType/STAFFTYPE'" required="true">
            </div>
            <div class="div_fitem_1">
              <label class="div_lable_1">内容:</label>
              <script id="content" name="content" type="text/plain"></script>
            </div>
        </form>
</body>
<script type="text/javascript">
var url= "staff/saveUpdate";
var id = '${id}';
$('#fm').form('load','staff/findById/'+id+"?t="+new Date().getTime());

$(function(){
    var ue = UE.getEditor('content',{
        autoHeightEnabled: false,
        initialFrameWidth : 700,
        initialFrameHeight: 330
    });
    var ueditorBody ='${content}';
    //判断ueditor 编辑器是否创建成功
    if(ueditorBody != null && ueditorBody != undefined && ueditorBody != ""){
        ue.addListener("ready", function () {
            // editor准备好之后才可以使用
            ue.setContent(ueditorBody);
        });
    }
});

function optSubmit(status){
    $("#publishStatus").val(status);
    var content = UE.getEditor('content').getContent();
    if((content.length - 7) > 10000){
        $.messager.show({
            title: 'Info',
            msg: "内容字数大于10000，无法保存！",
            timeout:1000,
            showType:'slide'
        });
        return;
    }
    $.trim(content);
    if(content.length <= 0){
        $.messager.show({
            title: 'Info',
            msg: "内容不能为空或全为空格！",
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
 
</script>
</html>