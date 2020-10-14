package com.tencent.connect.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.tencent.open.a.f;
import com.tencent.open.utils.c;
import com.tencent.open.utils.k;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class a
{
  public static final int a(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
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
  
  private static Bitmap a(Bitmap paramBitmap, int paramInt)
  {
    Matrix localMatrix = new Matrix();
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    if (i > j) {}
    for (;;)
    {
      float f = paramInt / i;
      localMatrix.postScale(f, f);
      return Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), localMatrix, true);
      i = j;
    }
  }
  
  public static final Bitmap a(String paramString, int paramInt)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    try
    {
      BitmapFactory.decodeFile(paramString, localOptions);
      i = localOptions.outWidth;
      j = localOptions.outHeight;
      if ((localOptions.mCancel) || (localOptions.outWidth == -1) || (localOptions.outHeight == -1)) {
        return null;
      }
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      int i;
      int j;
      for (;;)
      {
        localOutOfMemoryError.printStackTrace();
      }
      if (i > j) {}
      for (;;)
      {
        localOptions.inPreferredConfig = Bitmap.Config.RGB_565;
        if (i > paramInt) {
          localOptions.inSampleSize = a(localOptions, -1, paramInt * paramInt);
        }
        localOptions.inJustDecodeBounds = false;
        Object localObject = null;
        try
        {
          paramString = BitmapFactory.decodeFile(paramString, localOptions);
          if (paramString == null)
          {
            return null;
            i = j;
          }
        }
        catch (OutOfMemoryError paramString)
        {
          for (;;)
          {
            paramString.printStackTrace();
            paramString = (String)localObject;
          }
          i = localOptions.outWidth;
          j = localOptions.outHeight;
          if (i > j) {}
          while (i > paramInt)
          {
            return a(paramString, paramInt);
            i = j;
          }
        }
      }
    }
    return paramString;
  }
  
  protected static final String a(Bitmap paramBitmap, String paramString1, String paramString2)
  {
    File localFile = new File(paramString1);
    if (!localFile.exists()) {
      localFile.mkdirs();
    }
    paramString1 = paramString1 + paramString2;
    paramString2 = new File(paramString1);
    if (paramString2.exists()) {
      paramString2.delete();
    }
    if (paramBitmap != null) {}
    try
    {
      paramString2 = new FileOutputStream(paramString2);
      paramBitmap.compress(Bitmap.CompressFormat.JPEG, 80, paramString2);
      paramString2.flush();
      paramString2.close();
      paramBitmap.recycle();
      return paramString1;
    }
    catch (FileNotFoundException paramBitmap)
    {
      paramBitmap.printStackTrace();
      return null;
    }
    catch (IOException paramBitmap)
    {
      for (;;)
      {
        paramBitmap.printStackTrace();
      }
    }
  }
  
  public static final void a(Context paramContext, String paramString, final c paramc)
  {
    f.b("openSDK_LOG.AsynScaleCompressImage", "scaleCompressImage");
    if (TextUtils.isEmpty(paramString))
    {
      paramc.a(1, null);
      return;
    }
    if (!k.b())
    {
      paramc.a(2, null);
      return;
    }
    new Thread(new Runnable()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        switch (paramAnonymousMessage.what)
        {
        default: 
          super.handleMessage(paramAnonymousMessage);
          return;
        case 101: 
          paramAnonymousMessage = (String)paramAnonymousMessage.obj;
          paramc.a(0, paramAnonymousMessage);
          return;
        }
        int i = paramAnonymousMessage.arg1;
        paramc.a(i, null);
      }
    }
    {
      public void run()
      {
        Object localObject1 = a.a(this.a, 140);
        if (localObject1 != null)
        {
          Object localObject2 = Environment.getExternalStorageDirectory() + "/tmp/";
          String str = k.f(this.a);
          str = "share2qq_temp" + str + ".jpg";
          if (!a.a(this.a, 140, 140)) {
            f.b("openSDK_LOG.AsynScaleCompressImage", "not out of bound,not compress!");
          }
          for (localObject1 = this.a;; localObject1 = a.a((Bitmap)localObject1, (String)localObject2, str))
          {
            f.b("openSDK_LOG.AsynScaleCompressImage", "-->destFilePath: " + (String)localObject1);
            if (localObject1 == null) {
              break;
            }
            localObject2 = this.b.obtainMessage(101);
            ((Message)localObject2).obj = localObject1;
            this.b.sendMessage((Message)localObject2);
            return;
            f.b("openSDK_LOG.AsynScaleCompressImage", "out of bound,compress!");
          }
        }
        localObject1 = this.b.obtainMessage(102);
        ((Message)localObject1).arg1 = 3;
        this.b.sendMessage((Message)localObject1);
      }
    }).start();
  }
  
  public static final void a(Context paramContext, ArrayList<String> paramArrayList, final c paramc)
  {
    f.b("openSDK_LOG.AsynScaleCompressImage", "batchScaleCompressImage");
    if (paramArrayList == null)
    {
      paramc.a(1, null);
      return;
    }
    new Thread(new Runnable()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        switch (paramAnonymousMessage.what)
        {
        default: 
          super.handleMessage(paramAnonymousMessage);
          return;
        }
        paramAnonymousMessage = paramAnonymousMessage.getData().getStringArrayList("images");
        paramc.a(0, paramAnonymousMessage);
      }
    }
    {
      public void run()
      {
        int i = 0;
        if (i < this.a.size())
        {
          localObject1 = (String)this.a.get(i);
          String str1;
          String str2;
          if ((!k.g((String)localObject1)) && (k.h((String)localObject1)))
          {
            localObject2 = a.a((String)localObject1, 10000);
            if (localObject2 != null)
            {
              str1 = Environment.getExternalStorageDirectory() + "/tmp/";
              str2 = k.f((String)localObject1);
              str2 = "share2qzone_temp" + str2 + ".jpg";
              if (a.a((String)localObject1, 640, 10000)) {
                break label148;
              }
              f.b("openSDK_LOG.AsynScaleCompressImage", "not out of bound,not compress!");
            }
          }
          for (;;)
          {
            if (localObject1 != null) {
              this.a.set(i, localObject1);
            }
            i += 1;
            break;
            label148:
            f.b("openSDK_LOG.AsynScaleCompressImage", "out of bound, compress!");
            localObject1 = a.a((Bitmap)localObject2, str1, str2);
          }
        }
        Object localObject1 = this.b.obtainMessage(101);
        Object localObject2 = new Bundle();
        ((Bundle)localObject2).putStringArrayList("images", this.a);
        ((Message)localObject1).setData((Bundle)localObject2);
        this.b.sendMessage((Message)localObject1);
      }
    }).start();
  }
  
  private static int b(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    double d1 = paramOptions.outWidth;
    double d2 = paramOptions.outHeight;
    int i;
    if (paramInt2 == -1)
    {
      i = 1;
      if (paramInt1 != -1) {
        break label60;
      }
    }
    label60:
    for (int j = 128;; j = (int)Math.min(Math.floor(d1 / paramInt1), Math.floor(d2 / paramInt1)))
    {
      if (j >= i) {
        break label84;
      }
      return i;
      i = (int)Math.ceil(Math.sqrt(d1 * d2 / paramInt2));
      break;
    }
    label84:
    if ((paramInt2 == -1) && (paramInt1 == -1)) {
      return 1;
    }
    if (paramInt1 == -1) {
      return i;
    }
    return j;
  }
  
  private static final boolean b(String paramString, int paramInt1, int paramInt2)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    try
    {
      BitmapFactory.decodeFile(paramString, localOptions);
      j = localOptions.outWidth;
      k = localOptions.outHeight;
      if ((localOptions.mCancel) || (localOptions.outWidth == -1) || (localOptions.outHeight == -1)) {
        return false;
      }
    }
    catch (OutOfMemoryError paramString)
    {
      int j;
      int k;
      for (;;)
      {
        paramString.printStackTrace();
      }
      int i;
      if (j > k)
      {
        i = j;
        if (j >= k) {
          break label159;
        }
      }
      for (;;)
      {
        f.b("openSDK_LOG.AsynScaleCompressImage", "longSide=" + i + "shortSide=" + j);
        localOptions.inPreferredConfig = Bitmap.Config.RGB_565;
        if ((i <= paramInt2) && (j <= paramInt1)) {
          break label166;
        }
        return true;
        i = k;
        break;
        label159:
        j = k;
      }
    }
    label166:
    return false;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\connect\share\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */