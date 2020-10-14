package com.androlua.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipUtil
{
  private static final Logger a = Logger.getLogger(ZipUtil.class.getName());
  private static final byte[] b = new byte['က'];
  
  /* Error */
  private static void a(File paramFile, ZipOutputStream paramZipOutputStream, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 41	java/io/File:isFile	()Z
    //   4: istore 5
    //   6: iconst_0
    //   7: istore_3
    //   8: iload 5
    //   10: ifeq +204 -> 214
    //   13: aconst_null
    //   14: astore 8
    //   16: aconst_null
    //   17: astore 9
    //   19: aconst_null
    //   20: astore 6
    //   22: new 43	java/io/BufferedInputStream
    //   25: dup
    //   26: new 45	java/io/FileInputStream
    //   29: dup
    //   30: aload_0
    //   31: invokespecial 48	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   34: getstatic 26	com/androlua/util/ZipUtil:b	[B
    //   37: arraylength
    //   38: invokespecial 51	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;I)V
    //   41: astore 7
    //   43: aload_0
    //   44: invokevirtual 54	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   47: aload_2
    //   48: invokevirtual 60	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   51: istore_3
    //   52: aload_0
    //   53: invokevirtual 54	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   56: iload_3
    //   57: invokevirtual 64	java/lang/String:substring	(I)Ljava/lang/String;
    //   60: astore_0
    //   61: getstatic 70	java/lang/System:out	Ljava/io/PrintStream;
    //   64: aload_0
    //   65: invokevirtual 76	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   68: aload_1
    //   69: new 78	java/util/zip/ZipEntry
    //   72: dup
    //   73: aload_0
    //   74: invokespecial 80	java/util/zip/ZipEntry:<init>	(Ljava/lang/String;)V
    //   77: invokevirtual 86	java/util/zip/ZipOutputStream:putNextEntry	(Ljava/util/zip/ZipEntry;)V
    //   80: aload 7
    //   82: getstatic 26	com/androlua/util/ZipUtil:b	[B
    //   85: iconst_0
    //   86: getstatic 26	com/androlua/util/ZipUtil:b	[B
    //   89: arraylength
    //   90: invokevirtual 90	java/io/BufferedInputStream:read	([BII)I
    //   93: istore_3
    //   94: iload_3
    //   95: iconst_m1
    //   96: if_icmpeq +15 -> 111
    //   99: aload_1
    //   100: getstatic 26	com/androlua/util/ZipUtil:b	[B
    //   103: iconst_0
    //   104: iload_3
    //   105: invokevirtual 94	java/util/zip/ZipOutputStream:write	([BII)V
    //   108: goto -28 -> 80
    //   111: aload 7
    //   113: ifnull +147 -> 260
    //   116: aload 7
    //   118: invokevirtual 97	java/io/BufferedInputStream:close	()V
    //   121: return
    //   122: astore_0
    //   123: goto +71 -> 194
    //   126: astore_1
    //   127: aload 7
    //   129: astore_0
    //   130: goto +22 -> 152
    //   133: astore_1
    //   134: aload 7
    //   136: astore_0
    //   137: goto +35 -> 172
    //   140: astore_0
    //   141: aload 6
    //   143: astore 7
    //   145: goto +49 -> 194
    //   148: astore_1
    //   149: aload 8
    //   151: astore_0
    //   152: aload_0
    //   153: astore 6
    //   155: aload_1
    //   156: invokevirtual 100	java/io/IOException:printStackTrace	()V
    //   159: aload_0
    //   160: ifnull +100 -> 260
    //   163: aload_0
    //   164: invokevirtual 97	java/io/BufferedInputStream:close	()V
    //   167: return
    //   168: astore_1
    //   169: aload 9
    //   171: astore_0
    //   172: aload_0
    //   173: astore 6
    //   175: aload_1
    //   176: invokevirtual 101	java/io/FileNotFoundException:printStackTrace	()V
    //   179: aload_0
    //   180: ifnull +80 -> 260
    //   183: aload_0
    //   184: invokevirtual 97	java/io/BufferedInputStream:close	()V
    //   187: return
    //   188: astore_0
    //   189: aload_0
    //   190: invokevirtual 100	java/io/IOException:printStackTrace	()V
    //   193: return
    //   194: aload 7
    //   196: ifnull +16 -> 212
    //   199: aload 7
    //   201: invokevirtual 97	java/io/BufferedInputStream:close	()V
    //   204: goto +8 -> 212
    //   207: astore_1
    //   208: aload_1
    //   209: invokevirtual 100	java/io/IOException:printStackTrace	()V
    //   212: aload_0
    //   213: athrow
    //   214: aload_0
    //   215: invokevirtual 104	java/io/File:isDirectory	()Z
    //   218: ifeq +42 -> 260
    //   221: aload_0
    //   222: invokevirtual 108	java/io/File:listFiles	()[Ljava/io/File;
    //   225: astore_0
    //   226: aload_0
    //   227: ifnull +33 -> 260
    //   230: aload_0
    //   231: arraylength
    //   232: ifle +28 -> 260
    //   235: aload_0
    //   236: arraylength
    //   237: istore 4
    //   239: iload_3
    //   240: iload 4
    //   242: if_icmpge +18 -> 260
    //   245: aload_0
    //   246: iload_3
    //   247: aaload
    //   248: aload_1
    //   249: aload_2
    //   250: invokestatic 110	com/androlua/util/ZipUtil:a	(Ljava/io/File;Ljava/util/zip/ZipOutputStream;Ljava/lang/String;)V
    //   253: iload_3
    //   254: iconst_1
    //   255: iadd
    //   256: istore_3
    //   257: goto -18 -> 239
    //   260: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	261	0	paramFile	File
    //   0	261	1	paramZipOutputStream	ZipOutputStream
    //   0	261	2	paramString	String
    //   7	250	3	i	int
    //   237	6	4	j	int
    //   4	5	5	bool	boolean
    //   20	154	6	localFile	File
    //   41	159	7	localObject1	Object
    //   14	136	8	localObject2	Object
    //   17	153	9	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   43	80	122	finally
    //   80	94	122	finally
    //   99	108	122	finally
    //   43	80	126	java/io/IOException
    //   80	94	126	java/io/IOException
    //   99	108	126	java/io/IOException
    //   43	80	133	java/io/FileNotFoundException
    //   80	94	133	java/io/FileNotFoundException
    //   99	108	133	java/io/FileNotFoundException
    //   22	43	140	finally
    //   155	159	140	finally
    //   175	179	140	finally
    //   22	43	148	java/io/IOException
    //   22	43	168	java/io/FileNotFoundException
    //   116	121	188	java/io/IOException
    //   163	167	188	java/io/IOException
    //   183	187	188	java/io/IOException
    //   199	204	207	java/io/IOException
  }
  
  public static void append(String paramString1, String paramString2)
  {
    ZipFile localZipFile = new ZipFile(paramString1);
    paramString1 = new ZipOutputStream(new FileOutputStream(paramString1));
    Object localObject1 = localZipFile.entries();
    while (((Enumeration)localObject1).hasMoreElements())
    {
      localObject2 = (ZipEntry)((Enumeration)localObject1).nextElement();
      localObject3 = System.out;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("copy: ");
      localStringBuilder.append(((ZipEntry)localObject2).getName());
      ((PrintStream)localObject3).println(localStringBuilder.toString());
      paramString1.putNextEntry((ZipEntry)localObject2);
      if (!((ZipEntry)localObject2).isDirectory()) {
        copy(localZipFile.getInputStream((ZipEntry)localObject2), paramString1);
      }
      paramString1.closeEntry();
    }
    localObject1 = new ZipEntry(paramString2);
    Object localObject2 = System.out;
    Object localObject3 = new StringBuilder();
    ((StringBuilder)localObject3).append("append: ");
    ((StringBuilder)localObject3).append(((ZipEntry)localObject1).getName());
    ((PrintStream)localObject2).println(((StringBuilder)localObject3).toString());
    paramString1.putNextEntry((ZipEntry)localObject1);
    copy(new FileInputStream(new File(paramString2)), paramString1);
    paramString1.closeEntry();
    localZipFile.close();
    paramString1.close();
  }
  
  public static void copy(InputStream paramInputStream, OutputStream paramOutputStream)
  {
    for (;;)
    {
      int i = paramInputStream.read(b);
      if (i == -1) {
        break;
      }
      paramOutputStream.write(b, 0, i);
    }
  }
  
  /* Error */
  public static boolean unzip(File paramFile, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 176	java/io/File:exists	()Z
    //   4: istore_3
    //   5: iconst_0
    //   6: istore 4
    //   8: iconst_0
    //   9: istore 5
    //   11: iload_3
    //   12: ifne +45 -> 57
    //   15: getstatic 24	com/androlua/util/ZipUtil:a	Ljava/util/logging/Logger;
    //   18: astore_1
    //   19: new 136	java/lang/StringBuilder
    //   22: dup
    //   23: invokespecial 137	java/lang/StringBuilder:<init>	()V
    //   26: astore 6
    //   28: aload 6
    //   30: aload_0
    //   31: invokevirtual 177	java/io/File:getName	()Ljava/lang/String;
    //   34: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: pop
    //   38: aload 6
    //   40: ldc -77
    //   42: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: pop
    //   46: aload_1
    //   47: aload 6
    //   49: invokevirtual 146	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   52: invokevirtual 182	java/util/logging/Logger:info	(Ljava/lang/String;)V
    //   55: iconst_0
    //   56: ireturn
    //   57: new 37	java/io/File
    //   60: dup
    //   61: aload_1
    //   62: invokespecial 161	java/io/File:<init>	(Ljava/lang/String;)V
    //   65: astore 6
    //   67: aload 6
    //   69: invokevirtual 176	java/io/File:exists	()Z
    //   72: ifne +50 -> 122
    //   75: aload 6
    //   77: invokevirtual 185	java/io/File:mkdirs	()Z
    //   80: ifne +42 -> 122
    //   83: getstatic 24	com/androlua/util/ZipUtil:a	Ljava/util/logging/Logger;
    //   86: astore_0
    //   87: new 136	java/lang/StringBuilder
    //   90: dup
    //   91: invokespecial 137	java/lang/StringBuilder:<init>	()V
    //   94: astore_1
    //   95: aload_1
    //   96: ldc -69
    //   98: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: pop
    //   102: aload_1
    //   103: aload 6
    //   105: invokevirtual 177	java/io/File:getName	()Ljava/lang/String;
    //   108: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   111: pop
    //   112: aload_0
    //   113: aload_1
    //   114: invokevirtual 146	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   117: invokevirtual 182	java/util/logging/Logger:info	(Ljava/lang/String;)V
    //   120: iconst_0
    //   121: ireturn
    //   122: aconst_null
    //   123: astore 7
    //   125: aconst_null
    //   126: astore 8
    //   128: aconst_null
    //   129: astore 6
    //   131: getstatic 24	com/androlua/util/ZipUtil:a	Ljava/util/logging/Logger;
    //   134: ldc -67
    //   136: invokevirtual 182	java/util/logging/Logger:info	(Ljava/lang/String;)V
    //   139: new 191	java/util/zip/ZipInputStream
    //   142: dup
    //   143: new 43	java/io/BufferedInputStream
    //   146: dup
    //   147: new 193	java/util/zip/CheckedInputStream
    //   150: dup
    //   151: new 45	java/io/FileInputStream
    //   154: dup
    //   155: aload_0
    //   156: invokespecial 48	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   159: new 195	java/util/zip/Adler32
    //   162: dup
    //   163: invokespecial 196	java/util/zip/Adler32:<init>	()V
    //   166: invokespecial 199	java/util/zip/CheckedInputStream:<init>	(Ljava/io/InputStream;Ljava/util/zip/Checksum;)V
    //   169: invokespecial 202	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   172: invokespecial 203	java/util/zip/ZipInputStream:<init>	(Ljava/io/InputStream;)V
    //   175: astore_0
    //   176: aload_0
    //   177: invokevirtual 207	java/util/zip/ZipInputStream:getNextEntry	()Ljava/util/zip/ZipEntry;
    //   180: astore 6
    //   182: aload 6
    //   184: ifnull +197 -> 381
    //   187: aload 6
    //   189: invokevirtual 143	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   192: astore 6
    //   194: new 136	java/lang/StringBuilder
    //   197: dup
    //   198: invokespecial 137	java/lang/StringBuilder:<init>	()V
    //   201: astore 7
    //   203: aload 7
    //   205: aload_1
    //   206: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   209: pop
    //   210: aload 7
    //   212: ldc -47
    //   214: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   217: pop
    //   218: aload 7
    //   220: aload 6
    //   222: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: pop
    //   226: aload 7
    //   228: invokevirtual 146	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   231: astore 7
    //   233: getstatic 70	java/lang/System:out	Ljava/io/PrintStream;
    //   236: aload 7
    //   238: invokevirtual 76	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   241: new 37	java/io/File
    //   244: dup
    //   245: aload 7
    //   247: invokespecial 161	java/io/File:<init>	(Ljava/lang/String;)V
    //   250: invokevirtual 213	java/io/File:getParentFile	()Ljava/io/File;
    //   253: astore 6
    //   255: aload 6
    //   257: invokevirtual 176	java/io/File:exists	()Z
    //   260: ifne +55 -> 315
    //   263: aload 6
    //   265: invokevirtual 185	java/io/File:mkdirs	()Z
    //   268: ifne +47 -> 315
    //   271: new 136	java/lang/StringBuilder
    //   274: dup
    //   275: invokespecial 137	java/lang/StringBuilder:<init>	()V
    //   278: astore_1
    //   279: aload_1
    //   280: ldc -41
    //   282: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   285: pop
    //   286: aload_1
    //   287: aload 6
    //   289: invokevirtual 177	java/io/File:getName	()Ljava/lang/String;
    //   292: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   295: pop
    //   296: aload_1
    //   297: ldc -39
    //   299: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   302: pop
    //   303: new 219	java/lang/RuntimeException
    //   306: dup
    //   307: aload_1
    //   308: invokevirtual 146	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   311: invokespecial 220	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   314: athrow
    //   315: new 222	java/io/BufferedOutputStream
    //   318: dup
    //   319: new 117	java/io/FileOutputStream
    //   322: dup
    //   323: aload 7
    //   325: invokespecial 118	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   328: getstatic 26	com/androlua/util/ZipUtil:b	[B
    //   331: arraylength
    //   332: invokespecial 225	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;I)V
    //   335: astore 6
    //   337: aload_0
    //   338: getstatic 26	com/androlua/util/ZipUtil:b	[B
    //   341: iconst_0
    //   342: getstatic 26	com/androlua/util/ZipUtil:b	[B
    //   345: arraylength
    //   346: invokevirtual 226	java/util/zip/ZipInputStream:read	([BII)I
    //   349: istore_2
    //   350: iload_2
    //   351: iconst_m1
    //   352: if_icmpeq +16 -> 368
    //   355: aload 6
    //   357: getstatic 26	com/androlua/util/ZipUtil:b	[B
    //   360: iconst_0
    //   361: iload_2
    //   362: invokevirtual 227	java/io/BufferedOutputStream:write	([BII)V
    //   365: goto -28 -> 337
    //   368: aload 6
    //   370: invokevirtual 230	java/io/BufferedOutputStream:flush	()V
    //   373: aload 6
    //   375: invokevirtual 231	java/io/BufferedOutputStream:close	()V
    //   378: goto -202 -> 176
    //   381: iconst_1
    //   382: istore 4
    //   384: iconst_1
    //   385: istore 5
    //   387: iload 4
    //   389: istore_3
    //   390: aload_0
    //   391: ifnull +102 -> 493
    //   394: iload 5
    //   396: istore_3
    //   397: aload_0
    //   398: invokevirtual 232	java/util/zip/ZipInputStream:close	()V
    //   401: iload 4
    //   403: istore_3
    //   404: goto +89 -> 493
    //   407: astore_0
    //   408: aload_0
    //   409: invokevirtual 100	java/io/IOException:printStackTrace	()V
    //   412: goto +81 -> 493
    //   415: astore_1
    //   416: goto +105 -> 521
    //   419: astore_1
    //   420: goto +18 -> 438
    //   423: astore_1
    //   424: goto +45 -> 469
    //   427: astore_1
    //   428: aload 6
    //   430: astore_0
    //   431: goto +90 -> 521
    //   434: astore_1
    //   435: aload 7
    //   437: astore_0
    //   438: aload_0
    //   439: astore 6
    //   441: aload_1
    //   442: invokevirtual 100	java/io/IOException:printStackTrace	()V
    //   445: iload 4
    //   447: istore_3
    //   448: aload_0
    //   449: ifnull +44 -> 493
    //   452: iload 5
    //   454: istore_3
    //   455: aload_0
    //   456: invokevirtual 232	java/util/zip/ZipInputStream:close	()V
    //   459: iload 4
    //   461: istore_3
    //   462: goto +31 -> 493
    //   465: astore_1
    //   466: aload 8
    //   468: astore_0
    //   469: aload_0
    //   470: astore 6
    //   472: aload_1
    //   473: invokevirtual 101	java/io/FileNotFoundException:printStackTrace	()V
    //   476: iload 4
    //   478: istore_3
    //   479: aload_0
    //   480: ifnull +13 -> 493
    //   483: iload 5
    //   485: istore_3
    //   486: aload_0
    //   487: invokevirtual 232	java/util/zip/ZipInputStream:close	()V
    //   490: iload 4
    //   492: istore_3
    //   493: iload_3
    //   494: ifeq +17 -> 511
    //   497: getstatic 24	com/androlua/util/ZipUtil:a	Ljava/util/logging/Logger;
    //   500: astore_0
    //   501: ldc -22
    //   503: astore_1
    //   504: aload_0
    //   505: aload_1
    //   506: invokevirtual 182	java/util/logging/Logger:info	(Ljava/lang/String;)V
    //   509: iload_3
    //   510: ireturn
    //   511: getstatic 24	com/androlua/util/ZipUtil:a	Ljava/util/logging/Logger;
    //   514: astore_0
    //   515: ldc -20
    //   517: astore_1
    //   518: goto -14 -> 504
    //   521: aload_0
    //   522: ifnull +15 -> 537
    //   525: aload_0
    //   526: invokevirtual 232	java/util/zip/ZipInputStream:close	()V
    //   529: goto +8 -> 537
    //   532: astore_0
    //   533: aload_0
    //   534: invokevirtual 100	java/io/IOException:printStackTrace	()V
    //   537: aload_1
    //   538: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	539	0	paramFile	File
    //   0	539	1	paramString	String
    //   349	13	2	i	int
    //   4	506	3	bool1	boolean
    //   6	485	4	bool2	boolean
    //   9	475	5	bool3	boolean
    //   26	445	6	localObject1	Object
    //   123	313	7	localObject2	Object
    //   126	341	8	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   397	401	407	java/io/IOException
    //   455	459	407	java/io/IOException
    //   486	490	407	java/io/IOException
    //   176	182	415	finally
    //   187	315	415	finally
    //   315	337	415	finally
    //   337	350	415	finally
    //   355	365	415	finally
    //   368	378	415	finally
    //   176	182	419	java/io/IOException
    //   187	315	419	java/io/IOException
    //   315	337	419	java/io/IOException
    //   337	350	419	java/io/IOException
    //   355	365	419	java/io/IOException
    //   368	378	419	java/io/IOException
    //   176	182	423	java/io/FileNotFoundException
    //   187	315	423	java/io/FileNotFoundException
    //   315	337	423	java/io/FileNotFoundException
    //   337	350	423	java/io/FileNotFoundException
    //   355	365	423	java/io/FileNotFoundException
    //   368	378	423	java/io/FileNotFoundException
    //   139	176	427	finally
    //   441	445	427	finally
    //   472	476	427	finally
    //   139	176	434	java/io/IOException
    //   139	176	465	java/io/FileNotFoundException
    //   525	529	532	java/io/IOException
  }
  
  public static boolean unzip(String paramString1, String paramString2)
  {
    return unzip(new File(paramString1), paramString2);
  }
  
  /* Error */
  public static boolean zip(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: new 37	java/io/File
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 161	java/io/File:<init>	(Ljava/lang/String;)V
    //   8: astore 6
    //   10: aload 6
    //   12: invokevirtual 176	java/io/File:exists	()Z
    //   15: istore_2
    //   16: iconst_0
    //   17: istore_3
    //   18: iconst_0
    //   19: istore 4
    //   21: iload_2
    //   22: ifne +47 -> 69
    //   25: getstatic 24	com/androlua/util/ZipUtil:a	Ljava/util/logging/Logger;
    //   28: astore 5
    //   30: new 136	java/lang/StringBuilder
    //   33: dup
    //   34: invokespecial 137	java/lang/StringBuilder:<init>	()V
    //   37: astore 6
    //   39: aload 6
    //   41: aload_0
    //   42: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: pop
    //   46: ldc -77
    //   48: astore_1
    //   49: aload 6
    //   51: astore_0
    //   52: aload_0
    //   53: aload_1
    //   54: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: pop
    //   58: aload 5
    //   60: aload_0
    //   61: invokevirtual 146	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   64: invokevirtual 182	java/util/logging/Logger:info	(Ljava/lang/String;)V
    //   67: iconst_0
    //   68: ireturn
    //   69: aload 6
    //   71: invokevirtual 104	java/io/File:isDirectory	()Z
    //   74: ifne +34 -> 108
    //   77: getstatic 24	com/androlua/util/ZipUtil:a	Ljava/util/logging/Logger;
    //   80: astore 5
    //   82: new 136	java/lang/StringBuilder
    //   85: dup
    //   86: invokespecial 137	java/lang/StringBuilder:<init>	()V
    //   89: astore_1
    //   90: aload_1
    //   91: aload_0
    //   92: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: pop
    //   96: ldc -14
    //   98: astore 6
    //   100: aload_1
    //   101: astore_0
    //   102: aload 6
    //   104: astore_1
    //   105: goto -53 -> 52
    //   108: new 136	java/lang/StringBuilder
    //   111: dup
    //   112: invokespecial 137	java/lang/StringBuilder:<init>	()V
    //   115: astore_0
    //   116: aload_0
    //   117: aload_1
    //   118: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   121: pop
    //   122: aload_0
    //   123: ldc -47
    //   125: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   128: pop
    //   129: aload_0
    //   130: aload 6
    //   132: invokevirtual 177	java/io/File:getName	()Ljava/lang/String;
    //   135: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: pop
    //   139: aload_0
    //   140: ldc -12
    //   142: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: pop
    //   146: new 37	java/io/File
    //   149: dup
    //   150: aload_0
    //   151: invokevirtual 146	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   154: invokespecial 161	java/io/File:<init>	(Ljava/lang/String;)V
    //   157: astore_1
    //   158: aload_1
    //   159: invokevirtual 176	java/io/File:exists	()Z
    //   162: ifeq +31 -> 193
    //   165: getstatic 24	com/androlua/util/ZipUtil:a	Ljava/util/logging/Logger;
    //   168: astore 5
    //   170: new 136	java/lang/StringBuilder
    //   173: dup
    //   174: invokespecial 137	java/lang/StringBuilder:<init>	()V
    //   177: astore_0
    //   178: aload_0
    //   179: aload_1
    //   180: invokevirtual 177	java/io/File:getName	()Ljava/lang/String;
    //   183: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: pop
    //   187: ldc -10
    //   189: astore_1
    //   190: goto -138 -> 52
    //   193: aload_1
    //   194: invokevirtual 213	java/io/File:getParentFile	()Ljava/io/File;
    //   197: invokevirtual 176	java/io/File:exists	()Z
    //   200: ifne +41 -> 241
    //   203: aload_1
    //   204: invokevirtual 213	java/io/File:getParentFile	()Ljava/io/File;
    //   207: invokevirtual 185	java/io/File:mkdirs	()Z
    //   210: ifne +31 -> 241
    //   213: getstatic 24	com/androlua/util/ZipUtil:a	Ljava/util/logging/Logger;
    //   216: astore 5
    //   218: new 136	java/lang/StringBuilder
    //   221: dup
    //   222: invokespecial 137	java/lang/StringBuilder:<init>	()V
    //   225: astore_0
    //   226: aload_0
    //   227: ldc -69
    //   229: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   232: pop
    //   233: aload_1
    //   234: invokevirtual 177	java/io/File:getName	()Ljava/lang/String;
    //   237: astore_1
    //   238: goto -186 -> 52
    //   241: getstatic 24	com/androlua/util/ZipUtil:a	Ljava/util/logging/Logger;
    //   244: ldc -8
    //   246: invokevirtual 182	java/util/logging/Logger:info	(Ljava/lang/String;)V
    //   249: aconst_null
    //   250: astore 5
    //   252: aconst_null
    //   253: astore_0
    //   254: new 82	java/util/zip/ZipOutputStream
    //   257: dup
    //   258: new 222	java/io/BufferedOutputStream
    //   261: dup
    //   262: new 250	java/util/zip/CheckedOutputStream
    //   265: dup
    //   266: new 117	java/io/FileOutputStream
    //   269: dup
    //   270: aload_1
    //   271: invokespecial 251	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   274: new 195	java/util/zip/Adler32
    //   277: dup
    //   278: invokespecial 196	java/util/zip/Adler32:<init>	()V
    //   281: invokespecial 254	java/util/zip/CheckedOutputStream:<init>	(Ljava/io/OutputStream;Ljava/util/zip/Checksum;)V
    //   284: invokespecial 255	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   287: invokespecial 121	java/util/zip/ZipOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   290: astore_1
    //   291: aload_1
    //   292: bipush 8
    //   294: invokevirtual 259	java/util/zip/ZipOutputStream:setMethod	(I)V
    //   297: aload 6
    //   299: aload_1
    //   300: aload 6
    //   302: invokevirtual 177	java/io/File:getName	()Ljava/lang/String;
    //   305: invokestatic 110	com/androlua/util/ZipUtil:a	(Ljava/io/File;Ljava/util/zip/ZipOutputStream;Ljava/lang/String;)V
    //   308: iconst_1
    //   309: istore_3
    //   310: iconst_1
    //   311: istore 4
    //   313: iload_3
    //   314: istore_2
    //   315: aload_1
    //   316: ifnull +95 -> 411
    //   319: aload_1
    //   320: invokevirtual 158	java/util/zip/ZipOutputStream:closeEntry	()V
    //   323: goto +8 -> 331
    //   326: astore_0
    //   327: aload_0
    //   328: invokevirtual 100	java/io/IOException:printStackTrace	()V
    //   331: iload 4
    //   333: istore_2
    //   334: aload_1
    //   335: invokevirtual 163	java/util/zip/ZipOutputStream:close	()V
    //   338: iload_3
    //   339: istore_2
    //   340: goto +71 -> 411
    //   343: astore_0
    //   344: aload_0
    //   345: invokevirtual 100	java/io/IOException:printStackTrace	()V
    //   348: goto +63 -> 411
    //   351: astore 5
    //   353: aload_1
    //   354: astore_0
    //   355: aload 5
    //   357: astore_1
    //   358: goto +81 -> 439
    //   361: astore 5
    //   363: goto +14 -> 377
    //   366: astore_1
    //   367: goto +72 -> 439
    //   370: astore_0
    //   371: aload 5
    //   373: astore_1
    //   374: aload_0
    //   375: astore 5
    //   377: aload_1
    //   378: astore_0
    //   379: aload 5
    //   381: invokevirtual 101	java/io/FileNotFoundException:printStackTrace	()V
    //   384: iload_3
    //   385: istore_2
    //   386: aload_1
    //   387: ifnull +24 -> 411
    //   390: aload_1
    //   391: invokevirtual 158	java/util/zip/ZipOutputStream:closeEntry	()V
    //   394: goto +8 -> 402
    //   397: astore_0
    //   398: aload_0
    //   399: invokevirtual 100	java/io/IOException:printStackTrace	()V
    //   402: iload 4
    //   404: istore_2
    //   405: aload_1
    //   406: invokevirtual 163	java/util/zip/ZipOutputStream:close	()V
    //   409: iload_3
    //   410: istore_2
    //   411: iload_2
    //   412: ifeq +17 -> 429
    //   415: getstatic 24	com/androlua/util/ZipUtil:a	Ljava/util/logging/Logger;
    //   418: astore_0
    //   419: ldc -22
    //   421: astore_1
    //   422: aload_0
    //   423: aload_1
    //   424: invokevirtual 182	java/util/logging/Logger:info	(Ljava/lang/String;)V
    //   427: iload_2
    //   428: ireturn
    //   429: getstatic 24	com/androlua/util/ZipUtil:a	Ljava/util/logging/Logger;
    //   432: astore_0
    //   433: ldc -20
    //   435: astore_1
    //   436: goto -14 -> 422
    //   439: aload_0
    //   440: ifnull +29 -> 469
    //   443: aload_0
    //   444: invokevirtual 158	java/util/zip/ZipOutputStream:closeEntry	()V
    //   447: goto +10 -> 457
    //   450: astore 5
    //   452: aload 5
    //   454: invokevirtual 100	java/io/IOException:printStackTrace	()V
    //   457: aload_0
    //   458: invokevirtual 163	java/util/zip/ZipOutputStream:close	()V
    //   461: goto +8 -> 469
    //   464: astore_0
    //   465: aload_0
    //   466: invokevirtual 100	java/io/IOException:printStackTrace	()V
    //   469: aload_1
    //   470: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	471	0	paramString1	String
    //   0	471	1	paramString2	String
    //   15	413	2	bool1	boolean
    //   17	393	3	bool2	boolean
    //   19	384	4	bool3	boolean
    //   28	223	5	localLogger	Logger
    //   351	5	5	localObject1	Object
    //   361	11	5	localFileNotFoundException	java.io.FileNotFoundException
    //   375	5	5	str	String
    //   450	3	5	localIOException	java.io.IOException
    //   8	293	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   319	323	326	java/io/IOException
    //   334	338	343	java/io/IOException
    //   405	409	343	java/io/IOException
    //   291	308	351	finally
    //   291	308	361	java/io/FileNotFoundException
    //   254	291	366	finally
    //   379	384	366	finally
    //   254	291	370	java/io/FileNotFoundException
    //   390	394	397	java/io/IOException
    //   443	447	450	java/io/IOException
    //   457	461	464	java/io/IOException
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\util\ZipUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */