package com.androlua;

import android.R.style;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager.TaskDescription;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.FileProvider;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ShortcutInfo.Builder;
import android.content.pm.ShortcutManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcelable;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.ArrayListAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.mobstat.StatService;
import com.luajava.JavaFunction;
import com.luajava.LuaException;
import com.luajava.LuaObject;
import com.luajava.LuaState;
import com.luajava.LuaStateFactory;
import dalvik.system.DexClassLoader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class LuaActivity
  extends Activity
  implements LuaBroadcastReceiver.OnReceiveListener, LuaContext
{
  private static String F;
  private static final HashMap<String, LuaActivity> G = new HashMap();
  private static ArrayList<String> a = new ArrayList();
  private boolean A;
  private boolean B = true;
  private LuaResources C;
  private ArrayList<LuaGcable> D = new ArrayList();
  private String E = "main";
  private LuaObject H;
  private LuaDexLoader b;
  private int c;
  private int d;
  private ListView e;
  private ArrayListAdapter<String> f;
  private LuaState g;
  private String h;
  public Handler handler;
  private StringBuilder i = new StringBuilder();
  private Boolean j = Boolean.valueOf(false);
  private Toast k;
  private LinearLayout l;
  public String luaCpath;
  public String luaDir;
  private boolean m;
  private long n;
  private Menu o;
  private LuaObject p;
  private LuaObject q;
  private LuaObject r;
  private LuaObject s;
  public TextView status;
  private String t;
  private String u;
  private String v;
  private String w;
  private LuaBroadcastReceiver x;
  private String y;
  private String z;
  
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
  
  private String a(File paramFile)
  {
    int i1 = paramFile.getName().lastIndexOf('.');
    if (i1 >= 0)
    {
      paramFile = paramFile.getName().substring(i1 + 1);
      paramFile = MimeTypeMap.getSingleton().getMimeTypeFromExtension(paramFile);
      if (paramFile != null) {
        return paramFile;
      }
    }
    return "application/octet-stream";
  }
  
  private void a()
  {
    this.g = LuaStateFactory.newLuaState();
    this.g.openLibs();
    this.g.pushJavaObject(this);
    this.g.setGlobal("activity");
    this.g.getGlobal("activity");
    this.g.setGlobal("this");
    this.g.pushContext(this);
    this.g.getGlobal("luajava");
    this.g.pushString(this.w);
    this.g.setField(-2, "luaextdir");
    this.g.pushString(this.luaDir);
    this.g.setField(-2, "luadir");
    this.g.pushString(this.h);
    this.g.setField(-2, "luapath");
    this.g.pop(1);
    b();
    new LuaPrint(this, this.g).register("print");
    this.g.getGlobal("package");
    this.g.pushString(this.y);
    this.g.setField(-2, "path");
    this.g.pushString(this.luaCpath);
    this.g.setField(-2, "cpath");
    this.g.pop(1);
    new JavaFunction(this.g)
    {
      public int execute()
      {
        ((LuaThread)this.b.toJavaObject(2)).set(this.b.toString(3), this.b.toJavaObject(4));
        return 0;
      }
    }.register("set");
    new JavaFunction(this.g)
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
      synchronized (this.g)
      {
        this.g.pushObjectValue(paramObject);
        this.g.setGlobal(paramString);
      }
    }
    catch (LuaException paramString)
    {
      sendError("setField", paramString);
      return;
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
  
  private void b()
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(this.luaDir);
    ((StringBuilder)localObject1).append("/init.lua");
    if (!new File(((StringBuilder)localObject1).toString()).exists()) {
      return;
    }
    label422:
    for (;;)
    {
      try
      {
        localObject1 = this.g;
        Object localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(this.luaDir);
        ((StringBuilder)localObject2).append("/init.lua");
        int i2 = ((LuaState)localObject1).LloadFile(((StringBuilder)localObject2).toString());
        int i1 = i2;
        if (i2 == 0)
        {
          this.g.newTable();
          localObject1 = this.g.getLuaObject(-1);
          this.g.setUpValue(-2, 1);
          i1 = this.g.pcall(0, 0, 0);
          if (i1 != 0) {
            break label422;
          }
          if (F == null)
          {
            localObject2 = ((LuaObject)localObject1).getField("app_key");
            if (((LuaObject)localObject2).isString())
            {
              F = ((LuaObject)localObject2).toString();
              StatService.setAppKey(((LuaObject)localObject2).toString());
            }
            localObject2 = ((LuaObject)localObject1).getField("app_channel");
            if (((LuaObject)localObject2).isString()) {
              StatService.setAppChannel(this, ((LuaObject)localObject2).toString(), true);
            }
            StatService.setOn(this, 1);
          }
          localObject2 = ((LuaObject)localObject1).getField("appname");
          if (((LuaObject)localObject2).isString()) {
            setTitle(((LuaObject)localObject2).getString());
          }
          localObject2 = ((LuaObject)localObject1).getField("app_name");
          if (((LuaObject)localObject2).isString()) {
            setTitle(((LuaObject)localObject2).getString());
          }
          localObject2 = ((LuaObject)localObject1).getField("debugmode");
          if (((LuaObject)localObject2).isBoolean()) {
            this.B = ((LuaObject)localObject2).getBoolean();
          }
          localObject2 = ((LuaObject)localObject1).getField("debug_mode");
          if (((LuaObject)localObject2).isBoolean()) {
            this.B = ((LuaObject)localObject2).getBoolean();
          }
          localObject1 = ((LuaObject)localObject1).getField("theme");
          if (((LuaObject)localObject1).isNumber())
          {
            setTheme((int)((LuaObject)localObject1).getInteger());
            return;
          }
          if (((LuaObject)localObject1).isString()) {
            setTheme(R.style.class.getField(((LuaObject)localObject1).getString()).getInt(null));
          }
        }
        else
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append(a(i1));
          ((StringBuilder)localObject1).append(": ");
          ((StringBuilder)localObject1).append(this.g.toString(-1));
          throw new LuaException(((StringBuilder)localObject1).toString());
        }
      }
      catch (Exception localException)
      {
        sendMsg(localException.getMessage());
        return;
      }
      return;
    }
  }
  
  public static LuaActivity getActivity(String paramString)
  {
    return (LuaActivity)G.get(paramString);
  }
  
  public void assetsToSD(String paramString1, String paramString2)
  {
    paramString2 = new FileOutputStream(paramString2);
    paramString1 = getAssets().open(paramString1);
    byte[] arrayOfByte = new byte['က'];
    for (;;)
    {
      int i1 = paramString1.read(arrayOfByte);
      if (i1 <= 0) {
        break;
      }
      paramString2.write(arrayOfByte, 0, i1);
    }
    paramString2.flush();
    paramString1.close();
    paramString2.close();
  }
  
  public boolean bindService(int paramInt)
  {
    bindService(new ServiceConnection()
    {
      public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
      {
        LuaActivity.this.runFunc("onServiceConnected", new Object[] { paramAnonymousComponentName, ((LuaService.LuaBinder)paramAnonymousIBinder).getService() });
      }
      
      public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
      {
        LuaActivity.this.runFunc("onServiceDisconnected", new Object[] { paramAnonymousComponentName });
      }
    }, paramInt);
  }
  
  public boolean bindService(ServiceConnection paramServiceConnection, int paramInt)
  {
    Intent localIntent = new Intent(this, LuaService.class);
    localIntent.putExtra("luaDir", this.luaDir);
    localIntent.putExtra("luaPath", this.h);
    return super.bindService(localIntent, paramServiceConnection, paramInt);
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
  
  public void createShortcut(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.DEFAULT");
    localIntent.setClassName(getPackageName(), LuaActivity.class.getName());
    localIntent.setData(Uri.parse(paramString1));
    if (Build.VERSION.SDK_INT >= 22)
    {
      localIntent.addFlags(524288);
      localIntent.addFlags(134217728);
    }
    Object localObject;
    if (Build.VERSION.SDK_INT >= 26)
    {
      localObject = (ShortcutManager)getSystemService("shortcut");
      paramString1 = new ShortcutInfo.Builder(this, paramString1).setIcon(Icon.createWithResource(this, 2130771968)).setShortLabel(paramString2).setIntent(localIntent).build();
      try
      {
        ((ShortcutManager)localObject).requestPinShortcut(paramString1, null);
        return;
      }
      catch (Exception paramString1)
      {
        paramString1.printStackTrace();
        paramString1 = "添加快捷方式出错";
      }
    }
    else
    {
      paramString1 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
      localObject = Intent.ShortcutIconResource.fromContext(this, 2130771968);
      paramString1.putExtra("android.intent.extra.shortcut.NAME", paramString2);
      paramString1.putExtra("android.intent.extra.shortcut.INTENT", localIntent);
      paramString1.putExtra("duplicate", 0);
      paramString1.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", (Parcelable)localObject);
      sendBroadcast(paramString1);
      paramString1 = "已添加快捷方式";
    }
    Toast.makeText(this, paramString1, 0).show();
  }
  
  public void createShortcut(String paramString1, String paramString2, String paramString3)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.DEFAULT");
    localIntent.setClassName(getPackageName(), LuaActivity.class.getName());
    localIntent.setData(Uri.parse(paramString1));
    if (Build.VERSION.SDK_INT >= 22)
    {
      localIntent.addFlags(524288);
      localIntent.addFlags(134217728);
    }
    if (Build.VERSION.SDK_INT >= 26)
    {
      ShortcutManager localShortcutManager = (ShortcutManager)getSystemService("shortcut");
      paramString1 = new ShortcutInfo.Builder(this, paramString1).setIcon(Icon.createWithFilePath(paramString3)).setShortLabel(paramString2).setIntent(localIntent).build();
      try
      {
        localShortcutManager.requestPinShortcut(paramString1, null);
        return;
      }
      catch (Exception paramString1)
      {
        paramString1.printStackTrace();
        paramString1 = "添加快捷方式出错";
      }
    }
    else
    {
      paramString1 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
      paramString1.putExtra("android.intent.extra.shortcut.NAME", paramString2);
      paramString1.putExtra("android.intent.extra.shortcut.INTENT", localIntent);
      paramString1.putExtra("duplicate", 0);
      paramString1.putExtra("android.intent.extra.shortcut.ICON", BitmapFactory.decodeFile(paramString3));
      sendBroadcast(paramString1);
      paramString1 = "已添加快捷方式";
    }
    Toast.makeText(this, paramString1, 0).show();
  }
  
  public Object doAsset(String paramString, Object... paramVarArgs)
  {
    i2 = 0;
    i1 = i2;
    try
    {
      byte[] arrayOfByte = readAsset(paramString);
      i1 = i2;
      this.g.setTop(0);
      i1 = i2;
      i2 = this.g.LloadBuffer(arrayOfByte, paramString);
      if (i2 == 0) {}
      int i3;
      for (;;) {}
    }
    catch (Exception paramString)
    {
      try
      {
        this.g.getGlobal("debug");
        this.g.getField(-1, "traceback");
        this.g.remove(-2);
        this.g.insert(-2);
        i3 = paramVarArgs.length;
        i1 = 0;
        while (i1 < i3)
        {
          this.g.pushObjectValue(paramVarArgs[i1]);
          i1 += 1;
        }
        i1 = this.g.pcall(i3, 0, -2 - i3);
        i2 = i1;
        if (i1 == 0) {
          return this.g.toJavaObject(-1);
        }
        i1 = i2;
        paramString = new StringBuilder();
        i1 = i2;
        paramString.append(a(i2));
        i1 = i2;
        paramString.append(": ");
        i1 = i2;
        paramString.append(this.g.toString(-1));
        i1 = i2;
        throw new LuaException(paramString.toString());
      }
      catch (Exception paramString)
      {
        for (;;)
        {
          i1 = i2;
        }
      }
      paramString = paramString;
      setTitle(a(i1));
      setContentView(this.l);
      sendMsg(paramString.getMessage());
      return null;
    }
  }
  
  public Object doFile(String paramString)
  {
    return doFile(paramString, new Object[0]);
  }
  
  public Object doFile(String paramString, Object[] paramArrayOfObject)
  {
    Object localObject = paramString;
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
      this.g.setTop(0);
      int i3 = this.g.LloadFile((String)localObject);
      i2 = i3;
      if (i3 == 0)
      {
        i1 = i3;
        try
        {
          this.g.getGlobal("debug");
          i1 = i3;
          this.g.getField(-1, "traceback");
          i1 = i3;
          this.g.remove(-2);
          i1 = i3;
          this.g.insert(-2);
          i1 = i3;
          int i4 = paramArrayOfObject.length;
          i2 = 0;
          while (i2 < i4)
          {
            i1 = i3;
            this.g.pushObjectValue(paramArrayOfObject[i2]);
            i2 += 1;
          }
          i1 = i3;
          i2 = this.g.pcall(i4, 1, -2 - i4);
          if (i2 == 0) {
            try
            {
              paramString = this.g.toJavaObject(-1);
              return paramString;
            }
            catch (LuaException paramString)
            {
              i1 = i2;
            }
          }
        }
        catch (LuaException paramString)
        {
          break label336;
        }
      }
      i1 = i2;
      paramString = new Intent();
      i1 = i2;
      paramString.putExtra("data", this.g.toString(-1));
      i1 = i2;
      setResult(i2, paramString);
      i1 = i2;
      paramString = new StringBuilder();
      i1 = i2;
      paramString.append(a(i2));
      i1 = i2;
      paramString.append(": ");
      i1 = i2;
      paramString.append(this.g.toString(-1));
      i1 = i2;
      throw new LuaException(paramString.toString());
    }
    catch (LuaException paramString)
    {
      int i2;
      int i1 = 0;
      label336:
      setTitle(a(i1));
      setContentView(this.l);
      sendMsg(paramString.getMessage());
      paramString = paramString.getMessage();
      i1 = paramString.indexOf("android.permission.");
      if (i1 > 0)
      {
        i1 += "android.permission.".length();
        i2 = paramString.indexOf(".", i1);
        if (i2 > i1)
        {
          paramArrayOfObject = paramString.substring(i1, i2);
          this.g.getGlobal("require");
          this.g.pushString("permission");
          this.g.pcall(1, 0, 0);
          this.g.getGlobal("permission_info");
          this.g.getField(-1, paramArrayOfObject);
          paramString = paramArrayOfObject;
          if (this.g.isString(-1))
          {
            paramString = new StringBuilder();
            paramString.append(paramArrayOfObject);
            paramString.append(" (");
            paramString.append(this.g.toString(-1));
            paramString.append(")");
            paramString = paramString.toString();
          }
          paramArrayOfObject = new StringBuilder();
          paramArrayOfObject.append("权限错误: ");
          paramArrayOfObject.append(paramString);
          sendMsg(paramArrayOfObject.toString());
          return null;
        }
      }
      boolean bool = this.A;
    }
    return null;
  }
  
  public Object doString(String paramString, Object... paramVarArgs)
  {
    try
    {
      LuaState localLuaState = this.g;
      int i2 = 0;
      localLuaState.setTop(0);
      int i3 = this.g.LloadString(paramString);
      int i1 = i3;
      if (i3 == 0)
      {
        this.g.getGlobal("debug");
        this.g.getField(-1, "traceback");
        this.g.remove(-2);
        this.g.insert(-2);
        i3 = paramVarArgs.length;
        i1 = i2;
        while (i1 < i3)
        {
          this.g.pushObjectValue(paramVarArgs[i1]);
          i1 += 1;
        }
        i2 = this.g.pcall(i3, 1, -2 - i3);
        i1 = i2;
        if (i2 == 0) {
          return this.g.toJavaObject(-1);
        }
      }
      paramString = new StringBuilder();
      paramString.append(a(i1));
      paramString.append(": ");
      paramString.append(this.g.toString(-1));
      throw new LuaException(paramString.toString());
    }
    catch (LuaException paramString)
    {
      sendMsg(paramString.getMessage());
    }
    return null;
  }
  
  public void finish(boolean paramBoolean)
  {
    if (!paramBoolean)
    {
      super.finish();
      return;
    }
    if (Build.VERSION.SDK_INT >= 21)
    {
      Intent localIntent = getIntent();
      if ((localIntent != null) && ((localIntent.getFlags() & 0x80000) != 0))
      {
        finishAndRemoveTask();
        return;
      }
    }
    super.finish();
  }
  
  public Object get(String paramString)
  {
    synchronized (this.g)
    {
      this.g.getGlobal(paramString);
      paramString = this.g.toJavaObject(-1);
      return paramString;
    }
  }
  
  public Object getArg(int paramInt)
  {
    Object[] arrayOfObject = (Object[])getIntent().getSerializableExtra("arg");
    if ((arrayOfObject != null) && (arrayOfObject.length < paramInt)) {
      return arrayOfObject[paramInt];
    }
    return null;
  }
  
  public AssetManager getAssets()
  {
    if ((this.b != null) && (this.b.getAssets() != null)) {
      return this.b.getAssets();
    }
    return super.getAssets();
  }
  
  public ArrayList<ClassLoader> getClassLoaders()
  {
    return this.b.getClassLoaders();
  }
  
  public Context getContext()
  {
    return this;
  }
  
  public View getDecorView()
  {
    return getWindow().getDecorView();
  }
  
  public Map getGlobalData()
  {
    return ((LuaApplication)getApplication()).getGlobalData();
  }
  
  public int getHeight()
  {
    return this.d;
  }
  
  public HashMap<String, String> getLibrarys()
  {
    return this.b.getLibrarys();
  }
  
  public String getLocalDir()
  {
    return this.t;
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
    return this.w;
  }
  
  public String getLuaExtDir(String paramString)
  {
    paramString = new File(getLuaExtDir(), paramString);
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
    return this.y;
  }
  
  public String getLuaPath()
  {
    Object localObject1 = getIntent().getData();
    if (localObject1 == null) {
      return null;
    }
    Object localObject2 = ((Uri)localObject1).getPath();
    localObject1 = localObject2;
    if (!new File((String)localObject2).exists())
    {
      localObject1 = localObject2;
      if (new File(getLuaPath((String)localObject2)).exists()) {
        localObject1 = getLuaPath((String)localObject2);
      }
    }
    this.h = ((String)localObject1);
    localObject2 = new File((String)localObject1);
    this.luaDir = new File(this.h).getParent();
    if ((((File)localObject2).getName().equals("main.lua")) && (new File(this.luaDir, "init.lua").exists()))
    {
      if (!a.contains(this.luaDir))
      {
        a.add(this.luaDir);
        return (String)localObject1;
      }
    }
    else {
      for (localObject2 = this.luaDir;; localObject2 = new File((String)localObject2).getParent())
      {
        if (localObject2 == null) {
          return localObject1;
        }
        if (a.contains(localObject2))
        {
          this.luaDir = ((String)localObject2);
          return (String)localObject1;
        }
        if ((new File((String)localObject2, "main.lua").exists()) && (new File((String)localObject2, "init.lua").exists()))
        {
          this.luaDir = ((String)localObject2);
          if (a.contains(this.luaDir)) {
            return localObject1;
          }
          break;
        }
      }
    }
    return (String)localObject1;
  }
  
  public String getLuaPath(String paramString)
  {
    return new File(getLuaDir(), paramString).getAbsolutePath();
  }
  
  public String getLuaPath(String paramString1, String paramString2)
  {
    return new File(getLuaDir(paramString1), paramString2).getAbsolutePath();
  }
  
  public LuaResources getLuaResources()
  {
    Resources localResources2 = super.getResources();
    Resources localResources1 = localResources2;
    if (this.b != null)
    {
      localResources1 = localResources2;
      if (this.b.getResources() != null) {
        localResources1 = this.b.getResources();
      }
    }
    this.C = new LuaResources(getAssets(), localResources1.getDisplayMetrics(), localResources1.getConfiguration());
    this.C.setSuperResources(localResources1);
    return this.C;
  }
  
  public LuaState getLuaState()
  {
    return this.g;
  }
  
  public Menu getOptionsMenu()
  {
    return this.o;
  }
  
  public String getPathFromUri(Uri paramUri)
  {
    if (paramUri != null)
    {
      int i1 = 1;
      String str = paramUri.getScheme();
      int i2 = str.hashCode();
      if (i2 != 3143036)
      {
        if ((i2 == 951530617) && (str.equals("content")))
        {
          i1 = 0;
          break label67;
        }
      }
      else {
        if (str.equals("file")) {
          break label67;
        }
      }
      i1 = -1;
      switch (i1)
      {
      default: 
        break;
      case 1: 
        return paramUri.getPath();
      case 0: 
        label67:
        paramUri = getContentResolver().query(paramUri, new String[] { "_data" }, null, null, null);
        if (paramUri != null)
        {
          i1 = paramUri.getColumnIndexOrThrow(getPackageName());
          if (i1 >= 0)
          {
            str = paramUri.getString(i1);
            paramUri.moveToFirst();
            paramUri.close();
            return str;
          }
        }
        break;
      }
    }
    return null;
  }
  
  public String getQuery(String paramString)
  {
    Uri localUri = getIntent().getData();
    if (localUri == null) {
      return null;
    }
    return localUri.getQueryParameter(paramString);
  }
  
  public Resources getResources()
  {
    if ((this.b != null) && (this.b.getResources() != null)) {
      return this.b.getResources();
    }
    if (this.C != null) {
      return this.C;
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
  
  public Resources getSuperResources()
  {
    return super.getResources();
  }
  
  public Uri getUriForFile(File paramFile)
  {
    return FileProvider.getUriForFile(this, getPackageName(), paramFile);
  }
  
  public Uri getUriForPath(String paramString)
  {
    return FileProvider.getUriForFile(this, getPackageName(), new File(paramString));
  }
  
  public int getWidth()
  {
    return this.c;
  }
  
  public void initMain()
  {
    a.add(getLocalDir());
  }
  
  public void installApk(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    paramString = new File(paramString);
    localIntent.setFlags(1);
    localIntent.setDataAndType(getUriForFile(paramString), a(paramString));
    localIntent.addFlags(268435456);
    startActivity(Intent.createChooser(localIntent, paramString.getName()));
  }
  
  public DexClassLoader loadApp(String paramString)
  {
    return this.b.loadApp(paramString);
  }
  
  public Bitmap loadBitmap(String paramString)
  {
    return LuaBitmap.getBitmap(this, paramString);
  }
  
  public DexClassLoader loadDex(String paramString)
  {
    return this.b.loadDex(paramString);
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
    ((StringBuilder)localObject2).append(this.v);
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
      localStringBuilder.append(this.v);
      localStringBuilder.append("/lib");
      localStringBuilder.append((String)localObject1);
      localStringBuilder.append(".so");
      LuaUtil.copyFile((String)localObject2, localStringBuilder.toString());
    }
    return this.g.getLuaObject("require").call(new Object[] { paramString });
  }
  
  public void loadResources(String paramString)
  {
    this.b.loadResources(paramString);
  }
  
  public void newActivity(int paramInt, String paramString)
  {
    newActivity(paramInt, paramString, null);
  }
  
  public void newActivity(int paramInt1, String paramString, int paramInt2, int paramInt3)
  {
    newActivity(paramInt1, paramString, paramInt2, paramInt3, null);
  }
  
  public void newActivity(int paramInt1, String paramString, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    newActivity(paramInt1, paramString, paramInt2, paramInt3, null, paramBoolean);
  }
  
  public void newActivity(int paramInt1, String paramString, int paramInt2, int paramInt3, Object[] paramArrayOfObject)
  {
    newActivity(paramInt1, paramString, paramInt2, paramInt3, paramArrayOfObject, false);
  }
  
  public void newActivity(int paramInt1, String paramString, int paramInt2, int paramInt3, Object[] paramArrayOfObject, boolean paramBoolean)
  {
    Intent localIntent = new Intent(this, LuaActivity.class);
    if (paramBoolean) {
      localIntent = new Intent(this, LuaActivityX.class);
    }
    localIntent.putExtra("name", paramString);
    Object localObject = paramString;
    if (paramString.charAt(0) != '/')
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(this.luaDir);
      ((StringBuilder)localObject).append("/");
      ((StringBuilder)localObject).append(paramString);
      localObject = ((StringBuilder)localObject).toString();
    }
    File localFile = new File((String)localObject);
    if (localFile.isDirectory())
    {
      paramString = new StringBuilder();
      paramString.append((String)localObject);
      paramString.append("/main.lua");
      if (new File(paramString.toString()).exists())
      {
        paramString = new StringBuilder();
        paramString.append((String)localObject);
      }
    }
    for (localObject = "/main.lua";; localObject = ".lua")
    {
      paramString.append((String)localObject);
      paramString = paramString.toString();
      break;
      if (!localFile.isDirectory())
      {
        paramString = (String)localObject;
        if (localFile.exists()) {
          break;
        }
      }
      paramString = (String)localObject;
      if (((String)localObject).endsWith(".lua")) {
        break;
      }
      paramString = new StringBuilder();
      paramString.append((String)localObject);
    }
    if (!new File(paramString).exists()) {
      throw new FileNotFoundException(paramString);
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("file://");
    ((StringBuilder)localObject).append(paramString);
    localIntent.setData(Uri.parse(((StringBuilder)localObject).toString()));
    if ((paramBoolean) && (Build.VERSION.SDK_INT >= 21))
    {
      localIntent.addFlags(524288);
      localIntent.addFlags(134217728);
    }
    if (paramArrayOfObject != null) {
      localIntent.putExtra("arg", paramArrayOfObject);
    }
    if (paramBoolean) {
      startActivity(localIntent);
    } else {
      startActivityForResult(localIntent, paramInt1);
    }
    overridePendingTransition(paramInt2, paramInt3);
  }
  
  public void newActivity(int paramInt, String paramString, boolean paramBoolean)
  {
    newActivity(paramInt, paramString, null, paramBoolean);
  }
  
  public void newActivity(int paramInt, String paramString, Object[] paramArrayOfObject)
  {
    newActivity(paramInt, paramString, paramArrayOfObject, false);
  }
  
  public void newActivity(int paramInt, String paramString, Object[] paramArrayOfObject, boolean paramBoolean)
  {
    Intent localIntent = new Intent(this, LuaActivity.class);
    if (paramBoolean) {
      localIntent = new Intent(this, LuaActivityX.class);
    }
    localIntent.putExtra("name", paramString);
    Object localObject = paramString;
    if (paramString.charAt(0) != '/')
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(this.luaDir);
      ((StringBuilder)localObject).append("/");
      ((StringBuilder)localObject).append(paramString);
      localObject = ((StringBuilder)localObject).toString();
    }
    File localFile = new File((String)localObject);
    if (localFile.isDirectory())
    {
      paramString = new StringBuilder();
      paramString.append((String)localObject);
      paramString.append("/main.lua");
      if (new File(paramString.toString()).exists())
      {
        paramString = new StringBuilder();
        paramString.append((String)localObject);
      }
    }
    for (localObject = "/main.lua";; localObject = ".lua")
    {
      paramString.append((String)localObject);
      paramString = paramString.toString();
      break;
      if (!localFile.isDirectory())
      {
        paramString = (String)localObject;
        if (localFile.exists()) {
          break;
        }
      }
      paramString = (String)localObject;
      if (((String)localObject).endsWith(".lua")) {
        break;
      }
      paramString = new StringBuilder();
      paramString.append((String)localObject);
    }
    if (!new File(paramString).exists()) {
      throw new FileNotFoundException(paramString);
    }
    if ((paramBoolean) && (Build.VERSION.SDK_INT >= 21))
    {
      localIntent.addFlags(524288);
      localIntent.addFlags(134217728);
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("file://");
    ((StringBuilder)localObject).append(paramString);
    localIntent.setData(Uri.parse(((StringBuilder)localObject).toString()));
    if (paramArrayOfObject != null) {
      localIntent.putExtra("arg", paramArrayOfObject);
    }
    if (paramBoolean)
    {
      startActivity(localIntent);
      return;
    }
    startActivityForResult(localIntent, paramInt);
  }
  
  public void newActivity(String paramString)
  {
    newActivity(1, paramString, null);
  }
  
  public void newActivity(String paramString, int paramInt1, int paramInt2)
  {
    newActivity(1, paramString, paramInt1, paramInt2, null);
  }
  
  public void newActivity(String paramString, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    newActivity(1, paramString, paramInt1, paramInt2, null, paramBoolean);
  }
  
  public void newActivity(String paramString, int paramInt1, int paramInt2, Object[] paramArrayOfObject)
  {
    newActivity(1, paramString, paramInt1, paramInt2, paramArrayOfObject);
  }
  
  public void newActivity(String paramString, int paramInt1, int paramInt2, Object[] paramArrayOfObject, boolean paramBoolean)
  {
    newActivity(1, paramString, paramInt1, paramInt2, paramArrayOfObject, paramBoolean);
  }
  
  public void newActivity(String paramString, boolean paramBoolean)
  {
    newActivity(1, paramString, null, paramBoolean);
  }
  
  public void newActivity(String paramString, Object[] paramArrayOfObject)
  {
    newActivity(1, paramString, paramArrayOfObject);
  }
  
  public void newActivity(String paramString, Object[] paramArrayOfObject, boolean paramBoolean)
  {
    newActivity(1, paramString, paramArrayOfObject, paramBoolean);
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
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramIntent != null)
    {
      Object localObject = paramIntent.getStringExtra("name");
      if (localObject != null)
      {
        Object[] arrayOfObject1 = (Object[])paramIntent.getSerializableExtra("data");
        if (arrayOfObject1 == null)
        {
          runFunc("onResult", new Object[] { localObject });
        }
        else
        {
          Object[] arrayOfObject2 = new Object[arrayOfObject1.length + 1];
          arrayOfObject2[0] = localObject;
          int i2;
          for (int i1 = 0; i1 < arrayOfObject1.length; i1 = i2)
          {
            i2 = i1 + 1;
            arrayOfObject2[i2] = arrayOfObject1[i1];
          }
          localObject = runFunc("onResult", arrayOfObject2);
          if ((localObject != null) && (localObject.getClass() == Boolean.class) && (((Boolean)localObject).booleanValue())) {
            return;
          }
        }
      }
    }
    runFunc("onActivityResult", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), paramIntent });
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    WindowManager localWindowManager = (WindowManager)getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.c = localDisplayMetrics.widthPixels;
    this.d = localDisplayMetrics.heightPixels;
    runFunc("onConfigurationChanged", new Object[] { paramConfiguration });
  }
  
  public void onContentChanged()
  {
    super.onContentChanged();
    this.m = true;
  }
  
  public boolean onContextItemSelected(MenuItem paramMenuItem)
  {
    runFunc("onContextItemSelected", new Object[] { paramMenuItem });
    return super.onContextItemSelected(paramMenuItem);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    setTheme(16974064);
    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
    super.onCreate(null);
    Object localObject1 = (WindowManager)getSystemService("window");
    Object localObject2 = new DisplayMetrics();
    ((WindowManager)localObject1).getDefaultDisplay().getMetrics((DisplayMetrics)localObject2);
    this.c = ((DisplayMetrics)localObject2).widthPixels;
    this.d = ((DisplayMetrics)localObject2).heightPixels;
    this.l = new LinearLayout(this);
    localObject1 = new ScrollView(this);
    ((ScrollView)localObject1).setFillViewport(true);
    this.status = new TextView(this);
    this.status.setTextColor(-16777216);
    ((ScrollView)localObject1).addView(this.status, new ViewGroup.LayoutParams(-1, -2));
    this.status.setText("");
    this.status.setTextIsSelectable(true);
    this.e = new ListView(this);
    this.e.setFastScrollEnabled(true);
    this.f = new ArrayListAdapter(this, 17367043)
    {
      public View getView(int paramAnonymousInt, View paramAnonymousView, ViewGroup paramAnonymousViewGroup)
      {
        paramAnonymousViewGroup = (TextView)super.getView(paramAnonymousInt, paramAnonymousView, paramAnonymousViewGroup);
        if (paramAnonymousView == null) {
          paramAnonymousViewGroup.setTextIsSelectable(true);
        }
        return paramAnonymousViewGroup;
      }
    };
    this.e.setAdapter(this.f);
    this.l.addView(this.e, new ViewGroup.LayoutParams(-1, -2));
    localObject1 = (LuaApplication)getApplication();
    this.t = ((LuaApplication)localObject1).getLocalDir();
    this.u = ((LuaApplication)localObject1).getOdexDir();
    this.v = ((LuaApplication)localObject1).getLibDir();
    this.z = ((LuaApplication)localObject1).getMdDir();
    this.luaCpath = ((LuaApplication)localObject1).getLuaCpath();
    this.luaDir = this.t;
    this.y = ((LuaApplication)localObject1).getLuaLpath();
    this.w = ((LuaApplication)localObject1).getLuaExtDir();
    this.handler = new MainHandler();
    try
    {
      this.status.setText("");
      this.f.clear();
      localObject2 = (Object[])getIntent().getSerializableExtra("arg");
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = new Object[0];
      }
      this.h = getLuaPath();
      this.E = new File(this.h).getName();
      int i1 = this.E.lastIndexOf(".");
      if (i1 > 0) {
        this.E = this.E.substring(0, i1);
      }
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(this.luaDir);
      ((StringBuilder)localObject2).append("/?.lua;");
      ((StringBuilder)localObject2).append(this.luaDir);
      ((StringBuilder)localObject2).append("/lua/?.lua;");
      ((StringBuilder)localObject2).append(this.luaDir);
      ((StringBuilder)localObject2).append("/?/init.lua;");
      ((StringBuilder)localObject2).append(this.y);
      this.y = ((StringBuilder)localObject2).toString();
      a();
      this.b = new LuaDexLoader(this);
      this.b.loadLibs();
      G.put(this.E, this);
      doFile(this.h, (Object[])localObject1);
      this.j = Boolean.valueOf(true);
      if (!this.E.equals("main")) {
        runFunc("main", (Object[])localObject1);
      }
      runFunc(this.E, (Object[])localObject1);
      runFunc("onCreate", new Object[] { paramBundle });
      if (!this.m)
      {
        paramBundle = getTheme().obtainStyledAttributes(new int[] { 16842801, 16842806, 16843599 });
        i1 = paramBundle.getColor(0, 16711935);
        int i2 = paramBundle.getColor(1, 16711935);
        paramBundle.recycle();
        this.status.setTextColor(i2);
        this.l.setBackgroundColor(i1);
        setContentView(this.l);
      }
      this.H = this.g.getLuaObject("onKeyShortcut");
      if (this.H.isNil()) {
        this.H = null;
      }
      this.p = this.g.getLuaObject("onKeyDown");
      if (this.p.isNil()) {
        this.p = null;
      }
      this.q = this.g.getLuaObject("onKeyUp");
      if (this.q.isNil()) {
        this.q = null;
      }
      this.r = this.g.getLuaObject("onKeyLongPress");
      if (this.r.isNil()) {
        this.r = null;
      }
      this.s = this.g.getLuaObject("onTouchEvent");
      if (this.s.isNil()) {
        this.s = null;
      }
      paramBundle = this.g.getLuaObject("onAccessibilityEvent");
      if (paramBundle.isFunction()) {
        LuaAccessibilityService.onAccessibilityEvent = paramBundle.getFunction();
      }
      return;
    }
    catch (Exception paramBundle)
    {
      sendMsg(paramBundle.getMessage());
      setContentView(this.l);
    }
  }
  
  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo)
  {
    runFunc("onCreateContextMenu", new Object[] { paramContextMenu, paramView, paramContextMenuInfo });
    super.onCreateContextMenu(paramContextMenu, paramView, paramContextMenuInfo);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    this.o = paramMenu;
    runFunc("onCreateOptionsMenu", new Object[] { paramMenu });
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  protected void onDestroy()
  {
    if (this.x != null) {
      unregisterReceiver(this.x);
    }
    Iterator localIterator = this.D.iterator();
    while (localIterator.hasNext()) {
      ((LuaGcable)localIterator.next()).gc();
    }
    G.remove(this.E);
    runFunc("onDestroy", new Object[0]);
    super.onDestroy();
    System.gc();
    this.g.gc(2, 1);
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (this.p != null) {
      try
      {
        Object localObject = this.p.call(new Object[] { Integer.valueOf(paramInt), paramKeyEvent });
        if ((localObject != null) && (localObject.getClass() == Boolean.class))
        {
          boolean bool = ((Boolean)localObject).booleanValue();
          if (bool) {
            return true;
          }
        }
      }
      catch (LuaException localLuaException)
      {
        sendError("onKeyDown", localLuaException);
      }
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public boolean onKeyLongPress(int paramInt, KeyEvent paramKeyEvent)
  {
    if (this.r != null) {
      try
      {
        Object localObject = this.r.call(new Object[] { Integer.valueOf(paramInt), paramKeyEvent });
        if ((localObject != null) && (localObject.getClass() == Boolean.class))
        {
          boolean bool = ((Boolean)localObject).booleanValue();
          if (bool) {
            return true;
          }
        }
      }
      catch (LuaException localLuaException)
      {
        sendError("onKeyLongPress", localLuaException);
      }
    }
    return super.onKeyLongPress(paramInt, paramKeyEvent);
  }
  
  public boolean onKeyShortcut(int paramInt, KeyEvent paramKeyEvent)
  {
    if (this.H != null) {
      try
      {
        Object localObject = this.H.call(new Object[] { Integer.valueOf(paramInt), paramKeyEvent });
        if ((localObject != null) && (localObject.getClass() == Boolean.class))
        {
          boolean bool = ((Boolean)localObject).booleanValue();
          if (bool) {
            return true;
          }
        }
      }
      catch (LuaException localLuaException)
      {
        sendError("onKeyShortcut", localLuaException);
      }
    }
    return super.onKeyShortcut(paramInt, paramKeyEvent);
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if (this.q != null) {
      try
      {
        Object localObject = this.q.call(new Object[] { Integer.valueOf(paramInt), paramKeyEvent });
        if ((localObject != null) && (localObject.getClass() == Boolean.class))
        {
          boolean bool = ((Boolean)localObject).booleanValue();
          if (bool) {
            return true;
          }
        }
      }
      catch (LuaException localLuaException)
      {
        sendError("onKeyUp", localLuaException);
      }
    }
    return super.onKeyUp(paramInt, paramKeyEvent);
  }
  
  public boolean onMenuItemSelected(int paramInt, MenuItem paramMenuItem)
  {
    if (!paramMenuItem.hasSubMenu()) {
      runFunc("onMenuItemSelected", new Object[] { Integer.valueOf(paramInt), paramMenuItem });
    }
    return super.onMenuItemSelected(paramInt, paramMenuItem);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    Object localObject;
    if (!paramMenuItem.hasSubMenu()) {
      localObject = runFunc("onOptionsItemSelected", new Object[] { paramMenuItem });
    } else {
      localObject = null;
    }
    if ((localObject != null) && (localObject.getClass() == Boolean.class) && (((Boolean)localObject).booleanValue())) {
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  protected void onPause()
  {
    super.onPause();
    runFunc("onPause", new Object[0]);
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    runFunc("onReceive", new Object[] { paramContext, paramIntent });
  }
  
  protected void onResume()
  {
    super.onResume();
    runFunc("onResume", new Object[0]);
  }
  
  protected void onStart()
  {
    super.onStart();
    runFunc("onStart", new Object[0]);
    StatService.onPageStart(this, this.E);
  }
  
  protected void onStop()
  {
    super.onStop();
    runFunc("onStop", new Object[0]);
    StatService.onPageEnd(this, this.E);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.s != null) {
      try
      {
        Object localObject = this.s.call(new Object[] { paramMotionEvent });
        if ((localObject != null) && (localObject.getClass() == Boolean.class))
        {
          boolean bool = ((Boolean)localObject).booleanValue();
          if (bool) {
            return true;
          }
        }
      }
      catch (LuaException localLuaException)
      {
        sendError("onTouchEvent", localLuaException);
      }
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  public void push(int paramInt, String paramString)
  {
    Message localMessage = new Message();
    Bundle localBundle = new Bundle();
    localBundle.putString("data", paramString);
    localMessage.setData(localBundle);
    localMessage.what = paramInt;
    this.handler.sendMessage(localMessage);
  }
  
  public void push(int paramInt, String paramString, Object[] paramArrayOfObject)
  {
    Message localMessage = new Message();
    Bundle localBundle = new Bundle();
    localBundle.putString("data", paramString);
    localBundle.putSerializable("args", paramArrayOfObject);
    localMessage.setData(localBundle);
    localMessage.what = paramInt;
    this.handler.sendMessage(localMessage);
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
    this.D.add(paramLuaGcable);
  }
  
  public Intent registerReceiver(IntentFilter paramIntentFilter)
  {
    if (this.x != null) {
      unregisterReceiver(this.x);
    }
    this.x = new LuaBroadcastReceiver(this);
    return super.registerReceiver(this.x, paramIntentFilter);
  }
  
  public Intent registerReceiver(LuaBroadcastReceiver.OnReceiveListener paramOnReceiveListener, IntentFilter paramIntentFilter)
  {
    return super.registerReceiver(new LuaBroadcastReceiver(paramOnReceiveListener), paramIntentFilter);
  }
  
  public Intent registerReceiver(LuaBroadcastReceiver paramLuaBroadcastReceiver, IntentFilter paramIntentFilter)
  {
    return super.registerReceiver(paramLuaBroadcastReceiver, paramIntentFilter);
  }
  
  public void result(Object[] paramArrayOfObject)
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("name", getIntent().getStringExtra("name"));
    localIntent.putExtra("data", paramArrayOfObject);
    setResult(0, localIntent);
    finish();
  }
  
  public Object runFunc(String paramString, Object... paramVarArgs)
  {
    if (this.g != null)
    {
      try
      {
        synchronized (this.g)
        {
          LuaState localLuaState2 = this.g;
          int i1 = 0;
          localLuaState2.setTop(0);
          this.g.pushGlobalTable();
          this.g.pushString(paramString);
          this.g.rawGet(-2);
          if (this.g.isFunction(-1))
          {
            this.g.getGlobal("debug");
            this.g.getField(-1, "traceback");
            this.g.remove(-2);
            this.g.insert(-2);
            int i2 = paramVarArgs.length;
            while (i1 < i2)
            {
              this.g.pushObjectValue(paramVarArgs[i1]);
              i1 += 1;
            }
            i1 = this.g.pcall(i2, 1, -2 - i2);
            if (i1 == 0)
            {
              paramVarArgs = this.g.toJavaObject(-1);
              return paramVarArgs;
            }
            paramVarArgs = new StringBuilder();
            paramVarArgs.append(a(i1));
            paramVarArgs.append(": ");
            paramVarArgs.append(this.g.toString(-1));
            throw new LuaException(paramVarArgs.toString());
          }
        }
      }
      catch (LuaException paramVarArgs)
      {
        sendError(paramString, paramVarArgs);
      }
      throw paramString;
    }
    return null;
  }
  
  public void sendError(String paramString, Exception paramException)
  {
    Object localObject = runFunc("onError", new Object[] { paramString, paramException });
    if ((localObject != null) && (localObject.getClass() == Boolean.class) && (((Boolean)localObject).booleanValue())) {
      return;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append(": ");
    ((StringBuilder)localObject).append(paramException.getMessage());
    sendMsg(((StringBuilder)localObject).toString());
  }
  
  public void sendMsg(String paramString)
  {
    Message localMessage = new Message();
    Bundle localBundle = new Bundle();
    localBundle.putString("data", paramString);
    localMessage.setData(localBundle);
    localMessage.what = 0;
    this.handler.sendMessage(localMessage);
    Log.i("lua", paramString);
  }
  
  public void set(String paramString, Object paramObject)
  {
    push(1, paramString, new Object[] { paramObject });
  }
  
  public void setContentView(LuaObject paramLuaObject)
  {
    setContentView(paramLuaObject, null);
  }
  
  public void setContentView(LuaObject paramLuaObject1, LuaObject paramLuaObject2)
  {
    LuaObject localLuaObject = this.g.getLuaObject("loadlayout");
    Object[] arrayOfObject;
    if (paramLuaObject1.isString())
    {
      arrayOfObject = new Object[2];
      arrayOfObject[0] = paramLuaObject1.getString();
      arrayOfObject[1] = paramLuaObject2;
    }
    for (paramLuaObject1 = arrayOfObject;; paramLuaObject1 = arrayOfObject)
    {
      paramLuaObject1 = (View)localLuaObject.call(paramLuaObject1);
      break;
      if (!paramLuaObject1.isTable()) {
        break label81;
      }
      arrayOfObject = new Object[2];
      arrayOfObject[0] = paramLuaObject1;
      arrayOfObject[1] = paramLuaObject2;
    }
    super.setContentView(paramLuaObject1);
    return;
    label81:
    throw new LuaException("layout may be table or string.");
  }
  
  public void setContentView(String paramString)
  {
    setContentView(paramString, null);
  }
  
  public void setContentView(String paramString, LuaObject paramLuaObject)
  {
    super.setContentView((View)this.g.getLuaObject("loadlayout").call(new Object[] { paramString, paramLuaObject }));
  }
  
  public void setDebug(boolean paramBoolean)
  {
    this.B = paramBoolean;
  }
  
  public void setFragment(Fragment paramFragment)
  {
    this.m = true;
    getFragmentManager().beginTransaction().replace(16908290, paramFragment).commit();
  }
  
  public void setLuaDir(String paramString)
  {
    this.luaDir = paramString;
  }
  
  public void setLuaExtDir(String paramString)
  {
    if (Environment.getExternalStorageState().equals("mounted")) {}
    for (paramString = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), paramString).getAbsolutePath();; paramString = getDir(paramString, 0).getAbsolutePath())
    {
      this.w = paramString;
      break;
      File[] arrayOfFile = new File("/storage").listFiles();
      int i2 = arrayOfFile.length;
      int i1 = 0;
      while (i1 < i2)
      {
        File localFile = arrayOfFile[i1];
        String[] arrayOfString = localFile.list();
        if ((arrayOfString != null) && (arrayOfString.length > 5)) {
          this.w = new File(localFile, paramString).getAbsolutePath();
        }
        i1 += 1;
      }
      if (this.w != null) {
        break;
      }
    }
    paramString = new File(this.w);
    if (!paramString.exists()) {
      paramString.mkdirs();
    }
  }
  
  public boolean setSharedData(String paramString, Object paramObject)
  {
    return LuaApplication.getInstance().setSharedData(paramString, paramObject);
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    super.setTitle(paramCharSequence);
    if (Build.VERSION.SDK_INT >= 21)
    {
      try
      {
        ActivityManager.TaskDescription localTaskDescription = new ActivityManager.TaskDescription(paramCharSequence.toString(), loadBitmap(getLuaPath("icon.png")));
        paramCharSequence = localTaskDescription;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        paramCharSequence = new ActivityManager.TaskDescription(paramCharSequence.toString());
      }
      setTaskDescription(paramCharSequence);
    }
  }
  
  public void shareFile(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    paramString = new File(paramString);
    localIntent.setType(a(paramString));
    localIntent.setFlags(1);
    localIntent.putExtra("android.intent.extra.STREAM", getUriForFile(paramString));
    startActivity(Intent.createChooser(localIntent, paramString.getName()));
  }
  
  @SuppressLint({"ShowToast"})
  public void showToast(String paramString)
  {
    long l1 = System.currentTimeMillis();
    if ((this.k != null) && (l1 - this.n <= 1000L))
    {
      this.i.append("\n");
      this.i.append(paramString);
      this.k.setText(this.i.toString());
      this.k.setDuration(1);
    }
    else
    {
      this.i.setLength(0);
      this.k = Toast.makeText(this, paramString, 1);
      this.i.append(paramString);
    }
    this.n = l1;
    this.k.show();
  }
  
  public ComponentName startService()
  {
    return startService(null, null);
  }
  
  public ComponentName startService(String paramString)
  {
    return startService(paramString, null);
  }
  
  public ComponentName startService(String paramString, Object[] paramArrayOfObject)
  {
    Intent localIntent = new Intent(this, LuaService.class);
    localIntent.putExtra("luaDir", this.luaDir);
    localIntent.putExtra("luaPath", this.h);
    if (paramString != null)
    {
      StringBuilder localStringBuilder;
      if (paramString.charAt(0) != '/')
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("file://");
        localStringBuilder.append(this.luaDir);
        localStringBuilder.append("/");
        localStringBuilder.append(paramString);
        paramString = ".lua";
      }
      for (;;)
      {
        localStringBuilder.append(paramString);
        localIntent.setData(Uri.parse(localStringBuilder.toString()));
        break;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("file://");
      }
    }
    if (paramArrayOfObject != null) {
      localIntent.putExtra("arg", paramArrayOfObject);
    }
    return super.startService(localIntent);
  }
  
  public ComponentName startService(Object[] paramArrayOfObject)
  {
    return startService(null, paramArrayOfObject);
  }
  
  public boolean stopService()
  {
    return stopService(new Intent(this, LuaService.class));
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
          LuaActivity.this.sendError("onTick", localLuaException);
        }
      }
    });
    localTicker.start();
    localTicker.setPeriod(paramLong);
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
  
  public void unZipAssets(String paramString1, String paramString2)
  {
    Object localObject = new File(paramString2);
    if (!((File)localObject).exists()) {
      ((File)localObject).mkdirs();
    }
    try
    {
      paramString1 = getAssets().open(paramString1);
      localObject = new ZipInputStream(paramString1);
      paramString1 = ((ZipInputStream)localObject).getNextEntry();
      byte[] arrayOfByte = new byte['က'];
      while (paramString1 != null)
      {
        StringBuilder localStringBuilder;
        if (paramString1.isDirectory())
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append(paramString2);
          localStringBuilder.append(File.separator);
          localStringBuilder.append(paramString1.getName());
          new File(localStringBuilder.toString()).mkdir();
        }
        else
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append(paramString2);
          localStringBuilder.append(File.separator);
          localStringBuilder.append(paramString1.getName());
          paramString1 = new File(localStringBuilder.toString());
          paramString1.createNewFile();
          paramString1 = new FileOutputStream(paramString1);
          for (;;)
          {
            int i1 = ((ZipInputStream)localObject).read(arrayOfByte);
            if (i1 <= 0) {
              break;
            }
            paramString1.write(arrayOfByte, 0, i1);
          }
          paramString1.close();
        }
        paramString1 = ((ZipInputStream)localObject).getNextEntry();
      }
      ((ZipInputStream)localObject).close();
      return;
    }
    catch (IOException paramString1) {}
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
        localObject = paramMessage.getData().getString("data");
        paramMessage = paramMessage.getData().getSerializable("args");
        LuaActivity.this.runFunc((String)localObject, (Object[])paramMessage);
        return;
      case 2: 
        paramMessage = paramMessage.getData().getString("data");
        LuaActivity.this.runFunc(paramMessage, new Object[0]);
        return;
      case 1: 
        paramMessage = paramMessage.getData();
        LuaActivity.a(LuaActivity.this, paramMessage.getString("data"), ((Object[])paramMessage.getSerializable("args"))[0]);
        return;
      }
      paramMessage = paramMessage.getData().getString("data");
      if (LuaActivity.a(LuaActivity.this)) {
        LuaActivity.this.showToast(paramMessage);
      }
      Object localObject = LuaActivity.this.status;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramMessage);
      localStringBuilder.append("\n");
      ((TextView)localObject).append(localStringBuilder.toString());
      LuaActivity.b(LuaActivity.this).add(paramMessage);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */