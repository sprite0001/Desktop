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
                <label for="status">标题:</label> 
                <input id="title" name="title" class="easyui-textbox" style="width: 100px;">
            </div>
            
            
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="status">所属小区:</label> 
                <input id="typeId" name="villageId" class="easyui-combobox" 
                    data-options="valueField:'villageId',textField:'villageName',url:'common/selectVillage',editable:false" style="width: 100px;">
            </div>
            
            <div style="float: left; padding: 20px 0 0 20px;">
            <label for="status" >审核状态:</label>
             <select id="verifyStatus" name="verifyStatus" class="easyui-combobox" style="width:70px;">
                    <option value="">全部</option>
                    <option value="1">已提交</option>
                    <option value="2">已通过</option>
                    <option value="3">未通过</option>
             </select>
            </div>
            
           
            <div style="float: left; padding: 20px 0 0 20px;">
                <input  type="button" onclick="searchProvince()" value="查询" />
            </div>
        </form>


    </div> 
    <table id="dg"  class="easyui-datagrid" style="height:620px;"
            url="intimateNews/findselfAll" 
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            fitColumns="true" singleSelect="true" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
                <th field="title" width="20">标题</th>
                <th field="villageName" width="50" >所属小区</th>
                <th field="reportTime" width="50" formatter="conversion">报道时间</th>
                <th field="replyFlag" width="20" formatter="shifouhuifuorzhiding">允许回复</th>
                <th field="tijiaoName" width="20"  >提交人</th>
                <th field="tijiaoShijian" width="20" formatter="conversion">提交时间</th>
                <th field="verifyStatus" width="50" formatter="tiexinbaostatus">状态</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-review" plain="true" onclick="shenhe()">审核</a>
    </div>
    
        <div id="dlg" class="easyui-dialog" style="padding:10px 20px;width:800px;height:540px;overflow:hidden;"
            closed="true" buttons="#dlg-buttons" modal="true">
            <iframe id='addUserFormUI' border='0' vspace='0' hspace='0' 
                marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
                style="height:100%;width:100%;left:10px;top:8px" src="">
            </iframe>
        </div>
         <div id="dlg-buttons">
            <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser('2')" style="width:90px">通过</a>
            <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser('3')" style="width:90px">不通过</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
        </div>
        
        <div id="dlg2" class="easyui-dialog" style="padding:10px 20px;width:800px;height:540px;overflow:hidden;"
            closed="true" buttons="#dlg-buttons2" modal="true">
            <iframe id='addUserFormUI2' border='0' vspace='0' hspace='0' 
                marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
                style="height:100%;width:100%;left:10px;top:8px" src="">
            </iframe>
        </div>
         <div id="dlg-buttons2">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg2').dialog('close')" style="width:90px">关闭</a>
        </div>
        
   
    <script type="text/javascript">
       
    function tiexinbaostatus(value){
        if(value=="0"){
            return "未提交";
        }else if(value=="1"){
            return "已提交";
        }else if(value=="2"){
            return "已通过";
        }else{
            return "<span style='color:red'>未通过</span>";
        }
    }
    
    function conversion(value){
            var time = new Date(value);  
            return time.format("yyyy-MM-dd hh:mm:ss");
    }
    
    function shifouhuifuorzhiding(val,row){
        if (val == "0"){
            return '<span>否</span>';
        } else {
            return '<span>是</span>';
        }
    }
    
       function searchProvince(){
          /*  $('#ff').form('submit',{  
                url: "intimateNews/findselfAll",  
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
       
        function shenhe(){
              var row = $('#dg').datagrid('getSelected');
              if(row){
                  
                  $('#addUserFormUI').attr("src","intimateNews/toshenheform/"+row.id);
                  $('#dlg').dialog('open').dialog('setTitle','审核');
                  
              }else{
                  $.messager.alert('提示信息','请先选择需要审核的贴心报！','info');
              }
        }
        
        function shenheyijian(){
            var row = $('#dg').datagrid('getSelected');
            if(row){
                $('#addUserFormUI2').attr("src","intimateNews/toshenheform/"+row.id);
                $('#dlg2').dialog('open').dialog('setTitle','审核');
            }else{
                $.messager.alert('提示信息','请先选需要查看的贴心报！','info');
            }
      }
        
        function huifu(){
              var row = $('#dg').datagrid('getSelected');
              if (row){
                   $('#addUserFormUI').attr("src","intimateNews/huifuOneAdd");
                   $('#dlg').dialog('open').dialog('setTitle','回复');
              }else{
                  $.messager.alert('提示信息','请先选择贴心报！','info');
              }
        }
        
        /**绑定页面回车事件，以及初始化页面时的光标定位**/  
        $(function(){  
        bindFormComm("ff",searchProvince);  
        }); 
        /* 编辑时，进行页面跳转的时候传入所选的row的id，然后把值传入到子画面，子画面根据该值来加载数据 */
        function editUser(){
            
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#addUserFormUI').attr("src","intimateNews/toEdit/"+row.id);
                $('#dlg').dialog('open').dialog('setTitle','编辑');
            }else{
                $.messager.alert('提示信息','请先选择要更新的记录。','info');
            }
        }
        function saveUser(verifyStatus){
            /* 调用共通js中是否为IE的判定方法 */
           if(window.frames["addUserFormUI"].contentWindow==undefined){
                window.frames["addUserFormUI"].optSubmit(verifyStatus);
           }else{
                window.frames["addUserFormUI"].contentWindow.optSubmit(verifyStatus);
           }
        }
        function destroyUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('提示','确定要删除吗?',function(r){
                    if (r){
                        $.post('intimateNews/deleteIntimateNews',{id:row.id,delFlag:'1'},function(result){
                            if (result.type=="Success"){
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
             
             //已提交  
             if(row.verifyStatus=='1'){
                  $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-review' plain='true' onclick='shenhe()'>审核</a>");
                  $.parser.parse($('#toolbar'));
             }
             
             //已通过状态下拥有的按钮
             if (row.verifyStatus == '2') {
                 $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-reviewopinion' plain='true' onclick='shenheyijian()'>查看详情</a>");
                 $.parser.parse($('#toolbar'));
             } 
             
           //未通过状态下拥有的按钮
             if (row.verifyStatus == '3') {
                 $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-review' plain='true' onclick='shenhe()'>审核</a>");
                 $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-reviewopinion' plain='true' onclick='shenheyijian()'>查看详情</a>");
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
    function accept(){
        var row = $('#dg').datagrid('getSelected');
        if (row){
            if(row.status==1){
            $.messager.confirm('提示','确定要启用吗?',function(r){
                if (r){
                    $.post('village/updateVillage',{villageId:row.villageId,status:'0'},function(result){
                        if (result.type=='Success'){
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
                $.messager.alert('提示信息','这条数据已经被启用','info');
            }
        }else{
            $.messager.alert('提示信息','请先选择要启用的记录。','info');
        }
    }
    function reject(){
        var row = $('#dg').datagrid('getSelected');
        if (row){
            if(row.status==0){
                $.messager.confirm('提示','确定要禁用吗?',function(r){
                    if (r){
                        $.post('village/updateVillage',{villageId:row.villageId,status:'1'},function(result){
                            if (result.type=='Success'){
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
            } else{
                $.messager.alert('提示信息','这条数据已经被禁用','info');
            }                        
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