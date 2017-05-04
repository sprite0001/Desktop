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
                <label for="phone">手机号:</label> 
                <input class="easyui-textbox" type="text" name="phone" id="phone" />
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
                <th field="phone" width="50">电话</th>
                <th field="content" width="50">内容</th>
                <th field="likingDate" formatter="conversion" width="50" >点赞日期</th>
            </tr>
        </thead>
    </table>
    <script type="text/javascript">
	    function conversion(value){
	   		var time = new Date(value);
	   		return time.format("yyyy-MM-dd");
		};
	    var url;
	    function searchMember(){
	        $('#ff').form('submit',{  
	             url: "quarters/searchVillageLikeRecord",  
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
    	   	url:"quarters/searchVillageLikeRecord",
    	   	queryParams:{villageId:villageId}
    	 });
	</script>
</body>
</html>