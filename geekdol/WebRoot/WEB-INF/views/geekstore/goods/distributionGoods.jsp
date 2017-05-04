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
	<style type="text/css">
		.s1{width:120px;}
		#d1{width:510px;height:320px;background-color:#F5DEB3;margin:0 auto;}
		#d2{height:30px;font-size:24px;background-color:blue;color:white;}
		#d3{padding-left:30px;}
	</style>
</head>
<body>
	<div class="ftitle"></div>
    <form id="fm" method="post">
        <div class="fitem">
            <label for="classificId">铺货模块:</label>
            <c:forEach items="${goodsClassfic}" var="item">
	             <input id="classificId" name="classificId" type="radio" value="${item.id }">${item.name }
	        </c:forEach>
        </div>
        <div class="fitem">
        	<label>销售价格:</label>
			<table id="dg" class="easyui-datagrid" style="height: 300px;width: 50%;" toolbar="#toolbar" 
				pagination="false" fitColumns="false" singleSelect="true"
				data-options="fit:false,onClickRow:onClickRow,border:true">
				<thead>
					<tr>
						<!-- <th field="id" width="100">商品id</th> -->
						<th field="goodsCode" width="151">商品编号</th>
						<th field="sellPrice" width="120" editor="textbox">销售价格(元)</th>
						<!-- <th field="classificId" width="100">分类Id</th> -->
						<th field="classificName" width="232">分类</th>
					</tr>
				</thead>
			</table>
			<div id="toolbar">
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="goodsSelect()">商品选择</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeit()">删除</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="reject()">撤销</a>
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">Accept</a>
		    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="goodsClasific()">选择分类</a>
		    </div>
		</div>
       	<div class="fitem">
       		<label for="startTime">上架时间:</label>
	        <input id="startTime" name="startTime" class="easyui-datetimebox" style="width: 150px;" required="required" editable="false">
       		<label for="endTime">至</label>
	        <input id="endTime" name="endTime" class="easyui-datetimebox" style="width: 150px;" required="required" editable="false">
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
       	<input type="hidden" id="id" name = "id" value="${id }">
       	<input type="hidden" id="villageId" name = "villageId">
       	<input type="hidden" id="goodsInfo" name = "goodsInfo">
    </form>
    <div id="GoodsDlg" class="easyui-dialog" style="padding:0px 0px;width: 1000px;height: 500px;"
           closed="true" buttons="#GoodsDlg-buttons" modal="true">
	  	<iframe id='GoodsListFormUI' border='0' vspace='0' hspace='0' 
	    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
	    	style="height:100%;width:100%;left:10px;top:8px" src="">
	    </iframe>
    </div>
    <div id="GoodsDlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="makeSure()" style="width:90px">确定</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#GoodsDlg').dialog('close')" style="width:90px">取消</a>
    </div>
</body>
<script type="text/javascript">
	/*弹出diago 子画面调用父画面的方法 */
	function close(data){
		var allData = $('#dg').datagrid('getData').rows;
		var result;
		if(allData.length<=0){
			// 第一次选择商品
			result = data;
		}else{
			// 添加商品
			result = allData;
			var selectGoodsId = "";
			$.each(allData,function(index,selectItem){
				selectGoodsId += selectItem.id+",";
			})
			$.each(data,function(index,item){
				if(selectGoodsId.indexOf(item.id)<=0){
					result.push(item);
				}
			});
		}
		$('#dg').datagrid('loadData', result);
		$('#GoodsDlg').dialog('close');
	}
	function closeClassific(data){
		var allData = $('#dg').datagrid('getData').rows;
		var firstData = data[0];
		$.each(allData,function(index,item){
			if(firstData.id == item.id){
				item.classificId = firstData.classificId;
				item.classificName = firstData.classificName;
			}
		})
		$('#dg').datagrid('loadData', allData);
		$('#GoodsDlg').dialog('close');
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
	/**分类选择*/
	function goodsClasific(){
		accept();
		var row = $('#dg').datagrid('getSelected');
		if(row){
			$('#GoodsListFormUI').attr("src","goods/goodsclassificMain/"+row.id);
	        $('#GoodsDlg').dialog('open').dialog('setTitle','商品分类选择');
		}else{
			$.messager.show({
                title: 'Warning',
                msg: "请选择商品进行分类",
                timeout:1000,
            	showType:'slide'
            });
		}
	}
	
	function getChanges(){
		var rows = $('#dg').datagrid('getChanges');
		alert(rows.length+' rows are changed!');
	}
    /**商品选择*/
	function goodsSelect(){
       	$('#GoodsListFormUI').attr("src","goods/toSelectGoods");
        $('#GoodsDlg').dialog('open').dialog('setTitle','商品选择');
	}
	
	function optSubmit(){
	    $('#fm').form('submit',{
	        url: "goods/saveDistributionGoods",
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
		       	$("#goodsInfo").val(rowstr);
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
	function makeSure(){
        /* 调用共通js中是否为IE的判定方法 */
   		if(isIE()) {
   			window.frames["GoodsListFormUI"].optSubmit();
   		} else {
   			window.frames["GoodsListFormUI"].contentWindow.optSubmit();
   		}
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