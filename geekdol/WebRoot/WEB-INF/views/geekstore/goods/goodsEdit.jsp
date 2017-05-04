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
            <input id="goodsName" name="goodsName" class="easyui-textbox" required="required" style="width: 360px;">
        </div>
        <div class="fitem">
            <label for="goodsCode">商品编号:</label>
            <input id="goodsCode" name="goodsCode" class="easyui-textbox" data-options="required:true">
            <label for="shortTopic">短标题:&nbsp;&nbsp;&nbsp;</label>
            <input id="shortTopic" name="shortTopic" class="easyui-textbox" required="required">
        </div>
        <div class="fitem">
            <label for="brand">品&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;牌:</label>
            <input id="brand" name="brand" class="easyui-textbox" required="required" style="width: 150px;">
            <label for="costPrice">成本价:&nbsp;&nbsp;&nbsp;</label>
            <input id="costPrice" name="costPrice" class="easyui-numberbox" precision="2" max="99999999999.99" required="true" style="width: 150px;">
        </div>
        <div class="fitem">
            <label for="originalPrice">原始价:&nbsp;&nbsp;&nbsp;</label>
            <input id="originalPrice" name="originalPrice" class="easyui-numberbox" precision="2" max="99999999999.99" required="true" style="width: 150px;">
            <label for="modelNumber">产品型号:</label>
            <input id="modelNumber" name="modelNumber" class="easyui-textbox" required="required" style="width: 150px;">
        </div>
        <div class="fitem">
        	<label for="launchDateStr">上市日期:</label>
            <input id="launchDateStr" name="launchDateStr" class="easyui-datetimebox" style="width: 150px;" required="required" editable="false">
       	</div>
       	<div class="fitem">
            <label for="thumbnailfile">缩略图:</label>
            <input type="hidden" name="thumbnail" id="thumbnail">
            <input style="width: 350px;" type="text" class="easyui-filebox" id="thumbnailfile" name="file">
            <img id="thumbnailImg" style="width: 60px;height: 60px;cursor: pointer;" onclick="showPic(this)">
        </div>
        <div class="fitem">
        	<label>展示图1:</label>
        	<input type="hidden" name="picOne" id="picOne">
        	<input style="width: 350px;" type="text" class="easyui-filebox" id="file1" name="file">
        	<img id="picture1" style="width: 60px;height: 60px;cursor: pointer;" onclick="showPic(this)">
        </div>
        <div class="fitem">
        	<label>展示图2:</label>
        	<input type="hidden" name="picTow" id="picTow">
        	<input style="width: 350px;" type="text" class="easyui-filebox" id="file2" name="file">
        	<img id="picture2" style="width: 60px;height: 60px;cursor: pointer;" onclick="showPic(this)">
        </div>
        <div class="fitem">
        	<label>展示图3:</label>
        	<input type="hidden" name="picThree" id="picThree">
        	<input style="width: 350px;" type="text" class="easyui-filebox" id="file3" name="file">
        	<img id="picture3" style="width: 60px;height: 60px;cursor: pointer;" onclick="showPic(this)">
        </div>
        <div class="fitem">
        	<label>扩展属性:</label>
			<table id="dg" class="easyui-datagrid" style="height: 300px;" 
				url="goods/findGoodsExtendInfo/${id }" toolbar="#toolbar" pagination="false" 
				fitColumns="false" singleSelect="true" data-options="fit:false,onClickRow:onClickRow,border:true">
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
       	<input type="hidden" id="id" name = "id" value="${id }">
       	<input type="hidden" id="extendInfo" name = "extendInfo">
    </form>
    <c:forEach items="${attachs}" var="item" varStatus="i">
     	<input type="hidden" name="pic${i.index }" id="pic${i.index }" value="${item.url }">
    </c:forEach>
</body>
<script type="text/javascript">
	$('#thumbnailfile').filebox({
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
	var id = $('#id').val();
	$('#fm').form({onLoadSuccess:loadsucc}); 
	$('#fm').form('load','goods/findGoods/'+id);
	//回调函数
	function loadsucc(data) {  
		$("#thumbnailImg").attr("src",data.thumbnail);
	}  
	//点击打开大图
	function showPic(obj) {  
		window.open(obj.src);
	}
	/**附件展示*/
	var attachs = eval('('+'${attachs}'+')');
	$.each(attachs, function(i, item) {
		$("#picture"+(i+1)).attr("src",item.url);
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
	        url: "goods/updateGoods",
	        onSubmit: function(){
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
	});
</script>
</html>