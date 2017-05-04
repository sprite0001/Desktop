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
	<script type="text/javascript" src="${basePath}static/js/validator.js"></script>
</head>
<body>
	<div class="ftitle"></div>
    <form id="fm" method="post" novalidate enctype="multipart/form-data">
        <div class="fitem">
            <label for="villageId">小区:</label>
            <input id="villageName" name="villageName" class="easyui-textbox" data-options="required:true" readonly="readonly">
            <input id="villageIdButton" type="button" value="选择" onclick="toSelectVillage()">
        </div>
        <div class="fitem">
            <label for="storeName">店名:</label>
            <input id="storeName" name="storeName" class="easyui-textbox" data-options="required:true,validType:'isBlank'" style="width: 150px;">
            <label for="status">经营种类:</label>
                <input id="typeId" name="typeId" class="easyui-combobox" 
					data-options="valueField:'value',textField:'lable',url:'common/initSysData/${STORETYPE }'" required="required">
        </div>
        <div class="fitem">
            <label for="minDeliverPrice">起送金额:</label>
            <input id="minDeliverPrice" name="minDeliverPrice" class="easyui-numberbox" precision="2" max="99999999999.99" required="required">
            <label for="deliveryNumber">送货电话:</label>
            <input id="deliveryNumber" name="deliveryNumber" class="easyui-textbox" required="required">
        </div>
        <div class="fitem">
            <label for="contcatName">联系人:</label>
            <input id="contcatName" name="contcatName" class="easyui-textbox" required="required" style="width: 150px;">
            <label for="contcatNumber">电话:</label>
            <input id="contcatNumber" name="contcatNumber" class="easyui-textbox" required="required" style="width: 150px;">
        </div>
        <div class="fitem">
            <label for="openingTime">营业时间:</label>
            <input id="openingTime" name="openingTime" class="easyui-timespinner" style="width: 150px;" data-options="min:'08:00',showSeconds:true">
            <label for="closingTime">关门时间:</label>
            <input id="closingTime" name="closingTime" class="easyui-timespinner" style="width: 150px;" data-options="min:'08:00',showSeconds:true">
        </div>
        <div class="fitem">
            <label for="legalPerson">法人:</label>
            <input id="legalPerson" name="legalPerson" class="easyui-textbox" data-options="required:true,validType:'isBlank'" style="width: 150px;">
            <label for="legalPersonId">法人身份证:</label>
            <input id="legalPersonId" name="legalPersonId" class="easyui-textbox" data-options="required:true,validType:'isBlank'" style="width: 150px;">
        </div>
        <div class="fitem">
            <label for="organizationCode">组织代码证:</label>
            <input id="organizationCode" name="organizationCode" class="easyui-textbox" data-options="required:true,validType:'isBlank'" style="width: 150px;">
            <label for="storeAdress">地址:</label>
            <input id="storeAdress" name="storeAdress" class="easyui-textbox" data-options="required:true,validType:'isBlank'" style="width: 150px;">
        </div>
        <div class="fitem">
            <label for="likingBaseValue">点赞起始值:</label>
            <input id="likingBaseValue" name="likingBaseValue" class="easyui-numberbox" required="required" style="width: 150px;">
            <label for="userId">店长用户:</label>
           	<input id="userId" name="userId" class="easyui-combobox" 
				data-options="valueField:'id',textField:'realName',url:'cooperationStore/selectShopkeeperByUserType/${USERTYPE_05 }/0'" style="width: 100px;" required="required">
        </div> 
        <div class="fitem">
            <label for="thumbnailfile">缩略图:</label>
            <input id="thumbnailfile" name="file" type="text" class="easyui-filebox" required="required" style="width: 300px;">
        </div>
        <div class="fitem">
            <label for="businessLicensefile">营业执照:</label>
            <input id="businessLicensefile" name="file" type="text" class="easyui-filebox" required="required" style="width: 300px;">
        </div>
        <div class="fitem">
            <label for="remark">简介:</label>
            <textarea id="remark" name="remark" class="easyui-validatebox" data-options="required:true,validType:'isBlank'" style="width: 450px;height:200px;"></textarea>
        </div>
        <input type="hidden" id="villageId" name = "villageId">
    </form>
    <div id="village_dlg" class="easyui-dialog" style="padding:10px 20px;width: 600px;height: 400px;"
           closed="true" buttons="#village_dlg_buttons" modal="true">
	  	<iframe id='addVillageFormUI' border='0' vspace='0' hspace='0' 
	    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
	    	style="height:98%;width:100%;left:10px;top:8px" src="">
	    </iframe>
    </div>
    <div id="village_dlg_buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveSubmit()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#village_dlg').dialog('close')" style="width:90px">取消</a>
    </div>
</body>
<script type="text/javascript">
$('#thumbnailfile').filebox({
    buttonText: '选择缩略图',
    buttonAlign: 'right',
    accept: 'image/*'
});
$('#businessLicensefile').filebox({
    buttonText: '选择营业执照',
    buttonAlign: 'right',
    accept: 'image/*'
});
function saveSubmit(){
    /* 调用共通js中是否为IE的判定方法 */
    if(isIE()) {
        window.frames["addVillageFormUI"].saveVillage();
    } else {
        window.frames["addVillageFormUI"].contentWindow.saveVillage();
    }
}

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
function toSelectVillage(){
	$('#addVillageFormUI').attr("src","cooperationStore/initVillagePage/null/null");
    $('#village_dlg').dialog('open').dialog('setTitle','小区选择');
}
function optSubmit(){
    $('#fm').form('submit',{
        url: "cooperationStore/saveCooperationStore",
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
                    timeout:1000,
                	showType:'slide'
                });
            	setTimeout('window.parent.close()',2000);
            }
        }
    });
}
</script>
</html>