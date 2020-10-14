package com.tencent.open.a;

public abstract class i
{
  private volatile int a = c.a;
  private volatile boolean b = true;
  private h c = h.a;
  
  public i()
  {
    this(c.a, true, h.a);
  }
  
  public i(int paramInt, boolean paramBoolean, h paramh)
  {
    a(paramInt);
    a(paramBoolean);
    a(paramh);
  }
  
  public void a(int paramInt)
  {
    this.a = paramInt;
  }
  
  protected abstract void a(int paramInt, Thread paramThread, long paramLong, String paramString1, String paramString2, Throwable paramThrowable);
  
  public void a(h paramh)
  {
    this.c = paramh;
  }
  
  public void a(boolean paramBoolean)
  {
    this.b = paramBoolean;
  }
  
  public void b(int paramInt, Thread paramThread, long paramLong, String paramString1, String paramString2, Throwable paramThrowable)
  {
    if ((d()) && (d.a.a(this.a, paramInt))) {
      a(paramInt, paramThread, paramLong, paramString1, paramString2, paramThrowable);
    }
  }
  
  public boolean d()
  {
    return this.b;
  }
  
  public h e()
  {
    return this.c;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\a\i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */