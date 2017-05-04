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
    <style type="text/css">
		.s1 { 
			width: 80px;
		}
		#d1 {
			width: 420px;
			height: 260px;
			background-color: #F5DEB3;
			margin-top: 14px;
		}
		#d2 {
			height: 30px;
			font-size: 24px;
			background-color: blue;
			color: white;
		}
		#d3 {
			padding-left: 15px;
		}
		.multiple_select {
			width: 140px;
			height: 200px;
		}
		.suggest_double {
			float: left;
			margin-top: 14px;
		}
	</style>
	<script type="text/javascript" src="${basePath}static/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${basePath}static/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${basePath}static/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${basePath}static/js/common.js"></script>
	<script type="text/javascript" src="${contextPath}/static/js/lab2.js"></script>
	<script type="text/javascript">
 
	var basePath = '<%=basePath%>';
	
	//动态改变s1的值
	function userLeft() {
		$.ajax({
			type: "POST",
			url:basePath+"aroundSuggestStore/queryAroundNoLeader/${id}",
			cache: false,
			dataType : "json",
			success: function(data){
				 var selObj = $("#s1"); 
				 var selOpt = $("#s1 option");
				 selOpt.remove();
				 for(var user in data){
					 selObj.append("<option value="+data[user].id+">"+data[user].realName+"</option>");
				 }  
	        }
	   	}); 	
	}
	
	function userRight() {
		$.ajax({
			type: "POST",
			url:basePath+"aroundSuggestStore/queryAroundLeader/${id}",
			cache: false,
			dataType : "json",
			success: function(data){
				 var selObj = $("#s2"); 
				 var selOpt = $("#s2 option");
				 selOpt.remove();
				 for(var user in data){
					 selObj.append("<option value="+data[user].id+">"+data[user].realName+"</option>");
				 }  
	        }
	   	}); 	
	}
	
	$(document).ready(function(){
		userLeft();
		userRight();
	});
	</script>
</head>
<body>
    <form id="fm" method="post" novalidate enctype="multipart/form-data">
    	<input id="id" name="id" value="${id}" type="hidden"/>
    	<input id="userId" name="userId" type="hidden"/>
    	<div class="div_fitem_1">
            <label class="lable_add" for="name">店名:</label>
            ${aroundSuggestStorevo.name}
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="villageId">所属小区:</label>
            ${aroundSuggestStorevo.villageName}
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="legalPerson">法人:</label>
            ${aroundSuggestStorevo.legalPerson}
        </div>
        <div class="div_fitem_1">
            <label class="lable_add" for="personCard">法人身份证:</label>
            ${aroundSuggestStorevo.personCard}
        </div>
        <div class="div_fitem_1" style="width:100%">
            <label class="lable_add" style="width:13%" for="organizationCode">组织代码证:</label>
            ${aroundSuggestStorevo.organizationCode}
        </div>
        <div id="d1" class="suggest_double" style="margin-left:18%">
			<div id="d3">
				<label>推荐到网格长:</label>
				<table cellpadding="0" cellspacing="8">
					<tr>
		                <td>待选</td>
		                <td>&nbsp;</td>
		                <td>已选</td>
		            </tr>
					<tr>
						<td>
							<select id="s1" name="s1" class="multiple_select" multiple="multiple"></select>
						</td>
						<td>
							<p><input id="b1" type="button" class="s1" value="--&gt;" /></p>
							<p><input type="button" id="b2" class="s1" value="--&gt;&gt;" /></p>
							<p><input type="button" id="b3" class="s1" value="&lt;--" /></p>
							<p><input type="button" id="b4" class="s1" value="&lt;&lt;--" /></p>
						</td>
						<td><select id="s2" name="s2" class="multiple_select" multiple="multiple"></select></td>
					</tr>
				</table>
			</div>
		</div>
    </form>
</body>
<script type="text/javascript">
function optSubmit(){
	var userId = "";
	$("#s2 option").each(function(){
		userId+=$(this).val()+",";
   	});
	if (userId.length > 0) {
		userId = userId.substr(0, userId.length - 1);
	}
	$("#userId").val(userId);
    $('#fm').form('submit',{
        url: "aroundSuggestStore/countySuggest",
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