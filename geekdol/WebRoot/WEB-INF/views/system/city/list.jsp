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
				<label for="username">市名称:</label> 
				<input class="easyui-textbox" type="text" name="cityName" />
			</div>
			<!--  <div style="float: left; padding: 20px 0 0 20px;">
				<label for="userid">所属省:</label> 
				<input class="easyui-textbox" type="text" name="city.provinceId"  /> 
			</div>-->
			<div style="float: left; padding: 20px 0 0 20px;">
                <label for="status">所属省:</label>
                <input id="provinceId" name="provinceId" class="easyui-combobox" 
					data-options="valueField:'provinceId',editable:false,textField:'provinceName',url:'common/initProvince'">
            </div> 
			<div style="float: left; padding: 20px 0 0 20px;" >
				<label for="status">使用状态:</label> 
				<select id="status" name="status" class="easyui-combobox" style="width: 60px;" editable="false">
					<option value="">全部</option>
					<option value="0">${QY}</option>
					<option value="1">${JY}</option>
				</select>
			</div>
			<!--<div style="float: left; padding: 20px 0 0 20px;">
				<label for="whId">所属仓库:</label> 
				<input id="whId" name="whId" class="easyui-combobox" 
					data-options="valueField:'organId',textField:'organName',url:'users/combobox?type=organ'" style="width: 100px;">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<label for="createTime">创建时间:</label> 
				<input type="datetime" class="easyui-datetimebox"  name="createTime" data-options="required:false" />
			</div>-->
			<!-- <div style="float: left; padding: 20px 0 0 20px;">
				<label for="userName">修改时间:</label> 
				<input class="easyui-datetimebox" type="text" name="updateTime" />
			</div> -->
			<div style="float: left; padding: 20px 0 0 20px;">
				<input  type="button" onclick="searchCity()" value="查询" />
			</div>
		</form>


	</div> 
    <table id="dg"  class="easyui-datagrid" style="height: 620px;"
            url="city/findAll"  rownumbers="false"
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')" fitColumns="true" singleSelect="true" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
            	<th field="cityCode" width="50" >市编码</th>
            	<th field="cityName" width="50" >市名称</th>
                <!--  <th field="storeVillage" width="50"  formatter="formatVillage">所属省</th> -->
                <th field="provinceName" width="50" >所属省</th>
                <th field="ordering" width="50" >排序序号</th>
                <th field="status" width="50" formatter = "formatStatus">使用状态</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
    	<shiro:hasPermission name="City:manager">
        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新增</a>        
        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑</a>
        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-qiyong" plain="true" onclick="statusQY()">启用</a>
        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reject" plain="true" onclick="statusJY()">禁用</a>
        </shiro:hasPermission>
    </div>
    
    	<div id="dlg" class="easyui-dialog" style="padding:10px 20px;width: 600px;height:400px;"
            closed="true" buttons="#dlg-buttons" modal="true">
		  	<iframe id='addUserFormUI' border='0' vspace='0' hspace='0' 
		    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='no' 
		    	style="height:96%;width:100%;left:10px;top:8px" src="">
		    </iframe>
	    </div>
	     <div id="dlg-buttons">
	        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
	    </div>
    <!-- <div id="dlg" class="easyui-dialog"  style="padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle"></div>
        <form id="fm" method="post" novalidate>
            <div class="fitem">
                <label>用户登录名:</label>
                <input name="userName" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>用户密码:</label>
                <input name="password" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>所属部门:</label>
                <input id="organId" name="organId" class="easyui-combobox" 
					data-options="valueField:'organId',textField:'organName',url:'users/combobox?type='+'organ'" style="width: 100px;">
            </div>
            <div class="fitem">
                <label>所属角色:</label>
                <input id="roleId" name="roleId" class="easyui-combobox" 
					data-options="valueField:'roleId',textField:'roleName',url:'users/combobox?type='+'role'" style="width: 100px;">
            </div>
        </form>
    </div> -->
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
	   
	   function searchCity(){
		 /*   $('#ff').form('submit',{  
			    url: "city/findAll",
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
        	$('#addUserFormUI').attr("src","city/add");
            $('#dlg').dialog('open').dialog('setTitle','新增');
            /* $("#villageId") .combobox({
            	
               	onLoadSuccess: function () { 
               		 var data = $('#villageId').combobox('getData');
               		//var data1 = JSON.stringify(data);
               		alert("1111111111111");
               		//if (data['villageList'].length > 0) {
		                 //$('#villageId').combobox('select', "123");
		                // $('#villageId').setValue("123456");
		            // } 
               		}
               });
            $('#roleId').combobox({
               	onLoadSuccess: function () { 
               		 var data = $('#roleId').combobox('getData');
        		             if (data.length > 0) {
        		                 $('#roleId').combobox('select', data[0].roleId);
        		             } 
               		}
               });
            $('#fm').form('clear');
            url = 'cooperationStore/add'; */
        }
        /* 编辑时，进行页面跳转的时候传入所选的row的id，然后把值传入到子画面，子画面根据该值来加载数据 */
        function editUser(){
        	
            var row = $('#dg').datagrid('getSelected');
            if (row){
            	if(row.status=='0'){
            		$('#addUserFormUI').attr("src","city/edit/"+row.cityId);
                    $('#dlg').dialog('open').dialog('setTitle','编辑');
            	}else{
            		$.messager.alert('提示信息','选择的记录已经禁用','info');
            	}
            	
            }else{
            	$.messager.alert('提示信息','请先选择要更新的记录。','info');
            }
        }
        function saveUser(){
            /* 调用共通js中是否为IE的判定方法 */
    		   if(window.frames["addUserFormUI"].contentWindow==undefined){
    	            window.frames["addUserFormUI"].optSubmit();
    	       }else{
    	            window.frames["addUserFormUI"].contentWindow.optSubmit();
    	       }
        }
        function destroyUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('提示','确定要删除吗?',function(r){
                    if (r){
                        $.post('city/delete',{cityId:row.cityId},function(result){
                            if (result.type=="Success"){
                            	$.messager.show({    // show error message
                                    title: 'Success',
                                    msg:result.msg,
                                }); 
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: 'Error',
                                    msg:result.msg,
                                }); 
                            	//$.messager.alert('提示信息','删除错误','info');
                            }
                        },'json');
                    }
                });
            }else{
            	$.messager.alert('提示信息','请先选择要删除的记录。','info');
            }
        }
        
        function statusQY(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
            	if(row.status=='1'){           		 
            		 $.messager.confirm('提示','确定要启用吗?',function(r){
                         if (r){
                             $.post('city/qystatus',{cityId:row.cityId,status:row.status},function(result){
                                 if (result.type=="Success"){ 
                                	 $.messager.show({    // show error message
                                         title: 'Success',
                                         msg:result.msg,
                                     }); 
                                     $('#dg').datagrid('reload');    // reload the user data
                                 } else {
                                	 $.messager.show({    // show error message
                                         title: 'Error',
                                         msg:result.msg
                                     }); 
                                 }
                             },'json');
                         }
                     });
            	} else{
            		$.messager.alert('提示信息','已经为启用状态','info');
            	}
               
            }else{
            	$.messager.alert('提示信息','请先选择要启用的记录。','info');
            }
        }
        
        function statusJY(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
            	if(row.status=='0'){
            		 $.messager.confirm('提示','确定要禁用吗?',function(r){
                         if (r){
                             $.post('city/jystatus',{cityId:row.cityId,status:row.status},function(result){
                                 if (result.type=="Success"){
                                	 $.messager.show({    // show error message
                                         title: 'Success',
                                         msg:result.msg,
                                     }); 
                                     $('#dg').datagrid('reload');    // reload the user data
                                 } else {
                                	 $.messager.show({    // show error message
                                         title: 'Error',
                                         msg:result.msg
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
       /*弹出diago 子画面调用父画面的方法 */
        function close(){
        	$('#dlg').dialog('close');
        	$('#dg').datagrid('reload');
        }
        /* 控制行背景色 
 $(function(){
	$('#dg').datagrid({
		rowStyler:function(index,row){
			if (row.sid>20011){
				return 'background-color:pink;color:blue;font-weight:bold;';
			}
		}
	});
}) */
/* $(function(){
	
	$('#dg').datagrid({
		onBeforeEdit:function(index,row){
			row.editing = true;
			updateActions(index);
		},
		onAfterEdit:function(index,row){
			row.editing = false;
			updateActions(index);
		},
		onCancelEdit:function(index,row){
			row.editing = false;
			updateActions(index);
		}
	});
}) */
/* function updateActions(index){
		$('#dg').datagrid('updateRow',{
			index: index,
			row:{}
		});
	} */
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
    
      function formatVillage(value){
    	        return value.villageName;
    	    };
    	    
    
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
		if (endEditing()){
			/* $('#dg').datagrid('acceptChanges'); */
			$("#dg").datagrid('endEdit', editIndex);
			var rows = $("#dg").datagrid('getChanges');
			 
            var rowstr = JSON.stringify(rows);
            /* alert(rowstr);
            $.post('users/bathUpdate', rowstr, function (data) {
                 if(data){
                	 alert(data);
                	 $.messager.show({
                		 title: "更新",
                		  msg: "记录更新成功",
                		  showType: 'slide',
                		  width: 300,
                		  height: 150,
                		  timeout: 2000
                	 });
                 }
            }); */
            
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
	  /**绑定页面回车事件，以及初始化页面时的光标定位**/  
    $(function(){  
    bindFormComm("ff",searchCity);  
    }); 
    </script>
</body>
</html>