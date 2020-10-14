package com.tencent.open.a;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class a
  extends i
  implements Handler.Callback
{
  private b a;
  private FileWriter b;
  private File c;
  private char[] d;
  private volatile g e;
  private volatile g f;
  private volatile g g;
  private volatile g h;
  private volatile boolean i = false;
  private HandlerThread j;
  private Handler k;
  
  public a(int paramInt, boolean paramBoolean, h paramh, b paramb)
  {
    super(paramInt, paramBoolean, paramh);
    a(paramb);
    this.e = new g();
    this.f = new g();
    this.g = this.e;
    this.h = this.f;
    this.d = new char[paramb.d()];
    g();
    this.j = new HandlerThread(paramb.c(), paramb.f());
    if (this.j != null) {
      this.j.start();
    }
    if ((this.j.isAlive()) && (this.j.getLooper() != null)) {
      this.k = new Handler(this.j.getLooper(), this);
    }
  }
  
  public a(b paramb)
  {
    this(c.b, true, h.a, paramb);
  }
  
  private void f()
  {
    if (Thread.currentThread() != this.j) {
      return;
    }
    if (this.i) {
      return;
    }
    this.i = true;
    i();
    try
    {
      this.h.a(g(), this.d);
      this.h.b();
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException = localIOException;
        this.h.b();
      }
    }
    finally
    {
      localObject = finally;
      this.h.b();
      throw ((Throwable)localObject);
    }
    this.i = false;
  }
  
  private Writer g()
  {
    File localFile = c().a();
    if (((localFile != null) && (!localFile.equals(this.c))) || ((this.b == null) && (localFile != null)))
    {
      this.c = localFile;
      h();
    }
    try
    {
      this.b = new FileWriter(this.c, true);
      return this.b;
    }
    catch (IOException localIOException) {}
    return null;
  }
  
  private void h()
  {
    try
    {
      if (this.b != null)
      {
        this.b.flush();
        this.b.close();
      }
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }
  
  /* Error */
  private void i()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 46	com/tencent/open/a/a:g	Lcom/tencent/open/a/g;
    //   6: aload_0
    //   7: getfield 42	com/tencent/open/a/a:e	Lcom/tencent/open/a/g;
    //   10: if_acmpne +22 -> 32
    //   13: aload_0
    //   14: aload_0
    //   15: getfield 44	com/tencent/open/a/a:f	Lcom/tencent/open/a/g;
    //   18: putfield 46	com/tencent/open/a/a:g	Lcom/tencent/open/a/g;
    //   21: aload_0
    //   22: aload_0
    //   23: getfield 42	com/tencent/open/a/a:e	Lcom/tencent/open/a/g;
    //   26: putfield 48	com/tencent/open/a/a:h	Lcom/tencent/open/a/g;
    //   29: aload_0
    //   30: monitorexit
    //   31: return
    //   32: aload_0
    //   33: aload_0
    //   34: getfield 42	com/tencent/open/a/a:e	Lcom/tencent/open/a/g;
    //   37: putfield 46	com/tencent/open/a/a:g	Lcom/tencent/open/a/g;
    //   40: aload_0
    //   41: aload_0
    //   42: getfield 44	com/tencent/open/a/a:f	Lcom/tencent/open/a/g;
    //   45: putfield 48	com/tencent/open/a/a:h	Lcom/tencent/open/a/g;
    //   48: goto -19 -> 29
    //   51: astore_1
    //   52: aload_0
    //   53: monitorexit
    //   54: aload_1
    //   55: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	56	0	this	a
    //   51	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	29	51	finally
    //   29	31	51	finally
    //   32	48	51	finally
    //   52	54	51	finally
  }
  
  public void a()
  {
    if (this.k.hasMessages(1024)) {
      this.k.removeMessages(1024);
    }
    this.k.sendEmptyMessage(1024);
  }
  
  protected void a(int paramInt, Thread paramThread, long paramLong, String paramString1, String paramString2, Throwable paramThrowable)
  {
    a(e().a(paramInt, paramThread, paramLong, paramString1, paramString2, paramThrowable));
  }
  
  public void a(b paramb)
  {
    this.a = paramb;
  }
  
  protected void a(String paramString)
  {
    this.g.a(paramString);
    if (this.g.a() >= c().d()) {
      a();
    }
  }
  
  public void b()
  {
    h();
    this.j.quit();
  }
  
  public b c()
  {
    return this.a;
  }
  
  public boolean handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    }
    for (;;)
    {
      return true;
      f();
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\a\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */