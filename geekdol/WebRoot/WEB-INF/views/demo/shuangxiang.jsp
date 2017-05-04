<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>jquery双向选择器代码 - 站长素材</title>

<style type="text/css">
.s1{width:120px;}
#d1{width:510px;height:320px;background-color:#F5DEB3;margin:0 auto;}
#d2{height:30px;font-size:24px;background-color:blue;color:white;}
#d3{padding-left:30px;}
</style>

<script type="text/javascript" src="${contextPath}/static/js/jquery-1.4.3.js"></script>
<script type="text/javascript" src="${contextPath}/static/js/lab2.js"></script>

<script type="text/javascript">
// 添加  
function col_add() {  
    var selObj = $("#mySelect");  
    var value="value";  
    var text="text";  
    selObj.append("<option value='"+value+"'>"+text+"</option>");  
}  
// 删除  
function col_delete() {  
    var selOpt = $("#mySelect option:selected");  
    selOpt.remove();  
}  
// 清空  
function col_clear() {  
    var selOpt = $("#mySelect option");  
    selOpt.remove();  
}  


//动态改变s1的值
function gaibianS1() { 
	 var dangqianValue = $("#s01 option:selected").val();
	 var selObj = $("#s1"); 
	 var selOpt = $("#s1 option");
	 selOpt.remove();  
	 if(dangqianValue=="zhengzhou"){
		 selObj.append("<option value='xihuxincheng'>西湖新城</option>");  
		 selObj.append("<option value='wnaghuhuayuan'>望湖花园</option>");  
	 }else{
		 selObj.append("<option value='shouchengguoji'>首城国际</option>");  
		 selObj.append("<option value='tiananmen'>天安门</option>");  
	 }
}  

$(document).ready(function(){  
	 gaibianS1();
}); 

</script>

</head>
<body>

<div id="d1">
	<div id="d2">选课</div>
	<div id="d3">
		<table cellpadding="0" cellspacing="8">
			<tr>
                <td>待选小区</td>
                <td>&nbsp;</td>
                <td>已选小区</td>
            </tr>
            
            <tr>
            
                <td>
                <select id="s01" name="s01" style="width:150px; height:25px;"  onchange="gaibianS1()">
                        <option value="zhengzhou">郑州</option>
                        <option value="beijing">北京</option>
                </select>
                </td>
                
                <td>&nbsp;</td>
                
                <td>
                
                </td>
               
             </tr>
            
			<tr>
				<td>
					<select id="s1" name="s1" style="width:150px; height:220px;" multiple="multiple">  </select>
				</td>
				<td>
					<p><input id="b1" type="button" class="s1" value="--&gt;" /></p>
					<p><input type="button" id="b2" class="s1" value="--&gt;&gt;" /></p>
					<p><input type="button" id="b3" class="s1" value="&lt;--" /></p>
					<p><input type="button" id="b4" class="s1" value="&lt;&lt;--" /></p>
				</td>
				<td><select id="s2" name="s2" style="width:150px;height:220px;" multiple="multiple"></select></td>
			</tr>
		</table>
	</div>
</div>
<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
<p>适用浏览器：IE8、360、FireFox、Chrome、Safari、Opera、傲游、搜狗、世界之窗.</p>
<p>来源：<a href="http://sc.chinaz.com/" target="_blank">站长素材</a></p>
</div>
</body>
</html>