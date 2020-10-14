package com.androlua.util;

public class VolatileDispose<T>
{
  private volatile T a;
  
  /* Error */
  public T blockedGet()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 19	com/androlua/util/VolatileDispose:a	Ljava/lang/Object;
    //   6: ifnull +12 -> 18
    //   9: aload_0
    //   10: getfield 19	com/androlua/util/VolatileDispose:a	Ljava/lang/Object;
    //   13: astore_1
    //   14: aload_0
    //   15: monitorexit
    //   16: aload_1
    //   17: areturn
    //   18: aload_0
    //   19: ldc2_w 20
    //   22: invokevirtual 25	java/lang/Object:wait	(J)V
    //   25: aload_0
    //   26: monitorexit
    //   27: aload_0
    //   28: getfield 19	com/androlua/util/VolatileDispose:a	Ljava/lang/Object;
    //   31: areturn
    //   32: astore_1
    //   33: new 27	java/lang/RuntimeException
    //   36: dup
    //   37: aload_1
    //   38: invokespecial 30	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   41: athrow
    //   42: astore_1
    //   43: aload_0
    //   44: monitorexit
    //   45: aload_1
    //   46: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	47	0	this	VolatileDispose
    //   13	4	1	localObject1	Object
    //   32	6	1	localInterruptedException	InterruptedException
    //   42	4	1	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   18	25	32	java/lang/InterruptedException
    //   2	16	42	finally
    //   18	25	42	finally
    //   25	27	42	finally
    //   33	42	42	finally
    //   43	45	42	finally
  }
  
  public T blockedGetOrThrow(Class<? extends RuntimeException> paramClass)
  {
    for (;;)
    {
      try
      {
        if (this.a != null)
        {
          paramClass = this.a;
          return paramClass;
        }
      }
      finally {}
      try
      {
        wait();
        return (T)this.a;
      }
      catch (InterruptedException localInterruptedException)
      {
        continue;
      }
      try
      {
        throw ((RuntimeException)paramClass.newInstance());
      }
      catch (IllegalAccessException paramClass)
      {
        throw new RuntimeException(paramClass);
      }
      catch (InstantiationException paramClass)
      {
        throw new RuntimeException(paramClass);
      }
    }
  }
  
  public void setAndNotify(T paramT)
  {
    try
    {
      this.a = paramT;
      notify();
      return;
    }
    finally {}
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\util\VolatileDispose.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */