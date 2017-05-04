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
    
    <!-- ueditor begin -->
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/hmedia_ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${basePath}static/ueditor/lang/zh-cn/zh-cn.js"></script>
    <!-- ueditor end -->
</head>
<body>
    <div class="ftitle"></div>
        <form id="fm" method="post" novalidate enctype="multipart/form-data">
            <div class="fitem">
                <label>标题:</label>
                <input name="title" class="easyui-textbox" data-options="required:true,validType:['isBlank','length[1,100]']">
            </div>
          <!--   <div class="div_fitem_1">
                <label class="lable_add">摘要:</label>
                <textarea name="summary" class="easyui-validatebox" style="vertical-align: top;" data-options="required:true,validType:'isBlank'"></textarea>
            </div> -->
            <div class="fitem" >
                <label >图片:</label>  
                <input  style="width: 350px;" type="text" class="easyui-filebox" id="file" name="file" >
                <img id="picture0"  style="width: 60px;height: 60px;cursor: pointer;" onclick="showPic(this)">
                <span style="color: red;">(650px*250px)</span>
            </div>
            <div class="fitem">
                <label>所属小区:</label>
                <input id="villageName" name="villageName" class="easyui-textbox" data-options="editable:false,required:true"/>
                <input type="button" value="选择" onclick="toSelectVillage()"/>
            </div>
            <div class="fitem">
                  <label>开始时间:</label> 
                  <input class="easyui-datetimebox" type="text" id="startTimeStr" name="startTimeStr"  style="width: 150px;" data-options="required:true" editable="false"/>
            </div>
            <div class="fitem">     
                  <label >结束时间:</label> 
                  <input class="easyui-datetimebox" type="text" id="endTimeStr" name="endTimeStr"  style="width: 150px;" data-options="required:true" editable="false"/>
            </div>
            
            <div class="fitem">
             <label >需要报名:</label>
             <input  name="enrollFlag" value="1" type="radio"  checked="checked">${xybm}
             <input  name="enrollFlag" value="0" type="radio" >${bxybm}    
            </div>
            
            <div class="fitem" id="baoming">
                  <label>报名截止时间:</label> 
                  <input class="easyui-datetimebox" type="text" name="enrollEndStr" id="enrollEndStr" style="width: 150px;"  editable="false"/>
            </div>
            
             <div class="fitem">     
               <label>活动汇商家:</label> 
               <input id="activityShops" name="activityShops" class="easyui-combobox" 
                    data-options="valueField:'id',textField:'realName',url:'common/getUserByUserType/${USERTYPE_07}',required:true,editable:false" style="width: 100px;" data-options="required:true"/>
             </div>
             
             <div class="fitem">
              <div style="float: left;">
              <label style="display:inline-block;width:60px">置顶:</label>
              <input id="topFlagCheckbox" type="checkbox" value="" />
              </div>
              <div id="topFlagDiv" style="display:none;margin-left:10px;float:left;">
               <label for="topStart">置顶开始时间:</label> 
               <input class="easyui-datetimebox" type="text" name="topStartStr" id="topStartStr" style="width: 150px;" editable="false"/>
               &nbsp;&nbsp;&nbsp;
               <label for="topEnd">置顶结束时间:</label> 
               <input class="easyui-datetimebox" type="text" name="topEndStr" id="topEndStr" style="width: 150px;" editable="false"/>
              </div>
             </div>
             
           <!--   <div class="fitem">
               <label>允许回复:</label>
               <input id="replyFlagCheckbox" type="checkbox" value="" /> 
            </div>  -->
            
            <div class="fitem" style="margin: 44px 0 0 0">
              <div style="width:10%;float:left;">
                <label>内容:</label>
              </div>
               <div style="margin-left:-60px;float:left;">
              <script id="editor" name="content" type="text/plain" style="margin-left:4%;width:600px;height:500px;">${content}</script>
              </div>
             </div>
            
             <input type="hidden" id="villageId" name = "villageId">
             <input id="topFlag" name="topFlag"  type="hidden" value="${topFlag}" />
             <input id="replyFlag" name="replyFlag"  type="hidden" value="${replyFlag}" />
             
             <input name="id" id="currenId" type="hidden" value="${id}">
            
             <!-- 报名截止时间 -->
             <input name="enrollEnd" id="enrollEnd" type="hidden" >
             <!--  活动开始时间-->
             <input name="startTime" id="startTime" type="hidden" >
             <!--  活动结束时间-->
             <input name="endTime" id="endTime" type="hidden" >
             <!-- 置顶开始时间  -->
             <input name="topStart" id="topStart" type="hidden" >
             <!-- 置顶结束时间 -->
             <input name="topEnd" id="topEnd" type="hidden" >
            
              <!-- 发布状态 -->
             <input  id="publishStatus" name="publishStatus"  type="hidden" />
             
              <!-- 令牌防止重复提交  -->
             <input type="hidden" name="token" id="token" value="${token}" />
             
              <!-- 图片默认路径 -->
             <input name="pic1" id="pic1" type="hidden" />
             
             
        </form>
        
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

var ue = UE.getEditor('editor');

$('#file').filebox({
    buttonText: '选择图片',
    buttonAlign: 'right',
    accept: 'image/*'
});

var id = $('#currenId').val();
$('#fm').form({onLoadSuccess:loadsucc});
$('#fm').form('load','activityCollection/findById/'+id+"?t="+new Date().getTime());

//回调函数
function loadsucc(data)  
{  
     //获取默认选中值
     var type=$('input:radio:checked').val();
     if(type==1){
         $("#baoming").show();
     }else{
         $("#baoming").hide();
     }
    
    //报名截止日期
    $(":radio").click(function(){
          var cunnent=$(this).val();
           if(cunnent=="1"){   //如果需要报名
               $("#baoming").show();
               $("#enrollEndStr").datebox({"required":true});
           }else{
               $("#baoming").hide();
               $("#enrollEndStr").datebox({"required":false});
           } 
     });
    
    var topFlag = "${topFlag}";
    if(topFlag == "1"){
        $("#topFlagCheckbox").attr("checked",true);
        $("#topFlagDiv").show();
    }
    
    var replyFlag = "${replyFlag}";
    if(replyFlag == "1"){
        $("#replyFlagCheckbox").attr("checked",true);
    }
    
    //是否置顶
    $("#topFlagCheckbox").change(function(){
        if($("#topFlagCheckbox").is(":checked")){
            $("#topFlag").val("1"); //置顶为1
            $("#topFlagDiv").show();
            $("#topStartStr").datebox({"required":true});
            $("#topEndStr").datebox({"required":true});
        }else{
            $("#topFlag").val("0"); //不置顶为0
            $("#topFlagDiv").hide();
            $("#topStartStr").datebox({"required":false});
            $("#topEndStr").datebox({"required":false});
        }
    });
    
    //是否允许回复
    $("#replyFlagCheckbox").change(function(){
        if($("#replyFlagCheckbox").is(":checked")){
            $("#replyFlag").val("1"); //允许回复为1
        }else{
            $("#replyFlag").val("0"); //不允许回复为0
        }
    });
    
      //显示图片
      var iconString=data.pic;
       $("#picture0").attr("src",iconString); 
       $("#pic1").val(iconString);
    
}  
//点击打开大图
function showPic(obj)  
{  
 window.open(obj.src);
}  


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

//点击关闭按钮   调用的事件
function closeDialog(){
    $('#village_dlg').dialog('close');
    parent.$('#saveBtnA').attr('onclick',"saveUser('0')"); 
    parent.$('#saveBtnB').attr('onclick',"saveUser('1')"); 
    parent.$('#closeBtn').attr('onclick',"javascript:$('#dlg').dialog('close')");
}
function toSelectVillage(){
	
	//点击x号关闭调用的地方(此方法必须放在dialog打开之前)
    $('#village_dlg').dialog({
        onClose:function(){
              //恢复按钮点击事件
             parent.$('#saveBtnA').attr('onclick',"saveUser('0')"); 
             parent.$('#saveBtnB').attr('onclick',"saveUser('1')"); 
             parent.$('#closeBtn').attr('onclick',"javascript:$('#dlg').dialog('close')");  
        }
    });
    
	
	    var villageId=$("#villageId").val();
        $('#addVillageFormUI').attr("src","activityCollection/selectVillage?villageId="+villageId);
        $('#village_dlg').dialog('open').dialog('setTitle','小区选择');

        //禁掉下面的按钮
        parent.$('#saveBtnA').removeAttr('onclick');//去掉a标签中的href属性
        parent.$('#saveBtnB').removeAttr('onclick');//去掉a标签中的href属性
        parent.$('#closeBtn').removeAttr('onclick');//去掉a标签中的href属性
}


function optSubmit(publishStatus){
	
	
    var str = $("#villageId").val();
    if(str == null || str == ""){
        $.messager.show({
            title: 'Info',
            msg: "请选择小区！",
            timeout:1000,
            showType:'slide'
        });
        return;
    }
    var length = UE.getEditor('editor').getContentLength(true);
    if(length > 10000){
        $.messager.show({
            title: 'Info',
            msg: "内容字数大于10000，无法保存！",
            timeout:1000,
            showType:'slide'
        });
        return;
    }
    if(length <= 0){
        $.messager.show({
            title: 'Info',
            msg: "内容不能为空或全为空格！",
            timeout:1000,
            showType:'slide'
        });
        return;
    }
	
    $('#publishStatus').val(publishStatus);
    $('#enrollEnd').val($('#enrollEndStr').datetimebox('getValue'));
    $('#topStart').val($('#topStartStr').datetimebox('getValue'));
    $('#topEnd').val($('#topEndStr').datetimebox('getValue'));
    $('#startTime').val($('#startTimeStr').datetimebox('getValue'));
    $('#endTime').val($('#endTimeStr').datetimebox('getValue'));
    
    
    var startTime = $('#startTimeStr').datetimebox('getValue');
    var endTime = $('#endTimeStr').datetimebox('getValue');
    var start = new Date(startTime);
    var end = new Date(endTime);
    if(start >= end){
        $.messager.show({
            title: 'Info',
            msg: "结束时间应大于开始时间！",
            timeout:1000,
            showType:'slide'
        });
        return;
    }
    
    //当为置顶时
    if($('#topFlagCheckbox').is(':checked')){
        var topStartStr = $('#topStartStr').datetimebox('getValue');
        var topEndStr = $('#topEndStr').datetimebox('getValue');
        var topStart = new Date(topStartStr);
        var topEnd = new Date(topEndStr);
        if(topStart >= topEnd){
            $.messager.show({
                title: 'Info',
                msg: "置顶结束时间应大于置顶开始时间！",
                timeout:1000,
                showType:'slide'
            });
            return;
        }
    } 
    
    $('#fm').form('submit',{
        url: "activityCollection/updateActivityCollection",
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
                setTimeout('window.parent.close()',1000);
            }
        }
    });
}
</script>
</html>