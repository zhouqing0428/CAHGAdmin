package io.renren.utils;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * 读取EXCEL数据
 * 创建人：
 * 创建时间：
 * @version
 */
public class ObjectExcelRead {

	/**  03 或03以前版本
	 * @param filepath //文件路径
	 * @param filename //文件名
	 * @param startrow //开始行号
	 * @param startcol //开始列号
	 * @param sheetnum //sheet
	 * @return list
	 */
	public static List<Object> readExcelXls(String filepath, String filename, int startrow, int startcol, int sheetnum) {
		List<Object> varList = new ArrayList<Object>();

		try {
			File target = new File(filepath, filename);
			FileInputStream fi = new FileInputStream(target);
			HSSFWorkbook wb = new HSSFWorkbook(fi);
			HSSFSheet sheet = wb.getSheetAt(sheetnum); 					//sheet 从0开始
			int rowNum = sheet.getLastRowNum() + 1; 					//取得最后一行的行号
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			for (int i = startrow; i < rowNum; i++) {					//行循环开始
			    HashMap varpd = new HashMap();
			    HSSFRow row = sheet.getRow(i); 							//行
				int cellNum = row.getLastCellNum(); 					//每行的最后一个单元格位置
				if(cellNum<6){
					break;
				}

				for (int j = startcol; j < cellNum; j++) {				//列循环开始
					if(j==1||j==3||j==7){
						continue;
					}
				    
					HSSFCell cell = row.getCell(Short.parseShort(j + ""));
					//System.out.println("cell.val="+cell.getStringCellValue());
					String cellValue = null;
					if (null != cell) {
						if(j==0){   
							try {
								Date date=cell.getDateCellValue();  //日期类型
								String val=sdf.format(date);
								varpd.put("var"+j, val);
								continue;
							} catch (Exception e) {
								// TODO: handle exception
								varpd.put("var"+j, "");  //其他字符串 让它为空
							}
						}
					  // System.out.println("j="+j);
					   switch (cell.getCellType()) { 
						case 0:  // 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
							//cellValue = String.valueOf((int) cell.getNumericCellValue());
							cellValue = String.valueOf((long) cell.getNumericCellValue());
							break;
						case 1:
							cellValue = cell.getStringCellValue();
							break;
						case 2:
							cellValue = cell.getNumericCellValue() + "";
							// cellValue = String.valueOf(cell.getDateCellValue());
							break;
						case 3:
							cellValue = "";
							break;
						case 4:
							cellValue = String.valueOf(cell.getBooleanCellValue());
							break;
						case 5:
							cellValue = String.valueOf(cell.getErrorCellValue());
							break;
						}
					} else {
						cellValue = "";
					}
					
					varpd.put("var"+j, cellValue);
					
				}
				varList.add(varpd);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
		return varList;
	}
	
	/**  07 或07以上以前版本
	 * @param filepath //文件路径
	 * @param filename //文件名
	 * @param startrow //开始行号
	 * @param startcol //开始列号
	 * @param sheetnum //sheet
	 * @return list
	 */
	public static List<Object> readExcelXlsx(String filepath, String filename, int startrow, int startcol, int sheetnum) {
		List<Object> varList = new ArrayList<Object>();

		try {
			File target = new File(filepath, filename);
			FileInputStream fi = new FileInputStream(target);
	
			//FileInputStream fi = new FileInputStream("G:/mytoolSoftware/apache-tomcat-8.0.24/webapps/TT/uploadFiles/excle/admin.xlsx");  //先写死
			XSSFWorkbook wb = new XSSFWorkbook(fi);
			
			XSSFSheet sheet = wb.getSheetAt(sheetnum); 					//sheet 从0开始
			int rowNum = sheet.getLastRowNum() + 1; 					//取得最后一行的行号
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			for (int i = startrow; i < rowNum; i++) {					//行循环开始
				
			   HashMap varpd = new HashMap();
				XSSFRow row = sheet.getRow(i); 							//行
				int cellNum = row.getLastCellNum(); 					//每行的最后一个单元格位置
				if(cellNum<6){
					break;
				}
				for (int j = startcol; j < cellNum; j++) {				//列循环开始
					if(j==1||j==3||j==7){
						continue;
					}
					
					XSSFCell cell = row.getCell(Short.parseShort(j + ""));
					String cellValue = null;
					if (null != cell) {
						if(j==0){   
							try {
								Date date=cell.getDateCellValue();  //日期类型
								String val=sdf.format(date);
								varpd.put("var"+j, val);
								continue;
							} catch (Exception e) {
								// TODO: handle exception
								varpd.put("var"+j, "");  //其他字符串 让它为空
							}
							
						}
					    //System.out.println("j="+j);
						switch (cell.getCellType()) { 					// 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
						case 0:
							cellValue = String.valueOf((long) cell.getNumericCellValue());
							break;
						case 1:
							cellValue = cell.getStringCellValue();
							break;
						case 2:
							cellValue = cell.getNumericCellValue() + "";
							// cellValue = String.valueOf(cell.getDateCellValue());
							break;
						case 3:
							cellValue = "";
							break;
						case 4:
							cellValue = String.valueOf(cell.getBooleanCellValue());
							break;
						case 5:
							cellValue = String.valueOf(cell.getErrorCellValue());
							break;
						
						}
					} else {
						cellValue = "";
					}
					varpd.put("var"+j, cellValue);
				}
				varList.add(varpd);
			}

		} catch (Exception e) {
			System.out.println("读取xlsx异常");
			System.out.println(e);
		}
		
		return varList;
	}
	
}