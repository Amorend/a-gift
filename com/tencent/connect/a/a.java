package com.tencent.connect.a;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.connect.auth.QQToken;
import com.tencent.open.b.d;
import com.tencent.open.utils.f;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class a
{
  private static Class<?> a = null;
  private static Class<?> b = null;
  private static Method c = null;
  private static Method d = null;
  private static Method e = null;
  private static Method f = null;
  private static boolean g = false;
  
  public static void a(Context paramContext, QQToken paramQQToken, String paramString, String... paramVarArgs)
  {
    if (!g) {
      return;
    }
    b(paramContext, paramQQToken);
    try
    {
      d.invoke(b, new Object[] { paramContext, paramString, paramVarArgs });
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static boolean a(Context paramContext, QQToken paramQQToken)
  {
    return f.a(paramContext, paramQQToken.getAppId()).b("Common_ta_enable");
  }
  
  public static void b(Context paramContext, QQToken paramQQToken)
  {
    try
    {
      if (a(paramContext, paramQQToken))
      {
        f.invoke(a, new Object[] { Boolean.valueOf(true) });
        return;
      }
      f.invoke(a, new Object[] { Boolean.valueOf(false) });
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void c(Context paramContext, QQToken paramQQToken)
  {
    String str = paramQQToken.getAppId();
    str = "Aqc" + str;
    try
    {
      a = Class.forName("com.tencent.stat.StatConfig");
      b = Class.forName("com.tencent.stat.StatService");
      c = b.getMethod("reportQQ", new Class[] { Context.class, String.class });
      d = b.getMethod("trackCustomEvent", new Class[] { Context.class, String.class, String[].class });
      e = b.getMethod("commitEvents", new Class[] { Context.class, Integer.TYPE });
      f = a.getMethod("setEnableStatService", new Class[] { Boolean.TYPE });
      b(paramContext, paramQQToken);
      a.getMethod("setAutoExceptionCaught", new Class[] { Boolean.TYPE }).invoke(a, new Object[] { Boolean.valueOf(false) });
      a.getMethod("setEnableSmartReporting", new Class[] { Boolean.TYPE }).invoke(a, new Object[] { Boolean.valueOf(true) });
      a.getMethod("setSendPeriodMinutes", new Class[] { Integer.TYPE }).invoke(a, new Object[] { Integer.valueOf(1440) });
      paramQQToken = Class.forName("com.tencent.stat.StatReportStrategy");
      a.getMethod("setStatSendStrategy", new Class[] { paramQQToken }).invoke(a, new Object[] { paramQQToken.getField("PERIOD").get(null) });
      b.getMethod("startStatService", new Class[] { Context.class, String.class, String.class }).invoke(b, new Object[] { paramContext, str, Class.forName("com.tencent.stat.common.StatConstants").getField("VERSION").get(null) });
      g = true;
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void d(Context paramContext, QQToken paramQQToken)
  {
    if (!TextUtils.isEmpty(paramQQToken.getOpenId())) {
      d.a().a(paramQQToken.getOpenId(), paramQQToken.getAppId(), "2", "1", "11", "0", "0", "0");
    }
    if (!g) {
      return;
    }
    b(paramContext, paramQQToken);
    if (paramQQToken.getOpenId() != null) {}
    try
    {
      c.invoke(b, new Object[] { paramContext, paramQQToken.getOpenId() });
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\connect\a\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */