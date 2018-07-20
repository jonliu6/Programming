package org.freecode.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOCase;
import org.apache.commons.io.comparator.DirectoryFileComparator;
import org.apache.commons.io.comparator.NameFileComparator;

public class BrowsingDirectory
{
    public static void main(String[] args)
    {
    	  if (args.length < 1)
    	  {
    	  	  System.out.println("Usage: java BrowsingDirectory [Directory Name]");
    	  	  System.exit(-1);
    	  }
    	  String dirName = args[0];
        FileList fl = new FileList(dirName);
        fl.listEntries(dirName, 0);
        System.out.println( fl.toString() );
    }

    public static class FileList
    {
        private String root;
        //private List<String> entries;
        private List entries;
    
        public FileList(String dirname)
        {
            root = dirname;
            //entries = new ArrayList<String>();
            entries = new ArrayList();
        }
    
        public String getRootName()
        {
            return root;
        }
    
        public void listEntries(String dname, int ind)
        {
            File folder = new File(dname);
            File[] files = folder.listFiles();
            DirectoryFileComparator dfc = new DirectoryFileComparator();
            Arrays.sort(files, DirectoryFileComparator.DIRECTORY_COMPARATOR);
            // dfc.sort(files);
            String indent;
    
            indent = getIndentation(ind, ' ');
    
            if (files == null)
            {
                //System.out.println("Please give a valid directory name");
                entries.add(indent + "Invalid file or directory: " + dname);
                return;
            }
            else
            {
                for (int i = 0; i < files.length; ++i)
                {
                    String fname = null;
                    Date fModDate = null;
                    if (files[i].isDirectory())
                    {
                        fname = new String(indent + "[" +
                                           files[i].getName() + "]");
                        entries.add(fname);
                        listEntries(files[i].getAbsolutePath(), ind + 4);
                    }
                    else
                    {
                        fname = indent + files[i].getName();
                        fModDate = new Date( files[i].lastModified() );
                        entries.add(fname);
                    }
                    
                    System.out.println(fname + ": " + fModDate);
                    
                }
            }
        }
    
        public String getIndentation(int num, char c)
        {
            StringBuffer sbuf = new StringBuffer();
            for (int i = 0; i < num; ++i)
            {
                sbuf.append(c);
            }
    
            return sbuf.toString();
        }
    
        public String toString()
        {
            StringBuffer sbuf = new StringBuffer();
            // Iterator<String> it;
            Iterator it;
    
            for (it = entries.iterator(); it.hasNext();)
            {
                sbuf.append(it.next().toString());
                sbuf.append("\r\n");
            }
    
            return sbuf.toString();
        }
    }

}



    