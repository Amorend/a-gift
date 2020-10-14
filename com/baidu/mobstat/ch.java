package com.baidu.mobstat;

import android.a.a.a.a;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.json.JSONObject;

class ch
{
  private static final ch a = new ch();
  private static HashMap<String, cl> n = new HashMap();
  private cm b = new cm();
  private cm c = new cm();
  private cm d = new cm();
  private cm e = new cm();
  private long f = 0L;
  private boolean g = true;
  private boolean h;
  private cf i = new cf();
  private int j = -1;
  private volatile int k;
  private volatile long l;
  private Handler m;
  
  private ch()
  {
    HandlerThread localHandlerThread = new HandlerThread("SessionAnalysisThread");
    localHandlerThread.start();
    localHandlerThread.setPriority(10);
    this.m = new Handler(localHandlerThread.getLooper());
  }
  
  static Context a(Object paramObject)
  {
    try
    {
      paramObject = (Context)paramObject.getClass().getMethod("getActivity", new Class[0]).invoke(paramObject, new Object[0]);
      return (Context)paramObject;
    }
    catch (Throwable paramObject)
    {
      cz.a(((Throwable)paramObject).getMessage());
    }
    return null;
  }
  
  public static ch a()
  {
    return a;
  }
  
  private void a(String paramString)
  {
    HashMap localHashMap = n;
    if (paramString == null) {}
    try
    {
      cz.c("page Object is null");
      return;
    }
    finally
    {
      cl localcl;
      for (;;) {}
    }
    localcl = new cl(paramString);
    if (!n.containsKey(paramString)) {
      n.put(paramString, localcl);
    }
    return;
    throw paramString;
  }
  
  private void a(boolean paramBoolean)
  {
    this.g = paramBoolean;
  }
  
  private cl b(String paramString)
  {
    synchronized (n)
    {
      if (!n.containsKey(paramString)) {
        a(paramString);
      }
      paramString = (cl)n.get(paramString);
      return paramString;
    }
  }
  
  private void b(Context paramContext)
  {
    String str = this.i.c().toString();
    this.k = str.getBytes().length;
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("cacheString = ");
    ((StringBuilder)localObject).append(str);
    cz.a(((StringBuilder)localObject).toString());
    localObject = dc.q(paramContext);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append((String)localObject);
    localStringBuilder.append("__local_last_session.json");
    cs.a(paramContext, localStringBuilder.toString(), str, false);
  }
  
  private void c(String paramString)
  {
    HashMap localHashMap = n;
    if (paramString == null) {}
    try
    {
      cz.c("pageName is null");
      return;
    }
    finally
    {
      for (;;) {}
    }
    if (n.containsKey(paramString)) {
      n.remove(paramString);
    }
    return;
    throw paramString;
  }
  
  private boolean h()
  {
    return this.g;
  }
  
  private int i()
  {
    try
    {
      localClass1 = Class.forName("android.app.Fragment");
    }
    catch (ClassNotFoundException localClassNotFoundException1)
    {
      Class localClass1;
      for (;;) {}
    }
    localClass1 = null;
    try
    {
      localClass2 = Class.forName("android.a.a.a.a");
    }
    catch (ClassNotFoundException localClassNotFoundException2)
    {
      Class localClass2;
      StackTraceElement[] arrayOfStackTraceElement;
      int i1;
      for (;;) {}
    }
    localClass2 = null;
    arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
    i1 = 0;
    while (i1 < arrayOfStackTraceElement.length)
    {
      Object localObject = arrayOfStackTraceElement[i1].getClassName();
      String str = arrayOfStackTraceElement[i1].getMethodName();
      if ((!TextUtils.isEmpty((CharSequence)localObject)) && (!TextUtils.isEmpty(str)) && (str.equals("onResume"))) {}
      try
      {
        localObject = Class.forName((String)localObject);
      }
      catch (Throwable localThrowable)
      {
        for (;;) {}
      }
      localObject = null;
      if (localObject != null)
      {
        if (Activity.class.isAssignableFrom((Class)localObject)) {
          return 1;
        }
        if ((localClass1 != null) && (localClass1.isAssignableFrom((Class)localObject))) {
          return 2;
        }
        if ((localClass2 != null) && (localClass2.isAssignableFrom((Class)localObject))) {
          return 2;
        }
      }
      i1 += 1;
    }
    return 3;
  }
  
  private void j()
  {
    boolean bool = h();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("isFirstResume:");
    localStringBuilder.append(bool);
    cz.a(localStringBuilder.toString());
    if (bool)
    {
      a(false);
      this.m.post(new ck(this));
    }
  }
  
  public void a(int paramInt)
  {
    this.j = (paramInt * 1000);
  }
  
  public void a(long paramLong)
  {
    this.i.a(paramLong);
  }
  
  public void a(a parama, long paramLong)
  {
    cz.a("post resume job");
    if (this.c.c) {
      cz.c("遗漏StatService.onPause() || missing StatService.onPause()");
    }
    this.c.c = true;
    j();
    co localco = new co(this, this.f, paramLong, this.l, null, parama, null, 2, 2);
    this.m.post(localco);
    this.c.b = new WeakReference(parama);
    this.c.a = paramLong;
  }
  
  @TargetApi(11)
  public void a(Fragment paramFragment, long paramLong)
  {
    cz.a("post resume job");
    if (this.d.c) {
      cz.c("遗漏StatService.onPause() || missing StatService.onPause()");
    }
    this.d.c = true;
    j();
    co localco = new co(this, this.f, paramLong, this.l, null, null, paramFragment, 2, 3);
    this.m.post(localco);
    this.d.b = new WeakReference(paramFragment);
    this.d.a = paramLong;
  }
  
  public void a(Context paramContext)
  {
    if (paramContext == null)
    {
      cz.a("clearLastSession context is null, invalid");
      return;
    }
    String str1 = new JSONObject().toString();
    String str2 = dc.q(paramContext);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(str2);
    localStringBuilder.append("__local_last_session.json");
    cs.a(paramContext, localStringBuilder.toString(), str1, false);
  }
  
  public void a(Context paramContext, long paramLong)
  {
    if (this.l == 0L)
    {
      paramContext = new ci(this, paramLong);
      this.m.post(paramContext);
    }
    this.l = paramLong;
  }
  
  public void a(Context paramContext, long paramLong, String paramString)
  {
    cz.a("AnalysisPageStart");
    if (TextUtils.isEmpty(paramString))
    {
      cz.c("自定义页面 pageName 为 null");
      return;
    }
    paramString = b(paramString);
    if (paramString == null)
    {
      cz.c("get page info, PageInfo null");
      return;
    }
    if (paramString.b) {
      cz.c("遗漏StatService.onPageEnd() || missing StatService.onPageEnd()");
    }
    paramString.b = true;
    paramString.c = paramLong;
    j();
    if (!this.h)
    {
      paramString = new co(this, this.f, paramLong, this.l, paramContext, null, null, i(), 1);
      this.m.post(paramString);
      this.h = true;
    }
    this.b.b = new WeakReference(paramContext);
    this.b.a = paramLong;
  }
  
  public void a(Context paramContext, long paramLong, String paramString1, String paramString2, ExtraInfo paramExtraInfo)
  {
    cz.a("post pause job");
    this.h = false;
    if (TextUtils.isEmpty(paramString2))
    {
      cz.c("自定义页面 pageName 无效值");
      return;
    }
    cl localcl = b(paramString2);
    if (localcl == null)
    {
      cz.c("get page info, PageInfo null");
      return;
    }
    if (!localcl.b)
    {
      cz.c("Please check (1)遗漏StatService.onPageStart() || missing StatService.onPageStart()");
      return;
    }
    localcl.b = false;
    localcl.d = paramLong;
    paramContext = new cn(this, paramLong, paramContext, null, localcl.c, (Context)this.b.b.get(), null, 1, paramString2, null, null, paramString1, false, paramExtraInfo, localcl);
    this.m.post(paramContext);
    c(paramString2);
    this.f = paramLong;
  }
  
  public void a(Context paramContext, long paramLong, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.e.c = true;
      this.e.b = new WeakReference(paramContext);
      this.e.a = paramLong;
    }
    cz.a("AnalysisResume job");
    if ((!paramBoolean) && (this.b.c)) {
      cz.c("遗漏StatService.onPause() || missing StatService.onPause()");
    }
    if (!paramBoolean) {
      this.b.c = true;
    }
    j();
    if (!this.h)
    {
      co localco = new co(this, this.f, paramLong, this.l, paramContext, null, null, 1, 1);
      this.m.post(localco);
      this.h = true;
    }
    this.b.b = new WeakReference(paramContext);
    this.b.a = paramLong;
  }
  
  public void a(Context paramContext, long paramLong, boolean paramBoolean, ExtraInfo paramExtraInfo)
  {
    cz.a("post pause job");
    this.h = false;
    if (paramBoolean)
    {
      this.e.c = false;
      paramContext = new cn(this, paramLong, paramContext, null, this.e.a, (Context)this.e.b.get(), null, 1, null, null, null, null, paramBoolean, paramExtraInfo, null);
      this.m.post(paramContext);
      this.f = paramLong;
      return;
    }
    if (!this.b.c)
    {
      cz.c("遗漏StatService.onResume() || missing StatService.onResume()");
      return;
    }
    this.b.c = false;
    paramContext = new cn(this, paramLong, paramContext, null, this.b.a, (Context)this.b.b.get(), null, 1, null, null, null, null, paramBoolean, paramExtraInfo, null);
    this.m.post(paramContext);
    this.f = paramLong;
  }
  
  public int b()
  {
    return this.k;
  }
  
  public void b(int paramInt)
  {
    this.i.b(paramInt);
  }
  
  public void b(long paramLong)
  {
    this.i.b(paramLong);
  }
  
  public void b(a parama, long paramLong)
  {
    cz.a("post pause job");
    if (!this.c.c)
    {
      cz.c("遗漏android.support.v4.app.Fragment StatService.onResume() || android.support.v4.app.Fragment missing StatService.onResume()");
      return;
    }
    this.c.c = false;
    parama = new cn(this, paramLong, null, parama, this.c.a, null, (a)this.c.b.get(), 2, null, null, null, null, false, null, null);
    this.m.post(parama);
    this.f = paramLong;
  }
  
  @TargetApi(11)
  public void b(Fragment paramFragment, long paramLong)
  {
    cz.a("post pause job");
    if (!this.d.c)
    {
      cz.c("遗漏android.app.Fragment StatService.onResume() || android.app.Fragment missing StatService.onResume()");
      return;
    }
    this.d.c = false;
    paramFragment = new cn(this, paramLong, null, null, this.d.a, null, null, 3, null, this.d.b.get(), paramFragment, null, false, null, null);
    this.m.post(paramFragment);
    this.f = paramLong;
  }
  
  public void b(Context paramContext, long paramLong)
  {
    paramContext = new cj(this, paramLong, paramContext);
    this.m.post(paramContext);
  }
  
  public int c()
  {
    if (this.j == -1) {
      this.j = 30000;
    }
    return this.j;
  }
  
  public void c(int paramInt)
  {
    this.i.a(paramInt);
  }
  
  public void d()
  {
    b(f() + 1);
  }
  
  public void e()
  {
    this.i.a();
  }
  
  public int f()
  {
    return this.i.d();
  }
  
  public long g()
  {
    return this.i.b();
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\ch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */