package com.wooxun.geekdol.common;

import java.io.File;
import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	/**
	 * 上传文件
	 * 
	 * @param files
	 * @param request
	 * @param fingerprintTime
	 * @return
	 */
	public static Boolean uploadfile(MultipartFile files, String filePath, String fileName) {

		MultipartFile myfile = files;
		File file = new File(filePath);
		if (!file.exists()) {
			System.err.println(filePath + " 不存在，创建路径。");
			file.mkdirs();
		}
		// for(MultipartFile myfile : files){
		/*
		 * if(myfile.isEmpty()){ System.out.println("文件未上传"); }else{
		 */
		System.out.println("文件长度: " + myfile.getSize());
		System.out.println("文件类型: " + myfile.getContentType());
		System.out.println("文件名称: " + myfile.getName());
		System.out.println("文件原名: " + myfile.getOriginalFilename());
		System.out.println("========================================");
		try {
			if (fileName == null) {
				fileName = myfile.getOriginalFilename();
			}
			// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
			FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(filePath, fileName));
			// 暂时先不开启 后期如有需要 图片压缩 则开启
			// ImgCompress imgCom = new ImgCompress(new File(filePath,
			// fileName));
			// imgCom.resizeFix(400, 400);
		} catch (IOException e) {
			return false;
		}
		// }
		// }
		return true;
	}
	
	/**
	 * @Title
	 * @Description 上传压缩图片
	 * @author:kangtianyu
	 * @CreateDate:2016年9月26日 上午10:13:01
	 * @param files
	 * @param filePath
	 * @param fileName
	 * @return
	 */
	public static Boolean uploadCompressPic(MultipartFile files, String filePath, String fileName) {

		MultipartFile myfile = files;
		File file = new File(filePath);
		if (!file.exists()) {
			System.err.println(filePath + " 不存在，创建路径。");
			file.mkdirs();
		}
		// for(MultipartFile myfile : files){
		/*
		 * if(myfile.isEmpty()){ System.out.println("文件未上传"); }else{
		 */
		System.out.println("文件长度: " + myfile.getSize());
		System.out.println("文件类型: " + myfile.getContentType());
		System.out.println("文件名称: " + myfile.getName());
		System.out.println("文件原名: " + myfile.getOriginalFilename());
		System.out.println("========================================");
		try {
			if (fileName == null) {
				fileName = myfile.getOriginalFilename();
			}
			/* 压缩图片 */
	        if(fileName.contains(".png")){
	        	fileName = fileName.replace(".png", ".jpg");
	        }
	        if(fileName.contains(".jpeg")){
	        	fileName = fileName.replace(".jpeg", ".jpg");
	        }
	        if(fileName.contains(".bmp")){
	        	fileName = fileName.replace(".bmp", ".jpg");
	        }
	        if(fileName.contains(".gif")){
	        	fileName = fileName.replace(".gif", ".jpg");
	        }
			// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
			FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(filePath, fileName));
			Thumbnails.of(filePath + fileName).scale(1f).outputQuality(0.25f).outputFormat("jpg").toFile(filePath + fileName);
			// 暂时先不开启 后期如有需要 图片压缩 则开启
			// ImgCompress imgCom = new ImgCompress(new File(filePath,
			// fileName));
			// imgCom.resizeFix(400, 400);
		} catch (IOException e) {
			return false;
		}
		// }
		// }
		return true;
	}

	// 删除文件夹
	// param folderPath 文件夹完整绝对路径
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除指定文件夹下所有文件
	// param path 文件夹完整绝对路径
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}
}