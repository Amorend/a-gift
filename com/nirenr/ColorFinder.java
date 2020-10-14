package com.nirenr;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ColorFinder
{
  private int a;
  private int b;
  private int[][] c;
  private float[][] d;
  private float e;
  
  public ColorFinder(Bitmap paramBitmap)
  {
    a(paramBitmap);
  }
  
  public ColorFinder(String paramString)
  {
    a(BitmapFactory.decodeFile(paramString));
  }
  
  private int a(int paramInt1, int paramInt2, int[][] paramArrayOfInt, int paramInt3, int paramInt4, int paramInt5)
  {
    int i = 0;
    while (i < this.b - paramInt2 - paramInt3)
    {
      int[] arrayOfInt = paramArrayOfInt[paramInt1];
      int j = paramInt2 + i;
      if (arrayOfInt[j] == 1)
      {
        int k = paramInt1 + paramInt5;
        if ((paramArrayOfInt[k][j] == 0) && (paramArrayOfInt[(k + paramInt4)][j] == 0))
        {
          i += 1;
          continue;
        }
      }
      if (i > paramInt3) {
        return i;
      }
      return -1;
    }
    return this.b - paramInt2 - paramInt3;
  }
  
  private void a(Bitmap paramBitmap)
  {
    this.a = paramBitmap.getWidth();
    this.b = paramBitmap.getHeight();
    int[] arrayOfInt = new int[this.a * this.b];
    paramBitmap.getPixels(arrayOfInt, 0, this.a, 0, 0, this.a, this.b);
    this.c = ((int[][])Array.newInstance(Integer.TYPE, new int[] { this.a, this.b }));
    int i = 0;
    while (i < this.b)
    {
      int j = 0;
      while (j < this.a)
      {
        this.c[j][i] = arrayOfInt[(this.a * i + j)];
        j += 1;
      }
      i += 1;
    }
  }
  
  public Point find(int paramInt)
  {
    int i = 0;
    while (i < this.b)
    {
      int j = 0;
      while (j < this.a)
      {
        if (this.c[j][i] == paramInt) {
          return new Point(j, i);
        }
        j += 1;
      }
      i += 1;
    }
    return new Point(-1, -1);
  }
  
  public Point find(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = 0;
    while (i < this.b)
    {
      int j = 0;
      while (j < this.a)
      {
        int k = this.c[j][i];
        if ((k << 8 >>> 24 == paramInt1) && (k << 16 >>> 24 == paramInt2) && (k << 24 >>> 24 == paramInt3)) {
          return new Point(j, i);
        }
        j += 1;
      }
      i += 1;
    }
    return new Point(-1, -1);
  }
  
  public Point find(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = 0;
    while (i < this.b)
    {
      int j = 0;
      while (j < this.a)
      {
        int n = this.c[j][i];
        int k = n << 8 >>> 24;
        int m = n << 16 >>> 24;
        n = n << 24 >>> 24;
        if ((k >= paramInt1 - paramInt4) && (k <= paramInt1 + paramInt4) && (m >= paramInt2 - paramInt4) && (m <= paramInt2 + paramInt4) && (n >= paramInt3 - paramInt4) && (n <= paramInt3 + paramInt4)) {
          return new Point(j, i);
        }
        j += 1;
      }
      i += 1;
    }
    return new Point(-1, -1);
  }
  
  public Point find(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    while (paramInt2 < paramInt4)
    {
      int i = paramInt1;
      while (i < paramInt3)
      {
        if (this.c[i][paramInt2] == paramInt5) {
          return new Point(i, paramInt2);
        }
        i += 1;
      }
      paramInt2 += 1;
    }
    return new Point(-1, -1);
  }
  
  public Point find(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
  {
    while (paramInt2 < paramInt4)
    {
      int i = paramInt1;
      while (i < paramInt3)
      {
        int j = this.c[i][paramInt2];
        if ((j << 8 >>> 24 == paramInt5) && (j << 16 >>> 24 == paramInt6) && (j << 24 >>> 24 == paramInt7)) {
          return new Point(i, paramInt2);
        }
        i += 1;
      }
      paramInt2 += 1;
    }
    return new Point(-1, -1);
  }
  
  public Point find(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8)
  {
    while (paramInt2 < paramInt4)
    {
      int i = paramInt1;
      while (i < paramInt3)
      {
        int m = this.c[i][paramInt2];
        int j = m << 8 >>> 24;
        int k = m << 16 >>> 24;
        m = m << 24 >>> 24;
        if ((j >= paramInt5 - paramInt8) && (j <= paramInt5 + paramInt8) && (k >= paramInt6 - paramInt8) && (k <= paramInt6 + paramInt8) && (m >= paramInt7 - paramInt8) && (m <= paramInt7 + paramInt8)) {
          return new Point(i, paramInt2);
        }
        i += 1;
      }
      paramInt2 += 1;
    }
    return new Point(-1, -1);
  }
  
  public Point find(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, ColorPoint[] paramArrayOfColorPoint)
  {
    int i = paramInt5 + paramInt8;
    int j = paramInt2;
    while (j < paramInt4)
    {
      int k = paramInt1;
      while (k < paramInt3)
      {
        int i1 = this.c[k][j];
        int m = i1 << 8 >>> 24;
        int n = i1 << 16 >>> 24;
        i1 = i1 << 24 >>> 24;
        if ((m >= paramInt5 - paramInt8) && (m <= i) && (n >= paramInt6 - paramInt8) && (n <= paramInt6 + paramInt8) && (i1 >= paramInt7 - paramInt8) && (i1 <= paramInt7 + paramInt8))
        {
          n = paramArrayOfColorPoint.length;
          m = 0;
          while (m < n)
          {
            if (!paramArrayOfColorPoint[m].check(this.c, paramInt1, paramInt2))
            {
              m = 0;
              break label175;
            }
            m += 1;
          }
          m = 1;
          label175:
          if (m != 0) {
            return new Point(k, j);
          }
        }
        k += 1;
      }
      j += 1;
    }
    return new Point(-1, -1);
  }
  
  public Point find(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int[][] paramArrayOfInt)
  {
    ColorPoint[] arrayOfColorPoint = new ColorPoint[paramArrayOfInt.length];
    int i = 0;
    while (i < paramArrayOfInt.length)
    {
      arrayOfColorPoint[i] = new ColorPoint(paramArrayOfInt[i]);
      i += 1;
    }
    return find(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, arrayOfColorPoint);
  }
  
  public Point find(Color paramColor)
  {
    return find(paramColor.red, paramColor.green, paramColor.blue);
  }
  
  public Point find(Color paramColor, int paramInt)
  {
    return find(paramColor.red, paramColor.green, paramColor.blue, paramInt);
  }
  
  public Point find(Point paramPoint1, Point paramPoint2, Color paramColor)
  {
    return find(paramPoint1.x, paramPoint1.y, paramPoint2.x, paramPoint2.y, paramColor.red, paramColor.green, paramColor.blue);
  }
  
  public Point find(Point paramPoint1, Point paramPoint2, Color paramColor, int paramInt)
  {
    return find(paramPoint1.x, paramPoint1.y, paramPoint2.x, paramPoint2.y, paramColor.red, paramColor.green, paramColor.blue, paramInt);
  }
  
  public ArrayList<Rect> findLine(float paramFloat, int paramInt)
  {
    return findLine(this.a / 2, 10, this.a - 10, this.b - paramInt * 16, paramFloat, paramInt * 8, paramInt * 4, paramInt);
  }
  
  public ArrayList<Rect> findLine(float paramFloat, int paramInt1, int paramInt2)
  {
    int i;
    int j;
    int k;
    if (this.b < this.a)
    {
      i = this.a / 2;
      j = 0;
      k = this.a - 10;
    }
    for (int m = this.b - paramInt1;; m = this.a)
    {
      return findLine(i, j, k, m, paramFloat, paramInt1, paramInt2 * 4, paramInt2);
      i = this.a / 2;
      j = this.a / 3;
      k = this.a - 10;
    }
  }
  
  public ArrayList<Rect> findLine(float paramFloat, int paramInt1, int paramInt2, int paramInt3)
  {
    int i;
    int j;
    int k;
    if (this.b < this.a)
    {
      i = this.a / 2;
      j = 0;
      k = this.a - 10;
    }
    for (int m = this.b - paramInt1;; m = this.a)
    {
      return findLine(i, j, k, m, paramFloat, paramInt1, paramInt2, paramInt3);
      i = this.a / 2;
      j = this.a / 3;
      k = this.a - 10;
    }
  }
  
  public ArrayList<Rect> findLine(int paramInt)
  {
    return findLine(this.a / 2, 10, this.a - 10, this.b - paramInt * 16, 0.5F, paramInt * 8, paramInt * 4, paramInt);
  }
  
  public ArrayList<Rect> findLine(int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat, int paramInt5, int paramInt6, int paramInt7)
  {
    int j;
    if (this.d == null)
    {
      this.d = ((float[][])Array.newInstance(Float.TYPE, new int[] { this.a, this.b }));
      localObject = new float[3];
      i = 0;
      f = 0.0F;
      while (i < this.b)
      {
        j = 0;
        while (j < this.a)
        {
          android.graphics.Color.colorToHSV(this.c[j][i], (float[])localObject);
          this.d[j][i] = localObject[2];
          f += localObject[2];
          j += 1;
        }
        i += 1;
      }
      this.e = (f / (this.a * this.b));
    }
    Object localObject = (int[][])Array.newInstance(Integer.TYPE, new int[] { this.a, this.b });
    float f = this.e;
    int i = 0;
    int k;
    while (i < this.b)
    {
      j = 0;
      while (j < this.a)
      {
        k = this.a;
        if (this.d[j][i] > f * paramFloat) {
          localObject[j][i] = 1;
        } else {
          localObject[j][i] = 0;
        }
        j += 1;
      }
      i += 1;
    }
    ArrayList localArrayList = new ArrayList();
    while (paramInt1 < paramInt3)
    {
      i = paramInt2;
      for (;;)
      {
        j = paramInt1;
        if (i >= paramInt4) {
          break;
        }
        k = a(paramInt1, i, (int[][])localObject, paramInt5, paramInt6, paramInt7);
        if (k > -1)
        {
          j = paramInt1 + paramInt7;
          localArrayList.add(new Rect(j, i, j, k + j));
          break;
        }
        i += 1;
      }
      paramInt1 = j + 1;
    }
    return localArrayList;
  }
  
  public int[][] getPixels()
  {
    return this.c;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\nirenr\ColorFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */