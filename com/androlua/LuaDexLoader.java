package com.androlua;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import com.luajava.LuaException;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class LuaDexLoader
{
  private static HashMap<String, LuaDexClassLoader> a = new HashMap();
  private ArrayList<ClassLoader> b = new ArrayList();
  private HashMap<String, String> c = new HashMap();
  private LuaContext d;
  private String e;
  private AssetManager f;
  private Resources g;
  private Resources.Theme h;
  private String i;
  
  public LuaDexLoader(LuaContext paramLuaContext)
  {
    this.d = paramLuaContext;
    this.e = paramLuaContext.getLuaDir();
    this.i = LuaApplication.getInstance().getOdexDir();
  }
  
  public AssetManager getAssets()
  {
    return this.f;
  }
  
  public ArrayList<ClassLoader> getClassLoaders()
  {
    return this.b;
  }
  
  public HashMap<String, String> getLibrarys()
  {
    return this.c;
  }
  
  public Resources getResources()
  {
    return this.g;
  }
  
  public Resources.Theme getTheme()
  {
    return this.h;
  }
  
  public LuaDexClassLoader loadApp(String paramString)
  {
    try
    {
      LuaDexClassLoader localLuaDexClassLoader = (LuaDexClassLoader)a.get(paramString);
      Object localObject = localLuaDexClassLoader;
      if (localLuaDexClassLoader == null)
      {
        localObject = this.d.getContext().getPackageManager().getPackageInfo(paramString, 0).applicationInfo;
        localObject = new LuaDexClassLoader(((ApplicationInfo)localObject).publicSourceDir, LuaApplication.getInstance().getOdexDir(), ((ApplicationInfo)localObject).nativeLibraryDir, this.d.getContext().getClassLoader());
        a.put(paramString, localObject);
      }
      if (!this.b.contains(localObject)) {
        this.b.add(localObject);
      }
      return (LuaDexClassLoader)localObject;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public DexClassLoader loadDex(String paramString)
  {
    Object localObject2 = (LuaDexClassLoader)a.get(paramString);
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = loadApp(paramString);
    }
    if (localObject1 == null)
    {
      if (paramString.charAt(0) != '/')
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(this.e);
        ((StringBuilder)localObject1).append("/");
        ((StringBuilder)localObject1).append(paramString);
        localObject1 = ((StringBuilder)localObject1).toString();
      }
      else
      {
        localObject1 = paramString;
      }
      localObject2 = localObject1;
      if (!new File((String)localObject1).exists())
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append(".dex");
        if (new File(((StringBuilder)localObject2).toString()).exists())
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append((String)localObject1);
        }
        for (localObject1 = ".dex";; localObject1 = ".jar")
        {
          ((StringBuilder)localObject2).append((String)localObject1);
          localObject2 = ((StringBuilder)localObject2).toString();
          break label253;
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append((String)localObject1);
          ((StringBuilder)localObject2).append(".jar");
          if (!new File(((StringBuilder)localObject2).toString()).exists()) {
            break;
          }
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append((String)localObject1);
        }
        paramString = new StringBuilder();
        paramString.append((String)localObject1);
        paramString.append(" not found");
        throw new LuaException(paramString.toString());
      }
      label253:
      localObject1 = LuaUtil.getFileMD5((String)localObject2);
      if ((localObject1 != null) && (((String)localObject1).equals("0"))) {
        localObject1 = paramString;
      }
      LuaDexClassLoader localLuaDexClassLoader = (LuaDexClassLoader)a.get(localObject1);
      paramString = localLuaDexClassLoader;
      if (localLuaDexClassLoader == null)
      {
        paramString = new LuaDexClassLoader((String)localObject2, this.i, LuaApplication.getInstance().getApplicationInfo().nativeLibraryDir, this.d.getContext().getClassLoader());
        a.put(localObject1, paramString);
      }
    }
    else
    {
      paramString = (String)localObject1;
    }
    if (!this.b.contains(paramString))
    {
      this.b.add(paramString);
      localObject1 = paramString.getDexPath();
      if (((String)localObject1).endsWith(".jar")) {
        loadResources((String)localObject1);
      }
    }
    return paramString;
  }
  
  public void loadLib(String paramString)
  {
    int j = paramString.indexOf(".");
    if (j > 0) {
      localObject1 = paramString.substring(0, j);
    } else {
      localObject1 = paramString;
    }
    Object localObject2 = localObject1;
    if (((String)localObject1).startsWith("lib")) {
      localObject2 = ((String)localObject1).substring(3);
    }
    Object localObject1 = this.d.getContext().getDir((String)localObject2, 0).getAbsolutePath();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append((String)localObject1);
    localStringBuilder.append("/lib");
    localStringBuilder.append((String)localObject2);
    localStringBuilder.append(".so");
    localObject1 = localStringBuilder.toString();
    if (!new File((String)localObject1).exists())
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(this.e);
      localStringBuilder.append("/libs/lib");
      localStringBuilder.append((String)localObject2);
      localStringBuilder.append(".so");
      if (!new File(localStringBuilder.toString()).exists())
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("can not find lib ");
        ((StringBuilder)localObject1).append(paramString);
        throw new LuaException(((StringBuilder)localObject1).toString());
      }
      paramString = new StringBuilder();
      paramString.append(this.e);
      paramString.append("/libs/lib");
      paramString.append((String)localObject2);
      paramString.append(".so");
      LuaUtil.copyFile(paramString.toString(), (String)localObject1);
    }
    this.c.put(localObject2, localObject1);
  }
  
  public void loadLibs()
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(this.d.getLuaDir());
    ((StringBuilder)localObject1).append("/libs");
    localObject1 = new File(((StringBuilder)localObject1).toString()).listFiles();
    if (localObject1 == null) {
      return;
    }
    int k = localObject1.length;
    int j = 0;
    while (j < k)
    {
      Object localObject2 = localObject1[j];
      if (((File)localObject2).getAbsolutePath().endsWith(".so")) {
        loadLib(((File)localObject2).getName());
      } else {
        loadDex(((File)localObject2).getAbsolutePath());
      }
      j += 1;
    }
  }
  
  public void loadResources(String paramString)
  {
    try
    {
      AssetManager localAssetManager = (AssetManager)AssetManager.class.newInstance();
      if (((Integer)localAssetManager.getClass().getMethod("addAssetPath", new Class[] { String.class }).invoke(localAssetManager, new Object[] { paramString })).intValue() == 0) {
        return;
      }
      this.f = localAssetManager;
      paramString = this.d.getContext().getResources();
      this.g = new LuaResources(this.f, paramString.getDisplayMetrics(), paramString.getConfiguration());
      this.h = this.g.newTheme();
      this.h.setTo(this.d.getContext().getTheme());
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaDexLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */