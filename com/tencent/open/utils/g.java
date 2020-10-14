package com.tencent.open.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.tencent.open.a.f;
import java.lang.ref.WeakReference;
import java.net.URL;

public class g
{
  private static g a = null;
  private volatile WeakReference<SharedPreferences> b = null;
  
  public static g a()
  {
    try
    {
      if (a == null) {
        a = new g();
      }
      g localg = a;
      return localg;
    }
    finally {}
  }
  
  public String a(Context paramContext, String paramString)
  {
    if ((this.b == null) || (this.b.get() == null)) {
      this.b = new WeakReference(paramContext.getSharedPreferences("ServerPrefs", 0));
    }
    paramContext = paramString;
    try
    {
      String str1 = new URL(paramString).getHost();
      if (str1 == null)
      {
        paramContext = paramString;
        f.e("openSDK_LOG.ServerSetting", "Get host error. url=" + paramString);
        return paramString;
      }
      paramContext = paramString;
      String str2 = ((SharedPreferences)this.b.get()).getString(str1, null);
      if (str2 != null)
      {
        paramContext = paramString;
        if (!str1.equals(str2)) {}
      }
      else
      {
        paramContext = paramString;
        f.a("openSDK_LOG.ServerSetting", "host=" + str1 + ", envHost=" + str2);
        return paramString;
      }
      paramContext = paramString;
      paramString = paramString.replace(str1, str2);
      paramContext = paramString;
      f.a("openSDK_LOG.ServerSetting", "return environment url : " + paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      f.e("openSDK_LOG.ServerSetting", "getEnvUrl url=" + paramContext + "error.: " + paramString.getMessage());
    }
    return paramContext;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\utils\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */