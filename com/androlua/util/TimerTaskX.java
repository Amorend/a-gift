package com.androlua.util;

public abstract class TimerTaskX
  implements Runnable
{
  private long a;
  final Object b = new Object();
  boolean c;
  long d;
  long e;
  boolean f;
  private boolean g;
  
  boolean a()
  {
    for (;;)
    {
      synchronized (this.b)
      {
        if (this.d > 0L) {
          break label42;
        }
        if (this.a > 0L)
        {
          break label42;
          return bool;
        }
      }
      boolean bool = false;
      continue;
      label42:
      bool = true;
    }
  }
  
  public boolean cancel()
  {
    for (;;)
    {
      synchronized (this.b)
      {
        if ((!this.c) && (this.d > 0L))
        {
          bool = true;
          this.c = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public long getPeriod()
  {
    return this.e;
  }
  
  public boolean isEnabled()
  {
    return this.g;
  }
  
  public abstract void run();
  
  public long scheduledExecutionTime()
  {
    synchronized (this.b)
    {
      long l = this.a;
      return l;
    }
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    this.g = paramBoolean;
  }
  
  public void setPeriod(long paramLong)
  {
    this.e = paramLong;
  }
  
  public void setScheduledTime(long paramLong)
  {
    synchronized (this.b)
    {
      this.a = paramLong;
      return;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\util\TimerTaskX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */