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
                <input name="title" class="easyui-textbox" data-options="required:true,validType:['isBlank','length[1,100]']" style="width:400px">
            </div>
            
          <!--    <div class="fitem">
                <div style="width:10%;position:absolute">
                <label  style="display:initial" >摘要:</label>
                </div>
                <textarea  name="summary" class="easyui-validatebox" data-options="required:true,validType:['isBlank','length[1,100]']" style="margin-left:4%;width:78%;height:100px;"></textarea>
            </div> -->
            
            <div class="fitem">
                <label>来源:</label>
                <input name="source" class="easyui-textbox" data-options="required:true,validType:['isBlank','length[1,100]']">
            </div>
            
            <div class="fitem" id="tubiao">
            <label>图标:</label>
            <input style="width: 300px;" type="text" class="easyui-filebox" id="picfile0" name="file">
            <img id="picture0" name="picture0" style="width: 60px;height: 60px;cursor: pointer;" onclick="showPic(this)">
            
            </div>
            <div class="fitem">
            <label for="type">类型:</label>
            <input  name="type" value="0" type="radio"  class="easyui-validatebox" >${TEXTCLASSS }
            <input  name="type" value="1" type="radio"  class="easyui-validatebox" >${PICTURECLASS }    
            <span style="color: red;">( 说明:如果选择图片类,可以添另3张图片 )</span>
            </div>
            
            <div id="fujianDiv"  class="fitem" style="display:none;">

            <div class="fitem">
                
            <label>展示图1:</label>
            <input style="width: 300px;" type="text" class="easyui-filebox" id="picfile1" name="file">
            <img id="picture1" name="picture1" style="width: 60px;height: 60px;cursor: pointer;" onclick="showPic(this)">
            </div>
            
            <div class="fitem">
               
            <label>展示图2:</label>
            <input style="width: 300px;" type="text" class="easyui-filebox" id="picfile2" name="file">
            <img id="picture2" name="picture2" style="width: 60px;height: 60px;cursor: pointer;" onclick="showPic(this)">
            
            </div>
            <div class="fitem">
                
            <label>展示图3:</label>
            <input style="width: 300px;" type="text" class="easyui-filebox" id="picfile3" name="file">
            <img id="picture3" name="picture3" style="width: 60px;height: 60px;cursor: pointer;" onclick="showPic(this)">
            
            </div>

          </div>
            
            <div class="fitem">
              <label for="reportTime">报道时间:</label> 
              <input class="easyui-datetimebox" type="text" id= "reportTimeStr"  name="reportTimeStr" data-options="required:true" editable="false"/>
            </div>
            <div class="fitem">
                <label>所属小区:</label>
                <input id="villageName" name="villageName" class="easyui-textbox" data-options="editable:false,required:true"/>
                <input type="button" value="选择" onclick="toSelectVillage()"/>
            </div>
            
             <div class="fitem">
              <div style="float: left;">
              <label style="display:inline-block;width:60px">置顶:</label>
              <input id="topFlagCheckbox" type="checkbox" value="" />
              </div>
              <div id="topFlagDiv" style="display:none;margin-left:10px;float: left;">
               <label for="topStart">置顶开始时间:</label> 
               <input class="easyui-datetimebox" type="text" name="topStartStr" id="topStartStr" style="width: 150px;" editable="false"/>
               &nbsp;&nbsp;&nbsp;
               <label for="topEnd">置顶结束时间:</label> 
               <input class="easyui-datetimebox" type="text" name="topStartStr" id="topEndStr" style="width: 150px;" editable="false"/>
              </div>
             </div>
             
              <div class="fitem" style="margin: 44px 0 0 0">
               <label style="display:inline-block;width:60px">允许回复:</label>
               <input id="replyFlagCheckbox" type="checkbox" value="" />
              </div>
            
            
            <div class="fitem">
              <div style="width:10%;position:absolute">
                <label  style="display:initial" >内容:</label>
              </div>
              <script id="editor" name="content" type="text/plain" style="margin-left:4%;width:600px;height:500px;">${content}</script>
            </div>
            
            
             <input type="hidden" id="villageId" name = "villageId">
             <input id="topFlag" name="topFlag"  type="hidden" />
             <input id="replyFlag" name="replyFlag"  type="hidden" />
             <!-- 贴心报审核状态  已通过 -->
             <input id="verifyStatus" name="verifyStatus"  type="hidden" />
              <!-- 贴心报发布状态 -->
             <input id="publishStatus" name="publishStatus"  type="hidden" />
             
             
             
             <input name="id" id="currenId" type="hidden" value="${id}">
             
             <!-- 报道时间 -->
             <input name="reportTime" id="reportTime" type="hidden" >
             <input name="topStart" id="topStart" type="hidden" >
             <input name="topEnd" id="topEnd" type="hidden" >
             
             <!-- 图片默认路径 -->
             <input name="pic1" id="pic0" type="hidden" />
             <input name="pic2" id="pic1" type="hidden" />
             <input name="pic3" id="pic2" type="hidden" />
             <input name="pic4" id="pic3" type="hidden" />
             
             
             <!-- 令牌防止重复提交  -->
             <input type="hidden" name="token" id="token" value="${token}" />
             
              <!-- 是不是快捷发布(0不是  1是)   -->
             <input type="hidden" name="isQuick"  value="1" />
             
             
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

$('#picfile0').filebox({
    buttonText: '选择展示图',
    buttonAlign: 'right',
    accept: 'image/*'
});

$('#picfile1').filebox({
    buttonText: '选择展示图',
    buttonAlign: 'right',
    accept: 'image/*'
});
$('#picfile2').filebox({
    buttonText: '选择展示图',
    buttonAlign: 'right',
    accept: 'image/*'
});
$('#picfile3').filebox({
    buttonText: '选择展示图',
    buttonAlign: 'right',
    accept: 'image/*'
});

var id = $('#currenId').val();
$('#fm').form({onLoadSuccess:loadsucc});  
$('#fm').form('load','intimateNews/findById/'+id+"?t="+new Date().getTime());

//回调函数
function loadsucc(data)  
{  
     //获取默认选中值
     var type=$('input:radio:checked').val();
     if(type==1){
         $("#fujianDiv").show();
         $("#tubiao").hide();
     }else{
         $("#fujianDiv").hide();
         $("#tubiao").show();
     }
    
    //类型
    $(":radio").click(function(){
          var cunnent=$(this).val();
           if(cunnent=="1"){   //如果选中的是图片   显示多张图片区域
               $("#fujianDiv").show();
               $("#tubiao").hide();
               $('#picfile0').filebox({required:false});
               //从来没选过图片类的情况下
               if(type==0){
                   $('#picfile1').filebox({required:true});
                   $('#picfile2').filebox({required:true});
                   $('#picfile3').filebox({required:true});
               }
           }else{
        	   $("#fujianDiv").hide();
               $("#tubiao").show();
               $('#picfile1').filebox({required:false});
               $('#picfile2').filebox({required:false});
               $('#picfile3').filebox({required:false});
               //从来没选过文字类的情况下
               if(type==1){
                  $('#picfile0').filebox({required:true});
               }
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
   var iconString=data.icon;
   var result=iconString.split(",");    
   if(type==1){
       for(var i=1;i<result.length+1;i++){
           $("#picture"+i).attr("src",result[i-1]);
            //图片默认赋初值
            $("#pic"+i).val(result[i-1]);
        } 
   }else{
       for(var i=0;i<result.length;i++){
           $("#picture"+i).attr("src",result[i]);
            //图片默认赋初值
            $("#pic"+i).val(result[i]);
        } 
   }
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

function toSelectVillage(){
	  var villageId=$("#villageId").val();
        $('#addVillageFormUI').attr("src","intimateNews/selectVillage?villageId="+villageId);
        $('#village_dlg').dialog('open').dialog('setTitle','小区选择');
}


function optSubmit(publishStatus){
    $('#publishStatus').val(publishStatus);
    $('#reportTime').val($('#reportTimeStr').datetimebox('getValue'));
    $('#topStart').val($('#topStartStr').datetimebox('getValue'));
    $('#topEnd').val($('#topEndStr').datetimebox('getValue'));
    
    
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
        url: "intimateNews/updateIntimateNews",
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
                // TODO:加载数据
                /* $('#dlg').dialog('close');        
                $('#dg').datagrid('reload');   */  
            }
        }
    });
}
</script>
</html>