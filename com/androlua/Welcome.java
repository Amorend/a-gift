package com.androlua;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import com.luajava.LuaFunction;
import com.luajava.LuaState;
import com.luajava.LuaStateFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Welcome
  extends Activity
{
  private boolean a;
  private LuaApplication b;
  private String c;
  private String d;
  private long e;
  private long f;
  private boolean g;
  private String h;
  private String i;
  
  public boolean checkInfo()
  {
    try
    {
      Object localObject = getPackageManager().getPackageInfo(getPackageName(), 0);
      long l1 = ((PackageInfo)localObject).lastUpdateTime;
      localObject = ((PackageInfo)localObject).versionName;
      SharedPreferences localSharedPreferences = getSharedPreferences("appInfo", 0);
      String str = localSharedPreferences.getString("versionName", "");
      if (!((String)localObject).equals(str))
      {
        SharedPreferences.Editor localEditor = localSharedPreferences.edit();
        localEditor.putString("versionName", (String)localObject);
        localEditor.apply();
        this.g = true;
        this.h = ((String)localObject);
        this.i = str;
      }
      long l2 = localSharedPreferences.getLong("lastUpdateTime", 0L);
      if (l2 != l1)
      {
        localObject = localSharedPreferences.edit();
        ((SharedPreferences.Editor)localObject).putLong("lastUpdateTime", l1);
        ((SharedPreferences.Editor)localObject).apply();
        this.a = true;
        this.e = l1;
        this.f = l2;
        return true;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return false;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.b = ((LuaApplication)getApplication());
    this.c = this.b.d;
    this.d = this.b.a;
    try
    {
      getWindow().setBackgroundDrawable(new NineBitmapDrawable(LuaBitmap.getLocalBitmap(this.b.getLuaPath("setup.png"))));
    }
    catch (IOException paramBundle)
    {
      paramBundle.printStackTrace();
    }
    if (checkInfo())
    {
      new UpdateTask(null).execute(new Object[0]);
      return;
    }
    startActivity();
  }
  
  public void startActivity()
  {
    Intent localIntent = new Intent(this, Main.class);
    if (this.g)
    {
      localIntent.putExtra("isVersionChanged", this.g);
      localIntent.putExtra("newVersionName", this.h);
      localIntent.putExtra("oldVersionName", this.i);
    }
    startActivity(localIntent);
    finish();
  }
  
  private class UpdateTask
    extends AsyncTask
  {
    private UpdateTask() {}
    
    private void a(long paramLong1, long paramLong2)
    {
      Object localObject = LuaStateFactory.newLuaState();
      ((LuaState)localObject).openLibs();
      try
      {
        if ((((LuaState)localObject).LloadBuffer(LuaUtil.readAsset(Welcome.this, "update.lua"), "update") == 0) && (((LuaState)localObject).pcall(0, 0, 0) == 0))
        {
          localObject = ((LuaState)localObject).getFunction("onUpdate");
          if (localObject != null) {
            ((LuaFunction)localObject).call(new Object[] { Welcome.c(Welcome.this), Welcome.d(Welcome.this) });
          }
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      try
      {
        a("assets", Welcome.e(Welcome.this));
        a("lua", Welcome.f(Welcome.this));
        return;
      }
      catch (IOException localIOException)
      {
        a(localIOException.getMessage());
      }
    }
    
    private void a(String paramString) {}
    
    private void a(String paramString1, String paramString2)
    {
      int i = paramString1.length();
      ZipFile localZipFile = new ZipFile(Welcome.this.getApplicationInfo().publicSourceDir);
      Enumeration localEnumeration = localZipFile.entries();
      while (localEnumeration.hasMoreElements())
      {
        Object localObject1 = (ZipEntry)localEnumeration.nextElement();
        Object localObject2 = ((ZipEntry)localObject1).getName();
        if (((String)localObject2).indexOf(paramString1) == 0)
        {
          localObject2 = ((String)localObject2).substring(i + 1);
          if (((ZipEntry)localObject1).isDirectory())
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append(paramString2);
            ((StringBuilder)localObject1).append(File.separator);
            ((StringBuilder)localObject1).append((String)localObject2);
            localObject1 = new File(((StringBuilder)localObject1).toString());
            if (!((File)localObject1).exists()) {
              ((File)localObject1).mkdirs();
            }
          }
          else
          {
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append(paramString2);
            ((StringBuilder)localObject3).append(File.separator);
            ((StringBuilder)localObject3).append((String)localObject2);
            Object localObject4 = ((StringBuilder)localObject3).toString();
            localObject3 = new File((String)localObject4);
            localObject4 = new File((String)localObject4).getParentFile();
            if ((!((File)localObject4).exists()) && (!((File)localObject4).mkdirs()))
            {
              paramString1 = new StringBuilder();
              paramString1.append("create file ");
              paramString1.append(((File)localObject4).getName());
              paramString1.append(" fail");
              throw new RuntimeException(paramString1.toString());
            }
          }
        }
        try
        {
          if ((((File)localObject3).exists()) && (((ZipEntry)localObject1).getSize() == ((File)localObject3).length()))
          {
            boolean bool = LuaUtil.getFileMD5(localZipFile.getInputStream((ZipEntry)localObject1)).equals(LuaUtil.getFileMD5((File)localObject3));
            if (!bool) {}
          }
        }
        catch (NullPointerException localNullPointerException)
        {
          for (;;) {}
        }
        Object localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append(paramString2);
        ((StringBuilder)localObject3).append(File.separator);
        ((StringBuilder)localObject3).append((String)localObject2);
        localObject2 = new FileOutputStream(((StringBuilder)localObject3).toString());
        localObject1 = localZipFile.getInputStream((ZipEntry)localObject1);
        localObject3 = new byte['က'];
        for (;;)
        {
          int j = ((InputStream)localObject1).read((byte[])localObject3);
          if (j == -1) {
            break;
          }
          ((FileOutputStream)localObject2).write((byte[])localObject3, 0, j);
        }
        ((FileOutputStream)localObject2).close();
        ((InputStream)localObject1).close();
      }
      localZipFile.close();
    }
    
    protected Object doInBackground(Object[] paramArrayOfObject)
    {
      a(Welcome.a(Welcome.this), Welcome.b(Welcome.this));
      return null;
    }
    
    protected void onPostExecute(Object paramObject)
    {
      Welcome.this.startActivity();
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\Welcome.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */