package com.luajava;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class LuaTable<K, V>
  extends LuaObject
  implements Map<K, V>
{
  public LuaTable(LuaState paramLuaState)
  {
    super(paramLuaState);
    paramLuaState.newTable();
    a(-1);
  }
  
  protected LuaTable(LuaState paramLuaState, int paramInt)
  {
    super(paramLuaState, paramInt);
  }
  
  public void clear()
  {
    push();
    this.b.pushNil();
    while (this.b.next(-2) != 0)
    {
      this.b.pop(1);
      this.b.pushValue(-1);
      this.b.pushNil();
      this.b.setTable(-4);
    }
    this.b.pop(1);
  }
  
  public boolean containsKey(Object paramObject)
  {
    push();
    for (;;)
    {
      try
      {
        this.b.pushObjectValue(paramObject);
        if (this.b.getTable(-2) == 0)
        {
          bool = true;
          this.b.pop(1);
          this.b.pop(1);
          return bool;
        }
      }
      catch (LuaException paramObject)
      {
        return false;
      }
      boolean bool = false;
    }
  }
  
  public boolean containsValue(Object paramObject)
  {
    return false;
  }
  
  public Set<Map.Entry<K, V>> entrySet()
  {
    HashSet localHashSet = new HashSet();
    push();
    this.b.pushNil();
    for (;;)
    {
      if (this.b.next(-2) != 0) {}
      try
      {
        localHashSet.add(new LuaEntry(this.b.toJavaObject(-2), this.b.toJavaObject(-1)));
        this.b.pop(1);
        continue;
        this.b.pop(1);
        return localHashSet;
      }
      catch (LuaException localLuaException)
      {
        for (;;) {}
      }
    }
  }
  
  public V get(Object paramObject)
  {
    push();
    for (;;)
    {
      try
      {
        this.b.pushObjectValue(paramObject);
        this.b.getTable(-2);
        paramObject = this.b.toJavaObject(-1);
      }
      catch (LuaException paramObject)
      {
        continue;
      }
      try
      {
        this.b.pop(1);
      }
      catch (LuaException localLuaException) {}
    }
    paramObject = null;
    this.b.pop(1);
    return (V)paramObject;
  }
  
  public boolean isEmpty()
  {
    push();
    this.b.pushNil();
    boolean bool;
    if (this.b.next(-2) == 0) {
      bool = true;
    } else {
      bool = false;
    }
    if (bool)
    {
      this.b.pop(1);
      return bool;
    }
    this.b.pop(3);
    return bool;
  }
  
  public boolean isList()
  {
    push();
    if (this.b.rawLen(-1) != 0)
    {
      pop();
      return true;
    }
    this.b.pushNil();
    boolean bool;
    if (this.b.next(-2) == 0) {
      bool = true;
    } else {
      bool = false;
    }
    if (bool)
    {
      this.b.pop(1);
      return bool;
    }
    this.b.pop(3);
    return bool;
  }
  
  public Set<K> keySet()
  {
    HashSet localHashSet = new HashSet();
    push();
    this.b.pushNil();
    for (;;)
    {
      if (this.b.next(-2) != 0) {}
      try
      {
        localHashSet.add(this.b.toJavaObject(-2));
        this.b.pop(1);
        continue;
        this.b.pop(1);
        return localHashSet;
      }
      catch (LuaException localLuaException)
      {
        for (;;) {}
      }
    }
  }
  
  public int length()
  {
    push();
    int i = this.b.rawLen(-1);
    pop();
    return i;
  }
  
  public V put(K paramK, V paramV)
  {
    push();
    try
    {
      this.b.pushObjectValue(paramK);
      this.b.pushObjectValue(paramV);
      this.b.setTable(-3);
      this.b.pop(1);
      return null;
    }
    catch (LuaException paramK)
    {
      for (;;) {}
    }
  }
  
  public void putAll(Map paramMap) {}
  
  public V remove(Object paramObject)
  {
    push();
    try
    {
      this.b.pushObjectValue(paramObject);
      this.b.setTable(-2);
      this.b.pop(1);
      return null;
    }
    catch (LuaException paramObject)
    {
      for (;;) {}
    }
  }
  
  public int size()
  {
    push();
    this.b.pushNil();
    int i = 0;
    while (this.b.next(-2) != 0)
    {
      i += 1;
      this.b.pop(1);
    }
    this.b.pop(1);
    return i;
  }
  
  public Collection values()
  {
    return null;
  }
  
  public class LuaEntry<K, V>
    implements Map.Entry<K, V>
  {
    private K b;
    private V c;
    
    public LuaEntry(V paramV)
    {
      this.b = paramV;
      Object localObject;
      this.c = localObject;
    }
    
    public K getKey()
    {
      return (K)this.b;
    }
    
    public V getValue()
    {
      return (V)this.c;
    }
    
    public V setValue(V paramV)
    {
      Object localObject = this.c;
      this.c = paramV;
      return (V)localObject;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\luajava\LuaTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */