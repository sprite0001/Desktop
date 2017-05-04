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
            
        <!--      <div class="fitem">
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
                <input  style="width: 350px;" type="text" class="easyui-filebox" id="file0" name="file" required="true">
            </div>
            
            <div class="fitem">
            <label for="type">类型:</label>
            <input  name="type" value="0" type="radio"  checked="checked">${TEXTCLASSS }
            <input  name="type" value="1" type="radio" >${PICTURECLASS }    
            <span style="color: red;">( 说明:如果选择图片类,可以添另3张图片 )</span>
            </div>
            
            <div id="fujianDiv"  class="fitem" style="display:none;">

            <div class="fitem">
                <label for="展示图1">展示图1:</label> 
                <input style="width: 350px;" type="text" class="easyui-filebox" id="file1" name="file"/>
            </div>
            <div class="fitem">
                <label for="展示图2">展示图2:</label> 
                <input style="width: 350px;" type="text" class="easyui-filebox" id="file2" name="file"/>
            </div>
            <div class="fitem">
                <label for="展示图3">展示图3:</label> 
                <input style="width: 350px;" type="text" class="easyui-filebox" id="file3" name="file"/>
            </div>

          </div>
            
            <div class="fitem">
              <label for="noticeTimeStr">报道时间:</label> 
              <input class="easyui-datetimebox" type="text" name="reportTime" data-options="required:true" editable="false"/>
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
              <div id="topFlagDiv" style="display:none;margin-left:10px;float:left;">
               <label for="topStart">置顶开始时间:</label> 
               <input class="easyui-datetimebox" type="text" name="topStart" id="topStartStr" style="width: 150px;" editable="false"/>
               &nbsp;&nbsp;&nbsp;
               <label for="topEnd">置顶结束时间:</label> 
               <input class="easyui-datetimebox" type="text" name="topEnd" id="topEndStr" style="width: 150px;" editable="false"/>
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
              <script id="editor" name="content" type="text/plain" style="margin-left:4%;width:600px;height:500px;"></script>
            </div>
            
             <input type="hidden" id="villageId" name = "villageId">
             <input id="topFlag" name="topFlag" value="0" type="hidden" />
             <input id="replyFlag" name="replyFlag" value="0" type="hidden" />
             <!-- 贴心报状态 -->
             <input id="verifyStatus" name="verifyStatus"  type="hidden" />
             <!-- 点赞数量 -->
             <input name = "likesNumber"  value=0 type="hidden">
              <!-- 负赞数量 -->
             <input  name="hateNumber" value=0 type="hidden" />
              <!-- 浏览量 -->
             <input  name="viewNumber" value=0 type="hidden" />
              <!-- 回复量 -->
             <input  name="replyNumber" value=0 type="hidden" />
              <!-- 违规量 -->
             <input  name="illegalNumber" value=0 type="hidden" />
             
              <!-- 发布状态 -->
             <input  name="publishStatus" value="0" type="hidden" />
             <!-- 是否从别人转发过来的 -->
             <input  name="repeatFlag" value="0" type="hidden" />
             
             <!-- 令牌防止重复提交  -->
             <input type="hidden" name="token" id="token" value="${token}" />
             
             <!-- 是不是快捷发布(0不是  1是)   -->
             <input type="hidden" name="isQuick"  value="0" />
             
             
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

$('#file0').filebox({
    buttonText: '选择展示图',
    buttonAlign: 'right',
    accept: 'image/*'
});

$('#file1').filebox({
    buttonText: '选择展示图',
    buttonAlign: 'right',
    accept: 'image/*'
});
$('#file2').filebox({
    buttonText: '选择展示图',
    buttonAlign: 'right',
    accept: 'image/*'
});
$('#file3').filebox({
    buttonText: '选择展示图',
    buttonAlign: 'right',
    accept: 'image/*'
});

$(function(){
    var ue = UE.getEditor('editor');
    
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
    
    //类型
    $(":radio").click(function(){
           var cunnent=$(this).val();
           if(cunnent=="1"){   //如果选中的是图片 
               $("#fujianDiv").show();
               $('#file1').filebox({required:true});
               $('#file2').filebox({required:true});
               $('#file3').filebox({required:true});
               //图标隐藏
               $("#tubiao").hide();
               $('#file0').filebox({required:false});
           }else{
               $("#fujianDiv").hide();
               $('#file1').filebox({required:false});
               $('#file2').filebox({required:false});
               $('#file3').filebox({required:false});
               //图标显示
               $("#tubiao").show();
               $('#file0').filebox({required:true});
           }
          });
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
//点击关闭按钮   调用的事件
function closeDialog(){
	$('#village_dlg').dialog('close');
	parent.$('#saveA').attr('onclick',"saveUser('0')"); 
    parent.$('#tijiaoA').attr('onclick',"saveUser('1')"); 
    parent.$('#closeBtn').attr('onclick',"javascript:$('#dlg').dialog('close')");
}

function toSelectVillage(){
	
		//点击x号关闭调用的地方(此方法必须放在dialog打开之前)
	    $('#village_dlg').dialog({
	        onClose:function(){
	              //恢复按钮点击事件
	             parent.$('#saveA').attr('onclick',"saveUser('0')"); 
	             parent.$('#tijiaoA').attr('onclick',"saveUser('1')"); 
	             parent.$('#closeBtn').attr('onclick',"javascript:$('#dlg').dialog('close')");  
	        }
	    });
	
	    var villageId=$("#villageId").val();
	    var villageName= $('#villageName').textbox('getValue');
        $('#addVillageFormUI').attr("src","intimateNews/selectVillage?villageId="+villageId);
        $('#village_dlg').dialog('open').dialog('setTitle','小区选择');
        //禁掉下面的按钮
        parent.$('#saveA').removeAttr('onclick');//去掉a标签中的href属性
        parent.$('#tijiaoA').removeAttr('onclick');//去掉a标签中的href属性
        parent.$('#closeBtn').removeAttr('onclick');//去掉a标签中的href属性
}


function optSubmit(verifyStatus){
	
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
	
	
	
    $('#verifyStatus').val(verifyStatus);
    $('#fm').form('submit',{
        url: "intimateNews/saveIntimateNews",
        onSubmit: function(){
            var result=$(this).form('validate');
       /*       //如果验证没有通过
            if(result==false){
            	//恢复a标签的href 和 onclick
                addHrefAndOnclick("saveA");
                addHrefAndOnclick("tijiaoA");
            }else{
            	//去掉a标签的href和onclick事件 防止重复提交
                removeHrefAndOnclick("saveA");
                removeHrefAndOnclick("tijiaoA");
            }  */
            return result;
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
            
            /* //恢复a标签的href 和 onclick
            addHrefAndOnclick("saveA");
            addHrefAndOnclick("tijiaoA"); */
        }
    });
}

</script>
</html>