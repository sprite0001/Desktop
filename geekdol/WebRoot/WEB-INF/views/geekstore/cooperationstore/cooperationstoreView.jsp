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