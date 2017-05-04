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
				<label for="realName">姓名:</label> 
				<input id="realName" name="realName" class="easyui-textbox" type="text">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<label for="staffLevel">用户级别:</label> 
				<input id="staffLevel" name="staffLevel" class="easyui-combobox" 
					data-options="valueField:'value',textField:'lable',url:'common/initSysData/${STAFFLEVELTYPE }'" style="width: 100px;" editable="false">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<label for="status">使用状态:</label> 
				<select id="status" name="status" class="easyui-combobox" style="width: 80px;" editable="false">
					<option value="">全部</option>
					<option value="0">${QY}</option>
					<option value="1">${JY}</option>
				</select>
			</div>
			<div style="float: left; padding: 20px 0 0 20px;" >
				<label for="moblie">手机号:</label> 
				<input class="easyui-textbox" type="text" name="moblie" id="moblie">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;" >
				<label for="email">邮箱:</label> 
				<input class="easyui-textbox" type="text" name="email" id="email">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<input  type="button" onclick="searchAppUser()" value="查询" />
			</div>
		</form>
	</div> 
    <table id="dg"  class="easyui-datagrid" style="height: 620px;"
            url="appUser/findAll" 
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            fitColumns="true" singleSelect="true" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
            	<th field="nickName" width="150" >昵称</th>
                <th field="moblie" width="150">手机号</th>
                <th field="realName" width="120">真实姓名</th>
                <th field="certificateTypeName" width="120">证件类型</th>
                <th field="certificateNumber" width="160">证件号码</th>
                <th field="email" width="150">邮箱</th>
                <th field="address" width="150">地址</th>
                <th field="staffLevelName" width="80">用户级别</th>
                <th field="status" width="50" formatter = "formatStatus">状态</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
    <shiro:hasPermission name="StaffUser:manager">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toAdd()">新增</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editAppUser()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyAppUser()">删除</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-qiyong" plain="true" onclick="accept()">启用</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reject" plain="true" onclick="reject()">禁用</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reset" plain="true" onclick="restPwd()">重置密码</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-member" plain="true" onclick="memberTransfer()">会员转入</a>
    </shiro:hasPermission>
    </div>
   	<div id="dlg" class="easyui-dialog" style="padding:10px 20px;width: 600px;height: 400px;"
           closed="true" buttons="#dlg-buttons" modal="true">
	  	<iframe id='addAppUserFormUI' border='0' vspace='0' hspace='0' 
	    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
	    	style="height:98%;width:100%;left:10px;top:8px" src="">
	    </iframe>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveSubmit()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
    </div>
    
    <div id="memberDlg" class="easyui-dialog" style="padding:10px 20px;width: 1060px;height: 500px;"
           closed="true" modal="true">
	  	<iframe id='memberListFormUI' border='0' vspace='0' hspace='0' 
	    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
	    	style="height:100%;width:100%;left:10px;top:8px" src="">
	    </iframe>
    </div>
    
	<script type="text/javascript">
	
		function formatStatus(val,row){
			if (val == "0"){
				return '<span>启用</span>';
			} else {
				return '<span>禁用</span>';
			}
		}
		
	   var url;
	   function searchAppUser(){
		   $('#ff').form('submit',{  
			    url: "appUser/findAll",  
			    onSubmit: function(){  
			    	return $(this).form('validate'); 
			    },  
			    success:function(data){  
			    	var data = eval('('+data+')');
			       $('#dg').datagrid('loadData',data);
			    }
			});  
	   }
	   
        function toAdd(){
        	$('#addAppUserFormUI').attr("src","appUser/toAddAppUser");
            $('#dlg').dialog('open').dialog('setTitle','新增');
            
            $('#fm').form('clear');
        }
        
        function saveSubmit(){
	        /* 调用共通js中是否为IE的判定方法 */
	   	   if(window.frames["addAppUserFormUI"].contentWindow==undefined){
	            window.frames["addAppUserFormUI"].optSubmit();
	       }else{
	            window.frames["addAppUserFormUI"].contentWindow.optSubmit();
	       }
        }
        /* 编辑时，进行页面跳转的时候传入所选的row的id，然后把值传入到子画面，子画面根据该值来加载数据 */
        function editAppUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
            	$('#addAppUserFormUI').attr("src","appUser/toEdit/"+row.id);
                $('#dlg').dialog('open').dialog('setTitle','编辑');
            }else{
            	$.messager.alert('提示信息','请先选择要更新的记录。','info');
            }
        }
        function updateSubmit(){
            /* 调用共通js中是否为IE的判定方法 */
      	  if(window.frames["addAppUserFormUI"].contentWindow==undefined){
               window.frames["addAppUserFormUI"].optSubmit();
          }else{
               window.frames["addAppUserFormUI"].contentWindow.optSubmit();
          }
        }
        function destroyAppUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('提示','确定要删除吗?',function(r){
                    if (r){
                        $.post('appUser/destroyAppUser',{id:row.id,delFlag:'1'},function(result){
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
        	$('#memberDlg').dialog('close');
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
	function accept(){
		var row = $('#dg').datagrid('getSelected');
        if (row){
        	if(row.status == '1'){
            $.messager.confirm('提示','确定要启用吗?',function(r){
                if (r){
                    $.post('appUser/rejectOrAccept',{id:row.id,status:'0'},function(result){
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
        		$.messager.alert('提示信息','已经为启用状态','info');
        	}
        }else{
        	$.messager.alert('提示信息','请先选择要启用的记录。','info');
        }
	}
	
	function reject(){
		var row = $('#dg').datagrid('getSelected');
        if (row){
        	if(row.status == '0'){            
        		$.messager.confirm('提示','确定要禁用吗?',function(r){
                    if (r){
                        $.post('appUser/rejectOrAccept',{id:row.id,status:'1'},function(result){
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
        		$.messager.alert('提示信息','已经为禁用状态','info');
        	}
        }else{
        	$.messager.alert('提示信息','请先选择要禁用的记录。','info');
        }
	}
	function restPwd(){
		var row = $('#dg').datagrid('getSelected');
        if (row){
            $.messager.confirm('提示','确定要重置吗?',function(r){
                if (r){
                    $.post('appUser/restPwd',{id:row.id},function(result){
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
        	$.messager.alert('提示信息','请先选择要重置的记录。','info');
        }
	}
	 /**绑定页面回车事件，以及初始化页面时的光标定位**/  
    $(function(){  
    bindFormComm("ff",searchAppUser);  
    }); 
	/**会员转入*/
	function memberTransfer(){
    	$('#memberListFormUI').attr("src","appUser/toMemberList");
    	$("#memberListFormUI").css({"width":"1050px" ,"height":"600px"});
    	$('#memberDlg').dialog('open').dialog('setTitle','会员转入');
        $('#memberDlg').dialog('open').css({"width":"1060px" ,"height":"500px"});
        
        $('#fm').form('clear');
    }
</script>
</body>
</html>