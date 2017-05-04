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
 <div style="height: 160px; width: 100%;"> 
	<form id="fm123" method="post" novalidate style="height: 100px; width: 100%;">
        	<div class="fitem">
        		<table width="700" height="100" border="0">
					  
					  <tr>
					    <td width="50" height="30">回复内容</td>
					    <td width="200"><textarea  name="comment" class="easyui-validatebox" readonly="readonly" style="height:50px; width: 500px"></textarea></td>
					  </tr>
					  <tr>
					    <td height="30">浏览/回复/违规</td>
					    <td width="50"><input name="pageView" class="easyui-textbox" readonly="readonly" style="width: 80px;">
					    <input name="reply" class="easyui-textbox" readonly="readonly" style="width: 80px;">
					    <input name="illegal" class="easyui-textbox" readonly="readonly" style="width: 80px;">
					    </td>
					  </tr>
					  <tr>
					    <td height="30">回复人</td>
					    <td><input name="realName" class="easyui-textbox" readonly="readonly" ></td>
					  </tr>
					  <tr>
					    <td height="30">回复时间</td>
					    <td><input name="discussTime" class="easyui-textbox" readonly="readonly"></td>
					  </tr>					 
					</table>               
            </div>
            <input name="id" id="id" type="hidden" value="${id}"> 
            <input name="commentId" id="commentId" type="hidden" value="${commentId}">       
        </form>
        </div> 
   <div style="height: 60px; width: 100%;"> 
   		 	
		<form id="ff" method="post">
			
			<div style="float: left; padding: 20px 0 0 20px;">
				<label for="userName">回复人:</label> 
				<input class="easyui-textbox" type="text" name="realName" />
			</div>
			 <div style="float: left; padding: 20px 0 0 20px;">
				<label for="userid">回复内容:</label> 
				<input class="easyui-textbox" type="text" name="commentReturnInfo"  /> 
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
            url="grouponDemand/findReturnInfo/${commentId}" 
            toolbar="#toolbar" pagination="false" queryParams="form2Json('ff')" fitColumns="true" singleSelect="true" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
            	<th field="ck" checkbox="false"></th>
            	<th field="realName" width="10%" >回复人</th>
                <th field="commentReturnInfo" width="30%">回复内容</th>
                <th field="opreaterIp" width="18%">回复人ip</th>
                <th field="insYmdhms" width="23%" formatter="conversion">回复时间</th>
                <th field="status" width="13%" formatter="formatType">违规状态</th>
            </tr>
        </thead>
    </table>
        
    </div>
   <script type="text/javascript">
   
   function formatType(val,row){
		if (val == "1"){
			return '<span>违规</span>';
		} else{
			return '<span>正常</span>';
		}
	}
   var id = $('#id').val();   
   var commentId = $('#commentId').val();
   $('#fm123').form('load','grouponDemand/returnDetailsInit/'+id);
   		function conversion(value){
       		var time = new Date(value);
       		return time.format("yyyy-MM-dd hh:mm:ss");
   		};
	</script>
    <script type="text/javascript">
	   var url;
	   
	   function searchApp(){
		   $('#ff').form('submit',{  
			    url: "grouponDemand/findReturnInfo/"+commentId,
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
       /*弹出diago 子画面调用父画面的方法 */
        function close(){
        	$('#dlg').dialog('close');
        	$('#dg').datagrid('reload');
        }       
    </script>
    <style type="text/css">
    	html { overflow-x:hidden; }
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