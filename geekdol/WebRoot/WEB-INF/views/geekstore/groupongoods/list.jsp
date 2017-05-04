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
				<label for="serialCode">团购编号:</label> 
				<input id="serialCode" name="serialCode" class="easyui-textbox" type="text">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<label for="grouponName">团购名称:</label> 
				<input id="grouponName" name="grouponName" class="easyui-textbox" type="text">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<label for="subtitle">副标题:</label> 
				<input id="subtitle" name="subtitle" class="easyui-textbox" type="text">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;" >
				<label for="status">状态:</label> 
				<input id="status" name="status" class="easyui-combobox" 
					data-options="valueField:'value',textField:'lable',url:'common/initSysData/${GROUPONGOODSSTATUS }'" style="width: 100px;">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;" >
				<label for="startTime">开始日期:</label> 
				<input id="startTime" class="easyui-datetimebox" name="startTime" editable="false">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;" >
				<label for="endTime">结束日期:</label> 
				<input id="endTime" class="easyui-datetimebox" name="endTime" editable="false">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<input  type="button" onclick="search()" value="查询" />
			</div>
		</form>
	</div> 
    <table id="dg" class="easyui-datagrid" style="height: 620px;"
            url="groupongoods/findAll" 
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            fitColumns="true" singleSelect="false" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
            	<th field="ck" checkbox="true" onclick="onClickRow()"></th>
            	<th field="serialCode" width="150" >团购编号</th>
                <th field="grouponClassific" formatter="grouponClassific" width="80">团购分类</th>
                <th field="grouponName" width="150">团购名称</th>
                <th field="subtitle" width="250">副标题</th>
                <th field="startTime" width="175" formatter="conversion">开始日期</th>
                <th field="endTime" width="175" formatter="conversion">结束日期</th>
                <th field="maxQuantity" width="100">团购数量</th>
                <th field="grouponPrice" width="150">价格</th>
                <th field="statusStr" width="100">状态</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
    <shiro:hasPermission name="Groupon:manager">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toAdd()">新增</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-grouponComment" plain="true" onclick="grouponComment()">评论管理</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toEdits()">编辑</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyGroupongoods()">删除</a>
    </shiro:hasPermission>
    </div>
   	<div id="dlg" class="easyui-dialog" style="padding:10px 20px;width: 800px;height: 580px;"
           closed="true" buttons="#dlg-buttons" modal="true">
	  	<iframe id='addGrouponFormUI' border='0' vspace='0' hspace='0' 
	    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
	    	style="height:100%;width:100%;left:10px;top:8px" src="">
	    </iframe>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveSubmit()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    
    <div id="GrouponCommentDlg" class="easyui-dialog" style="padding:0px 0px;width: 1060px;height: 550px;"
           closed="true" modal="true">
	  	<iframe id='GrouponCommentListFormUI' border='0' vspace='0' hspace='0' 
	    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
	    	style="height:100%;width:100%;left:10px;top:8px" src="">
	    </iframe>
    </div>
    
	<script type="text/javascript">
		function conversion(value){
	   		var time = new Date(value);
	   		return time.format("yyyy-MM-dd hh:mm:ss");
		};
		function grouponClassific(value){
			if (value == "1"){
				return '<span>服务</span>';
			} else{
				return '<span>商品</span>';
			}
		}
		
	   var url;
	   function search(){
		   /* $('#ff').form('submit',{  
			    url: "groupongoods/findAll",  
			    onSubmit: function(){  
			    	return $(this).form('validate'); 
			    },  
			    success:function(data){  
			    	var data = eval('('+data+')');
			       $('#dg').datagrid('loadData',data);
			    }
	   	   });*/
		   $('#dg').datagrid({ queryParams: form2Json("ff") });
	   }
	   
        function toAdd(){
        	$('#addGrouponFormUI').attr("src","groupongoods/toAddGroupongoods");
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
            	$('#GrouponCommentListFormUI').attr("src","groupongoods/viewGrouponComment/"+row.id);
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
            	$('#addGrouponFormUI').attr("src","groupongoods/toEditGroupongoods/"+row.id);
                $('#dlg').dialog('open').dialog('setTitle','编辑');
            }else{
            	$.messager.alert('提示信息','请先选择要更新的记录。','info');
            }
        }
        
        function saveSubmit(){
	        /* 调用共通js中是否为IE的判定方法 */
	   		if(isIE()) {
	   			window.frames["addGrouponFormUI"].optSubmit();
	   		} else {
	   			window.frames["addGrouponFormUI"].contentWindow.optSubmit();
	   		}
        }
        function updateSubmit(){
            /* 调用共通js中是否为IE的判定方法 */
      		if(isIE()) {
      			window.frames["addAppUserFormUI"].optSubmit();
      		} else {
      			window.frames["addAppUserFormUI"].contentWindow.optSubmit();
      		}
        }
        /**数据删除*/
        function destroyGroupongoods(){
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
                        $.post('groupongoods/deleteGrouponGoods',{id:row.id,delFlag:'1'},function(result){
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