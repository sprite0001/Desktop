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
                <label for="provinceId">省:</label> 
                <input id="province" name="provinceId" value="" class="easyui-combobox" style="width: 100px;">
            </div>
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="cityId">市:</label> 
                <input id="city" name="cityId" value="" class="easyui-combobox" style="width: 100px;">
             </div> 
             <div style="float: left; padding: 20px 0 0 20px;">
                <label for="countyId">区/县:</label> 
                <input id="county" name="countyId" value="" class="easyui-combobox" style="width: 100px;">
            </div>
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="communityId">社区:</label> 
                <input id="community" name="communityId" value="" class="easyui-combobox" style="width: 100px;">
           </div>
            <div style="float: left; padding: 20px 0 0 20px;"> 
                <label for="villageName">小区名:</label> 
                <input id="villageName" name="villageName" value="" class="easyui-textbox" style="width: 100px;">
           </div>
             <div style="float: left; padding: 20px 0 0 20px;">
                <label for="phoneNumber">电话:</label> 
                <input id="phoneNumber" name="phoneNumber" value="" class="easyui-textbox" style="width: 100px;">
            </div>
             
            <div style="float: left; padding: 20px 0 0 20px;">
            <shiro:hasPermission name="CommunityPhone:view">  
                <input  type="button" onclick="searchCommonphone()" value="查询" />
            </shiro:hasPermission>
            </div>
            
        </form>
    </div> 
    <table id="dg"  class="easyui-datagrid" style="height: 620px;"
            url="communityphone/findAll"
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            rownumbers="true" fitColumns="true" singleSelect="true" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
                <th field="id" hidden="true"></th>
                <th field="villageName" width="50" >小区名称</th>
                <th field="name" width="50" >电话名称</th>
                <th field="phoneNumber" width="50" >电话号码</th>
                <th field="phoneType" width="50" formatter = "formatType">类别</th>
                <th field="ordering" width="50" >排序序号</th>
                <th field="provinceName" width="50" >省</th>
                <th field="cityName" width="50" >市</th>
                <th field="countyName" width="50" >区</th>
                <th field="communityName" width="50" >社区</th>
                <th field="status" width="50" formatter = "formatStatus">使用状态</th>
            </tr>
        </thead>
    </table>
    <shiro:hasPermission name="CommunityPhone:manager">
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newCommunityPhone()">新增</a>
    </div>
    </shiro:hasPermission>
        <div id="dlg" class="easyui-dialog" style="padding:10px 20px;width: 600px;height:350px;overflow:hidden"
            closed="true" buttons="#dlg-buttons" modal="true">
            <iframe id='addCommunityPhoneFormUI' border='0' vspace='0' hspace='0' 
                marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='no' 
                style="height:100%;width:100%;left:10px;top:8px" src="">
            </iframe>
        </div>
          <div id="dlg-buttons">
            <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveCommunityPhone()" style="width:90px">保存</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
        </div>
         
         
         <!-- 修改弹出框 -->
    <div id="editordlg" class="easyui-dialog" style="padding:10px 20px;width: 600px;height:350px;overflow:hidden"
           closed="true" buttons="#editordlg-buttons" modal="true">
        <iframe id='editorCommunityPhoneFormUI' border='0' vspace='0' hspace='0' 
            marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='no' 
            style="height:100%;width:100%;left:10px;top:8px" src="">
        </iframe>
    </div>
         <div id="editordlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="updateSubmit()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#editordlg').dialog('close')" style="width:90px">取消</a>
    </div>
        
    <script type="text/javascript">
    
        function formatStatus(val,row){
            if (val == "0"){
                return '<span>${QY}</span>';
            } else {
                return '<span>${JY}</span>';
            }
        }
        
        function formatType(val,row){
            if (val == "0"){
                return '<span>${COMMUNTIYPHONE}</span>';
            } else {
                return '<span>${FUWUPHONE}</span>';
            }
        }
    </script>
   
    <script type="text/javascript">
    function conversion(value){
        var time = new Date(value);
        return time.format("yyyy-MM-dd");
    };
    
    var url;
       
       function searchCommonphone(){
           /* $('#ff').form('submit',{  
                url: "communityphone/findAll",  
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
       
        function newCommunityPhone(){
            $('#addCommunityPhoneFormUI').attr("src","communityphone/toAddCommunityPhone");
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
        function editCommunityPhone(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#editorCommunityPhoneFormUI').attr("src","communityphone/toupdate/"+row.id);
                $('#editordlg').dialog('open').dialog('setTitle','编辑');
            }else{
                $.messager.alert('提示信息','请先选择要更新的记录。','info');
            }
        }
        function updateSubmit(){
            /* 调用共通js中是否为IE的判定方法 */
           if(window.frames["editorCommunityPhoneFormUI"].contentWindow==undefined){
                window.frames["editorCommunityPhoneFormUI"].optSubmit();
           }else{
                window.frames["editorCommunityPhoneFormUI"].contentWindow.optSubmit();
           }
        } 
        function saveCommunityPhone(){
            /* 调用共通js中是否为IE的判定方法 */
           if(window.frames["addCommunityPhoneFormUI"].contentWindow==undefined){
                window.frames["addCommunityPhoneFormUI"].optSubmit();
           }else{
                window.frames["addCommunityPhoneFormUI"].contentWindow.optSubmit();
           }
        }
        function destroyCommunityPhone(){
             var row = $('#dg').datagrid('getSelected');
             if (row){
                 $.messager.confirm('提示','确定要删除吗?',function(r){
                     if (r){
                         $.post('communityphone/deleteCommunityPhone',{id:row.id},function(result){
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
            if (row.status == '0') { /*启用*/
                $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-add' plain='true' onclick='newCommunityPhone()'>新增</a>");
                $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-edit' plain='true' onclick='editCommunityPhone()'>编辑</a>");
                $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-remove' plain='true' onclick='destroyCommunityPhone()'>删除</a>");
                $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-reject' plain='true' onclick='reject()'>禁用</a>");
                $.parser.parse($('#toolbar'));
            } else {                /*禁用*/
                $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-add' plain='true' onclick='newCommunityPhone()'>新增</a>");
                $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-remove' plain='true' onclick='destroyCommunityPhone()'>删除</a>");
                $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-qiyong' plain='true' onclick='accept()'>启用</a>");
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
    
     
    </script>
        <!-- 省市区关联 -->
    <script type="text/javascript">
    function accept(){
        var row = $('#dg').datagrid('getSelected');
        if (row){
            $.messager.confirm('提示','确定要启用吗?',function(r){
                if (r){
                    $.post('communityphone/enableCommunityPhone',{id:row.id},function(result){
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
                    $.post('communityphone/disableCommunityPhone',{id:row.id},function(result){
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
    $(function(){
        
        // 省份 下拉框选择控件，下拉框的内容是动态查询数据库信息 
        $('#province').combobox({ 
                    url:'common/initProvince',
                    editable:false, //不可编辑状态
                    cache: false,
                    panelHeight: 'auto',//自动高度适合
                    valueField:'provinceId',   
                    textField:'provinceName',
                    
        onHidePanel: function(){

                $("#city").combobox("setValue",'');
                $("#county").combobox("setValue",'');
                $("#county").combobox('loadData', {});
                $("#community").combobox("setValue",'');
                $("#community").combobox('loadData', {});
                var province = $('#province').combobox('getValue');     
                if(province!=''){
                $.ajax({
                    type: "POST",
                    url: "common/getCityByProvice/"+province,
                    cache: false,
                    dataType : "json",
                    success: function(data){
                    $("#city").combobox("loadData",data);
                                           }
                                       });  
                               }
                         } 
                     }); 
        //市下拉菜单
        $('#city').combobox({ 

            editable:false, //不可编辑状态
            cache: false,
            panelHeight: 'auto',//自动高度适合
            valueField:'cityId',   
            textField:'cityName',
            onHidePanel: function(){
                $("#county").combobox("setValue",'');
                $("#community").combobox("setValue",'');
                $("#community").combobox('loadData', {});
                var city = $('#city').combobox('getValue');     
                if(city!=''){
                $.ajax({
                    type: "POST",
                    url: "common/getCountryByCity/"+city,
                    cache: false,
                    dataType : "json",
                    success: function(data){
                    $("#county").combobox("loadData",data);
                                           }
                                       });  
                               }
                         }
           }); 
        //区下拉菜单
        $('#county').combobox({ 
            editable:false, //不可编辑状态
            cache: false,
            panelHeight: 'auto',//自动高度适合
            valueField:'countyId',   
            textField:'countyName',
            onHidePanel: function(){
                $("#community").combobox("setValue",'');
                var county = $('#county').combobox('getValue');     
                if(county!=''){
                $.ajax({
                    type: "POST",
                    url: "common/getCommunityByCounty/"+county,
                    cache: false,
                    dataType : "json",
                    success: function(data){
                    $("#community").combobox("loadData",data);
                                           }
                                       });  
                               }
                         }
         });  
      //社区下拉菜单
        $('#community').combobox({ 

            editable:false, //不可编辑状态
            cache: false,
            panelHeight: 'auto',//自动高度适合
            valueField:'communityId',   
            textField:'communityName',
           });  
      
    });
    /**绑定页面回车事件，以及初始化页面时的光标定位**/  
    $(function(){  
        bindFormComm("ff",searchCommonphone);
        }); 
    </script>
    
</body>
</html>