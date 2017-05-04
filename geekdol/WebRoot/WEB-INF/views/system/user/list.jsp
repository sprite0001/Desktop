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
<body >

   <div style="height: 100px; width: 90%;">
		<form id="ff" method="post">
			
			<div style="padding: 20px 0 0 20px;">
				<label for="realName">用户姓名:</label> 
				<input class="easyui-textbox" type="text" name="realName" />
				
				<label for="userName">登陆账号:</label> 
				<input class="easyui-textbox" type="text" name="userName" />
				
				<label for="status">使用状态:</label> 
				<select id="status" name="status" class="easyui-combobox" style="width: 60px;" editable="false">
					<option value="">全部</option>
					<option value="0">${QY}</option>
					<option value="1">${JY}</option>
				</select>
			</div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<label for="moblie">手机号:</label> 
				<input class="easyui-textbox" type="text" name="moblie" />
				
				<label for="email">邮箱:</label> 
				<input class="easyui-textbox" type="text" name="email" />
				
				<label for="userType">用户类型:</label> 
				<input id="userType" name="userType" class="easyui-combobox"  style="width: 100px;">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;">
                <shiro:hasPermission name="PcUser:view">
				    <input  type="button" onclick="searchUser()" value="查询" />
                </shiro:hasPermission>
			</div>
		</form>
	</div> 
    <table id="dg" class="easyui-datagrid" style="height: 620px;"
            url="user/selectList" 
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            fitColumns="true" singleSelect="true"
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
                <th field="id" hidden="true"></th>
            	<th field="realName" width="50" >用户姓名</th>
            	<th field="userName" width="50" >登陆账号</th>
                <th field="moblie" width="50">手机号</th>
                <th field="email" width="50" >邮箱</th>
                <th field="roleName" width="50" >所属角色</th>
                <th field="status" width="50" formatter="formatStatus">状态</th>
                <th field="userTypeName" width="50">用户类型</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <shiro:hasPermission name="PcUser:manager">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新增</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="PcUser:manager">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" id="bianji" plain="true" onclick="editUser()">编辑</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="PcUser:manager">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" id="shanchu" plain="true" onclick="destroyUser()">删除</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="PcUser:manager">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reset" id="chongzhimima" plain="true" onclick="initPassword()">重置密码</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="PcUser:manager">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-managerArea" id="guanliquyu" plain="true" onclick="managerArea()">管理区域</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="PcUser:manager">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-qiyong" id="qiyong" plain="true" onclick="status(0)">启用</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reject" id="jinyong" plain="true" onclick="status(1)">禁用</a>
    	</shiro:hasPermission>
    </div>
    <div id="dlgManager" class="easyui-dialog" style="padding:10px 40px;width: 840px;height: 540px;overflow: hidden;"
           closed="true" buttons="#dlg-mbuttons" modal="true">
	  	<iframe id='managerUserFormUI' border='0' vspace='0' hspace='0' 
	    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
	    	style="height:100%;width:100%;left:10px;top:8px;" src="">
	    </iframe>
    </div>
     <div id="dlg-mbuttons">
     	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" id="baocun" onclick="saveUser()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlgManager').dialog('close')" style="width:90px">关闭</a>
    </div>
   
<script type="text/javascript">

function formatStatus(val,row){
	if (val == "0"){
		return '<span>启用</span>';
	} else {
		return '<span>禁用</span>';
	}
}
</script>
   
    <script type="text/javascript">
	   var url;
	   
	   function searchUser(){
		  /*  $('#ff').form('submit',{  
			    url: "user/selectList",  
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
        function newUser(){
            $('#baocun').show();
        	$('#managerUserFormUI').attr("src","user/add");
            $('#dlgManager').dialog('open').dialog('setTitle','新增');
            $('#fm').form('clear');
        }
        function managerArea(){
        	$('#baocun').hide();
        	 var row = $('#dg').datagrid('getSelected');
             if (row){
            	var status = row.status;
            	if(status != 0){
                    $.messager.alert('提示信息','当前用户已被禁用。','info');
            	}else{
                    $('#managerUserFormUI').attr("src","userArea/managerArea/"+row.id);
                    $('#dlgManager').dialog('open').dialog('setTitle','管理区域');
                    $('#fm').form('clear');
            	}
             }else{
             	$.messager.alert('提示信息','请先选择要分配区域的用户。','info');
             }
        }
        /* 编辑时，进行页面跳转的时候传入所选的row的id，然后把值传入到子画面，子画面根据该值来加载数据 */
        function editUser(){
            $('#baocun').show();
            var row = $('#dg').datagrid('getSelected');
            if (row){
            	if(row.status == "1"){
            		$.messager.alert('提示信息','此用户已被禁用','info');
            	}else{
                	$('#managerUserFormUI').attr("src","user/toEditUser/"+row.id);
                    $('#dlgManager').dialog('open').dialog('setTitle','编辑');
            	}
            }else{
            	$.messager.alert('提示信息','请先选择要更新的记录。','info');
            }
        }
        function saveUser(){
            /* 调用共通js中是否为IE的判定方法 */
   		   if(window.frames["managerUserFormUI"].contentWindow==undefined){
   	            window.frames["managerUserFormUI"].optSubmit();
   	       }else{
   	            window.frames["managerUserFormUI"].contentWindow.optSubmit();
   	       }
        }
        function destroyUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('提示','确定要删除吗?',function(r){
                    if (r){
                        $.post('user/deleteUser',{id:row.id},function(result){
                        	if (result.type=="Success"){
                               	$.messager.show({    // show error message
                                   title: 'Success',
                                   timeout:1000,
                                   msg:result.msg,
                                   showType:'slide'
                                }); 
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: 'Error',
                                    timeout:1000,
                                    msg:result.msg,
                                    showType:'slide'
                                }); 
                            }
                        },'json');
                    }
                });
            }else{
                $.messager.alert('提示信息','请先选择要删除的记录。','info');
            }
        }
        function initPassword(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('提示','确定要重置密码吗?',function(r){
                    if (r){
                        $.post('user/initPassword',{id:row.id},function(result){
                            if (result.type=="Success"){
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: 'Error',
                                    msg:result.msg,
                                }); 
                            }
                        },'json');
                    }
                });
            }else{
            	$.messager.alert('提示信息','请先选择要重置密码的记录。','info');
            }
        }
        function initRow(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $("#bianji").show();
                $("#shanchu").show();
                $("#chongzhimima").show();
                $("#guanliquyu").show();
            	var status = row.status;
            	if(status == '1'){
            		$("#qiyong").show();
                    $("#jinyong").hide();
            	}else{
                    $("#qiyong").hide();
                    $("#jinyong").show();
            	}
            }else{
                $("#bianji").hide();
                $("#shanchu").hide();
                $("#chongzhimima").hide();
                $("#guanliquyu").hide();
                $("#qiyong").hide();
                $("#jinyong").hide();
            }
        }
        function status(val){
            var row = $('#dg').datagrid('getSelected');
            var str = "启用";
            if(val == '1'){
                str = "禁用";
            }
            if (row){
                if(row.status == val){
                    if(val == '1'){
                        $.messager.alert('提示信息','当前用户已禁用。','info');
                        return;
                    }else{
                        $.messager.alert('提示信息','当前用户已启用。','info');
                        return;
                    }
                }
            	$.messager.confirm('提示','确定要'+str+'吗?',function(r){
                    if (r){
                        $.post('user/delete',{id:row.id,status:val},function(result){
                            if (result.type=="Success"){
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                           	 $.messager.show({
                                    title: 'Error',
                                    msg:result.msg
                                }); 
                            }
                        },'json');
                    }
                });
            }else{
            	$.messager.alert('提示信息','请先选择要'+str+'的记录。','info');
            }
        }
       /*弹出diago 子画面调用父画面的方法 */
        function close(){
        	$('#dlg').dialog('close');
            $('#dlgManager').dialog('close');
        	$('#dg').datagrid('reload');
        }
        searchUser();
        
        $(function(){
        	initRow();
        });
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
	function accept(){
		if (endEditing()){
			$("#dg").datagrid('endEdit', editIndex);
			var rows = $("#dg").datagrid('getChanges');
            var rowstr = JSON.stringify(rows);
            $.ajax({
    			type: "post",
    			dataType:"json",
    			contentType:"application/json",
    			url: "users/bathUpdate",
    			data: rowstr,
    		    success: function(data){
    		    	if(data){
    		    		 alert(data);
    		    	}else{
    		    		alert(false);
    		    	}
    			}
    		});
		}
	}
	function reject(){
		$('#dg').datagrid('rejectChanges');
		editIndex = undefined;
	}
	function getChanges(){
		var rows = $('#dg').datagrid('getChanges');
		alert(rows.length+' rows are changed!');
	}
	$("#userType").combobox({
	    url: 'common/initSysData/USERTYPE', //获取所有私有域
	    valueField: "value",
	    textField: "lable",
	    panelHeight: "auto",
	    editable: false
	});
	/**绑定页面回车事件，以及初始化页面时的光标定位**/  
    $(function(){  
    bindFormComm("ff",searchUser);  
    });
    </script>
</body>
</html>