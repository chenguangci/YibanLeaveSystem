package com.yiban.dto;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public  class workbookFactory {
	

	
	

	public static HSSFWorkbook getHssfWorkbook() {
		// TODO Auto-generated method stub
		
		
		return new  HSSFWorkbook();
	}
	
	
	
	


	public static short workBook(String[] tableHeader) {
		// TODO Auto-generated method stub
		short cellNumber = (short) tableHeader.length;// 表的列数
		return cellNumber;
	}
}
