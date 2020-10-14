package com.luajava;

import java.io.PrintStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.StringTokenizer;

public class LuaObject
  implements Serializable
{
  protected int a;
  protected final LuaState b;
  
  protected LuaObject(LuaObject paramLuaObject1, LuaObject paramLuaObject2)
  {
    if (paramLuaObject1.getLuaState() != paramLuaObject2.getLuaState()) {
      throw new LuaException("LuaStates must be the same!");
    }
    synchronized (paramLuaObject1.getLuaState())
    {
      if ((!paramLuaObject1.isTable()) && (!paramLuaObject1.isUserdata())) {
        throw new LuaException("Object parent should be a table or userdata .");
      }
      this.b = paramLuaObject1.getLuaState();
      paramLuaObject1.push();
      paramLuaObject2.push();
      this.b.getTable(-2);
      this.b.remove(-2);
      a(-1);
      this.b.pop(1);
      return;
    }
  }
  
  protected LuaObject(LuaObject paramLuaObject, Number paramNumber)
  {
    synchronized (paramLuaObject.getLuaState())
    {
      this.b = paramLuaObject.getLuaState();
      if ((!paramLuaObject.isTable()) && (!paramLuaObject.isUserdata())) {
        throw new LuaException("Object parent should be a table or userdata .");
      }
      paramLuaObject.push();
      this.b.pushNumber(paramNumber.doubleValue());
      this.b.getTable(-2);
      this.b.remove(-2);
      a(-1);
      this.b.pop(1);
      return;
    }
  }
  
  protected LuaObject(LuaObject paramLuaObject, String paramString)
  {
    synchronized (paramLuaObject.getLuaState())
    {
      this.b = paramLuaObject.getLuaState();
      if ((!paramLuaObject.isTable()) && (!paramLuaObject.isUserdata())) {
        throw new LuaException("Object parent should be a table or userdata .");
      }
      paramLuaObject.push();
      this.b.pushString(paramString);
      this.b.getTable(-2);
      this.b.remove(-2);
      a(-1);
      this.b.pop(1);
      return;
    }
  }
  
  protected LuaObject(LuaState paramLuaState)
  {
    this.b = paramLuaState;
  }
  
  protected LuaObject(LuaState paramLuaState, int paramInt)
  {
    try
    {
      this.b = paramLuaState;
      a(paramInt);
      return;
    }
    finally {}
  }
  
  protected LuaObject(LuaState paramLuaState, String paramString)
  {
    try
    {
      this.b = paramLuaState;
      paramLuaState.getGlobal(paramString);
      a(-1);
      paramLuaState.pop(1);
      return;
    }
    finally {}
  }
  
  public LuaObject _call(Object... paramVarArgs)
  {
    return _call_aux(paramVarArgs, 1)[0];
  }
  
  public LuaObject[] _call_aux(Object[] paramArrayOfObject, int paramInt)
  {
    for (;;)
    {
      synchronized (this.b)
      {
        if ((!isFunction()) && (!isTable()) && (!isUserdata())) {
          throw new LuaException("Invalid object. Not a function, table or userdata .");
        }
        int m = this.b.getTop();
        push();
        if (paramArrayOfObject != null)
        {
          int k = paramArrayOfObject.length;
          int i = 0;
          j = k;
          Object localObject;
          if (i < k)
          {
            localObject = paramArrayOfObject[i];
            this.b.pushObjectValue(localObject);
            i += 1;
            continue;
          }
          i = this.b.pcall(j, paramInt, 0);
          if (i != 0)
          {
            if (!this.b.isString(-1)) {
              break label402;
            }
            paramArrayOfObject = this.b.toString(-1);
            this.b.pop(1);
            if (i == 2)
            {
              localObject = new StringBuilder();
              ((StringBuilder)localObject).append("Runtime error. ");
              ((StringBuilder)localObject).append(paramArrayOfObject);
              paramArrayOfObject = (Object[])localObject;
              paramArrayOfObject = paramArrayOfObject.toString();
            }
            else
            {
              if (i == 4)
              {
                localObject = new StringBuilder();
                ((StringBuilder)localObject).append("Memory allocation error. ");
                ((StringBuilder)localObject).append(paramArrayOfObject);
                paramArrayOfObject = (Object[])localObject;
                continue;
              }
              if (i == 6)
              {
                localObject = new StringBuilder();
                ((StringBuilder)localObject).append("Error while running the error handler function. ");
                ((StringBuilder)localObject).append(paramArrayOfObject);
                paramArrayOfObject = (Object[])localObject;
                continue;
              }
              localObject = new StringBuilder();
              ((StringBuilder)localObject).append("Lua Error code ");
              ((StringBuilder)localObject).append(i);
              ((StringBuilder)localObject).append(". ");
              ((StringBuilder)localObject).append(paramArrayOfObject);
              paramArrayOfObject = ((StringBuilder)localObject).toString();
            }
            throw new LuaException(paramArrayOfObject);
          }
          i = paramInt;
          if (paramInt == -1) {
            i = this.b.getTop() - m;
          }
          if (this.b.getTop() - m < i) {
            throw new LuaException("Invalid Number of Results .");
          }
          paramArrayOfObject = new LuaObject[i];
          if (i > 0)
          {
            paramArrayOfObject[(i - 1)] = this.b.getLuaObject(-1);
            this.b.pop(1);
            i -= 1;
            continue;
          }
          return paramArrayOfObject;
        }
      }
      int j = 0;
      continue;
      label402:
      paramArrayOfObject = "";
    }
  }
  
  protected void a(int paramInt)
  {
    synchronized (this.b)
    {
      this.b.pushValue(paramInt);
      this.a = this.b.Lref(-1001000);
      return;
    }
  }
  
  public Object[] asArray()
  {
    for (;;)
    {
      Object localObject1;
      int i;
      synchronized (this.b)
      {
        if (!isTable()) {
          throw new LuaException("Invalid object. Not a table .");
        }
        push();
        int j = this.b.objLen(-1);
        localObject1 = Array.newInstance(Object.class, j);
        i = 1;
        if (i <= j)
        {
          this.b.pushInteger(i);
          this.b.getTable(-2);
        }
      }
      try
      {
        Array.set(localObject1, i - 1, this.b.toJavaObject(-1));
        this.b.pop(1);
        i += 1;
        continue;
        this.b.pop(1);
        localObject1 = (Object[])localObject1;
        return (Object[])localObject1;
        localObject2 = finally;
        throw ((Throwable)localObject2);
      }
      catch (LuaException localLuaException)
      {
        for (;;) {}
      }
    }
  }
  
  /* Error */
  public java.util.Map asMap(LuaState paramLuaState, Class<?> paramClass, int paramInt)
  {
    // Byte code:
    //   0: aload_1
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 30	com/luajava/LuaObject:isTable	()Z
    //   6: ifne +13 -> 19
    //   9: new 21	com/luajava/LuaException
    //   12: dup
    //   13: ldc -103
    //   15: invokespecial 26	com/luajava/LuaException:<init>	(Ljava/lang/String;)V
    //   18: athrow
    //   19: new 180	java/util/HashMap
    //   22: dup
    //   23: invokespecial 181	java/util/HashMap:<init>	()V
    //   26: astore_2
    //   27: aload_0
    //   28: invokevirtual 40	com/luajava/LuaObject:push	()V
    //   31: aload_1
    //   32: invokevirtual 184	com/luajava/LuaState:pushNil	()V
    //   35: aload_1
    //   36: iload_3
    //   37: invokevirtual 187	com/luajava/LuaState:next	(I)I
    //   40: istore 4
    //   42: iload 4
    //   44: ifeq +27 -> 71
    //   47: aload_2
    //   48: aload_1
    //   49: bipush -2
    //   51: invokevirtual 170	com/luajava/LuaState:toJavaObject	(I)Ljava/lang/Object;
    //   54: aload_1
    //   55: iconst_m1
    //   56: invokevirtual 170	com/luajava/LuaState:toJavaObject	(I)Ljava/lang/Object;
    //   59: invokevirtual 191	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   62: pop
    //   63: aload_1
    //   64: iconst_1
    //   65: invokevirtual 55	com/luajava/LuaState:pop	(I)V
    //   68: goto -33 -> 35
    //   71: aload_1
    //   72: iconst_1
    //   73: invokevirtual 55	com/luajava/LuaState:pop	(I)V
    //   76: aload_1
    //   77: monitorexit
    //   78: aload_2
    //   79: areturn
    //   80: astore_2
    //   81: aload_1
    //   82: monitorexit
    //   83: aload_2
    //   84: athrow
    //   85: astore 5
    //   87: goto -24 -> 63
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	90	0	this	LuaObject
    //   0	90	1	paramLuaState	LuaState
    //   0	90	2	paramClass	Class<?>
    //   0	90	3	paramInt	int
    //   40	3	4	i	int
    //   85	1	5	localLuaException	LuaException
    // Exception table:
    //   from	to	target	type
    //   2	19	80	finally
    //   19	35	80	finally
    //   35	42	80	finally
    //   47	63	80	finally
    //   63	68	80	finally
    //   71	78	80	finally
    //   81	83	80	finally
    //   47	63	85	com/luajava/LuaException
  }
  
  public Object call(Object... paramVarArgs)
  {
    return call_aux(paramVarArgs, 1)[0];
  }
  
  public Object[] call_aux(Object[] paramArrayOfObject, int paramInt)
  {
    for (;;)
    {
      synchronized (this.b)
      {
        if ((!isFunction()) && (!isTable()) && (!isUserdata())) {
          throw new LuaException("Invalid object. Not a function, table or userdata .");
        }
        int m = this.b.getTop();
        push();
        if (paramArrayOfObject != null)
        {
          int k = paramArrayOfObject.length;
          int i = 0;
          j = k;
          Object localObject;
          if (i < k)
          {
            localObject = paramArrayOfObject[i];
            this.b.pushObjectValue(localObject);
            i += 1;
            continue;
          }
          i = this.b.pcall(j, paramInt, 0);
          if (i != 0)
          {
            if (!this.b.isString(-1)) {
              break label402;
            }
            paramArrayOfObject = this.b.toString(-1);
            this.b.pop(1);
            if (i == 2)
            {
              localObject = new StringBuilder();
              ((StringBuilder)localObject).append("Runtime error. ");
              ((StringBuilder)localObject).append(paramArrayOfObject);
              paramArrayOfObject = (Object[])localObject;
              paramArrayOfObject = paramArrayOfObject.toString();
            }
            else
            {
              if (i == 4)
              {
                localObject = new StringBuilder();
                ((StringBuilder)localObject).append("Memory allocation error. ");
                ((StringBuilder)localObject).append(paramArrayOfObject);
                paramArrayOfObject = (Object[])localObject;
                continue;
              }
              if (i == 6)
              {
                localObject = new StringBuilder();
                ((StringBuilder)localObject).append("Error while running the error handler function. ");
                ((StringBuilder)localObject).append(paramArrayOfObject);
                paramArrayOfObject = (Object[])localObject;
                continue;
              }
              localObject = new StringBuilder();
              ((StringBuilder)localObject).append("Lua Error code ");
              ((StringBuilder)localObject).append(i);
              ((StringBuilder)localObject).append(". ");
              ((StringBuilder)localObject).append(paramArrayOfObject);
              paramArrayOfObject = ((StringBuilder)localObject).toString();
            }
            throw new LuaException(paramArrayOfObject);
          }
          i = paramInt;
          if (paramInt == -1) {
            i = this.b.getTop() - m;
          }
          if (this.b.getTop() - m < i) {
            throw new LuaException("Invalid Number of Results .");
          }
          paramArrayOfObject = new Object[i];
          if (i > 0)
          {
            paramArrayOfObject[(i - 1)] = this.b.toJavaObject(-1);
            this.b.pop(1);
            i -= 1;
            continue;
          }
          return paramArrayOfObject;
        }
      }
      int j = 0;
      continue;
      label402:
      paramArrayOfObject = "";
    }
  }
  
  public Object createProxy(Class paramClass)
  {
    synchronized (this.b)
    {
      if ((!isTable()) && (!isFunction())) {
        throw new LuaException("Invalid Object. Must be Table or Function.");
      }
      if ((isFunction()) && (paramClass.getMethods().length != 1)) {
        throw new LuaException("Invalid Object. Must be a interface Method of Function.");
      }
      if ((isTable()) && (getTable().isList())) {
        throw new LuaException("Invalid Object. Must be Table is Not Array.");
      }
      LuaInvocationHandler localLuaInvocationHandler = new LuaInvocationHandler(this);
      paramClass = Proxy.newProxyInstance(paramClass.getClassLoader(), new Class[] { paramClass }, localLuaInvocationHandler);
      return paramClass;
    }
  }
  
  public Object createProxy(String paramString)
  {
    synchronized (this.b)
    {
      if (!isTable()) {
        throw new LuaException("Invalid Object. Must be Table.");
      }
      Object localObject = new StringTokenizer(paramString, ",");
      paramString = new Class[((StringTokenizer)localObject).countTokens()];
      int i = 0;
      while (((StringTokenizer)localObject).hasMoreTokens())
      {
        paramString[i] = Class.forName(((StringTokenizer)localObject).nextToken());
        i += 1;
      }
      localObject = new LuaInvocationHandler(this);
      paramString = Proxy.newProxyInstance(getClass().getClassLoader(), paramString, (InvocationHandler)localObject);
      return paramString;
    }
  }
  
  public byte[] dump()
  {
    synchronized (this.b)
    {
      if (!isFunction()) {
        throw new LuaException("Invalid object. Not a function .");
      }
      push();
      byte[] arrayOfByte = this.b.dump(-1);
      this.b.pop(1);
      return arrayOfByte;
    }
  }
  
  protected void finalize()
  {
    try
    {
      synchronized (this.b)
      {
        if (this.b.getPointer() != 0L) {
          this.b.LunRef(-1001000, this.a);
        }
        return;
      }
    }
    catch (Exception localException)
    {
      StringBuilder localStringBuilder;
      for (;;) {}
    }
    ??? = System.err;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("Unable to release object ");
    localStringBuilder.append(this.a);
    ((PrintStream)???).println(localStringBuilder.toString());
  }
  
  public boolean getBoolean()
  {
    synchronized (this.b)
    {
      push();
      boolean bool = this.b.toBoolean(-1);
      this.b.pop(1);
      return bool;
    }
  }
  
  public LuaObject getField(String paramString)
  {
    return this.b.getLuaObject(this, paramString);
  }
  
  public LuaFunction<?> getFunction()
  {
    synchronized (this.b)
    {
      push();
      LuaFunction localLuaFunction = new LuaFunction(this.b, -1);
      this.b.pop(1);
      return localLuaFunction;
    }
  }
  
  public LuaObject getI(long paramLong)
  {
    return this.b.getLuaObject(this, Long.valueOf(paramLong));
  }
  
  public long getInteger()
  {
    synchronized (this.b)
    {
      push();
      long l = this.b.toInteger(-1);
      this.b.pop(1);
      return l;
    }
  }
  
  public LuaState getLuaState()
  {
    return this.b;
  }
  
  public double getNumber()
  {
    synchronized (this.b)
    {
      push();
      double d = this.b.toNumber(-1);
      this.b.pop(1);
      return d;
    }
  }
  
  public Object getObject()
  {
    synchronized (this.b)
    {
      push();
      Object localObject1 = this.b.getObjectFromUserdata(-1);
      this.b.pop(1);
      return localObject1;
    }
  }
  
  public String getString()
  {
    synchronized (this.b)
    {
      push();
      String str = this.b.toString(-1);
      this.b.pop(1);
      return str;
    }
  }
  
  public LuaTable<?, ?> getTable()
  {
    synchronized (this.b)
    {
      push();
      LuaTable localLuaTable = new LuaTable(this.b, -1);
      this.b.pop(1);
      return localLuaTable;
    }
  }
  
  public boolean isBoolean()
  {
    synchronized (this.b)
    {
      push();
      boolean bool = this.b.isBoolean(-1);
      this.b.pop(1);
      return bool;
    }
  }
  
  public boolean isFunction()
  {
    synchronized (this.b)
    {
      push();
      boolean bool = this.b.isFunction(-1);
      this.b.pop(1);
      return bool;
    }
  }
  
  public boolean isInteger()
  {
    synchronized (this.b)
    {
      push();
      boolean bool = this.b.isInteger(-1);
      this.b.pop(1);
      return bool;
    }
  }
  
  public boolean isJavaFunction()
  {
    synchronized (this.b)
    {
      push();
      boolean bool = this.b.isJavaFunction(-1);
      this.b.pop(1);
      return bool;
    }
  }
  
  public boolean isJavaObject()
  {
    synchronized (this.b)
    {
      push();
      boolean bool = this.b.isObject(-1);
      this.b.pop(1);
      return bool;
    }
  }
  
  public boolean isNil()
  {
    synchronized (this.b)
    {
      push();
      boolean bool = this.b.isNil(-1);
      this.b.pop(1);
      return bool;
    }
  }
  
  public boolean isNumber()
  {
    synchronized (this.b)
    {
      push();
      boolean bool = this.b.isNumber(-1);
      this.b.pop(1);
      return bool;
    }
  }
  
  public boolean isString()
  {
    synchronized (this.b)
    {
      push();
      boolean bool = this.b.isString(-1);
      this.b.pop(1);
      return bool;
    }
  }
  
  public boolean isTable()
  {
    synchronized (this.b)
    {
      push();
      boolean bool = this.b.isTable(-1);
      this.b.pop(1);
      return bool;
    }
  }
  
  public boolean isUserdata()
  {
    synchronized (this.b)
    {
      push();
      boolean bool = this.b.isUserdata(-1);
      this.b.pop(1);
      return bool;
    }
  }
  
  public void pop()
  {
    this.b.pop(1);
  }
  
  public void push()
  {
    this.b.rawGetI(-1001000, this.a);
  }
  
  public void setField(String paramString, Object paramObject)
  {
    push();
    try
    {
      this.b.pushObjectValue(paramObject);
    }
    catch (LuaException paramObject)
    {
      for (;;) {}
    }
    this.b.pushNil();
    this.b.setField(-2, paramString);
    this.b.pop(1);
  }
  
  public void setI(long paramLong, Object paramObject)
  {
    push();
    try
    {
      this.b.pushObjectValue(paramObject);
    }
    catch (LuaException paramObject)
    {
      for (;;) {}
    }
    this.b.pushNil();
    this.b.setI(-2, paramLong);
    this.b.pop(1);
  }
  
  public String toString()
  {
    synchronized (this.b)
    {
      try
      {
        if (isNil()) {
          return "nil";
        }
        if (isBoolean())
        {
          boolean bool = getBoolean();
          return String.valueOf(bool);
        }
        if (isNumber())
        {
          double d = getNumber();
          return String.valueOf(d);
        }
        if (isString())
        {
          str = getString();
          return str;
        }
        if (isFunction()) {
          return "Lua Function";
        }
        if (isJavaObject())
        {
          str = getObject().toString();
          return str;
        }
        if (isUserdata()) {
          return "Userdata";
        }
        if (isTable()) {
          return "Lua Table";
        }
        if (isJavaFunction()) {
          return "Java Function";
        }
        return null;
      }
      catch (LuaException localLuaException)
      {
        String str;
        for (;;) {}
      }
      return null;
      throw str;
    }
  }
  
  public int type()
  {
    synchronized (this.b)
    {
      push();
      int i = this.b.type(-1);
      this.b.pop(1);
      return i;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\luajava\LuaObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */