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
    <title>ECharts</title>
    <script src="${basePath}static/js/echarts.min.js"></script>
    <script type="text/javascript" src="${basePath}static/easyui/jquery.min.js"></script>
</head>
<body>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 600px;height:400px;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        $.ajax({
            url: 'grouponDemand/echartsInit',
            dataType:'json'
        }).done(function (data) {
    myChart.setOption({
        title: {
            text: '我要团统计'
        },
        tooltip: {},
        legend: {
            data:['团购人数']
        },
        xAxis: {
            data: data.className
        },
        yAxis: {},
        series: [{
            name: '团购人数',
            type: 'bar',
            data: data.number
        }]
    });
});

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
</body>
</html>