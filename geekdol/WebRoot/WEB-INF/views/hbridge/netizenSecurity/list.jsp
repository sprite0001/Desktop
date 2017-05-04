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
						<label for="title">标题:</label>
					</div>
					<input id="title" name="title" class="easyui-textbox" type="text">
				</div>
				<div style="display:inline-flex;margin-right:10px">
					<div style="width:90px">
						<label for="startTime">网安报时间从:</label> 
					</div>
					<input id="startTime" name="startTime" class="easyui-datebox" style="width: 140px;" editable="false">
					<div style="margin-left: 6px;width: 28px;">
						<label for="endTime">到:</label> 
					</div>
					<input id="endTime" name="endTime" class="easyui-datebox" style="width: 140px;" editable="false">
				</div>
				<div style="display:inline-flex;margin-right:10px">
					<div style="width:50px">
						<label for="publishStatus">状态:</label>
					</div>
					<select id="publishStatus" name="publishStatus" class="easyui-combobox" editable="false" style="width: 80px;" editable="false">
						<option value="">全部</option>
						<option value="1">${WYTYFB}</option>
						<option value="0">${WYTWFB}</option>
					</select>
				</div>
				<input type="button" onclick="searchNetizenSecurity()" value="查询" />
			</div>
		</form>
	</div> 
    <table id="dg" class="easyui-datagrid" style="height: 620px;"
    		url="netizenSecurity/findAll"
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            fitColumns="true" singleSelect="true" rownumbers="true"
            data-options="onClickRow: onClickRow,fit:false,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
            	<th field="title" width="50" >标题</th>
                <!-- <th field="summary" width="100">摘要</th> -->
                <th field="newsTime" width="80" formatter="conversion">网安报时间</th>
                <th field="publishStatus" width="50" formatter = "formatStatus">发布状态</th>
                <th field="publishTime" width="80" formatter="conversion">发布时间</th>
                <th field="viewsNumber" width="50">浏览量</th>
            </tr>
        </thead>
    </table>
    <shiro:hasPermission name="NetizenSecurity:manager">
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toAdd()">新增</a>
    </div>
    </shiro:hasPermission>
    <div id="dlg" class="easyui-dialog" style="padding:10px 20px;width: 800px;height: 540px;overflow:hidden;"
           closed="true" buttons="#dlg-buttons" modal="true">
	  	<iframe id='addNetizenSecurityFormUI' border='0' vspace='0' hspace='0' 
	    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
	    	style="height:100%;width:100%;left:10px;top:8px;" src="">
	    </iframe>
    </div>
    <div id="dlg-buttons" style="width:100%;border:0">
        <a href="javascript:void(0)" id="saveBtn" class="easyui-linkbutton c6" iconCls="icon-save" onclick="saveSubmit(0)" style="width:90px;">保存</a>
        <a href="javascript:void(0)" id="publishBtn" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveSubmit(1)" style="width:90px;">发布</a>
        <a href="javascript:void(0)" id="cancelBtn" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px;">关闭</a>
        <a href="javascript:void(0)" class="easyui-linkbutton hiddenUse" style="border:0"></a>
    </div>
    
	<script type="text/javascript">
		function conversion(value){
			if (value == null || value == '' ) {
				return "";
			} else {
				var time = new Date(value);  
		        return time.format("yyyy-MM-dd hh:mm:ss");
			}
	   	}
		
		function formatStatus(val,row){
			if (val == "0") {
				return '<span style=\'color:red;\'>未发布</span>';
			} else if (val == "1") {
				return "<span>发布</span>";
			} else {
				return '<span></span>';
			}
		}
		
	   var url;
	   function searchNetizenSecurity(){
		   var startTime = $('#startTime').datetimebox('getValue');
		   var endTime = $('#endTime').datetimebox('getValue');
		   if (startTime != null && startTime != '' && endTime != null && endTime != '') {
			   startTime = startTime.replace(/-/g,"/");
			   endTime = endTime.replace(/-/g,"/");
			   var oDate1 = new Date(startTime);
			   var oDate2 = new Date(endTime);
			   if(oDate1.getTime() > oDate2.getTime()){
			   		$.messager.alert('提示信息','开始时间应小于结束时间。','info');
			   		return;
			   }
		   }
	       $('#dg').datagrid({ queryParams: form2Json("ff") });
	   }
	   
        function toAdd(){
        	document.getElementById("addNetizenSecurityFormUI").contentWindow.document.body.innerText = "";
        	$('#addNetizenSecurityFormUI').attr("src","netizenSecurity/toAddNetizenSecurity");
            $('#dlg').dialog('open').dialog('setTitle','新增');
            $('#saveBtn').show();
            $('#publishBtn').show();
        }
        function toEdits(){
        	document.getElementById("addNetizenSecurityFormUI").contentWindow.document.body.innerText = "";
        	var row = $('#dg').datagrid('getSelected');
            if (row){
            	$('#addNetizenSecurityFormUI').attr("src","netizenSecurity/toEditorNetizenSecurity/"+row.id);
                $('#saveBtn').show();
                $('#publishBtn').show();
                $('#dlg').dialog('open').dialog('setTitle','编辑');
            }else{
            	$.messager.alert('提示信息','请先选择要更新的记录。','info');
            }
        }
        function saveSubmit(val){
	        /* 调用共通js中是否为IE的判定方法 */
	   	   if(window.frames["addNetizenSecurityFormUI"].contentWindow==undefined){
	            window.frames["addNetizenSecurityFormUI"].optSubmit(val);
	       }else{
	            window.frames["addNetizenSecurityFormUI"].contentWindow.optSubmit(val);
	       }
        }
        function deleteNetizenSecurity() {
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
                        $.post('netizenSecurity/deleteNetizenSecurity',{id:row.id,delFlag:'1'},function(result){
                            if (result.type=='Success'){
                            	$.messager.show({    // show error message
                                    title: 'Success',
                                    msg: result.msg
                                });
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: 'Error',
                                    msg: result.msg
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
            	var messgaeWarn = '确定要取消发布此网安报吗?';
            	if (val == 1) {
            		messgaeWarn = '确定要发布此网安报吗?';
            	}
                $.messager.confirm('提示',messgaeWarn,function(r){
                    if (r){
                        $.post('netizenSecurity/updateNetizenSecurityStatus',{id:row.id,publishStatus:val},function(result){
                            if (result.type=='Success'){
                            	$.messager.show({    // show error message
                                    title: 'Success',
                                    msg: result.msg
                                });
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: 'Error',
                                    msg: result.msg
                                });
                            }
                        },'json');
                    }
                });
            }else{
            	$.messager.alert('提示信息','请先选择要更改状态的记录。','info');
            }
        }
        
        function toView() {
        	document.getElementById("addNetizenSecurityFormUI").contentWindow.document.body.innerText = "";
        	var row = $('#dg').datagrid('getSelected');
            if (row){
            	$('#addNetizenSecurityFormUI').attr("src","netizenSecurity/toViewNetizenSecurity/"+row.id);
                $('#dlg').dialog('open').dialog('setTitle','详情');
                $('#saveBtn').hide();
                $('#publishBtn').hide();
                $('#cancelBtn').show();
            }else{
            	$.messager.alert('提示信息','请先选择要查看的记录。','info');
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
        	<shiro:hasPermission name="NetizenSecurity:view">
	        if (row.publishStatus == '0') {
	        	<shiro:hasPermission name="NetizenSecurity:manager">
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-add' plain='true' onclick='toAdd()'>新增</a>");
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-edit' plain='true' onclick='toEdits()'>修改</a>");
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconCls:'icon-remove',plain:true\" onclick='deleteNetizenSecurity()'>删除</a>");
	        	</shiro:hasPermission>
	        	<shiro:hasPermission name="NetizenSecurity:publish">
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-storenotice' plain='true' onclick='editstatus(1)'>发布</a>");
	        	</shiro:hasPermission>
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-view' plain='true' onclick='toView()'>查看详情</a>");
	        	$.parser.parse($('#toolbar'));
	        } else {
	        	<shiro:hasPermission name="NetizenSecurity:manager">
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-add' plain='true' onclick='toAdd()'>新增</a>");
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconCls:'icon-remove',plain:true\" onclick='deleteNetizenSecurity()'>删除</a>");
	        	</shiro:hasPermission>
	        	<shiro:hasPermission name="NetizenSecurity:publish">
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-quxiaofabu' plain='true' onclick='editstatus(0)'>取消发布</a>");
	        	</shiro:hasPermission>
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-view' plain='true' onclick='toView()'>查看详情</a>");
	        	$.parser.parse($('#toolbar'));
	        }
	        </shiro:hasPermission>
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
    bindFormComm("ff",searchNetizenSecurity);  
    }); 
    </script>
</body>
</html>