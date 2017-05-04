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
				<label for="provinceId">省:</label> 
				<input id="provinceId" name="provinceId" value="" class="easyui-combobox" style="width: 100px;">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<label for="cityId">市:</label> 
				<input id="cityId" name="cityId" value="" class="easyui-combobox" style="width: 100px;">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<label for="countyId">区/县:</label> 
				<input id="countyId" name="countyId" value="" class="easyui-combobox" style="width: 100px;">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;" >
				<label for="communityName">社区名称:</label> 
				<input class="easyui-textbox" type="text" name="communityName" id="communityName">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;" >
                <label for="status">使用状态:</label> 
                <select id="status" name="status" class="easyui-combobox" style="width: 60px;" editable="false">
                    <option value="">全部</option>
                    <option value="0">${QY}</option>
                    <option value="1">${JY}</option>
                </select>
            </div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<input  type="button" onclick="searchCommunity()" value="查询" />
			</div>
		</form>
	</div> 
    <table id="dg"  class="easyui-datagrid" style="height: 620px;"
            url="community/findAll" 
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            fitColumns="true" singleSelect="true" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
            	<th field="communityCode" width="150" >社区编码</th>
                <th field="communityName" width="150">社区名称</th>
                <th field="provinceName" width="150" >所属省</th>
                <th field="cityName" width="150">所属市</th>
                <th field="countyName" width="150">所属区</th>
                <th field="ordering" width="150">排序</th>
                <th field="status" width="150" formatter = "formatStatus">状态</th>
            </tr>
        </thead>
    </table>
    
    <div id="toolbar">
	    <shiro:hasPermission name="Community:manager">
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toAdd()">新增</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editCommunity()">编辑</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyCommunity()">删除</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-qiyong',plain:true" onclick="accept()">启用</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reject',plain:true" onclick="reject()">禁用</a>
	    </shiro:hasPermission>
    </div>
    <!-- 新增弹出框 -->
   	<div id="dlg" class="easyui-dialog" style="padding:10px 20px;width: 600px;height:400px;overflow:hidden"
           closed="true" buttons="#dlg-buttons" modal="true">
	  	<iframe id='addCommunityFormUI' border='0' vspace='0' hspace='0' 
	    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='no' 
	    	style="height:90%;width:100%;left:10px;top:8px" src="">
	    </iframe>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveSubmit()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
    </div>
    <!-- 修改弹出框 -->
    <!-- <div id="editordlg" class="easyui-dialog" style="padding:10px 20px;width: 600px;height:400px;overflow:hidden"
           closed="true" buttons="#editordlg-buttons" modal="true">
	  	<iframe id='editorCommunityFormUI' border='0' vspace='0' hspace='0' 
	    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='no' 
	    	style="height:90%;width:100%;left:10px;top:8px" src="">
	    </iframe>
    </div>
    <div id="editordlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="updateSubmit()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#editordlg').dialog('close')" style="width:90px">关闭</a>
    </div> -->
	    
	<script type="text/javascript">
	
		function formatStatus(val,row){
			if (val == "0"){
				return '<span>启用</span>';
			} else {
				return '<span>禁用</span>';
			}
		}
		
	   var url;
	   function searchCommunity(){
		  /*  $('#ff').form('submit',{  
			    url: "community/findAll",  
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
        	$('#addCommunityFormUI').attr("src","community/toAddCommunity");
            $('#dlg').dialog('open').dialog('setTitle','新增');
            /* $('#organId').combobox({
               	onLoadSuccess: function () { 
               		 var data = $('#organId').combobox('getData');
        		             if (data.length > 0) {
        		                 $('#organId').combobox('select', data[0].organId);
        		             } 
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
            url = 'community/add'; */
        }
        
        function saveSubmit(){
	        /* 调用共通js中是否为IE的判定方法 */
	   	   if(window.frames["addCommunityFormUI"].contentWindow==undefined){
	            window.frames["addCommunityFormUI"].optSubmit();
	       }else{
	            window.frames["addCommunityFormUI"].contentWindow.optSubmit();
	       }
        }
        /* 编辑时，进行页面跳转的时候传入所选的row的id，然后把值传入到子画面，子画面根据该值来加载数据 */
        function editCommunity(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
            	$('#addCommunityFormUI').attr("src","community/toEdit/"+row.communityId+"/"+row.provinceId+"/"+row.cityId+"/"+row.countyId);
                $('#dlg').dialog('open').dialog('setTitle','编辑');
            }else{
            	$.messager.alert('提示信息','请先选择要更新的记录。','info');
            }
        }
        function destroyCommunity(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('提示','确定要删除吗?',function(r){
                    if (r){
                        $.post('community/deleteCommunity',{communityId:row.communityId,delFlag:'1'},function(result){
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
	// 启用
	function accept(){
		var row = $('#dg').datagrid('getSelected');
		
			if (row){
				if(row.status == '1'){// 启用
	            $.messager.confirm('提示','确定要启用吗?',function(r){
	                if (r){
	                    $.post('community/rejectOrAcceptCommunity',{communityId:row.communityId,status:'0'},function(result){
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
	// 禁用
	function reject(){
		var row = $('#dg').datagrid('getSelected');
		
			if (row){
				if(row.status == '0'){// 禁用	            
	        	 $.messager.confirm('提示','确定要禁用吗?',function(r){
		                if (r){
		                    $.post('community/rejectOrAcceptCommunity',{communityId:row.communityId,status:'1'},function(result){
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
	
    $(function(){
    	// 省份 下拉框选择控件，下拉框的内容是动态查询数据库信息 
	 	$('#provinceId').combobox({ 
		    url:'common/initProvince',
		    editable:false, //不可编辑状态
		    cache: false,
		    panelHeight: '200px',//自动高度适合
		    valueField:'provinceId',   
		    textField:'provinceName',
 			onHidePanel: function(){
			    $("#cityId").combobox("setValue",'');
			    $("#countyId").combobox("setValue",'');
	            $("#countyId").combobox('loadData', {});
			    //$("#cregicounty").val('');
				var province = $('#provinceId').combobox('getValue');		
				if(province!=''){
					$.ajax({
						type: "POST",
						url: "common/getCityByProvice/"+province,
						cache: false,
						dataType : "json",
						success: function(data){
							$("#cityId").combobox("loadData",data);
				        }
			        }); 	
	            }
	        } 
        }); 
		//市下拉菜单
		$('#cityId').combobox({ 
		    editable:false, //不可编辑状态
		    cache: false,
		    panelHeight: '200px',//自动高度适合
		    valueField:'cityId',   
		    textField:'cityName',
		    onHidePanel: function(){
			    $("#countyId").combobox("setValue",'');
				var city = $('#cityId').combobox('getValue');		
				if(city!=''){
					$.ajax({
						type: "POST",
						url: "common/getCountryByCity/"+city,
						cache: false,
						dataType : "json",
						success: function(data){
							$("#countyId").combobox("loadData",data);
				        }
			        }); 	
	            }
	        }
		});
		//区下拉菜单
		$('#countyId').combobox({ 
		    editable:false, //不可编辑状态
		    cache: false,
		    panelHeight: '200px',//自动高度适合
		    valueField:'countyId',   
		    textField:'countyName',
		});  
    })
     /**绑定页面回车事件，以及初始化页面时的光标定位**/  
    $(function(){  
    bindFormComm("ff",searchCommunity);  
    });
</script>
</body>
</html>