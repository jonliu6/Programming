/**
 * Created by IntelliJ IDEA.
 * User: Jonathan
 * Date: Mar 20, 2007
 * Time: 3:24:14 PM
 * To change this template use File | Settings | File Templates.
 */
// $Header: /generation/JADE-Service/Source/java/com/westech/jade/service/jdbc/SQLResults.java,v 1.2 2005/04/12 01:44:39 RPlanins Exp $

import com.westech.jade.service.jdbc.DatabaseType;
import com.westech.jade.service.jdbc.SQLExecutorBlobType;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Date;

/**
 * <p>Title: SQLResults</p>
 * <p>SQLResults stores the theResultsArrayList of a sql query in the form of objects stored in an Arraylist
 * container object.</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Accenture Business Services for Utilities</p>
 *
 * @author Jeff Smith (jeff@SoftTechDesign.com, www.SoftTechDesign.com)
 * @author Ryan Planinshek
 * @author Mike Jensen
 * @version $Revision: 1.2 $ $Date: 2005/04/12 01:44:39 $
 */
public class SQLResults
{

  /** ArrayList containing the theResultsArrayList of the sql query */
  private ArrayList theResultsArrayList = null;

  /** ArrayList containing the column names returned by the sql query */
  private ArrayList theColumnNameArrayList = null;

  /** number of columns returned by the sql query */
  private int theColumnCount = 0;

  /** database type (e.g. DatabaseType.ORACLE), needed for exception handling */
  private DatabaseType theDatabaseType = null;

  /** formatted width of each field included in toString() */
  private int theToStringFormatWidth = 30;

  /**
   * Constructor creates {@link ArrayList} objects and initializes the {@link DatabaseType} and ColumnCount
   *
   * @param aDatabaseType {@link DatabaseType} - cannot be null
   * @param aColumnCount number of columns in the result set
   */
  public SQLResults( DatabaseType aDatabaseType, int aColumnCount )
  {

    theResultsArrayList = new ArrayList();
    theColumnNameArrayList = new ArrayList();
    theDatabaseType = aDatabaseType;
    theColumnCount = aColumnCount;
  }

  /**
   * Setter for string format width (used in toString() method)
   *
   * @param aToStringFormatWidth a width for displaying the fields when toString() is called on this instance
   */
  public void setToStringFormatWidth( int aToStringFormatWidth )
  {
    theToStringFormatWidth = aToStringFormatWidth;
  }

  /**
   * Adds an object (a field value from a SELECT) to the SQL ResultsArrayList container)
   *
   * @param aObject an object
   */
  void add( Object aObject )
  {
    theResultsArrayList.add( aObject );
  }

  /**
   * Gets the number of rows returned by query
   *
   * @return number of rows
   */
  public int getRowCount()
  {
    int rowCount = 0;
    try
    {
      rowCount = theResultsArrayList.size() / theColumnCount;
    }
    catch ( Exception e )
    {
      System.err.println("ERROR: ");
    }
    return rowCount;
  }

  /**
   * GetObject returns an object in the ResultsArrayList given a aRowIndex and column index
   *
   * @param aRowIndex a row index
   * @param aColumnIndex a column index
   * @return {@link Object} that corresponds to the given indexes
   */
  public Object getObject( int aRowIndex, int aColumnIndex )
  {
    validateRowAndColumnIndexes( aRowIndex, aColumnIndex );
    int index = ( aRowIndex * theColumnCount ) + aColumnIndex;
    return theResultsArrayList.get( index );
  }

  /**
   * Adds a column name to the ColumnNameArrayList list
   *
   * @param aColumnName a column name to add
   */
  public void addColumnName( String aColumnName )
  {
    theColumnNameArrayList.add( aColumnName );
  }

  /**
   * Given a aColumnName, return the corresponding index
   *
   * @param aColumnName a column name
   * @return corresponding index
   */
  private int getColumnIndex( String aColumnName )
  {
    int columnIndex = -1;
    for ( int i = 0; i < theColumnNameArrayList.size(); i++ )
    {
      String thisColumnName = (String)theColumnNameArrayList.get( i );
      if ( thisColumnName.equalsIgnoreCase( aColumnName ) )
      {
        columnIndex = i;
        break;
      }
    }

    if ( columnIndex < 0 )
    {
      System.err.println("ERROR: ");
    }
    return columnIndex;
  }

  /**
   * Gets the value of the field corresponding to row index, column index as an {@link Integer}
   *
   * @param aRowIndex a row index
   * @param aColumnIndex a column index
   * @return {@link Integer} value of the field
   */
  public Integer getInteger( int aRowIndex, int aColumnIndex )
  {
    Integer integerObject = null;
    Object object = getObject( aRowIndex, aColumnIndex );
    if ( object != null )
    {
      if ( object instanceof Integer )
      {
        integerObject = (Integer)object;
      }
      else if ( object instanceof Double )
      {
        integerObject = new Integer( ( (Double)object ).intValue() );
      }
      else
      {
        integerObject = new Integer( ( (BigDecimal)object ).intValue() );
      }
    }

    return integerObject;
  }

  /**
   * Gets the value of the field corresponding to row index, column name as an {@link Integer}
   *
   * @param aRowIndex a row index
   * @param aColumnName a column name
   * @return {@link Integer} value of the field
   */
  public Integer getInteger( int aRowIndex, String aColumnName )
  {
    return getInteger( aRowIndex, getColumnIndex( aColumnName ) );
  }

  /**
   * Gets the value of the field corresponding to row index, column index as an {@link Long}
   *
   * @param aRowIndex a row index
   * @param aColumnIndex a column index
   * @return {@link Long} value of the field
   */
  public Long getLong( int aRowIndex, int aColumnIndex )
  {
    Long longObject = null;
    Object object = getObject( aRowIndex, aColumnIndex );
    if ( object != null )
    {
      if ( object instanceof Long )
      {
        longObject = (Long)object;
      }
      if ( object instanceof Integer )
      {
        longObject = new Long( object.toString() );
      }
      else
      {
        longObject = new Long( ( (BigDecimal)object ).longValue() );
      }
    }

    return longObject;
  }

  /**
   * Gets the value of the field corresponding to row index, column name as a {@link Long}
   *
   * @param aRowIndex a row index
   * @param aColumnName a column name
   * @return {@link Long} value of field
   */
  public Long getLong( int aRowIndex, String aColumnName )
  {
    return getLong( aRowIndex, getColumnIndex( aColumnName ) );
  }

  /**
   * Gets the value of the field corresponding to (row, col) index as a String
   *
   * @param aRowIndex a row index
   * @param aColumnIndex a column index
   * @return String value of field
   */
  public String getString( int aRowIndex, int aColumnIndex )
  {
    String stringValue = null;
    Object object = getObject( aRowIndex, aColumnIndex );

    if ( object == null )
    {
      // do nothing ... we're going to return null
    }
    else if ( object instanceof BigDecimal )
    {
      stringValue = String.valueOf( getDouble( aRowIndex, aColumnIndex ) );
    }
    else if ( object instanceof Integer )
    {
      stringValue = String.valueOf( getInteger( aRowIndex, aColumnIndex ) );
    }
    else if ( object instanceof java.sql.Date || object instanceof Timestamp )
    {
      stringValue = String.valueOf( getDate( aRowIndex, aColumnIndex ) );
    }
    else
    {
      stringValue = String.valueOf( object );
    }

    return stringValue;
  }

  /**
   * Gets the value of the field corresponding to row index, column name as a String
   *
   * @param aRowIndex a row index
   * @param aColumnName a column name
   * @return String value of field
   */
  public String getString( int aRowIndex, String aColumnName )
  {
    return getString( aRowIndex, getColumnIndex( aColumnName ) );
  }

  /**
   * Gets the value of the field corresponding to (row, col) as a {@link Boolean}
   *
   * @param aRowIndex a row index
   * @param aColumnIndex a column index
   * @return {@link Boolean} value of field
   */
  public Boolean getBoolean( int aRowIndex, int aColumnIndex )
  {
    Boolean booleanObject = null;
    Object object = getObject( aRowIndex, aColumnIndex );

    if ( object != null )
    {
      if (object instanceof String)
      {
        //The Boolean constructor accepts the string values of "true" and "false"
        if ( "1".equals( object ) )
        {
          object = "true";
        }
        else if ( "0".equals( object ) )
        {
          object = "false";
        }
        booleanObject = new Boolean( (String)object );
      }
      else if ( object instanceof Integer )
      {
        //The Boolean constructor accepts the string values of "true" and "false"
        if ( new Integer( "1" ).equals( object ) )
        {
          object = "true";
        }
        else if ( new Integer( "0" ).equals( object ) )
        {
          object = "false";
        }

        booleanObject = new Boolean( (String)object );

      }
      else
      {
        booleanObject = (Boolean)object;
      }
    }

    return booleanObject;
  }

  /**
   * Gets the value of the field corresponding to row index, column name as a boolean
   *
   * @param aRowIndex a row index
   * @param aColumnName a column name
   * @return {@link Boolean} value of field
   */
  public Boolean getBoolean( int aRowIndex, String aColumnName )
  {
    return getBoolean( aRowIndex, getColumnIndex( aColumnName ) );
  }

  /**
   * Gets the value of the field corresponding to (row, col) as a {@link Date}
   *
   * @param aRowIndex a row index
   * @param aColumnIndex a column index
   * @return {@link Date} value of field
   */
  public Date getDate( int aRowIndex, int aColumnIndex )
  {
    Date dateObject = null;
    Object object = getObject( aRowIndex, aColumnIndex );

    if ( object != null )
    {
      if ( object instanceof Timestamp )
      {
        dateObject = new Date( ( (Timestamp)object ).getTime() );
      }
      else if ( object instanceof java.sql.Date )
      {
        dateObject = (java.sql.Date)object;
      }
      else if ( object instanceof Date )
      {
        dateObject = (Date)object;
      }
      else
      {
        System.err.println("ERROR: ");
      }
    }

    return dateObject;
  }

  /**
   * Gets the value of the field corresponding to row, columnName as a {@link Date}
   *
   * @param aRowIndex a row index
   * @param aColumnName a column name
   * @return {@link Date} value of field
   */
  public Date getDate( int aRowIndex, String aColumnName )
  {
    return getDate( aRowIndex, getColumnIndex( aColumnName ) );
  }

  /**
   * Gets the value of the field corresponding to (row, col) as a {@link Double}
   *
   * @param aRowIndex a row index
   * @param aColumnIndex a column index
   * @return {@link Double} value of the field
   */
  public Double getDouble( int aRowIndex, int aColumnIndex )
  {
    Double doubleObject = null;
    Object object = getObject( aRowIndex, aColumnIndex );
    if ( object != null )
    {
      if ( object instanceof Double )
      {
        doubleObject = (Double)object;
      }
      else
      {
        doubleObject = new Double( ( (BigDecimal)object ).longValue() );
      }
    }

    return doubleObject;
  }

  /**
   * Gets the value of the field corresponding to row, column name as a {@link Double}
   *
   * @param aRowIndex a row index
   * @param aColumnName a column name
   * @return {@link Double} value of field
   */
  public Double getDouble( int aRowIndex, String aColumnName )
  {
    return getDouble( aRowIndex, getColumnIndex( aColumnName ) );
  }

  /**
   * Gets the value of the field corresponding to (row, col) as a {@link Float}
   *
   * @param aRowIndex a row index
   * @param aColumnIndex a column index
   * @return {@link Float} value of the field
   */
  public Float getFloat( int aRowIndex, int aColumnIndex )
  {
    Float floatObject = null;
    Object object = getObject( aRowIndex, aColumnIndex );
    if ( object != null )
    {
      if ( object instanceof Float )
      {
        floatObject = (Float)object;
      }
      else
      {
        floatObject = new Float( ( (BigDecimal)object ).longValue() );
      }
    }
    return floatObject;
  }

  /**
   * Gets the value of the field corresponding to ( row, col ) as a {@link Blob}
   *
   * @param aRowIndex int
   * @param aColumnIndex int
   * @return Blob
   */
  public SQLExecutorBlobType getSQLExecutorBlobType( int aRowIndex, int aColumnIndex )
  {
    SQLExecutorBlobType sQLExecutorStringBlob = null;
    Object object = getObject( aRowIndex, aColumnIndex );
    if ( object != null )
    {
      if ( object instanceof SQLExecutorBlobType )
      {
        sQLExecutorStringBlob = (SQLExecutorBlobType)object;
      }
      else
      {
        System.err.println("ERROR:");
      }
    }
    return sQLExecutorStringBlob;

  }

  /**
   * Gets the value of the field corresponding to row, column name as a {@link Blob}
   *
   * @param aRowIndex a row index
   * @param aColumnName a column name
   * @return {@link Blob} value of field
   */
  public SQLExecutorBlobType getSQLExecutorBlobType( int aRowIndex, String aColumnName )
  {
    return ( getSQLExecutorBlobType( aRowIndex, getColumnIndex( aColumnName ) ) );
  }


  /**
   * Gets the value of the field corresponding to row, column name as a {@link Float}
   *
   * @param aRowIndex a row index
   * @param aColumnName a column name
   * @return {@link Float} value of field
   */
  public Float getFloat( int aRowIndex, String aColumnName )
  {
    return ( getFloat( aRowIndex, getColumnIndex( aColumnName ) ) );
  }

  /**
   * Returns the contents of SQLResults in a text table. This method is useful for debugging SQL statements.
   *
   * @return String representation
   */
  public String toString()
  {
    if ( theColumnCount < 1 )
    {
      return null;
    }

    StringBuffer sb = new StringBuffer( "\n" );
    for ( int col = 0; col < theColumnCount; col++ )
    {
      String formattedColName = formatWithSpaces( (String)theColumnNameArrayList.get( col ) );
      sb.append( formattedColName );
    }

    sb.deleteCharAt( sb.length() - 2 );
    int len = sb.length();
    sb.append( "\n" );
    for ( int i = 0; i < len - 1; i++ )
    {
      sb.append( "-" );
    }
    sb.append( "\n" );

    for ( int row = 0; row < getRowCount(); row++ )
    {
      for ( int col = 0; col < theColumnCount; col++ )
      {
        String formattedColName = formatWithSpaces( getString( row, col ) );
        sb.append( formattedColName );
      }
      sb.deleteCharAt( sb.length() - 2 );
      sb.append( "\n" );
    }

    return sb.toString();
  }

  /**
   * Formats a string by adding spaces on the end if the string is shorter than
   * theToStringFormatWidth, or truncates string if it is too long.
   * @param s String
   * @return String
   */
  private String formatWithSpaces( String s )
  {
    StringBuffer sb = null;

    if ( s == null )
    {
      s = "NULL";
    }

    sb = new StringBuffer( s );
    if ( s.length() < theToStringFormatWidth )
    {
      for ( int i = 0; i < theToStringFormatWidth - s.length(); i++ )
      {
        sb.append( " " );
      }
      return ( sb.toString() );
    }
    else
    {
      return ( sb.substring( 0, theToStringFormatWidth ) );
    }
  }

  private void validateRowAndColumnIndexes( int aRowIndex, int aColumnIndex ) throws IndexOutOfBoundsException
  {
    if ( aRowIndex < 0 || getRowCount() < aRowIndex  )
    {
      throw new IndexOutOfBoundsException( "The given row index " + aRowIndex + " is not in the range [0," + getRowCount() + "]" );
    }

    if ( aColumnIndex < 0 || theColumnCount < aColumnIndex  )
    {
      throw new IndexOutOfBoundsException( "The given column index " + aColumnIndex + " is not in the range [0," + theColumnCount + "]" );
    }
  }
}

