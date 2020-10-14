package com.tencent.open.a;

import android.os.Environment;
import android.text.TextUtils;
import java.io.File;

public class f
{
  public static f a = null;
  protected static final b c;
  private static boolean d = false;
  protected a b = new a(c);
  
  static
  {
    int i = c.m;
    long l = c.n;
    c = new b(c(), i, c.g, c.h, c.c, c.i, 10, c.e, l);
  }
  
  public static f a()
  {
    if (a == null) {}
    try
    {
      if (a == null)
      {
        a = new f();
        d = true;
      }
      return a;
    }
    finally {}
  }
  
  public static final void a(String paramString1, String paramString2)
  {
    a().a(1, paramString1, paramString2, null);
  }
  
  public static final void a(String paramString1, String paramString2, Throwable paramThrowable)
  {
    a().a(2, paramString1, paramString2, paramThrowable);
  }
  
  public static void b()
  {
    try
    {
      a().d();
      if (a != null) {
        a = null;
      }
      return;
    }
    finally {}
  }
  
  public static final void b(String paramString1, String paramString2)
  {
    a().a(2, paramString1, paramString2, null);
  }
  
  public static final void b(String paramString1, String paramString2, Throwable paramThrowable)
  {
    a().a(16, paramString1, paramString2, paramThrowable);
  }
  
  protected static File c()
  {
    int j = 0;
    String str = c.d;
    try
    {
      d.c localc = d.b.b();
      i = j;
      if (localc != null)
      {
        long l1 = localc.c();
        long l2 = c.f;
        i = j;
        if (l1 > l2) {
          i = 1;
        }
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        localThrowable.printStackTrace();
        int i = j;
      }
    }
    if (i != 0) {
      return new File(Environment.getExternalStorageDirectory(), str);
    }
    return new File(com.tencent.open.utils.e.c(), str);
  }
  
  public static final void c(String paramString1, String paramString2)
  {
    a().a(4, paramString1, paramString2, null);
  }
  
  public static final void d(String paramString1, String paramString2)
  {
    a().a(8, paramString1, paramString2, null);
  }
  
  public static final void e(String paramString1, String paramString2)
  {
    a().a(16, paramString1, paramString2, null);
  }
  
  protected void a(int paramInt, String paramString1, String paramString2, Throwable paramThrowable)
  {
    String str;
    if (d)
    {
      str = com.tencent.open.utils.e.b();
      if (!TextUtils.isEmpty(str)) {
        break label54;
      }
    }
    for (;;)
    {
      e.a.b(paramInt, Thread.currentThread(), System.currentTimeMillis(), paramString1, paramString2, paramThrowable);
      if (!d.a.a(c.b, paramInt)) {
        return;
      }
      if (this.b != null) {
        break;
      }
      return;
      label54:
      str = str + " SDK_VERSION:" + "3.3.0.lite";
      if (this.b == null) {
        return;
      }
      e.a.b(32, Thread.currentThread(), System.currentTimeMillis(), "openSDK_LOG", str, null);
      this.b.b(32, Thread.currentThread(), System.currentTimeMillis(), "openSDK_LOG", str, null);
      d = false;
    }
    this.b.b(paramInt, Thread.currentThread(), System.currentTimeMillis(), paramString1, paramString2, paramThrowable);
  }
  
  protected void d()
  {
    if (this.b != null)
    {
      this.b.a();
      this.b.b();
      this.b = null;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\a\f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */