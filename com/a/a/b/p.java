package com.a.a.b;

import java.util.List;
import java.util.Vector;

public class p
  implements CharSequence
{
  protected char[] a = new char[51];
  protected int b;
  protected int c;
  protected int d;
  protected List<m> e;
  private int f;
  private q g;
  private s h;
  
  public p()
  {
    this.a[50] = 65535;
    this.f = 1;
    this.b = 0;
    this.c = 50;
    this.d = 1;
    this.g = new q();
    this.h = new s(this);
  }
  
  private int a(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = p(paramInt3);
    r.a(o(paramInt3), "findCharOffsetBackward: Invalid startingOffset given");
    paramInt3 = paramInt2;
    paramInt2 = i;
    while ((paramInt3 < paramInt1) && (paramInt2 < this.a.length))
    {
      i = paramInt3;
      if (this.a[paramInt2] == '\n') {
        i = paramInt3 + 1;
      }
      int j = paramInt2 + 1;
      paramInt2 = j;
      paramInt3 = i;
      if (j == this.b)
      {
        paramInt2 = this.c;
        paramInt3 = i;
      }
    }
    if (paramInt3 != paramInt1) {
      return -1;
    }
    return q(paramInt2);
  }
  
  private void a(int paramInt1, int paramInt2)
  {
    m localm = b(paramInt1);
    localm = (m)this.e.get(localm.a());
    localm.a(localm.a() + paramInt2);
  }
  
  private int b(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 == 0) {
      return 0;
    }
    r.a(o(paramInt3), "findCharOffsetBackward: Invalid startOffset given");
    paramInt3 = p(paramInt3);
    while ((paramInt2 > paramInt1 - 1) && (paramInt3 >= 0))
    {
      int i = paramInt3;
      if (paramInt3 == this.c) {
        i = this.b;
      }
      i -= 1;
      paramInt3 = i;
      if (this.a[i] == '\n')
      {
        paramInt2 -= 1;
        paramInt3 = i;
      }
    }
    if (paramInt3 >= 0) {
      return q(paramInt3) + 1;
    }
    r.a(false, "findCharOffsetBackward: Invalid cache entry or line arguments");
    return -1;
  }
  
  private m b(int paramInt)
  {
    int k = this.e.size();
    int i = 0;
    int j = 0;
    while (i < k)
    {
      int m = ((m)this.e.get(i)).a();
      j += m;
      if (j >= paramInt) {
        return new m(i, j - m);
      }
      i += 1;
    }
    return new m(0, 0);
  }
  
  private void b(int paramInt1, int paramInt2)
  {
    if (length() == 0)
    {
      f();
      return;
    }
    m localm1 = c(paramInt1);
    if (paramInt2 == 1)
    {
      localm2 = (m)this.e.get(localm1.a());
      if (localm2.a() > 1)
      {
        localm2.a(localm2.a() - 1);
        return;
      }
      this.e.remove(localm1.a());
      return;
    }
    paramInt1 -= localm1.b();
    m localm2 = (m)this.e.get(localm1.a());
    if (localm2.a() > paramInt1) {
      localm2.a(localm2.a() - paramInt1);
    } else {
      this.e.remove(localm1.a());
    }
    paramInt2 -= paramInt1;
    if (paramInt2 > 0)
    {
      paramInt1 = localm1.a();
      while (paramInt1 >= 0)
      {
        localm1 = (m)this.e.get(paramInt1);
        int i = localm1.a();
        if (paramInt2 > i)
        {
          paramInt2 -= i;
          this.e.remove(paramInt1);
          paramInt1 -= 1;
        }
        else
        {
          localm1.a(localm1.a() - paramInt2);
        }
      }
    }
  }
  
  private int c(int paramInt1, int paramInt2)
  {
    int i = paramInt1;
    int k;
    for (int j = 0; i < paramInt1 + paramInt2; j = k)
    {
      k = j;
      if (this.a[i] == '\n') {
        k = j + 1;
      }
      i += 1;
    }
    return j;
  }
  
  private m c(int paramInt)
  {
    int k = this.e.size();
    int i = 0;
    int j = 0;
    while (i < k)
    {
      int m = ((m)this.e.get(i)).a();
      j += m;
      if (j > paramInt) {
        return new m(i, j - m);
      }
      i += 1;
    }
    return new m(0, 0);
  }
  
  public static int g(int paramInt)
  {
    long l = paramInt + 50 + 1;
    if (l < 2147483647L) {
      return (int)l;
    }
    return -1;
  }
  
  void a(int paramInt)
  {
    if (paramInt >= 0) {}
    try
    {
      a(this.b, paramInt);
      this.d += c(this.b, paramInt);
      break label68;
      b(this.b, 0 - paramInt);
      this.d -= c(this.b + paramInt, -paramInt);
      label68:
      this.b += paramInt;
      this.g.c(q(this.b - 1) + 1);
      return;
    }
    finally
    {
      Object localObject1;
      for (;;) {}
    }
    throw ((Throwable)localObject1);
  }
  
  public void a(int paramInt1, int paramInt2, long paramLong, boolean paramBoolean)
  {
    if (paramBoolean) {}
    try
    {
      this.h.b(paramInt1, paramInt2, paramLong);
      i = paramInt1 + paramInt2;
      if (i == this.b) {
        break label136;
      }
      if (r(i)) {
        k(i);
      } else {
        l(i + e());
      }
    }
    finally
    {
      for (;;)
      {
        Object localObject1;
        continue;
        int i = 0;
        continue;
        i += 1;
      }
    }
    if (i < paramInt2)
    {
      this.b -= 1;
      if (this.a[this.b] == '\n') {
        this.d -= 1;
      }
    }
    else
    {
      this.g.c(paramInt1);
      b(paramInt1, paramInt2);
      return;
      throw ((Throwable)localObject1);
    }
  }
  
  public void a(List<m> paramList)
  {
    this.e = paramList;
  }
  
  public void a(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    try
    {
      this.a = paramArrayOfChar;
      m(paramInt1);
      this.d = paramInt2;
      this.f = 1;
      return;
    }
    finally
    {
      paramArrayOfChar = finally;
      throw paramArrayOfChar;
    }
  }
  
  public void a(char[] paramArrayOfChar, int paramInt, long paramLong, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (;;)
    {
      try
      {
        this.h.a(paramInt, paramArrayOfChar.length, paramLong);
        i = p(paramInt);
        if (i != this.c) {
          if (r(i)) {
            k(i);
          } else {
            l(i);
          }
        }
        if (paramArrayOfChar.length < e()) {
          continue;
        }
        n(paramArrayOfChar.length - e());
      }
      finally
      {
        continue;
        int i = 0;
        continue;
      }
      if (i >= paramArrayOfChar.length) {
        continue;
      }
      if (paramArrayOfChar[i] == '\n') {
        this.d += 1;
      }
      this.a[this.b] = paramArrayOfChar[i];
      this.b += 1;
      i += 1;
    }
    this.g.c(paramInt);
    a(paramInt, paramArrayOfChar.length);
    return;
    throw paramArrayOfChar;
  }
  
  public char charAt(int paramInt)
  {
    try
    {
      char c1 = this.a[p(paramInt)];
      return c1;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public final int d()
  {
    try
    {
      int i = this.a.length;
      int j = e();
      return i - j;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  protected final int e()
  {
    return this.c - this.b;
  }
  
  public void f()
  {
    this.e = new Vector();
    this.e.add(new m(length(), 0));
  }
  
  public List<m> g()
  {
    return this.e;
  }
  
  public int h(int paramInt)
  {
    if (paramInt < 0) {
      return -1;
    }
    try
    {
      m localm = this.g.a(paramInt);
      int k = localm.a();
      int j = localm.b();
      int i;
      if (paramInt > k)
      {
        i = a(paramInt, k, j);
      }
      else
      {
        i = j;
        if (paramInt < k) {
          i = b(paramInt, k, j);
        }
      }
      if (i >= 0) {
        this.g.a(paramInt, i);
      }
      return i;
    }
    finally {}
  }
  
  public boolean h()
  {
    return this.h.e();
  }
  
  public int i(int paramInt)
  {
    for (;;)
    {
      int j;
      int i;
      int i4;
      int m;
      int k;
      int n;
      int i2;
      int i3;
      try
      {
        boolean bool = o(paramInt);
        if (!bool) {
          return -1;
        }
        m localm = this.g.b(paramInt);
        j = localm.a();
        i = p(localm.b());
        i4 = p(paramInt);
        if (i4 > i)
        {
          int i1 = -1;
          m = -1;
          paramInt = i;
          k = paramInt;
          n = i1;
          i2 = m;
          i3 = j;
          if (paramInt < i4)
          {
            k = paramInt;
            n = i1;
            i2 = m;
            i3 = j;
            if (paramInt < this.a.length)
            {
              n = i1;
              k = m;
              i = j;
              if (this.a[paramInt] == '\n')
              {
                n = j + 1;
                k = q(paramInt) + 1;
                i = n;
              }
              i2 = paramInt + 1;
              paramInt = i2;
              i1 = n;
              m = k;
              j = i;
              if (i2 != this.b) {
                continue;
              }
              paramInt = this.c;
              i1 = n;
              m = k;
              j = i;
              continue;
              m = k;
              k = paramInt;
              n = i;
              i2 = j;
              i3 = m;
              if (paramInt > i4)
              {
                k = paramInt;
                n = i;
                i2 = j;
                i3 = m;
                if (paramInt > 0)
                {
                  k = paramInt;
                  if (paramInt == this.c) {
                    k = this.b;
                  }
                  n = k - 1;
                  paramInt = n;
                  k = m;
                  if (this.a[n] != '\n') {
                    continue;
                  }
                  j = q(n) + 1;
                  k = m - 1;
                  i = m;
                  paramInt = n;
                  continue;
                }
              }
            }
          }
          if (k == i4)
          {
            if (n != -1) {
              this.g.a(n, i2);
            }
            return i3;
          }
          return -1;
        }
      }
      finally {}
      if (i4 < i)
      {
        k = j;
        m = -1;
        j = -1;
        paramInt = i;
        i = m;
      }
      else
      {
        n = -1;
        i2 = -1;
        k = i;
        i3 = j;
      }
    }
  }
  
  public void i()
  {
    this.h.f();
  }
  
  public void j()
  {
    this.h.g();
  }
  
  char[] j(int paramInt)
  {
    char[] arrayOfChar = new char[paramInt];
    int i = 0;
    while (i < paramInt)
    {
      arrayOfChar[i] = this.a[(this.b + i)];
      i += 1;
    }
    return arrayOfChar;
  }
  
  public int k()
  {
    return this.h.a();
  }
  
  protected final void k(int paramInt)
  {
    while (this.b > paramInt)
    {
      this.c -= 1;
      this.b -= 1;
      this.a[this.c] = this.a[this.b];
    }
  }
  
  public int l()
  {
    return this.h.b();
  }
  
  protected final void l(int paramInt)
  {
    while (this.c < paramInt)
    {
      this.a[this.b] = this.a[this.c];
      this.b += 1;
      this.c += 1;
    }
  }
  
  public int length()
  {
    return d() - 1;
  }
  
  protected void m(int paramInt)
  {
    int j = this.a.length - 1;
    char[] arrayOfChar = this.a;
    int i = j - 1;
    arrayOfChar[j] = 65535;
    paramInt -= 1;
    while (paramInt >= 0)
    {
      this.a[i] = this.a[paramInt];
      i -= 1;
      paramInt -= 1;
    }
    this.b = 0;
    this.c = (i + 1);
  }
  
  protected void n(int paramInt)
  {
    int i = paramInt + this.f * 50;
    char[] arrayOfChar = new char[this.a.length + i];
    paramInt = 0;
    while (paramInt < this.b)
    {
      arrayOfChar[paramInt] = this.a[paramInt];
      paramInt += 1;
    }
    paramInt = this.c;
    while (paramInt < this.a.length)
    {
      arrayOfChar[(paramInt + i)] = this.a[paramInt];
      paramInt += 1;
    }
    this.c += i;
    this.a = arrayOfChar;
    this.f <<= 1;
  }
  
  /* Error */
  public final boolean o(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iload_1
    //   3: iflt +25 -> 28
    //   6: aload_0
    //   7: invokevirtual 179	com/a/a/b/p:d	()I
    //   10: istore_2
    //   11: iload_1
    //   12: iload_2
    //   13: if_icmpge +15 -> 28
    //   16: iconst_1
    //   17: istore_3
    //   18: goto +12 -> 30
    //   21: astore 4
    //   23: aload_0
    //   24: monitorexit
    //   25: aload 4
    //   27: athrow
    //   28: iconst_0
    //   29: istore_3
    //   30: aload_0
    //   31: monitorexit
    //   32: iload_3
    //   33: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	34	0	this	p
    //   0	34	1	paramInt	int
    //   10	4	2	i	int
    //   17	16	3	bool	boolean
    //   21	5	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   6	11	21	finally
  }
  
  protected final int p(int paramInt)
  {
    if (r(paramInt)) {
      return paramInt;
    }
    return paramInt + e();
  }
  
  protected final int q(int paramInt)
  {
    if (r(paramInt)) {
      return paramInt;
    }
    return paramInt - e();
  }
  
  protected final boolean r(int paramInt)
  {
    return paramInt < this.b;
  }
  
  public CharSequence subSequence(int paramInt1, int paramInt2)
  {
    for (;;)
    {
      try
      {
        Object localObject1;
        if ((o(paramInt1)) && (paramInt2 > 0))
        {
          int i = paramInt2;
          if (paramInt1 + paramInt2 > d()) {
            i = d() - paramInt1;
          }
          paramInt1 = p(paramInt1);
          localObject1 = new char[i];
          paramInt2 = 0;
          if (paramInt2 < i)
          {
            localObject1[paramInt2] = this.a[paramInt1];
            int j = paramInt1 + 1;
            paramInt1 = j;
            if (j == this.b) {
              paramInt1 = this.c;
            }
          }
          else
          {
            localObject1 = new String((char[])localObject1);
            return (CharSequence)localObject1;
          }
        }
        else
        {
          localObject1 = new String();
          return (CharSequence)localObject1;
        }
      }
      finally {}
      paramInt2 += 1;
    }
  }
  
  public String toString()
  {
    int k = d();
    StringBuffer localStringBuffer = new StringBuffer();
    int j = 0;
    while (j < k)
    {
      int i = charAt(j);
      if (i == 65535) {
        break;
      }
      localStringBuffer.append(i);
      j += 1;
    }
    return new String(localStringBuffer);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\a\a\b\p.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */