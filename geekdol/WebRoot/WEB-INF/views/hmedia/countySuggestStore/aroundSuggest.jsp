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
		.suggest_double {
			float: left;
			margin-top: 14px;
		}
		.multiple_select {
			width: 140px;
			height: 200px;
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
	function villageLeft() {
		$.ajax({
			type: "POST",
			url:basePath+"countySuggestStore/queryVillageNoSuggest/0",
			cache: false,
			dataType : "json",
			success: function(data){
				 var selObj = $("#s1"); 
				 var selOpt = $("#s1 option");
				 selOpt.remove();
				 for(var village in data){
					 selObj.append("<option value="+data[village].villageId+">"+data[village].villageName+"</option>");
				 }  
	        }
	   	}); 	
	}
	
	$(document).ready(function(){
		villageLeft();
	});
	</script>
</head>
<body>
	<div class="ftitle"></div>
    <form id="fm" method="post" novalidate>
    	<input id="sid" name="sid" value="${sid}" type="hidden"/>
    	<input id="countySuggestStoreToPersonId" name="countySuggestStoreToPersonId" value="${countySuggestStoreToPersonId}" type="hidden"/>
    	<input id="villageId" name="villageId" type="hidden"/>
    	<input id="type" name="type" value="${type}" type="hidden"/>
    	<c:if test="${type eq '1' }">
	    	<div class="div_fitem_1">
	            <label class="lable_add" for="name">店名:</label>
	            ${aroundStoreVo.name}
	        </div>
        	<div class="div_fitem_1">
	            <label class="lable_add" for="villageId">所属小区:</label>
	            ${aroundStoreVo.villageName}
	        </div>
	        <div class="div_fitem_1">
	            <label class="lable_add" for="legalPerson">法人:</label>
	            ${aroundStoreVo.legalPerson}
	        </div>
        	<div class="div_fitem_1">
	            <label class="lable_add" for="personCard">法人身份证:</label>
	            ${aroundStoreVo.personCard}
	        </div>
        	<div class="div_fitem_1">
	            <label class="lable_add" style="width:13%" for="organizationCode">组织代码证:</label>
	            ${aroundStoreVo.organizationCode}
	        </div>
        </c:if>
    	<c:if test="${type eq '2' }">
	    	<div class="div_fitem_1">
	            <label class="lable_add" for="name">店名:</label>
	            ${aroundSuggestStoreVo.name}
	        </div>
        	<div class="div_fitem_1">
	            <label class="lable_add" for="villageId">所属小区:</label>
	            ${aroundSuggestStoreVo.villageName}
	        </div>
	        <div class="div_fitem_1">
	            <label class="lable_add" for="legalPerson">法人:</label>
	            ${aroundSuggestStoreVo.legalPerson}
	        </div>
        	<div class="div_fitem_1">
	            <label class="lable_add" for="personCard">法人身份证:</label>
	            ${aroundSuggestStoreVo.personCard}
	        </div>
	        <div class="div_fitem_1">
	            <label class="lable_add" style="width:13%" for="organizationCode">组织代码证:</label>
	            ${aroundSuggestStoreVo.organizationCode}
	        </div>
        </c:if>
        <div class="div_fitem_1">
            <label for="suggestStart">推荐开始时间:</label>
            <input id="suggestStart" name="suggestStart" class="easyui-datetimebox" required="true" style="width: 150px;" editable="false">
        </div>
       	<div class="div_fitem_1">
            <label for="suggestEnd">推荐结束时间:</label>
            <input id="suggestEnd" name="suggestEnd" class="easyui-datetimebox" required="true" style="width: 150px;" editable="false">
        </div>
        <div id="d1" class="suggest_double" style="margin-left:18%">
			<div id="d3">
				<label>推荐到小区:</label>
				<table cellpadding="0" cellspacing="8">
					<tr>
		                <td>待选小区</td>
		                <td>&nbsp;</td>
		                <td>已选小区</td>
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
	var villageId = "";
	$("#s2 option").each(function(){
        villageId+=$(this).val()+",";
   	});
	if (villageId.length > 0) {
		villageId = villageId.substr(0, villageId.length - 1);
	}
	$("#villageId").val(villageId);
    $('#fm').form('submit',{
        url: "countySuggestStore/acceptSuggest/${type}/${sid}/${countySuggestStoreToPersonId}",
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