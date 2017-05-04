<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<title>${TITLE}</title>
<link rel="stylesheet" href="${basePath}static/css/index.css">
<link rel="stylesheet" type="text/css" href="${basePath}static/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${basePath}static/easyui/themes/icon.css">
<script type="text/javascript" src="${basePath}static/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basePath}static/easyui/locale/easyui-lang-zh_CN.js"></script>
<script>
	if(window.addEventListener){
		window.addEventListener('beforeunload', function(){}, false);
	}
	window.history.forward();

	document.onkeydown=function() {
		if(event.keyCode==13) {
			$('#ff').submit();
			return false;                               
		}
	};
	
		$(function(){
			$('#username').focus();
			ShowMessage();
		});
		
		function ShowMessage(){
			var message;
			message="${msg}";
			if(message.length >0){
				$.messager.alert("后台管理系统",message);
			}
		}
		
		function sub(){
			var username = $("#username").val();
			var password = $("#password").val();
			var captcha_key = $("#captcha_key").val();
			
			if(username == "") {
				alert("请输入用户名！");
				$("#username").focus();
				return false;
			}
			if(password == "") {
				alert("请输入密码！");
				$("#password").focus();
				return false;
			}
			/* if(captcha_key == "" || captcha_key == '验证码') {
				alert("请输入验证码！");
				$("#captcha_key").focus();
				return false;
			} */
			$('#ff').submit();
		}
		
		jQuery(document).ready(function(){
			$("#captcha").click(function(){
				$(this).attr("src", "${basePath}Captcha.jpg?time=" + new Date());
				return false;
			});
			
			$("#captcha").click();
		});
	</script>
	<script type="text/javascript">
	 if(window != top){
			top.location.href=location.href;
		}
	</script>
</head>

<body class="b_bg">

<div class="main">
<div class="main_top">
<div class="main_top_left">
<p>LOGO</p>
</div>
<div class="main_top_right">
<h1>吉客多后台管理系统</h1>
<p>Background management system</p>

</div>

</div>

<div class="center">
	<form id="ff" action="${basePath}login" method="post">
		<input type="text" id="username" name="username" value="" class="in_1" placeholder="请输入用户名" required="true"><br/>
		<input type="password" id="password" name="password" value="" class="in_2" placeholder="请输入密码" required="true"><br/>
		<%-- <input type="text" id="captcha_key" name="captcha_key" class="in_3" placeholder="请输入验证码">
		<img src="${basePath}static/images/icon_yz.png"> --%>
		<input type="button" style="cursor:pointer;" value="登陆" class="submit" onClick="sub();">
	</form>

	<!-- <div class="wangji">
    	<a href="">忘记密码？</a>
    </div> -->
</div>

</div>
<div class="footer">
        
</div>
</body>
</html>
