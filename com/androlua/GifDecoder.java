package com.androlua;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class GifDecoder
  extends Thread
{
  public static final int STATUS_FINISH = -1;
  public static final int STATUS_FORMAT_ERROR = 1;
  public static final int STATUS_OPEN_ERROR = 2;
  public static final int STATUS_PARSING = 0;
  private boolean A = false;
  private byte[] B = new byte['Ā'];
  private int C = 0;
  private int D = 0;
  private int E = 0;
  private boolean F = false;
  private int G = 0;
  private int H;
  private short[] I;
  private byte[] J;
  private byte[] K;
  private byte[] L;
  private GifFrame M;
  private int N;
  private GifAction O = null;
  private byte[] P = null;
  private InputStream a;
  private int b;
  private boolean c;
  private int d;
  private int e = 1;
  private int[] f;
  private int[] g;
  private int[] h;
  public int height;
  private int i;
  private int j;
  private int k;
  private int l;
  private boolean m;
  private boolean n;
  private int o;
  private int p;
  private int q;
  private int r;
  private int s;
  private int t;
  private int u;
  private int v;
  private int w;
  public int width;
  private Bitmap x;
  private Bitmap y;
  private GifFrame z = null;
  
  public GifDecoder(InputStream paramInputStream, GifAction paramGifAction)
  {
    this.a = paramInputStream;
    this.O = paramGifAction;
  }
  
  public GifDecoder(String paramString, GifAction paramGifAction)
  {
    this.a = new FileInputStream(paramString);
    this.O = paramGifAction;
  }
  
  public GifDecoder(byte[] paramArrayOfByte, GifAction paramGifAction)
  {
    this.P = paramArrayOfByte;
    this.O = paramGifAction;
  }
  
  private void a()
  {
    int[] arrayOfInt = new int[this.width * this.height];
    int i1 = this.E;
    int i4 = 0;
    int i2;
    if (i1 > 0)
    {
      if (this.E == 3)
      {
        i1 = this.N - 2;
        if (i1 > 0) {}
        for (Bitmap localBitmap = getFrameImage(i1 - 1);; localBitmap = null)
        {
          this.y = localBitmap;
          break;
        }
      }
      if (this.y != null)
      {
        this.y.getPixels(arrayOfInt, 0, this.width, 0, 0, this.width, this.height);
        if (this.E == 2)
        {
          if (!this.F) {
            i2 = this.k;
          } else {
            i2 = 0;
          }
          i3 = 0;
          while (i3 < this.w)
          {
            i5 = (this.u + i3) * this.width + this.t;
            i6 = this.v;
            i1 = i5;
            while (i1 < i6 + i5)
            {
              arrayOfInt[i1] = i2;
              i1 += 1;
            }
            i3 += 1;
          }
        }
      }
    }
    int i3 = 0;
    int i6 = 1;
    for (int i5 = 8; i4 < this.s; i5 = i2)
    {
      int i7;
      if (this.n)
      {
        i1 = i3;
        i7 = i6;
        i2 = i5;
        if (i3 >= this.s)
        {
          i7 = i6 + 1;
          switch (i7)
          {
          default: 
            i1 = i3;
            i2 = i5;
            break;
          case 4: 
            i1 = 1;
            i2 = 2;
            break;
          case 3: 
            i1 = 2;
            i2 = 4;
            break;
          case 2: 
            i1 = 4;
            i2 = i5;
          }
        }
        i5 = i1 + i2;
        i3 = i1;
        i6 = i7;
        i1 = i5;
      }
      else
      {
        i1 = i3;
        i3 = i4;
        i2 = i5;
      }
      i3 += this.q;
      if (i3 < this.height)
      {
        int i8 = i3 * this.width;
        i7 = this.p + i8;
        i5 = this.r + i7;
        i3 = i5;
        if (this.width + i8 < i5) {
          i3 = this.width + i8;
        }
        i5 = this.r * i4;
        while (i7 < i3)
        {
          i8 = this.L[i5];
          i8 = this.h[(i8 & 0xFF)];
          if (i8 != 0) {
            arrayOfInt[i7] = i8;
          }
          i7 += 1;
          i5 += 1;
        }
      }
      i4 += 1;
      i3 = i1;
    }
    this.x = Bitmap.createBitmap(arrayOfInt, this.width, this.height, Bitmap.Config.ARGB_4444);
  }
  
  private int[] a(int paramInt)
  {
    int i2 = paramInt * 3;
    byte[] arrayOfByte = new byte[i2];
    int i3 = 0;
    try
    {
      i1 = this.a.read(arrayOfByte);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      i1 = 0;
    }
    if (i1 < i2)
    {
      this.b = 1;
      return null;
    }
    int[] arrayOfInt = new int['Ā'];
    i2 = 0;
    int i1 = i3;
    while (i1 < paramInt)
    {
      i3 = i2 + 1;
      i2 = arrayOfByte[i2];
      int i4 = i3 + 1;
      arrayOfInt[i1] = ((i2 & 0xFF) << 16 | 0xFF000000 | (arrayOfByte[i3] & 0xFF) << 8 | arrayOfByte[i4] & 0xFF);
      i2 = i4 + 1;
      i1 += 1;
    }
    return arrayOfInt;
  }
  
  private int b()
  {
    this.a = new ByteArrayInputStream(this.P);
    this.P = null;
    return c();
  }
  
  private int c()
  {
    f();
    if (this.a != null)
    {
      k();
      if (!e())
      {
        i();
        if (this.N < 0)
        {
          this.b = 1;
          this.O.parseOk(false, -1);
        }
        else
        {
          this.b = -1;
          this.O.parseOk(true, -1);
        }
      }
      try
      {
        this.a.close();
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    else
    {
      this.b = 2;
      this.O.parseOk(false, -1);
    }
    return this.b;
  }
  
  private void d()
  {
    int i22 = this.r * this.s;
    if ((this.L == null) || (this.L.length < i22)) {
      this.L = new byte[i22];
    }
    if (this.I == null) {
      this.I = new short['က'];
    }
    if (this.J == null) {
      this.J = new byte['က'];
    }
    if (this.K == null) {
      this.K = new byte['ခ'];
    }
    int i2 = g();
    int i21 = 1 << i2;
    int i16 = i21 + 2;
    i2 += 1;
    int i17 = (1 << i2) - 1;
    int i3 = 0;
    while (i3 < i21)
    {
      this.I[i3] = 0;
      this.J[i3] = ((byte)i3);
      i3 += 1;
    }
    int i4 = i2;
    int i13 = i16;
    i3 = i17;
    int i5 = -1;
    int i18 = 0;
    int i6 = 0;
    int i8 = 0;
    int i11 = 0;
    int i9 = 0;
    int i12 = 0;
    int i14 = 0;
    int i10 = 0;
    int i7 = i21;
    label636:
    while (i18 < i22)
    {
      int i15;
      if (i6 == 0)
      {
        if (i8 < i4)
        {
          i15 = i11;
          if (i11 == 0)
          {
            i15 = h();
            if (i15 <= 0) {
              break;
            }
            i12 = 0;
          }
          i9 += ((this.B[i12] & 0xFF) << i8);
          i8 += 8;
          i12 += 1;
          i11 = i15 - 1;
          continue;
        }
        i15 = i9 & i3;
        i9 >>= i4;
        int i19 = i8 - i4;
        if ((i15 > i13) || (i15 == i21 + 1)) {
          break;
        }
        if (i15 == i7)
        {
          i4 = i2;
          i13 = i16;
          i3 = i17;
          i5 = -1;
          i8 = i19;
          break label636;
        }
        if (i5 == -1)
        {
          this.K[i6] = this.J[i15];
          i5 = i15;
          i14 = i5;
          i6 += 1;
          i8 = i19;
          break label636;
        }
        if (i15 == i13)
        {
          arrayOfByte = this.K;
          i20 = i6 + 1;
          arrayOfByte[i6] = ((byte)i14);
          i8 = i5;
          i6 = i20;
        }
        else
        {
          i8 = i15;
        }
        while (i8 > i7)
        {
          this.K[i6] = this.J[i8];
          i8 = this.I[i8];
          i6 += 1;
        }
        int i20 = i7;
        i14 = this.J[i8] & 0xFF;
        if (i13 >= 4096) {
          break;
        }
        byte[] arrayOfByte = this.K;
        i8 = i6 + 1;
        int i1 = (byte)i14;
        arrayOfByte[i6] = i1;
        this.I[i13] = ((short)i5);
        this.J[i13] = i1;
        i13 += 1;
        if ((i13 & i3) == 0)
        {
          i7 = i4;
          i6 = i3;
          if (i13 < 4096)
          {
            i7 = i4 + 1;
            i6 = i3 + i13;
          }
        }
        else
        {
          i6 = i3;
          i7 = i4;
        }
        i5 = i15;
        i15 = i8;
        i8 = i19;
        i4 = i7;
        i3 = i6;
        i7 = i20;
      }
      else
      {
        i15 = i6;
      }
      i6 = i15 - 1;
      this.L[i10] = this.K[i6];
      i18 += 1;
      i10 += 1;
    }
    while (i10 < i22)
    {
      this.L[i10] = 0;
      i10 += 1;
    }
  }
  
  private boolean e()
  {
    return this.b != 0;
  }
  
  private void f()
  {
    this.b = 0;
    this.N = 0;
    this.M = null;
    this.f = null;
    this.g = null;
  }
  
  private int g()
  {
    try
    {
      int i1 = this.a.read();
      return i1;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    this.b = 1;
    return 0;
  }
  
  private int h()
  {
    this.C = g();
    int i3 = this.C;
    i2 = 0;
    int i1 = 0;
    if (i3 > 0) {
      try
      {
        while (i1 < this.C)
        {
          i2 = this.a.read(this.B, i1, this.C - i1);
          if (i2 == -1) {
            break;
          }
          i1 += i2;
        }
        return i2;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        i2 = i1;
        if (i1 < this.C)
        {
          this.b = 1;
          i2 = i1;
        }
      }
    }
  }
  
  private void i()
  {
    int i1 = 0;
    while ((i1 == 0) && (!e()))
    {
      int i2 = g();
      if (i2 != 0) {
        if (i2 != 33)
        {
          if (i2 != 44)
          {
            if (i2 != 59) {
              this.b = 1;
            } else {
              i1 = 1;
            }
          }
          else {
            l();
          }
        }
        else
        {
          i2 = g();
          if (i2 != 249)
          {
            if (i2 != 255) {}
            String str;
            do
            {
              q();
              break;
              h();
              str = "";
              i2 = 0;
              while (i2 < 11)
              {
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append(str);
                localStringBuilder.append((char)this.B[i2]);
                str = localStringBuilder.toString();
                i2 += 1;
              }
            } while (!str.equals("NETSCAPE2.0"));
            n();
          }
          else
          {
            j();
          }
        }
      }
    }
  }
  
  private void j()
  {
    g();
    int i1 = g();
    this.D = ((i1 & 0x1C) >> 2);
    int i2 = this.D;
    boolean bool = true;
    if (i2 == 0) {
      this.D = 1;
    }
    if ((i1 & 0x1) == 0) {
      bool = false;
    }
    this.F = bool;
    this.G = (o() * 10);
    this.H = g();
    g();
  }
  
  private void k()
  {
    String str = "";
    int i1 = 0;
    while (i1 < 6)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append((char)g());
      str = localStringBuilder.toString();
      i1 += 1;
    }
    if (!str.startsWith("GIF"))
    {
      this.b = 1;
      return;
    }
    m();
    if ((this.c) && (!e()))
    {
      this.f = a(this.d);
      this.j = this.f[this.i];
    }
  }
  
  private void l()
  {
    this.p = o();
    this.q = o();
    this.r = o();
    this.s = o();
    int i1 = g();
    boolean bool;
    if ((i1 & 0x80) != 0) {
      bool = true;
    } else {
      bool = false;
    }
    this.m = bool;
    if ((i1 & 0x40) != 0) {
      bool = true;
    } else {
      bool = false;
    }
    this.n = bool;
    this.o = (2 << (i1 & 0x7));
    if (this.m)
    {
      this.g = a(this.o);
      this.h = this.g;
    }
    else
    {
      this.h = this.f;
      if (this.i == this.H) {
        this.j = 0;
      }
    }
    if (this.F)
    {
      i1 = this.h[this.H];
      this.h[this.H] = 0;
    }
    else
    {
      i1 = 0;
    }
    if (this.h == null) {
      this.b = 1;
    }
    if (e()) {
      return;
    }
    d();
    q();
    if (e()) {
      return;
    }
    this.N += 1;
    this.x = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_4444);
    a();
    if (this.M == null)
    {
      this.M = new GifFrame(this.x, this.G);
      this.z = this.M;
    }
    else
    {
      for (GifFrame localGifFrame = this.M; localGifFrame.nextFrame != null; localGifFrame = localGifFrame.nextFrame) {}
      localGifFrame.nextFrame = new GifFrame(this.x, this.G);
    }
    if (this.F) {
      this.h[this.H] = i1;
    }
    p();
    this.O.parseOk(true, this.N);
  }
  
  private void m()
  {
    this.width = o();
    this.height = o();
    int i1 = g();
    boolean bool;
    if ((i1 & 0x80) != 0) {
      bool = true;
    } else {
      bool = false;
    }
    this.c = bool;
    this.d = (2 << (i1 & 0x7));
    this.i = g();
    this.l = g();
  }
  
  private void n()
  {
    do
    {
      h();
      if (this.B[0] == 1) {
        this.e = (this.B[1] & 0xFF | (this.B[2] & 0xFF) << 8);
      }
    } while ((this.C > 0) && (!e()));
  }
  
  private int o()
  {
    return g() | g() << 8;
  }
  
  private void p()
  {
    this.E = this.D;
    this.t = this.p;
    this.u = this.q;
    this.v = this.r;
    this.w = this.s;
    this.y = this.x;
    this.k = this.j;
    this.D = 0;
    this.F = false;
    this.G = 0;
    this.g = null;
  }
  
  private void q()
  {
    do
    {
      h();
    } while ((this.C > 0) && (!e()));
  }
  
  public void free()
  {
    for (;;)
    {
      GifFrame localGifFrame = this.M;
      if (localGifFrame == null) {
        break;
      }
      localGifFrame.image = null;
      this.M = this.M.nextFrame;
    }
    if (this.a != null) {}
    try
    {
      this.a.close();
      this.a = null;
      this.P = null;
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public GifFrame getCurrentFrame()
  {
    return this.z;
  }
  
  public int getDelay(int paramInt)
  {
    this.G = -1;
    if ((paramInt >= 0) && (paramInt < this.N))
    {
      GifFrame localGifFrame = getFrame(paramInt);
      if (localGifFrame != null) {
        this.G = localGifFrame.delay;
      }
    }
    return this.G;
  }
  
  public int[] getDelays()
  {
    GifFrame localGifFrame = this.M;
    int[] arrayOfInt = new int[this.N];
    int i1 = 0;
    while ((localGifFrame != null) && (i1 < this.N))
    {
      arrayOfInt[i1] = localGifFrame.delay;
      localGifFrame = localGifFrame.nextFrame;
      i1 += 1;
    }
    return arrayOfInt;
  }
  
  public GifFrame getFrame(int paramInt)
  {
    GifFrame localGifFrame = this.M;
    int i1 = 0;
    while (localGifFrame != null)
    {
      if (i1 == paramInt) {
        return localGifFrame;
      }
      localGifFrame = localGifFrame.nextFrame;
      i1 += 1;
    }
    return null;
  }
  
  public int getFrameCount()
  {
    return this.N;
  }
  
  public Bitmap getFrameImage(int paramInt)
  {
    GifFrame localGifFrame = getFrame(paramInt);
    if (localGifFrame == null) {
      return null;
    }
    return localGifFrame.image;
  }
  
  public Bitmap getImage()
  {
    return getFrameImage(0);
  }
  
  public int getLoopCount()
  {
    return this.e;
  }
  
  public int getStatus()
  {
    return this.b;
  }
  
  public GifFrame next()
  {
    if (!this.A)
    {
      this.A = true;
      return this.M;
    }
    if (this.b == 0)
    {
      if (this.z.nextFrame == null) {}
    }
    else {
      for (GifFrame localGifFrame = this.z.nextFrame;; localGifFrame = this.M)
      {
        this.z = localGifFrame;
        break;
        this.z = this.z.nextFrame;
        if (this.z != null) {
          break;
        }
      }
    }
    return this.z;
  }
  
  public boolean parseOk()
  {
    return this.b == -1;
  }
  
  public void reset()
  {
    this.z = this.M;
  }
  
  public void run()
  {
    if (this.a != null)
    {
      c();
      return;
    }
    if (this.P != null) {
      b();
    }
  }
  
  public static abstract interface GifAction
  {
    public abstract void parseOk(boolean paramBoolean, int paramInt);
  }
  
  public static class GifFrame
  {
    public int delay;
    public Bitmap image;
    public GifFrame nextFrame = null;
    
    public GifFrame(Bitmap paramBitmap, int paramInt)
    {
      this.image = paramBitmap;
      this.delay = paramInt;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\GifDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */