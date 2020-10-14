package com.tencent.open.a;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.os.StatFs;
import java.io.File;
import java.text.SimpleDateFormat;

public class d
{
  public static final class a
  {
    public static final boolean a(int paramInt1, int paramInt2)
    {
      if (paramInt2 == (paramInt1 & paramInt2)) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
  }
  
  public static final class b
  {
    public static boolean a()
    {
      String str = Environment.getExternalStorageState();
      if (("mounted".equals(str)) || ("mounted_ro".equals(str))) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public static d.c b()
    {
      if (!a()) {
        return null;
      }
      return d.c.b(Environment.getExternalStorageDirectory());
    }
  }
  
  public static class c
  {
    private File a;
    private long b;
    private long c;
    
    public static c b(File paramFile)
    {
      c localc = new c();
      localc.a(paramFile);
      paramFile = new StatFs(paramFile.getAbsolutePath());
      long l1 = paramFile.getBlockSize();
      long l2 = paramFile.getBlockCount();
      long l3 = paramFile.getAvailableBlocks();
      localc.a(l2 * l1);
      localc.b(l3 * l1);
      return localc;
    }
    
    public File a()
    {
      return this.a;
    }
    
    public void a(long paramLong)
    {
      this.b = paramLong;
    }
    
    public void a(File paramFile)
    {
      this.a = paramFile;
    }
    
    public long b()
    {
      return this.b;
    }
    
    public void b(long paramLong)
    {
      this.c = paramLong;
    }
    
    public long c()
    {
      return this.c;
    }
    
    public String toString()
    {
      return String.format("[%s : %d / %d]", new Object[] { a().getAbsolutePath(), Long.valueOf(c()), Long.valueOf(b()) });
    }
  }
  
  public static final class d
  {
    @SuppressLint({"SimpleDateFormat"})
    public static SimpleDateFormat a(String paramString)
    {
      return new SimpleDateFormat(paramString);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\a\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */