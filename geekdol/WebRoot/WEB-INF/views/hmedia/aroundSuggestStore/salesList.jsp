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

   <div style="height: 100px; width: 90%;">
   		<input type="hidden" id="aroundSuggestStoreId" name="aroundSuggestStoreId" value="${aroundSuggestStoreId}" />
   		<div style="float: left; padding: 20px 0 0 20px;" >
   			<div class="div_fitem_1">
				<label>店名:</label> 
				${aroundSuggestStorevo.name}
			</div>
	        <div class="div_fitem_1">
				<label>所属小区:</label> 
				${aroundSuggestStorevo.villageName}
			</div>
	        <div class="div_fitem_1">
				<label>联系人:</label> 
				${aroundSuggestStorevo.concat}
			</div>
	        <div class="div_fitem_1">
				<label>联系电话:</label> 
				${aroundSuggestStorevo.concatPhone}
			</div>
		</div>
	</div> 
    <table id="dg" class="easyui-datagrid"
    		url="aroundSuggestStore/salesFindAll?aroundSuggestStoreId=${aroundSuggestStoreId}"
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            fitColumns="true" singleSelect="true" rownumbers="true"
            data-options="onClickRow: onClickRow,fit:false,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
            	<th field="startTime" width="50" formatter = "conversion">促销开始时间</th>
                <th field="endTime" width="50" formatter = "conversion">促销结束时间</th>
                <th field="promotionInfo" width="150">促销信息</th>
                <th field="applicationTime" width="50" formatter = "conversion">申请时间</th>
                <th field="publishStatus" width="50" formatter = "formatStatus">发布状态</th>
                <th field="publishTime" width="50" formatter = "conversion">发布时间</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
    <%-- <shiro:hasPermission name="StaffUser:manager"> --%>
    	<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-add' plain='true' onclick='toAdd()'>新增</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deletePromotion()">删除</a>
    	<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-edit' plain='true' onclick='toEdits()'>修改</a>
    	<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-storenotice' plain='true' onclick='editstatus(1)'>发布</a>
    <%-- </shiro:hasPermission> --%>
    </div>
    <div id="dlg" class="easyui-dialog" style="padding:10px 20px;width: 800px;height: 580px;"
           closed="true" buttons="#dlg-buttons" modal="true">
	  	<iframe id='addAroundSuggestStorePromotionFormUI' border='0' vspace='0' hspace='0' 
	    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
	    	style="height:100%;width:100%;left:10px;top:8px" src="">
	    </iframe>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveSubmit()" style="width:90px;float:left;margin-left:36%;">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px;float:left;margin-left:20px;">取消</a>
        <a href="javascript:void(0)" class="easyui-linkbutton hiddenUse" style="border:0"></a>
    </div>
    
	<script type="text/javascript">
		function formatStatus(val,row){
			if (val == "0") {
				return '<span>未发布</span>';
			} else if (val == "1") {
				return "<span>已发布</span>";
			} else {
				return "<span></span>";
			}
		}
		function conversion(value){
			if (value != null && value != "") {
				var time = new Date(value);
	       		return time.format("yyyy-MM-dd hh:mm:ss");
			} else {
				return "";
			}
   		}
		function saveSubmit(val){
		    /* 调用共通js中是否为IE的判定方法 */
		   if(window.frames["addAroundSuggestStorePromotionFormUI"].contentWindow==undefined){
	            window.frames["addAroundSuggestStorePromotionFormUI"].optSubmit(val);
	       }else{
	            window.frames["addAroundSuggestStorePromotionFormUI"].contentWindow.optSubmit(val);
	       }
		}
		function toAdd(){
			parent.$('#dlg-buttons').empty();
			parent.$('#dlg-buttons').append("<a href='javascript:void(0)' class='easyui-linkbutton c6' iconCls='icon-ok' onclick='saveSubmit(0)' style='width:90px;float:left;margin-left:30%;'>保存</a>");
			parent.$('#dlg-buttons').append("<a href='javascript:void(0)' class='easyui-linkbutton c6' iconCls='icon-ok' onclick='saveSubmit(1)' style='width:90px;float:left;margin-left:20px;'>发布</a>");
			parent.$('#dlg-buttons').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-cancel' onclick=\"javascript:$('#dlg').dialog('close')\" style='width:90px;float:left;margin-left:20px;'>取消</a>");
			$('#dlg-buttons').append("<a href='javascript:void(0)' class='easyui-linkbutton hiddenUse' style='border:0'></a>");
			$.parser.parse(parent.$('#dlg-buttons'));
			parent.$('#addAroundSuggestStoreFormUI').attr("src","aroundSuggestStore/toAddAroundSuggestStorePromotion/${aroundSuggestStoreId}");
			parent.$('#dlg').dialog('open').dialog('setTitle','新增促销');
			parent.$('#dlg-buttons').css('display','block');
            $('#fm').form('clear');
        }
        function toEdits(){
        	parent.$('#dlg-buttons').empty();
        	parent.$('#dlg-buttons').append("<a href='javascript:void(0)' class='easyui-linkbutton c6' iconCls='icon-ok' onclick='saveSubmit(0)' style='width:90px;float:left;margin-left:36%;'>保存</a>");
        	parent.$('#dlg-buttons').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-cancel' onclick=\"javascript:$('#dlg').dialog('close')\" style='width:90px;float:left;margin-left:20px;'>取消</a>");
        	$('#dlg-buttons').append("<a href='javascript:void(0)' class='easyui-linkbutton hiddenUse' style='border:0'></a>");
        	$.parser.parse(parent.$('#dlg-buttons'));
        	var row = $('#dg').datagrid('getSelected');
            if (row){
            	parent.$('#addAroundSuggestStoreFormUI').attr("src","aroundSuggestStore/toEditorAroundSuggestStorePromotion/"+row.id+"/${aroundSuggestStoreId}");
            	parent.$('#dlg').dialog('open').dialog('setTitle','编辑促销');
            	parent.$('#dlg-buttons').css('display','block');
            }else{
            	$.messager.alert('提示信息','请先选择要更新的记录。','info');
            }
        }
        function deletePromotion() {
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
                        $.post('aroundSuggestStore/deleteAroundSuggestStorePromotion',{id:row.id,delFlag:'1'},function(result){
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
            	var messgaeWarn = '确定要撤销发布此促销吗?';
            	if (val == 1) {
            		messgaeWarn = '确定要发布此促销吗?';
            	}
                $.messager.confirm('提示',messgaeWarn,function(r){
                    if (r){
                        $.post('aroundSuggestStore/updateAroundSuggestStorePromotionStatus',{id:row.id,publishStatus:val},function(result){
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
            	$.messager.alert('提示信息','请先选择要更改记录的记录。','info');
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
        $('#toolbar').empty();
        var row = $('#dg').datagrid('getSelected');
        if (row){
	        if (row.publishStatus == '0') {
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-add' plain='true' onclick='toAdd()'>新增</a>");
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconCls:'icon-remove',plain:true\" onclick='deletePromotion()'>删除</a>");
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-edit' plain='true' onclick='toEdits()'>修改</a>");
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-storenotice' plain='true' onclick='editstatus(1)'>发布</a>");
	        	$.parser.parse($('#toolbar'));
	        } else {
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-add' plain='true' onclick='toAdd()'>新增</a>");
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconCls:'icon-remove',plain:true\" onclick='deletePromotion()'>删除</a>");
	        	$('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-quxiaofabu' plain='true' onclick='editstatus(0)'>取消发布</a>");
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
    
     
    </script>
</body>
</html>