package com.tencent.open.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.tencent.open.a.f;
import com.tencent.open.b.a;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import org.json.JSONException;
import org.json.JSONObject;

public class k
{
  private static String a = "";
  private static String b = "";
  private static String c = "";
  private static String d = "";
  private static int e = -1;
  private static String f;
  private static String g = "0123456789ABCDEF";
  
  private static char a(int paramInt)
  {
    paramInt &= 0xF;
    if (paramInt < 10) {}
    for (char c1 = (char)(48 + paramInt);; c1 = (char)(97 + (paramInt - 10))) {
      return c1;
    }
  }
  
  public static Bundle a(String paramString)
  {
    Bundle localBundle = new Bundle();
    localObject = localBundle;
    if (paramString != null) {}
    try
    {
      paramString = paramString.split("&");
      int j = paramString.length;
      int i = 0;
      for (;;)
      {
        localObject = localBundle;
        if (i >= j) {
          break;
        }
        localObject = paramString[i].split("=");
        if (localObject.length == 2) {
          localBundle.putString(URLDecoder.decode(localObject[0]), URLDecoder.decode(localObject[1]));
        }
        i += 1;
      }
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        localObject = null;
      }
    }
    return (Bundle)localObject;
  }
  
  public static Bundle a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    return a(paramString1, paramString3, paramString4, paramString2, paramString5, paramString6, "", "", "", "", "", "");
  }
  
  public static Bundle a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("platform", "1");
    localBundle.putString("result", paramString1);
    localBundle.putString("code", paramString2);
    localBundle.putString("tmcost", paramString3);
    localBundle.putString("rate", paramString4);
    localBundle.putString("cmd", paramString5);
    localBundle.putString("uin", paramString6);
    localBundle.putString("appid", paramString7);
    localBundle.putString("share_type", paramString8);
    localBundle.putString("detail", paramString9);
    localBundle.putString("os_ver", Build.VERSION.RELEASE);
    localBundle.putString("network", a.a(e.a()));
    localBundle.putString("apn", a.b(e.a()));
    localBundle.putString("model_name", Build.MODEL);
    localBundle.putString("sdk_ver", "3.3.0.lite");
    localBundle.putString("packagename", e.b());
    localBundle.putString("app_ver", d(e.a(), e.b()));
    return localBundle;
  }
  
  public static Bundle a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("openid", paramString1);
    localBundle.putString("report_type", paramString2);
    localBundle.putString("act_type", paramString3);
    localBundle.putString("via", paramString4);
    localBundle.putString("app_id", paramString5);
    localBundle.putString("result", paramString6);
    localBundle.putString("type", paramString7);
    localBundle.putString("login_status", paramString8);
    localBundle.putString("need_user_auth", paramString9);
    localBundle.putString("to_uin", paramString10);
    localBundle.putString("call_source", paramString11);
    localBundle.putString("to_type", paramString12);
    return localBundle;
  }
  
  public static String a()
  {
    try
    {
      Object localObject = NetworkInterface.getNetworkInterfaces();
      while ((localObject != null) && (((Enumeration)localObject).hasMoreElements()))
      {
        Enumeration localEnumeration = ((NetworkInterface)((Enumeration)localObject).nextElement()).getInetAddresses();
        while (localEnumeration.hasMoreElements())
        {
          InetAddress localInetAddress = (InetAddress)localEnumeration.nextElement();
          if (!localInetAddress.isLoopbackAddress())
          {
            localObject = localInetAddress.getHostAddress().toString();
            return (String)localObject;
          }
        }
      }
    }
    catch (SocketException localSocketException)
    {
      for (;;)
      {
        f.a("openSDK_LOG.Util", "getUserIp SocketException ", localSocketException);
      }
    }
    return "";
  }
  
  public static final String a(Context paramContext)
  {
    if (paramContext != null)
    {
      paramContext = paramContext.getPackageManager().getApplicationLabel(paramContext.getApplicationInfo());
      if (paramContext != null) {
        return paramContext.toString();
      }
    }
    return null;
  }
  
  public static final String a(String paramString1, int paramInt, String paramString2, String paramString3)
  {
    String str1 = paramString1;
    if (TextUtils.isEmpty(str1)) {
      return "";
    }
    String str2 = "UTF-8";
    if (!TextUtils.isEmpty(paramString2)) {
      str2 = paramString2;
    }
    paramString1 = str1;
    for (;;)
    {
      int j;
      int i;
      try
      {
        if (str1.getBytes(str2).length > paramInt) {
          break label188;
        }
        return str1;
      }
      catch (Exception paramString2)
      {
        int k;
        f.e("openSDK_LOG.Util", "Util.subString has exception: " + paramString2.getMessage());
        return paramString1;
      }
      paramString1 = str1;
      if (j < str1.length())
      {
        paramString1 = str1;
        k = str1.substring(j, j + 1).getBytes(str2).length;
        if (i + k > paramInt)
        {
          paramString1 = str1;
          paramString2 = str1.substring(0, j);
          str1 = paramString2;
          paramString1 = paramString2;
          if (!TextUtils.isEmpty(paramString3))
          {
            paramString1 = paramString2;
            str1 = paramString2 + paramString3;
          }
          return str1;
        }
        i += k;
        j += 1;
      }
      else
      {
        return str1;
        label188:
        i = 0;
        j = 0;
      }
    }
  }
  
  public static String a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfByte.length * 2);
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      String str2 = Integer.toString(paramArrayOfByte[i] & 0xFF, 16);
      String str1 = str2;
      if (str2.length() == 1) {
        str1 = "0" + str2;
      }
      localStringBuilder.append(str1);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static JSONObject a(JSONObject paramJSONObject, String paramString)
  {
    Object localObject = paramJSONObject;
    paramJSONObject = (JSONObject)localObject;
    if (localObject == null) {
      paramJSONObject = new JSONObject();
    }
    if (paramString != null)
    {
      paramString = paramString.split("&");
      int j = paramString.length;
      int i = 0;
      for (;;)
      {
        if (i < j)
        {
          localObject = paramString[i].split("=");
          if (localObject.length == 2) {}
          try
          {
            localObject[0] = URLDecoder.decode(localObject[0]);
            localObject[1] = URLDecoder.decode(localObject[1]);
            paramJSONObject.put(localObject[0], localObject[1]);
            i += 1;
          }
          catch (Exception localException)
          {
            for (;;) {}
          }
          catch (JSONException localJSONException)
          {
            for (;;)
            {
              f.e("openSDK_LOG.Util", "decodeUrlToJson has exception: " + localJSONException.getMessage());
            }
          }
        }
      }
    }
    return paramJSONObject;
  }
  
  private static void a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Intent localIntent = new Intent();
    localIntent.setComponent(new ComponentName(paramString1, paramString2));
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.addFlags(1073741824);
    localIntent.addFlags(268435456);
    localIntent.setData(Uri.parse(paramString3));
    paramContext.startActivity(localIntent);
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    int i = 0;
    try
    {
      boolean bool = f(paramContext);
      if (bool)
      {
        i = bool;
        a(paramContext, "com.tencent.mtt", "com.tencent.mtt.MainActivity", paramString);
      }
      else
      {
        i = bool;
        a(paramContext, "com.android.browser", "com.android.browser.BrowserActivity", paramString);
      }
    }
    catch (Exception localException1)
    {
      if (i != 0) {
        try
        {
          a(paramContext, "com.android.browser", "com.android.browser.BrowserActivity", paramString);
        }
        catch (Exception localException2)
        {
          try
          {
            a(paramContext, "com.google.android.browser", "com.android.browser.BrowserActivity", paramString);
          }
          catch (Exception localException3)
          {
            try
            {
              a(paramContext, "com.android.chrome", "com.google.android.apps.chrome.Main", paramString);
            }
            catch (Exception paramContext)
            {
              return false;
            }
          }
        }
      } else {
        try
        {
          a(paramContext, "com.google.android.browser", "com.android.browser.BrowserActivity", paramString);
        }
        catch (Exception localException4)
        {
          try
          {
            a(paramContext, "com.android.chrome", "com.google.android.apps.chrome.Main", paramString);
          }
          catch (Exception paramContext)
          {
            return false;
          }
        }
      }
    }
    return true;
  }
  
  public static boolean a(Context paramContext, boolean paramBoolean)
  {
    if ((d(paramContext)) && (h.a(paramContext, "com.tencent.minihd.qq") != null)) {
      return true;
    }
    if (!paramBoolean)
    {
      if ((h.c(paramContext, "4.1") >= 0) || (h.a(paramContext, "com.tencent.tim") != null)) {}
      for (paramBoolean = true;; paramBoolean = false) {
        return paramBoolean;
      }
    }
    if ((h.c(paramContext, "4.1") >= 0) || (h.a(paramContext, "com.tencent.tim") != null)) {}
    for (paramBoolean = true;; paramBoolean = false) {
      return paramBoolean;
    }
  }
  
  public static Bundle b(String paramString)
  {
    paramString = paramString.replace("auth://", "http://");
    try
    {
      paramString = new URL(paramString);
      Bundle localBundle = a(paramString.getQuery());
      localBundle.putAll(a(paramString.getRef()));
      return localBundle;
    }
    catch (MalformedURLException paramString) {}
    return new Bundle();
  }
  
  public static void b(Context paramContext, String paramString)
  {
    if (paramContext == null) {
      return;
    }
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      b = paramContext.versionName;
      a = b.substring(0, b.lastIndexOf('.'));
      d = b.substring(b.lastIndexOf('.') + 1, b.length());
      e = paramContext.versionCode;
      return;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      f.e("openSDK_LOG.Util", "getPackageInfo has exception: " + paramContext.getMessage());
      return;
    }
    catch (Exception paramContext)
    {
      f.e("openSDK_LOG.Util", "getPackageInfo has exception: " + paramContext.getMessage());
    }
  }
  
  public static boolean b()
  {
    File localFile = null;
    if (Environment.getExternalStorageState().equals("mounted")) {
      localFile = Environment.getExternalStorageDirectory();
    }
    return localFile != null;
  }
  
  public static boolean b(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null) {
      return true;
    }
    paramContext = paramContext.getAllNetworkInfo();
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.length)
      {
        if (paramContext[i].isConnectedOrConnecting()) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public static String c(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    try
    {
      paramContext = (LocationManager)paramContext.getSystemService("location");
      Object localObject = new Criteria();
      ((Criteria)localObject).setCostAllowed(false);
      ((Criteria)localObject).setAccuracy(2);
      localObject = paramContext.getBestProvider((Criteria)localObject, true);
      if (localObject != null)
      {
        paramContext = paramContext.getLastKnownLocation((String)localObject);
        if (paramContext == null) {
          return "";
        }
        double d1 = paramContext.getLatitude();
        double d2 = paramContext.getLongitude();
        f = d1 + "*" + d2;
        paramContext = f;
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        f.b("openSDK_LOG.Util", "getLocation>>>", paramContext);
      }
    }
    return "";
  }
  
  public static String c(Context paramContext, String paramString)
  {
    if (paramContext == null) {
      return "";
    }
    b(paramContext, paramString);
    return b;
  }
  
  public static JSONObject c(String paramString)
  {
    paramString = paramString.replace("auth://", "http://");
    try
    {
      paramString = new URL(paramString);
      JSONObject localJSONObject = a(null, paramString.getQuery());
      a(localJSONObject, paramString.getRef());
      return localJSONObject;
    }
    catch (MalformedURLException paramString) {}
    return new JSONObject();
  }
  
  public static String d(Context paramContext, String paramString)
  {
    if (paramContext == null) {
      return "";
    }
    b(paramContext, paramString);
    return a;
  }
  
  public static JSONObject d(String paramString)
    throws JSONException
  {
    String str = paramString;
    paramString = str;
    if (str.equals("false")) {
      paramString = "{value : false}";
    }
    str = paramString;
    if (paramString.equals("true")) {
      str = "{value : true}";
    }
    paramString = str;
    if (str.contains("allback(")) {
      paramString = str.replaceFirst("[\\s\\S]*allback\\(([\\s\\S]*)\\);[^\\)]*\\z", "$1").trim();
    }
    str = paramString;
    if (paramString.contains("online[0]=")) {
      str = "{online:" + paramString.charAt(paramString.length() - 2) + "}";
    }
    return new JSONObject(str);
  }
  
  public static boolean d(Context paramContext)
  {
    double d1 = 0.0D;
    try
    {
      paramContext = paramContext.getResources().getDisplayMetrics();
      float f1 = paramContext.widthPixels / paramContext.xdpi;
      float f2 = paramContext.heightPixels / paramContext.ydpi;
      double d2 = Math.sqrt(Math.pow(f1, 2.0D) + Math.pow(f2, 2.0D));
      d1 = d2;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    return d1 > 6.5D;
  }
  
  public static String e(Context paramContext, String paramString)
  {
    if (paramContext == null) {
      return "";
    }
    c = d(paramContext, paramString);
    return c;
  }
  
  public static boolean e(Context paramContext)
  {
    if ((h.c(paramContext, "5.9.5") >= 0) || (h.a(paramContext, "com.tencent.tim") != null)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean e(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static String f(String paramString)
  {
    str = paramString;
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(i(paramString));
      localObject = ((MessageDigest)localObject).digest();
      paramString = str;
      if (localObject != null)
      {
        paramString = new StringBuilder();
        int j = localObject.length;
        int i = 0;
        while (i < j)
        {
          int k = localObject[i];
          paramString.append(a(k >>> 4));
          paramString.append(a(k));
          i += 1;
        }
        paramString = paramString.toString();
      }
    }
    catch (NoSuchAlgorithmException paramString)
    {
      for (;;)
      {
        f.e("openSDK_LOG.Util", "encrypt has exception: " + paramString.getMessage());
        paramString = str;
      }
    }
    return paramString;
  }
  
  private static boolean f(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo("com.tencent.mtt", 64);
      Object localObject = paramContext.versionName;
      if ((h.a((String)localObject, "4.3") >= 0) && (!((String)localObject).startsWith("4.4")))
      {
        localObject = paramContext.signatures;
        if (localObject == null) {}
      }
      try
      {
        paramContext = MessageDigest.getInstance("MD5");
        paramContext.update(localObject[0].toByteArray());
        localObject = a(paramContext.digest());
        paramContext.reset();
        boolean bool = ((String)localObject).equals("d8391a394d4a179e6fe7bdb8a301258b");
        if (bool) {
          return true;
        }
      }
      catch (NoSuchAlgorithmException paramContext)
      {
        for (;;)
        {
          f.e("openSDK_LOG.Util", "isQQBrowerAvailable has exception: " + paramContext.getMessage());
        }
      }
      return false;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean f(Context paramContext, String paramString)
  {
    boolean bool2 = true;
    boolean bool1 = bool2;
    if (d(paramContext))
    {
      bool1 = bool2;
      if (h.a(paramContext, "com.tencent.minihd.qq") != null) {
        bool1 = false;
      }
    }
    bool2 = bool1;
    if (bool1)
    {
      bool2 = bool1;
      if (h.a(paramContext, "com.tencent.tim") != null) {
        bool2 = false;
      }
    }
    bool1 = bool2;
    if (bool2) {
      if (h.c(paramContext, paramString) >= 0) {
        break label63;
      }
    }
    label63:
    for (bool1 = true;; bool1 = false) {
      return bool1;
    }
  }
  
  public static boolean g(Context paramContext, String paramString)
  {
    boolean bool2 = true;
    boolean bool1 = bool2;
    if (d(paramContext))
    {
      bool1 = bool2;
      if (h.a(paramContext, "com.tencent.minihd.qq") != null) {
        bool1 = false;
      }
    }
    bool2 = bool1;
    if (bool1)
    {
      bool2 = bool1;
      if (h.a(paramContext, "com.tencent.tim") != null) {
        bool2 = false;
      }
    }
    bool1 = bool2;
    if (bool2) {
      if (h.c(paramContext, paramString) >= 0) {
        break label63;
      }
    }
    label63:
    for (bool1 = true;; bool1 = false) {
      return bool1;
    }
  }
  
  public static final boolean g(String paramString)
  {
    if (paramString == null) {
      return false;
    }
    if ((paramString.startsWith("http://")) || (paramString.startsWith("https://"))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean h(String paramString)
  {
    if (paramString == null) {
      return false;
    }
    paramString = new File(paramString);
    return (paramString != null) && (paramString.exists());
  }
  
  public static byte[] i(String paramString)
  {
    try
    {
      paramString = paramString.getBytes("UTF-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString) {}
    return null;
  }
  
  public static class a
  {
    public String a;
    public long b;
    public long c;
    
    public a(String paramString, int paramInt)
    {
      this.a = paramString;
      this.b = paramInt;
      if (this.a != null) {
        this.c = this.a.length();
      }
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\utils\k.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */