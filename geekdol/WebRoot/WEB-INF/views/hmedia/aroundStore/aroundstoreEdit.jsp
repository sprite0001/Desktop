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
	<style type="text/css">
	.village_choose {
		padding: 10px 20px;
		width: 500px;
		height: 380px;
		overflow: hidden;
	}
	</style>
</head>
<body>
	<div class="ftitle"></div>
    <form id="fm" method="post" novalidate>
    	<div class="div_fitem_1">
            <label class="lable_add" for="name">店名:</label>
            <input id="name" name="name" class="easyui-textbox" data-options="required:true,validType:'isBlank'" style="width: 150px;">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="villageId">小区:</label>
            <input id="villageId" name="villageId" type="hidden">
            <input id="villageName" name="villageName" class="easyui-textbox" data-options="editable:false,required:true">
            <input type="button" value="选择" onclick="toSelectVillage()">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="adress">地址:</label>
            <input id="adress" name="adress" class="easyui-textbox" data-options="required:true,validType:'isBlank'" style="width: 150px;">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="storeType">类型:</label> 
				<input id="storeType" name="storeType" class="easyui-combobox" 
					data-options="editable:false,required:true,valueField:'value',textField:'lable',url:'common/initSysData/${STORETYPE}'">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="concat">联系人:</label>
            <input id="concat" name="concat" class="easyui-textbox" data-options="required:true,validType:'isBlank'" style="width: 150px;">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="concatPhone">电话:</label>
            <input id="concatPhone" name="concatPhone" class="easyui-textbox" data-options="required:true,validType:'phoneRex'" style="width: 150px;">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="hoursStart">营业时间:</label>
            <input id="hoursStart" name="hoursStart" class="easyui-timespinner" style="width: 150px;" data-options="min:'08:00',showSeconds:true">
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="hoursEnd">关门时间:</label>
            <input id="hoursEnd" name="hoursEnd" class="easyui-timespinner" style="width: 150px;" data-options="min:'08:00',showSeconds:true">
        </div>
        <div class="div_fitem_1" style="width:100%;position:relative;">
            <div style="width:25%;position:absolute">
        		<label class="lable_add" style="display: initial" for="introduce">简介:</label>
        	</div>
            <textarea id="introduce" name="introduce" maxlength="200" class="easyui-validatebox" data-options="required:true,validType:'isBlank'" style="margin-left:13%;width:78%;height:200px;"></textarea>
        </div>
        <input name="id" id="id" type="hidden" value="${id}">
    </form>
    <div id="village_dlg" class="easyui-dialog village_choose" style="width:480px;height:370px;"
           closed="true" buttons="#village_dlg_buttons" modal="true">
	  	<iframe id='addVillageFormUI' border='0' vspace='0' hspace='0' 
	    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
	    	style="height:100%;width:100%;left:10px;top:8px" src="">
	    </iframe>
    </div>
    <div id="village_dlg_buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveSubmit()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#village_dlg').dialog('close')" style="width:90px">取消</a>
    </div>
</body>
<script type="text/javascript">
$('#hoursStart').timespinner({
    min: '08:00',
    required: true,
    showSeconds: true
});
$('#hoursEnd').timespinner({
    max: '24:00',
    required: true,
    showSeconds: true
});
function conversion(value){
	var time = new Date(value);
	return time.format("HH:mm:ss");
};
function saveSubmit(){
    /* 调用共通js中是否为IE的判定方法 */
    if(window.frames["addVillageFormUI"].contentWindow == undefined) {
        window.frames["addVillageFormUI"].saveVillage();
    } else {
        window.frames["addVillageFormUI"].contentWindow.saveVillage();
    }
}
function toSelectVillage(){
	var villageId=$("#villageId").val();
	$('#addVillageFormUI').attr("src","aroundStore/selectVillage?villageId="+villageId);
	/* var id = $('#id').val();
	$('#addVillageFormUI').attr("src","aroundStore/initVillagePage/"+id); */
    $('#village_dlg').dialog('open').dialog('setTitle','小区选择');
}
var url= "aroundStore/updateAroundStoreStatus";
var id = $('#id').val();
$('#fm').form('load','aroundStore/findById/'+id+"?t="+new Date().getTime());
function optSubmit(){
	var hsh = $('#hoursStart').timespinner('getHours');
	var hsm = $('#hoursStart').timespinner('getMinutes');
	var hss = $('#hoursStart').timespinner('getSeconds');
	var heh = $('#hoursEnd').timespinner('getHours');
	var hem = $('#hoursEnd').timespinner('getMinutes');
	var hes = $('#hoursEnd').timespinner('getSeconds');
	if(heh < hsh){
        $.messager.show({
            title: 'Info',
            msg: "结束时间应大于开始时间！",
            timeout:1000,
            showType:'slide'
        });
        return;
	} else {
		if (heh == hsh) {
			if(hem < hsm){
		        $.messager.show({
		            title: 'Info',
		            msg: "结束时间应大于开始时间！",
		            timeout:1000,
		            showType:'slide'
		        });
		        return;
			}
		} else {
			if(hes < hss){
		        $.messager.show({
		            title: 'Info',
		            msg: "结束时间应大于开始时间！",
		            timeout:1000,
		            showType:'slide'
		        });
		        return;
			}
		}
	}
    $('#fm').form('submit',{
        url: "aroundStore/updateAroundStore",
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if ("Error"==result.type){
                $.messager.show({
                    title: 'Error',
                    msg: result.Msg
                });
                
            } else {
            	$.messager.show({
                    title: 'Success',
                    msg: result.Msg,
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