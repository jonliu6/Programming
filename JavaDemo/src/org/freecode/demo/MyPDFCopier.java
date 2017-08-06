package org.freecode.demo;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Created by IntelliJ IDEA.
 * User: jl
 * Date: Sep 5, 2012
 * Time: 12:48:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyPDFCopier
{
  public static void main(String[] args)
  {
    String srcName;
    String tgtName;
    int fromPage = 1;
    int toPage = 0;

    if ( args.length < 1 )
    {
        System.out.println( "Usage: MyPDFCopier filename [from page#] [to page#]" );

    }
    else
    {
        //      System.out.println( "Usage: " + args[0] );
      srcName = args[0];
      tgtName = copyFileName( srcName );
      if ( args.length >= 2 )
      {
        fromPage = Integer.parseInt( args[1] );
        if ( args.length >= 3 )
        {
          toPage = Integer.parseInt( args[2] );
        }
        // else
        // {
        //   System.out.println( "Usage: MyPDFCopier filename [from page#] [to page#]" );
        // }
      }
      System.out.println("Copy from page " + fromPage + " to " + toPage + " of " + srcName + " and save as " + tgtName );
      copyPDF( srcName, tgtName, fromPage, toPage ); 
    }

  }

  private static String getFileName(String fullName )
  {
    return fullName.substring(fullName.lastIndexOf( "\\" ) + 1, fullName.length());
  }

  private static String getPath(String fullName )
  {
    return fullName.substring(0, fullName.lastIndexOf( "\\" ) );
  }

  private static String copyFileName(String aFileName)
  {
    String fileName1 = aFileName.substring( 0, aFileName.lastIndexOf("." ) );
    String fileName2 = aFileName.substring( aFileName.lastIndexOf( "." ), aFileName.length() );  // extension

    return fileName1 + "_copy" + fileName2;
  }

  private static void copyPDF( String sourceFileName, String targetFileName, int fromPage, int toPage )
  {
    try
    {
      PdfReader reader = new PdfReader( sourceFileName );
      int numOfPage = reader.getNumberOfPages();
      int lastPageOfCopy = numOfPage; // by default
      if ( toPage < numOfPage && toPage > 0 )
      {
        lastPageOfCopy = toPage;
      }

//      System.out.println( numOfPage );
//      reader.selectPages( "2-5" );
//      System.out.println( reader.getNumberOfPages() );
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
      Document doc = new Document();
      PdfCopy copier = new PdfCopy( doc, outputStream );
      doc.open();
      PdfStamper stamper = new PdfStamper( reader, outputStream );
      for ( int i = fromPage; i < lastPageOfCopy + 1; ++i )
      {
        PdfImportedPage importedPage = stamper.getImportedPage( reader, i );
        copier.addPage( importedPage );
      }
      copier.freeReader( reader );
      outputStream.flush();
      doc.close();

      // write PDF from bytestream
      OutputStream output = new FileOutputStream( targetFileName );
      outputStream.writeTo( output );
      output.flush();
      output.close();
    }
    catch ( IOException ioe )
    {
      ioe.printStackTrace();
    }
    catch ( DocumentException de)
    {
      de.printStackTrace(  );
    }
  }
}
