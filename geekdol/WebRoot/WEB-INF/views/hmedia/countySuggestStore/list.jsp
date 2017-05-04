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
	<div class="easyui-tabs" style="width:auto;">
		<div title="推荐给我">
			<div style="height: 100px; width: 90%;">
			    <form id="from" method="post">
			        <div style="float: left; padding: 20px 0 0 20px;">
			        	<div style="display:inline-flex;margin-right:10px">
			        		<div style="width:50px">
								<label for="name">店名:</label>
							</div>
							<input id="name" name="name" class="easyui-textbox" type="text">
						</div>
						<div style="display:inline-flex;margin-right:10px">
			        		<div style="width:50px">
								<label for="villageName">小区名:</label> 
							</div>
							<input id="villageName" name="villageName" class="easyui-textbox" type="text">
						</div>
						<div style="display:inline-flex;margin-right:10px">
							<div style="width:50px">
								<label for="handleStatus">状态:</label> 
							</div>
							<input id="handleStatus" name="handleStatus" class="easyui-combobox" 
								data-options="editable:false,valueField:'value',textField:'lable',url:'common/initSysData/${HANDLESTATUS}'" style="width: 100px;">
						</div>
					</div>
					<div style="float: left; padding: 20px 0 0 20px;" >
						<div style="display:inline-flex;margin-right:10px">
							<div style="width:50px">
								<label for="concat">联系人:</label>
							</div>
							<input class="easyui-textbox" type="text" name="concat" id="concat">
						</div>
						<div style="display:inline-flex;margin-right:10px">
							<div style="width:50px">
								<label for="concatPhone">电话:</label>
							</div>
							<input class="easyui-textbox" type="text" name="concatPhone" id="concatPhone">
						</div>
						<div style="display:inline-flex;margin-right:10px">
							<div style="width:50px">
								<label for="storeType">类型:</label> 
							</div>
							<input id="storeType" name="storeType" class="easyui-combobox" 
								data-options="editable:false,valueField:'value',textField:'lable',url:'common/initSysData/STORETYPE'" style="width: 100px;">
						</div>
						<input type="button" onclick="searchCSSTP(0,0)" value="查询" />
					</div>
			    </form>
		    </div>
		    <table id="dgFrom" class="easyui-datagrid" style="height: 620px;"
		    		url="countySuggestStore/findAll/0"
		            toolbar="#toolbarfrom" pagination="true" queryParams="form2Json('from')"
		            fitColumns="true" singleSelect="true" rownumbers="true"
		            data-options="onClickRow: onClickRowFrom,fit:false,border:false,pageSize:20,pageList:[20,50,100,200]" >
		        <thead>
		            <tr>
		            	<th field="name" width="50" >店名</th>
		                <th field="villageName" width="50">所在小区</th>
		                <th field="storeTypeName" width="50">类型</th>
		                <th field="suggestPeople" width="50">推荐人</th>
		                <th field="suggestPeoplePhone" width="50">推荐人电话</th>
		                <th field="sugDate" width="50">推荐时间</th>
		                <th field="handleStatus" width="50" formatter="formatStatusFrom">处理状态</th>
		            </tr>
		        </thead>
		    </table>
		    <shiro:hasPermission name="CountySuggestS:manager">
			    <div id="toolbarfrom">
					<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-details' plain='true' onclick='toView(0)'>详情</a>
					<a href='javascript:void(0)' class='easyui-linkbutton' data-options="iconCls:'icon-jieshou',plain:true" onclick='acceptSuggest()'>接受</a>
					<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-jujue' plain='true' onclick='editstatus(0,3)'>拒绝</a>
			    </div>
		    </shiro:hasPermission>
		    <div id="dlgFrom" class="easyui-dialog" style="padding:10px 20px;width: 800px;height: 580px;overflow: hidden;"
		           closed="true" buttons="#dlg-buttonsFrom" modal="true">
			  	<iframe id='CountyStoreFormUIFrom' border='0' vspace='0' hspace='0' 
			    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
			    	style="height:100%;width:100%;left:10px;top:8px" src="">
			    </iframe>
		    </div>
		    <div id="dlg-buttonsFrom" style="display:none">
		        <a href="javascript:void(0)" id="saveBtn" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveSubmit()" style="width:90px">保存</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlgFrom').dialog('close')" style="width:90px">取消</a>
		    </div>
	    </div>
	    <div title="我的推荐">
	    	<div style="height: 100px; width: 90%;">
		    	<form id="to" method="post">
			        <div style="float: left; padding: 20px 0 0 20px;">
			        	<div style="display:inline-flex;margin-right:10px">
							<div style="width:50px">
								<label for="name">店名:</label> 
							</div>
							<input id="name" name="name" class="easyui-textbox" type="text">
						</div>
						<div style="display:inline-flex;margin-right:10px">
							<div style="width:50px">
								<label for="villageName">小区名:</label>
							</div>
							<input id="villageName" name="villageName" class="easyui-textbox" type="text">
						</div>
						<div style="display:inline-flex;margin-right:10px">
							<div style="width:50px">
								<label for="handleStatus">状态:</label> 
							</div>
							<input id="handleStatus" name="handleStatus" class="easyui-combobox" 
								data-options="editable:false,valueField:'value',textField:'lable',url:'common/initSysData/HANDLESTATUS1'" style="width: 100px;">
						</div>
					</div>
					<div style="float: left; padding: 20px 0 0 20px;" >
						<div style="display:inline-flex;margin-right:10px">
							<div style="width:50px">
								<label for="concat">联系人:</label>
							</div>
							<input class="easyui-textbox" type="text" name="concat" id="concat">
						</div>
						<div style="display:inline-flex;margin-right:10px">
							<div style="width:50px">
								<label for="concatPhone">电话:</label>
							</div>
							<input class="easyui-textbox" type="text" name="concatPhone" id="concatPhone">
						</div>
						<div style="display:inline-flex;margin-right:10px">
							<div style="width:50px">
								<label for="storeType">类型:</label>
							</div>
							<input id="storeType" name="storeType" class="easyui-combobox" 
								data-options="editable:false,valueField:'value',textField:'lable',url:'common/initSysData/STORETYPE'" style="width: 100px;">
						</div>
						<input type="button" onclick="searchCSSTP(1,1)" value="查询" />
					</div>
			    </form>
		    </div>
		    <table id="dgTo" class="easyui-datagrid" style="height: 620px;"
		    		url="countySuggestStore/findAll/1"
		            toolbar="#toolbarto" pagination="true" queryParams="form2Json('to')"
		            fitColumns="true" singleSelect="true" rownumbers="true"
		            data-options="onClickRow: onClickRowTo,fit:false,border:false,pageSize:20,pageList:[20,50,100,200]" >
		        <thead>
		            <tr>
		            	<th field="name" width="50" >店名</th>
		                <th field="villageName" width="50">所在小区</th>
		                <th field="storeTypeName" width="50">类型</th>
		                <th field="suggestPeople" width="50">被推荐人</th>
		                <th field="suggestPeoplePhone" width="50">被推荐人电话</th>
		                <th field="sugDate" width="50">推荐时间</th>
		                <th field="handleStatus" width="50" formatter="formatStatusTo">处理状态</th>
		            </tr>
		        </thead>
		    </table>
		    <shiro:hasPermission name="CountySuggestS:manager">
			    <div id="toolbarto">
					<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-edit' plain='true' onclick='toView(1)'>详情</a>
					<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-editstatus' plain='true' onclick='editstatus(1,0)'>撤销推荐</a>
			    </div>
		    </shiro:hasPermission>
		    <div id="dlgTo" class="easyui-dialog" style="padding:10px 20px;width: 800px;height: 580px;overflow: hidden;"
		           closed="true" buttons="#dlg-buttonsTo" modal="true">
			  	<iframe id='CountyStoreFormUITo' border='0' vspace='0' hspace='0' 
			    	marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' 
			    	style="height:100%;width:100%;left:10px;top:8px" src="">
			    </iframe>
		    </div>
		    <div id="dlg-buttonsTo" style="display:none">
		        <a href="javascript:void(0)" id="saveBtn" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveSubmit()" style="width:90px">保存</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlgTo').dialog('close')" style="width:90px">取消</a>
		    </div>
	    </div>
    </div>
</body>
<script type="text/javascript">
/*弹出diago 子画面调用父画面的方法 */
function close(){
	$('#dlgFrom').dialog('close');
	$('#dgFrom').datagrid('reload');
}

	function saveSubmit(){
	    /* 调用共通js中是否为IE的判定方法 */
	   if(window.frames["CountyStoreFormUIFrom"].contentWindow==undefined){
            window.frames["CountyStoreFormUIFrom"].optSubmit();
       }else{
            window.frames["CountyStoreFormUIFrom"].contentWindow.optSubmit();
       }
	}
	function formatStatusFrom(val,row){
		if (val == "1") {
			return "<span style='color:red'>待处理</span>";
		} else if (val == "2") {
			return '<span>接受</span>';
		} else {
			return "<span style='color:#990000'>拒绝</span>";
		}
	}
	function formatStatusTo(val,row){
		var suggestFlag = row.suggestFlag;
		if (suggestFlag == "0") {
			return "<span style='color:red'>取消推荐</span>";
		} else {
			if (val == "1") {
				return "<span>已推荐</span>";
			} else if (val == "2") {
				return '<span>接受</span>';
			} else {
				return "<span style='color:#990000'>拒绝</span>";
			}
		}
	}
	function searchCSSTP(flag,val){
		if (flag == "0") {
			/* $('#from').form('submit',{  
			    url: "countySuggestStore/findAll/"+val,  
			    onSubmit: function(){  
			    	return $(this).form('validate'); 
			    },  
			    success:function(data){  
			    	var data = eval('('+data+')');
			       $('#dgFrom').datagrid('loadData',data);
			    }
			}); */
			$('#dgFrom').datagrid({ queryParams: form2Json("from") });
		} else {
			/* $('#to').form('submit',{  
			    url: "countySuggestStore/findAll/"+val,  
			    onSubmit: function(){  
			    	return $(this).form('validate'); 
			    },  
			    success:function(data){  
			    	var data = eval('('+data+')');
			       $('#dgTo').datagrid('loadData',data);
			    }
			}); */
			$('#dgTo').datagrid({ queryParams: form2Json("to") });
		}
	   	
	}
	function editstatus(flag, val){
		var rows = null;
		if (flag == "0") {
			rows = $('#dgFrom').datagrid('getSelections');
		} else {
			rows = $('#dgTo').datagrid('getSelections');
		}
    	if(rows.length>1){
    		$.messager.show({    // show error message
                title: 'Warning',
                msg: '请选择一条数据变更状态!'
            });
    		return;
    	}
        var row = rows[0];
        if (row){
        	var messgaeWarn = '';
        	if (flag == "0") {
        		if (val == 2) {
            		messgaeWarn = '确定要接受此推荐吗?';
            	} else if (val == 3) {
            		messgaeWarn = '确定要拒绝此推荐吗?';
            	}
        	} else if (flag == "1") {
            	if (val == 1) {
            		messgaeWarn = '确定要推荐此店吗?';
            	} else {
            		messgaeWarn = '确定要撤销推荐此店吗?';
            	}
        	}
            $.messager.confirm('提示',messgaeWarn,function(r){
                if (r){
                    $.post('countySuggestStore/updateCountySuggestStoreStatus/'+flag,{id:row.id,suggestFlag:val},function(result){
                        if (result.type=='Success'){
                        	$.messager.show({    // show error message
                                title: 'Success',
                                msg: result.Msg
                            });
                        	if (flag == "0") {
                    			rows = $('#dgFrom').datagrid('reload');
                    		} else {
                    			rows = $('#dgTo').datagrid('reload');
                    		}
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
        	$.messager.alert('提示信息','请先选择要更改状态的记录。','info');
        }
    }
	function toView(flag){
		if (flag == "0") {
			document.getElementById("CountyStoreFormUIFrom").contentWindow.document.body.innerText = "";
			var row = $('#dgFrom').datagrid('getSelected');
	        if (row){
	        	$('#CountyStoreFormUIFrom').attr("src","countySuggestStore/toViewCountSuggestStore/"+row.id);
	            $('#dlgFrom').dialog('open').dialog('setTitle','详情');
	            $('#dlg-buttonsFrom').css('display','none');
	        }else{
	        	$.messager.alert('提示信息','请先选择要查看的记录。','info');
	        }
		} else {
			document.getElementById("CountyStoreFormUITo").contentWindow.document.body.innerText = "";
			var row = $('#dgTo').datagrid('getSelected');
	        if (row){
	        	$('#CountyStoreFormUITo').attr("src","countySuggestStore/toViewCountSuggestStore/"+row.id);
	            $('#dlgTo').dialog('open').dialog('setTitle','详情');
	            $('#dlg-buttonsTo').css('display','none');
	        }else{
	        	$.messager.alert('提示信息','请先选择要查看的记录。','info');
	        }
		}
    	
    }
	function acceptSuggest() {
		document.getElementById("CountyStoreFormUIFrom").contentWindow.document.body.innerText = "";
    	var row = $('#dgFrom').datagrid('getSelected');
        if (row){
        	$.post('countySuggestStore/checkRecommend',{id:row.id},function(result){
                if (result.type=='Success'){
                	$('#CountyStoreFormUIFrom').attr("src","countySuggestStore/toSuggest/"+row.id);
                    $('#dlgFrom').dialog('open').dialog('setTitle','接受推荐');
                    $('#dlg-buttonsFrom').css('display','block');
                } else {
                    $.messager.show({    // show error message
                        title: 'Error',
                        msg: result.Msg
                    });
                }
            },'json');
        }else{
        	$.messager.alert('提示信息','请先选择要进行推荐的记录。','info');
        }
    }
</script>
<script type="text/javascript">
	/* 行编辑时所用的方法 */
	var editIndexFrom = undefined;
	function endEditingFrom(){
	    if (editIndexFrom == undefined){return true}
	    if ($('#dgFrom').datagrid('validateRow', editIndexFrom)){
	        $('#dgFrom').datagrid('endEdit', editIndexFrom);
	        editIndexFrom = undefined;
	        return true;
	    } else {
	        return false;
	    }
	}
	function onClickRowFrom(index){
	    if (editIndexFrom != index){
	        if (endEditingFrom()){
	            $('#dgFrom').datagrid('selectRow', index)
	                    .datagrid('beginEdit', index);
	            editIndexFrom = index;
	        } else {
	            $('#dgFrom').datagrid('selectRow', editIndexFrom);
	        }
	    }
	    var row = $('#dgFrom').datagrid('getSelected');
	    if (row){
	    	$('#toolbarfrom').empty();
	     	if (row.handleStatus == '1') {
	    		$('#toolbarfrom').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-details' plain='true' onclick='toView(0)'>详情</a>");
	     		$('#toolbarfrom').append("<a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconCls:'icon-jieshou',plain:true\" onclick='acceptSuggest()'>接受</a>");
	     		$('#toolbarfrom').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-jujue' plain='jujue' onclick='editstatus(0,3)'>拒绝</a>");
	     		$.parser.parse($('#toolbarfrom'));
	     	} else if (row.handleStatus == '2') {
	    		$('#toolbarfrom').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-details' plain='true' onclick='toView(0)'>详情</a>");
	     		$.parser.parse($('#toolbarfrom'));
	     	} else {
	     		$('#toolbarfrom').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-details' plain='true' onclick='toView(0)'>详情</a>");
	     		$('#toolbarfrom').append("<a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconCls:'icon-jieshou',plain:true\" onclick='acceptSuggest()'>接受</a>");
	     		$.parser.parse($('#toolbarfrom'));
	     	}
	    }
	}
</script>
<script type="text/javascript">
	/* 行编辑时所用的方法 */
	var editIndexTo = undefined;
	function endEditingTo(){
	    if (editIndexTo == undefined){return true}
	    if ($('#dgTo').datagrid('validateRow', editIndexTo)){
	        $('#dgTo').datagrid('endEdit', editIndexTo);
	        editIndexTo = undefined;
	        return true;
	    } else {
	        return false;
	    }
	}
	function onClickRowTo(index){
	    if (editIndexTo != index){
	        if (endEditingTo()){
	            $('#dgTo').datagrid('selectRow', index)
	                    .datagrid('beginEdit', index);
	            editIndexTo = index;
	        } else {
	            $('#dgTo').datagrid('selectRow', editIndexTo);
	        }
	    }
	    var row = $('#dgTo').datagrid('getSelected');
	    if (row){
	    	$('#toolbarto').empty();
	    	if (row.suggestFlag == "0") {
	    		$('#toolbarto').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-details' plain='true' onclick='toView(1)'>详情</a>");
	     		$('#toolbarto').append("<a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconCls:'icon-tuijian',plain:true\" onclick='editstatus(1,1)'>推荐</a>");
	     		$.parser.parse($('#toolbarto'));
			} else {
				if (row.handleStatus == '1') {
					$('#toolbarto').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-details' plain='true' onclick='toView(1)'>详情</a>");
		     		$('#toolbarto').append("<a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconCls:'icon-editstatus',plain:true\" onclick='editstatus(1,0)'>撤销推荐</a>");
		     		$.parser.parse($('#toolbarto'));
		     	} else {
		     		$('#toolbarto').append("<a href='javascript:void(0)' class='easyui-linkbutton' iconCls='icon-details' plain='true' onclick='toView(1)'>详情</a>");
		     		$.parser.parse($('#toolbarto'));
		     	}
			}
	    }
	}
</script>
</html>