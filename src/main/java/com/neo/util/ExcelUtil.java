package com.neo.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.neo.bean.User;



public class ExcelUtil {

	public static Log log = LogFactory.getLog(ExcelUtil.class);
	
	public static List<User> getUsers(MultipartFile file) throws Exception {
		return getExcelUsers(ExcelToList(file));
	}
	
	
	public static List<Map<String,String>> ExcelToList(MultipartFile file) throws Exception{
		
		
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		
		String fileName = file.getOriginalFilename();
		String filetype = fileName.substring(fileName.lastIndexOf(".") + 1);
		InputStream fi = file.getInputStream();
		
		if(fi == null)
			throw new Exception("文件不存在");
		//xls文件
		if("xls".equals(filetype.toLowerCase())){
			try {
				HSSFWorkbook wookbook = new HSSFWorkbook(fi);
				HSSFSheet sheet = wookbook.getSheet("Sheet1");
				int rows = sheet.getPhysicalNumberOfRows();
				
				//获取标题行
				HSSFRow title = sheet.getRow(0);
				log.info(title.getLastCellNum());
				int index = title.getFirstCellNum();
				int rowcount = title.getLastCellNum();
				for (int i = 1; i < rows; i++){
					
					HSSFRow row = sheet.getRow(i);
					if(isBlankRow(row, index, rowcount))
						continue;
					if (row != null){
						Map<String,String> map = new TreeMap<String,String>();
						int cells = title.getPhysicalNumberOfCells();
						
						for (int j = 0; j < cells; j++){
							String value = "";
							HSSFCell cell = row.getCell(j);
							if (cell != null){
								switch (cell.getCellType()){
									case HSSFCell.CELL_TYPE_FORMULA:
										break;
									case HSSFCell.CELL_TYPE_NUMERIC:
										cell.setCellType(HSSFCell.CELL_TYPE_STRING);
										value += cell.getStringCellValue().trim();
										break;
									case HSSFCell.CELL_TYPE_STRING:
										value += cell.getStringCellValue().trim();
										break;
									default:
										value = "";
										break;
								}
							}
							//String key = title.getCell(j).getStringCellValue().trim();
							map.put(title.getCell(j).getStringCellValue().trim(), value);
						}
						
						mapList.add(map);
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception e){
				e.printStackTrace();
			}
		}else if("xlsx".equals(filetype.toLowerCase())){
			//xlsx文件
			try {
				XSSFWorkbook wookbook = new XSSFWorkbook(fi);
				XSSFSheet sheet = wookbook.getSheet("Sheet1");
				int rows = sheet.getPhysicalNumberOfRows();
				//获取标题行
				XSSFRow title = sheet.getRow(0);
				int index = title.getFirstCellNum();
				int rowcount = title.getLastCellNum();
				for (int i = 1; i < rows; i++){
					
					XSSFRow row = sheet.getRow(i);
					if(isBlankRow(row, index, rowcount))
						continue;
					if (row != null){
						Map<String,String> map = new TreeMap<String,String>();
						int cells = title.getPhysicalNumberOfCells();
						
						for (int j = 0; j < cells; j++){
							String value = "";
							XSSFCell cell = row.getCell(j);
							if (cell != null){
								switch (cell.getCellType()){
									case HSSFCell.CELL_TYPE_FORMULA:
										break;
									case HSSFCell.CELL_TYPE_NUMERIC:
										cell.setCellType(HSSFCell.CELL_TYPE_STRING);
										value += cell.getStringCellValue().trim();
										break;
									case HSSFCell.CELL_TYPE_STRING:
										value += cell.getStringCellValue().trim();
										break;
									default:
										value = "";
										break;
								}
							}
							map.put(title.getCell(j).getStringCellValue().trim(), value);
						}
						
						mapList.add(map);
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		if(fi != null)
			fi.close();
		
		return mapList;
	}
	
	public static List<User> getExcelUsers(List<Map<String,String>> maps) {
		List<User> ret = new ArrayList<User>();
		
		String username = "";
		String password = "";
		
		
		for(Map<String, String> map : maps) {
			
			username = "";
			password = "";
			
			Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
			while(iterator.hasNext()) {
				Entry<String, String> next = iterator.next();
				
				String key = next.getKey();
				
				switch(key) {
				case "姓名(必填)":
					username = next.getValue();
					break;
				case "密码":
					password = next.getValue();
					break;
				}

			}
			
			if(username == null || password == null) continue;
			if(username.trim().equals("") || password.trim().equals("")) continue;
			
			User user = new User();
			
			user.setUsername(username);
			user.setPassword(password);
			
			ret.add(user);
			
		}
		
		
		return ret;
	}
	
	
	public static void dataToExcel(List<User> users, HttpServletResponse response) throws IOException {
	
		
		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFSheet sheet = wb.createSheet("获取excel测试表格");

		HSSFRow row = null;

		row = sheet.createRow(0);//创建第一个单元格
		row.setHeight((short) (26.25 * 20));
		row.createCell(0).setCellValue("用户信息列表");//为第一行单元格设值

		/*为标题设计空间
		* firstRow从第1行开始
		* lastRow从第0行结束
		*
		*从第1个单元格开始
		* 从第3个单元格结束
		*/
		CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 3);
		sheet.addMergedRegion(rowRegion);

		/*CellRangeAddress columnRegion = new CellRangeAddress(1,4,0,0);
		sheet.addMergedRegion(columnRegion);*/


		/*
		* 动态获取数据库列 sql语句 select COLUMN_NAME from INFORMATION_SCHEMA.Columns where table_name='user' and table_schema='test'
		* 第一个table_name 表名字
		* 第二个table_name 数据库名称
		* */
		row = sheet.createRow(1);
		row.setHeight((short) (22.50 * 20));//设置行高
		row.createCell(0).setCellValue("用户Id");//为第一个单元格设值
		row.createCell(1).setCellValue("用户名");//为第二个单元格设值
		row.createCell(2).setCellValue("用户密码");//为第三个单元格设值
		row.createCell(3).setCellValue("导入时间");

		for (int i = 0; i < users.size(); i++) {
			row = sheet.createRow(i + 2);
			User user = users.get(i);
			row.createCell(0).setCellValue(user.getId());
			row.createCell(1).setCellValue(user.getUsername());
			row.createCell(2).setCellValue(user.getPassword());
			row.createCell(3).setCellValue(user.getRegTime());
		}
		sheet.setDefaultRowHeight((short) (16.5 * 20));
		//列宽自适应
		for (int i = 0; i <= 13; i++) {
			sheet.autoSizeColumn(i);
		}

		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		OutputStream os = response.getOutputStream();
		response.setHeader("Content-disposition", "attachment;filename=data.xls");//默认Excel名称
		wb.write(os);
		os.flush();
		os.close();
	}
	
 	public static boolean isBlankRow(HSSFRow row, int index, int rowCount){
		if(row == null)
			return true;
		for(int i=index; i < rowCount; i++){
			if(row.getCell(i) != null && 
					!"".equals(row.getCell(i).getStringCellValue().trim())){
				return false;
			}
		}
		return true;
	}
	
	public static boolean isBlankRow(XSSFRow row, int index, int rowCount){
		if(row == null)
			return true;
		for(int i=index; i < rowCount; i++){
			if(row.getCell(i) != null || 
					!"".equals(row.getCell(i).getStringCellValue().trim())){
				return false;
			}
		}
		return true;
	}
}
