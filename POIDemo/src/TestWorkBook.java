import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFDataValidationHelper;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.util.CellRangeAddressList;


public class TestWorkBook {

	public static void main(String[] args)
	{
		HSSFWorkbook  wbk = new HSSFWorkbook();
		// create a reference sheet
		HSSFSheet sht = wbk.createSheet( "Test Sheet" );
		
		HSSFSheet refSheet = wbk.createSheet( "References" );
		String[] content =  new String[] {"DEV","EX","MET","NI"};
		content = new String[] {
				"Advanced knowledge in current area of work",
				"Analytical Skills / Critical Thinking",
				"Assertiveness",
				"BCH IT Systems",
				"BCH Knowledge / Broaden Perspective",
				"Business Insight",
				"Business Writing",
				"hange Readiness / Management",
				"Communicate with Clarity / Listen / Anticipate Reactions",
				"Conflict Resolution",
				"Contract Management",
				"Courage to Act",
				"Customer Service / Service Delivery",
				"Decision Making / Expand Options / Difficult Decisions",
				"Develop / Coach Others",
				"Difficult Conversations",
				"Engineer - PEng",
				"English as a Second Language",
				"Facilitation Skills",
				"Financial Management / Budgets / Forecasting",
				"Industry Knowledge",
				"Influence and Impact",
				"Innovative",
				"Leadership at All Levels / Judgement",
				"Manage Stress and Workload",
				"Management Development",
				"Negotiating",
				"Networking / Build Profile",
				"Operational Experience",
				"Organization Skills / Planning",
				"People Leadership",
				"Performance Management",
				"Political Awareness",
				"Presentation Skills",
				"Problem Solving / Solution Focused",
				"Process Improvement / Operational Efficiency",
				"Procurement Expertise",
				"Project Management / Delivery",
				"Regulatory Expertise",
				"Relationship Management / Building",
				"Results Orientation",
				"Safety",
				"Self Awareness / Impact on Others",
				"Situational Leadership",
				"Stakeholder Engagement",
				"Strategic Thinking / Planning",
				"Team Building / Engaging / Delegating",
				"Technical Skills / Expertise",
				"Time Management"
		};
		
		for ( int i = 0; i < content.length ; ++i )
		{
			String aString = content[i];
			HSSFRow aRow = refSheet.createRow(i);
			HSSFCell aCell = aRow.createCell(0);
			aCell.setCellValue(aString);
		}
		
		int i = 1;
		int firstRow = 1;
		
		TestWorkBook testWB = new TestWorkBook();
		List<EmployeeRecord> aList = testWB.createAEmployeeList();
		int lastRow = aList.size();
		int firstColumn = 2;
		int lastColumn = 2;
		
		Name namedCell = wbk.createName();
		namedCell.setNameName("References");
		namedCell.setRefersToFormula("References!$A$1:$A" + content.length );
		DVConstraint constraint = DVConstraint.createFormulaListConstraint("References");
		CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, firstColumn, lastColumn);
		HSSFDataValidation validation = new HSSFDataValidation(addressList, constraint);
		validation.setShowErrorBox(false);
		
		HSSFCellStyle lockedStyle = wbk.createCellStyle();
		lockedStyle.setLocked( true );
		
		HSSFCellStyle unlockedStyle = wbk.createCellStyle();
		unlockedStyle.setLocked( false );
		
		HSSFRow row1 = sht.createRow( 0 );
//		row1.setRowStyle( lockedStyle );
		HSSFCell cell1 = row1.createCell( 0 );
		cell1.setCellValue("IDN");
		HSSFCell cell2 = row1.createCell( 1 );
		cell2.setCellValue("Full Name");
		HSSFCell cell3 = row1.createCell( 2 );
		cell3.setCellValue("Rating");
		HSSFCell cell4 = row1.createCell( 3);
		cell4.setCellValue("HR ?");
		
//		HSSFRow row2 = sht.createRow( 1 );
//		row2.setRowStyle( unlockedStyle );
				
		// this implementation only can do a list less than 255 characters
		DataValidation dv = null;
//		DataValidationConstraint dvc = null;
//		DataValidationHelper dvh = null;
//		dvh = new HSSFDataValidationHelper( sht );
//		CellRangeAddressList addressList = new  CellRangeAddressList(firstRow, firstRow, firstColumn, lastColumn);
//
//		
//		dvc = dvh.createExplicitListConstraint( content );
//		dv = dvh.createValidation(dvc, addressList);
//		dv.setShowErrorBox(false);
		
		// dv.setSuppressDropDownArrow( true );
//		sht.addValidationData( dv );
		sht.addValidationData(validation);
//		XSSFWorkbook xwb = null;
		
		for( Iterator<EmployeeRecord> it = aList.iterator(); it.hasNext(); )
		{
			HSSFRow row = sht.createRow( i++ );
			EmployeeRecord er = it.next();
			
			cell1 = row.createCell( 0 );
			cell1.setCellValue( er.getTheIDN() );
			cell1.setCellStyle( lockedStyle );
			cell2 = row.createCell( 1 );
			cell2.setCellValue( er.getTheFullName() );
			cell2.setCellStyle( unlockedStyle );
			cell3 = row.createCell( 2 );
			cell3.setCellValue( er.getTheRating() );
			cell3.setCellStyle( unlockedStyle );
			cell4 = row.createCell( 3 );
			cell4.setCellValue( er.getTheHRFlag() );
			cell4.setCellStyle( lockedStyle );
		}
		
		sht.protectSheet("");
		
		OutputStream os = null;
		
		try
		{
			os = new FileOutputStream("c:/Temp/TestWorkBook.xls");
			wbk.write( os );
			os.flush();
		}
		catch ( FileNotFoundException fnfe )
		{
			fnfe.printStackTrace();
		}
		catch ( IOException ioe )
		{
			ioe.printStackTrace();
		}
		finally
		{
			try
			{
			if ( os != null )
			{
				os.close();
			}
			}
			catch ( IOException ioe )
			{
				ioe.printStackTrace();
			}
		}
		
	}
	
	List<EmployeeRecord> createAEmployeeList()
	{
		List<EmployeeRecord> aList = new ArrayList<EmployeeRecord>();
		aList.add( new EmployeeRecord( "101010" , "John Smith", "A", "Yes"));
		aList.add( new EmployeeRecord( "202020" , "Mary Rose", "B", "No"));
		aList.add( new EmployeeRecord( "303030" , "Rob Taylor", "C", "No"));
		
		return aList;
	}
	
	class EmployeeRecord
	{
		String theIDN;
		String theFullName;
		String theRating;
		String theHRFlag;
		
		public EmployeeRecord(String idn, String fullName, String rating, String hrFlag )
		{
			theIDN = idn;
			theFullName = fullName;
			theRating = rating;
			theHRFlag = hrFlag;
		}
		
		public String getTheIDN() {
			return theIDN;
		}
		public void setTheIDN(String theIDN) {
			this.theIDN = theIDN;
		}
		public String getTheFullName() {
			return theFullName;
		}
		public void setTheFullName(String theFullName) {
			this.theFullName = theFullName;
		}
		public String getTheRating() {
			return theRating;
		}
		public void setTheRating(String theRating) {
			this.theRating = theRating;
		}
		public String getTheHRFlag() {
			return theHRFlag;
		}
		public void setTheHRFlag(String theHRFlag) {
			this.theHRFlag = theHRFlag;
		}
		@Override
		public String toString() {
			return "EmployeeRecord [theIDN=" + theIDN + ", theFullName="
					+ theFullName + ", theRating=" + theRating + ", theHRFlag="
					+ theHRFlag + "]";
		}
	}
}
