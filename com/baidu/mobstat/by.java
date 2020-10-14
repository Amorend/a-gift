package com.baidu.mobstat;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.Timer;
import java.util.zip.GZIPOutputStream;

class by
{
  private static by a = new by();
  private boolean b = false;
  private int c = 0;
  private int d = 1;
  private SendStrategyEnum e = SendStrategyEnum.APP_START;
  private Timer f;
  private Handler g;
  
  private by()
  {
    HandlerThread localHandlerThread = new HandlerThread("LogSenderThread");
    localHandlerThread.start();
    this.g = new Handler(localHandlerThread.getLooper());
  }
  
  public static by a()
  {
    return a;
  }
  
  private String a(Context paramContext, String paramString1, String paramString2)
  {
    if (!paramString1.startsWith("https:")) {
      return c(paramContext, paramString1, paramString2);
    }
    return b(paramContext, paramString1, paramString2);
  }
  
  private String b(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = cs.d(paramContext, paramString1);
    paramContext.setDoOutput(true);
    paramContext.setInstanceFollowRedirects(false);
    paramContext.setUseCaches(false);
    paramContext.setRequestProperty("Content-Type", "gzip");
    paramContext.connect();
    cz.a("AdUtil.httpPost connected");
    try
    {
      paramString1 = new BufferedWriter(new OutputStreamWriter(new GZIPOutputStream(paramContext.getOutputStream())));
      paramString1.write(paramString2);
      paramString1.flush();
      paramString1.close();
      paramString2 = new BufferedReader(new InputStreamReader(paramContext.getInputStream()));
      paramString1 = new StringBuilder();
      for (;;)
      {
        String str = paramString2.readLine();
        if (str == null) {
          break;
        }
        paramString1.append(str);
      }
      int i = paramContext.getContentLength();
      if ((paramContext.getResponseCode() == 200) && (i == 0))
      {
        paramString1 = paramString1.toString();
        return paramString1;
      }
      paramString2 = new StringBuilder();
      paramString2.append("http code = ");
      paramString2.append(paramContext.getResponseCode());
      paramString2.append("; contentResponse = ");
      paramString2.append(paramString1);
      throw new IOException(paramString2.toString());
    }
    finally
    {
      paramContext.disconnect();
    }
  }
  
  private boolean b(Context paramContext, String paramString)
  {
    boolean bool2 = this.b;
    boolean bool1 = false;
    if ((bool2) && (!dc.n(paramContext))) {
      return false;
    }
    try
    {
      a(paramContext, Config.LOG_SEND_URL, paramString);
      bool1 = true;
    }
    catch (Exception paramContext)
    {
      cz.c(paramContext);
    }
    paramContext = new StringBuilder();
    paramContext.append("send log data over. result = ");
    paramContext.append(bool1);
    paramContext.append("; data=");
    paramContext.append(paramString);
    cz.a(paramContext.toString());
    return bool1;
  }
  
  private String c(Context paramContext, String paramString1, String paramString2)
  {
    cz.a("httpPostEncrypt");
    paramContext = cs.d(paramContext, paramString1);
    paramContext.setDoOutput(true);
    paramContext.setInstanceFollowRedirects(false);
    paramContext.setUseCaches(false);
    paramContext.setRequestProperty("Content-Type", "gzip");
    paramString1 = cq.a();
    Object localObject = cq.b();
    paramContext.setRequestProperty("key", da.a(paramString1));
    paramContext.setRequestProperty("iv", da.a((byte[])localObject));
    paramString1 = cq.a(paramString1, (byte[])localObject, paramString2.getBytes("utf-8"));
    paramContext.connect();
    cz.a("AdUtil.httpPost connected");
    try
    {
      paramString2 = new GZIPOutputStream(paramContext.getOutputStream());
      paramString2.write(paramString1);
      paramString2.flush();
      paramString2.close();
      paramString2 = new BufferedReader(new InputStreamReader(paramContext.getInputStream()));
      paramString1 = new StringBuilder();
      for (;;)
      {
        localObject = paramString2.readLine();
        if (localObject == null) {
          break;
        }
        paramString1.append((String)localObject);
      }
      int i = paramContext.getContentLength();
      if ((paramContext.getResponseCode() == 200) && (i == 0))
      {
        paramString1 = paramString1.toString();
        return paramString1;
      }
      paramString2 = new StringBuilder();
      paramString2.append("http code = ");
      paramString2.append(paramContext.getResponseCode());
      paramString2.append("; contentResponse = ");
      paramString2.append(paramString1);
      throw new IOException(paramString2.toString());
    }
    finally
    {
      paramContext.disconnect();
    }
  }
  
  private void c(Context paramContext)
  {
    if ((this.b) && (!dc.n(paramContext))) {
      return;
    }
    this.g.post(new cc(this, paramContext));
  }
  
  public void a(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt <= 30)) {
      this.c = paramInt;
    }
  }
  
  public void a(Context paramContext)
  {
    Context localContext = paramContext;
    if (paramContext != null) {
      localContext = paramContext.getApplicationContext();
    }
    if (localContext == null) {
      return;
    }
    this.g.post(new bz(this, localContext));
  }
  
  public void a(Context paramContext, SendStrategyEnum paramSendStrategyEnum, int paramInt, boolean paramBoolean)
  {
    if (paramSendStrategyEnum.equals(SendStrategyEnum.SET_TIME_INTERVAL))
    {
      if ((paramInt > 0) && (paramInt <= 24))
      {
        this.d = paramInt;
        this.e = SendStrategyEnum.SET_TIME_INTERVAL;
        bj.a().a(paramContext, this.e.ordinal());
        bj.a().b(paramContext, this.d);
      }
      else
      {
        cz.c("timeInterval is invalid, new strategy does not work");
      }
    }
    else
    {
      this.e = paramSendStrategyEnum;
      bj.a().a(paramContext, this.e.ordinal());
      if (paramSendStrategyEnum.equals(SendStrategyEnum.ONCE_A_DAY)) {
        bj.a().b(paramContext, 24);
      }
    }
    this.b = paramBoolean;
    bj.a().a(paramContext, this.b);
    paramContext = new StringBuilder();
    paramContext.append("sstype is:");
    paramContext.append(this.e.name());
    paramContext.append(" And timeInterval is:");
    paramContext.append(this.d);
    paramContext.append(" And mOnlyWifi:");
    paramContext.append(this.b);
    cz.a(paramContext.toString());
  }
  
  public void a(Context paramContext, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("__send_data_");
    localStringBuilder.append(System.currentTimeMillis());
    cs.a(paramContext, localStringBuilder.toString(), paramString, false);
  }
  
  public void b(Context paramContext)
  {
    paramContext = paramContext.getApplicationContext();
    long l = this.d * 3600000;
    this.f = new Timer();
    this.f.schedule(new cb(this, paramContext), l, l);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\by.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */