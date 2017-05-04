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

   <div style="height: 120px; width: 100%;">
        <form id="ff" method="post">
            <div style=" padding: 20px 0 0 20px;">
             <span id="provinceSpan">
                <label for="status">省:</label> 
                <input id="province" name="provinceId" class="easyui-combobox" style="width: 100px;">
             </span>
             <span id="citySpan">
                <label for="status">市:</label> 
                <input id="city" name="cityId" class="easyui-combobox" style="width: 100px;">
             </span>
             <span id="countySpan">
                <label for="status">区县:</label> 
                <input id="county" name="countyId" class="easyui-combobox" style="width: 100px;">
             </span>
             <span id="communitySpan">
                <label for="status">社区:</label> 
                <input id="community" name="communityId" class="easyui-combobox" style="width: 100px;">
             </span>
            </div>
            <div style=" padding: 20px 0 0 20px; display: flex;">
            	<label>地区类别:</label> 
                <input id="areaLevel" name="areaLevel" class="easyui-combobox"  style="width: 100px;">
                <input type="hidden" name="userId" id="userId" value="${userId }" />
                <div id="villageSpan" style="width: 300px;">
                    <label for="villageName">小区名:</label> 
                    <input class="easyui-textbox" type="text" name="villageName" />
                </div>
                <input  type="button" onclick="searchProvince()" value="查询" />
            </div>
        </form>
    </div> 
    <table id="dg" class="easyui-datagrid" style="height: 420px;"
            url="userArea/selectListNoUser?userId=${userId }" 
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            fitColumns="true" checkbox="true" rownumbers="true" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <input type="hidden" id="userId" name="userId" value="${userId }" />
            <tr>
                <th field="id" hidden="true"></th>
                <th field="areaIds" width="10" checkbox="true" ><input name="allArea" id="allArea" type="checkbox" value="" /> </th>
                <th field="villageName" width="40" >小区名称</th>
                <th field="villageAddress" width="50">小区地址</th>
                <th field="provinceName" width="20">所属省</th>
                <th field="cityName" width="20">所属市</th>
                <th field="countyName" width="20">所属区</th>
                <th field="communityName" width="50">所属社区</th>
                <th field="areaLevel" width="5"></th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-saves" plain="true" onclick="saveUser()">保存</a>
        <!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-close" plain="true" onclick="close()">关闭</a> -->
    </div>
    <script type="text/javascript">
    
       function formatIskaidian(val,row){
           if (val == "0"){
               return '<span>否</span>';
           } else {
               return '<span>是</span>';
           }
       }
    
        function formatStatus(val,row){
            if (val == "0"){
                return '<span>启用</span>';
            } else {
                return '<span>禁用</span>';
            }
        }
    </script>
   
    <script type="text/javascript">
       var url;
       
       function searchProvince(){
          /*  $('#ff').form('submit',{  
                url: "userArea/selectListNoUser",  
                onSubmit: function(){  
                    return $(this).form('validate'); 
                },  
                success:function(data){  
                    var data = eval('('+data+')');
                    $('#dg').datagrid('loadData',data);
                    $("#dg").datagrid('hideColumn', 'areaLevel'); 
                }
            });   */
           $('#dg').datagrid({ queryParams: form2Json("ff") });

       }
       $('#areaLevel').combobox({  
           onChange:function(){
               var val = $('#areaLevel').combobox('getValue');
               if(val == "05"){
                   $("#provinceSpan").show();
                   $("#citySpan").show();
                   $("#countySpan").show();
                   $("#communitySpan").show();
                   $("#villageSpan").show();
               }
               if(val == "04"){
                   $("#provinceSpan").show();
                   $("#citySpan").show();
                   $("#countySpan").show();
                   $("#communitySpan").show();
                   $("#villageSpan").hide();
               }
               if(val == "03"){
                   $("#provinceSpan").show();
                   $("#citySpan").show();
                   $("#countySpan").show();
                   $("#communitySpan").hide();
                   $("#villageSpan").hide();
               }
               if(val == "02"){
                   $("#provinceSpan").show();
                   $("#citySpan").show();
                   $("#countySpan").hide();
                   $("#communitySpan").hide();
                   $("#villageSpan").hide();
               }
               if(val == "01"){
                   $("#provinceSpan").show();
                   $("#citySpan").hide();
                   $("#countySpan").hide();
                   $("#communitySpan").hide();
                   $("#villageSpan").hide();
               }
           }
       });
       function saveUser(){
           var oldAL = "${oldAL}";
           var rows = $("#dg").datagrid("getSelections");
           if(rows){
               var userId = "${userId}";
               var areaId = "";
               var areaLevel = "";
               for(var i = 0;i < rows.length;i++){
                   if(i == rows.length - 1) {
                       areaId = areaId + rows[i].areaId;
                   }else{
                       areaId = areaId + rows[i].areaId + ",";
                   }
                   areaLevel = rows[i].areaLevel;
               }
               if(oldAL != "" && oldAL != areaLevel){
                   $.messager.alert('提示信息','保存的区域级别和用户负责的区域级别不一致！','info');
                   return;
               }
               var data = "";
               $.post('userArea/saveUserArea/'+areaId+'/'+userId+'/'+areaLevel,'',function(result){
                   if (result.type=="Success"){
                       $.messager.show({
                           title: 'Success',
                           msg: result.msg,
                           timeout:1000,
                           showType:'slide'
                       });
                   } else {
                       $.messager.show({    // show error message
                           title: 'Error',
                           timeout:1000,
                           msg:result.msg,
                       }); 
                   }
               },'json');
               setTimeout('window.parent.close()',2000);
            }
        }        
       /*弹出diago 子画面调用父画面的方法 */
        function close(){
            setTimeout('window.parent.close()',2);
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
            $.messager.alert('提示信息','请先选择要启用的记录。','info');
        }
    }
    
    function getChanges(){
        var rows = $('#dg').datagrid('getChanges');
        alert(rows.length+' rows are changed!');
    }
    
    </script>
    
    
        
    <!-- 省市区关联 -->
    <script type="text/javascript">
    var areaLevelVal = "${areaLevel}";
    $(function(){
        if(areaLevelVal == "05"){
            $("#provinceSpan").show();
            $("#citySpan").show();
            $("#countySpan").show();
            $("#communitySpan").show();
            $("#villageSpan").show();
        }
        if(areaLevelVal == "04"){
            $("#provinceSpan").show();
            $("#citySpan").show();
            $("#countySpan").show();
            $("#communitySpan").show();
            $("#villageSpan").hide();
        }
        if(areaLevelVal == "03"){
            $("#provinceSpan").show();
            $("#citySpan").show();
            $("#countySpan").show();
            $("#communitySpan").hide();
            $("#villageSpan").hide();
        }
        if(areaLevelVal == "02"){
            $("#provinceSpan").show();
            $("#citySpan").show();
            $("#countySpan").hide();
            $("#communitySpan").hide();
            $("#villageSpan").hide();
        }
        if(areaLevelVal == "01"){
            $("#provinceSpan").show();
            $("#citySpan").hide();
            $("#countySpan").hide();
            $("#communitySpan").hide();
            $("#villageSpan").hide();
        }
        $("#dg").datagrid('hideColumn', 'areaLevel'); 
        if(areaLevelVal == ""){
            areaLevelVal = "05";
        }
        // 省份 下拉框选择控件，下拉框的内容是动态查询数据库信息 
        $('#province').combobox({ 
                    url:'common/initProvince',
                    editable:false, //不可编辑状态
                    cache: false,
                    panelHeight: '200px',//自动高度适合
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
            panelHeight: '200px',//自动高度适合
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
            panelHeight: '200px',//自动高度适合
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
            panelHeight: '200px',//自动高度适合
            valueField:'communityId',   
            textField:'communityName',
           });  
      
    });
    
    $("#areaLevel").combobox({
        url: 'sysdata/selectByType/AREALEVEL', //获取所有私有域
        valueField: "value",
        textField: "lable",
        panelHeight: "auto",
        value:areaLevelVal,
        editable: false
    });
    /**绑定页面回车事件，以及初始化页面时的光标定位**/  
    $(function(){  
    bindFormComm("ff",searchProvince);  
    });
    </script>
    
</body>
</html>