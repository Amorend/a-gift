package com.tencent.connect.auth;

import com.tencent.tauth.IUiListener;
import java.util.HashMap;

public class b
{
  public static b a;
  private static int e;
  public HashMap<String, a> b = new HashMap();
  public final String c = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  
  static
  {
    if (!b.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      d = bool;
      e = 0;
      return;
    }
  }
  
  public static b a()
  {
    if (a == null) {
      a = new b();
    }
    return a;
  }
  
  public static int b()
  {
    int i = e + 1;
    e = i;
    return i;
  }
  
  public String a(a parama)
  {
    int i = b();
    try
    {
      this.b.put("" + i, parama);
      return "" + i;
    }
    catch (Throwable parama)
    {
      for (;;)
      {
        parama.printStackTrace();
      }
    }
  }
  
  public String c()
  {
    int j = (int)Math.ceil(Math.random() * 20.0D + 3.0D);
    char[] arrayOfChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    int k = arrayOfChar.length;
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (i < j)
    {
      localStringBuffer.append(arrayOfChar[((int)(Math.random() * k))]);
      i += 1;
    }
    return localStringBuffer.toString();
  }
  
  public static class a
  {
    public IUiListener a;
    public a b;
    public String c;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\connect\auth\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */