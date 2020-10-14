package com.androlua;

import com.androlua.util.TimerX;
import com.luajava.LuaObject;

public class LuaTimer
  extends TimerX
  implements LuaGcable
{
  private LuaTimerTask a;
  
  public LuaTimer(LuaContext paramLuaContext, LuaObject paramLuaObject)
  {
    this(paramLuaContext, paramLuaObject, null);
  }
  
  public LuaTimer(LuaContext paramLuaContext, LuaObject paramLuaObject, Object[] paramArrayOfObject)
  {
    super("LuaTimer");
    paramLuaContext.regGc(this);
    this.a = new LuaTimerTask(paramLuaContext, paramLuaObject, paramArrayOfObject);
  }
  
  public LuaTimer(LuaContext paramLuaContext, String paramString)
  {
    this(paramLuaContext, paramString, null);
  }
  
  public LuaTimer(LuaContext paramLuaContext, String paramString, Object[] paramArrayOfObject)
  {
    super("LuaTimer");
    paramLuaContext.regGc(this);
    this.a = new LuaTimerTask(paramLuaContext, paramString, paramArrayOfObject);
  }
  
  public void gc()
  {
    stop();
  }
  
  public boolean getEnabled()
  {
    return this.a.isEnabled();
  }
  
  public long getPeriod()
  {
    return this.a.getPeriod();
  }
  
  public boolean isEnabled()
  {
    return this.a.isEnabled();
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    this.a.setEnabled(paramBoolean);
  }
  
  public void setPeriod(long paramLong)
  {
    this.a.setPeriod(paramLong);
  }
  
  public void start(long paramLong)
  {
    schedule(this.a, paramLong);
  }
  
  public void start(long paramLong1, long paramLong2)
  {
    schedule(this.a, paramLong1, paramLong2);
  }
  
  public void stop()
  {
    this.a.cancel();
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaTimer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */