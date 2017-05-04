package com.wooxun.geekdol.common;

import java.io.File;

import net.coobird.thumbnailator.Thumbnails;

/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author kangtianyu	
 * @CreateDate 2016年9月24日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 kangtianyu	2016年9月24日  下午5:32:23 		创建
 *==========================================================
 * 
 */
public class TestImage {
	
	public static void main(String[] args) {
        try {
            Thumbnails.of("C:\\temp\\123.jpg").scale(1f).outputQuality(0.3f).outputFormat("jpg").toFile("C:\\temp\\123new.jpg");
            
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        /**
         * 缩略图end
         */
        System.out.println("成功");
	}

}
