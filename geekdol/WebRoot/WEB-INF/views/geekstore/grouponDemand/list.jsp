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
				<label for="userName">标题:</label> 
				<input class="easyui-textbox" type="text" name="title" />
			</div>
			 <div style="float: left; padding: 20px 0 0 20px;">
				<label for="userid">商品分类:</label> 
				<input class="easyui-textbox" type="text" name="classificName"  /> 
			</div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<label for="userid">截止日期:</label> 
				<input id="startdate" 	class="easyui-datebox" name="startDate" data-options="prompt:'请选择开始日期',editable:false">
				<input id="enddate"	class="easyui-datebox" name="endDateStr"   data-options="prompt:'请选择结束日期',editable:false">
			</div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<input  type="button" onclick="searchApp()" value="查询" />
			</div>
		</form>
	</div> 
     <table id="dg"  class="easyui-datagrid" style="height: 620px;"
            url="grouponDemand/findAll" rownumbers="true"
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')" fitColumns="true" singleSelect="true" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
                <th field="title" width="9%">标题</th>
                <th field="classificName" width="9%">商品分类</th>
                <th field="contactsName" width="9%">联系人</th>
                <th field="endDate" width="18%" formatter="conversion">团购截止日期</th>
                <th field="contactsPhone" width="13%">联系电话</th>
                <th field="insYmdhms" width="18%" formatter="conversion">登记日期</th>
                <th field="status" width="9%" formatter="formatType">状态</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
    	<shiro:hasPermission name="GrouponDemand:view">
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-tongji" plain="true" onclick="census()">统计</a>
        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-details" plain="true" onclick="details()">详情</a> 
        </shiro:hasPermission>
        <shiro:hasPermission name="GrouponDemand:manager">	       
        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-shoucang" plain="true" onclick="delivery()">收藏</a>        	
        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-storenotice" plain="true" onclick="publish()">发布</a>        	
        </shiro:hasPermission>
    </div>
    
   	<div id="dlg" class="easyui-dialog" style="padding:10px 20px;width: 900px; height: 500px"
           closed="true" buttons="#dlg-buttons">
	  	<iframe id='addUserFormUI' border='0' vspace='0' hspace='0' 
	    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
	    	style="height:90%;width:100%;left:10px;top:8px" src="">
	    </iframe>
    </div>
   <script type="text/javascript">
   		function conversion(value){
       		var time = new Date(value);
       		return time.format("yyyy-MM-dd hh:mm:ss");
   		};
		function formatType(val,row){
			if (val == "1"){
				return '<span>已发布</span>';
			} else{
				return '<span>未发布</span>';
			}
		}
	</script>
    <script type="text/javascript">
	   var url;
	   
	   function searchApp(){
		  /*  $('#ff').form('submit',{  
			    url: "grouponDemand/findAll",
			    onSubmit: function(){  
			    	return $(this).form('validate'); 
			    },  
			    success:function(data){  
			    	var data = eval('('+data+')');
			       $('#dg').datagrid('loadData',data);
			    }
			});  */
		 $('#dg').datagrid({ queryParams: form2Json("ff") });
	   }
        /* 编辑时，进行页面跳转的时候传入所选的row的id，然后把值传入到子画面，子画面根据该值来加载数据 */
        function delivery(){
        	var row = $('#dg').datagrid('getSelected');
            if (row){
            	if(row.collectFlag=="0"){
            		$.messager.confirm('提示','确定要收藏吗?',function(r){
                        if (r){
                            $.post('grouponDemand/collectGrouponDemand',{id:row.id},function(result){
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
            	} else{
            		$.messager.alert('提示信息','已经收藏','info');
            	}
                
            }else{
            	$.messager.alert('提示信息','请先选择要收藏的记录。','info');
            }
        }        
        
        function saveUser(){
            /* 调用共通js中是否为IE的判定方法 */
    		if(isIE()) {
    			window.frames["addUserFormUI"].optSubmit();
    		} else {
    			window.frames["addUserFormUI"].contentWindow.optSubmit();
    		}
        }
        function details(){
      	  var row = $('#dg').datagrid('getSelected');
            if (row){
            	$('#addUserFormUI').attr("src","grouponDemand/details/"+row.id);
                $('#dlg').dialog('open').dialog('setTitle','详情');
            }else{
            	$.messager.alert('提示信息','请先选择要查看的记录。','info');
            }
      }
        function census(){
              	$('#addUserFormUI').attr("src","grouponDemand/echarts");
                  $('#dlg').dialog('open').dialog('setTitle','统计');
        }
        
        function destroyUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('提示','确定要删除吗?',function(r){
                    if (r){
                        $.post('grouponDemand/deleteGrouponDemand',{id:row.id},function(result){
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
        function publish(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
            	if(row.status=="0"){
            		$.messager.confirm('提示','确定要发布吗?',function(r){
                        if (r){
                            $.post('grouponDemand/publishGrouponDemand',{id:row.id},function(result){
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
            		$.messager.alert('提示信息','已经发布','info');
            	}
                
            }else{
            	$.messager.alert('提示信息','请先选择要发布的记录。','info');
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