package com.tencent.open.a;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class g
  implements Iterable<String>
{
  private ConcurrentLinkedQueue<String> a = null;
  private AtomicInteger b = null;
  
  public int a()
  {
    return this.b.get();
  }
  
  public int a(String paramString)
  {
    int i = paramString.length();
    this.a.add(paramString);
    return this.b.addAndGet(i);
  }
  
  public void a(Writer paramWriter, char[] paramArrayOfChar)
    throws IOException
  {
    if ((paramWriter == null) || (paramArrayOfChar == null) || (paramArrayOfChar.length == 0)) {
      return;
    }
    int m = paramArrayOfChar.length;
    int i = m;
    int k = 0;
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      int n = 0;
      int j = str.length();
      if (j > 0)
      {
        if (i > j) {}
        for (int i1 = j;; i1 = i)
        {
          str.getChars(n, n + i1, paramArrayOfChar, k);
          int i3 = i - i1;
          k += i1;
          int i2 = j - i1;
          i1 = n + i1;
          j = i2;
          n = i1;
          i = i3;
          if (i3 != 0) {
            break;
          }
          paramWriter.write(paramArrayOfChar, 0, m);
          k = 0;
          i = m;
          j = i2;
          n = i1;
          break;
        }
      }
    }
    if (k > 0) {
      paramWriter.write(paramArrayOfChar, 0, k);
    }
    paramWriter.flush();
  }
  
  public void b()
  {
    this.a.clear();
    this.b.set(0);
  }
  
  public Iterator<String> iterator()
  {
    return this.a.iterator();
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\a\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */