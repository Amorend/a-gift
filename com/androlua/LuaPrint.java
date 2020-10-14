package com.androlua;

import com.luajava.JavaFunction;
import com.luajava.LuaState;

public class LuaPrint
  extends JavaFunction
{
  private LuaState a;
  private LuaContext c;
  private StringBuilder d = new StringBuilder();
  
  public LuaPrint(LuaContext paramLuaContext, LuaState paramLuaState)
  {
    super(paramLuaState);
    this.a = paramLuaState;
    this.c = paramLuaContext;
  }
  
  public int execute()
  {
    int j = this.a.getTop();
    int i = 2;
    if (j < 2)
    {
      this.c.sendMsg("");
      return 0;
    }
    while (i <= this.a.getTop())
    {
      j = this.a.type(i);
      Object localObject1 = null;
      String str = this.a.typeName(j);
      if (str.equals("userdata"))
      {
        Object localObject2 = this.a.toJavaObject(i);
        if (localObject2 != null) {
          localObject1 = localObject2.toString();
        }
      }
      else if (str.equals("boolean"))
      {
        if (this.a.toBoolean(i)) {
          localObject1 = "true";
        } else {
          localObject1 = "false";
        }
      }
      else
      {
        localObject1 = this.a.toString(i);
      }
      if (localObject1 == null) {
        localObject1 = str;
      }
      this.d.append("\t");
      this.d.append((String)localObject1);
      this.d.append("\t");
      i += 1;
    }
    this.c.sendMsg(this.d.toString().substring(1, this.d.length() - 1));
    this.d.setLength(0);
    return 0;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaPrint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */