package android.widget;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class HorizontalListView
  extends AdapterView<ListAdapter>
{
  private int A;
  private boolean B = false;
  private boolean C = false;
  private View.OnClickListener D;
  private Rect E;
  private int F = -2004318072;
  private Drawable G;
  private int H;
  private DataSetObserver I = new DataSetObserver()
  {
    public void onChanged()
    {
      HorizontalListView.a(HorizontalListView.this, true);
      HorizontalListView.b(HorizontalListView.this, false);
      HorizontalListView.b(HorizontalListView.this);
      HorizontalListView.this.invalidate();
      HorizontalListView.this.requestLayout();
    }
    
    public void onInvalidated()
    {
      HorizontalListView.b(HorizontalListView.this, false);
      HorizontalListView.b(HorizontalListView.this);
      HorizontalListView.c(HorizontalListView.this);
      HorizontalListView.this.invalidate();
      HorizontalListView.this.requestLayout();
    }
  };
  private Runnable J = new Runnable()
  {
    public void run()
    {
      HorizontalListView.this.requestLayout();
    }
  };
  protected Scroller a = new Scroller(getContext());
  protected ListAdapter b;
  protected int c;
  protected int d;
  private final DisplayMetrics e;
  private final GestureListener f = new GestureListener(null);
  private GestureDetector g;
  private int h;
  private List<Queue<View>> i = new ArrayList();
  private boolean j = false;
  private Rect k = new Rect();
  private View l = null;
  private int m = 0;
  private Drawable n = null;
  private Integer o = null;
  private int p = Integer.MAX_VALUE;
  private int q;
  private int r;
  private int s;
  private RunningOutOfDataListener t = null;
  private int u = 0;
  private boolean v = false;
  private OnScrollStateChangedListener w = null;
  private HorizontalListView.OnScrollStateChangedListener.ScrollState x = HorizontalListView.OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE;
  private EdgeEffect y;
  private EdgeEffect z;
  
  public HorizontalListView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public HorizontalListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.e = paramContext.getResources().getDisplayMetrics();
    this.y = new EdgeEffect(paramContext);
    this.z = new EdgeEffect(paramContext);
    this.g = new GestureDetector(paramContext, this.f);
    a();
    setWillNotDraw(false);
    HoneycombPlus.setFriction(this.a, 0.009F);
  }
  
  private int a(float paramFloat)
  {
    return (int)TypedValue.applyDimension(1, paramFloat, this.e);
  }
  
  private void a()
  {
    this.q = -1;
    this.r = -1;
    this.s = -1;
    this.h = 0;
    this.c = 0;
    this.d = 0;
    this.p = Integer.MAX_VALUE;
    setCurrentScrollState(HorizontalListView.OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE);
  }
  
  private void a(int paramInt)
  {
    this.i.clear();
    int i1 = 0;
    while (i1 < paramInt)
    {
      this.i.add(new LinkedList());
      i1 += 1;
    }
  }
  
  private void a(int paramInt1, int paramInt2)
  {
    while ((paramInt1 + paramInt2 + this.m < getWidth()) && (this.r + 1 < this.b.getCount()))
    {
      this.r += 1;
      if (this.q < 0) {
        this.q = this.r;
      }
      View localView = this.b.getView(this.r, b(this.r), this);
      a(localView, -1);
      int i1;
      if (this.r == 0) {
        i1 = 0;
      } else {
        i1 = this.m;
      }
      paramInt1 += i1 + localView.getMeasuredWidth();
      g();
    }
  }
  
  private void a(int paramInt, View paramView)
  {
    paramInt = this.b.getItemViewType(paramInt);
    if (c(paramInt)) {
      ((Queue)this.i.get(paramInt)).offer(paramView);
    }
  }
  
  private void a(Canvas paramCanvas)
  {
    int i2;
    if ((this.y != null) && (!this.y.isFinished()) && (h()))
    {
      i2 = paramCanvas.save();
      i1 = getHeight();
      paramCanvas.rotate(-90.0F, 0.0F, 0.0F);
      paramCanvas.translate(-i1 + getPaddingBottom(), 0.0F);
      this.y.setSize(getRenderHeight(), getRenderWidth());
      i1 = i2;
      if (!this.y.draw(paramCanvas)) {}
    }
    for (int i1 = i2;; i1 = i2)
    {
      invalidate();
      do
      {
        paramCanvas.restoreToCount(i1);
        return;
        if ((this.z == null) || (this.z.isFinished()) || (!h())) {
          break;
        }
        i2 = paramCanvas.save();
        i1 = getWidth();
        paramCanvas.rotate(90.0F, 0.0F, 0.0F);
        paramCanvas.translate(getPaddingTop(), -i1);
        this.z.setSize(getRenderHeight(), getRenderWidth());
        i1 = i2;
      } while (!this.z.draw(paramCanvas));
    }
  }
  
  private void a(Canvas paramCanvas, Rect paramRect)
  {
    if (this.n != null)
    {
      this.n.setBounds(paramRect);
      this.n.draw(paramCanvas);
    }
  }
  
  private void a(View paramView)
  {
    ViewGroup.LayoutParams localLayoutParams = b(paramView);
    int i2 = ViewGroup.getChildMeasureSpec(this.A, getPaddingTop() + getPaddingBottom(), localLayoutParams.height);
    int i1;
    if (localLayoutParams.width > 0) {
      i1 = View.MeasureSpec.makeMeasureSpec(localLayoutParams.width, 1073741824);
    } else {
      i1 = View.MeasureSpec.makeMeasureSpec(0, 0);
    }
    paramView.measure(i1, i2);
  }
  
  private void a(View paramView, int paramInt)
  {
    addViewInLayout(paramView, paramInt, b(paramView), true);
    a(paramView);
  }
  
  private void a(Boolean paramBoolean)
  {
    if (this.C != paramBoolean.booleanValue())
    {
      Object localObject = this;
      while ((((View)localObject).getParent() instanceof View)) {
        if ((!(((View)localObject).getParent() instanceof ListView)) && (!(((View)localObject).getParent() instanceof ScrollView)))
        {
          localObject = (View)((View)localObject).getParent();
        }
        else
        {
          ((View)localObject).getParent().requestDisallowInterceptTouchEvent(paramBoolean.booleanValue());
          this.C = paramBoolean.booleanValue();
        }
      }
    }
  }
  
  private View b(int paramInt)
  {
    paramInt = this.b.getItemViewType(paramInt);
    if (c(paramInt)) {
      return (View)((Queue)this.i.get(paramInt)).poll();
    }
    return null;
  }
  
  private ViewGroup.LayoutParams b(View paramView)
  {
    ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
    paramView = localLayoutParams;
    if (localLayoutParams == null) {
      paramView = new ViewGroup.LayoutParams(-2, -1);
    }
    return paramView;
  }
  
  private void b()
  {
    a();
    removeAllViewsInLayout();
    requestLayout();
  }
  
  private void b(int paramInt1, int paramInt2)
  {
    while ((paramInt1 + paramInt2 - this.m >= 0) && (this.q >= 1))
    {
      this.q -= 1;
      View localView = this.b.getView(this.q, b(this.q), this);
      a(localView, 0);
      if (this.q == 0) {
        i1 = localView.getMeasuredWidth();
      } else {
        i1 = this.m + localView.getMeasuredWidth();
      }
      int i1 = paramInt1 - i1;
      int i2 = this.h;
      if (i1 + paramInt2 == 0)
      {
        paramInt1 = localView.getMeasuredWidth();
      }
      else
      {
        paramInt1 = this.m;
        paramInt1 = localView.getMeasuredWidth() + paramInt1;
      }
      this.h = (i2 - paramInt1);
      paramInt1 = i1;
    }
  }
  
  private void b(Canvas paramCanvas)
  {
    int i2 = getChildCount();
    Rect localRect = this.k;
    this.k.top = getPaddingTop();
    this.k.bottom = (this.k.top + getRenderHeight());
    int i1 = 0;
    while (i1 < i2)
    {
      if ((i1 != i2 - 1) || (!h(this.r)))
      {
        View localView = getChildAt(i1);
        localRect.left = localView.getRight();
        localRect.right = (localView.getRight() + this.m);
        if (localRect.left < getPaddingLeft()) {
          localRect.left = getPaddingLeft();
        }
        if (localRect.right > getWidth() - getPaddingRight()) {
          localRect.right = (getWidth() - getPaddingRight());
        }
        a(paramCanvas, localRect);
        if ((i1 == 0) && (localView.getLeft() > getPaddingLeft()))
        {
          localRect.left = getPaddingLeft();
          localRect.right = localView.getLeft();
          a(paramCanvas, localRect);
        }
      }
      i1 += 1;
    }
  }
  
  private float c()
  {
    if (Build.VERSION.SDK_INT >= 14) {
      return IceCreamSandwichPlus.getCurrVelocity(this.a);
    }
    return 30.0F;
  }
  
  private int c(int paramInt1, int paramInt2)
  {
    int i2 = getChildCount();
    int i1 = 0;
    while (i1 < i2)
    {
      getChildAt(i1).getHitRect(this.k);
      if (this.k.contains(paramInt1, paramInt2)) {
        return i1;
      }
      i1 += 1;
    }
    return -1;
  }
  
  private boolean c(int paramInt)
  {
    return paramInt < this.i.size();
  }
  
  private void d(int paramInt)
  {
    View localView = getRightmostChild();
    int i2 = 0;
    if (localView != null) {
      i1 = localView.getRight();
    } else {
      i1 = 0;
    }
    a(i1, paramInt);
    localView = getLeftmostChild();
    int i1 = i2;
    if (localView != null) {
      i1 = localView.getLeft();
    }
    b(i1, paramInt);
  }
  
  private boolean d()
  {
    if (h(this.r))
    {
      View localView = getRightmostChild();
      if (localView != null)
      {
        int i1 = this.p;
        this.p = (this.c + (localView.getRight() - getPaddingLeft()) - getRenderWidth());
        if (this.p < 0) {
          this.p = 0;
        }
        if (this.p != i1) {
          return true;
        }
      }
    }
    return false;
  }
  
  private void e()
  {
    if (this.l != null)
    {
      this.l.setPressed(false);
      refreshDrawableState();
      this.l = null;
    }
  }
  
  private void e(int paramInt)
  {
    View localView;
    for (;;)
    {
      localView = getLeftmostChild();
      if ((localView == null) || (localView.getRight() + paramInt > 0)) {
        break;
      }
      int i2 = this.h;
      int i1;
      if (h(this.q)) {
        i1 = localView.getMeasuredWidth();
      } else {
        i1 = this.m + localView.getMeasuredWidth();
      }
      this.h = (i2 + i1);
      a(this.q, localView);
      removeViewInLayout(localView);
      this.q += 1;
    }
    for (;;)
    {
      localView = getRightmostChild();
      if ((localView == null) || (localView.getLeft() + paramInt < getWidth())) {
        break;
      }
      a(this.r, localView);
      removeViewInLayout(localView);
      this.r -= 1;
    }
  }
  
  private void f()
  {
    if (this.y != null) {
      this.y.onRelease();
    }
    if (this.z != null) {
      this.z.onRelease();
    }
  }
  
  private void f(int paramInt)
  {
    int i3 = getChildCount();
    int i4 = this.H;
    int i2 = 0;
    this.H = 0;
    if (i3 > 0)
    {
      this.h += paramInt;
      int i1 = this.h;
      paramInt = i2;
      while (paramInt < i3)
      {
        View localView = getChildAt(paramInt);
        i2 = getPaddingLeft() + i1;
        int i5 = getPaddingTop();
        int i6 = localView.getMeasuredWidth();
        int i7 = localView.getMeasuredHeight() + i5;
        this.H = Math.max(this.H, getPaddingBottom() + i7);
        localView.layout(i2, i5, i6 + i2, i7);
        i1 += localView.getMeasuredWidth() + this.m;
        paramInt += 1;
      }
      if (i4 == 0) {
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            HorizontalListView.this.requestLayout();
          }
        }, 96L);
      }
    }
  }
  
  private View g(int paramInt)
  {
    if ((paramInt >= this.q) && (paramInt <= this.r)) {
      return getChildAt(paramInt - this.q);
    }
    return null;
  }
  
  private void g()
  {
    if ((this.t != null) && (this.b != null) && (this.b.getCount() - (this.r + 1) < this.u) && (!this.v))
    {
      this.v = true;
      this.t.onRunningOutOfData();
    }
  }
  
  private View getLeftmostChild()
  {
    return getChildAt(0);
  }
  
  private int getRenderHeight()
  {
    return getHeight() - getPaddingTop() - getPaddingBottom();
  }
  
  private int getRenderWidth()
  {
    return getWidth() - getPaddingLeft() - getPaddingRight();
  }
  
  private View getRightmostChild()
  {
    return getChildAt(getChildCount() - 1);
  }
  
  private boolean h()
  {
    ListAdapter localListAdapter = this.b;
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (localListAdapter != null)
    {
      if (this.b.isEmpty()) {
        return false;
      }
      bool1 = bool2;
      if (this.p > 0) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private boolean h(int paramInt)
  {
    return paramInt == this.b.getCount() - 1;
  }
  
  private void i(int paramInt)
  {
    if (this.y != null)
    {
      if (this.z == null) {
        return;
      }
      int i1 = this.c + paramInt;
      if ((this.a == null) || (this.a.isFinished()))
      {
        EdgeEffect localEdgeEffect;
        if (i1 < 0)
        {
          paramInt = Math.abs(paramInt);
          this.y.onPull(paramInt / getRenderWidth());
          if (this.z.isFinished()) {
            return;
          }
          localEdgeEffect = this.z;
        }
        else
        {
          if (i1 <= this.p) {
            return;
          }
          paramInt = Math.abs(paramInt);
          this.z.onPull(paramInt / getRenderWidth());
          if (this.y.isFinished()) {
            return;
          }
          localEdgeEffect = this.y;
        }
        localEdgeEffect.onRelease();
      }
    }
  }
  
  private void setCurrentScrollState(HorizontalListView.OnScrollStateChangedListener.ScrollState paramScrollState)
  {
    if ((this.x != paramScrollState) && (this.w != null)) {
      this.w.onScrollStateChanged(paramScrollState);
    }
    this.x = paramScrollState;
  }
  
  protected boolean a(MotionEvent paramMotionEvent)
  {
    this.B = (this.a.isFinished() ^ true);
    this.a.forceFinished(true);
    setCurrentScrollState(HorizontalListView.OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE);
    e();
    if (!this.B)
    {
      int i1 = c((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
      if (i1 >= 0)
      {
        this.l = getChildAt(i1);
        if (this.l != null)
        {
          this.l.setPressed(true);
          refreshDrawableState();
        }
      }
    }
    return true;
  }
  
  protected boolean a(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    this.a.fling(this.d, 0, (int)-paramFloat1, 0, 0, this.p, 0, 0);
    setCurrentScrollState(HorizontalListView.OnScrollStateChangedListener.ScrollState.SCROLL_STATE_FLING);
    requestLayout();
    return true;
  }
  
  protected void dispatchDraw(Canvas paramCanvas)
  {
    Object localObject;
    if (this.E != null)
    {
      localObject = new Paint();
      ((Paint)localObject).setColor(-2004318072);
      paramCanvas.drawRect(this.E, (Paint)localObject);
    }
    if ((this.s >= 0) && (this.q <= this.s) && (this.r >= this.s) && (this.G != null))
    {
      localObject = getSelectedView();
      if (localObject != null)
      {
        localObject = new Rect(((View)localObject).getLeft(), ((View)localObject).getTop(), ((View)localObject).getRight(), ((View)localObject).getBottom());
        this.G.setBounds((Rect)localObject);
        this.G.draw(paramCanvas);
      }
    }
    super.dispatchDraw(paramCanvas);
    if ((this.s >= 0) && (this.q <= this.s) && (this.r >= this.s))
    {
      localObject = getSelectedView();
      if (localObject != null)
      {
        localObject = new Rect(((View)localObject).getLeft(), ((View)localObject).getBottom() - a(2.0F), ((View)localObject).getRight(), ((View)localObject).getBottom());
        Paint localPaint = new Paint();
        localPaint.setColor(this.F);
        paramCanvas.drawRect((Rect)localObject, localPaint);
      }
    }
    a(paramCanvas);
  }
  
  protected void dispatchSetPressed(boolean paramBoolean) {}
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = super.dispatchTouchEvent(paramMotionEvent) | this.g.onTouchEvent(paramMotionEvent);
    int i1 = paramMotionEvent.getAction();
    if (i1 != 3) {
      switch (i1)
      {
      default: 
        return bool;
      }
    }
    for (this.E = null;; this.E = new Rect(paramMotionEvent.getLeft(), paramMotionEvent.getTop(), paramMotionEvent.getRight(), paramMotionEvent.getBottom()))
    {
      invalidate();
      return bool;
      i1 = c((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
      if (i1 < 0) {
        break;
      }
      paramMotionEvent = getChildAt(i1);
    }
    return bool;
  }
  
  public ListAdapter getAdapter()
  {
    return this.b;
  }
  
  public int getFirstVisiblePosition()
  {
    return this.q;
  }
  
  public int getLastVisiblePosition()
  {
    return this.r;
  }
  
  protected float getLeftFadingEdgeStrength()
  {
    int i1 = getHorizontalFadingEdgeLength();
    if (this.c == 0) {
      return 0.0F;
    }
    if (this.c < i1) {
      return this.c / i1;
    }
    return 1.0F;
  }
  
  protected float getRightFadingEdgeStrength()
  {
    int i1 = getHorizontalFadingEdgeLength();
    if (this.c == this.p) {
      return 0.0F;
    }
    if (this.p - this.c < i1) {
      return (this.p - this.c) / i1;
    }
    return 1.0F;
  }
  
  public View getSelectedView()
  {
    return g(this.s);
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    b(paramCanvas);
  }
  
  @SuppressLint({"WrongCall"})
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.b == null) {
      return;
    }
    invalidate();
    if (this.j)
    {
      i1 = this.c;
      a();
      removeAllViewsInLayout();
      this.d = i1;
      this.j = false;
    }
    if (this.o != null)
    {
      this.d = this.o.intValue();
      this.o = null;
    }
    if (this.a.computeScrollOffset()) {
      this.d = this.a.getCurrX();
    }
    if (this.d < 0)
    {
      this.d = 0;
      if (!this.y.isFinished()) {}
    }
    for (EdgeEffect localEdgeEffect = this.y;; localEdgeEffect = this.z)
    {
      localEdgeEffect.onAbsorb((int)c());
      do
      {
        this.a.forceFinished(true);
        setCurrentScrollState(HorizontalListView.OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE);
        break;
        if (this.d <= this.p) {
          break;
        }
        this.d = this.p;
      } while (!this.z.isFinished());
    }
    int i1 = this.c - this.d;
    e(i1);
    d(i1);
    f(i1);
    this.c = this.d;
    if (d())
    {
      onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    if (this.a.isFinished())
    {
      if (this.x == HorizontalListView.OnScrollStateChangedListener.ScrollState.SCROLL_STATE_FLING) {
        setCurrentScrollState(HorizontalListView.OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE);
      }
    }
    else {
      postOnAnimation(this.J);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (View.MeasureSpec.getMode(paramInt2) == Integer.MIN_VALUE) {
      setMeasuredDimension(paramInt1, View.MeasureSpec.makeMeasureSpec(this.H, 1073741824));
    }
    this.A = paramInt2;
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if ((paramParcelable instanceof Bundle))
    {
      paramParcelable = (Bundle)paramParcelable;
      this.o = Integer.valueOf(paramParcelable.getInt("BUNDLE_ID_CURRENT_X"));
      super.onRestoreInstanceState(paramParcelable.getParcelable("BUNDLE_ID_PARENT_STATE"));
    }
  }
  
  public Parcelable onSaveInstanceState()
  {
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("BUNDLE_ID_PARENT_STATE", super.onSaveInstanceState());
    localBundle.putInt("BUNDLE_ID_CURRENT_X", this.c);
    return localBundle;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i1 = paramMotionEvent.getAction();
    if (i1 != 1)
    {
      if (i1 == 3)
      {
        e();
        f();
        a(Boolean.valueOf(false));
      }
    }
    else
    {
      if ((this.a == null) || (this.a.isFinished())) {
        setCurrentScrollState(HorizontalListView.OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE);
      }
      a(Boolean.valueOf(false));
      f();
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  public void scrollTo(int paramInt)
  {
    this.a.startScroll(this.d, 0, paramInt - this.d, 0);
    setCurrentScrollState(HorizontalListView.OnScrollStateChangedListener.ScrollState.SCROLL_STATE_FLING);
    requestLayout();
  }
  
  public void setAdapter(ListAdapter paramListAdapter)
  {
    if (this.b != null) {
      this.b.unregisterDataSetObserver(this.I);
    }
    if (paramListAdapter != null)
    {
      this.v = false;
      this.b = paramListAdapter;
      this.b.registerDataSetObserver(this.I);
    }
    a(this.b.getViewTypeCount());
    b();
  }
  
  public void setDivider(Drawable paramDrawable)
  {
    this.n = paramDrawable;
    if (paramDrawable != null) {}
    for (int i1 = paramDrawable.getIntrinsicWidth();; i1 = 0)
    {
      setDividerWidth(i1);
      return;
    }
  }
  
  public void setDividerWidth(int paramInt)
  {
    this.m = paramInt;
    requestLayout();
    invalidate();
  }
  
  public void setOnClickListener(View.OnClickListener paramOnClickListener)
  {
    this.D = paramOnClickListener;
  }
  
  public void setOnScrollStateChangedListener(OnScrollStateChangedListener paramOnScrollStateChangedListener)
  {
    this.w = paramOnScrollStateChangedListener;
  }
  
  public void setRunningOutOfDataListener(RunningOutOfDataListener paramRunningOutOfDataListener, int paramInt)
  {
    this.t = paramRunningOutOfDataListener;
    this.u = paramInt;
  }
  
  public void setSelectedBackground(Drawable paramDrawable)
  {
    this.G = paramDrawable;
    invalidate();
  }
  
  public void setSelectedBackgroundColor(int paramInt)
  {
    this.G = new ColorDrawable(paramInt);
    invalidate();
  }
  
  public void setSelectedColor(int paramInt)
  {
    this.F = paramInt;
    invalidate();
  }
  
  public void setSelection(int paramInt)
  {
    this.s = paramInt;
    if ((this.b != null) && (paramInt >= 0) && (paramInt <= this.b.getCount()))
    {
      View localView = getSelectedView();
      if ((localView == null) || (localView.getRight() > getWidth()) || (localView.getLeft() < 0))
      {
        localView = null;
        int i1 = 0;
        int i2 = 0;
        while (i1 < paramInt - 1)
        {
          localView = this.b.getView(i1, localView, this);
          a(localView);
          int i3;
          if (i1 == 0) {
            i3 = localView.getMeasuredWidth();
          } else {
            i3 = this.m + localView.getMeasuredWidth();
          }
          i2 += i3;
          i1 += 1;
        }
        scrollTo(i2);
      }
      invalidate();
      return;
    }
    invalidate();
  }
  
  private class GestureListener
    extends GestureDetector.SimpleOnGestureListener
  {
    private GestureListener() {}
    
    public boolean onDown(MotionEvent paramMotionEvent)
    {
      return HorizontalListView.this.a(paramMotionEvent);
    }
    
    public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
    {
      HorizontalListView.a(HorizontalListView.this, null);
      return HorizontalListView.this.a(paramMotionEvent1, paramMotionEvent2, paramFloat1, paramFloat2);
    }
    
    public void onLongPress(MotionEvent paramMotionEvent)
    {
      HorizontalListView.b(HorizontalListView.this);
      int i = HorizontalListView.a(HorizontalListView.this, (int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
      if ((i >= 0) && (!HorizontalListView.d(HorizontalListView.this)))
      {
        paramMotionEvent = HorizontalListView.this.getChildAt(i);
        AdapterView.OnItemLongClickListener localOnItemLongClickListener = HorizontalListView.this.getOnItemLongClickListener();
        if (localOnItemLongClickListener != null)
        {
          i = HorizontalListView.e(HorizontalListView.this) + i;
          if (localOnItemLongClickListener.onItemLongClick(HorizontalListView.this, paramMotionEvent, i, HorizontalListView.this.b.getItemId(i))) {
            HorizontalListView.this.performHapticFeedback(0);
          }
        }
      }
    }
    
    public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
    {
      HorizontalListView.a(HorizontalListView.this, null);
      HorizontalListView.a(HorizontalListView.this, Boolean.valueOf(true));
      HorizontalListView.a(HorizontalListView.this, HorizontalListView.OnScrollStateChangedListener.ScrollState.SCROLL_STATE_TOUCH_SCROLL);
      HorizontalListView.b(HorizontalListView.this);
      paramMotionEvent1 = HorizontalListView.this;
      paramMotionEvent1.d += (int)paramFloat1;
      HorizontalListView.a(HorizontalListView.this, Math.round(paramFloat1));
      HorizontalListView.this.requestLayout();
      return true;
    }
    
    public boolean onSingleTapConfirmed(MotionEvent paramMotionEvent)
    {
      HorizontalListView.b(HorizontalListView.this);
      AdapterView.OnItemClickListener localOnItemClickListener = HorizontalListView.this.getOnItemClickListener();
      int i = HorizontalListView.a(HorizontalListView.this, (int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
      if ((i >= 0) && (!HorizontalListView.d(HorizontalListView.this)))
      {
        paramMotionEvent = HorizontalListView.this.getChildAt(i);
        i = HorizontalListView.e(HorizontalListView.this) + i;
        if (localOnItemClickListener != null)
        {
          localOnItemClickListener.onItemClick(HorizontalListView.this, paramMotionEvent, i, HorizontalListView.this.b.getItemId(i));
          return true;
        }
      }
      if ((HorizontalListView.f(HorizontalListView.this) != null) && (!HorizontalListView.d(HorizontalListView.this))) {
        HorizontalListView.f(HorizontalListView.this).onClick(HorizontalListView.this);
      }
      return false;
    }
  }
  
  @TargetApi(11)
  private static final class HoneycombPlus
  {
    static
    {
      if (Build.VERSION.SDK_INT < 11) {
        throw new RuntimeException("Should not get to HoneycombPlus class unless sdk is >= 11!");
      }
    }
    
    public static void setFriction(Scroller paramScroller, float paramFloat)
    {
      if (paramScroller != null) {
        paramScroller.setFriction(paramFloat);
      }
    }
  }
  
  @TargetApi(14)
  private static final class IceCreamSandwichPlus
  {
    static
    {
      if (Build.VERSION.SDK_INT < 14) {
        throw new RuntimeException("Should not get to IceCreamSandwichPlus class unless sdk is >= 14!");
      }
    }
    
    public static float getCurrVelocity(Scroller paramScroller)
    {
      return paramScroller.getCurrVelocity();
    }
  }
  
  public static abstract interface OnScrollStateChangedListener
  {
    public abstract void onScrollStateChanged(ScrollState paramScrollState);
    
    public static enum ScrollState
    {
      private ScrollState() {}
    }
  }
  
  public static abstract interface RunningOutOfDataListener
  {
    public abstract void onRunningOutOfData();
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\widget\HorizontalListView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */