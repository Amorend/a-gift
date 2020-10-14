package com.mycompany.myapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time
{
  private static final int seconds_of_15days = 1296000;
  private static final int seconds_of_1day = 86400;
  private static final int seconds_of_1hour = 3600;
  private static final int seconds_of_1minute = 60;
  private static final int seconds_of_1year = 31104000;
  private static final int seconds_of_30days = 2592000;
  private static final int seconds_of_30minutes = 1800;
  private static final int seconds_of_6months = 15552000;
  
  public static String getTimeRange(String paramString)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Object localObject1 = new Date(System.currentTimeMillis());
    Object localObject2 = localSimpleDateFormat.format((Date)localObject1);
    Date localDate = (Date)null;
    try
    {
      localObject2 = localSimpleDateFormat.parse((String)localObject2);
      localObject1 = localObject2;
      paramString = localSimpleDateFormat.parse(paramString);
      localObject1 = localObject2;
    }
    catch (ParseException paramString)
    {
      int i;
      for (;;)
      {
        paramString.printStackTrace();
        paramString = localDate;
      }
      if (i >= 1800) {
        break label119;
      }
      return i / 60 + "分钟前";
      if (i >= 3600) {
        break label129;
      }
      return "半小时前";
      if (i >= 86400) {
        break label159;
      }
      return i / 3600 + "小时前";
      if (i >= 1296000) {
        break label188;
      }
      return i / 86400 + "天前";
      if (i >= 2592000) {
        break label197;
      }
      return "半个月前";
      if (i >= 15552000) {
        break label226;
      }
      return i / 2592000 + "月前";
      if (i >= 31104000) {
        break label235;
      }
      return "半年前";
      if (i < 31104000) {
        break label264;
      }
      return i / 31104000 + "年前";
    }
    i = (int)((((Date)localObject1).getTime() - paramString.getTime()) / 'Ϩ');
    if (i < 60) {
      return "刚刚";
    }
    label119:
    label129:
    label159:
    label188:
    label197:
    label226:
    label235:
    label264:
    return "";
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\mycompany\myapp\Time.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */