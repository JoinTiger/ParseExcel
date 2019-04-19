package com.neo.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;

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

import com.neo.bean.Record;
import com.neo.bean.User;



public class ExcelUtil {

	public static Log log = LogFactory.getLog(ExcelUtil.class);
	
	public static List<User> getUsers(MultipartFile file) throws Exception {
		return getExcelUsers(ExcelToList(file));
	}
	
	
	public static List<Map<String,String>> ExcelToList(MultipartFile file) {
		
		
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		
		String fileName = file.getOriginalFilename();
		String filetype = fileName.substring(fileName.lastIndexOf(".") + 1);
		InputStream fi = null;
		try {
			fi = file.getInputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		//xls文件
		HSSFWorkbook hwookbook = null;
		XSSFWorkbook xwookbook = null;
		
		if("xls".equals(filetype.toLowerCase())){
			try {
				hwookbook = new HSSFWorkbook(fi);
				HSSFSheet sheet = hwookbook.getSheet("Sheet1");
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
			} finally {
				
				try {
					if(fi != null) {
						fi.close();
						fi = null;
					}
					
					if(hwookbook != null) {
						hwookbook.close();
						hwookbook = null;
					}
					
					if(xwookbook != null) {
						xwookbook.close();
						xwookbook = null;
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				

			
		} else if("xlsx".equals(filetype.toLowerCase())){
			//xlsx文件
			try {
				xwookbook = new XSSFWorkbook(fi);
				XSSFSheet sheet = xwookbook.getSheet("Sheet1");
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
			} finally {
				
				try {
					if(fi != null) {
						fi.close();
						fi = null;
					}
					
					if(hwookbook != null) {
						hwookbook.close();
						hwookbook = null;
					}
					
					if(xwookbook != null) {
						xwookbook.close();
						xwookbook = null;
					}
					
				} catch(IOException e) {
					e.printStackTrace();
				}
				
				
			}
		}
		
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
	
	
	public static List<Record> getExcelRecords(List<Map<String,String>> maps) {
		List<Record> ret = new ArrayList<Record>();
		
		String macSn = "";
		String nCNum = "";
		String iPCNum = "";
		String contractNum = "";
		String sVNum = "";
		String motorNum = "";
		
		
		for(Map<String, String> map : maps) {
			
			macSn = "";       
			nCNum = "";       
			iPCNum = "";      
			contractNum = ""; 
			sVNum = "";       
			motorNum = "";    
			
			Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
			while(iterator.hasNext()) {
				Entry<String, String> next = iterator.next();
				
				String key = next.getKey();
				
				switch(key) {
				
				case "系统SN号(MacSn)":
					macSn = next.getValue();
					break;
				case "生产提供的数控装置编号(NCNum)":
					nCNum = next.getValue();
					break;
				case "生产提供的IPC编号(IPCNum)":
					iPCNum = next.getValue();
					break;

					
					
				case "合同编号(ContractNum)":
					contractNum = next.getValue();
					break;
				case "伺服驱动编号(SVNum)":
					sVNum = next.getValue();
					break;
				case "电机编号(MotorNum)":
					motorNum = next.getValue();
					break;
					
				}
				
				

			}
			
			if(macSn == null || nCNum == null || iPCNum == null) continue;
			
//			if(username == null || password == null) continue;
//			if(username.trim().equals("") || password.trim().equals("")) continue;
//			
//			User user = new User();
//			
//			user.setUsername(username);
//			user.setPassword(password);
//			
//			ret.add(user);
			
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


		CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 3);
		sheet.addMergedRegion(rowRegion);


		row = sheet.createRow(1);
		row.setHeight((short) (22.50 * 20));//设置行高
		row.createCell(0).setCellValue("用户Id");//为第一个单元格设值
		row.createCell(1).setCellValue("用户名");//为第二个单元格设值
		row.createCell(2).setCellValue("用户密码");//为第三个单元格设值
		row.createCell(3).setCellValue("导入时间");

		for (int i = 0; i < users.size(); i++) {
			row = sheet.createRow(i + 2);
			User user = users.get(i);
			
			SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			row.createCell(0).setCellValue(user.getId());
			row.createCell(1).setCellValue(user.getUsername());
			row.createCell(2).setCellValue(user.getPassword());
			row.createCell(3).setCellValue(format0.format(user.getRegTime()));
			
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
		
		if(os != null) {
			os.close();
			os = null;
		}
		
		if(wb != null) {
			wb.close();
			wb = null;
		}
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
