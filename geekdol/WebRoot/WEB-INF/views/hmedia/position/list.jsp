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
                <label for="moduleCode">模块:</label> 
                 <input id="typeId" name="moduleCode" class="easyui-combobox" 
                    data-options="valueField:'value',textField:'lable',url:'common/initSysData/${MODULECODE}'" editable="false">
            </div>
            
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="positionCode">编号:</label> 
                <input class="easyui-textbox" type="text" name="positionCode" id="positionCode" />
            </div>
            
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="positionName">名称:</label> 
                <input class="easyui-textbox" type="text" name="positionName" id="positionName" />
            </div>
            
            
            
            <div style="float: left; padding: 20px 0 0 20px;">
            <shiro:hasPermission name="Position:view">
                <input  type="button" onclick="searchPosition()" value="查询" />
            </shiro:hasPermission>
            </div>
        </form>


    </div> 
    <table id="dg"  class="easyui-datagrid" style="height: 620px;"
            url="position/findAll"
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            rownumbers="true" fitColumns="true" singleSelect="true" 
            data-options="fit:false,nowrap:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
                <th field="positionId" hidden="true"></th>
                <th field="positionCode" width="50" >编号</th>
                <th field="moduleCode" width="50" formatter = "formatStatus">模块</th>
                <th field="positionName" width="50" >名称</th>
                <th field="remark" width="50" >备注</th>
            </tr>
        </thead>
    </table>
     <shiro:hasPermission name="Position:manager">
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newPosition()">新增</a>
    </div>
    </shiro:hasPermission>
        <div id="dlg" class="easyui-dialog" style="padding:10px 20px;width: 600px;height:350px;overflow:hidden"
            closed="true" buttons="#dlg-buttons" modal="true">
            <iframe id='addPositionFormUI' border='0' vspace='0' hspace='0' 
                marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
                style="height:100%;width:100%;left:10px;top:8px" src="">
            </iframe>
        </div>
          <div id="dlg-buttons">
            <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="savePosition()" style="width:90px">保存</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
        </div>
        
    <script type="text/javascript">
    function conversion(value){
        var time = new Date(value);
        return time.format("yyyy-MM-dd");
    };
    
    var url;
       
       function searchPosition(){
          /*  $('#ff').form('submit',{  
                url: "position/findAll",  
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
       
        function newPosition(){
            $('#addPositionFormUI').attr("src","position/toAddPosition");
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
        function editPosition(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#addPositionFormUI').attr("src","position/toupdate/"+row.positionId);
                $('#dlg').dialog('open').dialog('setTitle','编辑');
            }else{
                $.messager.alert('提示信息','请先选择要更新的记录。','info');
            }
        }
        function savePosition(){
            /* 调用共通js中是否为IE的判定方法 */
           if(window.frames["addPositionFormUI"].contentWindow==undefined){
                window.frames["addPositionFormUI"].optSubmit();
           }else{
                window.frames["addPositionFormUI"].contentWindow.optSubmit();
           }
        }
        function destroyPosition(){
             var row = $('#dg').datagrid('getSelected');
             if (row){
                 $.messager.confirm('提示','确定要删除吗?',function(r){
                     if (r){
                         $.post('position/delete',{positionId:row.positionId},function(result){
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
        var row = $('#dg').datagrid('getSelected');
        if (row){
            $('#toolbar').empty();
                $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-add' plain='true' onclick='newPosition()'>新增</a>");
                $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-edit' plain='true' onclick='editPosition()'>编辑</a>");
                $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-remove' plain='true' onclick='destroyPosition()'>删除</a>");
                $.parser.parse($('#toolbar'));
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
    
     
    </script>
     <script type="text/javascript">
    
        function formatStatus(val,row){
            if(val == "01"){
               return '<span>${HMEDIA}</span>';
            } else if(val == "02"){
               return '<span>${HBRIDGE}</span>';
            } else if(val == "03"){
               return '<span>${INTIMATENEWS}</span>';
            }else if(val=="04"){
                return '<span>${Activity}</span>';
            }else if(val=="05"){
                return '<span>${ZHOUBINADIAN}</span>';
            }else if(val=="06"){
                return '<span>${QINGLANGWANGLUO}</span>';
            }else if(val=="07"){
                return '<span>${SHENGHUOREXIAN}</span>';
            }else if(val=="08"){
                return '<span>${GEEKSTORE}</span>';
            }else if(val=="09"){
                return '<span>${XINGZHENG}</span>';
            }else if(val=="10"){
                return '<span>${WENZHENG}</span>';
            }else if(val=="11"){
                return '<span>${ZHENGWUYUN}</span>';
            }else if(val=="12"){
                return '<span>${YUQING}</span>';
            }else if(val=="13"){
                return '<span>${SUIXINPAI}</span>';
            }
        }
        /**绑定页面回车事件，以及初始化页面时的光标定位**/  
        $(function(){  
        bindFormComm("ff",searchPosition);  
        }); 
    </script>
</body>
</html>