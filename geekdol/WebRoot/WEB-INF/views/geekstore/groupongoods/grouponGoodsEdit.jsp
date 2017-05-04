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
	</style>
</head>
<body>
	<div class="ftitle"></div>
    <form id="fm" method="post" novalidate enctype="multipart/form-data">
        <div class="fitem">
            <label for="serialCode">团购编号:</label>
            <input id="serialCode" name="serialCode" class="easyui-textbox" data-options="required:true">
            <label for="grouponClassific">团购分类:</label>
            <select id="grouponClassific" name="grouponClassific" class="easyui-combobox" style="width: 150px;">
				<option value="">全部</option>
				<option value="0" <c:if test="${'0' eq grouponClassific }">selected="selected"</c:if>>${GOOD}</option>
				<option value="1" <c:if test="${'1' eq grouponClassific }">selected="selected"</c:if>>${SERVICE}</option>
			</select>
        </div>
        <div class="fitem">
            <label for="grouponName">团购名称:</label>
            <input id="grouponName" name="grouponName" class="easyui-textbox" required="true" style="width: 360px;">
        </div>
        <div class="fitem">
            <label for="subtitle">副标题:&nbsp;&nbsp;&nbsp;</label>
            <input id="subtitle" name="subtitle" class="easyui-textbox" required="true" style="width: 360px;">
        </div>
        <div class="fitem">
            <label for="maxQuantity">团购数量:</label>
            <input id="maxQuantity" name="maxQuantity" class="easyui-numberbox" required="true" style="width: 150px;">
            <label for="minQuantity">最低数量:</label>
            <input id="minQuantity" name="minQuantity" class="easyui-numberbox" required="true" style="width: 150px;">
        </div>
        <div class="fitem">
            <label for="costPrice">成本价格:</label>
            <input id="costPrice" name="costPrice" class="easyui-numberbox" precision="2" max="99999999999.99" required="true" style="width: 150px;">
            <label for="grouponPrice">团购价格:</label>
            <input id="grouponPrice" name="grouponPrice" class="easyui-numberbox" precision="2" max="99999999999.99" required="true" style="width: 150px;">
        </div>
        <div class="fitem">
        	<label for="startTime">起始时间:</label>
            <input id="startTime" name="startTimeStr" class="easyui-datetimebox" required="required" style="width: 150px;" editable="false">
            <label for="endTime">结束时间:</label>
            <input id="endTime" name="endTimeStr" class="easyui-datetimebox" required="true" style="width: 150px;" editable="false">
        </div>
        <div class="fitem">
        	<label for="detailModel">处理模式:</label>
            <input id="detailModel" name="detailModel" class="easyui-combobox" 
					data-options="valueField:'value',textField:'lable',url:'common/initSysData/${PROCESSINGMODE }'" style="width: 150px;">
            <label for="storePrice">合作价格:</label>
            <input id="storePrice" name="storePrice" class="easyui-numberbox" precision="2" max="99999999999.99" required="true" style="width: 150px;">
        </div>
        <div class="fitem">
        	<label>展示图1:</label>
	        <input type="hidden" name="picOne" id="picOne">
        	<input style="width: 300px;" type="text" class="easyui-filebox" id="picfile1" name="file">
        	<img id="picture1" name="picture1" style="width: 60px;height: 60px;cursor: pointer;" onclick="showPic(this)">
        </div>
        <div class="fitem">
        	<label>展示图2:</label>
        	<input style="width: 300px;" type="text" class="easyui-filebox" id="picfile2" name="file">
        	<input type="hidden" name="picTow" id="picTow">
        	<img id="picture2" name="picture2" style="width: 60px;height: 60px;cursor: pointer;" onclick="showPic(this)">
        </div>
        <div class="fitem">
        	<label>展示图3:</label>
        	<input style="width: 300px;" type="text" class="easyui-filebox" id="picfile3" name="file">
        	<input type="hidden" name="picThree" id="picThree">
        	<img id="picture3" name="picture3" style="width: 60px;height: 60px;cursor: pointer;" onclick="showPic(this)">
        </div>
        <div class="fitem">
        	<label>扩展属性:</label>
			<table id="dg" class="easyui-datagrid" style="height: 300px;" toolbar="#toolbar" 
				url="groupongoods/findGrouponGoodsExtendInfo/${id }" pagination="false" fitColumns="false" singleSelect="true"
				data-options="fit:false,onClickRow:onClickRow,border:true">
				<thead>
					<tr>
						<th field="extendAttr" width="100"  editor="textbox">属性名称</th>
						<th field="extendValue" width="100"  editor="textbox">属性值</th>
						<th field="extendMark" width="300"  editor="textbox">描述</th>
					</tr>
				</thead>
			</table>
			<div id="toolbar">
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="append()">新增</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeit()">删除</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="reject()">撤销</a>
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">Accept</a>
		    </div>
		</div>
       	<div class="fitem">
        	<label>发布店面:</label>
        	<table cellpadding="0" cellspacing="8" style="margin-left: 44px;">
				<tr>
	                <td>待选小区</td>
	                <td>&nbsp;</td>
	                <td>已选小区</td>
	            </tr>
	            <tr>
				   <td>
					   <select id="s01" name="s01" style="width:150px; height:25px;" onchange="gaibianS1()"></select>
				   </td>
				   <td>&nbsp;</td>
				   <td></td>
				</tr>
				<tr>
					<td>
						<select id="s1" name="s1" style="width:150px; height:220px;" multiple="multiple"></select>
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
       	</div>
       	<div class="fitem">
       		<label>详情:</label>
       		<script id="editor" name="details" type="text/plain" style="width:700px;height:500px;"></script>
       	</div>
       	<input type="hidden" id="id" name = "id" value="${id }">
       	<input type="hidden" id="villageId" name = "villageId">
       	<input type="hidden" id="extendInfo" name = "extendInfo">
    </form>
    <c:forEach items="${attachs}" var="item" varStatus="i">
     	<input type="hidden" name="pic${i.index }" id="pic${i.index }" value="${item.url }">
    </c:forEach>
</body>
<script type="text/javascript">
	$('#picfile1').filebox({
	    buttonText: '选择展示图',
	    buttonAlign: 'right',
	    accept: 'image/*'
	});
	$('#picfile2').filebox({
	    buttonText: '选择展示图',
	    buttonAlign: 'right',
	    accept: 'image/*'
	});
	$('#picfile3').filebox({
	    buttonText: '选择展示图',
	    buttonAlign: 'right',
	    accept: 'image/*'
	});
	var id = $('#id').val();
	$('#fm').form('load','groupongoods/findGrouponGoods/'+id);
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
	function append(){
		if (endEditing()){
			$('#dg').datagrid('appendRow',{});
			editIndex = $('#dg').datagrid('getRows').length-1;
			$('#dg').datagrid('selectRow', editIndex)
					.datagrid('beginEdit', editIndex);
		}
	}
	//删除 
	function removeit(){
		if (editIndex == undefined){return}
		$('#dg').datagrid('cancelEdit', editIndex)
				.datagrid('deleteRow', editIndex);
		editIndex = undefined;
	}
	//撤销编辑
	function reject(){
		$('#dg').datagrid('rejectChanges');
		editIndex = undefined;
	}
	function accept(){
		if (endEditing()){
			/* $('#dg').datagrid('acceptChanges'); */
			$("#dg").datagrid('endEdit', editIndex);
			//var rows = $("#dg").datagrid('getChanges');
			var rows = $("#dg").datagrid('acceptChanges');
            var rowstr = JSON.stringify(rows);
            /* alert(rowstr); */
		}
	}
	
	function getChanges(){
		var rows = $('#dg').datagrid('getChanges');
		alert(rows.length+' rows are changed!');
	}
    
	function optSubmit(){
	    $('#fm').form('submit',{
	        url: "groupongoods/updateGrouponGoods",
	        onSubmit: function(){
		       	 var villageId = "";
		       	 $("#s2 option").each(function(){
		               villageId+=$(this).val()+",";
		           });
		       	 if (villageId.length > 0) {
		       		 villageId = villageId.substr(0, villageId.length - 1);
		       	 }
		       	$("#villageId").val(villageId);
		       	
		       	$("#dg").datagrid('endEdit', editIndex);
		       	var rows = $("#dg").datagrid('getData');
		       	var rowstr = JSON.stringify(rows);
		       	$("#extendInfo").val(rowstr);
		       	
		       	$("#picOne").val($("#pic0").val());
		       	$("#picTow").val($("#pic1").val());
		       	$("#picThree").val($("#pic2").val());
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
$(function(){
	var ue = UE.getEditor('editor');
    var ueditorBody ='${details}';
    //判断ueditor 编辑器是否创建成功
    if(ueditorBody != null && ueditorBody != undefined && ueditorBody != ""){
        ue.addListener("ready", function () {
            // editor准备好之后才可以使用
            ue.setContent(ueditorBody);
        });
    }
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
		$("#picture"+(i+1)).attr("src",item.url);
	});
	
	$.ajax({
	  type: 'POST',
	  url: "common/initCity",
	  data:"",
	  dataType: 'json',
	  success: function(data){
		  var html = '';
		  $.each(data, function(i, item) {
		     html += "<option value='"+item.cityId+"'>"+item.cityName+"</option>";
		  });
		  $("#s01").append(html);
	  }
	});
});
//动态改变s1的值
function gaibianS1() { 
	var cityId = $("#s01").val();
	$.ajax({
		type: "POST",
		url:"common/getVillageByCity/"+cityId,
		cache: false,
		dataType : "json",
		success: function(data){
			 var selObj = $("#s1"); 
			 var selOpt = $("#s1 option");
			 selOpt.remove();
			 for(var village in data){
				 if(data[village].villageId != 0){
					 selObj.append("<option value="+data[village].villageId+">"+data[village].villageName+"</option>");
				 }
			 }  
        }
   	}); 	
}
</script>
</html>