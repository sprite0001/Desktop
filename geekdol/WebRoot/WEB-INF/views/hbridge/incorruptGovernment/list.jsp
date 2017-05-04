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
				<label for="title">标题:</label> 
                <input id="title" name="title" class="easyui-textbox" style="width: 100px;">
            </div>
            <div style="float: left; padding: 20px 0 0 20px;">
				<label for="publishPerson">发布人:</label> 
                <input id="publishPerson" name="publishPerson" class="easyui-textbox" style="width: 100px;">
            </div>
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="type">类别:</label> 
                <input id="type" name="type" class="easyui-combobox" 
					data-options="valueField:'value',textField:'lable',url:'common/initSysData/${INCORRUPTGOVERNMENTTYPE }'" style="width: 100px;" editable="false">
            </div>
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="publishStartTime">发布时间介于:</label>
            	<input id="publishStartTime" class="easyui-datebox" name="publishStartTime" editable="false">
            </div>
            <div style="float: left; padding: 20px 0 0 7px;">
            	<label for="publishEndTime">到:</label>
            	<input id="publishEndTime" class="easyui-datebox" name="publishEndTime" editable="false">
            </div>
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="publishStatus">状态:</label> 
                <input id="publishStatus" name="publishStatus" class="easyui-combobox" 
					data-options="valueField:'value',textField:'lable',url:'common/initSysData/${INCORRUPTGOVERNMENTSTATUS }'" style="width: 100px;" editable="false">
            </div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<input  type="button" onclick="searchProvince()" value="查询" />
			</div>
		</form>
	</div> 
    <table id="dg" class="easyui-datagrid" style="height:620px;"
            url="incorruptGovernment/findAll" 
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            fitColumns="true" singleSelect="true" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
                <th field="title" width="60">标题</th>
            	<th field="typeStr" width="25" >类别</th>
                <th field="publishPerson" width="20">发布人</th>
                <th field="publishTime" width="25" formatter="conversion">发布时间</th>
                <th field="publishStatusStr" width="15" formatter="formatStatus">发布状态</th>
                <th field="viewNumber" width="20">浏览量</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
    <shiro:hasPermission name="IncorruptGovernment:manager">
        <a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-add' plain='true' onclick='newUser()'>新增</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="IncorruptGovernment:view">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-view" plain="true" onclick="view()">查看详情</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="IncorruptGovernment:manager">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-view" plain="true" id="editor" onclick="editUser()">修改</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-storenotice" plain="true" id="comment" onclick="viewIncorruptGovernmentComment()">评论管理</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="IncorruptGovernment:publish">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-storenotice" plain="true" id="fabu" onclick="fabu()">发布</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-storenotice" plain="true" id="quxiaofabu" onclick="quxiaofabu()">取消发布</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="IncorruptGovernment:manager">
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="delete" onclick="destroy()">删除</a>
    </shiro:hasPermission>
    </div>
   	<div id="dlg" class="easyui-dialog" style="padding:10px 20px;width: 800px;height: 540px;overflow:hidden;"
           closed="true" buttons="#dlg-buttons" modal="true">
	  	<iframe id='addUserFormUI' border='0' vspace='0' hspace='0' 
	    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
	    	style="height:100%;width:100%;left:10px;top:8px" src="">
	    </iframe>
    </div>
     <div id="dlg-buttons">
        <a id="saveA" href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-saves" onclick="saveUser()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
    </div>

    <script type="text/javascript">
	    function formatStatus(val){
	        if (val == "已发布"){
	            return '<span>发布 </span>';
	        } else {
	            return '<span style=\'color:red;\'>未发布</span>';
	        }
	    }
		function conversion(value){
			if(value == '' || value == null || value == 'null' 
					|| value == undefined || value == 'undefined'){
				return "";
			}
			var time = new Date(value);  
		    return time.format("yyyy-MM-dd hh:mm:ss");
		}
	    //跳转到回复管理界面
	    function toComment() {
	    	$('#saveA').hide();
	        var row = $('#dg').datagrid('getSelected');
	        if (row){
	            $('#addUserFormUI').attr("src","intimateNews/toCommentIntimateNews/"+row.id+"/"+0);
	            $('#dlg').dialog('open').dialog('setTitle','贴心报回复管理');
	            $('#dlg-buttons').css('display','none');
	        }else{
	            $.messager.alert('提示信息','请先选择要进行回复管理的记录。','info');
	        }
	    }
	   
	   function searchProvince(){
		   $('#dg').datagrid({ queryParams: form2Json("ff") });
	   }
	   
        function newUser(){
        	$('#saveA').show();
        	$('#addUserFormUI').attr("src","incorruptGovernment/toAdd");
            $('#dlg').dialog('open').dialog('setTitle','新增');
            $('#fm').form('clear');
        }
        
        /* 编辑时，进行页面跳转的时候传入所选的row的id，然后把值传入到子画面，子画面根据该值来加载数据 */
        function editUser(){
        	$('#saveA').show();
            var row = $('#dg').datagrid('getSelected');
            if (row){
            	// 只有发布过状态不可以修改
            	if(row.publishStatus == '02'){
            		$.messager.alert('提示信息','已发布状态下禁止修改。','info');
            	}else{
            		$('#addUserFormUI').attr("src","incorruptGovernment/toEditor/"+row.id);
                    $('#dlg').dialog('open').dialog('setTitle','编辑');
            	}
            }else{
            	$.messager.alert('提示信息','请先选择要更新的记录。','info');
            }
        }
        function saveUser(){
    		   if(window.frames["addUserFormUI"].contentWindow==undefined){
    	            window.frames["addUserFormUI"].optSubmit();
    	       }else{
    	            window.frames["addUserFormUI"].contentWindow.optSubmit();
    	       }
        }
        function view(){
        	$('#saveA').hide();
     	   var row = $('#dg').datagrid('getSelected');
     	   if(row){
     	       $('#addUserFormUI').attr("src","incorruptGovernment/views/"+row.id);
     	       $('#dlg').dialog('open').dialog('setTitle','详情');
     	   }else{
     	       $.messager.alert('提示信息','请先选需要查看的清风气正！','info');
     	   }
        }
        /** 删除*/
        function destroy(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
            	// 发布状态禁止删除
            	if(row.publishStatus == '02'){
            		$.messager.alert('提示信息','已发布状态下禁止删除。','info');
            	}else{
            		$.messager.confirm('提示','确定要删除吗?',function(r){
                        if (r){
                            $.post('incorruptGovernment/deleteIncorruptGovernment',{id:row.id,delFlag:'1'},function(result){
                                if (result.type=="Success"){
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
            	}
            }else{
                $.messager.alert('提示信息','请先选择要删除的记录。','info');
            }
        }
        //发布
        function fabu(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('提示','确定要发布吗?',function(r){
                    if (r){
                        $.post('incorruptGovernment/fabuIncorruptGovernment',{id:row.id,publishStatus:'02'},function(result){
                            if (result.type=="Success"){
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
                $.messager.alert('提示信息','请先选择要发布的记录。','info');
            }
        }
        
    	//取消发布
        function quxiaofabu(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('提示','确定要取消发布吗?',function(r){
                    if (r){
                        $.post('incorruptGovernment/quxiaofabuIncorruptGovernment',{id:row.id,publishStatus:'03'},function(result){
                            if (result.type=="Success"){
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
                $.messager.alert('提示信息','请先选择要取消发布的记录。','info');
            }
        }
    	/** 回复管理*/
    	function viewIncorruptGovernmentComment(){
    		$('#saveA').hide();
    		var row = $('#dg').datagrid('getSelected');
            if (row){
            	$('#addUserFormUI').attr("src","incorruptGovernment/viewIncorruptGovernmentComment/"+row.id);
                $('#dlg').dialog('open').dialog('setTitle','评论管理');
            }else{
                $.messager.alert('提示信息','请先选择要查看的记录。','info');
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
		initRow();
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
   
	function getChanges(){
		var rows = $('#dg').datagrid('getChanges');
		alert(rows.length+' rows are changed!');
	}
	
	function initRow(){
        var row = $('#dg').datagrid('getSelected');
        if (row){
            var status = row.publishStatus;
            if(status == '01'){
                $("#fabu").show();
                $("#quxiaofabu").hide();
                $("#editor").show();
                $("#delete").show();
                $("#comment").hide();
            }else{
                $("#fabu").hide();
            	$("#quxiaofabu").show();
                $("#editor").hide();
                $("#delete").hide();
                $("#comment").show();
            }
        }else{
            $("#editor").hide();
            $("#delete").hide();
            $("#fabu").hide();
            $("#quxiaofabu").hide();
            $("#comment").hide();
        }
    }
	  /**绑定页面回车事件，以及初始化页面时的光标定位**/  
    $(function(){  
        bindFormComm("ff",searchProvince);
        initRow();
    }); 
    </script>
</body>
</html>