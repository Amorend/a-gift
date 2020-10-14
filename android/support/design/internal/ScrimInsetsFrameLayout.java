package android.support.design.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.design.R.style;
import android.support.design.R.styleable;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class ScrimInsetsFrameLayout
  extends FrameLayout
{
  Drawable mInsetForeground;
  Rect mInsets;
  private Rect mTempRect = new Rect();
  
  public ScrimInsetsFrameLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ScrimInsetsFrameLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public ScrimInsetsFrameLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ScrimInsetsFrameLayout, paramInt, R.style.Widget_Design_ScrimInsetsFrameLayout);
    this.mInsetForeground = paramContext.getDrawable(R.styleable.ScrimInsetsFrameLayout_insetForeground);
    paramContext.recycle();
    setWillNotDraw(true);
    ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener()
    {
      public WindowInsetsCompat onApplyWindowInsets(View paramAnonymousView, WindowInsetsCompat paramAnonymousWindowInsetsCompat)
      {
        if (ScrimInsetsFrameLayout.this.mInsets == null) {
          ScrimInsetsFrameLayout.this.mInsets = new Rect();
        }
        ScrimInsetsFrameLayout.this.mInsets.set(paramAnonymousWindowInsetsCompat.getSystemWindowInsetLeft(), paramAnonymousWindowInsetsCompat.getSystemWindowInsetTop(), paramAnonymousWindowInsetsCompat.getSystemWindowInsetRight(), paramAnonymousWindowInsetsCompat.getSystemWindowInsetBottom());
        ScrimInsetsFrameLayout.this.onInsetsChanged(paramAnonymousWindowInsetsCompat);
        paramAnonymousView = ScrimInsetsFrameLayout.this;
        if ((!paramAnonymousWindowInsetsCompat.hasSystemWindowInsets()) || (ScrimInsetsFrameLayout.this.mInsetForeground == null)) {}
        for (boolean bool = true;; bool = false)
        {
          paramAnonymousView.setWillNotDraw(bool);
          ViewCompat.postInvalidateOnAnimation(ScrimInsetsFrameLayout.this);
          return paramAnonymousWindowInsetsCompat.consumeSystemWindowInsets();
        }
      }
    });
  }
  
  public void draw(@NonNull Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    int i = getWidth();
    int j = getHeight();
    if ((this.mInsets != null) && (this.mInsetForeground != null))
    {
      int k = paramCanvas.save();
      paramCanvas.translate(getScrollX(), getScrollY());
      this.mTempRect.set(0, 0, i, this.mInsets.top);
      this.mInsetForeground.setBounds(this.mTempRect);
      this.mInsetForeground.draw(paramCanvas);
      this.mTempRect.set(0, j - this.mInsets.bottom, i, j);
      this.mInsetForeground.setBounds(this.mTempRect);
      this.mInsetForeground.draw(paramCanvas);
      this.mTempRect.set(0, this.mInsets.top, this.mInsets.left, j - this.mInsets.bottom);
      this.mInsetForeground.setBounds(this.mTempRect);
      this.mInsetForeground.draw(paramCanvas);
      this.mTempRect.set(i - this.mInsets.right, this.mInsets.top, i, j - this.mInsets.bottom);
      this.mInsetForeground.setBounds(this.mTempRect);
      this.mInsetForeground.draw(paramCanvas);
      paramCanvas.restoreToCount(k);
    }
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (this.mInsetForeground != null) {
      this.mInsetForeground.setCallback(this);
    }
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (this.mInsetForeground != null) {
      this.mInsetForeground.setCallback(null);
    }
  }
  
  protected void onInsetsChanged(WindowInsetsCompat paramWindowInsetsCompat) {}
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\design\internal\ScrimInsetsFrameLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */