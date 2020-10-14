package android.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PageView
  extends ViewGroup
{
  public static final int SCROLL_STATE_DRAGGING = 1;
  public static final int SCROLL_STATE_IDLE = 0;
  public static final int SCROLL_STATE_SETTLING = 2;
  private static final int[] a = { 16842931 };
  private static final Comparator<ItemInfo> b = new Comparator()
  {
    public int compare(PageView.ItemInfo paramAnonymousItemInfo1, PageView.ItemInfo paramAnonymousItemInfo2)
    {
      return paramAnonymousItemInfo1.b - paramAnonymousItemInfo2.b;
    }
  };
  private static final Interpolator c = new Interpolator()
  {
    public float getInterpolation(float paramAnonymousFloat)
    {
      paramAnonymousFloat -= 1.0F;
      return paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat + 1.0F;
    }
  };
  private static final ViewPositionComparator d = new ViewPositionComparator();
  private int A = 1;
  private boolean B;
  private boolean C;
  private int D;
  private int E;
  private int F;
  private float G;
  private float H;
  private float I;
  private float J;
  private int K = -1;
  private VelocityTracker L;
  private int M;
  private int N;
  private int O;
  private int P;
  private boolean Q;
  private long R;
  private EdgeEffect S;
  private EdgeEffect T;
  private boolean U = true;
  private boolean V = false;
  private boolean W;
  private int aa;
  private List<OnPageChangeListener> ab;
  private OnPageChangeListener ac;
  private OnPageChangeListener ad;
  private OnAdapterChangeListener ae;
  private PageTransformer af;
  private Method ag;
  private int ah;
  private ArrayList<View> ai;
  private int aj = 0;
  private final Runnable ak = new Runnable()
  {
    public void run()
    {
      PageView.a(PageView.this, 0);
      PageView.this.c();
    }
  };
  private boolean al = true;
  private final ArrayList<ItemInfo> e = new ArrayList();
  private final ItemInfo f = new ItemInfo();
  private final Rect g = new Rect();
  private int h;
  private BasePageAdapter i;
  private int j;
  private int k = -1;
  private Parcelable l = null;
  private ClassLoader m = null;
  private Scroller n;
  private PageObserver o;
  private int p;
  private Drawable q;
  private int r;
  private int s;
  private float t = -3.4028235E38F;
  private float u = Float.MAX_VALUE;
  private int v;
  private int w;
  private boolean x;
  private boolean y;
  private boolean z;
  
  public PageView(Context paramContext)
  {
    super(paramContext);
    a();
  }
  
  public PageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a();
  }
  
  private int a(int paramInt1, float paramFloat, int paramInt2, int paramInt3)
  {
    if ((Math.abs(paramInt3) > this.O) && (Math.abs(paramInt2) > this.M))
    {
      if (paramInt2 <= 0) {
        paramInt1 += 1;
      }
    }
    else
    {
      float f1;
      if (paramInt1 >= this.j) {
        f1 = 0.4F;
      } else {
        f1 = 0.6F;
      }
      paramInt1 = (int)(paramInt1 + paramFloat + f1);
    }
    paramInt2 = paramInt1;
    if (this.e.size() > 0)
    {
      ItemInfo localItemInfo1 = (ItemInfo)this.e.get(0);
      ItemInfo localItemInfo2 = (ItemInfo)this.e.get(this.e.size() - 1);
      paramInt2 = Math.max(localItemInfo1.b, Math.min(paramInt1, localItemInfo2.b));
    }
    return paramInt2;
  }
  
  private Rect a(Rect paramRect, View paramView)
  {
    Rect localRect = paramRect;
    if (paramRect == null) {
      localRect = new Rect();
    }
    if (paramView == null)
    {
      localRect.set(0, 0, 0, 0);
      return localRect;
    }
    localRect.left = paramView.getLeft();
    localRect.right = paramView.getRight();
    localRect.top = paramView.getTop();
    localRect.bottom = paramView.getBottom();
    for (paramRect = paramView.getParent(); ((paramRect instanceof ViewGroup)) && (paramRect != this); paramRect = paramRect.getParent())
    {
      paramRect = (ViewGroup)paramRect;
      localRect.left += paramRect.getLeft();
      localRect.right += paramRect.getRight();
      localRect.top += paramRect.getTop();
      localRect.bottom += paramRect.getBottom();
    }
    return localRect;
  }
  
  private void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    ItemInfo localItemInfo;
    if ((paramInt2 > 0) && (!this.e.isEmpty()))
    {
      int i1 = getPaddingLeft();
      int i2 = getPaddingRight();
      int i3 = getPaddingLeft();
      int i4 = getPaddingRight();
      paramInt2 = (int)(getScrollX() / (paramInt2 - i3 - i4 + paramInt4) * (paramInt1 - i1 - i2 + paramInt3));
      scrollTo(paramInt2, getScrollY());
      if (!this.n.isFinished())
      {
        paramInt3 = this.n.getDuration();
        paramInt4 = this.n.timePassed();
        localItemInfo = b(this.j);
        this.n.startScroll(paramInt2, 0, (int)(localItemInfo.e * paramInt1), 0, paramInt3 - paramInt4);
      }
    }
    else
    {
      localItemInfo = b(this.j);
      float f1;
      if (localItemInfo != null) {
        f1 = Math.min(localItemInfo.e, this.u);
      } else {
        f1 = 0.0F;
      }
      paramInt1 = (int)(f1 * (paramInt1 - getPaddingLeft() - getPaddingRight()));
      if (paramInt1 != getScrollX())
      {
        a(false);
        scrollTo(paramInt1, getScrollY());
      }
    }
  }
  
  private void a(int paramInt1, boolean paramBoolean1, int paramInt2, boolean paramBoolean2)
  {
    ItemInfo localItemInfo = b(paramInt1);
    int i1;
    if (localItemInfo != null) {
      i1 = (int)(getClientWidth() * Math.max(this.t, Math.min(localItemInfo.e, this.u)));
    } else {
      i1 = 0;
    }
    if (paramBoolean1)
    {
      a(i1, 0, paramInt2);
      if (paramBoolean2) {
        d(paramInt1);
      }
    }
    else
    {
      if (paramBoolean2) {
        d(paramInt1);
      }
      a(false);
      scrollTo(i1, 0);
      c(i1);
    }
  }
  
  private void a(MotionEvent paramMotionEvent)
  {
    int i1 = paramMotionEvent.getActionIndex();
    if (paramMotionEvent.getPointerId(i1) == this.K)
    {
      if (i1 == 0) {
        i1 = 1;
      } else {
        i1 = 0;
      }
      this.G = paramMotionEvent.getX(i1);
      this.K = paramMotionEvent.getPointerId(i1);
      if (this.L != null) {
        this.L.clear();
      }
    }
  }
  
  private void a(ItemInfo paramItemInfo1, int paramInt, ItemInfo paramItemInfo2)
  {
    int i4 = this.i.getCount();
    int i1 = getClientWidth();
    float f2;
    if (i1 > 0) {
      f2 = this.p / i1;
    } else {
      f2 = 0.0F;
    }
    if (paramItemInfo2 != null)
    {
      i1 = paramItemInfo2.b;
      if (i1 < paramItemInfo1.b)
      {
        f1 = paramItemInfo2.e + paramItemInfo2.d + f2;
        i1 += 1;
        i2 = 0;
        while ((i1 <= paramItemInfo1.b) && (i2 < this.e.size()))
        {
          for (;;)
          {
            paramItemInfo2 = (ItemInfo)this.e.get(i2);
            i3 = i1;
            f3 = f1;
            if (i1 <= paramItemInfo2.b) {
              break;
            }
            i3 = i1;
            f3 = f1;
            if (i2 >= this.e.size() - 1) {
              break;
            }
            i2 += 1;
          }
          while (i3 < paramItemInfo2.b)
          {
            f3 += this.i.getPageWidth(i3) + f2;
            i3 += 1;
          }
          paramItemInfo2.e = f3;
          f1 = f3 + (paramItemInfo2.d + f2);
          i1 = i3 + 1;
        }
      }
      if (i1 > paramItemInfo1.b)
      {
        i2 = this.e.size() - 1;
        f1 = paramItemInfo2.e;
        i1 -= 1;
        while ((i1 >= paramItemInfo1.b) && (i2 >= 0))
        {
          for (;;)
          {
            paramItemInfo2 = (ItemInfo)this.e.get(i2);
            i3 = i1;
            f3 = f1;
            if (i1 >= paramItemInfo2.b) {
              break;
            }
            i3 = i1;
            f3 = f1;
            if (i2 <= 0) {
              break;
            }
            i2 -= 1;
          }
          while (i3 > paramItemInfo2.b)
          {
            f3 -= this.i.getPageWidth(i3) + f2;
            i3 -= 1;
          }
          f1 = f3 - (paramItemInfo2.d + f2);
          paramItemInfo2.e = f1;
          i1 = i3 - 1;
        }
      }
    }
    int i3 = this.e.size();
    float f3 = paramItemInfo1.e;
    i1 = paramItemInfo1.b - 1;
    if (paramItemInfo1.b == 0) {
      f1 = paramItemInfo1.e;
    } else {
      f1 = -3.4028235E38F;
    }
    this.t = f1;
    int i2 = paramItemInfo1.b;
    i4 -= 1;
    if (i2 == i4) {
      f1 = paramItemInfo1.e + paramItemInfo1.d - 1.0F;
    } else {
      f1 = Float.MAX_VALUE;
    }
    this.u = f1;
    i2 = paramInt - 1;
    float f1 = f3;
    while (i2 >= 0)
    {
      paramItemInfo2 = (ItemInfo)this.e.get(i2);
      while (i1 > paramItemInfo2.b)
      {
        f1 -= this.i.getPageWidth(i1) + f2;
        i1 -= 1;
      }
      f1 -= paramItemInfo2.d + f2;
      paramItemInfo2.e = f1;
      if (paramItemInfo2.b == 0) {
        this.t = f1;
      }
      i2 -= 1;
      i1 -= 1;
    }
    f1 = paramItemInfo1.e + paramItemInfo1.d + f2;
    i2 = paramItemInfo1.b + 1;
    i1 = paramInt + 1;
    paramInt = i2;
    while (i1 < i3)
    {
      paramItemInfo1 = (ItemInfo)this.e.get(i1);
      while (paramInt < paramItemInfo1.b)
      {
        f1 += this.i.getPageWidth(paramInt) + f2;
        paramInt += 1;
      }
      if (paramItemInfo1.b == i4) {
        this.u = (paramItemInfo1.d + f1 - 1.0F);
      }
      paramItemInfo1.e = f1;
      f1 += paramItemInfo1.d + f2;
      i1 += 1;
      paramInt += 1;
    }
    this.V = false;
  }
  
  private void a(boolean paramBoolean)
  {
    if (this.aj == 2) {
      i1 = 1;
    } else {
      i1 = 0;
    }
    if (i1 != 0)
    {
      setScrollingCacheEnabled(false);
      this.n.abortAnimation();
      i2 = getScrollX();
      i3 = getScrollY();
      int i4 = this.n.getCurrX();
      int i5 = this.n.getCurrY();
      if ((i2 != i4) || (i3 != i5))
      {
        scrollTo(i4, i5);
        if (i4 != i2) {
          c(i4);
        }
      }
    }
    this.z = false;
    int i3 = 0;
    int i2 = i1;
    int i1 = i3;
    while (i1 < this.e.size())
    {
      ItemInfo localItemInfo = (ItemInfo)this.e.get(i1);
      if (localItemInfo.c)
      {
        localItemInfo.c = false;
        i2 = 1;
      }
      i1 += 1;
    }
    if (i2 != 0)
    {
      if (paramBoolean)
      {
        postOnAnimation(this.ak);
        return;
      }
      this.ak.run();
    }
  }
  
  private boolean a(float paramFloat1, float paramFloat2)
  {
    return ((paramFloat1 < this.E) && (paramFloat2 > 0.0F)) || ((paramFloat1 > getWidth() - this.E) && (paramFloat2 < 0.0F));
  }
  
  private void b(int paramInt1, float paramFloat, int paramInt2)
  {
    if (this.ac != null) {
      this.ac.onPageScrolled(paramInt1, paramFloat, paramInt2);
    }
    if (this.ab != null)
    {
      int i1 = 0;
      int i2 = this.ab.size();
      while (i1 < i2)
      {
        OnPageChangeListener localOnPageChangeListener = (OnPageChangeListener)this.ab.get(i1);
        if (localOnPageChangeListener != null) {
          localOnPageChangeListener.onPageScrolled(paramInt1, paramFloat, paramInt2);
        }
        i1 += 1;
      }
    }
    if (this.ad != null) {
      this.ad.onPageScrolled(paramInt1, paramFloat, paramInt2);
    }
  }
  
  private void b(boolean paramBoolean)
  {
    int i3 = getChildCount();
    int i1 = 0;
    while (i1 < i3)
    {
      int i2;
      if (paramBoolean) {
        i2 = 2;
      } else {
        i2 = 0;
      }
      getChildAt(i1).setLayerType(i2, null);
      i1 += 1;
    }
  }
  
  private boolean b(float paramFloat)
  {
    float f1 = this.G;
    this.G = paramFloat;
    float f2 = getScrollX() + (f1 - paramFloat);
    float f3 = getClientWidth();
    paramFloat = this.t * f3;
    f1 = this.u * f3;
    ItemInfo localItemInfo = (ItemInfo)this.e.get(0);
    Object localObject = this.e;
    int i1 = this.e.size();
    int i2 = 1;
    localObject = (ItemInfo)((ArrayList)localObject).get(i1 - 1);
    if (localItemInfo.b != 0)
    {
      paramFloat = localItemInfo.e * f3;
      i1 = 0;
    }
    else
    {
      i1 = 1;
    }
    if (((ItemInfo)localObject).b != this.i.getCount() - 1)
    {
      f1 = ((ItemInfo)localObject).e * f3;
      i2 = 0;
    }
    if (f2 < paramFloat)
    {
      if (i1 != 0) {
        this.S.onPull(Math.abs(paramFloat - f2) / f3);
      }
    }
    else
    {
      paramFloat = f2;
      if (f2 > f1)
      {
        if (i2 != 0) {
          this.T.onPull(Math.abs(f2 - f1) / f3);
        }
        paramFloat = f1;
      }
    }
    f1 = this.G;
    i1 = (int)paramFloat;
    this.G = (f1 + (paramFloat - i1));
    scrollTo(i1, getScrollY());
    c(i1);
    return false;
  }
  
  private void c(boolean paramBoolean)
  {
    ViewParent localViewParent = getParent();
    if (localViewParent != null) {
      localViewParent.requestDisallowInterceptTouchEvent(paramBoolean);
    }
  }
  
  private boolean c(int paramInt)
  {
    if (this.e.size() == 0)
    {
      this.W = false;
      a(0, 0.0F, 0);
      if (!this.W) {
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
      }
      return false;
    }
    ItemInfo localItemInfo = j();
    int i2 = getClientWidth();
    int i3 = this.p;
    float f2 = this.p;
    float f1 = i2;
    f2 /= f1;
    int i1 = localItemInfo.b;
    f1 = (paramInt / f1 - localItemInfo.e) / (localItemInfo.d + f2);
    paramInt = (int)((i3 + i2) * f1);
    this.W = false;
    a(i1, f1, paramInt);
    if (!this.W) {
      throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }
    return true;
  }
  
  private void d(int paramInt)
  {
    if (this.ac != null)
    {
      this.ac.onPageSelected(paramInt);
      this.ac.onPageChange(getChildAt(paramInt), paramInt);
    }
    if (this.ab != null)
    {
      int i1 = 0;
      int i2 = this.ab.size();
      while (i1 < i2)
      {
        OnPageChangeListener localOnPageChangeListener = (OnPageChangeListener)this.ab.get(i1);
        if (localOnPageChangeListener != null) {
          localOnPageChangeListener.onPageSelected(paramInt);
        }
        i1 += 1;
      }
    }
    if (this.ad != null) {
      this.ad.onPageSelected(paramInt);
    }
  }
  
  private void e(int paramInt)
  {
    if (this.ac != null) {
      this.ac.onPageScrollStateChanged(paramInt);
    }
    if (this.ab != null)
    {
      int i1 = 0;
      int i2 = this.ab.size();
      while (i1 < i2)
      {
        OnPageChangeListener localOnPageChangeListener = (OnPageChangeListener)this.ab.get(i1);
        if (localOnPageChangeListener != null) {
          localOnPageChangeListener.onPageScrollStateChanged(paramInt);
        }
        i1 += 1;
      }
    }
    if (this.ad != null) {
      this.ad.onPageScrollStateChanged(paramInt);
    }
  }
  
  private void g()
  {
    int i2;
    for (int i1 = 0; i1 < getChildCount(); i1 = i2 + 1)
    {
      i2 = i1;
      if (!((LayoutParams)getChildAt(i1).getLayoutParams()).isDecor)
      {
        removeViewAt(i1);
        i2 = i1 - 1;
      }
    }
  }
  
  private int getClientWidth()
  {
    return getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
  }
  
  private void h()
  {
    if (this.ah != 0)
    {
      if (this.ai == null) {
        this.ai = new ArrayList();
      } else {
        this.ai.clear();
      }
      int i2 = getChildCount();
      int i1 = 0;
      while (i1 < i2)
      {
        View localView = getChildAt(i1);
        this.ai.add(localView);
        i1 += 1;
      }
      Collections.sort(this.ai, d);
    }
  }
  
  private boolean i()
  {
    this.K = -1;
    k();
    this.S.onRelease();
    this.T.onRelease();
    return true;
  }
  
  private ItemInfo j()
  {
    int i1 = getClientWidth();
    float f1;
    if (i1 > 0) {
      f1 = getScrollX() / i1;
    } else {
      f1 = 0.0F;
    }
    float f2;
    if (i1 > 0) {
      f2 = this.p / i1;
    } else {
      f2 = 0.0F;
    }
    Object localObject = null;
    i1 = 0;
    int i2 = 1;
    int i3 = -1;
    float f3 = 0.0F;
    float f4 = 0.0F;
    while (i1 < this.e.size())
    {
      ItemInfo localItemInfo2 = (ItemInfo)this.e.get(i1);
      int i4 = i1;
      ItemInfo localItemInfo1 = localItemInfo2;
      if (i2 == 0)
      {
        int i5 = localItemInfo2.b;
        i3 += 1;
        i4 = i1;
        localItemInfo1 = localItemInfo2;
        if (i5 != i3)
        {
          localItemInfo1 = this.f;
          localItemInfo1.e = (f3 + f4 + f2);
          localItemInfo1.b = i3;
          localItemInfo1.d = this.i.getPageWidth(localItemInfo1.b);
          i4 = i1 - 1;
        }
      }
      f3 = localItemInfo1.e;
      f4 = localItemInfo1.d;
      if ((i2 == 0) && (f1 < f3)) {
        return (ItemInfo)localObject;
      }
      if (f1 >= f4 + f3 + f2)
      {
        if (i4 == this.e.size() - 1) {
          return localItemInfo1;
        }
        i3 = localItemInfo1.b;
        f4 = localItemInfo1.d;
        i1 = i4 + 1;
        i2 = 0;
        localObject = localItemInfo1;
      }
      else
      {
        return localItemInfo1;
      }
    }
    return (ItemInfo)localObject;
  }
  
  private void k()
  {
    this.B = false;
    this.C = false;
    if (this.L != null)
    {
      this.L.recycle();
      this.L = null;
    }
  }
  
  private void setScrollState(int paramInt)
  {
    if (this.aj == paramInt) {
      return;
    }
    this.aj = paramInt;
    if (this.af != null)
    {
      boolean bool;
      if (paramInt != 0) {
        bool = true;
      } else {
        bool = false;
      }
      b(bool);
    }
    e(paramInt);
  }
  
  private void setScrollingCacheEnabled(boolean paramBoolean)
  {
    if (this.y != paramBoolean) {
      this.y = paramBoolean;
    }
  }
  
  float a(float paramFloat)
  {
    return (float)Math.sin((float)((paramFloat - 0.5F) * 0.4712389167638204D));
  }
  
  ItemInfo a(int paramInt1, int paramInt2)
  {
    ItemInfo localItemInfo = new ItemInfo();
    localItemInfo.b = paramInt1;
    localItemInfo.a = this.i.instantiateItem(this, paramInt1);
    localItemInfo.d = this.i.getPageWidth(paramInt1);
    if ((paramInt2 >= 0) && (paramInt2 < this.e.size()))
    {
      this.e.add(paramInt2, localItemInfo);
      return localItemInfo;
    }
    this.e.add(localItemInfo);
    return localItemInfo;
  }
  
  ItemInfo a(View paramView)
  {
    int i1 = 0;
    while (i1 < this.e.size())
    {
      ItemInfo localItemInfo = (ItemInfo)this.e.get(i1);
      if (this.i.isViewFromObject(paramView, localItemInfo.a)) {
        return localItemInfo;
      }
      i1 += 1;
    }
    return null;
  }
  
  void a()
  {
    setWillNotDraw(false);
    setDescendantFocusability(262144);
    setFocusable(true);
    Context localContext = getContext();
    this.n = new Scroller(localContext, c);
    ViewConfiguration localViewConfiguration = ViewConfiguration.get(localContext);
    float f1 = localContext.getResources().getDisplayMetrics().density;
    this.F = localViewConfiguration.getScaledPagingTouchSlop();
    this.M = ((int)(400.0F * f1));
    this.N = localViewConfiguration.getScaledMaximumFlingVelocity();
    this.S = new EdgeEffect(localContext);
    this.T = new EdgeEffect(localContext);
    this.O = ((int)(25.0F * f1));
    this.P = ((int)(2.0F * f1));
    this.D = ((int)(f1 * 16.0F));
    setAccessibilityDelegate(new View.AccessibilityDelegate());
    if (Build.VERSION.SDK_INT < 16) {
      return;
    }
    if (getImportantForAccessibility() == 0) {
      setImportantForAccessibility(1);
    }
  }
  
  void a(int paramInt)
  {
    int i4;
    if (this.j != paramInt)
    {
      if (this.j < paramInt) {
        i1 = 66;
      } else {
        i1 = 17;
      }
      localObject2 = b(this.j);
      this.j = paramInt;
      i4 = i1;
    }
    else
    {
      i4 = 2;
      localObject2 = null;
    }
    if (this.i == null)
    {
      h();
      return;
    }
    if (this.z)
    {
      h();
      return;
    }
    if (getWindowToken() == null) {
      return;
    }
    this.i.startUpdate(this);
    paramInt = this.A;
    int i8 = Math.max(0, this.j - paramInt);
    int i6 = this.i.getCount();
    int i7 = Math.min(i6 - 1, this.j + paramInt);
    paramInt = 0;
    while (paramInt < this.e.size())
    {
      localObject1 = (ItemInfo)this.e.get(paramInt);
      if (((ItemInfo)localObject1).b >= this.j)
      {
        if (((ItemInfo)localObject1).b != this.j) {
          break;
        }
        break label199;
      }
      paramInt += 1;
    }
    Object localObject1 = null;
    label199:
    Object localObject3 = localObject1;
    if (localObject1 == null)
    {
      localObject3 = localObject1;
      if (i6 > 0) {
        localObject3 = a(this.j, paramInt);
      }
    }
    if (localObject3 != null)
    {
      i1 = paramInt - 1;
      if (i1 >= 0) {
        localObject1 = (ItemInfo)this.e.get(i1);
      } else {
        localObject1 = null;
      }
      int i9 = getClientWidth();
      float f3;
      float f1;
      if (i9 <= 0)
      {
        f3 = 0.0F;
      }
      else
      {
        f1 = ((ItemInfo)localObject3).d;
        f3 = getPaddingLeft() / i9 + (2.0F - f1);
      }
      int i5 = this.j - 1;
      float f2 = 0.0F;
      int i3;
      Object localObject4;
      int i2;
      while (i5 >= 0)
      {
        if ((f2 >= f3) && (i5 < i8))
        {
          if (localObject1 == null) {
            break;
          }
          f1 = f2;
          i3 = i1;
          localObject4 = localObject1;
          i2 = paramInt;
          if (i5 != ((ItemInfo)localObject1).b) {
            break label554;
          }
          f1 = f2;
          i3 = i1;
          localObject4 = localObject1;
          i2 = paramInt;
          if (((ItemInfo)localObject1).c) {
            break label554;
          }
          this.e.remove(i1);
          this.i.destroyItem(this, i5, ((ItemInfo)localObject1).a);
          i1 -= 1;
          paramInt -= 1;
          f1 = f2;
          i2 = i1;
          i3 = paramInt;
          if (i1 < 0) {
            break label533;
          }
        }
        else if ((localObject1 != null) && (i5 == ((ItemInfo)localObject1).b))
        {
          f2 += ((ItemInfo)localObject1).d;
          i1 -= 1;
          f1 = f2;
          i2 = i1;
          i3 = paramInt;
          if (i1 < 0) {
            break label533;
          }
        }
        else
        {
          f2 += a(i5, i1 + 1).d;
          paramInt += 1;
          f1 = f2;
          i2 = i1;
          i3 = paramInt;
          if (i1 < 0) {
            break label533;
          }
        }
        localObject1 = (ItemInfo)this.e.get(i1);
        f1 = f2;
        break label543;
        label533:
        localObject1 = null;
        paramInt = i3;
        i1 = i2;
        label543:
        i2 = paramInt;
        localObject4 = localObject1;
        i3 = i1;
        label554:
        i5 -= 1;
        f2 = f1;
        i1 = i3;
        localObject1 = localObject4;
        paramInt = i2;
      }
      f2 = ((ItemInfo)localObject3).d;
      i1 = paramInt + 1;
      if (f2 < 2.0F)
      {
        if (i1 < this.e.size()) {
          localObject1 = (ItemInfo)this.e.get(i1);
        } else {
          localObject1 = null;
        }
        if (i9 <= 0) {
          f3 = 0.0F;
        } else {
          f3 = getPaddingRight() / i9 + 2.0F;
        }
        i2 = this.j;
        localObject4 = localObject1;
        for (;;)
        {
          i3 = i2 + 1;
          if (i3 >= i6) {
            break;
          }
          if ((f2 >= f3) && (i3 > i7))
          {
            if (localObject4 == null) {
              break;
            }
            f1 = f2;
            i2 = i1;
            localObject1 = localObject4;
            if (i3 != ((ItemInfo)localObject4).b) {
              break label899;
            }
            f1 = f2;
            i2 = i1;
            localObject1 = localObject4;
            if (((ItemInfo)localObject4).c) {
              break label899;
            }
            this.e.remove(i1);
            this.i.destroyItem(this, i3, ((ItemInfo)localObject4).a);
            f1 = f2;
            i2 = i1;
            if (i1 >= this.e.size()) {}
          }
          for (;;)
          {
            localObject1 = (ItemInfo)this.e.get(i1);
            f1 = f2;
            i2 = i1;
            break;
            do
            {
              do
              {
                localObject1 = null;
                break label899;
                if ((localObject4 == null) || (i3 != ((ItemInfo)localObject4).b)) {
                  break;
                }
                f2 += ((ItemInfo)localObject4).d;
                i1 += 1;
                f1 = f2;
                i2 = i1;
              } while (i1 >= this.e.size());
              break;
              localObject1 = a(i3, i1);
              i1 += 1;
              f2 += ((ItemInfo)localObject1).d;
              f1 = f2;
              i2 = i1;
            } while (i1 >= this.e.size());
          }
          label899:
          f2 = f1;
          i1 = i2;
          localObject4 = localObject1;
          i2 = i3;
        }
      }
      a((ItemInfo)localObject3, paramInt, (ItemInfo)localObject2);
    }
    Object localObject2 = this.i;
    paramInt = this.j;
    if (localObject3 != null) {
      localObject1 = ((ItemInfo)localObject3).a;
    } else {
      localObject1 = null;
    }
    ((BasePageAdapter)localObject2).setPrimaryItem(this, paramInt, localObject1);
    this.i.finishUpdate(this);
    int i1 = getChildCount();
    paramInt = 0;
    while (paramInt < i1)
    {
      localObject2 = getChildAt(paramInt);
      localObject1 = (LayoutParams)((View)localObject2).getLayoutParams();
      ((LayoutParams)localObject1).d = paramInt;
      if ((!((LayoutParams)localObject1).isDecor) && (((LayoutParams)localObject1).a == 0.0F))
      {
        localObject2 = a((View)localObject2);
        if (localObject2 != null)
        {
          ((LayoutParams)localObject1).a = ((ItemInfo)localObject2).d;
          ((LayoutParams)localObject1).c = ((ItemInfo)localObject2).b;
        }
      }
      paramInt += 1;
    }
    h();
    if (hasFocus())
    {
      localObject1 = findFocus();
      if (localObject1 != null) {
        localObject1 = b((View)localObject1);
      } else {
        localObject1 = null;
      }
      if ((localObject1 == null) || (((ItemInfo)localObject1).b != this.j))
      {
        paramInt = 0;
        while (paramInt < getChildCount())
        {
          localObject1 = getChildAt(paramInt);
          localObject2 = a((View)localObject1);
          if ((localObject2 != null) && (((ItemInfo)localObject2).b == this.j) && (((View)localObject1).requestFocus(i4))) {
            return;
          }
          paramInt += 1;
        }
      }
    }
  }
  
  protected void a(int paramInt1, float paramFloat, int paramInt2)
  {
    int i1 = this.aa;
    int i6 = 0;
    View localView;
    if (i1 > 0)
    {
      int i7 = getScrollX();
      i1 = getPaddingLeft();
      int i2 = getPaddingRight();
      int i8 = getWidth();
      int i9 = getChildCount();
      int i4 = 0;
      while (i4 < i9)
      {
        localView = getChildAt(i4);
        LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if (localLayoutParams.isDecor)
        {
          int i3 = localLayoutParams.gravity & 0x7;
          if (i3 != 1)
          {
            int i5;
            if (i3 != 3)
            {
              if (i3 != 5)
              {
                i5 = i1;
                i3 = i1;
                i1 = i5;
              }
              else
              {
                i3 = i8 - i2 - localView.getMeasuredWidth();
                i2 += localView.getMeasuredWidth();
              }
            }
            else
            {
              i5 = localView.getWidth() + i1;
              i3 = i1;
              i1 = i5;
            }
          }
          else
          {
            i3 = Math.max((i8 - localView.getMeasuredWidth()) / 2, i1);
          }
          i3 = i3 + i7 - localView.getLeft();
          if (i3 != 0) {
            localView.offsetLeftAndRight(i3);
          }
        }
        i4 += 1;
      }
    }
    b(paramInt1, paramFloat, paramInt2);
    if (this.af != null)
    {
      paramInt2 = getScrollX();
      i1 = getChildCount();
      paramInt1 = i6;
      while (paramInt1 < i1)
      {
        localView = getChildAt(paramInt1);
        if (!((LayoutParams)localView.getLayoutParams()).isDecor)
        {
          paramFloat = (localView.getLeft() - paramInt2) / getClientWidth();
          this.af.transformPage(localView, paramFloat);
        }
        paramInt1 += 1;
      }
    }
    this.W = true;
  }
  
  void a(int paramInt1, int paramInt2, int paramInt3)
  {
    if (getChildCount() == 0)
    {
      setScrollingCacheEnabled(false);
      return;
    }
    int i1 = getScrollX();
    int i2 = getScrollY();
    int i3 = paramInt1 - i1;
    paramInt2 -= i2;
    if ((i3 == 0) && (paramInt2 == 0))
    {
      a(false);
      c();
      setScrollState(0);
      return;
    }
    setScrollingCacheEnabled(true);
    setScrollState(2);
    paramInt1 = getClientWidth();
    int i4 = paramInt1 / 2;
    float f2 = Math.abs(i3);
    float f1 = paramInt1;
    float f3 = Math.min(1.0F, f2 * 1.0F / f1);
    f2 = i4;
    f3 = a(f3);
    paramInt1 = Math.abs(paramInt3);
    if (paramInt1 > 0)
    {
      paramInt1 = Math.round(Math.abs((f2 + f3 * f2) / paramInt1) * 1000.0F) * 4;
    }
    else
    {
      f2 = this.i.getPageWidth(this.j);
      paramInt1 = (int)((Math.abs(i3) / (f1 * f2 + this.p) + 1.0F) * 100.0F);
    }
    paramInt1 = Math.min(paramInt1, 600);
    this.n.startScroll(i1, i2, i3, paramInt2, paramInt1);
    postInvalidateOnAnimation();
  }
  
  void a(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    a(paramInt, paramBoolean1, paramBoolean2, 0);
  }
  
  void a(int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2)
  {
    if ((this.i != null) && (this.i.getCount() > 0))
    {
      if ((!paramBoolean2) && (this.j == paramInt1) && (this.e.size() != 0))
      {
        setScrollingCacheEnabled(false);
        return;
      }
      paramBoolean2 = true;
      int i1;
      if (paramInt1 < 0)
      {
        i1 = 0;
      }
      else
      {
        i1 = paramInt1;
        if (paramInt1 >= this.i.getCount()) {
          i1 = this.i.getCount() - 1;
        }
      }
      paramInt1 = this.A;
      if ((i1 > this.j + paramInt1) || (i1 < this.j - paramInt1))
      {
        paramInt1 = 0;
        while (paramInt1 < this.e.size())
        {
          ((ItemInfo)this.e.get(paramInt1)).c = true;
          paramInt1 += 1;
        }
      }
      if (this.j == i1) {
        paramBoolean2 = false;
      }
      if (this.U)
      {
        this.j = i1;
        if (paramBoolean2) {
          d(i1);
        }
        requestLayout();
        return;
      }
      a(i1);
      a(i1, paramBoolean1, paramInt2, paramBoolean2);
      return;
    }
    setScrollingCacheEnabled(false);
  }
  
  protected boolean a(View paramView, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramView instanceof ViewGroup))
    {
      ViewGroup localViewGroup = (ViewGroup)paramView;
      int i2 = paramView.getScrollX();
      int i3 = paramView.getScrollY();
      int i1 = localViewGroup.getChildCount() - 1;
      while (i1 >= 0)
      {
        View localView = localViewGroup.getChildAt(i1);
        int i4 = paramInt2 + i2;
        if ((i4 >= localView.getLeft()) && (i4 < localView.getRight()))
        {
          int i5 = paramInt3 + i3;
          if ((i5 >= localView.getTop()) && (i5 < localView.getBottom()) && (a(localView, true, paramInt1, i4 - localView.getLeft(), i5 - localView.getTop()))) {
            return true;
          }
        }
        i1 -= 1;
      }
    }
    return (paramBoolean) && (paramView.canScrollHorizontally(-paramInt1));
  }
  
  public void addFocusables(ArrayList<View> paramArrayList, int paramInt1, int paramInt2)
  {
    int i2 = paramArrayList.size();
    int i3 = getDescendantFocusability();
    if (i3 != 393216)
    {
      int i1 = 0;
      while (i1 < getChildCount())
      {
        View localView = getChildAt(i1);
        if (localView.getVisibility() == 0)
        {
          ItemInfo localItemInfo = a(localView);
          if ((localItemInfo != null) && (localItemInfo.b == this.j)) {
            localView.addFocusables(paramArrayList, paramInt1, paramInt2);
          }
        }
        i1 += 1;
      }
    }
    if ((i3 != 262144) || (i2 == paramArrayList.size()))
    {
      if (!isFocusable()) {
        return;
      }
      if (((paramInt2 & 0x1) == 1) && (isInTouchMode()) && (!isFocusableInTouchMode())) {
        return;
      }
      if (paramArrayList != null) {
        paramArrayList.add(this);
      }
    }
  }
  
  public void addOnPageChangeListener(OnPageChangeListener paramOnPageChangeListener)
  {
    if (this.ab == null) {
      this.ab = new ArrayList();
    }
    this.ab.add(paramOnPageChangeListener);
  }
  
  public void addTouchables(ArrayList<View> paramArrayList)
  {
    int i1 = 0;
    while (i1 < getChildCount())
    {
      View localView = getChildAt(i1);
      if (localView.getVisibility() == 0)
      {
        ItemInfo localItemInfo = a(localView);
        if ((localItemInfo != null) && (localItemInfo.b == this.j)) {
          localView.addTouchables(paramArrayList);
        }
      }
      i1 += 1;
    }
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    ViewGroup.LayoutParams localLayoutParams = paramLayoutParams;
    if (!checkLayoutParams(paramLayoutParams)) {
      localLayoutParams = generateLayoutParams(paramLayoutParams);
    }
    paramLayoutParams = (LayoutParams)localLayoutParams;
    paramLayoutParams.isDecor |= paramView instanceof Decor;
    if (this.x)
    {
      if ((paramLayoutParams != null) && (paramLayoutParams.isDecor)) {
        throw new IllegalStateException("Cannot add page decor view during layout");
      }
      paramLayoutParams.b = true;
      addViewInLayout(paramView, paramInt, localLayoutParams);
      return;
    }
    super.addView(paramView, paramInt, localLayoutParams);
  }
  
  public boolean arrowScroll(int paramInt)
  {
    View localView2 = findFocus();
    boolean bool = false;
    View localView1 = null;
    Object localObject;
    int i1;
    if (localView2 == this)
    {
      localObject = localView1;
    }
    else
    {
      if (localView2 != null)
      {
        for (localObject = localView2.getParent(); (localObject instanceof ViewGroup); localObject = ((ViewParent)localObject).getParent()) {
          if (localObject == this)
          {
            i1 = 1;
            break label70;
          }
        }
        i1 = 0;
        label70:
        if (i1 == 0)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(localView2.getClass().getSimpleName());
          for (localObject = localView2.getParent(); (localObject instanceof ViewGroup); localObject = ((ViewParent)localObject).getParent())
          {
            localStringBuilder.append(" => ");
            localStringBuilder.append(localObject.getClass().getSimpleName());
          }
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("arrowScroll tried to find focus based on non-child current focused view ");
          ((StringBuilder)localObject).append(localStringBuilder.toString());
          Log.e("PageView", ((StringBuilder)localObject).toString());
          localObject = localView1;
          break label199;
        }
      }
      localObject = localView2;
    }
    label199:
    localView1 = FocusFinder.getInstance().findNextFocus(this, (View)localObject, paramInt);
    int i2;
    if ((localView1 != null) && (localView1 != localObject)) {
      if (paramInt == 17)
      {
        i1 = a(this.g, localView1).left;
        i2 = a(this.g, (View)localObject).left;
        if ((localObject != null) && (i1 >= i2)) {
          bool = d();
        } else {
          bool = localView1.requestFocus();
        }
      }
    }
    for (;;)
    {
      break label379;
      if (paramInt == 66)
      {
        i1 = a(this.g, localView1).left;
        i2 = a(this.g, (View)localObject).left;
        if ((localObject == null) || (i1 > i2)) {
          break;
        }
        bool = e();
        continue;
        if ((paramInt != 17) && (paramInt != 1))
        {
          if ((paramInt == 66) || (paramInt == 2)) {
            bool = e();
          }
        }
        else {
          bool = d();
        }
      }
    }
    label379:
    if (bool) {
      playSoundEffect(SoundEffectConstants.getContantForFocusDirection(paramInt));
    }
    return bool;
  }
  
  ItemInfo b(int paramInt)
  {
    int i1 = 0;
    while (i1 < this.e.size())
    {
      ItemInfo localItemInfo = (ItemInfo)this.e.get(i1);
      if (localItemInfo.b == paramInt) {
        return localItemInfo;
      }
      i1 += 1;
    }
    return null;
  }
  
  ItemInfo b(View paramView)
  {
    for (;;)
    {
      ViewParent localViewParent = paramView.getParent();
      if (localViewParent == this) {
        break label34;
      }
      if ((localViewParent == null) || (!(localViewParent instanceof View))) {
        break;
      }
      paramView = (View)localViewParent;
    }
    return null;
    label34:
    return a(paramView);
  }
  
  void b()
  {
    int i9 = this.i.getCount();
    this.h = i9;
    if ((this.e.size() < this.A * 2 + 1) && (this.e.size() < i9)) {
      i1 = 1;
    } else {
      i1 = 0;
    }
    int i2 = this.j;
    int i6 = i1;
    int i1 = i2;
    int i3 = 0;
    i2 = 0;
    Object localObject;
    while (i3 < this.e.size())
    {
      localObject = (ItemInfo)this.e.get(i3);
      int i8 = this.i.getItemPosition(((ItemInfo)localObject).a);
      int i4;
      int i5;
      int i7;
      if (i8 == -1)
      {
        i4 = i3;
        i5 = i2;
        i7 = i1;
      }
      else
      {
        if (i8 == -2)
        {
          this.e.remove(i3);
          i5 = i3 - 1;
          i4 = i2;
          if (i2 == 0)
          {
            this.i.startUpdate(this);
            i4 = 1;
          }
          this.i.destroyItem(this, ((ItemInfo)localObject).b, ((ItemInfo)localObject).a);
          i3 = i5;
          i2 = i4;
          if (this.j == ((ItemInfo)localObject).b)
          {
            i1 = Math.max(0, Math.min(this.j, i9 - 1));
            i2 = i4;
            i3 = i5;
          }
        }
        for (;;)
        {
          i6 = 1;
          i4 = i3;
          i5 = i2;
          i7 = i1;
          break;
          i4 = i3;
          i5 = i2;
          i7 = i1;
          if (((ItemInfo)localObject).b == i8) {
            break;
          }
          if (((ItemInfo)localObject).b == this.j) {
            i1 = i8;
          }
          ((ItemInfo)localObject).b = i8;
        }
      }
      i3 = i4 + 1;
      i2 = i5;
      i1 = i7;
    }
    if (i2 != 0) {
      this.i.finishUpdate(this);
    }
    Collections.sort(this.e, b);
    if (i6 != 0)
    {
      i3 = getChildCount();
      i2 = 0;
      while (i2 < i3)
      {
        localObject = (LayoutParams)getChildAt(i2).getLayoutParams();
        if (!((LayoutParams)localObject).isDecor) {
          ((LayoutParams)localObject).a = 0.0F;
        }
        i2 += 1;
      }
      a(i1, false, true);
      requestLayout();
    }
  }
  
  public boolean beginFakeDrag()
  {
    if (this.B) {
      return false;
    }
    this.Q = true;
    setScrollState(1);
    this.G = 0.0F;
    this.I = 0.0F;
    if (this.L == null) {
      this.L = VelocityTracker.obtain();
    } else {
      this.L.clear();
    }
    long l1 = SystemClock.uptimeMillis();
    MotionEvent localMotionEvent = MotionEvent.obtain(l1, l1, 0, 0.0F, 0.0F, 0);
    this.L.addMovement(localMotionEvent);
    localMotionEvent.recycle();
    this.R = l1;
    return true;
  }
  
  void c()
  {
    a(this.j);
  }
  
  public boolean canScrollHorizontally(int paramInt)
  {
    BasePageAdapter localBasePageAdapter = this.i;
    boolean bool2 = false;
    boolean bool1 = false;
    if (localBasePageAdapter == null) {
      return false;
    }
    int i1 = getClientWidth();
    int i2 = getScrollX();
    if (paramInt < 0)
    {
      if (i2 > (int)(i1 * this.t)) {
        bool1 = true;
      }
      return bool1;
    }
    bool1 = bool2;
    if (paramInt > 0)
    {
      bool1 = bool2;
      if (i2 < (int)(i1 * this.u)) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return ((paramLayoutParams instanceof LayoutParams)) && (super.checkLayoutParams(paramLayoutParams));
  }
  
  public void clearOnPageChangeListeners()
  {
    if (this.ab != null) {
      this.ab.clear();
    }
  }
  
  public void computeScroll()
  {
    if ((!this.n.isFinished()) && (this.n.computeScrollOffset()))
    {
      int i1 = getScrollX();
      int i2 = getScrollY();
      int i3 = this.n.getCurrX();
      int i4 = this.n.getCurrY();
      if ((i1 != i3) || (i2 != i4))
      {
        scrollTo(i3, i4);
        if (!c(i3))
        {
          this.n.abortAnimation();
          scrollTo(0, i4);
        }
      }
      postInvalidateOnAnimation();
      return;
    }
    a(true);
  }
  
  boolean d()
  {
    if (this.j > 0)
    {
      setCurrentItem(this.j - 1, true);
      return true;
    }
    return false;
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    return (super.dispatchKeyEvent(paramKeyEvent)) || (executeKeyEvent(paramKeyEvent));
  }
  
  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    if (paramAccessibilityEvent.getEventType() == 4096) {
      return super.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent);
    }
    int i2 = getChildCount();
    int i1 = 0;
    while (i1 < i2)
    {
      View localView = getChildAt(i1);
      if (localView.getVisibility() == 0)
      {
        ItemInfo localItemInfo = a(localView);
        if ((localItemInfo != null) && (localItemInfo.b == this.j) && (localView.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent))) {
          return true;
        }
      }
      i1 += 1;
    }
    return false;
  }
  
  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    int i4 = getOverScrollMode();
    int i3 = 0;
    int i1 = 0;
    boolean bool;
    if ((i4 != 0) && ((i4 != 1) || (this.i == null) || (this.i.getCount() <= 1)))
    {
      this.S.finish();
      this.T.finish();
    }
    else
    {
      int i2;
      if (!this.S.isFinished())
      {
        i3 = paramCanvas.save();
        i1 = getHeight() - getPaddingTop() - getPaddingBottom();
        i4 = getWidth();
        paramCanvas.rotate(270.0F);
        paramCanvas.translate(-i1 + getPaddingTop(), this.t * i4);
        this.S.setSize(i1, i4);
        i2 = false | this.S.draw(paramCanvas);
        paramCanvas.restoreToCount(i3);
      }
      i3 = i2;
      if (!this.T.isFinished())
      {
        i4 = paramCanvas.save();
        i3 = getWidth();
        int i5 = getHeight();
        int i6 = getPaddingTop();
        int i7 = getPaddingBottom();
        paramCanvas.rotate(90.0F);
        paramCanvas.translate(-getPaddingTop(), -(this.u + 1.0F) * i3);
        this.T.setSize(i5 - i6 - i7, i3);
        bool = i2 | this.T.draw(paramCanvas);
        paramCanvas.restoreToCount(i4);
      }
    }
    if (bool) {
      postInvalidateOnAnimation();
    }
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    Drawable localDrawable = this.q;
    if ((localDrawable != null) && (localDrawable.isStateful())) {
      localDrawable.setState(getDrawableState());
    }
  }
  
  boolean e()
  {
    if ((this.i != null) && (this.j < this.i.getCount() - 1))
    {
      setCurrentItem(this.j + 1, true);
      return true;
    }
    return false;
  }
  
  public void endFakeDrag()
  {
    if (!this.Q) {
      throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }
    Object localObject = this.L;
    ((VelocityTracker)localObject).computeCurrentVelocity(1000, this.N);
    int i1 = (int)((VelocityTracker)localObject).getXVelocity(this.K);
    this.z = true;
    int i2 = getClientWidth();
    int i3 = getScrollX();
    localObject = j();
    a(a(((ItemInfo)localObject).b, (i3 / i2 - ((ItemInfo)localObject).e) / ((ItemInfo)localObject).d, i1, (int)(this.G - this.I)), true, true, i1);
    k();
    this.Q = false;
  }
  
  public boolean executeKeyEvent(KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getAction() == 0)
    {
      int i1 = paramKeyEvent.getKeyCode();
      if (i1 != 61) {
        switch (i1)
        {
        default: 
          break;
        case 22: 
          i1 = 66;
          break;
        }
      }
      for (i1 = 17;; i1 = 2)
      {
        return arrowScroll(i1);
        if (Build.VERSION.SDK_INT < 11) {
          break label92;
        }
        if (!paramKeyEvent.hasNoModifiers()) {
          break;
        }
      }
      if (paramKeyEvent.hasModifiers(1)) {
        return arrowScroll(1);
      }
    }
    label92:
    return false;
  }
  
  public void fakeDragBy(float paramFloat)
  {
    if (!this.Q) {
      throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }
    this.G += paramFloat;
    float f2 = getScrollX() - paramFloat;
    float f3 = getClientWidth();
    paramFloat = this.t * f3;
    float f1 = this.u * f3;
    Object localObject = (ItemInfo)this.e.get(0);
    ItemInfo localItemInfo = (ItemInfo)this.e.get(this.e.size() - 1);
    if (((ItemInfo)localObject).b != 0) {
      paramFloat = ((ItemInfo)localObject).e * f3;
    }
    if (localItemInfo.b != this.i.getCount() - 1) {
      f1 = localItemInfo.e * f3;
    }
    if (f2 >= paramFloat)
    {
      paramFloat = f2;
      if (f2 > f1) {
        paramFloat = f1;
      }
    }
    f1 = this.G;
    int i1 = (int)paramFloat;
    this.G = (f1 + (paramFloat - i1));
    scrollTo(i1, getScrollY());
    c(i1);
    long l1 = SystemClock.uptimeMillis();
    localObject = MotionEvent.obtain(this.R, l1, 2, this.G, 0.0F, 0);
    this.L.addMovement((MotionEvent)localObject);
    ((MotionEvent)localObject).recycle();
  }
  
  protected ViewGroup.LayoutParams generateDefaultLayoutParams()
  {
    return new LayoutParams();
  }
  
  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return generateDefaultLayoutParams();
  }
  
  public BasePageAdapter getAdapter()
  {
    return this.i;
  }
  
  protected int getChildDrawingOrder(int paramInt1, int paramInt2)
  {
    int i1 = paramInt2;
    if (this.ah == 2) {
      i1 = paramInt1 - 1 - paramInt2;
    }
    return ((LayoutParams)((View)this.ai.get(i1)).getLayoutParams()).d;
  }
  
  public int getCurrentItem()
  {
    return this.j;
  }
  
  public int getOffscreenPageLimit()
  {
    return this.A;
  }
  
  public int getPageMargin()
  {
    return this.p;
  }
  
  public boolean isFakeDragging()
  {
    return this.Q;
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    this.U = true;
  }
  
  protected void onDetachedFromWindow()
  {
    removeCallbacks(this.ak);
    super.onDetachedFromWindow();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if ((this.p > 0) && (this.q != null) && (this.e.size() > 0) && (this.i != null))
    {
      int i3 = getScrollX();
      int i4 = getWidth();
      float f1 = this.p;
      float f4 = i4;
      float f2 = f1 / f4;
      Object localObject = this.e;
      int i2 = 0;
      localObject = (ItemInfo)((ArrayList)localObject).get(0);
      f1 = ((ItemInfo)localObject).e;
      int i5 = this.e.size();
      int i1 = ((ItemInfo)localObject).b;
      int i6 = ((ItemInfo)this.e.get(i5 - 1)).b;
      while (i1 < i6)
      {
        while ((i1 > ((ItemInfo)localObject).b) && (i2 < i5))
        {
          localObject = this.e;
          i2 += 1;
          localObject = (ItemInfo)((ArrayList)localObject).get(i2);
        }
        float f3;
        float f5;
        if (i1 == ((ItemInfo)localObject).b)
        {
          f3 = ((ItemInfo)localObject).e;
          float f6 = ((ItemInfo)localObject).d;
          f1 = ((ItemInfo)localObject).e;
          f5 = ((ItemInfo)localObject).d;
          f3 = (f3 + f6) * f4;
          f1 = f1 + f5 + f2;
        }
        else
        {
          f5 = this.i.getPageWidth(i1);
          f3 = (f1 + f5) * f4;
          f1 += f5 + f2;
        }
        if (this.p + f3 > i3)
        {
          this.q.setBounds((int)f3, this.r, (int)(this.p + f3 + 0.5F), this.s);
          this.q.draw(paramCanvas);
        }
        if (f3 > i3 + i4) {
          return;
        }
        i1 += 1;
      }
    }
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (!this.al) {
      return false;
    }
    int i1 = paramMotionEvent.getAction() & 0xFF;
    if ((i1 != 3) && (i1 != 1))
    {
      if (i1 != 0)
      {
        if (this.B) {
          return true;
        }
        if (this.C) {
          return false;
        }
      }
      float f1;
      if (i1 != 0)
      {
        if (i1 != 2)
        {
          if (i1 == 6) {
            a(paramMotionEvent);
          }
        }
        else
        {
          i1 = this.K;
          if (i1 != -1)
          {
            i1 = paramMotionEvent.findPointerIndex(i1);
            float f2 = paramMotionEvent.getX(i1);
            f1 = f2 - this.G;
            float f4 = Math.abs(f1);
            float f3 = paramMotionEvent.getY(i1);
            float f5 = Math.abs(f3 - this.J);
            if ((f1 != 0.0F) && (!a(this.G, f1)) && (a(this, false, (int)f1, (int)f2, (int)f3)))
            {
              this.G = f2;
              this.H = f3;
              this.C = true;
              return false;
            }
            if ((f4 > this.F) && (f4 * 0.5F > f5))
            {
              this.B = true;
              c(true);
              setScrollState(1);
              if (f1 > 0.0F) {
                f1 = this.I + this.F;
              } else {
                f1 = this.I - this.F;
              }
              this.G = f1;
              this.H = f3;
              setScrollingCacheEnabled(true);
            }
            else if (f5 > this.F)
            {
              this.C = true;
            }
            if ((this.B) && (b(f2))) {
              postInvalidateOnAnimation();
            }
          }
        }
      }
      else
      {
        f1 = paramMotionEvent.getX();
        this.I = f1;
        this.G = f1;
        f1 = paramMotionEvent.getY();
        this.J = f1;
        this.H = f1;
        this.K = paramMotionEvent.getPointerId(0);
        this.C = false;
        this.n.computeScrollOffset();
        if ((this.aj == 2) && (Math.abs(this.n.getFinalX() - this.n.getCurrX()) > this.P))
        {
          this.n.abortAnimation();
          this.z = false;
          c();
          this.B = true;
          c(true);
          setScrollState(1);
        }
        else
        {
          a(false);
          this.B = false;
        }
      }
      if (this.L == null) {
        this.L = VelocityTracker.obtain();
      }
      this.L.addMovement(paramMotionEvent);
      return this.B;
    }
    i();
    return false;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i8 = getChildCount();
    int i9 = paramInt3 - paramInt1;
    int i10 = paramInt4 - paramInt2;
    paramInt2 = getPaddingLeft();
    paramInt1 = getPaddingTop();
    int i2 = getPaddingRight();
    paramInt4 = getPaddingBottom();
    int i11 = getScrollX();
    int i3 = 0;
    int i4 = 0;
    View localView;
    int i1;
    LayoutParams localLayoutParams;
    while (i4 < i8)
    {
      localView = getChildAt(i4);
      i1 = paramInt2;
      int i7 = i2;
      int i6 = paramInt1;
      int i5 = paramInt4;
      paramInt3 = i3;
      if (localView.getVisibility() != 8)
      {
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        i1 = paramInt2;
        i7 = i2;
        i6 = paramInt1;
        i5 = paramInt4;
        paramInt3 = i3;
        if (localLayoutParams.isDecor)
        {
          paramInt3 = localLayoutParams.gravity & 0x7;
          i5 = localLayoutParams.gravity & 0x70;
          if (paramInt3 != 1)
          {
            if (paramInt3 != 3)
            {
              if (paramInt3 != 5)
              {
                paramInt3 = paramInt2;
                i1 = paramInt2;
              }
              else
              {
                paramInt3 = i9 - i2 - localView.getMeasuredWidth();
                i2 += localView.getMeasuredWidth();
                i1 = paramInt2;
              }
            }
            else
            {
              i1 = localView.getMeasuredWidth();
              paramInt3 = paramInt2;
              i1 += paramInt2;
            }
          }
          else
          {
            paramInt3 = Math.max((i9 - localView.getMeasuredWidth()) / 2, paramInt2);
            i1 = paramInt2;
          }
          if (i5 != 16)
          {
            if (i5 != 48)
            {
              if (i5 != 80)
              {
                paramInt2 = paramInt1;
              }
              else
              {
                paramInt2 = i10 - paramInt4 - localView.getMeasuredHeight();
                paramInt4 += localView.getMeasuredHeight();
              }
            }
            else
            {
              i5 = localView.getMeasuredHeight();
              paramInt2 = paramInt1;
              paramInt1 = i5 + paramInt1;
            }
          }
          else {
            paramInt2 = Math.max((i10 - localView.getMeasuredHeight()) / 2, paramInt1);
          }
          paramInt3 += i11;
          localView.layout(paramInt3, paramInt2, localView.getMeasuredWidth() + paramInt3, paramInt2 + localView.getMeasuredHeight());
          paramInt3 = i3 + 1;
          i5 = paramInt4;
          i6 = paramInt1;
          i7 = i2;
        }
      }
      i4 += 1;
      paramInt2 = i1;
      i2 = i7;
      paramInt1 = i6;
      paramInt4 = i5;
      i3 = paramInt3;
    }
    paramInt3 = 0;
    while (paramInt3 < i8)
    {
      localView = getChildAt(paramInt3);
      if (localView.getVisibility() != 8)
      {
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if (!localLayoutParams.isDecor)
        {
          ItemInfo localItemInfo = a(localView);
          if (localItemInfo != null)
          {
            float f1 = i9 - paramInt2 - i2;
            i1 = (int)(localItemInfo.e * f1) + paramInt2;
            if (localLayoutParams.b)
            {
              localLayoutParams.b = false;
              localView.measure(View.MeasureSpec.makeMeasureSpec((int)(f1 * localLayoutParams.a), 1073741824), View.MeasureSpec.makeMeasureSpec(i10 - paramInt1 - paramInt4, 1073741824));
            }
            localView.layout(i1, paramInt1, localView.getMeasuredWidth() + i1, localView.getMeasuredHeight() + paramInt1);
          }
        }
      }
      paramInt3 += 1;
    }
    this.r = paramInt1;
    this.s = (i10 - paramInt4);
    this.aa = i3;
    if (this.U) {
      a(this.j, false, 0, true);
    }
    this.U = false;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(getDefaultSize(0, paramInt1), getDefaultSize(0, paramInt2));
    paramInt1 = getMeasuredWidth();
    this.E = Math.min(paramInt1 / 10, this.D);
    int i1 = getPaddingLeft();
    int i2 = getPaddingRight();
    paramInt2 = getMeasuredHeight();
    int i3 = getPaddingTop();
    int i4 = getPaddingBottom();
    int i9 = getChildCount();
    paramInt2 = paramInt2 - i3 - i4;
    paramInt1 = paramInt1 - i1 - i2;
    i3 = 0;
    View localView;
    LayoutParams localLayoutParams;
    for (;;)
    {
      int i6 = 1;
      int i8 = 1073741824;
      if (i3 >= i9) {
        break;
      }
      localView = getChildAt(i3);
      i1 = paramInt1;
      i2 = paramInt2;
      if (localView.getVisibility() != 8)
      {
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        i1 = paramInt1;
        i2 = paramInt2;
        if (localLayoutParams != null)
        {
          i1 = paramInt1;
          i2 = paramInt2;
          if (localLayoutParams.isDecor)
          {
            i1 = localLayoutParams.gravity & 0x7;
            i2 = localLayoutParams.gravity & 0x70;
            int i5;
            if ((i2 != 48) && (i2 != 80)) {
              i5 = 0;
            } else {
              i5 = 1;
            }
            i4 = i6;
            if (i1 != 3) {
              if (i1 == 5) {
                i4 = i6;
              } else {
                i4 = 0;
              }
            }
            i6 = Integer.MIN_VALUE;
            if (i5 != 0) {
              i2 = 1073741824;
            }
            do
            {
              i1 = Integer.MIN_VALUE;
              break;
              i2 = i6;
            } while (i4 == 0);
            i1 = 1073741824;
            i2 = i6;
            int i7;
            if (localLayoutParams.width != -2)
            {
              if (localLayoutParams.width != -1) {
                i2 = localLayoutParams.width;
              } else {
                i2 = paramInt1;
              }
              i6 = 1073741824;
              i7 = i2;
            }
            else
            {
              i7 = paramInt1;
              i6 = i2;
            }
            if (localLayoutParams.height != -2)
            {
              if (localLayoutParams.height != -1) {
                i1 = localLayoutParams.height;
              } else {
                i1 = paramInt2;
              }
            }
            else
            {
              i2 = paramInt2;
              i8 = i1;
              i1 = i2;
            }
            localView.measure(View.MeasureSpec.makeMeasureSpec(i7, i6), View.MeasureSpec.makeMeasureSpec(i1, i8));
            if (i5 != 0)
            {
              i2 = paramInt2 - localView.getMeasuredHeight();
              i1 = paramInt1;
            }
            else
            {
              i1 = paramInt1;
              i2 = paramInt2;
              if (i4 != 0)
              {
                i1 = paramInt1 - localView.getMeasuredWidth();
                i2 = paramInt2;
              }
            }
          }
        }
      }
      i3 += 1;
      paramInt1 = i1;
      paramInt2 = i2;
    }
    this.v = View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824);
    this.w = View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824);
    this.x = true;
    c();
    paramInt2 = 0;
    this.x = false;
    i1 = getChildCount();
    while (paramInt2 < i1)
    {
      localView = getChildAt(paramInt2);
      if (localView.getVisibility() != 8)
      {
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if ((localLayoutParams == null) || (!localLayoutParams.isDecor)) {
          localView.measure(View.MeasureSpec.makeMeasureSpec((int)(paramInt1 * localLayoutParams.a), 1073741824), this.w);
        }
      }
      paramInt2 += 1;
    }
  }
  
  protected boolean onRequestFocusInDescendants(int paramInt, Rect paramRect)
  {
    int i1 = getChildCount();
    int i3 = -1;
    int i2;
    if ((paramInt & 0x2) != 0)
    {
      i3 = i1;
      i1 = 0;
      i2 = 1;
    }
    else
    {
      i1 -= 1;
      i2 = -1;
    }
    while (i1 != i3)
    {
      View localView = getChildAt(i1);
      if (localView.getVisibility() == 0)
      {
        ItemInfo localItemInfo = a(localView);
        if ((localItemInfo != null) && (localItemInfo.b == this.j) && (localView.requestFocus(paramInt, paramRect))) {
          return true;
        }
      }
      i1 += i2;
    }
    return false;
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof SavedState))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    if (this.i != null)
    {
      this.i.restoreState(paramParcelable.b, paramParcelable.c);
      a(paramParcelable.a, false, true);
      return;
    }
    this.k = paramParcelable.a;
    this.l = paramParcelable.b;
    this.m = paramParcelable.c;
  }
  
  public Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    localSavedState.a = this.j;
    if (this.i != null) {
      localSavedState.b = this.i.saveState();
    }
    return localSavedState;
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramInt1 != paramInt3) {
      a(paramInt1, paramInt3, this.p, this.p);
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.Q) {
      return true;
    }
    int i1 = paramMotionEvent.getAction();
    boolean bool = false;
    if ((i1 == 0) && (paramMotionEvent.getEdgeFlags() != 0)) {
      return false;
    }
    if (this.i != null)
    {
      if (this.i.getCount() == 0) {
        return false;
      }
      if (this.L == null) {
        this.L = VelocityTracker.obtain();
      }
      this.L.addMovement(paramMotionEvent);
      Object localObject;
      switch (paramMotionEvent.getAction() & 0xFF)
      {
      case 4: 
      default: 
        break;
      case 6: 
        a(paramMotionEvent);
        this.G = paramMotionEvent.getX(paramMotionEvent.findPointerIndex(this.K));
        break;
      case 5: 
        i1 = paramMotionEvent.getActionIndex();
        this.G = paramMotionEvent.getX(i1);
        i1 = paramMotionEvent.getPointerId(i1);
        break;
      case 3: 
        if (!this.B) {
          break label580;
        }
        a(this.j, true, 0, false);
        break;
      case 2: 
        if (!this.B)
        {
          i1 = paramMotionEvent.findPointerIndex(this.K);
          if (i1 == -1) {
            break;
          }
          f1 = paramMotionEvent.getX(i1);
          float f3 = Math.abs(f1 - this.G);
          float f2 = paramMotionEvent.getY(i1);
          float f4 = Math.abs(f2 - this.H);
          if ((f3 > this.F) && (f3 > f4))
          {
            this.B = true;
            c(true);
            if (f1 - this.I > 0.0F) {
              f1 = this.I + this.F;
            } else {
              f1 = this.I - this.F;
            }
            this.G = f1;
            this.H = f2;
            setScrollState(1);
            setScrollingCacheEnabled(true);
            localObject = getParent();
            if (localObject != null) {
              ((ViewParent)localObject).requestDisallowInterceptTouchEvent(true);
            }
          }
        }
        if (!this.B) {
          break label580;
        }
        bool = false | b(paramMotionEvent.getX(paramMotionEvent.findPointerIndex(this.K)));
        break;
      case 1: 
        if (!this.B) {
          break label580;
        }
        localObject = this.L;
        ((VelocityTracker)localObject).computeCurrentVelocity(1000, this.N);
        i1 = (int)((VelocityTracker)localObject).getXVelocity(this.K);
        this.z = true;
        int i2 = getClientWidth();
        int i3 = getScrollX();
        localObject = j();
        a(a(((ItemInfo)localObject).b, (i3 / i2 - ((ItemInfo)localObject).e) / ((ItemInfo)localObject).d, i1, (int)(paramMotionEvent.getX(paramMotionEvent.findPointerIndex(this.K)) - this.I)), true, true, i1);
        bool = i();
        break;
      }
      this.n.abortAnimation();
      this.z = false;
      c();
      float f1 = paramMotionEvent.getX();
      this.I = f1;
      this.G = f1;
      f1 = paramMotionEvent.getY();
      this.J = f1;
      this.H = f1;
      i1 = paramMotionEvent.getPointerId(0);
      this.K = i1;
      label580:
      if (bool) {
        postInvalidateOnAnimation();
      }
      return true;
    }
    return false;
  }
  
  public void removeOnPageChangeListener(OnPageChangeListener paramOnPageChangeListener)
  {
    if (this.ab != null) {
      this.ab.remove(paramOnPageChangeListener);
    }
  }
  
  public void removeView(View paramView)
  {
    if (this.x)
    {
      removeViewInLayout(paramView);
      return;
    }
    super.removeView(paramView);
  }
  
  public void setAdapter(BasePageAdapter paramBasePageAdapter)
  {
    if (this.i != null)
    {
      this.i.unregisterDataSetObserver(this.o);
      this.i.startUpdate(this);
      int i1 = 0;
      while (i1 < this.e.size())
      {
        localObject = (ItemInfo)this.e.get(i1);
        this.i.destroyItem(this, ((ItemInfo)localObject).b, ((ItemInfo)localObject).a);
        i1 += 1;
      }
      this.i.finishUpdate(this);
      this.e.clear();
      g();
      this.j = 0;
      scrollTo(0, 0);
    }
    Object localObject = this.i;
    this.i = paramBasePageAdapter;
    this.h = 0;
    if (this.i != null)
    {
      if (this.o == null) {
        this.o = new PageObserver(null);
      }
      this.i.registerDataSetObserver(this.o);
      this.z = false;
      boolean bool = this.U;
      this.U = true;
      this.h = this.i.getCount();
      if (this.k >= 0)
      {
        this.i.restoreState(this.l, this.m);
        a(this.k, false, true);
        this.k = -1;
        this.l = null;
        this.m = null;
      }
      else if (!bool)
      {
        c();
      }
      else
      {
        requestLayout();
      }
    }
    if ((this.ae != null) && (localObject != paramBasePageAdapter)) {
      this.ae.onAdapterChanged((BasePageAdapter)localObject, paramBasePageAdapter);
    }
  }
  
  void setChildrenDrawingOrderEnabledCompat(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT >= 7)
    {
      if (this.ag == null) {
        try
        {
          this.ag = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", new Class[] { Boolean.TYPE });
        }
        catch (NoSuchMethodException localNoSuchMethodException)
        {
          Log.e("PageView", "Can't find setChildrenDrawingOrderEnabled", localNoSuchMethodException);
        }
      }
      try
      {
        this.ag.invoke(this, new Object[] { Boolean.valueOf(paramBoolean) });
        return;
      }
      catch (Exception localException)
      {
        Log.e("PageView", "Error changing children drawing order", localException);
      }
    }
  }
  
  public void setCurrentItem(int paramInt)
  {
    this.z = false;
    a(paramInt, this.U ^ true, false);
  }
  
  public void setCurrentItem(int paramInt, boolean paramBoolean)
  {
    this.z = false;
    a(paramInt, paramBoolean, false);
  }
  
  public void setOffscreenPageLimit(int paramInt)
  {
    int i1 = paramInt;
    if (paramInt < 1)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Requested offscreen page limit ");
      localStringBuilder.append(paramInt);
      localStringBuilder.append(" too small; defaulting to ");
      localStringBuilder.append(1);
      Log.w("PageView", localStringBuilder.toString());
      i1 = 1;
    }
    if (i1 != this.A)
    {
      this.A = i1;
      c();
    }
  }
  
  void setOnAdapterChangeListener(OnAdapterChangeListener paramOnAdapterChangeListener)
  {
    this.ae = paramOnAdapterChangeListener;
  }
  
  public void setOnPageChangeListener(OnPageChangeListener paramOnPageChangeListener)
  {
    this.ac = paramOnPageChangeListener;
  }
  
  public void setPageMargin(int paramInt)
  {
    int i1 = this.p;
    this.p = paramInt;
    int i2 = getWidth();
    a(i2, i2, paramInt, i1);
    requestLayout();
  }
  
  public void setPageMarginDrawable(int paramInt)
  {
    setPageMarginDrawable(getContext().getResources().getDrawable(paramInt));
  }
  
  public void setPageMarginDrawable(Drawable paramDrawable)
  {
    this.q = paramDrawable;
    if (paramDrawable != null) {
      refreshDrawableState();
    }
    boolean bool;
    if (paramDrawable == null) {
      bool = true;
    } else {
      bool = false;
    }
    setWillNotDraw(bool);
    invalidate();
  }
  
  public void setPageTransformer(boolean paramBoolean, PageTransformer paramPageTransformer)
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      int i2 = 1;
      boolean bool1;
      if (paramPageTransformer != null) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      boolean bool2;
      if (this.af != null) {
        bool2 = true;
      } else {
        bool2 = false;
      }
      int i1;
      if (bool1 != bool2) {
        i1 = 1;
      } else {
        i1 = 0;
      }
      this.af = paramPageTransformer;
      setChildrenDrawingOrderEnabledCompat(bool1);
      if (bool1)
      {
        if (paramBoolean) {
          i2 = 2;
        }
        this.ah = i2;
      }
      else
      {
        this.ah = 0;
      }
      if (i1 != 0) {
        c();
      }
    }
  }
  
  public void setScrollEnabled(boolean paramBoolean)
  {
    this.al = paramBoolean;
  }
  
  public void setTouchEnabled(boolean paramBoolean)
  {
    this.al = paramBoolean;
  }
  
  public void showPage(int paramInt)
  {
    setCurrentItem(paramInt, true);
  }
  
  protected boolean verifyDrawable(Drawable paramDrawable)
  {
    return (super.verifyDrawable(paramDrawable)) || (paramDrawable == this.q);
  }
  
  static abstract interface Decor {}
  
  static class ItemInfo
  {
    Object a;
    int b;
    boolean c;
    float d;
    float e;
  }
  
  public static class LayoutParams
    extends ViewGroup.LayoutParams
  {
    float a = 0.0F;
    boolean b;
    int c;
    int d;
    public int gravity;
    public boolean isDecor;
    
    public LayoutParams()
    {
      super(-1);
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, PageView.f());
      this.gravity = paramContext.getInteger(0, 48);
      paramContext.recycle();
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super(-1);
    }
  }
  
  static abstract interface OnAdapterChangeListener
  {
    public abstract void onAdapterChanged(BasePageAdapter paramBasePageAdapter1, BasePageAdapter paramBasePageAdapter2);
  }
  
  public static abstract interface OnPageChangeListener
  {
    public abstract void onPageChange(View paramView, int paramInt);
    
    public abstract void onPageScrollStateChanged(int paramInt);
    
    public abstract void onPageScrolled(int paramInt1, float paramFloat, int paramInt2);
    
    public abstract void onPageSelected(int paramInt);
  }
  
  private class PageObserver
    extends DataSetObserver
  {
    private PageObserver() {}
    
    public void onChanged()
    {
      PageView.this.b();
    }
    
    public void onInvalidated()
    {
      PageView.this.b();
    }
  }
  
  public static abstract interface PageTransformer
  {
    public abstract void transformPage(View paramView, float paramFloat);
  }
  
  private class PagerObserver
    extends DataSetObserver
  {
    private PagerObserver() {}
    
    public void onChanged()
    {
      PageView.this.b();
    }
    
    public void onInvalidated()
    {
      PageView.this.b();
    }
  }
  
  public static class SavedState
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator()
    {
      public PageView.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new PageView.SavedState(paramAnonymousParcel);
      }
      
      public PageView.SavedState[] newArray(int paramAnonymousInt)
      {
        return new PageView.SavedState[paramAnonymousInt];
      }
    };
    int a;
    Parcelable b;
    ClassLoader c;
    
    SavedState(Parcel paramParcel)
    {
      super();
      ClassLoader localClassLoader = getClass().getClassLoader();
      this.a = paramParcel.readInt();
      this.b = paramParcel.readParcelable(localClassLoader);
      this.c = localClassLoader;
    }
    
    public SavedState(Parcelable paramParcelable)
    {
      super();
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("FragmentPage.SavedState{");
      localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      localStringBuilder.append(" position=");
      localStringBuilder.append(this.a);
      localStringBuilder.append("}");
      return localStringBuilder.toString();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(this.a);
      paramParcel.writeParcelable(this.b, paramInt);
    }
  }
  
  public static class SimpleOnPageChangeListener
    implements PageView.OnPageChangeListener
  {
    public void onPageChange(View paramView, int paramInt) {}
    
    public void onPageScrollStateChanged(int paramInt) {}
    
    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {}
    
    public void onPageSelected(int paramInt) {}
  }
  
  static class ViewPositionComparator
    implements Comparator<View>
  {
    public int compare(View paramView1, View paramView2)
    {
      paramView1 = (PageView.LayoutParams)paramView1.getLayoutParams();
      paramView2 = (PageView.LayoutParams)paramView2.getLayoutParams();
      if (paramView1.isDecor != paramView2.isDecor)
      {
        if (paramView1.isDecor) {
          return 1;
        }
        return -1;
      }
      return paramView1.c - paramView2.c;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\widget\PageView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */