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
 <div style="height: 40px; width: 100%;">
 	<div class="fitem" style="width: 40%; float: left;">
 		 <label style="width: 60px;">团购名称:</label>${goodsName}&nbsp;&nbsp;&nbsp;&nbsp;
 	</div>
	<div class="fitem" style="width: 58%; float: right;">
		 <label style="width: 100px;">浏览/回复/违规:</label>${goodsView}/${reply}/${illegal}
 	</div>
	<div class="fitem" style="width: 100%;">
		 <label style="width: 60px;">回复内容:</label><span>${commentList.comment}</span>
		 &nbsp;&nbsp;&nbsp;&nbsp;
		 <label style="width: 60px;">回复人:</label><span>${commentList.opreaterName}</span>
		 &nbsp;&nbsp;&nbsp;&nbsp;
		 <label style="width: 60px;">回复时间:</label><span>${commentList.discussTimeStr}</span>
    </div>
</div> 
   <div style="height: 70px; width: 100%;"> 
		<form id="ff" method="post">
			<input name="commentId" id="commentId" type="hidden" value="${commentId}">
			<input name="classificId" id="classificId" type="hidden" value="${classificId}">   
			<div style="float: left; padding: 20px 0 0 0;">
				<label for="nickName">回复人:</label> 
				<input class="easyui-textbox" type="text" name="realName" id="realName"/>
			</div>
			 <div style="float: left; padding: 20px 0 0 20px;">
				<label for="commentReturnInfo">回复内容:</label> 
				<input class="easyui-textbox" type="text" name="commentReturnInfo" id="commentReturnInfo"/> 
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
				<input  type="button" onclick="searchCommentReturnInfo()" value="查询" />
			</div>
		</form>
	</div> 
     <table id="dg"  class="easyui-datagrid" style="height: 250px;"
            url="goods/findcommentReturninfo/${commentId}/${classificId}" modal="true"
            toolbar="#toolbar" pagination="false" queryParams="form2Json('ff')" fitColumns="true" singleSelect="true" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
            	<th field="ck" checkbox="false"></th>
            	<th field="realName" width="10%" >回复人</th>
                <th field="commentReturnInfo" width="30%">回复内容</th>
                <th field="opreaterIp" width="18%">回复人ip</th>
                <th field="discussTime" width="23%" formatter="conversion">回复时间</th>
                <th field="status" width="13%" formatter="formatType">违规状态</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
    <shiro:hasPermission name="Goods:manager">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyReturnInfo()">删除</a>
    </shiro:hasPermission>
    </div>
   	<div id="dlg" class="easyui-dialog" style="padding:10px 20px;width: 800px; height: 550px"
           closed="true" buttons="#dlg-buttons">
	  	<iframe id='addUserFormUI' border='0' vspace='0' hspace='0' 
	    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='no' 
	    	style="height:90%;width:100%;left:10px;top:8px" src="">
	    </iframe>
    </div>
   <script type="text/javascript">
   function formatType(val,row){
		if (val == "1"){
			return '<span>违规</span>';
		} else{
			return '<span>正常</span>';
		}
	}
   function conversion(value){
  		var time = new Date(value);
  		return time.format("yyyy-MM-dd hh:mm:ss");
		};
	   var commentId = $('#commentId').val();
	   var classificId = $('#classificId').val();
	   var url;
	   function searchCommentReturnInfo(){
		   $('#ff').form('submit',{  
			    url: "goods/findcommentReturninfo/"+commentId+"/"+classificId,
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
	   			window.frames["addUserFormUI"].optSubmit();
	   		} else {
	   			window.frames["addUserFormUI"].contentWindow.optSubmit();
	   		}
       }
       /** 删除评论回复*/
       function destroyReturnInfo(){
     		var row = $('#dg').datagrid('getSelected');
     		var classificId = $("#classificId").val();
     		var url = 'goods/destroyReturnInfo/'+row.id+'/'+classificId;
	      	if (row){
	            $.messager.confirm('提示','确定要删除吗?',function(r){
	                if (r){
	                    $.get(url,{},function(result){
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
    </script>
</body>
</html>