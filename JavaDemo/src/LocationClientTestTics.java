import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import com.gesmallworld.core.tics.TicsConnection;

/**
 * Compile: javac -cp H:\Training\Smallworld\Java\lib\geswcore.jar;. LocationClientTestTics.java
 * Run: java -cp H:\Training\Smallworld\Java\lib\geswcore.jar;. LocationClientTestTics kdcbchomsc017 7070 "-122.8239577,49.2860435,-122.8410177,49.2919705"
 * Test data including more device types: -122.537871588,49.1024100545,-122.535723232,49.1038211058  (lambert format: 3377605.00000,-43323680.0000, 3393205.00000,-43307872.0000)
 */
public class LocationClientTestTics {
  protected static int getMaxProtocol()  { return 1; }
  protected static int getMinProtocol()  { return 1; }
  private static String FILE_NAME_PREFIX = "./LocationClientTestTics";
  private static String FILE_NAME_EXTENSION = ".json";
	
  public static void main(String[] args) {
	if (args == null || args.length < 3) {
	  System.err.println("Usage: java LocationClientTestTics host port bounding_box_string");
	  System.exit(-1);
    }
	// System.out.println(args[0]);
	TicsConnection connection = null;
	try {
	  connection = new TicsConnection();
      String host = args[0];
      String port = args[1];
      String boundsText = args[2];
      connection.connect(host, port);
      connection.establishProtocol(getMinProtocol(), getMaxProtocol()); // Handshaking protocols of the client
	  
	  connection.putString(boundsText);
	  // System.out.println(connection.getString());
	  //String msg = "";
      //if (boundsText != null && boundsText.indexOf(",") > -1) {
    	//  String[] boundValues = boundsText.split(",");
    	//  if (boundValues != null) {
    	//	  double[] bounds = new double[boundValues.length];
    	//	  for (int i = 0, len = boundValues.length; i < len; i++ ) {
    	//		  bounds[i] = Double.parseDouble(boundValues[i]);
		//		  msg += String.valueOf(bounds[i]) + " ";
    	//	  }
    	//	  connection.putFloats(bounds);
			  
		//	  System.out.println("Sent " + bounds.length + " numbers [" + msg + "] to " + host + ":" + port);
    	//  }
      //}
	  
	  int num = connection.getInt();
	  if (num > 0) {
		  System.out.println("receiving " + num + " record(s)...");
	  }
	  StringBuilder geoData = new StringBuilder();
	  geoData.append(generateGeoDataHeader());
	  for (int i = 0; i < num; ++i) {
		  // System.out.println(connection.getString());
		  String tmpStr = connection.getString();
		  if (tmpStr != null) {
			  String[] deviceInfo = tmpStr.split(",");
			  if (deviceInfo != null) {
				  String deviceId = deviceInfo[0];
				  String deviceType = deviceInfo[1];
				  Double longitude = Double.valueOf(deviceInfo[2]);
				  Double latitude = Double.valueOf(deviceInfo[3]);
				  String misc = deviceInfo[4];
				  
				  if (i > 0) {
					  geoData.append(",\n");
				  }
                  geoData.append("    {\n      \"type\":\"Feature\",\n      \"geometry\":{\n        \"type\":\"Point\",\n        \"coordinates\":[" + longitude + "," + latitude + "]\n      },\n");
                  geoData.append("      \"properties\":{\n        \"ID\":" + deviceId + ",\n        \"DeviceType\":" + deviceType + ",\n        \"Misc.\":\"" + misc + "\"\n      }\n    }");
			  }
		  }
	  }
	  geoData.append(generateGeoDataFooter());
	  
	  // returnData.append(connection.getString());
	  // returnData.append(connection.getString());
	  // returnData.append(connection.getString());
	  Date dt = new Date();
	  saveTextToFile(geoData.toString(), FILE_NAME_PREFIX + dt.getTime() + FILE_NAME_EXTENSION);
	  // System.out.println("received " + returnData.length() + " character(s).");
	  connection.disconnect();
	}
	catch (Exception ex) {
		ex.printStackTrace();
	}
  }
  
  private static String generateGeoDataHeader() {
	  return "{\n  \"type\":\"FeatureCollection\",\n  \"features\":[\n";
  }
  
  private static String generateGeoDataFooter() {
	  return "\n  ]\n}";
  }
  
  public static void saveTextToFile(String txt, String fileName) throws IOException {
	FileWriter fWriter = null;
	try {
		fWriter = new FileWriter(fileName);
		fWriter.write(txt);
	}
	catch (IOException ioe) {
		ioe.printStackTrace();
	}
	finally {
		if (fWriter != null) {
			fWriter.close();
		}
	}
  }
}