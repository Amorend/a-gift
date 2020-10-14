package com.baidu.mobstat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.crypto.Cipher;
import org.json.JSONArray;
import org.json.JSONObject;

public final class g
{
  private static final String a;
  private static j e;
  private final Context b;
  private int c = 0;
  private PublicKey d;
  
  static
  {
    String str1 = new String(b.a(new byte[] { 77, 122, 65, 121, 77, 84, 73, 120, 77, 68, 73, 61 }));
    String str2 = new String(b.a(new byte[] { 90, 71, 108, 106, 100, 87, 82, 112, 89, 87, 73, 61 }));
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(str1);
    localStringBuilder.append(str2);
    a = localStringBuilder.toString();
  }
  
  private g(Context paramContext)
  {
    this.b = paramContext.getApplicationContext();
    a();
  }
  
  public static String a(Context paramContext)
  {
    return c(paramContext).b();
  }
  
  /* Error */
  private static String a(File paramFile)
  {
    // Byte code:
    //   0: new 87	java/io/FileReader
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 90	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   8: astore_2
    //   9: aload_2
    //   10: astore_0
    //   11: sipush 8192
    //   14: newarray <illegal type>
    //   16: astore_3
    //   17: aload_2
    //   18: astore_0
    //   19: new 92	java/io/CharArrayWriter
    //   22: dup
    //   23: invokespecial 93	java/io/CharArrayWriter:<init>	()V
    //   26: astore 4
    //   28: aload_2
    //   29: astore_0
    //   30: aload_2
    //   31: aload_3
    //   32: invokevirtual 97	java/io/FileReader:read	([C)I
    //   35: istore_1
    //   36: iload_1
    //   37: ifle +16 -> 53
    //   40: aload_2
    //   41: astore_0
    //   42: aload 4
    //   44: aload_3
    //   45: iconst_0
    //   46: iload_1
    //   47: invokevirtual 101	java/io/CharArrayWriter:write	([CII)V
    //   50: goto -22 -> 28
    //   53: aload_2
    //   54: astore_0
    //   55: aload 4
    //   57: invokevirtual 102	java/io/CharArrayWriter:toString	()Ljava/lang/String;
    //   60: astore_3
    //   61: aload_2
    //   62: ifnull +14 -> 76
    //   65: aload_2
    //   66: invokevirtual 105	java/io/FileReader:close	()V
    //   69: aload_3
    //   70: areturn
    //   71: astore_0
    //   72: aload_0
    //   73: invokestatic 108	com/baidu/mobstat/g:b	(Ljava/lang/Throwable;)V
    //   76: aload_3
    //   77: areturn
    //   78: astore_3
    //   79: goto +12 -> 91
    //   82: astore_2
    //   83: aconst_null
    //   84: astore_0
    //   85: goto +30 -> 115
    //   88: astore_3
    //   89: aconst_null
    //   90: astore_2
    //   91: aload_2
    //   92: astore_0
    //   93: aload_3
    //   94: invokestatic 108	com/baidu/mobstat/g:b	(Ljava/lang/Throwable;)V
    //   97: aload_2
    //   98: ifnull +14 -> 112
    //   101: aload_2
    //   102: invokevirtual 105	java/io/FileReader:close	()V
    //   105: aconst_null
    //   106: areturn
    //   107: astore_0
    //   108: aload_0
    //   109: invokestatic 108	com/baidu/mobstat/g:b	(Ljava/lang/Throwable;)V
    //   112: aconst_null
    //   113: areturn
    //   114: astore_2
    //   115: aload_0
    //   116: ifnull +15 -> 131
    //   119: aload_0
    //   120: invokevirtual 105	java/io/FileReader:close	()V
    //   123: goto +8 -> 131
    //   126: astore_0
    //   127: aload_0
    //   128: invokestatic 108	com/baidu/mobstat/g:b	(Ljava/lang/Throwable;)V
    //   131: aload_2
    //   132: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	133	0	paramFile	File
    //   35	12	1	i	int
    //   8	58	2	localFileReader	FileReader
    //   82	1	2	localObject1	Object
    //   90	12	2	localObject2	Object
    //   114	18	2	localObject3	Object
    //   16	61	3	localObject4	Object
    //   78	1	3	localException1	Exception
    //   88	6	3	localException2	Exception
    //   26	30	4	localCharArrayWriter	java.io.CharArrayWriter
    // Exception table:
    //   from	to	target	type
    //   65	69	71	java/lang/Exception
    //   11	17	78	java/lang/Exception
    //   19	28	78	java/lang/Exception
    //   30	36	78	java/lang/Exception
    //   42	50	78	java/lang/Exception
    //   55	61	78	java/lang/Exception
    //   0	9	82	finally
    //   0	9	88	java/lang/Exception
    //   101	105	107	java/lang/Exception
    //   11	17	114	finally
    //   19	28	114	finally
    //   30	36	114	finally
    //   42	50	114	finally
    //   55	61	114	finally
    //   93	97	114	finally
    //   119	123	126	java/lang/Exception
  }
  
  private static String a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      throw new IllegalArgumentException("Argument b ( byte array ) is null! ");
    }
    String str1 = "";
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      String str2 = Integer.toHexString(paramArrayOfByte[i] & 0xFF);
      StringBuilder localStringBuilder;
      if (str2.length() == 1)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(str1);
        str1 = "0";
      }
      for (;;)
      {
        localStringBuilder.append(str1);
        localStringBuilder.append(str2);
        str1 = localStringBuilder.toString();
        break;
        localStringBuilder = new StringBuilder();
      }
      i += 1;
    }
    return str1.toLowerCase();
  }
  
  private List<i> a(Intent paramIntent, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = this.b.getPackageManager();
    paramIntent = localPackageManager.queryBroadcastReceivers(paramIntent, 0);
    Object localObject3;
    if (paramIntent != null)
    {
      paramIntent = paramIntent.iterator();
      do
      {
        if (!paramIntent.hasNext()) {
          break;
        }
        localObject3 = (ResolveInfo)paramIntent.next();
      } while ((((ResolveInfo)localObject3).activityInfo == null) || (((ResolveInfo)localObject3).activityInfo.applicationInfo == null));
    }
    for (;;)
    {
      try
      {
        Object localObject2 = localPackageManager.getReceiverInfo(new ComponentName(((ResolveInfo)localObject3).activityInfo.packageName, ((ResolveInfo)localObject3).activityInfo.name), 128).metaData;
        if (localObject2 == null) {
          break;
        }
        Object localObject1 = ((Bundle)localObject2).getString("galaxy_data");
        if (TextUtils.isEmpty((CharSequence)localObject1)) {
          break;
        }
        byte[] arrayOfByte = b.a(((String)localObject1).getBytes("utf-8"));
        Object localObject4 = new JSONObject(new String(arrayOfByte));
        localObject1 = new i(null);
        ((i)localObject1).b = ((JSONObject)localObject4).getInt("priority");
        ((i)localObject1).a = ((ResolveInfo)localObject3).activityInfo.applicationInfo;
        if (this.b.getPackageName().equals(((ResolveInfo)localObject3).activityInfo.applicationInfo.packageName)) {
          ((i)localObject1).d = true;
        }
        if (paramBoolean)
        {
          localObject2 = ((Bundle)localObject2).getString("galaxy_sf");
          if (!TextUtils.isEmpty((CharSequence)localObject2))
          {
            localObject3 = localPackageManager.getPackageInfo(((ResolveInfo)localObject3).activityInfo.applicationInfo.packageName, 64);
            localObject4 = ((JSONObject)localObject4).getJSONArray("sigs");
            String[] arrayOfString = new String[((JSONArray)localObject4).length()];
            i = 0;
            if (i < arrayOfString.length)
            {
              arrayOfString[i] = ((JSONArray)localObject4).getString(i);
              i += 1;
              continue;
            }
            if (a(arrayOfString, a(((PackageInfo)localObject3).signatures)))
            {
              localObject2 = a(b.a(((String)localObject2).getBytes()), this.d);
              arrayOfByte = d.a(arrayOfByte);
              if ((localObject2 == null) || (!Arrays.equals((byte[])localObject2, arrayOfByte))) {
                break label427;
              }
              i = 1;
              if (i != 0) {
                ((i)localObject1).c = true;
              }
            }
          }
        }
        localArrayList.add(localObject1);
      }
      catch (Exception localException) {}
      Collections.sort(localArrayList, new h(this));
      return localArrayList;
      break;
      label427:
      int i = 0;
    }
  }
  
  /* Error */
  private void a()
  {
    // Byte code:
    //   0: new 310	java/io/ByteArrayInputStream
    //   3: dup
    //   4: invokestatic 314	com/baidu/mobstat/f:a	()[B
    //   7: invokespecial 315	java/io/ByteArrayInputStream:<init>	([B)V
    //   10: astore_1
    //   11: aload_0
    //   12: ldc_w 317
    //   15: invokestatic 323	java/security/cert/CertificateFactory:getInstance	(Ljava/lang/String;)Ljava/security/cert/CertificateFactory;
    //   18: aload_1
    //   19: invokevirtual 327	java/security/cert/CertificateFactory:generateCertificate	(Ljava/io/InputStream;)Ljava/security/cert/Certificate;
    //   22: invokevirtual 333	java/security/cert/Certificate:getPublicKey	()Ljava/security/PublicKey;
    //   25: putfield 279	com/baidu/mobstat/g:d	Ljava/security/PublicKey;
    //   28: aload_1
    //   29: ifnull +49 -> 78
    //   32: aload_1
    //   33: invokevirtual 334	java/io/ByteArrayInputStream:close	()V
    //   36: return
    //   37: astore_2
    //   38: goto +6 -> 44
    //   41: astore_2
    //   42: aconst_null
    //   43: astore_1
    //   44: aload_1
    //   45: ifnull +15 -> 60
    //   48: aload_1
    //   49: invokevirtual 334	java/io/ByteArrayInputStream:close	()V
    //   52: goto +8 -> 60
    //   55: astore_1
    //   56: aload_1
    //   57: invokestatic 108	com/baidu/mobstat/g:b	(Ljava/lang/Throwable;)V
    //   60: aload_2
    //   61: athrow
    //   62: aconst_null
    //   63: astore_1
    //   64: aload_1
    //   65: ifnull +13 -> 78
    //   68: aload_1
    //   69: invokevirtual 334	java/io/ByteArrayInputStream:close	()V
    //   72: return
    //   73: astore_1
    //   74: aload_1
    //   75: invokestatic 108	com/baidu/mobstat/g:b	(Ljava/lang/Throwable;)V
    //   78: return
    //   79: astore_1
    //   80: goto -18 -> 62
    //   83: astore_2
    //   84: goto -20 -> 64
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	87	0	this	g
    //   10	39	1	localByteArrayInputStream	java.io.ByteArrayInputStream
    //   55	2	1	localException1	Exception
    //   63	6	1	localObject1	Object
    //   73	2	1	localException2	Exception
    //   79	1	1	localException3	Exception
    //   37	1	2	localObject2	Object
    //   41	20	2	localObject3	Object
    //   83	1	2	localException4	Exception
    // Exception table:
    //   from	to	target	type
    //   11	28	37	finally
    //   0	11	41	finally
    //   48	52	55	java/lang/Exception
    //   32	36	73	java/lang/Exception
    //   68	72	73	java/lang/Exception
    //   0	11	79	java/lang/Exception
    //   11	28	83	java/lang/Exception
  }
  
  /* Error */
  @android.annotation.SuppressLint({"NewApi"})
  private boolean a(String paramString)
  {
    // Byte code:
    //   0: getstatic 343	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 24
    //   5: if_icmplt +8 -> 13
    //   8: iconst_0
    //   9: istore_2
    //   10: goto +5 -> 15
    //   13: iconst_1
    //   14: istore_2
    //   15: aconst_null
    //   16: astore 5
    //   18: aconst_null
    //   19: astore_3
    //   20: aload_0
    //   21: getfield 72	com/baidu/mobstat/g:b	Landroid/content/Context;
    //   24: ldc_w 345
    //   27: iload_2
    //   28: invokevirtual 349	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   31: astore 4
    //   33: aload 4
    //   35: aload_1
    //   36: invokevirtual 277	java/lang/String:getBytes	()[B
    //   39: invokevirtual 353	java/io/FileOutputStream:write	([B)V
    //   42: aload 4
    //   44: invokevirtual 356	java/io/FileOutputStream:flush	()V
    //   47: aload 4
    //   49: ifnull +16 -> 65
    //   52: aload 4
    //   54: invokevirtual 357	java/io/FileOutputStream:close	()V
    //   57: goto +8 -> 65
    //   60: astore_1
    //   61: aload_1
    //   62: invokestatic 108	com/baidu/mobstat/g:b	(Ljava/lang/Throwable;)V
    //   65: iload_2
    //   66: ifne +30 -> 96
    //   69: new 359	java/io/File
    //   72: dup
    //   73: aload_0
    //   74: getfield 72	com/baidu/mobstat/g:b	Landroid/content/Context;
    //   77: invokevirtual 363	android/content/Context:getFilesDir	()Ljava/io/File;
    //   80: ldc_w 345
    //   83: invokespecial 366	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   86: invokevirtual 369	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   89: sipush 436
    //   92: invokestatic 374	com/baidu/mobstat/k:a	(Ljava/lang/String;I)Z
    //   95: ireturn
    //   96: iconst_1
    //   97: ireturn
    //   98: astore_1
    //   99: aload 4
    //   101: astore_3
    //   102: goto +46 -> 148
    //   105: astore_3
    //   106: aload 4
    //   108: astore_1
    //   109: aload_3
    //   110: astore 4
    //   112: goto +12 -> 124
    //   115: astore_1
    //   116: goto +32 -> 148
    //   119: astore 4
    //   121: aload 5
    //   123: astore_1
    //   124: aload_1
    //   125: astore_3
    //   126: aload 4
    //   128: invokestatic 108	com/baidu/mobstat/g:b	(Ljava/lang/Throwable;)V
    //   131: aload_1
    //   132: ifnull +14 -> 146
    //   135: aload_1
    //   136: invokevirtual 357	java/io/FileOutputStream:close	()V
    //   139: iconst_0
    //   140: ireturn
    //   141: astore_1
    //   142: aload_1
    //   143: invokestatic 108	com/baidu/mobstat/g:b	(Ljava/lang/Throwable;)V
    //   146: iconst_0
    //   147: ireturn
    //   148: aload_3
    //   149: ifnull +15 -> 164
    //   152: aload_3
    //   153: invokevirtual 357	java/io/FileOutputStream:close	()V
    //   156: goto +8 -> 164
    //   159: astore_3
    //   160: aload_3
    //   161: invokestatic 108	com/baidu/mobstat/g:b	(Ljava/lang/Throwable;)V
    //   164: aload_1
    //   165: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	166	0	this	g
    //   0	166	1	paramString	String
    //   9	57	2	i	int
    //   19	83	3	localObject1	Object
    //   105	5	3	localException1	Exception
    //   125	28	3	str	String
    //   159	2	3	localException2	Exception
    //   31	80	4	localObject2	Object
    //   119	8	4	localException3	Exception
    //   16	106	5	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   52	57	60	java/lang/Exception
    //   33	47	98	finally
    //   33	47	105	java/lang/Exception
    //   20	33	115	finally
    //   126	131	115	finally
    //   20	33	119	java/lang/Exception
    //   135	139	141	java/lang/Exception
    //   152	156	159	java/lang/Exception
  }
  
  private boolean a(String paramString1, String paramString2)
  {
    try
    {
      boolean bool = Settings.System.putString(this.b.getContentResolver(), paramString1, paramString2);
      return bool;
    }
    catch (Exception paramString1)
    {
      b(paramString1);
    }
    return false;
  }
  
  private boolean a(String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    int j = 0;
    if ((paramArrayOfString1 != null) && (paramArrayOfString2 != null))
    {
      if (paramArrayOfString1.length != paramArrayOfString2.length) {
        return false;
      }
      HashSet localHashSet = new HashSet();
      int k = paramArrayOfString1.length;
      int i = 0;
      while (i < k)
      {
        localHashSet.add(paramArrayOfString1[i]);
        i += 1;
      }
      paramArrayOfString1 = new HashSet();
      k = paramArrayOfString2.length;
      i = j;
      while (i < k)
      {
        paramArrayOfString1.add(paramArrayOfString2[i]);
        i += 1;
      }
      return localHashSet.equals(paramArrayOfString1);
    }
    return false;
  }
  
  private static byte[] a(byte[] paramArrayOfByte, PublicKey paramPublicKey)
  {
    Cipher localCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    localCipher.init(2, paramPublicKey);
    return localCipher.doFinal(paramArrayOfByte);
  }
  
  private String[] a(Signature[] paramArrayOfSignature)
  {
    String[] arrayOfString = new String[paramArrayOfSignature.length];
    int i = 0;
    while (i < arrayOfString.length)
    {
      arrayOfString[i] = a(d.a(paramArrayOfSignature[i].toByteArray()));
      i += 1;
    }
    return arrayOfString;
  }
  
  private j b()
  {
    Object localObject1 = a(new Intent("com.baidu.intent.action.GALAXY").setPackage(this.b.getPackageName()), true);
    int j = 0;
    int i;
    if ((localObject1 != null) && (((List)localObject1).size() != 0))
    {
      localObject1 = (i)((List)localObject1).get(0);
      bool2 = ((i)localObject1).c;
      bool1 = bool2;
      if (!((i)localObject1).c)
      {
        i = 0;
        for (;;)
        {
          bool1 = bool2;
          if (i >= 3) {
            break;
          }
          Log.w("DeviceId", "galaxy config err, In the release version of the signature should be matched");
          i += 1;
        }
      }
    }
    else
    {
      i = 0;
      while (i < 3)
      {
        Log.w("DeviceId", "galaxy lib host missing meta-data,make sure you know the right way to integrate galaxy");
        i += 1;
      }
      bool1 = false;
    }
    localObject1 = new File(this.b.getFilesDir(), "libcuid.so");
    boolean bool2 = ((File)localObject1).exists();
    Object localObject5 = null;
    Object localObject4 = null;
    if (bool2) {
      localObject1 = j.a(f(a((File)localObject1)));
    } else {
      localObject1 = null;
    }
    Object localObject2 = localObject1;
    Object localObject6;
    if (localObject1 == null)
    {
      this.c |= 0x10;
      localObject6 = a(new Intent("com.baidu.intent.action.GALAXY"), bool1);
      localObject2 = localObject1;
      if (localObject6 != null)
      {
        localObject3 = "files";
        localObject2 = this.b.getFilesDir();
        if (!"files".equals(((File)localObject2).getName()))
        {
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append("fetal error:: app files dir name is unexpectedly :: ");
          ((StringBuilder)localObject3).append(((File)localObject2).getAbsolutePath());
          Log.e("DeviceId", ((StringBuilder)localObject3).toString());
          localObject3 = ((File)localObject2).getName();
        }
        localObject6 = ((List)localObject6).iterator();
        do
        {
          do
          {
            do
            {
              localObject2 = localObject1;
              if (!((Iterator)localObject6).hasNext()) {
                break;
              }
              localObject2 = (i)((Iterator)localObject6).next();
            } while (((i)localObject2).d);
            localObject2 = new File(new File(((i)localObject2).a.dataDir, (String)localObject3), "libcuid.so");
          } while (!((File)localObject2).exists());
          localObject2 = j.a(f(a((File)localObject2)));
          localObject1 = localObject2;
        } while (localObject2 == null);
      }
    }
    localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = j.a(f(b("com.baidu.deviceid.v2")));
    }
    boolean bool1 = c("android.permission.READ_EXTERNAL_STORAGE");
    localObject2 = localObject1;
    if (localObject1 == null)
    {
      localObject2 = localObject1;
      if (bool1)
      {
        this.c |= 0x2;
        localObject2 = e();
      }
    }
    localObject1 = localObject2;
    if (localObject2 == null)
    {
      this.c |= 0x8;
      localObject1 = d();
    }
    if ((localObject1 == null) && (bool1))
    {
      this.c |= 0x1;
      localObject2 = h("");
      localObject1 = d((String)localObject2);
      i = 1;
    }
    else
    {
      localObject2 = null;
      i = j;
    }
    Object localObject3 = localObject1;
    if (localObject1 == null)
    {
      this.c |= 0x4;
      if (i == 0) {
        localObject2 = h("");
      }
      localObject3 = new j(null);
      localObject1 = b(this.b);
      if (Build.VERSION.SDK_INT < 23)
      {
        localObject6 = UUID.randomUUID().toString();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append((String)localObject2);
        localStringBuilder.append((String)localObject1);
        localStringBuilder.append((String)localObject6);
        localObject1 = localStringBuilder.toString();
      }
      else
      {
        localObject6 = new StringBuilder();
        ((StringBuilder)localObject6).append("com.baidu");
        ((StringBuilder)localObject6).append((String)localObject1);
        localObject1 = ((StringBuilder)localObject6).toString();
      }
      ((j)localObject3).a = c.a(((String)localObject1).getBytes(), true);
      ((j)localObject3).b = ((String)localObject2);
    }
    localObject2 = new File(this.b.getFilesDir(), "libcuid.so");
    if ((this.c & 0x10) == 0)
    {
      localObject1 = localObject5;
      if (((File)localObject2).exists()) {}
    }
    else
    {
      localObject1 = localObject4;
      if (TextUtils.isEmpty(null)) {
        localObject1 = e(((j)localObject3).a());
      }
      a((String)localObject1);
    }
    bool1 = c();
    localObject2 = localObject1;
    if (bool1) {
      if ((this.c & 0x2) == 0)
      {
        localObject2 = localObject1;
        if (!TextUtils.isEmpty(b("com.baidu.deviceid.v2"))) {}
      }
      else
      {
        localObject2 = localObject1;
        if (TextUtils.isEmpty((CharSequence)localObject1)) {
          localObject2 = e(((j)localObject3).a());
        }
        a("com.baidu.deviceid.v2", (String)localObject2);
      }
    }
    if (c("android.permission.WRITE_EXTERNAL_STORAGE"))
    {
      localObject1 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid2");
      if (((this.c & 0x8) != 0) || (!((File)localObject1).exists()))
      {
        localObject1 = localObject2;
        if (TextUtils.isEmpty((CharSequence)localObject2)) {
          localObject1 = e(((j)localObject3).a());
        }
        g((String)localObject1);
      }
    }
    if ((bool1) && (((0x1 & this.c) != 0) || (TextUtils.isEmpty(b("com.baidu.deviceid")))))
    {
      a("com.baidu.deviceid", ((j)localObject3).a);
      a("bd_setting_i", ((j)localObject3).b);
    }
    if ((bool1) && (!TextUtils.isEmpty(((j)localObject3).b)))
    {
      localObject1 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid");
      if (((this.c & 0x2) != 0) || (!((File)localObject1).exists())) {
        b(((j)localObject3).b, ((j)localObject3).a);
      }
    }
    return (j)localObject3;
  }
  
  public static String b(Context paramContext)
  {
    String str = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    paramContext = str;
    if (TextUtils.isEmpty(str)) {
      paramContext = "";
    }
    return paramContext;
  }
  
  private String b(String paramString)
  {
    try
    {
      paramString = Settings.System.getString(this.b.getContentResolver(), paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      b(paramString);
    }
    return null;
  }
  
  private static void b(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1)) {
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString1);
    localStringBuilder.append("=");
    localStringBuilder.append(paramString2);
    paramString1 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig");
    paramString2 = new File(paramString1, ".cuid");
    try
    {
      if ((paramString1.exists()) && (!paramString1.isDirectory()))
      {
        Random localRandom = new Random();
        File localFile = paramString1.getParentFile();
        String str = paramString1.getName();
        Object localObject;
        do
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append(str);
          ((StringBuilder)localObject).append(localRandom.nextInt());
          ((StringBuilder)localObject).append(".tmp");
          localObject = new File(localFile, ((StringBuilder)localObject).toString());
        } while (((File)localObject).exists());
        paramString1.renameTo((File)localObject);
        ((File)localObject).delete();
      }
      paramString1.mkdirs();
      paramString1 = new FileWriter(paramString2, false);
      paramString1.write(b.a(a.a(a, a, localStringBuilder.toString().getBytes()), "utf-8"));
      paramString1.flush();
      paramString1.close();
      return;
    }
    catch (IOException|Exception paramString1) {}
  }
  
  private static void b(Throwable paramThrowable) {}
  
  private static j c(Context paramContext)
  {
    if (e == null) {
      try
      {
        if (e == null)
        {
          SystemClock.uptimeMillis();
          e = new g(paramContext).b();
          SystemClock.uptimeMillis();
        }
      }
      finally {}
    }
    return e;
  }
  
  private boolean c()
  {
    return c("android.permission.WRITE_SETTINGS");
  }
  
  private boolean c(String paramString)
  {
    return this.b.checkPermission(paramString, Process.myPid(), Process.myUid()) == 0;
  }
  
  private j d()
  {
    Object localObject3 = b("com.baidu.deviceid");
    Object localObject2 = b("bd_setting_i");
    Object localObject1 = localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject2))
    {
      localObject2 = h("");
      localObject1 = localObject2;
      if (!TextUtils.isEmpty((CharSequence)localObject2))
      {
        a("bd_setting_i", (String)localObject2);
        localObject1 = localObject2;
      }
    }
    localObject2 = localObject3;
    if (TextUtils.isEmpty((CharSequence)localObject3))
    {
      localObject2 = b(this.b);
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("com.baidu");
      ((StringBuilder)localObject3).append((String)localObject1);
      ((StringBuilder)localObject3).append((String)localObject2);
      localObject2 = b(c.a(((StringBuilder)localObject3).toString().getBytes(), true));
    }
    if (!TextUtils.isEmpty((CharSequence)localObject2))
    {
      localObject3 = new j(null);
      ((j)localObject3).a = ((String)localObject2);
      ((j)localObject3).b = ((String)localObject1);
      return (j)localObject3;
    }
    return null;
  }
  
  private j d(String paramString)
  {
    int i;
    if (Build.VERSION.SDK_INT < 23) {
      i = 1;
    } else {
      i = 0;
    }
    if ((i != 0) && (TextUtils.isEmpty(paramString))) {
      return null;
    }
    String str = "";
    Object localObject1 = new File(Environment.getExternalStorageDirectory(), "baidu/.cuid");
    int j;
    if (((File)localObject1).exists())
    {
      j = 0;
    }
    else
    {
      localObject1 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid");
      j = 1;
    }
    Object localObject4 = str;
    Object localObject3 = paramString;
    Object localObject2;
    Object localObject5;
    try
    {
      localObject1 = new BufferedReader(new FileReader((File)localObject1));
      localObject4 = str;
      localObject3 = paramString;
      localObject2 = new StringBuilder();
      for (;;)
      {
        localObject4 = str;
        localObject3 = paramString;
        localObject5 = ((BufferedReader)localObject1).readLine();
        if (localObject5 == null) {
          break;
        }
        localObject4 = str;
        localObject3 = paramString;
        ((StringBuilder)localObject2).append((String)localObject5);
        localObject4 = str;
        localObject3 = paramString;
        ((StringBuilder)localObject2).append("\r\n");
      }
      localObject4 = str;
      localObject3 = paramString;
      ((BufferedReader)localObject1).close();
      localObject4 = str;
      localObject3 = paramString;
      localObject5 = new String(a.b(a, a, b.a(((StringBuilder)localObject2).toString().getBytes()))).split("=");
      localObject1 = str;
      localObject2 = paramString;
      if (localObject5 != null)
      {
        localObject1 = str;
        localObject2 = paramString;
        localObject4 = str;
        localObject3 = paramString;
        if (localObject5.length == 2)
        {
          if (i != 0)
          {
            localObject4 = str;
            localObject3 = paramString;
            if (paramString.equals(localObject5[0]))
            {
              localObject2 = localObject5[1];
              break label398;
            }
          }
          localObject1 = str;
          localObject2 = paramString;
          if (i == 0)
          {
            localObject4 = str;
            localObject3 = paramString;
            localObject1 = paramString;
            if (!TextUtils.isEmpty(paramString)) {
              break label408;
            }
            localObject1 = localObject5[1];
            break label408;
          }
        }
      }
      localObject4 = localObject1;
      localObject3 = localObject2;
      if (j == 0)
      {
        localObject4 = localObject1;
        localObject3 = localObject2;
        b((String)localObject2, (String)localObject1);
        localObject3 = localObject2;
        localObject4 = localObject1;
      }
    }
    catch (FileNotFoundException|IOException|Exception paramString)
    {
      for (;;) {}
    }
    if (!TextUtils.isEmpty((CharSequence)localObject4))
    {
      paramString = new j(null);
      paramString.a = ((String)localObject4);
      paramString.b = ((String)localObject3);
      return paramString;
    }
    return null;
    for (;;)
    {
      label398:
      localObject1 = localObject2;
      localObject2 = paramString;
      break;
      label408:
      localObject2 = localObject5[1];
      paramString = (String)localObject1;
    }
  }
  
  private j e()
  {
    Object localObject = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid2");
    if (((File)localObject).exists())
    {
      localObject = a((File)localObject);
      if (!TextUtils.isEmpty((CharSequence)localObject)) {
        try
        {
          localObject = j.a(new String(a.b(a, a, b.a(((String)localObject).getBytes()))));
          return (j)localObject;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }
    return null;
  }
  
  private static String e(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    try
    {
      paramString = b.a(a.a(a, a, paramString.getBytes()), "utf-8");
      return paramString;
    }
    catch (UnsupportedEncodingException|Exception paramString)
    {
      b(paramString);
    }
    return "";
  }
  
  private static String f(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    try
    {
      paramString = new String(a.b(a, a, b.a(paramString.getBytes())));
      return paramString;
    }
    catch (Exception paramString)
    {
      b(paramString);
    }
    return "";
  }
  
  private static void g(String paramString)
  {
    Object localObject1 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig");
    File localFile1 = new File((File)localObject1, ".cuid2");
    try
    {
      if ((((File)localObject1).exists()) && (!((File)localObject1).isDirectory()))
      {
        Random localRandom = new Random();
        File localFile2 = ((File)localObject1).getParentFile();
        String str = ((File)localObject1).getName();
        Object localObject2;
        do
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append(str);
          ((StringBuilder)localObject2).append(localRandom.nextInt());
          ((StringBuilder)localObject2).append(".tmp");
          localObject2 = new File(localFile2, ((StringBuilder)localObject2).toString());
        } while (((File)localObject2).exists());
        ((File)localObject1).renameTo((File)localObject2);
        ((File)localObject2).delete();
      }
      ((File)localObject1).mkdirs();
      localObject1 = new FileWriter(localFile1, false);
      ((FileWriter)localObject1).write(paramString);
      ((FileWriter)localObject1).flush();
      ((FileWriter)localObject1).close();
      return;
    }
    catch (IOException|Exception paramString) {}
  }
  
  private String h(String paramString)
  {
    Object localObject3 = null;
    try
    {
      TelephonyManager localTelephonyManager = (TelephonyManager)this.b.getSystemService("phone");
      Object localObject1 = localObject3;
      if (localTelephonyManager != null) {
        localObject1 = localTelephonyManager.getDeviceId();
      }
    }
    catch (Exception localException)
    {
      Log.e("DeviceId", "Read IMEI failed", localException);
      localObject2 = localObject3;
    }
    Object localObject2 = i((String)localObject2);
    if (TextUtils.isEmpty((CharSequence)localObject2)) {
      return paramString;
    }
    return (String)localObject2;
  }
  
  private static String i(String paramString)
  {
    String str = paramString;
    if (paramString != null)
    {
      str = paramString;
      if (paramString.contains(":")) {
        str = "";
      }
    }
    return str;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */