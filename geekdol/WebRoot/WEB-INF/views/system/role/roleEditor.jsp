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
        <form id="fm" method="post" novalidate>
            
            <div class="div_fitem_1">
                <label class="lable_add">角色名称:</label>
                <input name="name" class="easyui-textbox" data-options="required:true,validType:['isBlank','cnennum','length[1,30]']">
            </div>
            
            <div class="div_fitem_1">
                <label class="lable_add">角色描述:</label>
                <textarea style="vertical-align: top;" name="description" class="easyui-validatebox" data-options="required:true,validType:['isBlank','cnennum','length[1,250]']"   style="height:100px;"></textarea>
            </div>
          
          <div class="div_fitem_1">
                <label class="lable_add">排序: </label>
                <input  name="ordering"   class="easyui-numberbox" data-options="required:true,validType:['integer','length[1,10]']">
            </div>
          <div class="div_fitem_1"> 
              <label class="lable_add"> </label>
              <input id="replyFlagCheckbox" type="hidden"/>
           </div>  
           
            <div class="div_fitem_1"> 
              <label class="lable_add"> </label>
              <input id="replyFlagCheckbox" type="hidden"/>
           </div>  
           <div class="fitem">
                <label>角色权限:</label>
                
                <table id= "table" style="border-right:1px solid #8DB2E3;border-bottom:1px solid #8DB2E3">
                        <thead>
                           <tr>
                               <th style="border-left:1px solid #8DB2E3;border-top:1px solid #8DB2E3" width="20%">主菜单</th>
                               <th style="border-left:1px solid #8DB2E3;border-top:1px solid #8DB2E3" width="20%">子菜单</th>
                               <th style="border-left:1px solid #8DB2E3;border-top:1px solid #8DB2E3">查询权</th>
                               <th style="border-left:1px solid #8DB2E3;border-top:1px solid #8DB2E3">管理权</th>
                               <th style="border-left:1px solid #8DB2E3;border-top:1px solid #8DB2E3">审核权</th>
                               <th style="border-left:1px solid #8DB2E3;border-top:1px solid #8DB2E3">发布权</th>
                               <th style="border-left:1px solid #8DB2E3;border-top:1px solid #8DB2E3">特定人权</th>
                           </tr>
                       </thead>
                       
                       
                       <tbody>
                         
                            <c:set var="zname" value=""></c:set>
                                            <c:forEach items="${moduleList}" var="module" varStatus="status">
                                                <tr>
                                                    <td style="text-align: center;border-left:1px solid #8DB2E3;border-top:1px solid #8DB2E3">
                                                    <c:if test="${zname != fn:substringBefore(module.name,'】') }">
                                                    ${fn:substringBefore(module.name,'】')}】<input type="checkbox"  value="${status.index}" onclick="changeCheckbox(this)">
                                                    </c:if>
                                                    </td>
                                                    <td style="border-left:1px solid #8DB2E3;border-top:1px solid #8DB2E3">${fn:substringAfter(module.name,'】')}</td>
                                                    <td style="text-align: center;border-left:1px solid #8DB2E3;border-top:1px solid #8DB2E3">
                                                    <c:forEach  items="${module.permissions}" var="permissions">
                                                        <c:if test="${permissions.value eq 'view' }">
                                                            <input type="checkbox" name="checkbox" value="${permissions.perId}" <c:if test="${fn:contains(permissionsIds,permissions.perId) }">checked='checked'</c:if> >
                                                        </c:if>
                                                    </c:forEach>
                                                    </td>   
                                                    <td style="text-align: center;border-left:1px solid #8DB2E3;border-top:1px solid #8DB2E3">
                                                    <c:forEach  items="${module.permissions}" var="permissions">
                                                        <c:if test="${permissions.value eq 'manager' }">
                                                            <input type="checkbox" name="checkbox" value="${permissions.perId}" <c:if test="${fn:contains(permissionsIds,permissions.perId) }">checked='checked'</c:if> >
                                                        </c:if>
                                                    </c:forEach>
                                                    </td>   
                                                    <td style="text-align: center;border-left:1px solid #8DB2E3;border-top:1px solid #8DB2E3">
                                                    <c:forEach  items="${module.permissions}" var="permissions">
                                                        <c:if test="${permissions.value eq 'verify' }">
                                                                <input type="checkbox" name="checkbox" value="${permissions.perId}" <c:if test="${fn:contains(permissionsIds,permissions.perId) }">checked='checked'</c:if>>
                                                        </c:if>
                                                    </c:forEach>
                                                    </td>
                                                    
                                                      <td style="text-align: center;border-left:1px solid #8DB2E3;border-top:1px solid #8DB2E3">
                                                     <c:forEach  items="${module.permissions}" var="permissions">
                                                        <c:if test="${permissions.value eq 'publish' }">
                                                              <input type="checkbox" name="checkbox" value="${permissions.perId}" <c:if test="${fn:contains(permissionsIds,permissions.perId) }">checked='checked'</c:if>>
                                                        </c:if>
                                                    </c:forEach>
                                                    </td>
                                                    
                                                    <td style="text-align: center;border-left:1px solid #8DB2E3;border-top:1px solid #8DB2E3">
                                                     <c:forEach  items="${module.permissions}" var="permissions">
                                                        <c:if test="${permissions.value eq 'specific' }">
                                                                <input type="checkbox" name="checkbox" value="${permissions.perId}" <c:if test="${fn:contains(permissionsIds,permissions.perId) }">checked='checked'</c:if>>
                                                        </c:if>
                                                    </c:forEach>
                                                    </td>   
                                                </tr>
                                                <c:set var="zname" value="${fn:substringBefore(module.name,'】')}"></c:set>
                                           </c:forEach>
                         </tbody>

               </table>
               <!-- 令牌防止重复提交  -->
             <input type="hidden" name="token" id="token" value="${token}" />
                
            </div>
          
            <input name="roleId" id="currenId" type="hidden" value="${id}">
            <input type="hidden" name="permissionsIds" id="permissionsIds" value="">
        </form>
</body>
<script type="text/javascript">
var url= "role/update";
function optSubmit(){
	var right = new Array();
	$('input[name="checkbox"]:checked').each(function(){
        right.push(this.value);
    });
	if(right.length == 0){
		 $.messager.show({
             title: '提示',
             msg: "请选择权限！"
         });
         setTimeout('',1000);
         return;
   }
	var strRight = right.toString();
	$("#permissionsIds").val(strRight);
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
                    msg: result.Msg
                });
                
            } else {
            	$.messager.show({
                    title: 'Success',
                    msg: result.Msg,
                    timeout:1000,
                	showType:'slide'
                });
            	setTimeout('window.parent.close()',1000);
            }
        }
    });
}

function changeCheckbox(obj){
	var ss=parseInt(obj.value)+1;
    if(ss==1){
        $("#table tr:gt(0):lt(6)").find("input[name='checkbox']").each(function(i,val) {
            if($(this).prop("checked")){
                 $(this).prop('checked',false);
               }else{
                 $(this).prop('checked',true);
               }
        }) 
    }else if(ss==7){
        $("#table tr:gt(6):lt(7)").find("input[name='checkbox']").each(function(i,val) {
            if($(this).prop("checked")){
                 $(this).prop('checked',false);
               }else{
                 $(this).prop('checked',true);
               }
        }) 
    }else if(ss==14){
        $("#table tr:gt(13):lt(1)").find("input[name='checkbox']").each(function(i,val) {
            if($(this).prop("checked")){
                 $(this).prop('checked',false);
               }else{
                 $(this).prop('checked',true);
               }
        }) 
    }else if(ss==15){
        $("#table tr:gt(14):lt(2)").find("input[name='checkbox']").each(function(i,val) {
            if($(this).prop("checked")){
                 $(this).prop('checked',false);
               }else{
                 $(this).prop('checked',true);
               }
        }) 
    }else if(ss==17){
        $("#table tr:gt(16):lt(13)").find("input[name='checkbox']").each(function(i,val) {
            if($(this).prop("checked")){
                 $(this).prop('checked',false);
               }else{
                 $(this).prop('checked',true);
               }
        }) 
    }
}
$(function(){
    var id = $('#currenId').val();
    $('#fm').form('load','role/findById/'+id+"?t="+new Date().getTime());
})
</script>
</html>