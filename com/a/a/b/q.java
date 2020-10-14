package com.a.a.b;

public class q
{
  private m[] a = new m[4];
  
  public q()
  {
    this.a[0] = new m(0, 0);
    int i = 1;
    while (i < 4)
    {
      this.a[i] = new m(-1, -1);
      i += 1;
    }
  }
  
  private boolean b(int paramInt1, int paramInt2)
  {
    int i = 1;
    while (i < 4)
    {
      if (this.a[i].a() == paramInt1)
      {
        this.a[i].b(paramInt2);
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private void c(int paramInt1, int paramInt2)
  {
    d(3);
    this.a[1] = new m(paramInt1, paramInt2);
  }
  
  private void d(int paramInt)
  {
    if (paramInt == 0) {
      return;
    }
    m localm = this.a[paramInt];
    while (paramInt > 1)
    {
      this.a[paramInt] = this.a[(paramInt - 1)];
      paramInt -= 1;
    }
    this.a[1] = localm;
  }
  
  public m a(int paramInt)
  {
    int i = 0;
    int j = Integer.MAX_VALUE;
    int k = 0;
    while (i < 4)
    {
      int n = Math.abs(paramInt - this.a[i].a());
      int m = j;
      if (n < j)
      {
        k = i;
        m = n;
      }
      i += 1;
      j = m;
    }
    m localm = this.a[k];
    d(k);
    return localm;
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    if (paramInt1 <= 0) {
      return;
    }
    if (!b(paramInt1, paramInt2)) {
      c(paramInt1, paramInt2);
    }
  }
  
  public m b(int paramInt)
  {
    int i = 0;
    int j = Integer.MAX_VALUE;
    int k = 0;
    while (i < 4)
    {
      int n = Math.abs(paramInt - this.a[i].b());
      int m = j;
      if (n < j)
      {
        k = i;
        m = n;
      }
      i += 1;
      j = m;
    }
    m localm = this.a[k];
    d(k);
    return localm;
  }
  
  protected final void c(int paramInt)
  {
    int i = 1;
    while (i < 4)
    {
      if (this.a[i].b() >= paramInt) {
        this.a[i] = new m(-1, -1);
      }
      i += 1;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\a\a\b\q.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */