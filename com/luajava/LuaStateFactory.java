package com.luajava;

import java.util.HashMap;
import java.util.Map;

public final class LuaStateFactory
{
  private static final Map<Long, LuaState> a = new HashMap();
  
  public static LuaState getExistingState(long paramLong)
  {
    try
    {
      LuaState localLuaState2 = (LuaState)a.get(Long.valueOf(paramLong));
      LuaState localLuaState1 = localLuaState2;
      if (localLuaState2 == null)
      {
        localLuaState1 = new LuaState(paramLong);
        a.put(Long.valueOf(paramLong), localLuaState1);
      }
      return localLuaState1;
    }
    finally {}
  }
  
  public static long insertLuaState(LuaState paramLuaState)
  {
    try
    {
      a.put(Long.valueOf(paramLuaState.getPointer()), paramLuaState);
      long l = paramLuaState.getPointer();
      return l;
    }
    finally
    {
      paramLuaState = finally;
      throw paramLuaState;
    }
  }
  
  public static LuaState newLuaState()
  {
    try
    {
      LuaState localLuaState = new LuaState();
      a.put(Long.valueOf(localLuaState.getPointer()), localLuaState);
      return localLuaState;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static void removeLuaState(long paramLong)
  {
    try
    {
      a.put(Long.valueOf(paramLong), null);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\luajava\LuaStateFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */