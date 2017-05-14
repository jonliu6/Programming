import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Reboot
{
  private String cmd = "\\system32\\shutdown.exe -f -r -t 60";
  private String winDir = null;
  private String rootDir = null;
  private String bootFile = "\\boot.ini";
  private String defBootSec = null;
  private List<String> bootSectors = null;
  private List<String> currentBootInformation = null;
  private List<String> newBootInformation = null;

  public Reboot()
  {
    winDir = System.getenv("windir");
    String[] winDirData = winDir.split("\\\\");
    rootDir = winDirData[0];
  }

  public String getRootDrive()
  {
    return rootDir;
  }

  public String getDefaultBootSector()
  {
    return defBootSec;
  }

  public List<String> getBootSectors()
  {
    return bootSectors;
  }

  public List<String> getBootInformation()
  {
    FileReader fr = null;
    BufferedReader br = null;
    currentBootInformation = new ArrayList<String>();

    try
    {
      fr = new FileReader( rootDir + bootFile );
      br = new BufferedReader(fr);
      String eachLine = null;
      String[] eachLineInfo = null;
      boolean recordSectors = false;
      bootSectors = new ArrayList<String>();

      while ( ( eachLine = br.readLine() ) != null )
      {
        currentBootInformation.add( eachLine );
        eachLineInfo = eachLine.split("=");
        if ( eachLineInfo[0].equalsIgnoreCase("default") )
        {
          defBootSec = eachLineInfo[1];
        }

        if ( recordSectors )
        {
          bootSectors.add(eachLineInfo[0]);
        }

        if ( eachLineInfo[0].equalsIgnoreCase("[operating systems]") )
        {
          recordSectors = true;
        }

      }
    }
    catch ( IOException ioe )
    {
      ioe.printStackTrace();
    }
    finally
    {
      try
      {
        br = null;
        fr.close();
      }
      catch(IOException ioe)
      {
        ioe.printStackTrace();
      }
    }

    return currentBootInformation;
  }

  public List<String> changeBootInformation()
  {
    System.out.println(bootSectors);
    String eachItem = null;
    String[] eachItemInfo = null;
    newBootInformation = new ArrayList<String>();

    Iterator<String> it = currentBootInformation.iterator();
    for ( ; it.hasNext(); )
    {
      eachItem = it.next();
      eachItemInfo = eachItem.split( "=" );
      if ( eachItemInfo[0].equalsIgnoreCase("default") )
      {
        Iterator<String> itSec = bootSectors.iterator();
        for ( ; itSec.hasNext(); )
        {
          String bootSec = itSec.next();
          if ( ! eachItemInfo[1].equalsIgnoreCase( bootSec ) )
          {
            eachItemInfo[1] = bootSec;
            System.out.println("------" + eachItemInfo[1] + "----");
            break;
          }
        }
        eachItem = eachItemInfo[0] + "=" + eachItemInfo[1];
      }

      newBootInformation.add(eachItem);
    }

    return newBootInformation;
  }

  public void setBootInformation(List<String> newBootInfo)
  {
    //PrintWriter pw = null;
    FileWriter fw = null;
    BufferedWriter bw = null;

    Iterator<String> it = newBootInfo.iterator();
    try
    {
      //pw = new PrintWriter( rootDir + bootFile );
      fw = new FileWriter(rootDir + bootFile);
      bw = new BufferedWriter(fw);
      
      for (; it.hasNext(); )
      {
        // pw.println( it.next() );
        bw.write(it.next() + "\r\n"); 
      }
      //pw.flush();
      fw.flush();
    }
    catch ( IOException ioe )
    {
      ioe.printStackTrace();
    }
    finally
    {
      //pw.close();
      try
      {
        fw.close();
      }
      catch (IOException ioe)
      {
        ioe.printStackTrace();
      }
    }
  }

  public void execute()
  {
    try
    {
      Process p = Runtime.getRuntime().exec( winDir + cmd );
    }
    catch (IOException ioe)
    {
      ioe.printStackTrace();
    }
  }

  public static void main(String[] args)
  {
    Reboot rb = new Reboot();
    System.out.println( rb.getBootInformation() );
    System.out.println( rb.getDefaultBootSector() );
    System.out.println( rb.getBootSectors() );
    rb.setBootInformation( rb.changeBootInformation() );
  }

}
