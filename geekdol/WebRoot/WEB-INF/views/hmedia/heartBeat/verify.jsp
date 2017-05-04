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
                <label>随心拍人员:</label>
                <input name="userName" class="easyui-textbox" disabled = "true">
            </div>
            <div class="fitem">
                <label>手机号:</label>
                <input name="mobile" class="easyui-textbox" disabled = "true">
            </div>
            <div class="fitem">
                <label>发内容时的地址:</label>
                <input name="currentLocation" class="easyui-textbox" disabled = "true">
            </div>
            <div class="fitem">
                <label>所属小区:</label>
                <input name="villageName" class="easyui-textbox" disabled = "true">
            </div>
            <div class="fitem">
                <label>类型:</label>
                <input name="contentTypeName" class="easyui-textbox" disabled = "true">
            </div>
            <div class="fitem">
                <label>状态:</label>
                <input name="treatmentStatusName" class="easyui-textbox" disabled = "true">
            </div>
            <div class="fitem">
                <c:forEach var="message" items="${attList}">
                    <label>展示图:</label>
                    <img name="picture1" style="width: 60px;height: 60px;cursor: pointer;" src="${message.url}" onclick="showPic('${message.url}')">
                </c:forEach>
            </div>
            <div class="fitem">
              <div style="width:10%;position:absolute">
                <label  style="display:initial" >内容:</label>
              </div>
              <script id="editor" name="content" type="text/plain" style="margin-left:4%;width:600px;height:500px;"></script>
            </div>
            <input name="id" id="currenId" type="hidden" value="${id}">
            
            <div class="fitem">
                <label>审核状态:</label>
                <select id="treatmentStatus" class="easyui-combobox" name="treatmentStatus" style="width:200px;" data-options="editable:false">
                    <option value="04">正常</option>
				    <option value="02">违规</option>
				</select>
            </div>
            <div class="fitem" id="verifySub" style="display:none";>
                <label>审核意见:</label>
                <input name="verrifyContent" id="verrifyContent" class="easyui-textbox" data-options="multiline:true" value="" style="width:300px;height:100px">
            </div>
        </form>
</body>

<script type="text/javascript">

var id = $('#currenId').val();
$('#fm').form({onLoadSuccess:loadsucc});  
$('#fm').form('load','heartBeat/findById/'+id+"?t="+new Date().getTime());

$(document).ready(function () {
    $("#treatmentStatus").combobox({
        onChange: function(n,o){
            var val = $('#treatmentStatus').combobox('getValue');
            if(val == 04){
                $("#verifySub").hide();
            }else{
                $("#verifySub").show();
            }
        }
    });

    $('#treatmentStatus').combobox({ 
        editable:false, //不可编辑状态
        cache: false,
        panelHeight: '200px',
        valueField:'value',   
        textField:'label',
        data: [{
            label: '正常',
            value: '04'
        },{
            label: '违规',
            value: '02'
        }]
    });  
});

//回调函数
function loadsucc(data)  
{  
    var ue = UE.getEditor('editor');
    var ueditorBody ='${content}';
    //判断ueditor 编辑器是否创建成功
    if(ueditorBody != null && ueditorBody != undefined && ueditorBody != ""){
        ue.addListener("ready", function () {
            // editor准备好之后才可以使用
            ue.setContent(ueditorBody);
            //设置编辑器为不可编辑状态
            setDisabled();
        });
    }
    
    //设置编辑器为不可编辑状态
    function setDisabled() {
        UE.getEditor('editor').setDisabled('fullscreen');
        disableBtn("enable");
    }
    
}  
//点击打开大图
function showPic(obj)  
{  
 window.open(obj.src);
}  

function optSubmit(){
    var val = $('#treatmentStatus').combobox('getValue');
    if(val == "02"){
        var textVal = $('#verrifyContent').textbox('getValue');
        if(textVal.length > 30){
            $.messager.show({
                title: 'Info',
                msg: "审核意见不能超过30个字",
                timeout:1000,
                showType:'slide'
            });
            return;
        }
        if($.trim(textVal).length <= 0){
            $.messager.show({
                title: 'Info',
                msg: "审核意见不能为空或全为空格",
                timeout:1000,
                showType:'slide'
            });
            return;
        }
    }
    $('#fm').form('submit',{
        url: "heartBeat/saveVerify",
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