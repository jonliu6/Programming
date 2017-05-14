import org.apache.xerces.parsers.DOMParser;
import org.apache.xml.serialize.XMLSerializer;
import org.apache.xml.serialize.OutputFormat;
import org.w3c.dom.Document;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: Jonathan
 * Date: Mar 11, 2007
 * Time: 12:06:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class XMLFormatter {
  final private static int IDENTATION_CHAR_NUM = 2;

  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println("Usage: XMLFormatter old_xml new_xml");
      return;
    }

    XMLFormatter xmlFmt = new XMLFormatter(args[0], args[1]);
  }

  public XMLFormatter(String oldFile, String newFile)
  {
    Document doc = null;
    //System.out.println(oldFile + "-" + newFile);
    File oldXML = new File(oldFile);
    if (! oldXML.exists()) {
      System.out.println("Couldn't find " + oldFile);
      return;
    }
    DOMParser parser = new DOMParser();
    try {
      parser.parse(oldFile);
      doc = parser.getDocument();

    } catch (Exception ex) {
      ex.printStackTrace();
      return;
    }

    try
    {
      FileWriter fw = new FileWriter(newFile);
      OutputFormat format = new OutputFormat(doc);
      format.setIndenting(true);
      // format.setLineWidth(65);
      format.setIndent(IDENTATION_CHAR_NUM);
      XMLSerializer serializer = new XMLSerializer(fw, format);
      serializer.serialize(doc);
      fw.close();
    } catch (IOException ioe) {
           ioe.printStackTrace();
    }
  }
}
