package com.luajava;

import com.androlua.LuaContext;

public class LuaState
{
  public static final int LUAI_MAXSTACK = 1000000;
  public static final int LUA_ERRERR = 6;
  public static final int LUA_ERRGCMM = 5;
  public static final int LUA_ERRMEM = 4;
  public static final int LUA_ERRRUN = 2;
  public static final int LUA_ERRSYNTAX = 3;
  public static final int LUA_GCCOLLECT = 2;
  public static final int LUA_GCCOUNT = 3;
  public static final int LUA_GCCOUNTB = 4;
  public static final int LUA_GCRESTART = 1;
  public static final int LUA_GCSETPAUSE = 6;
  public static final int LUA_GCSETSTEPMUL = 7;
  public static final int LUA_GCSTEP = 5;
  public static final int LUA_GCSTOP = 0;
  public static final int LUA_MULTRET = -1;
  public static final int LUA_OPEQ = 0;
  public static final int LUA_OPLE = 2;
  public static final int LUA_OPLT = 1;
  public static final int LUA_REGISTRYINDEX = -1001000;
  public static final int LUA_RIDX_GLOBALS = 2;
  public static final int LUA_RIDX_LAST = 2;
  public static final int LUA_RIDX_MAINTHREAD = 1;
  public static final int LUA_TBOOLEAN = 1;
  public static final int LUA_TFUNCTION = 6;
  public static final int LUA_TINTEGER = 9;
  public static final int LUA_TLIGHTUSERDATA = 2;
  public static final int LUA_TNIL = 0;
  public static final int LUA_TNONE = -1;
  public static final int LUA_TNUMBER = 3;
  public static final int LUA_TSTRING = 4;
  public static final int LUA_TTABLE = 5;
  public static final int LUA_TTHREAD = 8;
  public static final int LUA_TUSERDATA = 7;
  public static final int LUA_YIELD = 1;
  private static Class<?> a = Number.class;
  private static Class<?> b = Byte.class;
  private static Class<?> c = Short.class;
  private static Class<?> d = Integer.class;
  private static Class<?> e = Long.class;
  private static Class<?> f = Float.class;
  private static Class<?> g = Double.class;
  private long h;
  private LuaContext i;
  
  static
  {
    System.loadLibrary("luajava");
  }
  
  protected LuaState()
  {
    this.h = _newstate();
  }
  
  protected LuaState(long paramLong)
  {
    this.h = paramLong;
    LuaStateFactory.insertLuaState(this);
  }
  
  private synchronized native int _LargError(long paramLong, int paramInt, String paramString);
  
  private synchronized native int _LcallMeta(long paramLong, int paramInt, String paramString);
  
  private synchronized native void _LcheckAny(long paramLong, int paramInt);
  
  private synchronized native int _LcheckInteger(long paramLong, int paramInt);
  
  private synchronized native double _LcheckNumber(long paramLong, int paramInt);
  
  private synchronized native void _LcheckStack(long paramLong, int paramInt, String paramString);
  
  private synchronized native String _LcheckString(long paramLong, int paramInt);
  
  private synchronized native void _LcheckType(long paramLong, int paramInt1, int paramInt2);
  
  private synchronized native int _LdoFile(long paramLong, String paramString);
  
  private synchronized native int _LdoString(long paramLong, String paramString);
  
  private synchronized native int _LgetMetaField(long paramLong, int paramInt, String paramString);
  
  private synchronized native void _LgetMetatable(long paramLong, String paramString);
  
  private synchronized native String _Lgsub(long paramLong, String paramString1, String paramString2, String paramString3);
  
  private synchronized native int _LloadBuffer(long paramLong1, byte[] paramArrayOfByte, long paramLong2, String paramString);
  
  private synchronized native int _LloadFile(long paramLong, String paramString);
  
  private synchronized native int _LloadString(long paramLong, String paramString);
  
  private synchronized native int _LnewMetatable(long paramLong, String paramString);
  
  private synchronized native int _LoptInteger(long paramLong, int paramInt1, int paramInt2);
  
  private synchronized native double _LoptNumber(long paramLong, int paramInt, double paramDouble);
  
  private synchronized native String _LoptString(long paramLong, int paramInt, String paramString);
  
  private synchronized native int _Lref(long paramLong, int paramInt);
  
  private synchronized native void _LunRef(long paramLong, int paramInt1, int paramInt2);
  
  private synchronized native void _Lwhere(long paramLong, int paramInt);
  
  private synchronized native void _call(long paramLong, int paramInt1, int paramInt2);
  
  private synchronized native int _checkStack(long paramLong, int paramInt);
  
  private synchronized native void _close(long paramLong);
  
  private synchronized native int _compare(long paramLong, int paramInt1, int paramInt2, int paramInt3);
  
  private synchronized native void _concat(long paramLong, int paramInt);
  
  private synchronized native void _copy(long paramLong, int paramInt1, int paramInt2);
  
  private synchronized native void _createTable(long paramLong, int paramInt1, int paramInt2);
  
  private synchronized native byte[] _dump(long paramLong, int paramInt);
  
  private synchronized native int _equal(long paramLong, int paramInt1, int paramInt2);
  
  private synchronized native int _error(long paramLong);
  
  private synchronized native int _gc(long paramLong, int paramInt1, int paramInt2);
  
  private synchronized native int _getField(long paramLong, int paramInt, String paramString);
  
  private synchronized native int _getGlobal(long paramLong, String paramString);
  
  private synchronized native int _getI(long paramLong1, int paramInt, long paramLong2);
  
  private synchronized native int _getMetaTable(long paramLong, int paramInt);
  
  private synchronized native Object _getObjectFromUserdata(long paramLong, int paramInt);
  
  private synchronized native int _getTable(long paramLong, int paramInt);
  
  private synchronized native int _getTop(long paramLong);
  
  private synchronized native String _getUpValue(long paramLong, int paramInt1, int paramInt2);
  
  private synchronized native int _getUserValue(long paramLong, int paramInt);
  
  private synchronized native void _insert(long paramLong, int paramInt);
  
  private synchronized native int _isBoolean(long paramLong, int paramInt);
  
  private synchronized native int _isCFunction(long paramLong, int paramInt);
  
  private synchronized native int _isFunction(long paramLong, int paramInt);
  
  private synchronized native int _isInteger(long paramLong, int paramInt);
  
  private synchronized native boolean _isJavaFunction(long paramLong, int paramInt);
  
  private synchronized native int _isNil(long paramLong, int paramInt);
  
  private synchronized native int _isNone(long paramLong, int paramInt);
  
  private synchronized native int _isNoneOrNil(long paramLong, int paramInt);
  
  private synchronized native int _isNumber(long paramLong, int paramInt);
  
  private synchronized native boolean _isObject(long paramLong, int paramInt);
  
  private synchronized native int _isString(long paramLong, int paramInt);
  
  private synchronized native int _isTable(long paramLong, int paramInt);
  
  private synchronized native int _isThread(long paramLong, int paramInt);
  
  private synchronized native int _isUserdata(long paramLong, int paramInt);
  
  private synchronized native int _isYieldable(long paramLong);
  
  private synchronized native int _lessThan(long paramLong, int paramInt1, int paramInt2);
  
  private synchronized native void _newTable(long paramLong);
  
  private synchronized native long _newstate();
  
  private synchronized native long _newthread(long paramLong);
  
  private synchronized native int _next(long paramLong, int paramInt);
  
  private synchronized native int _objlen(long paramLong, int paramInt);
  
  private synchronized native void _openBase(long paramLong);
  
  private synchronized native void _openDebug(long paramLong);
  
  private synchronized native void _openIo(long paramLong);
  
  private synchronized native void _openLibs(long paramLong);
  
  private synchronized native void _openLuajava(long paramLong);
  
  private synchronized native void _openMath(long paramLong);
  
  private synchronized native void _openOs(long paramLong);
  
  private synchronized native void _openPackage(long paramLong);
  
  private synchronized native void _openString(long paramLong);
  
  private synchronized native void _openTable(long paramLong);
  
  private synchronized native int _pcall(long paramLong, int paramInt1, int paramInt2, int paramInt3);
  
  private synchronized native void _pop(long paramLong, int paramInt);
  
  private synchronized native void _pushBoolean(long paramLong, int paramInt);
  
  private synchronized native void _pushGlobalTable(long paramLong);
  
  private synchronized native void _pushInteger(long paramLong1, long paramLong2);
  
  private synchronized native void _pushJavaFunction(long paramLong, JavaFunction paramJavaFunction);
  
  private synchronized native void _pushJavaObject(long paramLong, Object paramObject);
  
  private synchronized native void _pushNil(long paramLong);
  
  private synchronized native void _pushNumber(long paramLong, double paramDouble);
  
  private synchronized native void _pushString(long paramLong, String paramString);
  
  private synchronized native void _pushString(long paramLong, byte[] paramArrayOfByte, int paramInt);
  
  private synchronized native void _pushValue(long paramLong, int paramInt);
  
  private synchronized native int _rawGet(long paramLong, int paramInt);
  
  private synchronized native int _rawGetI(long paramLong1, int paramInt, long paramLong2);
  
  private synchronized native void _rawSet(long paramLong, int paramInt);
  
  private synchronized native void _rawSetI(long paramLong1, int paramInt, long paramLong2);
  
  private synchronized native int _rawequal(long paramLong, int paramInt1, int paramInt2);
  
  private synchronized native int _rawlen(long paramLong, int paramInt);
  
  private synchronized native void _remove(long paramLong, int paramInt);
  
  private synchronized native void _replace(long paramLong, int paramInt);
  
  private synchronized native int _resume(long paramLong1, long paramLong2, int paramInt);
  
  private synchronized native void _rotate(long paramLong, int paramInt1, int paramInt2);
  
  private synchronized native void _setField(long paramLong, int paramInt, String paramString);
  
  private synchronized native void _setGlobal(long paramLong, String paramString);
  
  private synchronized native void _setI(long paramLong1, int paramInt, long paramLong2);
  
  private synchronized native int _setMetaTable(long paramLong, int paramInt);
  
  private synchronized native void _setTable(long paramLong, int paramInt);
  
  private synchronized native void _setTop(long paramLong, int paramInt);
  
  private synchronized native String _setUpValue(long paramLong, int paramInt1, int paramInt2);
  
  private synchronized native void _setUserValue(long paramLong, int paramInt);
  
  private synchronized native int _status(long paramLong);
  
  private synchronized native int _strlen(long paramLong, int paramInt);
  
  private synchronized native int _toBoolean(long paramLong, int paramInt);
  
  private synchronized native long _toInteger(long paramLong, int paramInt);
  
  private synchronized native double _toNumber(long paramLong, int paramInt);
  
  private synchronized native String _toString(long paramLong, int paramInt);
  
  private synchronized native long _toThread(long paramLong, int paramInt);
  
  private synchronized native int _type(long paramLong, int paramInt);
  
  private synchronized native String _typeName(long paramLong, int paramInt);
  
  private synchronized native void _xmove(long paramLong1, long paramLong2, int paramInt);
  
  private synchronized native int _yield(long paramLong, int paramInt);
  
  public static Number convertLuaNumber(Double paramDouble, Class<?> paramClass)
  {
    if (paramClass.isPrimitive())
    {
      if (paramClass == Integer.TYPE) {
        return Integer.valueOf(paramDouble.intValue());
      }
      if (paramClass == Long.TYPE) {
        return Long.valueOf(paramDouble.longValue());
      }
      if (paramClass == Float.TYPE) {
        return Float.valueOf(paramDouble.floatValue());
      }
      if (paramClass == Double.TYPE) {
        return Double.valueOf(paramDouble.doubleValue());
      }
      if (paramClass == Byte.TYPE) {
        return Byte.valueOf(paramDouble.byteValue());
      }
      if (paramClass == Short.TYPE) {
        return Short.valueOf(paramDouble.shortValue());
      }
    }
    else if (paramClass.isAssignableFrom(a))
    {
      if (paramClass.isAssignableFrom(d)) {
        return new Integer(paramDouble.intValue());
      }
      if (paramClass.isAssignableFrom(e)) {
        return new Long(paramDouble.longValue());
      }
      if (paramClass.isAssignableFrom(f)) {
        return new Float(paramDouble.floatValue());
      }
      if (paramClass.isAssignableFrom(g)) {
        return paramDouble;
      }
      if (paramClass.isAssignableFrom(b)) {
        return new Byte(paramDouble.byteValue());
      }
      if (paramClass.isAssignableFrom(c)) {
        return new Short(paramDouble.shortValue());
      }
    }
    return null;
  }
  
  public static Number convertLuaNumber(Long paramLong, Class<?> paramClass)
  {
    if (paramClass.isPrimitive())
    {
      if (paramClass == Integer.TYPE) {
        return Integer.valueOf(paramLong.intValue());
      }
      if (paramClass == Long.TYPE) {
        return Long.valueOf(paramLong.longValue());
      }
      if (paramClass == Float.TYPE) {
        return Float.valueOf(paramLong.floatValue());
      }
      if (paramClass == Double.TYPE) {
        return Double.valueOf(paramLong.doubleValue());
      }
      if (paramClass == Byte.TYPE) {
        return Byte.valueOf(paramLong.byteValue());
      }
      if (paramClass == Short.TYPE) {
        return Short.valueOf(paramLong.shortValue());
      }
    }
    else if (paramClass.isAssignableFrom(a))
    {
      if (paramClass.isAssignableFrom(d)) {
        return new Integer(paramLong.intValue());
      }
      if (paramClass.isAssignableFrom(e)) {
        return new Long(paramLong.longValue());
      }
      if (paramClass.isAssignableFrom(f)) {
        return new Float(paramLong.floatValue());
      }
      if (paramClass.isAssignableFrom(g)) {
        return paramLong;
      }
      if (paramClass.isAssignableFrom(b)) {
        return new Byte(paramLong.byteValue());
      }
      if (paramClass.isAssignableFrom(c)) {
        return new Short(paramLong.shortValue());
      }
    }
    return null;
  }
  
  public int LargError(int paramInt, String paramString)
  {
    return _LargError(this.h, paramInt, paramString);
  }
  
  public int LcallMeta(int paramInt, String paramString)
  {
    return _LcallMeta(this.h, paramInt, paramString);
  }
  
  public void LcheckAny(int paramInt)
  {
    _LcheckAny(this.h, paramInt);
  }
  
  public int LcheckInteger(int paramInt)
  {
    return _LcheckInteger(this.h, paramInt);
  }
  
  public double LcheckNumber(int paramInt)
  {
    return _LcheckNumber(this.h, paramInt);
  }
  
  public void LcheckStack(int paramInt, String paramString)
  {
    _LcheckStack(this.h, paramInt, paramString);
  }
  
  public String LcheckString(int paramInt)
  {
    return _LcheckString(this.h, paramInt);
  }
  
  public void LcheckType(int paramInt1, int paramInt2)
  {
    _LcheckType(this.h, paramInt1, paramInt2);
  }
  
  public int LdoFile(String paramString)
  {
    return _LdoFile(this.h, paramString);
  }
  
  public int LdoString(String paramString)
  {
    return _LdoString(this.h, paramString);
  }
  
  public int LgetMetaField(int paramInt, String paramString)
  {
    return _LgetMetaField(this.h, paramInt, paramString);
  }
  
  public void LgetMetatable(String paramString)
  {
    _LgetMetatable(this.h, paramString);
  }
  
  public String Lgsub(String paramString1, String paramString2, String paramString3)
  {
    return _Lgsub(this.h, paramString1, paramString2, paramString3);
  }
  
  public int LloadBuffer(byte[] paramArrayOfByte, String paramString)
  {
    return _LloadBuffer(this.h, paramArrayOfByte, paramArrayOfByte.length, paramString);
  }
  
  public int LloadFile(String paramString)
  {
    return _LloadFile(this.h, paramString);
  }
  
  public int LloadString(String paramString)
  {
    return _LloadString(this.h, paramString);
  }
  
  public int LnewMetatable(String paramString)
  {
    return _LnewMetatable(this.h, paramString);
  }
  
  public int LoptInteger(int paramInt1, int paramInt2)
  {
    return _LoptInteger(this.h, paramInt1, paramInt2);
  }
  
  public double LoptNumber(int paramInt, double paramDouble)
  {
    return _LoptNumber(this.h, paramInt, paramDouble);
  }
  
  public String LoptString(int paramInt, String paramString)
  {
    return _LoptString(this.h, paramInt, paramString);
  }
  
  public int Lref(int paramInt)
  {
    return _Lref(this.h, paramInt);
  }
  
  public void LunRef(int paramInt1, int paramInt2)
  {
    _LunRef(this.h, paramInt1, paramInt2);
  }
  
  public void Lwhere(int paramInt)
  {
    _Lwhere(this.h, paramInt);
  }
  
  public void call(int paramInt1, int paramInt2)
  {
    _call(this.h, paramInt1, paramInt2);
  }
  
  public int checkStack(int paramInt)
  {
    return _checkStack(this.h, paramInt);
  }
  
  public void close()
  {
    try
    {
      LuaStateFactory.removeLuaState(this.h);
      _close(this.h);
      this.h = 0L;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public int compare(int paramInt1, int paramInt2, int paramInt3)
  {
    return _compare(this.h, paramInt1, paramInt2, paramInt3);
  }
  
  public void concat(int paramInt)
  {
    _concat(this.h, paramInt);
  }
  
  public void copy(int paramInt1, int paramInt2)
  {
    _copy(this.h, paramInt1, paramInt2);
  }
  
  public void createTable(int paramInt1, int paramInt2)
  {
    _createTable(this.h, paramInt1, paramInt2);
  }
  
  public byte[] dump(int paramInt)
  {
    return _dump(this.h, paramInt);
  }
  
  public int equal(int paramInt1, int paramInt2)
  {
    return _equal(this.h, paramInt1, paramInt2);
  }
  
  public int error()
  {
    return _error(this.h);
  }
  
  public int gc(int paramInt1, int paramInt2)
  {
    return _gc(this.h, paramInt1, paramInt2);
  }
  
  public LuaContext getContext()
  {
    return this.i;
  }
  
  public int getField(int paramInt, String paramString)
  {
    return _getField(this.h, paramInt, paramString);
  }
  
  public LuaFunction getFunction(int paramInt)
  {
    LuaObject localLuaObject = getLuaObject(paramInt);
    if (localLuaObject.isFunction()) {
      return localLuaObject.getFunction();
    }
    return null;
  }
  
  public LuaFunction getFunction(String paramString)
  {
    paramString = getLuaObject(paramString);
    if (paramString.isFunction()) {
      return paramString.getFunction();
    }
    return null;
  }
  
  public int getGlobal(String paramString)
  {
    try
    {
      int j = _getGlobal(this.h, paramString);
      return j;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public int getI(int paramInt, long paramLong)
  {
    return _getI(this.h, paramInt, paramLong);
  }
  
  public LuaObject getLuaObject(int paramInt)
  {
    if (isFunction(paramInt)) {
      return new LuaFunction(this, paramInt);
    }
    if (isTable(paramInt)) {
      return new LuaTable(this, paramInt);
    }
    return new LuaObject(this, paramInt);
  }
  
  public LuaObject getLuaObject(LuaObject paramLuaObject1, LuaObject paramLuaObject2)
  {
    if ((paramLuaObject1.getLuaState().getPointer() == this.h) && (paramLuaObject1.getLuaState().getPointer() == paramLuaObject2.getLuaState().getPointer())) {
      return new LuaObject(paramLuaObject1, paramLuaObject2);
    }
    throw new LuaException("Object must have the same LuaState as the parent!");
  }
  
  public LuaObject getLuaObject(LuaObject paramLuaObject, Number paramNumber)
  {
    return new LuaObject(paramLuaObject, paramNumber);
  }
  
  public LuaObject getLuaObject(LuaObject paramLuaObject, String paramString)
  {
    return new LuaObject(paramLuaObject, paramString);
  }
  
  public LuaObject getLuaObject(String paramString)
  {
    pushGlobalTable();
    pushString(paramString);
    rawGet(-2);
    paramString = getLuaObject(-1);
    pop(2);
    return paramString;
  }
  
  public int getMetaTable(int paramInt)
  {
    return _getMetaTable(this.h, paramInt);
  }
  
  public Object getObjectFromUserdata(int paramInt)
  {
    return _getObjectFromUserdata(this.h, paramInt);
  }
  
  public long getPointer()
  {
    return this.h;
  }
  
  public int getTable(int paramInt)
  {
    return _getTable(this.h, paramInt);
  }
  
  public int getTop()
  {
    return _getTop(this.h);
  }
  
  public String getUpValue(int paramInt1, int paramInt2)
  {
    return _getUpValue(this.h, paramInt1, paramInt2);
  }
  
  public int getUserValue(int paramInt)
  {
    return _getUserValue(this.h, paramInt);
  }
  
  public void insert(int paramInt)
  {
    _insert(this.h, paramInt);
  }
  
  public boolean isBoolean(int paramInt)
  {
    return _isBoolean(this.h, paramInt) != 0;
  }
  
  public boolean isCFunction(int paramInt)
  {
    return _isCFunction(this.h, paramInt) != 0;
  }
  
  public boolean isClosed()
  {
    try
    {
      long l = this.h;
      boolean bool;
      if (l == 0L) {
        bool = true;
      } else {
        bool = false;
      }
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean isFunction(int paramInt)
  {
    return _isFunction(this.h, paramInt) != 0;
  }
  
  public boolean isInteger(int paramInt)
  {
    return _isInteger(this.h, paramInt) != 0;
  }
  
  public boolean isJavaFunction(int paramInt)
  {
    return _isJavaFunction(this.h, paramInt);
  }
  
  public boolean isNil(int paramInt)
  {
    return _isNil(this.h, paramInt) != 0;
  }
  
  public boolean isNone(int paramInt)
  {
    return _isNone(this.h, paramInt) != 0;
  }
  
  public boolean isNoneOrNil(int paramInt)
  {
    return _isNoneOrNil(this.h, paramInt) != 0;
  }
  
  public boolean isNumber(int paramInt)
  {
    return _isNumber(this.h, paramInt) != 0;
  }
  
  public boolean isObject(int paramInt)
  {
    return _isObject(this.h, paramInt);
  }
  
  public boolean isString(int paramInt)
  {
    return _isString(this.h, paramInt) != 0;
  }
  
  public boolean isTable(int paramInt)
  {
    return _isTable(this.h, paramInt) != 0;
  }
  
  public boolean isThread(int paramInt)
  {
    return _isThread(this.h, paramInt) != 0;
  }
  
  public boolean isUserdata(int paramInt)
  {
    return _isUserdata(this.h, paramInt) != 0;
  }
  
  public int isYieldable()
  {
    return _isYieldable(this.h);
  }
  
  public int lessThan(int paramInt1, int paramInt2)
  {
    return _lessThan(this.h, paramInt1, paramInt2);
  }
  
  public void newTable()
  {
    _newTable(this.h);
  }
  
  public LuaState newThread()
  {
    LuaState localLuaState = new LuaState(_newthread(this.h));
    LuaStateFactory.insertLuaState(localLuaState);
    return localLuaState;
  }
  
  public int next(int paramInt)
  {
    return _next(this.h, paramInt);
  }
  
  public int objLen(int paramInt)
  {
    return _objlen(this.h, paramInt);
  }
  
  public void openBase()
  {
    _openBase(this.h);
  }
  
  public void openDebug()
  {
    _openDebug(this.h);
  }
  
  public void openIo()
  {
    _openIo(this.h);
  }
  
  public void openLibs()
  {
    _openLibs(this.h);
    _openLuajava(this.h);
    pushPrimitive();
  }
  
  public void openLuajava()
  {
    _openLuajava(this.h);
    pushPrimitive();
  }
  
  public void openMath()
  {
    _openMath(this.h);
  }
  
  public void openOs()
  {
    _openOs(this.h);
  }
  
  public void openPackage()
  {
    _openPackage(this.h);
  }
  
  public void openString()
  {
    _openString(this.h);
  }
  
  public void openTable()
  {
    _openTable(this.h);
  }
  
  public int pcall(int paramInt1, int paramInt2, int paramInt3)
  {
    return _pcall(this.h, paramInt1, paramInt2, paramInt3);
  }
  
  public void pop(int paramInt)
  {
    _pop(this.h, paramInt);
  }
  
  public void pushBoolean(boolean paramBoolean)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public void pushContext(LuaContext paramLuaContext)
  {
    this.i = paramLuaContext;
    pushString("_LuaContext");
    pushJavaObject(paramLuaContext);
    setTable(-1001000);
  }
  
  public void pushGlobalTable()
  {
    try
    {
      _pushGlobalTable(this.h);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void pushInteger(long paramLong)
  {
    _pushInteger(this.h, paramLong);
  }
  
  public void pushJavaFunction(JavaFunction paramJavaFunction)
  {
    _pushJavaFunction(this.h, paramJavaFunction);
  }
  
  public void pushJavaObject(Object paramObject)
  {
    _pushJavaObject(this.h, paramObject);
  }
  
  public void pushNil()
  {
    _pushNil(this.h);
  }
  
  public void pushNumber(double paramDouble)
  {
    _pushNumber(this.h, paramDouble);
  }
  
  public void pushObjectValue(Object paramObject)
  {
    if (paramObject == null)
    {
      pushNil();
      return;
    }
    if ((paramObject instanceof Boolean))
    {
      pushBoolean(((Boolean)paramObject).booleanValue());
      return;
    }
    long l;
    if ((paramObject instanceof Long))
    {
      l = ((Long)paramObject).longValue();
      pushInteger(l);
      return;
    }
    int j;
    if ((paramObject instanceof Integer)) {
      j = ((Integer)paramObject).intValue();
    }
    for (;;)
    {
      l = j;
      break;
      if ((paramObject instanceof Short))
      {
        j = ((Short)paramObject).shortValue();
      }
      else if ((paramObject instanceof Character))
      {
        j = ((Character)paramObject).charValue();
      }
      else
      {
        if (!(paramObject instanceof Byte)) {
          break label132;
        }
        j = ((Byte)paramObject).byteValue();
      }
    }
    label132:
    if ((paramObject instanceof Float)) {}
    for (double d1 = ((Float)paramObject).floatValue();; d1 = ((Double)paramObject).doubleValue())
    {
      pushNumber(d1);
      return;
      if (!(paramObject instanceof Double)) {
        break;
      }
    }
    if ((paramObject instanceof String))
    {
      pushString((String)paramObject);
      return;
    }
    if ((paramObject instanceof JavaFunction))
    {
      pushJavaFunction((JavaFunction)paramObject);
      return;
    }
    Object localObject = paramObject;
    if ((paramObject instanceof LuaObject))
    {
      paramObject = (LuaObject)paramObject;
      localObject = paramObject;
      if (((LuaObject)paramObject).getLuaState() == this)
      {
        ((LuaObject)paramObject).push();
        return;
      }
    }
    pushJavaObject(localObject);
  }
  
  public void pushPrimitive()
  {
    pushJavaObject(Boolean.TYPE);
    setGlobal("boolean");
    pushJavaObject(Byte.TYPE);
    setGlobal("byte");
    pushJavaObject(Character.TYPE);
    setGlobal("char");
    pushJavaObject(Short.TYPE);
    setGlobal("short");
    pushJavaObject(Integer.TYPE);
    setGlobal("int");
    pushJavaObject(Long.TYPE);
    setGlobal("long");
    pushJavaObject(Float.TYPE);
    setGlobal("float");
    pushJavaObject(Double.TYPE);
    setGlobal("double");
  }
  
  public void pushString(String paramString)
  {
    if (paramString == null)
    {
      _pushNil(this.h);
      return;
    }
    _pushString(this.h, paramString);
  }
  
  public void pushString(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
    {
      _pushNil(this.h);
      return;
    }
    _pushString(this.h, paramArrayOfByte, paramArrayOfByte.length);
  }
  
  public void pushValue(int paramInt)
  {
    _pushValue(this.h, paramInt);
  }
  
  public int rawGet(int paramInt)
  {
    return _rawGet(this.h, paramInt);
  }
  
  public int rawGetI(int paramInt, long paramLong)
  {
    return _rawGetI(this.h, paramInt, paramLong);
  }
  
  public int rawLen(int paramInt)
  {
    return _rawlen(this.h, paramInt);
  }
  
  public void rawSet(int paramInt)
  {
    _rawSet(this.h, paramInt);
  }
  
  public void rawSetI(int paramInt, long paramLong)
  {
    _rawSetI(this.h, paramInt, paramLong);
  }
  
  public int rawequal(int paramInt1, int paramInt2)
  {
    return _rawequal(this.h, paramInt1, paramInt2);
  }
  
  public void remove(int paramInt)
  {
    _remove(this.h, paramInt);
  }
  
  public void replace(int paramInt)
  {
    _replace(this.h, paramInt);
  }
  
  public int resume(LuaState paramLuaState, int paramInt)
  {
    return _resume(this.h, paramLuaState.getPointer(), paramInt);
  }
  
  public void rotate(int paramInt1, int paramInt2)
  {
    _rotate(this.h, paramInt1, paramInt2);
  }
  
  public void setField(int paramInt, String paramString)
  {
    _setField(this.h, paramInt, paramString);
  }
  
  public void setGlobal(String paramString)
  {
    try
    {
      _setGlobal(this.h, paramString);
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void setI(int paramInt, long paramLong)
  {
    _setI(this.h, paramInt, paramLong);
  }
  
  public int setMetaTable(int paramInt)
  {
    return _setMetaTable(this.h, paramInt);
  }
  
  public void setTable(int paramInt)
  {
    _setTable(this.h, paramInt);
  }
  
  public void setTop(int paramInt)
  {
    _setTop(this.h, paramInt);
  }
  
  public String setUpValue(int paramInt1, int paramInt2)
  {
    return _setUpValue(this.h, paramInt1, paramInt2);
  }
  
  public void setUserValue(int paramInt)
  {
    _setUserValue(this.h, paramInt);
  }
  
  public int status()
  {
    return _status(this.h);
  }
  
  public int strLen(int paramInt)
  {
    return _strlen(this.h, paramInt);
  }
  
  public boolean toBoolean(int paramInt)
  {
    return _toBoolean(this.h, paramInt) != 0;
  }
  
  public long toInteger(int paramInt)
  {
    return _toInteger(this.h, paramInt);
  }
  
  public Object toJavaObject(int paramInt)
  {
    try
    {
      boolean bool = isBoolean(paramInt);
      Object localObject1 = null;
      if (bool) {
        localObject1 = Boolean.valueOf(toBoolean(paramInt));
      } else if (type(paramInt) == 4) {
        localObject1 = toString(paramInt);
      } else if (isFunction(paramInt)) {
        localObject1 = getLuaObject(paramInt).getFunction();
      } else if (isTable(paramInt)) {
        localObject1 = getLuaObject(paramInt).getTable();
      } else if (type(paramInt) == 3)
      {
        if (isInteger(paramInt)) {
          localObject1 = Long.valueOf(toInteger(paramInt));
        } else {
          localObject1 = Double.valueOf(toNumber(paramInt));
        }
      }
      else if (isUserdata(paramInt))
      {
        if (isObject(paramInt)) {
          localObject1 = getObjectFromUserdata(paramInt);
        } else {
          localObject1 = getLuaObject(paramInt);
        }
      }
      else {
        isNil(paramInt);
      }
      return localObject1;
    }
    finally {}
  }
  
  public double toNumber(int paramInt)
  {
    return _toNumber(this.h, paramInt);
  }
  
  public String toString(int paramInt)
  {
    return _toString(this.h, paramInt);
  }
  
  public LuaState toThread(int paramInt)
  {
    return new LuaState(_toThread(this.h, paramInt));
  }
  
  public int type(int paramInt)
  {
    return _type(this.h, paramInt);
  }
  
  public String typeName(int paramInt)
  {
    return _typeName(this.h, paramInt);
  }
  
  public void xmove(LuaState paramLuaState, int paramInt)
  {
    _xmove(this.h, paramLuaState.h, paramInt);
  }
  
  public int yield(int paramInt)
  {
    return _yield(this.h, paramInt);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\luajava\LuaState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */