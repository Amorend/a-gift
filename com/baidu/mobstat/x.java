package com.baidu.mobstat;

import android.content.ContentValues;
import android.database.Cursor;
import java.io.Closeable;
import java.util.ArrayList;

abstract class x
  implements Closeable
{
  private af a;
  
  public x(String paramString1, String paramString2)
  {
    ae localae = new ae();
    this.a = new af(localae, paramString1);
    if (localae.getDatabasePath(".confd") != null) {
      a(paramString2);
    }
  }
  
  private void a(String paramString)
  {
    this.a.a(paramString);
  }
  
  protected long a(ContentValues paramContentValues)
  {
    return this.a.a(null, paramContentValues);
  }
  
  public abstract long a(String paramString1, String paramString2);
  
  protected Cursor a(String paramString, int paramInt1, int paramInt2)
  {
    af localaf = this.a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(" desc");
    paramString = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramInt2);
    localStringBuilder.append(", ");
    localStringBuilder.append(paramInt1);
    return localaf.a(null, null, null, null, null, paramString, localStringBuilder.toString());
  }
  
  protected Cursor a(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(paramString1);
    ((StringBuilder)localObject1).append("=? ");
    paramString1 = ((StringBuilder)localObject1).toString();
    localObject1 = this.a;
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(paramString3);
    ((StringBuilder)localObject2).append(" desc");
    paramString3 = ((StringBuilder)localObject2).toString();
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(paramInt);
    ((StringBuilder)localObject2).append("");
    localObject2 = ((StringBuilder)localObject2).toString();
    return ((af)localObject1).a(null, paramString1, new String[] { paramString2 }, null, null, paramString3, (String)localObject2);
  }
  
  public abstract ArrayList<w> a(int paramInt1, int paramInt2);
  
  /* Error */
  public boolean a()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 23	com/baidu/mobstat/x:a	Lcom/baidu/mobstat/af;
    //   6: invokevirtual 74	com/baidu/mobstat/af:a	()Z
    //   9: istore_1
    //   10: aload_0
    //   11: monitorexit
    //   12: iload_1
    //   13: ireturn
    //   14: astore_2
    //   15: goto +12 -> 27
    //   18: astore_2
    //   19: aload_2
    //   20: invokestatic 80	com/baidu/mobstat/bd:b	(Ljava/lang/Throwable;)V
    //   23: aload_0
    //   24: monitorexit
    //   25: iconst_0
    //   26: ireturn
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_2
    //   30: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	31	0	this	x
    //   9	4	1	bool	boolean
    //   14	1	2	localObject	Object
    //   18	12	2	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   2	10	14	finally
    //   19	23	14	finally
    //   2	10	18	java/lang/Exception
  }
  
  protected boolean a(long paramLong)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramLong);
    ((StringBuilder)localObject).append("");
    localObject = ((StringBuilder)localObject).toString();
    return this.a.a("_id=? ", new String[] { localObject }) > 0;
  }
  
  protected int b()
  {
    return this.a.b();
  }
  
  public abstract boolean b(long paramLong);
  
  /* Error */
  public void close()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 23	com/baidu/mobstat/x:a	Lcom/baidu/mobstat/af;
    //   6: invokevirtual 95	com/baidu/mobstat/af:close	()V
    //   9: goto +12 -> 21
    //   12: astore_1
    //   13: goto +11 -> 24
    //   16: astore_1
    //   17: aload_1
    //   18: invokestatic 80	com/baidu/mobstat/bd:b	(Ljava/lang/Throwable;)V
    //   21: aload_0
    //   22: monitorexit
    //   23: return
    //   24: aload_0
    //   25: monitorexit
    //   26: aload_1
    //   27: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	28	0	this	x
    //   12	1	1	localObject	Object
    //   16	11	1	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   2	9	12	finally
    //   17	21	12	finally
    //   2	9	16	java/lang/Exception
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\x.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */