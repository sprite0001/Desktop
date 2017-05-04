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
                <label class="lable_add" style="width: 8%">标题:</label>
              ${intimateNewsVo.title}
            </div>
            
            <div class="div_fitem_1" style="width: 100%">
                <label class="lable_add" style="width: 8%">来源:</label> 
                ${intimateNewsVo.source}
            </div>
            
            <div class="div_fitem_1" style="width: 100%" id="tubiao">
            	<label class="lable_add" style="width: 8%">图标:</label>
            	<img id="picture0"  style="width: 80px;height: 80px;cursor: pointer;" onclick="showPic(this)">
            </div>
            <div class="div_fitem_1" style="width: 100%">
	            <label for="type" class="lable_add" style="width: 8%">类型:</label>
	            <div hidden="true">
	            	<input  name="type" value="0" type="radio"  class="easyui-validatebox" disabled="disabled">${TEXTCLASSS }
	            	<input  name="type" value="1" type="radio"  class="easyui-validatebox" disabled="disabled">${PICTURECLASS }    
	            </div>
	           	${type}
            </div>
            
            <div id="fujianDiv"  class="fitem" style="display:none;">

            <div class="fitem">
                
            <label>展示图1:</label>
            &nbsp;
            <div  hidden="true" >
            <input style="width: 300px;" type="text" class="easyui-filebox" id="picfile1" name="file" readonly="readonly">
            </div>
            <img id="picture1"  style="width: 80px;height: 80px;cursor: pointer;" onclick="showPic(this)">
             
            &nbsp;&nbsp;   
            <label>展示图2:</label>
            &nbsp;
            <div  hidden="true" >
            <input style="width: 300px;" type="text" class="easyui-filebox" id="picfile2" name="file" readonly="readonly">
            </div>
            <img id="picture2"  style="width: 80px;height: 80px;cursor: pointer;" onclick="showPic(this)">
            
            
            &nbsp;&nbsp;    
            <label>展示图3:</label>
            &nbsp;
            <div  hidden="true" >
            <input style="width: 300px;" type="text" class="easyui-filebox" id="picfile3" name="file" readonly="readonly">
           </div>
            <img id="picture3"  style="width: 80px;height: 80px;cursor: pointer;" onclick="showPic(this)">
            
            </div>

          </div>
            
            <div class="div_fitem_1" style="width: 100%">
              <label for="reportTime" class="lable_add" style="width: 8%">报道时间:</label> 
              ${intimateNewsVo.reportTimeStr}
            </div>
            <div class="div_fitem_1" style="width: 100%">
                <label class="lable_add" style="width: 8%">所属小区:</label>
         ${intimateNewsVo.villageName}
            </div>
            
              <div class="div_fitem_1" style="width: 100%">
              <div style="float: left;">
              <label style="display:inline-block;width:60px">置顶:</label>
              <div hidden="true">
               <input id="topFlagCheckbox" type="checkbox" value="" disabled="disabled"/>
              </div>
               ${zd}
              </div>
              <div id="topFlagDiv" style="display:none;clear: both; padding-top: 10px; margin-left: -16px;">
                &nbsp;&nbsp;&nbsp;
               <label for="topStart">置顶开始时间:</label> 
              ${intimateNewsVo.topStartStr}
               &nbsp;&nbsp;&nbsp;
               <label for="topEnd">置顶结束时间:</label> 
              ${intimateNewsVo.topEndStr}
              </div>
             </div>
             
              <div class="div_fitem_1" style="width: 100%">
               <label style="display:inline-block;width:60px">允许回复:</label>
               <div hidden="true">
               <input id="replyFlagCheckbox" type="checkbox" value="" disabled="disabled" />
             </div>
             ${hf}
              </div>
            
            <c:if test="${!empty intimateNewsVo.verifyOpinion}">
            
             <div class="div_fitem_1" style="width: 100%">
                <label class="lable_add" style="width: 8%">审核:</label> 
                 ${intimateNewsVo.verifyOpinion}
                </div>
            </c:if>
           
             <div class="div_fitem_1" style="width: 100%">
               <div style="float:left;">
                <label >内容:</label>
               </div>
                <div id="content" style="margin-left:4.5%;margin-top:-12px;width:78%;float: left;">
                    ${intimateNewsVo.content}
                </div>
            </div>
            
       
           
         
             <input type="hidden" id="villageId" name = "villageId">
             <input id="topFlag" name="topFlag"  type="hidden" />
             <input id="replyFlag" name="replyFlag"  type="hidden" />
             <!-- 贴心报状态 -->
             <input id="verifyStatus" name="verifyStatus"  type="hidden" />
             
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
     //如果选中的是图片类
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
           if(cunnent==1){   //如果选中的是图片   显示多张图片区域
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
</script>
</html>