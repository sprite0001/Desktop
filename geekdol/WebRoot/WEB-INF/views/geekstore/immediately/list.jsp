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
			<div style="float: left; padding: 20px 0 0 20px;" >
				<label for="modelNumber">型号:</label> 
				<input id="modelNumber" class="easyui-textbox" name="modelNumber" type="text">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;" >
				<label for="status">状态:</label> 
				<select id="status" name="status" class="easyui-combobox" style="width: 100px;">
					<option value="">全部</option>
					<option value="${GOODSSTATUS_03 }">销售中</option>
					<option value="${GOODSSTATUS_04 }">已下架</option>
				</select>
			</div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<input type="button" onclick="search()" value="查询" />
			</div>
		</form>
	</div> 
    <table id="dg" class="easyui-datagrid" style="height: 620px;"
            url="immediately/findAll" 
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            fitColumns="true" singleSelect="false" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
            	<th field="ck" checkbox="true" onclick="onClickRow()"></th>
            	<th field="goodsCode" width="150" >商品编号</th>
                <th field="goodsName" width="150">商品名称</th>
                <th field="brand" width="150">品牌</th>
                <th field="modelNumber" width="100">产品型号</th>
                <th field="costPrice" width="150">成本价</th>
                <th field="originalPrice" width="150">原始价</th>
                <th field="sellPrice" width="150">销售价</th>
                <!-- <th field="launchDate" width="185" formatter="conversion">上市日期</th> -->
                <th field="startTime" width="185" formatter="conversion">起始日期</th>
                <th field="endTime" width="185" formatter="conversion">结束日期</th>
                <th field="statusStr" width="100">状态</th>
            </tr>
        </thead>
    </table>
    <shiro:hasPermission name="Immediately:manager">
    <div id="toolbar">
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="recomendGoods()">新产品推荐</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="offRecomendGoods()">新产品撤销</a>
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
	<script type="text/javascript">
		function conversion(value){
	   		var time = new Date(value);
	   		return time.format("yyyy-MM-dd hh:mm:ss");
		};
	   var url;
	   function search(){
		   $('#ff').form('submit',{  
			    url: "immediately/findAll",  
			    onSubmit: function(){  
			    	return $(this).form('validate'); 
			    },  
			    success:function(data){  
			    	var data = eval('('+data+')');
			       $('#dg').datagrid('loadData',data);
			    }
	   	   });  
	   }
        /**推荐*/
        function recomendGoods(){
        	var rows = $('#dg').datagrid('getSelections');
        	if(rows.length>1){
        		$.messager.show({    // show error message
                    title: 'Warning',
                    msg: '请选择一条数据推荐!'
                });
        		return;
        	}
            var row = rows[0];
            if (row){
            	console.log(row.id);
            	$('#addGoodsFormUI').attr("src","immediately/recomendGoods/"+row.goodsId);
                $('#dlg').dialog('open').dialog('setTitle','推荐');
            }else{
            	$.messager.alert('提示信息','请先选择要推荐的商品。','info');
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

        /**撤销商品*/
        function offRecomendGoods(){
        	var rows = $('#dg').datagrid('getSelections');
        	if(rows.length>1){
        		$.messager.show({    // show error message
                    title: 'Warning',
                    msg: '请选择一条商品撤销!'
                });
        		return;
        	}
            var row = rows[0];
            if (row){
                $.messager.confirm('提示','确定要撤销该商品吗?',function(r){
                    if (r){
                        $.post('immediately/offRecomendGoods',{goodsId:row.goodsId},function(result){
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
            	$.messager.alert('提示信息','请先选择要撤销的商品。','info');
            }
        }
       /*弹出diago 子画面调用父画面的方法 */
        function close(){
        	$('#dlg').dialog('close');
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