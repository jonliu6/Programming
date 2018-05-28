package org.freecode.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class TestActualRows {
    private int MAX_COLUMN_NUMBER = 4; 
	
	public static void main(String[] args)
	{
		TestActualRows driver = new TestActualRows();
		
		String oldFileName = "c:/Temp/TestWorkBook.xls";
		String newFileName = "c:/Temp/TesstWorkBook.xlsx";
		
		driver.printDataFromWorksheet( driver.loadSheetFromWorkbook( driver.loadFileIntoWorkbook( driver.loadFileByName(oldFileName) ), "Test Sheet" ) );
	}
	
	private File loadFileByName( String aFileName )
	{
		File f = new File(aFileName);
		return f;		
	}
	 
	private Workbook loadFileIntoWorkbook( File aFile ) 
	{
		Workbook wk = null;
		
		try
		{
			wk = WorkbookFactory.create(new FileInputStream(aFile));
		}
		catch (InvalidFormatException ife)
		{
			ife.printStackTrace();
		}
		catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		
		return wk;
	}
	
	private Sheet loadSheetFromWorkbook(Workbook aWorkbook, String aSheetName)
	{
		Sheet sht = aWorkbook.getSheet(aSheetName);
		
		return sht;
	}
	
	private void printDataFromWorksheet( Sheet aSheet )
	{
		Iterator<Row> itRow = null;
		Iterator<Cell> itCell = null;
		
		if ( aSheet != null )
		{
			itRow = aSheet.rowIterator();
			System.out.println("Number of Rows: " + aSheet.getPhysicalNumberOfRows() );
			while (itRow.hasNext())
			{
				Row aRow = itRow.next();
				System.out.println( "Row #: " + aRow.getRowNum() + ", First Cell #: " + aRow.getFirstCellNum() + ", Last Cell #: " + aRow.getLastCellNum());
				if ( aRow != null )
				{
					if ( isEmptyRow(aRow) )
					{
						break;
					}
					itCell = aRow.iterator();
					while ( itCell.hasNext() )
					{
						Cell aCell = itCell.next();
//						if ( aCell == null || aCell.getCellType() == Cell.CELL_TYPE_BLANK )
//						{
//							break;
//						}
						if ( isCellOutOfRange(aCell, MAX_COLUMN_NUMBER ))
						{
							break;
						}
						System.out.print( "[" + aCell.getRichStringCellValue() + "]\t");
					}
				}
				System.out.println();
			}
			
		}
	}
	
	private boolean isEmptyRow( Row aRow )
	{
		boolean isEmpty = false;
		
		Cell firstCell = aRow.getCell(aRow.getFirstCellNum());
		if ( firstCell == null || firstCell.getCellType() == Cell.CELL_TYPE_BLANK)
		{
			isEmpty = true;
		}
		
		return isEmpty;
	}
	
	private boolean isCellOutOfRange( Cell aCell, Integer maxColNum )
	{
		boolean isOut = false;
		
		if ( aCell.getColumnIndex() >= maxColNum )
		{
			isOut = true;
		}
		
		return isOut;
	}
}
