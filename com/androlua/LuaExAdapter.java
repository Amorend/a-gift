package com.androlua;

import com.luajava.LuaTable;

public class LuaExAdapter
  extends LuaExpandableListAdapter
{
  public LuaExAdapter(LuaContext paramLuaContext, LuaTable paramLuaTable1, LuaTable paramLuaTable2)
  {
    this(paramLuaContext, null, null, paramLuaTable1, paramLuaTable2);
  }
  
  public LuaExAdapter(LuaContext paramLuaContext, LuaTable<Integer, LuaTable<String, Object>> paramLuaTable, LuaTable<Integer, LuaTable<Integer, LuaTable<String, Object>>> paramLuaTable1, LuaTable paramLuaTable2, LuaTable paramLuaTable3)
  {
    super(paramLuaContext, paramLuaTable, paramLuaTable1, paramLuaTable2, paramLuaTable3);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaExAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */