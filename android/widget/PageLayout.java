package android.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class PageLayout
  extends HorizontalScrollView
{
  private int a = 0;
  private LinearLayout b;
  private OnPageChangeListener c;
  private int d;
  private Scroller e;
  private int f;
  private int g;
  private int h;
  private VelocityTracker i;
  private int j;
  private int k;
  
  public PageLayout(Context paramContext)
  {
    super(paramContext);
    a(paramContext);
  }
  
  public PageLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a(paramContext);
  }
  
  private void a()
  {
    if (this.i != null)
    {
      this.i.recycle();
      this.i = null;
    }
  }
  
  private void a(Context paramContext)
  {
    setHorizontalScrollBarEnabled(false);
    this.k = paramContext.getResources().getDisplayMetrics().widthPixels;
    this.a = (this.k / 2);
    this.b = new LinearLayout(paramContext);
    super.addView(this.b);
    this.e = new Scroller(getContext());
    setFocusable(true);
    setWillNotDraw(false);
    paramContext = ViewConfiguration.get(paramContext);
    this.f = paramContext.getScaledTouchSlop();
    this.g = paramContext.getScaledMinimumFlingVelocity();
    this.h = paramContext.getScaledMaximumFlingVelocity();
  }
  
  private void a(MotionEvent paramMotionEvent)
  {
    if (this.i == null) {
      this.i = VelocityTracker.obtain();
    }
    this.i.addMovement(paramMotionEvent);
  }
  
  public void addView(View paramView)
  {
    FrameLayout localFrameLayout = new FrameLayout(getContext());
    localFrameLayout.addView(paramView);
    this.b.addView(localFrameLayout);
  }
  
  public void addView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    FrameLayout localFrameLayout = new FrameLayout(getContext());
    localFrameLayout.addView(paramView, paramLayoutParams);
    this.b.addView(localFrameLayout);
  }
  
  public View getPage(int paramInt)
  {
    return this.b.getChildAt(paramInt);
  }
  
  public int getTouchScale()
  {
    return this.a;
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getX() >= this.a) && (paramMotionEvent.getX() <= this.k - this.a)) {
      return false;
    }
    return super.onInterceptTouchEvent(paramMotionEvent);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramBoolean) {
      showPage(this.d);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int m = getMeasuredWidth();
    int n = this.b.getChildCount();
    if ((this.j != n) || (this.k != m))
    {
      this.j = n;
      this.k = m;
      m = 0;
      while (m < n)
      {
        localObject = (ViewGroup)this.b.getChildAt(m);
        ViewGroup.LayoutParams localLayoutParams = ((ViewGroup)localObject).getLayoutParams();
        localLayoutParams.width = this.k;
        ((ViewGroup)localObject).setLayoutParams(localLayoutParams);
        ((ViewGroup)localObject).requestLayout();
        m += 1;
      }
      Object localObject = this.b.getLayoutParams();
      ((ViewGroup.LayoutParams)localObject).width = (this.k * n);
      this.b.setLayoutParams((ViewGroup.LayoutParams)localObject);
      this.b.requestLayout();
      requestLayout();
    }
    super.onMeasure(paramInt1, paramInt2);
    showPage(this.d);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int m = paramMotionEvent.getAction();
    a(paramMotionEvent);
    if (m != 1) {
      return super.onTouchEvent(paramMotionEvent);
    }
    paramMotionEvent = this.i;
    paramMotionEvent.computeCurrentVelocity(1000, this.h);
    int i1 = (int)paramMotionEvent.getYVelocity();
    m = (int)paramMotionEvent.getXVelocity();
    a();
    int n = Math.abs(m);
    i1 = Math.abs(i1);
    if ((n > this.g) && (n > i1))
    {
      if (m > 0) {}
      for (m = Math.max(0, this.d - 1);; m = Math.min(this.b.getChildCount() - 1, this.d + 1))
      {
        showPage(m);
        return true;
      }
    }
    m = getScrollX();
    if (m % this.k < this.k / 2) {
      m /= this.k;
    }
    for (;;)
    {
      showPage(m);
      return true;
      m = m / this.k + 1;
    }
  }
  
  public void setOnPageChangeListener(OnPageChangeListener paramOnPageChangeListener)
  {
    this.c = paramOnPageChangeListener;
  }
  
  public void setTouchScale(int paramInt)
  {
    this.a = paramInt;
  }
  
  public void showPage(int paramInt)
  {
    smoothScrollTo(this.k * paramInt, 0);
    if ((this.c != null) && (this.d != paramInt)) {
      this.c.onPageChange(this, paramInt);
    }
    this.d = paramInt;
  }
  
  public void showPage(View paramView)
  {
    int n = this.b.getChildCount();
    int m = 0;
    while (m < n)
    {
      if (this.b.getChildAt(m).equals(paramView)) {
        showPage(m);
      }
      m += 1;
    }
  }
  
  public static abstract interface OnPageChangeListener
  {
    public abstract void onPageChange(View paramView, int paramInt);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\widget\PageLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */