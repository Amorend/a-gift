package com.androlua;

import java.io.IOException;

public class ZipUtil
{
  public static boolean unzip(String paramString1, String paramString2)
  {
    try
    {
      LuaUtil.unZip(paramString1, paramString2);
      return true;
    }
    catch (IOException paramString1)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static boolean zip(String paramString1, String paramString2)
  {
    return LuaUtil.zip(paramString1, paramString2);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\ZipUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */