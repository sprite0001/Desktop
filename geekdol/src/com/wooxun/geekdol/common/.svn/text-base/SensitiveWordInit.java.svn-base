package com.wooxun.geekdol.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
* @Title
* @Description
* @version 1.0.0
* @Author kangtianyu	
* @CreateDate 2016年8月13日 
* @param 
* @see 
* @modified 修改履历
*==========================================================
* No    修改人员			修改日期  	   			描述
* 1. 	 kangtianyu	2016年8月13日  上午9:05:39 		创建
*==========================================================
*
 */
public class SensitiveWordInit {
	@SuppressWarnings("rawtypes")
	public HashMap sensitiveWordMap;
	
	public SensitiveWordInit(){
		super();
	}
	
	/**
	 * @author chenming 
	 * @date 2014年4月20日 下午2:28:32
	 * @version 1.0
	 * @param keyWordList 
	 */
	@SuppressWarnings("rawtypes")
	public Map initKeyWord(List<String> keyWordList){
		try {
			//读取敏感词库
			Set<String> keyWordSet = readSensitiveWordFile(keyWordList);
			//将敏感词库加入到HashMap中
			addSensitiveWordToHashMap(keyWordSet);
			//spring获取application，然后application.setAttribute("sensitiveWordMap",sensitiveWordMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sensitiveWordMap;
	}

	/**
	 * 读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：<br>
	 * 中 = {
	 *      isEnd = 0
	 *      国 = {<br>
	 *      	 isEnd = 1
	 *           人 = {isEnd = 0
	 *                民 = {isEnd = 1}
	 *                }
	 *           男  = {
	 *           	   isEnd = 0
	 *           		人 = {
	 *           			 isEnd = 1
	 *           			}
	 *           	}
	 *           }
	 *      }
	 *  五 = {
	 *      isEnd = 0
	 *      星 = {
	 *      	isEnd = 0
	 *      	红 = {
	 *              isEnd = 0
	 *              旗 = {
	 *                   isEnd = 1
	 *                  }
	 *              }
	 *      	}
	 *      }
	 * @author chenming 
	 * @date 2014年4月20日 下午3:04:20
	 * @param keyWordSet  敏感词库
	 * @version 1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addSensitiveWordToHashMap(Set<String> keyWordSet) {
		sensitiveWordMap = new HashMap(keyWordSet.size());     //初始化敏感词容器，减少扩容操作
		String key = null;  
		Map nowMap = null;
		Map<String, String> newWorMap = null;
		//迭代keyWordSet
		Iterator<String> iterator = keyWordSet.iterator();
		while(iterator.hasNext()){
			key = iterator.next();    //关键字
			nowMap = sensitiveWordMap;
			for(int i = 0 ; i < key.length() ; i++){
				char keyChar = key.charAt(i);       //转换成char型
				Object wordMap = nowMap.get(keyChar);       //获取
				
				if(wordMap != null){        //如果存在该key，直接赋值
					nowMap = (Map) wordMap;
				}
				else{     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
					newWorMap = new HashMap<String,String>();
					newWorMap.put("isEnd", "0");     //不是最后一个
					nowMap.put(keyChar, newWorMap);
					nowMap = newWorMap;
				}
				
				if(i == key.length() - 1){
					nowMap.put("isEnd", "1");    //最后一个
				}
			}
		}
	}

	/**
	 * 读取敏感词库中的内容，将内容添加到set集合中
	 * @author chenming 
	 * @date 2014年4月20日 下午2:31:18
	 * @return
	 * @version 1.0
	 * @param keyWordList 
	 * @throws Exception 
	 */
	private Set<String> readSensitiveWordFile(List<String> keyWordList) throws Exception{
		Set<String> set = new HashSet<String>(keyWordList);   
		return set;
	}
}
