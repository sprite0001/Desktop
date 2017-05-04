<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>小区绑定</title>

<style type="text/css">
.s1{width:120px;}
#d1{width:510px;height:320px;background-color:#F5DEB3;margin:0 auto;}
#d2{height:30px;font-size:24px;background-color:blue;color:white;}
#d3{padding-left:30px;}
</style>

<script type="text/javascript" src="${contextPath}/static/js/jquery-1.4.3.js"></script>
<script type="text/javascript" src="${contextPath}/static/js/lab2.js"></script>
</head>
<body>

<div id="d1">
	<div id="d3">
	<input type="hidden" id="villageId" value="${villageId}" />
		<table cellpadding="0" cellspacing="8">
			<tr>
                <td>待选小区</td>
                <td>&nbsp;</td>
                <td>已选小区</td>
            </tr>
			<tr>
				<td>
					<select id="s1" name="s1" style="width:150px; height:220px;" multiple="multiple"></select>
				</td>
				<td>
					<p><input id="b1" type="button" class="s1" value="--&gt;" /></p>
					<p><input type="button" id="b2" class="s1" value="--&gt;&gt;" /></p>
					<p><input type="button" id="b3" class="s1" value="&lt;--" /></p>
					<p><input type="button" id="b4" class="s1" value="&lt;&lt;--" /></p>
				</td>
				<td><select id="s2" name="s2" style="width:150px;height:220px;" multiple="multiple"></select></td>
			</tr>
		</table>
	</div>
</div>
</body>
<script type="text/javascript">
var basePath = '<%=basePath%>';
var villageId=$('#villageId').val();
var flag; //标志位 是否含有逗号
var ids;
if(villageId){
    if(villageId.indexOf(",") != -1){
        flag=true;
        ids=villageId.split(",");
    }else{
        flag=false;
        ids=villageId;
    }
}
//动态改变s1的值
function selectoPition() {
    $.ajax({
        type: "POST",
        url:basePath+"intimateNews/queryVillageByUserId",
        cache: false,
        dataType : "json",
        success: function(data){
             var selObj = $("#s1"); 
             var selOpt = $("#s1 option");
             
             var selObj2 = $("#s2"); 
             var selOpt2 = $("#s2 option");
             
             selOpt.remove();
             selOpt2.remove;
              for(var village in data){
                 if(villageId){
                     if(flag){
                    	 //如果不包含的话
                    	 if(ids.indexOf(data[village].villageId+"")==-1){
                             selObj.append("<option value="+data[village].villageId+">"+data[village].villageName+"</option>");
                         }else{
                             selObj2.append("<option value="+data[village].villageId+">"+data[village].villageName+"</option>");
                         }
                     }else{
                         if(data[village].villageId!=ids){
                             selObj.append("<option value="+data[village].villageId+">"+data[village].villageName+"</option>");
                         }else{
                             selObj2.append("<option value="+data[village].villageId+">"+data[village].villageName+"</option>");
                         }
                     }
                 }else{
                     selObj.append("<option value="+data[village].villageId+">"+data[village].villageName+"</option>");
                 }
             }   
        }
    });     
}

$(document).ready(function(){
	selectoPition();
}); 

function saveVillage(){
	 //$("#s2 option:selected")  获取选中的值
	 var str = "";
	 var villageId = "";
	 $("#s2 option").each(function(){
        villageId+=$(this).val()+",";
        str+=$(this).text()+",";
    });
	 if (str.length > 0) {
	      str = str.substr(0, str.length - 1);
	 }
	 if (villageId.length > 0) {
		 villageId = villageId.substr(0, villageId.length - 1);
	 }
	 parent.$("#villageId").val(villageId);
	 parent.$("#villageName").textbox("setValue",str);
	 parent.$('#village_dlg').dialog('close');
	 //恢复按钮的属性
	 parent.parent.$('#saveA').attr('onclick',"saveUser('0')"); 
     parent.parent.$('#tijiaoA').attr('onclick',"saveUser('1')"); 
     parent.parent.$('#closeBtn').attr('onclick',"javascript:$('#dlg').dialog('close')"); 
  }
</script>
</html>