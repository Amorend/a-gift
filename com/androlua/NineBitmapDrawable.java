package com.androlua;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class NineBitmapDrawable
  extends Drawable
{
  private Paint a = new Paint();
  private Bitmap b;
  private int c;
  private int d;
  private int e;
  private int f;
  private Rect g;
  private Rect h;
  private Rect i;
  private Rect j;
  private Rect k;
  private Rect l;
  private Rect m;
  private Rect n;
  private Rect o;
  
  public NineBitmapDrawable(Bitmap paramBitmap)
  {
    int i3 = paramBitmap.getWidth();
    int i5 = paramBitmap.getHeight();
    int i1 = 0;
    while (i1 < i3)
    {
      if (paramBitmap.getPixel(i1, 0) == -16777216) {
        break label58;
      }
      i1 += 1;
    }
    i1 = 0;
    label58:
    if ((i1 != 0) && (i1 != i3 - 1))
    {
      int i2 = i1;
      while (i2 < i3)
      {
        if (paramBitmap.getPixel(i2, 0) != -16777216)
        {
          i3 -= i2;
          break label111;
        }
        i2 += 1;
      }
      i3 = 0;
      label111:
      if ((i3 != 0) && (i3 != 1))
      {
        i2 = 0;
        while (i2 < i5)
        {
          if (paramBitmap.getPixel(0, i2) == -16777216) {
            break label156;
          }
          i2 += 1;
        }
        i2 = 0;
        label156:
        if ((i2 != 0) && (i2 != i5 - 1))
        {
          int i4 = i2;
          while (i4 < i5)
          {
            if (paramBitmap.getPixel(0, i4) != -16777216)
            {
              i4 = i5 - i4;
              break label215;
            }
            i4 += 1;
          }
          i4 = 0;
          label215:
          if ((i4 != 0) && (i4 != 1))
          {
            a(paramBitmap, i1, i2, i3, i4);
            return;
          }
          throw new IllegalArgumentException("not found y2");
        }
        throw new IllegalArgumentException("not found y1");
      }
      throw new IllegalArgumentException("not found x2");
    }
    throw new IllegalArgumentException("not found x1");
  }
  
  public NineBitmapDrawable(Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    a(paramBitmap, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public NineBitmapDrawable(String paramString)
  {
    this(LuaBitmap.getLocalBitmap(paramString));
  }
  
  private void a(Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.b = paramBitmap;
    int i2 = paramBitmap.getWidth();
    int i1 = paramBitmap.getHeight();
    this.c = paramInt1;
    this.d = paramInt2;
    this.e = paramInt3;
    this.f = paramInt4;
    paramInt3 = i2 - paramInt3;
    paramInt4 = i1 - paramInt4;
    this.g = new Rect(1, 1, paramInt1, paramInt2);
    this.h = new Rect(paramInt1, 1, paramInt3, paramInt2);
    i2 -= 1;
    this.i = new Rect(paramInt3, 1, i2, paramInt2);
    this.j = new Rect(1, paramInt2, paramInt1, paramInt4);
    this.k = new Rect(paramInt1, paramInt2, paramInt3, paramInt4);
    this.l = new Rect(paramInt3, paramInt2, i2, paramInt4);
    paramInt2 = i1 - 1;
    this.m = new Rect(1, paramInt4, paramInt1, paramInt2);
    this.n = new Rect(paramInt1, paramInt4, paramInt3, paramInt2);
    this.o = new Rect(paramInt3, paramInt4, i2, paramInt2);
  }
  
  public void draw(Canvas paramCanvas)
  {
    Rect localRect1 = getBounds();
    int i1 = localRect1.right;
    int i2 = localRect1.bottom;
    localRect1 = new Rect(0, 0, this.c, this.d);
    Rect localRect2 = new Rect(this.c, 0, i1 - this.e, this.d);
    Rect localRect3 = new Rect(i1 - this.e, 0, i1, this.d);
    Rect localRect4 = new Rect(0, this.d, this.c, i2 - this.f);
    Rect localRect5 = new Rect(this.c, this.d, i1 - this.e, i2 - this.f);
    Rect localRect6 = new Rect(i1 - this.e, this.d, i1, i2 - this.f);
    Rect localRect7 = new Rect(0, i2 - this.f, this.c, i2);
    Rect localRect8 = new Rect(this.c, i2 - this.f, i1 - this.e, i2);
    Rect localRect9 = new Rect(i1 - this.e, i2 - this.f, i1, i2);
    paramCanvas.drawBitmap(this.b, this.g, localRect1, this.a);
    paramCanvas.drawBitmap(this.b, this.h, localRect2, this.a);
    paramCanvas.drawBitmap(this.b, this.i, localRect3, this.a);
    paramCanvas.drawBitmap(this.b, this.j, localRect4, this.a);
    paramCanvas.drawBitmap(this.b, this.k, localRect5, this.a);
    paramCanvas.drawBitmap(this.b, this.l, localRect6, this.a);
    paramCanvas.drawBitmap(this.b, this.m, localRect7, this.a);
    paramCanvas.drawBitmap(this.b, this.n, localRect8, this.a);
    paramCanvas.drawBitmap(this.b, this.o, localRect9, this.a);
  }
  
  public int getOpacity()
  {
    return 0;
  }
  
  public void setAlpha(int paramInt)
  {
    this.a.setAlpha(paramInt);
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    this.a.setColorFilter(paramColorFilter);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\NineBitmapDrawable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */