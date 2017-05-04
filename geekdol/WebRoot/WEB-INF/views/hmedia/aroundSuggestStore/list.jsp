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
	<style type="text/css">
	.hiddenUse {
		background:none;
		border-radius:none;
		padding:0;
		cursor:default;
	}
	</style>
</head>
<body>
   <div style="height: 100px; width: 90%;">
		<form id="ff" method="post">
			<div style="float: left; padding: 20px 0 0 20px;">
				<div style="display:inline-flex;margin-right:10px">
					<div style="width:50px">
						<label for="name">店名:</label>
					</div>
					<input id="name" name="name" class="easyui-textbox" type="text">
				</div>
				<div style="display:inline-flex;margin-right:10px">
					<div style="width:50px">
						<label for="villageName">小区名:</label> 
					</div>
					<input id="villageName" name="villageName" class="easyui-textbox" type="text">
				</div>
				<div style="display:inline-flex;margin-right:10px">
					<div style="width:50px">
						<label for="useStatus">状态:</label>
					</div>
					<select id="suggestFlag" name="suggestFlag" class="easyui-combobox" editable="false" style="width: 100px;">
						<option value="">全部</option>
						<option value="1">${YTJ}</option>
						<option value="0">${YCX}</option>
					</select>
				</div>
			</div>
			<div style="float: left; padding: 20px 0 0 20px;" >
				<div style="display:inline-flex;margin-right:10px">
					<div style="width:50px">
						<label for="concat">联系人:</label> 
					</div>
					<input class="easyui-textbox" type="text" name="concat" id="concat">
				</div>
				<div style="display:inline-flex;margin-right:10px">
					<div style="width:50px">
						<label for="concatPhone">电话:</label> 
					</div>
					<input class="easyui-textbox" type="text" name="concatPhone" id="concatPhone">
				</div>
				<div style="display:inline-flex;margin-right:10px">
					<div style="width:50px">
						<label for="storeType">类型:</label>
					</div> 
					<input id="storeType" name="storeType" class="easyui-combobox" 
						data-options="editable:false,valueField:'value',textField:'lable',url:'common/initSysData/${STORETYPE}'" style="width: 100px;">
				</div>
				<input type="button" onclick="searchAroundSuggestStore()" value="查询" />
			</div>
		</form>
	</div> 
    <table id="dg" class="easyui-datagrid" style="height: 620px;"
    		url="aroundSuggestStore/findAll"
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            fitColumns="true" singleSelect="true" rownumbers="true"
            data-options="onClickRow: onClickRow,fit:false,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
            	<th field="name" width="50" >店名</th>
                <th field="villageName" width="50">推荐小区</th>
                <th field="suggestStart" width="50" formatter="conversion">推荐开始时间</th>
                <th field="suggestEnd" width="50" formatter="conversion">推荐结束时间</th>
                <th field="storeTypeName" width="50">类型</th>
                <th field="suggestFlag" width="50" formatter = "formatStatus">状态</th>
            </tr>
        </thead>
    </table>
    <shiro:hasPermission name="AroundSuggestS:manager">
	    <div id="toolbar">
	        <a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-add' plain='true' onclick='toAdd()'>新增</a>
			<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-edit' plain='true' onclick='toEdits()'>修改</a>
			<a href='javascript:void(0)' class='easyui-linkbutton' data-options="iconCls:'icon-remove',plain:true" onclick='deleteAroundSuggestStore()'>删除</a>
			<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-editstatus' plain='true' onclick='editstatus(0)'>撤销推荐</a>
			<a href='javascript:void(0)' class='easyui-linkbutton' data-options="iconCls:'icon-toSuggestAgain',plain:true" onclick='toSuggestAgain()'>再次推荐</a>
			<a href='javascript:void(0)' class='easyui-linkbutton' data-options="iconCls:'icon-toSales',plain:true" onclick='toSales()'>促销管理</a>
			<a href='javascript:void(0)' class='easyui-linkbutton' data-options="iconCls:'icon-tocomment',plain:true" onclick='toComment()'>回复管理</a>
	    </div>
    </shiro:hasPermission>
    <div id="dlg" class="easyui-dialog" style="padding:10px 40px;width: 800px;height: 580px;overflow: hidden;"
           closed="true" buttons="#dlg-buttons" modal="true">
	  	<iframe id='addAroundSuggestStoreFormUI' border='0' vspace='0' hspace='0' 
	    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
	    	style="height:100%;width:100%;left:10px;top:8px" src="">
	    </iframe>
    </div>
    <div id="dlg-buttons" style="width:100%;border:0">
        <a href="javascript:void(0)" id="saveBtn" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveSubmit()" style="width:90px;float:left;margin-left:36%;">保存</a>
        <a href="javascript:void(0)" id="cancelBtn" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px;float:left;margin-left:20px;">取消</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton hiddenUse" style="border:0"></a>
    </div>
    
	<script type="text/javascript">
		function formatStatus(val,row){
			if (val == "1") {
				return '<span>已推荐</span>';
			} else if (val == "0") {
				return "<span style='color:red'>已撤销</span>";
			} else {
				return '<span></span>';
			}
		}
		function conversion(value){
       		var time = new Date(value);
       		return time.format("yyyy-MM-dd hh:mm:ss");
   		}
		
	   var url;
	   function searchAroundSuggestStore(){
		   /* $('#ff').form('submit',{  
			    url: "aroundSuggestStore/findAll",  
			    onSubmit: function(){  
			    	return $(this).form('validate'); 
			    },  
			    success:function(data){  
			    	var data = eval('('+data+')');
			       $('#dg').datagrid('loadData',data);
			    }
			});   */
		   $('#dg').datagrid({ queryParams: form2Json("ff") });
	   }
	   
        function toAdd(){
        	document.getElementById("addAroundSuggestStoreFormUI").contentWindow.document.body.innerText = "";
        	$('#dlg-buttons').empty();
			$('#dlg-buttons').append("<a href='javascript:void(0)' id='saveBtn' class='easyui-linkbutton c6' iconCls='icon-ok' onclick='saveSubmit()' style='width:90px;float:left;margin-left:36%;'>保存</a>");
        	$('#dlg-buttons').append("<a href='javascript:void(0)' id='cancelBtn' class='easyui-linkbutton' iconCls='icon-cancel' onclick=\"javascript:$('#dlg').dialog('close')\" style='width:90px;float:left;margin-left:20px;'>取消</a>");
        	$('#dlg-buttons').append("<a href='javascript:void(0)' class='easyui-linkbutton hiddenUse' style='border:0'></a>");
        	$.parser.parse($('#dlg-buttons'));
        	$('#addAroundSuggestStoreFormUI').attr("src","aroundSuggestStore/toAddAroundSuggestStore");
            $('#dlg').dialog('open').dialog('setTitle','新增');
            $('#saveBtn').css('display','block');
            $('#cancelBtn').css('display','block');
            $('#fm').form('clear');
        }
        function toEdits(){
        	document.getElementById("addAroundSuggestStoreFormUI").contentWindow.document.body.innerText = "";
        	$('#dlg-buttons').empty();
			$('#dlg-buttons').append("<a href='javascript:void(0)' id='saveBtn' class='easyui-linkbutton c6' iconCls='icon-ok' onclick='saveSubmit()' style='width:90px;float:left;margin-left:36%;'>保存</a>");
        	$('#dlg-buttons').append("<a href='javascript:void(0)' id='cancelBtn' class='easyui-linkbutton' iconCls='icon-cancel' onclick=\"javascript:$('#dlg').dialog('close')\" style='width:90px;float:left;margin-left:20px;'>取消</a>");
        	$('#dlg-buttons').append("<a href='javascript:void(0)' class='easyui-linkbutton hiddenUse' style='border:0'></a>");
        	$.parser.parse($('#dlg-buttons'));
        	var row = $('#dg').datagrid('getSelected');
            if (row){
            	$('#addAroundSuggestStoreFormUI').attr("src","aroundSuggestStore/toEditorAroundSuggestStore/"+row.id);
                $('#dlg').dialog('open').dialog('setTitle','编辑');
                $('#saveBtn').css('display','block');
                $('#cancelBtn').css('display','block');
            }else{
            	$.messager.alert('提示信息','请先选择要更新的记录。','info');
            }
        }
        function saveSubmit(){
	        /* 调用共通js中是否为IE的判定方法 */
	   	   if(window.frames["addAroundSuggestStoreFormUI"].contentWindow==undefined){
	            window.frames["addAroundSuggestStoreFormUI"].optSubmit();
	       }else{
	            window.frames["addAroundSuggestStoreFormUI"].contentWindow.optSubmit();
	       }
        }
        function deleteAroundSuggestStore() {
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
                        $.post('aroundSuggestStore/deleteAroundSuggestStore',{id:row.id,delFlag:'1'},function(result){
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
        
        function editstatus(val){
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
            	var messgaeWarn = '确定要撤销推荐此店吗?';
            	if (val == 1) {
            		messgaeWarn = '确定要推荐此店吗?';
            	}
                $.messager.confirm('提示',messgaeWarn,function(r){
                    if (r){
                        $.post('aroundSuggestStore/updateAroundSuggestStoreStatus',{id:row.id,suggestFlag:val},function(result){
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
            	$.messager.alert('提示信息','请先选择要更改状态的记录。','info');
            }
        }
        
        function toSuggestAgain() {
        	document.getElementById("addAroundSuggestStoreFormUI").contentWindow.document.body.innerText = "";
        	$('#dlg-buttons').empty();
			$('#dlg-buttons').append("<a href='javascript:void(0)' id='saveBtn' class='easyui-linkbutton c6' iconCls='icon-ok' onclick='saveSubmit()' style='width:90px;float:left;margin-left:36%;'>保存</a>");
        	$('#dlg-buttons').append("<a href='javascript:void(0)' id='cancelBtn' class='easyui-linkbutton' iconCls='icon-cancel' onclick=\"javascript:$('#dlg').dialog('close')\" style='width:90px;float:left;margin-left:20px;'>取消</a>");
        	$('#dlg-buttons').append("<a href='javascript:void(0)' class='easyui-linkbutton hiddenUse' style='border:0'></a>");
        	$.parser.parse($('#dlg-buttons'));
        	var row = $('#dg').datagrid('getSelected');
            if (row){
            	$('#addAroundSuggestStoreFormUI').attr("src","aroundSuggestStore/toSuggest/"+row.id+"/0");
                $('#dlg').dialog('open').dialog('setTitle','再次推荐');
                $('#saveBtn').css('display','block');
                $('#cancelBtn').css('display','block');
            }else{
            	$.messager.alert('提示信息','请先选择要进行本网格推荐的记录。','info');
            }
        }
        
        function toCountySuggest() {
        	document.getElementById("addAroundSuggestStoreFormUI").contentWindow.document.body.innerText = "";
        	$('#dlg-buttons').empty();
			$('#dlg-buttons').append("<a href='javascript:void(0)' id='saveBtn' class='easyui-linkbutton c6' iconCls='icon-ok' onclick='saveSubmit()' style='width:90px;float:left;margin-left:36%;'>保存</a>");
        	$('#dlg-buttons').append("<a href='javascript:void(0)' id='cancelBtn' class='easyui-linkbutton' iconCls='icon-cancel' onclick=\"javascript:$('#dlg').dialog('close')\" style='width:90px;float:left;margin-left:20px;'>取消</a>");
        	$('#dlg-buttons').append("<a href='javascript:void(0)' class='easyui-linkbutton hiddenUse' style='border:0'></a>");
        	$.parser.parse($('#dlg-buttons'));
        	var row = $('#dg').datagrid('getSelected');
            if (row){
            	$.post('aroundSuggestStore/checkRecommend',{id:row.id},function(result){
                    if (result.type=='Success'){
                    	$('#addAroundSuggestStoreFormUI').attr("src","aroundSuggestStore/toSuggest/"+row.id+"/1");
                        $('#dlg').dialog('open').dialog('setTitle','本区推荐');
                        $('#saveBtn').css('display','block');
                        $('#cancelBtn').css('display','block');
                    } else {
                        $.messager.show({    // show error message
                            title: 'Error',
                            msg: result.Msg
                        });
                    }
                },'json');
            }else{
            	$.messager.alert('提示信息','请先选择要进行本区推荐的记录。','info');
            }
        }
        
        function toSales() {
        	document.getElementById("addAroundSuggestStoreFormUI").contentWindow.document.body.innerText = "";
        	$('#dlg-buttons').empty();
			$('#dlg-buttons').append("<a href='javascript:void(0)' id='saveBtn' class='easyui-linkbutton c6' iconCls='icon-ok' onclick='saveSubmit()' style='width:90px;float:left;margin-left:36%;'>保存</a>");
        	$('#dlg-buttons').append("<a href='javascript:void(0)' id='cancelBtn' class='easyui-linkbutton' iconCls='icon-cancel' onclick=\"javascript:$('#dlg').dialog('close')\" style='width:90px;float:left;margin-left:20px;'>取消</a>");
        	$('#dlg-buttons').append("<a href='javascript:void(0)' iconCls='' class='easyui-linkbutton hiddenUse' style='border:0'></a>");
        	$.parser.parse($('#dlg-buttons'));
        	var row = $('#dg').datagrid('getSelected');
            if (row){
            	$('#addAroundSuggestStoreFormUI').attr("src","aroundSuggestStore/toSales/"+row.id);
                $('#dlg').dialog('open').dialog('setTitle','促销管理');
                $('#saveBtn').css('display','none');
                $('#cancelBtn').css('display','none');
            }else{
            	$.messager.alert('提示信息','请先选择要进行促销管理的记录。','info');
            }
        }
        
        function toComment() {
        	document.getElementById("addAroundSuggestStoreFormUI").contentWindow.document.body.innerText = "";
        	$('#dlg-buttons').empty();
			$('#dlg-buttons').append("<a href='javascript:void(0)' id='saveBtn' class='easyui-linkbutton c6' iconCls='icon-ok' onclick='saveSubmit()' style='width:90px;float:left;margin-left:36%;'>保存</a>");
        	$('#dlg-buttons').append("<a href='javascript:void(0)' id='cancelBtn' class='easyui-linkbutton' iconCls='icon-cancel' onclick=\"javascript:$('#dlg').dialog('close')\" style='width:90px;float:left;margin-left:20px;'>取消</a>");
        	$('#dlg-buttons').append("<a href='javascript:void(0)' iconCls='' class='easyui-linkbutton hiddenUse' style='border:0'></a>");
        	var row = $('#dg').datagrid('getSelected');
            if (row){
            	$('#addAroundSuggestStoreFormUI').attr("src","aroundSuggestStore/toCommentASS/"+row.id);
                $('#dlg').dialog('open').dialog('setTitle','本网格推荐周边店回复管理');
                $('#saveBtn').css('display','none');
                $('#cancelBtn').css('display','none');
            }else{
            	$.messager.alert('提示信息','请先选择要进行回复管理的记录。','info');
            }
        }
    </script>
    <script type="text/javascript">
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
        var row = $('#dg').datagrid('getSelected');
        if (row){
        	$('#toolbar').empty();
	        if (row.suggestFlag == '1') {
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-add' plain='true' onclick='toAdd()'>新增</a>");
        		$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-edit' plain='true' onclick='toEdits()'>修改</a>");
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconCls:'icon-remove',plain:true\" onclick='deleteAroundSuggestStore()'>删除</a>");
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-editstatus' plain='true' onclick='editstatus(0)'>撤销推荐</a>");
        		$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconCls:'icon-toSuggestAgain',plain:true\" onclick='toSuggestAgain()'>再次推荐</a>");
        		$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconCls:'icon-tuijian',plain:true\" onclick='toCountySuggest()'>本区推荐</a>");
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconCls:'icon-toSales',plain:true\" onclick='toSales()'>促销管理</a>");
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconCls:'icon-tocomment',plain:true\" onclick='toComment()'>回复管理</a>");
	        	$.parser.parse($('#toolbar'));
	        } else {
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-add' plain='true' onclick='toAdd()'>新增</a>");
        		$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-edit' plain='true' onclick='toEdits()'>修改</a>");
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconCls:'icon-remove',plain:true\" onclick='deleteAroundSuggestStore()'>删除</a>");
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-tuijian' plain='true' onclick='editstatus(1)'>推荐</a>");
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconCls:'icon-toSuggestAgain',plain:true\" onclick='toSuggestAgain()'>再次推荐</a>");
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconCls:'icon-tuijian',plain:true\" onclick='toCountySuggest()'>本区推荐</a>");
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconCls:'icon-tocomment',plain:true\" onclick='toComment()'>回复管理</a>");
	        	$.parser.parse($('#toolbar'));
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
    
    /**绑定页面回车事件，以及初始化页面时的光标定位**/  
    $(function(){  
        bindFormComm("ff",searchAroundSuggestStore);
        }); 
    </script>
</body>
</html>