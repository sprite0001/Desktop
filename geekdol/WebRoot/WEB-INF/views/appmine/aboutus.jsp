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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="content-language" content="zh">
	<meta name="keywords" content="吉客多">
	<meta content="吉客多关于我们" name="description">
	<meta name="format-detection" content="telephone=no">
	<meta name="viewport"
		content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta content="yes" name="apple-touch-fullscreen">
	<meta http-equiv="cleartype" content="on">
	<title>关于我们</title>
    <link rel="stylesheet" type="text/css" href="${basePath}static/css/aboutus.css">
	<style type="text/css">
	body,td,th {
	  font-family: "Open Sans";
	}
	</style>
</head>
<body>
    <div class="aboutus">
    <article style="margin-top: 60px;line-height: 30px;">
      <table width="80%" align="center">
        <tbody>
          <tr>
            <td>
              <p><span style="font-size:18px"><strong>——心通桥是郑州市深化网络行政和媒体助政工作，结合在 “走基层、转作风、改文风”活动，为基层群众提供便利，帮助群众发现问题、解决问题，切实将营造氛围与社会管理结合起来，推进“走、转、改”活动深入开展.</strong></span></p>
            </td>
          </tr>
        </tbody>
      </table>
    </article>
  </div>
</body>
</html>