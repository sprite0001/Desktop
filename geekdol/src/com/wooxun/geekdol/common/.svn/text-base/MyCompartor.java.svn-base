package com.wooxun.geekdol.common;

import java.util.Comparator;
import java.util.Date;
import java.util.Map;

/**
 * @Title
 * @Description 自定义比较类，用于按map中的发布时间排序
 * @version 1.0.0
 * @Author zhangyang
 * @CreateDate 2016年8月2日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1.  zhangyang	2016年8月2日  下午3:31:40 		创建
 *==========================================================
 * 
 */
@SuppressWarnings("rawtypes")
public class MyCompartor implements Comparator{
    private String sort = "";
    public MyCompartor(String sort){
        this.sort = sort;
    }
    @Override
    @SuppressWarnings("unchecked")
    public int compare(Object o1, Object o2){
        Map<String,Object> map1= (Map<String,Object>)o1;
           Map<String,Object> map2= (Map<String,Object>)o2;
           if(map1.get(sort) == null){
               return -1;
           }
           if(map2.get(sort) == null){
               return 1;
           }
           Date d1 = (Date)map1.get(sort);
           Date d2 = (Date)map2.get(sort);
           if(d1.getTime() - d2.getTime() > 0){
               return 1;
           }else if(d1.getTime() - d2.getTime() < 0){
               return -1;
           }else{
               return 0;
           }
    }
}
