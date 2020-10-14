package com.tencent.open.b;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.tencent.open.a.f;
import com.tencent.open.utils.e;
import java.util.Locale;

public class c
{
  static String a = null;
  static String b = null;
  static String c = null;
  private static String d;
  private static String e = null;
  
  public static String a()
  {
    try
    {
      Object localObject = e.a();
      if (localObject == null) {
        return "";
      }
      localObject = (WifiManager)((Context)localObject).getSystemService("wifi");
      if (localObject == null) {
        return "";
      }
      localObject = ((WifiManager)localObject).getConnectionInfo();
      if (localObject == null) {
        return "";
      }
      localObject = ((WifiInfo)localObject).getMacAddress();
      return (String)localObject;
    }
    catch (SecurityException localSecurityException)
    {
      f.b("openSDK_LOG.MobileInfoUtil", "getLocalMacAddress>>>", localSecurityException);
    }
    return "";
  }
  
  public static String a(Context paramContext)
  {
    if (!TextUtils.isEmpty(d)) {
      return d;
    }
    if (paramContext == null) {
      return "";
    }
    d = "";
    paramContext = (WindowManager)paramContext.getSystemService("window");
    if (paramContext != null)
    {
      int i = paramContext.getDefaultDisplay().getWidth();
      int j = paramContext.getDefaultDisplay().getHeight();
      d = i + "x" + j;
    }
    return d;
  }
  
  public static String b()
  {
    return Locale.getDefault().getLanguage();
  }
  
  public static String b(Context paramContext)
  {
    if ((a != null) && (a.length() > 0)) {
      return a;
    }
    if (paramContext == null) {
      return "";
    }
    try
    {
      a = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
      paramContext = a;
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "";
  }
  
  public static String c(Context paramContext)
  {
    if ((b != null) && (b.length() > 0)) {
      return b;
    }
    if (paramContext == null) {
      return "";
    }
    try
    {
      b = ((TelephonyManager)paramContext.getSystemService("phone")).getSimSerialNumber();
      paramContext = b;
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "";
  }
  
  public static String d(Context paramContext)
  {
    if ((c != null) && (c.length() > 0)) {
      return c;
    }
    if (paramContext == null) {
      return "";
    }
    try
    {
      c = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      paramContext = c;
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "";
  }
  
  public static String e(Context paramContext)
  {
    try
    {
      Object localObject;
      DisplayMetrics localDisplayMetrics;
      StringBuilder localStringBuilder;
      if (e == null)
      {
        localObject = (WindowManager)paramContext.getSystemService("window");
        localDisplayMetrics = new DisplayMetrics();
        ((WindowManager)localObject).getDefaultDisplay().getMetrics(localDisplayMetrics);
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("imei=").append(b(paramContext)).append('&');
        localStringBuilder.append("model=").append(Build.MODEL).append('&');
        localStringBuilder.append("os=").append(Build.VERSION.RELEASE).append('&');
        localStringBuilder.append("apilevel=").append(Build.VERSION.SDK_INT).append('&');
        localObject = a.b(paramContext);
        if (localObject != null) {
          break label261;
        }
        localObject = "";
        localStringBuilder.append("network=").append((String)localObject).append('&');
        localObject = localStringBuilder.append("sdcard=");
        if (!Environment.getExternalStorageState().equals("mounted")) {
          break label264;
        }
      }
      label261:
      label264:
      for (int i = 1;; i = 0)
      {
        ((StringBuilder)localObject).append(i).append('&');
        localStringBuilder.append("display=").append(localDisplayMetrics.widthPixels).append('*').append(localDisplayMetrics.heightPixels).append('&');
        localStringBuilder.append("manu=").append(Build.MANUFACTURER).append("&");
        localStringBuilder.append("wifi=").append(a.e(paramContext));
        e = localStringBuilder.toString();
        paramContext = e;
        return paramContext;
        break;
      }
      return null;
    }
    catch (Exception paramContext) {}
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\b\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */