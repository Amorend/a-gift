package com.tencent.open.b;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import com.tencent.open.a.f;

public class a
{
  protected static final Uri a = Uri.parse("content://telephony/carriers/preferapn");
  
  public static String a(Context paramContext)
  {
    int i = d(paramContext);
    if (i == 2) {
      return "wifi";
    }
    if (i == 1) {
      return "cmwap";
    }
    if (i == 4) {
      return "cmnet";
    }
    if (i == 16) {
      return "uniwap";
    }
    if (i == 8) {
      return "uninet";
    }
    if (i == 64) {
      return "wap";
    }
    if (i == 32) {
      return "net";
    }
    if (i == 512) {
      return "ctwap";
    }
    if (i == 256) {
      return "ctnet";
    }
    if (i == 2048) {
      return "3gnet";
    }
    if (i == 1024) {
      return "3gwap";
    }
    paramContext = b(paramContext);
    if ((paramContext == null) || (paramContext.length() == 0)) {
      return "none";
    }
    return paramContext;
  }
  
  public static String b(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getContentResolver().query(a, null, null, null, null);
      if (paramContext == null) {
        return null;
      }
      paramContext.moveToFirst();
      if (paramContext.isAfterLast())
      {
        if (paramContext != null) {
          paramContext.close();
        }
      }
      else
      {
        String str = paramContext.getString(paramContext.getColumnIndex("apn"));
        if (paramContext != null) {
          paramContext.close();
        }
        return str;
      }
    }
    catch (SecurityException paramContext)
    {
      f.e("openSDK_LOG.APNUtil", "getApn has exception: " + paramContext.getMessage());
      return "";
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        f.e("openSDK_LOG.APNUtil", "getApn has exception: " + paramContext.getMessage());
      }
    }
    return null;
  }
  
  public static String c(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getContentResolver().query(a, null, null, null, null);
      if (paramContext == null) {
        return null;
      }
      paramContext.moveToFirst();
      if (paramContext.isAfterLast())
      {
        if (paramContext != null) {
          paramContext.close();
        }
      }
      else
      {
        String str = paramContext.getString(paramContext.getColumnIndex("proxy"));
        if (paramContext != null) {
          paramContext.close();
        }
        return str;
      }
    }
    catch (SecurityException paramContext)
    {
      f.e("openSDK_LOG.APNUtil", "getApnProxy has exception: " + paramContext.getMessage());
      return "";
    }
    return null;
  }
  
  public static int d(Context paramContext)
  {
    try
    {
      Object localObject = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (localObject == null) {
        return 128;
      }
      localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
      if (localObject == null) {
        return 128;
      }
      if (((NetworkInfo)localObject).getTypeName().toUpperCase().equals("WIFI")) {
        return 2;
      }
      localObject = ((NetworkInfo)localObject).getExtraInfo().toLowerCase();
      if (((String)localObject).startsWith("cmwap")) {
        return 1;
      }
      if ((((String)localObject).startsWith("cmnet")) || (((String)localObject).startsWith("epc.tmobile.com"))) {
        break label258;
      }
      if (((String)localObject).startsWith("uniwap")) {
        return 16;
      }
      if (((String)localObject).startsWith("uninet")) {
        return 8;
      }
      if (((String)localObject).startsWith("wap")) {
        return 64;
      }
      if (((String)localObject).startsWith("net")) {
        return 32;
      }
      if (((String)localObject).startsWith("ctwap")) {
        return 512;
      }
      if (((String)localObject).startsWith("ctnet")) {
        return 256;
      }
      if (((String)localObject).startsWith("3gwap")) {
        return 1024;
      }
      if (((String)localObject).startsWith("3gnet")) {
        return 2048;
      }
      if (((String)localObject).startsWith("#777"))
      {
        paramContext = c(paramContext);
        if (paramContext != null)
        {
          int i = paramContext.length();
          if (i > 0) {
            return 512;
          }
        }
        return 256;
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        f.e("openSDK_LOG.APNUtil", "getMProxyType has exception: " + paramContext.getMessage());
      }
    }
    return 128;
    label258:
    return 4;
  }
  
  public static String e(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null) {
      return "MOBILE";
    }
    paramContext = paramContext.getActiveNetworkInfo();
    if (paramContext != null) {
      return paramContext.getTypeName();
    }
    return "MOBILE";
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\b\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */