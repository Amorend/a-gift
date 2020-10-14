package com.a.a.b;

import android.util.Log;
import java.io.PrintStream;

public class r
  extends Exception
{
  public static void a(String paramString)
  {
    a(false, paramString);
  }
  
  public static void a(boolean paramBoolean, String paramString)
  {
    if (!paramBoolean)
    {
      System.err.print("TextWarrior assertion failed: ");
      System.err.println(paramString);
      Log.i("lua", paramString);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\a\a\b\r.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */