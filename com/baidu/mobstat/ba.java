package com.baidu.mobstat;

import android.content.Context;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ba
{
  public static final ba a = new ba();
  
  private void a(JSONObject paramJSONObject)
  {
    paramJSONObject = new be(paramJSONObject);
    bc.b = paramJSONObject.a;
    bc.c = paramJSONObject.b;
    bc.d = paramJSONObject.c;
  }
  
  private boolean a()
  {
    if (!y.a.b()) {
      return true;
    }
    if (!y.b.b()) {
      return true;
    }
    if (!y.c.b()) {
      return true;
    }
    if (!y.d.b()) {
      return true;
    }
    return !y.e.b();
  }
  
  private void b(Context paramContext, JSONObject paramJSONObject)
  {
    JSONObject localJSONObject = new JSONObject();
    int j = 0;
    try
    {
      localJSONObject.put("he", paramJSONObject);
      i = paramJSONObject.toString().length();
      j = 0 + i;
    }
    catch (JSONException paramJSONObject)
    {
      bd.a(paramJSONObject);
    }
    bd.a("APP_MEM");
    int i = j;
    if (!az.a(paramContext).b())
    {
      paramJSONObject = dc.t(paramContext);
      localObject = new JSONArray();
      bd.a(paramJSONObject);
      ((JSONArray)localObject).put(paramJSONObject);
      i = j;
      if (((JSONArray)localObject).length() > 0) {
        try
        {
          localJSONObject.put("app_mem3", localObject);
          i = ((JSONArray)localObject).toString().length();
          i = j + i;
        }
        catch (JSONException paramJSONObject)
        {
          bd.a(paramJSONObject);
          i = j;
        }
      }
    }
    bd.a("APP_APK");
    Object localObject = y.e.a(20480);
    paramJSONObject = new JSONArray();
    localObject = ((List)localObject).iterator();
    String str;
    while (((Iterator)localObject).hasNext())
    {
      str = (String)((Iterator)localObject).next();
      bd.a(str);
      paramJSONObject.put(str);
    }
    j = i;
    if (paramJSONObject.length() > 0) {
      try
      {
        localJSONObject.put("app_apk3", paramJSONObject);
        j = paramJSONObject.toString().length();
        j = i + j;
      }
      catch (JSONException paramJSONObject)
      {
        bd.a(paramJSONObject);
        j = i;
      }
    }
    bd.a("APP_CHANGE");
    localObject = y.d.a(10240);
    paramJSONObject = new JSONArray();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      str = (String)((Iterator)localObject).next();
      bd.a(str);
      paramJSONObject.put(str);
    }
    i = j;
    if (paramJSONObject.length() > 0) {
      try
      {
        localJSONObject.put("app_change3", paramJSONObject);
        i = paramJSONObject.toString().length();
        i = j + i;
      }
      catch (JSONException paramJSONObject)
      {
        bd.a(paramJSONObject);
        i = j;
      }
    }
    bd.a("APP_TRACE");
    localObject = y.c.a(15360);
    paramJSONObject = new JSONArray();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      str = (String)((Iterator)localObject).next();
      bd.a(str);
      paramJSONObject.put(str);
    }
    j = i;
    if (paramJSONObject.length() > 0) {
      try
      {
        localJSONObject.put("app_trace3", paramJSONObject);
        j = paramJSONObject.toString().length();
        j = i + j;
      }
      catch (JSONException paramJSONObject)
      {
        bd.a(paramJSONObject);
        j = i;
      }
    }
    bd.a("APP_LIST");
    localObject = y.b.a(46080);
    paramJSONObject = new JSONArray();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      str = (String)((Iterator)localObject).next();
      bd.a(str);
      paramJSONObject.put(str);
    }
    i = j;
    if (paramJSONObject.length() > 0) {
      try
      {
        localJSONObject.put("app_list3", paramJSONObject);
        i = paramJSONObject.toString().length();
        i = j + i;
      }
      catch (JSONException paramJSONObject)
      {
        bd.a(paramJSONObject);
        i = j;
      }
    }
    bd.a("AP_LIST");
    localObject = y.a.a(184320 - i);
    paramJSONObject = new JSONArray();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      str = (String)((Iterator)localObject).next();
      bd.a(str);
      paramJSONObject.put(str);
    }
    j = i;
    if (paramJSONObject.length() > 0) {
      try
      {
        localJSONObject.put("ap_list3", paramJSONObject);
        j = paramJSONObject.toString().length();
        j = i + j;
      }
      catch (JSONException paramJSONObject)
      {
        bd.a(paramJSONObject);
        j = i;
      }
    }
    paramJSONObject = new StringBuilder();
    paramJSONObject.append("log in bytes is almost :");
    paramJSONObject.append(j);
    bd.a(paramJSONObject.toString());
    paramJSONObject = new JSONArray();
    paramJSONObject.put(localJSONObject);
    localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("payload", paramJSONObject);
      al.a().a(paramContext, localJSONObject.toString());
      return;
    }
    catch (Exception paramContext)
    {
      bd.a(paramContext);
    }
  }
  
  private void c(Context paramContext)
  {
    bd.a("collectAPWithStretegy 1");
    Object localObject = az.a(paramContext);
    long l1 = ((az)localObject).a(u.a);
    long l2 = System.currentTimeMillis();
    long l3 = ((az)localObject).e();
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("now time: ");
    ((StringBuilder)localObject).append(l2);
    ((StringBuilder)localObject).append(": last time: ");
    ((StringBuilder)localObject).append(l1);
    ((StringBuilder)localObject).append("; time interval: ");
    ((StringBuilder)localObject).append(l3);
    bd.a(((StringBuilder)localObject).toString());
    if ((l1 == 0L) || (l2 - l1 > l3))
    {
      bd.a("collectAPWithStretegy 2");
      n.a(paramContext);
    }
  }
  
  private void d(Context paramContext)
  {
    bd.a("collectAPPListWithStretegy 1");
    long l1 = System.currentTimeMillis();
    Object localObject = az.a(paramContext);
    long l2 = ((az)localObject).a(u.b);
    long l3 = ((az)localObject).f();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("now time: ");
    localStringBuilder.append(l1);
    localStringBuilder.append(": last time: ");
    localStringBuilder.append(l2);
    localStringBuilder.append("; userInterval : ");
    localStringBuilder.append(l3);
    bd.a(localStringBuilder.toString());
    if ((l2 == 0L) || (l1 - l2 > l3) || (!((az)localObject).a(l2)))
    {
      bd.a("collectUserAPPListWithStretegy 2");
      n.a(paramContext, false);
    }
    l2 = ((az)localObject).a(u.c);
    l3 = ((az)localObject).g();
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("now time: ");
    ((StringBuilder)localObject).append(l1);
    ((StringBuilder)localObject).append(": last time: ");
    ((StringBuilder)localObject).append(l2);
    ((StringBuilder)localObject).append("; sysInterval : ");
    ((StringBuilder)localObject).append(l3);
    bd.a(((StringBuilder)localObject).toString());
    if ((l2 == 0L) || (l1 - l2 > l3))
    {
      bd.a("collectSysAPPListWithStretegy 2");
      n.a(paramContext, true);
    }
  }
  
  private void e(Context paramContext)
  {
    bd.a("collectAPPTraceWithStretegy 1");
    long l1 = System.currentTimeMillis();
    Object localObject = az.a(paramContext);
    long l2 = ((az)localObject).a(u.e);
    long l3 = ((az)localObject).i();
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("now time: ");
    ((StringBuilder)localObject).append(l1);
    ((StringBuilder)localObject).append(": last time: ");
    ((StringBuilder)localObject).append(l2);
    ((StringBuilder)localObject).append("; time interval: ");
    ((StringBuilder)localObject).append(l3);
    bd.a(((StringBuilder)localObject).toString());
    if ((l2 == 0L) || (l1 - l2 > l3))
    {
      bd.a("collectAPPTraceWithStretegy 2");
      n.b(paramContext, false);
    }
  }
  
  private void f(Context paramContext)
  {
    bd.a("collectAPKWithStretegy 1");
    long l1 = System.currentTimeMillis();
    Object localObject = az.a(paramContext);
    long l2 = ((az)localObject).a(u.g);
    long l3 = ((az)localObject).h();
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("now time: ");
    ((StringBuilder)localObject).append(l1);
    ((StringBuilder)localObject).append(": last time: ");
    ((StringBuilder)localObject).append(l2);
    ((StringBuilder)localObject).append("; interval : ");
    ((StringBuilder)localObject).append(l3);
    bd.a(((StringBuilder)localObject).toString());
    if ((l2 == 0L) || (l1 - l2 > l3))
    {
      bd.a("collectAPKWithStretegy 2");
      n.b(paramContext);
    }
  }
  
  private void g(Context paramContext)
  {
    az.a(paramContext).a(u.h, System.currentTimeMillis());
    JSONObject localJSONObject = v.a(paramContext);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("header: ");
    localStringBuilder.append(localJSONObject);
    bd.a(localStringBuilder.toString());
    while (a()) {
      b(paramContext, localJSONObject);
    }
  }
  
  public void a(Context paramContext, long paramLong)
  {
    az.a(paramContext).a(u.i, paramLong);
  }
  
  public void a(Context paramContext, String paramString)
  {
    az.a(paramContext).a(paramString);
  }
  
  public void a(Context paramContext, JSONObject paramJSONObject)
  {
    bd.a("startDataAnynalyzed start");
    a(paramJSONObject);
    paramJSONObject = az.a(paramContext);
    boolean bool = paramJSONObject.a();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("is data collect closed:");
    localStringBuilder.append(bool);
    bd.a(localStringBuilder.toString());
    if (!bool)
    {
      if (!y.a.b(10000)) {
        c(paramContext);
      }
      if (!y.b.b(10000)) {
        d(paramContext);
      }
      if (!y.c.b(10000)) {
        e(paramContext);
      }
      if ((bc.e) && (!y.e.b(10000))) {
        f(paramContext);
      }
      bool = dc.n(paramContext);
      if ((bool) && (paramJSONObject.l()))
      {
        bd.a("sendLog");
        g(paramContext);
      }
      else
      {
        if (!bool) {}
        for (paramContext = "isWifiAvailable = false, will not sendLog";; paramContext = "can not sendLog due to time stratergy")
        {
          bd.a(paramContext);
          break;
        }
      }
    }
    bd.a("startDataAnynalyzed finished");
  }
  
  public boolean a(Context paramContext)
  {
    paramContext = az.a(paramContext);
    long l1 = paramContext.a(u.i);
    long l2 = paramContext.c();
    long l3 = System.currentTimeMillis();
    if (l3 - l1 > l2)
    {
      paramContext = new StringBuilder();
      paramContext.append("need to update, checkWithLastUpdateTime lastUpdateTime =");
      paramContext.append(l1);
      paramContext.append("nowTime=");
      paramContext.append(l3);
      paramContext.append(";timeInteveral=");
      paramContext.append(l2);
      bd.a(paramContext.toString());
      return true;
    }
    paramContext = new StringBuilder();
    paramContext.append("no need to update, checkWithLastUpdateTime lastUpdateTime =");
    paramContext.append(l1);
    paramContext.append("nowTime=");
    paramContext.append(l3);
    paramContext.append(";timeInteveral=");
    paramContext.append(l2);
    bd.a(paramContext.toString());
    return false;
  }
  
  public void b(Context paramContext, String paramString)
  {
    az.a(paramContext).b(paramString);
  }
  
  public boolean b(Context paramContext)
  {
    return (!az.a(paramContext).a()) || (a(paramContext));
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\ba.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */