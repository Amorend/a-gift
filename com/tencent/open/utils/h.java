package com.tencent.open.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import com.tencent.open.a.f;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.List;

public class h
{
  public static int a(String paramString)
  {
    if ("shareToQQ".equals(paramString)) {
      return 10103;
    }
    if ("shareToQzone".equals(paramString)) {
      return 10104;
    }
    if ("addToQQFavorites".equals(paramString)) {
      return 10105;
    }
    if ("sendToMyComputer".equals(paramString)) {
      return 10106;
    }
    if ("shareToTroopBar".equals(paramString)) {
      return 10107;
    }
    if ("action_login".equals(paramString)) {
      return 11101;
    }
    if ("action_request".equals(paramString)) {
      return 10100;
    }
    return -1;
  }
  
  public static int a(String paramString1, String paramString2)
  {
    if ((paramString1 == null) && (paramString2 == null)) {
      return 0;
    }
    if ((paramString1 != null) && (paramString2 == null)) {
      return 1;
    }
    if ((paramString1 == null) && (paramString2 != null)) {
      return -1;
    }
    String[] arrayOfString1 = paramString1.split("\\.");
    String[] arrayOfString2 = paramString2.split("\\.");
    int i = 0;
    for (;;)
    {
      int j;
      int k;
      try
      {
        if ((i < arrayOfString1.length) && (i < arrayOfString2.length))
        {
          j = Integer.parseInt(arrayOfString1[i]);
          k = Integer.parseInt(arrayOfString2[i]);
          if (j < k) {
            return -1;
          }
        }
        else
        {
          if (arrayOfString1.length > i) {
            return 1;
          }
          j = arrayOfString2.length;
          if (j > i) {
            return -1;
          }
          return 0;
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
        return paramString1.compareTo(paramString2);
      }
      if (j > k) {
        return 1;
      }
      i += 1;
    }
  }
  
  private static long a(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    long l = 0L;
    byte[] arrayOfByte = new byte[' '];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte, 0, arrayOfByte.length);
      if (i == -1) {
        break;
      }
      paramOutputStream.write(arrayOfByte, 0, i);
      l += i;
    }
    f.c("openSDK_LOG.SystemUtils", "-->copy, copyed size is: " + l);
    return l;
  }
  
  public static String a(int paramInt)
  {
    if (paramInt == 10103) {
      return "shareToQQ";
    }
    if (paramInt == 10104) {
      return "shareToQzone";
    }
    if (paramInt == 10105) {
      return "addToQQFavorites";
    }
    if (paramInt == 10106) {
      return "sendToMyComputer";
    }
    if (paramInt == 10107) {
      return "shareToTroopBar";
    }
    if (paramInt == 11101) {
      return "action_login";
    }
    if (paramInt == 10100) {
      return "action_request";
    }
    return null;
  }
  
  public static String a(Context paramContext)
  {
    return paramContext.getApplicationInfo().loadLabel(paramContext.getPackageManager()).toString();
  }
  
  public static String a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getPackageInfo(paramString, 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  public static boolean a(Context paramContext, Intent paramIntent)
  {
    if ((paramContext == null) || (paramIntent == null)) {
      return false;
    }
    if (paramContext.getPackageManager().queryIntentActivities(paramIntent, 0).size() != 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean a(Context paramContext, String paramString1, String paramString2)
  {
    f.a("openSDK_LOG.SystemUtils", "OpenUi, validateAppSignatureForPackage");
    for (;;)
    {
      int i;
      try
      {
        paramContext = paramContext.getPackageManager().getPackageInfo(paramString1, 64);
        paramContext = paramContext.signatures;
        int j = paramContext.length;
        i = 0;
        if (i >= j) {
          break;
        }
        if (k.f(paramContext[i].toCharsString()).equals(paramString2)) {
          return true;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        return false;
      }
      i += 1;
    }
    return false;
  }
  
  /* Error */
  @android.annotation.SuppressLint({"SdCardPath"})
  public static boolean a(String paramString1, String paramString2, int paramInt)
  {
    // Byte code:
    //   0: ldc 61
    //   2: new 63	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 67	java/lang/StringBuilder:<init>	()V
    //   9: ldc -94
    //   11: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   14: aload_0
    //   15: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   21: invokestatic 86	com/tencent/open/a/f:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   24: invokestatic 167	com/tencent/open/utils/e:a	()Landroid/content/Context;
    //   27: astore 11
    //   29: aload 11
    //   31: ifnonnull +12 -> 43
    //   34: ldc 61
    //   36: ldc -87
    //   38: invokestatic 86	com/tencent/open/a/f:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   41: iconst_0
    //   42: ireturn
    //   43: aload 11
    //   45: ldc -85
    //   47: iconst_0
    //   48: invokevirtual 175	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   51: astore 10
    //   53: new 177	java/io/File
    //   56: dup
    //   57: aload 11
    //   59: invokevirtual 181	android/content/Context:getFilesDir	()Ljava/io/File;
    //   62: aload_1
    //   63: invokespecial 184	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   66: astore 4
    //   68: aload 4
    //   70: invokevirtual 188	java/io/File:exists	()Z
    //   73: ifne +191 -> 264
    //   76: aload 4
    //   78: invokevirtual 191	java/io/File:getParentFile	()Ljava/io/File;
    //   81: astore 5
    //   83: aload 5
    //   85: ifnull +17 -> 102
    //   88: aload 5
    //   90: invokevirtual 194	java/io/File:mkdirs	()Z
    //   93: ifeq +9 -> 102
    //   96: aload 4
    //   98: invokevirtual 197	java/io/File:createNewFile	()Z
    //   101: pop
    //   102: aconst_null
    //   103: astore 7
    //   105: aconst_null
    //   106: astore 6
    //   108: aconst_null
    //   109: astore 9
    //   111: aconst_null
    //   112: astore 8
    //   114: aload 8
    //   116: astore 4
    //   118: aload 9
    //   120: astore 5
    //   122: aload 11
    //   124: invokevirtual 201	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   127: aload_0
    //   128: invokevirtual 207	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   131: astore_0
    //   132: aload_0
    //   133: astore 6
    //   135: aload 8
    //   137: astore 4
    //   139: aload_0
    //   140: astore 7
    //   142: aload 9
    //   144: astore 5
    //   146: aload 11
    //   148: aload_1
    //   149: iconst_0
    //   150: invokevirtual 211	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   153: astore_1
    //   154: aload_0
    //   155: astore 6
    //   157: aload_1
    //   158: astore 4
    //   160: aload_0
    //   161: astore 7
    //   163: aload_1
    //   164: astore 5
    //   166: aload_0
    //   167: aload_1
    //   168: invokestatic 213	com/tencent/open/utils/h:a	(Ljava/io/InputStream;Ljava/io/OutputStream;)J
    //   171: pop2
    //   172: aload_0
    //   173: astore 6
    //   175: aload_1
    //   176: astore 4
    //   178: aload_0
    //   179: astore 7
    //   181: aload_1
    //   182: astore 5
    //   184: aload 10
    //   186: invokeinterface 219 1 0
    //   191: astore 8
    //   193: aload_0
    //   194: astore 6
    //   196: aload_1
    //   197: astore 4
    //   199: aload_0
    //   200: astore 7
    //   202: aload_1
    //   203: astore 5
    //   205: aload 8
    //   207: ldc -35
    //   209: iload_2
    //   210: invokeinterface 227 3 0
    //   215: pop
    //   216: aload_0
    //   217: astore 6
    //   219: aload_1
    //   220: astore 4
    //   222: aload_0
    //   223: astore 7
    //   225: aload_1
    //   226: astore 5
    //   228: aload 8
    //   230: invokeinterface 230 1 0
    //   235: pop
    //   236: aload_0
    //   237: ifnull +7 -> 244
    //   240: aload_0
    //   241: invokevirtual 233	java/io/InputStream:close	()V
    //   244: aload_1
    //   245: ifnull +7 -> 252
    //   248: aload_1
    //   249: invokevirtual 234	java/io/OutputStream:close	()V
    //   252: iconst_1
    //   253: ireturn
    //   254: astore 4
    //   256: aload 4
    //   258: invokevirtual 237	java/io/IOException:printStackTrace	()V
    //   261: goto -159 -> 102
    //   264: aload 10
    //   266: ldc -35
    //   268: iconst_0
    //   269: invokeinterface 241 3 0
    //   274: istore_3
    //   275: ldc 61
    //   277: new 63	java/lang/StringBuilder
    //   280: dup
    //   281: invokespecial 67	java/lang/StringBuilder:<init>	()V
    //   284: ldc -13
    //   286: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   289: iload_2
    //   290: invokevirtual 246	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   293: ldc -8
    //   295: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   298: iload_3
    //   299: invokevirtual 246	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   302: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   305: invokestatic 86	com/tencent/open/a/f:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   308: iload_2
    //   309: iload_3
    //   310: if_icmpne -208 -> 102
    //   313: iconst_1
    //   314: ireturn
    //   315: astore_0
    //   316: goto -72 -> 244
    //   319: astore_0
    //   320: goto -68 -> 252
    //   323: astore_0
    //   324: aload 6
    //   326: astore 7
    //   328: aload 4
    //   330: astore 5
    //   332: ldc 61
    //   334: ldc -6
    //   336: aload_0
    //   337: invokestatic 254	com/tencent/open/a/f:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   340: aload 6
    //   342: ifnull +8 -> 350
    //   345: aload 6
    //   347: invokevirtual 233	java/io/InputStream:close	()V
    //   350: aload 4
    //   352: ifnull +8 -> 360
    //   355: aload 4
    //   357: invokevirtual 234	java/io/OutputStream:close	()V
    //   360: iconst_0
    //   361: ireturn
    //   362: astore_0
    //   363: goto -13 -> 350
    //   366: astore_0
    //   367: goto -7 -> 360
    //   370: astore_0
    //   371: aload 7
    //   373: ifnull +8 -> 381
    //   376: aload 7
    //   378: invokevirtual 233	java/io/InputStream:close	()V
    //   381: aload 5
    //   383: ifnull +8 -> 391
    //   386: aload 5
    //   388: invokevirtual 234	java/io/OutputStream:close	()V
    //   391: aload_0
    //   392: athrow
    //   393: astore_1
    //   394: goto -13 -> 381
    //   397: astore_1
    //   398: goto -7 -> 391
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	401	0	paramString1	String
    //   0	401	1	paramString2	String
    //   0	401	2	paramInt	int
    //   274	37	3	i	int
    //   66	155	4	localObject1	Object
    //   254	102	4	localIOException	IOException
    //   81	306	5	localObject2	Object
    //   106	240	6	str	String
    //   103	274	7	localObject3	Object
    //   112	117	8	localEditor	android.content.SharedPreferences.Editor
    //   109	34	9	localObject4	Object
    //   51	214	10	localSharedPreferences	android.content.SharedPreferences
    //   27	120	11	localContext	Context
    // Exception table:
    //   from	to	target	type
    //   96	102	254	java/io/IOException
    //   240	244	315	java/io/IOException
    //   248	252	319	java/io/IOException
    //   122	132	323	java/lang/Exception
    //   146	154	323	java/lang/Exception
    //   166	172	323	java/lang/Exception
    //   184	193	323	java/lang/Exception
    //   205	216	323	java/lang/Exception
    //   228	236	323	java/lang/Exception
    //   345	350	362	java/io/IOException
    //   355	360	366	java/io/IOException
    //   122	132	370	finally
    //   146	154	370	finally
    //   166	172	370	finally
    //   184	193	370	finally
    //   205	216	370	finally
    //   228	236	370	finally
    //   332	340	370	finally
    //   376	381	393	java/io/IOException
    //   386	391	397	java/io/IOException
  }
  
  public static String b(Context paramContext, String paramString)
  {
    f.a("openSDK_LOG.SystemUtils", "OpenUi, getSignValidString");
    String str1 = "";
    localObject = str1;
    try
    {
      String str2 = paramContext.getPackageName();
      localObject = str1;
      paramContext = paramContext.getPackageManager().getPackageInfo(str2, 64).signatures;
      localObject = str1;
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localObject = str1;
      localMessageDigest.update(paramContext[0].toByteArray());
      localObject = str1;
      paramContext = k.a(localMessageDigest.digest());
      localObject = str1;
      localMessageDigest.reset();
      localObject = str1;
      f.a("openSDK_LOG.SystemUtils", "-->sign: " + paramContext);
      localObject = str1;
      localMessageDigest.update(k.i(str2 + "_" + paramContext + "_" + paramString + ""));
      localObject = str1;
      paramContext = k.a(localMessageDigest.digest());
      localObject = paramContext;
      localMessageDigest.reset();
      localObject = paramContext;
      f.a("openSDK_LOG.SystemUtils", "-->signEncryped: " + paramContext);
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        f.b("openSDK_LOG.SystemUtils", "OpenUi, getSignValidString error", paramContext);
        paramContext = (Context)localObject;
      }
    }
    return paramContext;
  }
  
  public static int c(Context paramContext, String paramString)
  {
    return a(a(paramContext, "com.tencent.mobileqq"), paramString);
  }
  
  public static int d(Context paramContext, String paramString)
  {
    return a(a(paramContext, "com.tencent.tim"), paramString);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\utils\h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */