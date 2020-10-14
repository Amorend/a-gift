package com.baidu.mobstat;

import android.content.Context;

public class au
{
  private static l a;
  
  public static l a(Context paramContext)
  {
    try
    {
      bd.a("getBPStretegyController begin");
      l locall = a;
      Object localObject1 = locall;
      if (locall == null) {
        try
        {
          Object localObject3 = ax.a(paramContext, "com.baidu.bottom.remote.BPStretegyController2");
          localObject1 = locall;
          if (localObject3 != null)
          {
            localObject1 = new aw(((Class)localObject3).newInstance());
            try
            {
              bd.a("Get BPStretegyController load remote class v2");
            }
            catch (Exception localException1)
            {
              localObject3 = localException1;
            }
            bd.a(localException2);
          }
        }
        catch (Exception localException2)
        {
          localObject1 = localException1;
        }
      }
      Object localObject2 = localObject1;
      if (localObject1 == null)
      {
        localObject2 = new av();
        bd.a("Get BPStretegyController load local class");
      }
      a = (l)localObject2;
      ax.a(paramContext, (l)localObject2);
      bd.a("getBPStretegyController end");
      return (l)localObject2;
    }
    finally {}
  }
  
  public static void a()
  {
    try
    {
      a = null;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\au.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */