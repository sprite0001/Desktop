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
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/hbridge_ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/lang/zh-cn/zh-cn.js"></script>
    <!-- ueditor end -->
</head>
<body>
    <div class="ftitle"></div>
        <form id="fm" method="post" novalidate style="height: 100px; width: 100%;">
            <div id="cityDiv" class="div_fitem_1" style="width: 100%;display:none;">
                  <label class="lable_add" style="width: 13%" for="status">市:</label> 
                  ${cityName}
            </div>
            <div id="countyDiv" class="div_fitem_1" style="width: 100%;display:none;">
                  <label class="lable_add" style="width: 13%" for="status">区县:</label> 
                  ${countyName}
            </div>
            <div id="communityDiv" class="div_fitem_1" style="width: 100%;display:none;">
                  <label class="lable_add" style="width: 13%" for="status">社区:</label>
                  ${communityName}
            </div>
            <div class="div_fitem_1" style="width: 100%;">
              <label class="lable_add" style="width: 13%" for="areaLevelLabel">地区类别:</label> 
              ${areaName}
            </div>
            <input type="hidden" name="id" id="id" value="" />
            <input type="hidden" name="publishStatus" id="publishStatus" value="" />
            <div class="div_fitem_1" style="width: 100%;">
              <label class="lable_add" style="width: 13%">标题:</label>
              ${title}
            </div>
            <div class="div_fitem_1" style="width: 100%;">
              <label class="lable_add" style="width: 13%">来源:</label>
              ${source}
            </div>
            <div class="div_fitem_1">
              <label class="lable_add" for="noticeTimeStr">公告落款时间:</label> 
              <input class="easyui-textbox" type="text" name="noticeTimeStr" id="noticeTimeStr" data-options="disabled:true"/>
            </div>
            <div class="div_fitem_1" style="width:100%;display:inline-block;">
              <label class="lable_add" style="width: 97px;">置顶:</label>
              <input id="topFlag" name="topFlag" value="0" type="hidden" />
              <input id="topFlagRadio" type="checkbox" value="" disabled="disabled"/>
              <div id="topFlagDiv" style="display:none;width:100%;padding: 12px 0px 7px 0px;">
                  <label for="topStartStr" style="width: 104px; display: block; float: left;">置顶开始时间:</label> 
                  <input class="easyui-textbox" type="text" name="topStartStr" id="topStartStr" style="width: 150px;" data-options="disabled:true"/>
                  <label for="topEndStr" style="margin-left: 141px; margin-right: 23px;">置顶结束时间:</label> 
                  <input class="easyui-textbox" type="text" name="topEndStr" id="topEndStr" style="width: 150px;" data-options="disabled:true"/>
              </div>
            </div>
            <div class="div_fitem_1" style="width:100%;position:relative;">
		        <div style="width:25%;position:absolute;">
		          <label class="lable_add" style="display: initial" >内容:</label>
		        </div>
		        <div id="content" style="margin-left:13%;width:78%;height:200px;position:absolute;">
		        	${content}
		        </div>
		    </div>
        </form>
</body>
<script type="text/javascript">
var areaLevelT = "${areaLevel}";
var areaIdT = "${areaId}";
if(areaLevelT == "04"){
    $("#communityDiv").show();
}
if(areaLevelT == "03"){
    $("#countyDiv").show();
}
if(areaLevelT == "02"){
    $("#cityDiv").show();
}

$(function(){
    $("#noticeTimeStr").textbox('setValue',"${noticeTime}");
    var topFlag = "${topFlag}";
    if(topFlag == "1"){
        $("#topFlagRadio").attr("checked",true);
        $("#topFlagDiv").show();
        $("#topStartStr").textbox('setValue',"${topStartStr}");
        $("#topEndStr").textbox('setValue',"${topEndStr}");
    }
});
$(function(){
    $("#topFlagRadio").change(function(){
        if($("#topFlagRadio").is(":checked")){
            $("#topFlag").val("1");
            $("#topFlagDiv").show();
        }else{
            $("#topFlag").val("0");
            $("#topFlagDiv").hide();
        }
    });
});
</script>
</html>