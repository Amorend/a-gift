package com.baidu.mobstat;

import android.content.Context;
import android.content.Intent;
import com.baidu.bottom.service.BottomReceiver;

public class at
  extends Thread
{
  public at(BottomReceiver paramBottomReceiver, Context paramContext, Intent paramIntent, db paramdb) {}
  
  public void run()
  {
    try
    {
      try
      {
        BottomReceiver.a(this.d, this.a, this.b);
        BottomReceiver.b(this.d, this.a, this.b);
        long l = System.currentTimeMillis();
        if (l - BottomReceiver.a() < 30000L)
        {
          bd.a("No need to handle receiver due to time strategy");
          return;
        }
        BottomReceiver.a(l);
        ao.c.a(this.a);
      }
      finally
      {
        this.c.b();
        BottomReceiver.a(null);
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    this.c.b();
    BottomReceiver.a(null);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\at.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */