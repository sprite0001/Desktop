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

   <div style="height: 60px; width: 90%;">
        <form id="ff" method="post">
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="title">标题:</label> 
                <input class="easyui-textbox" type="text" name="title" id="title" />
                <label for="newsflashTimeBeginStr">快报时间介于:</label> 
                <input class="easyui-datebox" type="text" name="newsflashTimeBeginStr" id="newsflashTimeBeginStr" editable="false"/>
                <label for="newsflashTimeEndStr">到:</label> 
                <input class="easyui-datebox" type="text" name="newsflashTimeEndStr" id="newsflashTimeEndStr" editable="false" />
                <label for="verifyStatus">审核状态:</label> 
                <input id="verifyStatus" name="verifyStatus" class="easyui-combobox" 
                    data-options="valueField:'value',textField:'lable',url:'admNewsflash/selectVerifyType'" style="width: 100px;" editable="false">
            </div>
            <div style="float: left; padding: 20px 0 0 20px;">
                <shiro:hasPermission name="AdmNewsflash:manager">
                    <input  type="button" onclick="searchKeyWords()" value="查询" />
                </shiro:hasPermission>
            </div>
        </form>
    </div> 
    <table id="dg"  class="easyui-datagrid" style="height: 600px;"
            url="admNewsflash/selectListVerify"
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            rownumbers="true" fitColumns="true" singleSelect="true" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
                <th field="title" width="60">标题</th>
                <th field="newsflashTime" width="40" formatter="conversion">快报时间</th>
                <th field="submitUserName" width="45">提交人</th>
                <th field="submitTime" width="40" formatter="conversion">提交时间</th>
                <th field="verifyStatusName" width="20">审核状态</th>
                <th field="scannedNumber" width="30">浏览</th>
            </tr>
        </thead>
    </table>
    <shiro:hasPermission name="AdmNewsflash:verify">
	    <div id="toolbar">
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-review" id="shenhe" plain="true" onclick="editKeywords()">审核</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-view" id="chakan" plain="true" onclick="view()">查看详情</a>
	    </div>
    </shiro:hasPermission>
    <div id="editordlg" class="easyui-dialog" style="padding:10px 20px;width: 900px;height: 580px;overflow:hidden;"
           closed="true" buttons="#editordlg-buttons" modal="true">
        <iframe id='editorKeywordsFormUI' border='0' vspace='0' hspace='0' 
            marginwidth='0' marginheight='0' framespacing='0' frameborder='0'
            style="height:100%;width:100%;left:10px;top:8px" src="">
        </iframe>
    </div>
    <div id="editordlg-buttons">
        <shiro:hasPermission name="AdmNewsflash:manager">
            <a id="saveBtnA" href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="updateSubmit('2')" style="width:90px">通过</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="AdmNewsflash:manager">
            <a id="saveBtnB" href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="updateSubmit('3')" style="width:90px">不通过</a>
        </shiro:hasPermission>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#editordlg').dialog('close')" style="width:90px">关闭</a>
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
             url: "admNewsflash/selectListVerify",  
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
	  /* 审核时，进行页面跳转的时候传入所选的row的id，然后把值传入到子画面，子画面根据该值来加载数据 */
	  function editKeywords(){
		  $('#saveBtnA').show();
		  $('#saveBtnB').show();
	      var row = $('#dg').datagrid('getSelected');
	      if (row){
	          $('#editorKeywordsFormUI').attr("src","admNewsflash/toVerify/"+row.id);
	          $('#editordlg').dialog('open').dialog('setTitle','查看审核');
	      }else{
	          $.messager.alert('提示信息','请先选择要查看审核的记录。','info');
	      }
	  }
	  function view(){
		  $('#saveBtnA').hide();
		  $('#saveBtnB').hide();
          var row = $('#dg').datagrid('getSelected');
          if (row){
              $('#editorKeywordsFormUI').attr("src","admNewsflash/toVerify/"+row.id);
              $('#editordlg').dialog('open').dialog('setTitle','查看');
          }else{
              $.messager.alert('提示信息','请先选择要查看的记录。','info');
          }
      }
	  function updateSubmit(status){
	      /* 调用共通js中是否为IE的判定方法 */
	       if(window.frames["editorKeywordsFormUI"].contentWindow==undefined){
	            window.frames["editorKeywordsFormUI"].optSubmit(status);
	       }else{
	            window.frames["editorKeywordsFormUI"].contentWindow.optSubmit(status);
	       }
	  } 
	 /*弹出diago 子画面调用父画面的方法 */
	  function close(){
	      $('#editordlg').dialog('close');
	      $('#dg').datagrid('reload');
	  }
	  function initRow(){
	        var row = $('#dg').datagrid('getSelected');
	        if (row){
	            var status = row.verifyStatus;
	            if(status == '1'){
	                $("#shenhe").show();
	            }else{
                    $("#shenhe").hide();
	            }
	        }else{
	            $("#shenhe").hide();
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
    /* 行审核时所用的方法 */
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