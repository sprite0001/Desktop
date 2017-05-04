package com.wooxun.geekdol.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class DownLoadUtil {

	/**   
     *   Excel文件下载
     *   @param   filePath   文件下载路径
     *   @param   response 
     *   @param   fileName
     *   @param   fileType
     *   @throws  Exception    
     */ 
	public static void downLoadFile(String filePath,
			HttpServletResponse response, String fileName, String fileType)
			throws FileNotFoundException, IOException {
		File file = new File(filePath); // 根据文件路径获得File文件
		// 设置文件类型(这样设置就不止是下Excel文件了，一举多得)
		if ("pdf".equals(fileType)) {
			response.setContentType("application/pdf;charset=GBK");
		} else if ("xls".equals(fileType)) {
			response.setContentType("application/msexcel;charset=GBK");
		} else if ("doc".equals(fileType)) {
			response.setContentType("application/msword;charset=GBK");
		}

		// 文件名
		response.setHeader("Content-Disposition", "attachment;filename=\""
				+ new String(fileName.getBytes(), "ISO8859-1") + "\"");
		response.setContentLength((int) file.length());
		byte[] buffer = new byte[4096];// 缓冲区
		BufferedOutputStream output = null;
		BufferedInputStream input = null;
		try {
			output = new BufferedOutputStream(response.getOutputStream());
			input = new BufferedInputStream(new FileInputStream(file));
			int n = -1;

			// 遍历，开始下载
			while ((n = input.read(buffer, 0, 4096)) > -1) {
				output.write(buffer, 0, n);
			}
			output.flush();
			response.flushBuffer();
		} finally {

			// 关闭流，不可少
			if (input != null)
				input.close();
			if (output != null)
				output.close();
		}

	}
	
	/**
	 * 修改了IE浏览器到处时文件名乱码的问题
	 * @param request
	 * @param filePath
	 * @param response
	 * @param fileName
	 * @param fileType
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void downLoadFileForIE(HttpServletRequest request,String filePath,
			HttpServletResponse response, String fileName, String fileType)
			throws FileNotFoundException, IOException {
		File file = new File(filePath); // 根据文件路径获得File文件
		// 设置文件类型(这样设置就不止是下Excel文件了，一举多得)
		if ("pdf".equals(fileType)) {
			response.setContentType("application/pdf;charset=GBK");
		} else if ("xls".equals(fileType)) {
			response.setContentType("application/msexcel;charset=GBK");
		} else if ("doc".equals(fileType)) {
			response.setContentType("application/msword;charset=GBK");
		}

		String encodingFileName=processFileName(request, fileName);
		// 文件名
		response.setHeader("Content-Disposition", "attachment;filename=\""
				+ encodingFileName + "\"");
		response.setContentLength((int) file.length());
		byte[] buffer = new byte[4096];// 缓冲区
		BufferedOutputStream output = null;
		BufferedInputStream input = null;
		try {
			output = new BufferedOutputStream(response.getOutputStream());
			input = new BufferedInputStream(new FileInputStream(file));
			int n = -1;

			// 遍历，开始下载
			while ((n = input.read(buffer, 0, 4096)) > -1) {
				output.write(buffer, 0, n);
			}
			output.flush();
			response.flushBuffer();
		} finally {

			// 关闭流，不可少
			if (input != null)
				input.close();
			if (output != null)
				output.close();
		}

	}
	
     /**
      * 
      * @Title: processFileName
      * @Description: ie,chrom,firfox下处理文件名显示乱码
      */
    public static String processFileName(HttpServletRequest request, String fileNames) {
        String codedfilename = null;
        try {
            String agent = request.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie            
            	String name = java.net.URLEncoder.encode(fileNames, "UTF8");                 
            	codedfilename = name;
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等 
                codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codedfilename;
    } 
}