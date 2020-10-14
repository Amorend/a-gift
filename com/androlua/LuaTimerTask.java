package com.androlua;

import com.androlua.util.TimerTaskX;
import com.luajava.JavaFunction;
import com.luajava.LuaException;
import com.luajava.LuaObject;
import com.luajava.LuaState;
import com.luajava.LuaStateFactory;
import java.util.regex.Pattern;

public class LuaTimerTask
  extends TimerTaskX
{
  private LuaState a;
  private LuaContext g;
  private String h;
  private Object[] i = new Object[0];
  private boolean j = true;
  private byte[] k;
  
  public LuaTimerTask(LuaContext paramLuaContext, LuaObject paramLuaObject)
  {
    this(paramLuaContext, paramLuaObject, null);
  }
  
  public LuaTimerTask(LuaContext paramLuaContext, LuaObject paramLuaObject, Object[] paramArrayOfObject)
  {
    this.g = paramLuaContext;
    if (paramArrayOfObject != null) {
      this.i = paramArrayOfObject;
    }
    this.k = paramLuaObject.dump();
  }
  
  public LuaTimerTask(LuaContext paramLuaContext, String paramString)
  {
    this(paramLuaContext, paramString, null);
  }
  
  public LuaTimerTask(LuaContext paramLuaContext, String paramString, Object[] paramArrayOfObject)
  {
    this.g = paramLuaContext;
    this.h = paramString;
    if (paramArrayOfObject != null) {
      this.i = paramArrayOfObject;
    }
  }
  
  private String a(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unknown error ");
      localStringBuilder.append(paramInt);
      return localStringBuilder.toString();
    case 6: 
      return "error error";
    case 5: 
      return "GC error";
    case 4: 
      return "Out of memory";
    case 3: 
      return "Syntax error";
    case 2: 
      return "Runtime error";
    }
    return "Yield error";
  }
  
  private void a(String paramString, Object... paramVarArgs)
  {
    try
    {
      if (Pattern.matches("^\\w+$", paramString))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramString);
        localStringBuilder.append(".lua");
        doAsset(localStringBuilder.toString(), paramVarArgs);
        return;
      }
      if (Pattern.matches("^[\\w\\.\\_/]+$", paramString))
      {
        this.a.getGlobal("luajava");
        this.a.pushString(this.g.getLuaDir());
        this.a.setField(-2, "luadir");
        this.a.pushString(paramString);
        this.a.setField(-2, "luapath");
        this.a.pop(1);
        b(paramString, paramVarArgs);
        return;
      }
      c(paramString, paramVarArgs);
      return;
    }
    catch (Exception paramString)
    {
      this.g.sendError(toString(), paramString);
    }
  }
  
  private void a(byte[] paramArrayOfByte, Object... paramVarArgs)
  {
    this.a.setTop(0);
    int n = this.a.LloadBuffer(paramArrayOfByte, "TimerTask");
    int m = n;
    if (n == 0)
    {
      this.a.getGlobal("debug");
      this.a.getField(-1, "traceback");
      this.a.remove(-2);
      this.a.insert(-2);
      n = paramVarArgs.length;
      m = 0;
      while (m < n)
      {
        this.a.pushObjectValue(paramVarArgs[m]);
        m += 1;
      }
      n = this.a.pcall(n, 0, -2 - n);
      m = n;
      if (n == 0) {
        return;
      }
    }
    paramArrayOfByte = new StringBuilder();
    paramArrayOfByte.append(a(m));
    paramArrayOfByte.append(": ");
    paramArrayOfByte.append(this.a.toString(-1));
    throw new LuaException(paramArrayOfByte.toString());
  }
  
  private void b()
  {
    this.a = LuaStateFactory.newLuaState();
    this.a.openLibs();
    this.a.pushJavaObject(this.g);
    LuaState localLuaState;
    if ((this.g instanceof LuaActivity)) {
      localLuaState = this.a;
    }
    for (String str = "activity";; str = "service")
    {
      localLuaState.setGlobal(str);
      break;
      if (!(this.g instanceof LuaService)) {
        break;
      }
      localLuaState = this.a;
    }
    this.a.pushJavaObject(this);
    this.a.setGlobal("this");
    this.a.pushContext(this.g);
    new LuaPrint(this.g, this.a).register("print");
    this.a.getGlobal("package");
    this.a.pushString(this.g.getLuaLpath());
    this.a.setField(-2, "path");
    this.a.pushString(this.g.getLuaCpath());
    this.a.setField(-2, "cpath");
    this.a.pop(1);
    new JavaFunction(this.a)
    {
      public int execute()
      {
        LuaTimerTask.a(LuaTimerTask.this).set(this.b.toString(2), this.b.toJavaObject(3));
        return 0;
      }
    }.register("set");
    new JavaFunction(this.a)
    {
      public int execute()
      {
        int j = this.b.getTop();
        Object[] arrayOfObject;
        LuaContext localLuaContext;
        String str;
        if (j > 2)
        {
          arrayOfObject = new Object[j - 2];
          int i = 3;
          while (i <= j)
          {
            arrayOfObject[(i - 3)] = this.b.toJavaObject(i);
            i += 1;
          }
          localLuaContext = LuaTimerTask.a(LuaTimerTask.this);
          str = this.b.toString(2);
        }
        else
        {
          if (j != 2) {
            break label109;
          }
          localLuaContext = LuaTimerTask.a(LuaTimerTask.this);
          str = this.b.toString(2);
          arrayOfObject = new Object[0];
        }
        localLuaContext.call(str, arrayOfObject);
        label109:
        return 0;
      }
    }.register("call");
  }
  
  private void b(String paramString, Object... paramVarArgs)
  {
    this.a.setTop(0);
    int n = this.a.LloadFile(paramString);
    int m = n;
    if (n == 0)
    {
      this.a.getGlobal("debug");
      this.a.getField(-1, "traceback");
      this.a.remove(-2);
      this.a.insert(-2);
      n = paramVarArgs.length;
      m = 0;
      while (m < n)
      {
        this.a.pushObjectValue(paramVarArgs[m]);
        m += 1;
      }
      n = this.a.pcall(n, 0, -2 - n);
      m = n;
      if (n == 0) {
        return;
      }
    }
    paramString = new StringBuilder();
    paramString.append(a(m));
    paramString.append(": ");
    paramString.append(this.a.toString(-1));
    throw new LuaException(paramString.toString());
  }
  
  private void c(String paramString, Object... paramVarArgs)
  {
    this.a.setTop(0);
    int n = this.a.LloadString(paramString);
    int m = n;
    if (n == 0)
    {
      this.a.getGlobal("debug");
      this.a.getField(-1, "traceback");
      this.a.remove(-2);
      this.a.insert(-2);
      n = paramVarArgs.length;
      m = 0;
      while (m < n)
      {
        this.a.pushObjectValue(paramVarArgs[m]);
        m += 1;
      }
      n = this.a.pcall(n, 0, -2 - n);
      m = n;
      if (n == 0) {
        return;
      }
    }
    paramString = new StringBuilder();
    paramString.append(a(m));
    paramString.append(": ");
    paramString.append(this.a.toString(-1));
    throw new LuaException(paramString.toString());
  }
  
  private void d(String paramString, Object... paramVarArgs)
  {
    try
    {
      localObject = this.a;
      int m = 0;
      ((LuaState)localObject).setTop(0);
      this.a.getGlobal(paramString);
      if (this.a.isFunction(-1))
      {
        this.a.getGlobal("debug");
        this.a.getField(-1, "traceback");
        this.a.remove(-2);
        this.a.insert(-2);
        int n = paramVarArgs.length;
        while (m < n)
        {
          this.a.pushObjectValue(paramVarArgs[m]);
          m += 1;
        }
        m = this.a.pcall(n, 1, -2 - n);
        if (m == 0) {
          return;
        }
        paramVarArgs = new StringBuilder();
        paramVarArgs.append(a(m));
        paramVarArgs.append(": ");
        paramVarArgs.append(this.a.toString(-1));
        throw new LuaException(paramVarArgs.toString());
      }
    }
    catch (LuaException paramVarArgs)
    {
      Object localObject = this.g;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(toString());
      localStringBuilder.append(" ");
      localStringBuilder.append(paramString);
      ((LuaContext)localObject).sendError(localStringBuilder.toString(), paramVarArgs);
    }
  }
  
  public boolean cancel()
  {
    return super.cancel();
  }
  
  public void doAsset(String paramString, Object... paramVarArgs)
  {
    byte[] arrayOfByte = LuaUtil.readAsset(this.g.getContext(), paramString);
    this.a.setTop(0);
    int n = this.a.LloadBuffer(arrayOfByte, paramString);
    int m = n;
    if (n == 0)
    {
      this.a.getGlobal("debug");
      this.a.getField(-1, "traceback");
      this.a.remove(-2);
      this.a.insert(-2);
      n = paramVarArgs.length;
      m = 0;
      while (m < n)
      {
        this.a.pushObjectValue(paramVarArgs[m]);
        m += 1;
      }
      n = this.a.pcall(n, 0, -2 - n);
      m = n;
      if (n == 0) {
        return;
      }
    }
    paramString = new StringBuilder();
    paramString.append(a(m));
    paramString.append(": ");
    paramString.append(this.a.toString(-1));
    throw new LuaException(paramString.toString());
  }
  
  public Object get(String paramString)
  {
    this.a.getGlobal(paramString);
    return this.a.toJavaObject(-1);
  }
  
  public boolean isEnabled()
  {
    return this.j;
  }
  
  public void run()
  {
    if (!this.j) {
      return;
    }
    try
    {
      Object localObject;
      Object[] arrayOfObject;
      if (this.a == null)
      {
        b();
        if (this.k != null)
        {
          localObject = this.k;
          arrayOfObject = this.i;
          a((byte[])localObject, arrayOfObject);
        }
        else
        {
          localObject = this.h;
        }
      }
      else
      {
        for (arrayOfObject = this.i;; arrayOfObject = this.i)
        {
          a((String)localObject, arrayOfObject);
          break label148;
          this.a.getGlobal("run");
          if (!this.a.isNil(-1))
          {
            d("run", new Object[0]);
            break label148;
          }
          if (this.k != null)
          {
            localObject = this.k;
            arrayOfObject = this.i;
            break;
          }
          localObject = this.h;
        }
      }
      label148:
      return;
    }
    catch (LuaException localLuaException)
    {
      this.g.sendError(toString(), localLuaException);
      this.a.gc(2, 1);
      System.gc();
    }
  }
  
  public void set(String paramString, Object paramObject)
  {
    this.a.pushObjectValue(paramObject);
    this.a.setGlobal(paramString);
  }
  
  public void setArg(LuaObject paramLuaObject)
  {
    this.i = paramLuaObject.asArray();
  }
  
  public void setArg(Object[] paramArrayOfObject)
  {
    this.i = paramArrayOfObject;
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    this.j = paramBoolean;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaTimerTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */