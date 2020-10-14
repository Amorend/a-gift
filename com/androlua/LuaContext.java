package com.androlua;

import android.content.Context;
import com.luajava.LuaState;
import java.util.ArrayList;
import java.util.Map;

public abstract interface LuaContext
{
  public abstract void call(String paramString, Object... paramVarArgs);
  
  public abstract Object doFile(String paramString, Object... paramVarArgs);
  
  public abstract ArrayList<ClassLoader> getClassLoaders();
  
  public abstract Context getContext();
  
  public abstract Map getGlobalData();
  
  public abstract int getHeight();
  
  public abstract String getLuaCpath();
  
  public abstract String getLuaDir();
  
  public abstract String getLuaDir(String paramString);
  
  public abstract String getLuaExtDir();
  
  public abstract String getLuaExtDir(String paramString);
  
  public abstract String getLuaExtPath(String paramString);
  
  public abstract String getLuaExtPath(String paramString1, String paramString2);
  
  public abstract String getLuaLpath();
  
  public abstract String getLuaPath();
  
  public abstract String getLuaPath(String paramString);
  
  public abstract String getLuaPath(String paramString1, String paramString2);
  
  public abstract LuaState getLuaState();
  
  public abstract Object getSharedData(String paramString);
  
  public abstract Object getSharedData(String paramString, Object paramObject);
  
  public abstract int getWidth();
  
  public abstract void regGc(LuaGcable paramLuaGcable);
  
  public abstract void sendError(String paramString, Exception paramException);
  
  public abstract void sendMsg(String paramString);
  
  public abstract void set(String paramString, Object paramObject);
  
  public abstract void setLuaExtDir(String paramString);
  
  public abstract boolean setSharedData(String paramString, Object paramObject);
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */