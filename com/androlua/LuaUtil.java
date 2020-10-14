package com.androlua;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class LuaUtil
{
  private static final byte[] a = new byte['က'];
  public static final HashMap<String, String> mFileTypes = new HashMap();
  
  static
  {
    mFileTypes.put("FFD8FF", "jpg");
    mFileTypes.put("89504E47", "png");
    mFileTypes.put("47494638", "gif");
    mFileTypes.put("49492A00", "tif");
    mFileTypes.put("424D", "bmp");
    mFileTypes.put("41433130", "dwg");
    mFileTypes.put("38425053", "psd");
    mFileTypes.put("7B5C727466", "rtf");
    mFileTypes.put("3C3F786D6C", "xml");
    mFileTypes.put("68746D6C3E", "html");
    mFileTypes.put("44656C69766572792D646174653A", "eml");
    mFileTypes.put("D0CF11E0", "doc");
    mFileTypes.put("5374616E64617264204A", "mdb");
    mFileTypes.put("252150532D41646F6265", "ps");
    mFileTypes.put("255044462D312E", "pdf");
    mFileTypes.put("504B0304", "docx");
    mFileTypes.put("52617221", "rar");
    mFileTypes.put("57415645", "wav");
    mFileTypes.put("41564920", "avi");
    mFileTypes.put("2E524D46", "rm");
    mFileTypes.put("000001BA", "mpg");
    mFileTypes.put("000001B3", "mpg");
    mFileTypes.put("6D6F6F76", "mov");
    mFileTypes.put("3026B2758E66CF11", "asf");
    mFileTypes.put("4D546864", "mid");
    mFileTypes.put("1F8B08", "gz");
  }
  
  private static String a(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if ((paramArrayOfByte != null) && (paramArrayOfByte.length > 0))
    {
      int i = 0;
      while (i < paramArrayOfByte.length)
      {
        String str = Integer.toHexString(paramArrayOfByte[i] & 0xFF).toUpperCase();
        if (str.length() < 2) {
          localStringBuilder.append(0);
        }
        localStringBuilder.append(str);
        i += 1;
      }
      return localStringBuilder.toString();
    }
    return null;
  }
  
  /* Error */
  private static void a(File paramFile, java.util.zip.ZipOutputStream paramZipOutputStream, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 169	java/io/File:isFile	()Z
    //   4: istore 5
    //   6: iconst_0
    //   7: istore_3
    //   8: iload 5
    //   10: ifeq +218 -> 228
    //   13: aconst_null
    //   14: astore 8
    //   16: aconst_null
    //   17: astore 9
    //   19: aconst_null
    //   20: astore 6
    //   22: new 171	java/io/BufferedInputStream
    //   25: dup
    //   26: new 173	java/io/FileInputStream
    //   29: dup
    //   30: aload_0
    //   31: invokespecial 176	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   34: getstatic 13	com/androlua/LuaUtil:a	[B
    //   37: arraylength
    //   38: invokespecial 179	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;I)V
    //   41: astore 7
    //   43: new 131	java/lang/StringBuilder
    //   46: dup
    //   47: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   50: astore 6
    //   52: aload 6
    //   54: aload_2
    //   55: invokevirtual 155	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   58: pop
    //   59: aload 6
    //   61: aload_0
    //   62: invokevirtual 182	java/io/File:getName	()Ljava/lang/String;
    //   65: invokevirtual 155	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: pop
    //   69: aload 6
    //   71: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   74: astore_0
    //   75: getstatic 188	java/lang/System:out	Ljava/io/PrintStream;
    //   78: aload_0
    //   79: invokevirtual 194	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   82: aload_1
    //   83: new 196	java/util/zip/ZipEntry
    //   86: dup
    //   87: aload_0
    //   88: invokespecial 198	java/util/zip/ZipEntry:<init>	(Ljava/lang/String;)V
    //   91: invokevirtual 204	java/util/zip/ZipOutputStream:putNextEntry	(Ljava/util/zip/ZipEntry;)V
    //   94: aload 7
    //   96: getstatic 13	com/androlua/LuaUtil:a	[B
    //   99: iconst_0
    //   100: getstatic 13	com/androlua/LuaUtil:a	[B
    //   103: arraylength
    //   104: invokevirtual 208	java/io/BufferedInputStream:read	([BII)I
    //   107: istore_3
    //   108: iload_3
    //   109: iconst_m1
    //   110: if_icmpeq +15 -> 125
    //   113: aload_1
    //   114: getstatic 13	com/androlua/LuaUtil:a	[B
    //   117: iconst_0
    //   118: iload_3
    //   119: invokevirtual 212	java/util/zip/ZipOutputStream:write	([BII)V
    //   122: goto -28 -> 94
    //   125: aload 7
    //   127: ifnull +208 -> 335
    //   130: aload 7
    //   132: invokevirtual 215	java/io/BufferedInputStream:close	()V
    //   135: return
    //   136: astore_0
    //   137: goto +71 -> 208
    //   140: astore_1
    //   141: aload 7
    //   143: astore_0
    //   144: goto +22 -> 166
    //   147: astore_1
    //   148: aload 7
    //   150: astore_0
    //   151: goto +35 -> 186
    //   154: astore_0
    //   155: aload 6
    //   157: astore 7
    //   159: goto +49 -> 208
    //   162: astore_1
    //   163: aload 8
    //   165: astore_0
    //   166: aload_0
    //   167: astore 6
    //   169: aload_1
    //   170: invokevirtual 218	java/io/IOException:printStackTrace	()V
    //   173: aload_0
    //   174: ifnull +161 -> 335
    //   177: aload_0
    //   178: invokevirtual 215	java/io/BufferedInputStream:close	()V
    //   181: return
    //   182: astore_1
    //   183: aload 9
    //   185: astore_0
    //   186: aload_0
    //   187: astore 6
    //   189: aload_1
    //   190: invokevirtual 219	java/io/FileNotFoundException:printStackTrace	()V
    //   193: aload_0
    //   194: ifnull +141 -> 335
    //   197: aload_0
    //   198: invokevirtual 215	java/io/BufferedInputStream:close	()V
    //   201: return
    //   202: astore_0
    //   203: aload_0
    //   204: invokevirtual 218	java/io/IOException:printStackTrace	()V
    //   207: return
    //   208: aload 7
    //   210: ifnull +16 -> 226
    //   213: aload 7
    //   215: invokevirtual 215	java/io/BufferedInputStream:close	()V
    //   218: goto +8 -> 226
    //   221: astore_1
    //   222: aload_1
    //   223: invokevirtual 218	java/io/IOException:printStackTrace	()V
    //   226: aload_0
    //   227: athrow
    //   228: aload_0
    //   229: invokevirtual 222	java/io/File:isDirectory	()Z
    //   232: ifeq +103 -> 335
    //   235: aload_0
    //   236: invokevirtual 226	java/io/File:listFiles	()[Ljava/io/File;
    //   239: astore_0
    //   240: aload_0
    //   241: ifnull +94 -> 335
    //   244: aload_0
    //   245: arraylength
    //   246: ifle +89 -> 335
    //   249: aload_0
    //   250: arraylength
    //   251: istore 4
    //   253: iload_3
    //   254: iload 4
    //   256: if_icmpge +79 -> 335
    //   259: aload_0
    //   260: iload_3
    //   261: aaload
    //   262: astore 6
    //   264: aload 6
    //   266: invokevirtual 169	java/io/File:isFile	()Z
    //   269: ifeq +13 -> 282
    //   272: aload 6
    //   274: aload_1
    //   275: aload_2
    //   276: invokestatic 228	com/androlua/LuaUtil:a	(Ljava/io/File;Ljava/util/zip/ZipOutputStream;Ljava/lang/String;)V
    //   279: goto +49 -> 328
    //   282: new 131	java/lang/StringBuilder
    //   285: dup
    //   286: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   289: astore 7
    //   291: aload 7
    //   293: aload_2
    //   294: invokevirtual 155	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   297: pop
    //   298: aload 7
    //   300: aload 6
    //   302: invokevirtual 182	java/io/File:getName	()Ljava/lang/String;
    //   305: invokevirtual 155	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   308: pop
    //   309: aload 7
    //   311: ldc -26
    //   313: invokevirtual 155	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   316: pop
    //   317: aload 6
    //   319: aload_1
    //   320: aload 7
    //   322: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   325: invokestatic 228	com/androlua/LuaUtil:a	(Ljava/io/File;Ljava/util/zip/ZipOutputStream;Ljava/lang/String;)V
    //   328: iload_3
    //   329: iconst_1
    //   330: iadd
    //   331: istore_3
    //   332: goto -79 -> 253
    //   335: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	336	0	paramFile	File
    //   0	336	1	paramZipOutputStream	java.util.zip.ZipOutputStream
    //   0	336	2	paramString	String
    //   7	325	3	i	int
    //   251	6	4	j	int
    //   4	5	5	bool	boolean
    //   20	298	6	localObject1	Object
    //   41	280	7	localObject2	Object
    //   14	150	8	localObject3	Object
    //   17	167	9	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   43	94	136	finally
    //   94	108	136	finally
    //   113	122	136	finally
    //   43	94	140	java/io/IOException
    //   94	108	140	java/io/IOException
    //   113	122	140	java/io/IOException
    //   43	94	147	java/io/FileNotFoundException
    //   94	108	147	java/io/FileNotFoundException
    //   113	122	147	java/io/FileNotFoundException
    //   22	43	154	finally
    //   169	173	154	finally
    //   189	193	154	finally
    //   22	43	162	java/io/IOException
    //   22	43	182	java/io/FileNotFoundException
    //   130	135	202	java/io/IOException
    //   177	181	202	java/io/IOException
    //   197	201	202	java/io/IOException
    //   213	218	221	java/io/IOException
  }
  
  private boolean a(int paramInt1, int paramInt2, int[][] paramArrayOfInt, int paramInt3, int paramInt4)
  {
    int i = 0;
    while (i < paramInt3)
    {
      int[] arrayOfInt = paramArrayOfInt[paramInt1];
      int j = paramInt2 + i;
      if (arrayOfInt[j] == 1)
      {
        if (paramArrayOfInt[(paramInt1 + paramInt4)][j] != 0) {
          return false;
        }
        i += 1;
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  public static void assetsToSD(Context paramContext, String paramString1, String paramString2)
  {
    paramString2 = new FileOutputStream(paramString2);
    paramContext = paramContext.getAssets().open(paramString1);
    paramString1 = new byte['က'];
    for (;;)
    {
      int i = paramContext.read(paramString1);
      if (i <= 0) {
        break;
      }
      paramString2.write(paramString1, 0, i);
    }
    paramString2.flush();
    paramContext.close();
    paramString2.close();
  }
  
  public static Bitmap captureScreen(Activity paramActivity)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity = ((WindowManager)paramActivity.getSystemService("window")).getDefaultDisplay();
    paramActivity.getMetrics(localDisplayMetrics);
    int j = localDisplayMetrics.heightPixels;
    int k = localDisplayMetrics.widthPixels;
    int i = paramActivity.getPixelFormat();
    paramActivity = new PixelFormat();
    PixelFormat.getPixelFormatInfo(i, paramActivity);
    i = paramActivity.bytesPerPixel;
    int m = j * k;
    paramActivity = new byte[i * m];
    i = 0;
    try
    {
      Runtime.getRuntime().exec(new String[] { "/system/bin/su", "-c", "chmod 777 /dev/graphics/fb0" });
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    try
    {
      new DataInputStream(new FileInputStream(new File("/dev/graphics/fb0"))).readFully(paramActivity);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    int[] arrayOfInt = new int[m];
    while (i < arrayOfInt.length)
    {
      m = i * 4;
      int n = paramActivity[m];
      int i1 = paramActivity[(m + 1)];
      int i2 = paramActivity[(m + 2)];
      arrayOfInt[i] = (((paramActivity[(m + 3)] & 0xFF) << 24) + ((n & 0xFF) << 16) + ((i1 & 0xFF) << 8) + (i2 & 0xFF));
      i += 1;
    }
    return Bitmap.createBitmap(arrayOfInt, k, j, Bitmap.Config.ARGB_8888);
  }
  
  public static boolean copyDir(File paramFile1, File paramFile2)
  {
    File localFile = paramFile2.getParentFile();
    if (!localFile.exists()) {
      localFile.mkdirs();
    }
    boolean bool = paramFile1.isDirectory();
    int i = 0;
    if (bool)
    {
      paramFile1 = paramFile1.listFiles();
      bool = true;
      if ((paramFile1 != null) && (paramFile1.length != 0))
      {
        int j = paramFile1.length;
        while (i < j)
        {
          localFile = paramFile1[i];
          bool = copyDir(localFile, new File(paramFile2, localFile.getName()));
          i += 1;
        }
        return bool;
      }
      if (!paramFile2.exists())
      {
        bool = paramFile2.mkdirs();
        break label144;
      }
      return true;
    }
    try
    {
      if (!paramFile2.exists()) {
        paramFile2.createNewFile();
      }
      bool = copyFile(new FileInputStream(paramFile1), new FileOutputStream(paramFile2));
      label144:
      return bool;
    }
    catch (IOException paramFile1)
    {
      Log.i("lua", paramFile1.getMessage());
    }
    return false;
  }
  
  public static boolean copyDir(String paramString1, String paramString2)
  {
    return copyDir(new File(paramString1), new File(paramString2));
  }
  
  public static void copyFile(String paramString1, String paramString2)
  {
    try
    {
      copyFile(new FileInputStream(paramString1), new FileOutputStream(paramString2));
      return;
    }
    catch (IOException paramString1)
    {
      Log.i("lua", paramString1.getMessage());
    }
  }
  
  public static boolean copyFile(InputStream paramInputStream, OutputStream paramOutputStream)
  {
    try
    {
      byte[] arrayOfByte = new byte['က'];
      for (;;)
      {
        int i = paramInputStream.read(arrayOfByte);
        if (i == -1) {
          break;
        }
        paramOutputStream.write(arrayOfByte, 0, i);
      }
      return true;
    }
    catch (Exception paramInputStream)
    {
      Log.i("lua", paramInputStream.getMessage());
    }
    return false;
  }
  
  /* Error */
  public static String getFileHeader(InputStream paramInputStream)
  {
    // Byte code:
    //   0: iconst_4
    //   1: newarray <illegal type>
    //   3: astore_1
    //   4: aload_0
    //   5: aload_1
    //   6: iconst_0
    //   7: aload_1
    //   8: arraylength
    //   9: invokevirtual 392	java/io/InputStream:read	([BII)I
    //   12: pop
    //   13: aload_1
    //   14: invokestatic 394	com/androlua/LuaUtil:a	([B)Ljava/lang/String;
    //   17: astore_2
    //   18: aload_2
    //   19: astore_1
    //   20: aload_0
    //   21: ifnull +30 -> 51
    //   24: aload_0
    //   25: invokevirtual 260	java/io/InputStream:close	()V
    //   28: aload_2
    //   29: areturn
    //   30: astore_1
    //   31: aload_0
    //   32: ifnull +7 -> 39
    //   35: aload_0
    //   36: invokevirtual 260	java/io/InputStream:close	()V
    //   39: aload_1
    //   40: athrow
    //   41: aload_0
    //   42: ifnull +7 -> 49
    //   45: aload_0
    //   46: invokevirtual 260	java/io/InputStream:close	()V
    //   49: aconst_null
    //   50: astore_1
    //   51: aload_1
    //   52: areturn
    //   53: astore_1
    //   54: goto -13 -> 41
    //   57: astore_0
    //   58: aload_2
    //   59: areturn
    //   60: astore_0
    //   61: goto -22 -> 39
    //   64: astore_0
    //   65: goto -16 -> 49
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	68	0	paramInputStream	InputStream
    //   3	17	1	localObject1	Object
    //   30	10	1	localObject2	Object
    //   50	2	1	str1	String
    //   53	1	1	localException	Exception
    //   17	42	2	str2	String
    // Exception table:
    //   from	to	target	type
    //   0	18	30	finally
    //   0	18	53	java/lang/Exception
    //   24	28	57	java/io/IOException
    //   35	39	60	java/io/IOException
    //   45	49	64	java/io/IOException
  }
  
  public static String getFileMD5(File paramFile)
  {
    try
    {
      paramFile = getFileMD5(new FileInputStream(paramFile));
      return paramFile;
    }
    catch (FileNotFoundException paramFile)
    {
      for (;;) {}
    }
    return null;
  }
  
  /* Error */
  public static String getFileMD5(InputStream paramInputStream)
  {
    // Byte code:
    //   0: sipush 4096
    //   3: newarray <illegal type>
    //   5: astore_2
    //   6: ldc_w 400
    //   9: invokestatic 406	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   12: astore_3
    //   13: aload_0
    //   14: aload_2
    //   15: invokevirtual 253	java/io/InputStream:read	([B)I
    //   18: istore_1
    //   19: iload_1
    //   20: iconst_m1
    //   21: if_icmpeq +13 -> 34
    //   24: aload_3
    //   25: aload_2
    //   26: iconst_0
    //   27: iload_1
    //   28: invokevirtual 409	java/security/MessageDigest:update	([BII)V
    //   31: goto -18 -> 13
    //   34: new 411	java/math/BigInteger
    //   37: dup
    //   38: iconst_1
    //   39: aload_3
    //   40: invokevirtual 415	java/security/MessageDigest:digest	()[B
    //   43: invokespecial 418	java/math/BigInteger:<init>	(I[B)V
    //   46: bipush 16
    //   48: invokevirtual 420	java/math/BigInteger:toString	(I)Ljava/lang/String;
    //   51: astore_2
    //   52: aload_0
    //   53: invokevirtual 260	java/io/InputStream:close	()V
    //   56: aload_2
    //   57: areturn
    //   58: astore_0
    //   59: aload_0
    //   60: invokevirtual 337	java/lang/Exception:printStackTrace	()V
    //   63: aload_2
    //   64: areturn
    //   65: astore_2
    //   66: goto +21 -> 87
    //   69: astore_2
    //   70: aload_2
    //   71: invokevirtual 337	java/lang/Exception:printStackTrace	()V
    //   74: aload_0
    //   75: invokevirtual 260	java/io/InputStream:close	()V
    //   78: aconst_null
    //   79: areturn
    //   80: astore_0
    //   81: aload_0
    //   82: invokevirtual 337	java/lang/Exception:printStackTrace	()V
    //   85: aconst_null
    //   86: areturn
    //   87: aload_0
    //   88: invokevirtual 260	java/io/InputStream:close	()V
    //   91: goto +8 -> 99
    //   94: astore_0
    //   95: aload_0
    //   96: invokevirtual 337	java/lang/Exception:printStackTrace	()V
    //   99: aload_2
    //   100: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	101	0	paramInputStream	InputStream
    //   18	10	1	i	int
    //   5	59	2	localObject1	Object
    //   65	1	2	localObject2	Object
    //   69	31	2	localException	Exception
    //   12	28	3	localMessageDigest	java.security.MessageDigest
    // Exception table:
    //   from	to	target	type
    //   52	56	58	java/lang/Exception
    //   6	13	65	finally
    //   13	19	65	finally
    //   24	31	65	finally
    //   34	52	65	finally
    //   70	74	65	finally
    //   6	13	69	java/lang/Exception
    //   13	19	69	java/lang/Exception
    //   24	31	69	java/lang/Exception
    //   34	52	69	java/lang/Exception
    //   74	78	80	java/lang/Exception
    //   87	91	94	java/lang/Exception
  }
  
  public static String getFileMD5(String paramString)
  {
    return getFileMD5(new File(paramString));
  }
  
  public static String getFileSha1(File paramFile)
  {
    try
    {
      paramFile = getFileSha1(new FileInputStream(paramFile));
      return paramFile;
    }
    catch (FileNotFoundException paramFile)
    {
      for (;;) {}
    }
    return null;
  }
  
  /* Error */
  public static String getFileSha1(InputStream paramInputStream)
  {
    // Byte code:
    //   0: sipush 4096
    //   3: newarray <illegal type>
    //   5: astore_2
    //   6: ldc_w 428
    //   9: invokestatic 406	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   12: astore_3
    //   13: aload_0
    //   14: aload_2
    //   15: invokevirtual 253	java/io/InputStream:read	([B)I
    //   18: istore_1
    //   19: iload_1
    //   20: iconst_m1
    //   21: if_icmpeq +13 -> 34
    //   24: aload_3
    //   25: aload_2
    //   26: iconst_0
    //   27: iload_1
    //   28: invokevirtual 409	java/security/MessageDigest:update	([BII)V
    //   31: goto -18 -> 13
    //   34: new 411	java/math/BigInteger
    //   37: dup
    //   38: iconst_1
    //   39: aload_3
    //   40: invokevirtual 415	java/security/MessageDigest:digest	()[B
    //   43: invokespecial 418	java/math/BigInteger:<init>	(I[B)V
    //   46: bipush 16
    //   48: invokevirtual 420	java/math/BigInteger:toString	(I)Ljava/lang/String;
    //   51: astore_2
    //   52: aload_0
    //   53: invokevirtual 260	java/io/InputStream:close	()V
    //   56: aload_2
    //   57: areturn
    //   58: astore_0
    //   59: aload_0
    //   60: invokevirtual 337	java/lang/Exception:printStackTrace	()V
    //   63: aload_2
    //   64: areturn
    //   65: astore_2
    //   66: goto +21 -> 87
    //   69: astore_2
    //   70: aload_2
    //   71: invokevirtual 337	java/lang/Exception:printStackTrace	()V
    //   74: aload_0
    //   75: invokevirtual 260	java/io/InputStream:close	()V
    //   78: aconst_null
    //   79: areturn
    //   80: astore_0
    //   81: aload_0
    //   82: invokevirtual 337	java/lang/Exception:printStackTrace	()V
    //   85: aconst_null
    //   86: areturn
    //   87: aload_0
    //   88: invokevirtual 260	java/io/InputStream:close	()V
    //   91: goto +8 -> 99
    //   94: astore_0
    //   95: aload_0
    //   96: invokevirtual 337	java/lang/Exception:printStackTrace	()V
    //   99: aload_2
    //   100: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	101	0	paramInputStream	InputStream
    //   18	10	1	i	int
    //   5	59	2	localObject1	Object
    //   65	1	2	localObject2	Object
    //   69	31	2	localException	Exception
    //   12	28	3	localMessageDigest	java.security.MessageDigest
    // Exception table:
    //   from	to	target	type
    //   52	56	58	java/lang/Exception
    //   6	13	65	finally
    //   13	19	65	finally
    //   24	31	65	finally
    //   34	52	65	finally
    //   70	74	65	finally
    //   6	13	69	java/lang/Exception
    //   13	19	69	java/lang/Exception
    //   24	31	69	java/lang/Exception
    //   34	52	69	java/lang/Exception
    //   74	78	80	java/lang/Exception
    //   87	91	94	java/lang/Exception
  }
  
  public static String getFileSha1(String paramString)
  {
    return getFileMD5(new File(paramString));
  }
  
  public static String getFileType(File paramFile)
  {
    try
    {
      paramFile = (String)mFileTypes.get(getFileHeader(new FileInputStream(paramFile)));
      return paramFile;
    }
    catch (FileNotFoundException paramFile)
    {
      paramFile.printStackTrace();
    }
    return "unknown";
  }
  
  public static String getFileType(InputStream paramInputStream)
  {
    return (String)mFileTypes.get(getFileHeader(paramInputStream));
  }
  
  public static String getFileType(String paramString)
  {
    try
    {
      paramString = (String)mFileTypes.get(getFileHeader(new FileInputStream(paramString)));
      return paramString;
    }
    catch (FileNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return "unknown";
  }
  
  public static byte[] readAll(InputStream paramInputStream)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(4096);
    byte[] arrayOfByte = new byte['က'];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (-1 == i) {
        break;
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
    paramInputStream = localByteArrayOutputStream.toByteArray();
    localByteArrayOutputStream.close();
    return paramInputStream;
  }
  
  public static byte[] readAsset(Context paramContext, String paramString)
  {
    paramContext = paramContext.getAssets().open(paramString);
    paramString = readAll(paramContext);
    paramContext.close();
    return paramString;
  }
  
  public static byte[] readZip(String paramString1, String paramString2)
  {
    paramString1 = new ZipFile(paramString1);
    return readAll(paramString1.getInputStream(paramString1.getEntry(paramString2)));
  }
  
  public static void rmDir(File paramFile, String paramString)
  {
    if (paramFile.isDirectory())
    {
      File[] arrayOfFile = paramFile.listFiles();
      int j = arrayOfFile.length;
      int i = 0;
      while (i < j)
      {
        rmDir(arrayOfFile[i], paramString);
        i += 1;
      }
      paramFile.delete();
    }
    if (paramFile.getName().endsWith(paramString)) {
      paramFile.delete();
    }
  }
  
  public static boolean rmDir(File paramFile)
  {
    if (paramFile.isDirectory())
    {
      File[] arrayOfFile = paramFile.listFiles();
      int j = arrayOfFile.length;
      int i = 0;
      while (i < j)
      {
        rmDir(arrayOfFile[i]);
        i += 1;
      }
    }
    return paramFile.delete();
  }
  
  public static void unZip(String paramString)
  {
    unZip(paramString, new File(paramString).getParent(), "");
  }
  
  public static void unZip(String paramString1, String paramString2)
  {
    unZip(paramString1, paramString2, "");
  }
  
  public static void unZip(String paramString1, String paramString2, String paramString3)
  {
    paramString1 = new ZipFile(paramString1);
    Enumeration localEnumeration = paramString1.entries();
    while (localEnumeration.hasMoreElements())
    {
      Object localObject1 = (ZipEntry)localEnumeration.nextElement();
      Object localObject2 = ((ZipEntry)localObject1).getName();
      if (((String)localObject2).startsWith(paramString3)) {
        if (((ZipEntry)localObject1).isDirectory())
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append(paramString2);
          ((StringBuilder)localObject1).append(File.separator);
          ((StringBuilder)localObject1).append((String)localObject2);
          localObject1 = new File(((StringBuilder)localObject1).toString());
          if (!((File)localObject1).exists()) {
            ((File)localObject1).mkdirs();
          }
        }
        else
        {
          Object localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(paramString2);
          ((StringBuilder)localObject3).append(File.separator);
          ((StringBuilder)localObject3).append((String)localObject2);
          localObject3 = new File(((StringBuilder)localObject3).toString()).getParentFile();
          if ((!((File)localObject3).exists()) && (!((File)localObject3).mkdirs()))
          {
            paramString1 = new StringBuilder();
            paramString1.append("create file ");
            paramString1.append(((File)localObject3).getName());
            paramString1.append(" fail");
            throw new RuntimeException(paramString1.toString());
          }
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(paramString2);
          ((StringBuilder)localObject3).append(File.separator);
          ((StringBuilder)localObject3).append((String)localObject2);
          localObject2 = new FileOutputStream(((StringBuilder)localObject3).toString());
          localObject1 = paramString1.getInputStream((ZipEntry)localObject1);
          localObject3 = new byte['က'];
          for (;;)
          {
            int i = ((InputStream)localObject1).read((byte[])localObject3);
            if (i == -1) {
              break;
            }
            ((FileOutputStream)localObject2).write((byte[])localObject3, 0, i);
          }
          ((FileOutputStream)localObject2).close();
          ((InputStream)localObject1).close();
        }
      }
    }
    paramString1.close();
  }
  
  public static void unZip(String paramString, boolean paramBoolean)
  {
    if (!paramBoolean)
    {
      unZip(paramString);
      return;
    }
    Object localObject2 = new File(paramString).getName();
    int i = ((String)localObject2).lastIndexOf(".");
    Object localObject1 = localObject2;
    if (i > 0) {
      localObject1 = ((String)localObject2).substring(0, i);
    }
    i = ((String)localObject1).indexOf("_");
    localObject2 = localObject1;
    if (i > 0) {
      localObject2 = ((String)localObject1).substring(0, i);
    }
    i = ((String)localObject2).indexOf("(");
    localObject1 = localObject2;
    if (i > 0) {
      localObject1 = ((String)localObject2).substring(0, i);
    }
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(new File(paramString).getParent());
    ((StringBuilder)localObject2).append(File.separator);
    ((StringBuilder)localObject2).append((String)localObject1);
    unZip(paramString, ((StringBuilder)localObject2).toString(), "");
  }
  
  public static boolean zip(String paramString)
  {
    return zip(paramString, new File(paramString).getParent());
  }
  
  public static boolean zip(String paramString1, String paramString2)
  {
    File localFile = new File(paramString1);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(localFile.getName());
    localStringBuilder.append(".zip");
    return zip(paramString1, paramString2, localStringBuilder.toString());
  }
  
  /* Error */
  public static boolean zip(String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: new 165	java/io/File
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 329	java/io/File:<init>	(Ljava/lang/String;)V
    //   8: astore 6
    //   10: new 165	java/io/File
    //   13: dup
    //   14: aload_1
    //   15: aload_2
    //   16: invokespecial 550	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   19: astore 7
    //   21: aload 7
    //   23: invokevirtual 355	java/io/File:getParentFile	()Ljava/io/File;
    //   26: invokevirtual 358	java/io/File:exists	()Z
    //   29: istore_3
    //   30: iconst_0
    //   31: istore 4
    //   33: iconst_0
    //   34: istore 5
    //   36: iload_3
    //   37: ifne +16 -> 53
    //   40: aload 7
    //   42: invokevirtual 355	java/io/File:getParentFile	()Ljava/io/File;
    //   45: invokevirtual 361	java/io/File:mkdirs	()Z
    //   48: ifne +5 -> 53
    //   51: iconst_0
    //   52: ireturn
    //   53: aload 7
    //   55: invokevirtual 358	java/io/File:exists	()Z
    //   58: ifeq +12 -> 70
    //   61: aload 7
    //   63: invokevirtual 369	java/io/File:createNewFile	()Z
    //   66: pop
    //   67: goto +3 -> 70
    //   70: aconst_null
    //   71: astore_2
    //   72: aconst_null
    //   73: astore_1
    //   74: aload_1
    //   75: astore_0
    //   76: new 552	java/util/zip/CheckedOutputStream
    //   79: dup
    //   80: new 235	java/io/FileOutputStream
    //   83: dup
    //   84: aload 7
    //   86: invokespecial 370	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   89: new 554	java/util/zip/Adler32
    //   92: dup
    //   93: invokespecial 555	java/util/zip/Adler32:<init>	()V
    //   96: invokespecial 558	java/util/zip/CheckedOutputStream:<init>	(Ljava/io/OutputStream;Ljava/util/zip/Checksum;)V
    //   99: astore 7
    //   101: aload_1
    //   102: astore_0
    //   103: new 200	java/util/zip/ZipOutputStream
    //   106: dup
    //   107: new 560	java/io/BufferedOutputStream
    //   110: dup
    //   111: aload 7
    //   113: invokespecial 563	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   116: invokespecial 564	java/util/zip/ZipOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   119: astore_1
    //   120: aload 6
    //   122: aload_1
    //   123: ldc_w 485
    //   126: invokestatic 228	com/androlua/LuaUtil:a	(Ljava/io/File;Ljava/util/zip/ZipOutputStream;Ljava/lang/String;)V
    //   129: aload 7
    //   131: invokevirtual 568	java/util/zip/CheckedOutputStream:getChecksum	()Ljava/util/zip/Checksum;
    //   134: invokeinterface 574 1 0
    //   139: pop2
    //   140: iconst_1
    //   141: istore_3
    //   142: iconst_1
    //   143: istore 4
    //   145: aload_1
    //   146: ifnull +87 -> 233
    //   149: aload_1
    //   150: invokevirtual 577	java/util/zip/ZipOutputStream:closeEntry	()V
    //   153: goto +8 -> 161
    //   156: astore_0
    //   157: aload_0
    //   158: invokevirtual 218	java/io/IOException:printStackTrace	()V
    //   161: iload 4
    //   163: istore_3
    //   164: aload_1
    //   165: invokevirtual 578	java/util/zip/ZipOutputStream:close	()V
    //   168: iconst_1
    //   169: ireturn
    //   170: astore_0
    //   171: aload_0
    //   172: invokevirtual 218	java/io/IOException:printStackTrace	()V
    //   175: iload_3
    //   176: ireturn
    //   177: astore_2
    //   178: aload_1
    //   179: astore_0
    //   180: aload_2
    //   181: astore_1
    //   182: goto +53 -> 235
    //   185: astore_2
    //   186: goto +12 -> 198
    //   189: astore_1
    //   190: goto +45 -> 235
    //   193: astore_0
    //   194: aload_2
    //   195: astore_1
    //   196: aload_0
    //   197: astore_2
    //   198: aload_1
    //   199: astore_0
    //   200: aload_2
    //   201: invokevirtual 219	java/io/FileNotFoundException:printStackTrace	()V
    //   204: iload 4
    //   206: istore_3
    //   207: aload_1
    //   208: ifnull +25 -> 233
    //   211: aload_1
    //   212: invokevirtual 577	java/util/zip/ZipOutputStream:closeEntry	()V
    //   215: goto +8 -> 223
    //   218: astore_0
    //   219: aload_0
    //   220: invokevirtual 218	java/io/IOException:printStackTrace	()V
    //   223: iload 5
    //   225: istore_3
    //   226: aload_1
    //   227: invokevirtual 578	java/util/zip/ZipOutputStream:close	()V
    //   230: iload 4
    //   232: istore_3
    //   233: iload_3
    //   234: ireturn
    //   235: aload_0
    //   236: ifnull +27 -> 263
    //   239: aload_0
    //   240: invokevirtual 577	java/util/zip/ZipOutputStream:closeEntry	()V
    //   243: goto +8 -> 251
    //   246: astore_2
    //   247: aload_2
    //   248: invokevirtual 218	java/io/IOException:printStackTrace	()V
    //   251: aload_0
    //   252: invokevirtual 578	java/util/zip/ZipOutputStream:close	()V
    //   255: goto +8 -> 263
    //   258: astore_0
    //   259: aload_0
    //   260: invokevirtual 218	java/io/IOException:printStackTrace	()V
    //   263: aload_1
    //   264: athrow
    //   265: astore_0
    //   266: iconst_0
    //   267: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	268	0	paramString1	String
    //   0	268	1	paramString2	String
    //   0	268	2	paramString3	String
    //   29	205	3	bool1	boolean
    //   31	200	4	bool2	boolean
    //   34	190	5	bool3	boolean
    //   8	113	6	localFile	File
    //   19	111	7	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   149	153	156	java/io/IOException
    //   164	168	170	java/io/IOException
    //   226	230	170	java/io/IOException
    //   120	140	177	finally
    //   120	140	185	java/io/FileNotFoundException
    //   76	101	189	finally
    //   103	120	189	finally
    //   200	204	189	finally
    //   76	101	193	java/io/FileNotFoundException
    //   103	120	193	java/io/FileNotFoundException
    //   211	215	218	java/io/IOException
    //   239	243	246	java/io/IOException
    //   251	255	258	java/io/IOException
    //   61	67	265	java/io/IOException
  }
  
  public int checkPixel(int paramInt1, int paramInt2, int[][] paramArrayOfInt)
  {
    int m = paramArrayOfInt[paramInt1][paramInt2];
    if (paramInt2 + 30 < paramArrayOfInt[paramInt1].length)
    {
      int i = 1;
      int k;
      for (int j = 0; i <= 30; j = k)
      {
        k = j;
        if (paramArrayOfInt[paramInt1][(paramInt2 + i)] == 0) {
          k = j + 1;
        }
        i += 1;
      }
      if (j > 15) {
        return 0;
      }
    }
    return m;
  }
  
  public int getDifferenceValue(String paramString1, String paramString2)
  {
    new File(paramString1);
    new File(paramString2);
    int k;
    int m;
    int[][] arrayOfInt;
    int i;
    int j;
    try
    {
      paramString1 = BitmapFactory.decodeFile(paramString1);
      paramString2 = BitmapFactory.decodeFile(paramString2);
      k = paramString1.getWidth();
      m = paramString1.getHeight();
      arrayOfInt = (int[][])Array.newInstance(Integer.TYPE, new int[] { k, m });
      i = 1;
    }
    catch (Exception paramString1)
    {
      label108:
      paramString1.printStackTrace();
      return 0;
    }
    if (j < m) {
      if (paramString1.getPixel(i, j) == paramString2.getPixel(i, j)) {
        arrayOfInt[(i - 1)][(j - 1)] = 0;
      }
    }
    for (;;)
    {
      if (i < arrayOfInt.length) {
        m = 0;
      }
      for (;;)
      {
        if (m >= arrayOfInt[i].length) {
          break label306;
        }
        int n = k;
        int i1 = j;
        if (arrayOfInt[i][m] == 1)
        {
          arrayOfInt[i][m] = checkPixel(i, m, arrayOfInt);
          n = k;
          i1 = j;
          if (arrayOfInt[i][m] == 1)
          {
            if (i > j)
            {
              i1 = i;
              n = k;
              break label289;
              i = (j + k) / 2;
              return i;
              for (;;)
              {
                if (i >= k) {
                  break label255;
                }
                j = 1;
                break;
                arrayOfInt[(i - 1)][(j - 1)] = 1;
                j += 1;
                break;
                i += 1;
              }
              label255:
              j = -1;
              i = 0;
              k = 999;
              break label108;
            }
            n = k;
            i1 = j;
            if (i < k)
            {
              n = i;
              i1 = j;
            }
          }
        }
        label289:
        m += 1;
        k = n;
        j = i1;
      }
      label306:
      i += 1;
    }
  }
  
  public BitmapDrawable toBlack(String paramString, float paramFloat, int paramInt1, int paramInt2)
  {
    Object localObject2 = BitmapFactory.decodeFile(paramString);
    int k = ((Bitmap)localObject2).getWidth();
    int m = ((Bitmap)localObject2).getHeight();
    Bitmap.createBitmap(k, m, Bitmap.Config.RGB_565);
    int n = k * m;
    paramString = new int[n];
    Object localObject1 = new float[n];
    float[] arrayOfFloat = new float[3];
    int i = 0;
    float f = 0.0F;
    int j;
    while (i < m)
    {
      j = 0;
      while (j < k)
      {
        Color.colorToHSV(((Bitmap)localObject2).getPixel(j, i), arrayOfFloat);
        localObject1[(k * i + j)] = arrayOfFloat[2];
        f += arrayOfFloat[2];
        j += 1;
      }
      i += 1;
    }
    f /= n;
    localObject2 = (int[][])Array.newInstance(Integer.TYPE, new int[] { k, m });
    i = 0;
    while (i < m)
    {
      j = 0;
      while (j < k)
      {
        n = k * i + j;
        if (localObject1[n] > f * paramFloat)
        {
          paramString[n] = -1;
          localObject2[j][i] = 1;
        }
        else
        {
          paramString[n] = -16777216;
          localObject2[j][i] = 0;
        }
        j += 1;
      }
      i += 1;
    }
    i = k / 2;
    while (i < k - 10)
    {
      j = k / 3;
      while (j < k)
      {
        if (a(i, j, (int[][])localObject2, paramInt1, paramInt2))
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append(i);
          ((StringBuilder)localObject1).append("");
          Log.i("find_color", ((StringBuilder)localObject1).toString());
          break;
        }
        j += 1;
      }
      i += 1;
    }
    return new BitmapDrawable(Bitmap.createBitmap(paramString, k, m, Bitmap.Config.RGB_565));
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */