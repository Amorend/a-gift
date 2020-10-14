package android.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GifView
  extends View
{
  private int a;
  private Movie b;
  private long c;
  private int d;
  private float e;
  private float f;
  private float g;
  private int h;
  private int i;
  private volatile boolean j;
  private boolean k = true;
  private String l;
  
  public GifView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public GifView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public GifView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    a(paramContext, paramAttributeSet, paramInt);
  }
  
  @SuppressLint({"NewApi"})
  private void a()
  {
    if (this.k)
    {
      if (Build.VERSION.SDK_INT >= 16)
      {
        postInvalidateOnAnimation();
        return;
      }
      invalidate();
    }
  }
  
  @SuppressLint({"NewApi"})
  private void a(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    this.a = -1;
    this.j = false;
    if (this.a != -1) {
      this.b = Movie.decodeStream(getResources().openRawResource(this.a));
    }
  }
  
  private void a(Canvas paramCanvas)
  {
    this.b.setTime(this.d);
    paramCanvas.save(1);
    paramCanvas.scale(this.g, this.g);
    this.b.draw(paramCanvas, this.e / this.g, this.f / this.g);
    paramCanvas.restore();
  }
  
  private void b()
  {
    long l1 = SystemClock.uptimeMillis();
    if (this.c == 0L) {
      this.c = l1;
    }
    int n = this.b.duration();
    int m = n;
    if (n == 0) {
      m = 1000;
    }
    this.d = ((int)((l1 - this.c) % m));
  }
  
  public String getGifPath()
  {
    return this.l;
  }
  
  public int getGifResource()
  {
    return this.a;
  }
  
  public boolean isPaused()
  {
    return this.j;
  }
  
  public boolean isPlaying()
  {
    return this.j ^ true;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    if (this.b != null)
    {
      if (!this.j)
      {
        b();
        a(paramCanvas);
        a();
        return;
      }
      a(paramCanvas);
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    this.e = ((getWidth() - this.h) / 2.0F);
    this.f = ((getHeight() - this.i) / 2.0F);
    if (getVisibility() == 0) {
      paramBoolean = true;
    } else {
      paramBoolean = false;
    }
    this.k = paramBoolean;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (this.b != null)
    {
      int m = this.b.width();
      int n = this.b.height();
      if (View.MeasureSpec.getMode(paramInt1) != 0)
      {
        paramInt1 = View.MeasureSpec.getSize(paramInt1);
        if (m > paramInt1)
        {
          f1 = m / paramInt1;
          break label55;
        }
      }
      float f1 = 1.0F;
      label55:
      if (View.MeasureSpec.getMode(paramInt2) != 0)
      {
        paramInt1 = View.MeasureSpec.getSize(paramInt2);
        if (n > paramInt1)
        {
          f2 = n / paramInt1;
          break label87;
        }
      }
      float f2 = 1.0F;
      label87:
      this.g = (1.0F / Math.max(f1, f2));
      this.h = ((int)(m * this.g));
      this.i = ((int)(n * this.g));
      paramInt1 = this.h;
      paramInt2 = this.i;
    }
    else
    {
      paramInt1 = getSuggestedMinimumWidth();
      paramInt2 = getSuggestedMinimumHeight();
    }
    setMeasuredDimension(paramInt1, paramInt2);
  }
  
  @SuppressLint({"NewApi"})
  public void onScreenStateChanged(int paramInt)
  {
    super.onScreenStateChanged(paramInt);
    boolean bool = true;
    if (paramInt != 1) {
      bool = false;
    }
    this.k = bool;
    a();
  }
  
  @SuppressLint({"NewApi"})
  protected void onVisibilityChanged(View paramView, int paramInt)
  {
    super.onVisibilityChanged(paramView, paramInt);
    boolean bool;
    if (paramInt == 0) {
      bool = true;
    } else {
      bool = false;
    }
    this.k = bool;
    a();
  }
  
  protected void onWindowVisibilityChanged(int paramInt)
  {
    super.onWindowVisibilityChanged(paramInt);
    boolean bool;
    if (paramInt == 0) {
      bool = true;
    } else {
      bool = false;
    }
    this.k = bool;
    a();
  }
  
  public void pause()
  {
    if (!this.j)
    {
      this.j = true;
      invalidate();
    }
  }
  
  public void play()
  {
    if (this.j)
    {
      this.j = false;
      this.c = (SystemClock.uptimeMillis() - this.d);
      invalidate();
    }
  }
  
  public void setGifPath(String paramString)
  {
    this.l = paramString;
    try
    {
      this.b = Movie.decodeStream(new FileInputStream(paramString));
    }
    catch (FileNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    requestLayout();
  }
  
  public void setGifResource(int paramInt)
  {
    this.a = paramInt;
    this.b = Movie.decodeStream(getResources().openRawResource(this.a));
    requestLayout();
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\widget\GifView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */