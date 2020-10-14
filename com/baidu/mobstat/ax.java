package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import dalvik.system.DexClassLoader;
import java.io.File;

class ax
{
  private static volatile DexClassLoader a;
  private static volatile boolean b;
  
  private static DexClassLoader a(Context paramContext)
  {
    try
    {
      if (a != null)
      {
        paramContext = a;
        return paramContext;
      }
      File localFile1 = paramContext.getFileStreamPath(".remote.jar");
      if (localFile1 != null)
      {
        boolean bool = localFile1.isFile();
        if (!bool) {
          return null;
        }
      }
      if (!b(paramContext, localFile1.getAbsolutePath()))
      {
        bd.a("remote jar version lower than min limit, need delete");
        if (localFile1.isFile()) {
          localFile1.delete();
        }
        return null;
      }
      if (!c(paramContext, localFile1.getAbsolutePath()))
      {
        bd.a("remote jar md5 is not right, need delete");
        if (localFile1.isFile()) {
          localFile1.delete();
        }
        return null;
      }
      File localFile2 = paramContext.getDir("outdex", 0);
      try
      {
        a = new DexClassLoader(localFile1.getAbsolutePath(), localFile2.getAbsolutePath(), null, paramContext.getClassLoader());
      }
      catch (Exception paramContext)
      {
        bd.a(paramContext);
      }
      paramContext = a;
      return paramContext;
    }
    finally {}
  }
  
  public static Class<?> a(Context paramContext, String paramString)
  {
    paramContext = a(paramContext);
    if (paramContext == null) {
      return null;
    }
    return paramContext.loadClass(paramString);
  }
  
  public static void a(Context paramContext, l paraml)
  {
    try
    {
      boolean bool = b;
      if (bool) {
        return;
      }
      if (!dc.n(paramContext))
      {
        bd.a("isWifiAvailable = false, will not to update");
        return;
      }
      if (!paraml.a(paramContext))
      {
        bd.a("check time, will not to update");
        return;
      }
      bd.a("can start update config");
      new ay(paramContext, paraml).start();
      b = true;
      return;
    }
    finally {}
  }
  
  /* Error */
  private static String b(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore_2
    //   5: aload_2
    //   6: astore_1
    //   7: new 26	java/io/File
    //   10: dup
    //   11: aload_0
    //   12: invokespecial 113	java/io/File:<init>	(Ljava/lang/String;)V
    //   15: astore_3
    //   16: aload_2
    //   17: astore_1
    //   18: aload_3
    //   19: invokevirtual 116	java/io/File:exists	()Z
    //   22: ifeq +46 -> 68
    //   25: aload_2
    //   26: astore_1
    //   27: new 118	java/lang/StringBuilder
    //   30: dup
    //   31: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   34: astore 5
    //   36: aload_2
    //   37: astore_1
    //   38: aload 5
    //   40: ldc 122
    //   42: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: pop
    //   46: aload_2
    //   47: astore_1
    //   48: aload 5
    //   50: aload_3
    //   51: invokevirtual 130	java/io/File:length	()J
    //   54: invokevirtual 133	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   57: pop
    //   58: aload_2
    //   59: astore_1
    //   60: aload 5
    //   62: invokevirtual 136	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   65: invokestatic 138	com/baidu/mobstat/bd:b	(Ljava/lang/String;)V
    //   68: aload_2
    //   69: astore_1
    //   70: new 140	java/util/jar/JarFile
    //   73: dup
    //   74: aload_0
    //   75: invokespecial 141	java/util/jar/JarFile:<init>	(Ljava/lang/String;)V
    //   78: astore_2
    //   79: aload_2
    //   80: invokevirtual 145	java/util/jar/JarFile:getManifest	()Ljava/util/jar/Manifest;
    //   83: invokevirtual 151	java/util/jar/Manifest:getMainAttributes	()Ljava/util/jar/Attributes;
    //   86: ldc -103
    //   88: invokevirtual 158	java/util/jar/Attributes:getValue	(Ljava/lang/String;)Ljava/lang/String;
    //   91: astore_1
    //   92: aload_2
    //   93: ifnull +7 -> 100
    //   96: aload_2
    //   97: invokevirtual 161	java/util/jar/JarFile:close	()V
    //   100: aload_1
    //   101: areturn
    //   102: astore_0
    //   103: aload_2
    //   104: astore_1
    //   105: goto +70 -> 175
    //   108: astore_1
    //   109: aload_1
    //   110: astore_3
    //   111: goto +11 -> 122
    //   114: astore_0
    //   115: goto +60 -> 175
    //   118: astore_3
    //   119: aload 4
    //   121: astore_2
    //   122: aload_2
    //   123: astore_1
    //   124: aload_3
    //   125: invokestatic 71	com/baidu/mobstat/bd:a	(Ljava/lang/Throwable;)V
    //   128: aload_2
    //   129: astore_1
    //   130: new 118	java/lang/StringBuilder
    //   133: dup
    //   134: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   137: astore_3
    //   138: aload_2
    //   139: astore_1
    //   140: aload_3
    //   141: ldc -93
    //   143: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: pop
    //   147: aload_2
    //   148: astore_1
    //   149: aload_3
    //   150: aload_0
    //   151: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   154: pop
    //   155: aload_2
    //   156: astore_1
    //   157: aload_3
    //   158: invokevirtual 136	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   161: invokestatic 44	com/baidu/mobstat/bd:a	(Ljava/lang/String;)V
    //   164: aload_2
    //   165: ifnull +7 -> 172
    //   168: aload_2
    //   169: invokevirtual 161	java/util/jar/JarFile:close	()V
    //   172: ldc -91
    //   174: areturn
    //   175: aload_1
    //   176: ifnull +7 -> 183
    //   179: aload_1
    //   180: invokevirtual 161	java/util/jar/JarFile:close	()V
    //   183: aload_0
    //   184: athrow
    //   185: astore_0
    //   186: aload_1
    //   187: areturn
    //   188: astore_0
    //   189: goto -17 -> 172
    //   192: astore_1
    //   193: goto -10 -> 183
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	196	0	paramString	String
    //   6	99	1	localObject1	Object
    //   108	2	1	localException1	Exception
    //   123	64	1	localObject2	Object
    //   192	1	1	localException2	Exception
    //   4	165	2	localObject3	Object
    //   15	96	3	localObject4	Object
    //   118	7	3	localException3	Exception
    //   137	21	3	localStringBuilder1	StringBuilder
    //   1	119	4	localObject5	Object
    //   34	27	5	localStringBuilder2	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   79	92	102	finally
    //   79	92	108	java/lang/Exception
    //   7	16	114	finally
    //   18	25	114	finally
    //   27	36	114	finally
    //   38	46	114	finally
    //   48	58	114	finally
    //   60	68	114	finally
    //   70	79	114	finally
    //   124	128	114	finally
    //   130	138	114	finally
    //   140	147	114	finally
    //   149	155	114	finally
    //   157	164	114	finally
    //   7	16	118	java/lang/Exception
    //   18	25	118	java/lang/Exception
    //   27	36	118	java/lang/Exception
    //   38	46	118	java/lang/Exception
    //   48	58	118	java/lang/Exception
    //   60	68	118	java/lang/Exception
    //   70	79	118	java/lang/Exception
    //   96	100	185	java/lang/Exception
    //   168	172	188	java/lang/Exception
    //   179	183	192	java/lang/Exception
  }
  
  private static boolean b(Context paramContext, String paramString)
  {
    paramContext = b(paramString);
    boolean bool2 = TextUtils.isEmpty(paramContext);
    boolean bool1 = false;
    if (bool2) {
      return false;
    }
    int i;
    try
    {
      i = Integer.valueOf(paramContext).intValue();
    }
    catch (Exception paramContext)
    {
      bd.b(paramContext);
      i = 0;
    }
    if (i >= 4) {
      bool1 = true;
    }
    return bool1;
  }
  
  private static boolean c(Context paramContext, String paramString)
  {
    String str = cx.a(new File(paramString));
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("remote.jar local file digest value digest = ");
    localStringBuilder.append(str);
    bd.a(localStringBuilder.toString());
    if (TextUtils.isEmpty(str)) {}
    for (paramContext = "remote.jar local file digest value fail";; paramContext = "remote.jar config digest value lost")
    {
      bd.a(paramContext);
      return false;
      paramString = b(paramString);
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("remote.jar local file digest value version = ");
      localStringBuilder.append(paramString);
      bd.a(localStringBuilder.toString());
      if (TextUtils.isEmpty(paramString)) {
        return false;
      }
      paramContext = d(paramContext, paramString);
      paramString = new StringBuilder();
      paramString.append("remote.jar config digest value remoteJarMd5 = ");
      paramString.append(paramContext);
      bd.a(paramString.toString());
      if (!TextUtils.isEmpty(paramContext)) {
        break;
      }
    }
    return str.equals(paramContext);
  }
  
  private static String d(Context paramContext, String paramString)
  {
    return az.a(paramContext).c(paramString);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\ax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */