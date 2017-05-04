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
            <label for="status">发布状态:</label>
                <select id="publishStatus" name="publishStatus" class="easyui-combobox" style="width: 80px;" editable="false">
                    <option value="">全部</option>
                    <option value="0">未发布</option>
                    <option value="1">已发布</option>
             </select>
            </div>
            
           
			<div style="float: left; padding: 20px 0 0 20px;">
				<input  type="button" onclick="searchProvince()" value="查询" />
			</div>
		</form>


	</div> 
    <table id="dg"  class="easyui-datagrid" style="height:620px;"
            url="intimateNews/findyishenheAll" 
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            fitColumns="true" singleSelect="true" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
                <th field="title" width="60">标题</th>
            	<th field="villageName" width="60" >所属小区</th>
                <th field="reportTime" width="45" formatter="conversion">报道时间</th>
                <th field="publishStatus" width="20" formatter="shifoufabu">发布状态</th>
                <th field="publishTime" width="45"  formatter="fabushijian">发布时间</th>
                <th field="replyFlag" width="20" formatter="shifouhuifuorzhiding">允许回复</th>
                <th field="repeatFlag" width="25" formatter="shifouzhuanfa">是否来自转发</th>
                <th field="hotnumber" width="35" formatter="quxiaofabuchuli">浏览/回复/违规</th>
            </tr>
        </thead>
    </table>
    
    <div id="toolbar">
    <shiro:hasPermission name="IntimateNewQ:manager">
     <a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-add' plain='true' onclick='newUser()'>新增</a>
    </shiro:hasPermission>
    </div>
   
    	<div id="dlg" class="easyui-dialog" style="padding:10px 20px;width:800px;height:540px;overflow:hidden;"
            closed="true" buttons="#dlg-buttons" modal="true">
		  	<iframe id='addUserFormUI' border='0' vspace='0' hspace='0' 
		    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
		    	style="height:100%;width:100%;left:10px;top:8px" src="">
		    </iframe>
	    </div>
	     <div id="dlg-buttons">
	        <a id="saveBtnA" href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser('0')" style="width:90px">保存</a>
	        <a id="saveBtnB" href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-storenotice" onclick="saveUser('1')" style="width:90px">发布</a>
	        <a id="zhidingBtn" href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveZhiding()" style="width:90px">保存</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="closeDialog()" style="width:90px">关闭</a>
	    </div>
   
    <script type="text/javascript">
    
    
    //关闭dialog函数
    function  closeDialog(){
        var dialogTitle=$('#dlg').panel('options').title;
        if(dialogTitle=="评论回复"){
            window.history.back();
            $('#dlg').dialog('open').dialog('setTitle','评论管理');
        }else{
            $('#dlg').dialog('close');
        }
    }
    
    function shifouzhuanfa(value){
        if(value=="1"){
            return "是";
        }else{
            return "否";
        } 
    }
    
    //取消发布处理
    function quxiaofabuchuli(value,row){
        if(row.publishStatus=="1"){
            return row.hotnumber;
        }else{
            return "0/0/0";
        }
    } 
    
  //跳转到评论管理界面
    function toComment() {
	  $('#saveBtnA').hide();
	  $('#saveBtnB').hide();
	  $('#zhidingBtn').hide();
        var row = $('#dg').datagrid('getSelected');
        if (row){
            $('#addUserFormUI').attr("src","intimateNews/toCommentIntimateNews/"+row.id);
            $('#dlg').dialog('open').dialog('setTitle','评论管理');
        }else{
            $.messager.alert('提示信息','请先选择评论记录。','info');
        }
    }
    
    //跳转到置顶页面
    function toZhiding() {
    	$('#saveBtnA').hide();
  	  $('#saveBtnB').hide();
  	  $('#zhidingBtn').show();
        var row = $('#dg').datagrid('getSelected');
        if (row){
            $('#addUserFormUI').attr("src","intimateNews/toZhiding/"+row.id);
            $('#dlg').dialog('open').dialog('setTitle','贴心报置顶');
        }else{
            $.messager.alert('提示信息','请先选择要进行置顶的记录。','info');
        }
    }
    
    
    function newUser(){
    	$('#saveBtnA').show();
  	  $('#saveBtnB').show();
  	  $('#zhidingBtn').hide();
        $('#addUserFormUI').attr("src","intimateNews/fabuAdd");
        $('#dlg').dialog('open').dialog('setTitle','新增');
        $('#fm').form('clear');
    }
    
    
    function conversion(value){
        var time = new Date(value);  
        return time.format("yyyy-MM-dd hh:mm:ss");
   }
    
    function fabushijian(value){
    	if(value){
    		 var time = new Date(value);  
    	     return time.format("yyyy-MM-dd hh:mm:ss");
    	}
   }
    
    function shifoufabu(val,row){
        if (val == "0"){
            return "<span style='color:red'>未发布</span>";
        } else {
            return '<span>已发布</span>';
        }
    }
    
    function shifouhuifuorzhiding(val,row){
        if (val == "0"){
            return '<span>否</span>';
        } else {
            return '<span>是</span>';
        }
    }
	   
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
    
    
    function shifouhuifuorzhiding(val,row){
        if (val == "0"){
            return '<span>否</span>';
        } else {
            return '<span>是</span>';
        }
    }
    
	   function searchProvince(){
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
        	$('#saveBtnA').hide();
      	  $('#saveBtnB').hide();
      	  $('#zhidingBtn').hide();
            var row = $('#dg').datagrid('getSelected');
            if(row){
                $('#addUserFormUI').attr("src","intimateNews/details/"+row.id);
                $('#dlg').dialog('open').dialog('setTitle','详情');
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
        
        /* 编辑时，进行页面跳转的时候传入所选的row的id，然后把值传入到子画面，子画面根据该值来加载数据 */
        function editUser(){
        	$('#saveBtnA').show();
      	  $('#saveBtnB').show();
      	  $('#zhidingBtn').hide();
            var row = $('#dg').datagrid('getSelected');
            if (row){
            	$('#addUserFormUI').attr("src","intimateNews/tofabuEdit/"+row.id);
                $('#dlg').dialog('open').dialog('setTitle','编辑');
            }else{
            	$.messager.alert('提示信息','请先选择要更新的记录。','info');
            }
        }
        function saveUser(publishStatus){
            /* 调用共通js中是否为IE的判定方法 */
   		   if(window.frames["addUserFormUI"].contentWindow==undefined){
   	            window.frames["addUserFormUI"].optSubmit(publishStatus);
   	       }else{
   	            window.frames["addUserFormUI"].contentWindow.optSubmit(publishStatus);
   	       }
        }
        //置顶
        function saveZhiding(){
            /* 调用共通js中是否为IE的判定方法 */
            if(window.frames["addUserFormUI"].contentWindow==undefined){
                window.frames["addUserFormUI"].optSubmit();
           }else{
                window.frames["addUserFormUI"].contentWindow.optSubmit();
           }
        }
        //发布
        function fabu(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('提示','确定要发布吗?',function(r){
                    if (r){
                        $.post('intimateNews/fabuIntimateNews',{id:row.id,publishStatus:'1'},function(result){
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
                $.messager.alert('提示信息','请先选择要发布的记录。','info');
            }
        }
        
        //取消发布
        function quxiaofabu(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('提示','确定要取消发布吗?',function(r){
                    if (r){
                        $.post('intimateNews/quxiaofabuIntimateNews',{id:row.id,publishStatus:'0'},function(result){
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
                $.messager.alert('提示信息','请先选择要取消发布的记录。','info');
            }
        }
        
        //删除
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
             <shiro:hasPermission name="IntimateNewQ:view">
             //未发布
             if(row.publishStatus=='0'){
            	 <shiro:hasPermission name="IntimateNewQ:manager">
            	 $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-add' plain='true' onclick='newUser()'>新增</a>");
                 $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-edit' plain='true' onclick='editUser()'>编辑</a>");
                 $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconCls:'icon-remove',plain:true\" onclick='destroyUser()'>删除</a>");
                 $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconCls:'icon-storenotice',plain:true\" onclick='fabu()'>发布</a>");
                 </shiro:hasPermission>
                 $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-view' plain='true' onclick='shenheyijian()'>查看详情</a>");
                 $.parser.parse($('#toolbar'));
              }
             //已发布
             if(row.publishStatus=='1'){
            	 <shiro:hasPermission name="IntimateNewQ:manager">
            	 $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-add' plain='true' onclick='newUser()'>新增</a>");
                 $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-tocomment' plain='true' onclick='toComment()'>评论管理</a>");
                 $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-top' plain='true' onclick='toZhiding()'>置顶</a>");
                 $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-quxiaofabu' plain='true' onclick='quxiaofabu()'>取消发布</a>");
                 </shiro:hasPermission>
                 $('#toolbar').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-view' plain='true' onclick='shenheyijian()'>查看详情</a>");
                 $.parser.parse($('#toolbar'));
              }
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
	/**绑定页面回车事件，以及初始化页面时的光标定位**/  
    $(function(){  
        bindFormComm("ff",searchProvince);
        
      //点击x号关闭调用的地方
        $('#dlg').dialog({
            onClose:function(){
                var dialogTitle=$('#dlg').panel('options').title;
                if(dialogTitle=="评论回复"){
                    window.history.back();
                    $('#dlg').dialog('open').dialog('setTitle','评论管理');
                }else{
                    $('#dlg').dialog('close');
                }
            }
        }); 
        
        }); 
    </script>
</body>
</html>