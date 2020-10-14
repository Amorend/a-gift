package com.androlua.util;

public class VolatileBox<T>
{
  private volatile T a;
  
  public VolatileBox() {}
  
  public VolatileBox(T paramT)
  {
    set(paramT);
  }
  
  /* Error */
  public T blockedGet()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 26	java/lang/Object:wait	()V
    //   6: aload_0
    //   7: monitorexit
    //   8: aload_0
    //   9: getfield 28	com/androlua/util/VolatileBox:a	Ljava/lang/Object;
    //   12: areturn
    //   13: astore_1
    //   14: goto +13 -> 27
    //   17: astore_1
    //   18: new 30	java/lang/RuntimeException
    //   21: dup
    //   22: aload_1
    //   23: invokespecial 33	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   26: athrow
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_1
    //   30: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	31	0	this	VolatileBox
    //   13	1	1	localObject	Object
    //   17	13	1	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   2	6	13	finally
    //   6	8	13	finally
    //   18	27	13	finally
    //   27	29	13	finally
    //   2	6	17	java/lang/InterruptedException
  }
  
  /* Error */
  public T blockedGetOrThrow(Class<? extends RuntimeException> paramClass)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 26	java/lang/Object:wait	()V
    //   6: aload_0
    //   7: monitorexit
    //   8: aload_0
    //   9: getfield 28	com/androlua/util/VolatileBox:a	Ljava/lang/Object;
    //   12: areturn
    //   13: astore_1
    //   14: goto +31 -> 45
    //   17: aload_1
    //   18: invokevirtual 45	java/lang/Class:newInstance	()Ljava/lang/Object;
    //   21: checkcast 30	java/lang/RuntimeException
    //   24: athrow
    //   25: astore_1
    //   26: new 30	java/lang/RuntimeException
    //   29: dup
    //   30: aload_1
    //   31: invokespecial 33	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   34: athrow
    //   35: astore_1
    //   36: new 30	java/lang/RuntimeException
    //   39: dup
    //   40: aload_1
    //   41: invokespecial 33	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   44: athrow
    //   45: aload_0
    //   46: monitorexit
    //   47: aload_1
    //   48: athrow
    //   49: astore_2
    //   50: goto -33 -> 17
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	53	0	this	VolatileBox
    //   0	53	1	paramClass	Class<? extends RuntimeException>
    //   49	1	2	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   2	6	13	finally
    //   6	8	13	finally
    //   17	25	13	finally
    //   26	35	13	finally
    //   36	45	13	finally
    //   45	47	13	finally
    //   17	25	25	java/lang/IllegalAccessException
    //   17	25	35	java/lang/InstantiationException
    //   2	6	49	java/lang/InterruptedException
  }
  
  public T get()
  {
    return (T)this.a;
  }
  
  public boolean isNull()
  {
    return this.a == null;
  }
  
  public boolean notNull()
  {
    return this.a != null;
  }
  
  public void set(T paramT)
  {
    this.a = paramT;
  }
  
  public void setAndNotify(T paramT)
  {
    this.a = paramT;
    try
    {
      notify();
      return;
    }
    finally {}
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\util\VolatileBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */