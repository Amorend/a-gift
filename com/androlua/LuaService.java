package com.androlua;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;
import com.luajava.JavaFunction;
import com.luajava.LuaException;
import com.luajava.LuaObject;
import com.luajava.LuaState;
import com.luajava.LuaStateFactory;
import dalvik.system.DexClassLoader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LuaService
  extends Service
  implements LuaBroadcastReceiver.OnReceiveListener, LuaContext
{
  private static LuaService b;
  LuaBinder a = new LuaBinder();
  private LuaDexLoader c;
  private ArrayList<LuaGcable> d = new ArrayList();
  private String e;
  private MainHandler f;
  private String g;
  private LuaState h;
  private String i;
  private String j;
  private String k;
  private String l;
  public String luaCpath;
  public String luaDir;
  private String m;
  private BroadcastReceiver n;
  private StringBuilder o = new StringBuilder();
  private Toast p;
  private StringBuilder q = new StringBuilder();
  private long r;
  private LuaResources s;
  
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
    this.h = LuaStateFactory.newLuaState();
    this.h.openLibs();
    this.h.pushJavaObject(this);
    this.h.setGlobal("service");
    this.h.getGlobal("service");
    this.h.setGlobal("this");
    this.h.pushContext(this);
    this.h.getGlobal("luajava");
    this.h.pushString(this.m);
    this.h.setField(-2, "luaextdir");
    this.h.pushString(this.luaDir);
    this.h.setField(-2, "luadir");
    this.h.pushString(this.i);
    this.h.setField(-2, "luapath");
    this.h.pop(1);
    new LuaAssetLoader(this, this.h);
    this.h.getGlobal("package");
    this.h.pushString(this.e);
    this.h.setField(-2, "path");
    this.h.pushString(this.luaCpath);
    this.h.setField(-2, "cpath");
    this.h.pop(1);
    new JavaFunction(this.h)
    {
      public int execute()
      {
        int i = this.b.getTop();
        int j = 2;
        if (i < 2)
        {
          LuaService.this.sendMsg("");
          return 0;
        }
        for (;;)
        {
          int k = this.b.getTop();
          i = 1;
          if (j > k) {
            break;
          }
          k = this.b.type(j);
          Object localObject1 = null;
          String str = this.b.typeName(k);
          k = str.hashCode();
          if (k != -266011147)
          {
            if ((k == 64711720) && (str.equals("boolean"))) {
              break label114;
            }
          }
          else if (str.equals("userdata"))
          {
            i = 0;
            break label114;
          }
          i = -1;
          switch (i)
          {
          default: 
            localObject1 = this.b.toString(j);
            break;
          case 1: 
            label114:
            if (this.b.toBoolean(j)) {
              localObject1 = "true";
            }
            break;
          }
          for (;;)
          {
            break;
            localObject1 = "false";
            continue;
            Object localObject2 = this.b.toJavaObject(j);
            if (localObject2 != null) {
              localObject1 = localObject2.toString();
            }
          }
          if (localObject1 == null) {
            localObject1 = str;
          }
          LuaService.a(LuaService.this).append("\t");
          LuaService.a(LuaService.this).append((String)localObject1);
          LuaService.a(LuaService.this).append("\t");
          j += 1;
        }
        LuaService.this.sendMsg(LuaService.a(LuaService.this).toString().substring(1, LuaService.a(LuaService.this).length() - 1));
        LuaService.a(LuaService.this).setLength(0);
        return 0;
      }
    }.register("print");
    new JavaFunction(this.h)
    {
      public int execute()
      {
        ((LuaThread)this.b.toJavaObject(2)).set(this.b.toString(3), this.b.toJavaObject(4));
        return 0;
      }
    }.register("set");
    new JavaFunction(this.h)
    {
      public int execute()
      {
        LuaThread localLuaThread = (LuaThread)this.b.toJavaObject(2);
        int j = this.b.getTop();
        if (j > 3)
        {
          Object[] arrayOfObject = new Object[j - 3];
          int i = 4;
          while (i <= j)
          {
            arrayOfObject[(i - 4)] = this.b.toJavaObject(i);
            i += 1;
          }
          localLuaThread.call(this.b.toString(3), arrayOfObject);
        }
        else if (j == 3)
        {
          localLuaThread.call(this.b.toString(3));
        }
        return 0;
      }
    }.register("call");
  }
  
  private void a(String paramString, Object paramObject)
  {
    try
    {
      this.h.pushObjectValue(paramObject);
      this.h.setGlobal(paramString);
      return;
    }
    catch (LuaException paramString)
    {
      sendMsg(paramString.getMessage());
    }
  }
  
  private void a(String paramString1, String paramString2)
  {
    try
    {
      if (new File(paramString1).exists())
      {
        paramString1 = new FileInputStream(paramString1);
        paramString2 = new FileOutputStream(paramString2);
        byte[] arrayOfByte = new byte['က'];
        int i1 = 0;
        for (;;)
        {
          int i2 = paramString1.read(arrayOfByte);
          if (i2 == -1) {
            break;
          }
          i1 += i2;
          System.out.println(i1);
          paramString2.write(arrayOfByte, 0, i2);
        }
        paramString1.close();
        return;
      }
    }
    catch (Exception paramString1)
    {
      System.out.println("复制文件操作出错");
      paramString1.printStackTrace();
    }
  }
  
  private static byte[] a(InputStream paramInputStream)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(4096);
    byte[] arrayOfByte = new byte['က'];
    for (;;)
    {
      int i1 = paramInputStream.read(arrayOfByte);
      if (-1 == i1) {
        break;
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, i1);
    }
    paramInputStream = localByteArrayOutputStream.toByteArray();
    localByteArrayOutputStream.close();
    return paramInputStream;
  }
  
  public static LuaService getService()
  {
    return b;
  }
  
  public void call(String paramString)
  {
    push(2, paramString);
  }
  
  public void call(String paramString, Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject.length == 0)
    {
      push(2, paramString);
      return;
    }
    push(3, paramString, paramArrayOfObject);
  }
  
  public Object doAsset(String paramString, Object... paramVarArgs)
  {
    for (;;)
    {
      try
      {
        byte[] arrayOfByte = readAsset(paramString);
        this.h.setTop(0);
        i2 = this.h.LloadBuffer(arrayOfByte, paramString);
        i1 = i2;
        if (i2 == 0)
        {
          this.h.getGlobal("debug");
          this.h.getField(-1, "traceback");
          this.h.remove(-2);
          this.h.insert(-2);
          if (paramVarArgs == null) {
            break label206;
          }
          i1 = paramVarArgs.length;
          break label208;
          if (i2 < i1)
          {
            this.h.pushObjectValue(paramVarArgs[i2]);
            i2 += 1;
            continue;
          }
          i2 = this.h.pcall(i1, 0, -2 - i1);
          i1 = i2;
          if (i2 == 0) {
            return this.h.toJavaObject(-1);
          }
        }
        paramString = new StringBuilder();
        paramString.append(a(i1));
        paramString.append(": ");
        paramString.append(this.h.toString(-1));
        throw new LuaException(paramString.toString());
      }
      catch (Exception paramString)
      {
        sendMsg(paramString.getMessage());
        return null;
      }
      label206:
      int i1 = 0;
      label208:
      int i2 = 0;
    }
  }
  
  public Object doFile(String paramString)
  {
    return doFile(paramString, new Object[0]);
  }
  
  public Object doFile(String paramString, Object[] paramArrayOfObject)
  {
    int i2 = 0;
    Object localObject = paramString;
    for (;;)
    {
      try
      {
        if (paramString.charAt(0) != '/')
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append(this.luaDir);
          ((StringBuilder)localObject).append("/");
          ((StringBuilder)localObject).append(paramString);
          localObject = ((StringBuilder)localObject).toString();
        }
        this.h.setTop(0);
        i3 = this.h.LloadFile((String)localObject);
        i1 = i3;
        if (i3 == 0)
        {
          this.h.getGlobal("debug");
          this.h.getField(-1, "traceback");
          this.h.remove(-2);
          this.h.insert(-2);
          if (paramArrayOfObject == null) {
            break label263;
          }
          i3 = paramArrayOfObject.length;
          i1 = i2;
          i2 = i3;
          if (i1 < i2)
          {
            this.h.pushObjectValue(paramArrayOfObject[i1]);
            i1 += 1;
            continue;
          }
          i2 = this.h.pcall(i2, 1, -2 - i2);
          i1 = i2;
          if (i2 == 0) {
            return this.h.toJavaObject(-1);
          }
        }
        paramString = new StringBuilder();
        paramString.append(a(i1));
        paramString.append(": ");
        paramString.append(this.h.toString(-1));
        throw new LuaException(paramString.toString());
      }
      catch (LuaException paramString)
      {
        sendMsg(paramString.getMessage());
        return null;
      }
      label263:
      int i3 = 0;
      int i1 = i2;
      i2 = i3;
    }
  }
  
  public Object doString(String paramString, Object... paramVarArgs)
  {
    for (;;)
    {
      try
      {
        LuaState localLuaState = this.h;
        i2 = 0;
        localLuaState.setTop(0);
        i3 = this.h.LloadString(paramString);
        i1 = i3;
        if (i3 == 0)
        {
          this.h.getGlobal("debug");
          this.h.getField(-1, "traceback");
          this.h.remove(-2);
          this.h.insert(-2);
          if (paramVarArgs == null) {
            break label211;
          }
          i3 = paramVarArgs.length;
          i1 = i2;
          i2 = i3;
          if (i1 < i2)
          {
            this.h.pushObjectValue(paramVarArgs[i1]);
            i1 += 1;
            continue;
          }
          i2 = this.h.pcall(i2, 1, -2 - i2);
          i1 = i2;
          if (i2 == 0) {
            return this.h.toJavaObject(-1);
          }
        }
        paramString = new StringBuilder();
        paramString.append(a(i1));
        paramString.append(": ");
        paramString.append(this.h.toString(-1));
        throw new LuaException(paramString.toString());
      }
      catch (LuaException paramString)
      {
        sendMsg(paramString.getMessage());
        return null;
      }
      label211:
      int i3 = 0;
      int i1 = i2;
      int i2 = i3;
    }
  }
  
  public Object get(String paramString)
  {
    this.h.getGlobal(paramString);
    return this.h.toJavaObject(-1);
  }
  
  public AssetManager getAssets()
  {
    if ((this.c != null) && (this.c.getAssets() != null)) {
      return this.c.getAssets();
    }
    return super.getAssets();
  }
  
  public LuaBinder getBinder()
  {
    return this.a;
  }
  
  public ArrayList<ClassLoader> getClassLoaders()
  {
    return this.c.getClassLoaders();
  }
  
  public Context getContext()
  {
    return this;
  }
  
  public Map getGlobalData()
  {
    return LuaApplication.getInstance().getGlobalData();
  }
  
  public int getHeight()
  {
    return getResources().getDisplayMetrics().heightPixels;
  }
  
  public HashMap<String, String> getLibrarys()
  {
    return this.c.getLibrarys();
  }
  
  public String getLuaCpath()
  {
    return this.luaCpath;
  }
  
  public String getLuaDir()
  {
    return this.luaDir;
  }
  
  public String getLuaDir(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.luaDir);
    localStringBuilder.append("/");
    localStringBuilder.append(paramString);
    paramString = new File(localStringBuilder.toString());
    if ((!paramString.exists()) && (!paramString.mkdirs())) {
      return null;
    }
    return paramString.getAbsolutePath();
  }
  
  public String getLuaExtDir()
  {
    return this.m;
  }
  
  public String getLuaExtDir(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.m);
    localStringBuilder.append("/");
    localStringBuilder.append(paramString);
    paramString = new File(localStringBuilder.toString());
    if ((!paramString.exists()) && (!paramString.mkdirs())) {
      return null;
    }
    return paramString.getAbsolutePath();
  }
  
  public String getLuaExtPath(String paramString)
  {
    return new File(getLuaExtDir(), paramString).getAbsolutePath();
  }
  
  public String getLuaExtPath(String paramString1, String paramString2)
  {
    return new File(getLuaExtDir(paramString1), paramString2).getAbsolutePath();
  }
  
  public String getLuaLpath()
  {
    return this.e;
  }
  
  public String getLuaPath()
  {
    return this.i;
  }
  
  public String getLuaPath(String paramString)
  {
    return new File(getLuaDir(), paramString).getAbsolutePath();
  }
  
  public String getLuaPath(String paramString1, String paramString2)
  {
    return new File(getLuaDir(paramString1), paramString2).getAbsolutePath();
  }
  
  public LuaState getLuaState()
  {
    return this.h;
  }
  
  public Resources getResources()
  {
    if ((this.c != null) && (this.c.getResources() != null)) {
      return this.c.getResources();
    }
    if (this.s != null) {
      return this.s;
    }
    return super.getResources();
  }
  
  public Object getSharedData(String paramString)
  {
    return LuaApplication.getInstance().getSharedData(paramString);
  }
  
  public Object getSharedData(String paramString, Object paramObject)
  {
    return LuaApplication.getInstance().getSharedData(paramString, paramObject);
  }
  
  public int getWidth()
  {
    return getResources().getDisplayMetrics().widthPixels;
  }
  
  public DexClassLoader loadDex(String paramString)
  {
    return this.c.loadDex(paramString);
  }
  
  public Object loadLib(String paramString)
  {
    int i1 = paramString.indexOf(".");
    Object localObject1;
    if (i1 > 0) {
      localObject1 = paramString.substring(0, i1);
    } else {
      localObject1 = paramString;
    }
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(this.l);
    ((StringBuilder)localObject2).append("/lib");
    ((StringBuilder)localObject2).append((String)localObject1);
    ((StringBuilder)localObject2).append(".so");
    if (!new File(((StringBuilder)localObject2).toString()).exists())
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(this.luaDir);
      ((StringBuilder)localObject2).append("/lib");
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append(".so");
      if (!new File(((StringBuilder)localObject2).toString()).exists())
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("can not find lib ");
        ((StringBuilder)localObject1).append(paramString);
        throw new LuaException(((StringBuilder)localObject1).toString());
      }
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(this.luaDir);
      ((StringBuilder)localObject2).append("/lib");
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append(".so");
      localObject2 = ((StringBuilder)localObject2).toString();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(this.l);
      localStringBuilder.append("/lib");
      localStringBuilder.append((String)localObject1);
      localStringBuilder.append(".so");
      a((String)localObject2, localStringBuilder.toString());
    }
    return this.h.getLuaObject("require").call(new Object[] { paramString });
  }
  
  public void loadResources(String paramString)
  {
    this.c.loadResources(paramString);
  }
  
  public LuaAsyncTask newTask(LuaObject paramLuaObject)
  {
    return newTask(paramLuaObject, null, null);
  }
  
  public LuaAsyncTask newTask(LuaObject paramLuaObject1, LuaObject paramLuaObject2)
  {
    return newTask(paramLuaObject1, null, paramLuaObject2);
  }
  
  public LuaAsyncTask newTask(LuaObject paramLuaObject1, LuaObject paramLuaObject2, LuaObject paramLuaObject3)
  {
    return new LuaAsyncTask(this, paramLuaObject1, paramLuaObject2, paramLuaObject3);
  }
  
  public LuaThread newThread(LuaObject paramLuaObject)
  {
    return newThread(paramLuaObject, null);
  }
  
  public LuaThread newThread(LuaObject paramLuaObject, Object[] paramArrayOfObject)
  {
    return new LuaThread(this, paramLuaObject, true, paramArrayOfObject);
  }
  
  public LuaTimer newTimer(LuaObject paramLuaObject)
  {
    return newTimer(paramLuaObject, null);
  }
  
  public LuaTimer newTimer(LuaObject paramLuaObject, Object[] paramArrayOfObject)
  {
    return new LuaTimer(this, paramLuaObject, paramArrayOfObject);
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    startForeground(1, new Notification());
    return new LuaBinder();
  }
  
  public void onCreate()
  {
    super.onCreate();
    Object localObject = super.getResources();
    this.s = new LuaResources(getAssets(), ((Resources)localObject).getDisplayMetrics(), ((Resources)localObject).getConfiguration());
    b = this;
    localObject = (LuaApplication)getApplication();
    this.j = ((LuaApplication)localObject).getLocalDir();
    this.k = ((LuaApplication)localObject).getOdexDir();
    this.l = ((LuaApplication)localObject).getLibDir();
    this.g = ((LuaApplication)localObject).getMdDir();
    this.luaCpath = ((LuaApplication)localObject).getLuaCpath();
    this.luaDir = this.j;
    this.e = ((LuaApplication)localObject).getLuaLpath();
    this.m = ((LuaApplication)localObject).getLuaExtDir();
    this.f = new MainHandler();
  }
  
  public void onDestroy()
  {
    runFunc("onDestroy", new Object[0]);
    if (this.n != null) {
      unregisterReceiver(this.n);
    }
    super.onDestroy();
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    runFunc("onReceive", new Object[] { paramContext, paramIntent });
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    b = this;
    if (this.h == null)
    {
      startForeground(1, new Notification());
      this.i = paramIntent.getStringExtra("luaPath");
      this.luaDir = paramIntent.getStringExtra("luaDir");
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(this.luaDir);
      ((StringBuilder)localObject).append("/?.lua;");
      ((StringBuilder)localObject).append(this.luaDir);
      ((StringBuilder)localObject).append("/lua/?.lua;");
      ((StringBuilder)localObject).append(this.luaDir);
      ((StringBuilder)localObject).append("/?/init.lua;");
      ((StringBuilder)localObject).append(this.e);
      this.e = ((StringBuilder)localObject).toString();
      localObject = paramIntent.getData();
      try
      {
        a();
        this.c = new LuaDexLoader(this);
        this.c.loadLibs();
        if (localObject != null) {}
        for (localObject = ((Uri)localObject).getPath();; localObject = "service.lua")
        {
          doFile((String)localObject);
          break;
        }
      }
      catch (Exception localException)
      {
        sendMsg(localException.getMessage());
      }
    }
    tmp208_205[0] = paramIntent;
    Object[] tmp212_208 = tmp208_205;
    tmp212_208[1] = Integer.valueOf(paramInt1);
    Object[] tmp219_212 = tmp212_208;
    tmp219_212[2] = Integer.valueOf(paramInt2);
    runFunc("onStartCommand", tmp219_212);
    runFunc("onStart", (Object[])paramIntent.getSerializableExtra("arg"));
    return super.onStartCommand(paramIntent, paramInt1, paramInt2);
  }
  
  public boolean onUnbind(Intent paramIntent)
  {
    return super.onUnbind(paramIntent);
  }
  
  public void push(int paramInt, String paramString)
  {
    Message localMessage = new Message();
    Bundle localBundle = new Bundle();
    localBundle.putString("data", paramString);
    localMessage.setData(localBundle);
    localMessage.what = paramInt;
    this.f.sendMessage(localMessage);
  }
  
  public void push(int paramInt, String paramString, Object[] paramArrayOfObject)
  {
    Message localMessage = new Message();
    Bundle localBundle = new Bundle();
    localBundle.putString("data", paramString);
    localBundle.putSerializable("args", paramArrayOfObject);
    localMessage.setData(localBundle);
    localMessage.what = paramInt;
    this.f.sendMessage(localMessage);
  }
  
  public byte[] readAsset(String paramString)
  {
    paramString = getAssets().open(paramString);
    byte[] arrayOfByte = a(paramString);
    paramString.close();
    return arrayOfByte;
  }
  
  public void regGc(LuaGcable paramLuaGcable)
  {
    this.d.add(paramLuaGcable);
  }
  
  public Intent registerReceiver(IntentFilter paramIntentFilter)
  {
    if (this.n != null) {
      unregisterReceiver(this.n);
    }
    this.n = new LuaBroadcastReceiver(this);
    return super.registerReceiver(this.n, paramIntentFilter);
  }
  
  public Intent registerReceiver(LuaBroadcastReceiver.OnReceiveListener paramOnReceiveListener, IntentFilter paramIntentFilter)
  {
    return super.registerReceiver(new LuaBroadcastReceiver(paramOnReceiveListener), paramIntentFilter);
  }
  
  public Intent registerReceiver(LuaBroadcastReceiver paramLuaBroadcastReceiver, IntentFilter paramIntentFilter)
  {
    return super.registerReceiver(paramLuaBroadcastReceiver, paramIntentFilter);
  }
  
  public Object runFunc(String paramString, Object... paramVarArgs)
  {
    if (this.h != null) {}
    for (;;)
    {
      try
      {
        localObject = this.h;
        int i2 = 0;
        ((LuaState)localObject).setTop(0);
        this.h.getGlobal(paramString);
        if (this.h.isFunction(-1))
        {
          this.h.getGlobal("debug");
          this.h.getField(-1, "traceback");
          this.h.remove(-2);
          this.h.insert(-2);
          if (paramVarArgs == null) {
            break label244;
          }
          i1 = paramVarArgs.length;
          if (i2 < i1)
          {
            this.h.pushObjectValue(paramVarArgs[i2]);
            i2 += 1;
            continue;
          }
          i1 = this.h.pcall(i1, 1, -2 - i1);
          if (i1 == 0) {
            return this.h.toJavaObject(-1);
          }
          paramVarArgs = new StringBuilder();
          paramVarArgs.append(a(i1));
          paramVarArgs.append(": ");
          paramVarArgs.append(this.h.toString(-1));
          throw new LuaException(paramVarArgs.toString());
        }
      }
      catch (LuaException paramVarArgs)
      {
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append(paramString);
        ((StringBuilder)localObject).append(" ");
        ((StringBuilder)localObject).append(paramVarArgs.getMessage());
        sendMsg(((StringBuilder)localObject).toString());
      }
      return null;
      label244:
      int i1 = 0;
    }
  }
  
  public void sendError(String paramString, Exception paramException)
  {
    runFunc("onError", new Object[] { paramString, paramException });
  }
  
  public void sendMsg(String paramString)
  {
    Message localMessage = new Message();
    Bundle localBundle = new Bundle();
    localBundle.putString("data", paramString);
    localMessage.setData(localBundle);
    localMessage.what = 0;
    this.f.sendMessage(localMessage);
    Log.i("lua", paramString);
  }
  
  public void set(String paramString, Object paramObject)
  {
    push(1, paramString, new Object[] { paramObject });
  }
  
  public void setBinder(LuaBinder paramLuaBinder)
  {
    this.a = paramLuaBinder;
  }
  
  public void setLuaExtDir(String paramString)
  {
    if (Environment.getExternalStorageState().equals("mounted")) {}
    for (paramString = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), paramString).getAbsolutePath();; paramString = getDir(paramString, 0).getAbsolutePath())
    {
      this.m = paramString;
      break;
      File[] arrayOfFile = new File("/storage").listFiles();
      int i2 = arrayOfFile.length;
      int i1 = 0;
      while (i1 < i2)
      {
        File localFile = arrayOfFile[i1];
        String[] arrayOfString = localFile.list();
        if ((arrayOfString != null) && (arrayOfString.length > 5)) {
          this.m = new File(localFile, paramString).getAbsolutePath();
        }
        i1 += 1;
      }
      if (this.m != null) {
        break;
      }
    }
    paramString = new File(this.m);
    if (!paramString.exists()) {
      paramString.mkdirs();
    }
  }
  
  public boolean setSharedData(String paramString, Object paramObject)
  {
    return LuaApplication.getInstance().setSharedData(paramString, paramObject);
  }
  
  @SuppressLint({"ShowToast"})
  public void showToast(String paramString)
  {
    long l1 = System.currentTimeMillis();
    if ((this.p != null) && (l1 - this.r <= 1000L))
    {
      this.q.append("\n");
      this.q.append(paramString);
      this.p.setText(this.q.toString());
      this.p.setDuration(1);
    }
    else
    {
      this.q.setLength(0);
      this.p = Toast.makeText(this, paramString, 1);
      this.q.append(paramString);
    }
    this.r = l1;
    this.p.show();
  }
  
  public LuaAsyncTask task(long paramLong, LuaObject paramLuaObject)
  {
    return task(paramLong, null, null);
  }
  
  public LuaAsyncTask task(long paramLong, Object[] paramArrayOfObject, LuaObject paramLuaObject)
  {
    paramLuaObject = new LuaAsyncTask(this, paramLong, paramLuaObject);
    paramLuaObject.execute(paramArrayOfObject);
    return paramLuaObject;
  }
  
  public LuaAsyncTask task(LuaObject paramLuaObject)
  {
    return task(paramLuaObject, null, null, null);
  }
  
  public LuaAsyncTask task(LuaObject paramLuaObject1, LuaObject paramLuaObject2, LuaObject paramLuaObject3)
  {
    return task(paramLuaObject1, null, paramLuaObject2, paramLuaObject3);
  }
  
  public LuaAsyncTask task(LuaObject paramLuaObject, Object[] paramArrayOfObject)
  {
    return task(paramLuaObject, paramArrayOfObject, null, null);
  }
  
  public LuaAsyncTask task(LuaObject paramLuaObject1, Object[] paramArrayOfObject, LuaObject paramLuaObject2)
  {
    return task(paramLuaObject1, null, null, paramLuaObject2);
  }
  
  public LuaAsyncTask task(LuaObject paramLuaObject1, Object[] paramArrayOfObject, LuaObject paramLuaObject2, LuaObject paramLuaObject3)
  {
    paramLuaObject1 = new LuaAsyncTask(this, paramLuaObject1, paramLuaObject2, paramLuaObject3);
    paramLuaObject1.execute(paramArrayOfObject);
    return paramLuaObject1;
  }
  
  public LuaThread thread(LuaObject paramLuaObject)
  {
    paramLuaObject = newThread(paramLuaObject, null);
    paramLuaObject.start();
    return paramLuaObject;
  }
  
  public LuaThread thread(LuaObject paramLuaObject, Object[] paramArrayOfObject)
  {
    paramLuaObject = new LuaThread(this, paramLuaObject, true, paramArrayOfObject);
    paramLuaObject.start();
    return paramLuaObject;
  }
  
  public Ticker ticker(final LuaObject paramLuaObject, long paramLong)
  {
    Ticker localTicker = new Ticker();
    localTicker.setOnTickListener(new Ticker.OnTickListener()
    {
      public void onTick()
      {
        try
        {
          paramLuaObject.call(new Object[0]);
          return;
        }
        catch (LuaException localLuaException)
        {
          localLuaException.printStackTrace();
          LuaService.this.sendError("onTick", localLuaException);
        }
      }
    });
    localTicker.setPeriod(paramLong);
    localTicker.start();
    return localTicker;
  }
  
  public LuaTimer timer(LuaObject paramLuaObject, long paramLong)
  {
    return timer(paramLuaObject, 0L, paramLong, null);
  }
  
  public LuaTimer timer(LuaObject paramLuaObject, long paramLong1, long paramLong2)
  {
    return timer(paramLuaObject, paramLong1, paramLong2, null);
  }
  
  public LuaTimer timer(LuaObject paramLuaObject, long paramLong1, long paramLong2, Object[] paramArrayOfObject)
  {
    paramLuaObject = new LuaTimer(this, paramLuaObject, paramArrayOfObject);
    paramLuaObject.start(paramLong1, paramLong2);
    return paramLuaObject;
  }
  
  public LuaTimer timer(LuaObject paramLuaObject, long paramLong, Object[] paramArrayOfObject)
  {
    return timer(paramLuaObject, 0L, paramLong, paramArrayOfObject);
  }
  
  public class LuaBinder
    extends Binder
  {
    public LuaBinder() {}
    
    public LuaService getService()
    {
      return LuaService.this;
    }
  }
  
  public class MainHandler
    extends Handler
  {
    public MainHandler() {}
    
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (paramMessage.what)
      {
      default: 
        return;
      case 3: 
        String str = paramMessage.getData().getString("data");
        paramMessage = paramMessage.getData().getSerializable("args");
        LuaService.this.runFunc(str, (Object[])paramMessage);
        return;
      case 2: 
        paramMessage = paramMessage.getData().getString("data");
        LuaService.this.runFunc(paramMessage, new Object[0]);
        return;
      case 1: 
        paramMessage = paramMessage.getData();
        LuaService.a(LuaService.this, paramMessage.getString("data"), ((Object[])paramMessage.getSerializable("args"))[0]);
        return;
      }
      paramMessage = paramMessage.getData().getString("data");
      LuaService.this.showToast(paramMessage);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */