<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>${TITLE}</title>
    <c:set var="basePath" scope="page" value="<%=basePath %>"/>
    <c:set var="contextPath" scope="page" value="<%=path %>"/> 
    <link href="${contextPath}/static/index/css/default.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/index/js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/index/js/themes/icon.css" />
    <script type="text/javascript" src="${contextPath}/static/index/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="${contextPath}/static/index/js/jQuery.easyui.js"></script>

    <script type="text/javascript" src='${contextPath}/static/index/js/outlook2.js'> </script>
    
    <link rel="SHORTCUT ICON" href="${contextPath}/favicon.ico"/>

    <script type="text/javascript">
        //设置登录窗口
        function openPwd() {
            $('#w').window({
                title: '修改密码',
                width: 300,
                modal: true,
                shadow: true,
                closed: true,
                height: 160,
                resizable:false
            });
        }
        //关闭登录窗口
        function close() {
            $('#w').window('close');
        }

        //修改密码
        function serverLogin() {
            var oldPassword = $('#oldPassword');
            var newpass = $('#txtNewPass');
            var rePass = $('#txtRePass');

            if (oldPassword.val() == '') {
                msgShow('系统提示', '请输入原密码！', 'warning');
                return false;
            }
            if (newpass.val() == '') {
                msgShow('系统提示', '请输入密码！', 'warning');
                return false;
            }
            if (rePass.val() == '') {
                msgShow('系统提示', '请在一次输入密码！', 'warning');
                return false;
            }

            if (newpass.val() != rePass.val()) {
                msgShow('系统提示', '两次密码不一致！请重新输入', 'warning');
                return false;
            }
            $.post('${contextPath}/user/changePsw/'+newpass.val()+'/'+oldPassword.val(),'',function(result){
                if (result.type=='Success'){
                    $.messager.show({    // show error message
                        title: 'Success',
                        msg: result.Msg
                    });
                    newpass.val('');
                    rePass.val('');
                    oldPassword.val('');
                    close();
                } else {
                    $.messager.show({    // show error message
                        title: 'Error',
                        msg: result.Msg
                    });
                }
            },'json');
        }

        $(function() {

            openPwd();
            //
            $('#editpass').click(function() {
                $('#w').window('open');
            });

            $('#btnEp').click(function() {
                serverLogin();
            })
            $('#close').click(function() {
                close();
            })

            $('#loginOut').click(function() {
                $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {

                    if (r) {
                        location.href = '${basePath}login/loginout';
                    }
                });

            })
            
        });

    </script>

</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
<noscript>
<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
    <img src="${contextPath}/static/index/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
</div></noscript>
    <div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(${contextPath}/static/index/images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head">欢迎 您：${user.userName} <a href="#" id="editpass">修改密码</a> <a href="#" id="loginOut">安全退出</a></span>
        <span style="padding-left:10px; font-size: 16px; "><img src="${contextPath}/static/index/images/blocks.gif" width="20" height="20" align="absmiddle" /> 吉客多后台管理系统 </span>
    </div>
    <div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
        <div class="footer">  </div>
    </div>
    <div region="west" split="true" title="导航菜单" style="width:180px;" id="west">
    <div class="easyui-accordion" fit="true" border="false">
        <!--  导航内容 -->
        <shiro:hasAnyRoles name="IntimateNewM,IntimateNewV,IntimateNewQ,IntimateNewF,ActivityCollection,Recommend,CommunityPhone,CommonPhone,AroundStore,AroundSuggestS,CountySuggestS">
        <div title="心媒体管理" icon="icon-hmedia">
            <ul>
                <li>
                <shiro:hasPermission name="IntimateNewM:view">
                   <li>
                    <div><a target="mainFrame" way="${contextPath}/intimateNews/managerlist"><span class="icon icon-role"></span>贴心报管理</a></div> 
                   </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="IntimateNewV:view"> 
                   <li>
                    <div><a target="mainFrame" way="${contextPath}/intimateNews/shenhelist"><span class="icon icon-role"></span>贴心报审核</a></div>
                   </li>
                </shiro:hasPermission>
                <%-- <shiro:hasPermission name="IntimateNewV:view">
                    <li>
                    <div><a target="mainFrame" href="${contextPath}/intimateNews/selfmedialist"><span class="icon icon-role"></span>自媒体审核</a></div>
                    </li>
                </shiro:hasPermission> --%>
                <shiro:hasPermission name="IntimateNewQ:view"> 
                    <li>
                    <div><a target="mainFrame" way="${contextPath}/intimateNews/tofabulist"><span class="icon icon-role"></span>贴心报快捷发布</a></div>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="IntimateNewF:view">    
                    <li>
                    <div><a target="mainFrame" way="${contextPath}/intimateNews/zhuanfalist"><span class="icon icon-role"></span>转发贴心报</a></div>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission  name="ActivityCollection:view">
                    <li>
                    <div><a target="mainFrame" way="${contextPath}/activityCollection/tolist"><span class="icon icon-role"></span>活动汇管理</a></div>
                    </li>
                </shiro:hasPermission> 
                <shiro:hasPermission name="HeartBeat:view">    
                   <li>
                    <div><a target="mainFrame" way="${contextPath}/heartBeat/list"><span class="icon icon-role"></span>随心拍管理</a></div> 
                   </li>
                </shiro:hasPermission> 
                <%-- <shiro:hasPermission name="AroundStore:view">
                    <li>
                        <div><a target="mainFrame" way="${contextPath}/aroundStore/list"><span class="icon icon-role"></span>周边店管理</a></div> 
                    </li>
                </shiro:hasPermission>
                  <shiro:hasPermission name="AroundSuggestS:view">
                    <li>
                        <div><a target="mainFrame" way="${contextPath}/aroundSuggestStore/list"><span class="icon icon-role"></span>本网格推荐周边店管理</a></div> 
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="CountySuggestS:view">
                    <li>
                        <div><a target="mainFrame" way="${contextPath}/countySuggestStore/list"><span class="icon icon-role"></span>本区推荐周边店管理</a></div> 
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="Recommend:view">
                    <li>
                    <div><a target="mainFrame" way="${contextPath}/recommend/list"><span class="icon icon-role"></span>推荐功能设置</a></div>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="CommunityPhone:view">
                    <li>
                    <div><a target="mainFrame" way="${contextPath}/communityphone/list"><span class="icon icon-role"></span>社区电话管理</a></div>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="CommonPhone:view">
                    <li>
                    <div><a target="mainFrame" way="${contextPath}/commonphone/list"><span class="icon icon-role"></span>常用电话管理</a></div>
                    </li>
                </shiro:hasPermission> --%>
            </ul>
        </div>
        </shiro:hasAnyRoles>
        <shiro:hasAnyRoles name="VillageNotice,CityDistrictNotice,StaffLevel,Staff,AdmNewsflash,IncorruptGovernment">
        <div title="心通桥管理" icon="icon-hbridge">
            <ul>
                <shiro:hasPermission name="VillageNotice:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/villageNotice/list"><span class="icon icon-role"></span>小区公告管理</a></div> 
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="CityDistrictNotice:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/cityDistrictNotice/list"><span class="icon icon-role"></span>市/区/办事处公告管理</a></div> 
                </li>
                </shiro:hasPermission>
                <%-- <shiro:hasPermission name="StaffLevel:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/staffLevel/list"><span class="icon icon-users"></span>政务内参级别管理</a></div> 
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="Staff:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/staff/list"><span class="icon icon-role"></span>政务内参管理</a></div> 
                </li>
                </shiro:hasPermission> --%>
                <shiro:hasPermission name="AdmNewsflash:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/admNewsflash/list"><span class="icon icon-role"></span>行政快报管理</a></div> 
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="AdmNewsflashV:verify">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/admNewsflash/verifyList"><span class="icon icon-role"></span>行政快报审核</a></div> 
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="NetizenAcumen:view">
                    <li>
                    <div><a target="mainFrame" way="${contextPath}/netizenacumen/list"><span class="icon icon-role"></span>网民法眼信息管理</a></div>
                    </li>
                </shiro:hasPermission>

                <shiro:hasPermission name="IncorruptGovernment:view"> 
                    <li>
                    <div><a target="mainFrame" way="${contextPath}/incorruptGovernment/info"><span class="icon icon-role"></span>风清气正信息管理</a></div>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="NetizenSecurity:view"> 
                    <li>
                    <div><a target="mainFrame" way="${contextPath}/netizenSecurity/list"><span class="icon icon-role"></span>网安报管理</a></div>
                    </li>
                </shiro:hasPermission>
            </ul>
        </div>
        </shiro:hasAnyRoles>
        <%-- <shiro:hasAnyRoles name="Cooperation,CooperationA,VillageLike,GoodsClassific,Goods,Order,GrouponDemand,Groupon,GrouponOrder,GroupPurchaseCode,PaymentRecoment,Immediately,GrouponVerify">
        <div title="吉客店管理" icon="icon-geekstore">
            <ul>
                <shiro:hasPermission name="Cooperation:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/cooperationStore/info"><span class="icon icon-users"></span>合作店管理</a></div> 
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="CooperationA:view"> 
                <li>
                    <div><a target="mainFrame" way="${contextPath}/cooperationApplication/list"><span class="icon icon-users"></span>合作店申请查询</a></div> 
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="VillageLike:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/villageLikeRecord/list"><span class="icon icon-users"></span>点赞记录查询</a></div>                    
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="GoodsClassific:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/goodsclassific/goodsclassificMain"><span class="icon icon-role"></span>商品分类</a></div> 
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="Goods:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/goods/info"><span class="icon icon-users"></span>商品管理</a></div> 
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="Order:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/order/list"><span class="icon icon-users"></span>订单管理</a></div> 
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="Immediately:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/immediately/info"><span class="icon icon-users"></span>即可送管理</a></div> 
                </li>
                </shiro:hasPermission>
                    <shiro:hasPermission name="Groupon:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/groupongoods/info"><span class="icon icon-users"></span>团购管理</a></div> 
                </li>
                </shiro:hasPermission>
                
            
                <shiro:hasPermission name="GrouponVerify:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/grouponVerify/info"><span class="icon icon-users"></span>团购审核管理</a></div> 
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="GrouponDemand:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/grouponDemand/list"><span class="icon icon-users"></span>我要团管理</a></div> 
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="GrouponOrder:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/groupBuyOrder/listVillage"><span class="icon icon-users"></span>团购合作店订单管理</a></div> 
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="GrouponOrder:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/groupBuyOrder/list"><span class="icon icon-users"></span>团购供应商订单管理</a></div> 
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="GroupPurchaseCode:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/groupPurchaseCode/validate"><span class="icon icon-users"></span>团购验证</a></div> 
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="PaymentRecoment:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/paymentRecoment/list"><span class="icon icon-users"></span>支付流水</a></div> 
                </li>
                </shiro:hasPermission>
            </ul>
        </div>
        </shiro:hasAnyRoles> --%>
         <shiro:hasAnyRoles name="Advert,Position">
        <div title="广告管理" icon="icon-advert">
            <ul>
                  <shiro:hasPermission name="Advert:view">
                    <li>
                    <div><a target="mainFrame" way="${contextPath}/advert/list"><span class="icon icon-role"></span>广告管理</a></div> 
                    </li>
                  </shiro:hasPermission>
                  <shiro:hasPermission name="Position:view">
                    <li>
                    <div><a target="mainFrame" way="${contextPath}/position/list"><span class="icon icon-role"></span>广告位置管理</a></div> 
                    </li>
                  </shiro:hasPermission>
            </ul>
        </div>
        </shiro:hasAnyRoles>
        <shiro:hasAnyRoles name="AppPosition,AppModular,AppVersion,AppSet">
        <div title="APP设置管理" icon="icon-appset">
            <ul>
                <%-- <shiro:hasPermission name="AppPosition:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/appPosition/list"><span class="icon icon-users"></span>App功能位置管理</a></div> 
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="AppModular:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/appModular/list"><span class="icon icon-users"></span>App功能管理</a></div> 
                </li>
                </shiro:hasPermission> --%>
                <shiro:hasPermission name="AppVersion:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/appVersion/list"><span class="icon icon-users"></span>App版本管理</a></div> 
                </li>
                </shiro:hasPermission>
                <%-- <shiro:hasPermission name="AppSet:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/appSet/toAdd"><span class="icon icon-users"></span>App设置管理</a></div> 
                </li>
                </shiro:hasPermission> --%>
            </ul>
        </div>
        </shiro:hasAnyRoles>
        <shiro:hasAnyRoles name="Province,City,County,Community,Village,PcUser,Role,StaffUser,AppUser,SysData,MyCooperation,Suggestion,Syslog,Keywords,CommunityInfo,">
        <div title="系统管理" icon="icon-sysset">
            <ul>
                <shiro:hasPermission name="Province:view">
                    <li>
                        <div><a target="mainFrame" way="${contextPath}/province/list"><span class="icon icon-users"></span>省管理</a></div> 
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="City:view">
                    <li>
                        <div><a target="mainFrame" way="${contextPath}/city/list"><span class="icon icon-users"></span>市管理</a></div> 
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="County:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/county/list"><span class="icon icon-role"></span>行政区管理</a></div> 
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="Community:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/community/info"><span class="icon icon-role"></span>社区管理</a></div> 
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="Village:view">               
                <li>
                    <div><a target="mainFrame" way="${contextPath}/village/list"><span class="icon icon-role"></span>小区管理</a></div> 
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="Role:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/role/list"><span class="icon icon-role"></span>角色管理</a></div> 
                </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="PcUser:view">
                    <li>
                        <div><a target="mainFrame" way="${contextPath}/user/list"><span class="icon icon-users"></span>用户管理</a></div> 
                    </li>
                </shiro:hasPermission>
                <%-- <shiro:hasPermission name="StaffUser:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/appUser/info"><span class="icon icon-role"></span>内参管理</a></div> 
                </li>
                </shiro:hasPermission> --%>
                <shiro:hasPermission name="AppUser:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/member/list"><span class="icon icon-role"></span>会员管理</a></div> 
                </li>
                </shiro:hasPermission>
                  <shiro:hasPermission name="MyCooperation:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/cooperation/list"><span class="icon icon-role"></span>我要合作</a></div> 
                </li>
                </shiro:hasPermission>
                 <shiro:hasPermission name="Suggestion:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/suggestion/list"><span class="icon icon-role"></span>投诉建议</a></div> 
                </li>
                </shiro:hasPermission>
                 <shiro:hasPermission name="Syslog:view">
                <li>                
                    <div><a target="mainFrame" way="${contextPath}/syslog/list"><span class="icon icon-log"></span>日志管理</a></div> 
                </li>
                </shiro:hasPermission>
                 <shiro:hasPermission name="Keywords:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/keywords/list"><span class="icon icon-role"></span>关键字库</a></div> 
                </li>
                 </shiro:hasPermission>
                <shiro:hasPermission name="SysData:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/sysdata/list"><span class="icon icon-role"></span>字典管理</a></div> 
                </li>
                </shiro:hasPermission>
               <%--  <shiro:hasPermission name="ManagerMessage:view">
                <li>
                    <div><a target="mainFrame" way="${contextPath}/message/list"><span class="icon icon-role"></span>给店主和网格长发信息</a></div> 
                </li>
                </shiro:hasPermission> --%>
                <%-- <shiro:hasPermission name="CommunityInfo:view"> 
                <li>
                    <div><a target="mainFrame" way="${contextPath}/quarters/list"><span class="icon icon-role"></span>小区信息查询</a></div> 
                </li>
                </shiro:hasPermission> --%>
                <%--  <li>
                    <div><a target="mainFrame" href="${contextPath}/toueditor"><span class="icon icon-role"></span>富文本编辑器</a></div> 
                </li>
                <li>
                    <div><a target="mainFrame" href="${contextPath}/selectshuangxiang"><span class="icon icon-role"></span>双向选择器</a></div> 
                </li> --%>
            </ul>
        </div>
        </shiro:hasAnyRoles>
    </div>

    </div>
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
            <div title="欢迎使用" style="padding:20px;overflow:hidden;" id="home">
               <h1>吉客多后台管理系统!</h1>
            </div>
        </div>
    </div>
    
    <!--修改密码窗口-->
    <div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 150px; padding: 5px;
        background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <table cellpadding=3>
                    <tr>
                        <td>原密码：</td>
                        <td><input id="oldPassword" type="password" class="txt01" /></td>
                    </tr>
                    <tr>
                        <td>新密码：</td>
                        <td><input id="txtNewPass" type="password" class="txt01" /></td>
                    </tr>
                    <tr>
                        <td>确认密码：</td>
                        <td><input id="txtRePass" type="password" class="txt01" /></td>
                    </tr>
                </table>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >
                    确定</a> <a class="easyui-linkbutton" id="close" icon="icon-cancel" href="javascript:void(0)"
                       >取消</a>
            </div>
        </div>
    </div>

    <div id="mm" class="easyui-menu" style="width:150px;">
        <div id="mm-tabclose">关闭</div>
        <div id="mm-tabcloseall">全部关闭</div>
        <div id="mm-tabcloseother">除此之外全部关闭</div>
        <div class="menu-sep"></div>
        <div id="mm-tabcloseright">当前页右侧全部关闭</div>
        <div id="mm-tabcloseleft">当前页左侧全部关闭</div>
        <div class="menu-sep"></div>
        <div id="mm-exit">退出</div>
    </div>

</body>
</html>