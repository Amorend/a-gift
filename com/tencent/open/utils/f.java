package com.tencent.open.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class f
{
  private static Map<String, f> a = Collections.synchronizedMap(new HashMap());
  private static String b = null;
  private Context c = null;
  private String d = null;
  private JSONObject e = null;
  private long f = 0L;
  private int g = 0;
  private boolean h = true;
  
  private f(Context paramContext, String paramString)
  {
    this.c = paramContext.getApplicationContext();
    this.d = paramString;
    a();
    b();
  }
  
  public static f a(Context paramContext, String paramString)
  {
    Object localObject = paramString;
    for (;;)
    {
      synchronized (a)
      {
        com.tencent.open.a.f.a("openSDK_LOG.OpenConfig", "getInstance begin");
        if (localObject != null) {
          b = (String)localObject;
        }
        paramString = (String)localObject;
        if (localObject == null)
        {
          if (b != null) {
            paramString = b;
          }
        }
        else
        {
          f localf = (f)a.get(paramString);
          localObject = localf;
          if (localf == null)
          {
            localObject = new f(paramContext, paramString);
            a.put(paramString, localObject);
          }
          com.tencent.open.a.f.a("openSDK_LOG.OpenConfig", "getInstance end");
          return (f)localObject;
        }
      }
      paramString = "0";
    }
  }
  
  private void a()
  {
    String str = c("com.tencent.open.config.json");
    try
    {
      this.e = new JSONObject(str);
      return;
    }
    catch (JSONException localJSONException)
    {
      this.e = new JSONObject();
    }
  }
  
  private void a(String paramString1, String paramString2)
  {
    try
    {
      if (this.d != null) {
        paramString1 = paramString1 + "." + this.d;
      }
      for (;;)
      {
        paramString1 = new OutputStreamWriter(this.c.openFileOutput(paramString1, 0), Charset.forName("UTF-8"));
        paramString1.write(paramString2);
        paramString1.flush();
        paramString1.close();
        return;
      }
      return;
    }
    catch (IOException paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  private void a(JSONObject paramJSONObject)
  {
    d("cgi back, do update");
    this.e = paramJSONObject;
    a("com.tencent.open.config.json", paramJSONObject.toString());
    this.f = SystemClock.elapsedRealtime();
  }
  
  private void b()
  {
    if (this.g != 0)
    {
      d("update thread is running, return");
      return;
    }
    this.g = 1;
    final Bundle localBundle = new Bundle();
    localBundle.putString("appid", this.d);
    localBundle.putString("appid_for_getting_config", this.d);
    localBundle.putString("status_os", Build.VERSION.RELEASE);
    localBundle.putString("status_machine", Build.MODEL);
    localBundle.putString("status_version", Build.VERSION.SDK);
    localBundle.putString("sdkv", "3.3.0.lite");
    localBundle.putString("sdkp", "a");
    new Thread()
    {
      public void run()
      {
        try
        {
          JSONObject localJSONObject = k.d(HttpUtils.openUrl2(f.a(f.this), "http://cgi.connect.qq.com/qqconnectopen/openapi/policy_conf", "GET", localBundle).a);
          f.a(f.this, localJSONObject);
          f.a(f.this, 0);
          return;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            localException.printStackTrace();
          }
        }
      }
    }.start();
  }
  
  private String c(String paramString)
  {
    String str3 = "";
    BufferedReader localBufferedReader;
    try
    {
      if (this.d != null)
      {
        localObject1 = paramString + "." + this.d;
        localObject1 = this.c.openFileInput((String)localObject1);
        paramString = (String)localObject1;
        localBufferedReader = new BufferedReader(new InputStreamReader(paramString, Charset.forName("UTF-8")));
        localObject1 = new StringBuffer();
      }
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      for (;;)
      {
        try
        {
          Object localObject1;
          String str4 = localBufferedReader.readLine();
          if (str4 != null)
          {
            ((StringBuffer)localObject1).append(str4);
            continue;
            localObject1 = paramString;
            continue;
            localFileNotFoundException = localFileNotFoundException;
            try
            {
              paramString = this.c.getAssets().open(paramString);
            }
            catch (IOException paramString)
            {
              paramString.printStackTrace();
              return str3;
            }
          }
          str1 = localFileNotFoundException.toString();
        }
        catch (IOException localIOException)
        {
          String str1;
          localIOException = localIOException;
          localIOException.printStackTrace();
          try
          {
            paramString.close();
            localBufferedReader.close();
            str2 = str3;
          }
          catch (IOException paramString)
          {
            paramString.printStackTrace();
            str2 = str3;
          }
          continue;
        }
        finally {}
        try
        {
          paramString.close();
          localBufferedReader.close();
          return str1;
        }
        catch (IOException paramString)
        {
          paramString.printStackTrace();
        }
      }
    }
    try
    {
      String str2;
      paramString.close();
      localBufferedReader.close();
      throw ((Throwable)localObject2);
    }
    catch (IOException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
  }
  
  private void c()
  {
    int j = this.e.optInt("Common_frequency");
    int i = j;
    if (j == 0) {
      i = 1;
    }
    long l = i * 3600000;
    if (SystemClock.elapsedRealtime() - this.f >= l) {
      b();
    }
  }
  
  private void d(String paramString)
  {
    if (this.h) {
      com.tencent.open.a.f.a("openSDK_LOG.OpenConfig", paramString + "; appid: " + this.d);
    }
  }
  
  public int a(String paramString)
  {
    d("get " + paramString);
    c();
    return this.e.optInt(paramString);
  }
  
  public boolean b(String paramString)
  {
    d("get " + paramString);
    c();
    paramString = this.e.opt(paramString);
    if (paramString == null) {
      return false;
    }
    if ((paramString instanceof Integer))
    {
      if (!paramString.equals(Integer.valueOf(0))) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    if ((paramString instanceof Boolean)) {
      return ((Boolean)paramString).booleanValue();
    }
    return false;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\utils\f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */