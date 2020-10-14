package com.baidu.mobstat;

import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.UnknownHostException;

public final class cz
{
  public static int a = 7;
  
  private static String a()
  {
    return "sdkstat";
  }
  
  private static void a(int paramInt, String paramString)
  {
    if (a(paramInt)) {
      Log.println(paramInt, a(), paramString);
    }
  }
  
  public static void a(String paramString)
  {
    a(3, paramString);
  }
  
  public static void a(String paramString, Throwable paramThrowable)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append('\n');
    localStringBuilder.append(d(paramThrowable));
    a(3, localStringBuilder.toString());
  }
  
  public static void a(Throwable paramThrowable)
  {
    a(3, d(paramThrowable));
  }
  
  private static boolean a(int paramInt)
  {
    return paramInt >= a;
  }
  
  public static void b(String paramString)
  {
    a(5, paramString);
  }
  
  public static void b(String paramString, Throwable paramThrowable)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append('\n');
    localStringBuilder.append(d(paramThrowable));
    a(5, localStringBuilder.toString());
  }
  
  public static void b(Throwable paramThrowable)
  {
    a(5, d(paramThrowable));
  }
  
  public static void c(String paramString)
  {
    a(6, paramString);
  }
  
  public static void c(Throwable paramThrowable)
  {
    a(6, d(paramThrowable));
  }
  
  private static String d(Throwable paramThrowable)
  {
    if (paramThrowable == null) {
      return "";
    }
    for (Object localObject = paramThrowable; localObject != null; localObject = ((Throwable)localObject).getCause()) {
      if ((localObject instanceof UnknownHostException)) {
        return "";
      }
    }
    localObject = new StringWriter();
    paramThrowable.printStackTrace(new PrintWriter((Writer)localObject));
    return ((StringWriter)localObject).toString();
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\cz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */