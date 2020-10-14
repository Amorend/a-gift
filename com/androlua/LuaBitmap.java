package com.androlua;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.WeakHashMap;

public class LuaBitmap
{
  static WeakHashMap<String, WeakReference<Bitmap>> a = new WeakHashMap();
  private static long b = 604800000L;
  
  private static int a(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    int i = b(paramOptions, paramInt1, paramInt2);
    if (i <= 8)
    {
      paramInt1 = 1;
      for (;;)
      {
        paramInt2 = paramInt1;
        if (paramInt1 >= i) {
          break;
        }
        paramInt1 <<= 1;
      }
    }
    paramInt2 = (i + 7) / 8 * 8;
    return paramInt2;
  }
  
  private static int b(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    double d1 = paramOptions.outWidth;
    double d2 = paramOptions.outHeight;
    int i;
    if (paramInt2 == -1) {
      i = 1;
    } else {
      i = (int)Math.ceil(Math.sqrt(d1 * d2 / paramInt2));
    }
    int j;
    if (paramInt1 == -1)
    {
      j = 128;
    }
    else
    {
      double d3 = paramInt1;
      j = (int)Math.min(Math.floor(d1 / d3), Math.floor(d2 / d3));
    }
    if (j < i) {
      return i;
    }
    if ((paramInt2 == -1) && (paramInt1 == -1)) {
      return 1;
    }
    if (paramInt1 == -1) {
      return i;
    }
    return j;
  }
  
  public static boolean checkCache(LuaContext paramLuaContext, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramLuaContext.getLuaExtDir("cache"));
    localStringBuilder.append("/");
    localStringBuilder.append(paramString.hashCode());
    paramLuaContext = new File(localStringBuilder.toString());
    return (paramLuaContext.exists()) && (b != -1L) && (System.currentTimeMillis() - paramLuaContext.lastModified() < b);
  }
  
  public static Bitmap decodeScale(int paramInt, File paramFile)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    int i = 1;
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramFile.getAbsolutePath(), localOptions);
    if ((localOptions.outHeight > paramInt * 4) || (localOptions.outWidth > paramInt)) {
      i = (int)Math.pow(2.0D, (int)Math.round(Math.log(paramInt / Math.max(localOptions.outHeight, localOptions.outWidth)) / Math.log(0.5D)));
    }
    localOptions = new BitmapFactory.Options();
    localOptions.inSampleSize = i;
    return BitmapFactory.decodeFile(paramFile.getAbsolutePath(), localOptions);
  }
  
  public static Bitmap getAssetBitmap(Context paramContext, String paramString)
  {
    paramContext = paramContext.getAssets().open(paramString);
    paramString = BitmapFactory.decodeStream(paramContext);
    paramContext.close();
    return paramString;
  }
  
  public static Bitmap getBitmap(LuaContext paramLuaContext, String paramString)
  {
    Object localObject = (WeakReference)a.get(paramString);
    if (localObject != null)
    {
      localObject = (Bitmap)((WeakReference)localObject).get();
      if (localObject != null) {
        return (Bitmap)localObject;
      }
    }
    if ((!paramString.toLowerCase().startsWith("http://")) && (!paramString.toLowerCase().startsWith("https://")))
    {
      if (paramString.charAt(0) != '/')
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(paramLuaContext.getLuaDir());
        ((StringBuilder)localObject).append("/");
        ((StringBuilder)localObject).append(paramString);
        paramLuaContext = getLocalBitmap(paramLuaContext, ((StringBuilder)localObject).toString());
      }
      else
      {
        paramLuaContext = getLocalBitmap(paramLuaContext, paramString);
      }
    }
    else {
      paramLuaContext = getHttpBitmap(paramLuaContext, paramString);
    }
    a.put(paramString, new WeakReference(paramLuaContext));
    return paramLuaContext;
  }
  
  public static Bitmap getBitmapFromFile(File paramFile, int paramInt1, int paramInt2)
  {
    if ((paramFile != null) && (paramFile.exists()))
    {
      BitmapFactory.Options localOptions;
      if ((paramInt1 > 0) && (paramInt2 > 0))
      {
        localOptions = new BitmapFactory.Options();
        localOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(paramFile.getPath(), localOptions);
        localOptions.inSampleSize = a(localOptions, Math.min(paramInt1, paramInt2), paramInt1 * paramInt2);
        localOptions.inJustDecodeBounds = false;
        localOptions.inInputShareable = true;
        localOptions.inPurgeable = true;
      }
      else
      {
        localOptions = null;
      }
      try
      {
        paramFile = BitmapFactory.decodeFile(paramFile.getPath(), localOptions);
        return paramFile;
      }
      catch (OutOfMemoryError paramFile)
      {
        paramFile.printStackTrace();
      }
    }
    return null;
  }
  
  public static long getCacheTime()
  {
    return b;
  }
  
  public static Bitmap getHttpBitmap(LuaContext paramLuaContext, String paramString)
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(paramLuaContext.getLuaExtDir("cache"));
    ((StringBuilder)localObject1).append("/");
    ((StringBuilder)localObject1).append(paramString.hashCode());
    localObject1 = ((StringBuilder)localObject1).toString();
    Object localObject2 = new File((String)localObject1);
    int i;
    if ((((File)localObject2).exists()) && (b != -1L) && (System.currentTimeMillis() - ((File)localObject2).lastModified() < b)) {
      i = paramLuaContext.getWidth();
    }
    for (paramLuaContext = new File((String)localObject1);; paramLuaContext = new File((String)localObject1))
    {
      return decodeScale(i, paramLuaContext);
      new File((String)localObject1).delete();
      paramString = (HttpURLConnection)new URL(paramString).openConnection();
      paramString.setConnectTimeout(120000);
      paramString.setDoInput(true);
      paramString.connect();
      paramString = paramString.getInputStream();
      localObject2 = new FileOutputStream((String)localObject1);
      if (!LuaUtil.copyFile(paramString, (OutputStream)localObject2))
      {
        ((FileOutputStream)localObject2).close();
        paramString.close();
        new File((String)localObject1).delete();
        throw new RuntimeException("LoadHttpBitmap Error.");
      }
      ((FileOutputStream)localObject2).close();
      paramString.close();
      i = paramLuaContext.getWidth();
    }
  }
  
  public static Bitmap getHttpBitmap(String paramString)
  {
    paramString = (HttpURLConnection)new URL(paramString).openConnection();
    paramString.setDoInput(true);
    paramString.connect();
    paramString = paramString.getInputStream();
    Bitmap localBitmap = BitmapFactory.decodeStream(paramString);
    paramString.close();
    return localBitmap;
  }
  
  public static Bitmap getImageFromPath(String paramString)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    localOptions.inSampleSize = a(localOptions, -1, 62500);
    localOptions.inJustDecodeBounds = false;
    try
    {
      paramString = BitmapFactory.decodeFile(paramString, localOptions);
      return paramString;
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static Bitmap getLocalBitmap(LuaContext paramLuaContext, String paramString)
  {
    return decodeScale(paramLuaContext.getWidth(), new File(paramString));
  }
  
  public static Bitmap getLocalBitmap(String paramString)
  {
    paramString = new FileInputStream(paramString);
    Bitmap localBitmap = BitmapFactory.decodeStream(paramString);
    paramString.close();
    return localBitmap;
  }
  
  public static void setCacheTime(long paramLong)
  {
    b = paramLong;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaBitmap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */