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
<form id="fm123" method="post" novalidate style="height: 280px; width: 100%;">
        		<table width="700" height="280" border="0">
					  <tr>
					    <td height="30">标题</td>
					    <td width="50"><input name="title" class="easyui-textbox" readonly="readonly"></td>
					  </tr>
					  <tr>
					    <td width="50" height="30">商品分类</td>
					    <td width="200"><input name="classificName" class="easyui-textbox" readonly="readonly"></td>
					  </tr>
					  <tr>
					    <td height="30">截止日期</td>
					    <td><input name="endDate" class="easyui-textbox" readonly="readonly" ></td>
					  </tr>
					  <tr>
					    <td height="30">状态</td>
					    <td><input name="status" class="easyui-textbox" readonly="readonly"></td>
					  </tr>
					  <tr>
					    <td height="30">联系人</td>
					    <td><input name="contactsName" class="easyui-textbox" readonly="readonly"></td>
					  </tr>
					  <tr>
					    <td height="30">联系电话</td>
					    <td><input name="contactsPhone" class="easyui-textbox" readonly="readonly"></td>
					  </tr>
					  <tr>
					    <td height="30">详情</td>
					    <td>
					    <textarea  name="details" class="easyui-validatebox" readonly="readonly" style="height:50px; width: 500px"></textarea>
					    </td>
					  </tr>
					  <tr>
					    <td height="30">同意数量</td>
					    <td><input name="number" class="easyui-textbox" readonly="readonly"></td>
					  </tr>
					</table>               
            <input name="id" id="id" type="hidden" value="${id}">        
        </form>
   <div style="height: 70px; width: 100%;">   	
		<form id="ff" method="post">
			
			<div style="float: left; padding: 20px 0 0 20px;">
				<label for="userName">回复人:</label> 
				<input class="easyui-textbox" type="text" name="realName" />
			</div>
			 <div style="float: left; padding: 20px 0 0 20px;">
				<label for="userid">回复内容:</label> 
				<input class="easyui-textbox" type="text" name="comment"  /> 
			</div>
			<div style="float: left; padding: 20px 0 0 20px;" >
				<label for="status">违规状态:</label> 
				<select id="status" name="status" class="easyui-combobox" style="width: 60px;">
					<option value="">全部</option>
					<option value="0">正常</option>
					<option value="1">违规</option>
				</select>
			</div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<input  type="button" onclick="searchApp()" value="查询" />
			</div>
		</form>


	</div> 
     <table id="dg"  class="easyui-datagrid" style="height: 250px;"
            url="grouponDemand/findComments/${id}" 
            toolbar="#toolbar" pagination="false" queryParams="form2Json('ff')" fitColumns="true" singleSelect="true" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
            	<th field="ck" checkbox="false"></th>
            	<th field="realName" width="10%" >评论人</th>
                <th field="comment" width="30%">评论内容</th>
                <th field="opreaterIp" width="18%">评论人ip</th>
                <th field="discussTime" width="23%" formatter="conversion">评论时间</th>
                <th field="status" width="13%" formatter="formatType">违规状态</th>
            </tr>
        </thead>
    </table>
        <div id="toolbar">
    	<shiro:hasPermission name="GrouponDemand:manager">
        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="returnDetails()">回复</a>        
        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="destroyUser()">删除</a>        	        	
        </shiro:hasPermission>
    </div>
    <div id="toolbar">
    </div>
    	
    	<div id="dlg1" class="easyui-dialog" style="padding:10px 20px;width: 700px; height: 400px"
            closed="true" buttons="#dlg-buttons">
		  	<iframe id='addUserFormUI1' border='0' vspace='0' hspace='0' 
		    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
		    	style="height:90%;width:100%;left:10px;top:8px" src="">
		    </iframe>
	    </div>
	    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg1').dialog('close')" style="width:90px">取消</a>
    </div>
   <script type="text/javascript">
   var id = $('#id').val();
   $('#fm123').form('load','grouponDemand/detailsInit/'+id);
   		function conversion(value){
       		var time = new Date(value);
       		return time.format("yyyy-MM-dd hh:mm:ss");
   		};
		function formatType(val,row){
			if (val == "1"){
				return '<span>违规</span>';
			} else{
				return '<span>正常</span>';
			}
		}	   
	   function searchApp(){
		   $('#ff').form('submit',{  
			    url: "grouponDemand/findComments/"+id,
			    onSubmit: function(){  
			    	return $(this).form('validate'); 
			    },  
			    success:function(data){  
			    	var data = eval('('+data+')');
			       $('#dg').datagrid('loadData',data);
			    }
			});  

	   }       
        
        function saveUser(){
            /* 调用共通js中是否为IE的判定方法 */
    		if(isIE()) {
    			window.frames["addUserFormUI1"].optSubmit();
    		} else {
    			window.frames["addUserFormUI1"].contentWindow.optSubmit();
    		}
        }
        function returnDetails(){
      	  var row = $('#dg').datagrid('getSelected');
            if (row){
            	$('#addUserFormUI1').attr("src","grouponDemand/returnDetails/"+row.id);
                $('#dlg1').dialog('open').dialog('setTitle','回复详情');
            }else{
            	$.messager.alert('提示信息','请先选择要查看的记录。','info');
            }
      }
        
        function destroyUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('提示','确定要删除吗?',function(r){
                    if (r){
                        $.post('grouponDemand/deleteGrouponDemandComment',{id:row.id},function(result){
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
    
    </script>
</body>
</html>