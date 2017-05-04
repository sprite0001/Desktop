package com.wooxun.geekdol.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.antlr.grammar.v3.ANTLRv3Parser.finallyClause_return;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import com.wooxun.geekdol.system.model.Community;
import com.wooxun.geekdol.system.model.Village;


/**
 * @Title
 * @Description
 * @version 1.0.0
 * @Author kangtianyu	
 * @CreateDate 2016年9月27日 
 * @param 
 * @see 
 * @modified 修改履历
 *==========================================================
 * No    修改人员			修改日期  	   			描述
 * 1. 	 kangtianyu	2016年9月27日  上午9:29:45 		创建
 *==========================================================
 * 
 */
public class TestExcelInputJdbc {
	
	private static final String URL = "jdbc:mysql://172.16.16.28:3306/geekdol?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true";

	public static void main(String[] args) {
		
		// 创建小区列表用于存放excel小区数据
		List<Village> villageList = new ArrayList<Village>();
		
		// 创建文件输入流用于获取文件信息
		FileInputStream fileIn = null;
		
		// 根据指定的文件输入流导入Excel从而产生Workbook对象
	    HSSFWorkbook wb = null;
		
		// 创建jdbc连接对象
		Connection conn = null;
		
		// 创建mysql连接
		String url = URL;
		
		// 创建用户名变量
		String user = "root";
		
		// 创建密码变量
		String password = "123456";
		
		try {
			// 读取文件(由于仅用于读取一次excel,所以不设置excel文件路径为静态常量常驻内存)
			fileIn = new FileInputStream("C:\\temp\\village.xls");
			// 将excel文件流转化为poi对象
		    wb = new HSSFWorkbook(fileIn);
		    // 获取excel文档中的第一个表单
		    HSSFSheet sht = wb.getSheetAt(0);
		    
		    // 动态加载mysql驱动(由于仅用于读取一次excel,所以不设置驱动为静态常量常驻内存)
            Class.forName("com.mysql.jdbc.Driver");
 
            System.out.println("成功加载MySQL驱动程序");
            
            // 获取数据库连接
            conn = DriverManager.getConnection(url, user, password);
            
            // 创建预编译对象
            PreparedStatement pstmt = null;
			
		    // 对Sheet中的每一行进行迭代
		    for (Row r : sht) {
		    	
		    	// 如果是第一行则跳过(第一行为信息展示)
		    	if (r.getRowNum() == 0) {
		    		continue;
		    	}
		    	
		    	// 获取小区名字
		    	String villageName = r.getCell(0).getStringCellValue();
		    	// 获取小区均价
		    	String excelCellString = r.getCell(1).getStringCellValue();
		    	String averagePrice = null;
		    	if (excelCellString.contains("均价")) {
		    		averagePrice = excelCellString.substring(2, excelCellString.indexOf("元"));
		    	} else {
		    		averagePrice = excelCellString.substring(0, excelCellString.indexOf("元"));
		    	}
		    	if (averagePrice.contains("万")) {
		    		averagePrice = averagePrice.substring(0, averagePrice.indexOf("万"));
		    	}
		    	// 获取小区省id
		    	Long provinceId = (long)(r.getCell(2).getNumericCellValue());
		    	// 获取小区市id
		    	Long cityId = (long)(r.getCell(3).getNumericCellValue());
		    	// 获取小区行政区id 开始
		    	Long countyId = 0l;
		    	String countyName = r.getCell(4).getStringCellValue();
		    	String countySql = "SELECT county_id FROM s_county WHERE province_id = ? AND city_id = ? AND county_name LIKE CONCAT(CONCAT('%', ?),'%') AND del_flag = '0'";
		    	pstmt = conn.prepareStatement(countySql);
		    	pstmt.setLong(1, provinceId);
		    	pstmt.setLong(2, cityId);
		    	pstmt.setString(3, countyName);
		    	ResultSet rsCounty = pstmt.executeQuery();
		    	while (rsCounty.next()) {
		    		// 从结果集中获取county_id存放于result中
		    		countyId = rsCounty.getLong("county_id");
				}
		    	closeResultSet(rsCounty);
		    	if (countyId == 0) {
		    		System.out.println("找不到对应的行政区:行政区名---->" + countyName);
		    		continue;
		    	}
		    	// 获取小区行政区id 结束
		    	// 获取小区社区id 开始
		    	Long communityId = 0l;
		    	String communityName = r.getCell(5).getStringCellValue();
		    	String communitySql = "SELECT community_id FROM s_community WHERE province_id = ? AND city_id = ? AND county_id = ? AND community_name = ? AND del_flag = '0'";
		    	pstmt = conn.prepareStatement(communitySql);
		    	pstmt.setLong(1, provinceId);
		    	pstmt.setLong(2, cityId);
		    	pstmt.setLong(3, countyId);
		    	pstmt.setString(4, communityName);
		    	ResultSet rsCommunity = pstmt.executeQuery();
		    	while (rsCommunity.next()) {
		    		// 从结果集中获取community_id存放于result中
		    		communityId = rsCommunity.getLong("community_id");
				}
		    	closeResultSet(rsCommunity);
		    	if (communityId == 0) {
		    		System.out.println("找不到对应的社区:社区名---->" + communityName);
		    		continue;
		    	}
		    	closePrepStmt(pstmt);
		    	// 获取小区社区id 结束
		    	// 获取小区地址
		    	String villageAddress = r.getCell(6).getStringCellValue();
		    	// 获取小区建成年份
		    	String buildYear = DateFormatUtils.format(r.getCell(7).getDateCellValue(), "yyyy-MM-dd");
		    	// 获取小区总户数
		    	String houseHoldsString = "";
		    	if (r.getCell(8) != null) {
		    		houseHoldsString = r.getCell(8).getStringCellValue();
				}
		    	Long houseHolds = 0l;
		    	if (StringUtils.isNotBlank(houseHoldsString)) {
		    		if (houseHoldsString.contains("户")) {
		    			houseHolds = Long.valueOf(houseHoldsString.substring(0, houseHoldsString.indexOf("户")));
		    		} else if (houseHoldsString.contains("暂无资料")) {
		    			houseHolds = 0l;
					} else {
						houseHolds = Long.valueOf(houseHoldsString);
					}
		    	}
		    	if (houseHolds == 0) {
		    		houseHolds = 2000l;
				}
		    	// 获取小区精度
		    	String villageLongitude = r.getCell(9).getStringCellValue();
		    	// 获取小区纬度
		    	String villageLatitude = r.getCell(10).getStringCellValue();
		    	
		    	
		    	// 创建小区实体类对象
		    	Village village = new Village();
		    	
		    	// 设置小区名字
		    	village.setVillageName(villageName);
		    	// 设置小区均价
		    	village.setAveragePrice(Long.valueOf(averagePrice.trim()));
		    	// 设置小区省id
		    	village.setProvinceId(provinceId);
		    	// 设置小区市id
		    	village.setCityId(cityId);
		    	// 设置小区行政区id
		    	village.setCountyId(countyId);
		    	// 设置小区社区id
		    	village.setCommunityId(communityId);
		    	// 设置小区地址
		    	village.setVillageAddress(villageAddress);
		    	// 设置小区建成年份
		    	village.setBuildYear(buildYear);
		    	// 设置小区总户数
		    	village.setHouseHolds(Long.valueOf(houseHolds));
		    	// 设置小区精度
		    	village.setVillageLongitude(villageLongitude);
		    	// 设置小区纬度
		    	village.setVillageLatitude(villageLatitude);
		    	// 设置小区共通参数
		    	addAttr(village);
		    	
		    	// 向小区列表中添加数据
		    	villageList.add(village);
		    }
		    
		    // 小区批量插入语句
	    	String villageInsertSql = "INSERT INTO s_village (province_id, city_id, county_id, community_id, "
	    			+ "house_holds, build_year, average_price, village_name, village_address, village_longitude, "
	    			+ "village_latitude, status, ins_id, ins_ymdhms, upd_id, upd_ymdhms, upd_eac, del_flag) "
	    			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    	
	    	// 设置数据库连接不进行自动提交
	    	conn.setAutoCommit(false);
	    	// 创建批量插入的预编译
	    	PreparedStatement psPreparedStatement = conn.prepareStatement(villageInsertSql);
	    	// 遍历小区列表
	    	for (Village v: villageList) {
	    		psPreparedStatement.setLong(1, v.getProvinceId());
	    		psPreparedStatement.setLong(2, v.getCityId());
	    		psPreparedStatement.setLong(3, v.getCountyId());
	    		psPreparedStatement.setLong(4, v.getCommunityId());
	    		psPreparedStatement.setLong(5, v.getHouseHolds());
	    		psPreparedStatement.setString(6, v.getBuildYear());
	    		psPreparedStatement.setLong(7, v.getAveragePrice());
	    		psPreparedStatement.setString(8, v.getVillageName());
	    		psPreparedStatement.setString(9, v.getVillageAddress());
	    		psPreparedStatement.setString(10, v.getVillageLongitude());
	    		psPreparedStatement.setString(11, v.getVillageLatitude());
	    		psPreparedStatement.setString(12, v.getStatus());
	    		psPreparedStatement.setLong(13, v.getInsId());
	    		psPreparedStatement.setTimestamp(14, new java.sql.Timestamp(v.getInsYmdhms().getTime()));
	    		psPreparedStatement.setLong(15, v.getUpdId());
	    		psPreparedStatement.setTimestamp(16, new java.sql.Timestamp(v.getUpdYmdhms().getTime()));
	    		psPreparedStatement.setLong(17, v.getUpdEac());
	    		psPreparedStatement.setString(18, v.getDelFlag());
	    		psPreparedStatement.addBatch(); 
	    	}
	    	// 执行批量
	    	psPreparedStatement.executeBatch();
	    	// 提交
	        conn.commit();
	    	closePrepStmt(psPreparedStatement);
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	closeConnection(conn);
        }

	}
	
	/*public static void main(String[] args) {
		// 创建社区列表用于存放excel社区数据
		List<Community> communityList = new ArrayList<Community>();
		
		// 创建文件输入流用于获取文件信息
		FileInputStream fileIn = null;
		
		// 根据指定的文件输入流导入Excel从而产生Workbook对象
	    HSSFWorkbook wb = null;
		
		// 创建jdbc连接对象
		Connection conn = null;
		
		// 创建mysql连接(由于仅用于读取一次excel,所以不设置url为静态常量常驻内存)
		String url = URL;
		
		// 创建用户名变量
		String user = "root";
		
		// 创建密码变量
		String password = "123456";
		
		try {
			// 读取文件(由于仅用于读取一次excel,所以不设置excel文件路径为静态常量常驻内存)
			fileIn = new FileInputStream("C:\\temp\\community.xls");
			// 将excel文件流转化为poi对象
		    wb = new HSSFWorkbook(fileIn);
		    // 获取excel文档中的第一个表单
		    HSSFSheet sht = wb.getSheetAt(0);
		    
		    // 动态加载mysql驱动(由于仅用于读取一次excel,所以不设置驱动为静态常量常驻内存)
            Class.forName("com.mysql.jdbc.Driver");
 
            System.out.println("成功加载MySQL驱动程序");
            
            // 获取数据库连接
            conn = DriverManager.getConnection(url, user, password);
            
            // 创建预编译对象
            PreparedStatement pstmt = null;
			
		    // 对Sheet中的每一行进行迭代
		    for (Row r : sht) {
		    	
		    	// 如果是第一行则跳过(第一行为信息展示)
		    	if (r.getRowNum() == 0) {
		    		continue;
		    	}
		    	String countyName = r.getCell(0).getStringCellValue().trim();
		    	String communityName = r.getCell(1).getStringCellValue();
		    	
		    	Long countyId = 0l;
		    	String countySql = "SELECT county_id FROM s_county WHERE province_id = ? AND city_id = ? AND county_name LIKE CONCAT(CONCAT('%', ?),'%') AND del_flag = '0'";
		    	pstmt = conn.prepareStatement(countySql);
		    	pstmt.setLong(1, 1l);
		    	pstmt.setLong(2, 2l);
		    	pstmt.setString(3, countyName);
		    	ResultSet rsProvince = pstmt.executeQuery();
		    	while (rsProvince.next()) {
		    		// 从结果集中获取county_id存放于result中
		    		countyId = rsProvince.getLong("county_id");
				}
		    	closeResultSet(rsProvince);
		    	if (countyId == 0) {
		    		System.out.println("找不到对应的行政区-----名字:" + countyName);
		    		continue;
		    	}
		    	
		    	
		    	// 创建社区实体类对象
		    	Community community = new Community();
		    	
		    	community.setCommunityName(communityName);
		    	community.setProvinceId(1l);
		    	community.setCityId(2l);
		    	community.setCountyId(countyId);
		    	community.setCommunityCode(PinYin2Abbreviation.cn2py(communityName).toUpperCase());
		    	addAttr(community);
		    	
		    	// 向社区列表中添加数据
		    	communityList.add(community);
		    }
		    
		    // 社区批量插入语句
	    	String communityInsertSql = "INSERT INTO s_community (province_id, city_id, county_id, community_name, "
	    			+ "community_code, status, ins_id, ins_ymdhms, upd_id, upd_ymdhms, upd_eac, del_flag) "
	    			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    	
	    	// 设置数据库连接不进行自动提交
	    	conn.setAutoCommit(false);
	    	// 创建批量插入的预编译
	    	PreparedStatement psPreparedStatement = conn.prepareStatement(communityInsertSql);
	    	// 遍历社区列表
	    	for (Community c: communityList) {
	    		psPreparedStatement.setLong(1, c.getProvinceId());
	    		psPreparedStatement.setLong(2, c.getCityId());
	    		psPreparedStatement.setLong(3, c.getCountyId());
	    		psPreparedStatement.setString(4, c.getCommunityName());
	    		psPreparedStatement.setString(5, c.getCommunityCode());
	    		psPreparedStatement.setString(6, c.getStatus());
	    		psPreparedStatement.setLong(7, c.getInsId());
	    		psPreparedStatement.setTimestamp(8, new java.sql.Timestamp(c.getInsYmdhms().getTime()));
	    		psPreparedStatement.setLong(9, c.getUpdId());
	    		psPreparedStatement.setTimestamp(10, new java.sql.Timestamp(c.getUpdYmdhms().getTime()));
	    		psPreparedStatement.setLong(11, c.getUpdEac());
	    		psPreparedStatement.setString(12, c.getDelFlag());
	    		psPreparedStatement.addBatch(); 
	    	}
	    	// 执行批量
	    	psPreparedStatement.executeBatch();
	    	// 提交
	        conn.commit();
	    	closePrepStmt(psPreparedStatement);
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	closeConnection(conn);
        }
	}*/
	
	/**
	 * 关闭数据库连接
	 * @param con
	 */
	public static void closeConnection(Connection con) {
		try {
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭PreparedStatement
	 * @param prepStmt
	 */
	public static void closePrepStmt(PreparedStatement prepStmt) {
		try {
			if (prepStmt != null)
				prepStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭Statement
	 * @param stmt
	 */
	public static void closeStmt(Statement stmt) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭ResultSet结果集
	 * @param rs
	 */
	public static void closeResultSet(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Title
	 * @Description 设置小区实体类共通属性
	 * @author:kangtianyu
	 * @CreateDate:2016年9月27日 上午10:24:34
	 * @param _temp
	 */
	private static void addAttr(Village _temp) {
		_temp.setStatus(ConstantStr.QY_FLAG);
		_temp.setInsYmdhms(new Date());
		_temp.setInsId(1l);
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(1l);
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}
	
	/**
	 * @Title
	 * @Description 设置社区实体类共通属性
	 * @author:kangtianyu
	 * @CreateDate:2016年9月27日 上午10:24:34
	 * @param _temp
	 */
	private static void addAttr(Community _temp) {
		_temp.setStatus(ConstantStr.QY_FLAG);
		_temp.setInsYmdhms(new Date());
		_temp.setInsId(1l);
		_temp.setUpdEac(0L);
		_temp.setUpdYmdhms(new Date());
		_temp.setUpdId(1l);
		_temp.setDelFlag(ConstantStr.DELETE_N);
	}

}
