package com.androlua;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.FileProvider;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.widget.Toast;
import com.luajava.LuaState;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LuaApplication
  extends Application
  implements LuaContext
{
  private static LuaApplication h;
  private static HashMap<String, Object> i = new HashMap();
  protected String a;
  protected String b;
  protected String c;
  protected String d;
  protected String e;
  protected String f;
  protected String g;
  private SharedPreferences j;
  
  private static SharedPreferences a(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 24)
    {
      Context localContext = paramContext.createDeviceProtectedStorageContext();
      if (localContext != null) {
        return PreferenceManager.getDefaultSharedPreferences(localContext);
      }
      return PreferenceManager.getDefaultSharedPreferences(paramContext);
    }
    return PreferenceManager.getDefaultSharedPreferences(paramContext);
  }
  
  public static LuaApplication getInstance()
  {
    return h;
  }
  
  public void call(String paramString, Object[] paramArrayOfObject) {}
  
  public Object doFile(String paramString, Object[] paramArrayOfObject)
  {
    return null;
  }
  
  public Object get(String paramString)
  {
    return i.get(paramString);
  }
  
  public ArrayList<ClassLoader> getClassLoaders()
  {
    return null;
  }
  
  public Context getContext()
  {
    return this;
  }
  
  public Map getGlobalData()
  {
    return i;
  }
  
  public int getHeight()
  {
    return getResources().getDisplayMetrics().heightPixels;
  }
  
  public String getLibDir()
  {
    return this.c;
  }
  
  public String getLocalDir()
  {
    return this.a;
  }
  
  public String getLuaCpath()
  {
    return this.e;
  }
  
  public String getLuaDir()
  {
    return this.a;
  }
  
  public String getLuaDir(String paramString)
  {
    return null;
  }
  
  public String getLuaExtDir()
  {
    return this.g;
  }
  
  public String getLuaExtDir(String paramString)
  {
    return null;
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
    return this.f;
  }
  
  public String getLuaPath()
  {
    return null;
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
    return null;
  }
  
  public String getMdDir()
  {
    return this.d;
  }
  
  public String getOdexDir()
  {
    return this.b;
  }
  
  public String getPathFromUri(Uri paramUri)
  {
    if (paramUri != null)
    {
      int k = 1;
      String str1 = getPackageName();
      String str2 = paramUri.getScheme();
      int m = str2.hashCode();
      if (m != 3143036)
      {
        if ((m == 951530617) && (str2.equals("content")))
        {
          k = 0;
          break label69;
        }
      }
      else {
        if (str2.equals("file")) {
          break label69;
        }
      }
      k = -1;
      switch (k)
      {
      default: 
        break;
      case 1: 
        return paramUri.getPath();
      case 0: 
        label69:
        paramUri = getContentResolver().query(paramUri, new String[] { str1 }, null, null, null);
        if (paramUri != null)
        {
          k = paramUri.getColumnIndexOrThrow(getPackageName());
          if (k >= 0)
          {
            str1 = paramUri.getString(k);
            paramUri.moveToFirst();
            paramUri.close();
            return str1;
          }
        }
        break;
      }
    }
    return null;
  }
  
  public Object getSharedData(String paramString)
  {
    return this.j.getAll().get(paramString);
  }
  
  public Object getSharedData(String paramString, Object paramObject)
  {
    paramString = this.j.getAll().get(paramString);
    if (paramString == null) {
      return paramObject;
    }
    return paramString;
  }
  
  public Uri getUriForFile(File paramFile)
  {
    return FileProvider.a(this, getPackageName(), paramFile);
  }
  
  public Uri getUriForPath(String paramString)
  {
    return FileProvider.a(this, getPackageName(), new File(paramString));
  }
  
  public int getWidth()
  {
    return getResources().getDisplayMetrics().widthPixels;
  }
  
  public void onCreate()
  {
    super.onCreate();
    h = this;
    CrashHandler.getInstance().init(getApplicationContext());
    this.j = a(this);
    StringBuilder localStringBuilder;
    if (Environment.getExternalStorageState().equals("mounted"))
    {
      localObject1 = Environment.getExternalStorageDirectory().getAbsolutePath();
      localStringBuilder = new StringBuilder();
      localStringBuilder.append((String)localObject1);
      localStringBuilder.append("/AndroLua");
    }
    for (Object localObject1 = localStringBuilder.toString();; localObject1 = getDir("AndroLua", 0).getAbsolutePath())
    {
      this.g = ((String)localObject1);
      break;
      localObject1 = new File("/storage").listFiles();
      int m = localObject1.length;
      int k = 0;
      while (k < m)
      {
        localStringBuilder = localObject1[k];
        Object localObject2 = localStringBuilder.list();
        if ((localObject2 != null) && (localObject2.length > 5))
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append(localStringBuilder.getAbsolutePath());
          ((StringBuilder)localObject2).append("/AndroLua");
          this.g = ((StringBuilder)localObject2).toString();
        }
        k += 1;
      }
      if (this.g != null) {
        break;
      }
    }
    localObject1 = new File(this.g);
    if (!((File)localObject1).exists()) {
      ((File)localObject1).mkdirs();
    }
    this.a = getFilesDir().getAbsolutePath();
    this.b = getDir("odex", 0).getAbsolutePath();
    this.c = getDir("lib", 0).getAbsolutePath();
    this.d = getDir("lua", 0).getAbsolutePath();
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(getApplicationInfo().nativeLibraryDir);
    ((StringBuilder)localObject1).append("/lib?.so;");
    ((StringBuilder)localObject1).append(this.c);
    ((StringBuilder)localObject1).append("/lib?.so");
    this.e = ((StringBuilder)localObject1).toString();
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(this.d);
    ((StringBuilder)localObject1).append("/?.lua;");
    ((StringBuilder)localObject1).append(this.d);
    ((StringBuilder)localObject1).append("/lua/?.lua;");
    ((StringBuilder)localObject1).append(this.d);
    ((StringBuilder)localObject1).append("/?/init.lua;");
    this.f = ((StringBuilder)localObject1).toString();
  }
  
  public void regGc(LuaGcable paramLuaGcable) {}
  
  public void sendError(String paramString, Exception paramException) {}
  
  public void sendMsg(String paramString)
  {
    Toast.makeText(this, paramString, 0).show();
  }
  
  public void set(String paramString, Object paramObject)
  {
    i.put(paramString, paramObject);
  }
  
  public void setLuaExtDir(String paramString)
  {
    if (Environment.getExternalStorageState().equals("mounted")) {}
    for (paramString = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), paramString).getAbsolutePath();; paramString = getDir(paramString, 0).getAbsolutePath())
    {
      this.g = paramString;
      return;
      File[] arrayOfFile = new File("/storage").listFiles();
      int m = arrayOfFile.length;
      int k = 0;
      while (k < m)
      {
        File localFile = arrayOfFile[k];
        String[] arrayOfString = localFile.list();
        if ((arrayOfString != null) && (arrayOfString.length > 5)) {
          this.g = new File(localFile, paramString).getAbsolutePath();
        }
        k += 1;
      }
      if (this.g != null) {
        break;
      }
    }
  }
  
  public boolean setSharedData(String paramString, Object paramObject)
  {
    SharedPreferences.Editor localEditor = this.j.edit();
    if (paramObject == null)
    {
      localEditor.remove(paramString);
    }
    else if ((paramObject instanceof String))
    {
      localEditor.putString(paramString, paramObject.toString());
    }
    else if ((paramObject instanceof Long))
    {
      localEditor.putLong(paramString, ((Long)paramObject).longValue());
    }
    else if ((paramObject instanceof Integer))
    {
      localEditor.putInt(paramString, ((Integer)paramObject).intValue());
    }
    else
    {
      if (!(paramObject instanceof Float)) {
        break label127;
      }
      localEditor.putFloat(paramString, ((Float)paramObject).floatValue());
    }
    localEditor.apply();
    return true;
    label127:
    return false;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaApplication.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */