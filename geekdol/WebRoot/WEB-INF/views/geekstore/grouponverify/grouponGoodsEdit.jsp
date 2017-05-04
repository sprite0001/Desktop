<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%
String path = request.getContextPath();
String httpPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="httpPath" value="<%=httpPath %>"></c:set>
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
	
	<!-- ueditor begin -->
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/geekstore_ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/lang/zh-cn/zh-cn.js"></script>
    <!-- ueditor end -->
    
	<style type="text/css">
		.s1{width:120px;}
		#d1{width:510px;height:320px;background-color:#F5DEB3;margin:0 auto;}
		#d2{height:30px;font-size:24px;background-color:blue;color:white;}
		#d3{padding-left:30px;}
		.fitemImg{width: 146px;
    	 float: left;}
	</style>
</head>
<body>
	<div class="ftitle"></div>
    <form id="fm" method="post" novalidate>
        <div class="fitem">
            <label for="serialCode">团购编号:</label>
            <input id="serialCode" name="serialCode" class="easyui-textbox" readonly="readonly">
            <label for="grouponClassific">团购分类:</label>
            <select id="grouponClassific" name="grouponClassific" class="easyui-combobox" style="width: 150px;">
				<option value="">全部</option>
				<option value="0" <c:if test="${'0' eq grouponClassific }">selected="selected"</c:if>>${GOOD}</option>
				<option value="1" <c:if test="${'1' eq grouponClassific }">selected="selected"</c:if>>${SERVICE}</option>
			</select>
        </div>
        <div class="fitem">
            <label for="grouponName">团购名称:</label>
            <input id="grouponName" name="grouponName" class="easyui-textbox" style="width: 360px;" readonly="readonly">
        </div>
        <div class="fitem">
            <label for="subtitle">副标题:&nbsp;&nbsp;&nbsp;</label>
            <input id="subtitle" name="subtitle" class="easyui-textbox" style="width: 360px;" readonly="readonly">
        </div>
        <div class="fitem">
            <label for="maxQuantity">团购数量:</label>
            <input id="maxQuantity" name="maxQuantity" class="easyui-numberbox" style="width: 150px;" readonly="readonly">
            <label for="minQuantity">最低数量:</label>
            <input id="minQuantity" name="minQuantity" class="easyui-numberbox" style="width: 150px;" readonly="readonly">
        </div>
        <div class="fitem">
            <label for="costPrice">成本价格:</label>
            <input id="costPrice" name="costPrice" class="easyui-numberbox" precision="2" max="99999999999.99" style="width: 150px;" readonly="readonly">
            <label for="grouponPrice">团购价格:</label>
            <input id="grouponPrice" name="grouponPrice" class="easyui-numberbox" precision="2" max="99999999999.99" style="width: 150px;" readonly="readonly">
        </div>
        <div class="fitem">
        	<label for="startTime">起始时间:</label>
            <input id="startTime" name="startTimeStr" class="easyui-datetimebox" readonly="readonly" style="width: 150px;">
            <label for="endTime">结束时间:</label>
            <input id="endTime" name="endTimeStr" class="easyui-datetimebox" readonly="readonly" style="width: 150px;">
        </div>
        <div class="fitem">
        	<label for="detailModel">处理模式:</label>
            <input id="detailModel" name="detailModel" class="easyui-combobox" readonly="readonly"
					data-options="valueField:'value',textField:'lable',url:'common/initSysData/${PROCESSINGMODE }'" style="width: 150px;">
            <label for="storePrice">合作价格:</label>
            <input id="storePrice" name="storePrice" class="easyui-numberbox" precision="2" max="99999999999.99" readonly="readonly" style="width: 150px;">
        </div>
        <div class="fitemImg">
        	<label>展示图1:</label>
        	<img alt="" src="" id="pic1" name="pic1" style="width: 60px;height: 60px;cursor: pointer;" onclick="showPic(this)">
        </div>
        <div class="fitemImg">
        	<label>展示图2:</label>
        	<img alt="" src="" id="pic2" name="pic2" style="width: 60px;height: 60px;cursor: pointer;" onclick="showPic(this)">
        </div>
        <div class="fitem">
        	<label>展示图3:</label>
        	<img alt="" src="" id="pic3" name="pic3" style="width: 60px;height: 60px;cursor: pointer;" onclick="showPic(this)">
        </div>
        <div class="fitem">
        	<label>扩展属性:</label>
			<table id="dg" class="easyui-datagrid" style="height: 200px;"
				url="grouponVerify/findGrouponGoodsExtendInfo/${id }" pagination="false" fitColumns="false" singleSelect="true"
				data-options="fit:false,onClickRow:onClickRow,border:true">
				<thead>
					<tr>
						<th field="extendAttr" width="100">属性名称</th>
						<th field="extendValue" width="100">属性值</th>
						<th field="extendMark" width="300">描述</th>
					</tr>
				</thead>
			</table>
		</div>
       	<div class="fitem">
        	<label>发布店面:</label>
        	<table cellpadding="0" cellspacing="8" style="margin-left: 44px;">
				<tr>
					<td><select id="s2" name="s2" style="width:150px;height:220px;" multiple="multiple"></select></td>
				</tr>
			</table>
       	</div>
       	<div class="fitem">
       		<label>详情:</label>
       		${details}
       	</div>
       	<div class="fitem">
       		<label for="statusStr">审核情况:</label>
       		<select id="statusStr" name="statusStr" style="width: 100px;width: 99px;height: 25px;margin-bottom: 12px;"
       			onchange="showRejectReason()">
       			<option value="${GROUPONGOODSSTATUS_04 }">通过</option>
       			<option value="${GROUPONGOODSSTATUS_05 }">拒绝</option>
       		</select>
       	</div>
       	<div class="rejectReason fitem" style="display: none;">
       		<label for="rejectReason">拒绝理由:</label>
       		<textarea id="rejectReason" name="rejectReason" class="easyui-validatebox" style="height:230px;width: 260px"></textarea>
       	</div>
       	<input type="hidden" id="id" name = "id" value="${id }">
    </form>
</body>
<script type="text/javascript">
	var id = $('#id').val();
	$('#fm').form('load','grouponVerify/findGrouponGoods/'+id);
	//点击打开大图
	function showPic(obj) {  
		window.open(obj.src);
	}
    /* 行编辑时所用的方法 */
    var editIndex = undefined;
	function endEditing(){
		if (editIndex == undefined){return true}
		if ($('#dg').datagrid('validateRow', editIndex)){
			$('#dg').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickRow(index){
		if (editIndex != index){
			if (endEditing()){
				$('#dg').datagrid('selectRow', index)
						.datagrid('beginEdit', index);
				editIndex = index;
			} else {
				$('#dg').datagrid('selectRow', editIndex);
			}
		}
	}
	
	function getChanges(){
		var rows = $('#dg').datagrid('getChanges');
		alert(rows.length+' rows are changed!');
	}
    
	function optSubmit(){
	    $('#fm').form('submit',{
	        url: "grouponVerify/updateGrouponGoods",
	        onSubmit: function(){
	            return $(this).form('validate');
	        },
	        success: function(result){
	            var result = eval('('+result+')');
	            if ("Error"==result.type){
	                $.messager.show({
	                    title: 'Error',
	                    msg: result.Msg
	                });
	                
	            } else {
	            	$.messager.show({
	                    title: 'Success',
	                    msg: result.Msg,
	                    timeout:1000,
	                	showType:'slide'
	                });
	            	setTimeout('window.parent.close()',2000);
	            }
	        }
	    });
	}
	function showRejectReason(){
		 var rejectValue = '${GROUPONGOODSSTATUS_05 }';
		 var selectValue = $("#statusStr").val();
		 if(selectValue == rejectValue){
			 $(".rejectReason").show();
		 }else{
			 $("#rejectReason").val("");
			 $(".rejectReason").hide();
		 }
	}
	$(function(){
	    /**已选择的小区初始化*/
	    var village = eval('('+'${village}'+')');
	    var showHtml = '';
		$.each(village, function(i, item) {
			showHtml += "<option value='"+item.villageId+"'>"+item.villageName+"</option>";
		});
		$("#s2").append(showHtml);
		
		/**附件展示*/
		var attachs = eval('('+'${attachs}'+')');
		$.each(attachs, function(i, item) {
			$("#pic"+(i+1)).attr("src",item.url);
		});
	});
</script>
</html>