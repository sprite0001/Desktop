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
    
    <!-- ueditor begin -->
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/hmedia_ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/lang/zh-cn/zh-cn.js"></script>
    <!-- ueditor end -->
    
</head>
<body>
    <div class="ftitle"></div>
        <form id="fm" method="post" novalidate >
             
            <div class="div_fitem_1">
                <label class="lable_add">标题:</label>
                <label>${title}</label>
            </div>
          
            <div class="div_fitem_1">
                <label class="lable_add">所属区域:</label>
                <label>${areaName}</label>
            </div>
            
            <div class="div_fitem_1">
                  <label class="lable_add">置顶开始时间:</label> 
                  <input class="easyui-datetimebox" type="text" id="topStartStr" name="topStartStr" style="width: 150px;" editable="false" required="true"/>
            </div>
            
            <div class="div_fitem_1">
                <label class="lable_add">置顶结束时间:</label>
                <input class="easyui-datetimebox" type="text" id="topEndStr" name="topEndStr" style="width: 150px;" editable="false" required="true"/>
            </div>
            
             <input  name="id" value="${id}" type="hidden" />
             <input  name="token" value="${token}" type="hidden" />
              <!-- 置顶状态为1 -->
             <input name="topFlag" value="1" type="hidden" />
             
        </form>
</body>

<script type="text/javascript">

$(function(){
    $('#topStartStr').datetimebox('setValue','${topStart}');
    $('#topEndStr').datetimebox('setValue','${topEnd}')
})

function optSubmit(){

    var topStartStr = $('#topStartStr').datetimebox('getValue');
    var topEndStr = $('#topEndStr').datetimebox('getValue');
    var topStart = new Date(topStartStr);
    var topEnd = new Date(topEndStr);
    if(topStart >= topEnd){
        $.messager.show({
            title: 'Info',
            msg: "置顶结束时间应大于置顶开始时间！",
            timeout:1000,
            showType:'slide'
        });
        return;
    }   
    
$('#fm').form('submit',{
    url: "cityDistrictNotice/saveZhiDing",
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
</script>
</html>