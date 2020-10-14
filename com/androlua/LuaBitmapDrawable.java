package com.androlua;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Movie;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import com.androlua.util.AsyncTaskX;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LuaBitmapDrawable
  extends Drawable
  implements Runnable
{
  public static final int CENTER = 5;
  public static final int CENTER_CROP = 6;
  public static final int CENTER_INSIDE = 7;
  public static final int FIT_CENTER = 3;
  public static final int FIT_END = 4;
  public static final int FIT_START = 2;
  public static final int FIT_XY = 1;
  public static final int MATRIX = 0;
  private static long p = 604800000L;
  private final LuaContext a;
  private int b;
  private long c;
  private int d;
  private Movie e;
  private LoadingDrawable f;
  private BitmapDrawable g;
  private NineBitmapDrawable h;
  private ColorFilter i;
  private int j;
  private int k = 3;
  private GifDecoder l;
  private GifDecoder m;
  private GifDecoder.GifFrame n;
  private int o;
  
  public LuaBitmapDrawable(LuaContext paramLuaContext, String paramString)
  {
    this.a = paramLuaContext;
    this.f = new LoadingDrawable(paramLuaContext.getContext());
    if ((!paramString.toLowerCase().startsWith("http://")) && (!paramString.toLowerCase().startsWith("https://")))
    {
      String str = paramString;
      if (!paramString.startsWith("/")) {
        str = paramLuaContext.getLuaPath(paramString);
      }
      a(str);
      return;
    }
    a(paramLuaContext, paramString);
  }
  
  private void a(final LuaContext paramLuaContext, final String paramString)
  {
    new AsyncTaskX()
    {
      protected String a(String... paramAnonymousVarArgs)
      {
        try
        {
          paramAnonymousVarArgs = LuaBitmapDrawable.getHttpBitmap(paramLuaContext, paramString);
          return paramAnonymousVarArgs;
        }
        catch (Exception paramAnonymousVarArgs)
        {
          paramAnonymousVarArgs.printStackTrace();
        }
        return "";
      }
      
      protected void a(String paramAnonymousString)
      {
        LuaBitmapDrawable.a(LuaBitmapDrawable.this, paramAnonymousString);
      }
    }.execute(new String[0]);
  }
  
  private void a(final String paramString)
  {
    try
    {
      this.l = new GifDecoder(new FileInputStream(paramString), new GifDecoder.GifAction()
      {
        public void parseOk(boolean paramAnonymousBoolean, int paramAnonymousInt)
        {
          if ((!paramAnonymousBoolean) && (paramAnonymousInt < 0))
          {
            LuaBitmapDrawable.b(LuaBitmapDrawable.this, paramString);
            return;
          }
          if ((paramAnonymousBoolean) && (LuaBitmapDrawable.a(LuaBitmapDrawable.this) == null) && (LuaBitmapDrawable.b(LuaBitmapDrawable.this).getFrameCount() > 1)) {
            LuaBitmapDrawable.a(LuaBitmapDrawable.this, LuaBitmapDrawable.b(LuaBitmapDrawable.this));
          }
        }
      });
      this.l.start();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      b(paramString);
    }
  }
  
  private void b(String paramString)
  {
    if (paramString.isEmpty())
    {
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          LuaBitmapDrawable.c(LuaBitmapDrawable.this).setState(-1);
        }
      }, 1000L);
      invalidateSelf();
      return;
    }
    if (this.e != null)
    {
      this.b = this.e.duration();
      if (this.b == 0) {
        this.b = 1000;
      }
    }
    else
    {
      for (;;)
      {
        try
        {
          this.h = new NineBitmapDrawable(paramString);
        }
        catch (Exception localException)
        {
          continue;
        }
        try
        {
          this.g = new BitmapDrawable(LuaBitmap.getLocalBitmap(this.a, paramString));
        }
        catch (Exception paramString)
        {
          paramString.printStackTrace();
        }
      }
    }
    if ((this.e == null) && (this.g == null) && (this.h == null)) {
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          LuaBitmapDrawable.c(LuaBitmapDrawable.this).setState(-1);
        }
      }, 1000L);
    }
    invalidateSelf();
  }
  
  public static long getCacheTime()
  {
    return p;
  }
  
  public static String getHttpBitmap(LuaContext paramLuaContext, String paramString)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramLuaContext.getLuaExtDir("cache"));
    ((StringBuilder)localObject).append("/");
    ((StringBuilder)localObject).append(paramString.hashCode());
    paramLuaContext = ((StringBuilder)localObject).toString();
    localObject = new File(paramLuaContext);
    if ((((File)localObject).exists()) && (System.currentTimeMillis() - ((File)localObject).lastModified() < p)) {
      return paramLuaContext;
    }
    new File(paramLuaContext).delete();
    paramString = (HttpURLConnection)new URL(paramString).openConnection();
    paramString.setConnectTimeout(120000);
    paramString.setDoInput(true);
    paramString.connect();
    paramString = paramString.getInputStream();
    localObject = new FileOutputStream(paramLuaContext);
    if (!LuaUtil.copyFile(paramString, (OutputStream)localObject))
    {
      ((FileOutputStream)localObject).close();
      paramString.close();
      new File(paramLuaContext).delete();
      throw new RuntimeException("LoadHttpBitmap Error.");
    }
    ((FileOutputStream)localObject).close();
    paramString.close();
    return paramLuaContext;
  }
  
  public static void setCacheTime(long paramLong)
  {
    p = paramLong;
  }
  
  public void draw(Canvas paramCanvas)
  {
    paramCanvas.drawColor(this.j);
    long l1;
    Object localObject;
    int i1;
    int i4;
    float f2;
    float f1;
    float f4;
    float f3;
    int i3;
    int i2;
    if (this.m != null)
    {
      l1 = System.currentTimeMillis();
      if ((this.c != 0L) && (this.n != null)) {}
      while (l1 - this.c > this.o)
      {
        this.n = this.m.next();
        this.o = this.n.delay;
        this.c += this.o;
        continue;
        this.n = this.m.next();
        this.o = this.n.delay;
        this.c = l1;
      }
      if (this.n != null)
      {
        localObject = getBounds();
        BitmapDrawable localBitmapDrawable = new BitmapDrawable(this.n.image);
        i1 = localBitmapDrawable.getIntrinsicWidth();
        i4 = localBitmapDrawable.getIntrinsicHeight();
        if (this.k == 1)
        {
          f2 = ((Rect)localObject).right - ((Rect)localObject).left;
          f1 = i1;
          f2 /= f1;
          f4 = ((Rect)localObject).bottom - ((Rect)localObject).top;
          f3 = i4;
          f4 /= f3;
          i1 = (int)(f1 * f2);
        }
        for (f1 = f3 * f4;; f1 *= f3)
        {
          i3 = (int)f1;
          i2 = i1;
          break;
          i2 = i1;
          i3 = i4;
          if (this.k == 0) {
            break;
          }
          f2 = ((Rect)localObject).bottom - ((Rect)localObject).top;
          f1 = i4;
          f3 = f2 / f1;
          f4 = ((Rect)localObject).right - ((Rect)localObject).left;
          f2 = i1;
          f3 = Math.min(f3, f4 / f2);
          i1 = (int)(f2 * f3);
        }
        i4 = ((Rect)localObject).left;
        i1 = ((Rect)localObject).top;
        switch (this.k)
        {
        default: 
          break;
        case 4: 
          i1 = ((Rect)localObject).bottom - ((Rect)localObject).top - i3;
          break;
        case 3: 
          i4 = (((Rect)localObject).right - ((Rect)localObject).left - i2) / 2;
          i1 = (((Rect)localObject).bottom - ((Rect)localObject).top - i3) / 2;
        }
        localBitmapDrawable.setBounds(new Rect(i4, i1, i2 + i4, i3 + i1));
        localBitmapDrawable.draw(paramCanvas);
      }
    }
    for (;;)
    {
      invalidateSelf();
      return;
      if (this.e != null)
      {
        l1 = System.currentTimeMillis();
        if (this.c == 0L) {
          this.c = l1;
        }
        this.d = ((int)((l1 - this.c) % this.b));
        this.e.setTime(this.d);
        localObject = getBounds();
        paramCanvas.save();
        i3 = this.e.width();
        i4 = this.e.height();
        f1 = 1.0F;
        float f5;
        if (this.k == 1)
        {
          f3 = ((Rect)localObject).right - ((Rect)localObject).left;
          f2 = i3;
          f3 /= f2;
          f5 = ((Rect)localObject).bottom - ((Rect)localObject).top;
          f4 = i4;
          f5 /= f4;
          paramCanvas.scale(f3, f5);
          i1 = (int)(f2 * f3);
        }
        for (f2 = f4 * f5;; f2 *= f1)
        {
          i2 = (int)f2;
          break;
          i1 = i3;
          i2 = i4;
          if (this.k == 0) {
            break;
          }
          f1 = ((Rect)localObject).bottom - ((Rect)localObject).top;
          f2 = i4;
          f1 /= f2;
          f4 = ((Rect)localObject).right - ((Rect)localObject).left;
          f3 = i3;
          f1 = Math.min(f1, f4 / f3);
          paramCanvas.scale(f1, f1);
          i1 = (int)(f3 * f1);
        }
        i3 = ((Rect)localObject).left;
        i4 = ((Rect)localObject).top;
        switch (this.k)
        {
        default: 
          i2 = i3;
          i1 = i4;
          break;
        case 4: 
          i1 = (int)(((Rect)localObject).bottom - ((Rect)localObject).top - i2 / f1);
          i2 = i3;
          break;
        case 3: 
          i1 = (int)((((Rect)localObject).right - ((Rect)localObject).left - i1) / f1 / 2.0F);
          i3 = (int)((((Rect)localObject).bottom - ((Rect)localObject).top - i2) / f1 / 2.0F);
          i2 = i1;
          i1 = i3;
        }
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(this.e);
        ((StringBuilder)localObject).append("");
        Log.i("LuaBitmapDrawable4", ((StringBuilder)localObject).toString());
        this.e.draw(paramCanvas, i2, i1, new Paint());
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(this.e);
        ((StringBuilder)localObject).append("");
        Log.i("LuaBitmapDrawable6", ((StringBuilder)localObject).toString());
        paramCanvas.restore();
      }
      else
      {
        if (this.g != null)
        {
          localObject = getBounds();
          i1 = this.g.getIntrinsicWidth();
          i4 = this.g.getIntrinsicHeight();
          if (this.k == 1)
          {
            f2 = ((Rect)localObject).right - ((Rect)localObject).left;
            f1 = i1;
            f2 /= f1;
            f4 = ((Rect)localObject).bottom - ((Rect)localObject).top;
            f3 = i4;
            f4 /= f3;
            i1 = (int)(f1 * f2);
          }
          for (f1 = f3 * f4;; f1 *= f3)
          {
            i3 = (int)f1;
            i2 = i1;
            break;
            i2 = i1;
            i3 = i4;
            if (this.k == 0) {
              break;
            }
            f2 = ((Rect)localObject).bottom - ((Rect)localObject).top;
            f1 = i4;
            f3 = f2 / f1;
            f4 = ((Rect)localObject).right - ((Rect)localObject).left;
            f2 = i1;
            f3 = Math.min(f3, f4 / f2);
            i1 = (int)(f2 * f3);
          }
          i4 = ((Rect)localObject).left;
          i1 = ((Rect)localObject).top;
          switch (this.k)
          {
          default: 
            break;
          case 4: 
            i1 = ((Rect)localObject).bottom - ((Rect)localObject).top - i3;
            break;
          case 3: 
            i4 = (((Rect)localObject).right - ((Rect)localObject).left - i2) / 2;
            i1 = (((Rect)localObject).bottom - ((Rect)localObject).top - i3) / 2;
          }
          this.g.setBounds(new Rect(i4, i1, i2 + i4, i3 + i1));
          this.g.draw(paramCanvas);
          return;
        }
        if (this.h != null)
        {
          this.h.setBounds(getBounds());
          this.h.draw(paramCanvas);
          return;
        }
        if (this.f == null) {
          break;
        }
        this.f.setBounds(getBounds());
        this.f.draw(paramCanvas);
      }
    }
  }
  
  public int getIntrinsicHeight()
  {
    if (this.e != null) {
      return this.e.height();
    }
    if (this.g != null) {
      this.g.getIntrinsicHeight();
    } else if (this.h != null) {
      this.h.getIntrinsicHeight();
    }
    return super.getIntrinsicHeight();
  }
  
  public int getIntrinsicWidth()
  {
    if (this.e != null) {
      return this.e.width();
    }
    if (this.g != null) {
      this.g.getIntrinsicWidth();
    } else if (this.h != null) {
      this.h.getIntrinsicWidth();
    }
    return super.getIntrinsicWidth();
  }
  
  public int getOpacity()
  {
    return 0;
  }
  
  public void run()
  {
    invalidateSelf();
  }
  
  public void setAlpha(int paramInt) {}
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    this.i = paramColorFilter;
  }
  
  public void setFillColor(int paramInt)
  {
    if (paramInt == this.j) {
      return;
    }
    this.j = paramInt;
  }
  
  public void setScaleType(int paramInt)
  {
    if (this.k != paramInt)
    {
      this.k = paramInt;
      invalidateSelf();
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaBitmapDrawable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */