package android.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowInsets;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

public class DrawerLayout
  extends ViewGroup
  implements DrawerLayoutImpl
{
  public static final int LOCK_MODE_LOCKED_CLOSED = 1;
  public static final int LOCK_MODE_LOCKED_OPEN = 2;
  public static final int LOCK_MODE_UNLOCKED = 0;
  public static final int STATE_DRAGGING = 1;
  public static final int STATE_IDLE = 0;
  public static final int STATE_SETTLING = 2;
  static final DrawerLayoutCompatImpl a;
  private static final int[] b;
  private static final boolean c;
  private static final boolean d;
  private Drawable A;
  private CharSequence B;
  private CharSequence C;
  private Object D;
  private boolean E;
  private Drawable F = null;
  private Drawable G = null;
  private Drawable H = null;
  private Drawable I = null;
  private final ArrayList<View> J;
  private final View.AccessibilityDelegate e = new View.AccessibilityDelegate();
  private float f;
  private int g;
  private int h = -1728053248;
  private float i;
  private Paint j = new Paint();
  private final ViewDragHelper k;
  private final ViewDragHelper l;
  private final ViewDragCallback m;
  private final ViewDragCallback n;
  private int o;
  private boolean p;
  private boolean q = true;
  private int r;
  private int s;
  private boolean t;
  private boolean u;
  private DrawerListener v;
  private float w;
  private float x;
  private Drawable y;
  private Drawable z;
  
  static
  {
    boolean bool2 = true;
    b = new int[] { 16842931 };
    boolean bool1;
    if (Build.VERSION.SDK_INT >= 19) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    c = bool1;
    if (Build.VERSION.SDK_INT >= 21) {
      bool1 = bool2;
    } else {
      bool1 = false;
    }
    d = bool1;
    if (Build.VERSION.SDK_INT >= 21) {}
    for (Object localObject = new DrawerLayoutCompatImplApi21();; localObject = new DrawerLayoutCompatImplBase())
    {
      a = (DrawerLayoutCompatImpl)localObject;
      return;
    }
  }
  
  public DrawerLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public DrawerLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public DrawerLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setDescendantFocusability(262144);
    float f1 = getResources().getDisplayMetrics().density;
    this.g = ((int)(64.0F * f1 + 0.5F));
    float f2 = 400.0F * f1;
    this.m = new ViewDragCallback(3);
    this.n = new ViewDragCallback(5);
    this.k = ViewDragHelper.create(this, 1.0F, this.m);
    this.k.setEdgeTrackingEnabled(1);
    this.k.setMinVelocity(f2);
    this.m.setDragger(this.k);
    this.l = ViewDragHelper.create(this, 1.0F, this.n);
    this.l.setEdgeTrackingEnabled(2);
    this.l.setMinVelocity(f2);
    this.n.setDragger(this.l);
    setFocusableInTouchMode(true);
    setImportantForAccessibility(1);
    setAccessibilityDelegate(new View.AccessibilityDelegate());
    setMotionEventSplittingEnabled(false);
    if (getFitsSystemWindows())
    {
      a.configureApplyInsets(this);
      this.y = a.getDefaultStatusBarBackground(paramContext);
    }
    this.f = (f1 * 10.0F);
    this.J = new ArrayList();
  }
  
  private void a(View paramView, boolean paramBoolean)
  {
    int i3 = getChildCount();
    int i1 = 0;
    while (i1 < i3)
    {
      View localView = getChildAt(i1);
      if (((!paramBoolean) && (!f(localView))) || ((paramBoolean) && (localView == paramView))) {}
      for (int i2 = 1;; i2 = 4)
      {
        localView.setImportantForAccessibility(i2);
        break;
        if (Build.VERSION.SDK_INT < 19) {
          break;
        }
      }
      i1 += 1;
    }
  }
  
  private boolean a(Drawable paramDrawable, int paramInt)
  {
    if (paramDrawable == null) {
      return false;
    }
    if ((Build.VERSION.SDK_INT >= 19) && (!paramDrawable.isAutoMirrored())) {
      return false;
    }
    setLayoutDirection(paramInt);
    return true;
  }
  
  static String b(int paramInt)
  {
    if ((paramInt & 0x3) == 3) {
      return "LEFT";
    }
    if ((paramInt & 0x5) == 5) {
      return "RIGHT";
    }
    return Integer.toHexString(paramInt);
  }
  
  private void d()
  {
    if (d) {
      return;
    }
    this.z = e();
    this.A = f();
  }
  
  private Drawable e()
  {
    int i1 = getLayoutDirection();
    if (i1 == 0)
    {
      if (this.F != null)
      {
        a(this.F, i1);
        return this.F;
      }
    }
    else if (this.G != null)
    {
      a(this.G, i1);
      return this.G;
    }
    return this.H;
  }
  
  private Drawable f()
  {
    int i1 = getLayoutDirection();
    if (i1 == 0)
    {
      if (this.G != null)
      {
        a(this.G, i1);
        return this.G;
      }
    }
    else if (this.F != null)
    {
      a(this.F, i1);
      return this.F;
    }
    return this.I;
  }
  
  private boolean g()
  {
    int i2 = getChildCount();
    int i1 = 0;
    while (i1 < i2)
    {
      if (((LayoutParams)getChildAt(i1).getLayoutParams()).b) {
        return true;
      }
      i1 += 1;
    }
    return false;
  }
  
  private static boolean g(View paramView)
  {
    paramView = paramView.getBackground();
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramView != null)
    {
      bool1 = bool2;
      if (paramView.getOpacity() == -1) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private boolean h()
  {
    return i() != null;
  }
  
  private View i()
  {
    int i2 = getChildCount();
    int i1 = 0;
    while (i1 < i2)
    {
      View localView = getChildAt(i1);
      if ((f(localView)) && (isDrawerVisible(localView))) {
        return localView;
      }
      i1 += 1;
    }
    return null;
  }
  
  View a()
  {
    int i2 = getChildCount();
    int i1 = 0;
    while (i1 < i2)
    {
      View localView = getChildAt(i1);
      if (((LayoutParams)localView.getLayoutParams()).c) {
        return localView;
      }
      i1 += 1;
    }
    return null;
  }
  
  View a(int paramInt)
  {
    int i1 = Gravity.getAbsoluteGravity(paramInt, getLayoutDirection());
    int i2 = getChildCount();
    paramInt = 0;
    while (paramInt < i2)
    {
      View localView = getChildAt(paramInt);
      if ((d(localView) & 0x7) == (i1 & 0x7)) {
        return localView;
      }
      paramInt += 1;
    }
    return null;
  }
  
  void a(int paramInt1, int paramInt2, View paramView)
  {
    int i2 = this.k.getViewDragState();
    int i3 = this.l.getViewDragState();
    int i1 = 2;
    if ((i2 != 1) && (i3 != 1))
    {
      paramInt1 = i1;
      if (i2 != 2) {
        if (i3 == 2) {
          paramInt1 = i1;
        } else {
          paramInt1 = 0;
        }
      }
    }
    else
    {
      paramInt1 = 1;
    }
    if ((paramView != null) && (paramInt2 == 0))
    {
      LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
      if (localLayoutParams.a == 0.0F) {
        a(paramView);
      } else if (localLayoutParams.a == 1.0F) {
        b(paramView);
      }
    }
    if (paramInt1 != this.o)
    {
      this.o = paramInt1;
      if (this.v != null) {
        this.v.onDrawerStateChanged(paramInt1);
      }
    }
  }
  
  void a(View paramView)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    if (localLayoutParams.c)
    {
      localLayoutParams.c = false;
      if (this.v != null) {
        this.v.onDrawerClosed(paramView);
      }
      a(paramView, false);
      if (hasWindowFocus())
      {
        paramView = getRootView();
        if (paramView != null) {
          paramView.sendAccessibilityEvent(32);
        }
      }
    }
  }
  
  void a(View paramView, float paramFloat)
  {
    if (this.v != null) {
      this.v.onDrawerSlide(paramView, paramFloat);
    }
  }
  
  void a(boolean paramBoolean)
  {
    int i4 = getChildCount();
    int i1 = 0;
    int i3;
    for (int i2 = 0; i1 < i4; i2 = i3)
    {
      View localView = getChildAt(i1);
      LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
      i3 = i2;
      if (f(localView)) {
        if ((paramBoolean) && (!localLayoutParams.b))
        {
          i3 = i2;
        }
        else
        {
          i3 = localView.getWidth();
          if (a(localView, 3)) {}
          for (boolean bool = this.k.smoothSlideViewTo(localView, -i3, localView.getTop());; bool = this.l.smoothSlideViewTo(localView, getWidth(), localView.getTop()))
          {
            i3 = i2 | bool;
            break;
          }
          localLayoutParams.b = false;
        }
      }
      i1 += 1;
    }
    this.m.removeCallbacks();
    this.n.removeCallbacks();
    if (i2 != 0) {
      invalidate();
    }
  }
  
  boolean a(View paramView, int paramInt)
  {
    return (d(paramView) & paramInt) == paramInt;
  }
  
  public void addFocusables(ArrayList<View> paramArrayList, int paramInt1, int paramInt2)
  {
    if (getDescendantFocusability() == 393216) {
      return;
    }
    int i4 = getChildCount();
    int i3 = 0;
    int i1 = 0;
    int i2 = 0;
    View localView;
    while (i1 < i4)
    {
      localView = getChildAt(i1);
      if (f(localView))
      {
        if (isDrawerOpen(localView))
        {
          localView.addFocusables(paramArrayList, paramInt1, paramInt2);
          i2 = 1;
        }
      }
      else {
        this.J.add(localView);
      }
      i1 += 1;
    }
    if (i2 == 0)
    {
      i2 = this.J.size();
      i1 = i3;
      while (i1 < i2)
      {
        localView = (View)this.J.get(i1);
        if (localView.getVisibility() == 0) {
          localView.addFocusables(paramArrayList, paramInt1, paramInt2);
        }
        i1 += 1;
      }
    }
    this.J.clear();
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    super.addView(paramView, paramInt, paramLayoutParams);
    if ((a() == null) && (!f(paramView))) {}
    for (paramInt = 1;; paramInt = 4)
    {
      paramView.setImportantForAccessibility(paramInt);
      break;
      if (Build.VERSION.SDK_INT < 19) {
        break;
      }
    }
    if (!c) {
      paramView.setAccessibilityDelegate(this.e);
    }
  }
  
  void b()
  {
    if (!this.u)
    {
      long l1 = SystemClock.uptimeMillis();
      MotionEvent localMotionEvent = MotionEvent.obtain(l1, l1, 3, 0.0F, 0.0F, 0);
      int i2 = getChildCount();
      int i1 = 0;
      while (i1 < i2)
      {
        getChildAt(i1).dispatchTouchEvent(localMotionEvent);
        i1 += 1;
      }
      localMotionEvent.recycle();
      this.u = true;
    }
  }
  
  void b(View paramView)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    if (!localLayoutParams.c)
    {
      localLayoutParams.c = true;
      if (this.v != null) {
        this.v.onDrawerOpened(paramView);
      }
      a(paramView, true);
      if (hasWindowFocus()) {
        sendAccessibilityEvent(32);
      }
      paramView.requestFocus();
    }
  }
  
  void b(View paramView, float paramFloat)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    if (paramFloat == localLayoutParams.a) {
      return;
    }
    localLayoutParams.a = paramFloat;
    a(paramView, paramFloat);
  }
  
  float c(View paramView)
  {
    return ((LayoutParams)paramView.getLayoutParams()).a;
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return ((paramLayoutParams instanceof LayoutParams)) && (super.checkLayoutParams(paramLayoutParams));
  }
  
  public void closeDrawer(int paramInt)
  {
    Object localObject = a(paramInt);
    if (localObject == null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("No drawer view found with gravity ");
      ((StringBuilder)localObject).append(b(paramInt));
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    }
    closeDrawer((View)localObject);
  }
  
  public void closeDrawer(View paramView)
  {
    Object localObject;
    if (!f(paramView))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("View ");
      ((StringBuilder)localObject).append(paramView);
      ((StringBuilder)localObject).append(" is not a sliding drawer");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    }
    if (this.q)
    {
      paramView = (LayoutParams)paramView.getLayoutParams();
      paramView.a = 0.0F;
      paramView.c = false;
    }
    else
    {
      if (a(paramView, 3)) {
        localObject = this.k;
      }
      for (int i1 = -paramView.getWidth();; i1 = getWidth())
      {
        ((ViewDragHelper)localObject).smoothSlideViewTo(paramView, i1, paramView.getTop());
        break;
        localObject = this.l;
      }
    }
    invalidate();
  }
  
  public void closeDrawers()
  {
    a(false);
  }
  
  public void computeScroll()
  {
    int i2 = getChildCount();
    float f1 = 0.0F;
    int i1 = 0;
    while (i1 < i2)
    {
      f1 = Math.max(f1, ((LayoutParams)getChildAt(i1).getLayoutParams()).a);
      i1 += 1;
    }
    this.i = f1;
    if ((this.k.continueSettling(true) | this.l.continueSettling(true))) {
      postInvalidateOnAnimation();
    }
  }
  
  int d(View paramView)
  {
    return Gravity.getAbsoluteGravity(((LayoutParams)paramView.getLayoutParams()).gravity, getLayoutDirection());
  }
  
  protected boolean drawChild(Canvas paramCanvas, View paramView, long paramLong)
  {
    int i8 = getHeight();
    boolean bool1 = e(paramView);
    int i1 = getWidth();
    int i7 = paramCanvas.save();
    int i3;
    int i2;
    int i4;
    if (bool1)
    {
      int i9 = getChildCount();
      i3 = 0;
      i2 = 0;
      while (i3 < i9)
      {
        View localView = getChildAt(i3);
        i4 = i2;
        int i5 = i1;
        if (localView != paramView)
        {
          i4 = i2;
          i5 = i1;
          if (localView.getVisibility() == 0)
          {
            i4 = i2;
            i5 = i1;
            if (g(localView))
            {
              i4 = i2;
              i5 = i1;
              if (f(localView)) {
                if (localView.getHeight() < i8)
                {
                  i4 = i2;
                  i5 = i1;
                }
                else
                {
                  int i6;
                  if (a(localView, 3))
                  {
                    i6 = localView.getRight();
                    i4 = i2;
                    i5 = i1;
                    if (i6 > i2)
                    {
                      i4 = i6;
                      i5 = i1;
                    }
                  }
                  else
                  {
                    i6 = localView.getLeft();
                    i4 = i2;
                    i5 = i1;
                    if (i6 < i1)
                    {
                      i5 = i6;
                      i4 = i2;
                    }
                  }
                }
              }
            }
          }
        }
        i3 += 1;
        i2 = i4;
        i1 = i5;
      }
      paramCanvas.clipRect(i2, 0, i1, getHeight());
    }
    else
    {
      i2 = 0;
    }
    boolean bool2 = super.drawChild(paramCanvas, paramView, paramLong);
    paramCanvas.restoreToCount(i7);
    if ((this.i > 0.0F) && (bool1))
    {
      i3 = (int)(((this.h & 0xFF000000) >>> 24) * this.i);
      i4 = this.h;
      this.j.setColor(i3 << 24 | i4 & 0xFFFFFF);
      paramCanvas.drawRect(i2, 0.0F, i1, getHeight(), this.j);
      return bool2;
    }
    float f1;
    if ((this.z != null) && (a(paramView, 3)))
    {
      i1 = this.z.getIntrinsicWidth();
      i2 = paramView.getRight();
      i3 = this.k.getEdgeSize();
      f1 = Math.max(0.0F, Math.min(i2 / i3, 1.0F));
      this.z.setBounds(i2, paramView.getTop(), i1 + i2, paramView.getBottom());
      this.z.setAlpha((int)(f1 * 255.0F));
    }
    for (paramView = this.z;; paramView = this.A)
    {
      paramView.draw(paramCanvas);
      return bool2;
      if ((this.A == null) || (!a(paramView, 5))) {
        break;
      }
      i1 = this.A.getIntrinsicWidth();
      i2 = paramView.getLeft();
      i3 = getWidth();
      i4 = this.l.getEdgeSize();
      f1 = Math.max(0.0F, Math.min((i3 - i2) / i4, 1.0F));
      this.A.setBounds(i2 - i1, paramView.getTop(), i2, paramView.getBottom());
      this.A.setAlpha((int)(f1 * 255.0F));
    }
    return bool2;
  }
  
  boolean e(View paramView)
  {
    return ((LayoutParams)paramView.getLayoutParams()).gravity == 0;
  }
  
  boolean f(View paramView)
  {
    return (Gravity.getAbsoluteGravity(((LayoutParams)paramView.getLayoutParams()).gravity, paramView.getLayoutDirection()) & 0x7) != 0;
  }
  
  protected ViewGroup.LayoutParams generateDefaultLayoutParams()
  {
    return new LayoutParams(-1, -1);
  }
  
  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    if ((paramLayoutParams instanceof LayoutParams)) {
      return new LayoutParams((LayoutParams)paramLayoutParams);
    }
    if ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams)) {
      return new LayoutParams((ViewGroup.MarginLayoutParams)paramLayoutParams);
    }
    return new LayoutParams(paramLayoutParams);
  }
  
  public float getDrawerElevation()
  {
    if (d) {
      return this.f;
    }
    return 0.0F;
  }
  
  public int getDrawerLockMode(int paramInt)
  {
    paramInt = Gravity.getAbsoluteGravity(paramInt, getLayoutDirection());
    if (paramInt == 3) {
      return this.r;
    }
    if (paramInt == 5) {
      return this.s;
    }
    return 0;
  }
  
  public int getDrawerLockMode(View paramView)
  {
    int i1 = d(paramView);
    if (i1 == 3) {
      return this.r;
    }
    if (i1 == 5) {
      return this.s;
    }
    return 0;
  }
  
  public CharSequence getDrawerTitle(int paramInt)
  {
    paramInt = Gravity.getAbsoluteGravity(paramInt, getLayoutDirection());
    if (paramInt == 3) {
      return this.B;
    }
    if (paramInt == 5) {
      return this.C;
    }
    return null;
  }
  
  public Drawable getStatusBarBackgroundDrawable()
  {
    return this.y;
  }
  
  public boolean isDrawerOpen(int paramInt)
  {
    View localView = a(paramInt);
    if (localView != null) {
      return isDrawerOpen(localView);
    }
    return false;
  }
  
  public boolean isDrawerOpen(View paramView)
  {
    if (!f(paramView))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("View ");
      localStringBuilder.append(paramView);
      localStringBuilder.append(" is not a drawer");
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    return ((LayoutParams)paramView.getLayoutParams()).c;
  }
  
  public boolean isDrawerVisible(int paramInt)
  {
    View localView = a(paramInt);
    if (localView != null) {
      return isDrawerVisible(localView);
    }
    return false;
  }
  
  public boolean isDrawerVisible(View paramView)
  {
    if (!f(paramView))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("View ");
      localStringBuilder.append(paramView);
      localStringBuilder.append(" is not a drawer");
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    return ((LayoutParams)paramView.getLayoutParams()).a > 0.0F;
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    this.q = true;
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    this.q = true;
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if ((this.E) && (this.y != null))
    {
      int i1 = a.getTopInset(this.D);
      if (i1 > 0)
      {
        this.y.setBounds(0, 0, getWidth(), i1);
        this.y.draw(paramCanvas);
      }
    }
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    int i1 = paramMotionEvent.getActionMasked();
    boolean bool3 = this.k.shouldInterceptTouchEvent(paramMotionEvent);
    boolean bool4 = this.l.shouldInterceptTouchEvent(paramMotionEvent);
    boolean bool2 = true;
    switch (i1)
    {
    default: 
      break;
    case 2: 
      if (this.k.checkTouchSlop(3))
      {
        this.m.removeCallbacks();
        this.n.removeCallbacks();
      }
      break;
    case 1: 
    case 3: 
      a(true);
      this.t = false;
      this.u = false;
      break;
    case 0: 
      float f1 = paramMotionEvent.getX();
      float f2 = paramMotionEvent.getY();
      this.w = f1;
      this.x = f2;
      if (this.i > 0.0F)
      {
        paramMotionEvent = this.k.findTopChildUnder((int)f1, (int)f2);
        if ((paramMotionEvent != null) && (e(paramMotionEvent)))
        {
          i1 = 1;
          break label171;
        }
      }
      i1 = 0;
      label171:
      this.t = false;
      this.u = false;
      break;
    }
    i1 = 0;
    boolean bool1 = bool2;
    if (!(bool3 | bool4))
    {
      bool1 = bool2;
      if (i1 == 0)
      {
        bool1 = bool2;
        if (!g())
        {
          if (this.u) {
            return true;
          }
          bool1 = false;
        }
      }
    }
    return bool1;
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (h()))
    {
      paramKeyEvent.startTracking();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      paramKeyEvent = i();
      if ((paramKeyEvent != null) && (getDrawerLockMode(paramKeyEvent) == 0)) {
        closeDrawers();
      }
      return paramKeyEvent != null;
    }
    return super.onKeyUp(paramInt, paramKeyEvent);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.p = true;
    int i4 = paramInt3 - paramInt1;
    int i5 = getChildCount();
    paramInt3 = 0;
    while (paramInt3 < i5)
    {
      View localView = getChildAt(paramInt3);
      if (localView.getVisibility() != 8)
      {
        LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if (e(localView))
        {
          localView.layout(localLayoutParams.leftMargin, localLayoutParams.topMargin, localLayoutParams.leftMargin + localView.getMeasuredWidth(), localLayoutParams.topMargin + localView.getMeasuredHeight());
        }
        else
        {
          int i6 = localView.getMeasuredWidth();
          int i7 = localView.getMeasuredHeight();
          float f1;
          int i1;
          if (a(localView, 3))
          {
            paramInt1 = -i6;
            f1 = i6;
            i1 = paramInt1 + (int)(localLayoutParams.a * f1);
            f1 = (i6 + i1) / f1;
          }
          else
          {
            f1 = i6;
            i1 = i4 - (int)(localLayoutParams.a * f1);
            f1 = (i4 - i1) / f1;
          }
          int i2;
          if (f1 != localLayoutParams.a) {
            i2 = 1;
          } else {
            i2 = 0;
          }
          paramInt1 = localLayoutParams.gravity & 0x70;
          if (paramInt1 != 16)
          {
            if (paramInt1 != 80)
            {
              localView.layout(i1, localLayoutParams.topMargin, i6 + i1, localLayoutParams.topMargin + i7);
            }
            else
            {
              paramInt1 = paramInt4 - paramInt2;
              localView.layout(i1, paramInt1 - localLayoutParams.bottomMargin - localView.getMeasuredHeight(), i6 + i1, paramInt1 - localLayoutParams.bottomMargin);
            }
          }
          else
          {
            int i8 = paramInt4 - paramInt2;
            int i3 = (i8 - i7) / 2;
            if (i3 < localLayoutParams.topMargin)
            {
              paramInt1 = localLayoutParams.topMargin;
            }
            else
            {
              paramInt1 = i3;
              if (i3 + i7 > i8 - localLayoutParams.bottomMargin) {
                paramInt1 = i8 - localLayoutParams.bottomMargin - i7;
              }
            }
            localView.layout(i1, paramInt1, i6 + i1, i7 + paramInt1);
          }
          if (i2 != 0) {
            b(localView, f1);
          }
          if (localLayoutParams.a > 0.0F) {
            paramInt1 = 0;
          } else {
            paramInt1 = 4;
          }
          if (localView.getVisibility() != paramInt1) {
            localView.setVisibility(paramInt1);
          }
        }
      }
      paramInt3 += 1;
    }
    this.p = false;
    this.q = false;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i6 = View.MeasureSpec.getMode(paramInt1);
    int i5 = View.MeasureSpec.getMode(paramInt2);
    int i1 = View.MeasureSpec.getSize(paramInt1);
    int i4 = View.MeasureSpec.getSize(paramInt2);
    int i2;
    int i3;
    if (i6 == 1073741824)
    {
      i2 = i1;
      i3 = i4;
      if (i5 == 1073741824) {}
    }
    else
    {
      if (!isInEditMode()) {
        break label568;
      }
      if ((i6 != Integer.MIN_VALUE) && (i6 == 0)) {
        i1 = 300;
      }
      if (i5 == Integer.MIN_VALUE)
      {
        i2 = i1;
        i3 = i4;
      }
      else
      {
        i2 = i1;
        i3 = i4;
        if (i5 == 0)
        {
          i3 = 300;
          i2 = i1;
        }
      }
    }
    setMeasuredDimension(i2, i3);
    if ((this.D != null) && (getFitsSystemWindows())) {
      i1 = 1;
    } else {
      i1 = 0;
    }
    int i7 = getLayoutDirection();
    int i8 = getChildCount();
    i4 = 0;
    while (i4 < i8)
    {
      Object localObject1 = getChildAt(i4);
      if (((View)localObject1).getVisibility() != 8)
      {
        localObject2 = (LayoutParams)((View)localObject1).getLayoutParams();
        if (i1 != 0)
        {
          i5 = Gravity.getAbsoluteGravity(((LayoutParams)localObject2).gravity, i7);
          if (((View)localObject1).getFitsSystemWindows()) {
            a.dispatchChildInsets((View)localObject1, this.D, i5);
          } else {
            a.applyMarginInsets((ViewGroup.MarginLayoutParams)localObject2, this.D, i5);
          }
        }
        if (e((View)localObject1)) {
          i6 = View.MeasureSpec.makeMeasureSpec(i2 - ((LayoutParams)localObject2).leftMargin - ((LayoutParams)localObject2).rightMargin, 1073741824);
        }
        for (i5 = View.MeasureSpec.makeMeasureSpec(i3 - ((LayoutParams)localObject2).topMargin - ((LayoutParams)localObject2).bottomMargin, 1073741824);; i5 = getChildMeasureSpec(paramInt2, ((LayoutParams)localObject2).topMargin + ((LayoutParams)localObject2).bottomMargin, ((LayoutParams)localObject2).height))
        {
          ((View)localObject1).measure(i6, i5);
          break;
          if (!f((View)localObject1)) {
            break label502;
          }
          if ((d) && (((View)localObject1).getElevation() != this.f)) {
            ((View)localObject1).setElevation(this.f);
          }
          i5 = d((View)localObject1) & 0x7;
          if ((0x0 & i5) != 0)
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("Child drawer has absolute gravity ");
            ((StringBuilder)localObject1).append(b(i5));
            ((StringBuilder)localObject1).append(" but this ");
            ((StringBuilder)localObject1).append("DrawerLayout");
            ((StringBuilder)localObject1).append(" already has a drawer view along that edge");
            throw new IllegalStateException(((StringBuilder)localObject1).toString());
          }
          i6 = getChildMeasureSpec(paramInt1, this.g + ((LayoutParams)localObject2).leftMargin + ((LayoutParams)localObject2).rightMargin, ((LayoutParams)localObject2).width);
        }
      }
      i4 += 1;
      continue;
      label502:
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Child ");
      ((StringBuilder)localObject2).append(localObject1);
      ((StringBuilder)localObject2).append(" at index ");
      ((StringBuilder)localObject2).append(i4);
      ((StringBuilder)localObject2).append(" does not have a valid layout_gravity - must be Gravity.LEFT, Gravity.RIGHT or Gravity.NO_GRAVITY");
      throw new IllegalStateException(((StringBuilder)localObject2).toString());
    }
    return;
    label568:
    throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    if (paramParcelable.a != 0)
    {
      View localView = a(paramParcelable.a);
      if (localView != null) {
        openDrawer(localView);
      }
    }
    setDrawerLockMode(paramParcelable.b, 3);
    setDrawerLockMode(paramParcelable.c, 5);
  }
  
  public void onRtlPropertiesChanged(int paramInt)
  {
    d();
  }
  
  protected Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    View localView = a();
    if (localView != null) {
      localSavedState.a = ((LayoutParams)localView.getLayoutParams()).gravity;
    }
    localSavedState.b = this.r;
    localSavedState.c = this.s;
    return localSavedState;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    this.k.processTouchEvent(paramMotionEvent);
    this.l.processTouchEvent(paramMotionEvent);
    int i1 = paramMotionEvent.getAction() & 0xFF;
    if (i1 != 3)
    {
      switch (i1)
      {
      default: 
        return true;
      case 1: 
        f2 = paramMotionEvent.getX();
        f1 = paramMotionEvent.getY();
        paramMotionEvent = this.k.findTopChildUnder((int)f2, (int)f1);
        if ((paramMotionEvent != null) && (e(paramMotionEvent)))
        {
          f2 -= this.w;
          f1 -= this.x;
          i1 = this.k.getTouchSlop();
          if (f2 * f2 + f1 * f1 < i1 * i1)
          {
            paramMotionEvent = a();
            if ((paramMotionEvent != null) && (getDrawerLockMode(paramMotionEvent) != 2))
            {
              bool = false;
              break label162;
            }
          }
        }
        boolean bool = true;
        label162:
        a(bool);
        this.t = false;
        return true;
      }
      float f1 = paramMotionEvent.getX();
      float f2 = paramMotionEvent.getY();
      this.w = f1;
      this.x = f2;
    }
    for (;;)
    {
      this.t = false;
      this.u = false;
      return true;
      a(true);
    }
  }
  
  public void openDrawer(int paramInt)
  {
    Object localObject = a(paramInt);
    if (localObject == null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("No drawer view found with gravity ");
      ((StringBuilder)localObject).append(b(paramInt));
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    }
    openDrawer((View)localObject);
  }
  
  public void openDrawer(View paramView)
  {
    Object localObject;
    if (!f(paramView))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("View ");
      ((StringBuilder)localObject).append(paramView);
      ((StringBuilder)localObject).append(" is not a sliding drawer");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    }
    if (this.q)
    {
      localObject = (LayoutParams)paramView.getLayoutParams();
      ((LayoutParams)localObject).a = 1.0F;
      ((LayoutParams)localObject).c = true;
      a(paramView, true);
    }
    else
    {
      if (a(paramView, 3)) {
        localObject = this.k;
      }
      for (int i1 = 0;; i1 = getWidth() - paramView.getWidth())
      {
        ((ViewDragHelper)localObject).smoothSlideViewTo(paramView, i1, paramView.getTop());
        break;
        localObject = this.l;
      }
    }
    invalidate();
  }
  
  public void requestDisallowInterceptTouchEvent(boolean paramBoolean)
  {
    super.requestDisallowInterceptTouchEvent(paramBoolean);
    this.t = paramBoolean;
    if (paramBoolean) {
      a(true);
    }
  }
  
  public void requestLayout()
  {
    if (!this.p) {
      super.requestLayout();
    }
  }
  
  public void setChildInsets(Object paramObject, boolean paramBoolean)
  {
    this.D = paramObject;
    this.E = paramBoolean;
    if ((!paramBoolean) && (getBackground() == null)) {
      paramBoolean = true;
    } else {
      paramBoolean = false;
    }
    setWillNotDraw(paramBoolean);
    requestLayout();
  }
  
  public void setDrawerElevation(float paramFloat)
  {
    this.f = paramFloat;
    if (Build.VERSION.SDK_INT >= 21)
    {
      int i1 = 0;
      while (i1 < getChildCount())
      {
        View localView = getChildAt(i1);
        if (f(localView)) {
          localView.setElevation(this.f);
        }
        i1 += 1;
      }
    }
  }
  
  public void setDrawerListener(DrawerListener paramDrawerListener)
  {
    this.v = paramDrawerListener;
  }
  
  public void setDrawerLockMode(int paramInt)
  {
    setDrawerLockMode(paramInt, 3);
    setDrawerLockMode(paramInt, 5);
  }
  
  public void setDrawerLockMode(int paramInt1, int paramInt2)
  {
    paramInt2 = Gravity.getAbsoluteGravity(paramInt2, getLayoutDirection());
    if (paramInt2 == 3) {
      this.r = paramInt1;
    } else if (paramInt2 == 5) {
      this.s = paramInt1;
    }
    Object localObject;
    if (paramInt1 != 0)
    {
      if (paramInt2 == 3) {
        localObject = this.k;
      } else {
        localObject = this.l;
      }
      ((ViewDragHelper)localObject).cancel();
    }
    switch (paramInt1)
    {
    default: 
      
    case 2: 
      localObject = a(paramInt2);
      if (localObject != null)
      {
        openDrawer((View)localObject);
        return;
      }
      break;
    case 1: 
      localObject = a(paramInt2);
      if (localObject != null) {
        closeDrawer((View)localObject);
      }
      break;
    }
  }
  
  public void setDrawerLockMode(int paramInt, View paramView)
  {
    if (!f(paramView))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("View ");
      localStringBuilder.append(paramView);
      localStringBuilder.append(" is not a drawer with appropriate layout_gravity");
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    setDrawerLockMode(paramInt, ((LayoutParams)paramView.getLayoutParams()).gravity);
  }
  
  public void setDrawerShadow(int paramInt1, int paramInt2)
  {
    setDrawerShadow(getResources().getDrawable(paramInt1), paramInt2);
  }
  
  public void setDrawerShadow(Drawable paramDrawable, int paramInt)
  {
    if (d) {
      return;
    }
    if ((paramInt & 0x800003) == 8388611)
    {
      this.F = paramDrawable;
    }
    else if ((paramInt & 0x800005) == 8388613)
    {
      this.G = paramDrawable;
    }
    else if ((paramInt & 0x3) == 3)
    {
      this.H = paramDrawable;
    }
    else
    {
      if ((paramInt & 0x5) != 5) {
        return;
      }
      this.I = paramDrawable;
    }
    d();
    invalidate();
  }
  
  public void setDrawerTitle(int paramInt, CharSequence paramCharSequence)
  {
    paramInt = Gravity.getAbsoluteGravity(paramInt, getLayoutDirection());
    if (paramInt == 3)
    {
      this.B = paramCharSequence;
      return;
    }
    if (paramInt == 5) {
      this.C = paramCharSequence;
    }
  }
  
  public void setScrimColor(int paramInt)
  {
    this.h = paramInt;
    invalidate();
  }
  
  public void setStatusBarBackground(int paramInt)
  {
    Drawable localDrawable;
    if (paramInt != 0) {
      localDrawable = getContext().getResources().getDrawable(paramInt);
    } else {
      localDrawable = null;
    }
    this.y = localDrawable;
    invalidate();
  }
  
  public void setStatusBarBackground(Drawable paramDrawable)
  {
    this.y = paramDrawable;
    invalidate();
  }
  
  public void setStatusBarBackgroundColor(int paramInt)
  {
    this.y = new ColorDrawable(paramInt);
    invalidate();
  }
  
  @SuppressLint({"NewApi"})
  public static class DrawerLayoutCompatApi21
  {
    private static final int[] a = { 16843828 };
    
    public static void applyMarginInsets(ViewGroup.MarginLayoutParams paramMarginLayoutParams, Object paramObject, int paramInt)
    {
      WindowInsets localWindowInsets = (WindowInsets)paramObject;
      if (paramInt == 3)
      {
        paramObject = localWindowInsets.replaceSystemWindowInsets(localWindowInsets.getSystemWindowInsetLeft(), localWindowInsets.getSystemWindowInsetTop(), 0, localWindowInsets.getSystemWindowInsetBottom());
      }
      else
      {
        paramObject = localWindowInsets;
        if (paramInt == 5) {
          paramObject = localWindowInsets.replaceSystemWindowInsets(0, localWindowInsets.getSystemWindowInsetTop(), localWindowInsets.getSystemWindowInsetRight(), localWindowInsets.getSystemWindowInsetBottom());
        }
      }
      paramMarginLayoutParams.leftMargin = ((WindowInsets)paramObject).getSystemWindowInsetLeft();
      paramMarginLayoutParams.topMargin = ((WindowInsets)paramObject).getSystemWindowInsetTop();
      paramMarginLayoutParams.rightMargin = ((WindowInsets)paramObject).getSystemWindowInsetRight();
      paramMarginLayoutParams.bottomMargin = ((WindowInsets)paramObject).getSystemWindowInsetBottom();
    }
    
    public static void configureApplyInsets(View paramView)
    {
      if ((paramView instanceof DrawerLayoutImpl))
      {
        paramView.setOnApplyWindowInsetsListener(new InsetsListener());
        paramView.setSystemUiVisibility(1280);
      }
    }
    
    public static void dispatchChildInsets(View paramView, Object paramObject, int paramInt)
    {
      WindowInsets localWindowInsets = (WindowInsets)paramObject;
      if (paramInt == 3)
      {
        paramObject = localWindowInsets.replaceSystemWindowInsets(localWindowInsets.getSystemWindowInsetLeft(), localWindowInsets.getSystemWindowInsetTop(), 0, localWindowInsets.getSystemWindowInsetBottom());
      }
      else
      {
        paramObject = localWindowInsets;
        if (paramInt == 5) {
          paramObject = localWindowInsets.replaceSystemWindowInsets(0, localWindowInsets.getSystemWindowInsetTop(), localWindowInsets.getSystemWindowInsetRight(), localWindowInsets.getSystemWindowInsetBottom());
        }
      }
      paramView.dispatchApplyWindowInsets((WindowInsets)paramObject);
    }
    
    public static Drawable getDefaultStatusBarBackground(Context paramContext)
    {
      paramContext = paramContext.obtainStyledAttributes(a);
      try
      {
        Drawable localDrawable = paramContext.getDrawable(0);
        return localDrawable;
      }
      finally
      {
        paramContext.recycle();
      }
    }
    
    public static int getTopInset(Object paramObject)
    {
      if (paramObject != null) {
        return ((WindowInsets)paramObject).getSystemWindowInsetTop();
      }
      return 0;
    }
    
    static class InsetsListener
      implements View.OnApplyWindowInsetsListener
    {
      public WindowInsets onApplyWindowInsets(View paramView, WindowInsets paramWindowInsets)
      {
        paramView = (DrawerLayoutImpl)paramView;
        boolean bool;
        if (paramWindowInsets.getSystemWindowInsetTop() > 0) {
          bool = true;
        } else {
          bool = false;
        }
        paramView.setChildInsets(paramWindowInsets, bool);
        return paramWindowInsets.consumeSystemWindowInsets();
      }
    }
  }
  
  static abstract interface DrawerLayoutCompatImpl
  {
    public abstract void applyMarginInsets(ViewGroup.MarginLayoutParams paramMarginLayoutParams, Object paramObject, int paramInt);
    
    public abstract void configureApplyInsets(View paramView);
    
    public abstract void dispatchChildInsets(View paramView, Object paramObject, int paramInt);
    
    public abstract Drawable getDefaultStatusBarBackground(Context paramContext);
    
    public abstract int getTopInset(Object paramObject);
  }
  
  @SuppressLint({"NewApi"})
  static class DrawerLayoutCompatImplApi21
    implements DrawerLayout.DrawerLayoutCompatImpl
  {
    public void applyMarginInsets(ViewGroup.MarginLayoutParams paramMarginLayoutParams, Object paramObject, int paramInt)
    {
      DrawerLayout.DrawerLayoutCompatApi21.applyMarginInsets(paramMarginLayoutParams, paramObject, paramInt);
    }
    
    public void configureApplyInsets(View paramView)
    {
      DrawerLayout.DrawerLayoutCompatApi21.configureApplyInsets(paramView);
    }
    
    public void dispatchChildInsets(View paramView, Object paramObject, int paramInt)
    {
      DrawerLayout.DrawerLayoutCompatApi21.dispatchChildInsets(paramView, paramObject, paramInt);
    }
    
    public Drawable getDefaultStatusBarBackground(Context paramContext)
    {
      return DrawerLayout.DrawerLayoutCompatApi21.getDefaultStatusBarBackground(paramContext);
    }
    
    public int getTopInset(Object paramObject)
    {
      return DrawerLayout.DrawerLayoutCompatApi21.getTopInset(paramObject);
    }
  }
  
  @SuppressLint({"NewApi"})
  static class DrawerLayoutCompatImplBase
    implements DrawerLayout.DrawerLayoutCompatImpl
  {
    public void applyMarginInsets(ViewGroup.MarginLayoutParams paramMarginLayoutParams, Object paramObject, int paramInt) {}
    
    public void configureApplyInsets(View paramView) {}
    
    public void dispatchChildInsets(View paramView, Object paramObject, int paramInt) {}
    
    public Drawable getDefaultStatusBarBackground(Context paramContext)
    {
      return null;
    }
    
    public int getTopInset(Object paramObject)
    {
      return 0;
    }
  }
  
  public static abstract interface DrawerListener
  {
    public abstract void onDrawerClosed(View paramView);
    
    public abstract void onDrawerOpened(View paramView);
    
    public abstract void onDrawerSlide(View paramView, float paramFloat);
    
    public abstract void onDrawerStateChanged(int paramInt);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  private static @interface EdgeGravity {}
  
  public static class LayoutParams
    extends ViewGroup.MarginLayoutParams
  {
    float a;
    boolean b;
    boolean c;
    public int gravity = 0;
    
    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }
    
    public LayoutParams(int paramInt1, int paramInt2, int paramInt3)
    {
      this(paramInt1, paramInt2);
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, DrawerLayout.c());
      this.gravity = paramContext.getInt(0, 0);
      paramContext.recycle();
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
    }
    
    public LayoutParams(LayoutParams paramLayoutParams)
    {
      super();
      this.gravity = paramLayoutParams.gravity;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  private static @interface LockMode {}
  
  protected static class SavedState
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator()
    {
      public DrawerLayout.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new DrawerLayout.SavedState(paramAnonymousParcel);
      }
      
      public DrawerLayout.SavedState[] newArray(int paramAnonymousInt)
      {
        return new DrawerLayout.SavedState[paramAnonymousInt];
      }
    };
    int a = 0;
    int b = 0;
    int c = 0;
    
    public SavedState(Parcel paramParcel)
    {
      super();
      this.a = paramParcel.readInt();
    }
    
    public SavedState(Parcelable paramParcelable)
    {
      super();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(this.a);
    }
  }
  
  public static abstract class SimpleDrawerListener
    implements DrawerLayout.DrawerListener
  {
    public void onDrawerClosed(View paramView) {}
    
    public void onDrawerOpened(View paramView) {}
    
    public void onDrawerSlide(View paramView, float paramFloat) {}
    
    public void onDrawerStateChanged(int paramInt) {}
  }
  
  @Retention(RetentionPolicy.SOURCE)
  private static @interface State {}
  
  private class ViewDragCallback
    extends ViewDragHelper.Callback
  {
    private final int b;
    private ViewDragHelper c;
    private final Runnable d = new Runnable()
    {
      public void run()
      {
        DrawerLayout.ViewDragCallback.a(DrawerLayout.ViewDragCallback.this);
      }
    };
    
    public ViewDragCallback(int paramInt)
    {
      this.b = paramInt;
    }
    
    private void a()
    {
      int j = this.b;
      int i = 3;
      if (j == 3) {
        i = 5;
      }
      View localView = DrawerLayout.this.a(i);
      if (localView != null) {
        DrawerLayout.this.closeDrawer(localView);
      }
    }
    
    private void b()
    {
      int k = this.c.getEdgeSize();
      int i = this.b;
      int j = 0;
      if (i == 3) {
        i = 1;
      } else {
        i = 0;
      }
      View localView;
      if (i != 0)
      {
        localView = DrawerLayout.this.a(3);
        if (localView != null) {
          j = -localView.getWidth();
        }
        j += k;
      }
      else
      {
        localView = DrawerLayout.this.a(5);
        j = DrawerLayout.this.getWidth() - k;
      }
      if ((localView != null) && (((i != 0) && (localView.getLeft() < j)) || ((i == 0) && (localView.getLeft() > j) && (DrawerLayout.this.getDrawerLockMode(localView) == 0))))
      {
        DrawerLayout.LayoutParams localLayoutParams = (DrawerLayout.LayoutParams)localView.getLayoutParams();
        this.c.smoothSlideViewTo(localView, j, localView.getTop());
        localLayoutParams.b = true;
        DrawerLayout.this.invalidate();
        a();
        DrawerLayout.this.b();
      }
    }
    
    public int clampViewPositionHorizontal(View paramView, int paramInt1, int paramInt2)
    {
      int i;
      if (DrawerLayout.this.a(paramView, 3))
      {
        paramInt2 = -paramView.getWidth();
        i = 0;
      }
      for (;;)
      {
        return Math.max(paramInt2, Math.min(paramInt1, i));
        i = DrawerLayout.this.getWidth();
        paramInt2 = i - paramView.getWidth();
      }
    }
    
    public int clampViewPositionVertical(View paramView, int paramInt1, int paramInt2)
    {
      return paramView.getTop();
    }
    
    public int getViewHorizontalDragRange(View paramView)
    {
      if (DrawerLayout.this.f(paramView)) {
        return paramView.getWidth();
      }
      return 0;
    }
    
    public void onEdgeDragStarted(int paramInt1, int paramInt2)
    {
      Object localObject;
      if ((paramInt1 & 0x1) == 1) {
        localObject = DrawerLayout.this;
      }
      for (paramInt1 = 3;; paramInt1 = 5)
      {
        localObject = ((DrawerLayout)localObject).a(paramInt1);
        break;
        localObject = DrawerLayout.this;
      }
      if ((localObject != null) && (DrawerLayout.this.getDrawerLockMode((View)localObject) == 0)) {
        this.c.captureChildView((View)localObject, paramInt2);
      }
    }
    
    public boolean onEdgeLock(int paramInt)
    {
      return false;
    }
    
    public void onEdgeTouched(int paramInt1, int paramInt2)
    {
      DrawerLayout.this.postDelayed(this.d, 160L);
    }
    
    public void onViewCaptured(View paramView, int paramInt)
    {
      ((DrawerLayout.LayoutParams)paramView.getLayoutParams()).b = false;
      a();
    }
    
    public void onViewDragStateChanged(int paramInt)
    {
      DrawerLayout.this.a(this.b, paramInt, this.c.getCapturedView());
    }
    
    public void onViewPositionChanged(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      paramInt2 = paramView.getWidth();
      if (DrawerLayout.this.a(paramView, 3)) {}
      for (float f = paramInt1 + paramInt2;; f = DrawerLayout.this.getWidth() - paramInt1)
      {
        f /= paramInt2;
        break;
      }
      DrawerLayout.this.b(paramView, f);
      if (f == 0.0F) {
        paramInt1 = 4;
      } else {
        paramInt1 = 0;
      }
      paramView.setVisibility(paramInt1);
      DrawerLayout.this.invalidate();
    }
    
    public void onViewReleased(View paramView, float paramFloat1, float paramFloat2)
    {
      paramFloat2 = DrawerLayout.this.c(paramView);
      int k = paramView.getWidth();
      int i;
      if (DrawerLayout.this.a(paramView, 3))
      {
        if ((paramFloat1 <= 0.0F) && ((paramFloat1 != 0.0F) || (paramFloat2 <= 0.5F))) {
          i = -k;
        } else {
          i = 0;
        }
      }
      else
      {
        int j = DrawerLayout.this.getWidth();
        if (paramFloat1 >= 0.0F)
        {
          i = j;
          if (paramFloat1 == 0.0F)
          {
            i = j;
            if (paramFloat2 <= 0.5F) {}
          }
        }
        else
        {
          i = j - k;
        }
      }
      this.c.settleCapturedViewAt(i, paramView.getTop());
      DrawerLayout.this.invalidate();
    }
    
    public void removeCallbacks()
    {
      DrawerLayout.this.removeCallbacks(this.d);
    }
    
    public void setDragger(ViewDragHelper paramViewDragHelper)
    {
      this.c = paramViewDragHelper;
    }
    
    public boolean tryCaptureView(View paramView, int paramInt)
    {
      return (DrawerLayout.this.f(paramView)) && (DrawerLayout.this.a(paramView, this.b)) && (DrawerLayout.this.getDrawerLockMode(paramView) == 0);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\widget\DrawerLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */