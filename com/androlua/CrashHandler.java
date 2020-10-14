package com.androlua;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class CrashHandler
  implements Thread.UncaughtExceptionHandler
{
  public static final String TAG = "CrashHandler";
  private static CrashHandler b = new CrashHandler();
  private Thread.UncaughtExceptionHandler a;
  private Context c;
  private Map<String, String> d = new LinkedHashMap();
  private DateFormat e = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
  
  private boolean a(Throwable paramThrowable)
  {
    if (paramThrowable == null) {
      return false;
    }
    collectDeviceInfo(this.c);
    b(paramThrowable);
    return true;
  }
  
  private String b(Throwable paramThrowable)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Object localObject1 = this.d.entrySet().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject3 = (Map.Entry)((Iterator)localObject1).next();
      localObject2 = (String)((Map.Entry)localObject3).getKey();
      localObject3 = (String)((Map.Entry)localObject3).getValue();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append((String)localObject2);
      localStringBuilder.append("=");
      localStringBuilder.append((String)localObject3);
      localStringBuilder.append("\n");
      localStringBuffer.append(localStringBuilder.toString());
    }
    localObject1 = new StringWriter();
    Object localObject2 = new PrintWriter((Writer)localObject1);
    do
    {
      paramThrowable.printStackTrace((PrintWriter)localObject2);
      paramThrowable = paramThrowable.getCause();
    } while (paramThrowable != null);
    ((PrintWriter)localObject2).close();
    localStringBuffer.append(localObject1.toString());
    try
    {
      long l = System.currentTimeMillis();
      paramThrowable = this.e.format(new Date());
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("crash-");
      ((StringBuilder)localObject1).append(paramThrowable);
      ((StringBuilder)localObject1).append("-");
      ((StringBuilder)localObject1).append(l);
      ((StringBuilder)localObject1).append(".log");
      paramThrowable = ((StringBuilder)localObject1).toString();
      if (Environment.getExternalStorageState().equals("mounted"))
      {
        localObject1 = new File("/sdcard/androlua/crash/");
        if (!((File)localObject1).exists()) {
          ((File)localObject1).mkdirs();
        }
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("/sdcard/androlua/crash/");
        ((StringBuilder)localObject1).append(paramThrowable);
        localObject1 = new FileOutputStream(((StringBuilder)localObject1).toString());
        ((FileOutputStream)localObject1).write(localStringBuffer.toString().getBytes());
        Log.e("crash", localStringBuffer.toString());
        ((FileOutputStream)localObject1).close();
      }
      return paramThrowable;
    }
    catch (Exception paramThrowable)
    {
      Log.e("CrashHandler", "an error occured while writing file...", paramThrowable);
    }
    return null;
  }
  
  public static CrashHandler getInstance()
  {
    return b;
  }
  
  public void collectDeviceInfo(Context paramContext)
  {
    Object localObject1;
    Object localObject2;
    try
    {
      localObject1 = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 1);
      if (localObject1 != null)
      {
        if (((PackageInfo)localObject1).versionName == null) {
          paramContext = "null";
        } else {
          paramContext = ((PackageInfo)localObject1).versionName;
        }
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(((PackageInfo)localObject1).versionCode);
        ((StringBuilder)localObject2).append("");
        localObject1 = ((StringBuilder)localObject2).toString();
        this.d.put("versionName", paramContext);
        this.d.put("versionCode", localObject1);
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Log.e("CrashHandler", "an error occured when collect package info", paramContext);
    }
    Field[] arrayOfField = Build.class.getDeclaredFields();
    int k = arrayOfField.length;
    int j = 0;
    int i = 0;
    Field localField;
    while (i < k)
    {
      localField = arrayOfField[i];
      try
      {
        localField.setAccessible(true);
        paramContext = localField.get(null);
        if ((paramContext instanceof String[]))
        {
          localObject1 = this.d;
          localObject2 = localField.getName();
        }
        for (paramContext = Arrays.toString((String[])paramContext);; paramContext = paramContext.toString())
        {
          ((Map)localObject1).put(localObject2, paramContext);
          break;
          localObject1 = this.d;
          localObject2 = localField.getName();
        }
        paramContext = new StringBuilder();
        paramContext.append(localField.getName());
        paramContext.append(" : ");
        paramContext.append(localField.get(null));
        Log.d("CrashHandler", paramContext.toString());
      }
      catch (Exception paramContext)
      {
        Log.e("CrashHandler", "an error occured when collect crash info", paramContext);
      }
      i += 1;
    }
    arrayOfField = Build.VERSION.class.getDeclaredFields();
    k = arrayOfField.length;
    i = j;
    while (i < k)
    {
      localField = arrayOfField[i];
      try
      {
        localField.setAccessible(true);
        paramContext = localField.get(null);
        if ((paramContext instanceof String[]))
        {
          localObject1 = this.d;
          localObject2 = localField.getName();
        }
        for (paramContext = Arrays.toString((String[])paramContext);; paramContext = paramContext.toString())
        {
          ((Map)localObject1).put(localObject2, paramContext);
          break;
          localObject1 = this.d;
          localObject2 = localField.getName();
        }
        paramContext = new StringBuilder();
        paramContext.append(localField.getName());
        paramContext.append(" : ");
        paramContext.append(localField.get(null));
        Log.d("CrashHandler", paramContext.toString());
      }
      catch (Exception paramContext)
      {
        Log.e("CrashHandler", "an error occured when collect crash info", paramContext);
      }
      i += 1;
    }
  }
  
  public void init(Context paramContext)
  {
    this.c = paramContext;
    this.a = Thread.getDefaultUncaughtExceptionHandler();
    Thread.setDefaultUncaughtExceptionHandler(this);
  }
  
  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    if ((!a(paramThrowable)) && (this.a != null)) {
      this.a.uncaughtException(paramThread, paramThrowable);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\CrashHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */