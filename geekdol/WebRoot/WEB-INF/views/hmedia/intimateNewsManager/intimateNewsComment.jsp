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

   <div style="width: 90%;">
        <input type="hidden" id="id" name="id" value="${id}" />
        <div style="margin-top: 10px" >
            <label>所属小区:</label> 
            ${intimateNewsVo.villageName}
            &nbsp;&nbsp;&nbsp;
            <label>浏览/评论/违规:</label> 
            <span id="hotnumber">${intimateNewsVo.hotnumber}</span>
            &nbsp;&nbsp;&nbsp;
            <label>点赞/倒赞:</label> 
            ${intimateNewsVo.likesOrhate}
        </div>
        <div style="margin-top: 10px" >
        <form id="ff" method="post">
                <input type="hidden" id="insId" name="insId" value="${insId}" />
                <label for="insName">评论人:</label> 
                <input class="easyui-textbox" type="text" name="insName" id="insName">
                <label for="content">评论内容:</label> 
                <input class="easyui-textbox" type="text" name="content" id="content">
                <label for="illegalStatus">违规状态:</label> 
                <select id="illegalStatus" name="illegalStatus" class="easyui-combobox" style="width: 80px;" editable="false">
                    <option value="">全部</option>
                    <option value="0">${ZC}</option>
                    <option value="1">${WG}</option>
                </select>
                <input type="button" onclick="searchComment()" value="查询" />
        </form>
        </div>
    </div> 
    <div style="margin-top: 30px">
    <table id="dg" class="easyui-datagrid" style="height: 620px;"
           
            url="intimateNews/findCommentAll?intimateNewsId=${id}"
            
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            fitColumns="true" singleSelect="true" rownumbers="true"
            data-options="nowrap:false,onClickRow: onClickRow,fit:false,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
                <th field="insName" width="50" >评论人</th>
                <th field="content" width="50">评论内容</th>
                <th field="opreaterIp" width="50">评论人IP</th>
                <th field="insYmdhms" width="50" formatter = "conversion">评论时间</th>
                <th field="likesOrhate" width="50">点赞/倒赞</th>
                <th field="illegalStatus" width="50" formatter = "formatStatus">违规状态</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
    <%-- <shiro:hasPermission name="StaffUser:manager"> --%>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteComment()">删除</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-tocomment" plain="true" onclick="toCommentReply()">评论管理</a>
    <%-- </shiro:hasPermission> --%>
    </div>
    
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
       function searchComment(){
           $('#dg').datagrid({ queryParams: form2Json("ff") });
       }
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
                        $.post('intimateNews/deleteComment',{id:row.id,delFlag:'1'},function(result){
                            if (result.type=='Success'){
                            	$("#hotnumber").html(result.dataStr)
                                $.messager.show({    // show error message
                                    title: 'Success',
                                    msg: result.Msg
                                });
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: 'Error',
                                    msg: result.Msg
                                });
                            }
                        },'json');
                    }
                });
            }else{
                $.messager.alert('提示信息','请先选择要删除的记录。','info');
            }
        }
        function toCommentReply() {
            var row = $('#dg').datagrid('getSelected');
            if (row){
            		 parent.$('#addUserFormUI').attr("src","intimateNews/toReplyComment/"+row.id+"/${id}");
                     parent.$('#dlg').dialog('open').dialog('setTitle','评论回复');
            	
            }else{
                $.messager.alert('提示信息','请先选择评论记录。','info');
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
                $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-tocomment' plain='true' onclick='toCommentReply()'>评论管理</a>");
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