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

   <div style="height: 200px; width: 100%;">
        <input type="hidden" id="id" name="id" value="${id}" />
        <div style="float: left; padding: 20px 0 0 20px;" >
            <div class="fitem">
                <label>展示图:</label>
                <c:forEach var="message" items="${attList}">
                    <img name="picture1" style="width: 60px;height: 60px;cursor: pointer;" src="${message.url}" onclick="showPic('${message.url}')">
                </c:forEach>
            </div>
            <div class="fitem">
                <label style="display:initial" >内容:</label>
                ${content }
            </div>
        </div>
    </div> 
    <table id="dg" class="easyui-datagrid" style="height: 60%;"
            url="heartBeat/selectReportList?id=${id}"
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            fitColumns="true" singleSelect="true" rownumbers="true"
            data-options="onClickRow: onClickRow,fit:false,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
               <th field="id" hidden="true"></th>
               <th field="reportName" width="60">举报人员</th>
               <th field="mobile" width="60" >举报手机号</th>
               <th field="reportTypeName" width="30" >举报类型</th>
               <th field="insYmdhms" width="90" formatter="conversion">举报时间</th>
               <th field="reportContent" width="150">举报的内容</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
    <shiro:hasPermission name="HeartBeat:manager">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteComment()">删除</a>
    </shiro:hasPermission>
    </div>
    <div id="dlg" class="easyui-dialog" style="padding:10px 20px;width: 800px;height: 550px;"
           closed="true" buttons="#dlg-buttons" modal="true">
        <iframe id='addAroundStoreFormUI' border='0' vspace='0' hspace='0' 
            marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
            style="height:100%;width:100%;left:10px;top:8px" src="">
        </iframe>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveSubmit()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
    </div>
    
    <script type="text/javascript">
        function formatStatus(val,row){
            if (val == "0"){
                return '<span>正常</span>';
            } else {
                return "<span style='color:red'>违规</span>";
            }
        }
        function conversion(value){
            var time = new Date(value);
            return time.format("yyyy-MM-dd hh:mm:ss");
        }
        
       var url;
        function deleteComment() {
            var rows = $('#dg').datagrid('getSelections');
            if(rows.length>1){
                $.messager.show({    // show error message
                    title: 'Warning',
                    msg: '请选择一条数据删除!'
                });
                return;
            }
            var row = rows[0];
            if (row){   
                $.messager.confirm('提示','确定要删除吗?',function(r){
                    if (r){
                        $.post('heartBeat/deleteReport',{id:row.id,delFlag:'1'},function(result){
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
        $('#toolbar').empty();
        var row = $('#dg').datagrid('getSelected');
        if (row){
            if (row.illegalStatus == '0') {
                $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconCls:'icon-remove',plain:true\" onclick='deleteComment()'>删除</a>");
                $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-tocomment' plain='true' onclick='toCommentReply()'>回复管理</a>");
                $.parser.parse($('#toolbar'));
            } else {
                $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconCls:'icon-remove',plain:true\" onclick='deleteComment()'>删除</a>");
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
    /**绑定页面回车事件，以及初始化页面时的光标定位**/  
    $(function(){  
        bindFormComm("ff",searchComment);
        }); 
     
    </script>
</body>
</html>