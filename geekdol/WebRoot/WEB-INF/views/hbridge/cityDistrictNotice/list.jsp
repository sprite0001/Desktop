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
        <form id="ff" method="post">
            <div style=" padding: 20px 0 0 20px;">
                <span id="cityDiv" style="display:none;">
	                <label for="status">市:</label> 
                    <input id="cityId" name="cityId" class="easyui-combobox" style="width: 150px;">
                </span>
                <span id="countyDiv" style="display:none;">
	                <label for="status">区县:</label> 
                    <input id="countyId" name="countyId" class="easyui-combobox" style="width: 150px;">
                </span>
                <span id="communityDiv" style="display:none;">
	                <label for="status">社区:</label>
                    <input id="communityId" name="communityId" class="easyui-combobox" style="width: 150px;">
	            </span>
	            <label for="areaLevelLabel">地区类别:</label> 
	            <input type="hidden" name="areaLevel" id="areaLevel" value="${areaLevel}" />
	            <input type="text" id="areaName" name="areaName" value="${areaName}" class="easyui-textbox" disabled = "true">
            </div>
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="title">标题:</label> 
                <input class="easyui-textbox" type="text" name="title" id="title" />
                <label for="publishTimeBegin">发布时间介于:</label> 
                <input class="easyui-datebox" type="text" name="publishTimeBeginStr" id="publishTimeBeginStr" editable="false"/>
                <label for="publishTimeEnd">到:</label> 
                <input class="easyui-datebox" type="text" name="publishTimeEndStr" id="publishTimeEndStr" editable="false"/>
                  <label for="publishStatus">发布状态:</label> 
                  <select id="publishStatus" name="publishStatus" class="easyui-combobox" style="width: 60px;" editable="false">
                    <option value="">全部</option>
                    <option value="0">未发布</option>
                    <option value="1">已发布</option>
                  </select>
            </div>
            <div style="float: left; padding: 20px 0 0 20px;">
	            <shiro:hasPermission name="CityDistrictNotice:view">
	                <input  type="button" onclick="searchKeyWords()" value="查询" />
	            </shiro:hasPermission>
            </div>
        </form>
    </div> 
    <table id="dg"  class="easyui-datagrid" style="height: 500px;"
            url="cityDistrictNotice/selectList"
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            rownumbers="true" fitColumns="true" singleSelect="true" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
                <th field="title" width="20" >标题</th>
                <th field="areaLevelName" width="50" >区域级别</th>
                <th field="areaName" width="50" >所属区域</th>
                <th field="publishStatus" width="50" formatter="formatStatus" >发布状态</th>
                <th field="publishTime" width="50" formatter="conversion">发布时间</th>
                <th field="scannedNumber" width="50" >浏览</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
      <shiro:hasPermission name="CityDistrictNotice:manager">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newKeywords()">新增</a>
      </shiro:hasPermission>
      <shiro:hasPermission name="CityDistrictNotice:manager">  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" id="bianji" plain="true" onclick="editKeywords()">编辑</a>
      </shiro:hasPermission>
      <shiro:hasPermission name="CityDistrictNotice:manager">
        <a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-top' id="zhidinglink" plain='true' onclick='toZhiding()'>置顶</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-storenotice" id="fabu" plain="true" onclick="status(1)">发布</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-quxiaofabu" id="quxiao" plain="true" onclick="status(0)">取消发布</a>
      </shiro:hasPermission>
      <shiro:hasPermission name="CityDistrictNotice:manager">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" id="shanchu" plain="true" onclick="destroyKeywords()">删除</a>
      </shiro:hasPermission>
      <shiro:hasPermission name="CityDistrictNotice:manager">
        <a href='javascript:void(0)' class='easyui-linkbutton' id="show" iconCls='icon-view' plain='true' onclick='details()'>查看详情</a>
      </shiro:hasPermission>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="padding:10px 20px;width: 900px;height: 580px;overflow:hidden;"
        closed="true" buttons="#dlg-buttons" modal="true">
        <iframe id='addKeywordsFormUI' border='0' vspace='0' hspace='0' 
            marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
            style="height:100%;width:100%;left:10px;top:8px" src="">
        </iframe>
    </div>
      <div id="dlg-buttons">
        <a id="saveBtnA" href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveKeywords('0')" style="width:90px">保存</a>
        <a id="saveBtnB" href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveKeywords('1')" style="width:90px">保存并发布</a>
        <a id="editBtnA" href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="updateSubmit('')" style="width:90px">保存</a>
    	<a id="editBtnB" href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="updateSubmit('1')" style="width:90px">保存并发布</a>
    	<a id="zhidingBtn" href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveZhiding()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
    </div>
<script type="text/javascript">
	var areaLevelT = "${areaLevel}";
	if(areaLevelT == "04"){
	    $("#communityDiv").show();
        $('#communityId').combobox({ 
            url:'cityDistrictNotice/initArea',
            editable:false, //不可编辑状态
            cache: false,
            panelHeight: '200px',//自动高度适合
            valueField:'areaId',   
            textField:'areaName',
        }); 
	}
	if(areaLevelT == "03"){
	    $("#countyDiv").show();
        $('#countyId').combobox({ 
            url:'cityDistrictNotice/initArea',
            editable:false, //不可编辑状态
            cache: false,
            panelHeight: '200px',//自动高度适合
            valueField:'areaId',   
            textField:'areaName',
        }); 
	}
	if(areaLevelT == "02"){
	    $("#cityDiv").show();
        $('#cityId').combobox({ 
            url:'cityDistrictNotice/initArea',
            editable:false, //不可编辑状态
            cache: false,
            panelHeight: '200px',//自动高度适合
            valueField:'areaId',   
            textField:'areaName',
        }); 
	}
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
             url: "cityDistrictNotice/selectList",  
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
  //跳转到置顶页面
    function toZhiding() {
	  $('#saveBtnA').hide();
	  $('#saveBtnB').hide();
	  $('#editBtnA').hide();
	  $('#editBtnB').hide();
	  $('#zhidingBtn').show();
        var row = $('#dg').datagrid('getSelected');
        if (row){
            $('#addKeywordsFormUI').attr("src","cityDistrictNotice/toZhiDing/"+row.id);
            $('#dlg').dialog('open').dialog('setTitle','市区办事处公告置顶');
        }else{
            $.messager.alert('提示信息','请先选择要进行置顶的记录。','info');
        }
    }
    function details(){
    	$('#saveBtnA').hide();
  	  $('#saveBtnB').hide();
  	  $('#editBtnA').hide();
  	  $('#editBtnB').hide();
  	  $('#zhidingBtn').hide();
        var row = $('#dg').datagrid('getSelected');
        if(row){
            $('#addKeywordsFormUI').attr("src","cityDistrictNotice/toShow/"+row.id);
            $('#dlg').dialog('open').dialog('setTitle','详情');
        }else{
            $.messager.alert('提示信息','请先选需要查看的市区办事处公告！','info');
        }
  }
    //置顶
    function saveZhiding(){
        /* 调用共通js中是否为IE的判定方法 */
       if(window.frames["addKeywordsFormUI"].contentWindow==undefined){
            window.frames["addKeywordsFormUI"].optSubmit();
       }else{
            window.frames["addKeywordsFormUI"].contentWindow.optSubmit();
       }
    }
    function newKeywords(){
    	$('#saveBtnA').show();
  	  $('#saveBtnB').show();
  	  $('#editBtnA').hide();
  	  $('#editBtnB').hide();
  	  $('#zhidingBtn').hide();
    	var areaLevel = $("#areaLevel").val();
    	if(areaLevel == '05'){
            $.messager.alert('提示信息','您无权发布此等级公告，只能发布小区公告。','info');
            return;
    	}
    	if(areaLevel == '01'){
            $.messager.alert('提示信息','省级管理员不能发布市区办事处公告。','info');
            return;
        }
        $('#addKeywordsFormUI').attr("src","cityDistrictNotice/toAdd");
        $("#addKeywordsFormUI").css({"width":"800px" ,"height":"500px"});
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
      	  $('#zhidingBtn').hide();
            var areaLevel = $("#areaLevel").val();
            if(areaLevel == '05'){
                $.messager.alert('提示信息','您无权发布此等级公告，只能发布小区公告。','info');
                return;
            }
            var row = $('#dg').datagrid('getSelected');
            if (row){
                var publishStatus = row.publishStatus;
                if(publishStatus == "1"){
                    $.messager.alert('提示信息','此公告已经发布，无法编辑。','info');
                    return;
                }
                $('#addKeywordsFormUI').attr("src","cityDistrictNotice/toUpdate/"+row.id);
                $("#addKeywordsFormUI").css({"width":"800px" ,"height":"500px"});
                $('#dlg').dialog('open').dialog('setTitle','编辑');
            }else{
                $.messager.alert('提示信息','请先选择要更新的记录。','info');
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
                 $.messager.confirm('提示','确定要删除吗?',function(r){
                     if (r){
                         $.post('cityDistrictNotice/delete',{id:row.id},function(result){
                             if (result.type=='Success'){
                                   $.messager.show({    // show error message
                                       title: 'Success',
                                       msg: result.msg
                                   });
                                 //$('#dg').datagrid('reload');
                                 searchKeyWords()
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
            var str = "取消发布";
            if(val == '1'){
                str = "发布";
            }
            if (row){
                if(row.publishStatus == val){
                    if(val == '1'){
                        $.messager.alert('提示信息','当前公告已发布。','info');
                        return;
                    }else{
                        $.messager.alert('提示信息','当前公告已取消发布。','info');
                        return;
                    }
                }
                $.messager.confirm('提示','确定要'+str+'吗?',function(r){
                    if (r){
                        $.post('cityDistrictNotice/changeStatus',{id:row.id,publishStatus:val},function(result){
                            if (result.type=="Success"){
                                //$('#dg').datagrid('reload');
                                searchKeyWords();
                            } else {
                             $.messager.show({    // show error message
                                    title: 'Error',
                                    msg:result.msg
                                }); 
                            }
                        },'json');
                    }
                });
            }else{
                $.messager.alert('提示信息','请先选择要'+str+'的记录。','info');
            }
        }
       /*弹出diago 子画面调用父画面的方法 */
        function close(){
            $('#dlg').dialog('close');
            $('#dg').datagrid('reload');
        }
        function initRow(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $("#bianji").show();
                $("#shanchu").show();
                $("#show").show();
                var status = row.publishStatus;
                if(status == '0'){
                    $("#fabu").show();
                    $("#quxiao").hide();
                    $("#zhidinglink").hide();
                }else{
                    $("#fabu").hide();
                    $("#quxiao").show();
                    $("#zhidinglink").show();
                }
            }else{
                $("#bianji").hide();
                $("#shanchu").hide();
                $("#fabu").hide();
                $("#quxiao").hide();
                $("#zhidinglink").hide();
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