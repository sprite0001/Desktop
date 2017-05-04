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
        <form id="fm" method="post" novalidate style="height: 100px; width: 100%;">
        	<div class="fitem">
        		<table width="700" height="300" border="0">
					  <tr>
					    <td height="30">团购编码</td>
					    <td width="100"><input name="serialCode" class="easyui-textbox" required="true"></td>
					    <td width="100"></td>
					  </tr>
					  <tr>
					    <td width="50" height="30">团购码</td>
					    <td width="100"><input name="codeList" class="easyui-textbox"></td>
					    <td><div id="res0"></div></td>
					  </tr>	
					  <tr>
					    <td width="50" height="30">团购码</td>
					    <td width="100"><input name="codeList" class="easyui-textbox" ></td>
					    <td><div id="res1"></div></td>
					  </tr>	
					  <tr>
					    <td width="50" height="30">团购码</td>
					    <td width="100"><input name="codeList" class="easyui-textbox"></td>
					    <td><div id="res2"></div></td>
					  </tr>	
					  <tr>
					    <td width="50" height="30" colspan="2" align="center"><input  type="button" onclick="searchApp()" value="验证" /></td>
					    <td></td>
					  </tr>					  
					</table>               
            </div>       
        </form>
</body>
<script type="text/javascript">
var url = "groupPurchaseCode/validateOrder";
function searchApp(){
    $('#fm').form('submit',{
        url: url,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            	$('#res0').html(result.res0);
            	$('#res1').html(result.res1);
            	$('#res2').html(result.res2);
        }
    });
}


</script>
</html>