package com.luajava;

import com.androlua.LuaContext;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LuaInvocationHandler
  implements InvocationHandler
{
  private final LuaContext a;
  private LuaObject b;
  
  public LuaInvocationHandler(LuaObject paramLuaObject)
  {
    this.b = paramLuaObject;
    this.a = paramLuaObject.getLuaState().getContext();
  }
  
  public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
  {
    synchronized (this.b.b)
    {
      String str = paramMethod.getName();
      if (this.b.isFunction()) {
        paramObject = this.b;
      } else {
        paramObject = this.b.getField(str);
      }
      Class localClass = paramMethod.getReturnType();
      boolean bool = ((LuaObject)paramObject).isNil();
      Object localObject = null;
      paramMethod = null;
      if (bool)
      {
        if ((!localClass.equals(Boolean.TYPE)) && (!localClass.equals(Boolean.class)))
        {
          if ((!localClass.isPrimitive()) && (!Number.class.isAssignableFrom(localClass))) {
            return null;
          }
          return Integer.valueOf(0);
        }
        return Boolean.valueOf(false);
      }
      try
      {
        if ((!localClass.equals(Void.class)) && (!localClass.equals(Void.TYPE)))
        {
          paramObject = ((LuaObject)paramObject).call(paramArrayOfObject);
          if (paramObject != null) {
            try
            {
              if ((paramObject instanceof Double))
              {
                paramMethod = LuaState.convertLuaNumber((Double)paramObject, localClass);
                paramObject = paramMethod;
              }
            }
            catch (LuaException paramMethod)
            {
              break label214;
            }
          }
        }
        else
        {
          ((LuaObject)paramObject).call(paramArrayOfObject);
          paramObject = localObject;
        }
      }
      catch (LuaException paramArrayOfObject)
      {
        paramObject = paramMethod;
        paramMethod = paramArrayOfObject;
        label214:
        this.a.sendError(str, paramMethod);
      }
      if (paramObject == null) {
        if ((!localClass.equals(Boolean.TYPE)) && (!localClass.equals(Boolean.class)))
        {
          if ((localClass.isPrimitive()) || (Number.class.isAssignableFrom(localClass))) {
            return Integer.valueOf(0);
          }
        }
        else {
          return Boolean.valueOf(false);
        }
      }
      return paramObject;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\luajava\LuaInvocationHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */