package com.androlua;

import android.content.Context;
import android.view.View;
import com.luajava.LuaException;
import com.luajava.LuaObject;
import com.luajava.LuaTable;

public class LuaView
  extends View
{
  private LuaTable a;
  private LuaObject b;
  
  public LuaView(Context paramContext)
  {
    super(paramContext);
  }
  
  public LuaView(Context paramContext, LuaTable paramLuaTable)
  {
    super(paramContext);
    this.a = paramLuaTable;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (this.a != null) {
      try
      {
        this.b = this.a.getField("onMeasure");
        if (this.b.isFunction())
        {
          this.b.call(new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), this });
          return;
        }
      }
      catch (LuaException localLuaException)
      {
        localLuaException.printStackTrace();
      }
    }
    super.onMeasure(paramInt1, paramInt2);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */