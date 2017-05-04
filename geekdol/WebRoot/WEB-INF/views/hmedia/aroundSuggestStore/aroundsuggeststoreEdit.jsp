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
	<script type="text/javascript" src="${basePath}static/js/validator.js"></script>
</head>
<body>
    <form id="fm" method="post" novalidate enctype="multipart/form-data">
    	<input name="id" id="id" type="hidden" value="${id}">
    	<input id="attachCount" name="attachCount" type="hidden" value="${attachCount}" />
    	<div class="div_fitem_1">
            <label class="lable_add" for="name">店名:</label>
            <input id="name" name="name" class="easyui-textbox" data-options="required:true,validType:'isBlank'" style="width: 150px;">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="villageId">推荐到小区:</label>
            <input id="villageId" name="villageId" type="hidden">
            <input id="villageName" name="villageName" class="easyui-textbox" data-options="editable:false,required:true">
            <input type="button" value="选择" onclick="toSelectVillage()">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="suggestStart">推荐开始时间:</label>
            <input id="suggestStartStr" name="suggestStartStr" class="easyui-datetimebox" required="true" style="width: 150px;" editable="false">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="suggestEnd">推荐结束时间:</label>
            <input id="suggestEndStr" name="suggestEndStr" class="easyui-datetimebox" required="true" style="width: 150px;" editable="false">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="adress">地址:</label>
            <input id="adress" name="adress" class="easyui-textbox" data-options="required:true,validType:'isBlank'" style="width: 150px;">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="storeType">类型:</label> 
			<input id="storeType" name="storeType" class="easyui-combobox" 
				data-options="editable:false,valueField:'value',textField:'lable',url:'common/initSysDataNoAll/${STORETYPE}'">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="concat">联系人:</label>
            <input id="concat" name="concat" class="easyui-textbox" data-options="required:true,validType:'isBlank'" style="width: 150px;">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="concatPhone">电话:</label>
            <input id="concatPhone" name="concatPhone" class="easyui-textbox" data-options="validType:'phoneRex'" required="true" style="width: 150px;">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="hoursStart">营业时间:</label>
            <input id="hoursStart" name="hoursStart" class="easyui-timespinner" style="width: 150px;" data-options="min:'08:00',showSeconds:true">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="hoursEnd">关门时间:</label>
            <input id="hoursEnd" name="hoursEnd" class="easyui-timespinner" style="width: 150px;" data-options="min:'08:00',showSeconds:true">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="legalPerson">法人:</label>
            <input id="legalPerson" name="legalPerson" class="easyui-textbox" data-options="required:true,validType:'isBlank'" style="width: 150px;">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="personCard">法人身份证:</label>
            <input id="personCard" name="personCard" class="easyui-textbox" data-options="validType:'idcared'" required="true" style="width: 150px;">
        </div>
        <div class="div_fitem_1" style="width:100%">
            <label class="lable_add" style="width:86px" for="organizationCode">组织代码证:</label>
            <input id="organizationCode" name="organizationCode" class="easyui-textbox" disable="true" style="width: 150px;">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="iconfile">图标:</label>
            <input id="iconfile" name="file" class="easyui-filebox" type="text" style="width: 100px;">
            <img id="icon" src=""  width="80px" height="80px" onclick="showPic(this)" style="cursor: pointer;"/>
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="businessLicensefile">营业执照:</label>
            <input id="businessLicensefile" name="file" class="easyui-filebox" type="text" style="width: 120px;">
            <img id="licenseImage" src=""  width="80px" height="80px" onclick="showPic(this)" style="cursor: pointer;"/>
        </div>
        <div class="div_fitem_1" style="width:100%">
            <label class="lable_add" style="width:86px" for="storefile">店面图片:</label>
            <input id="storefile" name="file" class="easyui-filebox" type="text" style="width: 120px;">
            <img id="storeImage" src=""  width="80px" height="80px" onclick="showPic(this)" style="cursor: pointer;"/>
        </div>
        <div id="addFile" class="fitem" style="width:100%;">
	        <c:forEach var="attach" items="${attchList}" varStatus="status">
	        	<div class="div_fitem_1" style="margin-top:16px;">
	        		<label class="lable_add" for="propagandafile">宣传图片:</label>
	        		<input type="hidden" value="${attach.url }" name="attchfiles">
	            	<input name="propagandafile" class="easyui-filebox" type="text" style="width: 120px;">
	            	<img src="${attach.url }"  width="80px" height="80px" onclick="showPic(this)" style="cursor: pointer;"/>
	            </div>
	        </c:forEach>
        </div>
        <div class="div_fitem_1" style="width:100%;position:relative;margin-top:12px;">
        	<div style="width:25%;position:absolute">
        		<label class="lable_add" style="display: initial" for="introduce">简介:</label>
        	</div>
            <textarea id="introduce" name="introduce" maxlength="200" class="easyui-validatebox" style="margin-left:13%;width:70%;height:200px;"></textarea>
        </div>
        <div class="fitem" style="float: left;width: 100%;">
            <div class="div_fitem_1" style="margin-top:12px;">
            	<label class="lable_add" for="longitude">中心点经度:</label>
            	<input id="longitude" name="longitude" class="easyui-textbox" data-options="required:true,validType:'isBlank'" style="width: 150px;">
           	</div>
        	<div class="div_fitem_1" style="margin-top:12px;">
            	<label class="lable_add" for="latitude">中心点纬度:</label>
            	<input id="latitude" name="latitude" class="easyui-textbox" data-options="required:true,validType:'isBlank'" style="width: 150px;">
            </div>
            <div class="resource_item">
	        	<div  class="column">位置</div>
	           	<div class="content" style="width:100%;">
	                 <div id="allmap" style="height: 300px;width: 90%;"></div>     
	            </div>
            </div>
    	</div>
    </form>
    <div id="village_dlg" class="easyui-dialog" style="padding:10px 20px;width: 600px;height: 400px;"
           closed="true" buttons="#village_dlg_buttons" modal="true">
	  	<iframe id='addVillageFormUI' border='0' vspace='0' hspace='0' 
	    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
	    	style="height:100%;width:100%;left:10px;top:8px" src="">
	    </iframe>
    </div>
    <div id="village_dlg_buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveSubmit()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#village_dlg').dialog('close')" style="width:90px">取消</a>
    </div>
</body>
<script type="text/javascript">
$('#iconfile').filebox({
    buttonText: '选择图标',
    buttonAlign: 'right',
    accept: 'image/*'
});
$('#businessLicensefile').filebox({
    buttonText: '选择营业执照',
    buttonAlign: 'right',
    accept: 'image/*'
});
$('#storefile').filebox({
    buttonText: '选择店面图片',
    buttonAlign: 'right',
    accept: 'image/*'
});
$("input[name='propagandafile']").filebox({
    buttonText: '选择宣传图片',
    buttonAlign: 'right',
    accept: 'image/*'
});
$(document).ready(function(){
	var attachCount = parseInt($('#attachCount').val());
	if (attachCount < 3) {
		var least = 3 - attachCount;
		for (var i=0; i<least; i++) {
			$('#addFile').append("<div class='div_fitem_1' style='margin-top:16px;'><label class='lable_add' for='propagandafile'>宣传图片:</label><input name='propagandafile' class='easyui-filebox' type='text' style='width: 150px;'></div>");
		}
	}
	$("input[name='propagandafile']").filebox({
	    buttonText: '选择宣传图片',
	    buttonAlign: 'right',
	    accept: 'image/*'
	});
}) 
function saveSubmit(){
    /* 调用共通js中是否为IE的判定方法 */
    if(window.frames["addVillageFormUI"].contentWindow == undefined) {
        window.frames["addVillageFormUI"].saveVillage();
    } else {
        window.frames["addVillageFormUI"].contentWindow.saveVillage();
    }
}

function conversion(value){
	var time = new Date(value);
	return time.format("yyyy-MM-dd hh:mm:ss");
}

$('#hoursStart').timespinner({
    min: '08:00',
    required: true,
    showSeconds: true
});
$('#hoursEnd').timespinner({
    max: '24:00',
    required: true,
    showSeconds: true
});
function toSelectVillage(){
	var villageId=$("#villageId").val();
	$('#addVillageFormUI').attr("src","aroundSuggestStore/selectVillage?villageId="+villageId);
    $('#village_dlg').dialog('open').dialog('setTitle','小区选择');
}
$('#fm').form({onLoadSuccess:loadsucc});
$('#fm').form('load','aroundSuggestStore/findById/${id}?t='+new Date().getTime());
//回调函数
function loadsucc(data) {  
	$("#icon").attr("src",data.icon);
	$("#storeImage").attr("src",data.storeImage);
	$("#licenseImage").attr("src",data.licenseImage);
}  
//点击打开大图
function showPic(obj) {  
	window.open(obj.src);
}
function optSubmit(){
	var suggestStart = $('#suggestStartStr').datetimebox('getValue');
	var suggestEnd = $('#suggestEndStr').datetimebox('getValue');
	var sugstart = new Date(suggestStart);
	var sugend = new Date(suggestEnd);
	if(sugstart >= sugend){
        $.messager.show({
            title: 'Info',
            msg: "推荐结束时间应大于推荐开始时间！",
            timeout:1000,
            showType:'slide'
        });
        return;
	}
	var hsh = $('#hoursStart').timespinner('getHours');
	var hsm = $('#hoursStart').timespinner('getMinutes');
	var hss = $('#hoursStart').timespinner('getSeconds');
	var heh = $('#hoursEnd').timespinner('getHours');
	var hem = $('#hoursEnd').timespinner('getMinutes');
	var hes = $('#hoursEnd').timespinner('getSeconds');
	if(heh < hsh){
        $.messager.show({
            title: 'Info',
            msg: "结束时间应大于开始时间！",
            timeout:1000,
            showType:'slide'
        });
        return;
	} else {
		if (heh == hsh) {
			if(hem < hsm){
		        $.messager.show({
		            title: 'Info',
		            msg: "结束时间应大于开始时间！",
		            timeout:1000,
		            showType:'slide'
		        });
		        return;
			}
		} else {
			if(hes < hss){
		        $.messager.show({
		            title: 'Info',
		            msg: "结束时间应大于开始时间！",
		            timeout:1000,
		            showType:'slide'
		        });
		        return;
			}
		}
	}
    $('#fm').form('submit',{
        url: "aroundSuggestStore/updateAroundSuggestStore",
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
</script>
<!-- 百度地图开始 -->
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=vnpoj2aECUZd4MuGFBb7DWak"></script>
<script type="text/javascript">
setTimeout(function(){
	var longitude = $('#longitude').val();
    var latitude = $('#latitude').val();
    
    
    var point = new BMap.Point(longitude,latitude);  // 创建点坐标A--大渡口区
    
    window.map = new BMap.Map("allmap");  
    map.centerAndZoom(point,12);  
    map.enableScrollWheelZoom();
    
    // 百度地图API功能
    var iconImg = new BMap.Icon("${basePath}static/images/location.gif", new BMap.Size(100, 150));  
    var marker = new BMap.Marker(point,{icon:iconImg});
    var label = new BMap.Label("小区位置",{"offset":new BMap.Size(15,-15)});
    marker.setLabel(label);
    map.addOverlay(marker);
    marker.enableDragging();
    marker.addEventListener("click",attribute);
    function attribute(){
        var p = marker.getPosition();  //获取marker的位置
        $("#latitude").textbox("setValue", p.lat); 
         $("#longitude").textbox("setValue", p.lng); 
    }
},1000);
</script>
<!-- 百度地图结束 -->
</html>