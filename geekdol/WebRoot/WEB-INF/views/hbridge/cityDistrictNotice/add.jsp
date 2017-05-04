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
</head>
<body>
        <form id="fm" method="post" novalidate>
            <div id="cityDiv" class="div_fitem_1" style="display:none;">
                  <label class="lable_add">市:</label> 
                  <input id="cityId" name="cityId" class="easyui-combobox" style="width: 150px;">
            </div>
            <div id="countyDiv" class="div_fitem_1" style="display:none;">
                  <label class="lable_add">区县:</label> 
                  <input id="countyId" name="countyId" class="easyui-combobox" style="width: 150px;">
            </div>
            <div id="communityDiv" class="div_fitem_1" style="display:none;">
                  <label class="lable_add">社区:</label>
                  <input id="communityId" name="communityId" class="easyui-combobox" style="width: 150px;">
            </div>
            <input type="hidden" name="token" id="token" value="${token }" />
            <div class="div_fitem_1">
              <label class="lable_add" for="areaLevelLabel">地区类别:</label> 
              <input type="hidden" name="areaLevel" id="areaLevel" value="${areaLevel}" />
              <input type="text" id="areaName" name="areaName" value="${areaName}" class="easyui-textbox" disabled = "true" />
            </div>
            <input type="hidden" name="publishStatus" id="publishStatus" value="" />
            <div class="div_fitem_1">
              <label class="lable_add">标题:</label>
              <input id="title" name="title" value="" class="easyui-textbox" data-options="required:true,validType:['isBlank','length[1,100]']">
            </div>
            <div class="div_fitem_1" style="display:none;">
              <label class="lable_add">摘要:</label>
              <input id="summary" name="summary" value="" class="easyui-textbox">
            </div>
            <div class="div_fitem_1">
              <label class="div_lable_1">来源:</label>
              <input id="source" name="source" value="" class="easyui-textbox" data-options="required:true,validType:['isBlank','length[1,200]']">
            </div>
            <div class="div_fitem_1">
              <label class="lable_add">公告落款时间:</label> 
              <input class="easyui-datetimebox" type="text" name="noticeTimeStr" id="noticeTimeStr" required="true" editable="false"/>
            </div>
            <div class="div_fitem_1" style="width:100%;display:inline-block;">
              <label class="lable_add" style="width: 97px;">置顶:</label>
              <input id="topFlag" name="topFlag" value="0" type="hidden" />
              <input id="topFlagRadio" type="checkbox" value="" />
              <div id="topFlagDiv" style="display:none;width:100%;padding: 12px 0px 7px 0px;">
                  <label for="topStartStr" style="width: 104px; display: block; float: left;">置顶开始时间:</label> 
                  <input class="easyui-datetimebox" type="text" name="topStartStr" id="topStartStr" style="width: 150px;" editable="false"/>
                  <label for="topEndStr" style="margin-left: 141px; margin-right: 23px;">置顶结束时间:</label> 
                  <input class="easyui-datetimebox" type="text" name="topEndStr" id="topEndStr" style="width: 150px;" editable="false"/>
              </div>
            </div>
		    <!-- <div class="resource_item" style="width:100%;margin-top:10px;"> -->
		    <div class="div_fitem_1" style="width:100%;position:relative;">
               <!-- <div class="column" style="width:8.7%;margin-left:8.7%;">内容:</div> -->
               <div style="width:25%;position:absolute">
	              <label class="lable_add" style="display: initial" >内容:</label>
	            </div>
	            <script id="editor" name="content" type="text/plain" style="margin-left:10%;width:83%;height:230px;"></script>
               <!-- <div class="content" style="width:auto;">
                    <script id="editor" name="content" type="text/plain" style="width:1024px;height:480px;"></script>    
               </div> -->
            </div>
        </form>
</body>
<script type="text/javascript">
var areaLevelT = "${areaLevel}";
if(areaLevelT == "04"){
    $("#communityDiv").show();
    $('#communityId').combobox({ 
        url:'cityDistrictNotice/initArea',
        editable:false, //不可编辑状态
        cache: false,
        panelHeight: '200px',//自动高度适合
        valueField:'areaId',   
        textField:'areaName',
    }); 
}
if(areaLevelT == "03"){
    $("#countyDiv").show();
    $('#countyId').combobox({ 
        url:'cityDistrictNotice/initArea',
        editable:false, //不可编辑状态
        cache: false,
        panelHeight: '200px',//自动高度适合
        valueField:'areaId',   
        textField:'areaName',
    }); 
}
if(areaLevelT == "02"){
    $("#cityDiv").show();
    $('#cityId').combobox({ 
        url:'cityDistrictNotice/initArea',
        editable:false, //不可编辑状态
        cache: false,
        panelHeight: '200px',//自动高度适合
        valueField:'areaId',   
        textField:'areaName',
    }); 
}
var url= "cityDistrictNotice/saveAdd";

function optSubmit(status){
	var selectArea = "";
	if(areaLevelT == "04"){
		selectArea = $('#communityId').combobox('getValue'); 
	}
	if(areaLevelT == "03"){
        selectArea = $('#countyId').combobox('getValue'); 
	}
	if(areaLevelT == "02"){
        selectArea = $('#cityId').combobox('getValue'); 
	}
	if(selectArea == "" || selectArea == "0"){
        $.messager.show({
            title: 'Info',
            msg: "请选择区域！",
            timeout:1000,
            showType:'slide'
        });
        return;
	}
	$("#publishStatus").val(status);
    var content = UE.getEditor('editor').getContent();
    var length = UE.getEditor('editor').getContentLength(true);
    if(length > 10000){
        $.messager.show({
            title: 'Info',
            msg: "内容字数大于10000，无法保存！",
            timeout:1000,
            showType:'slide'
        });
        return;
    }
    if(length <= 0){
        $.messager.show({
            title: 'Info',
            msg: "内容不能为空或全为空格！",
            timeout:1000,
            showType:'slide'
        });
        return;
    }
    if($("#topFlagRadio").is(":checked")){
        var topStartStr = $('#topStartStr').datetimebox('getValue');
        var topEndStr = $('#topEndStr').datetimebox('getValue');
        if(topStartStr == null || topStartStr == "" || topEndStr == null || topEndStr == ""){
            $.messager.show({
                title: 'Info',
                msg: "置顶开始时间或结束时间不能为空！",
                timeout:1000,
                showType:'slide'
            });
            return;
        }
        var topStartDate = new Date(Date.parse(topStartStr.replace(/-/g, "/")));  
        var topEndDate = new Date(Date.parse(topEndStr.replace(/-/g, "/"))); 
        if(topEndDate <= topStartDate){
            $.messager.show({
                title: 'Info',
                msg: "置顶结束时间必须大于开始时间！",
                timeout:1000,
                showType:'slide'
            });
            return;
        }
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

$(function(){
    var ue = UE.getEditor('editor',{
        autoHeightEnabled: false,
        initialFrameWidth : 700,
        initialFrameHeight: 250
    });
    var ueditorBody ='';
    //判断ueditor 编辑器是否创建成功
    if(ueditorBody != null && ueditorBody != undefined && ueditorBody != ""){
        ue.addListener("ready", function () {
            // editor准备好之后才可以使用
            ue.setContent(ueditorBody);
        });
    }
});
$(function(){
	$("#topFlagRadio").change(function(){
		if($("#topFlagRadio").is(":checked")){
			$("#topFlag").val("1");
			$("#topFlagDiv").css("display", "inline-block");
		}else{
            $("#topFlag").val("0");
            $("#topFlagDiv").hide();
		}
	});
});
function getContent() {
    var arr = [];
    arr.push(UE.getEditor('editor').getContent());
    $("#content").val(arr.join("\n"));
    $("#ueditorForm").attr("action","${contextPath}/toueditor");
    $("#ueditorForm").submit();
}
</script>
</html>