package com.a.a.b;

import java.util.LinkedList;

public class s
{
  long a = -1L;
  private p b;
  private LinkedList<a> c = new LinkedList();
  private boolean d = false;
  private int e = 0;
  private int f = 0;
  
  public s(p paramp)
  {
    this.b = paramp;
  }
  
  private void a(a parama)
  {
    h();
    this.f += 1;
    this.c.add(parama);
  }
  
  private void h()
  {
    while (this.c.size() > this.f) {
      this.c.removeLast();
    }
  }
  
  public int a()
  {
    if (c())
    {
      Object localObject = (a)this.c.get(this.f - 1);
      int i = ((a)localObject).d;
      for (;;)
      {
        a locala = (a)this.c.get(this.f - 1);
        if (locala.d == i)
        {
          locala.a();
          this.f -= 1;
          if (!c()) {
            localObject = locala;
          }
        }
        else
        {
          return ((a)localObject).d();
        }
        localObject = locala;
      }
    }
    return -1;
  }
  
  public void a(int paramInt1, int paramInt2, long paramLong)
  {
    int i;
    if (c())
    {
      a locala = (a)this.c.get(this.f - 1);
      if (((locala instanceof c)) && (locala.a(paramInt1, paramInt2, paramLong))) {
        i = 1;
      } else {
        locala.c();
      }
    }
    else
    {
      i = 0;
    }
    if (i == 0)
    {
      a(new c(paramInt1, paramInt2, this.e));
      if (!this.d) {
        this.e += 1;
      }
    }
    this.a = paramLong;
  }
  
  public int b()
  {
    if (d())
    {
      Object localObject = (a)this.c.get(this.f);
      int i = ((a)localObject).d;
      for (;;)
      {
        a locala = (a)this.c.get(this.f);
        if (locala.d == i)
        {
          locala.b();
          this.f += 1;
          if (!d()) {
            localObject = locala;
          }
        }
        else
        {
          return ((a)localObject).e();
        }
        localObject = locala;
      }
    }
    return -1;
  }
  
  public void b(int paramInt1, int paramInt2, long paramLong)
  {
    int i;
    if (c())
    {
      a locala = (a)this.c.get(this.f - 1);
      if (((locala instanceof b)) && (locala.a(paramInt1, paramInt2, paramLong))) {
        i = 1;
      } else {
        locala.c();
      }
    }
    else
    {
      i = 0;
    }
    if (i == 0)
    {
      a(new b(paramInt1, paramInt2, this.e));
      if (!this.d) {
        this.e += 1;
      }
    }
    this.a = paramLong;
  }
  
  public final boolean c()
  {
    return this.f > 0;
  }
  
  public final boolean d()
  {
    return this.f < this.c.size();
  }
  
  public boolean e()
  {
    return this.d;
  }
  
  public void f()
  {
    this.d = true;
  }
  
  public void g()
  {
    this.d = false;
    this.e += 1;
  }
  
  private abstract class a
  {
    public int a;
    public int b;
    public String c;
    public int d;
    
    private a() {}
    
    public abstract void a();
    
    public abstract boolean a(int paramInt1, int paramInt2, long paramLong);
    
    public abstract void b();
    
    public abstract void c();
    
    public abstract int d();
    
    public abstract int e();
  }
  
  private class b
    extends s.a
  {
    public b(int paramInt1, int paramInt2, int paramInt3)
    {
      super(null);
      this.a = paramInt1;
      this.b = paramInt2;
      this.d = paramInt3;
    }
    
    public void a()
    {
      if (this.c == null)
      {
        c();
        s.b(s.this).a(this.b);
        return;
      }
      s.b(s.this).a(this.c.toCharArray(), this.a, 0L, false);
    }
    
    public boolean a(int paramInt1, int paramInt2, long paramLong)
    {
      if (s.this.a < 0L) {
        return false;
      }
      if ((paramLong - s.this.a < 1000000000L) && (paramInt1 == this.a - this.b - paramInt2 + 1))
      {
        this.a = paramInt1;
        this.b += paramInt2;
        s.a(s.this);
        return true;
      }
      return false;
    }
    
    public void b()
    {
      s.b(s.this).a(this.a, this.b, 0L, false);
    }
    
    public void c()
    {
      this.c = new String(s.b(s.this).j(this.b));
    }
    
    public int d()
    {
      return this.a + this.b;
    }
    
    public int e()
    {
      return this.a;
    }
  }
  
  private class c
    extends s.a
  {
    public c(int paramInt1, int paramInt2, int paramInt3)
    {
      super(null);
      this.a = paramInt1;
      this.b = paramInt2;
      this.d = paramInt3;
    }
    
    public void a()
    {
      if (this.c == null)
      {
        c();
        s.b(s.this).a(-this.b);
        return;
      }
      s.b(s.this).a(this.a, this.b, 0L, false);
    }
    
    public boolean a(int paramInt1, int paramInt2, long paramLong)
    {
      if (s.this.a < 0L) {
        return false;
      }
      if ((paramLong - s.this.a < 1000000000L) && (paramInt1 == this.a + this.b))
      {
        this.b += paramInt2;
        s.a(s.this);
        return true;
      }
      return false;
    }
    
    public void b()
    {
      s.b(s.this).a(this.c.toCharArray(), this.a, 0L, false);
    }
    
    public void c()
    {
      this.c = s.b(s.this).subSequence(this.a, this.b).toString();
    }
    
    public int d()
    {
      return this.a;
    }
    
    public int e()
    {
      return this.a + this.b;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\a\a\b\s.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */