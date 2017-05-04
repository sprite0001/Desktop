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
                <label for="title">标题:</label> 
                <input id="title" name="title" value="" class="easyui-textbox" type="text" style="width: 100px;">
            </div>
            
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="phone">发布人电话:</label> 
                <input id="phone" name="phone" value="" class="easyui-textbox" type="text"  style="width: 100px;">
            </div>
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="type">类别:</label> 
                <input id="type" name="type" class="easyui-combobox" style="width: 100px;" editable="false"
					data-options="valueField:'value',textField:'lable',url:'common/initSysData/${INCORRUPTGOVERNMENTTYPE }'">
            </div>
            
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="startTime">发布时间介于:</label> 
                <input id="startTime" name="startTime" value="" class="easyui-datebox" editable="false">
                <label for="endTime">到:</label> 
                <input id="endTime" name="endTime" value="" class="easyui-datebox" editable="false">
            </div>
            <div style="float: left; padding: 20px 0 0 20px;">
            <shiro:hasPermission name="NetizenAcumen:view">
                <input  type="button" onclick="searchNetizenAcumen()" value="查询" />
            </shiro:hasPermission>
            </div>
        </form>
    </div> 
    <table id="dg"  class="easyui-datagrid" style="height: 620px;"
            url="netizenacumen/findAll"
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            fitColumns="true" rownumbers="true" singleSelect="true" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
                <th field="id" hidden="true">
                <th field="title" width="50" >标题</th>
                <th field="type" width="50">类别</th>
                <th field="phone" width="50" >发布人电话</th>
                <th field="insYmdhms" width="50" formatter="conversion" >发布时间</th>
                <th field="insIp" width="50">发布人IP</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
     <shiro:hasPermission name="NetizenAcumen:view">
       <a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-view' plain='true' onclick='details()'>查看详情</a>
     </shiro:hasPermission>
      <shiro:hasPermission name="NetizenAcumen:manager">
       <a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-remove' plain='true' onclick='deletenetizenacumen()'>删除</a>
    </shiro:hasPermission>
    </div>
    <!-- 新增详情框 -->
    <div id="dlg" class="easyui-dialog" style="padding:10px 20px;width: 600px;height:400px;" 
        closed="true" buttons="#dlg-buttons" modal="true">
        <iframe id='addCountyFormUI' border='0' vspace='0' hspace='0' 
            marginwidth='0' marginheight='0' framespacing='0' frameborder='0'   
            style="height:96%;width:100%;left:10px;top:8px" src="">
        </iframe>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
    </div>
        
    <script type="text/javascript">
       var url;
       
       function searchNetizenAcumen(){
           $('#dg').datagrid({ queryParams: form2Json("ff") }); 
       }
      
        function deletenetizenacumen(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('提示','确定要删除吗?',function(r){
                    if (r){
                        $.post('netizenacumen/delete',{id:row.id},function(result){
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
        
        function details(){
            var row = $('#dg').datagrid('getSelected');
            if(row){
                $('#addCountyFormUI').attr("src","netizenacumen/details/"+row.id);
                $('#dlg').dialog('open').dialog('setTitle','详情');
            }else{
                $.messager.alert('提示信息','请先选需要查看的网民法眼信息！','info');
            }
      }
        
       /*弹出diago 子画面调用父画面的方法 */
        function close(){
            $('#dlg').dialog('close');
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
            /* var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:'productid'});
            var productname = $(ed.target).combobox('getText');
            $('#dg').datagrid('getRows')[editIndex]['productname'] = productname; */
            $('#dg').datagrid('endEdit', editIndex);
            editIndex = undefined;
            return true;
        } else {
            return false;
        }
    }
    function conversion(value){
        var time = new Date(value);
        return time.format("yyyy-MM-dd");
    };
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
    
    /**绑定页面回车事件，以及初始化页面时的光标定位**/  
    $(function(){  
    bindFormComm("ff",searchNetizenAcumen);  
    }); 
    </script>
</body>
</html>