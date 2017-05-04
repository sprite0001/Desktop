
/*******************************************************************************
 * 表单光标定位
 * 
 * @param formId
 * @param focusInputName
 */
function formFocus_Bruce(formId, focusInputName) {
	window.setTimeout(function() {
		$("#" + formId + " input[name='" + focusInputName + "']").focus();
	}, 0);
}

function formFocusTextArea_Bruce(formId, focusInputNameId) {
	window.setTimeout(function() {
		$("#" + formId + " #" + focusInputName).focus();
	}, 0);
}

function formFocusTextArea(formId, focusInputName) {
	window.setTimeout(function() {
		$("#" + formId + " textarea[name=" + focusInputName + "]").focus();
	}, 0);
}

function formFocusSelect(formId, focusInputName) {
	window.setTimeout(function() {
		alert(focusInputName);
		$("#" + formId + " select[name=" + focusInputName + "]").focus();
	}, 0);
}


/******************************************************************************* 
 * 绑定指定表单元素类型的回车事件 
 *  
 * @param formId 
 *            表单 
 * @param element 
 *            元素类型 (input/select/radio/...) 
 * @param subFunction 
 *            回车后要执行的js函数 
 * @param eventName 
 *            键盘事件 (keyup/keydown/keypress...) 
 */  
function bindEnter_Bruce(formId, element, subFunction, eventName) {  
    $("#" + formId + " " + element).bind(eventName, function(event) {  
        if (event.keyCode == '13') { 
        	subFunction();  
        }   
    });  
}  

/******************************************************************************* 
 * 表单回车事件绑定 表单光标定位 
 *  
 * @param formId 
 * @param focusInputName 
 * @param subFunction 
 *            回车要执行的函数 
 */  
//目前暂时不用
/*function bindFormComm(formId, focusInputName, subFunction) {  
    bindEnter_Bruce(formId, 'input', subFunction, 'keyup');  
    bindEnter_Bruce(formId, 'select', subFunction, 'keyup');  
    formFocus_Bruce(formId, focusInputName);  
}  */

function bindFormComm(formId,subFunction) {  
bindEnter_Bruce(formId, 'input', subFunction, 'keyup');  
bindEnter_Bruce(formId, 'select', subFunction, 'keyup');  
}  


//去掉a标签的href和onclick事件  防止重复提交
function removeHrefAndOnclick(objId){
	parent.$('#'+objId).removeAttr('onclick');//去掉a标签中的href属性
}

//增加a标签的href和onclick事件  防止重复提交
function addHrefAndOnclick(objId,fangfa){
	if(objId=="saveA"){
		parent.parent.$('#'+objId).attr('onclick',fangfa); 
	}
	if(objId=="tijiaoA"){
		parent.parent.$('#'+objId).attr('onclick',fangfa);  
	}
	if(objId=="closeBtn"){
		parent.parent.$('#'+objId).attr('onclick',fangfa);  
	}
}

//将表单数据转为json
function form2Json(id) {   
	var arr = $("#" + id).serializeArray()
    var jsonStr = "";     
	jsonStr += '{';
    for (var i = 0; i < arr.length; i++) {
        jsonStr += '"' + arr[i].name + '":"' + arr[i].value + '",'
    }
    jsonStr = jsonStr.substring(0, (jsonStr.length - 1));
    jsonStr += '}'     
    var json = JSON.parse(jsonStr);
    return json
}


var Common = {
    //EasyUI用DataGrid用日期格式化
    TimeFormatter: function (value, rec, index) {
        if (value == undefined) {
            return "";
        }
        var dateValue = new Date(value);
        if (dateValue.getFullYear() < 1900) {
            return "";
        }
        var val = dateValue.Format("yyyy-mm-dd HH:MM");
        return val.substr(11, 5);
    },
    DateTimeFormatter: function (value, rec, index) {
        if (value == undefined) {
            return "";
        }
        var time = new Date(value);
        if (time.getFullYear() < 1900) {
            return "";
        }
        return time.Format("yyyy-mm-dd HH:MM:SS");
    },

    //EasyUI用DataGrid用日期格式化
    DateFormatter: function (value, rec, index) {
        if (value == undefined) {
            return "";
        }
       /* value = value.toString();
        json格式时间转js时间格式
        value = value.substr(1, value.length - 2);
        var obj = eval('(' + "{Date: new " + value + "}" + ')');
        var dateValue = obj["Date"];*/
        var dateValue = new Date(value);
        if (dateValue.getFullYear() < 1900) {
            return "";
        }

        return dateValue.Format("yyyy-mm-dd");
    }
};

//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(H)、分(M)、秒(S)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(s)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-mm-dd HH:MM:SS.s") ==> 2015-07-02 08:09:04.423 
//(new Date()).Format("yyyy-m-d H:M:S.s")      ==> 2015-7-2 8:9:4.18 
Date.prototype.Format = function (fmt) { //author: meizz 
 var o = {
     "m+": this.getMonth() + 1, //月份 
     "d+": this.getDate(), //日 
     "H+": this.getHours(), //小时 
     "M+": this.getMinutes(), //分 
     "S+": this.getSeconds(), //秒 
     "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
     "s": this.getMilliseconds() //毫秒 
 };
 if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
 for (var k in o)
 if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
 return fmt;
}


function isIE() { //ie?
    if (!!window.ActiveXObject || "ActiveXObject" in window)
        return true;
    else
        return false;
}
function close(){
	$('#dlg').dialog('close');
	$('#dg').datagrid('reload');
}

function formatPrice(val,row){
	if (val =='E'){
		return '<span style="color:red;">'+val+'</span>';
	} else {
		return val;
	}
}

$.ajaxSetup({   
	error: function (XMLHttpRequest, textStatus, errorThrown){  
	if(XMLHttpRequest.status==403){  
	alert('您没有权限访问此资源或进行此操作');  
	return false;  
	}  
	},    
     complete:function(XMLHttpRequest,textStatus){     
    var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); //通过XMLHttpRequest取得响应头,sessionstatus，   
            if(sessionstatus=='timeout'){     
                  //如果超时就处理 ，指定要跳转的页面    
         var top = getTopWinow(); //获取当前页面的顶层窗口对象  
         $.messager.alert('提示:','登录超时, 请重新登录.','warning'); 
         top.location.href=location.href; //跳转到登陆页面  
         
        // alert('登录超时, 请重新登录.');   
             
         }     
     }     
});

/**  
 * 在页面中任何嵌套层次的窗口中获取顶层窗口  
 * @return 当前页面的顶层窗口对象  
 */  
function getTopWinow(){    
   var p = window;    
   while(p != p.parent){    
       p = p.parent;    
   }    
   return p;    
}

/* 继承 EasyUI 进行扩展自定义的格式  */

/**扩展自定义的日期类***/
  $.fn.datetimebox.defaults.formatter = function(date){
 var y = date.getFullYear();
 var m = date.getMonth()+1;
 var d = date.getDate();
 var h = date.getHours() > 9 ? date.getHours() : '0'+date.getHours();
 var mm = date.getMinutes() > 9 ? date.getMinutes() : '0'+date.getMinutes();
 var s = date.getSeconds() > 9 ? date.getSeconds() : '0'+date.getSeconds();
 return y+'-'+m+'-'+d+' '+h+":"+mm+":"+s;
 } 

//时间格式化
Date.prototype.format = function(format){
   if(!format){
       format = "yyyy-MM-dd hh:mm:ss";
   }


   var o = {
           "M+": this.getMonth() + 1, // month
           "d+": this.getDate(), // day
           "h+": this.getHours(), // hour
           "m+": this.getMinutes(), // minute
           "s+": this.getSeconds(), // second
           "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
          "S": this.getMilliseconds()
           // millisecond
  };
  if (/(y+)/.test(format)) {
       format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
   }


   for (var k in o) {
       if (new RegExp("(" + k + ")").test(format)) { 
           format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" +o[k]).length));
      }
   }
   return format;
 };   


/***扩展editors的datetimebox方法*****/
$.extend($.fn.datagrid.defaults.editors, {
numberspinner: {
        init: function(container, options){
            var input = $('<input type="text">').appendTo(container);
            return input.numberspinner(options);
        },
        destroy: function(target){
            $(target).numberspinner('destroy');
        },
        getValue: function(target){
            return $(target).numberspinner('getValue');
        },
        setValue: function(target, value){
            $(target).numberspinner('setValue',value);
        },
        resize: function(target, width){
            $(target).numberspinner('resize',width);
        }
    },
datetimebox: {//datetimebox就是你要自定义editor的名称
       init: function(container, options){
            var editor = $('<input />').appendTo(container);
            editor.enableEdit = false;
            editor.datetimebox(options);
            return editor;
        },
        getValue: function(target){
        var new_str = $(target).datetimebox('getValue').replace(/:/g,'-');
        new_str = new_str.replace(/ /g,'-');
        var arr = new_str.split("-");
        var datum = new Date(Date.UTC(arr[0],arr[1]-1,arr[2],arr[3]-8,arr[4],arr[5]));
        var timeStamp = datum.getTime();
        
        return new Date(timeStamp).format("yyyy-MM-dd hh:mm:ss");
            //return timeStamp;
       },
        setValue: function(target, value){
        if(value)
        $(target).datetimebox('setValue',new Date(value).format("yyyy-MM-dd hh:mm:ss"));
        else
        $(target).datetimebox('setValue',new Date().format("yyyy-MM-dd hh:mm:ss"));
        },
        resize: function(target, width){
           $(target).datetimebox('resize',width);        
        },
        destroy: function(target){
        $(target).datetimebox('destroy');
        }
    }
}); 


