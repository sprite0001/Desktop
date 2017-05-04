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
                <label for="name">功能名称:</label> 
                <input class="easyui-textbox" type="text" name="name" id="name" />
                <label>功能位置:</label>
                <input id="positionId" name="positionId" value="" class="easyui-combobox" 
                  data-options="valueField:'id',textField:'name',url:'appPosition/selectAll'" >
            </div>
            <div style="float: left; padding: 20px 0 0 20px;">
                <shiro:hasPermission name="Staff:view">
                    <input  type="button" onclick="searchKeyWords()" value="查询" />
                </shiro:hasPermission>
            </div>
        </form>
    </div> 
    <table id="dg"  class="easyui-datagrid" style="height: 620px;"
            url="appModular/selectList"
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            rownumbers="true" fitColumns="true" singleSelect="true" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
                <th field="name" width="20" >位置名称</th>
                <th field="positionName" width="50" >功能位置名称</th>
                <th field="pic" width="20" >功能图标路径</th>
                <th field="android" width="20" >Andrio链接地址</th>
                <th field="ios" width="20" >IOS链接地址</th>
                <th field="ordering" width="20" >序号</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
      <shiro:hasPermission name="AppPosition:manager">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newKeywords()">新增</a>
      </shiro:hasPermission>
      <shiro:hasPermission name="AppPosition:manager">  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" id="bianji" plain="true" onclick="editKeywords()">编辑</a>
      </shiro:hasPermission>
      <shiro:hasPermission name="AppPosition:manager">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" id="shanchu" plain="true" onclick="destroyKeywords()">删除</a>
      </shiro:hasPermission>
    </div>
    
        <div id="dlg" class="easyui-dialog" style="padding:10px 20px;width: 600px;"
            closed="true" buttons="#dlg-buttons" modal="true">
            <iframe id='addKeywordsFormUI' border='0' vspace='0' hspace='0' 
                marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='no' 
                style="height:40%;width:100%;left:10px;top:8px" src="">
            </iframe>
        </div>
          <div id="dlg-buttons">
            <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveKeywords()" style="width:90px">保存</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
        </div>
        
    <script type="text/javascript">
    
        function formatStatus(val,row){
            if (val == "0"){
                return '<span>启用</span>';
            } else {
                return '<span>禁用</span>';
            }
        }
    </script>
   
    <script type="text/javascript">
    function conversion(value){
        var time = new Date(value);
        return time.format("yyyy-MM-dd");
    };
    
    var url;
       
       function searchKeyWords(){
           /* $('#ff').form('submit',{  
                url: "appModular/selectList",  
                onSubmit: function(){  
                    return $(this).form('validate'); 
                },  
                success:function(data){  
                    var data = eval('('+data+')');
                   $('#dg').datagrid('loadData',data);
                }       
            });   */
           $('#dg').datagrid({ queryParams: form2Json("ff") });

       }
       
        function newKeywords(){
            $('#addKeywordsFormUI').attr("src","appModular/toAdd");
            $('#dlg').dialog('open').dialog('setTitle','新增');
            $('#organId').combobox({
                onLoadSuccess: function () { 
                     var data = $('#organId').combobox('getData');
                             if (data.length > 0) {
                                 $('#organId').combobox('select', data[0].organId);
                             } 
                    }
               });
            $('#roleId').combobox({
                onLoadSuccess: function () { 
                     var data = $('#roleId').combobox('getData');
                             if (data.length > 0) {
                                 $('#roleId').combobox('select', data[0].roleId);
                             } 
                    }
               });
            $('#fm').form('clear');
            url = 'users/addUsers';
        }
        /* 编辑时，进行页面跳转的时候传入所选的row的id，然后把值传入到子画面，子画面根据该值来加载数据 */
        function editKeywords(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#addKeywordsFormUI').attr("src","appModular/toUpdate/"+row.id);
                $('#dlg').dialog('open').dialog('setTitle','编辑');
            }else{
                $.messager.alert('提示信息','请先选择要更新的记录。','info');
            }
        }
        function saveKeywords(){
            /* 调用共通js中是否为IE的判定方法 */
           if(window.frames["addKeywordsFormUI"].contentWindow==undefined){
                window.frames["addKeywordsFormUI"].optSubmit();
           }else{
                window.frames["addKeywordsFormUI"].contentWindow.optSubmit();
           }
        }
        function destroyKeywords(){
        	 var row = $('#dg').datagrid('getSelected');
             if (row){
                 $.messager.confirm('提示','确定要删除吗?',function(r){
                     if (r){
                         $.post('appModular/delete',{id:row.id},function(result){
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
        function initRow(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $("#bianji").show();
                $("#shanchu").show();
                var status = row.publishStatus;
            }else{
                $("#bianji").hide();
                $("#shanchu").hide();
            }
        }
        $(function(){
            initRow();
        });
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
        initRow();
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
    /**绑定页面回车事件，以及初始化页面时的光标定位**/  
    $(function(){  
    bindFormComm("ff",searchKeyWords);  
    }); 
     
    </script>
</body>
</html>