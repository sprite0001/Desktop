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
            <label for="titlePicture">标题图片:</label>
            <input style="width: 350px;" type="text" class="easyui-filebox" id="titlePicture" name="file" style="width: 360px;">
        </div>
        <div class="fitem">
            <label for="subTite">副标题:&nbsp;&nbsp;&nbsp;</label>
            <input id="subTite" name="subTite" class="easyui-textbox" required="required">
            <label for="ordering">显示顺序:</label>
            <input id="ordering" name="ordering" class="easyui-numberbox" data-options="required:true">
        </div>
        <div class="fitem">
            <label for="preferentialPrice">优惠价:&nbsp;&nbsp;&nbsp;</label>
            <input id="preferentialPrice" name="preferentialPrice" class="easyui-numberbox" precision="2" max="99999999999.99" required="required">
            <label for="advertisement">广告语:&nbsp;&nbsp;&nbsp;</label>
            <input id="advertisement" name="advertisement" class="easyui-textbox" required="required" style="width: 150px;">
        </div>
        <div class="fitem">
            <label for="startTime">开始日期:</label>
            <input id="startTime" name="startTime" class="easyui-datetimebox" required="required" style="width: 150px;" editable="false">
            <label for="endTime">结束日期:</label>
            <input id="endTime" name="endTime" class="easyui-datetimebox" required="required" style="width: 150px;" editable="false">
        </div>
       	<input type="hidden" id="goodsId" name = "goodsId" value="${goodsId }">
       	<input type="hidden" id="villageId" name = "villageId">
    </form>
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
</body>
<script type="text/javascript">
	$('#titlePicture').filebox({
	    buttonText: '选择标题图片',
	    buttonAlign: 'right',
	    accept: 'image/*'
	});
    /* 行编辑时所用的方法 */
    var editIndex = undefined;
	function endEditing(){
		if (editIndex == undefined){return true}
		if ($('#dg').datagrid('validateRow', editIndex)){
			/* var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:'productid'});
			var productname = $(ed.target).combobox('getText');
			$('#dg').datagrid('getRows')[editIndex]['productname'] = productname; */
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
			$('#dg').datagrid('appendRow',{status:'P'});
			editIndex = $('#dg').datagrid('getRows').length-1;
			$('#dg').datagrid('selectRow', editIndex)
					.datagrid('beginEdit', editIndex);
		}
	}
	function removeit(){
		if (editIndex == undefined){return}
		$('#dg').datagrid('cancelEdit', editIndex)
				.datagrid('deleteRow', editIndex);
		editIndex = undefined;
	}
	function accept(){
		if (endEditing()){
			/* $('#dg').datagrid('acceptChanges'); */
			$("#dg").datagrid('endEdit', editIndex);
			var rows = $("#dg").datagrid('getChanges');
            var rowstr = JSON.stringify(rows);
            alert(rowstr);
		}
	}
	function reject(){
		$('#dg').datagrid('rejectChanges');
		editIndex = undefined;
	}
	function getChanges(){
		var rows = $('#dg').datagrid('getChanges');
		alert(rows.length+' rows are changed!');
	}
    
	function optSubmit(){
	    $('#fm').form('submit',{
	        url: "immediately/saveRecommentGoods",
	        onSubmit: function(){
	        	var villageId = "";
		       	 $("#s2 option").each(function(){
		               villageId+=$(this).val()+",";
		           });
		       	 if (villageId.length > 0) {
		       		 villageId = villageId.substr(0, villageId.length - 1);
		       	 }
		       	$("#villageId").val(villageId);
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
		 gaibianS1();
	});
	//动态改变s1的值
	/* 铺货、推荐的时候，获取有效(登录人管辖并且开通合作店)的小区 */
	function gaibianS1() { 
		$.ajax({
			type: "POST",
			url:"common/queryVillageHasCooperationStore",
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
</script>
</html>