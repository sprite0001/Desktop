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
<body >
    <table id="dg"  class="easyui-datagrid" 
            url="goodsclassific/typemag?id=${id}"
            toolbar="#toolbar" pagination="false" queryParams="form2Json('ff')"
            rownumbers="true" fitColumns="true" singleSelect="true"  idField="Id"
            >
        <thead>
            <tr>
                <th field="code" width="50">编号</th>
                <th field="name" width="50">名称</th>
                <th field="remarks" width="50">备注</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新增</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reject',plain:true" onclick="reject()">禁用</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-qiyong',plain:true" onclick="accept()">启用</a>
    </div>
    
       <div id="dlg" class="easyui-dialog" style="padding:10px 20px;width:600px;height:400px;overflow: hidden;"
            closed="true" buttons="#dlg-buttons" modal="true">
            <iframe id='addUserFormUI' border='0' vspace='0' hspace='0' 
                marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
                style="height:100%;width:100%;left:10px;top:8px;" src="">
            </iframe>
        </div>
         <div id="dlg-buttons">
            <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
        </div>
        
    
      <script type="text/javascript">
       var url;
       
       function searchProvince(){
           $('#ff').form('submit',{  
                url: "sysdata/findAll",  
                onSubmit: function(){  
                    return $(this).form('validate'); 
                },  
                success:function(data){  
                    var data = eval('('+data+')');
                   $('#dg').datagrid('loadData',data);
                }
            });  

       }
       
        function newUser(){
            $('#addUserFormUI').attr("src","goodsclassific/toAddGoodsclassific?id=${id}");
            $('#dlg').dialog('open').dialog('setTitle','新增');
            $('#fm').form('clear');
        }
        /* 编辑时，进行页面跳转的时候传入所选的row的id，然后把值传入到子画面，子画面根据该值来加载数据 */
        function editUser(){
            
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#addUserFormUI').attr("src","goodsclassific/toEdit/"+row.id);
                $('#dlg').dialog('open').dialog('setTitle','编辑');
            }else{
                $.messager.alert('提示信息','请先选择要更新的记录。','info');
            }
        }
        function saveUser(){
            /* 调用共通js中是否为IE的判定方法 */
            if(isIE()) {
                window.frames["addUserFormUI"].optSubmit();
            } else {
                window.frames["addUserFormUI"].contentWindow.optSubmit();
            }
        }
        function destroyUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('提示','确定要删除吗?',function(r){
                    if (r){
                        $.post('goodsclassific/delete',{id:row.id,delFlag:'1'},function(result){
                            if (result.type=="Success"){
                                 $.messager.show({    // show error message
                                     title: 'Success',
                                     msg: result.msg
                                 });
                                $('#dg').datagrid('reload');    // 刷新表格
                                //刷新父页面中的树
                                parent.$('#tt').tree('reload');    
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
    function accept(){
        var row = $('#dg').datagrid('getSelected');
        if (row){
            $.messager.confirm('提示','确定要启用吗?',function(r){
                if (r){
                    $.post('goodsclassific/update',{id:row.id,status:'0'},function(result){
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
            $.messager.alert('提示信息','请先选择要启用的记录。','info');
        }
    }
    function reject(){
        var row = $('#dg').datagrid('getSelected');
        if (row){
            $.messager.confirm('提示','确定要禁用吗?',function(r){
                if (r){
                    $.post('goodsclassific/update',{id:row.id,status:'1'},function(result){
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
            $.messager.alert('提示信息','请先选择要禁用的记录。','info');
        }
    }
    function getChanges(){
        var rows = $('#dg').datagrid('getChanges');
        alert(rows.length+' rows are changed!');
    }
    
    </script>
</body>
</html>