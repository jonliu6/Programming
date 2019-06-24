package org.freecode.demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import jxl.Cell;
//import jxl.Sheet;
//import jxl.Workbook;
//import jxl.read.biff.BiffException;

public class ExcelHelper implements Serializable {
	
	public final static String CONTENT_TYPE_OLD_EXCEL = "application/vnd.ms-excel";
	public final static String CONTENT_TYPE_NEW_EXCEL = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	/*****
	@Deprecated
	public void generateWorkbookByInputStream(InputStream is) {
		Workbook wb = null;
		
		try {
			wb = Workbook.getWorkbook(is);
			if (wb != null ) {
				Sheet sht = wb.getSheet(0);
				if (sht != null ) {
					int numOfRows = sht.getRows();
                    // System.out.println("##############################" + numOfRows + " row(s) found.");
					for (int i = 0; i < numOfRows; ++i) {
						Cell[] cols = sht.getRow(i);
						System.out.println("##############################");
						// System.out.println("##############################" + cols.length + " col(s) found.");
						if (cols != null) {
							for (int j = 0, len = cols.length; j < len; ++j) {
								System.out.println(cols[j].getContents() + "\t");
							}
						}
					}	 
				}
			}			
		}
		catch (BiffException be) {
			be.printStackTrace();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	*****/
	
	public void generateWorkbookByInputStream (InputStream is) {
		Workbook wb = null;
		Sheet sht = null;
		try {
			wb = WorkbookFactory.create(is);
			sht = wb.getSheetAt(0);
			processWorkbookRows(sht.rowIterator());
		}
		catch (InvalidFormatException ife) {
			ife.printStackTrace();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * using Apache POI supporting old Excel format (2003 and older)
	 * @param is
	 */
	public void generateOldWorkbookByInputStream(InputStream is) {
		HSSFWorkbook workbook = null;
		Iterator<Row> rows = null;
		try {
			workbook = new HSSFWorkbook(is);
			HSSFSheet sheet = workbook.getSheetAt(0);
			rows = sheet.rowIterator();
			
			processWorkbookRows(rows);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * using Apache POI supporting new Excel format (2003 and newer)
	 * @param is
	 */
    public void generateNewWorkbookByInputStream(InputStream is) {
    	XSSFWorkbook workbook = null;
    	Iterator<Row> rows = null;
    	try {
    		workbook = new XSSFWorkbook(is);
    		XSSFSheet sheet = workbook.getSheetAt(0);
    		rows = sheet.rowIterator();
    		
    		processWorkbookRows(rows);
    	}
    	catch (Exception ex) {
			ex.printStackTrace();
    	}
	}
    
    private void processWorkbookRows(Iterator<Row> rows) {
    	System.out.println("##############################");
    	while (rows.hasNext()) {
			Row aRow = rows.next();
			for (Iterator<Cell> it = aRow.cellIterator(); it.hasNext();) {
				Cell c = it.next();
				DataFormatter df = new DataFormatter();
				String cellValue = df.formatCellValue(c);
//				if (DateUtil.isCellDateFormatted(c)) {
//					Date d = c.getDateCellValue();
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					cellValue = sdf.format(d);
//				}
//				else if (CellFormatType.NUMBER.equals(c.getCellType())) {
//					cellValue = String.valueOf(c.getNumericCellValue());
//				}
//				else {
//					cellValue = c.getStringCellValue();
//				}
				System.out.print(cellValue + "\t"); 
			}
			System.out.println(" ");
		}
    	System.out.println("##############################");
    }
    
}
