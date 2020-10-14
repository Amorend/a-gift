package com.a.a.b;

import java.util.ArrayList;

public class e
  extends p
{
  private boolean f = false;
  private a g;
  private ArrayList<Integer> h;
  
  public e(a parama)
  {
    this.g = parama;
    m();
  }
  
  private void a(int paramInt1, int paramInt2)
  {
    while ((paramInt1 < this.h.size()) && (((Integer)this.h.get(paramInt1)).intValue() <= paramInt2)) {
      this.h.remove(paramInt1);
    }
  }
  
  private void a(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramInt1;
    if (paramInt1 > 0) {
      i = paramInt1 - 1;
    }
    paramInt1 = ((Integer)this.h.get(i)).intValue();
    i += 1;
    a(i, paramInt2 - paramInt3);
    b(i, paramInt3);
    b(i, paramInt1, paramInt2);
  }
  
  private void b(int paramInt1, int paramInt2)
  {
    while (paramInt1 < this.h.size())
    {
      this.h.set(paramInt1, Integer.valueOf(((Integer)this.h.get(paramInt1)).intValue() + paramInt2));
      paramInt1 += 1;
    }
  }
  
  private void b(int paramInt1, int paramInt2, int paramInt3)
  {
    if (!this.f)
    {
      paramInt2 = p(paramInt2);
      j = p(paramInt3);
      localArrayList = new ArrayList();
      while (paramInt2 < j)
      {
        paramInt3 = paramInt2;
        if (paramInt2 == this.b) {
          paramInt3 = this.c;
        }
        if (this.a[paramInt3] == '\n') {
          localArrayList.add(Integer.valueOf(q(paramInt3) + 1));
        }
        paramInt2 = paramInt3 + 1;
      }
      this.h.addAll(paramInt1, localArrayList);
      return;
    }
    if (!n())
    {
      r.a("Not enough space to do word wrap");
      return;
    }
    ArrayList localArrayList = new ArrayList();
    int m = p(paramInt2);
    int i5 = p(paramInt3);
    int j = this.g.getRowWidth();
    int i1 = paramInt2;
    paramInt3 = j;
    int k = 0;
    while (m < i5)
    {
      int n = m;
      if (m == this.b) {
        n = this.c;
      }
      int i = this.a[n];
      int i4 = k + this.g.getAdvance(i);
      int i3;
      if ((i != 32) && (i != 9) && (i != 10) && (i != 65535)) {
        i3 = 0;
      } else {
        i3 = 1;
      }
      k = i4;
      m = paramInt3;
      int i2 = i1;
      if (i3 != 0)
      {
        if (i4 <= paramInt3)
        {
          paramInt3 -= i4;
        }
        else
        {
          if (i4 > j)
          {
            m = p(i1);
            if ((i1 != paramInt2) && ((localArrayList.isEmpty()) || (i1 != ((Integer)localArrayList.get(localArrayList.size() - 1)).intValue()))) {
              localArrayList.add(Integer.valueOf(i1));
            }
            k = j;
            for (;;)
            {
              paramInt3 = k;
              if (m > n) {
                break;
              }
              paramInt3 = m;
              if (m == this.b) {
                paramInt3 = this.c;
              }
              m = this.g.getAdvance(this.a[paramInt3]);
              if (m > k)
              {
                localArrayList.add(Integer.valueOf(q(paramInt3)));
                k = j - m;
              }
              else
              {
                k -= m;
              }
              m = paramInt3 + 1;
            }
          }
          localArrayList.add(Integer.valueOf(i1));
          paramInt3 = j - i4;
        }
        i2 = q(n) + 1;
        k = 0;
        m = paramInt3;
      }
      paramInt3 = m;
      if (i == 10)
      {
        localArrayList.add(Integer.valueOf(i2));
        paramInt3 = j;
      }
      m = n + 1;
      i1 = i2;
    }
    this.h.addAll(paramInt1, localArrayList);
  }
  
  private void m()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(Integer.valueOf(0));
    this.h = localArrayList;
  }
  
  private boolean n()
  {
    return this.g.getRowWidth() >= this.g.getAdvance('M') * 2;
  }
  
  private int s(int paramInt)
  {
    int j;
    int i;
    for (paramInt = p(paramInt);; paramInt = i + 1)
    {
      j = paramInt;
      if (paramInt >= this.a.length) {
        break;
      }
      i = paramInt;
      if (paramInt == this.b) {
        i = this.c;
      }
      j = i;
      if (this.a[i] == '\n') {
        break;
      }
      if (this.a[i] == 65535)
      {
        j = i;
        break;
      }
    }
    return q(j) + 1;
  }
  
  void a(int paramInt)
  {
    try
    {
      super.a(paramInt);
      if (paramInt != 0)
      {
        int i;
        if (paramInt > 0) {
          i = this.b - paramInt;
        } else {
          i = this.b;
        }
        a(e(i), s(this.b), paramInt);
      }
      return;
    }
    finally {}
  }
  
  public void a(int paramInt1, int paramInt2, long paramLong, boolean paramBoolean)
  {
    try
    {
      super.a(paramInt1, paramInt2, paramLong, paramBoolean);
      a(e(paramInt1), s(paramInt1), -paramInt2);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void a(CharSequence paramCharSequence)
  {
    int m = paramCharSequence.length();
    char[] arrayOfChar = new char[p.g(m)];
    int j = 1;
    int i = 0;
    while (i < m)
    {
      arrayOfChar[i] = paramCharSequence.charAt(i);
      int k = j;
      if (paramCharSequence.charAt(i) == '\n') {
        k = j + 1;
      }
      i += 1;
      j = k;
    }
    a(arrayOfChar, m, j);
  }
  
  public void a(boolean paramBoolean)
  {
    if ((paramBoolean) && (!this.f)) {}
    for (paramBoolean = true;; paramBoolean = false)
    {
      this.f = paramBoolean;
      b();
      return;
      if ((paramBoolean) || (!this.f)) {
        break;
      }
    }
  }
  
  public void a(char[] paramArrayOfChar, int paramInt, long paramLong, boolean paramBoolean)
  {
    try
    {
      super.a(paramArrayOfChar, paramInt, paramLong, paramBoolean);
      a(e(paramInt), s(paramInt + paramArrayOfChar.length), paramArrayOfChar.length);
      return;
    }
    finally
    {
      paramArrayOfChar = finally;
      throw paramArrayOfChar;
    }
  }
  
  public boolean a()
  {
    return this.f;
  }
  
  public String b(int paramInt)
  {
    int i = c(paramInt);
    if (i == 0) {
      return new String();
    }
    return subSequence(((Integer)this.h.get(paramInt)).intValue(), i).toString();
  }
  
  public void b()
  {
    m();
    if ((this.f) && (!n()))
    {
      if (this.g.getRowWidth() > 0) {
        r.a("Text field has non-zero width but still too small for word wrap");
      }
      return;
    }
    b(1, 0, d());
  }
  
  public int c()
  {
    return this.h.size();
  }
  
  public int c(int paramInt)
  {
    if (f(paramInt)) {
      return 0;
    }
    if (paramInt != this.h.size() - 1) {}
    for (int i = ((Integer)this.h.get(paramInt + 1)).intValue();; i = d()) {
      return i - ((Integer)this.h.get(paramInt)).intValue();
    }
  }
  
  public int d(int paramInt)
  {
    if (f(paramInt)) {
      return -1;
    }
    return ((Integer)this.h.get(paramInt)).intValue();
  }
  
  public int e(int paramInt)
  {
    if (!o(paramInt)) {
      return -1;
    }
    int j = this.h.size() - 1;
    int i = 0;
    while (j >= i)
    {
      int n = (i + j) / 2;
      int m = n + 1;
      int k;
      if (m < this.h.size()) {
        k = ((Integer)this.h.get(m)).intValue();
      } else {
        k = d();
      }
      if ((paramInt >= ((Integer)this.h.get(n)).intValue()) && (paramInt < k)) {
        return n;
      }
      if (paramInt >= k) {
        i = m;
      } else {
        j = n - 1;
      }
    }
    return -1;
  }
  
  protected boolean f(int paramInt)
  {
    return (paramInt < 0) || (paramInt >= this.h.size());
  }
  
  public static abstract interface a
  {
    public abstract int getAdvance(char paramChar);
    
    public abstract int getRowWidth();
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\a\a\b\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */