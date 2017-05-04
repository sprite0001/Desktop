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
	<script type="text/javascript" src="${basePath}static/js/validator.js"></script>
</head>
<body>
	<div class="ftitle"></div>
        <form id="fm" method="post" novalidate>
            <div class="div_fitem_1">
                <label class="lable_add">小区名:</label>
                <input name="villageName" class="easyui-textbox" data-options="required:true,validType:['isBlank','length[1,50]'],validType:'hanzi'">
            </div>
            
            <div class="div_fitem_1">
                <label class="lable_add">地址:</label>
                <input name="villageAddress" class="easyui-textbox" data-options="required:true,validType:['isBlank','length[1,200]']">
            </div>
          
            <div class="div_fitem_1">
                <label class="lable_add">省:</label>
                <input id="province" name="provinceId" class="easyui-combobox" style="width: 150px;" required="true">
            </div>
             <div class="div_fitem_1">
                <label class="lable_add">市:</label>
                <input id="city" name="cityId" class="easyui-combobox" style="width: 150px;" required="true">
            </div>
            <div class="div_fitem_1">
                <label class="lable_add">区/县:</label>
                <input id="county" name="countyId" class="easyui-combobox" style="width: 150px;" required="true">
            </div>
            <div class="div_fitem_1">
                <label class="lable_add">社区:</label>
                <input id="community" name="communityId" class="easyui-combobox" style="width: 150px;" required="true"> 
            </div>
            <div class="div_fitem_1">
                <label class="lable_add">户数:</label>
                <input name="houseHolds" class="easyui-numberbox"  data-options="validType:['integer','length[1,10]']">
            </div>
            <div class="div_fitem_1">
                <label class="lable_add">人口:</label>
                <input name="population" class="easyui-numberbox" data-options="validType:['integer','length[1,10]']">
            </div>
            <div class="div_fitem_1">
                <label class="lable_add">排序序号:</label>
                <input name="ordering" class="easyui-numberbox"  data-options="validType:['integer','length[1,10]']">
            </div>
            
             <div class="div_fitem_1">
                <label class="lable_add">建成年份:</label>
                <input name="buildYear" class="easyui-datebox" editable="false" required="required">
             </div>
             
             <div class="div_fitem_1">
                <label class="lable_add">均价:</label>
                <input name="averagePrice" class="easyui-numberbox"  type="text"  data-options="validType:['integer','length[1,10]']">
             </div>
             
             <div class="div_fitem_1">
                <label class="lable_add">中心点经度:</label>
                <input id="villageLongitude" name="villageLongitude" class="easyui-textbox" data-options="required:true,validType:'xiaoshuOrzhengshu'">
             </div>
             
                 <div class="div_fitem_1">
                <label class="lable_add">中心点纬度:</label>
                <input id="villageLatitude" name="villageLatitude" class="easyui-textbox"   data-options="required:true,validType:'xiaoshuOrzhengshu'">
             </div>
             
             <!-- 百度地图开始 -->
             
             <div class="resource_item">
               <div class="column">位置:</div>
                    <div class="content" style="width:auto;margin-bottom: 20px;">
                    <div id="allmap" style="height:300px;width:500px;"></div>
               </div>
            </div>
            
        </form>
</body>
<!-- 百度地图开始 -->
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=vnpoj2aECUZd4MuGFBb7DWak"></script>

<script type="text/javascript">
setTimeout(function(){
    /* var latitude = $("#villageLatitudeHidden").val();
    var longitude = $("#villageLongitudeHidden").val(); */
     var latitude = 34.81262;
    var longitude = 113.552262; 
    
    var point = new BMap.Point(longitude,latitude);  // 创建点坐标A--大渡口区
    
    window.map = new BMap.Map("allmap");  
    map.centerAndZoom(point,12);  
    map.enableScrollWheelZoom();
   /*  //单击获取点击的经纬度
    map.addEventListener("click",function(e){
         $("#villageLatitude").textbox("setValue", e.point.lat); 
         $("#villageLongitude").textbox("setValue", e.point.lng); 
        alert(e.point.lng + "," + e.point.lat);
    }); */
    
    // 百度地图API功能
    var iconImg = new BMap.Icon("${basePath}static/images/location.gif", new BMap.Size(100, 150));  
    var marker = new BMap.Marker(point,{icon:iconImg});
    var label = new BMap.Label("小区位置",{"offset":new BMap.Size(15,-15)});
    marker.setLabel(label);
    map.addOverlay(marker);
    marker.enableDragging();
    marker.addEventListener("click",attribute);
    function attribute(){
        var p = marker.getPosition();  //获取marker的位置
        $("#villageLatitude").textbox("setValue", p.lat); 
         $("#villageLongitude").textbox("setValue", p.lng); 
        //alert("marker的位置是" + p.lng + "," + p.lat);    
    }
    
 
},1000);

</script>

<!-- 百度地图结束 -->


<script type="text/javascript">
var url= "village/saveVillage";




function optSubmit(){
	
	var provinceId = $('#province').combobox('getValue');
	if(provinceId=='0'||provinceId==""){
	    var res = "请选择所属省";
	    $.messager.show({
	     title: 'Error',
	     msg: res
	 });
	    return false;
	}
	var cityId = $('#city').combobox('getValue');
	if(cityId=='0'||cityId==""){
	    var res = "请选择所属市";
	    $.messager.show({
	     title: 'Error',
	     msg: res
	 });
	    return false;
	}

	var countyId = $('#county').combobox('getValue');
	if(countyId=='0'||countyId==""){
	    var res = "请选择所属区";
	    $.messager.show({
	     title: 'Error',
	     msg: res
	 });
	    return false;
	}
	var communityId = $('#community').combobox('getValue');
	if(communityId=='0'||communityId==""){
	    var res = "请选择所属社区";
	    $.messager.show({
	     title: 'Error',
	     msg: res
	 });
	    return false;
	}
	
	
    $('#fm').form('submit',{
        url: url,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if ("Error"==result.type){
                $.messager.show({
                    title: 'Error',
                    msg: result.msg
                });
                
            } else {
            	$.messager.show({
                    title: 'Success',
                    msg: result.msg,
                    timeout:1000,
                	showType:'slide'
                });
            	setTimeout('window.parent.close()',2000);
            }
        }
    });
}

$(function(){
    
    // 省份 下拉框选择控件，下拉框的内容是动态查询数据库信息 
    $('#province').combobox({ 
                url:'common/initProvinceQY',
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
                url: "common/getCityByProviceQY/"+province,
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
                url: "common/getCountryByCityQY/"+city,
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
                url: "common/getCommunityByCountyQY/"+county,
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



</script>
</html>