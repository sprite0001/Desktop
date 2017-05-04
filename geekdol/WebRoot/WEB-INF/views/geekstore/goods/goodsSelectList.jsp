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
				<input type="button" onclick="search()" value="查询" />
			</div>
		</form>
	</div> 
    <table id="dg" class="easyui-datagrid" style="height: 620px;"
            url="goods/findSelectGoods" pagination="true" queryParams="form2Json('ff')"
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
	<script type="text/javascript">
		function conversion(value){
	   		var time = new Date(value);
	   		return time.format("yyyy-MM-dd hh:mm:ss");
		};
	   var url;
	   function search(){
		  /*  $('#ff').form('submit',{  
			    url: "goods/findSelectGoods",  
			    onSubmit: function(){  
			    	return $(this).form('validate'); 
			    },  
			    success:function(data){  
			    	var data = eval('('+data+')');
			       $('#dg').datagrid('loadData',data);
			    }
	   	   });  */ 
		   $('#dg').datagrid({ queryParams: form2Json("ff") });
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
	function optSubmit(){
		var rows = $('#dg').datagrid('getSelections');
       	if(rows.length<=0){
       		$.messager.show({
                title: 'Warming',
                msg: "请确认选择商品",
                timeout:1000,
            	showType:'slide'
            });
       	}else{
	       	window.parent.close(rows);
       	}
	}
</script>
</body>
</html>