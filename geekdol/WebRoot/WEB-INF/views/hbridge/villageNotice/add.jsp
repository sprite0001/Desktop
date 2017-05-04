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
    <script type="text/javascript" src="${contextPath}/static/js/lab2.js"></script>
    <script type="text/javascript" src="${basePath}static/js/validator.js"></script>
    <!-- ueditor begin -->
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/hbridge_ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/lang/zh-cn/zh-cn.js"></script>
    <!-- ueditor end -->
<style type="text/css">
.s1{width:120px;}
#d1{width:510px;height:320px;background-color:#F5DEB3;margin:0 auto;}
#d2{height:30px;font-size:24px;background-color:blue;color:white;}
#d3{padding-left:30px;}
</style>
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
    var options = $("#s2").find("option");
    selOpt.remove();  
	 $.ajax( {    
	    url:'cityDistrictNotice/initArea',// 跳转到 action    
	    data:'',    
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	for(var i = 0;i<data.length;i++){
	    		if(data.length > 1 && i == 0){
	    			continue;
	    		}
	    		var isHave = false;
	    		if(options.length > 0){
	                for(var j=0; j<options.length; j++) {
	                    if(data[i].areaId == options.eq(j).val()){
	                        isHave = true;
	                        break;
	                    }
	                }
	    		}
	    	    if(!isHave){
	                selObj.append("<option value='"+data[i].areaId+"'>"+data[i].areaName+"</option>"); 
	    	    }
	    	}
	     },    
	     error : function() {    
	          alert("异常！");    
	     }    
	 });
}  

</script>
</head>
<body>
	<div class="ftitle"></div>
        <form id="fm" method="post" novalidate>
            <div class="div_fitem_1">
              <label class="lable_add">标题:</label>
              <input id="title" name="title" value="" class="easyui-textbox" data-options="required:true,validType:['isBlank','length[1,100]']">
            </div>
            <div class="div_fitem_1">
              <label class="div_lable_1">来源:</label>
              <input id="source" name="source" value="" class="easyui-textbox" data-options="required:true,validType:['isBlank','length[1,200]']">
            </div>
            <div class="div_fitem_1">
              <label class="lable_add" for="areaLevelLabel">所属小区:</label> 
              <input type="hidden" name="villageIds" id="villageIds" value="" />
              <input type="text" id="villageNames" name="villageNames" value="" class="easyui-textbox" disabled = "true" required="true" />
              <input  type="button" onclick="selectVillages()" value="选择" />
            </div>
            <input type="hidden" name="publishStatus" id="publishStatus" value="" />
            <input type="hidden" name="token" id="token" value="${token }" />
            <div id="cityDiv" class="div_fitem_1" style="display:none;">
                  <label class="lable_add" for="status">市:</label> 
                  <input id="cityId" name="cityId" class="easyui-combobox" style="width: 150px;">
            </div>
            <div id="countyDiv" class="div_fitem_1" style="display:none;">
                  <label class="lable_add" for="status">区县:</label> 
                  <input id="countyId" name="countyId" class="easyui-combobox" style="width: 150px;">
            </div>
            <div id="communityDiv" class="div_fitem_1" style="display:none;">
                  <label class="lable_add" for="status">社区:</label>
                  <input id="communityId" name="communityId" class="easyui-combobox" style="width: 150px;">
            </div>
            <!-- <div class="div_fitem_1">
              <label class="div_lable_1">摘要:</label>
              <input id="summary" name="summary" value="" class="easyui-textbox" data-options="validType:['length[1,255]']">
            </div> -->
            <input type="hidden" id="openDialog" value="0" />
            <div class="div_fitem_1">
              <label class="lable_add">公告落款时间:</label> 
              <input class="easyui-datetimebox" type="text" name="noticeTimeStr" id="noticeTimeStr" required="true" editable="false"/>
            </div>
            <div class="div_fitem_1" style="width:100%;display:inline-block;">
              <label class="lable_add" style="width: 89px;">置顶:</label>
              <input id="topFlag" name="topFlag" value="0" type="hidden" />
              <input id="topFlagRadio" type="checkbox" value="" />
              <div id="topFlagDiv" style="display:none;width:100%;padding: 12px 0px 7px 0px;">
                  <label for="topStartStr" style="width: 96px; display: block; float: left;">置顶开始时间:</label> 
                  <input class="easyui-datetimebox" type="text" name="topStartStr" id="topStartStr" style="width: 150px;" editable="false"/>
                  <label for="topEndStr" style="margin-left: 114px;margin-right: 15px;">置顶结束时间:</label> 
                  <input class="easyui-datetimebox" type="text" name="topEndStr" id="topEndStr" style="width: 150px;" editable="false"/>
              </div>
            </div>
            <div class="div_fitem_1" style="width:100%;position:relative;">
               <div style="width:25%;position:absolute">
	              <label class="lable_add" style="display: initial" >内容:</label>
	            </div>
               <script id="editor" name="content" type="text/plain" style="margin-left:12%;width:83%;height:230px;"></script>
            </div>
        </form>
        <div id="selectVillages" class="easyui-dialog" title="选择小区" style="width:600px;height:350px;" > 
            <div id="d1">
			    <div id="d3">
			        <table cellpadding="0" cellspacing="8">
			            <tr>
			                <td style="font-size:16px;">待选小区</td>
			                <td>&nbsp;</td>
			                <td style="font-size:16px;">已选小区</td>
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
			        <div style="text-align:right;">
                         <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveSelectVillages()" style="width:90px">保存</a>
                         <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeDialog()" style="width:90px">关闭</a>
			        </div>
			    </div>
			</div>
        </div>
</body>
<script type="text/javascript">
function selectVillages(){
	$("#selectVillages").dialog('open');
	gaibianS1();
	$("#openDialog").val(1);
}
$("#selectVillages").dialog({  
    onClose: function () {
        $("#openDialog").val(0);
    }  
}); 
function closeDialog(){
    $("#openDialog").val(0);
    $("#selectVillages").dialog('close');
}
function saveSelectVillages(){
	var options = $("#s2").find("option");
	var villageIds = "";
    var villageNames = "";
	for(var i=0; i<options.length; i++) {
		if(i == options.length - 1){
	        villageIds = villageIds + options.eq(i).val();
	        villageNames = villageNames + options.eq(i).html();
		}else{
	        villageIds = villageIds + options.eq(i).val() + ",";
            villageNames = villageNames + options.eq(i).html() + ",";
		}
	}
	$("#villageIds").val(villageIds);
	$("#villageNames").textbox('setValue',villageNames)
    $("#selectVillages").dialog('close');
    $("#openDialog").val(0);
}
var url= "villageNotice/saveAdd";
function optSubmit(status){
    var valTmp = $("#openDialog").val();
    if(valTmp == 1){
        return;
    }
	var str = $("#villageIds").val();
	if(str == null || str == ""){
        $.messager.show({
            title: 'Info',
            msg: "请选择小区！",
            timeout:1000,
            showType:'slide'
        });
        return;
	}
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
	$("#publishStatus").val(status);
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
	$("#selectVillages").dialog('close');
    var ue = UE.getEditor('editor',{
    	autoHeightEnabled: false,
        initialFrameWidth : 600,
        initialFrameHeight: 240
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
			$("#topFlagDiv").show();
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