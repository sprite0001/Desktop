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
			<div style="float: left; padding: 20px 0 0 20px;">
                <label for="status">省:</label> 
                <input id="province" name="provinceId" class="easyui-combobox" style="width: 100px;">
            
                <label for="status">市:</label> 
                <input id="city" name="cityId" class="easyui-combobox" style="width: 100px;">
           
                <label for="status">区县:</label> 
                <input id="county" name="countyId" class="easyui-combobox" style="width: 100px;">
            
                <label for="status">社区:</label> 
                <input id="community" name="communityId" class="easyui-combobox" style="width: 100px;">
                
                <label for="villageName">小区名:</label> 
                <input class="easyui-textbox" type="text" name="villageName" style="width: 60px;" />
                <label for="title">手机号:</label> 
                <input class="easyui-textbox" type="text" name="mobile" id="mobile" />
            </div>
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="publishTimeBegin">发布时间介于:</label> 
                <input class="easyui-datebox" type="text" name="publishTimeBeginStr" id="publishTimeBeginStr" editable="false"/>
                <label for="publishTimeEnd">到:</label> 
                <input class="easyui-datebox" type="text" name="publishTimeEndStr" id="publishTimeEndStr" editable="false"/>
                <label for="treatmentStatus">状态:</label> 
                <input id="treatmentStatus" name="treatmentStatus" class="easyui-combobox" 
                    data-options="valueField:'value',textField:'lable',url:'common/initSysData/HEARTBEATSTATUS'" style="width: 100px;" editable="false">
                <label for="contentType">类型:</label> 
                <input id="contentType" name="contentType" class="easyui-combobox" 
                    data-options="valueField:'value',textField:'lable',url:'common/initSysData/HEARTBEATTYPE'" style="width: 100px;" editable="false">
            </div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<input  type="button" onclick="searchProvince()" value="查询" />
			</div>
		</form>

	</div> 
    <table id="dg"  class="easyui-datagrid" style="height:620px;"
            url="heartBeat/selectList" 
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            fitColumns="true" singleSelect="true" rownumbers="true" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
                <th field="id" hidden="true"></th>
                <th field="content" width="60">内容</th>
                <th field="contentTypeName" width="20" >类别</th>
                <th field="publishTime" width="50" formatter="conversion">发布时间</th>
                <th field="currentLocation" width="50">发布位置</th>
                <th field="villageName" width="50" >所属小区</th>
                <th field="mobile" width="50" >手机号</th>
                <th field="reportNumber" width="25" >举报数</th>
                <th field="treatmentStatusName" width="20" >状态</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <shiro:hasPermission name="HeartBeat:view">
	        <a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-tocomment' id="pinglunLink" plain='true' onclick='toComment()'>评论管理</a>
	        <a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-tocomment' id="jubaoLink" plain='true' onclick='toReport()'>举报管理</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="HeartBeat:verify">
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-review" id="shenheLink" plain="true" onclick="editKeywords()">审核</a>
	    </shiro:hasPermission>
        <shiro:hasPermission name="HeartBeat:manager">
            <a href='javascript:void(0)' class='easyui-linkbutton' data-options="iconCls:'icon-remove',plain:true" id="shanchuLink" onclick='destroyUser()'>删除</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="HeartBeat:view">
            <a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-view' plain='true' id="chakanLink" onclick='shenheyijian()'>查看详情</a>
        </shiro:hasPermission>
        </div>
    	<div id="dlg" class="easyui-dialog" style="padding:10px 20px;width: 800px;height: 540px;overflow:hidden;"
            closed="true" buttons="#dlg-buttons" modal="true">
		  	<iframe id='addUserFormUI' border='0' vspace='0' hspace='0' 
		    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
		    	style="height:100%;width:100%;left:10px;top:8px" src="">
		    </iframe>
	    </div>
	    <div id="dlg-buttons">
	     	<a id="weiguiLink" href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveWeiGui()" style="width:90px">违规</a>
	     	<a id="saveBtn" href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveVerify()" style="width:90px">保存</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
	    </div>
	    
    <script type="text/javascript">
    
    /**绑定页面回车事件，以及初始化页面时的光标定位**/  
    $(function(){
	    $("#publishTimeBeginStr").datebox("setValue", "${todayStr}");
	    $("#publishTimeEndStr").datebox("setValue", "${tomorrowStr}");
	    searchProvince();
	    //bindFormComm("ff",searchProvince);  
	    //点击x号关闭调用的地方(此方法必须放在dialog打开之前)
	    $('#dlg').dialog({
	        onClose:function(){
	            var dialogTitle=$('#dlg').panel('options').title;
	            if(dialogTitle=="随心拍评论的回复管理"){
	                window.history.back();
	                $('#dlg').dialog('open').dialog('setTitle','随心拍评论管理');
	            }else{
	                $('#dlg').dialog('close');
	            }
	        }
	    }); 
    
    }); 
    
     //关闭dialog函数
    function  closeDialog(){
        var dialogTitle=$('#dlg').panel('options').title;
        if(dialogTitle=="随心拍评论的回复管理"){
            window.history.back();
            $('#dlg').dialog('open').dialog('setTitle','随心拍评论管理');
        }else{
            $('#dlg').dialog('close');
        }
    }
    function conversion(value){
        var time = new Date(value);  
        return time.format("yyyy-MM-dd hh:mm:ss");
   }
    //跳转到评论管理界面
    function toComment() {
    	$('#weiguiLink').hide();
    	$('#saveBtn').hide();
        var row = $('#dg').datagrid('getSelected');
        if (row){
            $('#addUserFormUI').attr("src","heartBeat/toComment/"+row.id);
            $('#dlg').dialog('open').dialog('setTitle','随心拍评论管理');
            $('#saveA').css('display','none');
            $('#tijiaoA').css('display','none');
        }else{
            $.messager.alert('提示信息','请先选择要进行评论管理的记录。','info');
        }
    }
    //跳转到评论管理界面
    function toReport() {
    	$('#weiguiLink').hide();
    	$('#saveBtn').hide();
        var row = $('#dg').datagrid('getSelected');
        if (row){
            $('#addUserFormUI').attr("src","heartBeat/toReport/"+row.id);
            $('#dlg').dialog('open').dialog('setTitle','随心拍举报管理');
        }else{
            $.messager.alert('提示信息','请先选择要进行举报管理的记录。','info');
        }
    }
    /* 审核时，进行页面跳转的时候传入所选的row的id，然后把值传入到子画面，子画面根据该值来加载数据 */
    function editKeywords(){
    	$('#weiguiLink').hide();
    	$('#saveBtn').show();
        var row = $('#dg').datagrid('getSelected');
        if (row){
            $('#addUserFormUI').attr("src","heartBeat/toVerify/"+row.id);
            $('#dlg').dialog('open').dialog('setTitle','查看审核');
        }else{
            $.messager.alert('提示信息','请先选择要查看审核的记录。','info');
        }
    }

    function saveWeiGui(){
        var row = $('#dg').datagrid('getSelected');
        if (row){
             if(row.treatmentStatus == '02'){
                 $.messager.alert('提示信息','此随心拍已经是违规状态了！','info');
             }else{
            	 $.messager.confirm('提示','确定要设置成违规吗?',function(r){
                     if (r){
                        /* 调用共通js中是否为IE的判定方法 */
                        if(window.frames["addUserFormUI"].contentWindow==undefined){
                            window.frames["addUserFormUI"].optSubmit();
                       }else{
                            window.frames["addUserFormUI"].contentWindow.optSubmit();
                       }
                    }
                });
             }
        }else{
            $.messager.alert('提示信息','请先选择随心拍！','info');
        }
    }
    function saveVerify(){
          /* 调用共通js中是否为IE的判定方法 */
          if(window.frames["addUserFormUI"].contentWindow==undefined){
              window.frames["addUserFormUI"].optSubmit();
         }else{
              window.frames["addUserFormUI"].contentWindow.optSubmit();
         }
   }
	   function searchProvince(){
		   $('#dg').datagrid({ queryParams: form2Json("ff") });
	   }
        
        function huifu(){
        	$('#weiguiLink').hide();
        	$('#saveBtn').hide();
        	  var row = $('#dg').datagrid('getSelected');
              if (row){
            	   $('#addUserFormUI').attr("src","heartBeat/huifuOneAdd");
                   $('#dlg').dialog('open').dialog('setTitle','评论');
              }else{
                  $.messager.alert('提示信息','请先选择随心拍！','info');
              }
        }
        
        /* 编辑时，进行页面跳转的时候传入所选的row的id，然后把值传入到子画面，子画面根据该值来加载数据 */
        function editUser(){
        	$('#weiguiLink').hide();
        	$('#saveBtn').hide();
            var row = $('#dg').datagrid('getSelected');
            if (row){
            	$('#addUserFormUI').attr("src","heartBeat/toEdit/"+row.id);
                $('#dlg').dialog('open').dialog('setTitle','编辑');
            }else{
            	$.messager.alert('提示信息','请先选择要更新的记录。','info');
            }
        }
        function initRow(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $("#pinglunLink").show();
                $("#shanchuLink").show();
                var treatmentStatus = row.treatmentStatus;
                if(treatmentStatus == '01'){
                    $("#jubaoLink").hide();
                    $("#shenheLink").hide();
                }else{
                    $("#jubaoLink").show();
                    if(treatmentStatus == '02' || treatmentStatus == '04'){
                        $("#shenheLink").hide();
                    }else{
                        $("#shenheLink").show();
                    }
                }
            }else{
                $("#pinglunLink").hide();
                $("#jubaoLink").hide();
                $("#shenheLink").hide();
                $("#shanchuLink").hide();
            }
        }
        $(function(){
            initRow();
        });
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
                        $.post('heartBeat/delete',{id:row.id,delFlag:'1'},function(result){
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
    $(function(){
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
	function shenheyijian(){
		$('#weiguiLink').show();
    	$('#saveBtn').hide();
           var row = $('#dg').datagrid('getSelected');
           if(row){
        	   
               var treatmentStatus = row.treatmentStatus;
        	   if(treatmentStatus != '01'){
        		   $("#weiguiLink").hide();
        	   }
               $('#addUserFormUI').attr("src","heartBeat/toShow/"+row.id);
               $('#dlg').dialog('open').dialog('setTitle','详情');
           }else{
               $.messager.alert('提示信息','请先选需要查看的随心拍！','info');
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
	function getChanges(){
		var rows = $('#dg').datagrid('getChanges');
		alert(rows.length+' rows are changed!');
	}
    </script>
</body>
</html>