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
	<script type="text/javascript" src="${basePath}static/easyui/plugins/jquery.edatagrid.js"></script> 
</head>
<body>
   <div style="height: 70px; width: 100%;">
		<form id="ff" method="post">
			<div style="float: left; padding: 20px 0 0 20px;">
				<label for="goodsCode">商品编号:</label> 
				<input id="goodsCode" name="goodsCode" class="easyui-textbox" type="text">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<label for="goodsName">商品名称:</label> 
				<input id="goodsName" name="goodsName" class="easyui-textbox" type="text">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<label for="shortTopic">短标题:</label> 
				<input id="shortTopic" name="shortTopic" class="easyui-textbox" type="text">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;" >
				<label for="modelNumber">型号:</label> 
				<input id="modelNumber" class="easyui-textbox" name="modelNumber" type="text">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;" >
				<label for="status">状态:</label> 
				<input id="status" name="status" class="easyui-combobox" 
					data-options="valueField:'value',textField:'lable',url:'common/initSysData/${GOODSSTATUS }'" style="width: 100px;">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<input type="button" onclick="search()" value="查询" />
			</div>
		</form>
	</div> 
    <table id="dg" class="easyui-datagrid" style="height: 620px;"
            url="goods/findAll" 
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            fitColumns="true" singleSelect="false" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
            	<th field="ck" checkbox="true" onclick="onClickRow()"></th>
            	<th field="goodsCode" width="150" >商品编号</th>
                <th field="goodsName" width="150">商品名称</th>
                <th field="brand" width="150">品牌</th>
                <th field="shortTopic" width="250">短标题</th>
                <th field="launchDate" width="175" formatter="conversion">上市日期</th>
                <th field="modelNumber" width="100">产品型号</th>
                <th field="costPrice" width="150">成本价</th>
                <th field="insYmdhms" width="175" formatter="conversion">登记日期</th>
                <th field="statusStr" width="100">状态</th>
            </tr>
        </thead>
    </table>
    <shiro:hasPermission name="Goods:manager">
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toAdd()">新增</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-puhuo" plain="true" onclick="toDistribution()">铺货</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-storenotice" plain="true" onclick="toPublish()">发布</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reject" plain="true" onclick="toReject()">禁用</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-grouponComment" plain="true" onclick="grouponComment()">评论管理</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toEdits()">编辑</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="offGoods()">下架</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyGoods()">删除</a>
    </div>
    </shiro:hasPermission>
   	<div id="dlg" class="easyui-dialog" style="padding:10px 20px;width: 800px;height: 580px;"
           closed="true" buttons="#dlg-buttons" modal="true">
	  	<iframe id='addGoodsFormUI' border='0' vspace='0' hspace='0' 
	    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
	    	style="height:100%;width:100%;left:10px;top:8px" src="">
	    </iframe>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveSubmit()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    
    <div id="distributionGoodsDlg" class="easyui-dialog" style="padding:0px 0px;width: 1060px;height: 550px;"
           closed="true" buttons="#distributionGoodsDlg-buttons" modal="true">
	  	<iframe id='distributionGoodsFormUI' border='0' vspace='0' hspace='0' 
	    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
	    	style="height:100%;width:100%;left:10px;top:8px" src="">
	    </iframe>
    </div>
    <div id="distributionGoodsDlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveDistributionGoodsSubmit()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#distributionGoodsDlg').dialog('close')" style="width:90px">取消</a>
    </div>
    
    <div id="GrouponCommentDlg" class="easyui-dialog" style="padding:0px 0px;width: 1060px;height: 550px;"
           closed="true" modal="true">
	  	<iframe id='GrouponCommentListFormUI' border='0' vspace='0' hspace='0' 
	    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
	    	style="height:100%;width:100%;left:10px;top:8px" src="">
	    </iframe>
    </div>
    <!-- <div id="distributionGoodsDlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveSubmit()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#GrouponCommentDlg').dialog('close')" style="width:90px">取消</a>
    </div> -->
    
	<script type="text/javascript">
		function conversion(value){
	   		var time = new Date(value);
	   		return time.format("yyyy-MM-dd hh:mm:ss");
		};
	   var url;
	   function search(){
		   /* $('#ff').form('submit',{  
			    url: "goods/findAll",  
			    onSubmit: function(){  
			    	return $(this).form('validate'); 
			    },  
			    success:function(data){  
			    	var data = eval('('+data+')');
			       $('#dg').datagrid('loadData',data);
			    }
	   	   }); */
		   $('#dg').datagrid({ queryParams: form2Json("ff") });
	   }
	    /**新增*/
        function toAdd(){
        	$('#addGoodsFormUI').attr("src","goods/toAddGoods");
            $('#dlg').dialog('open').dialog('setTitle','新增');
            
            $('#fm').form('clear');
        }
        /**评论*/
        function grouponComment(){
        	var rows = $('#dg').datagrid('getSelections');
        	if(rows.length>1){
        		$.messager.show({    // show error message
                    title: 'Warning',
                    msg: '请选择一条数据查看!'
                });
        		return;
        	}
            var row = rows[0];
            if (row){
            	$('#GrouponCommentListFormUI').attr("src","goods/goodsCommment/"+row.id+"/"+row.goodsCode+"/"+row.goodsView+"/"+row.goodsName);
                $('#GrouponCommentDlg').dialog('open').dialog('setTitle','评论管理');
            }else{
            	$.messager.alert('提示信息','请先选择要查看的记录。','info');
            }
        }
        
        function toEdits(){
        	var rows = $('#dg').datagrid('getSelections');
        	if(rows.length>1){
        		$.messager.show({    // show error message
                    title: 'Warning',
                    msg: '请选择一条数据编辑!'
                });
        		return;
        	}
            var row = rows[0];
            if (row){
            	$('#addGoodsFormUI').attr("src","goods/toEditGoods/"+row.id);
                $('#dlg').dialog('open').dialog('setTitle','编辑');
            }else{
            	$.messager.alert('提示信息','请先选择要更新的记录。','info');
            }
        }
        
        function saveSubmit(){
	        /* 调用共通js中是否为IE的判定方法 */
	   		if(isIE()) {
	   			window.frames["addGoodsFormUI"].optSubmit();
	   		} else {
	   			window.frames["addGoodsFormUI"].contentWindow.optSubmit();
	   		}
        }
        function saveDistributionGoodsSubmit(){
	        /* 调用共通js中是否为IE的判定方法 */
	   		if(isIE()) {
	   			window.frames["distributionGoodsFormUI"].optSubmit();
	   		} else {
	   			window.frames["distributionGoodsFormUI"].contentWindow.optSubmit();
	   		}
        }
        /**禁用数据*/
        function toReject(){
        	var rows = $('#dg').datagrid('getSelections');
        	if(rows.length>1){
        		$.messager.show({    // show error message
                    title: 'Warning',
                    msg: '请选择一条数据禁用产品!'
                });
        		return;
        	}
            var row = rows[0];
            if (row){
                $.messager.confirm('提示','确定要禁用产品吗?',function(r){
                    if (r){
                        $.post('goods/rejectGoods',{id:row.id},function(result){
                            if (result.type=='Success'){
                            	$.messager.show({    // show error message
                                    title: 'Success',
                                    msg: result.Msg
                                });
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: 'Error',
                                    msg: result.Msg
                                });
                            }
                        },'json');
                    }
                });
            }else{
            	$.messager.alert('提示信息','请先选择要禁用产品。','info');
            }
        }
        /**铺货*/
        function toDistribution(){
           	$('#distributionGoodsFormUI').attr("src","goods/toDistributionGoods");
            $('#distributionGoodsDlg').dialog('open').dialog('setTitle','铺货');
        }
        /**发布产品*/
        function toPublish(){
        	var rows = $('#dg').datagrid('getSelections');
        	if(rows.length>1){
        		$.messager.show({    // show error message
                    title: 'Warning',
                    msg: '请选择一条数据发布产品!'
                });
        		return;
        	}
            var row = rows[0];
            if (row){
                $.messager.confirm('提示','确定要发布产品吗?',function(r){
                    if (r){
                        $.post('goods/publishGoods',{id:row.id},function(result){
                            if (result.type=='Success'){
                            	$.messager.show({    // show error message
                                    title: 'Success',
                                    msg: result.Msg
                                });
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: 'Error',
                                    msg: result.Msg
                                });
                            }
                        },'json');
                    }
                });
            }else{
            	$.messager.alert('提示信息','请先选择要发布产品。','info');
            }
        }
        /**商品下架*/
        function offGoods(){
        	var rows = $('#dg').datagrid('getSelections');
        	if(rows.length>1){
        		$.messager.show({    // show error message
                    title: 'Warning',
                    msg: '请选择一条数据删除!'
                });
        		return;
        	}
            var row = rows[0];
            if (row){
                $.messager.confirm('提示','确定要下架该商品吗?',function(r){
                    if (r){
                        $.post('goods/offGoods',{id:row.id},function(result){
                            if (result.type=='Success'){
                            	$.messager.show({    // show error message
                                    title: 'Success',
                                    msg: result.Msg
                                });
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: 'Error',
                                    msg: result.Msg
                                });
                            }
                        },'json');
                    }
                });
            }else{
            	$.messager.alert('提示信息','请先选择要下架的商品。','info');
            }
        }
        /**数据删除*/
        function destroyGoods(){
        	var rows = $('#dg').datagrid('getSelections');
        	if(rows.length>1){
        		$.messager.show({    // show error message
                    title: 'Warning',
                    msg: '请选择一条数据删除!'
                });
        		return;
        	}
            var row = rows[0];
            if (row){
                $.messager.confirm('提示','确定要删除吗?',function(r){
                    if (r){
                        $.post('goods/deleteGoods',{id:row.id,delFlag:'1'},function(result){
                            if (result.type=='Success'){
                            	$.messager.show({    // show error message
                                    title: 'Success',
                                    msg: result.Msg
                                });
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: 'Error',
                                    msg: result.Msg
                                });
                            }
                        },'json');
                    }
                });
            }else{
            	$.messager.alert('提示信息','请先选择要删除的记录。','info');
            }
        }
       /*弹出diago 子画面调用父画面的方法 */
        function close(){
        	$('#dlg').dialog('close');
        	$('#GrouponCommentDlg').dialog('close');
        	$('#distributionGoodsDlg').dialog('close');
        	$('#dg').datagrid('reload');
        }
    </script>
    <style type="text/css">
        #fm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
        .fitem input{
            width:160px;
        }
    </style>
	<script type="text/javascript">
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
</script>
</body>
</html>