package com.baidu.mobstat;

import android.system.ErrnoException;
import android.system.Os;

class k
{
  static boolean a(String paramString, int paramInt)
  {
    try
    {
      Os.chmod(paramString, paramInt);
      return true;
    }
    catch (ErrnoException paramString)
    {
      g.a(paramString);
    }
    return false;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\k.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */