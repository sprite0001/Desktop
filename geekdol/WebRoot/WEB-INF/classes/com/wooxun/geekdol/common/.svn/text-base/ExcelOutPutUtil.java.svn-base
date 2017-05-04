package com.wooxun.geekdol.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

public class ExcelOutPutUtil {

	/**
	 * 生成一个Excel文件POI
	 * 
	 * @param inputFile
	 *            输入模板文件路径
	 * @param outputFile
	 *            输入文件存放于服务器路径
	 * @param dataList
	 *            待导出数据
	 * @throws Exception
	 * @roseuid:
	 */

	public static void exportExcelFile(String inputFile, OutputStream out,
			List<List<String>> dataList, ArrayList<int[]> commentIndexList)
			throws FileNotFoundException, IOException {
		// 用模板文件构造poi
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(inputFile));
		// 创建模板工作表
		HSSFWorkbook templatewb = new HSSFWorkbook(fs);
		// 直接取模板第一个sheet对象
		HSSFSheet templateSheet = templatewb.getSheetAt(0);
		//宽度自适应
		templateSheet.autoSizeColumn(1, true); 
		
		//字体
		HSSFFont font = templatewb.createFont();
        font.setFontName("宋体");//字体
        font.setFontHeightInPoints((short) 12);//字号
        font.setColor(HSSFColor.BLACK.index);//颜色
        
		// 得到模板的第一个sheet的第一行对象 为了得到模板样式
		HSSFRow templateRow = templateSheet.getRow(0);
		// 取得Excel文件的总列数
		int columns = templateSheet.getRow(0).getPhysicalNumberOfCells();
		// Debug.println("columns   is   :   " + columns);
		// //=========================
		// 创建样式数组
		HSSFCellStyle styleArray[] = new HSSFCellStyle[columns];

		// 一次性创建所有列的样式放在数组里
		for (int s = 0; s < columns; s++) {
			// 得到数组实例
			styleArray[s] = templatewb.createCellStyle();
			styleArray[s].setFont(font);
			 //下边框   
			styleArray[s].setBorderBottom(CellStyle.SOLID_FOREGROUND);  
	        //下边框颜色  
			styleArray[s].setBottomBorderColor(IndexedColors.BLACK.getIndex());//黑色  
	        //左边框  
			styleArray[s].setBorderLeft(CellStyle.SOLID_FOREGROUND);  
	        //左边框颜色  
			styleArray[s].setLeftBorderColor(IndexedColors.BLACK.getIndex());
	        //右边框  
			styleArray[s].setBorderRight(CellStyle.SOLID_FOREGROUND);  
	        //右边框颜色  
			styleArray[s].setRightBorderColor(IndexedColors.BLACK.getIndex());  
	        //上边框  
			styleArray[s].setBorderTop(CellStyle.SOLID_FOREGROUND);
	        //上边框颜色  
			styleArray[s].setTopBorderColor(IndexedColors.BLACK.getIndex());
		}
		// 循环对每一个单元格进行赋值
		// 定位行
		int c = 0;
		for (int rowId = 2; rowId <= dataList.size()+1; rowId++) {


			HSSFCell cell = null;
			int b = 0;

			if (dataList.get(rowId - 2) == null) {
				continue;

			}
			// 创建一个新的rowId行 行对象
			// 新建的行对象
			HSSFRow hssfRow = templateSheet.createRow(rowId-1);//863-FZQ修改 rowId->rowId-1
			//设置行高
			hssfRow.setHeight((short) 400);
			
			
			// 依次取第rowId行数据 每一个数据是valueList
			List<String> valueList = dataList.get(rowId - 2);
			int[] commentIndex = null;
			if (commentIndexList != null) {
				for (; c < commentIndexList.size();) {
					commentIndex = commentIndexList.get(c);
					c++;
					break;
				}
			}
			// 定位列
			for (int columnId = 0; columnId < valueList.size(); columnId++) {
				
				// 依次取出对应与colunmId列的值
				// 每一个单元格的值
				String dataValue = "";
				if (valueList.get(columnId) != null) {

					dataValue = valueList.get(columnId).toString();
				// 863-FZQ修改
				/*	if(dataValue.equals("0.0") || dataValue.equals("0")){
						dataValue = "";
					}*/
				}
				// 备注存在标示
				boolean commentFlag = false;
				HSSFCellStyle style = null;
				HSSFCell templateCell = null;
				if (commentIndex != null) {
					for (int a = 0; a < commentIndex.length; a++) {
						if (columnId == commentIndex[a]) {
							b = b - 1;
							commentFlag = true;
							break;
						}

					}
					if (commentFlag == false) {
						// 取出colunmId列的的style
						// 模板每一列的样式
						style = styleArray[b];
						// 取模板第colunmId列的单元格对象
						// 模板单元格对象
						templateCell = templateRow.getCell(b);
						// 创建新的rowId行 columnId列 单元格对象
						// 新建的单元格对象
						cell = hssfRow.createCell(b);
						// 如果对应的模板单元格 样式为非锁定
						if (templateCell.getCellStyle().getLocked() == false) {
							// 设置此列style为非锁定
							style.setLocked(false);
							// 设置到新的单元格上
							cell.setCellStyle(style);
						}
						// 否则样式为锁定
						else {
							// 设置此列style为锁定
							style.setLocked(true);
							// 设置到新单元格上
							cell.setCellStyle(style);
						}
					}
				} else {

					// 取出colunmId列的的style
					// 模板每一列的样式
					style = styleArray[columnId];
					// 取模板第colunmId列的单元格对象
					// 模板单元格对象
					templateCell = templateRow.getCell(columnId);
					// 创建新的rowId行 columnId列 单元格对象
					// 新建的单元格对象
					cell = hssfRow.createCell(columnId);
					// 如果对应的模板单元格 样式为非锁定
					if (templateCell.getCellStyle().getLocked() == false) {
						// 设置此列style为非锁定
						style.setLocked(false);
						// 设置到新的单元格上
						cell.setCellStyle(style);
					}
					// 否则样式为锁定
					else {
						// 设置此列style为锁定
						style.setLocked(true);
						// 设置到新单元格上
						cell.setCellStyle(style);
					}

				}

				// Debug.println("dataValue   :   " + dataValue);
				// 设置值 统一为String

				if (commentFlag == true) {
					if (dataValue != null && !"".equals(dataValue)) {

						// 声明一个画图的顶级管理器
						HSSFPatriarch patriarch = templateSheet
								.createDrawingPatriarch();
						HSSFRichTextString richString = new HSSFRichTextString(
								dataValue);
						// 定义注释的大小和位置,详见文档
						HSSFComment comment = patriarch
								.createComment(new HSSFClientAnchor(0, 0, 255,
										255, (short) 6, 6, (short) 10, 10));
						// 设置注释内容
						comment.setString(richString);
						cell.setCellComment(comment);

					}

				} else {
					cell.setCellValue(dataValue);
				}
				b++;
			}
		}
		// 将模板的内容写到输出文件上
		templatewb.write(out);
		out.flush();

		// 操作结束，关闭文件
		out.close();

	}
	
	
	public static void exportExcelFile(String inputFile, OutputStream out,
			List<List<String>> dataList, ArrayList<int[]> commentIndexList,
			ArrayList<int[]> flagIndexList,String year,String month,String status)
			throws FileNotFoundException, IOException {
		// 用模板文件构造poi
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(inputFile));
		// 创建模板工作表
		HSSFWorkbook templatewb = new HSSFWorkbook(fs);
		// 直接取模板第一个sheet对象
		HSSFSheet templateSheet = templatewb.getSheetAt(0);
		//宽度自适应
		templateSheet.autoSizeColumn(1, true); 
		
		//字体
		HSSFFont font = templatewb.createFont();
        font.setFontName("宋体");//字体
        font.setFontHeightInPoints((short) 12);//字号
        font.setColor(HSSFColor.BLACK.index);//颜色
        
		// 得到模板的第一个sheet的第一行对象 为了得到模板样式
		HSSFRow templateRow = templateSheet.getRow(0);
		// 取得Excel文件的总列数
		int columns = templateSheet.getRow(0).getPhysicalNumberOfCells();
		// Debug.println("columns   is   :   " + columns);
		// //=========================
		// 创建样式数组
		HSSFCellStyle styleArray[] = new HSSFCellStyle[columns];

		// 一次性创建所有列的样式放在数组里
		for (int s = 0; s < columns; s++) {
			// 得到数组实例
			styleArray[s] = templatewb.createCellStyle();
			styleArray[s].setFont(font);
			 //下边框   
			styleArray[s].setBorderBottom(CellStyle.SOLID_FOREGROUND);  
	        //下边框颜色  
			styleArray[s].setBottomBorderColor(IndexedColors.BLACK.getIndex());//黑色  
	        //左边框  
			styleArray[s].setBorderLeft(CellStyle.SOLID_FOREGROUND);  
	        //左边框颜色  
			styleArray[s].setLeftBorderColor(IndexedColors.BLACK.getIndex());
	        //右边框  
			styleArray[s].setBorderRight(CellStyle.SOLID_FOREGROUND);  
	        //右边框颜色  
			styleArray[s].setRightBorderColor(IndexedColors.BLACK.getIndex());  
	        //上边框  
			styleArray[s].setBorderTop(CellStyle.SOLID_FOREGROUND);
	        //上边框颜色  
			styleArray[s].setTopBorderColor(IndexedColors.BLACK.getIndex());
		}
		
		HSSFFont font0 = templatewb.createFont();
        font0.setFontName("宋体");//字体
        font0.setFontHeightInPoints((short) 20);//字号
        font0.setColor(HSSFColor.BLACK.index);//颜色
        HSSFCellStyle style0 = templatewb.createCellStyle();
        style0.setFont(font0);
        style0.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		HSSFRow hssfRow0 = templateSheet.createRow(0);
		//设置行高
		hssfRow0.setHeight((short) 500);
		HSSFCell hssfCell0 = hssfRow0.createCell(0);
		style0.setLocked(true);
		hssfCell0.setCellStyle(style0);
		hssfCell0.setCellValue(year + "年" + month + "月");
		
		HSSFCell hssfCell1 = hssfRow0.createCell(9);
		HSSFCellStyle style1 = templatewb.createCellStyle();
        style1.setFont(font0);
        style1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style1.setLocked(true);
		hssfCell1.setCellStyle(style1);
		// 0 : 863 ,1: 代理
		int[] intArry = new int[10];
		if(status.equals("0")){
			hssfCell1.setCellValue("软件事业本部外派补助表");
			int[] intArry1 = {7,8,9,10,11,12,13,15,16,17,18};
			intArry = intArry1;
		}else{
			hssfCell1.setCellValue("软件事业本部工资表");
			int[] intArry2 = {8,9,10,11,12,13,14,15,16,17,18,19,21,22,23,24,25};
			intArry = intArry2;
		}
		// 循环对每一个单元格进行赋值
		// 定位行
		int c = 0;
		int d = 0;
		for (int rowId = 1; rowId <= dataList.size(); rowId++) {
			HSSFCell cell = null;
			int b = 0;
			if (dataList.get(rowId - 1) == null) {
				continue;
			}
			// 创建一个新的rowId行 行对象
			// 新建的行对象
			HSSFRow hssfRow = templateSheet.createRow(rowId);
			//设置行高
			hssfRow.setHeight((short) 400);
			// 依次取第rowId行数据 每一个数据是valueList
			List<String> valueList = dataList.get(rowId - 1);
			int[] commentIndex = null;
			if (commentIndexList != null) {
				for (; c < commentIndexList.size();) {
					commentIndex = commentIndexList.get(c);
					c++;
					break;
				}
			}
			int[] flagIndex = null;
			if (flagIndexList != null) {
				for (; d < flagIndexList.size();) {
					flagIndex = flagIndexList.get(d);
					d++;
					break;
				}
			}
			// 定位列
			for (int columnId = 0; columnId < valueList.size(); columnId++) {
				// 依次取出对应与colunmId列的值
				// 每一个单元格的值
				String dataValue = "";
				if (valueList.get(columnId) != null) {
					dataValue = valueList.get(columnId).toString();
					if(dataValue.equals("0.0") || dataValue.equals("0")){
						dataValue = "";
					}
				}
				// 备注存在标示
				boolean commentFlag = false;
				HSSFCellStyle style = null;
				HSSFCell templateCell = null;
				if (commentIndex != null) {
					for (int a = 0; a < commentIndex.length; a++) {
						if (columnId == commentIndex[a]) {
							b = b - 1;
							commentFlag = true;
							break;
						}
					}
					if (commentFlag == false) {
						// 取出colunmId列的的style
						// 模板每一列的样式
						style = styleArray[b];
						// 取模板第colunmId列的单元格对象
						// 模板单元格对象
						templateCell = templateRow.getCell(b);
						// 创建新的rowId行 columnId列 单元格对象
						// 新建的单元格对象
						cell = hssfRow.createCell(b);
						// 如果对应的模板单元格 样式为非锁定
						if (templateCell.getCellStyle().getLocked() == false) {
							// 设置此列style为非锁定
							style.setLocked(false);
							// 设置到新的单元格上
							cell.setCellStyle(style);
							
						}
						// 否则样式为锁定
						else {
							// 设置此列style为锁定
							style.setLocked(true);
							// 设置到新单元格上
							cell.setCellStyle(style);
						}
					}
				} else {
					// 取出colunmId列的的style
					// 模板每一列的样式
					style = styleArray[columnId];
					// 取模板第colunmId列的单元格对象
					// 模板单元格对象
					templateCell = templateRow.getCell(columnId);
					// 创建新的rowId行 columnId列 单元格对象
					// 新建的单元格对象
					cell = hssfRow.createCell(columnId);
					// 如果对应的模板单元格 样式为非锁定
					if (templateCell.getCellStyle().getLocked() == false) {
						// 设置此列style为非锁定
						style.setLocked(false);
						// 设置到新的单元格上
						cell.setCellStyle(style);
						
					}
					// 否则样式为锁定
					else {
						// 设置此列style为锁定
						style.setLocked(true);
						// 设置到新单元格上
						cell.setCellStyle(style);
					}
				}
				// Debug.println("dataValue   :   " + dataValue);
				// 设置值 统一为String
				if (commentFlag == true) {
					if (dataValue != null && !"".equals(dataValue)) {
						// 声明一个画图的顶级管理器
						HSSFPatriarch patriarch = templateSheet
								.createDrawingPatriarch();
						HSSFRichTextString richString = new HSSFRichTextString(
								dataValue);
						// 定义注释的大小和位置,详见文档
						HSSFComment comment = patriarch
								.createComment(new HSSFClientAnchor(0, 0, 255,
										255, (short) 6, 6, (short) 10, 10));
						// 设置注释内容
						comment.setString(richString);
						cell.setCellComment(comment);
					}
				} else {
					if(ExcelOutPutUtil.contains(intArry, b) && StringUtils.isNotEmpty(dataValue)){
						cell.setCellValue(Double.parseDouble(dataValue));
					} else {
						cell.setCellValue(dataValue);
					}
					
				}
				b++;
			}
			for (int columnId = 0; columnId < valueList.size(); columnId++) {
				boolean flag = false;
				HSSFCellStyle style = null;
				HSSFCell templateCell = null;
				if(flagIndex != null){
					for (int a = 0; a < flagIndex.length; a++) {
						if (columnId == flagIndex[a]) {
							flag = true;
							break;
						}
					}
					if (flag == true) {
						// 取出colunmId列的的style
						// 模板每一列的样式
						style = templatewb.createCellStyle();
						style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
						style.setFillPattern(CellStyle.SOLID_FOREGROUND);
						// 取模板第colunmId列的单元格对象
						// 模板单元格对象
						templateCell = templateRow.getCell(columnId);
						// 创建新的rowId行 columnId列 单元格对象
						// 新建的单元格对象
						cell = hssfRow.getCell(columnId);
						// 如果对应的模板单元格 样式为非锁定
						if (templateCell.getCellStyle().getLocked() == false) {
							// 设置此列style为非锁定
							style.setLocked(false);
							// 设置到新的单元格上
							cell.setCellStyle(style);
							
						}
						// 否则样式为锁定
						else {
							// 设置此列style为锁定
							style.setLocked(true);
							// 设置到新单元格上
							cell.setCellStyle(style);
							
						}
						System.out.println("==== rowId+++++++" + rowId);
						System.out.println("==== columnId ++++++++" +columnId);
					}
					
					
				}
				}
		}
		// 将模板的内容写到输出文件上
		templatewb.write(out);
		out.flush();
		// 操作结束，关闭文件
		out.close();

	}
	
	/**
	 * 生成一个Excel文件POI
	 * 说明：这个方法用于生成多个sheet的Excel
	 * 
	 * @param inputFile
	 *            输入模板文件路径
	 * @param outputFile
	 *            输入文件存放于服务器路径
	 * @param allDataList
	 *            存放八张表中的所有数据信息
	 * @param index
	 *            使用模板表单的索引
	 * @throws Exception
	 * @roseuid:
	 */
	public static void exportMultiExcelFile(String inputFile, OutputStream out,
			List<List<List<String>>> allDataList, ArrayList<int[]> commentIndexList)
			throws FileNotFoundException, IOException {
		// 用模板文件构造poi
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(inputFile));
		// 创建模板工作表
		HSSFWorkbook templatewb = new HSSFWorkbook(fs);
		for(int i = 0; i < allDataList.size(); i++){
			//获取第i张sheet的数据
			List<List<String>> dataList = allDataList.get(i);
			//如果表中没有数据，不再显示这张表
			if(dataList.size() == 0){
				//隐藏对应索引处的sheet
				templatewb.setSheetHidden(i, true);
				continue;
			}
			// 直接取模板第i个sheet对象
			HSSFSheet templateSheet = templatewb.getSheetAt(i);
			
			//字体
			HSSFFont font = templatewb.createFont();
	        font.setFontName("宋体");//字体
	        font.setFontHeightInPoints((short) 12);//字号
	        font.setColor(HSSFColor.BLACK.index);//颜色
	        
			// 得到模板的第i个sheet的第一行对象 为了得到这个sheet样式
			HSSFRow templateRow = templateSheet.getRow(1);	
			// 取得Excel文件的总列数
			int columns = templateSheet.getRow(1).getPhysicalNumberOfCells();
			// Debug.println("columns   is   :   " + columns);

			// 创建样式数组,每个列对应一个样式：styleArray[i]
			HSSFCellStyle styleArray[] = new HSSFCellStyle[columns];

			// 一次性创建所有列的样式放在数组里
			for (int s = 0; s < columns; s++) {
				// 得到数组实例
				styleArray[s] = templatewb.createCellStyle();
				styleArray[s].setFont(font);
				 //下边框   
				styleArray[s].setBorderBottom(CellStyle.SOLID_FOREGROUND);  
		        //下边框颜色  
				styleArray[s].setBottomBorderColor(IndexedColors.BLACK.getIndex());//黑色  
		        //左边框  
				styleArray[s].setBorderLeft(CellStyle.SOLID_FOREGROUND);  
		        //左边框颜色  
				styleArray[s].setLeftBorderColor(IndexedColors.BLACK.getIndex());
		        //右边框  
				styleArray[s].setBorderRight(CellStyle.SOLID_FOREGROUND);  
		        //右边框颜色  
				styleArray[s].setRightBorderColor(IndexedColors.BLACK.getIndex());  
		        //上边框  
				styleArray[s].setBorderTop(CellStyle.SOLID_FOREGROUND);
		        //上边框颜色  
				styleArray[s].setTopBorderColor(IndexedColors.BLACK.getIndex());
			}
			// 定位行 循环对每一个单元格进行赋值
			// 起始位置：从第3行开始赋值(rowId=2)，第1行是表的标题，第2行是列标题
			// 结束位置：dataList.size()+1
			int c = 0;
			for (int rowId = 2; rowId <= dataList.size()+1; rowId++) {

				HSSFCell cell = null;
				int b = 0;

				if (dataList.get(rowId - 2) == null) {
					continue;
				}
				// 创建一个新的rowId行 行对象
				// 新建的行对象:hssfRow
				HSSFRow hssfRow = templateSheet.createRow(rowId);
				//设置行高
				hssfRow.setHeight((short) 450);
				
				// 依次取第rowId行数据,数据存放在valueList中
				List<String> valueList = dataList.get(rowId - 2);
				int[] commentIndex = null;
				if (commentIndexList != null) {
					for (; c < commentIndexList.size();) {
						commentIndex = commentIndexList.get(c);
						c++;
						break;
					}
				}
				// 定位列
				for (int columnId = 0; columnId < valueList.size(); columnId++) {
					
					// 依次取出对应与colunmId列的值
					// 每一个单元格的值,保存在dataValue中
					String dataValue = "";
					if (valueList.get(columnId) != null) {

						dataValue = valueList.get(columnId).toString();
						if(dataValue.equals("0.0") || dataValue.equals("0")){
							dataValue = "";
						}
					}
					// 备注存在标示
					boolean commentFlag = false;
					HSSFCellStyle style = null;
					HSSFCell templateCell = null;
					if (commentIndex != null) {
						for (int a = 0; a < commentIndex.length; a++) {
							if (columnId == commentIndex[a]) {
								b = b - 1;
								commentFlag = true;
								break;
							}

						}
						if (commentFlag == false) {
							// 取出colunmId列的的style
							// 模板每一列的样式
							style = styleArray[b];
							// 取模板第colunmId列的单元格对象
							// 模板单元格对象
							templateCell = templateRow.getCell(b);
							// 创建新的rowId行 columnId列 单元格对象
							// 新建的单元格对象
							cell = hssfRow.createCell(b);
							// 如果对应的模板单元格 样式为非锁定
							if (templateCell.getCellStyle().getLocked() == false) {
								// 设置此列style为非锁定
								style.setLocked(false);
								// 设置到新的单元格上
								cell.setCellStyle(style);
							}
							// 否则样式为锁定
							else {
								// 设置此列style为锁定
								style.setLocked(true);
								// 设置到新单元格上
								cell.setCellStyle(style);
							}
						}
					} else {
						// 取出colunmId列的的style
						// 模板每一列的样式
						style = styleArray[columnId];
						// 取模板第colunmId列的单元格对象
						// 模板单元格对象
						// templateRow：当前sheet的第1行对象 为了得到这个sheet样式
						templateCell = templateRow.getCell(columnId);
						// 创建新的rowId行 columnId列 单元格对象
						// 新建的单元格对象
						cell = hssfRow.createCell(columnId);
						// 如果对应的模板单元格 样式为非锁定
						if (templateCell.getCellStyle().getLocked() == false) {
							// 设置此列style为非锁定
							style.setLocked(false);
							// 设置到新的单元格上
							cell.setCellStyle(style);
						}
						// 否则模板单元格 样式为锁定
						else {
							// 设置此列style为锁定
							style.setLocked(true);
							// 设置到新单元格上
							cell.setCellStyle(style);
						}

					}

					// Debug.println("dataValue   :   " + dataValue);
					// 设置值 统一为String
					if (commentFlag == true) {
						if (dataValue != null && !"".equals(dataValue)) {

							// 声明一个画图的顶级管理器
							HSSFPatriarch patriarch = templateSheet
									.createDrawingPatriarch();
							HSSFRichTextString richString = new HSSFRichTextString(
									dataValue);
							// 定义注释的大小和位置,详见文档
							HSSFComment comment = patriarch
									.createComment(new HSSFClientAnchor(0, 0, 255,
											255, (short) 6, 6, (short) 10, 10));
							// 设置注释内容
							comment.setString(richString);
							cell.setCellComment(comment);

						}

					} else {
						cell.setCellValue(dataValue);
					}
					b++;
				}//为每一行的每一个列赋值的for循环结束
			}//为每行赋值的for循环结束
			
			//设置列的宽度为自适应
			for(int j = 0; j < columns; j++){
				templateSheet.autoSizeColumn(j,true);
			}
		}
		// 将第一个表显示在最上面
		templatewb.setActiveSheet(0);
		// 将模板的内容写到输出文件上
		templatewb.write(out);
		out.flush();
		// 操作结束，关闭文件
		out.close();

	}
	
	public static void exportExcelFile1(String inputFile, OutputStream out,
			List<List<String>> dataList, ArrayList<int[]> commentIndexList)
			throws FileNotFoundException, IOException {
		// 用模板文件构造poi
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(inputFile));
		// 创建模板工作表
		HSSFWorkbook templatewb = new HSSFWorkbook(fs);
		// 直接取模板第一个sheet对象
		HSSFSheet templateSheet = templatewb.getSheetAt(0);
		//宽度自适应
		templateSheet.autoSizeColumn(1, true); 
		
		//字体
		HSSFFont font = templatewb.createFont();
        font.setFontName("宋体");//字体
        font.setFontHeightInPoints((short) 12);//字号
        font.setColor(HSSFColor.BLACK.index);//颜色
        
		// 得到模板的第一个sheet的第一行对象 为了得到模板样式
		HSSFRow templateRow = templateSheet.getRow(0);
		// 取得Excel文件的总列数
		int columns = templateSheet.getRow(0).getPhysicalNumberOfCells();
		// Debug.println("columns   is   :   " + columns);
		// //=========================
		// 创建样式数组
		HSSFCellStyle styleArray[] = new HSSFCellStyle[columns];

		// 一次性创建所有列的样式放在数组里
		for (int s = 0; s < columns; s++) {
			// 得到数组实例
			styleArray[s] = templatewb.createCellStyle();
			styleArray[s].setFont(font);
			 //下边框   
			styleArray[s].setBorderBottom(CellStyle.SOLID_FOREGROUND);  
	        //下边框颜色  
			styleArray[s].setBottomBorderColor(IndexedColors.BLACK.getIndex());//黑色  
	        //左边框  
			styleArray[s].setBorderLeft(CellStyle.SOLID_FOREGROUND);  
	        //左边框颜色  
			styleArray[s].setLeftBorderColor(IndexedColors.BLACK.getIndex());
	        //右边框  
			styleArray[s].setBorderRight(CellStyle.SOLID_FOREGROUND);  
	        //右边框颜色  
			styleArray[s].setRightBorderColor(IndexedColors.BLACK.getIndex());  
	        //上边框  
			styleArray[s].setBorderTop(CellStyle.SOLID_FOREGROUND);
	        //上边框颜色  
			styleArray[s].setTopBorderColor(IndexedColors.BLACK.getIndex());
			//垂直对齐居中
			styleArray[s].setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			//水平居中
			styleArray[s].setAlignment(CellStyle.ALIGN_CENTER);
		}
		// 循环对每一个单元格进行赋值
		// 定位行
		int c = 0;
		for (int rowId = 1; rowId <= dataList.size(); rowId++) {


			HSSFCell cell = null;
			int b = 0;

			if (dataList.get(rowId - 1) == null) {
				continue;

			}
			// 创建一个新的rowId行 行对象
			// 新建的行对象
			HSSFRow hssfRow = templateSheet.createRow(rowId);
			//设置行高
			hssfRow.setHeight((short) 400);
			
			
			// 依次取第rowId行数据 每一个数据是valueList
			List<String> valueList = dataList.get(rowId - 1);
			int[] commentIndex = null;
			if (commentIndexList != null) {
				for (; c < commentIndexList.size();) {
					commentIndex = commentIndexList.get(c);
					c++;
					break;
				}
			}
			// 定位列
			for (int columnId = 0; columnId < valueList.size(); columnId++) {
				
				// 依次取出对应与colunmId列的值
				// 每一个单元格的值
				String dataValue = "";
				if (valueList.get(columnId) != null) {

					dataValue = valueList.get(columnId).toString();
					if(dataValue.equals("0.0") || dataValue.equals("0")){
						dataValue = "";
					}
				}
				// 备注存在标示
				boolean commentFlag = false;
				HSSFCellStyle style = null;
				HSSFCell templateCell = null;
				if (commentIndex != null) {
					for (int a = 0; a < commentIndex.length; a++) {
						if (columnId == commentIndex[a]) {
							b = b - 1;
							commentFlag = true;
							break;
						}

					}
					if (commentFlag == false) {
						// 取出colunmId列的的style
						// 模板每一列的样式
						style = styleArray[b];
						// 取模板第colunmId列的单元格对象
						// 模板单元格对象
						templateCell = templateRow.getCell(b);
						// 创建新的rowId行 columnId列 单元格对象
						// 新建的单元格对象
						cell = hssfRow.createCell(b);
						// 如果对应的模板单元格 样式为非锁定
						if (templateCell.getCellStyle().getLocked() == false) {
							// 设置此列style为非锁定
							style.setLocked(false);
							// 设置到新的单元格上
							cell.setCellStyle(style);
						}
						// 否则样式为锁定
						else {
							// 设置此列style为锁定
							style.setLocked(true);
							// 设置到新单元格上
							cell.setCellStyle(style);
						}
					}
				} else {

					// 取出colunmId列的的style
					// 模板每一列的样式
					style = styleArray[columnId];
					// 取模板第colunmId列的单元格对象
					// 模板单元格对象
					templateCell = templateRow.getCell(columnId);
					// 创建新的rowId行 columnId列 单元格对象
					// 新建的单元格对象
					cell = hssfRow.createCell(columnId);
					// 如果对应的模板单元格 样式为非锁定
					if (templateCell.getCellStyle().getLocked() == false) {
						// 设置此列style为非锁定
						style.setLocked(false);
						// 设置到新的单元格上
						cell.setCellStyle(style);
					}
					// 否则样式为锁定
					else {
						// 设置此列style为锁定
						style.setLocked(true);
						// 设置到新单元格上
						cell.setCellStyle(style);
					}

				}

				// Debug.println("dataValue   :   " + dataValue);
				// 设置值 统一为String

				if (commentFlag == true) {
					if (dataValue != null && !"".equals(dataValue)) {

						// 声明一个画图的顶级管理器
						HSSFPatriarch patriarch = templateSheet
								.createDrawingPatriarch();
						HSSFRichTextString richString = new HSSFRichTextString(
								dataValue);
						// 定义注释的大小和位置,详见文档
						HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 255,255, (short) 6, 6, (short) 10, 10));
						// 设置注释内容
						comment.setString(richString);
						cell.setCellComment(comment);

					}

				} else {
					cell.setCellValue(dataValue);
				}
				b++;
			}
		}
		// 将模板的内容写到输出文件上
		templatewb.write(out);
		out.flush();

		// 操作结束，关闭文件
		out.close();

	}
	
	/**
	 *
	 * @Title
	 * @Description 日营收列表页面
	 * @author:kangtianyu
	 * @CreateDate:2016年6月3日 上午09:51:48
	 * @param inputFile
	 * @param out
	 * @param dataList
	 * @param commentIndexList
	 * @param rownum
	 * @param param
	 * @return
	 */
	public static void exportExcelFile(String inputFile, OutputStream out,
			List<List<String>> dataList, ArrayList<int[]> commentIndexList, int rownum, Map<String, String> param)
			throws FileNotFoundException, IOException {
		// 用模板文件构造poi
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(inputFile));
		// 创建模板工作表
		HSSFWorkbook templatewb = new HSSFWorkbook(fs);
		// 直接取模板第一个sheet对象
		HSSFSheet templateSheet = templatewb.getSheetAt(0);
		//宽度自适应
		templateSheet.autoSizeColumn(1, true);

		//字体
		HSSFFont font = templatewb.createFont();
       font.setFontName("宋体");//字体
       font.setFontHeightInPoints((short) 12);//字号
       font.setColor(HSSFColor.BLACK.index);//颜色

		// 得到模板的第一个sheet的第一行对象 为了得到模板样式
		HSSFRow templateRow = templateSheet.getRow(0);
		// 取得Excel文件的总列数
		int columns = templateSheet.getRow(0).getPhysicalNumberOfCells();
		// Debug.println("columns   is   :   " + columns);
		// //=========================
		// 创建样式数组
		HSSFCellStyle styleArray[] = new HSSFCellStyle[columns];

		// 一次性创建所有列的样式放在数组里
		for (int s = 0; s < columns; s++) {
			// 得到数组实例
			styleArray[s] = templatewb.createCellStyle();
			styleArray[s].setFont(font);
			 //下边框
			styleArray[s].setBorderBottom(CellStyle.SOLID_FOREGROUND);
	        //下边框颜色
			styleArray[s].setBottomBorderColor(IndexedColors.BLACK.getIndex());//黑色
	        //左边框
			styleArray[s].setBorderLeft(CellStyle.SOLID_FOREGROUND);
	        //左边框颜色
			styleArray[s].setLeftBorderColor(IndexedColors.BLACK.getIndex());
	        //右边框
			styleArray[s].setBorderRight(CellStyle.SOLID_FOREGROUND);
	        //右边框颜色
			styleArray[s].setRightBorderColor(IndexedColors.BLACK.getIndex());
	        //上边框
			styleArray[s].setBorderTop(CellStyle.SOLID_FOREGROUND);
	        //上边框颜色
			styleArray[s].setTopBorderColor(IndexedColors.BLACK.getIndex());
		}

		if (param != null && !param.isEmpty()) {
			int iRow = 0;
			int iCol = 0;
			// 首先对各位置进行需要手动写入的数据进行赋值
			Iterator<Entry<String, String>> iterator = param.entrySet().iterator();
			while (iterator.hasNext()) {
				HSSFRichTextString val = new HSSFRichTextString("");
				Map.Entry<String, String> entry = iterator.next();
				if ("propointName".equals(entry.getKey().toString())) {
					val = new HSSFRichTextString(entry.getValue().toString());
					iRow = 2;
					iCol = 2;
				}
				if ("year".equals(entry.getKey().toString())) {
					val = new HSSFRichTextString(entry.getValue().toString()+"年");
					iRow = 2;
					iCol = 65;
				}
				if ("month".equals(entry.getKey().toString())) {
					val = new HSSFRichTextString(entry.getValue().toString()+"月");
					iRow = 2;
					iCol = 66;
				}
				setCellValue(templateSheet, iRow, iCol, val);
			}
		}

		// 循环对每一个单元格进行赋值
		// 定位行
		int c = 0;
		for (int rowId = 1; rowId <= dataList.size(); rowId++) {


			HSSFCell cell = null;
			int b = 0;

			if (dataList.get(rowId - 1) == null) {
				continue;

			}
			// 创建一个新的rowId行 行对象
			// 新建的行对象
			HSSFRow hssfRow = templateSheet.createRow(rowId+5);
			//设置行高
			hssfRow.setHeight((short) 400);


			// 依次取第rowId行数据 每一个数据是valueList
			List<String> valueList = dataList.get(rowId - 1);
			int[] commentIndex = null;
			if (commentIndexList != null) {
				for (; c < commentIndexList.size();) {
					commentIndex = commentIndexList.get(c);
					c++;
					break;
				}
			}
			// 定位列
			for (int columnId = 0; columnId < valueList.size(); columnId++) {

				// 依次取出对应与colunmId列的值
				// 每一个单元格的值
				String dataValue = "";
				if (valueList.get(columnId) != null) {

					dataValue = valueList.get(columnId).toString();
				// 863-FZQ修改
				/*	if(dataValue.equals("0.0") || dataValue.equals("0")){
						dataValue = "";
					}*/
				}
				// 备注存在标示
				boolean commentFlag = false;
				HSSFCellStyle style = null;
				HSSFCell templateCell = null;
				if (commentIndex != null) {
					for (int a = 0; a < commentIndex.length; a++) {
						if (columnId == commentIndex[a]) {
							b = b - 1;
							commentFlag = true;
							break;
						}

					}
					if (commentFlag == false) {
						// 取出colunmId列的的style
						// 模板每一列的样式
						style = styleArray[b];
						// 取模板第colunmId列的单元格对象
						// 模板单元格对象
						templateCell = templateRow.getCell(b);
						// 创建新的rowId行 columnId列 单元格对象
						// 新建的单元格对象
						cell = hssfRow.createCell(b);
						// 如果对应的模板单元格 样式为非锁定
						if (templateCell.getCellStyle().getLocked() == false) {
							// 设置此列style为非锁定
							style.setLocked(false);
							// 设置到新的单元格上
							cell.setCellStyle(style);
						}
						// 否则样式为锁定
						else {
							// 设置此列style为锁定
							style.setLocked(true);
							// 设置到新单元格上
							cell.setCellStyle(style);
						}
					}
				} else {

					// 取出colunmId列的的style
					// 模板每一列的样式
					style = styleArray[columnId];
					// 取模板第colunmId列的单元格对象
					// 模板单元格对象
					templateCell = templateRow.getCell(columnId);
					// 创建新的rowId行 columnId列 单元格对象
					// 新建的单元格对象
					cell = hssfRow.createCell(columnId);
					// 如果对应的模板单元格 样式为非锁定
					if (templateCell.getCellStyle().getLocked() == false) {
						// 设置此列style为非锁定
						style.setLocked(false);
						// 设置到新的单元格上
						cell.setCellStyle(style);
					}
					// 否则样式为锁定
					else {
						// 设置此列style为锁定
						style.setLocked(true);
						// 设置到新单元格上
						cell.setCellStyle(style);
					}

				}

				// Debug.println("dataValue   :   " + dataValue);
				// 设置值 统一为String

				if (commentFlag == true) {
					if (dataValue != null && !"".equals(dataValue)) {

						// 声明一个画图的顶级管理器
						HSSFPatriarch patriarch = templateSheet
								.createDrawingPatriarch();
						HSSFRichTextString richString = new HSSFRichTextString(
								dataValue);
						// 定义注释的大小和位置,详见文档
						HSSFComment comment = patriarch
								.createComment(new HSSFClientAnchor(0, 0, 255,
										255, (short) 6, 6, (short) 10, 10));
						// 设置注释内容
						comment.setString(richString);
						cell.setCellComment(comment);

					}

				} else {
					cell.setCellValue(dataValue);
				}
				b++;
			}
		}
		// 将模板的内容写到输出文件上
		templatewb.write(out);
		out.flush();

		// 操作结束，关闭文件
		out.close();

	}
	
	
	/**
	 *
	 * @Title
	 * @Description 毛利报表导出
	 * @author:Administrator
	 * @CreateDate:2016年6月6日 下午2:43:02
	 * @param inputFile
	 * @param out
	 * @param dataList
	 * @param month
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void exportExcelFileToML(String inputFile, OutputStream out,
            List<List<String>> dataList,String month)
            throws FileNotFoundException, IOException {
        // 用模板文件构造poi
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(inputFile));
        // 创建模板工作表
        HSSFWorkbook templatewb = new HSSFWorkbook(fs);
        // 直接取模板第一个sheet对象
        HSSFSheet templateSheet = templatewb.getSheetAt(0);
        //宽度自适应
        templateSheet.autoSizeColumn(1, true); 
        
        //字体
        HSSFFont font = templatewb.createFont();
        font.setFontName("宋体");//字体
        font.setFontHeightInPoints((short) 12);//字号
        font.setColor(HSSFColor.BLACK.index);//颜色
        
        // 得到模板的第一个sheet的第一行对象 为了得到模板样式
        HSSFRow templateRow = templateSheet.getRow(2);
        // 取得Excel文件的总列数
        int columns = templateSheet.getRow(2).getPhysicalNumberOfCells();
        // Debug.println("columns   is   :   " + columns);
        // //=========================
        // 创建样式数组
        HSSFCellStyle styleArray[] = new HSSFCellStyle[columns];

        // 一次性创建所有列的样式放在数组里
        for (int s = 0; s < columns; s++) {
            // 得到数组实例
            styleArray[s] = templatewb.createCellStyle();
            styleArray[s].setFont(font);
             //下边框   
            styleArray[s].setBorderBottom(CellStyle.SOLID_FOREGROUND);  
            //下边框颜色  
            styleArray[s].setBottomBorderColor(IndexedColors.BLACK.getIndex());//黑色  
            //左边框  
            styleArray[s].setBorderLeft(CellStyle.SOLID_FOREGROUND);  
            //左边框颜色  
            styleArray[s].setLeftBorderColor(IndexedColors.BLACK.getIndex());
            //右边框  
            styleArray[s].setBorderRight(CellStyle.SOLID_FOREGROUND);  
            //右边框颜色  
            styleArray[s].setRightBorderColor(IndexedColors.BLACK.getIndex());  
            //上边框  
            styleArray[s].setBorderTop(CellStyle.SOLID_FOREGROUND);
            //上边框颜色  
            styleArray[s].setTopBorderColor(IndexedColors.BLACK.getIndex());
        }
        // 对第二行第二个元素赋值日期
        HSSFRow row2 =templateSheet.getRow(1);
        HSSFCell cell2 = row2.getCell(1);
        cell2.setCellValue(month);
        
        // 循环对每一个单元格进行赋值
        // 定位行
        for (int rowId = 3; rowId <= dataList.size()+2; rowId++) {


            HSSFCell cell = null;

            if (dataList.get(rowId-3) == null) {
                continue;

            }
            // 创建一个新的rowId行 行对象
            // 新建的行对象
            HSSFRow hssfRow = templateSheet.createRow(rowId);
            //设置行高
            hssfRow.setHeight((short) 400);
            
            
            // 依次取第rowId行数据 每一个数据是valueList
            List<String> valueList = dataList.get(rowId-3);
            // 定位列
            for (int columnId = 0; columnId < valueList.size(); columnId++) {
                
                // 依次取出对应与colunmId列的值
                // 每一个单元格的值
                String dataValue = "";
                if (valueList.get(columnId) != null) {
                    dataValue = valueList.get(columnId).toString();
                }
                HSSFCellStyle style = null;
                HSSFCell templateCell = null;
                // 取出colunmId列的的style
                // 模板每一列的样式
                style = styleArray[columnId];
                // 取模板第colunmId列的单元格对象
                // 模板单元格对象
                templateCell = templateRow.getCell(columnId);
                // 创建新的rowId行 columnId列 单元格对象
                // 新建的单元格对象
                cell = hssfRow.createCell(columnId);
                
                // 如果对应的模板单元格 样式为非锁定
                if (templateCell.getCellStyle().getLocked() == false) {
                    // 设置此列style为非锁定
                    style.setLocked(false);
                    // 设置到新的单元格上
                    cell.setCellStyle(style);
                }
                // 否则样式为锁定
                else {
                    // 设置此列style为锁定
                    style.setLocked(true);
                    // 设置到新单元格上
                    cell.setCellStyle(style);
                }
                
                // 设置值 统一为String
                cell.setCellValue(dataValue);
            }
        }
        // 将模板的内容写到输出文件上
        templatewb.write(out);
        out.flush();

        // 操作结束，关闭文件
        out.close();

    }
	
	private static void setCellValue(HSSFSheet sheet, int iRow, int iCol, HSSFRichTextString val){
        HSSFRow row = sheet.getRow((short)iRow);
        HSSFCell cell = row.getCell((short)iCol);
        cell.setCellValue(val);
    }
	
	public static  boolean contains( final int[] array, final int v ) {
	    for ( final int e : array )
	        if ( e == v )
	            return true;

	    return false;
	}
}