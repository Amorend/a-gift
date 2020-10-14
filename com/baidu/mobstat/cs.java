package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;

public final class cs
{
  private static final Proxy a = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.172", 80));
  private static final Proxy b = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.200", 80));
  
  public static File a(String paramString)
  {
    if (!"mounted".equals(a())) {
      return null;
    }
    try
    {
      localFile = Environment.getExternalStorageDirectory();
    }
    catch (Exception localException)
    {
      File localFile;
      for (;;) {}
    }
    localFile = null;
    if (localFile == null) {
      return null;
    }
    return new File(localFile, paramString);
  }
  
  public static String a()
  {
    try
    {
      String str = Environment.getExternalStorageState();
      return str;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  /* Error */
  public static String a(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual 70	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   5: astore_0
    //   6: aload_0
    //   7: invokestatic 73	com/baidu/mobstat/cs:a	(Ljava/io/InputStream;)[B
    //   10: astore_2
    //   11: aload_0
    //   12: astore_1
    //   13: aload_2
    //   14: ifnull +35 -> 49
    //   17: new 45	java/lang/String
    //   20: dup
    //   21: aload_2
    //   22: ldc 75
    //   24: invokespecial 78	java/lang/String:<init>	([BLjava/lang/String;)V
    //   27: astore_1
    //   28: aload_0
    //   29: invokestatic 83	com/baidu/mobstat/cy:a	(Ljava/io/Closeable;)V
    //   32: aload_1
    //   33: areturn
    //   34: astore_1
    //   35: goto +6 -> 41
    //   38: astore_1
    //   39: aconst_null
    //   40: astore_0
    //   41: aload_0
    //   42: invokestatic 83	com/baidu/mobstat/cy:a	(Ljava/io/Closeable;)V
    //   45: aload_1
    //   46: athrow
    //   47: aconst_null
    //   48: astore_1
    //   49: aload_1
    //   50: invokestatic 83	com/baidu/mobstat/cy:a	(Ljava/io/Closeable;)V
    //   53: ldc 85
    //   55: areturn
    //   56: astore_0
    //   57: goto -10 -> 47
    //   60: astore_1
    //   61: aload_0
    //   62: astore_1
    //   63: goto -14 -> 49
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	66	0	paramContext	Context
    //   0	66	1	paramString	String
    //   10	12	2	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   6	11	34	finally
    //   17	28	34	finally
    //   0	6	38	finally
    //   0	6	56	java/lang/Exception
    //   6	11	60	java/lang/Exception
    //   17	28	60	java/lang/Exception
  }
  
  @SuppressLint({"DefaultLocale"})
  public static HttpURLConnection a(Context paramContext, String paramString, int paramInt1, int paramInt2)
  {
    URL localURL = new URL(paramString);
    paramString = (ConnectivityManager)paramContext.getSystemService("connectivity");
    paramContext = paramString.getNetworkInfo(0);
    paramString = paramString.getNetworkInfo(1);
    if ((paramString != null) && (paramString.isAvailable())) {
      cz.a("WIFI is available");
    }
    for (paramContext = localURL.openConnection();; paramContext = localURL.openConnection(paramContext))
    {
      paramContext = (HttpURLConnection)paramContext;
      break label159;
      if ((paramContext == null) || (!paramContext.isAvailable())) {
        break;
      }
      paramContext = paramContext.getExtraInfo();
      if (paramContext != null) {
        paramContext = paramContext.toLowerCase();
      } else {
        paramContext = "";
      }
      cz.a(paramContext);
      if ((!paramContext.startsWith("cmwap")) && (!paramContext.startsWith("uniwap")) && (!paramContext.startsWith("3gwap")))
      {
        if (!paramContext.startsWith("ctwap")) {
          break;
        }
        paramContext = b;
      }
      else
      {
        paramContext = a;
      }
    }
    paramContext = null;
    label159:
    paramString = paramContext;
    if (paramContext == null) {
      paramString = (HttpURLConnection)localURL.openConnection();
    }
    paramString.setConnectTimeout(paramInt1);
    paramString.setReadTimeout(paramInt2);
    return paramString;
  }
  
  /* Error */
  public static void a(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +4 -> 5
    //   4: return
    //   5: iload_3
    //   6: ifeq +10 -> 16
    //   9: ldc -101
    //   11: istore 4
    //   13: goto +6 -> 19
    //   16: iconst_0
    //   17: istore 4
    //   19: aload_0
    //   20: aload_1
    //   21: iload 4
    //   23: invokevirtual 159	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   26: astore_0
    //   27: new 161	java/io/ByteArrayInputStream
    //   30: dup
    //   31: aload_2
    //   32: ldc 75
    //   34: invokevirtual 165	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   37: invokespecial 168	java/io/ByteArrayInputStream:<init>	([B)V
    //   40: aload_0
    //   41: invokestatic 171	com/baidu/mobstat/cy:a	(Ljava/io/InputStream;Ljava/io/OutputStream;)Z
    //   44: pop
    //   45: goto +18 -> 63
    //   48: astore_1
    //   49: goto +6 -> 55
    //   52: astore_1
    //   53: aconst_null
    //   54: astore_0
    //   55: aload_0
    //   56: invokestatic 83	com/baidu/mobstat/cy:a	(Ljava/io/Closeable;)V
    //   59: aload_1
    //   60: athrow
    //   61: aconst_null
    //   62: astore_0
    //   63: aload_0
    //   64: invokestatic 83	com/baidu/mobstat/cy:a	(Ljava/io/Closeable;)V
    //   67: return
    //   68: astore_0
    //   69: goto -8 -> 61
    //   72: astore_1
    //   73: goto -10 -> 63
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	76	0	paramContext	Context
    //   0	76	1	paramString1	String
    //   0	76	2	paramString2	String
    //   0	76	3	paramBoolean	boolean
    //   11	11	4	i	int
    // Exception table:
    //   from	to	target	type
    //   27	45	48	finally
    //   19	27	52	finally
    //   19	27	68	java/lang/Exception
    //   27	45	72	java/lang/Exception
  }
  
  /* Error */
  public static void a(String paramString1, String paramString2, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore_3
    //   5: aload_0
    //   6: invokestatic 174	com/baidu/mobstat/cs:a	(Ljava/lang/String;)Ljava/io/File;
    //   9: astore 5
    //   11: aload 4
    //   13: astore_0
    //   14: aload 5
    //   16: ifnull +74 -> 90
    //   19: aload 5
    //   21: invokevirtual 177	java/io/File:exists	()Z
    //   24: ifne +18 -> 42
    //   27: aload 5
    //   29: invokevirtual 180	java/io/File:getParentFile	()Ljava/io/File;
    //   32: astore_0
    //   33: aload_0
    //   34: ifnull +8 -> 42
    //   37: aload_0
    //   38: invokevirtual 183	java/io/File:mkdirs	()Z
    //   41: pop
    //   42: new 185	java/io/FileOutputStream
    //   45: dup
    //   46: aload 5
    //   48: iload_2
    //   49: invokespecial 188	java/io/FileOutputStream:<init>	(Ljava/io/File;Z)V
    //   52: astore_0
    //   53: new 161	java/io/ByteArrayInputStream
    //   56: dup
    //   57: aload_1
    //   58: ldc 75
    //   60: invokevirtual 165	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   63: invokespecial 168	java/io/ByteArrayInputStream:<init>	([B)V
    //   66: aload_0
    //   67: invokestatic 171	com/baidu/mobstat/cy:a	(Ljava/io/InputStream;Ljava/io/OutputStream;)Z
    //   70: pop
    //   71: goto +7 -> 78
    //   74: astore_1
    //   75: goto +9 -> 84
    //   78: goto +12 -> 90
    //   81: astore_1
    //   82: aload_3
    //   83: astore_0
    //   84: aload_0
    //   85: invokestatic 83	com/baidu/mobstat/cy:a	(Ljava/io/Closeable;)V
    //   88: aload_1
    //   89: athrow
    //   90: aload_0
    //   91: invokestatic 83	com/baidu/mobstat/cy:a	(Ljava/io/Closeable;)V
    //   94: return
    //   95: astore_0
    //   96: aload 4
    //   98: astore_0
    //   99: goto -9 -> 90
    //   102: astore_1
    //   103: goto -25 -> 78
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	106	0	paramString1	String
    //   0	106	1	paramString2	String
    //   0	106	2	paramBoolean	boolean
    //   4	79	3	localObject1	Object
    //   1	96	4	localObject2	Object
    //   9	38	5	localFile	File
    // Exception table:
    //   from	to	target	type
    //   53	71	74	finally
    //   5	11	81	finally
    //   19	33	81	finally
    //   37	42	81	finally
    //   42	53	81	finally
    //   5	11	95	java/lang/Exception
    //   19	33	95	java/lang/Exception
    //   37	42	95	java/lang/Exception
    //   42	53	95	java/lang/Exception
    //   53	71	102	java/lang/Exception
  }
  
  private static byte[] a(InputStream paramInputStream)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    if (cy.a(paramInputStream, localByteArrayOutputStream)) {
      return localByteArrayOutputStream.toByteArray();
    }
    return null;
  }
  
  /* Error */
  public static String b(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 174	com/baidu/mobstat/cs:a	(Ljava/lang/String;)Ljava/io/File;
    //   4: astore_0
    //   5: aload_0
    //   6: ifnull +66 -> 72
    //   9: aload_0
    //   10: invokevirtual 177	java/io/File:exists	()Z
    //   13: ifeq +59 -> 72
    //   16: new 199	java/io/FileInputStream
    //   19: dup
    //   20: aload_0
    //   21: invokespecial 202	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   24: astore_0
    //   25: aload_0
    //   26: invokestatic 73	com/baidu/mobstat/cs:a	(Ljava/io/InputStream;)[B
    //   29: astore_2
    //   30: aload_0
    //   31: astore_1
    //   32: aload_2
    //   33: ifnull +35 -> 68
    //   36: new 45	java/lang/String
    //   39: dup
    //   40: aload_2
    //   41: ldc 75
    //   43: invokespecial 78	java/lang/String:<init>	([BLjava/lang/String;)V
    //   46: astore_1
    //   47: aload_0
    //   48: invokestatic 83	com/baidu/mobstat/cy:a	(Ljava/io/Closeable;)V
    //   51: aload_1
    //   52: areturn
    //   53: astore_1
    //   54: goto +6 -> 60
    //   57: astore_1
    //   58: aconst_null
    //   59: astore_0
    //   60: aload_0
    //   61: invokestatic 83	com/baidu/mobstat/cy:a	(Ljava/io/Closeable;)V
    //   64: aload_1
    //   65: athrow
    //   66: aconst_null
    //   67: astore_1
    //   68: aload_1
    //   69: invokestatic 83	com/baidu/mobstat/cy:a	(Ljava/io/Closeable;)V
    //   72: ldc 85
    //   74: areturn
    //   75: astore_0
    //   76: goto -10 -> 66
    //   79: astore_1
    //   80: aload_0
    //   81: astore_1
    //   82: goto -14 -> 68
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	85	0	paramString	String
    //   31	21	1	str1	String
    //   53	1	1	localObject1	Object
    //   57	8	1	localObject2	Object
    //   67	2	1	localCloseable	java.io.Closeable
    //   79	1	1	localException	Exception
    //   81	1	1	str2	String
    //   29	12	2	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   25	30	53	finally
    //   36	47	53	finally
    //   16	25	57	finally
    //   16	25	75	java/lang/Exception
    //   25	30	79	java/lang/Exception
    //   36	47	79	java/lang/Exception
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    return paramContext.deleteFile(paramString);
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    return paramContext.getFileStreamPath(paramString).exists();
  }
  
  public static boolean c(String paramString)
  {
    paramString = a(paramString);
    if ((paramString != null) && (paramString.isFile())) {
      return paramString.delete();
    }
    return false;
  }
  
  public static HttpURLConnection d(Context paramContext, String paramString)
  {
    return a(paramContext, paramString, 50000, 50000);
  }
  
  public static boolean e(Context paramContext, String paramString)
  {
    boolean bool = false;
    try
    {
      int i = paramContext.checkCallingOrSelfPermission(paramString);
      if (i != -1) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    cz.b("Check permission failed.");
    return false;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\cs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */