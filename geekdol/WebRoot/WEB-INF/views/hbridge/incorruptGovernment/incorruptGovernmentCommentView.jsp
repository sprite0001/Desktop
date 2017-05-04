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
	<div style="height: 35px; width: 100%; padding-top: 12px;">
		<div class="fitem" style="width: 40%;float: left;padding-left: 12px;">
			<label style="width: 40px;">标题:</label>${incorruptGovernment.title }
		</div>
		<div class="fitem" style="width: 25%;float: left;padding-left: 12px;">
			<label style="width: 40px;">分类:</label>${incorruptGovernment.typeStr }
		</div>
		<div class="fitem" style="width: 25%;float: left;padding-left: 12px;">
			<label style="width: 50px;">发布人:</label>${incorruptGovernment.publishPerson }
		</div>
	</div>
	<div style="height: 30px; width: 100%;">
		<div class="fitem" style="width: 40%;float: left;padding-left: 12px;">
			<label style="width: 90px;">浏览/回复/违规:</label>${incorruptGovernment.viewNumber }/
			${incorruptGovernment.replyNumber }/${incorruptGovernment.illegalNumber }
		</div>
		<div class="fitem" style="width: 40%;float: left;padding-left: 12px;">
			<label style="width: 105px;">关注量/不关注量:</label>${incorruptGovernment.likesNumber }/
			${incorruptGovernment.hateNumber }
		</div>
	</div> 
   <div style="height: 70px; width: 100%;"> 
		<form id="ff" method="post">
			<div style="float: left; padding: 20px 0 0 12px;">
				<label for="nickName">评论人:</label> 
				<input class="easyui-textbox" type="text" name="nickName" />
			</div>
			 <div style="float: left; padding: 20px 0 0 20px;">
				<label for="content">评论内容:</label> 
				<input class="easyui-textbox" type="text" name="content"/> 
			</div>
			<div style="float: left; padding: 20px 0 0 20px;" >
				<label for="illegalStatus">违规状态:</label> 
				<select id="illegalStatus" name="illegalStatus" class="easyui-combobox" style="width: 100px;" editable="false">
					<option value="">全部</option>
					<option value="${ZC_SUM }">${ZC }</option>
					<option value="${WG_SUM }">${WG }</option>
				</select>
			</div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<input type="button" onclick="searchComment()" value="查询" />
			</div>
			<input type="hidden" name="incorruptGovernmentId" id="incorruptGovernmentId" value="${incorruptGovernment.id }">
		</form>
	</div> 
     <table id="dg" class="easyui-datagrid" style="height: 350px;"
     		url="incorruptGovernment/findCommentList/${incorruptGovernment.id }" 
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')" fitColumns="true" singleSelect="true" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
            	<th field="ck" checkbox="false"></th>
            	<th field="nickName" width="10%" >评论人</th>
                <th field="content" width="30%">评论内容</th>
                <th field="opreaterIp" width="18%">评论人IP</th>
                <th field="opreaterTime" width="23%" formatter="conversion">评论时间</th>
                <th field="illegalStatus" width="13%" formatter="conversionStatus">违规状态</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
    <shiro:hasPermission name="IncorruptGovernment:manager">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="viewCommentReturninfo()">回复管理</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteComment()">删除</a>
    </shiro:hasPermission>
    </div>
   	<div id="dlg" class="easyui-dialog" style="padding:10px 20px;width: 800px; height: 550px"
           closed="true" buttons="#dlg-buttons">
	  	<iframe id='addUserFormUI' border='0' vspace='0' hspace='0' 
	    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='no' 
	    	style="height:90%;width:100%;left:10px;top:8px" src="">
	    </iframe>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
    </div>
   <script type="text/javascript">
	   /**绑定页面回车事件，以及初始化页面时的光标定位**/  
	   $(function(){  
			bindFormComm("ff",searchComment);    
	   });
	   function conversionStatus(value){
	 		if(value == '1'){
	 			return '<span style="color: #f23030;">违规</span>';
	 		}else if(value == '0'){
	 			return '<span>正常</span>';
	 		}
		};
		function conversion(value){
	  		var time = new Date(value);
	  		return time.format("yyyy-MM-dd hh:mm:ss");
		};
	   var incorruptGovernmentId = $('#incorruptGovernmentId').val();
	   function searchComment(){
		   $('#dg').datagrid({ queryParams: form2Json("ff") });
	   }       
        
        function saveUser(){
            /* 调用共通js中是否为IE的判定方法 */
   		   if(window.frames["addUserFormUI"].contentWindow==undefined){
   	            window.frames["addUserFormUI"].optSubmit();
   	       }else{
   	            window.frames["addUserFormUI"].contentWindow.optSubmit();
   	       }
        }
        
        /**数据删除*/
        function deleteComment(){
        	var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('提示','确定要删除吗?',function(r){
                    if (r){
                        $.post('incorruptGovernment/deleteComment',{id:row.id},function(result){
                            if (result.type=='Success'){
                            	$.messager.show({    // show error message
                                    title: 'Success',
                                    msg: result.Msg
                                });
                            	parent.$('#addUserFormUI').attr("src","incorruptGovernment/viewIncorruptGovernmentComment/${incorruptGovernment.id }");
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
        /**评论回复*/
        function viewCommentReturninfo(){
        	var incorruptGovernmentId = $("#incorruptGovernmentId").val();
        	var row = $('#dg').datagrid('getSelected');
            if (row){
            	parent.$('#addUserFormUI').attr("src","incorruptGovernment/toViewCommentReturninfo/"+incorruptGovernmentId+"/"+row.id);
                parent.$('#dlg').dialog('open').dialog('setTitle','评论回复');
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
    
    </script>
</body>
</html>