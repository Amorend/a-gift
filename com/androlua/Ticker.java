package com.androlua;

import android.os.Handler;
import android.os.Message;

public class Ticker
{
  private Handler a;
  private OnTickListener b;
  private Thread c;
  private long d = 1000L;
  private boolean e = true;
  private boolean f = false;
  private long g;
  private long h;
  
  public Ticker()
  {
    a();
  }
  
  private void a()
  {
    this.a = new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (Ticker.a(Ticker.this) != null) {
          Ticker.a(Ticker.this).onTick();
        }
      }
    };
    this.c = new Thread()
    {
      public void run()
      {
        Ticker.a(Ticker.this, true);
        for (;;)
        {
          if (Ticker.b(Ticker.this))
          {
            long l = System.currentTimeMillis();
            if (!Ticker.c(Ticker.this)) {
              Ticker.a(Ticker.this, l - Ticker.d(Ticker.this));
            }
            if (l - Ticker.e(Ticker.this) >= Ticker.f(Ticker.this))
            {
              Ticker.a(Ticker.this, l);
              Ticker.g(Ticker.this).sendEmptyMessage(0);
            }
          }
          try
          {
            sleep(1L);
          }
          catch (InterruptedException localInterruptedException) {}
          return;
        }
      }
    };
  }
  
  public boolean getEnabled()
  {
    return this.e;
  }
  
  public long getInterval()
  {
    return this.d;
  }
  
  public long getPeriod()
  {
    return this.d;
  }
  
  public boolean isRun()
  {
    return this.f;
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    this.e = paramBoolean;
    if (!paramBoolean) {
      this.h = (System.currentTimeMillis() - this.g);
    }
  }
  
  public void setInterval(long paramLong)
  {
    this.g = System.currentTimeMillis();
    this.d = paramLong;
  }
  
  public void setOnTickListener(OnTickListener paramOnTickListener)
  {
    this.b = paramOnTickListener;
  }
  
  public void setPeriod(long paramLong)
  {
    this.g = System.currentTimeMillis();
    this.d = paramLong;
  }
  
  public void start()
  {
    this.c.start();
  }
  
  public void stop()
  {
    this.f = false;
  }
  
  public static abstract interface OnTickListener
  {
    public abstract void onTick();
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\Ticker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */