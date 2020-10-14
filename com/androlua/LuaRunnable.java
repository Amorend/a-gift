package com.androlua;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.luajava.JavaFunction;
import com.luajava.LuaException;
import com.luajava.LuaMetaTable;
import com.luajava.LuaObject;
import com.luajava.LuaState;
import com.luajava.LuaStateFactory;
import java.util.regex.Pattern;

public class LuaRunnable
  extends Thread
  implements LuaGcable, LuaMetaTable, Runnable
{
  private LuaState a;
  private Handler b;
  private LuaContext c;
  private boolean d;
  private String e;
  private Object[] f = new Object[0];
  private byte[] g;
  public boolean isRun = false;
  
  public LuaRunnable(LuaContext paramLuaContext, LuaObject paramLuaObject)
  {
    this(paramLuaContext, paramLuaObject, false, null);
  }
  
  public LuaRunnable(LuaContext paramLuaContext, LuaObject paramLuaObject, boolean paramBoolean)
  {
    this(paramLuaContext, paramLuaObject, paramBoolean, null);
  }
  
  public LuaRunnable(LuaContext paramLuaContext, LuaObject paramLuaObject, boolean paramBoolean, Object[] paramArrayOfObject)
  {
    this.c = paramLuaContext;
    if (paramArrayOfObject != null) {
      this.f = paramArrayOfObject;
    }
    this.d = paramBoolean;
    this.g = paramLuaObject.dump();
  }
  
  public LuaRunnable(LuaContext paramLuaContext, LuaObject paramLuaObject, Object[] paramArrayOfObject)
  {
    this(paramLuaContext, paramLuaObject, false, paramArrayOfObject);
  }
  
  public LuaRunnable(LuaContext paramLuaContext, String paramString)
  {
    this(paramLuaContext, paramString, false, null);
  }
  
  public LuaRunnable(LuaContext paramLuaContext, String paramString, boolean paramBoolean)
  {
    this(paramLuaContext, paramString, paramBoolean, null);
  }
  
  public LuaRunnable(LuaContext paramLuaContext, String paramString, boolean paramBoolean, Object[] paramArrayOfObject)
  {
    paramLuaContext.regGc(this);
    this.c = paramLuaContext;
    this.e = paramString;
    this.d = paramBoolean;
    if (paramArrayOfObject != null) {
      this.f = paramArrayOfObject;
    }
  }
  
  public LuaRunnable(LuaContext paramLuaContext, String paramString, Object[] paramArrayOfObject)
  {
    this(paramLuaContext, paramString, false, paramArrayOfObject);
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
  
  private void a()
  {
    this.a = LuaStateFactory.newLuaState();
    this.a.openLibs();
    this.a.pushJavaObject(this.c.getContext());
    LuaState localLuaState;
    if ((this.c instanceof LuaActivity)) {
      localLuaState = this.a;
    }
    for (String str = "activity";; str = "service")
    {
      localLuaState.setGlobal(str);
      break;
      if (!(this.c instanceof LuaService)) {
        break;
      }
      localLuaState = this.a;
    }
    this.a.pushJavaObject(this);
    this.a.setGlobal("this");
    this.a.pushContext(this.c);
    new LuaPrint(this.c, this.a).register("print");
    this.a.getGlobal("package");
    this.a.pushString(this.c.getLuaLpath());
    this.a.setField(-2, "path");
    this.a.pushString(this.c.getLuaCpath());
    this.a.setField(-2, "cpath");
    this.a.pop(1);
    new JavaFunction(this.a)
    {
      public int execute()
      {
        LuaRunnable.a(LuaRunnable.this).set(this.b.toString(2), this.b.toJavaObject(3));
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
          localLuaContext = LuaRunnable.a(LuaRunnable.this);
          str = this.b.toString(2);
        }
        else
        {
          if (j != 2) {
            break label109;
          }
          localLuaContext = LuaRunnable.a(LuaRunnable.this);
          str = this.b.toString(2);
          arrayOfObject = new Object[0];
        }
        localLuaContext.call(str, arrayOfObject);
        label109:
        return 0;
      }
    }.register("call");
  }
  
  private void a(String paramString, Object paramObject)
  {
    try
    {
      this.a.pushObjectValue(paramObject);
      this.a.setGlobal(paramString);
      return;
    }
    catch (LuaException paramString)
    {
      this.c.sendMsg(paramString.getMessage());
    }
  }
  
  private void a(String paramString, Object... paramVarArgs)
  {
    try
    {
      if (Pattern.matches("^\\w+$", paramString))
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramString);
        localStringBuilder.append(".lua");
        doAsset(localStringBuilder.toString(), paramVarArgs);
        return;
      }
      if (Pattern.matches("^[\\w\\.\\_/]+$", paramString))
      {
        this.a.getGlobal("luajava");
        this.a.pushString(this.c.getLuaDir());
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
      paramVarArgs = this.c;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(toString());
      localStringBuilder.append(" ");
      localStringBuilder.append(paramString.getMessage());
      paramVarArgs.sendMsg(localStringBuilder.toString());
      quit();
    }
  }
  
  private void a(byte[] paramArrayOfByte, Object... paramVarArgs)
  {
    try
    {
      this.a.setTop(0);
      int j = this.a.LloadBuffer(paramArrayOfByte, "TimerTask");
      int i = j;
      if (j == 0)
      {
        this.a.getGlobal("debug");
        this.a.getField(-1, "traceback");
        this.a.remove(-2);
        this.a.insert(-2);
        j = paramVarArgs.length;
        i = 0;
        while (i < j)
        {
          this.a.pushObjectValue(paramVarArgs[i]);
          i += 1;
        }
        j = this.a.pcall(j, 0, -2 - j);
        i = j;
        if (j == 0) {
          return;
        }
      }
      paramArrayOfByte = new StringBuilder();
      paramArrayOfByte.append(a(i));
      paramArrayOfByte.append(": ");
      paramArrayOfByte.append(this.a.toString(-1));
      throw new LuaException(paramArrayOfByte.toString());
    }
    catch (Exception paramArrayOfByte)
    {
      paramVarArgs = this.c;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(toString());
      localStringBuilder.append(" ");
      localStringBuilder.append(paramArrayOfByte.getMessage());
      paramVarArgs.sendMsg(localStringBuilder.toString());
      quit();
    }
  }
  
  private void b(String paramString, Object... paramVarArgs)
  {
    this.a.setTop(0);
    int j = this.a.LloadFile(paramString);
    int i = j;
    if (j == 0)
    {
      this.a.getGlobal("debug");
      this.a.getField(-1, "traceback");
      this.a.remove(-2);
      this.a.insert(-2);
      j = paramVarArgs.length;
      i = 0;
      while (i < j)
      {
        this.a.pushObjectValue(paramVarArgs[i]);
        i += 1;
      }
      j = this.a.pcall(j, 0, -2 - j);
      i = j;
      if (j == 0) {
        return;
      }
    }
    paramString = new StringBuilder();
    paramString.append(a(i));
    paramString.append(": ");
    paramString.append(this.a.toString(-1));
    throw new LuaException(paramString.toString());
  }
  
  private void c(String paramString, Object... paramVarArgs)
  {
    this.a.setTop(0);
    int j = this.a.LloadString(paramString);
    int i = j;
    if (j == 0)
    {
      this.a.getGlobal("debug");
      this.a.getField(-1, "traceback");
      this.a.remove(-2);
      this.a.insert(-2);
      j = paramVarArgs.length;
      i = 0;
      while (i < j)
      {
        this.a.pushObjectValue(paramVarArgs[i]);
        i += 1;
      }
      j = this.a.pcall(j, 0, -2 - j);
      i = j;
      if (j == 0) {
        return;
      }
    }
    paramString = new StringBuilder();
    paramString.append(a(i));
    paramString.append(": ");
    paramString.append(this.a.toString(-1));
    throw new LuaException(paramString.toString());
  }
  
  private void d(String paramString, Object... paramVarArgs)
  {
    try
    {
      localObject = this.a;
      int i = 0;
      ((LuaState)localObject).setTop(0);
      this.a.getGlobal(paramString);
      if (this.a.isFunction(-1))
      {
        this.a.getGlobal("debug");
        this.a.getField(-1, "traceback");
        this.a.remove(-2);
        this.a.insert(-2);
        int j = paramVarArgs.length;
        while (i < j)
        {
          this.a.pushObjectValue(paramVarArgs[i]);
          i += 1;
        }
        i = this.a.pcall(j, 1, -2 - j);
        if (i == 0) {
          return;
        }
        paramVarArgs = new StringBuilder();
        paramVarArgs.append(a(i));
        paramVarArgs.append(": ");
        paramVarArgs.append(this.a.toString(-1));
        throw new LuaException(paramVarArgs.toString());
      }
    }
    catch (LuaException paramVarArgs)
    {
      Object localObject = this.c;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append(" ");
      localStringBuilder.append(paramVarArgs.getMessage());
      ((LuaContext)localObject).sendMsg(localStringBuilder.toString());
    }
  }
  
  public Object __call(Object[] paramArrayOfObject)
  {
    return null;
  }
  
  public Object __index(final String paramString)
  {
    new LuaMetaTable()
    {
      public Object __call(Object[] paramAnonymousArrayOfObject)
      {
        LuaRunnable.this.call(paramString, paramAnonymousArrayOfObject);
        return null;
      }
      
      public Object __index(String paramAnonymousString)
      {
        return null;
      }
      
      public void __newIndex(String paramAnonymousString, Object paramAnonymousObject) {}
    };
  }
  
  public void __newIndex(String paramString, Object paramObject)
  {
    set(paramString, paramObject);
  }
  
  public void call(String paramString)
  {
    push(3, paramString);
  }
  
  public void call(String paramString, Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject.length == 0)
    {
      push(3, paramString);
      return;
    }
    push(1, paramString, paramArrayOfObject);
  }
  
  public void doAsset(String paramString, Object... paramVarArgs)
  {
    byte[] arrayOfByte = LuaUtil.readAsset(this.c.getContext(), paramString);
    this.a.setTop(0);
    int j = this.a.LloadBuffer(arrayOfByte, paramString);
    int i = j;
    if (j == 0)
    {
      this.a.getGlobal("debug");
      this.a.getField(-1, "traceback");
      this.a.remove(-2);
      this.a.insert(-2);
      j = paramVarArgs.length;
      i = 0;
      while (i < j)
      {
        this.a.pushObjectValue(paramVarArgs[i]);
        i += 1;
      }
      j = this.a.pcall(j, 0, -2 - j);
      i = j;
      if (j == 0) {
        return;
      }
    }
    paramString = new StringBuilder();
    paramString.append(a(i));
    paramString.append(": ");
    paramString.append(this.a.toString(-1));
    throw new LuaException(paramString.toString());
  }
  
  public void gc()
  {
    quit();
  }
  
  public Object get(String paramString)
  {
    this.a.getGlobal(paramString);
    return this.a.toJavaObject(-1);
  }
  
  public void push(int paramInt, String paramString)
  {
    if (!this.isRun)
    {
      this.c.sendMsg("thread is not running");
      return;
    }
    Message localMessage = new Message();
    Bundle localBundle = new Bundle();
    localBundle.putString("data", paramString);
    localMessage.setData(localBundle);
    localMessage.what = paramInt;
    this.b.sendMessage(localMessage);
  }
  
  public void push(int paramInt, String paramString, Object[] paramArrayOfObject)
  {
    if (!this.isRun)
    {
      this.c.sendMsg("thread is not running");
      return;
    }
    Message localMessage = new Message();
    Bundle localBundle = new Bundle();
    localBundle.putString("data", paramString);
    localBundle.putSerializable("args", paramArrayOfObject);
    localMessage.setData(localBundle);
    localMessage.what = paramInt;
    this.b.sendMessage(localMessage);
  }
  
  public void quit()
  {
    if (this.isRun)
    {
      this.isRun = false;
      this.b.getLooper().quit();
    }
  }
  
  public void run()
  {
    try
    {
      if (this.a == null)
      {
        a();
        if (this.g != null) {
          a(this.g, this.f);
        } else {
          a(this.e, this.f);
        }
      }
      if (this.d)
      {
        Looper.prepare();
        this.b = new ThreadHandler(null);
        this.isRun = true;
        this.a.getGlobal("run");
        if (!this.a.isNil(-1))
        {
          this.a.pop(1);
          d("run", new Object[0]);
        }
        Looper.loop();
      }
      this.isRun = false;
      this.a.gc(2, 1);
      System.gc();
      return;
    }
    catch (LuaException localLuaException)
    {
      this.c.sendMsg(localLuaException.getMessage());
    }
  }
  
  public void set(String paramString, Object paramObject)
  {
    push(4, paramString, new Object[] { paramObject });
  }
  
  private class ThreadHandler
    extends Handler
  {
    private ThreadHandler() {}
    
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      Bundle localBundle = paramMessage.getData();
      switch (paramMessage.what)
      {
      default: 
        return;
      case 4: 
        LuaRunnable.a(LuaRunnable.this, localBundle.getString("data"), ((Object[])localBundle.getSerializable("args"))[0]);
        return;
      case 3: 
        LuaRunnable.b(LuaRunnable.this, localBundle.getString("data"), new Object[0]);
        return;
      case 2: 
        LuaRunnable.a(LuaRunnable.this, localBundle.getString("data"), new Object[0]);
        return;
      case 1: 
        LuaRunnable.b(LuaRunnable.this, localBundle.getString("data"), (Object[])localBundle.getSerializable("args"));
        return;
      }
      LuaRunnable.a(LuaRunnable.this, localBundle.getString("data"), (Object[])localBundle.getSerializable("args"));
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaRunnable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */