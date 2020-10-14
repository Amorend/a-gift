package com.baidu.mobstat;

import android.content.Context;
import android.util.Log;
import java.io.File;

public final class NativeCrashHandler
{
  private static boolean a;
  private static Context b;
  
  static
  {
    try
    {
      System.loadLibrary("crash_analysis");
      a = true;
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    Log.w("NativeCrashHandler", "Load library failed.");
  }
  
  public static void doNativeCrash()
  {
    if (a) {}
    try
    {
      nativeException();
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    Log.w("NativeCrashHandler", "Invoke method nativeException failed.");
  }
  
  public static void init(Context paramContext)
  {
    if (paramContext == null) {
      return;
    }
    b = paramContext.getApplicationContext();
    if (a)
    {
      paramContext = paramContext.getCacheDir();
      if ((!paramContext.exists()) || (!paramContext.isDirectory())) {}
    }
    try
    {
      nativeInit(paramContext.getAbsolutePath());
      return;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    Log.w("NativeCrashHandler", "Invoke method nativeInit failed.");
  }
  
  private static native void nativeException();
  
  private static native void nativeInit(String paramString);
  
  private static native void nativeProcess(String paramString);
  
  private static native void nativeUnint();
  
  public static void onCrashCallbackFromNative(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("crash: ");
    localStringBuilder.append(paramString);
    Log.w("NativeCrashHandler", localStringBuilder.toString());
    long l = System.currentTimeMillis();
    bl.a().a(l, paramString, "NativeException", 1);
  }
  
  public static void process(String paramString)
  {
    if (paramString != null)
    {
      if (paramString.length() == 0) {
        return;
      }
      if (a)
      {
        File localFile = new File(paramString);
        if ((!localFile.exists()) || (!localFile.isFile())) {}
      }
    }
    try
    {
      nativeProcess(paramString);
      return;
    }
    catch (Throwable paramString)
    {
      for (;;) {}
    }
    Log.w("NativeCrashHandler", "Invoke method nativeProcess failed.");
  }
  
  public static void uninit()
  {
    if (a) {}
    try
    {
      nativeUnint();
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    Log.w("NativeCrashHandler", "Invoke method nativeUnint failed.");
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\NativeCrashHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */