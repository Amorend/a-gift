package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public class dc
{
  private static String a;
  private static String b;
  private static final Pattern c = Pattern.compile("\\s*|\t|\r|\n");
  
  /* Error */
  public static String a()
  {
    // Byte code:
    //   0: new 29	java/lang/StringBuffer
    //   3: dup
    //   4: invokespecial 32	java/lang/StringBuffer:<init>	()V
    //   7: astore 4
    //   9: bipush 20
    //   11: newarray <illegal type>
    //   13: astore 5
    //   15: new 34	java/io/InputStreamReader
    //   18: dup
    //   19: new 36	java/io/FileInputStream
    //   22: dup
    //   23: ldc 38
    //   25: invokespecial 41	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   28: invokespecial 44	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   31: astore_3
    //   32: aload_3
    //   33: astore_2
    //   34: aload_3
    //   35: aload 5
    //   37: invokevirtual 50	java/io/Reader:read	([C)I
    //   40: istore_1
    //   41: iload_1
    //   42: iconst_m1
    //   43: if_icmpeq +69 -> 112
    //   46: aload_3
    //   47: astore_2
    //   48: iload_1
    //   49: aload 5
    //   51: arraylength
    //   52: if_icmpne +162 -> 214
    //   55: aload_3
    //   56: astore_2
    //   57: aload 5
    //   59: aload 5
    //   61: arraylength
    //   62: iconst_1
    //   63: isub
    //   64: caload
    //   65: bipush 13
    //   67: if_icmpeq +147 -> 214
    //   70: aload_3
    //   71: astore_2
    //   72: getstatic 56	java/lang/System:out	Ljava/io/PrintStream;
    //   75: aload 5
    //   77: invokevirtual 62	java/io/PrintStream:print	([C)V
    //   80: goto -48 -> 32
    //   83: iload_0
    //   84: iload_1
    //   85: if_icmpge -53 -> 32
    //   88: aload 5
    //   90: iload_0
    //   91: caload
    //   92: bipush 13
    //   94: if_icmpeq +125 -> 219
    //   97: aload_3
    //   98: astore_2
    //   99: aload 4
    //   101: aload 5
    //   103: iload_0
    //   104: caload
    //   105: invokevirtual 66	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   108: pop
    //   109: goto +110 -> 219
    //   112: aload_3
    //   113: astore_2
    //   114: aload 4
    //   116: invokevirtual 69	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   119: invokevirtual 74	java/lang/String:trim	()Ljava/lang/String;
    //   122: ldc 76
    //   124: ldc 78
    //   126: invokevirtual 82	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   129: astore 4
    //   131: aload_3
    //   132: ifnull +15 -> 147
    //   135: aload_3
    //   136: invokevirtual 85	java/io/Reader:close	()V
    //   139: aload 4
    //   141: areturn
    //   142: astore_2
    //   143: aload_2
    //   144: invokestatic 90	com/baidu/mobstat/cz:a	(Ljava/lang/Throwable;)V
    //   147: aload 4
    //   149: areturn
    //   150: astore 4
    //   152: goto +13 -> 165
    //   155: astore_2
    //   156: aconst_null
    //   157: astore_3
    //   158: goto +38 -> 196
    //   161: astore 4
    //   163: aconst_null
    //   164: astore_3
    //   165: aload_3
    //   166: astore_2
    //   167: aload 4
    //   169: invokestatic 90	com/baidu/mobstat/cz:a	(Ljava/lang/Throwable;)V
    //   172: aload_3
    //   173: ifnull +14 -> 187
    //   176: aload_3
    //   177: invokevirtual 85	java/io/Reader:close	()V
    //   180: aconst_null
    //   181: areturn
    //   182: astore_2
    //   183: aload_2
    //   184: invokestatic 90	com/baidu/mobstat/cz:a	(Ljava/lang/Throwable;)V
    //   187: aconst_null
    //   188: areturn
    //   189: astore 4
    //   191: aload_2
    //   192: astore_3
    //   193: aload 4
    //   195: astore_2
    //   196: aload_3
    //   197: ifnull +15 -> 212
    //   200: aload_3
    //   201: invokevirtual 85	java/io/Reader:close	()V
    //   204: goto +8 -> 212
    //   207: astore_3
    //   208: aload_3
    //   209: invokestatic 90	com/baidu/mobstat/cz:a	(Ljava/lang/Throwable;)V
    //   212: aload_2
    //   213: athrow
    //   214: iconst_0
    //   215: istore_0
    //   216: goto -133 -> 83
    //   219: iload_0
    //   220: iconst_1
    //   221: iadd
    //   222: istore_0
    //   223: goto -140 -> 83
    // Local variable table:
    //   start	length	slot	name	signature
    //   83	140	0	i	int
    //   40	46	1	j	int
    //   33	81	2	localObject1	Object
    //   142	2	2	localIOException1	java.io.IOException
    //   155	1	2	localObject2	Object
    //   166	1	2	localObject3	Object
    //   182	10	2	localIOException2	java.io.IOException
    //   195	18	2	localObject4	Object
    //   31	170	3	localObject5	Object
    //   207	2	3	localIOException3	java.io.IOException
    //   7	141	4	localObject6	Object
    //   150	1	4	localException1	Exception
    //   161	7	4	localException2	Exception
    //   189	5	4	localObject7	Object
    //   13	89	5	arrayOfChar	char[]
    // Exception table:
    //   from	to	target	type
    //   135	139	142	java/io/IOException
    //   34	41	150	java/lang/Exception
    //   48	55	150	java/lang/Exception
    //   57	70	150	java/lang/Exception
    //   72	80	150	java/lang/Exception
    //   99	109	150	java/lang/Exception
    //   114	131	150	java/lang/Exception
    //   9	32	155	finally
    //   9	32	161	java/lang/Exception
    //   176	180	182	java/io/IOException
    //   34	41	189	finally
    //   48	55	189	finally
    //   57	70	189	finally
    //   72	80	189	finally
    //   99	109	189	finally
    //   114	131	189	finally
    //   167	172	189	finally
    //   200	204	207	java/io/IOException
  }
  
  private static String a(byte paramByte)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("00");
    ((StringBuilder)localObject).append(Integer.toHexString(paramByte));
    ((StringBuilder)localObject).append(":");
    localObject = ((StringBuilder)localObject).toString();
    return ((String)localObject).substring(((String)localObject).length() - 3);
  }
  
  public static String a(int paramInt, Context paramContext)
  {
    try
    {
      paramContext = cr.c(paramInt, a(paramContext).getBytes());
      return paramContext;
    }
    catch (Exception paramContext)
    {
      cz.a(paramContext);
    }
    return "";
  }
  
  public static String a(Context paramContext)
  {
    paramContext = de.a(paramContext);
    return c.matcher(paramContext).replaceAll("");
  }
  
  public static String a(Context paramContext, int paramInt)
  {
    paramContext = u(paramContext);
    if (TextUtils.isEmpty(paramContext)) {
      return "";
    }
    return cr.c(paramInt, paramContext.getBytes());
  }
  
  public static String a(Context paramContext, String paramString)
  {
    String str = "";
    Object localObject = paramContext.getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getApplicationInfo(paramContext.getPackageName(), 128);
      paramContext = str;
      if (localObject != null)
      {
        paramContext = null;
        if (((ApplicationInfo)localObject).metaData != null) {
          paramContext = ((ApplicationInfo)localObject).metaData.get(paramString);
        }
        if (paramContext == null)
        {
          paramContext = new StringBuilder();
          paramContext.append("null,can't find information for key:");
          paramContext.append(paramString);
          cz.a(paramContext.toString());
          return "";
        }
        paramContext = paramContext.toString();
      }
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "";
  }
  
  public static int b(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    try
    {
      paramContext = d(paramContext);
    }
    catch (Exception paramContext)
    {
      cz.a(paramContext);
      paramContext = localDisplayMetrics;
    }
    return paramContext.widthPixels;
  }
  
  @TargetApi(9)
  private static String b()
  {
    if (Build.VERSION.SDK_INT < 9) {
      return "";
    }
    try
    {
      Object localObject1 = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Object localObject2 = (NetworkInterface)((Iterator)localObject1).next();
        if (((NetworkInterface)localObject2).getName().equalsIgnoreCase("wlan0"))
        {
          localObject1 = ((NetworkInterface)localObject2).getHardwareAddress();
          if (localObject1 == null) {
            return "";
          }
          localObject2 = new StringBuilder();
          int j = localObject1.length;
          int i = 0;
          while (i < j)
          {
            ((StringBuilder)localObject2).append(String.format("%02x:", new Object[] { Byte.valueOf(localObject1[i]) }));
            i += 1;
          }
          if (((StringBuilder)localObject2).length() > 0) {
            ((StringBuilder)localObject2).deleteCharAt(((StringBuilder)localObject2).length() - 1);
          }
          localObject1 = ((StringBuilder)localObject2).toString();
          return (String)localObject1;
        }
      }
    }
    catch (Throwable localThrowable)
    {
      cz.b(localThrowable);
    }
    return "";
  }
  
  public static String b(int paramInt, Context paramContext)
  {
    paramContext = j(paramContext);
    if (TextUtils.isEmpty(paramContext)) {
      return "";
    }
    return cr.c(paramInt, paramContext.getBytes());
  }
  
  private static String b(Context paramContext, String paramString)
  {
    Object localObject = null;
    if (paramString == null) {
      return null;
    }
    int i = paramString.lastIndexOf(':');
    paramContext = (Context)localObject;
    if (i > 0)
    {
      i += 1;
      paramContext = (Context)localObject;
      if (i < paramString.length()) {
        paramContext = paramString.substring(i);
      }
    }
    return paramContext;
  }
  
  public static int c(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    try
    {
      paramContext = d(paramContext);
    }
    catch (Exception paramContext)
    {
      cz.a(paramContext);
      paramContext = localDisplayMetrics;
    }
    return paramContext.heightPixels;
  }
  
  public static String c(int paramInt, Context paramContext)
  {
    paramContext = d(paramInt, paramContext);
    if (!TextUtils.isEmpty(paramContext)) {
      paramContext = cr.c(paramInt, paramContext.getBytes());
    } else {
      paramContext = null;
    }
    Object localObject = paramContext;
    if (TextUtils.isEmpty(paramContext)) {
      localObject = "";
    }
    return (String)localObject;
  }
  
  private static String c(Context paramContext, String paramString)
  {
    paramContext = paramContext.getApplicationInfo();
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.processName;
    if ((paramContext != null) && (!paramContext.equals(paramString))) {
      return paramString;
    }
    return null;
  }
  
  public static DisplayMetrics d(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }
  
  public static String d(int paramInt, Context paramContext)
  {
    String str2 = a();
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = e(paramInt, paramContext);
    }
    paramContext = str1;
    if (TextUtils.isEmpty(str1)) {
      paramContext = "";
    }
    return paramContext;
  }
  
  public static int e(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = paramContext.getPackageName();
    try
    {
      int i = localPackageManager.getPackageInfo(paramContext, 0).versionCode;
      return i;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    cz.b("Get app version code exception");
    return 1;
  }
  
  @SuppressLint({"NewApi"})
  public static String e(int paramInt, Context paramContext)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Object localObject2 = null;
    Object localObject1 = null;
    try
    {
      Enumeration localEnumeration1 = NetworkInterface.getNetworkInterfaces();
      Object localObject3;
      for (;;)
      {
        localObject2 = localObject1;
        localObject3 = localObject1;
        if (!localEnumeration1.hasMoreElements()) {
          break;
        }
        localObject2 = localObject1;
        NetworkInterface localNetworkInterface = (NetworkInterface)localEnumeration1.nextElement();
        localObject2 = localObject1;
        Enumeration localEnumeration2 = localNetworkInterface.getInetAddresses();
        localObject3 = localObject1;
        label145:
        do
        {
          for (;;)
          {
            localObject1 = localObject3;
            localObject2 = localObject3;
            if (!localEnumeration2.hasMoreElements()) {
              break;
            }
            localObject2 = localObject3;
            localObject1 = (InetAddress)localEnumeration2.nextElement();
            localObject2 = localObject3;
            if (!((InetAddress)localObject1).isAnyLocalAddress())
            {
              localObject2 = localObject3;
              if ((localObject1 instanceof Inet4Address))
              {
                localObject2 = localObject3;
                if (!((InetAddress)localObject1).isLoopbackAddress())
                {
                  localObject2 = localObject3;
                  if (!((InetAddress)localObject1).isSiteLocalAddress()) {
                    break label145;
                  }
                  localObject2 = localObject3;
                  localObject3 = localNetworkInterface.getHardwareAddress();
                }
              }
            }
          }
          localObject2 = localObject3;
        } while (((InetAddress)localObject1).isLinkLocalAddress());
        localObject2 = localObject3;
        localObject1 = localNetworkInterface.getHardwareAddress();
      }
      String str;
      return paramContext;
    }
    catch (Exception localException)
    {
      cz.a(localException);
      localObject3 = localObject2;
      if (localObject3 != null)
      {
        paramInt = 0;
        while (paramInt < localObject3.length)
        {
          localStringBuffer.append(a(localObject3[paramInt]));
          paramInt += 1;
        }
        return localStringBuffer.substring(0, localStringBuffer.length() - 1).replaceAll(":", "");
      }
      str = b(paramInt, paramContext);
      paramContext = str;
      if (str != null) {
        paramContext = str.replaceAll(":", "");
      }
    }
  }
  
  public static String f(int paramInt, Context paramContext)
  {
    paramContext = k(paramContext);
    if (TextUtils.isEmpty(paramContext)) {
      return "";
    }
    return cr.c(paramInt, paramContext.getBytes());
  }
  
  public static String f(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = paramContext.getPackageName();
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramContext, 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    cz.b("get app version name exception");
    return "";
  }
  
  public static String g(int paramInt, Context paramContext)
  {
    paramContext = m(paramContext);
    if (TextUtils.isEmpty(paramContext)) {
      return "";
    }
    return cr.d(paramInt, paramContext.getBytes());
  }
  
  public static String g(Context paramContext)
  {
    String str = String.format("%s_%s_%s", new Object[] { Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0) });
    try
    {
      if ((cs.e(paramContext, "android.permission.ACCESS_FINE_LOCATION")) || (cs.e(paramContext, "android.permission.ACCESS_COARSE_LOCATION")))
      {
        paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getCellLocation();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramContext);
        localStringBuilder.append("");
        cz.a(localStringBuilder.toString());
        if (paramContext == null) {
          return str;
        }
        if ((paramContext instanceof GsmCellLocation))
        {
          paramContext = (GsmCellLocation)paramContext;
          return String.format("%s_%s_%s", new Object[] { String.format("%d", new Object[] { Integer.valueOf(paramContext.getCid()) }), String.format("%d", new Object[] { Integer.valueOf(paramContext.getLac()) }), Integer.valueOf(0) });
        }
        paramContext = paramContext.toString().replace("[", "").replace("]", "").split(",");
        paramContext = String.format("%s_%s_%s", new Object[] { paramContext[0], paramContext[3], paramContext[4] });
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      cz.a("Get Location", paramContext);
    }
    return str;
  }
  
  public static String h(int paramInt, Context paramContext)
  {
    paramContext = p(paramContext);
    if (!TextUtils.isEmpty(paramContext)) {
      try
      {
        paramContext = cr.c(paramInt, paramContext.getBytes());
        return paramContext;
      }
      catch (Exception paramContext)
      {
        cz.b(paramContext);
      }
    }
    return "";
  }
  
  public static String h(Context paramContext)
  {
    try
    {
      if (cs.e(paramContext, "android.permission.ACCESS_FINE_LOCATION"))
      {
        paramContext = ((LocationManager)paramContext.getSystemService("location")).getLastKnownLocation("gps");
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("location: ");
        localStringBuilder.append(paramContext);
        cz.b(localStringBuilder.toString());
        if (paramContext != null)
        {
          paramContext = String.format("%s_%s_%s", new Object[] { Long.valueOf(paramContext.getTime()), Double.valueOf(paramContext.getLongitude()), Double.valueOf(paramContext.getLatitude()) });
          return paramContext;
        }
      }
    }
    catch (Exception paramContext)
    {
      cz.b(paramContext);
    }
    return "";
  }
  
  public static String i(Context paramContext)
  {
    if (Build.VERSION.SDK_INT < 23) {
      return j(paramContext);
    }
    return b();
  }
  
  public static String j(Context paramContext)
  {
    try
    {
      if (cs.e(paramContext, "android.permission.ACCESS_WIFI_STATE"))
      {
        paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
        if (paramContext != null)
        {
          paramContext = paramContext.getMacAddress();
          if (!TextUtils.isEmpty(paramContext)) {
            return paramContext;
          }
        }
      }
      else
      {
        cz.c("You need the android.Manifest.permission.ACCESS_WIFI_STATE permission. Open AndroidManifest.xml and just before the final </manifest> tag add: android.permission.ACCESS_WIFI_STATE");
      }
    }
    catch (Exception paramContext)
    {
      cz.b(paramContext);
    }
    return "";
  }
  
  @SuppressLint({"NewApi"})
  public static String k(Context paramContext)
  {
    String str = Build.BRAND;
    if (("4.1.1".equals(Build.VERSION.RELEASE)) && ("TCT".equals(str))) {
      return "";
    }
    try
    {
      if (cs.e(paramContext, "android.permission.BLUETOOTH"))
      {
        paramContext = BluetoothAdapter.getDefaultAdapter();
        if (paramContext != null)
        {
          paramContext = paramContext.getAddress();
          if (paramContext != null) {
            return paramContext;
          }
        }
      }
    }
    catch (Exception paramContext)
    {
      cz.b(paramContext);
    }
    return "";
  }
  
  public static String l(Context paramContext)
  {
    paramContext = m(paramContext);
    if (TextUtils.isEmpty(paramContext)) {
      return "";
    }
    return cq.a(paramContext.getBytes());
  }
  
  public static String m(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    if (!cs.e(paramContext, "android.permission.ACCESS_WIFI_STATE")) {
      return "";
    }
    int k = 0;
    try
    {
      if (cs.e(paramContext, "android.permission.ACCESS_FINE_LOCATION")) {
        bool = ((LocationManager)paramContext.getSystemService("location")).isProviderEnabled("gps");
      }
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        cz.a(localException1);
      }
    }
    boolean bool = false;
    try
    {
      localObject1 = (WifiManager)paramContext.getSystemService("wifi");
      localObject2 = ((WifiManager)localObject1).getConnectionInfo();
      try
      {
        localObject1 = ((WifiManager)localObject1).getScanResults();
      }
      catch (Throwable localThrowable1)
      {
        localObject1 = localObject2;
      }
      cz.a(localThrowable2);
    }
    catch (Throwable localThrowable2)
    {
      localObject1 = null;
    }
    Object localObject3 = null;
    Object localObject2 = localObject1;
    Object localObject1 = localObject3;
    if ((localObject1 != null) && (((List)localObject1).size() != 0)) {
      Collections.sort((List)localObject1, new dd());
    }
    JSONArray localJSONArray = new JSONArray();
    int i = 0;
    int j = 1;
    if ((localObject1 != null) && (i < ((List)localObject1).size()) && (i < 30)) {}
    for (;;)
    {
      try
      {
        ScanResult localScanResult = (ScanResult)((List)localObject1).get(i);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(localScanResult.BSSID);
        localStringBuilder.append("|");
        String str = localScanResult.SSID.replaceAll("\\|", "");
        localObject3 = str;
        if (str.length() > 30) {
          localObject3 = str.substring(0, 30);
        }
        localStringBuilder.append((String)localObject3);
        localStringBuilder.append("|");
        localStringBuilder.append(localScanResult.level);
        localStringBuilder.append("|");
        if ((localObject2 == null) || (!localScanResult.BSSID.equals(((WifiInfo)localObject2).getBSSID()))) {
          break label489;
        }
        localStringBuilder.append(j);
        localJSONArray.put(localStringBuilder.toString());
      }
      catch (Exception localException2)
      {
        cz.a(localException2);
      }
      i += 1;
      break;
      if (localJSONArray.length() == 0) {
        return null;
      }
      localObject1 = new JSONObject();
      try
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(System.currentTimeMillis());
        ((StringBuilder)localObject2).append("|");
        i = k;
        if (bool) {
          i = 1;
        }
        ((StringBuilder)localObject2).append(i);
        ((StringBuilder)localObject2).append("|");
        ((StringBuilder)localObject2).append(h(paramContext));
        ((JSONObject)localObject1).put("ap-list", localJSONArray);
        ((JSONObject)localObject1).put("meta-data", ((StringBuilder)localObject2).toString());
        paramContext = ((JSONObject)localObject1).toString();
        return paramContext;
      }
      catch (Exception paramContext)
      {
        cz.a(paramContext);
        return "";
      }
      label489:
      j = 0;
    }
  }
  
  public static boolean n(Context paramContext)
  {
    if (paramContext != null) {
      try
      {
        paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getNetworkInfo(1);
        if ((paramContext != null) && (paramContext.isAvailable()))
        {
          boolean bool = paramContext.isConnected();
          if (bool) {
            return true;
          }
        }
      }
      catch (Exception paramContext)
      {
        cz.a(paramContext);
      }
    }
    return false;
  }
  
  public static String o(Context paramContext)
  {
    String str = "";
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      paramContext = str;
      if (localNetworkInfo != null)
      {
        paramContext = localNetworkInfo.getTypeName();
        try
        {
          if ((!paramContext.equals("WIFI")) && (localNetworkInfo.getSubtypeName() != null))
          {
            str = localNetworkInfo.getSubtypeName();
            return str;
          }
          return paramContext;
        }
        catch (Exception localException1) {}
        cz.a(localException2);
      }
    }
    catch (Exception localException2)
    {
      paramContext = str;
    }
    return paramContext;
  }
  
  public static String p(Context paramContext)
  {
    if (paramContext != null) {
      return paramContext.getPackageName();
    }
    return "";
  }
  
  public static String q(Context paramContext)
  {
    String str1 = b;
    Object localObject = str1;
    if (str1 == null)
    {
      String str2 = v(paramContext);
      str1 = b(paramContext, str2);
      localObject = str1;
      if (TextUtils.isEmpty(str1)) {
        localObject = c(paramContext, str2);
      }
      if (localObject == null) {
        paramContext = "";
      } else {
        paramContext = (Context)localObject;
      }
      b = paramContext;
      localObject = paramContext;
    }
    return (String)localObject;
  }
  
  public static String r(Context paramContext)
  {
    Object localObject1 = "";
    String str = v(paramContext);
    if (str == null) {
      return "";
    }
    Object localObject2 = paramContext.getPackageManager();
    try
    {
      paramContext = ((PackageManager)localObject2).getPackageInfo(paramContext.getPackageName(), 4);
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      int j;
      int i;
      for (;;) {}
    }
    paramContext = null;
    if (paramContext == null) {
      return "";
    }
    localObject2 = paramContext.services;
    if (localObject2 == null) {
      return "";
    }
    j = localObject2.length;
    i = 0;
    for (;;)
    {
      paramContext = (Context)localObject1;
      if (i >= j) {
        break;
      }
      paramContext = localObject2[i];
      if (str.equals(paramContext.processName))
      {
        paramContext = paramContext.name;
        break;
      }
      i += 1;
    }
    localObject1 = paramContext;
    if (paramContext == null) {
      localObject1 = "";
    }
    return (String)localObject1;
  }
  
  public static boolean s(Context paramContext)
  {
    if (paramContext != null) {
      try
      {
        boolean bool = paramContext.getPackageManager().hasSystemFeature("android.hardware.type.watch");
        if (bool) {
          return true;
        }
      }
      catch (Exception paramContext)
      {
        cz.b(paramContext);
      }
    }
    return false;
  }
  
  public static String t(Context paramContext)
  {
    try
    {
      Object localObject = (ActivityManager)paramContext.getSystemService("activity");
      paramContext = new ActivityManager.MemoryInfo();
      ((ActivityManager)localObject).getMemoryInfo(paramContext);
      localObject = new JSONObject();
      ((JSONObject)localObject).put("m", paramContext.availMem);
      ((JSONObject)localObject).put("l", paramContext.lowMemory);
      ((JSONObject)localObject).put("t", paramContext.threshold);
      paramContext = new JSONArray();
      paramContext.put(localObject);
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(System.currentTimeMillis());
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("app_mem", paramContext);
      localJSONObject.put("meta-data", ((StringBuilder)localObject).toString());
      paramContext = cq.a(localJSONObject.toString().getBytes());
      return paramContext;
    }
    catch (Exception paramContext)
    {
      cz.a(paramContext);
    }
    return "";
  }
  
  private static String u(Context paramContext)
  {
    try
    {
      paramContext = BluetoothAdapter.getDefaultAdapter();
      if (paramContext != null)
      {
        paramContext = paramContext.getName();
        if (paramContext != null) {
          return paramContext;
        }
      }
    }
    catch (Exception paramContext)
    {
      cz.b(paramContext);
    }
    return "";
  }
  
  private static String v(Context paramContext)
  {
    Object localObject1 = a;
    localObject2 = localObject1;
    if (localObject1 == null) {
      try
      {
        localObject2 = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
        int i = 0;
        for (;;)
        {
          paramContext = (Context)localObject1;
          if (localObject2 == null) {
            break;
          }
          paramContext = (Context)localObject1;
          if (i >= ((List)localObject2).size()) {
            break;
          }
          paramContext = (ActivityManager.RunningAppProcessInfo)((List)localObject2).get(i);
          if ((paramContext != null) && (paramContext.pid == Process.myPid()))
          {
            paramContext = paramContext.processName;
            break;
          }
          i += 1;
        }
        return (String)localObject2;
      }
      catch (Exception paramContext)
      {
        cz.b(paramContext);
        paramContext = (Context)localObject1;
        localObject1 = paramContext;
        if (paramContext == null) {
          localObject1 = "";
        }
        a = (String)localObject1;
        localObject2 = localObject1;
      }
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\dc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */