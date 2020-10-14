package com.tencent.connect.auth;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import com.tencent.open.a.f;
import com.tencent.open.utils.e;
import org.json.JSONObject;

public class QQToken
{
  public static final int AUTH_QQ = 2;
  public static final int AUTH_QZONE = 3;
  public static final int AUTH_WEB = 1;
  private static SharedPreferences f;
  private String a;
  private String b;
  private String c;
  private int d = 1;
  private long e = -1L;
  
  public QQToken(String paramString)
  {
    this.a = paramString;
  }
  
  @TargetApi(11)
  private static SharedPreferences a()
  {
    try
    {
      if (f == null) {
        f = e.a().getSharedPreferences("token_info_file", 0);
      }
      SharedPreferences localSharedPreferences = f;
      return localSharedPreferences;
    }
    finally {}
  }
  
  /* Error */
  private static JSONObject a(String paramString)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 45	com/tencent/open/utils/e:a	()Landroid/content/Context;
    //   6: ifnonnull +17 -> 23
    //   9: ldc 59
    //   11: ldc 61
    //   13: invokestatic 66	com/tencent/open/a/f:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   16: aconst_null
    //   17: astore_0
    //   18: ldc 2
    //   20: monitorexit
    //   21: aload_0
    //   22: areturn
    //   23: aload_0
    //   24: ifnonnull +8 -> 32
    //   27: aconst_null
    //   28: astore_0
    //   29: goto -11 -> 18
    //   32: aload_0
    //   33: invokestatic 72	com/tencent/open/utils/k:i	(Ljava/lang/String;)[B
    //   36: iconst_2
    //   37: invokestatic 78	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
    //   40: astore_0
    //   41: invokestatic 80	com/tencent/connect/auth/QQToken:a	()Landroid/content/SharedPreferences;
    //   44: aload_0
    //   45: aconst_null
    //   46: invokeinterface 86 3 0
    //   51: astore_0
    //   52: aload_0
    //   53: ifnonnull +15 -> 68
    //   56: ldc 59
    //   58: ldc 88
    //   60: invokestatic 66	com/tencent/open/a/f:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   63: aconst_null
    //   64: astore_0
    //   65: goto -47 -> 18
    //   68: aload_0
    //   69: ldc 90
    //   71: invokestatic 94	com/tencent/open/utils/d:b	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   74: astore_0
    //   75: new 96	org/json/JSONObject
    //   78: dup
    //   79: aload_0
    //   80: invokespecial 98	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   83: astore_0
    //   84: goto -66 -> 18
    //   87: astore_0
    //   88: ldc 59
    //   90: new 100	java/lang/StringBuilder
    //   93: dup
    //   94: invokespecial 101	java/lang/StringBuilder:<init>	()V
    //   97: ldc 103
    //   99: invokevirtual 107	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   102: aload_0
    //   103: invokevirtual 111	java/lang/Exception:toString	()Ljava/lang/String;
    //   106: invokevirtual 107	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: invokevirtual 112	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   112: invokestatic 66	com/tencent/open/a/f:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   115: aconst_null
    //   116: astore_0
    //   117: goto -99 -> 18
    //   120: astore_0
    //   121: ldc 2
    //   123: monitorexit
    //   124: aload_0
    //   125: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	126	0	paramString	String
    // Exception table:
    //   from	to	target	type
    //   75	84	87	java/lang/Exception
    //   3	16	120	finally
    //   32	52	120	finally
    //   56	63	120	finally
    //   68	75	120	finally
    //   75	84	120	finally
    //   88	115	120	finally
  }
  
  /* Error */
  private static void a(String paramString, JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 45	com/tencent/open/utils/e:a	()Landroid/content/Context;
    //   6: ifnonnull +14 -> 20
    //   9: ldc 59
    //   11: ldc 115
    //   13: invokestatic 66	com/tencent/open/a/f:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   16: ldc 2
    //   18: monitorexit
    //   19: return
    //   20: aload_0
    //   21: ifnull +7 -> 28
    //   24: aload_1
    //   25: ifnonnull +6 -> 31
    //   28: goto -12 -> 16
    //   31: aload_1
    //   32: ldc 117
    //   34: invokevirtual 120	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   37: astore_2
    //   38: aload_2
    //   39: invokestatic 126	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   42: ifne +85 -> 127
    //   45: aload_1
    //   46: ldc -128
    //   48: invokestatic 134	java/lang/System:currentTimeMillis	()J
    //   51: aload_2
    //   52: invokestatic 140	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   55: ldc2_w 141
    //   58: lmul
    //   59: ladd
    //   60: invokevirtual 146	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   63: pop
    //   64: aload_0
    //   65: invokestatic 72	com/tencent/open/utils/k:i	(Ljava/lang/String;)[B
    //   68: iconst_2
    //   69: invokestatic 78	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
    //   72: astore_0
    //   73: aload_1
    //   74: invokevirtual 147	org/json/JSONObject:toString	()Ljava/lang/String;
    //   77: ldc 90
    //   79: invokestatic 149	com/tencent/open/utils/d:a	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   82: astore_1
    //   83: aload_0
    //   84: ifnull +40 -> 124
    //   87: aload_1
    //   88: ifnonnull +6 -> 94
    //   91: goto +33 -> 124
    //   94: invokestatic 80	com/tencent/connect/auth/QQToken:a	()Landroid/content/SharedPreferences;
    //   97: invokeinterface 153 1 0
    //   102: aload_0
    //   103: aload_1
    //   104: invokeinterface 159 3 0
    //   109: invokeinterface 163 1 0
    //   114: pop
    //   115: goto -99 -> 16
    //   118: astore_0
    //   119: ldc 2
    //   121: monitorexit
    //   122: aload_0
    //   123: athrow
    //   124: goto -108 -> 16
    //   127: goto -111 -> 16
    //   130: astore_0
    //   131: goto -115 -> 16
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	134	0	paramString	String
    //   0	134	1	paramJSONObject	JSONObject
    //   37	15	2	str	String
    // Exception table:
    //   from	to	target	type
    //   3	16	118	finally
    //   31	64	118	finally
    //   64	83	118	finally
    //   94	115	118	finally
    //   31	64	130	java/lang/Exception
  }
  
  public String getAccessToken()
  {
    return this.b;
  }
  
  public String getAppId()
  {
    return this.a;
  }
  
  public int getAuthSource()
  {
    return this.d;
  }
  
  public long getExpireTimeInSecond()
  {
    return this.e;
  }
  
  public String getOpenId()
  {
    return this.c;
  }
  
  public boolean isSessionValid()
  {
    if ((this.b != null) && (System.currentTimeMillis() < this.e)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public JSONObject loadSession(String paramString)
  {
    try
    {
      paramString = a(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      f.c("QQToken", "login loadSession" + paramString.toString());
    }
    return null;
  }
  
  public void saveSession(JSONObject paramJSONObject)
  {
    try
    {
      a(this.a, paramJSONObject);
      return;
    }
    catch (Exception paramJSONObject)
    {
      f.c("QQToken", "login saveSession" + paramJSONObject.toString());
    }
  }
  
  public void setAccessToken(String paramString1, String paramString2)
    throws NumberFormatException
  {
    this.b = paramString1;
    this.e = 0L;
    if (paramString2 != null) {
      this.e = (System.currentTimeMillis() + Long.parseLong(paramString2) * 1000L);
    }
  }
  
  public void setAppId(String paramString)
  {
    this.a = paramString;
  }
  
  public void setAuthSource(int paramInt)
  {
    this.d = paramInt;
  }
  
  public void setOpenId(String paramString)
  {
    this.c = paramString;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\connect\auth\QQToken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */