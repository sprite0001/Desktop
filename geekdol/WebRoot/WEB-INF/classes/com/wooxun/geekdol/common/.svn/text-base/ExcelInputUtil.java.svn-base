package com.wooxun.geekdol.common;
/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author zhougp	
 * @CreateDate 2016年7月14日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 万通	2016年7月14日  下午4:56:46 		创建
 *==========================================================
 * 
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


@SuppressWarnings("deprecation")
public class ExcelInputUtil {
	/**
	 * 批量导入excel数据
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */

	public static List<String[]> excelInput(String filePath) throws FileNotFoundException, IOException {
		// 创建对Excel工作薄文件的引用
		HSSFWorkbook wookbook = new HSSFWorkbook(new FileInputStream(filePath));
		// 建立新的sheet对象
		HSSFSheet sheet = wookbook.getSheet(wookbook.getSheetName(0));
		// 获取Excel的所有行
		int rows = sheet.getPhysicalNumberOfRows();
		List<String[]> excelDataList = new ArrayList<String[]>();
		// 遍历行
		for (int i = 1; i < rows; i++) {
			String[] val = new String[8];
			// 读取左上角单元格
			HSSFRow row = sheet.getRow(i);
			// 行不能为空
			if (row != null) {
				// 遍历列
				for (int j = 0; j < 8; j++) {
					// 获取列的值
					HSSFCell cell = row.getCell(j);
					if (cell != null) {
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_FORMULA:
							break;
						case HSSFCell.CELL_TYPE_NUMERIC:
							val[j] = cell.getNumericCellValue() + "";
							break;
						case HSSFCell.CELL_TYPE_STRING:
							val[j] = cell.getStringCellValue() + "";
							break;
						default:
							val[j] = "0" + "";
							break;
						}
					}
				}
				// 将数据插入到mysql中
				excelDataList.add(i - 1, val);
			}
		}
		return excelDataList;
	}
	/**
	 * 批量导入excel数据（备注）
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static List<List<String[]>> excelInputComment(String filePath,int cells) throws FileNotFoundException, IOException{
		
		//获得excle文件扩展名最后一位，2003版的扩展名为.xls，2007版的扩展名为.xlsx，通过最后一位进行区分属于哪个版本
		String fileLast = filePath.substring(filePath.length() - 1, filePath.length());
		
		// 创建对Excel工作薄文件的引用
		// XSSF只能读取2007版的excel，HSSF只能读取2003版的excel
		Workbook workbook =null;
		List<List<String[]>> data = new ArrayList<List<String[]>>();
		int rows = 0;
		//如果为x则为2007版
		if("x".equals(fileLast)){
			workbook = new XSSFWorkbook(filePath);
			XSSFSheet sheet = (XSSFSheet) workbook.getSheet(workbook.getSheetName(0));
			rows = sheet.getPhysicalNumberOfRows();
			
			for (int i = 1; i < rows;i++) {
				List<String[]> excelDataList = new ArrayList<String[]>();
				// 读取左上角单元格
				XSSFRow row = sheet.getRow(i);
				// 行不能为空
				if (row != null) {
					String value = "";
					// 遍历列
					for (int j = 0; j < cells; j++) {
						// 获取列的值
						XSSFCell cell = row.getCell(j);
						if (cell != null) {
							switch (cell.getCellType()) {
							case XSSFCell.CELL_TYPE_FORMULA:
								break;
							case XSSFCell.CELL_TYPE_NUMERIC:
								if (cell.getCellComment() != null && !"".equals(cell.getCellComment()))
								{
									value += cell.getCellComment().getString()+",";	
								}else
								{
									value +=""+",";
								}
								value += cell.getNumericCellValue()+ ",";
								break;
							case XSSFCell.CELL_TYPE_STRING:
								if (cell.getCellComment() != null && !"".equals(cell.getCellComment()))
								{
									value += cell.getCellComment().getString()+",";	
								}else
								{
									value +=""+",";
								}
								value += cell.getStringCellValue() + ",";
								break;
							default:
								if (cell.getCellComment() != null && !"".equals(cell.getCellComment()))
								{
									value += cell.getCellComment().getString()+",";	
								}else
								{
									value +=""+",";
								}
								value += "0"+",";
								break;
							}
						}
					}
					// 将数据插入到mysql中
					String[] val = value.split(",");
					excelDataList.add(val);
				}
				data.add(excelDataList);
			}
			return data;
		}else{
			workbook = new HSSFWorkbook(new FileInputStream(filePath));
			HSSFSheet sheet = (HSSFSheet) workbook.getSheet(workbook.getSheetName(0));
			// 获取Excel的所有行
			rows = sheet.getPhysicalNumberOfRows();
			for (int i = 1; i < rows;i++) {
				List<String[]> excelDataList = new ArrayList<String[]>();
				// 读取左上角单元格
				HSSFRow row = sheet.getRow(i);
				// 行不能为空
				if (row != null) {
					String value = "";
					// 遍历列
					for (int j = 0; j < cells; j++) {
						// 获取列的值
						HSSFCell cell = row.getCell(j);
						if (cell != null) {
							switch (cell.getCellType()) {
							case HSSFCell.CELL_TYPE_FORMULA:
								String falue = "";
								try {
									falue = String.valueOf(cell.getStringCellValue());
								} catch (IllegalStateException e) {
									falue = String.valueOf(cell.getNumericCellValue());
								}
								if (cell.getCellComment() != null && !"".equals(cell.getCellComment()))
								{
									value += cell.getCellComment().getString()+",";	
								}else
								{
									value +=""+",";
								}
								value += falue+ ",";
								break;
							case HSSFCell.CELL_TYPE_NUMERIC:
								if (cell.getCellComment() != null && !"".equals(cell.getCellComment()))
								{
									value += cell.getCellComment().getString()+",";	
								}else
								{
									value +=""+",";
								}
								String dateValue =String.valueOf(cell.getNumericCellValue());
								if(HSSFDateUtil.isCellDateFormatted(cell)){
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
									dateValue= sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
								}
								
								value += dateValue+ ",";
								break;
							case HSSFCell.CELL_TYPE_STRING:
								if (cell.getCellComment() != null && !"".equals(cell.getCellComment()))
								{
									value += cell.getCellComment().getString()+",";	
								}else
								{
									value +=""+",";
								}
								value += cell.getStringCellValue() + ",";
								break;
							default:
								if (cell.getCellComment() != null && !"".equals(cell.getCellComment()))
								{
									value += cell.getCellComment().getString()+",";	
								}else
								{
									value +=""+",";
								}
								value += "0"+",";
								break;
							}
						}
					}
					// 将数据插入到mysql中
					String[] val = value.split(",");
					excelDataList.add(val);
				}
				data.add(excelDataList);
			}
			return data;
		}
	}
	
	/**
     * 批量导入excel第二个sheet数据（备注）
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public static List<List<String[]>> excelInputSecondComment(String filePath,int cells) throws FileNotFoundException, IOException{
        
        //获得excle文件扩展名最后一位，2003版的扩展名为.xls，2007版的扩展名为.xlsx，通过最后一位进行区分属于哪个版本
        String fileLast = filePath.substring(filePath.length() - 1, filePath.length());
        
        // 创建对Excel工作薄文件的引用
        // XSSF只能读取2007版的excel，HSSF只能读取2003版的excel
        Workbook workbook =null;
        List<List<String[]>> data = new ArrayList<List<String[]>>();
        int rows = 0;
        //如果为x则为2007版
        if("x".equals(fileLast)){
            workbook = new XSSFWorkbook(filePath);
            XSSFSheet sheet = (XSSFSheet) workbook.getSheet(workbook.getSheetName(1));
            rows = sheet.getPhysicalNumberOfRows();
            
            for (int i = 1; i < rows;i++) {
                List<String[]> excelDataList = new ArrayList<String[]>();
                // 读取左上角单元格
                XSSFRow row = sheet.getRow(i);
                // 行不能为空
                if (row != null) {
                    String value = "";
                    // 遍历列
                    for (int j = 0; j < cells; j++) {
                        // 获取列的值
                        XSSFCell cell = row.getCell(j);
                        if (cell != null) {
                            switch (cell.getCellType()) {
                            case XSSFCell.CELL_TYPE_FORMULA:
                                break;
                            case XSSFCell.CELL_TYPE_NUMERIC:
                                if (cell.getCellComment() != null && !"".equals(cell.getCellComment()))
                                {
                                    value += cell.getCellComment().getString()+","; 
                                }else
                                {
                                    value +=""+",";
                                }
                                value += cell.getNumericCellValue()+ ",";
                                break;
                            case XSSFCell.CELL_TYPE_STRING:
                                if (cell.getCellComment() != null && !"".equals(cell.getCellComment()))
                                {
                                    value += cell.getCellComment().getString()+","; 
                                }else
                                {
                                    value +=""+",";
                                }
                                value += cell.getStringCellValue() + ",";
                                break;
                            default:
                                if (cell.getCellComment() != null && !"".equals(cell.getCellComment()))
                                {
                                    value += cell.getCellComment().getString()+","; 
                                }else
                                {
                                    value +=""+",";
                                }
                                value += "0"+",";
                                break;
                            }
                        }
                    }
                    // 将数据插入到mysql中
                    String[] val = value.split(",");
                    excelDataList.add(val);
                }
                data.add(excelDataList);
            }
            return data;
        }else{
            workbook = new HSSFWorkbook(new FileInputStream(filePath));
            HSSFSheet sheet = (HSSFSheet) workbook.getSheet(workbook.getSheetName(1));
            // 获取Excel的所有行
            rows = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < rows;i++) {
                List<String[]> excelDataList = new ArrayList<String[]>();
                // 读取左上角单元格
                HSSFRow row = sheet.getRow(i);
                // 行不能为空
                if (row != null) {
                    String value = "";
                    // 遍历列
                    for (int j = 0; j < cells; j++) {
                        // 获取列的值
                        HSSFCell cell = row.getCell(j);
                        if (cell != null) {
                            switch (cell.getCellType()) {
                            case HSSFCell.CELL_TYPE_FORMULA:
                                String falue = "";
                                try {
                                    falue = String.valueOf(cell.getStringCellValue());
                                } catch (IllegalStateException e) {
                                    falue = String.valueOf(cell.getNumericCellValue());
                                }
                                if (cell.getCellComment() != null && !"".equals(cell.getCellComment()))
                                {
                                    value += cell.getCellComment().getString()+","; 
                                }else
                                {
                                    value +=""+",";
                                }
                                value += falue+ ",";
                                break;
                            case HSSFCell.CELL_TYPE_NUMERIC:
                                if (cell.getCellComment() != null && !"".equals(cell.getCellComment()))
                                {
                                    value += cell.getCellComment().getString()+","; 
                                }else
                                {
                                    value +=""+",";
                                }
                                String dateValue =String.valueOf(cell.getNumericCellValue());
                                if(HSSFDateUtil.isCellDateFormatted(cell)){
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                                    dateValue= sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                                }
                                
                                value += dateValue+ ",";
                                break;
                            case HSSFCell.CELL_TYPE_STRING:
                                if (cell.getCellComment() != null && !"".equals(cell.getCellComment()))
                                {
                                    value += cell.getCellComment().getString()+","; 
                                }else
                                {
                                    value +=""+",";
                                }
                                value += cell.getStringCellValue() + ",";
                                break;
                            default:
                                if (cell.getCellComment() != null && !"".equals(cell.getCellComment()))
                                {
                                    value += cell.getCellComment().getString()+","; 
                                }else
                                {
                                    value +=""+",";
                                }
                                value += "0"+",";
                                break;
                            }
                        }
                    }
                    // 将数据插入到mysql中
                    String[] val = value.split(",");
                    excelDataList.add(val);
                }
                data.add(excelDataList);
            }
            return data;
        }
    }
	
	
	
	
	/**
	 * 批量导入excel数据（备注）
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static List<List<String[]>> excelInputComment(String filePath,int cells,int sheetCount) throws FileNotFoundException, IOException{
		
		//获得excle文件扩展名最后一位，2003版的扩展名为.xls，2007版的扩展名为.xlsx，通过最后一位进行区分属于哪个版本
		String fileLast = filePath.substring(filePath.length() - 1, filePath.length());
		
		// 创建对Excel工作薄文件的引用
		// XSSF只能读取2007版的excel，HSSF只能读取2003版的excel
		Workbook workbook =null;
		List<List<String[]>> data = new ArrayList<List<String[]>>();
		int rows = 0;
		//如果为x则为2007版
		if("x".equals(fileLast)){
			workbook = new XSSFWorkbook(new FileInputStream(filePath));
			XSSFSheet sheet = (XSSFSheet) workbook.getSheet(workbook.getSheetName(sheetCount));
			rows = sheet.getPhysicalNumberOfRows();
			
			for (int i = 1; i < rows;i++) {
				List<String[]> excelDataList = new ArrayList<String[]>();
				// 读取左上角单元格
				XSSFRow row = sheet.getRow(i);
				// 行不能为空
				if (row != null) {
					String value = "";
					// 遍历列
					for (int j = 0; j < cells; j++) {
						// 获取列的值
						XSSFCell cell = row.getCell(j);
						if (cell != null) {
							switch (cell.getCellType()) {
							case XSSFCell.CELL_TYPE_FORMULA:
								String falue = "";
								try {
									falue = String.valueOf(cell.getStringCellValue());
								} catch (IllegalStateException e) {
									falue = String.valueOf(cell.getNumericCellValue());
								}
								if (cell.getCellComment() != null && !"".equals(cell.getCellComment()))
								{
									value += cell.getCellComment().getString().toString().replaceAll(",", "，")+",";	
								}else
								{
									value +=""+",";
								}
								value += falue+ ",";
								break;
							case XSSFCell.CELL_TYPE_NUMERIC:
								if (cell.getCellComment() != null && !"".equals(cell.getCellComment()))
								{
									value += cell.getCellComment().getString().toString().replaceAll(",", "，")+",";	
								}else
								{
									value +=""+",";
								}
								String dateValue =String.valueOf(cell.getNumericCellValue());
								if(HSSFDateUtil.isCellDateFormatted(cell)){
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
									dateValue= sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
								}
								
								value += dateValue+ ",";
								
								break;
							case XSSFCell.CELL_TYPE_STRING:
								if (cell.getCellComment() != null && !"".equals(cell.getCellComment()))
								{
									value += cell.getCellComment().getString().toString().replaceAll(",", "，")+",";	
								}else
								{
									value +=""+",";
								}
								value += cell.getStringCellValue() + ",";
								break;
							default:
								if (cell.getCellComment() != null && !"".equals(cell.getCellComment()))
								{
									value += cell.getCellComment().getString().toString().replaceAll(",", "，")+",";	
								}else
								{
									value +=""+",";
								}
								value += "0"+",";
								break;
							}
						}
					}
					// 将数据插入到mysql中
					String[] val = value.split(",");
					excelDataList.add(val);
				}
				data.add(excelDataList);
			}
			return data;
		}else{
			workbook = new HSSFWorkbook(new FileInputStream(filePath));
			HSSFSheet sheet = (HSSFSheet) workbook.getSheet(workbook.getSheetName(sheetCount));
			// 获取Excel的所有行
			rows = sheet.getPhysicalNumberOfRows();
			for (int i = 1; i < rows;i++) {
				List<String[]> excelDataList = new ArrayList<String[]>();
				// 读取左上角单元格
				HSSFRow row = sheet.getRow(i);
				// 行不能为空
				if (row != null) {
					String value = "";
					// 遍历列
					for (int j = 0; j < cells; j++) {
						// 获取列的值
						HSSFCell cell = row.getCell(j);
						if (cell != null) {
							switch (cell.getCellType()) {
							case HSSFCell.CELL_TYPE_FORMULA:
								String falue = "";
								try {
									falue = String.valueOf(cell.getStringCellValue());
								} catch (IllegalStateException e) {
									falue = String.valueOf(cell.getNumericCellValue());
								}
								if (cell.getCellComment() != null && !"".equals(cell.getCellComment()))
								{
									value += cell.getCellComment().getString().toString().replaceAll(",", "，")+",";	
								}else
								{
									value +=""+",";
								}
								value += falue+ ",";
								break;
							case HSSFCell.CELL_TYPE_NUMERIC:
								if (cell.getCellComment() != null && !"".equals(cell.getCellComment()))
								{
									value += cell.getCellComment().getString().toString().replaceAll(",", "，")+",";	
								}else
								{
									value +=""+",";
								}
								String dateValue =String.valueOf(cell.getNumericCellValue());
								if(HSSFDateUtil.isCellDateFormatted(cell)){
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
									dateValue= sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
								}
								
								value += dateValue+ ",";
								break;
							case HSSFCell.CELL_TYPE_STRING:
								if (cell.getCellComment() != null && !"".equals(cell.getCellComment()))
								{
									value += cell.getCellComment().getString().toString().replaceAll(",", "，")+",";	
								}else
								{
									value +=""+",";
								}
								value += cell.getStringCellValue() + ",";
								break;
							default:
								if (cell.getCellComment() != null && !"".equals(cell.getCellComment()))
								{
									value += cell.getCellComment().getString().toString().replaceAll(",", "，")+",";	
								}else
								{
									value +=""+",";
								}
								value += "0"+",";
								break;
							}
						}
					}
					// 将数据插入到mysql中
					String[] val = value.split(",");
					excelDataList.add(val);
				}
				data.add(excelDataList);
			}
			return data;
		}
	}
}