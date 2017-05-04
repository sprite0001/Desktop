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
        
            <div class="fitem">
              <label>广告位置:</label>
              
              <input id="positionName" name="positionName" class="easyui-combobox"  
                    data-options="valueField:'positionId',textField:'positionName',url:'advert/initPositionName'"   editable="false">
                    
            </div>
 
    
</body>
<script type="text/javascript">

/*     $('#positionName').combobox({ 
        url:'advert/initPositionName',
        editable:false, //不可编辑状态
        cache: false,
        panelHeight: 'auto',//自动高度适合
        valueField:'positionId',   
        textField:'positionName',
    });
  }) */
  
  function savePositionName(){
     var positionname= $('#positionName').combobox('getText');
     var positionId= $('#positionName').combobox('getValue');
     parent.$("#positionName").textbox("setValue",positionname);
     parent.$("#positionId").val(positionId);
     parent.$('#dlg').dialog('close');
  }
</script>
</html>