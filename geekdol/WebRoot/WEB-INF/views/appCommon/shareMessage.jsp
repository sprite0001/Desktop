<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
 	<base href="<%=basePath%>">
 	<meta charset="UTF-8">
 	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta content="yes" name="apple-touch-fullscreen">
    <title>${pageTitle}</title>
    <link rel="stylesheet" type="text/css" href="${basePath}static/css/weui.min.css">
    <link rel="stylesheet" type="text/css" href="${basePath}static/css/share.css">
</head>
<body>
  <div class="bd">
      <article class="weui_article">
          <h1>${title}</h1>
          <section>
              <div class="weui_media_box weui_media_text" style="width:100%;padding: 0;position: initial;border-bottom: 1px solid #e5e5e5;">
	              <ul class="weui_media_info">
	                  <li class="weui_media_info_meta">来源: ${source}</li>
	                  <li class="weui_media_info_meta">时间: ${publishTime}</li>
	              </ul>
	          </div>
              <section>
                  <p>${content}</p>
              </section>
          </section>
      </article>
  </div>
</body>
</html>