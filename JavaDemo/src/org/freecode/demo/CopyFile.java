package org.freecode.demo;

import java.io.*;

public class CopyFile
{
  public static void main(String[] args)
  {
    String srcFileName = args[0];
    String tgtFileName = args[1];
    System.out.println("copying " + srcFileName + " to " + tgtFileName + "..." );
    int bufSize = 4096;
    FileInputStream in =  null;
    FileOutputStream out = null;

    byte[] buf = new byte[bufSize];

    try
    {
      in = new FileInputStream(srcFileName);
      out = new FileOutputStream(tgtFileName);
      int len;
      while ( (len = in.read(buf) ) > 0 )
      {
        out.write(buf, 0, len);
      }
      in.close();
      out.close();
    }
    catch (FileNotFoundException fnfe)
    {
      fnfe.printStackTrace();
    }
    catch(IOException ioe)
    {
      ioe.printStackTrace();
    }
  }
}
