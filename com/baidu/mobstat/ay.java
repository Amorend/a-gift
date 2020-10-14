package com.baidu.mobstat;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

class ay
  extends Thread
{
  private Context a;
  private l b;
  
  public ay(Context paramContext, l paraml)
  {
    this.a = paramContext;
    this.b = paraml;
  }
  
  /* Error */
  private void a()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc 22
    //   4: invokestatic 27	com/baidu/mobstat/bd:a	(Ljava/lang/String;)V
    //   7: aload_0
    //   8: getfield 15	com/baidu/mobstat/ay:a	Landroid/content/Context;
    //   11: astore 7
    //   13: aload_0
    //   14: getfield 17	com/baidu/mobstat/ay:b	Lcom/baidu/mobstat/l;
    //   17: astore 8
    //   19: aload_0
    //   20: aload 7
    //   22: invokespecial 30	com/baidu/mobstat/ay:b	(Landroid/content/Context;)Ljava/lang/String;
    //   25: astore_3
    //   26: new 32	java/lang/StringBuilder
    //   29: dup
    //   30: invokespecial 33	java/lang/StringBuilder:<init>	()V
    //   33: astore 4
    //   35: aload 4
    //   37: ldc 35
    //   39: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: pop
    //   43: aload 4
    //   45: aload_3
    //   46: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: pop
    //   50: aload 4
    //   52: invokevirtual 43	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   55: invokestatic 46	com/baidu/mobstat/bd:c	(Ljava/lang/String;)V
    //   58: aload 7
    //   60: aload_3
    //   61: invokestatic 52	com/baidu/mobstat/cs:d	(Landroid/content/Context;Ljava/lang/String;)Ljava/net/HttpURLConnection;
    //   64: astore 6
    //   66: aload 6
    //   68: invokevirtual 57	java/net/HttpURLConnection:connect	()V
    //   71: aload 6
    //   73: ldc 59
    //   75: invokevirtual 63	java/net/HttpURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   78: astore 9
    //   80: new 32	java/lang/StringBuilder
    //   83: dup
    //   84: invokespecial 33	java/lang/StringBuilder:<init>	()V
    //   87: astore_3
    //   88: aload_3
    //   89: ldc 65
    //   91: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: pop
    //   95: aload_3
    //   96: aload 9
    //   98: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: pop
    //   102: aload_3
    //   103: invokevirtual 43	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   106: invokestatic 27	com/baidu/mobstat/bd:a	(Ljava/lang/String;)V
    //   109: aload 6
    //   111: ldc 67
    //   113: invokevirtual 63	java/net/HttpURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   116: astore 10
    //   118: new 32	java/lang/StringBuilder
    //   121: dup
    //   122: invokespecial 33	java/lang/StringBuilder:<init>	()V
    //   125: astore_3
    //   126: aload_3
    //   127: ldc 69
    //   129: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: pop
    //   133: aload_3
    //   134: aload 10
    //   136: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: pop
    //   140: aload_3
    //   141: invokevirtual 43	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   144: invokestatic 27	com/baidu/mobstat/bd:a	(Ljava/lang/String;)V
    //   147: aload 6
    //   149: invokevirtual 73	java/net/HttpURLConnection:getResponseCode	()I
    //   152: istore_1
    //   153: new 32	java/lang/StringBuilder
    //   156: dup
    //   157: invokespecial 33	java/lang/StringBuilder:<init>	()V
    //   160: astore_3
    //   161: aload_3
    //   162: ldc 75
    //   164: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   167: pop
    //   168: aload_3
    //   169: iload_1
    //   170: invokevirtual 78	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   173: pop
    //   174: aload_3
    //   175: invokevirtual 43	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   178: invokestatic 27	com/baidu/mobstat/bd:a	(Ljava/lang/String;)V
    //   181: aload 6
    //   183: invokevirtual 81	java/net/HttpURLConnection:getContentLength	()I
    //   186: istore_2
    //   187: new 32	java/lang/StringBuilder
    //   190: dup
    //   191: invokespecial 33	java/lang/StringBuilder:<init>	()V
    //   194: astore_3
    //   195: aload_3
    //   196: ldc 83
    //   198: invokevirtual 39	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   201: pop
    //   202: aload_3
    //   203: iload_2
    //   204: invokevirtual 78	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   207: pop
    //   208: aload_3
    //   209: invokevirtual 43	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   212: invokestatic 27	com/baidu/mobstat/bd:a	(Ljava/lang/String;)V
    //   215: iload_1
    //   216: sipush 200
    //   219: if_icmpne +91 -> 310
    //   222: iload_2
    //   223: ifle +87 -> 310
    //   226: aload 7
    //   228: ldc 85
    //   230: iconst_0
    //   231: invokevirtual 91	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   234: astore_3
    //   235: aload_3
    //   236: astore 5
    //   238: aload_3
    //   239: astore 4
    //   241: aload 6
    //   243: invokevirtual 95	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   246: aload_3
    //   247: invokestatic 100	com/baidu/mobstat/cy:a	(Ljava/io/InputStream;Ljava/io/OutputStream;)Z
    //   250: ifeq +14 -> 264
    //   253: aload_3
    //   254: astore 4
    //   256: ldc 102
    //   258: invokestatic 27	com/baidu/mobstat/bd:a	(Ljava/lang/String;)V
    //   261: aload_3
    //   262: astore 5
    //   264: aload 5
    //   266: invokestatic 105	com/baidu/mobstat/cy:a	(Ljava/io/Closeable;)V
    //   269: goto +41 -> 310
    //   272: astore 5
    //   274: goto +14 -> 288
    //   277: astore_3
    //   278: aconst_null
    //   279: astore 4
    //   281: goto +22 -> 303
    //   284: astore 5
    //   286: aconst_null
    //   287: astore_3
    //   288: aload_3
    //   289: astore 4
    //   291: aload 5
    //   293: invokestatic 108	com/baidu/mobstat/bd:b	(Ljava/lang/Throwable;)V
    //   296: aload_3
    //   297: astore 5
    //   299: goto -35 -> 264
    //   302: astore_3
    //   303: aload 4
    //   305: invokestatic 105	com/baidu/mobstat/cy:a	(Ljava/io/Closeable;)V
    //   308: aload_3
    //   309: athrow
    //   310: aconst_null
    //   311: invokestatic 113	com/baidu/mobstat/ax:a	(Ldalvik/system/DexClassLoader;)Ldalvik/system/DexClassLoader;
    //   314: pop
    //   315: invokestatic 117	com/baidu/mobstat/au:a	()V
    //   318: aload 9
    //   320: invokestatic 123	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   323: ifne +14 -> 337
    //   326: aload 8
    //   328: aload 7
    //   330: aload 9
    //   332: invokeinterface 128 3 0
    //   337: aload 10
    //   339: invokestatic 123	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   342: ifne +14 -> 356
    //   345: aload 8
    //   347: aload 7
    //   349: aload 10
    //   351: invokeinterface 130 3 0
    //   356: aload 6
    //   358: invokevirtual 133	java/net/HttpURLConnection:disconnect	()V
    //   361: ldc -121
    //   363: invokestatic 27	com/baidu/mobstat/bd:a	(Ljava/lang/String;)V
    //   366: aload_0
    //   367: monitorexit
    //   368: return
    //   369: astore_3
    //   370: aload 6
    //   372: invokevirtual 133	java/net/HttpURLConnection:disconnect	()V
    //   375: aload_3
    //   376: athrow
    //   377: astore_3
    //   378: aload_0
    //   379: monitorexit
    //   380: aload_3
    //   381: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	382	0	this	ay
    //   152	68	1	i	int
    //   186	37	2	j	int
    //   25	237	3	localObject1	Object
    //   277	1	3	localObject2	Object
    //   287	10	3	localObject3	Object
    //   302	7	3	localObject4	Object
    //   369	7	3	localObject5	Object
    //   377	4	3	localObject6	Object
    //   33	271	4	localObject7	Object
    //   236	29	5	localObject8	Object
    //   272	1	5	localIOException1	java.io.IOException
    //   284	8	5	localIOException2	java.io.IOException
    //   297	1	5	localObject9	Object
    //   64	307	6	localHttpURLConnection	java.net.HttpURLConnection
    //   11	337	7	localContext	Context
    //   17	329	8	locall	l
    //   78	253	9	str1	String
    //   116	234	10	str2	String
    // Exception table:
    //   from	to	target	type
    //   241	253	272	java/io/IOException
    //   256	261	272	java/io/IOException
    //   226	235	277	finally
    //   226	235	284	java/io/IOException
    //   241	253	302	finally
    //   256	261	302	finally
    //   291	296	302	finally
    //   66	215	369	finally
    //   264	269	369	finally
    //   303	310	369	finally
    //   310	337	369	finally
    //   337	356	369	finally
    //   2	66	377	finally
    //   356	366	377	finally
    //   370	377	377	finally
  }
  
  private void a(Context paramContext)
  {
    this.b.a(paramContext, System.currentTimeMillis());
  }
  
  private String b(Context paramContext)
  {
    Object localObject1 = paramContext.getFileStreamPath(".remote.jar");
    if ((localObject1 != null) && (((File)localObject1).exists()))
    {
      localObject1 = paramContext.getFileStreamPath(".remote.jar");
      if (localObject1 != null)
      {
        localObject1 = ax.a(((File)localObject1).getAbsolutePath());
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("startDownload remote jar file version = ");
        ((StringBuilder)localObject2).append((String)localObject1);
        bd.a(((StringBuilder)localObject2).toString());
        if (!TextUtils.isEmpty((CharSequence)localObject1)) {
          break label78;
        }
      }
    }
    localObject1 = "13";
    label78:
    Object localObject2 = new ArrayList();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("");
    localStringBuilder.append((String)localObject1);
    ((List)localObject2).add(new BasicNameValuePair("dynamicVersion", localStringBuilder.toString()));
    ((List)localObject2).add(new BasicNameValuePair("packageName", dc.p(paramContext)));
    ((List)localObject2).add(new BasicNameValuePair("appVersion", dc.f(paramContext)));
    ((List)localObject2).add(new BasicNameValuePair("cuid", dc.a(paramContext)));
    ((List)localObject2).add(new BasicNameValuePair("platform", "Android"));
    ((List)localObject2).add(new BasicNameValuePair("m", Build.MODEL));
    paramContext = new StringBuilder();
    paramContext.append(Build.VERSION.SDK_INT);
    paramContext.append("");
    ((List)localObject2).add(new BasicNameValuePair("s", paramContext.toString()));
    ((List)localObject2).add(new BasicNameValuePair("o", Build.VERSION.RELEASE));
    ((List)localObject2).add(new BasicNameValuePair("i", "13"));
    paramContext = URLEncodedUtils.format((List)localObject2, "utf-8");
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(bb.c);
    ((StringBuilder)localObject1).append("?");
    ((StringBuilder)localObject1).append(paramContext);
    return ((StringBuilder)localObject1).toString();
  }
  
  public void run()
  {
    for (;;)
    {
      try
      {
        if (!bb.a) {
          break label81;
        }
        i = 3;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("start version check in ");
        localStringBuilder.append(i);
        localStringBuilder.append("s");
        bd.a(localStringBuilder.toString());
        sleep(i * 1000);
        a();
        a(this.a);
      }
      catch (Exception localException)
      {
        bd.a(localException);
      }
      ax.a(false);
      return;
      label81:
      int i = 10;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\ay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */