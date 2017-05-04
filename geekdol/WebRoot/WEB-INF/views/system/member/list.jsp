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
    <script type="text/javascript" src="${basePath}static/easyui/plugins/jquery.edatagrid.js"></script> 
</head>
<body>

   <div style="height: 70px; width: 100%;">
        <form id="ff" method="post">
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="nickName">昵称:</label> 
                <input class="easyui-textbox" type="text" name="nickName" id="nickName" />
            </div>
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="status">使用状态:</label> 
                <select id="status" name="status" class="easyui-combobox" style="width: 80px;" editable="false">
                    <option value="">全部</option>
                    <option value="0">${QY}</option>
                    <option value="1">${JY}</option>
                </select>
            </div>
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="moblie">手机号:</label> 
                <input class="easyui-textbox" type="text" name="moblie" id="moblie" />
            </div>
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="email">邮箱:</label> 
                <input class="easyui-textbox" type="text" name="email" id="email" />
            </div>
             
            <div style="float: left; padding: 20px 0 0 20px;">
            <shiro:hasPermission name="AppUser:view">
                <input  type="button" onclick="searchMember()" value="查询" />
            </shiro:hasPermission>    
            </div>
        </form>


    </div> 
    <table id="dg"  class="easyui-datagrid" style="height: 620px;"
            url="member/findAll"
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            fitColumns="true" singleSelect="true" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
                <th field="id" hidden="true">
                <th field="nickName" width="50" >昵称</th>
                <th field="moblie" width="50">手机号</th>
                <th field="realName" width="50" >真实姓名</th>
                <th field="email" width="50" >邮箱</th>
                <th field="address" width="50" >地址</th>
                <th field="status" width="50" formatter="formatStatus">使用状态</th>
            </tr>
        </thead>
    </table>
    <shiro:hasPermission name="AppUser:manager">
    <div id="toolbar">
      <a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-remove' plain='true' onclick='destroyMember()'>删除</a>
      <a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-qiyong' plain='true' onclick='start()'>启用</a>
      <a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-reject' plain='true' onclick='stop()'>禁用</a>
      <a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-reset' plain='true' onclick='restPwd()'>重置密码</a>
    </div>
    </shiro:hasPermission>
    <script type="text/javascript">
    
        function formatStatus(val,row){
            if (val == "0"){
                return '<span>${QY}</span>';
            } else {
                return '<span>${JY}</span>';
            }
        }
    </script>
   
    <script type="text/javascript">
       var url;
       
       function searchMember(){
           /* $('#ff').form('submit',{  
                url: "member/findAll",  
                onSubmit: function(){  
                    return $(this).form('validate'); 
                },  
                success:function(data){  
                    var data = eval('('+data+')');
                   $('#dg').datagrid('loadData',data);
                }       
            });  */ 
           $('#dg').datagrid({ queryParams: form2Json("ff") }); 

       }
       
        function destroyMember(){
             var row = $('#dg').datagrid('getSelected');
             if (row){
                 $.messager.confirm('提示','确定要删除吗?',function(r){
                     if (r){
                         $.post('member/delete',{id:row.id},function(result){
                             if (result.type=='Success'){
                                   $.messager.show({    // show error message
                                       title: 'Success',
                                       msg: result.msg
                                   });
                                 $('#dg').datagrid('reload');    // reload the user data
                             } else {
                                 $.messager.show({    // show error message
                                     title: 'Error',
                                     msg: result.msg
                                 });
                             }
                         },'json');
                     }
                 });
             }else{
                 $.messager.alert('提示信息','请先选择要删除的记录。','info');
             }
        }
       /*弹出diago 子画面调用父画面的方法 */
        function close(){
            $('#dlg').dialog('close');
            $('#editordlg').dialog('close');
            $('#dg').datagrid('reload');
        }
    
    </script>
    <style type="text/css">
        #fm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
        .fitem input{
            width:160px;
        }
    </style>
    <script type="text/javascript">
    /* 行编辑时所用的方法 */
    var editIndex = undefined;
    function endEditing(){
        if (editIndex == undefined){return true}
        if ($('#dg').datagrid('validateRow', editIndex)){
            $('#dg').datagrid('endEdit', editIndex);
            editIndex = undefined;
            return true;
        } else {
            return false;
        }
    }
    function onClickRow(index){
        if (editIndex != index){
            if (endEditing()){
                $('#dg').datagrid('selectRow', index)
                        .datagrid('beginEdit', index);
                editIndex = index;
            } else {
                $('#dg').datagrid('selectRow', editIndex);
            }
        }
    }
    function append(){
        if (endEditing()){
            $('#dg').datagrid('appendRow',{status:'P'});
            editIndex = $('#dg').datagrid('getRows').length-1;
            $('#dg').datagrid('selectRow', editIndex)
                    .datagrid('beginEdit', editIndex);
        }
    }
    function removeit(){
        if (editIndex == undefined){return}
        $('#dg').datagrid('cancelEdit', editIndex)
                .datagrid('deleteRow', editIndex);
        editIndex = undefined;
    }
    /*禁用方法*/
    function stop(){
        var row = $('#dg').datagrid('getSelected');
        if (row){
        	if(row.status==0){
                 $.messager.confirm('提示','确定要禁用吗?',function(r){
                       if(r){
                         $.post('member/stop',{id:row.id},function(result){
                             if (result.type=="Success"){
                                 $.messager.show({    // show error message
                                     title: 'Success',
                                     msg:result.msg,
                                 }); 
                                 $('#dg').datagrid('reload');    // reload the user data
                             } else {
                                 $.messager.show({    // show error message
                                     title: 'Error',
                                     msg:result.msg
                                 }); 
                             }
                         },'json');
                       }
                 });
        	} else{
                $.messager.alert('提示信息','这条数据已经被禁用','info');
            } 
             }else{
                 $.messager.alert('提示信息','请先选择要禁用的记录。','info');
             }
         }
        /*启用方法*/
        function start(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
            	if(row.status==1){
                 $.messager.confirm('提示','确定要启用吗?',function(r){
                     if(r){   
                     $.post('member/start',{id:row.id},function(result){
                             if (result.type=="Success"){ 
                                 $.messager.show({    // show error message
                                     title: 'Success',
                                     msg:result.msg,
                                 }); 
                                 $('#dg').datagrid('reload');    // reload the user data
                             } else {
                                 $.messager.show({    // show error message
                                     title: 'Error',
                                     msg:result.msg
                                 }); 
                             }
                         },'json');
                 }
            });
            	 }else{
                     $.messager.alert('提示信息','这条数据已经被启用','info');
                    }
        }else{
            $.messager.alert('提示信息','请先选择要启用的记录。','info');
        }
    }
    /*重置密码*/
    function restPwd(){
        var row = $('#dg').datagrid('getSelected');
        if (row){
            $.messager.confirm('提示','确定要重置吗?',function(r){
                if (r){
                    $.post('member/restPwd',{id:row.id},function(result){
                        if (result.type=='Success'){
                            $.messager.show({    // show error message
                                title: 'Success',
                                msg: result.msg
                            });
                            $('#dg').datagrid('reload');    // reload the user data
                        } else {
                            $.messager.show({    // show error message
                                title: 'Error',
                                msg: result.msg
                            });
                        }
                    },'json');
                }
            });
        }else{
            $.messager.alert('提示信息','请先选择要重置的记录。','info');
        }
    }
    /**绑定页面回车事件，以及初始化页面时的光标定位**/  
    $(function(){  
    bindFormComm("ff",searchMember);  
    });
    </script>
</body>
</html>