package com.qsd.framework.tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 
 * 
 * @author suntongwei
 */
public class Excel {

	/**
	 * EXCEL类型枚举
	 * 
	 * @author suntongwei
	 */
	public enum ExcelType {
		// 97-2003
		HSSF,
		// 2007
		XSSF
	}

	/**
	 * Excel转换
	 * 
	 * @author suntongwei
	 */
	public interface ExcelConver {
		/**
		 * 转换实现方法
		 */
		public void conver(Object[] obj);
	}

	/**
	 * 
	 * 
	 * @param type
	 * @param in
	 * @param conver
	 * @throws IOException 
	 */
	public static void readExcel(ExcelType type, InputStream in, ExcelConver conver) throws IOException {
		if(type == ExcelType.HSSF) {
			readHSSFExcel(in,conver);
		}
	}
	
	private static void readHSSFExcel(InputStream in, ExcelConver conver) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook(in);
		HSSFSheet sheet = workbook.getSheetAt(0);
		for(int i = 1; i < sheet.getLastRowNum(); i++) {
			HSSFRow row = sheet.getRow(i);
			Object[] rowData = new Object[row.getLastCellNum()];
			for(int j = 0; j < row.getLastCellNum(); j++) {
				rowData[j] = row.getCell(j);
			}
			conver.conver(rowData);
		}
	}

	/**
	 * 
	 * 
	 * @param type
	 * @param filePath
	 * @param conver
	 * @throws FileNotFoundException
	 */
	public static void readExcel(ExcelType type, String filePath,
			ExcelConver conver) throws FileNotFoundException {

	}
}
