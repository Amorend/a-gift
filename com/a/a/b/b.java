package com.a.a.b;

import java.util.HashMap;

public abstract class b
{
  protected HashMap<a, Integer> a = a();
  
  private HashMap<a, Integer> a()
  {
    HashMap localHashMap = new HashMap(a.values().length);
    localHashMap.put(a.a, Integer.valueOf(-16777216));
    localHashMap.put(a.b, Integer.valueOf(-32));
    localHashMap.put(a.c, Integer.valueOf(-32));
    localHashMap.put(a.d, Integer.valueOf(-6832092));
    localHashMap.put(a.e, Integer.valueOf(-32));
    localHashMap.put(a.f, Integer.valueOf(-12537601));
    localHashMap.put(a.g, Integer.valueOf(-8355712));
    localHashMap.put(a.h, Integer.valueOf(545818760));
    localHashMap.put(a.i, Integer.valueOf(-5592406));
    localHashMap.put(a.j, Integer.valueOf(-12615841));
    localHashMap.put(a.k, Integer.valueOf(-3129123));
    localHashMap.put(a.l, Integer.valueOf(-14008065));
    localHashMap.put(a.m, Integer.valueOf(-10452737));
    localHashMap.put(a.n, Integer.valueOf(-2276216));
    localHashMap.put(a.o, Integer.valueOf(-8355712));
    return localHashMap;
  }
  
  public int a(int paramInt)
  {
    a locala;
    if (paramInt != 10)
    {
      if ((paramInt != 30) && (paramInt != 40)) {}
      switch (paramInt)
      {
      default: 
        switch (paramInt)
        {
        default: 
          switch (paramInt)
          {
          default: 
            r.a("Invalid token type");
          }
          break;
        }
      case 0: 
        locala = a.a;
        break;
        locala = a.n;
        break;
      case 4: 
        locala = a.m;
        break;
      case 3: 
        locala = a.l;
        break;
      case 1: 
        locala = a.k;
        break;
        locala = a.j;
        break;
      }
    }
    else
    {
      locala = a.o;
    }
    return a(locala);
  }
  
  public int a(a parama)
  {
    Object localObject = (Integer)this.a.get(parama);
    if (localObject == null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Color not specified for ");
      ((StringBuilder)localObject).append(parama);
      r.a(((StringBuilder)localObject).toString());
      return 0;
    }
    return ((Integer)localObject).intValue();
  }
  
  public void a(a parama, int paramInt)
  {
    this.a.put(parama, Integer.valueOf(paramInt));
  }
  
  public static enum a
  {
    private a() {}
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\a\a\b\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */