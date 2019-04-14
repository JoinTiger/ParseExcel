package com.neo.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
