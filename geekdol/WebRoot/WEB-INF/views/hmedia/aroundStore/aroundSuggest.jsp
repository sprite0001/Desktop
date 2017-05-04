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
    <style type="text/css">
		.s1 { 
			width: 80px;
		}
		#d1 {
			width: 420px;
			height: 260px;
			background-color: #F5DEB3;
			margin-top: 14px;
		}
		#d2 {
			height: 30px;
			font-size: 24px;
			background-color: blue;
			color: white;
		}
		#d3 {
			padding-left: 15px;
		}
		.suggest_double {
			float: left;
			margin-top: 14px;
		}
		.multiple_select {
			width: 140px;
			height: 200px;
		}
	</style>
	<script type="text/javascript" src="${basePath}static/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${basePath}static/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${basePath}static/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${basePath}static/js/common.js"></script>
	<script type="text/javascript" src="${contextPath}/static/js/lab2.js"></script>
	<script type="text/javascript" src="${basePath}static/js/validator.js"></script>
	<script type="text/javascript">
 
	var basePath = '<%=basePath%>';
	
	//动态改变s1的值
	function villageLeft() {
		$.ajax({
			type: "POST",
			url:basePath+"aroundStore/queryVillageNoAroundStore/0",
			cache: false,
			dataType : "json",
			success: function(data){
				 var selObj = $("#s1"); 
				 var selOpt = $("#s1 option");
				 selOpt.remove();
				 for(var village in data){
					 selObj.append("<option value="+data[village].villageId+">"+data[village].villageName+"</option>");
				 }  
	        }
	   	}); 	
	}

	$(document).ready(function(){
		villageLeft();
	});
	</script>
</head>
<body>
    <form id="fm" method="post" novalidate enctype="multipart/form-data">
    	<input name="aroundStoreId" value="${aroundStoreId}" type="hidden"/>
    	<input name="updEac" value="${aroundStoreVo.updEac}" type="hidden"/>
    	<input id="villageId" name="villageId" type="hidden"/>
    	<div class="div_fitem_1">
            <label class="lable_add" for="name">店名:</label>
            ${aroundStoreVo.name}
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="villageId">所属小区:</label>
            ${aroundStoreVo.villageName}
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="legalPerson">法人:</label>
            <input id="legalPerson" name="legalPerson" class="easyui-textbox" data-options="required:true,validType:'isBlank'" style="width: 150px;">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="personCard">法人身份证:</label>
            <input id="personCard" name="personCard" class="easyui-textbox" data-options="required:true,validType:'idcared'" style="width: 150px;">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="organizationCode">组织代码证:</label>
            <input id="organizationCode" name="organizationCode" class="easyui-textbox" data-options="required:true,validType:'isBlank',validType:'organizationCode'" style="width: 150px;">
        </div>
        <div class="div_fitem_1" style="height:22px">
            <label class="lable_add" for="iconfile">图标:</label>
            <input id="iconfile" name="file" class="easyui-filebox" type="text" required="true" style="width: 150px;">
        </div>
        <div class="div_fitem_1" style="height:22px">
            <label class="lable_add" for="businessLicensefile">营业执照:</label>
            <input id="businessLicensefile" name="file" class="easyui-filebox" type="text" required="true" style="width: 150px;">
        </div>
        <div class="div_fitem_1" style="height:22px">
            <label class="lable_add" for="storefile">店面图片:</label>
            <input id="storefile" name="file" class="easyui-filebox" type="text" required="true" style="width: 150px;">
        </div>
        <div class="fitem" style="width:100%;">
        	<div class="div_fitem_1" style="width:33%;margin-top:16px;">
            	<label class="lable_add" style="width:33%" for="propagandafile">宣传图片:</label>
           	 	<input name="propagandafile" class="easyui-filebox" type="text" style="width: 150px;">
            </div>
        	<div class="div_fitem_1" style="width:33%;margin-top:16px;">
            	<label class="lable_add" style="width:33%" for="propagandafile">宣传图片:</label>
            	<input name="propagandafile" class="easyui-filebox" type="text" style="width: 150px;">
            </div>
        	<div class="div_fitem_1" style="width:33%;margin-top:16px;">
            	<label class="lable_add" style="width:33%" for="propagandafile">宣传图片:</label>
            	<input name="propagandafile" class="easyui-filebox" type="text" style="width: 150px;">
            </div>
        </div>
        <div class="div_fitem_1" style="width:100%;position:relative;margin-top:12px;">
        	<div style="width:25%;position:absolute">
        		<label class="lable_add" style="display: initial" for="introduce">简介:</label>
        	</div>
            <textarea id="introduce" name="introduce" maxlength="200" class="easyui-validatebox" style="margin-left:13%;width:78%;height:200px;">${aroundStoreVo.introduce}</textarea>
        </div>
        <div class="fitem">
        	<div class="div_fitem_1" style="margin-top:12px;">
        		<label for="longitude">中心点经度:</label>
        		<input id="longitude" name="longitude" class="easyui-textbox" data-options="required:true,validType:'isBlank'" style="width: 150px;">
        	</div>
        	<div class="div_fitem_1" style="margin-top:12px;">
        		<label for="latitude">中心点纬度:</label>
        		<input id="latitude" name="latitude" class="easyui-textbox" data-options="required:true,validType:'isBlank'" style="width: 150px;">
        	</div>
        	<div class="resource_item">
	            <div class="column">位置</div>
	                 <div class="content" style="width:90%;">
	                 <div id="allmap" style="height:300px;width:486px;"></div>     
	            </div>
        	</div>
        </div>
        <div class="fitem">
           	<div class="div_fitem_1">
            	<label for="suggestStart">推荐开始时间:</label>
            	<input id="suggestStart" name="suggestStart" class="easyui-datetimebox" required="true" style="width: 140px;" editable="false"><br/>
            </div>
            <div class="div_fitem_1">
            	<label for="suggestEnd">推荐结束时间:</label>
            	<input id="suggestEnd" name="suggestEnd" class="easyui-datetimebox" required="true" style="width: 140px;" editable="false">
            </div>
           	<div id="d1" class="suggest_double" style="margin-left:8%">
				<div id="d3">
					<table cellpadding="0" cellspacing="8">
						<tr>
			                <td>待选小区</td>
			                <td>&nbsp;</td>
			                <td>已选小区</td>
			            </tr>
						<tr>
							<td>
								<select id="s1" name="s1" class="multiple_select" multiple="multiple"></select>
							</td>
							<td>
								<p><input id="b1" type="button" class="s1" value="--&gt;" /></p>
								<p><input type="button" id="b2" class="s1" value="--&gt;&gt;" /></p>
								<p><input type="button" id="b3" class="s1" value="&lt;--" /></p>
								<p><input type="button" id="b4" class="s1" value="&lt;&lt;--" /></p>
							</td>
							<td><select id="s2" name="s2" class="multiple_select" multiple="multiple"></select></td>
						</tr>
					</table>
				</div>
			</div>
           </div>
    </form>
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
$("#suggestStart").datetimebox({
    onSelect: function(date){
    	var dateTemp = date.split("-");
        var nDate = new Date(dateTemp[1] + '-' + dateTemp[2] + '-' + dateTemp[0]); //转换为MM-DD-YYYY格式    
        var millSeconds = Math.abs(nDate) + (1 * 24 * 60 * 60 * 1000);
        var rDate = new Date(millSeconds);
        var year = rDate.getFullYear();
        var month = rDate.getMonth() + 1;
        if (month < 10) {
        	month = "0" + month;
        }
        var day = rDate.getDate();
        if (day < 10) {
        	day = "0" + day;
        }
        var startDate = $('#suggestStart').val();
    	var time = startDate.substring(11, 19);
        //注意这个回调里面的date只有 年、月、日
        //时、分、秒需要用 var spinnerStart=$("#suggestStart").datetimebox("spinner"); 取得spinner对象，然后才能取到
        //也可以先取得suggestStart的时间，然后自己再格式化取得。
        //然后进行你的要加减的时间换算
        //最后进行进行设置值 其格式为   "2016-8-2 12:30:57"
       $("#suggestEnd").datetimebox("setValue", year + "-" + month + "-" + day + " " + time);
    }
});

function optSubmit(){
	var villageId = "";
	$("#s2 option").each(function(){
        villageId+=$(this).val()+",";
   	});
	if (villageId.length > 0) {
		villageId = villageId.substr(0, villageId.length - 1);
	}
	$("#villageId").val(villageId);
    $('#fm').form('submit',{
        url: "aroundStore/saveAroundSuggestStore/${aroundStoreId}",
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
	var longitude = 113.552262; 
    var latitude = 34.81262;
    
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