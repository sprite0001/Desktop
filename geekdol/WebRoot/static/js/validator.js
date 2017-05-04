$(document).ready(
 		function(){
 			
 			$.extend($.fn.validatebox.defaults.rules, {   
 			    minLength: {   
 			        validator: function(value, param){   //value 为需要校验的输入框的值 , param为使用此规则时存入的参数
 			            return value.length >= param[0];   
 			        },   
 			        message: '请输入最小{0}位字符.'  
 			    }   
 			}); 
 			$.extend($.fn.validatebox.defaults.rules, {
 			    isBlank: {
 			        validator: function (value, param) { 
 			        	return value.replace(/\s+/g,"") != '';
 			        },
 			        message: '不能为空，也不能全部为空格'
 			    }
 			});
 			$.extend($.fn.validatebox.defaults.rules, {   
 			    maxLength: {   
 			        validator: function(value, param){   
 			            return param[0] >= value.length;   
 			        },   
 			        message: '请输入最大{0}位字符.'  
 			    }   
 			}); 
 			
 			$.extend($.fn.validatebox.defaults.rules, {   
 			    length: {   
 			        validator: function(value, param){   
 			            return value.length >= param[0] && param[1] >= value.length;   
 			        },   
 			        message: '请输入{0}-{1}位字符.'  
 			    }   
 			}); 

 		// extend the 'equals' rule   
 			$.extend($.fn.validatebox.defaults.rules, {   
 			    equals: {   
 			        validator: function(value,param){   
 			            return value == $(param[0]).val();   
 			        },   
 			        message: '字段不相同.'  
 			    }   
 			});  
 			
 			$.extend($.fn.validatebox.defaults.rules, {   
 				web : {   
 			        validator: function(value){   
 			            return /^(http[s]{0,1}|ftp):\/\//i.test($.trim(value));   
 			        },   
 			        message: '网址格式错误.'  
 			    }   
 			}); 
 			
 			$.extend($.fn.validatebox.defaults.rules, {
 			    phoneRex: {
 			      validator: function(value){
 			          var rex=/^1[3-8]\d{9}$/;
 			          var rex2=/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
 			          if(rex.test(value)||rex2.test(value)){
 			             return true;
 			          }else{
 			             return false;
 			          }
 			      },
 			      message: '请输入正确电话或手机格式'
 			    }
 			});
 			$.extend($.fn.validatebox.defaults.rules, {
 			    hanzi: {
 			      validator: function(value){
 			          var rex=/^[\u4e00-\u9fa5]+$/;
 			          if(rex.test(value)){
 			             return true;
 			          }else{
 			             return false;
 			          }
 			      },
 			      message: '请输入正确的汉字'
 			    }
 			});
 			$.extend($.fn.validatebox.defaults.rules, {
 			    cnennum: {
 			      validator: function(value){
 			          var rex=/^(\w|[\u4E00-\u9FA5])*$/;
 			          if(rex.test(value)){
 			             return true;
 			          }else{
 			             return false;
 			          }
 			      },
 			      message: '请输入汉字或英文或数字'
 			    }
 			});
 			$.extend($.fn.validatebox.defaults.rules, {
 			    engAndNum: {
 			      validator: function(value){
 			          var rex=/^[a-zA-Z0-9]+$/;
 			          if(rex.test(value)){
 			             return true;
 			          }else{
 			             return false;
 			          }
 			      },
 			      message: '只能输入英文和数字！'
 			    }
 			});
 			$.extend($.fn.validatebox.defaults.rules, {   
 				organizationCode : {   
 			        validator: function(value){
 			        	var data0 = false;
	 			        $.ajax({
	 			       		type: "POST",async:false,
	 			       		url:"aroundSuggestStore/checkOrganizationCode",
	 			       		dataType:"json",
	 			       		data:{"organizationCode":value},
	 			       		async:false,
	 			       		success: function(result){
		 			       		if (result.type=='Success'){
		 			       			data0 = true;
	 			                } else {
	 			                	data0 = false;
	 			                }
	 			       		}
	 			       	});
 			        	return data0;
 			        },   
 			        message: '组织代码证重复.'  
 			    }   
 			});
 			
 			$.extend($.fn.validatebox.defaults.rules, {   
 				provinceName : {   
 			        validator: function(value){
 			        	var data0 = false;
	 			        $.ajax({
	 			       		type: "POST",async:false,
	 			       		url:"province/checkProvinceName",
	 			       		dataType:"json",
	 			       		data:{"provinceName":value, "id":null},
	 			       		async:false,
	 			       		success: function(result){
		 			       		if (result.type=='Success'){
		 			       			data0 = true;
	 			                } else {
	 			                	data0 = false;
	 			                }
	 			       		}
	 			       	});
 			        	return data0;
 			        },   
 			        message: '省名字重复.'  
 			    }   
 			});
 			            
           $.extend($.fn.validatebox.defaults.rules, {   
        	   mobile : {   
 			        validator: function(value){   
 			            return /^1[0-9]{10}$/i.test($.trim(value));   
 			        },   
 			        message: '手机号码格式错误.'  
 			    }   
 			});
           $.extend($.fn.validatebox.defaults.rules, {   
        	   integer: {// 验证整数
                   validator: function (value) {
                	   if(!isNaN(value)&&value>0){ 
                		   return true;
            	       }else{
            	    	   return false;
            	       }
                   },
                   message: '请输入正整数.'
               }  
 			});
           $.extend($.fn.validatebox.defaults.rules, {   
        	   date : {   
 			        validator: function(value){   
 			            return /^[0-9]{4}[-][0-9]{2}[-][0-9]{2}$/i.test($.trim(value));   
 			        },   
 			        message: '曰期格式错误,如2012-09-11'  
 			    }   
 			}); 
           $.extend($.fn.validatebox.defaults.rules, {   
        	   datetime : {   
 			        validator: function(value){   
 			            return /^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\d):[0-5]?\d:[0-5]?\d$/i.test($.trim(value));   
 			        },   
 			        message: '曰期格式错误,如2012-09-11 18:25:30'  
 			    }   
 			}); 
           
           $.extend($.fn.validatebox.defaults.rules, {   
        	   email : {   
 			        validator: function(value){   
 			            return /^[a-zA-Z0-9_+.-]+\@([a-zA-Z0-9-]+\.)+[a-zA-Z0-9]{2,4}$/i.test($.trim(value));   
 			        },   
 			        message: '电子邮箱格式错误.'  
 			    }   
 			}); 
           $.extend($.fn.validatebox.defaults.rules, {   
        	   inputCnEn: { // 验证输入合法性，可以是中文或英文
                   validator: function (value) {
                       return /^[\u4e00-\u9fa5_a-zA-Z]+$/i.test(value);
                   },
                   message: '请输入中文或英文'
               }
 			}); 
           $.extend($.fn.validatebox.defaults.rules, {   
        	   digitalNumber : {   
 			        validator: function(value){   
 			            return /^[0-9]*$/i.test($.trim(value));   
 			        },   
 			        message: '请输入数字！'  
 			    }   
 			}); 
           $.extend($.fn.validatebox.defaults.rules, {   
        	    xiaoshuOrzhengshu: {   
 			        validator: function(value){   
 			        	var rex=/^[+]?\d+(\.\d+)?$/;
 			        	 if(rex.test(value)){
 	 			             return true;
 	 			          }else{
 	 			             return false;
 	 			          }  
 			        },   
 			        message: '请输入整数或者小数！'  
 			    }   
 			}); 
           $.extend($.fn.validatebox.defaults.rules, {   
        	   captcha : {   
 			        validator: function(value){   
 			        	var data0 = false;
	 			        $.ajax({
	 			       		type: "POST",async:false,
	 			       		url:contextPath + "/json/valSimulation.action",
	 			       		dataType:"json",
	 			       		data:{"simulation":value},
	 			       		async:false,
	 			       		success: function(data){
	 			       			data0=data;
	 			       		}
	 			       	});
 			        	
	 			       return data0;
// 			        	return /^[a-zA-Z0-9]{4}$/i.test($.trim(value));
 			        },   
 			        message: '验证码错误.'  
 			    }   
 			}); 
           
           
           $.extend($.fn.validatebox.defaults.rules, {
       		//验证开始时间小于结束时间 
       		md: { 
       			validator: function(value, param){ 
       				var varify = false;
       				var loadTime = $(param[0]).textbox('getValue'); 
       				var loadTime1 = $(param[1]).textbox('getValue'); 
       				varify= loadTime1 > loadTime; 
       				return varify; 
       			}, 
       			message: '保修截至时间要大于上线时间！' 
       		} ,
       		// 验证textbox输入长度
       		text_length:{
       			validator: function(value){
       				var str = $.trim(value);
       				return str.length > 0 && value.length <= 25 ;
       			},
       			message:'请输入1-25个字符!'
       		},
       		// 验证textbox输入长度
       		text_length_two:{
       			validator: function(value){
       				return  value.length <= 50 ;
       			},
       			message:'请输入0-50个字符!'
       		}
       	});
           
           $.extend($.fn.validatebox.defaults.rules, {
               /*必须和某个字段相等*/
               equalTo: { 
               	validator: function (value, param) { 
               		return $(param[0]).val() == value; 
               	}, 
               	message: '字段不匹配' }
        	});
           
           $.extend($.fn.validatebox.defaults.rules, {     
        	    idcared: {     
        	        validator: function(value,param){    
        	            var flag= isCardID(value);  
        	            return flag==true?true:false;    
        	        },     
        	        message: '不是有效的身份证号码'    
        	    }     
        	}); 
 	});
var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}   

function isCardID(sId){   
    var iSum=0 ;  
    var info="" ;  
    if(!/^\d{17}(\d|x)$/i.test(sId)) return "你输入的身份证长度或格式错误";   
    sId=sId.replace(/x$/i,"a");   
    if(aCity[parseInt(sId.substr(0,2))]==null) return "你的身份证地区非法";   
    sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2));   
    var d=new Date(sBirthday.replace(/-/g,"/")) ;  
    if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate()))return "身份证上的出生日期非法";   
    for(var i = 17;i>=0;i --) iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;  
    if(iSum%11!=1) return "你输入的身份证号非法";   
    return true;
}