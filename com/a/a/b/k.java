package com.a.a.b;

import android.graphics.Rect;
import com.androlua.LuaLexer;
import com.androlua.LuaTokenTypes;
import java.util.ArrayList;
import java.util.List;

public class k
{
  private static h b = ;
  private static ArrayList<Rect> e = new ArrayList();
  a a = null;
  private f c;
  private b d = null;
  
  public k(a parama)
  {
    this.a = parama;
  }
  
  public static h a()
  {
    try
    {
      h localh = b;
      return localh;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static void a(h paramh)
  {
    try
    {
      b = paramh;
      return;
    }
    finally
    {
      paramh = finally;
      throw paramh;
    }
  }
  
  public static ArrayList<Rect> d()
  {
    return e;
  }
  
  public void a(f paramf)
  {
    if (!a().f()) {
      return;
    }
    b(new f(paramf));
    if (this.d == null)
    {
      this.d = new b(this);
      this.d.start();
      return;
    }
    this.d.a();
  }
  
  void a(List<m> paramList)
  {
    if (this.a != null) {
      this.a.a(paramList);
    }
    this.d = null;
  }
  
  public void b()
  {
    if (this.d != null)
    {
      this.d.b();
      this.d = null;
    }
  }
  
  public void b(f paramf)
  {
    try
    {
      this.c = paramf;
      return;
    }
    finally
    {
      paramf = finally;
      throw paramf;
    }
  }
  
  public f c()
  {
    try
    {
      f localf = this.c;
      return localf;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static abstract interface a
  {
    public abstract void a(List<m> paramList);
  }
  
  private class b
    extends Thread
  {
    private final k b;
    private final g c;
    private boolean d = false;
    private int e = 16;
    private ArrayList<m> f;
    
    public b(k paramk)
    {
      this.b = paramk;
      this.c = new g();
    }
    
    private void d()
    {
      Object localObject1 = k.this.c();
      int m = ((f)localObject1).f();
      ArrayList localArrayList1 = new ArrayList(8196);
      ArrayList localArrayList2 = new ArrayList(8196);
      ArrayList localArrayList3 = new ArrayList(8196);
      ArrayList localArrayList4 = new ArrayList(8196);
      LuaLexer localLuaLexer = new LuaLexer((CharSequence)localObject1);
      h localh = k.a();
      localh.e();
      for (;;)
      {
        int n;
        try
        {
          Object localObject5 = new StringBuilder();
          Object localObject3 = "";
          i = 0;
          Object localObject2 = null;
          localObject1 = null;
          n = 1;
          int j = -1;
          if (!this.c.c())
          {
            LuaTokenTypes localLuaTokenTypes = localLuaLexer.advance();
            if (localLuaTokenTypes != null)
            {
              int i1 = localLuaLexer.yylength();
              k = i;
              Object localObject4 = localObject5;
              if (i != 0)
              {
                k = i;
                localObject4 = localObject5;
                if (localObject2 == LuaTokenTypes.STRING)
                {
                  k = i;
                  localObject4 = localObject5;
                  if (localLuaTokenTypes != LuaTokenTypes.STRING)
                  {
                    localObject4 = ((StringBuilder)localObject5).toString();
                    if (((StringBuilder)localObject5).length() > 2) {
                      localh.c(((String)localObject4).substring(1, ((String)localObject4).length() - 1));
                    }
                    localObject4 = new StringBuilder();
                    k = 0;
                  }
                }
              }
              int i2 = k.1.a[localLuaTokenTypes.ordinal()];
              i = k;
              switch (i2)
              {
              case 41: 
                continue;
                localObject1 = new m(i1, 4);
                localArrayList1.add(localObject1);
                break;
              case 38: 
              case 39: 
              case 40: 
                localObject1 = new m(i1, 30);
                break;
              case 37: 
                if (m > 9999)
                {
                  localObject1 = new m(i1, 0);
                  continue;
                }
                if (localObject1 == LuaTokenTypes.NUMBER)
                {
                  localObject1 = (m)localArrayList1.get(localArrayList1.size() - 1);
                  ((m)localObject1).b(0);
                  ((m)localObject1).a(((m)localObject1).a() + i1);
                }
                localObject5 = localLuaLexer.yytext();
                if (localObject2 == LuaTokenTypes.FUNCTION)
                {
                  localArrayList1.add(new m(i1, 4));
                  localh.c((String)localObject5);
                }
                else if (localh.g((String)localObject5))
                {
                  localObject1 = new m(i1, 4);
                  localArrayList1.add(localObject1);
                }
                else
                {
                  if ((localObject2 != LuaTokenTypes.GOTO) && (localObject2 != LuaTokenTypes.AT))
                  {
                    if (localh.f((String)localObject5))
                    {
                      localObject1 = new m(i1, 3);
                      continue;
                    }
                    if ((localObject2 == LuaTokenTypes.DOT) && (localh.f((String)localObject3)) && (localh.a((String)localObject3, (String)localObject5)))
                    {
                      localObject1 = new m(i1, 3);
                      continue;
                    }
                    if (localh.e((String)localObject5))
                    {
                      localObject1 = new m(i1, 3);
                      continue;
                    }
                    localObject1 = new m(i1, 0);
                    continue;
                  }
                  localObject1 = new m(i1, 4);
                  continue;
                }
                if ((localObject2 == LuaTokenTypes.ASSIGN) && (((String)localObject5).equals("require")))
                {
                  localh.c((String)localObject3);
                  if (j >= 0) {
                    ((m)localArrayList1.get(j - 1)).b(4);
                  }
                }
                j = localArrayList1.size();
                localObject3 = localObject5;
                break;
              case 35: 
              case 36: 
                localArrayList1.add(new m(i1, 50));
                if (m > 9999)
                {
                  k = i;
                }
                else
                {
                  if (((String)localObject3).equals("require")) {
                    i = 1;
                  }
                  k = i;
                  if (i != 0)
                  {
                    ((StringBuilder)localObject4).append(localLuaLexer.yytext());
                    k = i;
                  }
                }
                break;
              case 29: 
              case 30: 
              case 31: 
              case 32: 
              case 33: 
              case 34: 
                localObject1 = new m(i1, 2);
                localArrayList1.add(localObject1);
                k = i;
                break;
              case 28: 
                k = localArrayList4.size();
                if (k > 0)
                {
                  localObject1 = (Rect)localArrayList4.remove(k - 1);
                  ((Rect)localObject1).bottom = localLuaLexer.yyline();
                  ((Rect)localObject1).right = localLuaLexer.yychar();
                  if (((Rect)localObject1).bottom - ((Rect)localObject1).top > 1) {
                    localArrayList2.add(localObject1);
                  }
                }
                localObject1 = new m(i1, 2);
                break;
              case 27: 
                localArrayList4.add(new Rect(localLuaLexer.yychar(), localLuaLexer.yyline(), 0, localLuaLexer.yyline()));
                localArrayList1.add(new m(i1, 2));
                break;
              case 20: 
                k = localArrayList3.size();
                if (k > 0)
                {
                  localObject1 = (Rect)localArrayList3.remove(k - 1);
                  ((Rect)localObject1).bottom = localLuaLexer.yyline();
                  ((Rect)localObject1).right = localLuaLexer.yychar();
                  if (((Rect)localObject1).bottom - ((Rect)localObject1).top > 1) {
                    localArrayList2.add(localObject1);
                  }
                }
                localObject1 = new m(i1, 1);
                break;
              case 12: 
              case 13: 
                localArrayList3.add(new Rect(localLuaLexer.yychar(), localLuaLexer.yyline(), 0, localLuaLexer.yyline()));
                localArrayList1.add(new m(i1, 1));
                k = 0;
                break;
              case 4: 
              case 8: 
              case 22: 
                localArrayList3.add(new Rect(localLuaLexer.yychar(), localLuaLexer.yyline(), 0, localLuaLexer.yyline()));
                localArrayList1.add(new m(i1, 1));
                break;
              case 3: 
                if (n != 0) {
                  localArrayList3.add(new Rect(localLuaLexer.yychar(), localLuaLexer.yyline(), 0, localLuaLexer.yyline()));
                }
                localObject1 = new m(i1, 1);
                localArrayList1.add(localObject1);
                k = 1;
                break;
              case 1: 
              case 2: 
              case 5: 
              case 6: 
              case 7: 
              case 9: 
              case 10: 
              case 11: 
              case 14: 
              case 15: 
              case 16: 
              case 17: 
              case 18: 
              case 19: 
              case 21: 
              case 23: 
              case 24: 
              case 25: 
              case 26: 
                localArrayList1.add(new m(i1, 1));
                break label1401;
                localArrayList1.add(new m(i1, 0));
                k = n;
                localObject1 = LuaTokenTypes.WHITE_SPACE;
                if (localLuaTokenTypes != localObject1) {
                  localObject2 = localLuaTokenTypes;
                }
                localObject1 = localLuaTokenTypes;
                localObject5 = localObject4;
                n = k;
                break;
              }
            }
          }
          continue;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          r.a(localException.getMessage());
          if (localArrayList1.isEmpty()) {
            localArrayList1.add(new m(0, 0));
          }
          localh.a();
          k.a(localArrayList2);
          this.f = localArrayList1;
          return;
        }
        break label1401;
        int i = k;
        int k = n;
        continue;
        label1401:
        k = n;
      }
    }
    
    public void a()
    {
      this.d = true;
      this.c.a();
    }
    
    public void b()
    {
      this.c.a();
    }
    
    public void c()
    {
      f localf = k.this.c();
      h localh = k.a();
      ArrayList localArrayList = new ArrayList();
      if (!localh.f())
      {
        localArrayList.add(new m(0, 0));
        this.f = localArrayList;
        return;
      }
      char[] arrayOfChar = new char[127];
      localf.f(0);
      int k = -1;
      char c2 = '\000';
      int m = 0;
      int n = 0;
      while ((localf.a()) && (!this.c.c()))
      {
        char c1 = localf.b();
        int i1;
        int i;
        switch (k)
        {
        default: 
          r.a("Invalid state in TokenScanner");
          i1 = k;
          i = m;
          break;
        case 51: 
          if (((!localh.g(c1)) && (c1 != '\n')) || (localh.d(c2)))
          {
            i1 = k;
            i = m;
            if (!localh.d(c1)) {
              break label937;
            }
            i1 = k;
            i = m;
            if (!localh.d(c2)) {
              break label937;
            }
          }
          break;
        case 50: 
          if (((!localh.f(c1)) && (c1 != '\n')) || (localh.d(c2)))
          {
            i1 = k;
            i = m;
            if (!localh.d(c1)) {
              break label937;
            }
            i1 = k;
            i = m;
            if (!localh.d(c2)) {
              break label937;
            }
            c1 = ' ';
            i = m;
          }
          break;
        case 40: 
          i1 = k;
          i = m;
          if (!localh.c(c2, c1)) {
            break label937;
          }
        case 20: 
        case 21: 
        case 30: 
          for (;;)
          {
            k = -1;
            i = m;
            break label941;
            if (localh.b(c2, c1))
            {
              k = 40;
              i = m;
              break label941;
            }
            i1 = k;
            i = m;
            if (c1 != '\n') {
              break;
            }
          }
        }
        int j;
        if (localh.a(c2, c1))
        {
          j = 1;
          i = 30;
        }
        else if (localh.b(c2, c1))
        {
          j = 1;
          i = 40;
        }
        else if (localh.f(c1))
        {
          j = 1;
          i = 50;
        }
        else if (localh.g(c1))
        {
          j = 1;
          i = 51;
        }
        else if (localh.h(c1))
        {
          j = 1;
          i = 20;
        }
        else if (localh.i(c1))
        {
          j = 1;
          i = 21;
        }
        else
        {
          i = k;
          j = 0;
        }
        if (j != 0)
        {
          if ((i != 30) && (i != 40))
          {
            j = n;
          }
          else
          {
            i1 = n - 1;
            j = i1;
            if (((m)localArrayList.get(localArrayList.size() - 1)).a() == i1)
            {
              localArrayList.remove(localArrayList.size() - 1);
              j = i1;
            }
          }
          if ((m > 0) && (k != 0)) {
            localArrayList.add(new m(n - m, 0));
          }
          localArrayList.add(new m(j, i));
          j = 0;
          k = i;
          i = j;
        }
        else
        {
          if ((!localh.b(c1)) && (!localh.a(c1)))
          {
            i1 = k;
            i = m;
            if (m < 127)
            {
              arrayOfChar[m] = c1;
              i = m + 1;
              i1 = k;
            }
          }
          else
          {
            int i2 = k;
            j = m;
            if (m > 0)
            {
              if (localh.e(arrayOfChar[0]))
              {
                localArrayList.add(new m(n - m, 10));
                i = 10;
              }
              else if (localh.d(new String(arrayOfChar, 0, m)))
              {
                localArrayList.add(new m(n - m, 1));
                i = 1;
              }
              else if (localh.e(new String(arrayOfChar, 0, m)))
              {
                i = 3;
                localArrayList.add(new m(n - m, 3));
              }
              else
              {
                i = k;
                if (k != 0)
                {
                  localArrayList.add(new m(n - m, 0));
                  i = 0;
                }
              }
              j = 0;
              i2 = i;
            }
            i1 = i2;
            i = j;
            if (i2 != 0)
            {
              i1 = i2;
              i = j;
              if (localh.a(c1))
              {
                localArrayList.add(new m(n, 0));
                k = 0;
                i = j;
                break label941;
              }
            }
          }
          label937:
          k = i1;
        }
        label941:
        n += 1;
        c2 = c1;
        m = i;
      }
      if (localArrayList.isEmpty()) {
        localArrayList.add(new m(0, 0));
      }
      this.f = localArrayList;
    }
    
    public void run()
    {
      do
      {
        this.d = false;
        this.c.b();
        if ((k.a() instanceof i)) {
          d();
        } else {
          c();
        }
      } while (this.d);
      if (!this.c.c()) {
        this.b.a(this.f);
      }
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\a\a\b\k.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */