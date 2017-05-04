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

   <div style="height: 100px; width: 90%;">
        <input type="hidden" id="id" name="id" value="${id}" />
        <div style="float: left; padding: 20px 0 0 20px;" >
            <label>标题:</label> 
            ${activityCollectionVo.title}
            &nbsp;&nbsp;&nbsp;
            <label>所属小区:</label> 
            ${activityCollectionVo.villageName}
            &nbsp;&nbsp;&nbsp;
            <label>浏览量/报名人数:</label> 
            ${activityCollectionVo.viewNumber}/${activityCollectionVo.enrollCount}
        </div>
        <form id="ff" method="post">
            <div style="float: left; padding: 20px 0 0 20px;" >
                <input type="hidden" id="insId" name="insId" value="${insId}" />
                <label for="name">姓名:</label> 
                <input class="easyui-textbox" type="text" name="name" id="name">
                <label for="mobile">联系方式:</label> 
                <input class="easyui-textbox" type="text" name="phone" id="phone">
                <input type="button" onclick="searchComment()" value="查询" />
                <input type="button" onclick="outexcel()" value="导出" />
            </div>
        </form>
    </div> 
    <table id="dg" class="easyui-datagrid" style="height: 620px;"
            url="activityCollection/findBaomingAll?activityCollectionId=${id}"
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            fitColumns="true" singleSelect="true" rownumbers="true"
            data-options="nowrap:false,onClickRow: onClickRow,fit:false,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
                <th field="name" width="50" >姓名</th>
                <th field="phone" width="50">联系方式</th>
                <th field="ip" width="50">报名人IP</th>
                <th field="time" width="50" formatter = "conversion">报名时间</th>
                <th field="remark" width="50">备注</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
    </div>
    <div id="dlg" class="easyui-dialog" style="padding:10px 20px;width: 800px;height: 580px;"
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
        function conversion(value){
            var time = new Date(value);
            return time.format("yyyy-MM-dd hh:mm:ss");
        }
        
       var url;
       function searchComment(){
          /*  $('#ff').form('submit',{  
                url: "activityCollection/findBaomingAll?activityCollectionId=${id}",  
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
       function outexcel(){
           $('#ff').form('submit',{  
                url: "activityCollection/exportExcle?activityCollectionId=${id}",  
                onSubmit: function(){  
                    return $(this).form('validate'); 
                },  
                success:function(result){  
                	var result = eval('('+result+')');
                	  if ("Error"==result.type){
                          $.messager.show({
                              title: 'Error',
                              msg: result.msg
                          });
                          setTimeout('window.parent.close()',1000);
                      } }
            });  
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
                        $.post('activityCollection/deleteActivityBaoming',{id:row.id,delFlag:'1'},function(result){
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
        function toCommentReply() {
            var row = $('#dg').datagrid('getSelected');
            if (row){
                parent.$('#addAroundStoreFormUI').attr("src","intimateNews/toReplyComment/"+row.id+"/${id}");
                parent.$('#dlg').dialog('open').dialog('setTitle','活动汇回复的回复管理');
            }else{
                $.messager.alert('提示信息','请先选择要进行回复管理的记录。','info');
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
             <shiro:hasPermission name="ActivityCollection:manager"> 
                $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconCls:'icon-remove',plain:true\" onclick='deleteComment()'>删除</a>");
                $.parser.parse($('#toolbar'));
             </shiro:hasPermission> 
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