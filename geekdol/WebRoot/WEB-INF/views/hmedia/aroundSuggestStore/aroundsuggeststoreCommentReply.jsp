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
		.div_add {
			width: 32%;
			text-align: left;
			display: inline-block;
		}
		.detail_row {
			margin-top: 10px;
			width: 100%;
		}
		.detail_query {
			width: 100%;
		}
		.query_param {
			margin-left: 6px;
		}
		.query_form {
			margin:14px 0;
		}
	</style>
</head>
<body>
   <div class="detail_query">
   		<input type="hidden" id="aroundSuggestStoreCommentId" name="aroundSuggestStoreCommentId" value="${aroundSuggestStoreCommentId}" />
   		<input type="hidden" id="aroundSuggestStoreId" name="aroundSuggestStoreId" value="${aroundSuggestStoreId}" />
   		<div style="width: 100%;">
   			<div class="div_add">
				<label>店名:</label> 
				${aroundSuggestStoreVo.name}
			</div>
			<div class="div_add">
				<label>所属小区:</label> 
				${aroundSuggestStoreVo.villageName}
			</div>
			<div class="div_add" style="width:30%">
				<label>浏览/回复/违规:</label> 
				${aroundSuggestStoreCommentVo.viewReplyIllegalNumber}
			</div>
		</div>
		<div class="detail_row">
			<div class="div_add">
				<label>点赞/倒赞:</label> 
				${aroundSuggestStoreVo.likesOrhate}
			</div>
			<div class="div_add" style="width:60%">
				<label>回复内容:</label> 
				${aroundSuggestStoreCommentVo.content}
			</div>
		</div>
		<div class="detail_row">
			<div class="div_add">
				<label>回复人:</label> 
				${aroundSuggestStoreCommentVo.insName}
			</div>
			<div class="div_add" style="width:60%">
				<label>回复时间:</label> 
				${commentDatetime}
			</div>
		</div>
		<form id="ff" method="post">
			<div class="query_form">
				<input type="hidden" id="insId" name="insId" value="${insId}" />
				<label for="insName">回复人:</label> 
				<input class="easyui-textbox" type="text" name="insName" id="insName" style="width: 70px;">
				<label for="content" class="query_param">回复内容:</label> 
				<input class="easyui-textbox" type="text" name="content" id="content" style="width: 120px;">
				<label for="illegalStatus" class="query_param">违规状态:</label> 
				<select id="illegalStatus" name="illegalStatus" class="easyui-combobox" editable="false" style="width: 80px;">
					<option value="">全部</option>
					<option value="0">${ZC}</option>
					<option value="1">${WG}</option>
				</select>
				<input type="button" class="query_param" onclick="searchCommentReply()" value="查询" />
			</div>
		</form>
	</div> 
    <table id="dg" class="easyui-datagrid"
    		url="aroundSuggestStore/findCommentReplyAll?aroundSuggestStoreCommentId=${aroundSuggestStoreCommentId}"
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            fitColumns="true" singleSelect="true" rownumbers="true"
            data-options="onClickRow: onClickRow,fit:false,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
            	<th field="insName" width="50" >回复人</th>
                <th field="content" width="50">回复内容</th>
                <th field="opreaterIp" width="50">回复人IP</th>
                <th field="insYmdhms" width="50" formatter = "conversion">回复时间</th>
                <th field="illegalStatus" width="50" formatter = "formatStatus">违规状态</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteCommentReply()">删除</a>
    </div>
    
	<script type="text/javascript">
		function formatStatus(val,row){
			if (val == "0"){
				return '<span>正常</span>';
			} else {
				return "<span style='color:red'>违规</span>";
			}
		}
		function conversion(value){
       		var time = new Date(value);
       		return time.format("yyyy-MM-dd hh:mm:ss");
   		}
		
	   var url;
	   function searchCommentReply(){
		   $('#ff').form('submit',{  
			    url: "aroundSuggestStore/findCommentReplyAll?aroundSuggestStoreCommentId=${aroundSuggestStoreCommentId}",  
			    onSubmit: function(){  
			    	return $(this).form('validate'); 
			    },  
			    success:function(data){  
			    	var data = eval('('+data+')');
			       $('#dg').datagrid('loadData',data);
			    }
			});  
	   }
        function deleteCommentReply() {
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
                        $.post('aroundSuggestStore/deleteCommentReply',{id:row.id,delFlag:'1'},function(result){
                            if (result.type=='Success'){
                            	$.messager.show({    // show error message
                                    title: 'Success',
                                    msg: result.Msg
                                });
                            	parent.$('#addAroundSuggestStoreFormUI').attr("src","aroundSuggestStore/toReplyComment/${aroundSuggestStoreCommentId}/${aroundSuggestStoreId}");
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
        bindFormComm("ff",searchCommentReply);
        }); 
     
    </script>
</body>
</html>