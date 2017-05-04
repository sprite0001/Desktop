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
                <label for="name">店名:</label> 
                <input class="easyui-textbox" type="text" name="name" id="name" />
            </div>
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="concat">联系人:</label> 
                <input class="easyui-textbox" type="text" name="concat" id="concat" />
            </div>
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="concatPhone">电话:</label> 
                <input class="easyui-textbox" type="text" name="concatPhone" id="concatPhone" />
            </div>
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="email">类型:</label> 
                <input id="storeType" name="storeType" class="easyui-combobox" 
					data-options="valueField:'value',textField:'lable',url:'common/initSysData/${STORETYPE }'" style="width: 100px;" editable="false">
            </div>
            <div style="float: left; padding: 20px 0 0 20px;">
                <label for="email">状态:</label> 
                <select id="useStatus" name="useStatus" class="easyui-combobox" style="width: 80px;" editable="false">
					<option value="">全部</option>
					<option value="0">${QY}</option>
					<option value="1">${JY}</option>
				</select>
            </div>
            <div style="float: left; padding: 20px 0 0 20px;">
                <input  type="button" onclick="searchMember()" value="查询" />
            </div>
            <input id="villageId" name="villageId" value="${id }" type="hidden">
        </form>
    </div> 
    <table id="dg_table" class="easyui-datagrid" style="height: 620px;"
            toolbar="#toolbar" pagination="true" queryParams="form2Json('ff')"
            fitColumns="true" singleSelect="true" rownumbers="true"
            data-options="fit:false,border:false,pageSize:20,pageList:[20,50,100,200]" >
        <thead>
            <tr>
                <th field="name" width="50" >店名</th>
                <th field="storeTypeName" width="50">类型</th>
                <th field="concat" width="50" >联系人</th>
                <th field="concatPhone" width="50">联系电话</th>
                <th field="startNumber" width="50">5星/4星/3星/2星/1星</th>
                <th field="likesOrhate" width="50" >点赞/倒赞</th>
                <th field="dialCount" width="50" >电话拨打次数</th>
                <th field="useStatus" width="50" formatter = "formatStatus">状态</th>
            </tr>
        </thead>
    </table>
    <script type="text/javascript">
	    function formatStatus(val,row){
			if (val == "0"){
				return '<span>启用</span>';
			} else {
				return '<span>禁用</span>';
			}
		}
	    var url;
	    function searchMember(){
	        $('#ff').form('submit',{  
	             url: "quarters/findAllAroundStore",  
	             onSubmit: function(){  
	                 return $(this).form('validate'); 
	             },  
	             success:function(data){  
	                 var data = eval('('+data+')');
	                $('#dg_table').datagrid('loadData',data);
	             }       
	         });  
	    }
	    /**绑定页面回车事件，以及初始化页面时的光标定位**/  
	    $(function(){  
	    bindFormComm("ff",searchMember);  
	    });
    </script>
    <script type="text/javascript">
    	 var villageId = $("#villageId").val();
    	 $('#dg_table').datagrid({
    	    url:"quarters/findAllAroundStore",
    	    queryParams:{villageId:villageId}
    	 });
	</script>
</body>
</html>