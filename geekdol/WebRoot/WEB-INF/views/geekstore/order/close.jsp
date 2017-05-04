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
</head>
<body>
	<div class="ftitle"></div>
        <form id="fm" method="post" novalidate style="height: 100px; width: 100%;">
            <div class="fitem">
                <label>关闭理由:</label>
                <textarea  name="orderCloseReasons" class="easyui-validatebox" required="true" style="height:50px; width: 500px"></textarea>
            </div>        	
            <input name="orderId" id="orderId" type="hidden" value="${orderId}">
            <input name="goodsCode" id="goodsCode" type="hidden" value="${goodsCode}">        
        </form>
        <form id="fm123" method="post" novalidate style="height: 100px; width: 100%;">
        	<div class="fitem">
            <table width="700" height="300" border="0">
					  <tr>
					    <td height="30" colspan="2" align="center">订单信息</td>
					    <td width="50">&nbsp;</td>
					    <td colspan="2" align="center">付款信息</td>
					  </tr>
					  <tr>
					    <td width="50" height="30">订单编号</td>
					    <td width="200"><input name="orderCode" class="easyui-textbox" readonly="readonly"></td>
					    <td>&nbsp;</td>
					    <td width="50">付款金额</td>
					    <td width="252"><input name="payAmount" class="easyui-textbox" readonly="readonly"></td>
					  </tr>
					  <tr>
					    <td height="30">商品编号</td>
					    <td><input name="goodsCode" class="easyui-textbox" readonly="readonly"></td>
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
        </form>
</body>
<script type="text/javascript">
var orderId = $('#orderId').val();
var goodsCode = $('#goodsCode').val();
$('#fm123').form('load','order/detailsInit/'+orderId+"/"+goodsCode);
var url = "";
function optSubmit(){
	var url= "order/colseSave/"+${orderId};
    $('#fm').form('submit',{
        url: url,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if (result.type=="Error"){
                $.messager.show({
                    title: 'Error',
                    msg: result.msg
                });
                
            } else {
            	$.messager.show({
                    title: 'Success',
                    msg: result.msg,
                    timeout:1000,
                	showType:'slide'
                });
            	setTimeout('window.parent.close()',1000);
            }
        }
    });
}


</script>
</html>