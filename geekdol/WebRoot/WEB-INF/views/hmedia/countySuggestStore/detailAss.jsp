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
    <form id="fm" method="post" novalidate enctype="multipart/form-data">
    	<input id="attachCount" name="attachCount" type="hidden" value="${attachCount}" />
    	<div class="div_fitem_1">
            <label class="lable_add" for="name">店名:</label>
            ${aroundSuggestStoreVo.name}
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="villageId">推荐到小区:</label>
            ${aroundSuggestStoreVo.villageName}
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="suggestStart">推荐开始时间:</label>
            ${suggestStart}
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="suggestEnd">推荐结束时间:</label>
            ${suggestEnd}
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="adress">地址:</label>
            ${aroundSuggestStoreVo.adress}
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="storeType">类型:</label> 
			<input id="storeType" name="storeType" class="easyui-combobox" 
				value="${aroundSuggestStoreVo.storeType}"
				data-options="disabled:true,valueField:'value',textField:'lable',url:'common/initSysData/STORETYPE'">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="concat">联系人:</label>
            ${aroundSuggestStoreVo.concat}
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="concatPhone">电话:</label>
            ${aroundSuggestStoreVo.concatPhone}
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="hoursStart">营业时间:</label>
            ${aroundSuggestStoreVo.hoursStart}
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="hoursEnd">关门时间:</label>
            ${aroundSuggestStoreVo.hoursEnd}
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="legalPerson">法人:</label>
            ${aroundSuggestStoreVo.legalPerson}
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="personCard">法人身份证:</label>
            ${aroundSuggestStoreVo.personCard}
        </div>
        <div class="div_fitem_1" style="width:100%">
            <label class="lable_add" style="width:86px" for="organizationCode">组织代码证:</label>
            ${aroundSuggestStoreVo.organizationCode}
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="iconfile">图标:</label>
            <img id="icon" src="${aroundSuggestStoreVo.icon}"  width="80px" height="80px" onclick="showPic(this)" style="cursor: pointer;"/>
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="businessLicensefile">营业执照:</label>
            <img id="licenseImage" src="${aroundSuggestStoreVo.licenseImage}"  width="80px" height="80px" onclick="showPic(this)" style="cursor: pointer;"/>
        </div>
        <div class="div_fitem_1" style="width:100%">
            <label class="lable_add" style="width:86px" for="storefile">店面图片:</label>
            <img id="storeImage" src="${aroundSuggestStoreVo.storeImage}"  width="80px" height="80px" onclick="showPic(this)" style="cursor: pointer;"/>
        </div>
        <div id="addFile" class="fitem" style="width:100%;">
	        <c:forEach var="attach" items="${attchList}" varStatus="status">
	        	<div class="div_fitem_1" style="margin-top:16px;">
	        		<label class="lable_add" for="propagandafile">宣传图片:</label>
	            	<img src="${attach.url }"  width="80px" height="80px" onclick="showPic(this)" style="cursor: pointer;"/>
	        	</div>
	        </c:forEach>
        </div>
        <div class="div_fitem_1" style="width:100%;position:relative;margin-top:12px;">
        	<div style="width:25%;position:absolute">
        		<label class="lable_add" style="display: initial" for="introduce">简介:</label>
        	</div>
        	<textarea id="introduce" name="introduce" class="easyui-validatebox" readonly="readonly" style="width: 200px;height:200px;">${aroundStoreVo.introduce}</textarea>
        </div>
        <div class="fitem" style="float: left;width: 100%;">
            <div class="div_fitem_1" style="margin-top:12px;">
            	<label class="lable_add" for="longitude">中心点经度:</label>
            	${aroundSuggestStoreVo.longitude}
            	<input id="longitude" name="longitude" value="${aroundSuggestStoreVo.longitude}" type="hidden">
            </div>
        	<div class="div_fitem_1" style="margin-top:12px;">
            	<label for="latitude">中心点纬度:</label>
            	${aroundSuggestStoreVo.latitude}
            	<input id="latitude" name="latitude" value="${aroundSuggestStoreVo.latitude}" type="hidden">
            </div>
           	<div class="resource_item" style="width:auto;">
               <div class="column">位置</div>
               <div class="content" style="width:100%;">
                    <div id="allmap" style="height: 300px;width: 90%;"></div>      
               </div>
           	</div>
        </div>
    </form>
</body>
<script type="text/javascript">
//点击打开大图
function showPic(obj) {  
	window.open(obj.src);
}
</script>
<!-- 百度地图开始 -->
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=vnpoj2aECUZd4MuGFBb7DWak"></script>
<script type="text/javascript">
setTimeout(function(){
    var latitude = $('#longitude').val();
    var longitude = $('#latitude').val();
    
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