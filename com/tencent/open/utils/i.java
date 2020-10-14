package com.tencent.open.utils;

import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class i
{
  public static final Executor a = c();
  private static Object b = new Object();
  private static Handler c;
  private static HandlerThread d;
  
  public static Handler a()
  {
    if (c == null) {}
    try
    {
      d = new HandlerThread("SDK_SUB");
      d.start();
      c = new Handler(d.getLooper());
      return c;
    }
    finally {}
  }
  
  public static void a(Runnable paramRunnable)
  {
    a().post(paramRunnable);
  }
  
  public static Executor b()
  {
    return new a(null);
  }
  
  private static Executor c()
  {
    Object localObject;
    if (Build.VERSION.SDK_INT >= 11) {
      localObject = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue());
    }
    for (;;)
    {
      if ((localObject instanceof ThreadPoolExecutor)) {
        ((ThreadPoolExecutor)localObject).setCorePoolSize(3);
      }
      return (Executor)localObject;
      try
      {
        localObject = AsyncTask.class.getDeclaredField("sExecutor");
        ((Field)localObject).setAccessible(true);
        localObject = (Executor)((Field)localObject).get(null);
      }
      catch (Exception localException)
      {
        for (;;)
        {
          ThreadPoolExecutor localThreadPoolExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        }
      }
    }
  }
  
  private static class a
    implements Executor
  {
    final Queue<Runnable> a = new LinkedList();
    Runnable b;
    
    protected void a()
    {
      try
      {
        Runnable localRunnable = (Runnable)this.a.poll();
        this.b = localRunnable;
        if (localRunnable != null) {
          i.a.execute(this.b);
        }
        return;
      }
      finally {}
    }
    
    public void execute(final Runnable paramRunnable)
    {
      try
      {
        this.a.offer(new Runnable()
        {
          public void run()
          {
            try
            {
              paramRunnable.run();
              return;
            }
            finally
            {
              i.a.this.a();
            }
          }
        });
        if (this.b == null) {
          a();
        }
        return;
      }
      finally
      {
        paramRunnable = finally;
        throw paramRunnable;
      }
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\utils\i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */