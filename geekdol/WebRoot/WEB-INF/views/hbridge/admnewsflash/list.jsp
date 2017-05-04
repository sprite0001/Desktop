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

   <div style="height: 50px; width: 90%;">
        <form id="ff" method="post">
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="title">标题:</label> 
                <input class="easyui-textbox" type="text" name="title" id="title" />
                <label for="newsflashTimeBeginStr">快报时间介于:</label> 
                <input class="easyui-datebox" type="text" name="newsflashTimeBeginStr" id="newsflashTimeBeginStr" editable="false"/>
                <label for="newsflashTimeEndStr">到:</label> 
                <input class="easyui-datebox" type="text" name="newsflashTimeEndStr" id="newsflashTimeEndStr" editable="false"/>
                <label for="verifyStatus">审核状态:</label> 
                <input id="verifyStatus" name="verifyStatus" class="easyui-combobox" 
                    data-options="valueField:'value',textField:'lable',url:'common/initSysData/VERIFYSTATUS'" style="width: 100px;" editable="false">
            </div>
            <div style="float: left; padding: 20px 0 0 20px;">
	            <shiro:hasPermission name="AdmNewsflash:view">
	                <input  type="button" onclick="searchKeyWords()" value="查询" />
	            </shiro:hasPermission>
            </div>
        </form>
    </div> 
    <table id="dg"  class="easyui-datagrid" style="height: 600px;"
            url="admNewsflash/selectList"
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            rownumbers="true" fitColumns="true" singleSelect="true" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
                <th field="title" width="60" >标题</th>
                <th field="newsflashTime" width="40" formatter="conversion">快报时间</th>
                <th field="verifyStatusName" width="25" >审核状态</th>
                <th field="publishTime" width="40" formatter="conversion">发布时间</th>
                <th field="scannedNumber" width="30" >浏览</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
      <shiro:hasPermission name="AdmNewsflash:manager">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newKeywords()">新增</a>
      </shiro:hasPermission>
      <shiro:hasPermission name="AdmNewsflash:manager">  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" id="bianji" plain="true" onclick="editKeywords()">编辑</a>
      </shiro:hasPermission>
      <shiro:hasPermission name="AdmNewsflash:manager">  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-storenotice" id="fabu" plain="true" onclick="status(1)">提交审核</a>
      </shiro:hasPermission>
      <shiro:hasPermission name="AdmNewsflash:manager">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" id="shanchu" plain="true" onclick="destroyKeywords()">删除</a>
      </shiro:hasPermission>
      <shiro:hasPermission name="AdmNewsflash:view">
        <a href='javascript:void(0)' class='easyui-linkbutton' id="show" iconCls='icon-view' plain='true' onclick='details()'>查看详情</a>
      </shiro:hasPermission>
    </div>
        <div id="dlg" class="easyui-dialog" style="padding:10px 20px;width: 900px;height: 540px;overflow:hidden;"
            closed="true" buttons="#dlg-buttons" modal="true">
            <iframe id='addKeywordsFormUI' border='0' vspace='0' hspace='0' 
                marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
                style="height:100%;width:100%;left:10px;top:8px" src="">
            </iframe>
        </div>
          <div id="dlg-buttons">
            <a id="saveBtnA" href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveKeywords('0')" style="width:90px">保存</a>
            <a id="saveBtnB" href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveKeywords('1')" style="width:90px">提交审核</a>
            <a id="editBtnA" href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="updateSubmit('0')" style="width:90px">保存</a>
        	<a id="editBtnB" href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="updateSubmit('1')" style="width:90px">提交审核</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
        </div>
<script type="text/javascript">
	function formatStatus(val,row){
	    if (val == "0"){
	        return '<span style=\'color:red;\'>未发布</span>';
	    } else {
	        return '<span>发布 </span>';
	    }
	}
    function conversion(value){
    	if(value == null){
    		return ;
    	}
        var time = new Date(value);
        return time.format("yyyy-MM-dd");
    };
    
    var url;
       
    function searchKeyWords(){
        /* $('#ff').form('submit',{  
             url: "admNewsflash/selectList",  
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
    	$('#saveBtnA').show();
    	$('#saveBtnB').show();
    	$('#editBtnA').hide();
    	$('#editBtnB').hide();
        $('#addKeywordsFormUI').attr("src","admNewsflash/toAdd");
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
        	$('#saveBtnA').hide();
        	$('#saveBtnB').hide();
        	$('#editBtnA').show();
        	$('#editBtnB').show();
            var row = $('#dg').datagrid('getSelected');
            if (row){
                if(row.verifyStatus == '1'){
                    $.messager.alert('提示信息','当前公告已提交，不允许编辑。','info');
                    return;
                }
                if(row.verifyStatus == '2' || row.verifyStatus == '3'){
                    $.messager.alert('提示信息','当前公告已审核，不允许编辑。','info');
                    return;
                }
                $('#addKeywordsFormUI').attr("src","admNewsflash/toUpdate/"+row.id);
                $('#dlg').dialog('open').dialog('setTitle','编辑');
            }else{
                $.messager.alert('提示信息','请先选择要编辑的记录。','info');
            }
        }
        function updateSubmit(status){
            /* 调用共通js中是否为IE的判定方法 */
          if(window.frames["addKeywordsFormUI"].contentWindow==undefined){
                window.frames["addKeywordsFormUI"].optSubmit(status);
           }else{
                window.frames["addKeywordsFormUI"].contentWindow.optSubmit(status);
           }
        } 
        function saveKeywords(status){
            /* 调用共通js中是否为IE的判定方法 */
           if(window.frames["addKeywordsFormUI"].contentWindow==undefined){
                window.frames["addKeywordsFormUI"].optSubmit(status);
           }else{
                window.frames["addKeywordsFormUI"].contentWindow.optSubmit(status);
           }
        }
        function destroyKeywords(){
        	 var row = $('#dg').datagrid('getSelected');
             if (row){
                /* if(row.verifyStatus == '1'){
                     $.messager.alert('提示信息','当前公告已提交审核，不能删除。','info');
                     return;
                }
                if(row.verifyStatus == '2' || row.verifyStatus == '3'){
                    $.messager.alert('提示信息','当前公告已审核，不能删除。','info');
                    return;
                } */
                 $.messager.confirm('提示','确定要删除吗?',function(r){
                     if (r){
                         $.post('admNewsflash/delete',{id:row.id},function(result){
                             if (result.type=='Success'){
                                   $.messager.show({    // show error message
                                       title: 'Success',
                                       msg: result.msg
                                   });
                                 $('#dg').datagrid('reload');
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
        function status(val){
            var row = $('#dg').datagrid('getSelected');
            if (row){
            	var val = '1';
                if(row.verifyStatus == '1'){
                     $.messager.alert('提示信息','当前公告已提交审核。','info');
                     return;
                }
                if(row.verifyStatus == '2' || row.verifyStatus == '3'){
                    $.messager.alert('提示信息','当前公告已审核。','info');
                    return;
               }
                $.messager.confirm('提示','确定要提交审核吗?',function(r){
                    if (r){
                        $.post('admNewsflash/changeStatus',{id:row.id,verifyStatus:val},function(result){
                            if (result.type=="Success"){
                                //searchKeyWords();
                            	$('#dg').datagrid('reload');
                            } else {
                             $.messager.show({
                                    title: 'Error',
                                    msg:result.msg
                                }); 
                            }
                        },'json');
                    }
                });
            }else{
                $.messager.alert('提示信息','请先选择要提交的记录。','info');
            }
        }
       /*弹出diago 子画面调用父画面的方法 */
        function close(){
            $('#dlg').dialog('close');
            $('#dg').datagrid('reload');
        }
        function details(){
        	$('#saveBtnA').hide();
        	$('#saveBtnB').hide();
        	$('#editBtnA').hide();
        	$('#editBtnB').hide();
            var row = $('#dg').datagrid('getSelected');
            if(row){
                $('#addKeywordsFormUI').attr("src","admNewsflash/toShow/"+row.id);
                $('#dlg').dialog('open').dialog('setTitle','详情');
            }else{
                $.messager.alert('提示信息','请先选需要查看的行政快报！','info');
            }
        }
       function initRow(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $("#bianji").show();
                $("#shanchu").show();
                $("#show").show();
                var status = row.verifyStatus;
                if(status == '0' || status == '3'){
                    $("#fabu").show();
                }else{
                    $("#fabu").hide();
                }
            }else{
                $("#bianji").hide();
                $("#shanchu").hide();
                $("#fabu").hide();
                $("#quxiao").hide();
                $("#show").hide();
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