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
<body style="overflow: auto;">

   <div style="height: 70px; width: 100%;">
		<form id="ff" method="post">
			<div style="float: left; padding: 20px 0 0 20px;">
				<label for="storeName">店名:</label> 
				<input id="storeName" name="storeName" class="easyui-textbox" type="text">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<label for="villageName">小区名:</label> 
				<input id="villageName" name="villageName" class="easyui-textbox" type="text">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;" >
				<label for="status">状态:</label> 
				<input id="status" name="status" class="easyui-combobox" 
					data-options="valueField:'value',textField:'lable',url:'common/initSysData/${STOREPOENTYPE }'" style="width: 100px;">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;" >
				<label for="contcatName">联系人:</label> 
				<input class="easyui-textbox" type="text" name="contcatName" id="contcatName">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;" >
				<label for="contcatNumber">电话:</label> 
				<input class="easyui-textbox" type="text" name="contcatNumber" id="contcatNumber">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;" >
				<label for="email">地址:</label> 
				<input class="easyui-textbox" type="text" name="storeAdress" id="storeAdress">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;" >
				<label for="typeId">类型:</label> 
				<input id="typeId" name="typeId" class="easyui-combobox" 
					data-options="valueField:'value',textField:'lable',url:'common/initSysData/${STORETYPE }'" style="width: 100px;">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<input  type="button" onclick="searchCooperationStore()" value="查询" />
			</div>
		</form>
	</div> 
    <table id="dg" class="easyui-datagrid" style="height: 620px;"
            url="cooperationStore/findAll" 
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            fitColumns="true" singleSelect="false" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
            	<th field="ck" checkbox="true" onclick="onClickRow()"></th>
            	<th field="storeName" width="150" >店名</th>
                <th field="villageName" width="150">所在小区</th>
                <th field="contcatName" width="150">联系人</th>
                <th field="contcatNumber" width="150">电话</th>
                <th field="storeType" width="150">经营种类</th>
                <th field="insYmdhms" width="150" formatter="conversion">登记日期</th>
                <th field="openType" width="150">状态</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
    <shiro:hasPermission name="Cooperation:manager">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toAdd()">新增</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-changestatus" plain="true" onclick="editstatus()">变更状态</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toEdits()">编辑</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyCooperationStore()">删除</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-topushmessage" plain="true" onclick="toPushMessage()">推送消息</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-storenotice" plain="true" onclick="toAddStoreNotice()">发布公告</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cooperationStore" plain="true" onclick="viewCooperationStore()">合作店一览</a>
    </shiro:hasPermission>
    </div>
   	<div id="dlg" class="easyui-dialog" style="padding:10px 20px;width: 800px;height: 580px;"
           closed="true" buttons="#dlg-buttons" modal="true">
	  	<iframe id='addCooperationStoreFormUI' border='0' vspace='0' hspace='0' 
	    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
	    	style="height:100%;width:100%;left:10px;top:8px" src="">
	    </iframe>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveSubmit()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    
    <div id="cooperationStoreDlg" class="easyui-dialog" style="padding:0px 0px;width: 1060px;height: 500px;"
           closed="true" modal="true">
	  	<iframe id='cooperationStoreListFormUI' border='0' vspace='0' hspace='0' 
	    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
	    	style="height:100%;width:100%;left:10px;top:8px" src="">
	    </iframe>
    </div>
    
	<script type="text/javascript">
		function conversion(value){
	   		var time = new Date(value);
	   		return time.format("yyyy-MM-dd");
		};
		
	   var url;
	   function searchCooperationStore(){
		   /* $('#ff').form('submit',{  
			    url: "cooperationStore/findAll",  
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
	   
        function toAdd(){
        	$('#addCooperationStoreFormUI').attr("src","cooperationStore/toAddCooperationStore");
            $('#dlg').dialog('open').dialog('setTitle','新增');
            
            $('#fm').form('clear');
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
            	$('#addCooperationStoreFormUI').attr("src","cooperationStore/toEditorCooperationStore/"+row.id);
                $('#dlg').dialog('open').dialog('setTitle','编辑');
            }else{
            	$.messager.alert('提示信息','请先选择要更新的记录。','info');
            }
        }
        function saveSubmit(){
	        /* 调用共通js中是否为IE的判定方法 */
	   		if(isIE()) {
	   			window.frames["addCooperationStoreFormUI"].optSubmit();
	   		} else {
	   			window.frames["addCooperationStoreFormUI"].contentWindow.optSubmit();
	   		}
        }
        /* 编辑时，进行页面跳转的时候传入所选的row的id，然后把值传入到子画面，子画面根据该值来加载数据 */
        function editstatus(){
        	var rows = $('#dg').datagrid('getSelections');
        	if(rows.length>1){
        		$.messager.show({    // show error message
                    title: 'Warning',
                    msg: '请选择一条数据变更状态!'
                });
        		return;
        	}
            var row = rows[0];
            if (row){
            	$('#addCooperationStoreFormUI').attr("src","cooperationStore/toUpdateCooperationStoreStatus/"+row.id);
                $('#dlg').dialog('open').dialog('setTitle','变更状态');
            }else{
            	$.messager.alert('提示信息','请先选择要更新的记录。','info');
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
        function destroyCooperationStore(){
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
                        $.post('cooperationStore/deleteCooperationStore',{id:row.id,delFlag:'1'},function(result){
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
        	$('#cooperationStoreDlg').dialog('close');
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
	/**推送消息*/
	function toPushMessage(){
		var rows = $('#dg').datagrid('getSelections');
		var id = "",name="";
		$.each(rows, function (key, val) {
            id += val.id +",";
            name += val.storeName +",";
        });
		id = id.substr(0,id.length-1);
		name = name.substr(0,name.length-1);
		name = encodeURIComponent(encodeURIComponent(name));
		if (rows.length>0){
        	$('#addCooperationStoreFormUI').attr("src","cooperationStore/toPushMessage/"+id+"/"+name);
            $('#dlg').dialog('open').dialog('setTitle','推送消息');
        }else{
        	$.messager.alert('提示信息','请先选择要推送消息的合作店铺。','info');
        }
	}
	/**发布公告*/
	function toAddStoreNotice(){
		var rows = $('#dg').datagrid('getSelections');
    	if(rows.length>1){
    		$.messager.show({    // show error message
                title: 'Warning',
                msg: '请选择一条数据发布公告!'
            });
    		return;
    	}
        var row = rows[0];
        if (row){
        	$('#addCooperationStoreFormUI').attr("src","cooperationStore/toAddStoreNotice/"+row.id);
            $('#dlg').dialog('open').dialog('setTitle','发布公告');
        }else{
        	$.messager.alert('提示信息','请先选择要发布公告的合作店铺。','info');
        }
	}
	
	/**店铺预览*/
	function viewCooperationStore(){
		var rows = $('#dg').datagrid('getSelections');
    	if(rows.length>1){
    		$.messager.show({    // show error message
                title: 'Warning',
                msg: '请选择一条数据预览!'
            });
    		return;
    	}
        var row = rows[0];
        if (row){
	    	$('#cooperationStoreListFormUI').attr("src","cooperationStore/showCooperationStore/"+row.id);
	    	$("#cooperationStoreListFormUI").css({"width":"1050px" ,"height":"600px"});
	    	$('#cooperationStoreDlg').dialog('open').dialog('setTitle','合作店一览');
	        $('#cooperationStoreDlg').dialog('open').css({"width":"1060px" ,"height":"500px"});
	        $('#fm').form('clear');
        }
    }
</script>
</body>
</html>