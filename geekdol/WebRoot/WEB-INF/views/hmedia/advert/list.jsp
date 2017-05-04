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
                <label for="advertCode">编号:</label> 
                <input class="easyui-textbox" type="text" name="advertCode" id="advertCode" />
            </div>
            
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="advertOwner">广告方:</label> 
                <input class="easyui-textbox" type="text" name="advertOwner" id="advertOwner" />
            </div>
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="beginFlag">状态:</label> 
                <select id="beginFlag" name="beginFlag" class="easyui-combobox" style="width: 80px;">
                    <option value="">全部</option>
                    <option value="1">${NOTBEGIN}</option>
                    <option value="2">${RUNING}</option>
                    <option value="3">${END}</option>
                </select>
            </div>
            
            <div style="float: left; padding: 20px 0 0 20px;">
            <shiro:hasPermission name="Advert:view">
                <input  type="button" onclick="searchAdvert()" value="查询" />
            </shiro:hasPermission>
            </div>
        </form>


    </div> 
    <table id="dg"  class="easyui-datagrid" style="height: 620px;"
            url="advert/findAll"
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            rownumbers="true" fitColumns="true" singleSelect="true" 
            data-options="fit:false,nowrap:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
                <th field="id" hidden="true"></th>
                <th field="advertCode" width="50" >编号</th>
                <th field="advertOwner" width="50" >广告方</th>
                <th field="positionName" width="50" >广告位置</th>
                <th field="startTime"  width="50" formatter="conversion">开始时间</th>
                <th field="endTime" width="50" formatter="conversion">结束时间</th>
                <th field="villageName" width="50">投放区域</th>
                <th field="beginFlag" width="50" formatter="formatStatus">状态</th>
            </tr>
        </thead>
    </table>
    <shiro:hasPermission name="Advert:manager">
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newAdvert()">新增</a>
    </div>
    </shiro:hasPermission>
        <div id="dlg" class="easyui-dialog" style="padding:10px 20px;width: 800px;height: 540px;overflow:hidden;"
            closed="true" buttons="#dlg-buttons" modal="true">
            <iframe id='addAdvertFormUI' border='0' vspace='0' hspace='0' 
                marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
                style="height:100%;width:100%;left:10px;top:8px" src="">
            </iframe>
        </div>
          <div id="dlg-buttons">
            <a id="save" href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveAdvert()" style="width:90px">保存</a>
            <a id="close" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
        </div>
   
    <script type="text/javascript">
    function conversion(value){
        var time = new Date(value);
        return time.format("yyyy-MM-dd");
    };
    
    var url;
       
       function searchAdvert(){
          /*  $('#ff').form('submit',{  
                url: "advert/findAll",  
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
       
        function newAdvert(){
            $('#addAdvertFormUI').attr("src","advert/toAddAdvert");
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
        function editAdvert(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#addAdvertFormUI').attr("src","advert/toEditorAdvert/"+row.id);
                $('#dlg').dialog('open').dialog('setTitle','编辑');
            }else{
                $.messager.alert('提示信息','请先选择要更新的记录。','info');
            }
        }
        function saveAdvert(){
            /* 调用共通js中是否为IE的判定方法 */
           if(window.frames["addAdvertFormUI"].contentWindow==undefined){
                window.frames["addAdvertFormUI"].optSubmit();
           }else{
                window.frames["addAdvertFormUI"].contentWindow.optSubmit();
           }
        }
        function destroyAdvert(){
             var row = $('#dg').datagrid('getSelected');
             if (row){
                 $.messager.confirm('提示','确定要删除吗?',function(r){
                     if (r){
                         $.post('advert/delete',{id:row.id},function(result){
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
        
        function start(){
            var row = $('#dg').datagrid('getSelected');
            var endTime = new Date(row.endTime);
            var nowTime = new Date();
            if (endTime.getTime() < nowTime.getTime()) {
            	$.messager.alert('提示信息','结束时间应大于当前时间,请在编辑界面重新设置','warning');
            	return false;
            }
            if (row){
                $.messager.confirm('提示','确定要开始吗?',function(r){
                    if (r){
                        $.post('advert/start',{id:row.id},function(result){
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
                $.messager.alert('提示信息','请先选择要开始的记录。','info');
            }
       }
        
        function end(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('提示','确定要结束吗?',function(r){
                    if (r){
                        $.post('advert/end',{id:row.id},function(result){
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
                $.messager.alert('提示信息','请先选择要结束的记录。','info');
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
        var row = $('#dg').datagrid('getSelected');
        if (row){
            $('#toolbar').empty();
            if (row.beginFlag == '1') { /*未开始*/
                $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-add' plain='true' onclick='newAdvert()'>新增</a>");
                $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-edit' plain='true' onclick='editAdvert()'>编辑</a>");
                $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-start' plain='true' onclick='start()'>开始</a>");
                $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-remove' plain='true' onclick='destroyAdvert()'>删除</a>");
                $.parser.parse($('#toolbar'));
            } else if(row.beginFlag == '2') {   /*展示中*/
                $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-add' plain='true' onclick='newAdvert()'>新增</a>");
                $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-edit' plain='true' onclick='editAdvert()'>编辑</a>");
                $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-end' plain='true' onclick='end()'>结束</a>");
                $.parser.parse($('#toolbar'));
            }else{                           /*已结束*/
                $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-add' plain='true' onclick='newAdvert()'>新增</a>");
                $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-edit' plain='true' onclick='editAdvert()'>编辑</a>");
                $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-start' plain='true' onclick='start()'>开始</a>");
                $.parser.parse($('#toolbar'));
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
    
    function formatStatus(val,row){
        if (val == "1"){
            return '<span>${NOTBEGIN}</span>';
        } else if(val == "2"){
            return '<span>${RUNING}</span>';
        } else if(val == "3"){
            return '<span>${END}</span>';
        }
    }
    /**绑定页面回车事件，以及初始化页面时的光标定位**/  
    $(function(){  
        bindFormComm("ff",searchAdvert);
        }); 
    </script>
</body>
</html>