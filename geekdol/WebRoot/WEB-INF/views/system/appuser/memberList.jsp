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
                <label for="nickName">昵称:</label> 
                <input class="easyui-textbox" type="text" name="nickName" id="nickName" />
            </div>
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="moblie">手机号:</label> 
                <input class="easyui-textbox" type="text" name="moblie" id="moblie" />
            </div>
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="email">邮箱:</label> 
                <input class="easyui-textbox" type="text" name="email" id="email" />
            </div>
            <div style="float: left; padding: 20px 0 0 20px;">
                <input  type="button" onclick="searchMember()" value="查询" />
            </div>
        </form>
    </div> 
    <table id="dg_table" class="easyui-datagrid" style="height: 620px;"
            url="appUser/findAllMember"
            toolbar="#toolbar" pagination="false" queryParams="form2Json('ff')"
            fitColumns="true" singleSelect="true" 
            data-options="fit:false,onClickRow: onClickRow,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
                <th field="id" hidden="true">
                <th field="ck" checkbox="true" onclick="onClickRow()"></th>
                <th field="nickName" width="50" >昵称</th>
                <th field="moblie" width="50">手机号</th>
                <th field="realName" width="50" >真实姓名</th>
                <th field="certificateTypeName" width="50">证件类型</th>
                <th field="certificateNumber" width="50">证件号码</th>
                <th field="email" width="50" >邮箱</th>
                <th field="address" width="50" >地址</th>
            </tr>
        </thead>
    </table>
    <form id="updateToStaff" method="post">
	    <div style="height: 70px; width: 100%;">
	    	<div style="float: left; padding: 20px 0 0 20px;">
	            <label for="selectNickName">昵称:</label> 
	            <input class="easyui-textbox" style="border:0px;" name="nickName" id="selectNickName" readonly="readonly"/>
	        </div>
	        <div style="float: left; padding: 20px 0 0 20px;">
	            <label for="selectRealName">真实姓名:</label> 
	            <input class="easyui-textbox" name="realName" id="selectRealName" required="true"/>
	        </div>
	        <div style="float: left; padding: 20px 0 0 20px;">
	            <label for="staffLevel">用户级别:</label>
	            <input id="staffLevel" name="staffLevel" class="easyui-combobox"
				data-options="required:'true',valueField:'value',textField:'lable',url:'common/initSysData/${STAFFLEVELTYPE }'" style="width: 150px;">
	        </div>
	        <div style="float: left; padding: 20px 0 0 20px;">
	            <input type="button" onclick="updateToStaff()" value="确认" />
	        </div>
	        <div style="float: left; padding: 20px 0 0 20px;">
	            <input type="button" onclick="javaScript:window.parent.close();" value="关闭" />
	        </div>
	    </div>
	    <input name="id" id="id" type="hidden">
    </form>
    <script type="text/javascript">
       var url;
       function searchMember(){
           $('#ff').form('submit',{  
                url: "appUser/findAllMember",  
                onSubmit: function(){  
                    return $(this).form('validate'); 
                },  
                success:function(data){  
                    var data = eval('('+data+')');
                   $('#dg_table').datagrid('loadData',data);
                }       
            });  

       }
       function updateToStaff(){
    	   if($("#id").val() == undefined || $("#id").val()=='' || $("#id").val()== null){
    		   var result = '请选择会员';
    		   $.messager.show({
                   title: 'Error',
                   msg: result
               });
    		   return false;
    	   }
    	   var val = $('#staffLevel').combobox('getValue');
    		if(val==null||val==""){
    			var res = "请选择用户级别";
    			$.messager.show({
                    title: 'Error',
                    msg: res
                });
    			return false;
    		}
    	   $('#updateToStaff').form('submit',{
    	        url: "appUser/memberToStaff",
    	        onSubmit: function(){
    	            return $(this).form('validate');
    	        },
    	        success: function(result){
    	            var result = eval('('+result+')');
    	            if ("Error"==result.type){
    	                $.messager.show({
    	                    title: 'Error',
    	                    msg: result.Msg
    	                });
    	                
    	            } else {
    	            	$.messager.show({
    	                    title: 'Success',
    	                    msg: result.Msg,
    	                    timeout:1000,
    	                	showType:'slide'
    	                });
    	            	setTimeout('window.parent.close()',2000);
    	            }
    	        }
    	    });
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
        if ($('#dg_table').datagrid('validateRow', editIndex)){
            /* var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:'productid'});
            var productname = $(ed.target).combobox('getText');
            $('#dg').datagrid('getRows')[editIndex]['productname'] = productname; */
            $('#dg_table').datagrid('endEdit', editIndex);
            editIndex = undefined;
            return true;
        } else {
            return false;
        }
    }
    
    function onClickRow(index){
    	var row = $('#dg_table').datagrid('getSelected');
    	$("#selectNickName").textbox("setValue", row.nickName);
    	if(row.realName != undefined && row.realName!=null &&row.realName!=""){
	    	$("#selectRealName").textbox("setValue", row.realName);
    	}
    	$("#id").val(row.id);
    }
    function append(){
        if (endEditing()){
            $('#dg_table').datagrid('appendRow',{status:'P'});
            editIndex = $('#dg_table').datagrid('getRows').length-1;
            $('#dg_table').datagrid('selectRow', editIndex)
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
    bindFormComm("ff",searchMember);  
    }); 
    </script>
</body>
</html>