package com.baidu.mobstat;

import android.content.Context;
import java.io.File;
import java.util.Arrays;

class cc
  implements Runnable
{
  cc(by paramby, Context paramContext) {}
  
  public void run()
  {
    try
    {
      Object localObject = this.a.getFilesDir();
      if (localObject != null)
      {
        if (!((File)localObject).exists()) {
          return;
        }
        localObject = ((File)localObject).list(new cd(this));
        if (localObject != null)
        {
          int i = localObject.length;
          if (i == 0) {
            return;
          }
          try
          {
            Arrays.sort((Object[])localObject, new ce(this));
          }
          catch (Exception localException2)
          {
            cz.b(localException2);
          }
          int m = localObject.length;
          int j = 0;
          i = 0;
          while (j < m)
          {
            String str1 = localObject[j];
            String str2 = cs.a(this.a, str1);
            if (by.a(this.b, this.a, str2))
            {
              cs.b(this.a, str1);
              i = 0;
            }
            else
            {
              int k = i + 1;
              i = k;
              if (k >= 5) {
                return;
              }
            }
            j += 1;
          }
        }
      }
      return;
    }
    catch (Exception localException1)
    {
      cz.b(localException1);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\cc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */