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
 <form id="fm" method="post" novalidate style="height: 320px; width: 100%;">
        	<div class="fitem">
        		<table width="700" height="300" border="0">
					  <tr>
					    <td height="30" colspan="2" align="center">订单信息</td>
					    <td width="50">&nbsp;</td>
					    <td colspan="2" align="center">付款信息</td>
					  </tr>
					  <tr>
					    <td width="50" height="30">订单编号</td>
					    <td width="200"><input name="code" class="easyui-textbox" readonly="readonly"></td>
					    <td>&nbsp;</td>
					    <td width="50">付款金额</td>
					    <td width="252"><input name="paymentAmount" class="easyui-textbox" readonly="readonly"></td>
					  </tr>
					  <tr>
					    <td height="30">商品编号</td>
					    <td><input name="serialCode" class="easyui-textbox" readonly="readonly"></td>
					    <td>&nbsp;</td>
					    <td>付款方式</td>
					    <td><input name="payMode" class="easyui-textbox" readonly="readonly"></td>
					  </tr>
					  <tr>
					    <td height="30">状态</td>
					    <td><input name="orderStatus" class="easyui-textbox" readonly="readonly"></td>
					    <td>&nbsp;</td>
					    <td>付款时间</td>
					    <td><input name="payTime" class="easyui-textbox" readonly="readonly"></td>
					  </tr>
					  <tr>
					    <td height="30" colspan="2" align="center">收货人信息</td>
					    <td>&nbsp;</td>
					    <td colspan="2" align="center">配送信息</td>
					  </tr>
					  <tr>
					    <td height="30">收货人</td>
					    <td><input name="receiverName" class="easyui-textbox" readonly="readonly"></td>
					    <td>&nbsp;</td>
					    <td>配送方式</td>
					    <td><input name="deliveryMode" class="easyui-textbox" style="width:70px " readonly="readonly"><input name="logisticsNumber" class="easyui-textbox" readonly="readonly"></td>
					  </tr>
					  <tr>
					    <td height="30">地址</td>
					    <td><input name="receiverAdress" class="easyui-textbox" readonly="readonly"></td>
					    <td>&nbsp;</td>
					    <td>联系电话</td>
					    <td><input name="phone" class="easyui-textbox" readonly="readonly"></td>
					  </tr>
					  <tr>
					    <td height="30">手机号码</td>
					    <td><input name="receiverPhone" class="easyui-textbox" readonly="readonly"></td>
					    <td>&nbsp;</td>
					    <td>运费</td>
					    <td><input name="freight" class="easyui-textbox" readonly="readonly"></td>
					  </tr>
					</table>               
            </div>
            <input name="id" id="id" type="hidden" value="${id}">
            <input name="serialCode" id="serialCode" type="hidden" value="${serialCode}">         
        </form> 
     <table id="dg"  class="easyui-datagrid" style="height: 220px;"
            url="groupBuyOrder/detailsCodeInit/${id}" 
            toolbar="#toolbar" pagination="false" queryParams="form2Json('ff')" fitColumns="true" singleSelect="true" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
            	<th field="ck" checkbox="false"></th>
            	<th field="id" width="10%" >序号</th>
                <th field="code" width="40%">团购码</th>
                <th field="status" width="10%" formatter="formatType">状态</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
    </div>
   <script type="text/javascript">
   var id = $('#id').val();
   var serialCode = $('#serialCode').val();
   $('#fm').form('load','groupBuyOrder/detailsInit/'+id+"/"+serialCode);
		function formatType(val,row){
			if (val == "0"){
				return '<span>未验证</span>';
			} else if(val == "1"){
				return '<span>已验证</span>';
			}
			else{
				return '<span>已失效</span>';
			}
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
          	  console.log(row.goodsCode);
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