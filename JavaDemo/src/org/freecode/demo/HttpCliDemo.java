package org.freecode.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * This is a sample of accessing RESTful
 * 
 */
public class HttpCliDemo {
	
	public static void main(String[] args) {
		String rsURL = "http://services.groupkt.com/country/get/iso2code/CN"; // ReSTful web service for get a country code
		CloseableHttpClient hc = HttpClients.createDefault();
		HttpGet hg = new HttpGet(rsURL);
		CloseableHttpResponse resp = null;
		HttpEntity entity = null;
		InputStream is = null;
		try {
			resp = hc.execute(hg);
			entity = resp.getEntity();
			StatusLine stat = resp.getStatusLine();
			int statusCode = stat.getStatusCode();
			if (statusCode >= 200 && statusCode < 300) {
				String respTxt = parseHttpResponseContent(entity.getContent());
				System.out.println(respTxt);
			}
			else {
				System.out.println("ERROR Code: " + statusCode + " " + stat.getReasonPhrase());
			}			
		}
		catch (UnknownHostException uhe) {
			uhe.printStackTrace();
		}
		catch (ClientProtocolException cpe) {
			cpe.printStackTrace();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		finally {
			try {
			    resp.close();
			}
			catch (IOException ioe1) {
				ioe1.printStackTrace();
			}
		}
		
	}
	
	public static String parseHttpResponseContent(InputStream is) {
		StringBuilder sbuf = new StringBuilder();
		BufferedReader bufRead = new BufferedReader(new InputStreamReader(is));
		String line = "";
		
		try {
			while ((line = bufRead.readLine()) != null) {
				sbuf.append(line);
			}
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		finally {
			try {
				is.close();
			}
			catch (IOException ioe1) {
				ioe1.printStackTrace();
			}
			
		}
		
		return sbuf.toString();
	}

}
