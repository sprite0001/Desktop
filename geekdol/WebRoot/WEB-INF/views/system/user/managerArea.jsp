<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html style="height: 100%;">
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
<body style="height: 100%;">

   <div style="height: 100px; width: 100%;">
		<form id="ff" method="post">
			<div style="float: left; padding: 20px 0 0 20px;">
				<label>地区类别:</label>
				<input id="areaLevel" name="areaLevel" class="easyui-combobox" style="width: 100px;" required="true">
			 <span id="provinceSpan">
				<label for="status">省:</label> 
                <input id="province" name="provinceId" class="easyui-combobox" style="width: 100px;">
			 </span>
             <span id="citySpan">
                <label for="status">市:</label> 
                <input id="city" name="cityId" class="easyui-combobox" style="width: 100px;">
             </span>
           
             <span id="countySpan">
                <label for="status">区县:</label> 
                <input id="county" name="countyId" class="easyui-combobox" style="width: 100px;">
             </span>
                
             <span id="communitySpan">
                <label for="status">社区:</label> 
                <input id="community" name="communityId" class="easyui-combobox" style="width: 100px;">
             </span>
            </div>
            <input type="hidden" id="userId" name = "userId" value="${userId }" />
            <div style="float: left; padding: 20px 0 0 20px;" id="villageSpan">
                <label for="villageName">小区名:</label> 
                <input class="easyui-textbox" type="text" name="villageName" />
            </div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<input  type="button" onclick="searchProvince()" value="查询" />
			</div>
		</form>
	</div> 
    <table id="dg"  class="easyui-datagrid"
            url="userArea/selectList?userId=${userId }" 
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            rownumbers="false" fitColumns="true" singleSelect="true" 
            sortName="createTime" sortOrder='asc'
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
            	<th field="areaLevelName" width="20" >类别 </th>
            	<th field="villageName" width="40" >小区名称</th>
                <th field="villageAddress" width="50">小区地址</th>
                <th field="provinceName" width="20">所属省</th>
                <th field="cityName" width="20">所属市</th>
                <th field="countyName" width="20">所属区</th>
                <th field="communityName" width="50">所属社区</th>
                <th field="status" width="50" formatter = "formatStatus">状态</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新增</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
    </div>
   	<div id="dlg" class="easyui-dialog" style="padding:10px 20px;width:100%;height:80%;overflow:hidden;"
          closed="true" buttons="#dlg-buttons" modal="true">
	  	<iframe id='addUserFormUI' border='0' vspace='0' hspace='0' 
	    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
	    	style="height:100%;width:100%;left:10px;top:8px" src="">
	    </iframe>
    </div>
	<script type="text/javascript">
	
	   function formatIskaidian(val,row){
           if (val == "0"){
               return '<span>否</span>';
           } else {
               return '<span>是</span>';
           }
       }
	
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
	   function searchProvince(){
		   /* $('#ff').form('submit',{  
			    url: "userArea/selectList",  
			    onSubmit: function(){
			    	return $(this).form('validate'); 
			    },  
			    success:function(data){  
			    	var data = eval('('+data+')');
			        $('#dg').datagrid('loadData',data);
				    $("#dg").datagrid('hideColumn', 'id'); 
			    }
			});   */
           $('#dg').datagrid({ queryParams: form2Json("ff") });
	   }
	   
        function newUser(){
        	$('#addUserFormUI').attr("src","userArea/addList/${userId}");
            $('#dlg').dialog('open').dialog('setTitle','新增');
            $('#organId').combobox({
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
            url = 'village/toAddVillage';
        }
        function destroyUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('提示','确定要删除吗?',function(r){
                    if (r){
                        $.post('userArea/deleteUserArea',{id:row.id},function(result){
                            if (result.type=="Success"){
                            	 $.messager.show({    // show error message
                                     title: 'Success',
                                     msg: result.msg
                                 });
                            	 searchProvince();
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
       /*弹出diago 子画面调用父画面的方法 */
        function close(){
        	$('#dlg').dialog('close');
        	$('#editordlg').dialog('close');
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
            $.messager.confirm('提示','确定要启用吗?',function(r){
                if (r){
                    $.post('village/updateVillage',{villageId:row.villageId,status:'0'},function(result){
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
            $.messager.alert('提示信息','请先选择要启用的记录。','info');
        }
	}
	function reject(){
		var row = $('#dg').datagrid('getSelected');
        if (row){
            $.messager.confirm('提示','确定要禁用吗?',function(r){
                if (r){
                    $.post('village/updateVillage',{villageId:row.villageId,status:'1'},function(result){
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
            $.messager.alert('提示信息','请先选择要禁用的记录。','info');
        }
	}
	function getChanges(){
		var rows = $('#dg').datagrid('getChanges');
		alert(rows.length+' rows are changed!');
	}
    
    </script>
    
    
        
    <!-- 省市区关联 -->
    <script type="text/javascript">
    var areaLevelVal = "${areaLevel}";
    $(function(){
        if(areaLevelVal == ""){
        	areaLevelVal = "05";
        }
        if(areaLevelVal == "05"){
            $("#provinceSpan").show();
            $("#citySpan").show();
            $("#countySpan").show();
            $("#communitySpan").show();
            $("#villageSpan").show();/* 
            $('#dg').datagrid('showColumn','villageName');
            $('#dg').datagrid('showColumn','villageAddress');
            $('#dg').datagrid('showColumn','provinceName');
            $('#dg').datagrid('showColumn','cityName');
            $('#dg').datagrid('showColumn','countyName');
            $('#dg').datagrid('showColumn','communityName'); */
        }
        if(areaLevelVal == "04"){
            $("#provinceSpan").show();
            $("#citySpan").show();
            $("#countySpan").show();
            $("#communitySpan").show();
            $("#villageSpan").hide();/* 
            $('#dg').datagrid('hideColumn','villageName');
            $('#dg').datagrid('hideColumn','villageAddress');
            $('#dg').datagrid('showColumn','provinceName');
            $('#dg').datagrid('showColumn','cityName');
            $('#dg').datagrid('showColumn','countyName');
            $('#dg').datagrid('showColumn','communityName'); */
        }
        if(areaLevelVal == "03"){
            $("#provinceSpan").show();
            $("#citySpan").show();
            $("#countySpan").show();
            $("#communitySpan").hide();
            $("#villageSpan").hide();/* 
            $('#dg').datagrid('hideColumn','villageName');
            $('#dg').datagrid('hideColumn','villageAddress');
            $('#dg').datagrid('showColumn','provinceName');
            $('#dg').datagrid('showColumn','cityName');
            $('#dg').datagrid('showColumn','countyName');
            $('#dg').datagrid('hideColumn','communityName'); */
        }
        if(areaLevelVal == "02"){
            $("#provinceSpan").show();
            $("#citySpan").show();
            $("#countySpan").hide();
            $("#communitySpan").hide();
            $("#villageSpan").hide();/* 
            $('#dg').datagrid('hideColumn','villageName');
            $('#dg').datagrid('hideColumn','villageAddress');
            $('#dg').datagrid('showColumn','provinceName');
            $('#dg').datagrid('showColumn','cityName');
            $('#dg').datagrid('hideColumn','countyName');
            $('#dg').datagrid('hideColumn','communityName'); */
        }
        if(areaLevelVal == "01"){
            $("#provinceSpan").show();
            $("#citySpan").hide();
            $("#countySpan").hide();
            $("#communitySpan").hide();
            $("#villageSpan").hide();/* 
            $('#dg').datagrid('hideColumn','villageName');
            $('#dg').datagrid('hideColumn','villageAddress');
            $('#dg').datagrid('showColumn','provinceName');
            $('#dg').datagrid('hideColumn','cityName');
            $('#dg').datagrid('hideColumn','countyName');
            $('#dg').datagrid('hideColumn','communityName'); */
        }
        // 省份 下拉框选择控件，下拉框的内容是动态查询数据库信息 
        $('#province').combobox({ 
                    url:'common/initProvince',
                    editable:false, //不可编辑状态
                    cache: false,
                    panelHeight: '200px',//自动高度适合
                    valueField:'provinceId',   
                    textField:'provinceName',
                    
        onHidePanel: function(){

                $("#city").combobox("setValue",'');
                $("#county").combobox("setValue",'');
                $("#county").combobox('loadData', {});
                $("#community").combobox("setValue",'');
                $("#community").combobox('loadData', {});
                var province = $('#province').combobox('getValue');     
                if(province!=''){
                $.ajax({
                    type: "POST",
                    url: "common/getCityByProvice/"+province,
                    cache: false,
                    dataType : "json",
                    success: function(data){
                    $("#city").combobox("loadData",data);
                                           }
                                       });  
                               }
                         } 
                     }); 
        //市下拉菜单
        $('#city').combobox({ 

            editable:false, //不可编辑状态
            cache: false,
            panelHeight: '200px',//自动高度适合
            valueField:'cityId',   
            textField:'cityName',
            onHidePanel: function(){
                $("#county").combobox("setValue",'');
                $("#community").combobox("setValue",'');
                $("#community").combobox('loadData', {});
                var city = $('#city').combobox('getValue');     
                if(city!=''){
                $.ajax({
                    type: "POST",
                    url: "common/getCountryByCity/"+city,
                    cache: false,
                    dataType : "json",
                    success: function(data){
                    $("#county").combobox("loadData",data);
                                           }
                                       });  
                               }
                         }
           }); 
        //区下拉菜单
        $('#county').combobox({ 
            editable:false, //不可编辑状态
            cache: false,
            panelHeight: '200px',//自动高度适合
            valueField:'countyId',   
            textField:'countyName',
            onHidePanel: function(){
                $("#community").combobox("setValue",'');
                var county = $('#county').combobox('getValue');     
                if(county!=''){
                $.ajax({
                    type: "POST",
                    url: "common/getCommunityByCounty/"+county,
                    cache: false,
                    dataType : "json",
                    success: function(data){
                    $("#community").combobox("loadData",data);
                                           }
                                       });  
                               }
                         }
         });  
      //社区下拉菜单
        $('#community').combobox({ 
            editable:false, //不可编辑状态
            cache: false,
            panelHeight: '200px',//自动高度适合
            valueField:'communityId',   
            textField:'communityName',
           });  
        $("#areaLevel").combobox({
            url: 'sysdata/selectByType/AREALEVEL', //获取所有私有域
            valueField: "value",
            textField: "lable",
            panelHeight: '200px',//自动高度适合
            value:areaLevelVal,
            editable: false
        });
        $('#areaLevel').combobox({  
            onChange:function(){
                var val = $('#areaLevel').combobox('getValue');
                if(val == "05"){
                    $("#provinceSpan").show();
                    $("#citySpan").show();
                    $("#countySpan").show();
                    $("#communitySpan").show();
                    $("#villageSpan").show();/* 
                    $('#dg').datagrid('showColumn','villageName');
                    $('#dg').datagrid('showColumn','villageAddress');
                    $('#dg').datagrid('showColumn','provinceName');
                    $('#dg').datagrid('showColumn','cityName');
                    $('#dg').datagrid('showColumn','countyName');
                    $('#dg').datagrid('showColumn','communityName'); */
                }
                if(val == "04"){
                    $("#provinceSpan").show();
                    $("#citySpan").show();
                    $("#countySpan").show();
                    $("#communitySpan").show();
                    $("#villageSpan").hide();/* 
                    $('#dg').datagrid('hideColumn','villageName');
                    $('#dg').datagrid('hideColumn','villageAddress');
                    $('#dg').datagrid('showColumn','provinceName');
                    $('#dg').datagrid('showColumn','cityName');
                    $('#dg').datagrid('showColumn','countyName');
                    $('#dg').datagrid('showColumn','communityName'); */
                }
                if(val == "03"){
                    $("#provinceSpan").show();
                    $("#citySpan").show();
                    $("#countySpan").show();
                    $("#communitySpan").hide();
                    $("#villageSpan").hide();/* 
                    $('#dg').datagrid('hideColumn','villageName');
                    $('#dg').datagrid('hideColumn','villageAddress');
                    $('#dg').datagrid('showColumn','provinceName');
                    $('#dg').datagrid('showColumn','cityName');
                    $('#dg').datagrid('showColumn','countyName');
                    $('#dg').datagrid('hideColumn','communityName'); */
                }
                if(val == "02"){
                    $("#provinceSpan").show();
                    $("#citySpan").show();
                    $("#countySpan").hide();
                    $("#communitySpan").hide();
                    $("#villageSpan").hide();/* 
                    $('#dg').datagrid('hideColumn','villageName');
                    $('#dg').datagrid('hideColumn','villageAddress');
                    $('#dg').datagrid('showColumn','provinceName');
                    $('#dg').datagrid('showColumn','cityName');
                    $('#dg').datagrid('hideColumn','countyName');
                    $('#dg').datagrid('hideColumn','communityName'); */
                }
                if(val == "01"){
                    $("#provinceSpan").show();
                    $("#citySpan").hide();
                    $("#countySpan").hide();
                    $("#communitySpan").hide();
                    $("#villageSpan").hide();/* 
                    $('#dg').datagrid('hideColumn','villageName');
                    $('#dg').datagrid('hideColumn','villageAddress');
                    $('#dg').datagrid('showColumn','provinceName');
                    $('#dg').datagrid('hideColumn','cityName');
                    $('#dg').datagrid('hideColumn','countyName');
                    $('#dg').datagrid('hideColumn','communityName'); */
                }
            }
        });
    });
    /**绑定页面回车事件，以及初始化页面时的光标定位**/  
    $(function(){  
    bindFormComm("ff",searchProvince);  
    });
    </script>
    
</body>
</html>