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
				<label for="userName">用户名:</label> 
				<input class="easyui-textbox" type="text" name="goodsCode" />
			</div>
			 <div style="float: left; padding: 20px 0 0 20px;">
				<label for="userid">订单编号:</label> 
				<input class="easyui-textbox" type="text" name="orderCode"  /> 
			</div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<label for="userid">第三方交易:</label> 
				<input class="easyui-textbox" type="text" name="transactionNumber"  /> 
			</div>
            <div style="float: left; padding: 20px 0 0 20px;">
            <label for="status">交易平台:</label>
                <input id="platform" name="platform" class="easyui-combobox" 
					data-options="valueField:'value',textField:'lable',url:'common/initSysData/${PAYMODEL}'">
        </div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<input  type="button" onclick="searchApp()" value="查询" />
			</div>
		</form>


	</div> 
     <table id="dg"  class="easyui-datagrid" style="height: 620px;"
            url="paymentRecoment/findAll" 
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')" rownumbers="true" fitColumns="true" singleSelect="true" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
                <th field="phone" width="9%">手机号</th>
                <th field="orderCode" width="9%">订单编号</th>
                <th field="transactionNumber" width="18%">第三方交易号</th>
                <th field="platformName" width="9%" >第三方平台</th>
                <th field="amount" width="10%">金额</th>
                <th field="transactionDate" width="18%" formatter="conversion">交易日期</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        
    </div>
    
    	<div id="dlg" class="easyui-dialog" style="padding:10px 20px;width: 800px; height: 550px"
            closed="true" buttons="#dlg-buttons">
		  	<iframe id='addUserFormUI' border='0' vspace='0' hspace='0' 
		    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='no' 
		    	style="height:90%;width:100%;left:10px;top:8px" src="">
		    </iframe>
	    </div>
	     <div id="dlg-buttons">
	        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
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
   function good(value){
	   var data = value.split(",");
	   var str = "";
	   for(var i=0; i<data.length; i++){
		   str +=data[i]+ "<br/>";
       }
	   return str;
   };
   		function conversion(value){
       		var time = new Date(value);
       		return time.format("yyyy-MM-dd hh:mm:ss");
   		};
		function formatType(val,row){
			if (val == "1"){
				return '<span>添加</span>';
			} else if(val == "2"){
				return '<span>修改</span>';
			} else{
				return '<span>删除</span>';
			}
		}
	</script>
    <script type="text/javascript">
	   var url;
	   
	   function searchApp(){
		   $('#ff').form('submit',{  
			    url: "paymentRecoment/findAll",
			    onSubmit: function(){  
			    	return $(this).form('validate'); 
			    },  
			    success:function(data){  
			    	var data = eval('('+data+')');
			       $('#dg').datagrid('loadData',data);
			    }
			});  

	   }
        function details(){
        	  var row = $('#dg').datagrid('getSelected');
              if (row){
            	  console.log(row.goodsCode);
              	$('#addUserFormUI').attr("src","order/details/"+row.orderId+"/"+row.goodsCode);
                  $('#dlg').dialog('open').dialog('setTitle','详情');
              }else{
              	$.messager.alert('提示信息','请先选择要查看的记录。','info');
              }
        }
        /* 编辑时，进行页面跳转的时候传入所选的row的id，然后把值传入到子画面，子画面根据该值来加载数据 */
        function delivery(){
        	
            var row = $('#dg').datagrid('getSelected');
            if (row){
            	if(row.status=="02"){
            		$('#addUserFormUI').attr("src","order/delivery/"+row.orderId+"/"+row.goodsCode);
                    $('#dlg').dialog('open').dialog('setTitle','发货');
            	} else{
            		$.messager.alert('提示信息','改订单不能发货','info');
            	}
            	
            }else{
            	$.messager.alert('提示信息','请先选择要发货的订单。','info');
            }
        }
        
function closeOrder(){
        	
            var row = $('#dg').datagrid('getSelected');
            if (row){
            	if(row.status=="01"){
            		$('#addUserFormUI').attr("src","order/close/"+row.orderId+"/"+row.goodsCode);
                    $('#dlg').dialog('open').dialog('setTitle','关闭');
            	}else{
            		$.messager.alert('提示信息','改订单不能关闭','info');
            	}
            	
            }else{
            	$.messager.alert('提示信息','请先选择要关闭的订单。','info');
            }
        }
function returnGood(){
	
    var row = $('#dg').datagrid('getSelected');
    if (row){
    	if(row.status=="04"){
    		$('#addUserFormUI').attr("src","order/returnGood/"+row.orderId+"/"+row.goodsCode);
            $('#dlg').dialog('open').dialog('setTitle','退货');
    	}else{
    		$.messager.alert('提示信息','改订单不能退货','info');
    	}    	
    }else{
    	$.messager.alert('提示信息','请先选择要退货的订单。','info');
    }
}

function refund(){
	
    var row = $('#dg').datagrid('getSelected');
    if (row){
    	if(row.status=="08"){
    		$('#addUserFormUI').attr("src","order/refund/"+row.orderId+"/"+row.goodsCode);
            $('#dlg').dialog('open').dialog('setTitle','退款');
    	} else{
    		$.messager.alert('提示信息','改订单不能退款','info');
    	}   	
    }else{
    	$.messager.alert('提示信息','请先选择要退款的订单。','info');
    }
}
        
        function saveUser(){
          /*   $('#fm').form('submit',{
                url: url,
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success: function(result){
                    var result = eval('('+result+')');
                    if ("Error"==result.type){
                        $.messager.show({
                            title: 'Error',
                            msg: result.msg
                        });
                    } else {
                    	$.messager.show({
                            title: 'Success',
                            msg: result.msg,
                            timeout:5000,
                        	showType:'slide'
                        });
                    	// TODO:加载数据
                        $('#dlg').dialog('close');        
                        $('#dg').datagrid('reload');    
                    }
                }
            }); */
            /* 调用共通js中是否为IE的判定方法 */
    		if(isIE()) {
    			window.frames["addUserFormUI"].optSubmit();
    		} else {
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
        
        function status(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
            	if(row.status=='0'){
            		 $.messager.confirm('提示','确定要禁用吗?',function(r){
                         if (r){
                             $.post('city/delete',{cityId:row.cityId,status:row.status},function(result){
                                 if (result.type=="Success"){
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
            		 $.messager.confirm('提示','确定要启用吗?',function(r){
                         if (r){
                             $.post('city/delete',{cityId:row.cityId,status:row.status},function(result){
                                 if (result.type=="Success"){
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
            	}
               
            }else{
            	$.messager.alert('提示信息','请先选择要启用的记录。','info');
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
    
    </script>
</body>
</html>