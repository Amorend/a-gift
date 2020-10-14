package com.luajava;

import android.content.Context;
import com.androlua.LuaContext;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class LuaJavaAPI
{
  private static Class<?> a = LuaState.class;
  private static Class<?> b = String.class;
  private static Class<?> c = List.class;
  private static Class<?> d = ArrayList.class;
  private static Class<?> e = HashMap.class;
  private static Class<?> f = Map.class;
  private static Class<?> g = LuaFunction.class;
  private static Class<?> h = LuaObject.class;
  private static Class<?> i = LuaTable.class;
  private static Class<?> j = Number.class;
  private static Class<?> k = Character.class;
  private static HashMap<String, Method> l = new HashMap();
  private static HashMap<String, Method> m = new HashMap();
  public static HashMap<String, Method[]> methodCache;
  public static HashMap<Class<?>, Method[]> methodsMap = new HashMap();
  private static HashMap<String, Method> n = new HashMap();
  private static HashMap<String, Method> o = new HashMap();
  private static HashMap<String, Method> p = new HashMap();
  private static final HashMap<Class<?>, HashMap<String, ArrayList<Method>>> q = new HashMap();
  
  static
  {
    methodCache = new HashMap();
  }
  
  private static int a(LuaState paramLuaState, Class<?> paramClass)
  {
    int i3;
    label27:
    label67:
    try
    {
      i1 = paramLuaState.getTop();
      i3 = 0;
      if (i1 != 1) {}
    }
    finally {}
    try
    {
      paramLuaState.pushJavaObject(paramClass.newInstance());
      return 1;
    }
    catch (Exception localException2)
    {
      break label27;
    }
    try
    {
      paramLuaState.pushJavaObject(paramClass.getConstructor(new Class[] { Context.class }).newInstance(new Object[] { paramLuaState.getContext().getContext() }));
      return 1;
    }
    catch (Exception localException3)
    {
      break label67;
      i2 = 0;
      break label129;
      i1 += 1;
      break label101;
    }
    int i4 = i1 - 1;
    Object[] arrayOfObject = new Object[i4];
    Constructor[] arrayOfConstructor = paramClass.getConstructors();
    StringBuilder localStringBuilder = new StringBuilder();
    int i5 = arrayOfConstructor.length;
    i1 = 0;
    label101:
    Object localObject;
    if (i1 < i5)
    {
      paramClass = arrayOfConstructor[i1];
      localObject = paramClass.getParameterTypes();
      if (localObject.length != i4) {
        break label398;
      }
    }
    else
    {
      for (;;)
      {
        label129:
        int i6 = localObject.length;
        if (i2 >= i6) {
          break label167;
        }
        try
        {
          arrayOfObject[i2] = e(paramLuaState, localObject[i2], i2 + 2);
          i2 += 1;
        }
        catch (Exception localException4)
        {
          label167:
          label169:
          for (;;) {}
        }
      }
      i2 = 0;
      break label169;
      i2 = 1;
      if (i2 == 0) {
        break label398;
      }
      try
      {
        localObject = paramClass.newInstance(arrayOfObject);
        paramLuaState.pushJavaObject(localObject);
        return 1;
      }
      catch (Exception localException1)
      {
        localStringBuilder.append("  at ");
        localStringBuilder.append(paramClass);
        localStringBuilder.append("\n  -> ");
        paramClass = localException1;
        if (localException1.getCause() != null) {
          paramClass = localException1.getCause();
        }
        localStringBuilder.append(paramClass);
        localStringBuilder.append("\n");
      }
      if (localStringBuilder.length() > 0)
      {
        paramClass = new StringBuilder();
        paramClass.append("Invalid constructor method call.\n");
        paramClass.append(localStringBuilder.toString());
        throw new LuaException(paramClass.toString());
      }
      i2 = arrayOfConstructor.length;
      i1 = i3;
      while (i1 < i2)
      {
        localStringBuilder.append(arrayOfConstructor[i1].toString());
        localStringBuilder.append("\n");
        i1 += 1;
      }
      paramClass = new StringBuilder();
      paramClass.append("Invalid constructor method call. Invalid Parameters.\n");
      paramClass.append(localStringBuilder.toString());
      throw new LuaException(paramClass.toString());
    }
  }
  
  /* Error */
  private static int a(LuaState paramLuaState, Object paramObject, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ifnonnull +7 -> 10
    //   6: aload_0
    //   7: monitorexit
    //   8: iconst_0
    //   9: ireturn
    //   10: aload_1
    //   11: instanceof 106
    //   14: ifeq +15 -> 29
    //   17: aload_1
    //   18: checkcast 106	java/lang/Class
    //   21: astore 7
    //   23: iconst_1
    //   24: istore 4
    //   26: goto +12 -> 38
    //   29: aload_1
    //   30: invokevirtual 191	java/lang/Object:getClass	()Ljava/lang/Class;
    //   33: astore 7
    //   35: iconst_0
    //   36: istore 4
    //   38: aload_2
    //   39: ldc -64
    //   41: invokevirtual 196	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   44: istore 5
    //   46: aconst_null
    //   47: astore 8
    //   49: iload 5
    //   51: ifne +98 -> 149
    //   54: aload_2
    //   55: iconst_0
    //   56: invokevirtual 200	java/lang/String:charAt	(I)C
    //   59: istore_3
    //   60: iload_3
    //   61: invokestatic 204	java/lang/Character:isLowerCase	(C)Z
    //   64: ifeq +288 -> 352
    //   67: new 140	java/lang/StringBuilder
    //   70: dup
    //   71: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   74: astore 6
    //   76: aload 6
    //   78: iload_3
    //   79: invokestatic 208	java/lang/Character:toUpperCase	(C)C
    //   82: invokevirtual 211	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   85: pop
    //   86: aload 6
    //   88: aload_2
    //   89: iconst_1
    //   90: invokevirtual 215	java/lang/String:substring	(I)Ljava/lang/String;
    //   93: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: pop
    //   97: aload 6
    //   99: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   102: astore 6
    //   104: goto +3 -> 107
    //   107: new 140	java/lang/StringBuilder
    //   110: dup
    //   111: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   114: astore 8
    //   116: aload 8
    //   118: ldc -64
    //   120: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   123: pop
    //   124: aload 8
    //   126: aload 6
    //   128: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   131: pop
    //   132: aload 8
    //   134: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   137: astore 8
    //   139: aconst_null
    //   140: astore 6
    //   142: aload 7
    //   144: astore 9
    //   146: goto +10 -> 156
    //   149: aconst_null
    //   150: astore 6
    //   152: aload 7
    //   154: astore 9
    //   156: aload 6
    //   158: astore 7
    //   160: aload 9
    //   162: ifnull +69 -> 231
    //   165: aload 9
    //   167: aload_2
    //   168: invokevirtual 219	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   171: astore 7
    //   173: aload 7
    //   175: astore 6
    //   177: aload 6
    //   179: astore 7
    //   181: goto +28 -> 209
    //   184: aload 6
    //   186: astore 7
    //   188: aload 8
    //   190: ifnull +19 -> 209
    //   193: aload 9
    //   195: aload 8
    //   197: invokevirtual 219	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   200: astore 7
    //   202: aload 7
    //   204: astore 6
    //   206: goto -29 -> 177
    //   209: aload 7
    //   211: ifnull +6 -> 217
    //   214: goto +17 -> 231
    //   217: aload 9
    //   219: invokevirtual 222	java/lang/Class:getSuperclass	()Ljava/lang/Class;
    //   222: astore 9
    //   224: aload 7
    //   226: astore 6
    //   228: goto -72 -> 156
    //   231: aload 7
    //   233: ifnonnull +7 -> 240
    //   236: aload_0
    //   237: monitorexit
    //   238: iconst_0
    //   239: ireturn
    //   240: iload 4
    //   242: ifeq +18 -> 260
    //   245: aload 7
    //   247: invokevirtual 227	java/lang/reflect/Field:getModifiers	()I
    //   250: invokestatic 233	java/lang/reflect/Modifier:isStatic	(I)Z
    //   253: ifne +7 -> 260
    //   256: aload_0
    //   257: monitorexit
    //   258: iconst_0
    //   259: ireturn
    //   260: aload 7
    //   262: invokevirtual 236	java/lang/reflect/Field:getType	()Ljava/lang/Class;
    //   265: astore 6
    //   267: aload 7
    //   269: invokevirtual 227	java/lang/reflect/Field:getModifiers	()I
    //   272: invokestatic 239	java/lang/reflect/Modifier:isPublic	(I)Z
    //   275: ifne +9 -> 284
    //   278: aload 7
    //   280: iconst_1
    //   281: invokevirtual 243	java/lang/reflect/Field:setAccessible	(Z)V
    //   284: aload 7
    //   286: aload_1
    //   287: aload_0
    //   288: aload 6
    //   290: aload_0
    //   291: invokevirtual 104	com/luajava/LuaState:getTop	()I
    //   294: invokestatic 148	com/luajava/LuaJavaAPI:e	(Lcom/luajava/LuaState;Ljava/lang/Class;I)Ljava/lang/Object;
    //   297: invokevirtual 247	java/lang/reflect/Field:set	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   300: goto +22 -> 322
    //   303: astore_1
    //   304: new 176	com/luajava/LuaException
    //   307: dup
    //   308: aload_1
    //   309: invokespecial 250	com/luajava/LuaException:<init>	(Ljava/lang/Exception;)V
    //   312: athrow
    //   313: aload_0
    //   314: aload_2
    //   315: iconst_3
    //   316: aload 6
    //   318: invokestatic 253	com/luajava/LuaJavaAPI:a	(Lcom/luajava/LuaState;Ljava/lang/String;ILjava/lang/Class;)Ljava/lang/String;
    //   321: pop
    //   322: aload_0
    //   323: monitorexit
    //   324: iconst_1
    //   325: ireturn
    //   326: aload_0
    //   327: monitorexit
    //   328: aload_1
    //   329: athrow
    //   330: astore 7
    //   332: goto -148 -> 184
    //   335: astore 7
    //   337: aload 6
    //   339: astore 7
    //   341: goto -132 -> 209
    //   344: astore_1
    //   345: goto -32 -> 313
    //   348: astore_1
    //   349: goto -23 -> 326
    //   352: aconst_null
    //   353: astore 6
    //   355: goto -248 -> 107
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	358	0	paramLuaState	LuaState
    //   0	358	1	paramObject	Object
    //   0	358	2	paramString	String
    //   59	20	3	c1	char
    //   24	217	4	i1	int
    //   44	6	5	bool	boolean
    //   74	280	6	localObject1	Object
    //   21	264	7	localObject2	Object
    //   330	1	7	localNoSuchFieldException1	NoSuchFieldException
    //   335	1	7	localNoSuchFieldException2	NoSuchFieldException
    //   339	1	7	localObject3	Object
    //   47	149	8	localObject4	Object
    //   144	79	9	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   267	284	303	java/lang/Exception
    //   284	300	303	java/lang/Exception
    //   165	173	330	java/lang/NoSuchFieldException
    //   193	202	335	java/lang/NoSuchFieldException
    //   267	284	344	com/luajava/LuaException
    //   284	300	344	com/luajava/LuaException
    //   6	8	348	finally
    //   10	23	348	finally
    //   29	35	348	finally
    //   38	46	348	finally
    //   54	104	348	finally
    //   107	139	348	finally
    //   165	173	348	finally
    //   193	202	348	finally
    //   217	224	348	finally
    //   236	238	348	finally
    //   245	258	348	finally
    //   260	267	348	finally
    //   267	284	348	finally
    //   284	300	348	finally
    //   304	313	348	finally
    //   313	322	348	finally
    //   322	324	348	finally
    //   326	328	348	finally
  }
  
  private static int a(LuaState paramLuaState, Object paramObject, String paramString, boolean paramBoolean)
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("setOn");
      ((StringBuilder)localObject).append(paramString.substring(2));
      ((StringBuilder)localObject).append("Listener");
      localObject = ((StringBuilder)localObject).toString();
      Iterator localIterator = getMethod(paramObject.getClass(), (String)localObject, paramBoolean).iterator();
      while (localIterator.hasNext())
      {
        localObject = (Method)localIterator.next();
        if ((!paramBoolean) || (Modifier.isStatic(((Method)localObject).getModifiers())))
        {
          Class[] arrayOfClass = ((Method)localObject).getParameterTypes();
          if ((arrayOfClass.length == 1) && (arrayOfClass[0].isInterface()))
          {
            paramLuaState.newTable();
            paramLuaState.pushValue(-2);
            paramLuaState.setField(-2, paramString);
            try
            {
              paramString = paramLuaState.getLuaObject(-1).createProxy(arrayOfClass[0]);
              paramLuaState.pop(1);
              ((Method)localObject).invoke(paramObject, new Object[] { paramString });
              return 1;
            }
            catch (Exception paramObject)
            {
              throw new LuaException((Exception)paramObject);
            }
          }
        }
      }
      return 0;
    }
    finally {}
  }
  
  private static int a(LuaState paramLuaState, String paramString)
  {
    try
    {
      paramLuaState.pushJavaObject(paramLuaState.getLuaObject(2).createProxy(paramString));
      return 1;
    }
    catch (Exception paramString)
    {
      throw new LuaException(paramString);
      throw paramString;
    }
    finally
    {
      for (;;) {}
    }
  }
  
  private static Object a(LuaState paramLuaState, Class paramClass, int paramInt)
  {
    try
    {
      paramClass = paramLuaState.getLuaObject(paramInt).createProxy(paramClass);
      return paramClass;
    }
    catch (Exception paramClass)
    {
      throw new LuaException(paramClass);
      throw paramClass;
    }
    finally
    {
      for (;;) {}
    }
  }
  
  private static Object a(LuaState paramLuaState, Class<?> paramClass, int paramInt1, int paramInt2)
  {
    Object localObject2 = null;
    Object localObject3 = null;
    Object localObject1 = null;
    if (paramInt1 == 0) {
      return null;
    }
    int i1 = 1;
    if (paramInt1 != 1) {
      switch (paramInt1)
      {
      default: 
        throw new LuaException("Invalid Parameters.");
      case 7: 
        if (paramLuaState.isObject(paramInt2))
        {
          localObject2 = paramLuaState.getObjectFromUserdata(paramInt2);
          if (localObject2 == null) {
            return null;
          }
          paramLuaState = (LuaState)localObject1;
          if (paramClass.isPrimitive())
          {
            localObject2.getClass();
            if (((paramClass != Byte.TYPE) || (!(localObject2 instanceof Byte))) && ((paramClass != Short.TYPE) || (!(localObject2 instanceof Short))) && ((paramClass != Integer.TYPE) || (!(localObject2 instanceof Integer))) && ((paramClass != Long.TYPE) || (!(localObject2 instanceof Long))) && ((paramClass != Float.TYPE) || (!(localObject2 instanceof Float))) && ((paramClass != Double.TYPE) || (!(localObject2 instanceof Double))))
            {
              paramLuaState = (LuaState)localObject1;
              if (paramClass == Character.TYPE)
              {
                paramLuaState = (LuaState)localObject1;
                if (!(localObject2 instanceof Character)) {}
              }
            }
            else
            {
              paramLuaState = (LuaState)localObject2;
            }
          }
          localObject1 = paramLuaState;
          paramInt1 = i1;
          if (paramLuaState != null) {
            break label598;
          }
          localObject1 = paramLuaState;
          if (paramClass.isAssignableFrom(localObject2.getClass()))
          {
            localObject1 = localObject2;
            paramInt1 = i1;
            break label598;
          }
        }
        else if (!paramClass.isAssignableFrom(h))
        {
          localObject1 = localObject2;
        }
        break;
      case 6: 
        if (!paramClass.isInterface()) {
          break;
        }
      case 5: 
        for (;;)
        {
          localObject1 = a(paramLuaState, paramClass, paramInt2);
          paramInt1 = i1;
          break label598;
          if (!paramClass.isAssignableFrom(LuaFunction.class))
          {
            localObject1 = localObject2;
            break;
            if (!paramClass.isAssignableFrom(LuaTable.class)) {}
          }
          else
          {
            localObject1 = paramLuaState.getLuaObject(paramInt2);
            paramInt1 = i1;
            break label598;
          }
          if (paramClass.isArray())
          {
            localObject1 = b(paramLuaState, paramClass.getComponentType(), paramInt2);
            paramInt1 = i1;
            break label598;
          }
          if (List.class.isAssignableFrom(paramClass))
          {
            localObject1 = c(paramLuaState, paramClass, paramInt2);
            paramInt1 = i1;
            break label598;
          }
          if (Map.class.isAssignableFrom(paramClass))
          {
            localObject1 = d(paramLuaState, paramClass, paramInt2);
            paramInt1 = i1;
            break label598;
          }
          localObject1 = localObject2;
          if (!paramClass.isInterface()) {
            break;
          }
        }
      case 4: 
        if (!paramClass.isAssignableFrom(b)) {
          localObject1 = localObject2;
        }
        break;
      }
    }
    for (;;)
    {
      paramInt1 = 0;
      break label598;
      localObject1 = paramLuaState.toString(paramInt2);
      paramInt1 = i1;
      break label598;
      if ((!paramClass.isPrimitive()) && (!paramClass.isAssignableFrom(Number.class)))
      {
        localObject1 = localObject2;
      }
      else
      {
        if (paramLuaState.isInteger(paramInt2))
        {
          localObject1 = LuaState.convertLuaNumber(Long.valueOf(paramLuaState.toInteger(paramInt2)), paramClass);
          paramInt1 = i1;
          break label598;
        }
        localObject1 = localObject3;
        paramInt1 = i1;
        if (!paramLuaState.isNumber(paramInt2)) {
          break label598;
        }
        localObject1 = LuaState.convertLuaNumber(Double.valueOf(paramLuaState.toNumber(paramInt2)), paramClass);
        paramInt1 = i1;
        break label598;
        if ((!paramClass.isPrimitive()) || (paramClass == Boolean.TYPE) || (paramClass.isAssignableFrom(Boolean.class))) {
          break;
        }
        localObject1 = localObject2;
      }
    }
    localObject1 = Boolean.valueOf(paramLuaState.toBoolean(paramInt2));
    paramInt1 = i1;
    label598:
    if ((paramInt1 != 0) && (localObject1 != null)) {
      return localObject1;
    }
    throw new LuaException("Invalid Parameter.");
  }
  
  private static String a(LuaState paramLuaState, int paramInt)
  {
    if (paramLuaState.isObject(paramInt)) {
      return paramLuaState.getObjectFromUserdata(paramInt).getClass().getName();
    }
    switch (paramLuaState.type(paramInt))
    {
    default: 
      return "unkown";
    case 8: 
      return "thread";
    case 6: 
      return "function";
    case 5: 
      return "table";
    case 4: 
      return "string";
    case 3: 
      return "number";
    case 2: 
    case 7: 
      return "userdata";
    }
    return "boolean";
  }
  
  private static String a(LuaState paramLuaState, String paramString, int paramInt, Class paramClass)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("bad argument to '");
    localStringBuilder.append(paramString);
    localStringBuilder.append("' (");
    localStringBuilder.append(paramClass.getName());
    localStringBuilder.append(" expected, got ");
    localStringBuilder.append(a(paramLuaState, paramInt));
    localStringBuilder.append(" value)");
    throw new LuaException(localStringBuilder.toString());
  }
  
  public static int asTable(long paramLong, Object paramObject)
  {
    synchronized (LuaStateFactory.getExistingState(paramLong))
    {
      try
      {
        ???.newTable();
        int i1;
        if (paramObject.getClass().isArray())
        {
          int i2 = Array.getLength(paramObject);
          i1 = 0;
          while (i1 <= i2 - 1)
          {
            ???.pushObjectValue(Array.get(paramObject, i1));
            i1 += 1;
            ???.rawSetI(-2, i1);
          }
        }
        if ((paramObject instanceof Collection))
        {
          paramObject = ((Collection)paramObject).iterator();
          i1 = 1;
          while (((Iterator)paramObject).hasNext())
          {
            ???.pushObjectValue(((Iterator)paramObject).next());
            ???.rawSetI(-2, i1);
            i1 += 1;
          }
        }
        if ((paramObject instanceof Map))
        {
          paramObject = ((Map)paramObject).entrySet().iterator();
          while (((Iterator)paramObject).hasNext())
          {
            localObject = (Map.Entry)((Iterator)paramObject).next();
            ???.pushObjectValue(((Map.Entry)localObject).getKey());
            ???.pushObjectValue(((Map.Entry)localObject).getValue());
            ???.setTable(-3);
          }
        }
        ???.pushValue(-1);
        return 1;
      }
      catch (Exception paramObject)
      {
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append("can not astable: ");
        ((StringBuilder)localObject).append(((Exception)paramObject).getMessage());
        throw new LuaException(((StringBuilder)localObject).toString());
      }
      throw ((Throwable)paramObject);
    }
  }
  
  private static int b(LuaState paramLuaState, Class paramClass)
  {
    try
    {
      paramLuaState.pushJavaObject(a(paramLuaState, paramClass, 2));
      return 1;
    }
    finally {}
  }
  
  private static int b(LuaState paramLuaState, Object paramObject, String paramString, boolean paramBoolean)
  {
    int i2 = 0;
    for (;;)
    {
      Object localObject1;
      String str;
      Object localObject2;
      Object localObject3;
      Class[] arrayOfClass;
      int i3;
      Object localObject4;
      try
      {
        char c1 = paramString.charAt(0);
        localObject1 = paramString;
        if (Character.isLowerCase(c1))
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append(Character.toUpperCase(c1));
          ((StringBuilder)localObject1).append(paramString.substring(1));
          localObject1 = ((StringBuilder)localObject1).toString();
        }
        paramString = new StringBuilder();
        paramString.append("set");
        paramString.append((String)localObject1);
        str = paramString.toString();
        paramString = null;
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(paramObject.getClass().getName());
        ((StringBuilder)localObject2).append("@->");
        ((StringBuilder)localObject2).append(str);
        localObject2 = ((StringBuilder)localObject2).toString();
        localObject3 = new Object[1];
        i1 = paramLuaState.type(-1);
        if (i1 != 1) {}
        switch (i1)
        {
        case 4: 
          paramString = (Method)l.get(localObject2);
          if (paramString == null) {
            break;
          }
          localObject3[0] = paramLuaState.toString(-1);
          break;
        case 3: 
          if (paramLuaState.isInteger(-1))
          {
            paramString = (Method)m.get(localObject2);
            if (paramString == null) {
              break label799;
            }
            localObject3[0] = LuaState.convertLuaNumber(Long.valueOf(paramLuaState.toInteger(-1)), paramString.getParameterTypes()[0]);
            break label799;
          }
          paramString = (Method)n.get(localObject2);
          if (paramString == null) {
            break label806;
          }
          localObject3[0] = LuaState.convertLuaNumber(Double.valueOf(paramLuaState.toNumber(-1)), paramString.getParameterTypes()[0]);
          break label806;
          paramString = (Method)o.get(localObject2);
          if (paramString != null) {
            localObject3[0] = Boolean.valueOf(paramLuaState.toBoolean(-1));
          }
          i1 = 1;
          if (paramString != null) {
            try
            {
              paramString.invoke(paramObject, (Object[])localObject3);
              return 1;
            }
            catch (Exception paramObject)
            {
              throw new LuaException((Exception)paramObject);
            }
          }
          localObject3 = getMethod(paramObject.getClass(), str, paramBoolean);
          paramString = new StringBuilder();
          Iterator localIterator = ((ArrayList)localObject3).iterator();
          if (localIterator.hasNext())
          {
            localObject3 = (Method)localIterator.next();
            if ((paramBoolean) && (!Modifier.isStatic(((Method)localObject3).getModifiers()))) {
              continue;
            }
            arrayOfClass = ((Method)localObject3).getParameterTypes();
            i3 = arrayOfClass.length;
            if (i3 != 1) {
              continue;
            }
          }
          break;
        }
      }
      finally {}
      try
      {
        localObject4 = e(paramLuaState, arrayOfClass[i2], paramLuaState.getTop());
        if (i1 != 1)
        {
          if (i1 != 9)
          {
            switch (i1)
            {
            default: 
              break;
            case 4: 
              paramString = l;
              paramString.put(localObject2, localObject3);
              break;
            case 3: 
              paramString = n;
              break;
            }
          }
          else
          {
            paramString = m;
            continue;
          }
        }
        else
        {
          paramString = o;
          continue;
        }
        try
        {
          ((Method)localObject3).invoke(paramObject, new Object[] { localObject4 });
          return 1;
        }
        catch (Exception paramObject)
        {
          throw new LuaException((Exception)paramObject);
        }
      }
      catch (LuaException localLuaException)
      {
        continue;
      }
      paramString.append("-> ");
      paramString.append(arrayOfClass[0]);
      paramString.append("\n");
      i2 = 0;
      continue;
      i2 = paramLuaState.getTop();
      if (paramLuaState.type(i2) == 5)
      {
        paramLuaState.getField(1, str);
        paramObject = paramLuaState.getFunction(-1);
        if ((paramLuaState.type(-1) == 6) && (paramObject != null))
        {
          i3 = paramLuaState.rawLen(i2);
          i1 = 0;
          if (i1 < i3)
          {
            i1 += 1;
            paramLuaState.getI(i2, i1);
            continue;
          }
          if (paramLuaState.pcall(i3, 0, 0) == 0) {
            return 1;
          }
          throw new LuaException(paramLuaState.toString(-1));
        }
      }
      if (paramString.length() > 0)
      {
        paramObject = new StringBuilder();
        ((StringBuilder)paramObject).append("Invalid setter ");
        ((StringBuilder)paramObject).append((String)localObject1);
        ((StringBuilder)paramObject).append(". Invalid Parameters.\n");
        ((StringBuilder)paramObject).append(paramString.toString());
        ((StringBuilder)paramObject).append(paramLuaState.toJavaObject(-1).getClass());
        throw new LuaException(((StringBuilder)paramObject).toString());
      }
      return 0;
      int i1 = -1;
      continue;
      i1 = 4;
      continue;
      label799:
      i1 = 9;
      continue;
      label806:
      i1 = 3;
    }
  }
  
  private static Object b(LuaState paramLuaState, Class<?> paramClass, int paramInt)
  {
    for (;;)
    {
      try
      {
        i2 = paramLuaState.objLen(paramInt);
        localObject = Array.newInstance(paramClass, i2);
        if (paramClass == b)
        {
          i1 = 1;
          if (i1 > i2) {
            continue;
          }
          paramLuaState.pushNumber(i1);
          paramLuaState.getTable(paramInt);
          Array.set(localObject, i1 - 1, paramLuaState.toString(-1));
          paramLuaState.pop(1);
          i1 += 1;
          continue;
        }
        if (paramClass == Double.TYPE)
        {
          i1 = 1;
          if (i1 > i2) {
            continue;
          }
          paramLuaState.pushNumber(i1);
          paramLuaState.getTable(paramInt);
          Array.set(localObject, i1 - 1, Double.valueOf(paramLuaState.toNumber(-1)));
          paramLuaState.pop(1);
          i1 += 1;
          continue;
        }
        if (paramClass == Float.TYPE)
        {
          i1 = 1;
          if (i1 > i2) {
            continue;
          }
          paramLuaState.pushNumber(i1);
          paramLuaState.getTable(paramInt);
          Array.set(localObject, i1 - 1, Float.valueOf((float)paramLuaState.toNumber(-1)));
          paramLuaState.pop(1);
          i1 += 1;
          continue;
        }
        if (paramClass == Long.TYPE)
        {
          i1 = 1;
          if (i1 > i2) {
            continue;
          }
          paramLuaState.pushNumber(i1);
          paramLuaState.getTable(paramInt);
          Array.set(localObject, i1 - 1, Long.valueOf(paramLuaState.toInteger(-1)));
          paramLuaState.pop(1);
          i1 += 1;
          continue;
        }
        if (paramClass == Integer.TYPE)
        {
          i1 = 1;
          if (i1 > i2) {
            continue;
          }
          paramLuaState.pushNumber(i1);
          paramLuaState.getTable(paramInt);
          Array.set(localObject, i1 - 1, Integer.valueOf((int)paramLuaState.toInteger(-1)));
          paramLuaState.pop(1);
          i1 += 1;
          continue;
        }
        if (paramClass == Short.TYPE)
        {
          i1 = 1;
          if (i1 > i2) {
            continue;
          }
          paramLuaState.pushNumber(i1);
          paramLuaState.getTable(paramInt);
          Array.set(localObject, i1 - 1, Short.valueOf((short)(int)paramLuaState.toInteger(-1)));
          paramLuaState.pop(1);
          i1 += 1;
          continue;
        }
        if (paramClass == Character.TYPE)
        {
          i1 = 1;
          if (i1 > i2) {
            continue;
          }
          paramLuaState.pushNumber(i1);
          paramLuaState.getTable(paramInt);
          Array.set(localObject, i1 - 1, Character.valueOf((char)(int)paramLuaState.toInteger(-1)));
          paramLuaState.pop(1);
          i1 += 1;
          continue;
        }
        if (paramClass != Byte.TYPE) {
          continue;
        }
        i1 = 1;
        if (i1 > i2) {
          continue;
        }
        paramLuaState.pushNumber(i1);
        paramLuaState.getTable(paramInt);
        Array.set(localObject, i1 - 1, Byte.valueOf((byte)(int)paramLuaState.toInteger(-1)));
        paramLuaState.pop(1);
        i1 += 1;
      }
      catch (Exception paramClass)
      {
        int i2;
        Object localObject;
        throw new LuaException(paramClass);
        throw paramClass;
        int i1 = 1;
      }
      finally
      {
        continue;
      }
      if (i1 > i2) {
        continue;
      }
      paramLuaState.pushNumber(i1);
      paramLuaState.getTable(paramInt);
      Array.set(localObject, i1 - 1, e(paramLuaState, paramClass, paramLuaState.getTop()));
      paramLuaState.pop(1);
      i1 += 1;
    }
    return localObject;
  }
  
  private static int c(LuaState paramLuaState, Class<?> paramClass)
  {
    try
    {
      paramLuaState.pushJavaObject(b(paramLuaState, paramClass, 2));
      return 1;
    }
    finally {}
  }
  
  private static Object c(LuaState paramLuaState, Class<List<Object>> paramClass, int paramInt)
  {
    for (;;)
    {
      try
      {
        int i2 = paramLuaState.objLen(paramInt);
        try
        {
          if (paramClass.equals(c)) {
            paramClass = new ArrayList();
          } else {
            paramClass = (List)paramClass.newInstance();
          }
        }
        catch (Exception paramClass)
        {
          throw new LuaException(paramClass);
        }
        if (i1 <= i2)
        {
          paramLuaState.pushNumber(i1);
          paramLuaState.getTable(paramInt);
          paramClass.add(paramLuaState.toJavaObject(-1));
          paramLuaState.pop(1);
          i1 += 1;
          continue;
        }
        return paramClass;
      }
      finally {}
      int i1 = 1;
    }
  }
  
  /* Error */
  public static int callMethod(long paramLong, Object paramObject, String paramString)
  {
    // Byte code:
    //   0: lload_0
    //   1: invokestatic 448	com/luajava/LuaStateFactory:getExistingState	(J)Lcom/luajava/LuaState;
    //   4: astore 15
    //   6: aload 15
    //   8: monitorenter
    //   9: new 140	java/lang/StringBuilder
    //   12: dup
    //   13: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   16: astore 16
    //   18: aconst_null
    //   19: astore 12
    //   21: aload 15
    //   23: invokevirtual 104	com/luajava/LuaState:getTop	()I
    //   26: istore 5
    //   28: iconst_m1
    //   29: istore 4
    //   31: iload 5
    //   33: ifne +157 -> 190
    //   36: getstatic 93	com/luajava/LuaJavaAPI:p	Ljava/util/HashMap;
    //   39: aload_3
    //   40: invokevirtual 497	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   43: checkcast 277	java/lang/reflect/Method
    //   46: astore 12
    //   48: aload 12
    //   50: ifnull +1127 -> 1177
    //   53: aload 12
    //   55: aload_2
    //   56: iconst_0
    //   57: anewarray 4	java/lang/Object
    //   60: invokevirtual 308	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   63: astore_2
    //   64: aload_2
    //   65: ifnonnull +22 -> 87
    //   68: aload 12
    //   70: invokevirtual 575	java/lang/reflect/Method:getReturnType	()Ljava/lang/Class;
    //   73: getstatic 578	java/lang/Void:TYPE	Ljava/lang/Class;
    //   76: invokevirtual 565	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   79: ifeq +8 -> 87
    //   82: aload 15
    //   84: monitorexit
    //   85: iconst_0
    //   86: ireturn
    //   87: aload 15
    //   89: aload_2
    //   90: invokevirtual 461	com/luajava/LuaState:pushObjectValue	(Ljava/lang/Object;)V
    //   93: aload 15
    //   95: monitorexit
    //   96: iconst_1
    //   97: ireturn
    //   98: astore_2
    //   99: aload 16
    //   101: ldc -106
    //   103: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: pop
    //   107: aload 16
    //   109: aload 12
    //   111: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   114: pop
    //   115: aload 16
    //   117: ldc -97
    //   119: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   122: pop
    //   123: aload_2
    //   124: astore_3
    //   125: aload_2
    //   126: invokevirtual 163	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   129: ifnull +8 -> 137
    //   132: aload_2
    //   133: invokevirtual 163	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   136: astore_3
    //   137: aload 16
    //   139: aload_3
    //   140: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   143: pop
    //   144: aload 16
    //   146: ldc -91
    //   148: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: pop
    //   152: new 140	java/lang/StringBuilder
    //   155: dup
    //   156: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   159: astore_2
    //   160: aload_2
    //   161: ldc_w 580
    //   164: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   167: pop
    //   168: aload_2
    //   169: aload 16
    //   171: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   174: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: pop
    //   178: new 176	com/luajava/LuaException
    //   181: dup
    //   182: aload_2
    //   183: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   186: invokespecial 179	com/luajava/LuaException:<init>	(Ljava/lang/String;)V
    //   189: athrow
    //   190: iload 5
    //   192: anewarray 4	java/lang/Object
    //   195: astore 17
    //   197: iload 5
    //   199: iconst_1
    //   200: if_icmpne +986 -> 1186
    //   203: aload 15
    //   205: iconst_1
    //   206: invokevirtual 414	com/luajava/LuaState:type	(I)I
    //   209: istore 6
    //   211: iload 6
    //   213: iconst_1
    //   214: if_icmpeq +209 -> 423
    //   217: iload 6
    //   219: tableswitch	default:+964->1183, 3:+70->289, 4:+21->240
    //   240: iconst_4
    //   241: istore 6
    //   243: getstatic 85	com/luajava/LuaJavaAPI:l	Ljava/util/HashMap;
    //   246: aload_3
    //   247: invokevirtual 497	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   250: checkcast 277	java/lang/reflect/Method
    //   253: astore 13
    //   255: iload 6
    //   257: istore 4
    //   259: aload 13
    //   261: astore 12
    //   263: aload 13
    //   265: ifnull +191 -> 456
    //   268: aload 17
    //   270: iconst_0
    //   271: aload 15
    //   273: iconst_1
    //   274: invokevirtual 366	com/luajava/LuaState:toString	(I)Ljava/lang/String;
    //   277: aastore
    //   278: iload 6
    //   280: istore 4
    //   282: aload 13
    //   284: astore 12
    //   286: goto +170 -> 456
    //   289: aload 15
    //   291: iconst_1
    //   292: invokevirtual 369	com/luajava/LuaState:isInteger	(I)Z
    //   295: ifeq +66 -> 361
    //   298: bipush 9
    //   300: istore 6
    //   302: getstatic 87	com/luajava/LuaJavaAPI:m	Ljava/util/HashMap;
    //   305: aload_3
    //   306: invokevirtual 497	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   309: checkcast 277	java/lang/reflect/Method
    //   312: astore 13
    //   314: iload 6
    //   316: istore 4
    //   318: aload 13
    //   320: astore 12
    //   322: aload 13
    //   324: ifnull +132 -> 456
    //   327: aload 17
    //   329: iconst_0
    //   330: aload 15
    //   332: iconst_1
    //   333: invokevirtual 373	com/luajava/LuaState:toInteger	(I)J
    //   336: invokestatic 377	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   339: aload 13
    //   341: invokevirtual 279	java/lang/reflect/Method:getParameterTypes	()[Ljava/lang/Class;
    //   344: iconst_0
    //   345: aaload
    //   346: invokestatic 381	com/luajava/LuaState:convertLuaNumber	(Ljava/lang/Long;Ljava/lang/Class;)Ljava/lang/Number;
    //   349: aastore
    //   350: iload 6
    //   352: istore 4
    //   354: aload 13
    //   356: astore 12
    //   358: goto +98 -> 456
    //   361: iconst_3
    //   362: istore 6
    //   364: getstatic 89	com/luajava/LuaJavaAPI:n	Ljava/util/HashMap;
    //   367: aload_3
    //   368: invokevirtual 497	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   371: checkcast 277	java/lang/reflect/Method
    //   374: astore 13
    //   376: iload 6
    //   378: istore 4
    //   380: aload 13
    //   382: astore 12
    //   384: aload 13
    //   386: ifnull +70 -> 456
    //   389: aload 17
    //   391: iconst_0
    //   392: aload 15
    //   394: iconst_1
    //   395: invokevirtual 388	com/luajava/LuaState:toNumber	(I)D
    //   398: invokestatic 391	java/lang/Double:valueOf	(D)Ljava/lang/Double;
    //   401: aload 13
    //   403: invokevirtual 279	java/lang/reflect/Method:getParameterTypes	()[Ljava/lang/Class;
    //   406: iconst_0
    //   407: aaload
    //   408: invokestatic 394	com/luajava/LuaState:convertLuaNumber	(Ljava/lang/Double;Ljava/lang/Class;)Ljava/lang/Number;
    //   411: aastore
    //   412: iload 6
    //   414: istore 4
    //   416: aload 13
    //   418: astore 12
    //   420: goto +36 -> 456
    //   423: getstatic 91	com/luajava/LuaJavaAPI:o	Ljava/util/HashMap;
    //   426: aload_3
    //   427: invokevirtual 497	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   430: checkcast 277	java/lang/reflect/Method
    //   433: astore 12
    //   435: aload 12
    //   437: ifnull +16 -> 453
    //   440: aload 17
    //   442: iconst_0
    //   443: aload 15
    //   445: iconst_1
    //   446: invokevirtual 400	com/luajava/LuaState:toBoolean	(I)Z
    //   449: invokestatic 403	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   452: aastore
    //   453: iconst_1
    //   454: istore 4
    //   456: iload 4
    //   458: istore 6
    //   460: aload 12
    //   462: ifnull +155 -> 617
    //   465: aload 12
    //   467: invokevirtual 278	java/lang/reflect/Method:getModifiers	()I
    //   470: invokestatic 239	java/lang/reflect/Modifier:isPublic	(I)Z
    //   473: ifne +9 -> 482
    //   476: aload 12
    //   478: iconst_1
    //   479: invokevirtual 581	java/lang/reflect/Method:setAccessible	(Z)V
    //   482: aload 12
    //   484: aload_2
    //   485: aload 17
    //   487: invokevirtual 308	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   490: astore_2
    //   491: aload_2
    //   492: ifnonnull +22 -> 514
    //   495: aload 12
    //   497: invokevirtual 575	java/lang/reflect/Method:getReturnType	()Ljava/lang/Class;
    //   500: getstatic 578	java/lang/Void:TYPE	Ljava/lang/Class;
    //   503: invokevirtual 565	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   506: ifeq +8 -> 514
    //   509: aload 15
    //   511: monitorexit
    //   512: iconst_0
    //   513: ireturn
    //   514: aload 15
    //   516: aload_2
    //   517: invokevirtual 461	com/luajava/LuaState:pushObjectValue	(Ljava/lang/Object;)V
    //   520: aload 15
    //   522: monitorexit
    //   523: iconst_1
    //   524: ireturn
    //   525: astore_2
    //   526: aload 16
    //   528: ldc -106
    //   530: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   533: pop
    //   534: aload 16
    //   536: aload 12
    //   538: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   541: pop
    //   542: aload 16
    //   544: ldc -97
    //   546: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   549: pop
    //   550: aload_2
    //   551: astore_3
    //   552: aload_2
    //   553: invokevirtual 163	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   556: ifnull +8 -> 564
    //   559: aload_2
    //   560: invokevirtual 163	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   563: astore_3
    //   564: aload 16
    //   566: aload_3
    //   567: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   570: pop
    //   571: aload 16
    //   573: ldc -91
    //   575: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   578: pop
    //   579: new 140	java/lang/StringBuilder
    //   582: dup
    //   583: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   586: astore_2
    //   587: aload_2
    //   588: ldc_w 580
    //   591: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   594: pop
    //   595: aload_2
    //   596: aload 16
    //   598: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   601: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   604: pop
    //   605: new 176	com/luajava/LuaException
    //   608: dup
    //   609: aload_2
    //   610: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   613: invokespecial 179	com/luajava/LuaException:<init>	(Ljava/lang/String;)V
    //   616: athrow
    //   617: getstatic 41	com/luajava/LuaJavaAPI:methodCache	Ljava/util/HashMap;
    //   620: aload_3
    //   621: invokevirtual 497	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   624: checkcast 583	[Ljava/lang/reflect/Method;
    //   627: astore 18
    //   629: iload 5
    //   631: newarray <illegal type>
    //   633: astore 12
    //   635: iconst_0
    //   636: istore 4
    //   638: iload 4
    //   640: iload 5
    //   642: if_icmpge +28 -> 670
    //   645: iload 4
    //   647: iconst_1
    //   648: iadd
    //   649: istore 7
    //   651: aload 12
    //   653: iload 4
    //   655: aload 15
    //   657: iload 7
    //   659: invokevirtual 414	com/luajava/LuaState:type	(I)I
    //   662: iastore
    //   663: iload 7
    //   665: istore 4
    //   667: goto -29 -> 638
    //   670: aload 18
    //   672: arraylength
    //   673: istore 9
    //   675: iconst_0
    //   676: istore 7
    //   678: iload 5
    //   680: istore 4
    //   682: iload 7
    //   684: iload 9
    //   686: if_icmpge +350 -> 1036
    //   689: aload 18
    //   691: iload 7
    //   693: aaload
    //   694: astore 14
    //   696: aload 14
    //   698: invokevirtual 279	java/lang/reflect/Method:getParameterTypes	()[Ljava/lang/Class;
    //   701: astore 13
    //   703: aload 13
    //   705: arraylength
    //   706: iload 4
    //   708: if_icmpeq +496 -> 1204
    //   711: iload 4
    //   713: istore 5
    //   715: aload 12
    //   717: astore 13
    //   719: goto +474 -> 1193
    //   722: aload 13
    //   724: arraylength
    //   725: istore 8
    //   727: iload 5
    //   729: iload 8
    //   731: if_icmpge +52 -> 783
    //   734: aload 13
    //   736: iload 5
    //   738: aaload
    //   739: astore 19
    //   741: aload 12
    //   743: iload 5
    //   745: iaload
    //   746: istore 10
    //   748: iload 5
    //   750: iconst_1
    //   751: iadd
    //   752: istore 8
    //   754: aload 17
    //   756: iload 5
    //   758: aload 15
    //   760: aload 19
    //   762: iload 10
    //   764: iload 8
    //   766: invokestatic 585	com/luajava/LuaJavaAPI:a	(Lcom/luajava/LuaState;Ljava/lang/Class;II)Ljava/lang/Object;
    //   769: aastore
    //   770: iload 8
    //   772: istore 5
    //   774: goto -52 -> 722
    //   777: iconst_0
    //   778: istore 8
    //   780: goto +6 -> 786
    //   783: iconst_1
    //   784: istore 8
    //   786: iload 4
    //   788: istore 5
    //   790: aload 12
    //   792: astore 13
    //   794: iload 8
    //   796: ifeq +397 -> 1193
    //   799: aload 14
    //   801: invokevirtual 278	java/lang/reflect/Method:getModifiers	()I
    //   804: invokestatic 239	java/lang/reflect/Modifier:isPublic	(I)Z
    //   807: istore 11
    //   809: iload 11
    //   811: ifne +17 -> 828
    //   814: aload 14
    //   816: iconst_1
    //   817: invokevirtual 581	java/lang/reflect/Method:setAccessible	(Z)V
    //   820: goto +8 -> 828
    //   823: astore 13
    //   825: goto +149 -> 974
    //   828: aload 14
    //   830: aload_2
    //   831: aload 17
    //   833: invokevirtual 308	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   836: astore 13
    //   838: iload 6
    //   840: lookupswitch	default:+52->892, 0:+91->931, 1:+84->924, 3:+77->917, 4:+70->910, 9:+55->895
    //   892: goto +46 -> 938
    //   895: getstatic 87	com/luajava/LuaJavaAPI:m	Ljava/util/HashMap;
    //   898: astore_2
    //   899: aload_2
    //   900: aload_3
    //   901: aload 14
    //   903: invokevirtual 501	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   906: pop
    //   907: goto +31 -> 938
    //   910: getstatic 85	com/luajava/LuaJavaAPI:l	Ljava/util/HashMap;
    //   913: astore_2
    //   914: goto -15 -> 899
    //   917: getstatic 89	com/luajava/LuaJavaAPI:n	Ljava/util/HashMap;
    //   920: astore_2
    //   921: goto -22 -> 899
    //   924: getstatic 91	com/luajava/LuaJavaAPI:o	Ljava/util/HashMap;
    //   927: astore_2
    //   928: goto -29 -> 899
    //   931: getstatic 93	com/luajava/LuaJavaAPI:p	Ljava/util/HashMap;
    //   934: astore_2
    //   935: goto -36 -> 899
    //   938: aload 13
    //   940: ifnonnull +22 -> 962
    //   943: aload 14
    //   945: invokevirtual 575	java/lang/reflect/Method:getReturnType	()Ljava/lang/Class;
    //   948: getstatic 578	java/lang/Void:TYPE	Ljava/lang/Class;
    //   951: invokevirtual 565	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   954: ifeq +8 -> 962
    //   957: aload 15
    //   959: monitorexit
    //   960: iconst_0
    //   961: ireturn
    //   962: aload 15
    //   964: aload 13
    //   966: invokevirtual 461	com/luajava/LuaState:pushObjectValue	(Ljava/lang/Object;)V
    //   969: aload 15
    //   971: monitorexit
    //   972: iconst_1
    //   973: ireturn
    //   974: aload 16
    //   976: ldc -106
    //   978: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   981: pop
    //   982: aload 16
    //   984: aload 14
    //   986: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   989: pop
    //   990: aload 16
    //   992: ldc -97
    //   994: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   997: pop
    //   998: aload 13
    //   1000: astore 14
    //   1002: aload 13
    //   1004: invokevirtual 163	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   1007: ifnull +10 -> 1017
    //   1010: aload 13
    //   1012: invokevirtual 163	java/lang/Exception:getCause	()Ljava/lang/Throwable;
    //   1015: astore 14
    //   1017: aload 16
    //   1019: aload 14
    //   1021: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1024: pop
    //   1025: aload 16
    //   1027: ldc -91
    //   1029: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1032: pop
    //   1033: goto +182 -> 1215
    //   1036: iconst_0
    //   1037: istore 4
    //   1039: aload 16
    //   1041: invokevirtual 168	java/lang/StringBuilder:length	()I
    //   1044: ifle +41 -> 1085
    //   1047: new 140	java/lang/StringBuilder
    //   1050: dup
    //   1051: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   1054: astore_2
    //   1055: aload_2
    //   1056: ldc_w 580
    //   1059: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1062: pop
    //   1063: aload_2
    //   1064: aload 16
    //   1066: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1069: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1072: pop
    //   1073: new 176	com/luajava/LuaException
    //   1076: dup
    //   1077: aload_2
    //   1078: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1081: invokespecial 179	com/luajava/LuaException:<init>	(Ljava/lang/String;)V
    //   1084: athrow
    //   1085: aload 18
    //   1087: arraylength
    //   1088: istore 5
    //   1090: iload 4
    //   1092: iload 5
    //   1094: if_icmpge +34 -> 1128
    //   1097: aload 16
    //   1099: aload 18
    //   1101: iload 4
    //   1103: aaload
    //   1104: invokevirtual 586	java/lang/reflect/Method:toString	()Ljava/lang/String;
    //   1107: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1110: pop
    //   1111: aload 16
    //   1113: ldc -91
    //   1115: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1118: pop
    //   1119: iload 4
    //   1121: iconst_1
    //   1122: iadd
    //   1123: istore 4
    //   1125: goto -35 -> 1090
    //   1128: new 140	java/lang/StringBuilder
    //   1131: dup
    //   1132: invokespecial 141	java/lang/StringBuilder:<init>	()V
    //   1135: astore_2
    //   1136: aload_2
    //   1137: ldc_w 588
    //   1140: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1143: pop
    //   1144: aload_2
    //   1145: aload 16
    //   1147: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1150: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1153: pop
    //   1154: new 176	com/luajava/LuaException
    //   1157: dup
    //   1158: aload_2
    //   1159: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1162: invokespecial 179	com/luajava/LuaException:<init>	(Ljava/lang/String;)V
    //   1165: athrow
    //   1166: astore_2
    //   1167: aload 15
    //   1169: monitorexit
    //   1170: aload_2
    //   1171: athrow
    //   1172: astore 13
    //   1174: goto -397 -> 777
    //   1177: iconst_0
    //   1178: istore 4
    //   1180: goto -990 -> 190
    //   1183: goto -727 -> 456
    //   1186: iload 4
    //   1188: istore 6
    //   1190: goto -573 -> 617
    //   1193: iload 5
    //   1195: istore 4
    //   1197: aload 13
    //   1199: astore 12
    //   1201: goto +14 -> 1215
    //   1204: iconst_0
    //   1205: istore 5
    //   1207: goto -485 -> 722
    //   1210: astore 13
    //   1212: goto -238 -> 974
    //   1215: iload 7
    //   1217: iconst_1
    //   1218: iadd
    //   1219: istore 7
    //   1221: goto -539 -> 682
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1224	0	paramLong	long
    //   0	1224	2	paramObject	Object
    //   0	1224	3	paramString	String
    //   29	1167	4	i1	int
    //   26	1180	5	i2	int
    //   209	980	6	i3	int
    //   649	571	7	i4	int
    //   725	70	8	i5	int
    //   673	14	9	i6	int
    //   746	17	10	i7	int
    //   807	3	11	bool	boolean
    //   19	1181	12	localObject1	Object
    //   253	540	13	localObject2	Object
    //   823	1	13	localException1	Exception
    //   836	175	13	localObject3	Object
    //   1172	26	13	localException2	Exception
    //   1210	1	13	localException3	Exception
    //   694	326	14	localObject4	Object
    //   4	1164	15	localLuaState	LuaState
    //   16	1130	16	localStringBuilder	StringBuilder
    //   195	637	17	arrayOfObject	Object[]
    //   627	473	18	arrayOfMethod	Method[]
    //   739	22	19	localClass	Class
    // Exception table:
    //   from	to	target	type
    //   53	64	98	java/lang/Exception
    //   465	482	525	java/lang/Exception
    //   482	491	525	java/lang/Exception
    //   814	820	823	java/lang/Exception
    //   9	18	1166	finally
    //   21	28	1166	finally
    //   36	48	1166	finally
    //   53	64	1166	finally
    //   68	85	1166	finally
    //   87	96	1166	finally
    //   99	123	1166	finally
    //   125	137	1166	finally
    //   137	190	1166	finally
    //   190	197	1166	finally
    //   203	211	1166	finally
    //   243	255	1166	finally
    //   268	278	1166	finally
    //   289	298	1166	finally
    //   302	314	1166	finally
    //   327	350	1166	finally
    //   364	376	1166	finally
    //   389	412	1166	finally
    //   423	435	1166	finally
    //   440	453	1166	finally
    //   465	482	1166	finally
    //   482	491	1166	finally
    //   495	512	1166	finally
    //   514	523	1166	finally
    //   526	550	1166	finally
    //   552	564	1166	finally
    //   564	617	1166	finally
    //   617	635	1166	finally
    //   651	663	1166	finally
    //   670	675	1166	finally
    //   696	711	1166	finally
    //   722	727	1166	finally
    //   754	770	1166	finally
    //   799	809	1166	finally
    //   814	820	1166	finally
    //   828	838	1166	finally
    //   895	899	1166	finally
    //   899	907	1166	finally
    //   910	914	1166	finally
    //   917	921	1166	finally
    //   924	928	1166	finally
    //   931	935	1166	finally
    //   943	960	1166	finally
    //   962	972	1166	finally
    //   974	998	1166	finally
    //   1002	1017	1166	finally
    //   1017	1033	1166	finally
    //   1039	1085	1166	finally
    //   1085	1090	1166	finally
    //   1097	1119	1166	finally
    //   1128	1166	1166	finally
    //   1167	1170	1166	finally
    //   754	770	1172	java/lang/Exception
    //   799	809	1210	java/lang/Exception
    //   828	838	1210	java/lang/Exception
  }
  
  public static int checkClass(LuaState paramLuaState, Object paramObject, String paramString)
  {
    for (;;)
    {
      int i1;
      try
      {
        if ((paramObject instanceof Class))
        {
          paramObject = ((Class)paramObject).getClasses();
          int i2 = paramObject.length;
          i1 = 0;
          if (i1 < i2)
          {
            Object localObject = paramObject[i1];
            if (((Class)localObject).getSimpleName().equals(paramString))
            {
              paramLuaState.pushJavaObject(localObject);
              return 3;
            }
          }
          else
          {
            return 0;
          }
        }
        else
        {
          return 0;
        }
      }
      finally {}
      i1 += 1;
    }
  }
  
  public static int checkField(LuaState paramLuaState, Object paramObject, String paramString)
  {
    for (;;)
    {
      Class localClass;
      int i1;
      try
      {
        if ((paramObject instanceof Class))
        {
          localClass = (Class)paramObject;
          i1 = 1;
        }
        else
        {
          localClass = paramObject.getClass();
          i1 = 0;
        }
      }
      finally {}
      try
      {
        paramString = localClass.getField(paramString);
        if (paramString == null) {
          return 0;
        }
        if ((i1 != 0) && (!Modifier.isStatic(paramString.getModifiers()))) {
          return 0;
        }
        try
        {
          if (!Modifier.isPublic(paramString.getModifiers())) {
            paramString.setAccessible(true);
          }
          paramObject = paramString.get(paramObject);
          paramLuaState.pushObjectValue(paramObject);
          if (Modifier.isFinal(paramString.getModifiers())) {
            return 5;
          }
          return 1;
        }
        catch (Exception paramObject)
        {
          throw new LuaException((Exception)paramObject);
        }
      }
      catch (NoSuchFieldException paramObject) {}
    }
    return 0;
  }
  
  public static int checkMethod(LuaState paramLuaState, Object paramObject, String paramString)
  {
    try
    {
      boolean bool;
      if ((paramObject instanceof Class))
      {
        paramObject = (Class)paramObject;
        bool = true;
      }
      else
      {
        paramObject = paramObject.getClass();
        bool = false;
      }
      String str = paramLuaState.toString(-1);
      Method[] arrayOfMethod2 = (Method[])methodCache.get(str);
      Method[] arrayOfMethod1 = arrayOfMethod2;
      if (arrayOfMethod2 == null)
      {
        paramObject = getMethod((Class)paramObject, paramString, bool);
        arrayOfMethod1 = new Method[((ArrayList)paramObject).size()];
        ((ArrayList)paramObject).toArray(arrayOfMethod1);
        methodCache.put(str, arrayOfMethod1);
      }
      if (arrayOfMethod1.length == 0) {
        return 0;
      }
      return 2;
    }
    finally {}
  }
  
  public static int createArray(long paramLong, String paramString)
  {
    synchronized (LuaStateFactory.getExistingState(paramLong))
    {
      int i1 = c(???, javaBindClass(paramString));
      return i1;
    }
  }
  
  public static int createProxy(long paramLong, String paramString)
  {
    synchronized (LuaStateFactory.getExistingState(paramLong))
    {
      int i1 = a(???, paramString);
      return i1;
    }
  }
  
  private static int d(LuaState paramLuaState, Class<?> paramClass)
  {
    try
    {
      paramLuaState.pushJavaObject(c(paramLuaState, paramClass, 2));
      return 1;
    }
    finally {}
  }
  
  private static Object d(LuaState paramLuaState, Class<Map<Object, Object>> paramClass, int paramInt)
  {
    try
    {
      if (paramClass.equals(f)) {
        paramClass = new HashMap();
      } else {
        paramClass = (Map)paramClass.newInstance();
      }
      paramLuaState.pushNil();
      while (paramLuaState.next(paramInt) != 0)
      {
        paramClass.put(paramLuaState.toJavaObject(-2), paramLuaState.toJavaObject(-1));
        paramLuaState.pop(1);
      }
      return paramClass;
    }
    catch (Exception paramClass)
    {
      throw new LuaException(paramClass);
      throw paramClass;
    }
    finally
    {
      for (;;) {}
    }
  }
  
  private static int e(LuaState paramLuaState, Class<?> paramClass)
  {
    try
    {
      paramLuaState.pushJavaObject(d(paramLuaState, paramClass, 2));
      return 1;
    }
    finally {}
  }
  
  private static Object e(LuaState paramLuaState, Class<?> paramClass, int paramInt)
  {
    return a(paramLuaState, paramClass, paramLuaState.type(paramInt), paramInt);
  }
  
  private static int f(LuaState paramLuaState, Class paramClass, int paramInt)
  {
    char c1;
    if ((paramClass == Character.TYPE) && (paramLuaState.type(paramInt) == 4))
    {
      paramClass = paramLuaState.toString(paramInt);
      if (paramClass.length() == 1)
      {
        c1 = paramClass.charAt(0);
      }
      else
      {
        paramClass = paramClass.toCharArray();
        break label257;
      }
    }
    else
    {
      if (!paramLuaState.isNumber(paramInt))
      {
        paramClass = new StringBuilder();
        paramClass.append(paramLuaState.toString(paramInt));
        paramClass.append(" is not number");
        throw new LuaException(paramClass.toString());
      }
      if (paramClass == Double.TYPE)
      {
        paramClass = Double.valueOf(paramLuaState.toNumber(paramInt));
        break label257;
      }
      if (paramClass == Float.TYPE)
      {
        paramClass = Float.valueOf((float)paramLuaState.toNumber(paramInt));
        break label257;
      }
      if (paramClass == Long.TYPE)
      {
        paramClass = Long.valueOf(paramLuaState.toInteger(paramInt));
        break label257;
      }
      if (paramClass == Integer.TYPE)
      {
        paramClass = Integer.valueOf((int)paramLuaState.toInteger(paramInt));
        break label257;
      }
      if (paramClass == Short.TYPE)
      {
        paramClass = Short.valueOf((short)(int)paramLuaState.toInteger(paramInt));
        break label257;
      }
      if (paramClass != Character.TYPE) {
        break label215;
      }
      c1 = (char)(int)paramLuaState.toInteger(paramInt);
    }
    paramClass = Character.valueOf(c1);
    break label257;
    label215:
    if (paramClass == Byte.TYPE) {
      paramClass = Byte.valueOf((byte)(int)paramLuaState.toInteger(paramInt));
    } else if (paramClass == Boolean.TYPE) {
      paramClass = Boolean.valueOf(paramLuaState.toBoolean(paramInt));
    } else {
      paramClass = null;
    }
    label257:
    paramLuaState.pushJavaObject(paramClass);
    return 1;
  }
  
  public static int getArrayValue(long paramLong, Object paramObject, int paramInt)
  {
    synchronized (LuaStateFactory.getExistingState(paramLong))
    {
      if (paramObject.getClass().isArray())
      {
        paramObject = Array.get(paramObject, paramInt);
      }
      else if ((paramObject instanceof List))
      {
        paramObject = ((List)paramObject).get(paramInt);
      }
      else
      {
        if (!(paramObject instanceof Map)) {
          break label82;
        }
        paramObject = ((Map)paramObject).get(Long.valueOf(paramInt));
      }
      ???.pushObjectValue(paramObject);
      return 1;
      label82:
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("can not get ");
      localStringBuilder.append(paramObject.getClass().getName());
      localStringBuilder.append(" value in ");
      localStringBuilder.append(paramInt);
      throw new LuaException(localStringBuilder.toString());
    }
  }
  
  public static int getContext(long paramLong)
  {
    synchronized (LuaStateFactory.getExistingState(paramLong))
    {
      ???.pushJavaObject(???.getContext());
      return 1;
    }
  }
  
  public static ArrayList<Method> getMethod(Class<?> paramClass, String paramString, boolean paramBoolean)
  {
    Object localObject1 = (HashMap)q.get(paramClass);
    Object localObject2 = localObject1;
    if (localObject1 == null)
    {
      localObject2 = new HashMap();
      q.put(paramClass, localObject2);
    }
    Object localObject3 = (ArrayList)((HashMap)localObject2).get(paramString);
    localObject1 = localObject3;
    if (localObject3 == null)
    {
      localObject3 = (Method[])methodsMap.get(paramClass);
      localObject1 = localObject3;
      if (localObject3 == null)
      {
        localObject1 = paramClass.getMethods();
        methodsMap.put(paramClass, localObject1);
      }
      int i2 = localObject1.length;
      int i1 = 0;
      while (i1 < i2)
      {
        Object localObject4 = localObject1[i1];
        String str = ((Method)localObject4).getName();
        localObject3 = (ArrayList)((HashMap)localObject2).get(str);
        paramClass = (Class<?>)localObject3;
        if (localObject3 == null)
        {
          paramClass = new ArrayList();
          ((HashMap)localObject2).put(str, paramClass);
        }
        paramClass.add(localObject4);
        i1 += 1;
      }
      localObject1 = (ArrayList)((HashMap)localObject2).get(paramString);
    }
    paramClass = (Class<?>)localObject1;
    if (localObject1 == null) {
      paramClass = new ArrayList();
    }
    if (paramBoolean)
    {
      localObject1 = new ArrayList();
      paramClass = paramClass.iterator();
      while (paramClass.hasNext())
      {
        localObject2 = (Method)paramClass.next();
        if (Modifier.isStatic(((Method)localObject2).getModifiers())) {
          ((ArrayList)localObject1).add(localObject2);
        }
      }
      paramClass = (Class<?>)localObject1;
      if (((ArrayList)localObject1).isEmpty()) {
        paramClass = getMethod(Class.class, paramString, false);
      }
      return paramClass;
    }
    return paramClass;
  }
  
  public static Class javaBindClass(String paramString)
  {
    try
    {
      localObject = Class.forName(paramString);
      return (Class)localObject;
    }
    catch (Exception localException)
    {
      Object localObject;
      int i1;
      for (;;) {}
    }
    i1 = -1;
    switch (paramString.hashCode())
    {
    default: 
      break;
    case 109413500: 
      if (paramString.equals("short")) {
        i1 = 3;
      }
      break;
    case 97526364: 
      if (paramString.equals("float")) {
        i1 = 6;
      }
      break;
    case 64711720: 
      if (paramString.equals("boolean")) {
        i1 = 0;
      }
      break;
    case 3327612: 
      if (paramString.equals("long")) {
        i1 = 5;
      }
      break;
    case 3052374: 
      if (paramString.equals("char")) {
        i1 = 2;
      }
      break;
    case 3039496: 
      if (paramString.equals("byte")) {
        i1 = 1;
      }
      break;
    case 104431: 
      if (paramString.equals("int")) {
        i1 = 4;
      }
      break;
    case -1325958191: 
      if (paramString.equals("double")) {
        i1 = 7;
      }
      break;
    }
    switch (i1)
    {
    default: 
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Class not found: ");
      ((StringBuilder)localObject).append(paramString);
      throw new LuaException(((StringBuilder)localObject).toString());
    case 7: 
      return Double.TYPE;
    case 6: 
      return Float.TYPE;
    case 5: 
      return Long.TYPE;
    case 4: 
      return Integer.TYPE;
    case 3: 
      return Short.TYPE;
    case 2: 
      return Character.TYPE;
    case 1: 
      return Byte.TYPE;
    }
    return Boolean.TYPE;
  }
  
  public static int javaCreate(long paramLong, Class<?> paramClass)
  {
    synchronized (LuaStateFactory.getExistingState(paramLong))
    {
      if (paramClass.isPrimitive())
      {
        i1 = c(???, paramClass);
        return i1;
      }
      if (c.isAssignableFrom(paramClass))
      {
        i1 = d(???, paramClass);
        return i1;
      }
      if (f.isAssignableFrom(paramClass))
      {
        i1 = e(???, paramClass);
        return i1;
      }
      if (paramClass.isInterface())
      {
        i1 = b(???, paramClass);
        return i1;
      }
      if (???.objLen(-1) == 0)
      {
        i1 = c(???, paramClass);
        return i1;
      }
      if (paramClass.isAssignableFrom(new LuaTable(???, -1).get(Integer.valueOf(1)).getClass()))
      {
        i1 = c(???, paramClass);
        return i1;
      }
      int i1 = a(???, paramClass);
      return i1;
    }
  }
  
  public static int javaEquals(long paramLong, Object paramObject1, Object paramObject2)
  {
    synchronized (LuaStateFactory.getExistingState(paramLong))
    {
      ???.pushBoolean(paramObject1.equals(paramObject2));
      return 1;
    }
  }
  
  public static int javaGetType(long paramLong, Object paramObject)
  {
    LuaState localLuaState = LuaStateFactory.getExistingState(paramLong);
    if (paramObject == null) {
      paramObject = "null";
    }
    try
    {
      for (;;)
      {
        localLuaState.pushString((String)paramObject);
        break;
        paramObject = paramObject.getClass().getName();
      }
      return 1;
    }
    finally
    {
      for (;;) {}
    }
    throw ((Throwable)paramObject);
  }
  
  public static int javaGetter(LuaState paramLuaState, Object paramObject, String paramString)
  {
    String str1 = null;
    for (;;)
    {
      Class localClass;
      int i1;
      Object localObject;
      String str2;
      try
      {
        if ((paramObject instanceof Map))
        {
          paramLuaState.pushObjectValue(((Map)paramObject).get(paramString));
          return 1;
        }
        if ((paramObject instanceof Class))
        {
          localClass = (Class)paramObject;
          i1 = 1;
        }
        else
        {
          localClass = paramObject.getClass();
          i1 = 0;
        }
        char c1 = paramString.charAt(0);
        localObject = paramString;
        if (Character.isLowerCase(c1))
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append(Character.toUpperCase(c1));
          ((StringBuilder)localObject).append(paramString.substring(1));
          localObject = ((StringBuilder)localObject).toString();
        }
        paramString = new StringBuilder();
        paramString.append(localClass.toString());
        paramString.append("@<-");
        paramString.append((String)localObject);
        str2 = paramString.toString();
        paramString = str1;
        if (i1 == 0) {
          paramString = (Method)p.get(str2);
        }
        str1 = paramString;
        if (paramString != null) {}
      }
      finally {}
      try
      {
        paramString = new StringBuilder();
        paramString.append("get");
        paramString.append((String)localObject);
        paramString = localClass.getMethod(paramString.toString(), new Class[0]);
      }
      catch (NoSuchMethodException paramString)
      {
        continue;
      }
      try
      {
        paramString = new StringBuilder();
        paramString.append("is");
        paramString.append((String)localObject);
        paramString = localClass.getMethod(paramString.toString(), new Class[0]);
        if ((i1 != 0) && (!Modifier.isStatic(paramString.getModifiers()))) {
          return 0;
        }
        p.put(str2, paramString);
        str1 = paramString;
      }
      catch (NoSuchMethodException paramObject) {}
    }
    return 0;
    try
    {
      paramObject = str1.invoke(paramObject, new Object[0]);
      if ((paramObject instanceof CharSequence)) {
        paramLuaState.pushString(paramObject.toString());
      } else {
        paramLuaState.pushObjectValue(paramObject);
      }
      return 1;
    }
    catch (Exception paramObject)
    {
      throw new LuaException((Exception)paramObject);
    }
  }
  
  public static int javaLoadLib(long paramLong, String paramString1, String paramString2)
  {
    synchronized (LuaStateFactory.getExistingState(paramLong))
    {
      try
      {
        paramString1 = Class.forName(paramString1);
        try
        {
          paramString1 = paramString1.getMethod(paramString2, new Class[] { a }).invoke(null, new Object[] { ??? });
          if ((paramString1 != null) && ((paramString1 instanceof Integer)))
          {
            int i1 = ((Integer)paramString1).intValue();
            return i1;
          }
          return 0;
        }
        catch (Exception paramString1)
        {
          paramString2 = new StringBuilder();
          paramString2.append("Error on calling method. Library could not be loaded. ");
          paramString2.append(paramString1.getMessage());
          throw new LuaException(paramString2.toString());
        }
      }
      catch (ClassNotFoundException paramString1)
      {
        throw new LuaException(paramString1);
      }
      throw paramString1;
    }
  }
  
  public static int javaNew(long paramLong, Class<?> paramClass)
  {
    synchronized (LuaStateFactory.getExistingState(paramLong))
    {
      if (paramClass.isPrimitive())
      {
        int i2 = ???.getTop();
        i1 = 2;
        while (i1 <= i2)
        {
          f(???, paramClass, i1);
          i1 += 1;
        }
        return i2 - 1;
      }
      int i1 = a(???, paramClass);
      return i1;
    }
  }
  
  public static int javaNewInstance(long paramLong, String paramString)
  {
    synchronized (LuaStateFactory.getExistingState(paramLong))
    {
      paramString = javaBindClass(paramString);
      if (paramString.isPrimitive())
      {
        i1 = f(???, paramString, -1);
        return i1;
      }
      int i1 = a(???, paramString);
      return i1;
    }
  }
  
  public static int javaObjectLength(long paramLong, Object paramObject)
  {
    synchronized (LuaStateFactory.getExistingState(paramLong))
    {
      try
      {
        int i1;
        if ((paramObject instanceof CharSequence)) {
          i1 = ((CharSequence)paramObject).length();
        } else if ((paramObject instanceof Collection)) {
          i1 = ((Collection)paramObject).size();
        } else if ((paramObject instanceof Map)) {
          i1 = ((Map)paramObject).size();
        } else {
          i1 = Array.getLength(paramObject);
        }
        paramLong = i1;
        ???.pushInteger(paramLong);
        return 1;
      }
      catch (Exception paramObject)
      {
        throw new LuaException((Exception)paramObject);
      }
      throw ((Throwable)paramObject);
    }
  }
  
  public static int javaSetter(LuaState paramLuaState, Object paramObject, String paramString)
  {
    try
    {
      boolean bool2 = paramObject instanceof Map;
      boolean bool1 = true;
      if (bool2)
      {
        ((Map)paramObject).put(paramString, paramLuaState.toJavaObject(-1));
        return 1;
      }
      if ((paramObject instanceof Class))
      {
        Class localClass = (Class)paramObject;
      }
      else
      {
        paramObject.getClass();
        bool1 = false;
      }
      if ((paramString.length() > 2) && (paramString.substring(0, 2).equals("on")) && (paramLuaState.type(-1) == 6))
      {
        i1 = a(paramLuaState, paramObject, paramString, bool1);
        return i1;
      }
      int i1 = b(paramLuaState, paramObject, paramString, bool1);
      if (i1 != 0) {
        return i1;
      }
      i1 = a(paramLuaState, paramObject, paramString);
      return i1;
    }
    finally {}
  }
  
  public static int javaSetter(LuaState paramLuaState, Object paramObject1, String paramString, Object paramObject2)
  {
    paramLuaState.pushObjectValue(paramObject2);
    int i1 = javaSetter(paramLuaState, paramObject1, paramString);
    paramLuaState.pop(1);
    return i1;
  }
  
  public static int javaToString(long paramLong, Object paramObject)
  {
    LuaState localLuaState = LuaStateFactory.getExistingState(paramLong);
    if (paramObject == null) {
      paramObject = "null";
    }
    try
    {
      for (;;)
      {
        localLuaState.pushString((String)paramObject);
        break;
        paramObject = paramObject.toString();
      }
      return 1;
    }
    finally
    {
      for (;;) {}
    }
    throw ((Throwable)paramObject);
  }
  
  public static int newArray(long paramLong, Class<?> paramClass)
  {
    synchronized (LuaStateFactory.getExistingState(paramLong))
    {
      try
      {
        int i2 = ???.getTop() - 1;
        localObject = new int[i2];
        int i1 = 0;
        while (i1 < i2)
        {
          localObject[i1] = ((int)???.toInteger(i1 + 2));
          i1 += 1;
        }
        ???.pushJavaObject(Array.newInstance(paramClass, (int[])localObject));
        return 1;
      }
      catch (Exception paramClass)
      {
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append("can not create a array: ");
        ((StringBuilder)localObject).append(paramClass.getMessage());
        throw new LuaException(((StringBuilder)localObject).toString());
      }
      throw paramClass;
    }
  }
  
  public static int newArray(long paramLong, Class<?> paramClass, int paramInt)
  {
    synchronized (LuaStateFactory.getExistingState(paramLong))
    {
      try
      {
        ???.pushJavaObject(Array.newInstance(paramClass, paramInt));
        return 1;
      }
      catch (Exception paramClass)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("can not create a array: ");
        localStringBuilder.append(paramClass.getMessage());
        throw new LuaException(localStringBuilder.toString());
      }
      throw paramClass;
    }
  }
  
  public static int objectCall(long paramLong, Object paramObject)
  {
    synchronized (LuaStateFactory.getExistingState(paramLong))
    {
      boolean bool = paramObject instanceof LuaMetaTable;
      int i1 = 2;
      if (bool)
      {
        int i2 = ???.getTop();
        Object[] arrayOfObject = new Object[i2 - 1];
        while (i1 <= i2)
        {
          arrayOfObject[(i1 - 2)] = ???.toJavaObject(i1);
          i1 += 1;
        }
        ???.pushObjectValue(((LuaMetaTable)paramObject).__call(arrayOfObject));
        return 1;
      }
      if (???.isTable(2))
      {
        ???.pushNil();
        if ((paramObject instanceof List))
        {
          paramObject = (List)paramObject;
          while (???.next(2) != 0)
          {
            ((List)paramObject).add(???.toJavaObject(-1));
            ???.pop(1);
          }
        }
        while (???.next(2) != 0)
        {
          javaSetter(???, paramObject, ???.toString(-2));
          ???.pop(1);
        }
        ???.setTop(1);
        return 1;
      }
      return 0;
    }
  }
  
  public static int objectIndex(long paramLong, Object paramObject, String paramString, int paramInt)
  {
    LuaState localLuaState = LuaStateFactory.getExistingState(paramLong);
    if (paramInt == 0)
    {
      try
      {
        if (checkMethod(localLuaState, paramObject, paramString) == 0) {
          break label121;
        }
        return 2;
      }
      finally
      {
        int i1;
        label49:
        label64:
        for (;;) {}
      }
      i1 = checkField(localLuaState, paramObject, paramString);
      if (i1 == 0) {
        break label141;
      }
      return i1;
      if (javaGetter(localLuaState, paramObject, paramString) == 0) {
        break label155;
      }
      return 4;
      if (checkClass(localLuaState, paramObject, paramString) == 0) {
        break label169;
      }
      return 3;
    }
    for (;;)
    {
      label79:
      if ((paramObject instanceof LuaMetaTable))
      {
        localLuaState.pushObjectValue(((LuaMetaTable)paramObject).__index(paramString));
        return 6;
      }
      label121:
      label141:
      label155:
      label169:
      do
      {
        return 0;
        throw ((Throwable)paramObject);
        if ((paramInt == 0) || (paramInt == 1) || (paramInt == 5)) {
          break;
        }
        if ((paramInt == 0) || (paramInt == 4)) {
          break label49;
        }
        if ((paramInt == 0) || (paramInt == 3)) {
          break label64;
        }
        if (paramInt == 0) {
          break label79;
        }
      } while (paramInt != 6);
    }
  }
  
  public static int objectNewIndex(long paramLong, Object paramObject, String paramString, int paramInt)
  {
    LuaState localLuaState = LuaStateFactory.getExistingState(paramLong);
    if ((paramInt == 0) || (paramInt == 1))
    {
      try
      {
        if (setFieldValue(localLuaState, paramObject, paramString) == 0) {
          break label89;
        }
        return 1;
      }
      finally {}
      if (javaSetter(localLuaState, paramObject, paramString) == 0) {
        break label103;
      }
      return 2;
    }
    for (;;)
    {
      label50:
      if ((paramObject instanceof LuaMetaTable))
      {
        ((LuaMetaTable)paramObject).__newIndex(paramString, localLuaState.toJavaObject(-1));
        return 3;
      }
      label89:
      label103:
      do
      {
        return 0;
        if ((paramInt == 0) || (paramInt == 2)) {
          break;
        }
        if (paramInt == 0) {
          break label50;
        }
      } while (paramInt != 3);
    }
  }
  
  public static int setArrayValue(long paramLong, Object paramObject, int paramInt)
  {
    synchronized (LuaStateFactory.getExistingState(paramLong))
    {
      if (paramObject.getClass().isArray()) {
        localObject = paramObject.getClass().getComponentType();
      }
      try
      {
        Array.set(paramObject, paramInt, e(???, (Class)localObject, 3));
      }
      catch (LuaException localLuaException)
      {
        StringBuilder localStringBuilder;
        for (;;) {}
      }
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramObject.getClass().getName());
      localStringBuilder.append(" [");
      localStringBuilder.append(paramInt);
      localStringBuilder.append("]");
      a(???, localStringBuilder.toString(), 3, (Class)localObject);
      break label163;
      if ((paramObject instanceof List))
      {
        ((List)paramObject).set(paramInt, ???.toJavaObject(3));
      }
      else
      {
        if (!(paramObject instanceof Map)) {
          break label168;
        }
        ((Map)paramObject).put(Long.valueOf(paramInt), ???.toJavaObject(3));
      }
      label163:
      return 0;
      label168:
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("can not set ");
      ((StringBuilder)localObject).append(paramObject.getClass().getName());
      ((StringBuilder)localObject).append(" value: ");
      ((StringBuilder)localObject).append(???.toJavaObject(3));
      ((StringBuilder)localObject).append(" in ");
      ((StringBuilder)localObject).append(paramInt);
      throw new LuaException(((StringBuilder)localObject).toString());
    }
  }
  
  /* Error */
  public static int setFieldValue(LuaState paramLuaState, Object paramObject, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ifnonnull +7 -> 10
    //   6: aload_0
    //   7: monitorexit
    //   8: iconst_0
    //   9: ireturn
    //   10: aload_1
    //   11: instanceof 106
    //   14: ifeq +14 -> 28
    //   17: aload_1
    //   18: checkcast 106	java/lang/Class
    //   21: astore 4
    //   23: iconst_1
    //   24: istore_3
    //   25: goto +11 -> 36
    //   28: aload_1
    //   29: invokevirtual 191	java/lang/Object:getClass	()Ljava/lang/Class;
    //   32: astore 4
    //   34: iconst_0
    //   35: istore_3
    //   36: aload 4
    //   38: aload_2
    //   39: invokevirtual 599	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   42: astore 5
    //   44: aload 5
    //   46: ifnonnull +7 -> 53
    //   49: aload_0
    //   50: monitorexit
    //   51: iconst_0
    //   52: ireturn
    //   53: iload_3
    //   54: ifeq +18 -> 72
    //   57: aload 5
    //   59: invokevirtual 227	java/lang/reflect/Field:getModifiers	()I
    //   62: invokestatic 233	java/lang/reflect/Modifier:isStatic	(I)Z
    //   65: ifne +7 -> 72
    //   68: aload_0
    //   69: monitorexit
    //   70: iconst_0
    //   71: ireturn
    //   72: aload 5
    //   74: invokevirtual 236	java/lang/reflect/Field:getType	()Ljava/lang/Class;
    //   77: astore 4
    //   79: aload 5
    //   81: invokevirtual 227	java/lang/reflect/Field:getModifiers	()I
    //   84: invokestatic 239	java/lang/reflect/Modifier:isPublic	(I)Z
    //   87: ifne +9 -> 96
    //   90: aload 5
    //   92: iconst_1
    //   93: invokevirtual 243	java/lang/reflect/Field:setAccessible	(Z)V
    //   96: aload 5
    //   98: aload_1
    //   99: aload_0
    //   100: aload 4
    //   102: aload_0
    //   103: invokevirtual 104	com/luajava/LuaState:getTop	()I
    //   106: invokestatic 148	com/luajava/LuaJavaAPI:e	(Lcom/luajava/LuaState;Ljava/lang/Class;I)Ljava/lang/Object;
    //   109: invokevirtual 247	java/lang/reflect/Field:set	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   112: goto +22 -> 134
    //   115: astore_1
    //   116: new 176	com/luajava/LuaException
    //   119: dup
    //   120: aload_1
    //   121: invokespecial 250	com/luajava/LuaException:<init>	(Ljava/lang/Exception;)V
    //   124: athrow
    //   125: aload_0
    //   126: aload_2
    //   127: iconst_m1
    //   128: aload 4
    //   130: invokestatic 253	com/luajava/LuaJavaAPI:a	(Lcom/luajava/LuaState;Ljava/lang/String;ILjava/lang/Class;)Ljava/lang/String;
    //   133: pop
    //   134: aload_0
    //   135: monitorexit
    //   136: iconst_1
    //   137: ireturn
    //   138: aload_0
    //   139: monitorexit
    //   140: iconst_0
    //   141: ireturn
    //   142: aload_0
    //   143: monitorexit
    //   144: aload_1
    //   145: athrow
    //   146: astore_1
    //   147: goto -9 -> 138
    //   150: astore_1
    //   151: goto -26 -> 125
    //   154: astore_1
    //   155: goto -13 -> 142
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	158	0	paramLuaState	LuaState
    //   0	158	1	paramObject	Object
    //   0	158	2	paramString	String
    //   24	30	3	i1	int
    //   21	108	4	localClass	Class
    //   42	55	5	localField	Field
    // Exception table:
    //   from	to	target	type
    //   79	96	115	java/lang/Exception
    //   96	112	115	java/lang/Exception
    //   36	44	146	java/lang/NoSuchFieldException
    //   79	96	150	com/luajava/LuaException
    //   96	112	150	com/luajava/LuaException
    //   6	8	154	finally
    //   10	23	154	finally
    //   28	34	154	finally
    //   36	44	154	finally
    //   49	51	154	finally
    //   57	70	154	finally
    //   72	79	154	finally
    //   79	96	154	finally
    //   96	112	154	finally
    //   116	125	154	finally
    //   125	134	154	finally
    //   134	136	154	finally
    //   138	140	154	finally
    //   142	144	154	finally
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\luajava\LuaJavaAPI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */