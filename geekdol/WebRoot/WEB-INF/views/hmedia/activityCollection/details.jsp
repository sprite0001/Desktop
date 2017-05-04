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
            <div class="div_fitem_1" style="width: 100%">
                <label class="lable_add" style="width: 13%">标题:</label>
                ${activityCollectionVo.title}
            </div>
         
            <div class="div_fitem_1" style="width: 100%">
                <label class="lable_add" style="width: 13%">图片:</label>  
                <img id="picture0"  style="width: 80px;height: 80px;cursor: pointer;" onclick="showPic(this)">
            </div>
            <div class="div_fitem_1" style="width: 100%">
                <label class="lable_add" style="width: 13%">所属小区:</label>
                ${activityCollectionVo.villageName}
            </div>
            <div class="div_fitem_1" style="width: 100%">
                  <label class="lable_add" style="width:13%">开始时间:</label> 
                  ${activityCollectionVo.startTimeStr}
            </div>
            <div class="div_fitem_1" style="width: 100%">     
                  <label class="lable_add" style="width: 13%">结束时间:</label> 
                  ${activityCollectionVo.endTimeStr}
            </div>
            
            <div class="div_fitem_1" style="width: 100%">
             <label class="lable_add" style="width: 13%">需要报名:</label>
                <div hidden="true">
                  <input  name="enrollFlag" value="1" type="radio"  checked="checked" disabled = "true">${xybm}
                  <input  name="enrollFlag" value="0" type="radio"  disabled = "true">${bxybm}     
                </div>
                ${bm}
            </div>
            
            <div  id="baoming" class="div_fitem_1" style="width: 100%">
                  <label class="lable_add" style="width: 13%">报名截止时间:</label> 
                  ${activityCollectionVo.enrollEndStr}
            </div>
            
             <div class="div_fitem_1" style="width: 100%">     
               <label class="lable_add" style="width: 13%">活动汇商家:</label> 
              ${shops}
             </div>
               
              <div class="div_fitem_1" style="width: 100%">
              <div style="float: left;">
              <label style="display:inline-block;width:95px">置顶:</label>
              <div hidden="true">
              <input id="topFlagCheckbox" type="checkbox" value="" disabled = "true"/>
               </div>
              ${zd}
              </div>
              <div id="topFlagDiv" style="display:none;margin-left:10px;loat: left;">
               &nbsp;&nbsp;&nbsp;
               <label for="topStart">置顶开始时间:</label> 
                ${activityCollectionVo.topStartStr}
               &nbsp;&nbsp;&nbsp;
               <label for="topEnd">置顶结束时间:</label> 
               ${activityCollectionVo.topStartStr}
              </div>
             </div>
            
              <div class="div_fitem_1" style="width: 100%">
               <div style="float:left;">
                <label >内容:</label>
               </div>
                <div id="content" style="margin-left:9.5%;margin-top:-12px;width:78%;float: left;">
                   ${activityCollectionVo.content}
                </div>
            </div>
            
           <div class="fitem" hidden="true" style="margin: 44px 0 0 0">
              <div style="width:10%;float:left;">
                <label>内容:</label>
              </div>
               <div style="margin-left:-60px;float:left;">
               <div style="margin-left:4%;width:600px;height:500px;">
               ${activityCollectionVo.content}
               </div>
               <div hidden="true">
                <script hidden="true" id="editor" name="content" type="text/plain" style="margin-left:4%;width:600px;height:500px;">${content}</script>
               </div> 
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
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#village_dlg').dialog('close')" style="width:90px">关闭</a>
      </div>
</body>



<script type="text/javascript">

var ue = UE.getEditor('editor');

function setDisabled() {
    UE.getEditor('editor').setDisabled('fullscreen');
    disableBtn("enable");
  }
  
//设置编辑器为不可编辑状态
ue.addListener("ready", function () {
    setDisabled();
});


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
}  
//点击打开大图
function showPic(obj)  
{  
 window.open(obj.src);
}  


function saveSubmit(){
    /* 调用共通js中是否为IE的判定方法 */
   if(window.frames["addVillageFormUI"].contentWindow==undefined){
        window.frames["addVillageFormUI"].saveVillage();
   }else{
        window.frames["addVillageFormUI"].contentWindow.saveVillage();
   }
}

function toSelectVillage(){
	    var id = $('#currenId').val();
        $('#addVillageFormUI').attr("src","activityCollection/selectVillage/"+id);
        $('#village_dlg').dialog('open').dialog('setTitle','小区选择');
}


function optSubmit(publishStatus){
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
}
</script>
</html>