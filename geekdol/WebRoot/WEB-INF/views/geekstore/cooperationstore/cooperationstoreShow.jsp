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
	<div class="easyui-tabs" style="width:1030px;height:598px">
		<div title="基本信息" style="padding:10px">
		    <form id="fm" method="post">
		        <div class="fitem">
		            <label for="villageName">小区:</label>
		            <input id="villageName" name="villageName" class="easyui-textbox"  data-options="required:true" readonly="readonly">
		        </div>
		        <div class="fitem">
		            <label for="storeName">店名:</label>
		            <input id="storeName" name="storeName" class="easyui-textbox" required="required" style="width: 150px;" readonly="readonly">
		            <label for="status">经营种类:</label>
		                <input id="typeId" name="typeId" required="required" class="easyui-combobox" 
							data-options="valueField:'value',textField:'lable',url:'common/initSysData/${STORETYPE }'" readonly="readonly">
		        </div>
		        <div class="fitem">
		            <label for="minDeliverPrice">起送金额:</label>
		            <input id="minDeliverPrice" name="minDeliverPrice" class="easyui-textbox" readonly="readonly">
		            <label for="deliveryNumber">送货电话:</label>
		            <input id="deliveryNumber" name="deliveryNumber" class="easyui-textbox" readonly="readonly">
		        </div>
		        <div class="fitem">
		            <label for="contcatName">联系人:</label>
		            <input id="contcatName" name="contcatName" class="easyui-textbox" required="required" style="width: 150px;" readonly="readonly">
		            <label for="contcatNumber">电话:</label>
		            <input id="contcatNumber" name="contcatNumber" class="easyui-textbox" required="required" style="width: 150px;" readonly="readonly">
		        </div>
		        <div class="fitem">
		            <label for="openingTime">营业时间:</label>
		            <input id="openingTime" name="openingTime" formatter="conversion" class="easyui-timespinner" style="width: 150px;" data-options="min:'08:00',showSeconds:true" readonly="readonly">
		            <label for="closingTime">关门时间:</label>
		            <input id="closingTime" name="closingTime" formatter="conversion" class="easyui-timespinner" style="width: 150px;" data-options="min:'08:00',showSeconds:true" readonly="readonly">
		        </div>
		        <div class="fitem">
		            <label for="legalPerson">法人:</label>
		            <input id="legalPerson" name="legalPerson" class="easyui-textbox" required="required" style="width: 150px;" readonly="readonly">
		            <label for="legalPersonId">法人身份证:</label>
		            <input id="legalPersonId" name="legalPersonId" class="easyui-textbox" required="required" style="width: 150px;" readonly="readonly">
		        </div>
		        <div class="fitem">
		            <label for="organizationCode">组织代码证:</label>
		            <input id="organizationCode" name="organizationCode" class="easyui-textbox" required="required" style="width: 150px;" readonly="readonly">
		            <label for="storeAdress">地址:</label>
		            <input id="storeAdress" name="storeAdress" class="easyui-textbox" required="required" style="width: 150px;" readonly="readonly">
		        </div>
		        <div class="fitem">
		            <label for="likingBaseValue">点赞起始值:</label>
		            <input id="likingBaseValue" name="likingBaseValue" class="easyui-numberbox" required="required" style="width: 150px;" readonly="readonly">
		            <label for="userId">店长用户:</label>
		            	<input id="userId" name="userId" class="easyui-combobox" 
							data-options="valueField:'id',textField:'realName',url:'common/getUserByUserType/${USERTYPE_05 }'" style="width: 100px;" readonly="readonly">
		        </div> 
		        <div class="fitem">
		            <label for="thumbnail">缩略图:</label>
		            <img id="thumbnail" src="" width="80px" height="80px" onclick="showPic(this)" style="cursor: pointer;"/>
		        </div>
		        <div class="fitem">
		            <label for="businessLicense">营业执照:</label>
		            <img id="businessLicense" src=""  width="80px" height="80px" onclick="showPic(this)" style="cursor: pointer;"/>
		        </div>
		        <div class="fitem">
		            <label for="remark">简介:</label>
		            <textarea id="remark" name="remark" class="easyui-validatebox" required="required" style="width: 450px;height:200px;" readonly="readonly"></textarea>
		        </div>
		        <input name="id" id="id" type="hidden" value="${id}">
		    </form>
	    </div>
	    <div title="店铺订单" style="padding:10px">
	    	<form id="ff" method="post">
	            <div style="float: left; padding: 20px 0 0 20px;">
	                <label for="orderCode">订单编号:</label> 
	                <input class="easyui-textbox" type="text" name="orderCode" id="orderCode" />
	            </div>
	            <div style="float: left; padding: 20px 0 0 20px;">
	                <label for="goodsCode">商品编号:</label> 
	                <input class="easyui-textbox" type="text" name="goodsCode" id="goodsCode" />
	            </div>
	            <div style="float: left; padding: 20px 0 0 20px;">
	                <label for="buyerName">买方:</label> 
	                <input class="easyui-textbox" type="text" name="buyerName" id="buyerName" />
	            </div>
	            <div style="float: left; padding: 20px 0 0 20px;">
	                <label for="sellerName">卖方:</label> 
	                <input class="easyui-textbox" type="text" name="sellerName" id="sellerName" />
	            </div>
	            <div style="float: left; padding: 20px 0 0 20px;">
	                <label for="sellModel">销售模块:</label> 
	                <input id="sellModel" name="sellModel"  class="easyui-combobox" 
					data-options="valueField:'id',textField:'name',url:'order/sellerModel'">
	            </div>
	            <div style="float: left; padding: 20px 0 0 20px;">
	                <label for="status">状态:</label> 
	                <input id="status" name="status" class="easyui-combobox" 
					data-options="valueField:'value',textField:'lable',url:'common/initSysData/${ORDERSTATUS }'">
	            </div>
	            <div style="float: left; padding: 20px 0 0 20px;">
	                <input  type="button" onclick="searchOrder()" value="查询" />
	            </div>
	            <input name="storeId" id="storeId" type="hidden" value="${id}">
	        </form>
	    	<table id="dg_table" class="easyui-datagrid" style="height: 620px;float: left;"
		            url="cooperationStore/viewCooperationStoreOrder/${id}" 
		            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
		            fitColumns="true" singleSelect="false" 
		            data-options="fit:false,border:false,pageSize:20,pageList:[20,50,100,200]" >
		        <thead>
		            <tr>
		            	<th field="orderCode" width="150" >订单编号</th>
		                <th field="buyerName" width="150">买方</th>
		                <th field="payMode" width="150">支付方式</th>
		                <th field="classificName" width="150">销售模块</th>
		                <th field="goodsCode" width="150">商品编号</th>
		                <th field="costPrice" width="150">成本价</th>
		                <th field="sellPrice" width="150">销售价</th>
		                <th field="orderTime" width="150" formatter="conversion">下单时间</th>
		            </tr>
		        </thead>
		    </table>
	    </div>
    </div>
</body>
<script type="text/javascript">
$('#openingTime').timespinner({
    min: '08:00',
    required: true,
    showSeconds: true
});
$('#closingTime').timespinner({
    max: '24:00',
    required: true,
    showSeconds: true
});
function conversion(value){
	var time = new Date(value);
	return time.format("HH:mm:ss");
};
function conversion(value){
	var time = new Date(value);
	return time.format("yyyy-MM-dd hh:mm:ss");
};
function searchOrder(){
	var id = $('#storeId').val();
    $('#ff').form('submit',{  
         url: "cooperationStore/viewCooperationStoreOrder/"+id,  
         onSubmit: function(){  
             return $(this).form('validate'); 
         },  
         success:function(data){  
             var data = eval('('+data+')');
            $('#dg_table').datagrid('loadData',data);
         }       
     });
}
var id = $('#id').val();
$('#fm').form({onLoadSuccess:loadsucc}); 
$('#fm').form('load','cooperationStore/findById/'+id);
//回调函数
function loadsucc(data) {  
	$("#thumbnail").attr("src",data.thumbnail);
	$("#businessLicense").attr("src",data.businessLicense);
}  
//点击打开大图
function showPic(obj) {  
	window.open(obj.src);
}
</script>
</html>