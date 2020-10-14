package com.tencent.open.b;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.i;
import com.tencent.open.utils.k;
import java.io.Serializable;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.Executor;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ByteArrayEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class g
{
  protected static g a;
  protected Random b = new Random();
  protected List<Serializable> c = Collections.synchronizedList(new ArrayList());
  protected List<Serializable> d = Collections.synchronizedList(new ArrayList());
  protected HandlerThread e = null;
  protected Handler f;
  protected Executor g = i.b();
  protected Executor h = i.b();
  
  private g()
  {
    if (this.e == null)
    {
      this.e = new HandlerThread("opensdk.report.handlerthread", 10);
      this.e.start();
    }
    if ((this.e.isAlive()) && (this.e.getLooper() != null)) {
      this.f = new Handler(this.e.getLooper())
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          switch (paramAnonymousMessage.what)
          {
          }
          for (;;)
          {
            super.handleMessage(paramAnonymousMessage);
            return;
            g.this.b();
            continue;
            g.this.e();
          }
        }
      };
    }
  }
  
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
  
  protected int a(int paramInt)
  {
    if (paramInt == 0)
    {
      paramInt = com.tencent.open.utils.f.a(com.tencent.open.utils.e.a(), null).a("Common_CGIReportFrequencySuccess");
      if (paramInt == 0) {
        paramInt = 10;
      }
      for (;;)
      {
        return paramInt;
      }
    }
    paramInt = com.tencent.open.utils.f.a(com.tencent.open.utils.e.a(), null).a("Common_CGIReportFrequencyFailed");
    if (paramInt == 0) {
      paramInt = 100;
    }
    for (;;)
    {
      break;
    }
  }
  
  public void a(final Bundle paramBundle, String paramString, final boolean paramBoolean)
  {
    if (paramBundle == null) {
      return;
    }
    com.tencent.open.a.f.a("openSDK_LOG.ReportManager", "-->reportVia, bundle: " + paramBundle.toString());
    if ((!a("report_via", paramString)) && (!paramBoolean)) {
      return;
    }
    this.g.execute(new Runnable()
    {
      public void run()
      {
        label396:
        for (;;)
        {
          try
          {
            Object localObject = new Bundle();
            ((Bundle)localObject).putString("uin", "1000");
            ((Bundle)localObject).putString("imei", c.b(com.tencent.open.utils.e.a()));
            ((Bundle)localObject).putString("imsi", c.c(com.tencent.open.utils.e.a()));
            ((Bundle)localObject).putString("android_id", c.d(com.tencent.open.utils.e.a()));
            ((Bundle)localObject).putString("mac", c.a());
            ((Bundle)localObject).putString("platform", "1");
            ((Bundle)localObject).putString("os_ver", Build.VERSION.RELEASE);
            ((Bundle)localObject).putString("position", k.c(com.tencent.open.utils.e.a()));
            ((Bundle)localObject).putString("network", a.a(com.tencent.open.utils.e.a()));
            ((Bundle)localObject).putString("language", c.b());
            ((Bundle)localObject).putString("resolution", c.a(com.tencent.open.utils.e.a()));
            ((Bundle)localObject).putString("apn", a.b(com.tencent.open.utils.e.a()));
            ((Bundle)localObject).putString("model_name", Build.MODEL);
            ((Bundle)localObject).putString("timezone", TimeZone.getDefault().getID());
            ((Bundle)localObject).putString("sdk_ver", "3.3.0.lite");
            ((Bundle)localObject).putString("qz_ver", k.d(com.tencent.open.utils.e.a(), "com.qzone"));
            ((Bundle)localObject).putString("qq_ver", k.c(com.tencent.open.utils.e.a(), "com.tencent.mobileqq"));
            ((Bundle)localObject).putString("qua", k.e(com.tencent.open.utils.e.a(), com.tencent.open.utils.e.b()));
            ((Bundle)localObject).putString("packagename", com.tencent.open.utils.e.b());
            ((Bundle)localObject).putString("app_ver", k.d(com.tencent.open.utils.e.a(), com.tencent.open.utils.e.b()));
            if (paramBundle != null) {
              ((Bundle)localObject).putAll(paramBundle);
            }
            localObject = new b((Bundle)localObject);
            g.this.d.add(localObject);
            int j = g.this.d.size();
            int i = com.tencent.open.utils.f.a(com.tencent.open.utils.e.a(), null).a("Agent_ReportTimeInterval");
            if (i != 0) {
              break label396;
            }
            i = 10000;
            if ((g.this.a("report_via", j)) || (paramBoolean))
            {
              g.this.e();
              g.this.f.removeMessages(1001);
              return;
            }
            if (!g.this.f.hasMessages(1001))
            {
              localObject = Message.obtain();
              ((Message)localObject).what = 1001;
              g.this.f.sendMessageDelayed((Message)localObject, i);
              return;
            }
          }
          catch (Exception localException)
          {
            com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "--> reporVia, exception in sub thread.", localException);
          }
          return;
        }
      }
    });
  }
  
  public void a(String paramString, long paramLong1, long paramLong2, long paramLong3, int paramInt)
  {
    a(paramString, paramLong1, paramLong2, paramLong3, paramInt, "", false);
  }
  
  public void a(String paramString1, final long paramLong1, final long paramLong2, final long paramLong3, final int paramInt, final String paramString2, boolean paramBoolean)
  {
    com.tencent.open.a.f.a("openSDK_LOG.ReportManager", "-->reportCgi, command: " + paramString1 + " | startTime: " + paramLong1 + " | reqSize:" + paramLong2 + " | rspSize: " + paramLong3 + " | responseCode: " + paramInt + " | detail: " + paramString2);
    if ((!a("report_cgi", "" + paramInt)) && (!paramBoolean)) {
      return;
    }
    this.h.execute(new Runnable()
    {
      public void run()
      {
        label487:
        label492:
        label506:
        for (;;)
        {
          int j;
          try
          {
            long l1 = SystemClock.elapsedRealtime();
            long l2 = paramLong1;
            Object localObject1 = new Bundle();
            Object localObject2 = a.a(com.tencent.open.utils.e.a());
            ((Bundle)localObject1).putString("apn", (String)localObject2);
            ((Bundle)localObject1).putString("appid", "1000067");
            ((Bundle)localObject1).putString("commandid", paramString2);
            ((Bundle)localObject1).putString("detail", paramInt);
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("network=").append((String)localObject2).append('&');
            localObject2 = localStringBuilder.append("sdcard=");
            if (!Environment.getExternalStorageState().equals("mounted")) {
              break label487;
            }
            i = 1;
            ((StringBuilder)localObject2).append(i).append('&');
            localStringBuilder.append("wifi=").append(a.e(com.tencent.open.utils.e.a()));
            ((Bundle)localObject1).putString("deviceInfo", localStringBuilder.toString());
            j = 100 / g.this.a(paramLong2);
            if (j > 0) {
              break label492;
            }
            i = 1;
            ((Bundle)localObject1).putString("frequency", i + "");
            ((Bundle)localObject1).putString("reqSize", paramLong3 + "");
            ((Bundle)localObject1).putString("resultCode", paramLong2 + "");
            ((Bundle)localObject1).putString("rspSize", this.f + "");
            ((Bundle)localObject1).putString("timeCost", l1 - l2 + "");
            ((Bundle)localObject1).putString("uin", "1000");
            localObject1 = new b((Bundle)localObject1);
            g.this.c.add(localObject1);
            j = g.this.c.size();
            i = com.tencent.open.utils.f.a(com.tencent.open.utils.e.a(), null).a("Agent_ReportTimeInterval");
            if (i != 0) {
              break label506;
            }
            i = 10000;
            if ((g.this.a("report_cgi", j)) || (this.g))
            {
              g.this.b();
              g.this.f.removeMessages(1000);
              return;
            }
            if (!g.this.f.hasMessages(1000))
            {
              localObject1 = Message.obtain();
              ((Message)localObject1).what = 1000;
              g.this.f.sendMessageDelayed((Message)localObject1, i);
              return;
            }
          }
          catch (Exception localException)
          {
            com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "--> reportCGI, exception in sub thread.", localException);
          }
          return;
          int i = 0;
          continue;
          i = j;
          if (j > 100) {
            i = 100;
          }
        }
      }
    });
  }
  
  public void a(final String paramString1, final String paramString2, final Bundle paramBundle, final boolean paramBoolean)
  {
    i.a(new Runnable()
    {
      public void run()
      {
        for (;;)
        {
          int n;
          int i1;
          int j;
          try
          {
            if (paramBundle == null)
            {
              com.tencent.open.a.f.e("openSDK_LOG.ReportManager", "-->httpRequest, params is null!");
              return;
            }
            k = 0;
            n = e.a();
            if (n == 0)
            {
              n = 3;
              com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->httpRequest, retryCount: " + n);
              i = 0;
              HttpClient localHttpClient = HttpUtils.getHttpClient(com.tencent.open.utils.e.a(), null, paramString1);
              Object localObject2 = HttpUtils.encodeUrl(paramBundle);
              Object localObject1 = localObject2;
              if (paramBoolean) {
                localObject1 = URLEncoder.encode((String)localObject2);
              }
              int m;
              if (paramString2.toUpperCase().equals("GET"))
              {
                localObject2 = new StringBuffer(paramString1);
                ((StringBuffer)localObject2).append((String)localObject1);
                localObject1 = new HttpGet(((StringBuffer)localObject2).toString());
                ((HttpUriRequest)localObject1).addHeader("Accept-Encoding", "gzip");
                ((HttpUriRequest)localObject1).addHeader("Content-Type", "application/x-www-form-urlencoded");
                i1 = k + 1;
                j = i;
                k = i;
                m = i;
              }
              try
              {
                int i2 = localHttpClient.execute((HttpUriRequest)localObject1).getStatusLine().getStatusCode();
                j = i;
                k = i;
                m = i;
                com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->httpRequest, statusCode: " + i2);
                if (i2 == 200) {
                  continue;
                }
                j = i;
                k = i;
                m = i;
                com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest : HttpStatuscode != 200");
              }
              catch (ConnectTimeoutException localConnectTimeoutException)
              {
                com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest ConnectTimeoutException");
                break label417;
              }
              catch (SocketTimeoutException localSocketTimeoutException)
              {
                com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest SocketTimeoutException");
                j = k;
                break label417;
              }
              catch (Exception localException1)
              {
                com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest Exception");
                i = m;
                continue;
                com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest Thread request failed");
              }
              if (i == 1)
              {
                com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest Thread request success");
                break label413;
                if (paramString2.toUpperCase().equals("POST"))
                {
                  localObject2 = new HttpPost(paramString1);
                  ((HttpPost)localObject2).setEntity(new ByteArrayEntity(k.i((String)localObject1)));
                  localObject1 = localObject2;
                  continue;
                }
                com.tencent.open.a.f.e("openSDK_LOG.ReportManager", "-->httpRequest unkonw request method return.");
                return;
                j = 1;
                k = 1;
                m = 1;
                i = 1;
                com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->ReportCenter httpRequest Thread success");
                continue;
              }
              return;
            }
          }
          catch (Exception localException2)
          {
            com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->httpRequest, exception in serial executor.");
          }
          label413:
          continue;
          label417:
          int k = i1;
          int i = j;
          if (i1 >= n) {
            i = j;
          }
        }
      }
    });
  }
  
  protected boolean a(String paramString, int paramInt)
  {
    int i = 0;
    if (paramString.equals("report_cgi"))
    {
      i = com.tencent.open.utils.f.a(com.tencent.open.utils.e.a(), null).a("Common_CGIReportMaxcount");
      if (i == 0) {
        i = 5;
      }
    }
    while (!paramString.equals("report_via")) {
      for (;;)
      {
        com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->availableCount, report: " + paramString + " | dataSize: " + paramInt + " | maxcount: " + i);
        if (paramInt < i) {
          break;
        }
        return true;
      }
    }
    i = com.tencent.open.utils.f.a(com.tencent.open.utils.e.a(), null).a("Agent_ReportBatchCount");
    if (i == 0) {
      i = 5;
    }
    for (;;)
    {
      break;
    }
    return false;
  }
  
  protected boolean a(String paramString1, String paramString2)
  {
    com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->availableFrequency, report: " + paramString1 + " | ext: " + paramString2);
    if (TextUtils.isEmpty(paramString1)) {
      return false;
    }
    boolean bool = false;
    int i = 100;
    if (paramString1.equals("report_cgi")) {}
    for (;;)
    {
      try
      {
        i = Integer.parseInt(paramString2);
        i = a(i);
        if (this.b.nextInt(100) < i)
        {
          bool = true;
          com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->availableFrequency, result: " + bool + " | frequency: " + i);
          return bool;
        }
      }
      catch (Exception paramString1)
      {
        return bool;
      }
      bool = false;
      continue;
      if (paramString1.equals("report_via"))
      {
        i = e.a(paramString2);
        if (this.b.nextInt(100) < i) {
          bool = true;
        } else {
          bool = false;
        }
      }
    }
  }
  
  protected void b()
  {
    this.h.execute(new Runnable()
    {
      public void run()
      {
        for (;;)
        {
          int i;
          int k;
          int m;
          try
          {
            Bundle localBundle = g.this.c();
            if (localBundle == null) {
              return;
            }
            j = 0;
            i = com.tencent.open.utils.f.a(com.tencent.open.utils.e.a(), null).a("Common_HttpRetryCount");
            if (i != 0) {
              break label286;
            }
            i = 3;
            com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->doReportCgi, retryCount: " + i);
            k = 0;
            m = j + 1;
            try
            {
              HttpClient localHttpClient = HttpUtils.getHttpClient(com.tencent.open.utils.e.a(), null, "https://wspeed.qq.com/w.cgi");
              HttpPost localHttpPost = new HttpPost("https://wspeed.qq.com/w.cgi");
              localHttpPost.addHeader("Accept-Encoding", "gzip");
              localHttpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
              localHttpPost.setEntity(new ByteArrayEntity(k.i(HttpUtils.encodeUrl(localBundle))));
              int n = localHttpClient.execute(localHttpPost).getStatusLine().getStatusCode();
              com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->doReportCgi, statusCode: " + n);
              j = k;
              if (n == 200)
              {
                f.a().b("report_cgi");
                j = 1;
              }
            }
            catch (ConnectTimeoutException localConnectTimeoutException)
            {
              com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->doReportCgi, doupload exception", localConnectTimeoutException);
            }
            catch (SocketTimeoutException localSocketTimeoutException)
            {
              com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->doReportCgi, doupload exception", localSocketTimeoutException);
            }
            catch (Exception localException1)
            {
              com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->doReportCgi, doupload exception", localException1);
              j = k;
              continue;
            }
            if (j == 0) {
              f.a().a("report_cgi", g.this.c);
            }
            g.this.c.clear();
          }
          catch (Exception localException2)
          {
            com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->doReportCgi, doupload exception out.", localException2);
          }
          return;
          label286:
          continue;
          int j = m;
          if (m >= i) {
            j = k;
          }
        }
      }
    });
  }
  
  protected Bundle c()
  {
    if (this.c.size() == 0) {
      return null;
    }
    Object localObject1 = (b)this.c.get(0);
    if (localObject1 == null)
    {
      com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->prepareCgiData, the 0th cgireportitem is null.");
      return null;
    }
    Object localObject2 = (String)((b)localObject1).a.get("appid");
    localObject1 = f.a().a("report_cgi");
    if (localObject1 != null) {
      this.c.addAll((Collection)localObject1);
    }
    com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->prepareCgiData, mCgiList size: " + this.c.size());
    if (this.c.size() == 0) {
      return null;
    }
    localObject1 = new Bundle();
    try
    {
      ((Bundle)localObject1).putString("appid", (String)localObject2);
      ((Bundle)localObject1).putString("releaseversion", "OpenSdk_3.3.0.lite");
      ((Bundle)localObject1).putString("device", Build.DEVICE);
      ((Bundle)localObject1).putString("qua", "V1_AND_OpenSDK_3.3.0.lite_1077_RDM_B");
      ((Bundle)localObject1).putString("key", "apn,frequency,commandid,resultcode,tmcost,reqsize,rspsize,detail,touin,deviceinfo");
      int i = 0;
      while (i < this.c.size())
      {
        localObject2 = (b)this.c.get(i);
        ((Bundle)localObject1).putString(i + "_1", (String)((b)localObject2).a.get("apn"));
        ((Bundle)localObject1).putString(i + "_2", (String)((b)localObject2).a.get("frequency"));
        ((Bundle)localObject1).putString(i + "_3", (String)((b)localObject2).a.get("commandid"));
        ((Bundle)localObject1).putString(i + "_4", (String)((b)localObject2).a.get("resultCode"));
        ((Bundle)localObject1).putString(i + "_5", (String)((b)localObject2).a.get("timeCost"));
        ((Bundle)localObject1).putString(i + "_6", (String)((b)localObject2).a.get("reqSize"));
        ((Bundle)localObject1).putString(i + "_7", (String)((b)localObject2).a.get("rspSize"));
        ((Bundle)localObject1).putString(i + "_8", (String)((b)localObject2).a.get("detail"));
        ((Bundle)localObject1).putString(i + "_9", (String)((b)localObject2).a.get("uin"));
        localObject2 = c.e(com.tencent.open.utils.e.a()) + "&" + (String)((b)localObject2).a.get("deviceInfo");
        ((Bundle)localObject1).putString(i + "_10", (String)localObject2);
        i += 1;
      }
      com.tencent.open.a.f.a("openSDK_LOG.ReportManager", "-->prepareCgiData, end. params: " + ((Bundle)localObject1).toString());
      return (Bundle)localObject1;
    }
    catch (Exception localException)
    {
      com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->prepareCgiData, exception.", localException);
    }
    return null;
  }
  
  protected Bundle d()
  {
    Object localObject1 = f.a().a("report_via");
    if (localObject1 != null) {
      this.d.addAll((Collection)localObject1);
    }
    com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->prepareViaData, mViaList size: " + this.d.size());
    if (this.d.size() == 0) {
      return null;
    }
    JSONArray localJSONArray = new JSONArray();
    Iterator localIterator1 = this.d.iterator();
    while (localIterator1.hasNext())
    {
      localObject1 = (Serializable)localIterator1.next();
      JSONObject localJSONObject = new JSONObject();
      b localb = (b)localObject1;
      Iterator localIterator2 = localb.a.keySet().iterator();
      while (localIterator2.hasNext())
      {
        String str = (String)localIterator2.next();
        try
        {
          localObject2 = (String)localb.a.get(str);
          localObject1 = localObject2;
          if (localObject2 == null) {
            localObject1 = "";
          }
          localJSONObject.put(str, localObject1);
        }
        catch (JSONException localJSONException1)
        {
          com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->prepareViaData, put bundle to json array exception", localJSONException1);
        }
      }
      localJSONArray.put(localJSONObject);
    }
    com.tencent.open.a.f.a("openSDK_LOG.ReportManager", "-->prepareViaData, JSONArray array: " + localJSONArray.toString());
    Bundle localBundle = new Bundle();
    Object localObject2 = new JSONObject();
    try
    {
      ((JSONObject)localObject2).put("data", localJSONArray);
      localBundle.putString("data", ((JSONObject)localObject2).toString());
      return localBundle;
    }
    catch (JSONException localJSONException2)
    {
      com.tencent.open.a.f.b("openSDK_LOG.ReportManager", "-->prepareViaData, put bundle to json array exception", localJSONException2);
    }
    return null;
  }
  
  protected void e()
  {
    this.g.execute(new Runnable()
    {
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 17	com/tencent/open/b/g$5:a	Lcom/tencent/open/b/g;
        //   4: invokevirtual 39	com/tencent/open/b/g:d	()Landroid/os/Bundle;
        //   7: astore 29
        //   9: aload 29
        //   11: ifnonnull +4 -> 15
        //   14: return
        //   15: ldc 41
        //   17: new 43	java/lang/StringBuilder
        //   20: dup
        //   21: invokespecial 44	java/lang/StringBuilder:<init>	()V
        //   24: ldc 46
        //   26: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   29: aload 29
        //   31: invokevirtual 56	android/os/Bundle:toString	()Ljava/lang/String;
        //   34: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   37: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   40: invokestatic 62	com/tencent/open/a/f:a	(Ljava/lang/String;Ljava/lang/String;)V
        //   43: invokestatic 67	com/tencent/open/b/e:a	()I
        //   46: istore 8
        //   48: iconst_0
        //   49: istore 4
        //   51: iconst_0
        //   52: istore 9
        //   54: invokestatic 73	android/os/SystemClock:elapsedRealtime	()J
        //   57: lstore 23
        //   59: lconst_0
        //   60: lstore 27
        //   62: lconst_0
        //   63: lstore 25
        //   65: iconst_0
        //   66: istore_3
        //   67: iload 4
        //   69: iconst_1
        //   70: iadd
        //   71: istore_2
        //   72: iload_2
        //   73: istore 4
        //   75: iload 9
        //   77: istore 11
        //   79: iload_2
        //   80: istore 5
        //   82: iload 9
        //   84: istore 12
        //   86: iload 9
        //   88: istore 13
        //   90: lload 27
        //   92: lstore 17
        //   94: iload_2
        //   95: istore 6
        //   97: iload 9
        //   99: istore 14
        //   101: iload_2
        //   102: istore 7
        //   104: iload 9
        //   106: istore 15
        //   108: iload 9
        //   110: istore 16
        //   112: invokestatic 78	com/tencent/open/utils/e:a	()Landroid/content/Context;
        //   115: ldc 80
        //   117: ldc 82
        //   119: aload 29
        //   121: invokestatic 88	com/tencent/open/utils/HttpUtils:openUrl2	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)Lcom/tencent/open/utils/k$a;
        //   124: astore 30
        //   126: iload_2
        //   127: istore 4
        //   129: iload 9
        //   131: istore 11
        //   133: iload_2
        //   134: istore 5
        //   136: iload 9
        //   138: istore 12
        //   140: iload 9
        //   142: istore 13
        //   144: lload 27
        //   146: lstore 17
        //   148: iload_2
        //   149: istore 6
        //   151: iload 9
        //   153: istore 14
        //   155: iload_2
        //   156: istore 7
        //   158: iload 9
        //   160: istore 15
        //   162: iload 9
        //   164: istore 16
        //   166: aload 30
        //   168: getfield 93	com/tencent/open/utils/k$a:a	Ljava/lang/String;
        //   171: invokestatic 98	com/tencent/open/utils/k:d	(Ljava/lang/String;)Lorg/json/JSONObject;
        //   174: astore 31
        //   176: iload_2
        //   177: istore 4
        //   179: iload 9
        //   181: istore 11
        //   183: iload_2
        //   184: istore 5
        //   186: iload 9
        //   188: istore 12
        //   190: iload 9
        //   192: istore 13
        //   194: lload 27
        //   196: lstore 17
        //   198: iload_2
        //   199: istore 6
        //   201: iload 9
        //   203: istore 14
        //   205: iload 9
        //   207: istore 16
        //   209: aload 31
        //   211: ldc 100
        //   213: invokevirtual 106	org/json/JSONObject:getInt	(Ljava/lang/String;)I
        //   216: istore_1
        //   217: iload_1
        //   218: ifeq +441 -> 659
        //   221: iload_2
        //   222: istore_1
        //   223: iload 9
        //   225: istore 10
        //   227: iload_2
        //   228: istore 4
        //   230: iload 9
        //   232: istore 11
        //   234: iload_2
        //   235: istore 5
        //   237: iload 9
        //   239: istore 12
        //   241: iload 9
        //   243: istore 13
        //   245: lload 27
        //   247: lstore 17
        //   249: iload_2
        //   250: istore 6
        //   252: iload 9
        //   254: istore 14
        //   256: iload_2
        //   257: istore 7
        //   259: iload 9
        //   261: istore 15
        //   263: iload 9
        //   265: istore 16
        //   267: aload 30
        //   269: getfield 93	com/tencent/open/utils/k$a:a	Ljava/lang/String;
        //   272: invokestatic 112	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
        //   275: ifne +6 -> 281
        //   278: goto +381 -> 659
        //   281: iload_1
        //   282: istore 4
        //   284: iload 10
        //   286: istore 11
        //   288: iload_1
        //   289: istore 5
        //   291: iload 10
        //   293: istore 12
        //   295: iload 10
        //   297: istore 13
        //   299: lload 27
        //   301: lstore 17
        //   303: iload_1
        //   304: istore 6
        //   306: iload 10
        //   308: istore 14
        //   310: iload_1
        //   311: istore 7
        //   313: iload 10
        //   315: istore 15
        //   317: iload 10
        //   319: istore 16
        //   321: aload 30
        //   323: getfield 116	com/tencent/open/utils/k$a:b	J
        //   326: lstore 21
        //   328: iload_1
        //   329: istore 4
        //   331: iload 10
        //   333: istore 11
        //   335: iload_1
        //   336: istore 5
        //   338: iload 10
        //   340: istore 12
        //   342: iload 10
        //   344: istore 13
        //   346: lload 21
        //   348: lstore 17
        //   350: iload_1
        //   351: istore 6
        //   353: iload 10
        //   355: istore 14
        //   357: iload_1
        //   358: istore 7
        //   360: iload 10
        //   362: istore 15
        //   364: iload 10
        //   366: istore 16
        //   368: aload 30
        //   370: getfield 119	com/tencent/open/utils/k$a:c	J
        //   373: lstore 19
        //   375: iload_3
        //   376: istore_2
        //   377: lload 21
        //   379: lstore 17
        //   381: lload 23
        //   383: lstore 21
        //   385: iload_1
        //   386: istore 4
        //   388: iload 10
        //   390: istore 9
        //   392: lload 21
        //   394: lstore 23
        //   396: lload 17
        //   398: lstore 27
        //   400: lload 19
        //   402: lstore 25
        //   404: iload_2
        //   405: istore_3
        //   406: iload_1
        //   407: iload 8
        //   409: if_icmplt -342 -> 67
        //   412: aload_0
        //   413: getfield 17	com/tencent/open/b/g$5:a	Lcom/tencent/open/b/g;
        //   416: ldc 121
        //   418: lload 21
        //   420: lload 17
        //   422: lload 19
        //   424: iload_2
        //   425: aconst_null
        //   426: iconst_0
        //   427: invokevirtual 124	com/tencent/open/b/g:a	(Ljava/lang/String;JJJILjava/lang/String;Z)V
        //   430: iload 10
        //   432: ifeq +195 -> 627
        //   435: invokestatic 129	com/tencent/open/b/f:a	()Lcom/tencent/open/b/f;
        //   438: ldc -125
        //   440: invokevirtual 134	com/tencent/open/b/f:b	(Ljava/lang/String;)V
        //   443: aload_0
        //   444: getfield 17	com/tencent/open/b/g$5:a	Lcom/tencent/open/b/g;
        //   447: getfield 137	com/tencent/open/b/g:d	Ljava/util/List;
        //   450: invokeinterface 142 1 0
        //   455: ldc 41
        //   457: new 43	java/lang/StringBuilder
        //   460: dup
        //   461: invokespecial 44	java/lang/StringBuilder:<init>	()V
        //   464: ldc -112
        //   466: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   469: iload 10
        //   471: invokevirtual 147	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
        //   474: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   477: invokestatic 149	com/tencent/open/a/f:b	(Ljava/lang/String;Ljava/lang/String;)V
        //   480: goto +188 -> 668
        //   483: astore 30
        //   485: invokestatic 73	android/os/SystemClock:elapsedRealtime	()J
        //   488: lstore 21
        //   490: lconst_0
        //   491: lstore 17
        //   493: lconst_0
        //   494: lstore 19
        //   496: bipush -7
        //   498: istore_2
        //   499: iload 4
        //   501: istore_1
        //   502: iload 11
        //   504: istore 10
        //   506: goto -121 -> 385
        //   509: astore 30
        //   511: invokestatic 73	android/os/SystemClock:elapsedRealtime	()J
        //   514: lstore 21
        //   516: lconst_0
        //   517: lstore 17
        //   519: lconst_0
        //   520: lstore 19
        //   522: bipush -8
        //   524: istore_2
        //   525: iload 5
        //   527: istore_1
        //   528: iload 12
        //   530: istore 10
        //   532: goto -147 -> 385
        //   535: astore 29
        //   537: aload_0
        //   538: getfield 17	com/tencent/open/b/g$5:a	Lcom/tencent/open/b/g;
        //   541: getfield 137	com/tencent/open/b/g:d	Ljava/util/List;
        //   544: invokeinterface 142 1 0
        //   549: ldc 41
        //   551: ldc -105
        //   553: invokestatic 149	com/tencent/open/a/f:b	(Ljava/lang/String;Ljava/lang/String;)V
        //   556: return
        //   557: astore 29
        //   559: aload 29
        //   561: invokevirtual 154	com/tencent/open/utils/HttpUtils$HttpStatusException:getMessage	()Ljava/lang/String;
        //   564: ldc -100
        //   566: ldc -98
        //   568: invokevirtual 164	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   571: invokestatic 169	java/lang/Integer:parseInt	(Ljava/lang/String;)I
        //   574: istore_1
        //   575: iload_1
        //   576: istore_3
        //   577: iload 13
        //   579: istore 10
        //   581: lload 23
        //   583: lstore 21
        //   585: lload 25
        //   587: lstore 19
        //   589: iload_3
        //   590: istore_2
        //   591: goto -179 -> 412
        //   594: astore 29
        //   596: goto -19 -> 577
        //   599: astore 30
        //   601: lconst_0
        //   602: lstore 17
        //   604: lconst_0
        //   605: lstore 19
        //   607: aload 30
        //   609: invokestatic 173	com/tencent/open/utils/HttpUtils:getErrorCodeFromException	(Ljava/io/IOException;)I
        //   612: istore_2
        //   613: iload 6
        //   615: istore_1
        //   616: iload 14
        //   618: istore 10
        //   620: lload 23
        //   622: lstore 21
        //   624: goto -239 -> 385
        //   627: invokestatic 129	com/tencent/open/b/f:a	()Lcom/tencent/open/b/f;
        //   630: ldc -125
        //   632: aload_0
        //   633: getfield 17	com/tencent/open/b/g$5:a	Lcom/tencent/open/b/g;
        //   636: getfield 137	com/tencent/open/b/g:d	Ljava/util/List;
        //   639: invokevirtual 176	com/tencent/open/b/f:a	(Ljava/lang/String;Ljava/util/List;)V
        //   642: goto -199 -> 443
        //   645: astore 29
        //   647: ldc 41
        //   649: ldc -78
        //   651: aload 29
        //   653: invokestatic 181	com/tencent/open/a/f:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   656: goto +12 -> 668
        //   659: iconst_1
        //   660: istore 10
        //   662: iload 8
        //   664: istore_1
        //   665: goto -384 -> 281
        //   668: return
        //   669: astore 31
        //   671: bipush -4
        //   673: istore_1
        //   674: goto -457 -> 217
        //   677: astore 30
        //   679: lconst_0
        //   680: lstore 17
        //   682: lconst_0
        //   683: lstore 19
        //   685: bipush -4
        //   687: istore_2
        //   688: iload 7
        //   690: istore_1
        //   691: iload 15
        //   693: istore 10
        //   695: lload 23
        //   697: lstore 21
        //   699: goto -314 -> 385
        //   702: astore 30
        //   704: lconst_0
        //   705: lstore 17
        //   707: lconst_0
        //   708: lstore 19
        //   710: bipush -6
        //   712: istore_2
        //   713: iload 8
        //   715: istore_1
        //   716: iload 16
        //   718: istore 10
        //   720: lload 23
        //   722: lstore 21
        //   724: goto -339 -> 385
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	727	0	this	5
        //   216	500	1	i	int
        //   71	642	2	j	int
        //   66	524	3	k	int
        //   49	451	4	m	int
        //   80	446	5	n	int
        //   95	519	6	i1	int
        //   102	587	7	i2	int
        //   46	668	8	i3	int
        //   52	339	9	bool1	boolean
        //   225	494	10	bool2	boolean
        //   77	426	11	bool3	boolean
        //   84	445	12	bool4	boolean
        //   88	490	13	bool5	boolean
        //   99	518	14	bool6	boolean
        //   106	586	15	bool7	boolean
        //   110	607	16	bool8	boolean
        //   92	614	17	l1	long
        //   373	336	19	l2	long
        //   326	397	21	l3	long
        //   57	664	23	l4	long
        //   63	523	25	l5	long
        //   60	339	27	l6	long
        //   7	113	29	localBundle	Bundle
        //   535	1	29	localNetworkUnavailableException	com.tencent.open.utils.HttpUtils.NetworkUnavailableException
        //   557	3	29	localHttpStatusException	com.tencent.open.utils.HttpUtils.HttpStatusException
        //   594	1	29	localException1	Exception
        //   645	7	29	localException2	Exception
        //   124	245	30	locala	com.tencent.open.utils.k.a
        //   483	1	30	localConnectTimeoutException	ConnectTimeoutException
        //   509	1	30	localSocketTimeoutException	SocketTimeoutException
        //   599	9	30	localIOException	java.io.IOException
        //   677	1	30	localJSONException1	JSONException
        //   702	1	30	localException3	Exception
        //   174	36	31	localJSONObject	JSONObject
        //   669	1	31	localJSONException2	JSONException
        // Exception table:
        //   from	to	target	type
        //   112	126	483	org/apache/http/conn/ConnectTimeoutException
        //   166	176	483	org/apache/http/conn/ConnectTimeoutException
        //   209	217	483	org/apache/http/conn/ConnectTimeoutException
        //   267	278	483	org/apache/http/conn/ConnectTimeoutException
        //   321	328	483	org/apache/http/conn/ConnectTimeoutException
        //   368	375	483	org/apache/http/conn/ConnectTimeoutException
        //   112	126	509	java/net/SocketTimeoutException
        //   166	176	509	java/net/SocketTimeoutException
        //   209	217	509	java/net/SocketTimeoutException
        //   267	278	509	java/net/SocketTimeoutException
        //   321	328	509	java/net/SocketTimeoutException
        //   368	375	509	java/net/SocketTimeoutException
        //   112	126	535	com/tencent/open/utils/HttpUtils$NetworkUnavailableException
        //   166	176	535	com/tencent/open/utils/HttpUtils$NetworkUnavailableException
        //   209	217	535	com/tencent/open/utils/HttpUtils$NetworkUnavailableException
        //   267	278	535	com/tencent/open/utils/HttpUtils$NetworkUnavailableException
        //   321	328	535	com/tencent/open/utils/HttpUtils$NetworkUnavailableException
        //   368	375	535	com/tencent/open/utils/HttpUtils$NetworkUnavailableException
        //   112	126	557	com/tencent/open/utils/HttpUtils$HttpStatusException
        //   166	176	557	com/tencent/open/utils/HttpUtils$HttpStatusException
        //   209	217	557	com/tencent/open/utils/HttpUtils$HttpStatusException
        //   267	278	557	com/tencent/open/utils/HttpUtils$HttpStatusException
        //   321	328	557	com/tencent/open/utils/HttpUtils$HttpStatusException
        //   368	375	557	com/tencent/open/utils/HttpUtils$HttpStatusException
        //   559	575	594	java/lang/Exception
        //   112	126	599	java/io/IOException
        //   166	176	599	java/io/IOException
        //   209	217	599	java/io/IOException
        //   267	278	599	java/io/IOException
        //   321	328	599	java/io/IOException
        //   368	375	599	java/io/IOException
        //   0	9	645	java/lang/Exception
        //   15	48	645	java/lang/Exception
        //   54	59	645	java/lang/Exception
        //   412	430	645	java/lang/Exception
        //   435	443	645	java/lang/Exception
        //   443	480	645	java/lang/Exception
        //   485	490	645	java/lang/Exception
        //   511	516	645	java/lang/Exception
        //   537	556	645	java/lang/Exception
        //   607	613	645	java/lang/Exception
        //   627	642	645	java/lang/Exception
        //   209	217	669	org/json/JSONException
        //   112	126	677	org/json/JSONException
        //   166	176	677	org/json/JSONException
        //   267	278	677	org/json/JSONException
        //   321	328	677	org/json/JSONException
        //   368	375	677	org/json/JSONException
        //   112	126	702	java/lang/Exception
        //   166	176	702	java/lang/Exception
        //   209	217	702	java/lang/Exception
        //   267	278	702	java/lang/Exception
        //   321	328	702	java/lang/Exception
        //   368	375	702	java/lang/Exception
      }
    });
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\b\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */