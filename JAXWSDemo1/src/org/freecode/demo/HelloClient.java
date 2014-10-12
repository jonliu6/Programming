package org.freecode.demo;

import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;

public class HelloClient {
	
	// genStubFromWsdlUsingAxis2.sh --location=http://localhost:7001/JAXWS/SayingHello?wsdl --output=c:/temp/stubs --package=org.freecode.demo
	public static void main(String[] args)
	{
		// WSDL2JAVA Stub
//		try
//		{
//		SayingHelloStub stub = new SayingHelloStub("http://localhost:7001/JAXWS/SayingHello?WSDL");
//		stub._getServiceClient().getOptions().setTimeOutInMilliSeconds( 60000 );
//		SayingHelloStub.SayHello requestEnv = new SayingHelloStub.SayHello();
//		requestEnv.setAName("Dr. Liu");
//		SayingHelloStub.SayHello1 request = new SayingHelloStub.SayHello1();
//		request.setSayHello( requestEnv );
//		SayingHelloStub.SayHelloResponse0 responseWrapper = stub.sayHello(request);		
//		SayingHelloStub.SayHelloResponse response = responseWrapper.getSayHelloResponse();
////		stub.sayHello(request);
//		System.out.println( response.getSayHelloResponse() );
//		}
//		catch (Exception ex)
//		{
//            ex.printStackTrace();
//		}
		
		// WSIMPORT Stub
//		SayingHello_Service service = null;
//		try
//		{
//		service = new SayingHello_Service(new URL("http://localhost:7001/JAXWS/SayingHello?wsdl"));
//		SayingHello helloPort = service.getSayingHelloPort();
//		System.out.println( helloPort.sayHello("Fujitsu"));
//		}
//		catch(Exception ex)
//		{
//			ex.printStackTrace();
//		}
		
		// http://localhost:30070/GTRM-WS-WebServices/FindingReports?WSDL
//		FindingReportsStub stub = null;
//		try
//		{
//			stub = new FindingReportsStub("http://localhost:30070/GTRM-WS-WebServices/FindingReports?WSDL");
//			FindingReportsStub.FindingReports0 request = new FindingReportsStub.FindingReports0();
//			FindingReportsStub.FindingReportsResponse1 responseWrapper = stub.findingReports(request);
//			FindingReportsStub.FindingReportsResponse response = responseWrapper.getFindingReportsResponse();
//			System.out.println("+++" + response.getFindingReportsResponse() + "+++");
//			
//		}
//		catch( Exception ex )
//		{
//			ex.printStackTrace();
//		}
		
		try
		{
		FindingReportsStub stub = new FindingReportsStub("http://localhost:30070/GTRM-WS-WebServices/FindingReports?WSDL");
        FindingReportsStub.FindingReports0 request = new FindingReportsStub.FindingReports0();
		FindingReportsStub.FindingReports requestWrapper = new FindingReportsStub.FindingReports();
		requestWrapper.setArg0("BGS");
		requestWrapper.setArg1("01");
		requestWrapper.setArg2(2000);
		requestWrapper.setArg3(2014);
		request.setFindingReports( requestWrapper );
		
		FindingReportsStub.FindingReportsResponse1 responseWrapper = stub.findingReports( request );
		
		FindingReportsStub.FindingReportsResponse response = responseWrapper.getFindingReportsResponse();
		FindingReportsStub.ReportTransferObject[] reportArray = response.getFindingReportsResponse();
		System.out.println( reportArray.length + " records found." );
		
		for (int i = 0; i < reportArray.length; ++i )
		{
			FindingReportsStub.ReportTransferObject rptObj = reportArray[i];
			System.out.println( rptObj.getReportNo() + " on " + rptObj.getReportDate() );
		}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		// wsimport http://localhost:30070/GTRM-WS-WebServices/FindingReports?WSDL -Xnocompile -d c:/Temp -p org.freecode.demo
//		try
//		{
//			FindingReports_Service service = new FindingReports_Service(new URL("http://localhost:30070/GTRM-WS-WebServices/FindingReports?WSDL"), new QName("http://www.bchydro.com/GTRM", "FindingReports"));
//			
//			FindingReports reportPort = service.getFindingReportsPort();
//			
//			List<ReportTransferObject> reports = reportPort.findingReports("BGS", "01", 2000, 2014);
//			System.out.println( reports.size() );
//			for(Iterator<ReportTransferObject> it = reports.iterator(); it.hasNext();)
//			{
//				ReportTransferObject rpt = it.next();
//				System.out.println( rpt.reportNo + "-" + rpt.reportDate );
//			}
//		}
//		catch ( Exception ex )
//		{
//			ex.printStackTrace();
//		}
		
	}

}
