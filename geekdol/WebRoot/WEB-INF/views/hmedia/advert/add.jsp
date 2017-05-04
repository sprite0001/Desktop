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
            <div class="div_fitem_1">
              <label class="lable_add">编号:</label>
              <input id="advertCode" name="advertCode" value="" class="easyui-textbox" data-options="required:true,validType:['isBlank','engAndNum','length[1,20]']">
            </div>
            <div class="div_fitem_1">
              <label class="lable_add">广告位置:</label> 
              <input id="positionName" name="positionName" class="easyui-textbox"  data-options="editable:false,required:true">
              <input id="positionId" name="positionId"  hidden="true">
              <input type="button" value="选择" onclick="toSelectPositionName()"> 
            </div>
            <div class="div_fitem_1">
              <label class="lable_add">广告方:</label>
              <input id="advertOwner" name="advertOwner" value="" class="easyui-textbox" data-options="required:true,validType:['isBlank','cnennum','length[1,100]']">
            </div>
            <div class="div_fitem_1">
              <label class="lable_add">总金额:</label> 
              <input id="advertMoney" name="advertMoney" value="" class="easyui-numberbox" precision="0" min="0" data-options="required:true,validType:'isBlank'">
            </div>
            <div class="div_fitem_1">
              <label class="lable_add">联系人:</label>
              <input id="advertContacts" name="advertContacts" value="" class="easyui-textbox" data-options="required:true,validType:['isBlank','inputCnEn','length[1,20]']">
            </div>
            <div class="div_fitem_1">
              <label class="lable_add">联系电话:</label> 
              <input id="contactNumber" name="contactNumber" class="easyui-textbox"  data-options="required:true,validType:'isBlank',validType:'phoneRex'">
            </div>
            <div class="div_fitem_1">
                <label class="lable_add">开始时间:</label>
                <input class="easyui-datebox" type="text" name="startTime" id="startTime" style="width: 150px;" required="true" editable="false"/>
            </div>
            <div class="div_fitem_1"> 
                <label class="lable_add">结束时间:</label> 
                <input class="easyui-datebox" type="text" name="endTime" id="endTime" style="width: 150px;" required="true" editable="false"/>
            </div>
              <div class="div_fitem_1">
              <label class="lable_add">显示顺序:</label>
              <input id="ordering" name="ordering" value="" class="easyui-textbox" data-options="validType:'integer'">
            </div>
            <div class="div_fitem_1">
              <label class="lable_add">投放区域:</label>
              <input id="villageName" name="villageName" class="easyui-textbox" data-options="required:true" editable="false">
              <input type="hidden" id="villageId" name ="villageId">
              <input type="button" value="选择" onclick="toSelectVillage()">
             </div>
            <div class="div_fitem_1" style="width: 100%">
                <input type="radio" name="adverType" value="1" checked="true" hidden="true">
                <label hidden="true">图片广告</label>
                <label class="lable_add" style="width: 13%">广告图片:</label>
                <input style="width: 190px;" name="file" id="file1" type="text" class="easyui-filebox" required="true" >
                <br><font style="color: #FF0000; margin-left: 13%;">请注意: 图片请按照宽720px,高280px进行上传,否则会变形失真</font>
            </div>
            
            <div class="fitem" hidden="true">
                <input type="radio"  name="adverType" value="2">
                <label>文字广告</label>
                &nbsp;&nbsp;&nbsp;
                <label>广告内容:</label>
                <textarea id="adverContent" name="adverContent" class="easyui-validatebox" style="vertical-align:top;width:185px"></textarea>
                &nbsp;
                <input type="radio" id="scrollType" name="scrollType" value="1"><label for="scrollType">上下滚动</label>
                <input type="radio" id="scrollType" name="scrollType" value="2"><label for="scrollType">左右滚动</label>
                <input type="radio" id="scrollType" name="scrollType" value="3"><label for="scrollType">静止</label>
            </div>
            
            <div class="div_fitem_1">
                <label class="lable_add"></label>
            </div>
             <div class="div_fitem_1">
                <label class="lable_add"></label>
            </div>
            
           <div class="div_fitem_1" style="width:100%;">
                <label class="lable_add" style="width:13%;">广告链接形式:</label>
                <input type="radio" id="linkType" name="linkType" value="4" checked="checked"><label for="linkType">网址</label>
               &nbsp;&nbsp;&nbsp;&nbsp;
                <input id="linkContenturl" name="urlString" class="easyui-textbox" style="width:190px" data-options="required:true,validType:['isBlank','web','length[1,200]']" >
                <!-- <input type="radio" id="linkType" name="linkType" value="1" checked="true"><label for="linkType">图片</label>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <input   style="width: 190px;" name="file" id="file2" type="text" class="easyui-filebox" required="true" > -->
           </div>
           <div class="div_fitem_1">
                <label class="lable_add">广告标题:</label>
                <input id="advertTitle" name="advertTitle" class="easyui-textbox" style="width:190px" data-options="required:true,validType:['isBlank','cnennum','length[1,10]']" >
           </div>
            <!-- <div class="fitem">
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; 
                 <input type="radio" id="linkType" name="linkType" value="2"><label for="linkType">Html</label>
                 &nbsp;&nbsp;&nbsp;
                 <input   style="width: 190px;" name="file" id="file3" type="text" class="easyui-filebox">
            </div> -->
            <!-- <div class="fitem"> 
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               <input type="radio" id="linkType" name="linkType" value="3"><label for="linkType">视频</label>
               &nbsp;&nbsp;&nbsp;&nbsp;
                <input   style="width: 190px;" name="file" id="file4" type="text" class="easyui-filebox">
            </div> -->
             <!-- <div class="fitem">   
                &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
               <input type="radio" id="linkType" name="linkType" value="4"><label for="linkType">网址</label>
               &nbsp;&nbsp;&nbsp;&nbsp;
                <input id="linkContenturl" name="urlString" class="easyui-textbox" style="width:190px" data-options="validType:'web'" >
            </div> -->
          
            <!-- 广告浏览量 -->
            <input type="hidden" name="scannNumber" value=0>
        </form>
        
        <div id="dlg" class="easyui-dialog" style="padding:10px 20px;width: 600px;height:350px;overflow:hidden"
            closed="true" buttons="#dlg-buttons" modal="true">
            <iframe id='addPositionNameFormUI' border='0' vspace='0' hspace='0' 
                marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='no' 
                style="height:100%;width:100%;left:10px;top:8px" src="">
            </iframe>
        </div>
          <div id="dlg-buttons">
            <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="savePositionName()" style="width:90px">选择</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
        </div>
        
        
        <div id="village_dlg" class="easyui-dialog" style="padding:10px 20px;width: 600px;height: 400px;"
           closed="true" buttons="#village_dlg_buttons" modal="true">
        <iframe id='addVillageFormUI' border='0' vspace='0' hspace='0' 
            marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
            style="height:100%;width:100%;left:10px;top:8px" src="">
        </iframe>
    </div>
    <div id="village_dlg_buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveSubmit()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeDialog()" style="width:90px">关闭</a>
    </div>
        
</body>
<script type="text/javascript">
$('#file1').filebox({
    buttonText: '选择图片',
    buttonAlign: 'right',
    accept: 'image/*'
});
$('#file2').filebox({
    buttonText: '选择图片',
    buttonAlign: 'right',
    accept: 'image/*'
});
$('#file3').filebox({
    buttonText: '选择Html',
    buttonAlign: 'right',
});
$('#file4').filebox({
    buttonText: '选择视频',
    buttonAlign: 'right',
});

function toSelectPositionName(){
    $('#addPositionNameFormUI').attr("src","advert/toAddPositioin");
    $('#dlg').dialog('open').dialog('setTitle','广告位置选择');
}



function savePositionName(){
    /* 调用共通js中是否为IE的判定方法 */
   if(window.frames["addPositionNameFormUI"].contentWindow==undefined){
        window.frames["addPositionNameFormUI"].savePositionName();
   }else{
        window.frames["addPositionNameFormUI"].contentWindow.savePositionName();
   }
}

//点击关闭按钮   调用的事件
function closeDialog(){
    $('#village_dlg').dialog('close');
    parent.$('#save').attr('onclick',"saveAdvert()"); 
    parent.$('#close').attr('onclick',"javascript:$('#dlg').dialog('close')");
}

function toSelectVillage(){
	
	//点击x号关闭调用的地方(此方法必须放在dialog打开之前)
    $('#village_dlg').dialog({
        onClose:function(){
        	//恢复按钮的属性
            parent.parent.$('#save').attr('onclick',"saveAdvert()"); 
            parent.parent.$('#close').attr('onclick',"javascript:$('#dlg').dialog('close')"); 
        }
    });
	
	var villageId=$("#villageId").val();
    var villageName= $('#villageName').textbox('getValue');
    $('#addVillageFormUI').attr("src","advert/selectVillage?villageId="+villageId);
    $('#village_dlg').dialog('open').dialog('setTitle','小区选择');
    
  //禁掉下面的按钮
    parent.$('#save').removeAttr('onclick');//去掉a标签中的href属性
    parent.$('#close').removeAttr('onclick');//去掉a标签中的href属性
}

$(function(){
    //广告类型
    $('input[name="adverType"]').click(function(){
            var cunnent=$(this).val();
           if(cunnent=="1"){ //图片广告  
               $("#file1").filebox({required:true});
               $("#adverContent").validatebox({required:false});
           }else{            //文字广告
               $("#file1").filebox({required:false});
               $("#adverContent").validatebox({required:true});
           }
          });
    
    /* //广告连接形式
    $('input[name="linkType"]').click(function(){
           var gg=$(this).val();
           if(gg=="1"){       //图片广告  
               $("#file2").filebox({required:true});
               $("#file3").filebox({required:false});
               $("#file4").filebox({required:false});
               $("#linkContenturl").textbox({required:false});
           }else if(gg=="2"){  //Html
               $("#file2").filebox({required:false});
               $("#file3").filebox({required:true});
               $("#file4").filebox({required:false});
               $("#linkContenturl").textbox({required:false});
           }else if(gg=="3"){  //视频
               $("#file2").filebox({required:false});
               $("#file3").filebox({required:false});
               $("#file4").filebox({required:true}); 
               $("#linkContenturl").textbox({required:false});
           }else{                   //网址
               $("#file2").filebox({required:false});
               $("#file3").filebox({required:false});
               $("#file4").filebox({required:false});
               $("#linkContenturl").textbox({required:true});
           }
          }); */
    
    
    
});

function saveSubmit(){
    /* 调用共通js中是否为IE的判定方法 */
    //console.log("浏览器标识："+navigator.userAgent);
    //360浏览器 为 undefined
    if(window.frames["addVillageFormUI"].contentWindow==undefined){
         window.frames["addVillageFormUI"].saveVillage();
    }else{
         window.frames["addVillageFormUI"].contentWindow.saveVillage();
    }
}

var url= "advert/saveAdvert";
function optSubmit(){
	$.ajax({
   		type: "POST",async:false,
   		url:"advert/checkAdvertCode",
   		dataType:"json",
   		data:{"advertCode":$('#advertCode').textbox('getValue'), "id":null},
   		async:false,
   		success: function(result){
       		if (result.type=='Error'){
       			$.messager.show({
       	            title: 'Warning',
       	            msg: '广告编码重复',
       	            timeout:1000,
       	        	showType:'slide'
       	        });
            } else {
            	var startTime = $('#startTime').datebox('getValue');
                var endTime = $('#endTime').datebox('getValue');
                startTime = startTime.replace(/-/g,"/");
            	endTime = endTime.replace(/-/g,"/");
                var start = new Date(startTime);
                var end = new Date(endTime);
                var nowTime = new Date();
                if(start >= end){
                    $.messager.show({
                        title: 'Info',
                        msg: "结束时间应大于开始时间！",
                        timeout:1000,
                        showType:'slide'
                    });
                    return;
                }
                if(nowTime >= end){
                    $.messager.show({
                        title: 'Info',
                        msg: "结束时间应大于当前时间！",
                        timeout:1000,
                        showType:'slide'
                    });
                    return;
                }
                var position = $("#positionId").val();
                if(position == null || position == ""){
                    $.messager.show({
                        title: 'Info',
                        msg: "请选择广告位置！",
                        timeout:1000,
                        showType:'slide'
                    });
                    return;
                }
                var str = $("#villageId").val();
                if(str == null || str == ""){
                    $.messager.show({
                        title: 'Info',
                        msg: "请选择投放位置！",
                        timeout:1000,
                        showType:'slide'
                    });
                    return;
                }
                $('#fm').form('submit',{
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
                                timeout:1000,
                                showType:'slide'
                            });
                            setTimeout('window.parent.close()',2000);
                        }
                    }
                });
            }
   		}
   	});
}
</script>
</html>