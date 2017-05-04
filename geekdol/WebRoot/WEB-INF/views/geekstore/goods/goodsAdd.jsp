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
            <label for="goodsName">商品名称:</label>
            <input id="goodsName" name="goodsName" class="easyui-textbox" required="true" style="width: 360px;">
        </div>
        <div class="fitem">
            <label for="goodsCode">商品编号:</label>
            <input id="goodsCode" name="goodsCode" class="easyui-textbox" data-options="required:true">
            <label for="shortTopic">短标题:&nbsp;&nbsp;&nbsp;</label>
            <input id="shortTopic" name="shortTopic" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label for="brand">品&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;牌:</label>
            <input id="brand" name="brand" class="easyui-textbox" required="true" style="width: 150px;">
            <label for="costPrice">成本价:&nbsp;&nbsp;&nbsp;</label>
            <input id="costPrice" name="costPrice" class="easyui-numberbox" precision="2" max="99999999999.99" required="true" style="width: 150px;">
        </div>
        <div class="fitem">
            <label for="originalPrice">原始价:&nbsp;&nbsp;&nbsp;</label>
            <input id="originalPrice" name="originalPrice" class="easyui-numberbox" precision="2" max="99999999999.99" required="true" style="width: 150px;">
            <label for="modelNumber">产品型号:</label>
            <input id="modelNumber" name="modelNumber" class="easyui-textbox" required="true" style="width: 150px;">
        </div>
        <div class="fitem">
        	<label for="launchDate">上市日期:</label>
            <input id="launchDate" name="launchDate" class="easyui-datetimebox" style="width: 150px;" required="required" editable="false">
            <label for="thumbnail">缩略图:&nbsp;&nbsp;&nbsp;</label>
            <input style="width: 149px;" type="text" class="easyui-filebox" id="thumbnail" name="file" required="required">
        </div>
        <div class="fitem">
        	<label>展示图1:&nbsp;</label>
        	<input style="width: 359px;" type="text" class="easyui-filebox" id="file1" name="file" required="required">
        </div>
        <div class="fitem">
        	<label>展示图2:&nbsp;</label>
        	<input style="width: 359px;" type="text" class="easyui-filebox" id="file2" name="file" required="required">
        </div>
        <div class="fitem">
        	<label>展示图3:&nbsp;</label>
        	<input style="width: 359px;" type="text" class="easyui-filebox" id="file3" name="file" required="required">
        </div>
        <div class="fitem">
        	<label>扩展属性:</label>
			<table id="dg" class="easyui-datagrid" style="height: 300px;" toolbar="#toolbar" 
				pagination="false" fitColumns="false" singleSelect="true"
				data-options="fit:false,onClickRow:onClickRow,border:true">
				<thead>
					<tr>
						<th field="extendName" width="100"  editor="textbox">属性名称</th>
						<th field="extendValue" width="100"  editor="textbox">属性值</th>
						<th field="extendRemark" width="300"  editor="textbox">描述</th>
					</tr>
				</thead>
			</table>
			<div id="toolbar">
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="append()">新增</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeit()">删除</a>
		   		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">Accept</a>
		    </div>
		</div>
       	<div class="fitem">
       		<label>详细描述:</label>
       		<script id="editor" name="details" type="text/plain" style="width:700px;height:500px;"></script>
       	</div>
       	<input type="hidden" id="extendInfo" name = "extendInfo">
    </form>
</body>
<script type="text/javascript">
	$('#thumbnail').filebox({
	    buttonText: '选择缩略图',
	    buttonAlign: 'right',
	    accept: 'image/*'
	});
	$('#file1').filebox({
	    buttonText: '选择展示图',
	    buttonAlign: 'right',
	    accept: 'image/*'
	});
	$('#file2').filebox({
	    buttonText: '选择展示图',
	    buttonAlign: 'right',
	    accept: 'image/*'
	});
	$('#file3').filebox({
	    buttonText: '选择展示图',
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
            /* alert(rowstr); */
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
        url: "goods/saveGoods",
        onSubmit: function(){
	       	$("#dg").datagrid('endEdit', editIndex);
	       	var rows = $("#dg").datagrid('getChanges');
	       	var rowstr = JSON.stringify(rows);
	       	$("#extendInfo").val(rowstr);
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
});
</script>
</html>