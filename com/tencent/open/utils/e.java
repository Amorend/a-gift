package com.tencent.open.utils;

import android.content.Context;
import java.io.File;

public final class e
{
  private static Context a;
  
  public static final Context a()
  {
    if (a == null) {
      return null;
    }
    return a;
  }
  
  public static final void a(Context paramContext)
  {
    a = paramContext;
  }
  
  public static final String b()
  {
    if (a() == null) {
      return "";
    }
    return a().getPackageName();
  }
  
  public static final File c()
  {
    if (a() == null) {
      return null;
    }
    return a().getFilesDir();
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\utils\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */