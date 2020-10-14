package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import java.text.SimpleDateFormat;
import org.json.JSONException;
import org.json.JSONObject;

public class az
{
  private static az a;
  private Context b;
  private JSONObject c = new JSONObject();
  private long d = 24L;
  private long e = 0L;
  private long f = 0L;
  private long g = 0L;
  private long h = 5L;
  private long i = 24L;
  private long j = 15L;
  private long k = 15L;
  private long l = 30L;
  private long m = 12L;
  private long n = 1L;
  private long o = 24L;
  private String p = "";
  private String q = "";
  
  private az(Context paramContext)
  {
    this.b = paramContext;
    m();
    j();
    k();
  }
  
  public static az a(Context paramContext)
  {
    if (a == null) {
      try
      {
        if (a == null) {
          a = new az(paramContext);
        }
      }
      finally {}
    }
    return a;
  }
  
  private long b(long paramLong)
  {
    long l1 = paramLong;
    if (paramLong - System.currentTimeMillis() > 0L) {
      l1 = 0L;
    }
    return l1;
  }
  
  private void m()
  {
    String str = cs.b("backups/system/.timestamp");
    try
    {
      if (!TextUtils.isEmpty(str)) {
        this.c = new JSONObject(str);
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  public long a(u paramu)
  {
    long l2 = paramu.j;
    long l1;
    try
    {
      paramu = paramu.toString();
      l1 = l2;
      if (this.c.has(paramu)) {
        l1 = this.c.getLong(paramu);
      }
    }
    catch (Exception paramu)
    {
      bd.a(paramu);
      l1 = l2;
    }
    return b(l1);
  }
  
  public void a(u paramu, long paramLong)
  {
    paramu.j = paramLong;
    try
    {
      this.c.put(paramu.toString(), paramLong);
    }
    catch (Exception paramu)
    {
      bd.a(paramu);
    }
    try
    {
      cs.a("backups/system/.timestamp", this.c.toString(), false);
      return;
    }
    catch (Exception paramu)
    {
      bd.a(paramu);
    }
  }
  
  public void a(String paramString)
  {
    cs.a(this.b, ".config2", paramString, false);
    j();
  }
  
  public boolean a()
  {
    return this.e != 0L;
  }
  
  public boolean a(long paramLong)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    return localSimpleDateFormat.format(Long.valueOf(paramLong)).equals(localSimpleDateFormat.format(Long.valueOf(System.currentTimeMillis())));
  }
  
  public void b(String paramString)
  {
    cs.a(this.b, ".sign", paramString, false);
    k();
  }
  
  public boolean b()
  {
    return this.f != 0L;
  }
  
  public long c()
  {
    return this.d * 60L * 60L * 1000L;
  }
  
  public String c(String paramString)
  {
    if ((!TextUtils.isEmpty(this.p)) && (this.p.equals(paramString)) && (!TextUtils.isEmpty(this.q))) {
      return this.q;
    }
    return "";
  }
  
  public long d()
  {
    return this.o * 60L * 60L * 1000L;
  }
  
  public long e()
  {
    return this.h * 60L * 1000L;
  }
  
  public long f()
  {
    return this.i * 60L * 60L * 1000L;
  }
  
  public long g()
  {
    return this.j * 24L * 60L * 60L * 1000L;
  }
  
  public long h()
  {
    return this.k * 24L * 60L * 60L * 1000L;
  }
  
  public long i()
  {
    return this.m * 60L * 60L * 1000L;
  }
  
  public void j()
  {
    Object localObject = cs.a(this.b, ".config2");
    try
    {
      localObject = new String(da.b(false, cu.a(), ct.a(((String)localObject).getBytes())));
      if (TextUtils.isEmpty((CharSequence)localObject)) {
        return;
      }
      localObject = new JSONObject((String)localObject);
      try
      {
        this.e = ((JSONObject)localObject).getLong("c");
      }
      catch (JSONException localJSONException2)
      {
        bd.b(localJSONException2);
      }
      try
      {
        this.h = ((JSONObject)localObject).getLong("d");
      }
      catch (JSONException localJSONException3)
      {
        bd.b(localJSONException3);
      }
      try
      {
        this.i = ((JSONObject)localObject).getLong("e");
      }
      catch (JSONException localJSONException4)
      {
        bd.b(localJSONException4);
      }
      try
      {
        this.j = ((JSONObject)localObject).getLong("i");
      }
      catch (JSONException localJSONException5)
      {
        bd.b(localJSONException5);
      }
      try
      {
        this.d = ((JSONObject)localObject).getLong("f");
      }
      catch (JSONException localJSONException6)
      {
        bd.b(localJSONException6);
      }
      try
      {
        this.o = ((JSONObject)localObject).getLong("s");
      }
      catch (JSONException localJSONException7)
      {
        bd.b(localJSONException7);
      }
      try
      {
        this.k = ((JSONObject)localObject).getLong("pk");
      }
      catch (JSONException localJSONException8)
      {
        bd.b(localJSONException8);
      }
      try
      {
        this.l = ((JSONObject)localObject).getLong("at");
      }
      catch (JSONException localJSONException9)
      {
        bd.b(localJSONException9);
      }
      try
      {
        this.m = ((JSONObject)localObject).getLong("as");
      }
      catch (JSONException localJSONException10)
      {
        bd.b(localJSONException10);
      }
      try
      {
        this.n = ((JSONObject)localObject).getLong("ac");
      }
      catch (JSONException localJSONException11)
      {
        bd.b(localJSONException11);
      }
      try
      {
        this.f = ((JSONObject)localObject).getLong("mc");
      }
      catch (JSONException localJSONException12)
      {
        bd.b(localJSONException12);
      }
      try
      {
        this.g = ((JSONObject)localObject).getLong("lsc");
        return;
      }
      catch (JSONException localJSONException1)
      {
        bd.b(localJSONException1);
        return;
      }
      return;
    }
    catch (Exception localException)
    {
      bd.b(localException);
    }
  }
  
  public void k()
  {
    Object localObject = cs.a(this.b, ".sign");
    try
    {
      localObject = new String(da.b(false, cu.a(), ct.a(((String)localObject).getBytes())));
      if (TextUtils.isEmpty((CharSequence)localObject)) {
        return;
      }
      localObject = new JSONObject((String)localObject);
      try
      {
        this.q = ((JSONObject)localObject).getString("sign");
      }
      catch (Exception localException3)
      {
        bd.b(localException3);
      }
      try
      {
        this.p = ((JSONObject)localObject).getString("ver");
        return;
      }
      catch (Exception localException1)
      {
        bd.b(localException1);
        return;
      }
      return;
    }
    catch (Exception localException2)
    {
      bd.b(localException2);
    }
  }
  
  public boolean l()
  {
    long l1 = System.currentTimeMillis();
    long l2 = a(u.h);
    long l3 = d();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("canSend now=");
    localStringBuilder.append(l1);
    localStringBuilder.append(";lastSendTime=");
    localStringBuilder.append(l2);
    localStringBuilder.append(";sendLogTimeInterval=");
    localStringBuilder.append(l3);
    bd.a(localStringBuilder.toString());
    return (l1 - l2 > l3) || (!a(l2));
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\az.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */