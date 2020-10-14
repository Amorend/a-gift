package com.tencent.open.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.open.a.f;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class b
{
  private static String c;
  private String a;
  private c b;
  private long d;
  private Handler e;
  private Runnable f = new Runnable()
  {
    public void run()
    {
      f.a("AsynLoadImg", "saveFileRunnable:");
      String str1 = k.f(b.b(b.this));
      str1 = "share_qq_" + str1 + ".jpg";
      String str2 = b.a() + str1;
      Object localObject = new File(str2);
      Message localMessage = b.c(b.this).obtainMessage();
      if (((File)localObject).exists())
      {
        localMessage.arg1 = 0;
        localMessage.obj = str2;
        f.a("AsynLoadImg", "file exists: time:" + (System.currentTimeMillis() - b.d(b.this)));
        b.c(b.this).sendMessage(localMessage);
        return;
      }
      boolean bool = false;
      localObject = b.a(b.b(b.this));
      if (localObject != null)
      {
        bool = b.this.a((Bitmap)localObject, str1);
        label184:
        if (!bool) {
          break label247;
        }
        localMessage.arg1 = 0;
        localMessage.obj = str2;
      }
      for (;;)
      {
        f.a("AsynLoadImg", "file not exists: download time:" + (System.currentTimeMillis() - b.d(b.this)));
        break;
        f.a("AsynLoadImg", "saveFileRunnable:get bmp fail---");
        break label184;
        label247:
        localMessage.arg1 = 1;
      }
    }
  };
  
  public b(Activity paramActivity)
  {
    this.e = new Handler(paramActivity.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        f.a("AsynLoadImg", "handleMessage:" + paramAnonymousMessage.arg1);
        if (paramAnonymousMessage.arg1 == 0)
        {
          b.a(b.this).a(paramAnonymousMessage.arg1, (String)paramAnonymousMessage.obj);
          return;
        }
        b.a(b.this).a(paramAnonymousMessage.arg1, null);
      }
    };
  }
  
  public static Bitmap a(String paramString)
  {
    f.a("AsynLoadImg", "getbitmap:" + paramString);
    try
    {
      Object localObject = (HttpURLConnection)new URL(paramString).openConnection();
      ((HttpURLConnection)localObject).setDoInput(true);
      ((HttpURLConnection)localObject).connect();
      localObject = ((HttpURLConnection)localObject).getInputStream();
      Bitmap localBitmap = BitmapFactory.decodeStream((InputStream)localObject);
      ((InputStream)localObject).close();
      f.a("AsynLoadImg", "image download finished." + paramString);
      return localBitmap;
    }
    catch (OutOfMemoryError paramString)
    {
      paramString.printStackTrace();
      f.a("AsynLoadImg", "getbitmap bmp fail---");
      return null;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
      f.a("AsynLoadImg", "getbitmap bmp fail---");
    }
    return null;
  }
  
  public void a(String paramString, c paramc)
  {
    f.a("AsynLoadImg", "--save---");
    if ((paramString == null) || (paramString.equals("")))
    {
      paramc.a(1, null);
      return;
    }
    if (!k.b())
    {
      paramc.a(2, null);
      return;
    }
    c = Environment.getExternalStorageDirectory() + "/tmp/";
    this.d = System.currentTimeMillis();
    this.a = paramString;
    this.b = paramc;
    new Thread(this.f).start();
  }
  
  public boolean a(Bitmap paramBitmap, String paramString)
  {
    String str = c;
    Object localObject4 = null;
    Object localObject3 = null;
    Object localObject2 = localObject3;
    localObject1 = localObject4;
    try
    {
      File localFile = new File(str);
      localObject2 = localObject3;
      localObject1 = localObject4;
      if (!localFile.exists())
      {
        localObject2 = localObject3;
        localObject1 = localObject4;
        localFile.mkdir();
      }
      localObject2 = localObject3;
      localObject1 = localObject4;
      str = str + paramString;
      localObject2 = localObject3;
      localObject1 = localObject4;
      f.a("AsynLoadImg", "saveFile:" + paramString);
      localObject2 = localObject3;
      localObject1 = localObject4;
      paramString = new BufferedOutputStream(new FileOutputStream(new File(str)));
      localObject2 = paramString;
      localObject1 = paramString;
      paramBitmap.compress(Bitmap.CompressFormat.JPEG, 80, paramString);
      localObject2 = paramString;
      localObject1 = paramString;
      paramString.flush();
      if (paramString != null) {}
      try
      {
        paramString.close();
        return true;
      }
      catch (IOException paramBitmap)
      {
        for (;;)
        {
          paramBitmap.printStackTrace();
        }
      }
      try
      {
        ((BufferedOutputStream)localObject1).close();
        throw paramBitmap;
      }
      catch (IOException paramString)
      {
        for (;;)
        {
          paramString.printStackTrace();
        }
      }
    }
    catch (IOException paramBitmap)
    {
      localObject1 = localObject2;
      paramBitmap.printStackTrace();
      localObject1 = localObject2;
      f.b("AsynLoadImg", "saveFile bmp fail---", paramBitmap);
      if (localObject2 != null) {}
      try
      {
        ((BufferedOutputStream)localObject2).close();
        return false;
      }
      catch (IOException paramBitmap)
      {
        for (;;)
        {
          paramBitmap.printStackTrace();
        }
      }
    }
    finally
    {
      if (localObject1 == null) {}
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\utils\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */