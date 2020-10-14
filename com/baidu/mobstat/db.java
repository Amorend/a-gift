package com.baidu.mobstat;

import android.net.LocalServerSocket;

public final class db
{
  private LocalServerSocket a;
  
  /* Error */
  public final boolean a()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 16	com/baidu/mobstat/db:a	Landroid/net/LocalServerSocket;
    //   6: ifnonnull +27 -> 33
    //   9: aload_0
    //   10: new 18	android/net/LocalServerSocket
    //   13: dup
    //   14: ldc 20
    //   16: invokespecial 23	android/net/LocalServerSocket:<init>	(Ljava/lang/String;)V
    //   19: putfield 16	com/baidu/mobstat/db:a	Landroid/net/LocalServerSocket;
    //   22: iconst_1
    //   23: istore_1
    //   24: aload_0
    //   25: monitorexit
    //   26: iload_1
    //   27: ireturn
    //   28: astore_2
    //   29: aload_0
    //   30: monitorexit
    //   31: aload_2
    //   32: athrow
    //   33: iconst_0
    //   34: istore_1
    //   35: goto -11 -> 24
    //   38: astore_2
    //   39: goto -6 -> 33
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	42	0	this	db
    //   23	12	1	bool	boolean
    //   28	4	2	localObject	Object
    //   38	1	2	localIOException	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   2	22	28	finally
    //   2	22	38	java/io/IOException
  }
  
  /* Error */
  public final void b()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 16	com/baidu/mobstat/db:a	Landroid/net/LocalServerSocket;
    //   6: astore_1
    //   7: aload_1
    //   8: ifnull +15 -> 23
    //   11: aload_0
    //   12: getfield 16	com/baidu/mobstat/db:a	Landroid/net/LocalServerSocket;
    //   15: invokevirtual 27	android/net/LocalServerSocket:close	()V
    //   18: aload_0
    //   19: aconst_null
    //   20: putfield 16	com/baidu/mobstat/db:a	Landroid/net/LocalServerSocket;
    //   23: aload_0
    //   24: monitorexit
    //   25: return
    //   26: astore_1
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_1
    //   30: athrow
    //   31: astore_1
    //   32: goto -9 -> 23
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	35	0	this	db
    //   6	2	1	localLocalServerSocket	LocalServerSocket
    //   26	4	1	localObject	Object
    //   31	1	1	localIOException	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   2	7	26	finally
    //   11	23	26	finally
    //   11	23	31	java/io/IOException
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\db.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */