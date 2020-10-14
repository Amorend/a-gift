package com.baidu.mobstat;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import java.io.File;
import java.util.Arrays;

public class al
{
  private static String a;
  private static al b;
  private Handler c;
  
  static
  {
    String str;
    if (Build.VERSION.SDK_INT < 9) {
      str = "http://openrcv.baidu.com/1010/bplus.gif";
    } else {
      str = "https://openrcv.baidu.com/1010/bplus.gif";
    }
    a = str;
  }
  
  private al()
  {
    HandlerThread localHandlerThread = new HandlerThread("LogSender");
    localHandlerThread.start();
    this.c = new Handler(localHandlerThread.getLooper());
  }
  
  public static al a()
  {
    if (b == null) {
      try
      {
        if (b == null) {
          b = new al();
        }
      }
      finally {}
    }
    return b;
  }
  
  /* Error */
  private String a(Context paramContext, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_2
    //   1: ldc 58
    //   3: invokevirtual 64	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   6: iconst_1
    //   7: ixor
    //   8: istore 4
    //   10: aload_1
    //   11: aload_2
    //   12: invokestatic 70	com/baidu/mobstat/cs:d	(Landroid/content/Context;Ljava/lang/String;)Ljava/net/HttpURLConnection;
    //   15: astore_2
    //   16: aload_2
    //   17: iconst_1
    //   18: invokevirtual 76	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   21: aload_2
    //   22: iconst_0
    //   23: invokevirtual 79	java/net/HttpURLConnection:setInstanceFollowRedirects	(Z)V
    //   26: aload_2
    //   27: iconst_0
    //   28: invokevirtual 82	java/net/HttpURLConnection:setUseCaches	(Z)V
    //   31: aload_2
    //   32: ldc 84
    //   34: ldc 86
    //   36: invokevirtual 90	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   39: aload_2
    //   40: invokevirtual 93	java/net/HttpURLConnection:connect	()V
    //   43: aload_2
    //   44: invokevirtual 97	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   47: astore 6
    //   49: new 99	java/util/zip/GZIPOutputStream
    //   52: dup
    //   53: aload 6
    //   55: invokespecial 102	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   58: astore 7
    //   60: aload 7
    //   62: iconst_4
    //   63: newarray <illegal type>
    //   65: dup
    //   66: iconst_0
    //   67: ldc 103
    //   69: bastore
    //   70: dup
    //   71: iconst_1
    //   72: ldc 104
    //   74: bastore
    //   75: dup
    //   76: iconst_2
    //   77: ldc 105
    //   79: bastore
    //   80: dup
    //   81: iconst_3
    //   82: ldc 106
    //   84: bastore
    //   85: invokevirtual 110	java/util/zip/GZIPOutputStream:write	([B)V
    //   88: aload 7
    //   90: iconst_4
    //   91: newarray <illegal type>
    //   93: dup
    //   94: iconst_0
    //   95: ldc 111
    //   97: bastore
    //   98: dup
    //   99: iconst_1
    //   100: ldc 111
    //   102: bastore
    //   103: dup
    //   104: iconst_2
    //   105: ldc 111
    //   107: bastore
    //   108: dup
    //   109: iconst_3
    //   110: ldc 112
    //   112: bastore
    //   113: invokevirtual 110	java/util/zip/GZIPOutputStream:write	([B)V
    //   116: aload 7
    //   118: iconst_4
    //   119: newarray <illegal type>
    //   121: dup
    //   122: iconst_0
    //   123: ldc 111
    //   125: bastore
    //   126: dup
    //   127: iconst_1
    //   128: ldc 111
    //   130: bastore
    //   131: dup
    //   132: iconst_2
    //   133: ldc 113
    //   135: bastore
    //   136: dup
    //   137: iconst_3
    //   138: ldc 114
    //   140: bastore
    //   141: invokevirtual 110	java/util/zip/GZIPOutputStream:write	([B)V
    //   144: aload 7
    //   146: bipush 8
    //   148: newarray <illegal type>
    //   150: dup
    //   151: iconst_0
    //   152: ldc 111
    //   154: bastore
    //   155: dup
    //   156: iconst_1
    //   157: ldc 111
    //   159: bastore
    //   160: dup
    //   161: iconst_2
    //   162: ldc 111
    //   164: bastore
    //   165: dup
    //   166: iconst_3
    //   167: ldc 111
    //   169: bastore
    //   170: dup
    //   171: iconst_4
    //   172: ldc 111
    //   174: bastore
    //   175: dup
    //   176: iconst_5
    //   177: ldc 111
    //   179: bastore
    //   180: dup
    //   181: bipush 6
    //   183: ldc 111
    //   185: bastore
    //   186: dup
    //   187: bipush 7
    //   189: ldc 111
    //   191: bastore
    //   192: invokevirtual 110	java/util/zip/GZIPOutputStream:write	([B)V
    //   195: aload 7
    //   197: iconst_2
    //   198: newarray <illegal type>
    //   200: dup
    //   201: iconst_0
    //   202: ldc 111
    //   204: bastore
    //   205: dup
    //   206: iconst_1
    //   207: ldc 115
    //   209: bastore
    //   210: invokevirtual 110	java/util/zip/GZIPOutputStream:write	([B)V
    //   213: iload 4
    //   215: ifeq +28 -> 243
    //   218: iconst_2
    //   219: newarray <illegal type>
    //   221: astore_1
    //   222: aload_1
    //   223: dup
    //   224: iconst_0
    //   225: ldc 111
    //   227: bastore
    //   228: dup
    //   229: iconst_1
    //   230: ldc 112
    //   232: bastore
    //   233: pop
    //   234: aload 7
    //   236: aload_1
    //   237: invokevirtual 110	java/util/zip/GZIPOutputStream:write	([B)V
    //   240: goto +22 -> 262
    //   243: iconst_2
    //   244: newarray <illegal type>
    //   246: astore_1
    //   247: aload_1
    //   248: dup
    //   249: iconst_0
    //   250: ldc 111
    //   252: bastore
    //   253: dup
    //   254: iconst_1
    //   255: ldc 111
    //   257: bastore
    //   258: pop
    //   259: goto -25 -> 234
    //   262: aload 7
    //   264: iconst_4
    //   265: newarray <illegal type>
    //   267: dup
    //   268: iconst_0
    //   269: ldc 103
    //   271: bastore
    //   272: dup
    //   273: iconst_1
    //   274: ldc 104
    //   276: bastore
    //   277: dup
    //   278: iconst_2
    //   279: ldc 105
    //   281: bastore
    //   282: dup
    //   283: iconst_3
    //   284: ldc 106
    //   286: bastore
    //   287: invokevirtual 110	java/util/zip/GZIPOutputStream:write	([B)V
    //   290: iload 4
    //   292: ifeq +159 -> 451
    //   295: invokestatic 120	com/baidu/mobstat/cq:a	()[B
    //   298: astore_1
    //   299: iconst_0
    //   300: invokestatic 123	com/baidu/mobstat/cu:a	()[B
    //   303: aload_1
    //   304: invokestatic 128	com/baidu/mobstat/da:a	(Z[B[B)[B
    //   307: astore 8
    //   309: aload 7
    //   311: aload 8
    //   313: arraylength
    //   314: i2l
    //   315: iconst_4
    //   316: invokestatic 131	com/baidu/mobstat/al:a	(JI)[B
    //   319: invokevirtual 110	java/util/zip/GZIPOutputStream:write	([B)V
    //   322: aload 7
    //   324: aload 8
    //   326: invokevirtual 110	java/util/zip/GZIPOutputStream:write	([B)V
    //   329: aload_3
    //   330: ldc -123
    //   332: invokevirtual 137	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   335: astore_3
    //   336: aload_1
    //   337: bipush 16
    //   339: newarray <illegal type>
    //   341: dup
    //   342: iconst_0
    //   343: ldc 112
    //   345: bastore
    //   346: dup
    //   347: iconst_1
    //   348: ldc 112
    //   350: bastore
    //   351: dup
    //   352: iconst_2
    //   353: ldc 112
    //   355: bastore
    //   356: dup
    //   357: iconst_3
    //   358: ldc 112
    //   360: bastore
    //   361: dup
    //   362: iconst_4
    //   363: ldc 112
    //   365: bastore
    //   366: dup
    //   367: iconst_5
    //   368: ldc 112
    //   370: bastore
    //   371: dup
    //   372: bipush 6
    //   374: ldc 112
    //   376: bastore
    //   377: dup
    //   378: bipush 7
    //   380: ldc 112
    //   382: bastore
    //   383: dup
    //   384: bipush 8
    //   386: ldc 112
    //   388: bastore
    //   389: dup
    //   390: bipush 9
    //   392: ldc 112
    //   394: bastore
    //   395: dup
    //   396: bipush 10
    //   398: ldc 112
    //   400: bastore
    //   401: dup
    //   402: bipush 11
    //   404: ldc 112
    //   406: bastore
    //   407: dup
    //   408: bipush 12
    //   410: ldc 112
    //   412: bastore
    //   413: dup
    //   414: bipush 13
    //   416: ldc 112
    //   418: bastore
    //   419: dup
    //   420: bipush 14
    //   422: ldc 112
    //   424: bastore
    //   425: dup
    //   426: bipush 15
    //   428: ldc 112
    //   430: bastore
    //   431: aload_3
    //   432: invokestatic 140	com/baidu/mobstat/cq:a	([B[B[B)[B
    //   435: astore_1
    //   436: aload 7
    //   438: aload_1
    //   439: arraylength
    //   440: i2l
    //   441: iconst_2
    //   442: invokestatic 131	com/baidu/mobstat/al:a	(JI)[B
    //   445: invokevirtual 110	java/util/zip/GZIPOutputStream:write	([B)V
    //   448: goto +10 -> 458
    //   451: aload_3
    //   452: ldc -123
    //   454: invokevirtual 137	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   457: astore_1
    //   458: aload 7
    //   460: aload_1
    //   461: invokevirtual 110	java/util/zip/GZIPOutputStream:write	([B)V
    //   464: aload 7
    //   466: invokevirtual 143	java/util/zip/GZIPOutputStream:close	()V
    //   469: aload 6
    //   471: invokevirtual 146	java/io/OutputStream:close	()V
    //   474: aload_2
    //   475: invokevirtual 150	java/net/HttpURLConnection:getResponseCode	()I
    //   478: istore 4
    //   480: aload_2
    //   481: invokevirtual 153	java/net/HttpURLConnection:getContentLength	()I
    //   484: istore 5
    //   486: new 155	java/lang/StringBuilder
    //   489: dup
    //   490: invokespecial 156	java/lang/StringBuilder:<init>	()V
    //   493: astore_1
    //   494: aload_1
    //   495: ldc -98
    //   497: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   500: pop
    //   501: aload_1
    //   502: iload 4
    //   504: invokevirtual 165	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   507: pop
    //   508: aload_1
    //   509: ldc -89
    //   511: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   514: pop
    //   515: aload_1
    //   516: iload 5
    //   518: invokevirtual 165	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   521: pop
    //   522: aload_1
    //   523: invokevirtual 171	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   526: invokestatic 175	com/baidu/mobstat/bd:c	(Ljava/lang/String;)V
    //   529: iload 4
    //   531: sipush 200
    //   534: if_icmpne +70 -> 604
    //   537: iload 5
    //   539: ifeq +6 -> 545
    //   542: goto +62 -> 604
    //   545: new 177	java/io/BufferedReader
    //   548: dup
    //   549: new 179	java/io/InputStreamReader
    //   552: dup
    //   553: aload_2
    //   554: invokevirtual 183	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   557: invokespecial 186	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   560: invokespecial 189	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   563: astore_1
    //   564: new 155	java/lang/StringBuilder
    //   567: dup
    //   568: invokespecial 156	java/lang/StringBuilder:<init>	()V
    //   571: astore_3
    //   572: aload_1
    //   573: invokevirtual 192	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   576: astore 6
    //   578: aload 6
    //   580: ifnonnull +14 -> 594
    //   583: aload_3
    //   584: invokevirtual 171	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   587: astore_1
    //   588: aload_2
    //   589: invokevirtual 195	java/net/HttpURLConnection:disconnect	()V
    //   592: aload_1
    //   593: areturn
    //   594: aload_3
    //   595: aload 6
    //   597: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   600: pop
    //   601: goto -29 -> 572
    //   604: new 155	java/lang/StringBuilder
    //   607: dup
    //   608: invokespecial 156	java/lang/StringBuilder:<init>	()V
    //   611: astore_1
    //   612: aload_1
    //   613: ldc -59
    //   615: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   618: pop
    //   619: aload_1
    //   620: aload_2
    //   621: invokevirtual 150	java/net/HttpURLConnection:getResponseCode	()I
    //   624: invokevirtual 165	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   627: pop
    //   628: new 199	java/io/IOException
    //   631: dup
    //   632: aload_1
    //   633: invokevirtual 171	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   636: invokespecial 200	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   639: athrow
    //   640: astore_1
    //   641: goto +15 -> 656
    //   644: astore_1
    //   645: aload_1
    //   646: invokestatic 203	com/baidu/mobstat/bd:b	(Ljava/lang/Throwable;)V
    //   649: aload_2
    //   650: invokevirtual 195	java/net/HttpURLConnection:disconnect	()V
    //   653: ldc -51
    //   655: areturn
    //   656: aload_2
    //   657: invokevirtual 195	java/net/HttpURLConnection:disconnect	()V
    //   660: aload_1
    //   661: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	662	0	this	al
    //   0	662	1	paramContext	Context
    //   0	662	2	paramString1	String
    //   0	662	3	paramString2	String
    //   8	283	4	bool	boolean
    //   478	57	4	i	int
    //   484	54	5	j	int
    //   47	549	6	localObject	Object
    //   58	407	7	localGZIPOutputStream	java.util.zip.GZIPOutputStream
    //   307	18	8	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   43	213	640	finally
    //   218	234	640	finally
    //   234	240	640	finally
    //   243	259	640	finally
    //   262	290	640	finally
    //   295	448	640	finally
    //   451	458	640	finally
    //   458	529	640	finally
    //   545	572	640	finally
    //   572	578	640	finally
    //   583	588	640	finally
    //   594	601	640	finally
    //   604	640	640	finally
    //   645	649	640	finally
    //   43	213	644	java/lang/Exception
    //   218	234	644	java/lang/Exception
    //   234	240	644	java/lang/Exception
    //   243	259	644	java/lang/Exception
    //   262	290	644	java/lang/Exception
    //   295	448	644	java/lang/Exception
    //   451	458	644	java/lang/Exception
    //   458	529	644	java/lang/Exception
    //   545	572	644	java/lang/Exception
    //   572	578	644	java/lang/Exception
    //   583	588	644	java/lang/Exception
    //   594	601	644	java/lang/Exception
    //   604	640	644	java/lang/Exception
  }
  
  private void a(Context paramContext)
  {
    if (!"mounted".equals(cs.a())) {
      return;
    }
    Object localObject = new File(Environment.getExternalStorageDirectory(), "backups/system");
    if (!((File)localObject).exists()) {
      return;
    }
    localObject = ((File)localObject).listFiles();
    if (localObject != null)
    {
      if (localObject.length == 0) {
        return;
      }
      try
      {
        Arrays.sort((Object[])localObject, new an(this));
      }
      catch (Exception localException)
      {
        bd.b(localException);
      }
      int m = localObject.length;
      int j = 0;
      int i = 0;
      while (j < m)
      {
        String str = localObject[j];
        if (str.isFile())
        {
          str = str.getName();
          if ((!TextUtils.isEmpty(str)) && (str.startsWith("__send_log_data_")))
          {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("backups/system");
            localStringBuilder.append(File.separator);
            localStringBuilder.append(str);
            str = localStringBuilder.toString();
            if (b(paramContext, cs.b(str)))
            {
              cs.c(str);
              i = 0;
            }
            else
            {
              int k = i + 1;
              i = k;
              if (k >= 5) {
                return;
              }
            }
          }
        }
        j += 1;
      }
    }
  }
  
  private void a(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("backups/system");
    localStringBuilder.append(File.separator);
    localStringBuilder.append("__send_log_data_");
    localStringBuilder.append(System.currentTimeMillis());
    cs.a(localStringBuilder.toString(), paramString, false);
  }
  
  private static byte[] a(long paramLong, int paramInt)
  {
    byte[] arrayOfByte = new byte[paramInt];
    int i = 0;
    while (i < paramInt)
    {
      arrayOfByte[(paramInt - i - 1)] = ((byte)(int)(paramLong & 0xFF));
      paramLong >>= 8;
      i += 1;
    }
    return arrayOfByte;
  }
  
  private boolean b(Context paramContext, String paramString)
  {
    if (paramContext != null)
    {
      if (TextUtils.isEmpty(paramString)) {
        return false;
      }
      try
      {
        a(paramContext, a, paramString);
        return true;
      }
      catch (Exception paramContext)
      {
        bd.c(paramContext);
      }
    }
    return false;
  }
  
  public void a(Context paramContext, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("data = ");
    localStringBuilder.append(paramString);
    bd.a(localStringBuilder.toString());
    if (paramString != null)
    {
      if ("".equals(paramString)) {
        return;
      }
      this.c.post(new am(this, paramString, paramContext));
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\al.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */