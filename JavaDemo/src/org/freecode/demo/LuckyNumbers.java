package org.freecode.demo;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LuckyNumbers
{
  private final static long PICK_TIMES = 20;
  private static long pickTimes = 0;
  private static int ceilingNumber = 0;
  private static int numberPerDraw = 0;
  private final static int CEILING_NUMBER = 49;
  private final static int NUMBER_PER_DRAW = 6;
  private static Random randomGenerator = null; 
  private TreeSet luckyNumberSet_ = null;
  private static TreeMap luckyNumberStat_ = null;
  private static TreeMap luckyNumberOccurrence_ = null;
  private final static String SHORT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
  private final static String SHORT_TIME_FORMAT = "HH:mm:ss";
  private static Date startTime = null;
  private static Date endTime = null;
  private static boolean slientMode = false;

  private static Date getCurrentTime()
  {
    Calendar cal = Calendar.getInstance();
    return cal.getTime();
  }

  private static void printDateTime( Date aDate )
  {
    SimpleDateFormat sdf = new SimpleDateFormat();
    System.out.println( sdf.format( aDate ) );
  }

  public static void main(String[] args)
  {
    startTime = getCurrentTime();
    printDateTime( startTime ); 

    if (args.length < 1)
    {
      pickTimes = PICK_TIMES;
      ceilingNumber = CEILING_NUMBER;
      numberPerDraw = NUMBER_PER_DRAW;
    }
    else
    {
      if ( args.length == 1 )
      {
        pickTimes = Long.parseLong(args[0].trim());
        ceilingNumber = CEILING_NUMBER;
        numberPerDraw = NUMBER_PER_DRAW;
      }
      else if ( args.length == 3 )
      {
        pickTimes = Long.parseLong(args[0].trim());
        ceilingNumber = Integer.parseInt(args[1].trim());
        numberPerDraw = Integer.parseInt(args[2].trim());
      }
      else
      {
        System.out.println("Usage: java LuckyNumber <number of execution> <ceiling number> <number per draw>");
        System.exit(-1);
      }
      if ( pickTimes > PICK_TIMES )
      {
        slientMode = true;
      }
    }

    randomGenerator = new Random();
    LuckyNumbers luckyNum = null;
    luckyNumberStat_ = new TreeMap();
    initializeStat(luckyNumberStat_);

    for ( long i = 0; i < pickTimes; ++i)
    {
      luckyNum = new LuckyNumbers();
      if ( slientMode == false )
      {
        System.out.println(luckyNum.getNumbers());
      }
      updateStat(luckyNum.getNumbers(), luckyNumberStat_);
    }

    System.out.println(luckyNumberStat_);

    luckyNumberOccurrence_ = new TreeMap();
    calculateOccurrence(luckyNumberStat_, luckyNumberOccurrence_);
    System.out.println(luckyNumberOccurrence_);

    endTime = getCurrentTime();
    printDateTime( endTime ); 
  }

  public static void initializeStat(TreeMap statData)
  {
    Integer statKey = null;
    Long statValue = null;

    for (int i = 0; i < ceilingNumber; ++i)
    {
      statKey = new Integer(i + 1);
      statValue = new Long(0L);

      statData.put(statKey, statValue);
    }
  }

  public static void updateStat(TreeSet onePick, TreeMap statData)
  {
    for (Iterator it = onePick.iterator(); it.hasNext();)
    {
      Integer oneNum = (Integer) it.next();
      long value = ( (Long) statData.get(oneNum) ).longValue();
      value += 1;

      statData.remove(oneNum);
      statData.put(oneNum, new Long(value));
    }
  }

  public static void calculateOccurrence(TreeMap statData, TreeMap calcData)
  {
    Set statKeys = statData.keySet();

    for (Iterator it = statKeys.iterator(); it.hasNext();)
    {
      Integer tmpKey = (Integer) it.next();
      Long calcKey = (Long) statData.get(tmpKey);

      if (calcData.containsKey(calcKey))
      {
        String oldValue = calcData.get(calcKey).toString();
        String newValue = oldValue + "," + tmpKey.toString();
        calcData.remove(calcKey);
        calcData.put(calcKey, newValue);
      }
      else
      {
        calcData.put(calcKey, tmpKey.toString());
      }
    }
  }

  public LuckyNumbers()
  {
    luckyNumberSet_ = new TreeSet();
    init();
  }

  public void init()
  {
    RandomNumber ranNum = null;
    int i = 0;
    while (luckyNumberSet_.size() < numberPerDraw)
    {
      ranNum = new RandomNumber(ceilingNumber);
      luckyNumberSet_.add(new Integer(ranNum.getValue()));
      ++i;
    }
    /* System.out.println(luckyNumberSet_ + "{" + i + "}"); */
  }

  public TreeSet getNumbers()
  {
    return luckyNumberSet_;
  }

  private class RandomNumber
  {
    private int num_ = 0;

    public RandomNumber(int maxValue)
    {
      num_ = randomGenerator.nextInt(maxValue) + 1;
    }

    public int getValue()
    {
      return num_;
    }
  }
}
